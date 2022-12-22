package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import laptop.database.UsersDao;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.model.User;

@WebServlet("/InserisciUtenteServlet")
public class InserisciUtenteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome=req.getParameter("nomeU");
		String cognome=req.getParameter("cognomeU");
		String email=req.getParameter("emailU");
		String pass=req.getParameter("passU");
		String dataN=req.getParameter("dataU");
		String invia=req.getParameter("buttonI");
		String indietro=req.getParameter("buttonA");
		try {
			if(invia!=null && invia.equals("invia"))
			{
				UserBean.getInstance().setNome(nome);
				UserBean.getInstance().setCognome(cognome);
				UserBean.getInstance().setEmail(email);
				UserBean.getInstance().setPassword(pass);
				java.util.Date utilDate;
			    java.sql.Date sqlDate;
			    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				
			    
		         utilDate = format.parse(dataN);
		        sqlDate = new java.sql.Date(utilDate.getTime());
		        UserBean.getInstance().setDataDiNascita(sqlDate.toLocalDate());
		        
		        User.getInstance().setNome(UserBean.getInstance().getNome());
		        User.getInstance().setCognome(UserBean.getInstance().getCognome());
		        User.getInstance().setEmail(UserBean.getInstance().getEmail());
		        User.getInstance().setPassword(UserBean.getInstance().getPassword());
		        User.getInstance().setDataDiNascita(UserBean.getInstance().getDataDiNascita());
		        
		        if(UsersDao.createUser(User.getInstance()))
		        		{
		        			RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
		        			view.forward(req, resp);
		        		}
		        else {
		        	UserBean.getInstance().setMex("errore nella creazione del nuovo utente");
		        	req.setAttribute("beanUb",UserBean.getInstance());
		        	RequestDispatcher view=getServletContext().getRequestDispatcher("/inserisciUtente.jsp");
        			view.forward(req, resp);
		        }
			}
			if(indietro!=null && indietro.equals("indietro"))
			{
				RequestDispatcher view=getServletContext().getRequestDispatcher("/gestioneUtente.jsp");
    			view.forward(req, resp);
			}
		}catch(SQLException | ParseException  e)
		{
			e.printStackTrace();
		}
		
	}

}
