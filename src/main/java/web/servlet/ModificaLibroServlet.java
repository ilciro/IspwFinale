package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import laptop.database.LibroDao;
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

@WebServlet("/ModificaLibroServlet")
public class ModificaLibroServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ModificaOggettoBean mOB=new ModificaOggettoBean(); 
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();
	private static LibroBean lB=new LibroBean();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buttonLista=req.getParameter("listaB");
		String buttonCateg=req.getParameter("buttonCat");
		String aggiorna=req.getParameter("buttonI");
		String annulla=req.getParameter("buttonA");
		try {
			
		lB.setId(SystemBean.getIstance().getId());
		l.setId(lB.getId());
		
		if(buttonLista!=null && buttonLista.equals("prendi dati"))
		{
		
			
			req.setAttribute("beanMOB", mOB);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(buttonCateg!=null && buttonCateg.equals("elenco categorie"))
		{
			req.setAttribute("beanMOBL",lB);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaOggettoPage.jsp");
			view.forward(req, resp);
		}
		if(aggiorna!=null && aggiorna.equals("aggiorna"))
		{
			//prendo roba
			String titoloN=req.getParameter("titoloNL");
			String pagineN=req.getParameter("pagineNL");
			String codiceN=req.getParameter("codiceNL");
			String editoreN=req.getParameter("editoreNL");
			String autoreN=req.getParameter("autoreNL");
			String linguaN=req.getParameter("linguaNL");
			String categoriaN=req.getParameter("categoriaNL");
			String dataN=req.getParameter("dataNL");//fare cast
			String recN=req.getParameter("recNL");
			String copieN=req.getParameter("copieNL");
			String descN=req.getParameter("descNL");
			String dispN=req.getParameter("dispNL");
			String prezzoN=req.getParameter("prezzoNL");
			
			lB.setTitolo(titoloN);
			lB.setNumeroPagine(Integer.parseInt(pagineN));
			lB.setCodIsbn(codiceN);
			lB.setEditore(editoreN);
			lB.setAutore(autoreN);
			lB.setLingua(linguaN);
			lB.setCategoria(categoriaN);
			java.util.Date utilDate;
		     java.sql.Date sqlDate;
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			
		    
	         utilDate = format.parse(dataN);
	        sqlDate = new java.sql.Date(utilDate.getTime());
	        lB.setDataPubb(sqlDate.toLocalDate());
	        lB.setRecensione(recN);
	        lB.setNrCopie(Integer.parseInt(copieN));
	        lB.setDesc(descN);
	        lB.setDisponibilita(Integer.parseInt(dispN));
	        lB.setPrezzo(Float.parseFloat(prezzoN));
	        
			l.setTitolo(lB.getTitolo());
			l.setNumeroPagine(lB.getNumeroPagine());
			l.setCodIsbn(lB.getCodIsbn());
			l.setEditore(lB.getEditore());
			l.setAutore(lB.getAutore());
			l.setLingua(lB.getLingua());
			l.setCategoria(categoriaN);
			l.setDataPubb(lB.getDataPubb());
			l.setRecensione(lB.getRecensione());
			l.setNrCopie(lB.getNrCopie());
			l.setDesc(lB.getDesc());
			l.setDisponibilita(lB.getDisponibilita());
			l.setPrezzo(lB.getPrezzo());
			
			lD.aggiornaLibro(l);
			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
			view.forward(req, resp);
			
		}
		if(annulla!=null && annulla.equals("indietro"))
		{
			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneOggetto.jsp");
			view.forward(req, resp);
		}
		}catch(SQLException | ParseException e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		
		
	}
	}
	
	

}
