package laptop.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import laptop.controller.ControllerReportRaccolta;
import laptop.controller.ControllerSystemState;

public class BoundaryRaccoltaPage implements Initializable {
	@FXML
	private Pane pane;
	@FXML
	private Label header;
	@FXML
	private Button buttonG;
	@FXML
	private Button rivisteB;
	@FXML
	private Button libriB;
	@FXML
	private Button buttonI;
	private ControllerReportRaccolta cRR;
	private ControllerSystemState vis=ControllerSystemState.getIstance();
	protected Scene scene;	
	private static String gestione="paginaGestione.fxml";
	
	@FXML
	private void giornali() throws IOException
	{
		vis.setTypeAsDaily();
		Stage stage;
		Parent root;
		stage = (Stage) buttonG.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource(gestione));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
	}
	@FXML
	private void riviste() throws IOException
	{
		vis.setTypeAsMagazine();
		Stage stage;
		Parent root;
		stage = (Stage) rivisteB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource(gestione));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
		
	}
	@FXML
	private void libri() throws IOException
	{
		vis.setTypeAsBook();
		Stage stage;
		Parent root;
		stage = (Stage) libriB.getScene().getWindow();
		root = FXMLLoader.load(getClass().getClassLoader().getResource(gestione));
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
		
	}
	@FXML
	private void torna() throws IOException {
		String tipoU=cRR.getTipo();
		
		if( vis.getIsLogged() &&  tipoU.equalsIgnoreCase("A")) {
			Stage stage;
			Parent root;
			stage = (Stage) buttonI.getScene().getWindow();
			root = FXMLLoader.load(getClass().getClassLoader().getResource("raccoltaPage.fxml"));
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
			 if( vis.getIsLogged() && (tipoU.equalsIgnoreCase("W") || tipoU.equalsIgnoreCase("E")) ) {

		
				Stage stage;
				Parent root;
				stage = (Stage) buttonI.getScene().getWindow();
				root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLoginES.fxml"));
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			 else
			 {
				 Stage stage;
					Parent root;
					stage = (Stage) buttonI.getScene().getWindow();
					root = FXMLLoader.load(getClass().getClassLoader().getResource("homePageAfterLogin.fxml"));
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
			 }
			 
			
			
	}

		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		cRR=new ControllerReportRaccolta();
		
	}
}
