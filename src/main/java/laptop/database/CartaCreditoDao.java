package laptop.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laptop.model.CartaDiCredito;
import laptop.utilities.ConnToDb;

public class CartaCreditoDao {

	private  String n;
	private  String cog;
	private String query;
	private static String eccezione="eccezione ottenuta :";
	


	public ObservableList<CartaDiCredito> getCarteCredito(String nome) throws SQLException 
	{
		String cod;
		query="select nomeP,cognomeP,codiceCarta from cartacredito where nomeP=?";
		
		ObservableList<CartaDiCredito> catalogo=FXCollections.observableArrayList();

		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setString(1, nome);
			ResultSet rs=prepQ.executeQuery();
			while(rs.next())
			{
				n=rs.getString(1);
				cog=rs.getString(2);
				cod=rs.getString(3);


				catalogo.add(new CartaDiCredito(n,cog,cod, null, cod,0));


			}
		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}
			
	
		return catalogo;


	}	

	public void daiPrivilegi() throws SQLException
	{

		query="set sql_safe_updates=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, 0);
			prepQ.executeUpdate();

		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, eccezione, e);
		}


	}
	public void insCC(CartaDiCredito cc) throws SQLException
	{

		query="insert into cartacredito (nomeP,cognomeP,codiceCarta,scad,codicePin,ammontare)  values(?,?,?,?,?,?)";
				 
		
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				prepQ.setString(1,cc.getNomeUser());
				prepQ.setString(2, cc.getCognomeUser());
				prepQ.setString(3, cc.getNumeroCC());
				prepQ.setDate(4, (Date) cc.getScadenza());
				prepQ.setString(5,cc.getCiv());
				//in alternativa vis.getSpesa
				prepQ.setFloat(6, cc.getPrezzoTransazine());
				prepQ.executeUpdate();
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			}


	}

	
	public CartaDiCredito  popolaDati(CartaDiCredito cc) throws SQLException
	{
		String cod;

		n = null;
		cog = null;
		cod = null;
		
		query="select nomeP,cognomeP,codiceCarta,scad from cartacredito where codiceCarta=?";

		
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);){
				prepQ.setString(1, cc.getNumeroCC());
				ResultSet rs=prepQ.executeQuery();

				while(rs.next())
				{
					n=rs.getString(1);
					cog=rs.getString(2);
					cod=rs.getString(3);
		}

				cc.setNomeUser(n);
				cc.setCognomeUser(cog);
				cc.setNumeroCC(cod);
			}	
			return cc;

	}


}











