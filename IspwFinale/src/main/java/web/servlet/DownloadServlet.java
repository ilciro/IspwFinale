package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import web.bean.AcquistaBean;
import web.bean.DownloadBean;
import web.bean.FatturaBean;
import web.bean.GiornaleBean;
import web.bean.LibroBean;
import web.bean.PagamentoBean;
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

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DownloadBean dB=new DownloadBean();
	private SystemBean sB=SystemBean.getIstance();
	private AcquistaBean aB=new AcquistaBean();
	private Libro l=new Libro();
	private FatturaBean fB=new FatturaBean();
	private PagamentoBean pB=new PagamentoBean();
	private LibroBean lB=new LibroBean();
	private Giornale g=new Giornale();
	private Rivista r=new Rivista();
	private GiornaleBean gB=new GiornaleBean();
	private RivistaBean rB=new RivistaBean();
	
	
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
						dB.setTitolo(aB.getTitolo());
						l.setId(sB.getId());
						l.scarica();						
						l.leggi(l.getId());
						break;
					case "giornale":
						dB.setId(sB.getId());
						dB.setTitolo(aB.getTitolo());
						g.setId(sB.getId());
						g.scarica();						
						g.leggi(g.getId());
						break;
					case "rivista":
						dB.setId(sB.getId());
						dB.setTitolo(aB.getTitolo());
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
				
				int idP=pB.retUltimoOrdine();
				statusP=pB.annullaOrdine(idP);
				
				
						
					if(statusF && statusP && metodoP.equals("cash"))
					{
						switch(type)
						{
							case "libro":
							l.setId(sB.getId());
							lB.aggiornaDisponibilita(l);
							break;
							case "giornale":
								g.setId(sB.getId());
								gB.aggiornaDisponibilita(g);
								break;
							case "rivista":
								r.setId(sB.getId());
								rB.aggiornaDisponibilita(r);
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
							lB.aggiornaDisponibilita(l);
							break;
							case "giornale":
								g.setId(sB.getId());
								gB.aggiornaDisponibilita(g);
								break;
							case "rivista":
								r.setId(sB.getId());
								rB.aggiornaDisponibilita(r);
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
				
		
		
	
	
	


