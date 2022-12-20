package laptop.controller;

import java.sql.SQLException;

import java.util.logging.Level;

import javafx.collections.ObservableList;
import laptop.database.CartaCreditoDao;
import laptop.database.ContrassegnoDao;
import laptop.database.GiornaleDao;
import laptop.database.LibroDao;
import laptop.database.PagamentoDao;
import laptop.database.RivistaDao;
import laptop.model.CartaDiCredito;
import laptop.model.Pagamento;
import laptop.model.raccolta.Giornale;
import laptop.model.raccolta.Libro;
import laptop.model.raccolta.Rivista;

public class ControllerPagamentoCC {
	private CartaCreditoDao cDao;
	private String appoggio = "";
	private CartaDiCredito cc;
	private PagamentoDao pDao;
	private ContrassegnoDao pD;
	private ControllerSystemState vis= ControllerSystemState.getIstance();
	private Libro l;
	private LibroDao lD;
	private GiornaleDao gD;
	private RivistaDao rD;
	private Giornale g;
	private Rivista r;
	private boolean state=false;
	
	
	private int cont=0;

	public boolean controllaPag(String d, String c,String civ) {
		int x;

		 int anno;
		 int mese;
		 int giorno;
		String[] verifica=null;


		appoggio = appoggio + d;
		  anno = Integer.parseInt((String) appoggio.subSequence(0, 4));
		  mese = Integer.parseInt((String) appoggio.subSequence(5, 7));
		  giorno = Integer.parseInt((String) appoggio.subSequence(8, 10));
		
		if (anno > 2020 && (mese >= 1 && mese <= 12) && (giorno >= 1 && giorno <= 31) && c.length() <= 20 && civ.length()==3 ) {
			
				
					 verifica= c.split("-");
					
					for ( x=0; x<verifica.length; x++) {
							if(verifica[x].length()==4)
							{
								cont++;
							}
					}
					if (cont==4)
					{
						state=true;
					}
					
				

		} 
		
		
		return state;

	}

	public ControllerPagamentoCC() {
		try {
		cDao = new CartaCreditoDao();
		cDao.daiPrivilegi();
		pD=new ContrassegnoDao();
		pD.daiPrivilegi();
		pDao=new PagamentoDao();
		lD=new LibroDao();
		l=new Libro();
		gD=new GiornaleDao();
		g=new Giornale();
		rD=new RivistaDao();
		r=new Rivista();
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test Eccezione").log(Level.INFO, "errore in sql",e);

		}
	}

	public void aggiungiCartaDB(String n, String c, String cod, java.sql.Date data, String civ, float prezzo)
			throws SQLException {
		
		
		
			cc = new CartaDiCredito(n, c, cod,  data, civ, prezzo);
						
			cc.setPrezzoTransazine(vis.getSpesaT());
			cDao.insCC(cc);
						
			Pagamento p;
			 p=new Pagamento(0,"cc",0,cc.getNomeUser(),vis.getSpesaT(),null);
				p.setMetodo("cc");
				p.setNomeUtente(cc.getNomeUser());
				String tipo=vis.getType();
				if(tipo.equals("libro"))
				{
					//prenod spesa da vis
					l.setId(vis.getId());
					p.setAmmontare(vis.getSpesaT());
					p.setId(l.getId());
					p.setTipo(lD.retTip(l));
				}
				if(tipo.equals("giornale"))
				{
					//prenod spesa da vis
					g.setId(vis.getId());
					p.setAmmontare(vis.getSpesaT());
					p.setId(g.getId());
					p.setTipo(gD.retTip(g));
					
				}
				if(tipo.equals("rivista"))
				{
					//prenod spesa da vis
					r.setId(vis.getId());
					p.setAmmontare(vis.getSpesaT());
					p.setId(r.getId());
					p.setTipo(rD.retTip(r));
					
				}
								
				pDao.inserisciPagamento(p);
		
		

	}

	public ObservableList<CartaDiCredito> ritornaElencoCC(String nomeU) throws SQLException {
		
		return cDao.getCarteCredito(nomeU);
	}
	
	public CartaDiCredito tornaDalDb(String codiceCarta) throws SQLException
	{
		cc=new CartaDiCredito();
		cc.setNumeroCC(codiceCarta);
		return cDao.popolaDati(cc);
	}

	public void pagamentoCC(String nome) throws SQLException
	{
		Pagamento p;
		p=new Pagamento(0,"cartaCredito", 0,nome,0, null);
			
		//inserire qui
		p.setMetodo("cCredito");
		p.setNomeUtente(nome);
		String tipo=vis.getType();
		if(tipo.equals("libro"))
		{
			//prenod spesa da vis
			l.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(l.getId());
			p.setTipo(lD.retTip(l));
			lD.aggiornaDisponibilita(l);
		}
		if(tipo.equals("giornale"))
		{
			//prenod spesa da vis
			g.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(g.getId());
			p.setTipo(gD.retTip(g));
			gD.aggiornaDisponibilita(g);
		}
		if(tipo.equals("rivista"))
		{
			//prenod spesa da vis
			r.setId(vis.getId());
			p.setAmmontare(vis.getSpesaT());
			p.setId(r.getId());
			p.setTipo(rD.retTip(r));
			rD.aggiornaDisponibilita(r);
			
		}
		
		
		
		//ammontare,acquisto,idProd
		//settare in p
		
		java.util.logging.Logger.getLogger("Pagamento effettuato").log(Level.INFO, "info {0}",p.getAmmontare()+p.getTipo()+p.getId());

		pDao.inserisciPagamento(p);
	}
	
}
