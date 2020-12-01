package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SampleController {

    @FXML
    private AnchorPane pane_login;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button btn_login;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField txt_username_up;

    @FXML
    private TextField txt_password_up;

    @FXML
    private TextField email_up;

    @FXML
    private ComboBox<String> type_up;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    
    public void loginPaneShow() {
    	pane_login.setVisible(true);
    	pane_signup.setVisible(false);
    }

    public void signupPaneShow() {
    	pane_login.setVisible(false);
    	pane_signup.setVisible(true);    	
    }
    
    @FXML
    void onClickLogin(ActionEvent event) {
    	loginPaneShow();
    }

    @FXML
    void onClicksignup(ActionEvent event) {
    	signupPaneShow();
    }
    
    @FXML
    private void Login(ActionEvent event) throws Exception{
    	conn = mysqlconnect.ConnectDb();
    	String sql = "select * from student where id =? and name=?";
    	// combobox 
    	// String sql = "select * from student where id =? and name=? and type =?";
    	
    	try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, txt_username.getText());
			pst.setString(2, txt_password.getText());
//			pst.setString(3, type.getValue().toString());
			rs = pst.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null,"Username and Password is correct !!!");
				
				btn_login.getScene().getWindow().hide();
				Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
				Stage mainStage = new Stage();
				Scene scene = new Scene(root);
				mainStage.setScene(scene);
				mainStage.show();
				
				
			} else {
				JOptionPane.showMessageDialog(null,"Invalid Username or Password");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void add_users(ActionEvent event) {
    	conn = mysqlconnect.ConnectDb();
    	String sql = "insert into student (id,name,email,phone) values (?,?,?,?)";
    	try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, txt_username_up.getText());
			pst.setString(2, txt_password_up.getText());
			pst.setString(3, email_up.getText());
			pst.setString(4, "1234567");
			
			pst.execute();
			JOptionPane.showMessageDialog(null,"Saved");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@FXML
	private void initialize() {
		type_up.getItems().addAll("Admin","Professor","Student","Assistant");
		type.getItems().addAll("Admin","Professor","Student","Assistant");
		
	}
}
