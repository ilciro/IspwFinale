package web.servlet;

import java.io.IOException;

import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * TODO fare singeltonBean/systemBean
		 */
		String l=req.getParameter("buttonL");
		String g=req.getParameter("buttonG");
		String r=req.getParameter("buttonR");
		String log=req.getParameter("buttonLogin");
		String ric=req.getParameter("buttonRic");
		if(l!=null && l.equals("libri"))
		{
			SystemBean.getIstance().setTypeAsBook();

			 RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
				view.forward(req,resp); 
		}
		if( g!=null && g.equals("giornali"))
		{
				SystemBean.getIstance().setTypeAsDaily();
				 RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
					view.forward(req,resp); 	
		}
		if(r!=null && r.equals("riviste"))
		{
			SystemBean.getIstance().setTypeAsMagazine();
			 RequestDispatcher view = getServletContext().getRequestDispatcher("/riviste.jsp"); 
				view.forward(req,resp); 	
		}
		if(log!=null && log.equals("login"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp"); 
			view.forward(req,resp); 	
		}
		if(ric!=null && ric.equals("ricerca"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/ricerca.jsp"); 
			view.forward(req,resp); 	
		}
	}

}
