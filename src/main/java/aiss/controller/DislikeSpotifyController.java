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

public class DislikeSpotifyController extends HttpServlet{
private static final long serialVersionUID = 1L;

	
	private static final Logger log = Logger.getLogger(SpotifyController.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DislikeSpotifyController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String songId = (String) request.getParameter("trackId");
		String accessToken = (String) request.getSession().getAttribute("Spotify-token");
		RequestDispatcher rd = null;

		if (accessToken != null && !"".equals(accessToken)) {

			log.log(Level.FINE, "Sving song " + songId);
			SpotifyResource spotify = new SpotifyResource(accessToken);
			spotify.dislikeSong(songId);
			
			request.setAttribute("songName", request.getParameter("songName").replace("%2", "+"));
			rd = request.getRequestDispatcher("/Back2youngController");
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