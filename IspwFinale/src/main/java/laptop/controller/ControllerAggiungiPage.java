package laptop.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;



public class ControllerAggiungiPage {
	private LibroDao lD;
	private Libro l;
	private GiornaleDao gD;
	private boolean status = false;
	private Rivista r;
	private RivistaDao rD;
	
	//funzione di aggiunta dei libri
	//e verifica dei dati inseriti 
	
	public boolean checkData(String[] info,String recensione,String descrizione,LocalDate data,String[] infoCosti) throws SQLException
	{

		
		if( infoCosti[1].length()>10 || data==null )
		{
			return status;

		}
		else
		{
		l.setTitolo(info[0]);
		l.setNumeroPagine(Integer.parseInt(infoCosti[0]));
		l.setCodIsbn(infoCosti[1]);
		l.setEditore(info[4]);
		l.setAutore(info[2]);
		l.setLingua(info[3]);
		l.setCategoria(info[5]);
		l.setDataPubb(data);
		l.setRecensione(recensione);
		l.setDesc(descrizione);
		l.setDisponibilita(Integer.parseInt(infoCosti[3]));
		l.setPrezzo(Float.parseFloat(infoCosti[4]));
		l.setNrCopie(Integer.parseInt(infoCosti[5]));
		
		
		
		status =lD.creaLibrio(l);
		
		return status;
		}
	}
	
	public boolean checkDataG(Giornale g) throws SQLException 
	{
		if(g.getDataPubb()==null )
		{
			return status;
		}
		else
		{
			status = gD.creaGiornale(g);

		}			
		return status;

	}
	public boolean checkDataR(String [] info,  LocalDate data,
			int dispo, float prezzo, int copie, String desc) throws  SQLException {
		
		

		
		if(data!=null )
		{
			
		r.setTitolo(info[0]);
		r.setTipologia(info[1]);
		r.setAutore(info[2]);
		r.setLingua("italiano");
		r.setEditore(info[4]);
		r.setDescrizione(desc);
		r.setDataPubb(data);
		r.setDisp(dispo);
		r.setPrezzo(prezzo);
		r.setCopieRim(copie);

		rD.creaRivista(r);
		
		status = true ;
		}
		
		return status;
	}

	// qui chiamo la funzione del dao
	
	public ControllerAggiungiPage()
	{
		lD=new LibroDao();
		l=new Libro();
		gD=new GiornaleDao();
		r=new Rivista();
		rD=new RivistaDao();
	}

}
