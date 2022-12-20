package laptop.controller;

import java.util.logging.Level;

import laptop.exception.LogoutException;
import laptop.model.User;

public class ControllerHomePageAfterLogin {
	
	private static User u = User.getInstance();
	private static ControllerSystemState vis = ControllerSystemState.getIstance() ;

	// qui ci va la funzione di logout
	
	public static boolean logout() throws LogoutException 
	{	
		
		String n = u.getNome();
		java.util.logging.Logger.getLogger("Test logout").log(Level.INFO, "stai sloggando come {0}" ,n);
		
		if (n==null)
		{
			throw new LogoutException("Errore Logout");

		}
		else {
		u.setId(-1);
		u.setNome(null);
		u.setCognome(null);
		u.setDataDiNascita(null);
		u.setDescrizione(null);
		u.setEmail(null);
		u.setPassword(null);
		
		
		java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "stai sloggando {0}",u.getEmail());
			vis.setIsLogged(false);
			return true;
		}

	}
	private ControllerHomePageAfterLogin()
	{
		
	}
	
}
