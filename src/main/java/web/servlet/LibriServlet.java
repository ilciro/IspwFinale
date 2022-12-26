package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.LibroDao;
import laptop.exception.IdException;
import laptop.model.raccolta.Libro;
import web.bean.LibroBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LibriServlet")
public class LibriServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static LibroBean lB=new LibroBean();
	private static String libri="/libri.jsp";
	private int dimensione=0;
	private static LibroDao lD=new LibroDao();
	private static Libro l=new Libro();

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String i=req.getParameter("procedi");
		String g=req.getParameter("genera lista");
		String a=req.getParameter("annulla");
		String id=req.getParameter("idOgg");
		
		try {
			dimensione =lD.getLibri().size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		setDim(dimensione);
		try {
			if(g!=null && g.equals("genera lista"))
			{
			
					
			 
				lB.setListaLibri(lD.getLibri());
				req.setAttribute("beanL",lB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(libri); 
				view.forward(req,resp);
			
			
			}
			if(i!=null && i.equals("procedi"))
			{
				
				int idO=Integer.parseInt(id);
				if((idO>=1) && (idO<getDim()))
				{
					
					
					lB.setId(Integer.parseInt(id));
					l.setId(lB.getId());
					lB.setTitolo(lD.getTitolo(l));
					SystemBean.getIstance().setId(lB.getId());
					SystemBean.getIstance().setTitolo(lB.getTitolo());
					req.setAttribute("beanL",lB);
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
			lB.setMex(new IdException("id nullo o eccede lista"));
			req.setAttribute("beanL",lB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(libri); 
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
