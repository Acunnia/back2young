package aiss.controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.SpotifyResource;
import aiss.model.resources.YoutubeResource;
import aiss.model.spotify.SearchTracks;
import aiss.model.youtube.VideoMusicSearch;

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
		String songName = request.getParameter("songName");
		String query = request.getParameter("query");
		String accessToken = (String) request.getSession().getAttribute("Spotify-token");
		RequestDispatcher rd = null;

		if (accessToken != null && !"".equals(accessToken)) {

			// Search for tracks in Spotify
			log.log(Level.FINE, "Searching for Spotify results of " + songName);
			SpotifyResource spotify = new SpotifyResource(accessToken);
			SearchTracks spotifyResults = spotify.getTopSearch(songName);
			SearchTracks spotifyResultsName = spotify.getTrackSearchName(query);

			if (spotifyResults != null) {
				rd = request.getRequestDispatcher("/youtubeSpotify.jsp");
				YoutubeResource youtube = new YoutubeResource();
				VideoMusicSearch videoResults = youtube.getVideo(songName);
				request.setAttribute("video", videoResults.getItems().get(0).getId().getVideoId());
			} else {
				log.log(Level.SEVERE, "Spotify object: " + spotifyResults);
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