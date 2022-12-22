package laptop.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import laptop.database.NegozioDao;
import laptop.model.Negozio;

public class ControllerScegliNegozio {
	
	private NegozioDao nD;
	public Negozio getN() {
		return n;
	}

	public void setN(Negozio n) {
		this.n = n;
	}

	private Negozio n;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;

	
	public ControllerScegliNegozio()
	{
		nD = new NegozioDao();
		n = new Negozio(); 
	}
	
	public ObservableList<Negozio> getNegozi() throws SQLException
	{
		ObservableList<Negozio>listOfNegozi;
		listOfNegozi = nD.getNegozi();
		return listOfNegozi;
	}
	
	public boolean isLogged()
	{
		return vis.getIsLogged();
	}
}