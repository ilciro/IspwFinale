package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.exception.IdException;
import web.bean.RivistaBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RivisteServlet")
public class RivisteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dimensione=0;
	private static RivistaBean rB=new RivistaBean();
	private static String riviste="/riviste.jsp";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String i=req.getParameter("procedi");
		String g=req.getParameter("genera lista");
		String a=req.getParameter("annulla");
		String id=req.getParameter("idOgg");
		
		try {
			dimensione =rB.getRiviste().size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		setDim(dimensione);
		try {
			if(g!=null && g.equals("genera lista"))
			{
			
					
			 
				rB.setListaRiviste(rB.getRiviste());
				req.setAttribute("beanR",rB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(riviste); 
				view.forward(req,resp);
			
			
			}
			if(i!=null && i.equals("procedi"))
			{
				
				int idO=Integer.parseInt(id);
				if((idO>=1) && (idO<getDim()))
				{
					
					
					rB.setId(Integer.parseInt(id));
					SystemBean.getIstance().setId(rB.getId());
					req.setAttribute("beanr",rB);
					req.setAttribute("bean1",SystemBean.getIstance());
					RequestDispatcher view = getServletContext().getRequestDispatcher("/acquista.jsp"); 
					view.forward(req,resp);
				}
				
				
			}
			if(a!=null && a.equals("indietro"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			
		
		} catch (SQLException e) {
			rB.setMex(new IdException("id nullo o eccede lista"));
			req.setAttribute("beanR",rB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(riviste); 
			view.forward(req,resp);
		}
	}
	
	private int getDim()
	{
		return dimensione;
	}
	private void setDim(int dim)
	{
		dimensione=dim;
	}

}