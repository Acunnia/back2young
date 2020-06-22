package aiss.model.resources;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.spotify.SearchTracks;


public class SpotifyTest {
	String token = "BQC9Ju73HsGX13fqkYgyC5c9QnTkMVFh8oYfErqz9jk1Tb1Ln7i3ddnP_PVc52OwirtfKr5Mfgp55JqbgVQT5HJxIMfXEYKrvhUblyDRdY454XxoFmhOfGLP2verIbjqYXnlQsngGKeYgJYML5_6F3sHOCCcafwW8ktYFeUOQMg98Yz6di3ynKm0U2mPOzBybXxnzQ";
	
	@Test
	public void testGetCancion() throws UnsupportedEncodingException {
		String songName = "Il Tempo";
		SpotifyResource acceso = new SpotifyResource(token);
		SearchTracks spotifyResults = acceso.getTrackSearchName(songName);
		assertNotNull("The search of track is not null", spotifyResults.getTracks().getItems().get(0));
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetCancionNull() throws UnsupportedEncodingException {
		String songName = " ";
		SpotifyResource acceso = new SpotifyResource(token);
		SearchTracks searchTacks = acceso.getTrackSearchName(songName);
		searchTacks.getTracks().getNext().length();
	}
}
