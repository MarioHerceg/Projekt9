package hr.java.vjezbe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage = new Stage();
	@Override
	public void start(Stage primaryStage) {
		try {
			stage=primaryStage;
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Herceg.fxml"));
			Scene scene = new Scene(root,400,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void setMainPage(BorderPane root) {
		 Scene scene = new Scene(root,400,500);
		 stage.setScene(scene);
		 stage.show();
		}


	public static void main(String[] args) {
		launch(args);
		
	}
}
