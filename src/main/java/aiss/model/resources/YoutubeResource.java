package aiss.model.resources;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jboss.resteasy.logging.Logger;
import org.restlet.resource.ClientResource;

import aiss.model.youtube.VideoMusicSearch;

public class YoutubeResource {
	private String canionSearchUri = "https://www.googleapis.com/youtube/v3/search?type=video&videoCategory.id&maxResults=1"
			+ "&order=viewCount&part=snippet&q=";
	
	private static final String _YT_API_KEY = "AIzaSyCn2aknvGqUeF5OQBZr-xfHdCGhLUbwOKE";
	
	public VideoMusicSearch getVideo(String query) throws UnsupportedEncodingException {
		String queryFormatted = URLEncoder.encode(query, "UTF-8");
		String uri = "https://www.googleapis.com/youtube/v3/search?type=video&videoCategory.id&maxResults=1"
				+ "&order=viewCount&part=snippet&key=" + _YT_API_KEY +"&q="+queryFormatted;
		
		ClientResource cr = new ClientResource(uri);
		VideoMusicSearch youtubeSearch = cr.get(VideoMusicSearch.class);
		
		return youtubeSearch;
	}
}