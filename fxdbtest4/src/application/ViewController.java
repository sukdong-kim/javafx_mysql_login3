package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ViewController {
    @FXML
    private Button btn_home;
    Main main = new Main();;
    @FXML
    void onClickhome(ActionEvent event) {
		btn_home.getScene().getWindow().hide();
		main.showMainView();
    }

}
