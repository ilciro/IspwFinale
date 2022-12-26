package web.bean;

import java.io.Serializable;
import java.sql.Date;

import java.time.LocalDate;
import java.util.logging.Level;

import javafx.collections.ObservableList;
import laptop. model.raccolta.CategorieLibro;
import laptop.model.raccolta.Raccolta;



public class LibroBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception mex;
	
	private String titolo;
	private String codIsbn;
	private String editore;
	private String autore;
	private String lingua;
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
	private String categoria;
	private ObservableList<Raccolta> listaLibri;

	
	




	public LibroBean()
	{
		java.util.logging.Logger.getLogger("libro bean").log(Level.INFO,"costruttore");

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







public Date getDate() {
	return date;
}



public void setDate(Date date) {
	this.date = date;
}


public float getPrezzo() {
	return prezzo;
}






public void setPrezzo(float prezzo) {
	this.prezzo = prezzo;
}






public String getCategoria() {
	return categoria;
}






public void setCategoria(String categoria) {
	switch (categoria){
	case "ADOLESCENTI_RAGAZZI": 
		this.categoria = CategorieLibro.ADOLESCENTI_RAGAZZI.toString();  
		break;
	case "ARTE": 
		this.categoria = CategorieLibro.ARTE.toString();  
		break;
	case "CINEMA_FOTOGRAFIA" : 
		this.categoria = CategorieLibro.CINEMA_FOTOGRAFIA.toString();  
		break;
	case "BIOGRAFIE" : 
		this.categoria = CategorieLibro.BIOGRAFIE.toString();  
		break;
	case "DIARI_MEMORIE" : 
		this.categoria = CategorieLibro.DIARI_MEMORIE.toString();  
		break;
	case "CALENDARI_AGENDE" : 
		this.categoria = CategorieLibro.CALENDARI_AGENDE.toString();  
		break;
	case "DIRITTO" : 
		this.categoria = CategorieLibro.DIRITTO.toString();  
		break;
	case "DIZINARI_OPERE" : 
		this.categoria = CategorieLibro.DIZINARI_OPERE.toString();  
		break;
	case "ECONOMIA" : 
		this.categoria = CategorieLibro.ECONOMIA.toString();  
		break;
	case "FAMIGLIA" : 
		this.categoria = CategorieLibro.FAMIGLIA.toString();  
		break;
	case "SALUTE_BENESSERE" : 
		this.categoria = CategorieLibro.SALUTE_BENESSERE.toString();  
		break;
	case "FANTASCIENZA_FANTASY" : 
		this.categoria = CategorieLibro.FANTASCIENZA_FANTASY.toString();  
		break;
	case "FUMETTI_MANGA" : 
		this.categoria = CategorieLibro.FUMETTI_MANGA.toString();  
		break;
	case "GIALLI_THRILLER" : 
		this.categoria = CategorieLibro.GIALLI_THRILLER.toString();  
		break;		
	case "COMPUTER_GIOCHI" : 
		this.categoria = CategorieLibro.COMPUTER_GIOCHI.toString();  
		break;
	case "HUMOR" : 
		this.categoria = CategorieLibro.HUMOR.toString();  
		break;
	case "INFORMATICA" : 
		this.categoria = CategorieLibro.INFORMATICA.toString();  
		break;		
	case "WEB_DIGITAL_MEDIA" : 
		this.categoria = CategorieLibro.WEB_DIGITAL_MEDIA.toString();  
		break;		
	case "LETTERATURA_NARRATIVA" : 
		this.categoria = CategorieLibro.LETTERATURA_NARRATIVA.toString();  
		break;		
	case "LIBRI_BAMBINI" : 
		this.categoria = CategorieLibro.LIBRI_BAMBINI.toString();  
		break;		
	case "LIBRI_SCOLASTICI" : 
		this.categoria = CategorieLibro.LIBRI_SCOLASTICI.toString();  
		break;
	case "LIBRI_UNIVERSITARI" : 
		this.categoria = CategorieLibro.LIBRI_UNIVERSITARI.toString();  
		break;			
	case "RICETTARI_GENERALI" : 
		this.categoria = CategorieLibro.RICETTARI_GENERALI.toString();  
		break;	
	case "LINGUISTICA_SCRITTURA" : 
		this.categoria = CategorieLibro.LINGUISTICA_SCRITTURA.toString();  
		break;
	case "POLITICA" : 
		this.categoria = CategorieLibro.POLITICA.toString();  
		break;
	case "RELIGIONE" : 
		this.categoria = CategorieLibro.RELIGIONE.toString();  
		break;
	case "ROMANZI_ROSA" : 
		this.categoria = CategorieLibro.ROMANZI_ROSA.toString();  
		break;		
	case "SCIENZE" : 
		this.categoria = CategorieLibro.SCIENZE.toString();  
		break;		
	case "TECNOLOGIA_MEDICINA" : 
		this.categoria = CategorieLibro.TECNOLOGIA_MEDICINA.toString();  
		break;
	
	default :
		this.categoria = null;
		break;
	}
	
}






public ObservableList<Raccolta> getListaLibri() {
	return listaLibri;
}






public void setListaLibri(ObservableList<Raccolta> listaLibri) {
	this.listaLibri = listaLibri;
}








}
