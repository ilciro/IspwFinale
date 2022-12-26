package web.bean;

import java.sql.Date;

import java.time.LocalDate;

import javafx.collections.ObservableList;
import laptop.model.raccolta.Raccolta;


public class RivistaBean {
	private Exception mex;
	private ObservableList<Raccolta> listaRiviste;
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

	
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;

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
	public ObservableList<Raccolta> getListaRiviste() {
		return listaRiviste;
	}
	public void setListaRiviste(ObservableList<Raccolta> listaRiviste) {
		this.listaRiviste = listaRiviste;
	}
}
