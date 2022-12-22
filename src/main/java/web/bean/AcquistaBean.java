package web.bean;

import java.io.Serializable;

public class AcquistaBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titolo;
	private String tipo;
	private float prezzo;
	private Exception mex;
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
	}

}
