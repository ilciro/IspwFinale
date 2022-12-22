package web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import web.bean.UserBean;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import laptop.utilities.ConnToDb;

@WebServlet("/ResetPassServlet")
public class ResetPassServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("emailL");
		String vecchiaPass=req.getParameter("vecchiaPassL");
		String nuovaPass=req.getParameter("nuovaPassL");
		String invia=req.getParameter("buttonI");
		String indietro=req.getParameter("buttonA");
		try {
		if(invia!=null && invia.equals("reset pass") && aggiornaPass(email,vecchiaPass,nuovaPass))
				{
					RequestDispatcher view = getServletContext().getRequestDispatcher("/index.jsp"); 
					view.forward(req,resp);
				}
			
		
		if(indietro!=null && indietro.equals("indietro"))
		{
			RequestDispatcher view = getServletContext().getRequestDispatcher("/login.jsp"); 
			view.forward(req,resp);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public boolean aggiornaPass(String email,String vecchiaP,String nuovaP) throws SQLException
	{
		boolean status=false;
		UserBean.getInstance().setEmail(email);
		UserBean.getInstance().setPassword(vecchiaP);
		if(UserBean.getInstance().getPassword().equals(vecchiaP) && (nuovaP.length()>=8 || !email.equals("") ) )
		{
			
			UserBean.getInstance().setPassword(nuovaP);

				if(checkUser(UserBean.getInstance()) == 1)
				{
					status=checkResetpass(UserBean.getInstance(), nuovaP,email);
				}
				
				
			
			
		}
		return status;
	}
	
	// modificare per passare utente
	private static int checkUser(UserBean  u) throws SQLException
	{
		int  status=0;
		// ritorna int per motivi legati al controller
		// anche se lo tratto come boolean
		//levato pwd se no non aggiorna


			String query="SELECT idUser FROM ispw.users where Email =?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				
			prepQ.setString(1, u.getEmail());
			ResultSet rs = prepQ.executeQuery();
			if(rs.next())
			{
				 int id=rs.getInt("idUser");
				 if(id>0)
					 status=1;	 			

			}
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("check user").log(Level.INFO, "eccezione ottenuta", e);

			}
			
			java.util.logging.Logger.getLogger("check user id").log(Level.INFO, "idUser {0}",u.getEmail());


		return status ;
	}
	
	public static boolean checkResetpass (UserBean u, String pwd,String email ) throws SQLException
	{

				String query="Update ispw.users SET pwd = ?  where Email = ?";
				int row=0;
				boolean state=false;
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);)
				{
		
			
			prepQ.setString(1,pwd);
			prepQ.setString(2,email);
			row=prepQ.executeUpdate();			
			if(row==1)
				state= true;
			
				}catch(SQLException e)
				{
					java.util.logging.Logger.getLogger("check reset pwd").log(Level.INFO, "eccezione ottenuta", e);

				}
		
		
		java.util.logging.Logger.getLogger("Reset pwd").log(Level.INFO, "row affected{0}",u.getEmail());
		return state ;
	}



}
