package aiss.model.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import aiss.model.musixmatch.Musixmatch;

public class MusixMatchResource {
	
	private static final Logger log = Logger.getLogger(MusixMatchResource.class.getName());
	private static final String key = "b1e4daea5217abcdd3ba8a23ec24b16d";
	
	public Musixmatch getLyrics(String queryArtist, String queryTrack) throws UnsupportedEncodingException{
		Musixmatch letra = null;
		ClientResource cr = null;
		try {
			String uri = "https://api.musixmatch.com/ws/1.1/matcher.lyrics.get?q_artist="+ URLEncoder.encode(queryArtist, "UTF-8") + "&q_track=" + URLEncoder.encode(queryTrack, "UTF-8") + "&apikey=" + key;
			cr = new ClientResource(uri);
			letra = cr.get(Musixmatch.class);
			
		} catch (ResourceException re){
			System.err.println("Error cuando accedia a MusixMatch: " + cr.getResponse().getStatus());
			
		} catch (IOException e){
			log.log(Level.SEVERE, " There was an IOException " + cr.getResponse().getStatus());
			
		}

        log.log(Level.FINE, "Búsqueda en MusixMatch realizada correctamente.");
       
        return letra;
	}
}
