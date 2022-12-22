package web.bean;

public class NegozioBean {
	private String nome;
	private String indirizzo;
	private boolean apertura;
	private boolean disponibile;
	private String messaggio;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public boolean isApertura() {
		return apertura;
	}
	public void setApertura(boolean apertura) {
		this.apertura = apertura;
	}
	public boolean isDisponibile() {
		return disponibile;
	}
	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	

}
