package helper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClienteView extends Application {
    private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (BorderPane)FXMLLoader.load(getClass().getResource("/view/ClienteView.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			primaryStage.setTitle("FinSys");
		
			primaryStage.setScene(scene);
			//primaryStage.setMaximized(true);
			primaryStage.show();
			setStage(primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ClienteView.stage = stage;
	}
		
}
