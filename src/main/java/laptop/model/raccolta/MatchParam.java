package laptop.model.raccolta;



public class MatchParam {
	
	public String[] popola1(String titolo,String tipologia,String autore,String lingua,String editore,String categoria,String descrizione) 
	{
		
		String[] appoggio=new String[7];

		
			appoggio[0]=titolo;
			appoggio[1]=tipologia;
			appoggio[2]=autore;
			appoggio[3]=lingua;
			appoggio[4]=editore;
			appoggio[5]=categoria;
			appoggio[6]=descrizione;
			
		
		return appoggio;		
		
	}
	
	public String[] popola2(int numPag,String isbn,int nrCopie,int disp,float prezzo,int copieRim)
	{
		

		String[] appoggio1=new String[6];
		
		 appoggio1[0]=String.valueOf(numPag);
		 appoggio1[1]=isbn;
		 appoggio1[2]=String.valueOf(nrCopie);
		 appoggio1[3]=String.valueOf(disp);
		 appoggio1[4]=String.valueOf(prezzo);
		 appoggio1[5]=String.valueOf(copieRim);
		 


		return appoggio1;
		
	}

	
	
	

}
