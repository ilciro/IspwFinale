package laptop.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import laptop.model.Fattura;
import laptop.utilities.ConnToDb;

public class ContrassegnoDao {
	
	private static String eccezione="eccezione ottenuta:";
	private String query;

	

	public void inserisciFattura(Fattura f) throws SQLException 
	{
		 
		query="insert into fattura values (?,?,?,?,?,?)";
		 		
 		try(Connection conn=ConnToDb.generalConnection();
 			PreparedStatement prepQ=conn.prepareStatement(query);){
 			
 			prepQ.setString(1, f.getNome());
 			prepQ.setString(2, f.getCognome());
 			prepQ.setString(3, f.getVia());
 			prepQ.setString(4,f.getCom());
 			prepQ.setInt(5, 0);
 			prepQ.setFloat(6,f.getAmmontare());
 			prepQ.execute();
 			
 			 
 		}catch(SQLException e)
 		{
 			java.util.logging.Logger.getLogger("insert fattura").log(Level.INFO, eccezione, e);
 		}
       
		 

         
        	 
	}  
	public void daiPrivilegi() throws SQLException 
	{
		
			query="set sql_safe_updates=?";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				prepQ.setInt(1,0);

				prepQ.executeUpdate();

			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("dai privilegi").log(Level.INFO, eccezione, e);
			}

		}
	

	public int retUltimoOrdine() throws SQLException 
	{
		int id=0;
		 query="select count(*) as massimo from fattura";
		 
		 try(Connection conn=ConnToDb.generalConnection();
				 PreparedStatement prepQ=conn.prepareStatement(query);
				 )
		 {
			 ResultSet rs=prepQ.executeQuery();
			 while(rs.next())
				{
					id=rs.getInt("massimo");

				}
			
		 }catch(SQLException e)
		 {
			 java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		 }

		
			
		return id;
		
		
	}
	
	public boolean annullaOrdine(int idC) throws SQLException
	{
		boolean state=false;
		int row=0;
		
		query="delete from fattura where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1,idC);
			row=prepQ.executeUpdate();
			if(row==1)
				state=true;
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}
			
			return state;

		}
		
}
	

		

	
	
	
	
	
	

         


