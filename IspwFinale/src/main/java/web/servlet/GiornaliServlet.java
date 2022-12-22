package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import laptop.exception.IdException;
import web.bean.GiornaleBean;
import web.bean.SystemBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;

@WebServlet("/GiornaliServlet")
public class GiornaliServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String giornali="/giornali.jsp";
	private int dimensione=0;
	private static GiornaleBean gB=new GiornaleBean();
	private static String giornale="giornale";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String i=req.getParameter("procedi");
		String g=req.getParameter("genera lista");
		String a=req.getParameter("annulla");
		String id=req.getParameter("idOgg");
		
		try {
			dimensione =getGiornali().size();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
		setDim(dimensione);
		try {
			if(g!=null && g.equals("genera lista"))
			{
			
					
				gB.setListaGiornali(getGiornali());
				
				req.setAttribute("beanG",gB);
				RequestDispatcher view = getServletContext().getRequestDispatcher(giornali); 
				view.forward(req,resp);
			
			
			}
			if(i!=null && i.equals("procedi"))
			{
				
				int idO=Integer.parseInt(id);
				if((idO>=1) && (idO<getDim()))
				{
					
					
					gB.setId(Integer.parseInt(id));
					SystemBean.getIstance().setId(gB.getId());
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
	
	
	public  List<Raccolta> getGiornali() throws SQLException   {

		List<Raccolta> catalogo=new ArrayList<>();

		Factory f=new Factory();
	
	
		String query="select * from giornale";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
		while(rs.next())        

		{
			
			f.createRaccoltaFinale1(giornale, rs.getString(1),rs.getString(2), null,rs.getString(3),rs.getString(4),null);
			f.createRaccoltaFinale2(giornale,0,null,0,rs.getInt(7),rs.getFloat(8),rs.getInt(6));
			catalogo.add(f.createRaccoltaFinaleCompleta(giornale, rs.getDate(5).toLocalDate(), null, null,rs.getInt(9)));
		
			
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("catalogo giornali").log(Level.INFO, "eccezione ottenuta :", e);
		}

	return catalogo;
}
	

}
