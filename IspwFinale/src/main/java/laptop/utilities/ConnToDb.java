package laptop.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;


public class ConnToDb 
{
	
	protected static Connection conn = null;

	
	private static String connessione="Tentativo di conessione al server..........\\\\n";
	protected static String url2;
	private static boolean status=false;
	private static String errore="Errore in mysql..........\\n";
	private static String driv="driver";
	

	public static  boolean initailConnection()
	{

		ResourceBundle rB=ResourceBundle.getBundle("configurations/configInitial");
		
		
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwd");
		String url=rB.getString("url");
		
		
		
		
		try
		{
			Class.forName(driver);

			conn = DriverManager.getConnection(url,user,pwd);
			java.util.logging.Logger.getLogger("Test InitialConnection").log(Level.INFO, "Connesso initial.....\n");

			status= true;

		} 
		catch (SQLException | ClassNotFoundException  e1)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "errore in mysql", e1);

		} 
		
		return status;
	}

	public static boolean connection() throws SQLException {
		
		boolean status=false;
		ResourceBundle rB=ResourceBundle.getBundle("configurations/configDb");
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwd");
		String url=rB.getString("url");

		try 
		{
			if(initailConnection()) 
			{
				//actuac DB project

				 Class.forName(driver);
				 java.util.logging.Logger.getLogger("Test connection").log(Level.INFO, connessione);
				
				

				conn = DriverManager.getConnection(url, user,pwd);
				java.util.logging.Logger.getLogger("Test connection standard").log(Level.INFO, "Connesso standard ........\n");
				

				status= true;
			}
			
		} 
		catch (SQLException | ClassNotFoundException  e1) 
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, errore, e1);

		} 
		

		return status;
	}
	
	public static Connection generalConnection()
	{

		ResourceBundle rB=ResourceBundle.getBundle("configurations/configDb");
		String driver=rB.getString(driv);
		String user=rB.getString("user");
		String pwd=rB.getString("pwd");
		String url=rB.getString("url");
		try
		{
			Class.forName(driver);
			java.util.logging.Logger.getLogger("Test General connection").log(Level.INFO, connessione);
			conn = DriverManager.getConnection(url, user,pwd);
			java.util.logging.Logger.getLogger("Test General connection standard").log(Level.INFO, "Connesso standard ........\n");

		} 
		catch (SQLException  | ClassNotFoundException e1)
		{
			java.util.logging.Logger.getLogger("Test general connection error").log(Level.INFO, errore, e1);


		} 
		
		return conn;
	
	}
	private ConnToDb(){
		
	}

	


}

