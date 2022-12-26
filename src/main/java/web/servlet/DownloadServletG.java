package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.ContrassegnoDao;
import laptop.database.GiornaleDao;
import laptop.database.PagamentoDao;
import laptop.model.raccolta.Giornale;
import web.bean.DownloadBean;
import web.bean.SystemBean;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class DownloadServletG
 */
@WebServlet("/DownloadServletG")
public class DownloadServletG extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DownloadBean dB=new DownloadBean();
	private static SystemBean sB=SystemBean.getIstance();
	private static Giornale g=new Giornale();
	private static PagamentoDao pD=new PagamentoDao();
	private static GiornaleDao  gD=new GiornaleDao();
	private static ContrassegnoDao fDao=new ContrassegnoDao();
	private static String index="/index.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String invia=request.getParameter("downloadB");
		String annulla=request.getParameter("annullaB");
		
		try {
			if(invia!=null && invia.equals("scarica e leggi") )

			{
				
						dB.setId(sB.getId());
						dB.setTitolo(sB.getTitolo());
						g.setId(sB.getId());
						g.scarica();						
						g.leggi(g.getId());
				
				request.setAttribute("bean",dB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
				view.forward(request,response);
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
						
							g.setId(sB.getId());
							gD.aggiornaDisponibilita(g);
						
						
						request.setAttribute("bean",dB);
						RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
						view.forward(request,response);
						
						
					}
					 if( statusP && metodoP.equals("cCredito"))
					{
						
							g.setId(sB.getId());
							gD.aggiornaDisponibilita(g);
							request.setAttribute("bean",dB);
							RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
							view.forward(request,response);
						
						
						
						
					}
					
				
				
				
			}
			
				
		}catch(SQLException | DocumentException  e)
		{
			request.setAttribute("bean",dB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(index); 
			view.forward(request,response);
		
		}
		
	}
	

}
