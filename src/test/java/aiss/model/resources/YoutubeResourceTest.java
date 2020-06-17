package aiss.model.resources;

import org.junit.Test;


import aiss.model.youtube.VideoMusicSearch;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;



public class YoutubeResourceTest {

	@Test
	public void getVideoTest() throws UnsupportedEncodingException {
		String titulo = "BlackPink  Kill This Love";
		YoutubeResource youtube = new YoutubeResource();
		VideoMusicSearch youtubeResults = youtube.getVideo(titulo);
		
		assertNotNull("The search returned null", youtubeResults);
		assertNotNull("The search returned null", youtubeResults.getItems().get(0).getSnippet().getTitle());
		assertFalse("The number of videos is zero", youtubeResults.getItems().size()==0);
	}
	
	@Test (expected = NullPointerException.class)
	public void getNullVideo() throws UnsupportedEncodingException {
		String titulo = null;
		YoutubeResource youtube = new YoutubeResource();
		VideoMusicSearch youtubeResults = youtube.getVideo(titulo);
		
		assertNull("The search returned null", youtubeResults);
	}
	
}
