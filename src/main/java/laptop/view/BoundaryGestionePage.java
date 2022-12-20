package laptop.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import laptop.controller.ControllerGestionePage;
import laptop.controller.ControllerSystemState;
import laptop.model.raccolta.Raccolta;

public class BoundaryGestionePage implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private TableView<Raccolta>table=new TableView<>();
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> titolo = new TableColumn<>("Titolo");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> tipologia = new TableColumn<>("Tipologia");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> editore = new TableColumn<>("Editore");
	@FXML
	private TableColumn<Raccolta, SimpleStringProperty> autore = new TableColumn<>("Autore");
	@FXML
	private TableColumn<Raccolta, SimpleFloatProperty> prezzo = new TableColumn<>("Prezzo");
	@FXML
	private TableColumn<Raccolta, SimpleIntegerProperty> id = new TableColumn<>("ID ProdottoTitolo");
	@FXML
	private Button buttonG;
	@FXML
	private Button buttonAdd;
	@FXML
	private Button modB;
	@FXML
	private Button buttonDel;
	@FXML
	private Button buttonB;
	@FXML
	private TextField idL;
	private ControllerGestionePage cGP;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	protected Scene scene;
	protected int identity;
	
	
	
	
	@FXML
	private void genera() throws SQLException  
	{
		if(vis.getType().equals("libro"))
		{
			table.setItems(cGP.getLibroS());
		}
		else if(vis.getType().equals("giornale"))
		{
			table.setItems(cGP.getGiornaleS());
		}
		else if(vis.getType().equals("rivista"))
		{
			table.setItems(cGP.getRivistaS());
		}
		
		
	}
	@FXML
	private void aggiungi() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonAdd.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("aggiungiOggettoPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	@FXML
	private void modifica() throws IOException {
		vis.setId(Integer.parseInt(idL.getText()));
		Stage stage;
		Parent root;
		stage = (Stage) modB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("modificaOggettoPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	@FXML
	private void cancella() throws  SQLException
	{
		
		
		cGP.cancella(Integer.parseInt(idL.getText()));
		
	}
	@FXML
	private void indietro() throws IOException
	{
		Stage stage;
		Parent root;
		stage = (Stage) buttonB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("adminPage.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		cGP=new ControllerGestionePage();
		header.setText(cGP.settaHeader());
		
		titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
		if(vis.getType().equals("libro") || vis.getType().equals("rivista"))
			tipologia.setCellValueFactory(null);
		else 
			tipologia.setCellValueFactory(new PropertyValueFactory<>("tipologia"));
		if(vis.getType().equals("giornale"))
			autore.setCellValueFactory(null);
		else 
			autore.setCellValueFactory(new PropertyValueFactory<>("autore"));			
		
		editore.setCellValueFactory(new PropertyValueFactory<>("editore"));
		prezzo.setCellValueFactory(new PropertyValueFactory<>("prezzo"));
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
	
		
		
	}

}
