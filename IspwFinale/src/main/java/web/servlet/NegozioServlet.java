package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.NegozioBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.Negozio;
import laptop.utilities.ConnToDb;

@WebServlet("/NegozioServlet")
public class NegozioServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NegozioBean nB=new NegozioBean();
	private Negozio n;
	private static String eccezione=" eccezione ottenuta";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String neg1=req.getParameter("buttonNeg1");
		String neg2=req.getParameter("buttonNeg2");
		String neg3=req.getParameter("buttonNeg3");
		String neg4=req.getParameter("buttonNeg4");
		
		n=new Negozio();
		try {
		if(neg1!=null && neg1.equals("Negozio A"))
		{
			nB.setNome("Negozio A");
			
			n.setNome(nB.getNome());
			n.setIsOpen(checkOpen(n));
			n.setIsValid(checkRitiro(n));
			nB.setApertura(n.getIsOpen());
			nB.setDisponibile(n.getIsValid());
			
			
			
			if(nB.isApertura() && nB.isDisponibile())
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			
		
		}
		if(neg2!=null && neg2.equals("Negozio B"))
		{
			nB.setNome("Negozio B");
			n.setNome(nB.getNome());
			n.setIsOpen(checkOpen(n));
			n.setIsValid(checkRitiro(n));
			nB.setApertura(n.getIsOpen());
			nB.setDisponibile(n.getIsValid());
			
			if(nB.isApertura() && nB.isDisponibile())
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			
		}
		if(neg3!=null && neg3.equals("Negozio C"))
		{
			nB.setNome("Negozio C");
			n.setNome(nB.getNome());
			n.setIsOpen(checkOpen(n));
			n.setIsValid(checkRitiro(n));
			nB.setApertura(n.getIsOpen());
			nB.setDisponibile(n.getIsValid());
			
			if(nB.isApertura() && nB.isDisponibile())
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			
		}
		if(neg4!=null && neg4.equals("Negozio D"))
		{
			nB.setNome("Negozio D");
			n.setNome(nB.getNome());
			n.setIsOpen(checkOpen(n));
			n.setIsValid(checkRitiro(n));
			nB.setApertura(n.getIsOpen());
			nB.setDisponibile(n.getIsValid());
			
			if(nB.isApertura() && nB.isDisponibile())
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			
		}
		else {
			nB.setMessaggio(" negozio chiuso o non vi è possibile ritirare");
			req.setAttribute("beanNeg", nB);
			RequestDispatcher view = getServletContext().getRequestDispatcher("/negozi.jsp"); 
			view.forward(req,resp);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean checkOpen(Negozio  shop) throws SQLException
	{
		int aperto=0;
		boolean state=false;
		String query="select isOpen from negozio where nome=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareCall(query);)
		{
			prepQ.setString(1, shop.getNome());
			ResultSet rs=prepQ.executeQuery();
			while(rs.next()){
				aperto=rs.getInt(1);
				if(aperto==1)
					state=true;
				
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}
			 
			
		
		return state;
	}

	
	//controllo se il negozio fa PickUP
	public boolean checkRitiro(Negozio shop) throws SQLException
	{
		String query="select isValid from negozio where nome=?";
		boolean state=false;
		int disp;
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setString(1, shop.getNome());
			ResultSet rs=prepQ.executeQuery();
			while ( rs.next() ) {

					disp=rs.getInt(1);
					if (disp==1)
						state=true;
				
						
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}
			
		return state;
	}

}
