package web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import web.bean.SystemBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("emailL");
		String pass=req.getParameter("passL");
		String login=req.getParameter("loginB");
		String annulla=req.getParameter("annullaB");
		String registra=req.getParameter("registerB");
		String reset=req.getParameter("resetB");
		try {
		if(login!=null && login.equals("login") && (!"".equals(email) && !"".equals(pass)))
			{
				UserBean.getInstance().setEmail(email);
				UserBean.getInstance().setPassword(pass);
				User.getInstance().setEmail(UserBean.getInstance().getEmail());
				User.getInstance().setPassword(UserBean.getInstance().getPassword());
				
				
				String ruolo=UserBean.getRuolo(User.getInstance());
				
				if(ruolo.equalsIgnoreCase("a"))
				{
					SystemBean.getIstance().setIsLogged(true);
					req.setAttribute("beanUb",UserBean.getInstance());
					req.setAttribute("beanS",SystemBean.getIstance() );
					RequestDispatcher view = getServletContext().getRequestDispatcher("/admin.jsp"); 
					view.forward(req,resp);
				}
				else if(ruolo.equalsIgnoreCase("u"))
				{
					SystemBean.getIstance().setIsLogged(true);
					req.setAttribute("beanUb",UserBean.getInstance());
					req.setAttribute("beanS",SystemBean.getIstance() );
					RequestDispatcher view = getServletContext().getRequestDispatcher("/utente.jsp"); 
					view.forward(req,resp);
				}
				else if(ruolo.equalsIgnoreCase("w"))
				{
					SystemBean.getIstance().setIsLogged(true);
					req.setAttribute("beanUb",UserBean.getInstance());
					req.setAttribute("beanS",SystemBean.getIstance() );
					RequestDispatcher view = getServletContext().getRequestDispatcher("/scrittore.jsp"); 
					view.forward(req,resp);
				}
				else if(ruolo.equalsIgnoreCase("e"))
				{
					SystemBean.getIstance().setIsLogged(true);
					req.setAttribute("beanUb",UserBean.getInstance());
					req.setAttribute("beanS",SystemBean.getIstance() );
					RequestDispatcher view = getServletContext().getRequestDispatcher("/editore.jsp"); 
					view.forward(req,resp);
				}
				
			}
		else {
			UserBean.getInstance().setMex(" utente non registrato / credenziali sbagliate ... per favore registrarsi");
			req.setAttribute("beanUb",UserBean.getInstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp"); 
			view.forward(req,resp);
		}
		if(annulla!=null && annulla.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
			view.forward(req,resp);
			
		}
		if(registra!=null && registra.equals("registrati"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/registrazione.jsp"); 
			view.forward(req,resp);
		}
		if(reset!=null && reset.equals("reset password"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/resetPassword.jsp"); 
			view.forward(req,resp);
		}
		
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
