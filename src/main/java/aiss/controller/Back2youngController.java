package aiss.controller;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String query = request.getParameter("searchQuery");
		String accessToken = (String) request.getSession().getAttribute("Spotify-token");
		RequestDispatcher rd = null;

		if (accessToken != null && !"".equals(accessToken)) {

			// Search for tracks in Spotify
			log.log(Level.FINE, "Searching for Spotify results of " + query);
			SpotifyResource spotify = new SpotifyResource(accessToken);
			SearchTracks spotifyResults = spotify.getTrackSearch(query);

			if (spotifyResults != null) {
				rd = request.getRequestDispatcher("/pruebaSpotify.jsp");
				request.setAttribute("tracks",spotifyResults.getTracks().getItems());
				request.setAttribute("video", spotifyResults.getTracks().getItems().get(0).getName());
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