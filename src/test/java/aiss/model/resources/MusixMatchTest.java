package aiss.model.resources;



import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import aiss.model.musixmatch.Lyrics;

public class MusixMatchTest {


    @Test
    public void testGetLyrics() throws UnsupportedEncodingException {
        String artista = "AC/DC";
        String cancion = "Thunderstruck";
        MusixMatchResource musixmatch = new MusixMatchResource();
        Lyrics musixmatchResults = musixmatch.getLyricsResource(artista, cancion);

        assertNotNull("The search of track is not null", musixmatchResults.getLyrics());

    }

    @Test (expected = NullPointerException.class)
    public void testGetNullLyrics() throws UnsupportedEncodingException {
        String artista = null;
        String cancion = null;
        MusixMatchResource musixmatch = new MusixMatchResource();
        Lyrics musixmatchResults = musixmatch.getLyricsResource(artista, cancion);

        assertNull("The search of track is null", musixmatchResults.getLyrics());

    }
}