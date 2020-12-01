package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		showMainView();
	}
	
	public void showMainView() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopMainView() {
		primaryStage.close();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
