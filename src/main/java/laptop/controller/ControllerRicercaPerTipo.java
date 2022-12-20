package laptop.controller;

import java.util.logging.Level;




public class ControllerRicercaPerTipo {

	private ControllerSystemState vis=ControllerSystemState.getIstance();
	private boolean state=false;
	
	public ControllerRicercaPerTipo() 
	{
		java.util.logging.Logger.getLogger("Test Costruttore").log(Level.INFO,"Costruttore ControllerRicercaPerTipo");

	}
	
	public boolean setRicercaL()
	{
		
		if (vis.getType().equals("libro"))
			state=true;
		return state;
	}
	public boolean setRicercaG()
	{
		ControllerSystemState.getIstance().setTypeAsDaily();
		if (ControllerSystemState.getIstance().getType().equals("giornale"))
			state= true;
		return state;
	}
	public boolean setRicercaR()
	{
		ControllerSystemState.getIstance().setTypeAsMagazine();
		if (ControllerSystemState.getIstance().getType().equals("rivista"))
			state= true;
		return state;
	}
}
