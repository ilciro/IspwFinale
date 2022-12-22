package laptop.controller;

// this is singelton battona 
// know evreything about the system

public class ControllerSystemState {
	
	 private int id;
	 private String type;
	 private boolean isLogged ;
	 private boolean isSearch;
	 private boolean isPickup;
	 private static ControllerSystemState instance=new ControllerSystemState() ;
	 private float spesaT;// usato per avere importo totale 
	 private int quantita; //usato per avere quantita oggetto che compro
	 private String metodoP; //usato per vedere se contanti o cc
	 
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

	private ControllerSystemState()
	 {
		 
	 }
	 
	 public static ControllerSystemState getIstance()
	 {
		 if (instance == null) 
		 {
			 return new ControllerSystemState();
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

	
	
}
