package web.bean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import laptop.model.raccolta.Factory;
import laptop.model.raccolta.Raccolta;
import laptop. model.raccolta.Rivista;
import laptop.utilities.ConnToDb;

public class RivistaBean {
	private Exception mex;
	private List<Raccolta> listaRiviste;
	private String listaCategorie;
	private String titolo;
	private String tipologia;
	private String autore;
	private String lingua;
	private String editore;
	private String descrizione;
	private LocalDate dataPubb;
	private int disp;
	private float prezzo;
	private int copieRim;
	private Date data;

	
	private static String rivista="rivista";
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
	}
	public List<Raccolta> getListaRiviste() {
		return listaRiviste;
	}
	public void setListaRiviste(List<Raccolta> listaRiviste) {
		this.listaRiviste = listaRiviste;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	public String getNomeR() throws SQLException {
		String titoloR="";
		String query="select titolo from rivista where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				titoloR=rs.getString("titolo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return titoloR;
	}
	public float getPrezzoR() {
		float prezzoR=(float) 0.00;
		String query="select prezzo from rivista where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				prezzoR=rs.getFloat("prezzo");
			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return prezzoR;
	}

	public List<Raccolta> getRiviste() throws SQLException
	{
		

		Factory f=new Factory();
		List<Raccolta> catalogo=new ArrayList<>();
		 
		String query="select * from rivista";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);
				ResultSet rs=prepQ.executeQuery())
		{
            while(rs.next())
            {

        		
        			f.createRaccoltaFinale1(rivista, rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null);
					f.createRaccoltaFinale2(rivista,0,null,0,rs.getInt(8),rs.getFloat(9),rs.getInt(10));
					catalogo.add(f.createRaccoltaFinaleCompleta(rivista, rs.getDate(7).toLocalDate(), null, null,rs.getInt(11)));
		
					
        		
            }
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("elenco riviste").log(Level.INFO, "eccezione nel db", e);
		}
		return catalogo;
		
	}
	public String retTip(Rivista r) {
		String tipologiaR="";
		String query="select tipologia from rivista where id=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, r.getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				tipologiaR=rs.getString("tipologia");
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return tipologiaR;
	}
	public void aggiornaDisponibilita(Rivista r) throws SQLException
	{
		//vedere il segno che cambia
		int d=SystemBean.getIstance().getQuantita();
		int i=r.getCopieRim();
		int rim=i-d;
		
		
		
	
		
	 String	query="update rivista set copieRimanenti=? where  idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, r.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna copie rimanenti r").log(Level.INFO, "eccezione in mysql", e);
		}
		


	}
	public String getListaCategorie() {
		return listaCategorie;
	}
	public void setListaCategorie(String listaCategorie) {
		this.listaCategorie = listaCategorie;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public LocalDate getDataPubb() {
		return dataPubb;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public int getDisp() {
		return disp;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public int getCopieRim() {
		return copieRim;
	}
	public void setCopieRim(int copieRim) {
		this.copieRim = copieRim;
	}
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public float getPrezzo() {
		return prezzo;
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
	public Boolean creaRivista(Rivista r) throws SQLException {
    	
		
		boolean state=false;
		String query= "INSERT INTO `ispw`.`rivista`"
	 			+ "(`titolo`,"
	 			+ "`tipologia`,"
	 			+ "`autore`,"
	 			+ "`lingua`,"
	 			+ "`editore`,"
	 			+ "`Descrizione`,"
	 			+ "`dataPubblicazione`,"
	 			+ "`disp`,"
	 			+ "`prezzo`,"
	 			+ "`copieRimanenti`)"
	 			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
		prepQ.setString(1,r.getTitolo()); 
		prepQ.setString(2,r.getTipologia());
		prepQ.setString(3,r.getAutore());
		prepQ.setString(4,r.getLingua());
		prepQ.setString(5,r.getEditore());
		prepQ.setString(6,r.getDescrizione());
		prepQ.setDate(7, java.sql.Date.valueOf(r.getDataPubb().toString()));  
		prepQ.setInt(8, r.getDisp());
		prepQ.setFloat(9, r.getPrezzo());
		prepQ.setInt(10,r.getCopieRim());


		
		prepQ.executeUpdate();
	 	state= true; // true		 			 	
		}catch(SQLException e)
		{
			state=false;
			java.util.logging.Logger.getLogger("crea rivista").log(Level.INFO, "eccezione", e);
		}
	



return state;


}
	public void aggiornaData(Rivista r, Date sqlDate) throws SQLException {
		String query="update rivista set dataPubblicazione=? where titolo=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setString(2, r.getTitolo());
			prepQ.executeUpdate();
		}
		
	}
	
	
	public String elencoCategorie()
	{
		String s= "SETTIMANALE";
		s+="\n";
		s+="BISETTIMANALE" ;
		s+="\n";
		s+= "MENSILE";
		s+="\n";
		s+= "BIMESTRALE" ;
		s+="\n";
		s+="TRIMESTRALE" ;	
		s+="\n";
		s+= "ANNUALE" ;
		s+="\n";

		s+= "ESTIVO" ;
		s+="\n";

		s+= "INVERNALE" ;
		s+="\n";

		s+= "SPORTIVO" ;
		s+="\n";

		s+= "CINEMATOGRAFICA" ; 
		s+="\n";

		s+= "GOSSIP" ;
		s+="\n";

		s+= "TELEVISIVO" ;
		s+="\n";

		s+= "MILITARE" ;
		s+="\n";

		s+= "INFORMATICA";
		s+="\n";

		return s;
	}
}
