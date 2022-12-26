package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import web.bean.GiornaleBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.GiornaleDao;
import laptop.model.raccolta.Giornale;

@WebServlet("/InserisciOggettoServletGiornale")
public class InserisciOggettoServletGiornale extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static GiornaleBean gB=new GiornaleBean();
	private static Giornale g=new Giornale();
	private static GiornaleDao gD=new GiornaleDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String titolo=req.getParameter("titoloG");
		String tipologia=req.getParameter("tipoG");
		String lingua=req.getParameter("linguaG");
		String editore=req.getParameter("editoreG");
		String data=req.getParameter("dataG");//cast
		String copie=req.getParameter("copieG");//cast
		String disp=req.getParameter("dispG");//cast
		String prezzo=req.getParameter("prezzoG");//cast
		String conferma=req.getParameter("confermaB");
		String annulla=req.getParameter("annullaB");
		
		java.util.Date utilDate;
	    java.sql.Date sqlDate;
		try {
		if(conferma!=null && conferma.equals("conferma"))
		{
			
				if(data!=null)
				{
					gB.setTitolo(titolo);
					gB.setTipologia(tipologia);
					gB.setLingua(lingua);
					gB.setEditore(editore);
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		
				    
				         utilDate = format.parse(data);
				        sqlDate = new java.sql.Date(utilDate.getTime());
				        gB.setData(sqlDate);
				    
				    gB.setCopieRimanenti(Integer.parseInt(copie));
				    gB.setDisponibilita(Integer.parseInt(disp));
				    gB.setPrezzo(Float.parseFloat(prezzo));
				    
				    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
					  String date = data;
		
					  //convert String to LocalDate
					  LocalDate localDate = LocalDate.parse(date, formatter);
					  
					  g.setTitolo(gB.getTitolo());
					  g.setTipologia(gB.getTipologia());
					  g.setLingua(gB.getLingua());
					  g.setEditore(gB.getEditore());
					  g.setDataPubb(localDate);
					  g.setCopieRimanenti(gB.getCopieRimanenti());
					  g.setDisponibilita(gB.getDisponibilita());
					  g.setPrezzo(gB.getPrezzo());
					  
					  
						if(gD.creaGiornale(g))
						{
							gD.aggiornaData(g, sqlDate);
							
							RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggetto.jsp"); 
							view.forward(req,resp); 
						}
					
				}
				else {
					gB.setMex(new Exception(" data nulla"));
					RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp"); 
					view.forward(req,resp); 
				}
			}
			if(annulla!=null && annulla.equals("annulla"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggetto.jsp"); 
				view.forward(req,resp); 
			}
			
		} catch (ParseException |SQLException |ServletException e) {
	        e.printStackTrace();
	    
		
		}
	}
		
}
