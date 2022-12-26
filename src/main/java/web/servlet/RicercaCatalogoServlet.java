package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import web.bean.RicercaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RicercaCatalogoServlet")
public class RicercaCatalogoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static RicercaBean rB=new RicercaBean();
	private static LibroDao lD=new LibroDao();
	private static RivistaDao rD=new RivistaDao();
	private static GiornaleDao gD=new GiornaleDao();
	private static String beanRicerca="beanRicerca";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String titolo=req.getParameter("cercaL");
		String cercaB=req.getParameter("cercaB");
		String visualizza=req.getParameter("visualizzaB");
		String indietro=req.getParameter("buttonI");
		String type=SystemBean.getIstance().getType();
		
		try {
		if(cercaB!=null && cercaB.equals("cerca"))
		{
			switch(type)
			{
			case "libro":
				rB.setLista(lD.getLibriByNameL(titolo));
				req.setAttribute(beanRicerca,rB);
				break;
			case "giornale":
				rB.setLista(gD.getGiornaliByNameL(titolo));
				req.setAttribute(beanRicerca,rB);				
				break;
			case "rivista":
				rB.setLista(rD.getRivistaSingoloL());
				req.setAttribute(beanRicerca, rB);
				break;
				default:break;
				
			}
			RequestDispatcher view=getServletContext().getRequestDispatcher("/ricercaInCatalogo.jsp");
			view.forward(req, resp);
		}
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/ricerca.jsp");
			view.forward(req, resp);
		}
		if(visualizza!=null && visualizza.equals("visualizza"))
		{
			RequestDispatcher view;
			switch(type)
			{
			case "libro":
				 view=getServletContext().getRequestDispatcher("/libri.jsp");
				view.forward(req, resp);
				break;
			case "giornale":
				 view=getServletContext().getRequestDispatcher("/giornali.jsp");
				view.forward(req, resp);	
				break;
			case "rivista":
				 view=getServletContext().getRequestDispatcher("/riviste.jsp");
				view.forward(req, resp);
				break;
				default:break;
				
			}
			}
		
		
		
	}catch(SQLException e)
		{
		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}
	

}
