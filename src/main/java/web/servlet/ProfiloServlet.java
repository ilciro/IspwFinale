package web.servlet;

import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.PagamentoBean;
import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.database.PagamentoDao;
import laptop.database.UsersDao;
import laptop.model.User;
@WebServlet("/ProfiloServlet")
public class ProfiloServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PagamentoBean pB=new PagamentoBean();
	private static PagamentoDao pD=new PagamentoDao();
	private static String profilo="/profilo.jsp";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dati=req.getParameter("prendiDatiB");
		String modifica=req.getParameter("modificBa");
		String ordini=req.getParameter("ordiniB");
		String cancella=req.getParameter("cancellaB");
		String indietro=req.getParameter("indietroB");
		
		
		try {
		if(dati!=null && dati.equals("prendi dati"))
		{
			User.getInstance().setEmail(UserBean.getInstance().getEmail());
			UsersDao.pickData(User.getInstance());
			UserBean.getInstance().setNome(User.getInstance().getNome());
			UserBean.getInstance().setCognome(User.getInstance().getCognome());
			UserBean.getInstance().setEmail(User.getInstance().getEmail());
			UserBean.getInstance().setDataDiNascita(UserBean.getInstance().getDataDiNascita());
			req.setAttribute("beanUb",UserBean.getInstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher(profilo); 
			view.forward(req,resp);
		}
		if(modifica!=null && modifica.equals("modifica"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/modificaProfilo.jsp"); 
			view.forward(req,resp);
		}
		if(ordini!=null && ordini.equals("ordini"))
		{
			//prendo pagamento dao> lista pagamento
			User.getInstance().setEmail(UserBean.getInstance().getEmail());
			pB.setListaPagamenti(pD.getPagamenti());
			req.setAttribute("bean", User.getInstance());
			req.setAttribute("beanP", pB);
			RequestDispatcher view = getServletContext().getRequestDispatcher(profilo); 
			view.forward(req,resp);
		}
		if(cancella!=null && cancella.equals("cancella"))
		{
			User.getInstance().setEmail(UserBean.getInstance().getEmail());
			if(UsersDao.deleteUser(User.getInstance()))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			else {
				UserBean.getInstance().setMex(" utente non cancellato... ");
				req.setAttribute("beanUb",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher(profilo); 
				view.forward(req,resp);
			}
			
		}
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/utente.jsp"); 
			view.forward(req,resp);
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("post ").log(Level.INFO, "eccezione nel post {0}.",e.toString());

		}
	}

	
}
