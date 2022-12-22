package web.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;

import laptop.model.User;
import laptop.utilities.ConnToDb;

public class UserBean {
	
	enum Ruoli {
		ADMIN,
		UTENTE,
		SCRITTORE,
		EDITORE;
 }
	
	
		


	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String descrizione;
	private LocalDate dataDiNascita;
	private String r;
	private String mex;
	private String listaUtenti;
	
	//istanza per il patter singleton
	private static UserBean userInstance ;

	private UserBean() {

	}

	public static UserBean getInstance() {
		if (userInstance == null)
		{
			userInstance = new UserBean();
			return userInstance; 
		}
		return userInstance;
	} 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	public String getIdRuolo()  {
		
		return r;
	}



	public void setIdRuolo(String ruolo) {

		 switch (ruolo){
			case "ADMIN":
				r = Ruoli.ADMIN.toString();
				break;				
			case "EDITORE":
				r = Ruoli.EDITORE.toString();
				break;
			case "SCRITTORE":
				r = Ruoli.SCRITTORE.toString();
				break;
			case "UTENTE":
				r = Ruoli.UTENTE.toString();
				break;	
			case "W":
				r = Ruoli.SCRITTORE.toString();
				break;
			case "E":
				r = Ruoli.EDITORE.toString();
				break;	
			case "A":
				r = Ruoli.ADMIN.toString();
				break;
				
			default:
				r= Ruoli.UTENTE.toString();
				break;
			}
		

	}

	public static String getRuolo (User u) throws SQLException
	{

		

			
			
			String query="SELECT idRuolo FROM ispw.users where Email = ?";
			String r="";
			try(Connection conn=ConnToDb.generalConnection();
					PreparedStatement prepQ=conn.prepareStatement(query);)
			{
				prepQ.setString(1, u.getEmail());
			
				ResultSet rs = prepQ.executeQuery();
				while(rs.next())
				{
					r =rs.getString(1);
	
				}
			
			}catch(SQLException e)
			{
				java.util.logging.Logger.getLogger("get ruolo user").log(Level.INFO, "eccezione nel db", e);

			}
		u.setIdRuolo(r);
		
		return r;

	}

	public String getMex() {
		return mex;
	}

	public void setMex(String mex) {
		this.mex = mex;
	}

	public String getListaUtenti() {
		return listaUtenti;
	}

	public void setListaUtenti(String listaUtenti) {
		this.listaUtenti = listaUtenti;
	}

	


}
