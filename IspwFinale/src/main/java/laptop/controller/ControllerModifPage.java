package laptop.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.RivistaDao;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;

public class ControllerModifPage {
	private LibroDao ld;
	private Libro l;
	private Giornale g;
	private GiornaleDao gD;
	private Rivista r;
	private RivistaDao rD;
	
	
	public ObservableList<Libro> getLibriById(int id) throws SQLException {
		l.setId(id);
		return ld.getLibriSingoloById(l);
	}
	
	public ObservableList<Giornale> getGiornaliById(int id) throws SQLException   {
		g.setId(id);
		return gD.getGiornaliSingoloById(g);
	}
	
		
		public void checkDataG(String[] info, LocalDate d, int dispo, float prezzo,
				int copie) throws SQLException  {
			

			g.setTitolo(info[0]);
			g.setTipologia(info[1]);
			g.setEditore(info[2]);
			g.setLingua(info[3]);
			g.setDataPubb(d);
			g.setDisponibilita(dispo);
			g.setPrezzo(prezzo);
			g.setCopieRimanenti(copie);
			
						gD.aggiornaGiornale(g);
			
		}
		public ObservableList<Rivista> getRivistaById(int id) throws SQLException {
			r.setId(id);
			return rD.getRivistaSingoloById(r);
		}
		
		

		public void checkDataR(String [] info, LocalDate d,
				int dispo, float prezzo, int copie, int id, String desc) throws SQLException {
			
			r.setTitolo(info[0]);
			r.setTipologia(info[1]);
			r.setAutore(info[2]);
			r.setLingua(info[3]);
			r.setEditore(info[4]);
			r.setDescrizione(desc);
			r.setDataPubb(d);
			r.setDisp(dispo);
			r.setPrezzo(prezzo);
			r.setCopieRim(copie);
			r.setId(id);
			
			rD.aggiornaRivista(r);
			
			
		}
		
	
	
	public ControllerModifPage()
	{
		ld=new LibroDao();
		l=new Libro();
		g=new Giornale();
		gD=new GiornaleDao();
		r=new Rivista();
		rD=new RivistaDao();
	}
	
	
	public void checkDataL(String []info,String recensione,String descrizione,LocalDate data,String[] infoCosti) throws NullPointerException, SQLException 
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
		
		
		ld.aggiornaLibro(l);
	}
	
	

}
