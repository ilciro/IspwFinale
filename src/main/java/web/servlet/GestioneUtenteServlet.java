package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.UsersDao;
import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.User;

@WebServlet("/GestioneUtenteServlet")
public class GestioneUtenteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String genera=req.getParameter("buttonGenera");
		String inserisci=req.getParameter("buttonAdd");
		String modifica=req.getParameter("buttonMod");
		String cancella=req.getParameter("buttonCanc");
		String indietro=req.getParameter("buttonI");
		String id=req.getParameter("idL");
		try {
			if(genera!=null && genera.equals("genera lista"))
			{
				req.setAttribute("beanUB",UserBean.getInstance());
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
				view.forward(req, resp);
			}
			if(inserisci!=null && inserisci.equals("inserisci"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/inserisciUtente.jsp");
				view.forward(req, resp);
			}
			if(modifica!=null && modifica.equals("modifica"))
			{
				SystemBean.getIstance().setId(Integer.parseInt(id));
				req.setAttribute("beanS", SystemBean.getIstance());
				RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaUtente.jsp");
				view.forward(req, resp);
			}
			if(cancella!=null && cancella.equals("cancella"))
			{
				User.getInstance().setId(Integer.parseInt(id));
				if(UsersDao.deleteUser(User.getInstance()))
				{
					RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
					view.forward(req, resp);
				}
				else {
					UserBean.getInstance().setMex("cancellazione NON andata a buon fine");
					req.setAttribute("beanUb", UserBean.getInstance());
					RequestDispatcher view=getServletContext().getRequestDispatcher("/modificaUtente.jsp");
					view.forward(req, resp);
				}
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/admin.jsp");
				view.forward(req, resp);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
