package web.servlet;

import java.io.IOException;
import java.util.logging.Level;

import laptop.exception.LogoutException;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UtentiServlet")
public class UtentiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String libro=req.getParameter("buttonL");
		String giornale=req.getParameter("buttonG");
		String rivista=req.getParameter("buttonR");
		String logout=req.getParameter("buttonLogout");
		String ricerca=req.getParameter("buttonRic");
		String profilo=req.getParameter("buttonProfilo");
		try {
			if(libro!=null && libro.equals("libri"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/libri.jsp"); 
				view.forward(req,resp);
			}
			if(giornale!=null && giornale.equals("giornali"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/giornali.jsp"); 
				view.forward(req,resp);
			}
			if(rivista!=null && rivista.equals("riviste"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/rivista.jsp"); 
				view.forward(req,resp);
			}
			if(logout!=null && logout.equals("logout"))
			{
				logout();
			}
			if(ricerca!=null && ricerca.equals("ricerca"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/ricerca.jsp"); 
				view.forward(req,resp);
			}
			if(profilo!=null && profilo.equals("profilo"))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/profilo.jsp"); 
				view.forward(req,resp);
			}
		}catch(LogoutException e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}
	
	public static boolean logout() throws LogoutException 
	{	
		
		String n = UserBean.getInstance().getNome();
		java.util.logging.Logger.getLogger("Test logout").log(Level.INFO, "stai sloggando come {0}" ,n);
		
		if (n==null)
		{
			throw new LogoutException("Errore Logout");

		}
		else {
			 UserBean.getInstance().setId(-1);
			 UserBean.getInstance().setNome(null);
			 UserBean.getInstance().setCognome(null);
			 UserBean.getInstance().setDataDiNascita(null);
			 UserBean.getInstance().setDescrizione(null);
			 UserBean.getInstance().setEmail(null);
			 UserBean.getInstance().setPassword(null);
		
		
		java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "stai sloggando {0}", UserBean.getInstance().getEmail());
			SystemBean.getIstance().setIsLogged(false);
			return true;
		}

	}
	

}
