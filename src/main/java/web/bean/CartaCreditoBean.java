package web.bean;

import java.util.Date;
import java.util.List;

import laptop.model.CartaDiCredito;

public class CartaCreditoBean {
	private String nome;
	private String cognome;
	private String numeroCC;
	private Date dataScad;
	private String civ;
	private List<CartaDiCredito> listaCarteCredito;
	private float prezzoTransazione;
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
	public String getNumeroCC() {
		return numeroCC;
	}
	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}
	public Date getDataScad() {
		return dataScad;
	}
	public void setDataScad(Date dataScad) {
		this.dataScad = dataScad;
	}
	public String getCiv() {
		return civ;
	}
	public void setCiv(String civ) {
		this.civ = civ;
	}
	public List<CartaDiCredito> getListaCarteCredito() {
		return listaCarteCredito;
	}
	public void setListaCarteCredito(List<CartaDiCredito> listaCarteCredito) {
		this.listaCarteCredito = listaCarteCredito;
	}
	public float getPrezzoTransazione() {
		return prezzoTransazione;
	}
	public void setPrezzoTransazione(float prezzoTransazione) {
		this.prezzoTransazione = prezzoTransazione;
	}
	
	

}
