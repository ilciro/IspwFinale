package laptop.view;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import laptop.utilities.CreateDefaultDB;

public class Main  extends Application {
    public static final String ANSI_GREEN = "\u001B[32m";

	
	@Override
	public void start(Stage primaryStage) {
		Scene scene;

		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("homePage.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("Benvenuto nella homePage");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch (Exception e)
		{
			java.util.logging.Logger.getLogger("main page").log(Level.SEVERE,"\n eccezione ottenuta .",e);

			
		}

	}

	public static void main(String[] args)  {
		//uso status per vedere se trigger creati
		 
		

		
		try {
			
			CreateDefaultDB.createDefaultDB();
			

		} catch (FileNotFoundException  |SQLException  eFile) {
			java.util.logging.Logger.getLogger("crwa db").log(Level.SEVERE,"\n eccezione ottenuta .",eFile);

		}
			
		
	
		

		launch(args);
		
		
		
	}
			
}
