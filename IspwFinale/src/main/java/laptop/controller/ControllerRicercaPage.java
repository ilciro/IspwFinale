package laptop.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Raccolta;


public class ControllerRicercaPage {
	
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	public ControllerRicercaPage()
	{
		lD = new LibroDao();
		gD = new GiornaleDao();
		rD =new RivistaDao();
		ControllerSystemState.getIstance().setIsSearch(true);
		
	}
	
	public ObservableList<Raccolta> cercaPerTipo (String s) throws SQLException
	{
		ObservableList<Raccolta> r = null;
		if(ControllerSystemState.getIstance().getType().equals("libro"))
		{
			//serach in libro dao
			r= lD.getLibriByName(s);
		}
		 if(ControllerSystemState.getIstance().getType().equals("giornale"))
		{
			//search in giornale dao
			r=  gD.getGiornaliByName(s);
		}
		 if(ControllerSystemState.getIstance().getType().equals("rivista"))
		{
			//search in rivista dao
			r= rD.getRivisteByName(s);
		}
		
		return r;
		
	}
	
	public String returnType()
	{
		return ControllerSystemState.getIstance().getType();
	}
	
	
}
