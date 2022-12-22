package web.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;

import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;

public class GiornaleBean {
	private Exception mex;
	private List<Raccolta> listaGiornali;
	private String giornale="giornale";
	private String  titolo;
	private String tipologia;
	private String lingua;
	private String editore;
	private LocalDate dataPubb;
	private int copieRimanenti;
	private int disponibilita;
	private float prezzo;
	private int id;
	private Date data;
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
	}
	public List<Raccolta> getListaGiornali() {
		return listaGiornali;
	}
	public void setListaGiornali(List<Raccolta> listaGiornali) {
		this.listaGiornali = listaGiornali;
	}
	public String getGiornale() {
		return giornale;
	}
	public void setGiornale(String giornale) {
		this.giornale = giornale;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeG() throws SQLException {
		String nomeG="";
		String query="select titolo from giornale where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1,SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				nomeG=rs.getString("titolo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return nomeG;
	}
	
	
	public float getPrezzo() throws SQLException {
		String query="select prezzo from giornale where id=?";
		float prezzo=(float) 0.0;
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				prezzo=rs.getFloat("prezzo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return prezzo;
	}
	public String retTip(Giornale g) throws SQLException {
		String tipologia="";
		String query="select tipologia from giornale where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, g.getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				tipologia=rs.getString("tipologia");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return tipologia;
	}
	
	public void aggiornaDisponibilita(Giornale g) throws SQLException
	{
		//vedere il segno che cambia
		int d=SystemBean.getIstance().getQuantita();
		int i=g.getCopieRimanenti();
		int rim=i-d;
		
		
		
	
		
	 String	query="update giornale set copieRim=? where  idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, g.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna copie rimanenti g").log(Level.INFO, "eccezione in mysql", e);
		}
		


	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getEditore() {
		return editore;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public LocalDate getDataPubb() {
		return dataPubb;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public int getCopieRimanenti() {
		return copieRimanenti;
	}
	public void setCopieRimanenti(int copieRimanenti) {
		this.copieRimanenti = copieRimanenti;
	}
	public int getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(int disponibilita) {
		this.disponibilita = disponibilita;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public  boolean creaGiornale(Giornale g) throws SQLException  {
		
		boolean state=false;
		
		String query= "INSERT INTO `ispw`.`giornale`"
				+ "(`titolo`,"
				+ "`tipologia`,"
				+ "`lingua`,"
				+ "`editore`,"
				+ "`dataPubblicazione`,"
				+ "`copiRim`,"
				+ "`disp`,"
				+ "`prezzo`)"
				+ "VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
		
		prepQ.setString(1,g.getTitolo()); 
		prepQ.setString(2,g.getTipologia());
		prepQ.setString(3,g.getLingua());
		prepQ.setString(4,g.getEditore());
		prepQ.setDate(5, java.sql.Date.valueOf(g.getDataPubb().toString())); 
		prepQ.setInt(6,g.getCopieRimanenti());
		prepQ.setInt(7, g.getDisponibilita());
		prepQ.setFloat(8, g.getPrezzo());

		prepQ.executeUpdate();

		state= true; // true		 			 	
	
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("creazione giornale").log(Level.INFO, "eccezione", e);
		}


	return state;


}
	public void aggiornaData(Giornale g, Date sqlDate) throws SQLException {
		int row=0;
		String query="update giornale set dataPubblicazione=? where titolo=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setString(2, g.getTitolo());
			row=prepQ.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println("row" +row);
	}

}
