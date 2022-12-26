package web.bean;

public class SystemBean {

		
		 private int id;
		 private String type;
		 private boolean isLogged ;
		 private boolean isSearch;
		 private boolean isPickup;
		 private static SystemBean instance=new SystemBean() ;
		 private float spesaT;// usato per avere importo totale 
		 private int quantita; //usato per avere quantita oggetto che compro
		 private String metodoP; //usato per vedere se contanti o cc
		 private boolean negozioSelezionato;// per vedere se download o negozio
		 private String titolo;
		 
		 public int getQuantita() {
			return quantita;
		}

		public void setQuantita(int quantita) {
			this.quantita = quantita;
		}

		public float getSpesaT() {
			return spesaT;
		}

		public void setSpesaT(float spesaT) {
			this.spesaT = spesaT;
		}

		private SystemBean()
		 {
			 
		 }
		 
		 public static SystemBean getIstance()
		 {
			 if (instance == null) 
			 {
				 return new SystemBean();
			 }
		 return instance;
		 }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		public void setTypeAsBook()
		{
			this.type = "libro";
		}
		public void setTypeAsMagazine()
		{
			this.type = "rivista";
		}
		public void setTypeAsDaily()
		{
			this.type = "giornale";
		}
		public String getType()
		{
			return type;
		}

		public boolean getIsLogged() {
			return isLogged;
		}

		public void setIsLogged(boolean isLogged) {
			this.isLogged = isLogged;
		}
		
		public boolean getIsSearch() {
			return isSearch;
		}

		public void setIsSearch(boolean isSearch) {
			this.isSearch = isSearch;
		}

		public boolean getIsPickup() {
			return isPickup;
		}

		public void setPickup(boolean isPickup) {
			this.isPickup = isPickup;
		}

		public String getMetodoP() {
			return metodoP;
		}

		public void setMetodoP(String metodoP) {
			this.metodoP = metodoP;
		}

		public boolean isNegozioSelezionato() {
			return negozioSelezionato;
		}

		public void setNegozioSelezionato(boolean negozioSelezionato) {
			this.negozioSelezionato = negozioSelezionato;
		}

		public String getTitolo() {
			return titolo;
		}

		public void setTitolo(String titolo) {
			this.titolo = titolo;
		}

		
		
	

}
