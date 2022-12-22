package web.bean;

import java.util.List;

import laptop.model.raccolta.Raccolta;

public class ModificaOggettoBean {
	private List<Raccolta> miaLista;
	private String tipologiaL;
	private String tipologiaG;
	private String tipologiaR;
	

	public List<Raccolta> getMiaLista() {
		return miaLista;
	}

	public void setMiaLista(List<Raccolta> miaLista) {
		this.miaLista = miaLista;
	}

	public String getTipologiaL() {
		return tipologiaL;
	}

	public void setTipologiaL(String tipologiaL) {
		this.tipologiaL = tipologiaL;
	}

	public String getTipologiaG() {
		return tipologiaG;
	}

	public void setTipologiaG(String tipologiaG) {
		this.tipologiaG = tipologiaG;
	}

	public String getTipologiaR() {
		return tipologiaR;
	}

	public void setTipologiaR(String tipologiaR) {
		this.tipologiaR = tipologiaR;
	}

	

}
