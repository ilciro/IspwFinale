package laptop.utilities;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;


public class CreateDefaultDB 
{

	private static String creaEditore="Create table if not exists EDITORE("
			+ "idEditor int primary key not null auto_increment,"
			+ "idUser int, casaEditrice VARCHAR (200), "
			+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser));";
	private static String creaScrittore="Create table  if not exists SCRITTORI("
			+ "idScrittore int primary key not null auto_increment,"
			+ "idUser int, editoreAssociato int, "
			+ "FOREIGN KEY (editoreAssociato) REFERENCES EDITORE(idEditor) ,"
			+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser) );";
	private static String creaGiornale="Create table if not exists GIORNALE("
			+ "titolo VARCHAR(200),tipologia Varchar(60),"
			+ "lingua varchar(10),"
			+ "editore varchar(200) ,"
			+ "dataPubblicazione date,"
			+ "copiRim int, "
			+ "disp int,"
			+ "prezzo float,"
			+ "id int primary key not null auto_increment);";
	private static String creaRivista="Create table if not exists RIVISTA("
			+ "titolo VARCHAR(200),tipologia Varchar(60),"
			+ "autore varchar(200), lingua varchar(10),"
			+ "editore varchar(200) ,"
			+ "Descrizione text, dataPubblicazione date,"
			+ "disp int,"
			+ "prezzo float,"
			+ "copieRimanenti int,"
			+ "id int primary key not null auto_increment);";
	private static String creaCartaCredito="Create table if not exists cartacredito ( "
			+ "nomeP VARCHAR(10),cognomeP  Varchar(20),"
			+ "codiceCarta varchar(20),"
			+ "scad date ,"
			+ "codicePin varchar(5) ,"
			+ "ammontare float );";
	private static String creaFattura="Create table if not exists FATTURA("
			+ "nome varchar(10),cognome varchar(10),"
			+ "via varchar(50),"
			+ "comunicazoni text,"
			+ "id int auto_increment not null  primary key,"
			+ "ammontare float);";
	private static String creaAmministratore="Create table  if not exists AMMINISTRATORE("
			+ "idAdmin int primary key not null auto_increment,"
			+ "idUser int,"
			+ "FOREIGN KEY (idUser) REFERENCES USERS(idUser));";
	private static String creaLibro="Create table  if not exists LIBRO("
			+ "titolo VARCHAR(200), numeroPagine int,"
			+ "Cod_isbn varchar(10) not null unique,"
			+ "editore varchar(200),"
			+ "autore varchar(200), lingua varchar(10),"
			+ "categoria Varchar(60), dataPubblicazione date,"
			+ "recensione text, copieVendute int, breveDescrizione text,"
			+ "disp int, prezzo float,"
			+ "copieRimanenti int,"
			+ "idProd int primary key auto_increment);";
	private static String creaPagamento="create table if not exists pagamento("
			+ "id_op int primary key auto_increment,"
			+ "metodo varchar(10),esito int ,"
			+ "nomeUtente varchar(10),spesaTotale float,"
			+ "eMail varchar(100 ),"
			+ "tipoAcquisto varchar(20),"
			+ "idProd int )";


	private CreateDefaultDB() 
	{
		java.util.logging.Logger.getLogger("Test Costruttore").log(Level.INFO, "creo db default");
		
	}

	public static void createDefaultDB () throws SQLException, FileNotFoundException, ClassNotFoundException
	{
	    boolean status;
	    
	   


	 
		
			 status = ConnToDb.initailConnection() && !ConnToDb.connection() ; 
			 
		
			// status = 1 se c'e' workbench ma non il db 
			// status = 0 se c'e' tutto
			// Se non trovo il db chiamo questa funzione che lo crea
			if(status) 
			{
				try (Statement st=ConnToDb.conn.createStatement())
				{
					st.execute("CREATE DATABASE IF NOT EXISTS ispw");
				}
				try (Statement st=ConnToDb.conn.createStatement())
				{
					st.execute("USE ISPW");
				}
			
				
				try (Statement st=ConnToDb.conn.createStatement())
				{
					st.execute("CREATE TABLE if not exists USERS(idUser INT primary key not null auto_increment,idRuolo VARCHAR(1) NOT NULL DEFAULT 'U',Nome VARCHAR(40),Cognome VARCHAR(40),Email VARCHAR(50) UNIQUE ,pwd VARCHAR(16),descrizione text,DataDiNascita date);"
							+ "");
				}
				try(Statement st=ConnToDb.conn.createStatement())
				{
					st.execute(creaEditore);
					st.execute(creaAmministratore);
					st.execute(creaCartaCredito);
					st.execute(creaFattura);
					st.execute(creaGiornale);
					st.execute(creaLibro);
					st.execute(creaPagamento);
					st.execute(creaRivista);
					st.execute(creaScrittore);
				}
				
				try(Statement st=ConnToDb.conn.createStatement())
				{
					st.execute("Create table if not exists NEGOZIO"
							+ "( idNegozio int not null auto_increment primary key, "
							+ "nome VARCHAR(100) , via VARCHAR(100),"
							+ "isValid boolean, isOpen BOOLEAN"
							+ ")");
				}
				
				java.util.logging.Logger.getLogger("Test create db").log(Level.INFO, "Connesso a mysql workbench, ma non ho torvato il database 'ispw'\n"
						+ "Database creato "+ "tabelle create" +"\n");
				
				
				if (PopulateDefaultDb.populateDefaultDb()) {
					java.util.logging.Logger.getLogger("Test create db").log(Level.INFO, "tabella popolata valori default");

					
						ConnToDb.conn.close();
						java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "trovato db e connesso.. buon proseguimento");

					
				}
				else
				{
					java.util.logging.Logger.getLogger("Test popolamento db").log(Level.INFO, "Ops ..! Qualcosa andato storto nel popolamento");

				}
			}
			// Se trovo tutto  chiudo la connesione e vado avanti con l'esecuzione del programma
			// Se qualcosa non va chiudo la connessione e vado nel cacth
			else 
			{
				java.util.logging.Logger.getLogger("Test Eccezione").log(Level.WARNING, "Ops..! qualcosa è andato storto nella connesione al database!");

			}
		}
		



}
