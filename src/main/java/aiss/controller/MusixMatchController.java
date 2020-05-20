package aiss.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.musixmatch.Lyrics;
import aiss.model.resources.MusixMatchResource;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusixMatchController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(MusixMatchController.class.getName());
	
	/**
     * @see HttpServlet#HttpServlet()
     */
	public MusixMatchController() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryArtist = request.getParameter("queryArtist");
		String queryTrack = request.getParameter("queryTrack");
		RequestDispatcher rd = null;
		
		log.log(Level.FINE, "Searching lyrics results in MusixMatch of song" + queryTrack + "from artist" + queryArtist);
		MusixMatchResource musixmatch = new MusixMatchResource();
		Lyrics musixMatchLyrics = musixmatch.getLyricsResource(queryArtist, queryTrack);
		
		if(musixMatchLyrics != null) {
			rd = request.getRequestDispatcher("/pruebaMusixMatch.jsp");
			request.setAttribute("lyrics", musixMatchLyrics.getLyrics());
		} else {
			log.log(Level.SEVERE, "MusixMatch lyrics: " + musixMatchLyrics);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}