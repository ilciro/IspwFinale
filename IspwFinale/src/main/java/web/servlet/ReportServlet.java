package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import web.bean.TextAreaBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static TextAreaBean tAB=new TextAreaBean();
	private static String report="/report.jsp";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder s=new StringBuilder();
		String finale="";
		
		try {
			String buttonTot=req.getParameter("totaleC");
			String buttonL=req.getParameter("totaleL");
			String buttonG=req.getParameter("totaleG");
			String buttonR=req.getParameter("totaleR");
			String buttonRacc=req.getParameter("totaleRacc");
			String buttonI=req.getParameter("buttonI");
			
			if(buttonTot!=null && buttonTot.equals("totale") )
			{
				
								
				tAB.setScrivi(finale);
				
				
					s.append(tAB.generaReportL());
					s.append(tAB.generaReportG());
					s.append(tAB.generaReportR());
					s.append(TextAreaBean.getListaUtenti());
					finale=s.toString();
					tAB.setScrivi(finale);
					req.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(req,resp);			
				
					
			}
			else if(buttonL!=null && buttonL.equals("libri") )
			{
				finale="";
				tAB.setScrivi(finale);
				
					s.append(tAB.generaReportL());
					finale=s.toString();					
					tAB.setScrivi(finale);
					req.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(req,resp);			
				
					
			}
			else if(buttonG!=null && buttonG.equals("giornale") )
			{
				tAB.setScrivi(finale);
				
				
					
					s.append(tAB.generaReportG());
					finale=s.toString();
					tAB.setScrivi(finale);
					req.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(req,resp);			
				
					
			}
			else if(buttonR!=null && buttonR.equals("rivista")  )
			{
				finale="";
				tAB.setScrivi(finale);
				
				
					
					s.append(tAB.generaReportR());
					finale=s.toString();
					tAB.setScrivi(finale);
					req.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(req,resp);			
				
					
			}
			else if(buttonRacc!=null && buttonRacc.equals("raccolta")  )
			{
				finale="";
				tAB.setScrivi(finale);
			
				
					s.append(tAB.generaReportL());
					s.append(tAB.generaReportG());
					s.append(tAB.generaReportR());
					finale=s.toString();
					tAB.setScrivi(finale);
					req.setAttribute("bean",tAB);
					RequestDispatcher view = getServletContext().getRequestDispatcher(report); 
					view.forward(req,resp);			
				
					
			}
			else if(buttonI!=null && buttonI.equals("indietro") )
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
				view.forward(req,resp);
			}
			
			
			
			
			
		}catch(SQLException |IOException e )
		{
			e.printStackTrace();
		}
	}

}
