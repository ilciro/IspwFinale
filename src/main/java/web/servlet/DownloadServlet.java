package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import web.bean.DownloadBean;
import web.bean.FatturaBean;

import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	private DownloadBean dB=new DownloadBean();
	private SystemBean sB=SystemBean.getIstance();
	private Libro l=new Libro();
	private FatturaBean fB=new FatturaBean();
	private static PagamentoDao pD=new PagamentoDao();
	private Giornale g=new Giornale();
	private Rivista r=new Rivista();
	private static LibroDao lD=new LibroDao();
	private static GiornaleDao gD=new GiornaleDao();
	private static RivistaDao rD=new RivistaDao();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String invia=req.getParameter("downloadB");
		String annulla=req.getParameter("annullaB");
		
		try {
			if(invia!=null && invia.equals("scarica e leggi") )

			{
				switch(sB.getType())
				{
					case "libro":
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						l.setId(sB.getId());
						l.scarica();						
						l.leggi(l.getId());
						break;
					case "giornale":
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						g.setId(sB.getId());
						g.scarica();						
						g.leggi(g.getId());
						break;
					case "rivista":
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						r.setId(sB.getId());
						r.scarica();
						r.leggi(r.getId());
						break;
					default:break;
				
				}
				
				req.setAttribute("bean",dB);
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			if(annulla!=null && annulla.equals("annulla"))
			{
				boolean statusF=false;
				boolean statusP=false;
				String type=sB.getType();
				String metodoP=sB.getMetodoP();
				
				int idF=fB.retUltimoOrdine(); //ultimo elemento (preso con count)
				statusF=fB.annullaOrdine(idF);
				
				int idP=pD.retUltimoOrdine();
				statusP=pD.annullaOrdine(idP);
				
				
						
					if(statusF && statusP && metodoP.equals("cash"))
					{
						switch(type)
						{
							case "libro":
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
							break;
							case "giornale":
								g.setId(sB.getId());
								gD.aggiornaDisponibilita(g);
								break;
							case "rivista":
								r.setId(sB.getId());
								rD.aggiornaDisponibilita(r);
								break;
							default:break;
	
						}
						req.setAttribute("bean",dB);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
						view.forward(req,resp);
						
					}
					 if( statusP && metodoP.equals("cCredito"))
					{
						switch(type)
						{
							case "libro":
							l.setId(sB.getId());
							lD.aggiornaDisponibilita(l);
							break;
							case "giornale":
								g.setId(sB.getId());
								gD.aggiornaDisponibilita(g);
								break;
							case "rivista":
								r.setId(sB.getId());
								rD.aggiornaDisponibilita(r);
								break;
							default:break;
						}
						req.setAttribute("bean",dB);
						RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
						view.forward(req,resp);
						
					}
					
				
				
				
			}
			
				
		}catch(SQLException | DocumentException  e)
		{
			e.printStackTrace();
		
		}
		
	}
}
				
		
		
	
	
	


