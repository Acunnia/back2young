package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;
import com.google.appengine.repackaged.com.google.gson.Gson;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;

import aiss.model.musixmatch.Lyrics;

public class MusixMatchResource {
	
	private static final Logger log = Logger.getLogger(MusixMatchResource.class.getName());
	
	public Lyrics getLyrics(String queryArtist, String queryTrack) throws UnsupportedEncodingException{
		String json = null;
		ClientResource cr = null;
		try {
			cr = new ClientResource("https://api.musixmatch.com/ws/1.1/matcher.lyrics.get?q_artist="+ queryArtist +"&q_track=" + queryTrack);
			json = cr.get(String.class);
			log.log(Level.FINE, "Búsqueda de la letra correcta." + json);
			Map<String, Object> reqAtrribs = cr.getRequestAttributes();
			Series<Header> headers = (Series<Header>)reqAtrribs.get("org.restlet.http.headers");
			if (headers == null) {
				headers = new Series<Header>(Header.class);
				reqAtrribs.put("org.restlet.http.headers", headers); 
			}
				headers.add(new Header("Authorization:", "Bearer "));
				ChallengeResponse chr = new ChallengeResponse(
						ChallengeScheme.HTTP_OAUTH_BEARER);
				cr.setChallengeResponse(chr);
				
		} catch (ResourceException re){
			System.err.println("Error cuando accedia a MusixMatch: " + cr.getResponse().getStatus());
	}
	
		Gson gsonObj = new Gson();
		Lyrics letra = gsonObj.fromJson(json, Lyrics.class);
        log.log(Level.FINE, "Búsqueda en MusixMatch realizada correctamente.");
       
        return letra;
	}
}
