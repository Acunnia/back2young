package aiss.model.resources;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.UnsupportedEncodingException;
import aiss.model.spotify.*;


public class SpotifyTest {

	
	@Test
	public void testGetCancion() throws UnsupportedEncodingException {
		String token = "BQAnR8ablZAFBKCFbJflcURnErxj0eQrMJVMfOHJok9kQd3D__bhOByowk1IoWpF5BDY1g1ktF_SWmuE2dwOLGSkLmUquOxQJuMZJO15bBoWt51OZ7rDrCF6f1atBjVRPAgdZfUd-y4seTmGUNut87x6ylGvnYprWrPE3IqSCgCzqCD-vVRy0xBRaSFU_UT-ezqA9MhhQa7_t0d50ZWgW-BkV7w43_7s41ID13YvPp-7Ppmb5GP6J9c9t9zsM31lkXGBC4nnQqdf";
		String songName = "Il Tempo";
		SpotifyResource acceso = new SpotifyResource(token);
		SearchTracks spotifyResults = acceso.getTrackSearchName(songName);
		assertNotNull("The search of track is not null", spotifyResults.getTracks().getItems().get(0));
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetCancionNull() throws UnsupportedEncodingException {
		String token = "BQAnR8ablZAFBKCFbJflcURnErxj0eQrMJVMfOHJok9kQd3D__bhOByowk1IoWpF5BDY1g1ktF_SWmuE2dwOLGSkLmUquOxQJuMZJO15bBoWt51OZ7rDrCF6f1atBjVRPAgdZfUd";
		String songName = null;
		SpotifyResource acceso = new SpotifyResource(token);
		SearchTracks spotifyResults = acceso.getTrackSearchName(songName);
		assertNotNull("The search of track is not null", spotifyResults.getTracks().getItems().get(0));
	}
	
	
	
}
