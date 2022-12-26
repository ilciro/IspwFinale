package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import laptop.model.User;
import laptop.utilities.ConnToDb;
@WebServlet("/ProfiloServlet")
public class ProfiloServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PagamentoBean pB=new PagamentoBean();
	private static PagamentoDao pD=new PagamentoDao();
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
			pickData(User.getInstance());
			UserBean.getInstance().setNome(User.getInstance().getNome());
			UserBean.getInstance().setCognome(User.getInstance().getCognome());
			UserBean.getInstance().setEmail(User.getInstance().getEmail());
			UserBean.getInstance().setDataDiNascita(UserBean.getInstance().getDataDiNascita());
			req.setAttribute("beanUb",UserBean.getInstance());
			RequestDispatcher view = getServletContext().getRequestDispatcher("/profilo.jsp"); 
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
			RequestDispatcher view = getServletContext().getRequestDispatcher("/profilo.jsp"); 
			view.forward(req,resp);
		}
		if(cancella!=null && cancella.equals("cancella"))
		{
			User.getInstance().setEmail(UserBean.getInstance().getEmail());
			if(deleteUser(User.getInstance()))
			{
				RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
				view.forward(req,resp);
			}
			else {
				UserBean.getInstance().setMex(" utente non cancellato... ");
				req.setAttribute("beanUb",UserBean.getInstance());
				RequestDispatcher view = getServletContext().getRequestDispatcher("/profilo.jsp"); 
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
			e.printStackTrace();
		}
	}

	private static User pickData(User u) throws SQLException
	{
		
		
			String query="SELECT idRuolo,nome,cognome,Email,descrizione,dataDiNascita from ispw.users where Email=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				prepQ.setString(1, u.getEmail());
			
			ResultSet rs = prepQ.executeQuery();
			while(rs.next())
			{
				// setto i vari dati 
				u.setIdRuolo(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setDescrizione(rs.getString(5));
				u.setDataDiNascita(rs.getDate(6).toLocalDate());



			}
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("pick data ").log(Level.INFO, "aaa", e);

			}

			java.util.logging.Logger.getLogger("pick user data email").log(Level.INFO, "bb", u.getEmail());


			
		
		// errore
		return u;
	}
	
	private static boolean deleteUser(User user) throws SQLException
	{
		String email = user.getEmail();
		String ruolo=user.getIdRuolo();
		String query="DELETE FROM ispw.users WHERE Email = ?";
		int row=0;
		boolean state =false;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
		
		
		
			
				prepQ.setString(1,email);
				row=prepQ.executeUpdate();
				if(row==1)
					state= true;

		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("delete user").log(Level.INFO, "eccezione generatat", e);

		}
			

		
			java.util.logging.Logger.getLogger("delete user ruolo").log(Level.INFO,"cancello user ruolo{0}",ruolo);


		
		return state ;
		
	}

}
