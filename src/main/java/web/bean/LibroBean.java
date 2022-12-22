package web.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import laptop. model.raccolta.CategorieLibro;
import laptop. model.raccolta.Factory;
import laptop. model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.utilities.ConnToDb;



public class LibroBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception mex;
	private List<Raccolta> listaLibri;
	private String LIBRO="libro";
	
	private String titolo;
	private String codIsbn;
	private String editore;
	private String autore;
	private String lingua;
	private String categorie;
	private LocalDate dataPubb;
	private String recensione;
	private int nrCopie; // numero copie vendute
	private String desc;
	private int disponibilita;
	
	private Date date;
	
	private int numeroPagine;
	private String tipologia;

	private int id;

	private float prezzo;
	
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}



	public LibroBean()
	{
		java.util.logging.Logger.getLogger("libro bean").log(Level.INFO,"costruttore");

	}
	
	
	
	public List<Raccolta> getLibri() throws SQLException
	{
		ArrayList<Raccolta> catalogo=new ArrayList<>();

		String query="select * from ispw.libro";
		Factory f=new Factory();
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
			java.util.logging.Logger.getLogger("get libri").log(Level.INFO, "eccezione in mysql", e);
		}
		

		return catalogo;
	}

	public List<Raccolta> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(List<Raccolta> listaLibri) {
		this.listaLibri = listaLibri;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Exception getMex() {
		return mex;
	}



	public void setMex(Exception mex) {
		this.mex = mex;
	}

	public String getNomeL() throws SQLException
	{
		String nome="";
		String query="select titolo from libro where idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				nome=rs.getString("titolo");
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return nome;
	}
	
	public int getRimanenza()
	{
		int rimanenza=0;
		String query="select copieRimanenti from libro where idProd=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				rimanenza=rs.getInt("copieRimanenti");
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get titolo libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return rimanenza;
	}
	public float getPrezzo()
	{
		float prezzo=(float) 0.0;
		String query="select prezzo from libro where idProd=?";
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
	
	public String getCategoriaD()
	{
		String cat="";
		String query="select categoria from libro where idProd=?" ;
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setInt(1, SystemBean.getIstance().getId());
			ResultSet rs=prepQ.executeQuery();
			if(rs.next())
			{
				cat=rs.getString("categoria");
			}
			
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("get categoria libro").log(Level.INFO, "eccezione in mysql", e);

		}
		return cat;
	}

	public void aggiornaDisponibilita(Libro l) throws SQLException
	{
		//vedere il segno che cambia
		int d=SystemBean.getIstance().getQuantita();
		int i=l.getNrCopie();
		int rim=i-d;
		
		
		
	
		
	 String	query="update libro set copieRimanenti=? where  idProd=?";
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query))
		{
			prepQ.setInt(1, rim);
			prepQ.setInt(2, l.getId());
			prepQ.executeUpdate();
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("aggiorna copie rimanenti l").log(Level.INFO, "eccezione in mysql", e);
		}
		


	}
public String retTip(Libro l) throws SQLException {
		
		String query="select categoria from libro where Cod_isbn=? or idProd=?";
		String cat=null;
		
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setString(1, l.getCodIsbn());
			prepQ.setInt(2, l.getId());
			ResultSet rs=prepQ.executeQuery();
		
		
			while ( rs.next() ) {
				cat=rs.getString("categoria");

			}
		}catch(SQLException e)
		{
			java.util.logging.Logger.getLogger("tipo l").log(Level.INFO, "eccezione ottenuta:", e);
		}
		return cat;


	}
public String setCategorie()
{
	
	String s="ADOLESCENTI_RAGAZZI"+"\n";
	s+="ARTE"+"\n";
	s+="CINEMA_FOTOGRAFIA"+"\n";
	s+="BIOGRAFIE"+"\n";
	s+="DIARI_MEMORIE"+"\n";
	s+="CALENDARI_AGENDE"+"\n";
	s+="DIRITTO"+"\n";
	s+="DIZINARI_OPERE"+"\n";
	s+="ECONOMIA"+"\n";
	s+="FAMIGLIA"+"\n";
	s+="SALUTE_BENESSERE"+"\n";
	s+="FANTASCIENZA_FANTASY"+"\n";
	s+="FUMETTI_MANGA"+"\n";
	s+="GIALLI_THRILLER"+"\n";
	s+="COMPUTER_GIOCHI"+"\n";
	s+="HUMOR"+"\n";
	s+="INFORMATICA"+"\n";
	s+="WEB_DIGITAL_MEDIA"+"\n";
	s+="LETTERATURA_NARRATIVA"+"\n";
	s+="LIBRI_BAMBINI"+"\n";
	s+="LIBRI_SCOLASTICI"+"\n";
	s+="LIBRI_UNIVERSITARI"+"\n";
	s+="RICETTARI_GENERALI"+"\n";
	s+="LINGUISTICA_SCRITTURA"+"\n";
	s+="POLITICA"+"\n";
	s+="RELIGIONE"+"\n";
	s+="ROMANZI_ROSA"+"\n";
	s+="SCIENZE"+"\n";
	s+="TECNOLOGIA_MEDICINA"+"\n";
	return s;
	}



public String getCodIsbn() {
	return codIsbn;
}



public void setCodIsbn(String codIsbn) {
	this.codIsbn = codIsbn;
}



public String getEditore() {
	return editore;
}



public void setEditore(String editore) {
	this.editore = editore;
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



public LocalDate getDataPubb() {
	return dataPubb;
}



public void setDataPubb(LocalDate dataPubb) {
	this.dataPubb = dataPubb;
}



public String getRecensione() {
	return recensione;
}



public void setRecensione(String recensione) {
	this.recensione = recensione;
}



public int getNrCopie() {
	return nrCopie;
}



public void setNrCopie(int nrCopie) {
	this.nrCopie = nrCopie;
}



public String getDesc() {
	return desc;
}



public void setDesc(String desc) {
	this.desc = desc;
}



public int getDisponibilita() {
	return disponibilita;
}



public void setDisponibilita(int disponibilita) {
	this.disponibilita = disponibilita;
}



public int getNumeroPagine() {
	return numeroPagine;
}



public void setNumeroPagine(int numeroPagine) {
	this.numeroPagine = numeroPagine;
}



public String getTipologia() {
	return tipologia;
}



public void setTipologia(String tipologia) {
	this.tipologia = tipologia;
}







public String getTitolo() {
	return titolo;
}



public void setTitolo(String titolo) {
	this.titolo = titolo;
}



public void setCategoria(String categoria) {
	switch (categoria){
	case "ADOLESCENTI_RAGAZZI": 
		this.categorie = CategorieLibro.ADOLESCENTI_RAGAZZI.toString();  
		break;
	case "ARTE": 
		this.categorie = CategorieLibro.ARTE.toString();  
		break;
	case "CINEMA_FOTOGRAFIA" : 
		this.categorie = CategorieLibro.CINEMA_FOTOGRAFIA.toString();  
		break;
	case "BIOGRAFIE" : 
		this.categorie = CategorieLibro.BIOGRAFIE.toString();  
		break;
	case "DIARI_MEMORIE" : 
		this.categorie = CategorieLibro.DIARI_MEMORIE.toString();  
		break;
	case "CALENDARI_AGENDE" : 
		this.categorie = CategorieLibro.CALENDARI_AGENDE.toString();  
		break;
	case "DIRITTO" : 
		this.categorie = CategorieLibro.DIRITTO.toString();  
		break;
	case "DIZINARI_OPERE" : 
		this.categorie = CategorieLibro.DIZINARI_OPERE.toString();  
		break;
	case "ECONOMIA" : 
		this.categorie = CategorieLibro.ECONOMIA.toString();  
		break;
	case "FAMIGLIA" : 
		this.categorie = CategorieLibro.FAMIGLIA.toString();  
		break;
	case "SALUTE_BENESSERE" : 
		this.categorie = CategorieLibro.SALUTE_BENESSERE.toString();  
		break;
	case "FANTASCIENZA_FANTASY" : 
		this.categorie = CategorieLibro.FANTASCIENZA_FANTASY.toString();  
		break;
	case "FUMETTI_MANGA" : 
		this.categorie = CategorieLibro.FUMETTI_MANGA.toString();  
		break;
	case "GIALLI_THRILLER" : 
		this.categorie = CategorieLibro.GIALLI_THRILLER.toString();  
		break;		
	case "COMPUTER_GIOCHI" : 
		this.categorie = CategorieLibro.COMPUTER_GIOCHI.toString();  
		break;
	case "HUMOR" : 
		this.categorie = CategorieLibro.HUMOR.toString();  
		break;
	case "INFORMATICA" : 
		this.categorie = CategorieLibro.INFORMATICA.toString();  
		break;		
	case "WEB_DIGITAL_MEDIA" : 
		this.categorie = CategorieLibro.WEB_DIGITAL_MEDIA.toString();  
		break;		
	case "LETTERATURA_NARRATIVA" : 
		this.categorie = CategorieLibro.LETTERATURA_NARRATIVA.toString();  
		break;		
	case "LIBRI_BAMBINI" : 
		this.categorie = CategorieLibro.LIBRI_BAMBINI.toString();  
		break;		
	case "LIBRI_SCOLASTICI" : 
		this.categorie = CategorieLibro.LIBRI_SCOLASTICI.toString();  
		break;
	case "LIBRI_UNIVERSITARI" : 
		this.categorie = CategorieLibro.LIBRI_UNIVERSITARI.toString();  
		break;			
	case "RICETTARI_GENERALI" : 
		this.categorie = CategorieLibro.RICETTARI_GENERALI.toString();  
		break;	
	case "LINGUISTICA_SCRITTURA" : 
		this.categorie = CategorieLibro.LINGUISTICA_SCRITTURA.toString();  
		break;
	case "POLITICA" : 
		this.categorie = CategorieLibro.POLITICA.toString();  
		break;
	case "RELIGIONE" : 
		this.categorie = CategorieLibro.RELIGIONE.toString();  
		break;
	case "ROMANZI_ROSA" : 
		this.categorie = CategorieLibro.ROMANZI_ROSA.toString();  
		break;		
	case "SCIENZE" : 
		this.categorie = CategorieLibro.SCIENZE.toString();  
		break;		
	case "TECNOLOGIA_MEDICINA" : 
		this.categorie = CategorieLibro.TECNOLOGIA_MEDICINA.toString();  
		break;
	
	default :
		this.categorie = null;
		break;
	}
}



public Date getDate() {
	return date;
}



public void setDate(Date date) {
	this.date = date;
}
//Creo il libro nel terzo caso d'uso per l'aggiunta manuale
	public boolean creaLibrio(Libro l) throws SQLException
	{
				boolean state=false;
				String query= "INSERT INTO `ispw`.`libro`"
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
						java.util.logging.Logger.getLogger("crea libro").log(Level.INFO, "ecezione", e);
				}
			
			
		
		return state;


	}



	public int aggiornaData(Libro l, Date sqlDate) throws SQLException {
		int row=0;
		String query="update libro set dataPubblicazioe=? where titolo=?";
		try(Connection conn=ConnToDb.generalConnection();
				PreparedStatement prepQ=conn.prepareStatement(query);)
		{
			prepQ.setDate(1, sqlDate);
			prepQ.setString(2, l.getTitolo());
			row=prepQ.executeUpdate();
		}
		return row;
		
	}

}
