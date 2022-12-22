package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import web.bean.LibroBean;
import web.bean.ModificaOggettoBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Libro;

@WebServlet("/InserisciOggettoServlet")
public class InserisciOggettoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static Libro l=new Libro();
	private static ModificaOggettoBean mOB=new ModificaOggettoBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String conferma=req.getParameter("confermaB");
		String annulla=req.getParameter("annullaB");
		String genera=req.getParameter("generaL");
		
		String type=SystemBean.getIstance().getType();
		try {
			if(genera!=null && genera.equals("prendi lista"))
			{
				mOB.setTipologiaL(lB.setCategorie());				
				req.setAttribute("beanMOB",mOB);
				RequestDispatcher view=getServletContext().getRequestDispatcher("/aggiungiOggettoPage.jsp");
				view.forward(req, resp);
			}
			
		if(conferma!=null && conferma.equals("conferma") && (type.equals("libro")))
			
		{
				
				String titolo=req.getParameter("titoloL");
				String pagine=req.getParameter("nrPagL");//to int
				String codice=req.getParameter("codiceL");
				String autore=req.getParameter("autoreL");
				String editore=req.getParameter("editoreL");
				String lingua=req.getParameter("linguaL");
				String catS=req.getParameter("catS");
				
				String data=req.getParameter("dataL");
				String recensione=req.getParameter("recensioneL");
				String desc=req.getParameter("descL");
				String disp=req.getParameter("checkL");
				String prezzo=req.getParameter("prezzoL");//cast to float
				String copie=req.getParameter("copieL");
				
				java.util.Date utilDate;
			     java.sql.Date sqlDate;
				
				lB.setTitolo(titolo);
				lB.setCodIsbn(codice);
				lB.setNumeroPagine(Integer.parseInt(pagine));
				lB.setEditore(editore);
				lB.setAutore(autore);
				lB.setLingua(lingua);
				lB.setCategoria(catS);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	
			    
			         utilDate = format.parse(data);
			        sqlDate = new java.sql.Date(utilDate.getTime());
			        lB.setDate(sqlDate);
			   
				
			    lB.setRecensione(recensione);
			    lB.setDesc(desc);
			    lB.setDisponibilita(0);
			    if(disp!=null)
			    {
			    	lB.setDisponibilita(1);
			    }
			    
				lB.setPrezzo(Float.parseFloat(prezzo));
				lB.setNrCopie(Integer.parseInt(copie));
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
				  String date = data;
	
				  //convert String to LocalDate
				  LocalDate localDate = LocalDate.parse(date, formatter);
			
			
				  
			l.setTitolo(lB.getTitolo());
			l.setNumeroPagine(lB.getNumeroPagine());
			l.setCodIsbn(lB.getCodIsbn());
			l.setEditore(lB.getEditore());
			l.setAutore(lB.getAutore());
			l.setLingua(lB.getLingua());
			l.setCategoria(lB.getCategoriaD());
			l.setDataPubb(localDate);
			l.setRecensione(lB.getRecensione());
			l.setDesc(lB.getDesc());
			l.setDisponibilita(lB.getDisponibilita());
			l.setPrezzo(lB.getPrezzo());
			l.setNrCopie(lB.getNrCopie());
			
				
				if(lB.creaLibrio(l))
				{
					lB.aggiornaData(l, sqlDate);
					req.setAttribute("bean", lB);
					RequestDispatcher view = getServletContext().getRequestDispatcher("/gestioneOggettoPage.jsp"); 
					view.forward(req,resp); 
	
				}
				else {
					RequestDispatcher view = getServletContext().getRequestDispatcher("/aggiungiOggettoPage"); 
					view.forward(req,resp); 
				}
			
			
		}
				
				
		if(annulla!=null && annulla.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
			view.forward(req, resp);
		}
		}catch(SQLException | ParseException e)
		{
			e.printStackTrace();
		}
	}
	

}
