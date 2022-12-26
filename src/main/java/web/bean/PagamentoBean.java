package web.bean;



import javafx.collections.ObservableList;
import laptop.model.Pagamento;


public class PagamentoBean  {
	/**
	 * 
	 */
	private int id;
	private String metodo;
	private int esito; //0 ok 1 fallito
	private String nomeUtente;
	private float ammontare;
	private String tipo;
	private int idOggetto;
	private ObservableList<Pagamento> listaPagamenti;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMetodo() {
	return metodo;
}

public void setMetodo(String metodo) {
	this.metodo = metodo;
}

public int getEsito() {
	return esito;
}

public void setEsito(int esito) {
	this.esito = esito;
}

public String getNomeUtente() {
	return nomeUtente;
}

public void setNomeUtente(String nomeUtente) {
	this.nomeUtente = nomeUtente;
}

public float getAmmontare() {
	return ammontare;
}

public void setAmmontare(float ammontare) {
	this.ammontare = ammontare;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public int getIdOggetto() {
	return idOggetto;
}

public void setIdOggetto(int idOggetto) {
	this.idOggetto = idOggetto;
}

public ObservableList<Pagamento> getListaPagamenti() {
	return listaPagamenti;
}

public void setListaPagamenti(ObservableList<Pagamento> listaPagamenti) {
	this.listaPagamenti = listaPagamenti;
}





}
