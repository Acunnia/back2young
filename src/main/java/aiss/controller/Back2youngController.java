package aiss.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.musixmatch.Lyrics;
import aiss.model.resources.MusixMatchResource;
import aiss.model.resources.SpotifyResource;
import aiss.model.spotify.Item;
import aiss.model.spotify.SearchTracks;

public class Back2youngController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(SpotifyController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Back2youngController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String songName = URLEncoder.encode(request.getParameter("songName"), "UTF-8");
		String accessToken = (String) request.getSession().getAttribute("Spotify-token");
		RequestDispatcher rd = null;

		if (accessToken != null && !"".equals(accessToken)) {

			// Search for tracks in Spotify
			log.log(Level.FINE, "Searching results of " + songName);
			SpotifyResource spotify = new SpotifyResource(accessToken);
			SearchTracks spotifyResultsName = spotify.getTrackSearchName(songName);
			

			if (spotifyResultsName != null) {
				Item findedTrack = spotifyResultsName.getTracks().getItems().get(0);
				
				rd = request.getRequestDispatcher("/youtubeSpotify.jsp");
				request.setAttribute("songName", songName.replace("%2B", "+"));
				request.setAttribute("trackSpotifyId", findedTrack.getId());
				Boolean fav = spotify.checkFavSong(findedTrack.getId());
				request.setAttribute("faved", fav);
				System.out.println("-----------------------------------" + fav);
				
				
//				YoutubeResource youtube = new YoutubeResource();
//				VideoMusicSearch videoResults = youtube.getVideo(findedTrack.getName() + " " +findedTrack.getName());
//				request.setAttribute("videoSong", videoResults.getItems().get(0).getId().getVideoId());
				
				MusixMatchResource musixmatch = new MusixMatchResource();
				Lyrics lyrics = musixmatch.getLyricsResource(findedTrack.getName(), findedTrack.getArtists().get(0).getName());
				request.setAttribute("lyricsSong", lyrics.getLyrics());
				
				
			} else {
				log.log(Level.SEVERE, "Spotify object: " + spotifyResultsName);
				rd = request.getRequestDispatcher("/error.jsp");
			}

		} else {
			log.info("Intentando acceder a Spotify sin un token de acceso, redirigiendo al OAuth servlet");
			rd = request.getRequestDispatcher("/AuthController/Spotify");
		}
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}