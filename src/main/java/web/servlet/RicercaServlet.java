package web.servlet;

import java.io.IOException;

import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String beanS="beanS";
	private static String ricercaInCatalogo="/ricercaInCatalogo.jsp";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buttonL=req.getParameter("buttonL");
		String buttonG=req.getParameter("buttonG");
		String buttonR=req.getParameter("buttonR");
		String buttonI=req.getParameter("buttonI");
		if(buttonL!=null && buttonL.equals("libri"))
		{
			SystemBean.getIstance().setTypeAsBook();
			req.setAttribute(beanS,SystemBean.getIstance());
			RequestDispatcher view=getServletContext().getRequestDispatcher(ricercaInCatalogo);
			view.forward(req, resp);
		}
		if(buttonG!=null && buttonG.equals("giornali"))
		{
			SystemBean.getIstance().setTypeAsDaily();
			req.setAttribute(beanS,SystemBean.getIstance());
			RequestDispatcher view=getServletContext().getRequestDispatcher(ricercaInCatalogo);
			view.forward(req, resp);
		}
		if(buttonR!=null && buttonR.equals("riviste"))
		{
			SystemBean.getIstance().setTypeAsMagazine();
			req.setAttribute(beanS,SystemBean.getIstance());
			RequestDispatcher view=getServletContext().getRequestDispatcher(ricercaInCatalogo);
			view.forward(req, resp);
		}
		if(buttonI!=null && buttonI.equals("indietro"))
		{
			if(SystemBean.getIstance().getIsLogged())
			{
				/*
				 * TODO vedere
				 */
			}
			else {
					RequestDispatcher view=getServletContext().getRequestDispatcher("/index.jsp");
					view.forward(req, resp);
			}
		}
			
	}

	
}
