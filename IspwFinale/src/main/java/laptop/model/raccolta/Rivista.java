package laptop.model.raccolta;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class Rivista implements Raccolta  {
	
	private String titolo;
	private String tipologia;
	private String autore;
	private String lingua;
	private String editore;
	private String descrizione;
	private LocalDate dataPubb;
	private int disp;
	private float prezzo;
	private int copieRim;
	private int id;
	private String url="C:\\libriScaricati";
	private String [] infoGenerali=new String[5];

	public Rivista(String [] info,String descrizione, LocalDate dataPubb2, int disp, float prezzo, int copieRim,int id) {
		this.setInfoGenerali(info);
		this.descrizione = descrizione;
		this.dataPubb = dataPubb2;
		this.disp = disp;
		this.prezzo = prezzo;
		this.copieRim = copieRim;
		this.id = id;
		this.titolo=info[0];
		this.tipologia=info[1];
		this.editore=info[4];
		this.lingua=info[3];
		this.autore=info[2];
	}
	public Rivista() {
		super();
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	public String getTipologia() {
		return this.tipologia;
	}
	public String getAutore() {
		return this.autore;
	}
	public String getLingua() {
		return this.lingua;
	}
	public String getEditore() {
		return this.editore;
	}
	public String getDescrizione() {
		return this.descrizione;
	}
	public LocalDate getDataPubb() {
		return this.dataPubb;
	}
	public int getDisp() {
		return this.disp;
	}
	public float getPrezzo() {
		return this.prezzo;
	}
	public int getCopieRim() {
		return this.copieRim;
	}
	public int getId() {
		return this.id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setTipologia(String tipologia) {
		switch (tipologia){
		
		case "SETTIMANALE": 
			this.tipologia = CategorieRivista.SETTIMANALE.toString();  
			break;

		case "BISETTIMANALE": 
			this.tipologia = CategorieRivista.BISETTIMANALE.toString();  
			break;

		case "MENSILE" : 
			this.tipologia = CategorieRivista.MENSILE.toString();  
			break;

		case "BIMESTRALE" : 
			this.tipologia = CategorieRivista.BIMESTRALE.toString();  
			break;

		case "TRIMESTRALE" : 
			this.tipologia = CategorieRivista.TRIMESTRALE.toString();  
			break;

		case "ANNUALE" : 
			this.tipologia = CategorieRivista.ANNUALE.toString();  
			break;

		case "ESTIVO" : 
			this.tipologia = CategorieRivista.ESTIVO.toString();  
			break;

		case "INVERNALE" : 
			this.tipologia = CategorieRivista.INVERNALE.toString();  
			break;

		case "SPORTIVO" : 
			this.tipologia = CategorieRivista.SPORTIVO.toString();  
			break;

		case "CINEMATOGRAFICA" : 
			this.tipologia = CategorieRivista.CINEMATOGRAFICA.toString();  
			break;

		case "GOSSIP" : 
			this.tipologia = CategorieRivista.GOSSIP.toString();  
			break;

		case "TELEVISIVO" : 
			this.tipologia = CategorieRivista.TELEVISIVO.toString();  
			break;

		case "MILITARE" : 
			this.tipologia = CategorieRivista.MILITARE.toString();  
			break;

		case "INFORMATICA" : 
			this.tipologia = CategorieRivista.INFORMATICA.toString();  
			break;

		default :
			this.tipologia = null;
			break;
		}
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setDataPubb(LocalDate dataPubb) {
		this.dataPubb = dataPubb;
	}
	public void setDisp(int disp) {
		this.disp = disp;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public void setCopieRim(int copieRim) {
		this.copieRim = copieRim;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void scarica() throws IOException {
		File file=null;
		File dirToOpen=null;
		 file = new File(url);
		 Desktop desktop=null;
	      file.mkdir();
	      
	      
		  desktop = Desktop.getDesktop();
	        
	        
	            dirToOpen = new File(url);
	            
					desktop.open(dirToOpen);
				
	        
		
	}
	@Override
	public void leggi(int i) throws FileNotFoundException, DocumentException {
		Document document=null;
	    //definiamo il nome del nostro file di prova
	    // Creiamo un Document
	     document = new Document();
	    // otteniamo una istanza di PdfWriter passando il document ed uno stream file
	   	PdfWriter.getInstance(document, new FileOutputStream("C:\\libriScaricati\\Rivista.pdf"));
			
			 
				
			
	        document.open();
	        // aggiungiamo un paragrafo
	        
				document.add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In sed nisi non mi vulputate vestibulum. In hac habitasse platea dictumst. Aenean pellentesque est eget tortor blandit pulvinar. Donec in finibus ante. Phasellus molestie pretium magna, non cursus tortor. Ut malesuada consequat lectus, et laoreet dui eleifend vel. In sit amet luctus quam. Sed laoreet tellus at imperdiet pulvinar. Vestibulum ut erat et nunc aliquet interdum.\r\n"
						+ "\n"
						+ "In commodo nisl velit, a egestas nunc consectetur vitae. Sed vulputate eros eget massa blandit ornare. Donec semper bibendum lacus, at pharetra enim pharetra sed. Mauris tempus tellus nec nulla molestie, faucibus semper nibh consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec iaculis purus nisi, vel volutpat sapien vulputate a. Aenean blandit non nibh in finibus. Integer ac tempus dui, laoreet gravida ex. Integer vitae orci vel nulla commodo cursus quis et nisi. Nulla sit amet nibh sed justo dapibus vulputate ut et nisi. Mauris efficitur commodo iaculis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus blandit urna eros, sed cursus mauris blandit ut. Lorem ipsum dolor sit amet, consectetur adipiscing elit.\r\n"
						+ "\n"
						+ "Vestibulum eros tellus, rhoncus vel fringilla nec, vehicula in velit. Nam malesuada eget tellus ut viverra. Aenean iaculis gravida urna, eu convallis leo dapibus vel. Nullam et suscipit diam. Sed tincidunt ipsum sed placerat consequat. Vestibulum lacinia lacinia commodo. Vestibulum lacus erat, pellentesque sed rhoncus id, tincidunt eget ante. Phasellus vulputate urna sit amet dolor rhoncus bibendum eu feugiat nisl. Proin ipsum libero, consectetur sit amet sagittis nec, feugiat nec quam. In dictum massa quis ligula semper, eget dignissim turpis blandit.\r\n"
						+ "\n"
						+ "Sed congue laoreet diam vel iaculis. Maecenas tempor convallis dolor nec laoreet. Quisque vitae dui vitae erat tempor volutpat aliquam ac ipsum. Nulla nulla erat, iaculis eu vulputate in, fermentum sed est. Ut ac enim ac felis molestie fringilla at a lectus. Donec porttitor a elit eget tempus. Morbi molestie libero lorem, et fringilla mi pharetra scelerisque. Fusce ut nunc sit amet odio dapibus tristique. Phasellus ultrices, nisl sit amet faucibus ultrices, odio nisl dictum enim, at sollicitudin arcu metus a risus. Duis suscipit, mi sed mollis euismod, erat justo pellentesque orci, vitae finibus nunc est vitae felis. Ut venenatis commodo eros sed fermentum. Fusce feugiat pellentesque justo. Ut finibus, lacus quis ornare consectetur, sapien urna placerat mauris, non ultricies justo nunc sed ante. Phasellus sodales dui a ex gravida, a tempor mi eleifend.\r\n"
						+ "\n"
						+ "Aenean pharetra tortor semper, laoreet dui sed, porta lacus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque interdum purus cursus venenatis mollis. Donec venenatis bibendum ullamcorper. Phasellus porttitor, mauris eget placerat imperdiet, tellus purus aliquet mauris, eget laoreet quam nibh eget nisi. Nam volutpat urna vitae eros porttitor efficitur. Etiam mi velit, vulputate sed lacinia rutrum, viverra sed nulla. Sed sem mi, tempus ut lacus faucibus, congue dignissim dolor. Praesent sed quam feugiat, condimentum eros non, luctus dui."));
				        // chiudiamo il documento
	        document.close();
		
		
	}
	public String [] getInfoGenerali() {
		return infoGenerali;
	}
	public void setInfoGenerali(String [] infoGenerali) {
		this.infoGenerali = infoGenerali;
	}
 
	
}
