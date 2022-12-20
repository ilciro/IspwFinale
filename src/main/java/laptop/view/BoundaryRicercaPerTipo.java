package laptop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import laptop.controller.ControllerRicercaPerTipo;
import laptop.controller.ControllerSystemState;

public class BoundaryRicercaPerTipo implements Initializable {
	
	@FXML
	private Pane pane;
	@FXML
	private Label labelC;
	@FXML
	private Button buttonL;
	@FXML
	private Button buttonG;
	@FXML
	private Button buttonR;
	@FXML
	private Button buttonB;
	
	private String title = "Benvenuto nella schermata del riepilogo ordine";
	private String pageFxml = "ricercaPage.fxml";
	private ControllerRicercaPerTipo cRPT;
	protected Scene scene;
		
	
	@FXML
	private void torna() throws IOException
	{
		
		if(ControllerSystemState.getIstance().getIsLogged()) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonB.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLogin.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
			else
			{
				Stage stage;
				Parent root;
				stage = (Stage) buttonB.getScene().getWindow();
				root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
	}
	@FXML
	private void cercaL() throws IOException
	{
		ControllerSystemState.getIstance().setTypeAsBook();

		if(cRPT.setRicercaL())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonL.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource(pageFxml));
			stage.setTitle(title);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			
			java.util.logging.Logger.getLogger("report libro").log(Level.WARNING,"\n not found");


		}
	}
	@FXML
	private void cercaG() throws IOException
	{
		if(cRPT.setRicercaG())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonG.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource(pageFxml));
			stage.setTitle(title);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			
			java.util.logging.Logger.getLogger("report libro").log(Level.WARNING,"\n not found ");


		}
	}
	@FXML 
	private void cercaR() throws IOException
	{
		if(cRPT.setRicercaR())
		{
			Stage stage;
			Parent root;
			stage = (Stage) buttonR.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource(pageFxml));
			stage.setTitle(title);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			
			java.util.logging.Logger.getLogger("report libro").log(Level.WARNING,"\n not found");


		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		cRPT = new  ControllerRicercaPerTipo();
	
	}
}
