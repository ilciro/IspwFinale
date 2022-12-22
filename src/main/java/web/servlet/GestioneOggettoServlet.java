package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;
import laptop.utilities.ConnToDb;

@WebServlet("/GestioneOggettoServlet")
public class GestioneOggettoServlet extends HttpServlet {

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
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String genera=req.getParameter("buttonGenera");
		String aggiungi=req.getParameter("buttonAdd");
		String id=req.getParameter("idL");
		String modifica=req.getParameter("buttonMod");
		String cancella=req.getParameter("buttonCanc");
		String indietro=req.getParameter("buttonI");
		// cancellare questa riga sotto
		//SystemBean.getIstance().setTypeAsBook();
		String type=SystemBean.getIstance().getType();
		
		try {
		if(genera!=null && genera.equals("genera lista"))
		{
			if (type.equals("libro"))
				mOB.setMiaLista(lB.getLibri());
			else if(type.equals("giornale"))
				mOB.setMiaLista(gB.getListaGiornali());
			else if(type.equals("rivista"))
				mOB.setMiaLista(rB.getListaRiviste());
		
			req.setAttribute("beanMOB",mOB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggetto.jsp"); 
			view.forward(req,resp);
		}
		if(aggiungi!=null && aggiungi.equals("inserisci"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(modifica!=null && modifica.equals("modifica") && !"".equals(id))
		{
			if(type.equals("libro"))
			{
				lB.setId(Integer.parseInt(type));
				SystemBean.getIstance().setId(lB.getId());
				l.setId(lB.getId());
			}
			else if(type.equals( "giornale"))
			{
				gB.setId(Integer.parseInt(id));
				SystemBean.getIstance().setId(gB.getId());
				g.setId(gB.getId());
			}	
			else if(type.equals("rivista"))
			{
				rB.setId(Integer.parseInt(id));
				SystemBean.getIstance().setId(rB.getId());
				r.setId(rB.getId());
			
			}
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(cancella!=null && cancella.equals("cancella") && !"".equals(id))
		{
			if(type.equals("libro"))
			{
				lB.setId(Integer.parseInt(type));
				l.setId(lB.getId());
				cancella(l);
			}
			else if(type.equals("giornale"))
			{
				gB.setId(Integer.parseInt(id));
				g.setId(gB.getId());
				cancellaG(g);
			}
			else if(type.equals("rivista"))
			{
			
				rB.setId(Integer.parseInt(id));
				r.setId(rB.getId());
				cancellaR(r);
			}
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
			e.printStackTrace();
		}
	}
	
	public void cancella(Libro l) throws SQLException {
		int row=0;
		String query="delete from libro where idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			row=prepQ.executeUpdate();
		}
		
		java.util.logging.Logger.getLogger("Cancella libro").log(Level.INFO,"libro cancellato {0}",row);

		
	}
	public  void cancellaG(Giornale g) throws SQLException  {
		int row=0;
		String query="delete from giornale where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, g.getId());
			row=prepQ.executeUpdate();
			
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("cancella").log(Level.INFO, "eccezione", e);
		}
		java.util.logging.Logger.getLogger("cancella g").log(Level.INFO,"\n rows affcted {0}",row);




	}
	
	public void cancellaR(Rivista r) throws SQLException {

		 int row=0;
		 String query="delete from rivista where id=?";
		 try(Connection conn=ConnToDb.generalConnection();
				 PreparedStatement prepQ=conn.prepareStatement(query);)
		 {
			 prepQ.setInt(1, r.getId());
			 row=prepQ.executeUpdate();
		 }catch(SQLException e)
		 {
			 java.util.logging.Logger.getLogger("cancella r").log(Level.INFO, "eccezione in cancellare rivista", e);
		 }
		 java.util.logging.Logger.getLogger("rivista cancellata").log(Level.INFO, "row delected{0}",row);

	}


	
}
