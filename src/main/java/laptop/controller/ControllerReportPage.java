package laptop.controller;

import java.io.IOException;
import java.sql.SQLException;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.database.UsersDao;

public class ControllerReportPage {
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	
	public void generaReportLibri () throws IOException, SQLException
	{
		lD.generaReport();
		
	}
	
	public ControllerReportPage()
	{
		lD=new LibroDao();
		rD=new RivistaDao();
		gD=new GiornaleDao();
	}
	public void getUtenti() throws IOException, SQLException  {
		 UsersDao.getListaUtenti();
	}

	public void generaReportRiviste () throws IOException, SQLException
	{
		rD.generaReport();
		
	}
	public void generaReportGiornali () throws IOException, SQLException 
	{
		gD.generaReport();
		
	}
	

}
