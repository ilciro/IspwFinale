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
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.model.raccolta.Libro;

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
	private static LibroDao lD=new LibroDao();
	private static ContrassegnoDao fDao=new ContrassegnoDao();

	
	
	private static String index="/index.jsp";
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String invia=req.getParameter("downloadB");
		String annulla=req.getParameter("annullaB");
		
		try {
			if(invia!=null && invia.equals("scarica e leggi") )

			{
				
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						l.setId(sB.getId());
						l.scarica();						
						l.leggi(l.getId());
				
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
						
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
						
						
						req.setAttribute("bean",dB);
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(req,resp);
						
						
					}
					 if( statusP && metodoP.equals("cCredito"))
					{
						
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
							req.setAttribute("bean",dB);
							RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
							view.forward(req,resp);
						
						
						
						
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
				
		
		
	
	
	


