package laptop.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import laptop.controller.ControllerReportPage;

public class BoundaryReportPage implements Initializable {
	
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private Button totaleB;
	@FXML
	private Button libroB;
	@FXML
	private Button raccoltaB;
	@FXML
	private Button giornaliB;
	@FXML
	private Button buttonI;
	@FXML
	private Button rivisteB;
	@FXML
	private TextArea ta;
	
	private ControllerReportPage cRP;

	protected String fileLibro = "ReportFinale\\riepilogoLibro.txt";
	protected String fileGiornale ="ReportFinale\\riepilogoGiornali.txt";	
	protected String fileRiviste = "ReportFinale\\riepilogoRiviste.txt";
	protected String fileUtenti = "ReportFinale\\riepilogoUtenti.txt";
	private static String eccezione="eccezione ottenuta :.";

	protected Scene scene;
	
	
	@FXML
	private void totale() throws SQLException,NullPointerException, IOException
	{
		String line="";
		ta.clear();
		
			cRP.generaReportLibri();
			cRP.generaReportGiornali();
			cRP.generaReportRiviste();			
			cRP.getUtenti();
				
		
		try (BufferedReader readerL = new BufferedReader(new FileReader(fileLibro)))
		{
			
			
			while( (line = readerL.readLine()) != null)
			{
				ta.appendText(line.concat("\n"));
            

				
			}
		} 
		catch(IOException | NullPointerException e)
		{
			java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,eccezione,e);

		}
		
		try (BufferedReader readerG = new BufferedReader(new FileReader(fileGiornale)))
		{
			
			
			while( (line = readerG.readLine()) != null)
			{
				ta.appendText(line.concat("\n"));
            

			}
		} 
		catch(IOException | NullPointerException e)
		{
			java.util.logging.Logger.getLogger("report giornale").log(Level.SEVERE,eccezione,e);

		}
		
		try (BufferedReader readerR = new BufferedReader(new FileReader(fileRiviste)))
		{
			
			
			while( (line = readerR.readLine()) != null)
			{
				ta.appendText(line.concat("\n"));
            

			}
		} 
		catch(IOException | NullPointerException e)
		{
			java.util.logging.Logger.getLogger("report rivista").log(Level.SEVERE,eccezione,e);

		}
		try (BufferedReader readerU = new BufferedReader(new FileReader(fileUtenti)))
		{
			
			
			while( (line = readerU.readLine()) != null)
			{
				ta.appendText(line.concat("\n"));
            

				
			}
		} 
		catch(IOException | NullPointerException e)
		{
			java.util.logging.Logger.getLogger("report utenti").log(Level.SEVERE,eccezione,e);

		}
		


		
		
		

	}
	@FXML
	private void reportLibri() throws IOException, SQLException 
	{
		ta.clear();
		String line="";

		  
		
			cRP.generaReportLibri();
		
		
		try(BufferedReader reader = new BufferedReader(new FileReader(fileLibro)))
		{
			while((line=reader.readLine())!=null)
			{
	            ta.appendText(line.concat("\n"));

			}
		}
        
    }
		

	
	@FXML
	private void raccolta() throws IOException, SQLException
	{
		String line="";
		String line1="";
		String line2="";
		ta.clear();
		cRP.generaReportLibri();
		cRP.generaReportGiornali();
	
		cRP.generaReportRiviste();
		
		
		try(BufferedReader readerL = new BufferedReader(new FileReader(fileLibro))) {
			while((line=readerL.readLine())!=null)
			{
	            ta.appendText(line.concat("\n"));

			}
			
		}
		catch(IOException e)
		{
			
			java.util.logging.Logger.getLogger("report libro").log(Level.SEVERE,"\n eccezione ottenuta .",e);

		}
		
        

		try(BufferedReader readerG = new BufferedReader(new FileReader(fileGiornale)))
		{
			while((line1=readerG.readLine())!=null)
			{
	            ta.appendText(line1.concat("\n"));

			}
		}
        
		try(BufferedReader readerR = new BufferedReader(new FileReader(fileRiviste)))
		{
			while((line2=readerR.readLine())!=null)
			{
	            ta.appendText(line2.concat("\n"));

			}
		}
        
		
	}
	@FXML
	private void reportGiornali() throws IOException, SQLException
	{
		String line="";
		ta.clear();

		
		cRP.generaReportGiornali();
		

		 try(BufferedReader reader = new BufferedReader(new FileReader(fileGiornale)))
		 {
			 while((line=reader.readLine())!=null)
			 {
		            ta.appendText(line.concat("\n"));

			 }
		}catch(IOException e)
		{
			java.util.logging.Logger.getLogger("report giornale").log(Level.SEVERE,"\n eccezione ottenuta .",e);

		}
		
	}
	@FXML
	private void indietro() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonI.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("adminPage.fxml"));
		stage.setTitle("Benvenuto nella schermata del riepilogo dei libri");
		scene = new Scene(root);
		stage.setScene(scene);

		stage.show();

	}
	@FXML
	private void reportRiviste() throws IOException, SQLException
	{
		String line2="";
		ta.clear();

		
			
				cRP.generaReportRiviste();
			
		
			
		try(BufferedReader readerR = new BufferedReader(new FileReader(fileRiviste)))
		{
			while((line2=readerR.readLine())!=null)
			{
	            ta.appendText(line2.concat("\n"));

			}
		}
		}
        
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cRP=new ControllerReportPage();
		
	}

}
