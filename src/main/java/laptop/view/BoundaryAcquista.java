package laptop.view;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import laptop.controller.ControllerAcquista;
import laptop.controller.ControllerSystemState;

public class BoundaryAcquista implements Initializable {
	@FXML
	private SplitPane split;
	@FXML
	private AnchorPane ap1;
	@FXML
	private AnchorPane ap2;
	@FXML
	private Label header;
	@FXML
	private Label labelN;
	@FXML
	private Label labelCosto;
	@FXML
	private Label labelQ;
	@FXML
	private Label labelT;
	@FXML
	private Label  nome;
	@FXML
	private Label  dispLabel;
	@FXML
	private Label costo;
	@FXML
	private TextField quantita;
	@FXML
	private CheckBox ritiroN;

	@FXML
	private Label totale;
	@FXML
	private Label labelPag;
	@FXML
	private RadioButton buttonCC;
	@FXML
	private RadioButton buttonCash;
	@FXML
	private Button calcola;
	@FXML
	private Button link;

	protected Scene scene;
	private ControllerAcquista cA;
	private ControllerSystemState vis = ControllerSystemState.getIstance() ;

	
	
	@FXML

	private void pagaCC() throws IOException {
		
		if(ritiroN.isSelected()) {
			vis.setPickup(true);
		}
		else if (!ritiroN.isSelected())
		{
			vis.setPickup(false);
		}
		if(Integer.parseInt(quantita.getText())>Integer.parseInt(dispLabel.getText()))
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonCC.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("acquista.fxml"));
			stage.setTitle("Benvenuto nella schermata di acquisto");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			java.util.logging.Logger.getLogger("Test pagacc").log(Level.SEVERE,"\n Non vi e sufficiente disponibilita");
		}
		else {
		Stage stage;
		Parent root;
		stage = (Stage) buttonCC.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("pagamentoCC.fxml"));
		stage.setTitle("Benvenuto nella schermata dell'acquisto con carta credito");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}


	}

	@FXML
	private void pagaCash() throws IOException {
		if(ritiroN.isSelected()) {
			vis.setPickup(true);
		}
		else if (!ritiroN.isSelected())
		{
			vis.setPickup(false);
		}
		if(Integer.parseInt(quantita.getText())>Integer.parseInt(dispLabel.getText()))
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonCC.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("acquista.fxml"));
			stage.setTitle("Benvenuto nella schermata di acquisto");
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			java.util.logging.Logger.getLogger("Test pagacc").log(Level.SEVERE,"\n Non vi e sufficiente disponibilita");

		}
		else {

		Stage stage;
		Parent root;
		stage = (Stage) buttonCash.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("pagamentoContrassegno.fxml"));
		stage.setTitle("Benvenuto nella schermata dell'acquisto in contanti");

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}

	}

	

	@FXML
	private void importo() throws IOException, SQLException, NumberFormatException {
		
		if (!nome.getText().equals("")) {
			buttonCC.setDisable(false);
			buttonCash.setDisable(false);

			String scelta = cA.getType();
			
			// qui mettere un controllo dal db oer il tipo di prodotto scelto usando l'istanza visualizza
			if (scelta.equals("libro")) {
				
				
				float x = cA.totale(nome.getText(), Integer.parseInt(dispLabel.getText()),Integer.parseInt(quantita.getText()));
				costo.setText(String.valueOf(x));
				float tot;
				tot = x * (Float.parseFloat(quantita.getText()));
				totale.setText(String.valueOf( tot));
				cA.inserisciAmmontareL(Integer.parseInt(quantita.getText()));
				vis.setSpesaT(tot);
				vis.setQuantita(Integer.parseInt(quantita.getText()));
				
				
				

			} else if (scelta.equals("giornale")) {
				float y = cA.totaleG(nome.getText(), Integer.parseInt(dispLabel.getText()),Integer.parseInt(quantita.getText()));
				costo.setText(String.valueOf(y));
				float tot1;
				tot1 = y * (Float.parseFloat(quantita.getText()));
				totale.setText(String.valueOf(tot1));
				cA.inserisciAmmontareG(Integer.parseInt(quantita.getText()));
				vis.setSpesaT(tot1);
				vis.setQuantita(Integer.parseInt(quantita.getText()));


			} else if (scelta.equals("rivista")) {
				float z = cA.totaleR(nome.getText(),Integer.parseInt(dispLabel.getText()), Integer.parseInt(quantita.getText()));
				costo.setText(String.valueOf(z));
				float tot2;
				tot2 = z * (Float.parseFloat(quantita.getText()));
				totale.setText(String.valueOf(tot2));
				cA.inserisciAmmontareR(Integer.parseInt(quantita.getText()));
				vis.setSpesaT(tot2);
				vis.setQuantita(Integer.parseInt(quantita.getText()));



				

			} else {
				throw new IOException();
			}

		}
		

	}

	public BoundaryAcquista() throws SQLException {
		cA = new ControllerAcquista();
	}

	@FXML
	private void indietro() throws IOException {
		if( vis.getIsLogged()) {
		Stage stage;
		Parent root;
		stage = (Stage) link.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLogin.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else
		{
			Stage stage;
			Parent root;
			stage = (Stage) link.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)   {
		
		buttonCC.setDisable(true);
		buttonCash.setDisable(true);
		
			try {
			nome.setText(cA.getNomeById());
			dispLabel.setText(String.valueOf(cA.getDisp()));
		
				costo.setText(String.valueOf(cA.getCosto()));
			} catch (SQLException  e) {
				java.util.logging.Logger.getLogger("Test initialize").log(Level.SEVERE," eccezione ottenuta {0}.",e);

				} 
		
		
		

	}

}
