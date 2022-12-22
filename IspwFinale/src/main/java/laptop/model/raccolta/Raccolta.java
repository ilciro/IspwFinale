package laptop.model.raccolta;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface Raccolta {

	void scarica() throws DocumentException, IOException;//stampo messsaggio libro scaricato 
	void leggi(int i) throws IOException, DocumentException;
	

}
