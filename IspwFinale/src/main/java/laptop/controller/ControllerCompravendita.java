package laptop.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.User;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Raccolta;
import laptop.model.raccolta.Rivista;



public class ControllerCompravendita {
	private LibroDao lD;
	private User u=User.getInstance();
	private GiornaleDao gD;
	private Giornale g;
	private Libro l;
	private Rivista r;
	private RivistaDao rD;
	private boolean status=false;
	private static String libro="libro";
	private static String rivista="rivista";
	private static String giornale="giornale";
	



	public  boolean disponibilitaLibro(String i ) throws SQLException {
	
		
		
			 l.setId(Integer.parseInt(i));
			 status=lD.checkDisp(l);
			 return status;
				
	}
	
	public  boolean disponibilitaGiornale(String i ) throws SQLException {
		
			 
			 g.setId(Integer.parseInt(i));
			 status=gD.checkDisp(g);
			 return status;
				
	}
	
public  boolean disponibilitaRivista(String i ) throws SQLException {
		
	
	
			 r.setId(Integer.parseInt(i));
			 status=rD.checkDisp(r);
			 return status;
				
	}

	public ControllerCompravendita() {
		lD = new LibroDao();
		gD=new GiornaleDao();
		g=new Giornale();
		r=new Rivista();
		l=new Libro();
		rD=new RivistaDao();
	}

	
	public ObservableList<Raccolta> getLibri() throws SQLException {
		return lD.getLibri();
	}
	public ObservableList<Raccolta> getGiornali() throws SQLException {
		return gD.getGiornali();
	}
	public ObservableList<Raccolta> getRiviste() throws SQLException {
		return rD.getRiviste();
	}
	
	
	/*
	 * Metodo udato per tornare tipo utente in base a se � loggato o no

	 */
	public String retTipoUser()
	{
		return u.getIdRuolo();
	}
	//usato nel caso se non � loggato-> "utente"
	public void setTipoUser(String ruolo)
	{
		u.setIdRuolo(ruolo);	
	}

	public String ritornaMessaggio()
	{
		String s=null;
		if(ControllerSystemState.getIstance().getType().equals(libro))
			s="Benvenuto... ecco la lista dei libri nel nostro catalogo...";
		else if(ControllerSystemState.getIstance().getType().equals(giornale))
			s="Benvenuto... ecco la lista dei giornali nel nostro catalogo...";
		else if(ControllerSystemState.getIstance().getType().equals(rivista))
			s="Benvenuto... ecco la lista dele riviste nel nostro catalogo...";
		return s;
	}
	public String popolaBottoneV()
	{
		String s=null;
		if(ControllerSystemState.getIstance().getType().equals(libro))
			s="Mostra Libro";
		else if(ControllerSystemState.getIstance().getType().equals(giornale))
			s="Mostra Giornale";
		else if(ControllerSystemState.getIstance().getType().equals(rivista))
			s="Mostra Rivista";
		return s;
	}
	public String popolaBottoneA()
	{
		String s=null;
		if(ControllerSystemState.getIstance().getType().equals(libro))
			s="Acquista Libro";
		else if(ControllerSystemState.getIstance().getType().equals(giornale))
			s="Acquista Giornale";
		else if(ControllerSystemState.getIstance().getType().equals(rivista))
			s="Acquista Rivista";
		return s;
	}




	
}
