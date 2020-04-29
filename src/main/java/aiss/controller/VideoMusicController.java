package aiss.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.resources.YoutubeResource;
import aiss.model.youtube.VideoMusicSearch;

public class VideoMusicController extends HttpServlet{
	private static final long seriarVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VideoMusicController.class.getName());
	
	public VideoMusicController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("searchQuery");
		RequestDispatcher rd = null;
		
		log.log(Level.FINE, "Searching for song with the title: " + query);
		
		YoutubeResource youtube = new YoutubeResource();
		VideoMusicSearch youtubeResults = youtube.getVideo(query);
		
		if(youtubeResults != null) {
			rd = request.getRequestDispatcher("/busquedasYT.jsp");
			request.setAttribute("videos", youtubeResults.getItems());
		} else {
			rd = request.getRequestDispatcher("/error.jsp");
		}
	
	}
}
