package laptop.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.database.UsersDao;

public class ControllerUserPage {
	
	public void getUtenti() throws IOException, SQLException  {
		 UsersDao.getListaUtenti();
	}
	
	public ControllerUserPage()
	{
		java.util.logging.Logger.getLogger("Test Costruttore").log(Level.INFO,"Costruttore ControllerUserPage");

	}

}
