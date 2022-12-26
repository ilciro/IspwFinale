package laptop.model.raccolta;

import java.time.LocalDate;





public class Factory {
	private MatchParam mp;
	private Libro l;
	private Giornale g;
	private Rivista r;
	private String tipologiaO;
	private static final String LIBRO = "libro";
	private static final String GIORNALE = "giornale";
	private static final String RIVISTA = "rivista";
	//usato per passare paramentri
	
	
	// controlli il tipo
	
	
	public String[] createRaccolta1(String titolo,String tipologia,String autore,String lingua,String editore,String categoria,String descrizione)  
	{
		 String[] infoGenerali ;

		
		 infoGenerali=mp.popola1(titolo, tipologia, autore, lingua, editore, categoria, descrizione);

		 return infoGenerali;
		 
	}
	public String[] createRaccolta2(int numPag,String isbn,int nrCopie,int disp,float prezzo,int copieRim)
	{
		 String[] infoCosti;

		 infoCosti= mp.popola2(numPag, isbn, nrCopie, disp, prezzo, copieRim);

		 return infoCosti;
	}
	
	
	public void createRaccoltaFinale1(String tipologiaO,String titolo,String tipologia,String autore,String lingua,String editore,String categoria) 
	{
		if(tipologiaO.equals(LIBRO))
		{
				l.setInfoGenerali(createRaccolta1(titolo, tipologia, autore, lingua, editore, categoria,autore));
		}
		if(tipologiaO.equals(GIORNALE))
		{

				g.setInfoGenerali(createRaccolta1(titolo, tipologia, null, lingua, editore, null,null));

		}
		if(tipologiaO.equals(RIVISTA))
		{
				r.setInfoGenerali(createRaccolta1(titolo, tipologia, autore, lingua, editore, categoria,null));
		}
		
	}
	
	public void createRaccoltaFinale2(String tipologiaO,int numPag,String isbn,int nrCopie,int disp,float prezzo,int copieRim) 
	{
		if(tipologiaO.equals(LIBRO))	
		{
				l.setInfoCostiDisp(createRaccolta2(numPag, isbn,nrCopie,disp,prezzo,copieRim));
				
		}
		if (tipologiaO.equals(GIORNALE))
			{
			g.setDisponibilita(disp);
			g.setPrezzo(prezzo);
			g.setCopieRimanenti(copieRim);
			
			
			}	
		 if (tipologiaO.equals(RIVISTA))
		{
			r.setDisp(disp);
			r.setPrezzo(prezzo);
			r.setCopieRim(copieRim);
		}
	}
	
	
	
	public Raccolta createRaccoltaFinaleCompleta(String tipologiaO,LocalDate dataPubb,String recensione,String descrizione,int id) 
	{
		switch(tipologiaO)
		{
			case LIBRO:
				l.setDataPubb(dataPubb);
				l.setRecensione(recensione);
				l.setDesc(descrizione);
				l.setId(id);
				return  new Libro(l.getInfoGenerali(),l.getDataPubb(),l.getRecensione(),l.getId(),l.getDesc(),l.getInfoCostiDisp());
			case GIORNALE:
				g.setDataPubb(dataPubb);
				g.setId(id);
				return new Giornale(g.getInfoGenerali(),g.getDataPubb(),g.getCopieRimanenti(),g.getDisponibilita(),g.getPrezzo(),g.getId());
			case RIVISTA:
				r.setDataPubb(dataPubb);
				r.setId(id);
				r.setDescrizione(descrizione);
				return new Rivista(r.getInfoGenerali(), r.getDescrizione(), r.getDataPubb(),r.getDisp(),r.getPrezzo() ,r.getCopieRim(),r.getId());
		
			default:
				return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public Raccolta creaLibro(String[] info,LocalDate dataPubb,String recensione,int id,String desc,String []infoCosti)
	{

		return new Libro(info,dataPubb,recensione,id,desc,infoCosti);
	}
	
	public Raccolta creaGiornale(String[] info,LocalDate dataPubb,int nrCopie, int disponibilita, float prezzo, int id)
	{
		return new Giornale(info,dataPubb,nrCopie,disponibilita,prezzo,id);
	}
	public Raccolta creaRivista(String [] info,	String descrizione, LocalDate dataPubb2, int disp, float prezzo, int copieRim,int id)
	{
		return new Rivista(info,descrizione, dataPubb2,disp,prezzo,copieRim,id);
	}
		
	
	public Factory()
	{
		mp=new MatchParam();
		l=new Libro();
		g=new Giornale();
		r=new Rivista();
	}
	public String getTipologiaO() {
		return tipologiaO;
	}
	public void setTipologiaO(String tipologiaO) {
		this.tipologiaO = tipologiaO;
	}
	
}

