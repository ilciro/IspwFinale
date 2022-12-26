package web.bean;

import java.sql.Date;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import laptop.model.raccolta.Raccolta;

public class GiornaleBean {
	private Exception mex;
	private ObservableList<Raccolta> listaGiornali;
	private String giornale="giornale";
	private String  titolo;
	private String tipologia;
	private String lingua;
	private String editore;
	private LocalDate dataPubb;
	private int copieRimanenti;
	private int disponibilita;
	private int id;
	private Date data;
	private float prezzo;
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
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
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public ObservableList<Raccolta> getListaGiornali() {
		return listaGiornali;
	}
	public void setListaGiornali(ObservableList<Raccolta> listaGiornali) {
		this.listaGiornali = listaGiornali;
	}


}
