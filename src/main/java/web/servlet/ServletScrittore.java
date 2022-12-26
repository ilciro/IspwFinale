package web.servlet;

import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletScrittore")

public class ServletScrittore extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buttonL=req.getParameter("buttonL");
		String buttonG=req.getParameter("buttonG");
		String buttonR=req.getParameter("buttonR");
		String buttonGestione=req.getParameter("buttonGestione");
		String buttonRic=req.getParameter("buttonRic");
		//String logoutB=req.getParameter("logoutB");
		String profiloB=req.getParameter("profiloB");
		try {
		if(buttonL!=null && buttonL.equals("libri"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/libri.jsp");
			view.forward(req, resp);
		}
		if(buttonG!=null && buttonG.equals("giornali"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/giornali.jsp");
			view.forward(req, resp);
		}
		if(buttonR!=null && buttonR.equals("riviste"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/riviste.jsp");
			view.forward(req, resp);
		}
		if(buttonGestione!=null && buttonGestione.equals("gestione"))
		{
			
				RequestDispatcher view=getServletContext().getRequestDispatcher("/raccolta.jsp");
				view.forward(req, resp);
			
		}
		if(buttonRic!=null && buttonRic.equals("ricerca"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/ricerca.jsp");
			view.forward(req, resp);
		}
		/*
		if(logoutB!=null && logoutB.equals("logout") &&(UsersDao.logout()))
				{
					RequestDispatcher view=getServletContext().getRequestDispatcher("/index.jsp");
					view.forward(req, resp);
				}
			*/
		if(profiloB!=null && profiloB.equals("vai al profilo"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/profilo.jsp");
			view.forward(req, resp);
		}
		
		
	} catch ( ServletException | IOException e) {
		e.printStackTrace();
	}
	
	}
}
