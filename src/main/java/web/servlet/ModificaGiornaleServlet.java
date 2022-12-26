package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import laptop.database.GiornaleDao;
import web.bean.GiornaleBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Giornale;

@WebServlet("/ModificaGiornaleServlet")

public class ModificaGiornaleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GiornaleBean gB=new GiornaleBean();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();
	private static ModificaOggettoBean mOB=new ModificaOggettoBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String genera=req.getParameter("buttonG");
		String aggiorna=req.getParameter("confermaB");
		String annulla=req.getParameter("annullaB");
		
		try {
			
			gB.setId(SystemBean.getIstance().getId());
			g.setId(gB.getId());
			
				mOB.setMiaLista(gD.getGiornaleSingoloL());
			
			if(genera!=null && genera.equals("prendi dati"))
			{
				req.setAttribute("beanMOB", mOB);
				RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
				view.forward(req, resp);
			}
			if(aggiorna!=null && aggiorna.equals("conferma"))
			{
				String titoloN=req.getParameter("titoloNG");
				String tipoN=req.getParameter("tipoG");
				String linguaN=req.getParameter("linguaNG");
				String editoreN=req.getParameter("editoreNG");
				String dataN=req.getParameter("dataNG");//fare cast
				String copieN=req.getParameter("copieNG");
				String dispN=req.getParameter("dispNG");
				String prezzoN=req.getParameter("prezzoNG");
				
				java.util.Date utilDate;
			     java.sql.Date sqlDate;
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		         utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        gB.setTitolo(titoloN);
		        gB.setTipologia(tipoN);
		        gB.setLingua(linguaN);
		        gB.setEditore(editoreN);
		        gB.setDataPubb(sqlDate.toLocalDate());
		       
		        gB.setCopieRimanenti(Integer.parseInt(copieN));
		        gB.setDisponibilita(Integer.parseInt(dispN));
		        gB.setPrezzo(Float.parseFloat(prezzoN));
		        
		        g.setTitolo(gB.getTitolo());
		        g.setTipologia(gB.getTipologia());
		        g.setLingua(gB.getLingua());
		        g.setEditore(gB.getEditore());
		        g.setDataPubb(gB.getDataPubb());
				g.setCopieRimanenti(gB.getCopieRimanenti());
				g.setDisponibilita(gB.getDisponibilita());
				g.setPrezzo(gB.getPrezzo());
				
				

				gD.aggiornaGiornale(g);
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
				view.forward(req, resp);
				
			}
			if(annulla!=null && annulla.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
				view.forward(req, resp);
			}
			
	
		
		} catch (SQLException |ParseException  e) {
			e.printStackTrace();
		
	}
	}
}
