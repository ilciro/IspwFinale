package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import web.bean.DownloadBean;

import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.ContrassegnoDao;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DownloadBean dB=new DownloadBean();
	private static SystemBean sB=SystemBean.getIstance();
	private static Libro l=new Libro();
	private static PagamentoDao pD=new PagamentoDao();
	private static Giornale g=new Giornale();
	private static Rivista r=new Rivista();
	private static LibroDao lD=new LibroDao();
	private static GiornaleDao gD=new GiornaleDao();
	private static RivistaDao rD=new RivistaDao();
	private static ContrassegnoDao fDao=new ContrassegnoDao();
	private static String giornale="giornale";
	private static String rivista="rivista";
	private static String libro="libro";
	
	private static String index="/index.jsp";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String invia=req.getParameter("downloadB");
		String annulla=req.getParameter("annullaB");
		String type=sB.getType();
		
		try {
			if(invia!=null && invia.equals("scarica e leggi") )

			{
				if(type.equals(libro))
				{
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						l.setId(sB.getId());
						l.scarica();						
						l.leggi(l.getId());
				}
				else if(type.equals(giornale))
				{
						
					
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						g.setId(sB.getId());
						g.scarica();						
						g.leggi(g.getId());
				}
				else if(type.equals(rivista))
				{
					
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						r.setId(sB.getId());
						r.scarica();
						r.leggi(r.getId());
					
				}
				
				req.setAttribute("bean",dB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
				view.forward(req,resp);
			}
			if(annulla!=null && annulla.equals("annulla"))
			{
				
				//split
				boolean statusF=false;
				boolean statusP=false;
				
				String metodoP=sB.getMetodoP();
				
				int idF=fDao.retUltimoOrdine(); //ultimo elemento (preso con count)
				statusF=fDao.annullaOrdine(idF);
				
				int idP=pD.retUltimoOrdine();
				statusP=pD.annullaOrdine(idP);
				
				
					if(statusF && statusP && metodoP.equals("cash"))
					{
						if(type.equals(libro))
						{
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
						}
						else if(type.equals(giornale))
						{
								g.setId(sB.getId());
								gD.aggiornaDisponibilita(g);
						}
						else if(type.equals(rivista))
						{
								r.setId(sB.getId());
								rD.aggiornaDisponibilita(r);
						
						}
						req.setAttribute("bean",dB);
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(req,resp);
						
					}
					 if( statusP && metodoP.equals("cCredito"))
					{
						if(type.equals(libro))
						{
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
						}
						else if(type.equals(giornale))
						{
							
								g.setId(sB.getId());
								gD.aggiornaDisponibilita(g);
						}
						else if(type.equals(rivista))
						{
							
								r.setId(sB.getId());
								rD.aggiornaDisponibilita(r);
							
						}
						
						
					}
					
				
				
				
			}
			
				
		}catch(SQLException | DocumentException  e)
		{
			req.setAttribute("bean",dB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
			view.forward(req,resp);
		
		}
		
	}
	
	
}
				
		
		
	
	
	


