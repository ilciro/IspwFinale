package laptop.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import laptop.database.UsersDao;
import laptop.model.User;

public class ControllerModificaUtente {
	private boolean state = false;
	private User uT;



	public ControllerModificaUtente()
	{
		java.util.logging.Logger.getLogger("Test Costruttore").log(Level.INFO,"Costruttore ControllerModificaUtente");

		uT=User.getInstance();
	}



	public User prendi() {

		return UsersDao.pickData(User.getInstance());


	}

	public boolean aggiorna(String n, String c, String email, String pass, String desc, LocalDate localDate, String vecchiaMail) throws SQLException {


	
			if( !n.equals("")  && !n.equals(User.getInstance().getNome()))
			{

				User.getInstance().setNome(n);
				UsersDao.aggiornaNome(User.getInstance());
				state =  true; 

			}
			if (!c.equals("")  && !c.equals(User.getInstance().getCognome()))
			{
				User.getInstance().setCognome(c);
				UsersDao.aggiornaCognome(User.getInstance());
				state =  true; 

			}
			if(!email.equals("")  && !email.equals(vecchiaMail))
			{

				UsersDao.aggiornaEmail(User.getInstance(),email);
				state =  true; 


			}
			if(!pass.equals("")  && !pass.equals(User.getInstance().getPassword()))
			{
				User.getInstance().setPassword(pass);
				UsersDao.aggiornaPass(User.getInstance());
				state =  true; 

			}
			if(!desc.equals("") && !desc.equals(User.getInstance().getDescrizione()))
			{
				User.getInstance().setDescrizione(desc);
				UsersDao.aggiornaDesc(User.getInstance());
				state =  true; 
			}

			if(!(localDate.toString().equals(" "))  && !localDate.equals(User.getInstance().getDataDiNascita()))
			{
				User.getInstance().setDataDiNascita(localDate);
				state = true;

			}
			
		
		return state;

	}
	public boolean aggiornaTot(String n, String c, String email, String pass, String desc, LocalDate localDate, String ruolo) throws SQLException {
		uT=prendi();
		uT.setNome(n);
		uT.setCognome(c);
		uT.setEmail(email);
		uT.setPassword(pass);
		uT.setDescrizione(desc);
		uT.setDataDiNascita(localDate);
		uT.setIdRuolo(ruolo);
		if(UsersDao.aggiornaUtente(uT) != null)
		{
			state=true;
		}
		return state;
	}

}
