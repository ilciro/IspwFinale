package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;

@WebServlet("/GestioneOggettoServlet")
public class GestioneOggettoServlet extends HttpServlet {
	
	public GestioneOggettoServlet()
	{
		super();
		try {
		if (SystemBean.getIstance().getType().equals(libro))
			mOB.setMiaLista(lD.getLibri());
			
		if(SystemBean.getIstance().getType().equals(giornale))
			mOB.setMiaLista(gD.getGiornali());
		if(SystemBean.getIstance().getType().equals(rivista))
			mOB.setMiaLista(rD.getRiviste());
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("costruttore ").log(Level.INFO, "eccezione nel costruttore {0}.",e.toString());

		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static ModificaOggettoBean mOB=new ModificaOggettoBean();
	private static GiornaleBean gB=new GiornaleBean();
	private static RivistaBean rB=new RivistaBean();
	private static Giornale g=new Giornale();
	private static Libro l=new Libro();
	private static Rivista r=new Rivista();
	private static LibroDao lD=new LibroDao();
	private static RivistaDao rD=new RivistaDao();
	private static GiornaleDao gD=new GiornaleDao();
	private static String libro="libro";
	private static String rivista="rivista";
	private static String giornale ="giornale";
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String genera=req.getParameter("buttonGenera");
		String aggiungi=req.getParameter("buttonAdd");
		String id=req.getParameter("idL");
		String modifica=req.getParameter("buttonMod");
		String cancella=req.getParameter("buttonCanc");
		String indietro=req.getParameter("buttonI");
		
		String type=SystemBean.getIstance().getType();
		
		try {
		if(genera!=null && genera.equals("genera lista"))
		{
			req.setAttribute("beanMOB",mOB);
			req.setAttribute("beanS",SystemBean.getIstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggetto.jsp"); 
			view.forward(req,resp);
		}
		if(aggiungi!=null && aggiungi.equals("inserisci"))
		{
			req.setAttribute("bean1",SystemBean.getIstance());

			RequestDispatcher view=getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
			view.forward(req, resp);
		}
		
		if(modifica!=null && modifica.equals("modifica"))
		{
			checkModifica(type,id);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		
		if(cancella!=null && cancella.equals("cancella") && !"".equals(id))
		{
			
			checkCancella(type,id);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/raccolta.jsp");
			view.forward(req, resp);
		}
		
		
	
	

	
	}catch(SQLException e)
	{
		java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

	}
	}
	//used for checkData before modif

	private void checkModifica(String type,String  id)
	{
		if(!"".equals(id))
		{
			if(type.equals(libro))
			{
				lB.setId(Integer.parseInt(id));
				SystemBean.getIstance().setId(lB.getId());
				l.setId(lB.getId());
			}
			else if(type.equals( giornale))
			{
				gB.setId(Integer.parseInt(id));
				SystemBean.getIstance().setId(gB.getId());
				g.setId(gB.getId());
			}	
			else if(type.equals(rivista))
			{
				rB.setId(Integer.parseInt(id));
				SystemBean.getIstance().setId(rB.getId());
				r.setId(rB.getId());
			
			}
		}
	}
	//used for checkData before delete
	private void checkCancella(String type,String  id) throws SQLException
	{
		if(!"".equals(id))
		{
			if(type.equals(libro))
			{
				lB.setId(Integer.parseInt(type));
				l.setId(lB.getId());
				lD.cancella(l);
			}
			else if(type.equals(giornale))
			{
				gB.setId(Integer.parseInt(id));
				g.setId(gB.getId());
				gD.cancella(g);
			}
			else if(type.equals(rivista))
			{
			
				rB.setId(Integer.parseInt(id));
				r.setId(rB.getId());
				rD.cancella(r);
			}
		}
	}
}
