package web.bean;



public class FatturaBean  {
	/**
	 * 
	 */
	private String nome;
	private String cognome;
	private String indirizzo;
	private String comunicazioni;
	private Exception mex;
	

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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getComunicazioni() {
		return comunicazioni;
	}
	public void setComunicazioni(String comunicazioni) {
		this.comunicazioni = comunicazioni;
	}
	public Exception getMex() {
		return mex;
	}
	public void setMex(Exception mex) {
		this.mex = mex;
	}
	
	

}
