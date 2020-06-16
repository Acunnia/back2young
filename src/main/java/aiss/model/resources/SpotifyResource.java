package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

import com.google.appengine.repackaged.com.google.gson.Gson;

import aiss.model.spotify.SearchTracks;
import aiss.model.spotify.UserProfile;

public class SpotifyResource {

	private String baseURL = "https://api.spotify.com/v1";
	private String topSearchUri1 = "https://api.spotify.com/v1/search?q=year:";
	private String topSearchUri2 = "&type=track&popularity=100&limit=5";
	private String trackSearchUri = "https://api.spotify.com/v1/search?type=track&limit=1&q=";
			
	private String accessToken = null;
	private static final Logger log=Logger.getLogger(SpotifyResource.class.getName());
	
	
	public SpotifyResource(String access_Token) {
		this.accessToken = access_Token;
	}
	
	public SearchTracks getTopSearch(String query) throws UnsupportedEncodingException{
		String json = null;
		ClientResource cr = null;
		try{
			cr = new ClientResource(topSearchUri1 + query + topSearchUri2 + "&access_token=" + accessToken);
			json = cr.get(String.class);
			log.log(Level.FINE,"Búsqueda realizada correctamente."+json);
			Map<String, Object> reqAttribs = cr.getRequestAttributes(); 
			Series<Header> headers = (Series<Header>)reqAttribs.get("org.restlet.http.headers"); 
			if (headers == null) { 
				headers = new Series<Header>(Header.class); 
				reqAttribs.put("org.restlet.http.headers", headers); 
			} 
				headers.add(new Header("Authorization:", "Bearer "+accessToken));
				ChallengeResponse chr = new ChallengeResponse(
							ChallengeScheme.HTTP_OAUTH_BEARER);
				chr.setRawValue(accessToken);
				cr.setChallengeResponse(chr);
				
			}catch (ResourceException re){
				System.err.println("Error cuando accedia a Spotify: " + cr.getResponse().getStatus());
		}
		 Gson gsonObj = new Gson();
		 SearchTracks sol = gsonObj.fromJson(json, SearchTracks.class);
         log.log(Level.FINE, "Búsqueda en Spotify realizada correctamente.");
        
         return sol;
	}
	
	public SearchTracks getTrackSearchName(String query) throws UnsupportedEncodingException{
		String json = null;
		ClientResource cr = null;
		try{
			cr = new ClientResource(trackSearchUri + query  + "&access_token=" + accessToken);
			json = cr.get(String.class);
			log.log(Level.FINE,"Búsqueda realizada correctamente."+json);
			Map<String, Object> reqAttribs = cr.getRequestAttributes(); 
			Series<Header> headers = (Series<Header>)reqAttribs.get("org.restlet.http.headers"); 
			if (headers == null) { 
				headers = new Series<Header>(Header.class); 
				reqAttribs.put("org.restlet.http.headers", headers); 
			} 
				headers.add(new Header("Authorization:", "Bearer "+accessToken));
				ChallengeResponse chr = new ChallengeResponse(
							ChallengeScheme.HTTP_OAUTH_BEARER);
				chr.setRawValue(accessToken);
				cr.setChallengeResponse(chr);
				
			}catch (ResourceException re){
				System.err.println("Error cuando accedia a Spotify: " + cr.getResponse().getStatus());
		}
		 Gson gsonObj = new Gson();
		 SearchTracks sol = gsonObj.fromJson(json, SearchTracks.class);
         log.log(Level.FINE, "Búsqueda en Spotify realizada correctamente.");
        
         return sol;
	}
	
	public Boolean checkFavSong(String songId) {
		String userId = this.getUserId();
		if (userId != null) {
			String checkUserFavURL = baseURL + "/me/tracks/contains?ids=" + songId;
			ClientResource cr = new ClientResource(checkUserFavURL);
			
			Map<String, Object> headers = cr.getRequestAttributes();
            headers.put("Authorization", "Bearer " + accessToken);

	        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	        chr.setRawValue(accessToken);
	        cr.setChallengeResponse(chr);
	        
	        log.info("Checking if user favs song with id: " + songId);
	        
	        try {
	        	return cr.get(String.class).contains("true");
			} catch (ResourceException re) {
				System.err.println(re);
				return null;
			}
		} else {
            log.warning("Error when getting userID from Spotify");
            return null;
		}
		
	}
	
	public void likeSong(String songId) {
		String userId = this.getUserId();
		if (userId != null) {
			String checkUserFavURL = baseURL + "/me/tracks?ids=" + songId;
			ClientResource cr = new ClientResource(checkUserFavURL);
			
			Map<String, Object> headers = cr.getRequestAttributes();
			headers.put("Authorization", "Bearer " + accessToken);

	        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	        chr.setRawValue(accessToken);
	        cr.setChallengeResponse(chr);
	        
	        log.info("Saving song: " + songId);
	        
	        try {
	        	cr.put("{}");
			} catch (ResourceException re) {
				System.err.println(re);
			}
		} else {
            log.warning("Error when getting userID from Spotify");
		}
	}
	
	public void dislikeSong(String songId) {
		String userId = this.getUserId();
		if (userId != null) {
			String checkUserFavURL = baseURL + "/me/tracks?ids=" + songId;
			ClientResource cr = new ClientResource(checkUserFavURL);
			
			Map<String, Object> headers = cr.getRequestAttributes();
            headers.put("Authorization", "Bearer " + accessToken);

	        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
	        chr.setRawValue(accessToken);
	        cr.setChallengeResponse(chr);
	        
	        log.info("Saving song: " + songId);
	        
	        try {
	        	cr.delete();
			} catch (ResourceException re) {
				System.err.println(re);
			}
		} else {
            log.warning("Error when getting userID from Spotify");
		}
	}
	
 	protected String getUserId() {
        String userProfileURL = baseURL + "/me";
        ClientResource cr = new ClientResource(userProfileURL);

        ChallengeResponse chr = new ChallengeResponse(ChallengeScheme.HTTP_OAUTH_BEARER);
        chr.setRawValue(accessToken);
        cr.setChallengeResponse(chr);

        log.info("Retrieving user profile");

        try {
            return cr.get(UserProfile.class).getId();

        } catch (ResourceException re) {
            log.warning("Error when retrieving the user profile: " + cr.getResponse().getStatus());
            log.warning(userProfileURL);
            return null;
        }
    }
	
}