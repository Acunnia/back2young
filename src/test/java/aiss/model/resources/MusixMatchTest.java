package aiss.model.resources;

import aiss.model.musixmatch.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

public class MusixMatchTest {
	

	@Test
	public void testGetLyrics() throws UnsupportedEncodingException {
		String artista = "AC/DC";
		String cancion = "Thunderstruck";
		MusixMatchResource musixmatch = new MusixMatchResource();
		Musixmatch musixmatchResults = musixmatch.getLyrics(artista, cancion);
		
		assertNotNull("The search of lyrics is not null", musixmatchResults.getMessage().getBody().getLyrics().getLyricsBody());
		
		
		//System.out.println("The search of songs's lyric " + cancion + " from artist " + artista + " returned: \n\n" + musixmatchResults.getMessage().getBody().getLyrics().getLyricsBody());
	}
}
