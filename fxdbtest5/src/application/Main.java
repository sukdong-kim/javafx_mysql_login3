package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	private static Stage primaryStage;
	public static Stage checkDialogStage;
	public static String m_name,m_phone,m_address;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		showMainView();
	}
	public void showMainView() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Pizza Management");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopMainView() {
		primaryStage.close();
	}
	
	public static void showMenuView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Menu.fxml"));
		AnchorPane checkView = loader.load();

		checkDialogStage = new Stage();
		checkDialogStage.setTitle("Pizza Order");
		checkDialogStage.initModality(Modality.WINDOW_MODAL);
		checkDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		checkDialogStage.setScene(scene);
		checkDialogStage.showAndWait();

	}
	public static void main(String[] args) {
		launch(args);
	}
}
