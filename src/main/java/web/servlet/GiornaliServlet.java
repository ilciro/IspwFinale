package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.GiornaleDao;
import laptop.exception.IdException;
import web.bean.GiornaleBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Giornale;


@WebServlet("/GiornaliServlet")
public class GiornaliServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String giornali="/giornali.jsp";
	private int dimensione=0;
	private static GiornaleBean gB=new GiornaleBean();
	private static GiornaleDao gD=new GiornaleDao();
	private Giornale gior=new Giornale();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String i=req.getParameter("procedi");
		String g=req.getParameter("genera lista");
		String a=req.getParameter("annulla");
		String id=req.getParameter("idOgg");
		
		try {
			dimensione =gD.getGiornali().size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		setDim(dimensione);
		try {
			if(g!=null && g.equals("genera lista"))
			{
			
					
				gB.setListaGiornali(gD.getGiornali());
				
				req.setAttribute("beanG",gB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(giornali); 
				view.forward(req,resp);
			
			
			}
			if(i!=null && i.equals("procedi"))
			{
				
				int idO=Integer.parseInt(id);
				if((idO>=1) && (idO<getDim()))
				{
					
				
					gB.setId(idO);
					gior.setId(gB.getId());
					gB.setTitolo(gD.getTitolo(gior));
					SystemBean.getIstance().setId(gB.getId());					
					SystemBean.getIstance().setTitolo(gB.getTitolo());
				
					
					req.setAttribute("beanG",gB);
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
			gB.setMex(new IdException("id nullo o eccede lista"));
			req.setAttribute("beanL",gB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(giornali); 
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
