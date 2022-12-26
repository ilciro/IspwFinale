package laptop.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import laptop.controller.ControllerSystemState;
import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibroDao  {
	
	private Factory f;
	private  String query ;
	
	private  int q; // quantita'
	
	private boolean state=false;
	private ControllerSystemState vis=ControllerSystemState.getIstance();

	private static final String LIBRO = "libro";
	private static final String ECCEZIONE="ECCEZIONE generata:";
	private static String queryL="select * from libro where idProd=?";


	public float getCosto(Libro l) throws SQLException
	{
		float prezzo=(float) 0.0;
		
		query=queryL;

		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
			
			while ( rs.next() ) {
				prezzo=rs.getFloat("prezzo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("costo libro").log(Level.INFO, ECCEZIONE, e);
		}
		return prezzo;

	}

	public void aggiornaDisponibilita(Libro l) throws SQLException
	{
		//vedere il segno che cambia
		int d=vis.getQuantita();
		int i=l.getNrCopie();
		int rim=i-d;
		
		
		
	
		
		query="update libro set copieRimanenti=? where  idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, l.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna disp l").log(Level.INFO, ECCEZIONE, e);
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
			java.util.logging.Logger.getLogger("dai privilegi").log(Level.INFO, ECCEZIONE, e);
		}
			

	}
	

	public ObservableList<Raccolta> getLibri() throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();

		query="select * from ispw.libro";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
		while(rs.next())
		{
			
				
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
					f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
					
				
			
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get libri").log(Level.INFO, ECCEZIONE, e);
		}
		

		return catalogo;
	}

	public ObservableList<Raccolta> getLibriByName(String s) throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		
		query="select * from libro where titolo=? or autore=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ= conn.prepareStatement(query);)
		{
			prepQ.setString(1, s);
			prepQ.setString(2, s);
			ResultSet rs=prepQ.executeQuery();
			while(rs.next())
			{
				
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
					f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
				
					
				
			}
		
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get libri by name").log(Level.INFO, ECCEZIONE, e);
		}
		
		
		return catalogo;

	}

	public Libro getLibro(Libro l) throws SQLException
	{
		query=queryL;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
		while (rs.next())
		{
			f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
			
			
			f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

			l=(Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15));
		
			
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get libro").log(Level.INFO, ECCEZIONE, e);
		}
		
		return l;

	}
	

	public LibroDao()
	{
		f=new Factory();
	}

	public int retId(Libro l) throws SQLException {
		int id=0;
		query="select idProd from libro where Cod_isbn=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setString(1, l.getCodIsbn());
			ResultSet rs=prepQ.executeQuery();
		
			while ( rs.next() ) {
				id=rs.getInt("idProd");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("ret id libro").log(Level.INFO, ECCEZIONE, e);
		}
		return id;



	}
	

	public String retTip(Libro l) throws SQLException {
		
		query="select categoria from libro where Cod_isbn=? or idProd=?";
		String categoria=null;
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setString(1, l.getCodIsbn());
			prepQ.setInt(2, l.getId());
			ResultSet rs=prepQ.executeQuery();
		
		
			while ( rs.next() ) {
				categoria=rs.getString("categoria");

			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("tipo l").log(Level.INFO, ECCEZIONE, e);
		}
		return categoria;


	}

	public void aggiornaCopieVendute(Libro l) throws SQLException
	{
		
		int d=vis.getQuantita();
		int i=l.getNrCopie();
		
		int rim=i+d;
		
		
		query="update ispw.libro set copieVendute=copieVendute+? where Cod_isbn=? ";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setString(2, l.getCodIsbn());
			
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna copie vendute").log(Level.INFO, ECCEZIONE, e);
		}
		

		

	}

	// Creo il libro nel terzo caso d'uso per l'aggiunta manuale
	public boolean creaLibrio(Libro l) throws SQLException
	{
				
				query= "INSERT INTO `ispw`.`libro`"
						+ "(`titolo`,"
						+ "`numeroPagine`,"
						+ "`Cod_isbn`,"
						+ "`editore`,"
						+ "`autore`,"
						+ "`lingua`,"
						+ "`categoria`,"
						+ "`dataPubblicazione`,"
						+ "`recensione`,"
						+ " copieVendute,"
						+ "`breveDescrizione`,"
						+ "`disp`,"
						+ "`prezzo`,"
						+ "`copieRimanenti`,"
						+ "idProd )"
						+ "VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				try(Connection conn=ConnToDb.generalConnection();
						PreparedStatement prepQ=conn.prepareStatement(query);)
				{
				prepQ.setString(1,l.getTitolo()); 
				prepQ.setInt(2,l.getNumeroPagine());
				prepQ.setString(3,l.getCodIsbn());
				prepQ.setString(4,l.getEditore());
				prepQ.setString(5,l.getAutore());
				prepQ.setString(6,l.getLingua());
				prepQ.setString(7,l.getCategoria());
				prepQ.setDate(8, java.sql.Date.valueOf(l.getDataPubb().toString()));  
				prepQ.setString(9, l.getRecensione());
				prepQ.setInt(10,l.getNrCopie());
				prepQ.setString(11, l.getDesc());
				prepQ.setInt(12, l.getDisponibilita());
				prepQ.setFloat(13, l.getPrezzo());
				prepQ.setInt(14,l.getNrCopie());
				prepQ.setInt(15, 0);
				prepQ.executeUpdate();
				state= true; // true	
				}catch(SQLException e)
				{
						java.util.logging.Logger.getLogger("crea libro").log(Level.INFO, ECCEZIONE, e);
				}
			
			
		
		return state;


	}
	
	public int getQuantita(Libro l) throws SQLException
	{
		query="select copieRimanenti from libro where idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareCall(query);)
		{
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
		
			while (rs.next()) {
					q = rs.getInt(1);
				}

			}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("quantita l").log(Level.INFO, ECCEZIONE, e);
		}
			
		

		


		return q;
	}
	

	// Uso questo pulseante quando clicco sul pulsante mostra libro 
	public boolean checkDisp(Libro l) throws SQLException
	{
		int id=l.getId();
		int disp=0;
		query="select disp from ispw.libro where idProd=?";
			
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				
				)
		{
			
		prepQ.setInt(1, id);
		ResultSet rs=prepQ.executeQuery();
				while(rs.next())
				{
					disp = rs.getInt(1);
					if (disp == 1)
						state=true;
					
				
					java.util.logging.Logger.getLogger("Disponibilita").log(Level.INFO,"libro disponibile");
				}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("disponibilita l").log(Level.INFO, ECCEZIONE, e);
		}
				

		return state;
	}

	//fare singoli get dal db con associazione alle funzioni 
	//o fare associazioni dal contoller
	 
	public String getNome(Libro l) throws SQLException
	{
		String name=null;
		query="select titolo from libro where idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
			while (rs.next())
		{
			name = rs.getString(1);
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("nome l").log(Level.INFO, ECCEZIONE, e);
		}
		return name;
	}

	public ObservableList<Raccolta> getLibroSingolo() throws SQLException
	{
		ObservableList<Raccolta> catalogo=FXCollections.observableArrayList();
		query="select * from libro ";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			
			ResultSet rs=prepQ.executeQuery();
		while (rs.next())
		{
			f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
			
			
			f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

			catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
		
			
		}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("libro singolo").log(Level.INFO, ECCEZIONE, e);
		}
		
		java.util.logging.Logger.getLogger("Catalogo").log(Level.INFO, "ctalogo {0}",catalogo);
	
		return catalogo;

	}

	public void cancella(Libro l) throws SQLException {
		int row=0;
		query="delete from libro where idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			row=prepQ.executeUpdate();
		}
		
		java.util.logging.Logger.getLogger("Cancella libro").log(Level.INFO,"libro cancellato {0}",row);

		
	}

	public ObservableList<Libro> getLibriSingoloById(Libro l) throws SQLException
	{
		query=queryL;
		ObservableList<Libro> catalogo=FXCollections.observableArrayList();
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add((Libro) f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}

		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("libro by id").log(Level.INFO, ECCEZIONE, e);
		}
		java.util.logging.Logger.getLogger("catalogo").log(Level.INFO,"catalogo trovato");

		return catalogo;

	}

	public void aggiornaLibro(Libro l) throws SQLException,NullPointerException
	{


		int rowAffected=0;

		

		query=" UPDATE libro "
				+ "SET "
				+ " `titolo` =?,"
				+ " `numeroPagine` = ?,"
				+ " `Cod_isbn` = ?,"
				+ " `editore` = ?,"
				+ " `autore` = ?,"
				+ " `lingua` = ?,"
				+ " `categoria` = ?,"
				+ " `dataPubblicazione` = ?,"
				+ " `recensione` = ?,"
				+ " `copieVendute` = ?,"
				+ " `breveDescrizione` =?,"
				+ " `disp` = ?,"
				+ " `prezzo` = ?,"
				+ " `copieRimanenti` =?"
				+ " WHERE `idProd`= ? or idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{

		prepQ.setString(1,l.getTitolo());
		prepQ.setInt(2,l.getNumeroPagine());
		prepQ.setString(3,l.getCodIsbn());
		prepQ.setString(4,l.getEditore());
		prepQ.setString(5,l.getAutore());
		prepQ.setString(6,l.getLingua());
		prepQ.setString(7,l.getCategoria());
		prepQ.setString(8, l.getDataPubb().toString());
		prepQ.setString(9,l.getRecensione());
		prepQ.setInt(10,l.getNrCopie());
		prepQ.setString(11,l.getDesc());
		prepQ.setInt(12,l.getDisponibilita());
		prepQ.setFloat(13,l.getPrezzo());
		prepQ.setInt(14,l.getNrCopie());
		prepQ.setInt(15, l.getId());
		prepQ.setInt(16, vis.getId());


		rowAffected = prepQ.executeUpdate();
		}

		java.util.logging.Logger.getLogger("Aggiornamento libro").log(Level.INFO, "row affected {0}",rowAffected);


	}	

	public void generaReport() throws SQLException, IOException
	{
		FileWriter w=null;
		w=new FileWriter("ReportFinale\\riepilogoLibro.txt");
		query="select titolo,copieVendute,prezzo as totale from libro";
		
		   try (BufferedWriter b=new BufferedWriter (w)){
		

			   try(Connection 	conn = ConnToDb.generalConnection();
					   PreparedStatement prepQ=conn.prepareStatement(query);)
			   {
		
			ResultSet rs=prepQ.executeQuery();


			while(rs.next())
			{



				rs.getString(1);
				rs.getInt(2);
				rs.getFloat(3);


				b.write("Titolo :"+rs.getString(1)+"\t"+"Ricavo totale :" +rs.getInt(2)*rs.getFloat(3)+"\n");




				b.flush();


			}

		}catch(SQLException e)
			   {
								java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
			   }
		   }
		

	}
	
	public void incrementaDisponibilita(Libro l) throws SQLException
	{
		int d=vis.getQuantita();
		int i=l.getNrCopie();
		
		int rim=i+d;
		query="update libro set copieRimanenti= ? where Cod_isbn=? or idProd=?";
		
		
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, rim);
			prepQ.setString(2, l.getCodIsbn());
			prepQ.setInt(3, l.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, ECCEZIONE, e);
		}
		
		

	}
	
	public List<Raccolta> getLibriSingoloByIdList(Libro l) throws SQLException
	{
		query=queryL;
		List<Raccolta> catalogo=new ArrayList<>();
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			ResultSet rs=prepQ.executeQuery();
		while(rs.next())
		{

			
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
				
				
					f.createRaccoltaFinale2(LIBRO, rs.getInt(2), rs.getString(3), rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));

					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));

				

		}

		}catch(SQLException e)
		{
						java.util.logging.Logger.getLogger("libro by id").log(Level.INFO, ECCEZIONE, e);
		}
		java.util.logging.Logger.getLogger("catalogo").log(Level.INFO,"catalogo trovato");

		return catalogo;

	}
	public List<Raccolta> getLibriByNameL(String s) throws SQLException
	{
		List<Raccolta> catalogo=new ArrayList<>();
		
		query="select * from libro where titolo=? or autore=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ= conn.prepareStatement(query);)
		{
			prepQ.setString(1, s);
			prepQ.setString(2, s);
			ResultSet rs=prepQ.executeQuery();
			while(rs.next())
			{
				
					f.createRaccoltaFinale1(LIBRO, rs.getString(1), rs.getString(7), rs.getString(5), rs.getString(6),rs.getString(4), rs.getString(7));
					f.createRaccoltaFinale2(LIBRO,rs.getInt(2),rs.getString(3),rs.getInt(10),rs.getInt(12),rs.getFloat(13),rs.getInt(14));
					catalogo.add(f.createRaccoltaFinaleCompleta(LIBRO, rs.getDate(8).toLocalDate(), rs.getString(9), rs.getString(11),rs.getInt(15)));
				
					
				
			}
		
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get libri by name").log(Level.INFO, ECCEZIONE, e);
		}
		
		
		return catalogo;

	}
	public String getTitolo(Libro l) 
	{
		String t="";
		query="select titolo from libro where idProd=? or idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, l.getId());
			prepQ.setInt(2, vis.getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				t=rs.getString("titolo");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return t;
	}

	public void aggiornaData(Libro l, Date sqlDate) throws SQLException {
		int row=0;
		query="update libro set dataPubblicazione=? where idProd=? or idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setInt(2, l.getId());
			prepQ.setInt(3, vis.getId());
			row=prepQ.executeUpdate();
			
		}
		
		java.util.logging.Logger.getLogger("aggiorna data").log(Level.INFO, "libri aggiornati {0}.",row);

	}

	

}
