package laptop.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import laptop.controller.ControllerCompravendita;
import laptop.controller.ControllerSystemState;
import laptop.exception.IdException;
import laptop.model.raccolta.Raccolta;

public class BoundaryCompravendita implements Initializable {
	@FXML
	private Pane panel;
	@FXML
	private Label header;
	@FXML
	private TableView<Raccolta> table;
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> categoria = new TableColumn<>("Categoria");
	@FXML
	private TableColumn<Raccolta, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> idLibro = new TableColumn<>("Id Libro");

	@FXML
	private Button buttonL;
	@FXML
	private TextField entryText;
	@FXML
	private Button buttonV;
	@FXML
	private Button buttonA;
	@FXML
	private Button buttonI;

	private ControllerCompravendita cCV;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;
	protected Scene scene;
	private static String titoloS="titolo";
	private static String editoreS="editore";
	private static String prezzoS="prezzo";



	@FXML
	private void verifica() throws  IOException, SQLException {
		
			String i = entryText.getText();
			if(i==null || i.equals(""))
			{
				try {
					throw new IdException("id null or empty");
					
				}catch(IdException idE)
				{
					java.util.logging.Logger.getLogger("Test pagacc").log(Level.SEVERE,"\n eccezione ottenuta {0}.",idE.toString());

				}
			}		
			
			
			
			
			if( cCV.disponibilitaLibro(i) || cCV.disponibilitaGiornale(i) || cCV.disponibilitaRivista(i))
			{
				vis.setId(Integer.parseInt(i));
				
			}
			
			
			Stage stage;
			Parent root=null;
			stage = (Stage) buttonV.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("visualizzaPage.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			
				

			
		
		

	}

	@FXML
	private void procedi() throws IOException, SQLException {
		String i = entryText.getText();
		if(i==null || i.equals(""))
		{
			try {
				throw new IdException("id null or empty");
				
			}catch(IdException idE)
			{
				java.util.logging.Logger.getLogger("Test pagacc").log(Level.SEVERE,"\n eccezione ottenuta {0}",idE.toString());
			}
		}	
		if( cCV.disponibilitaLibro(i) || cCV.disponibilitaGiornale(i) || cCV.disponibilitaRivista(i))
		{
			vis.setId(Integer.parseInt(i));
			
		}
			Stage stage;
			Parent root;
			stage = (Stage) buttonA.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("acquista.fxml"));
			stage.setTitle("Benvenuto nella schermata del riepilogo ordine");

			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			


	}

	@FXML
	private void vediLista() throws SQLException {
		//vedere if anche qui
		if(ControllerSystemState.getIstance().getType().equals("libro"))
			table.setItems(cCV.getLibri());
		else if(ControllerSystemState.getIstance().getType().equals("giornale"))
			table.setItems(cCV.getGiornali());
		else if(ControllerSystemState.getIstance().getType().equals("rivista"))
			table.setItems(cCV.getRiviste());

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cCV = new ControllerCompravendita();
		
		buttonV.setText(cCV.popolaBottoneV());
		buttonA.setText(cCV.popolaBottoneA());
		
		if(ControllerSystemState.getIstance().getType().equals("libro"))
		{
			header.setText(cCV.ritornaMessaggio());
			titolo.setCellValueFactory(new PropertyValueFactory<>(titoloS));
			editore.setCellValueFactory(new PropertyValueFactory<>(editoreS));
			autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
			categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
			prezzo.setCellValueFactory(new PropertyValueFactory<>(prezzoS));
			idLibro.setCellValueFactory(new PropertyValueFactory<>("id"));
			
		}
		else if(ControllerSystemState.getIstance().getType().equals("giornale"))
		{
			header.setText(cCV.ritornaMessaggio());
			titolo.setCellValueFactory(new PropertyValueFactory<>(titoloS));
			editore.setCellValueFactory(new PropertyValueFactory<>(editoreS));
			autore.setCellValueFactory(null);
			categoria.setCellValueFactory(null);
			prezzo.setCellValueFactory(new PropertyValueFactory<>(prezzoS));
			idLibro.setCellValueFactory(new PropertyValueFactory<>("id"));
		}
		else if(ControllerSystemState.getIstance().getType().equals("rivista"))
		{
			header.setText(cCV.ritornaMessaggio());
			titolo.setCellValueFactory(new PropertyValueFactory<>(titoloS));
			editore.setCellValueFactory(new PropertyValueFactory<>(editoreS));
			autore.setCellValueFactory(new PropertyValueFactory<>("autore"));
			categoria.setCellValueFactory(null);
			prezzo.setCellValueFactory(new PropertyValueFactory<>(prezzoS));
			idLibro.setCellValueFactory(new PropertyValueFactory<>("id"));
		}
		
		//stampare sttringa opportuuna


	}

	@FXML
	private void torna() throws IOException {
		
		/*
		 * Si vede utente "generico " loggato 
		 * se non � loggato si inposta come tipo "Utente"
		 * qui viene usato utente "UTENtE" come quello che non puo fare nulla (utente base)
		 * per restituire tipo vedere controllerCompravenditaLibri
		 * 
		 * FATTO QUESTO per aggirare il problema del nullPointer EXcoeption di cCV.retTipoUser
		 * 
		 */
		
		if(!vis.getIsLogged())
			cCV.setTipoUser("UTENTE");
		
		String tipoU=cCV.retTipoUser();
		
		if( vis.getIsLogged() &&  tipoU.equalsIgnoreCase("ADMIN")) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLogin.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else if( vis.getIsLogged() && (tipoU.equalsIgnoreCase("SCRITTORE") || tipoU.equalsIgnoreCase("EDITORE")) ) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLoginES.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}	
		else if((tipoU.equalsIgnoreCase("UTENTE") ) )
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}


	}
}

