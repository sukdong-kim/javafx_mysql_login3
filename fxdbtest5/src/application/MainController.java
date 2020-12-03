package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
	int cnt=0;
	Main main = new Main();

    @FXML    private AnchorPane pane_login;
    @FXML    private TextField txt_username;  
    @FXML    private TextField txt_phone;
    @FXML    private TextField txt_address;
    @FXML    private TextField txt_phone_up;
    @FXML    private TextField txt_address_up;
    @FXML    private Button btn_login;
    @FXML    private AnchorPane pane_signup;
    @FXML    private TextField txt_username_up;
    @FXML    private ComboBox<String> type_up;
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
	ResultSet srs;
	Statement stmt=null;
    
    
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
    	String sql = "select * from pizza_history where name =? and phone=?";
    	
    	try {
			pst = conn.prepareStatement(sql);
			String r1,r2,r3;
			r1 = txt_username.getText();
			r2 = txt_phone.getText();
			pst.setString(1, r1);
			pst.setString(2, r2);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				txt_address.setText(rs.getString("address"));
				main.m_name = r1;
				main.m_phone = r2;
				main.m_address = rs.getString("address");
				
				main.stopMainView();
				main.showMenuView();
												
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
    	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	String currentdate = format.format(Calendar.getInstance().getTime());
    	String sql = "insert into pizza_history (id,name,phone,address,date,pizza_type) values (?,?,?,?,?,?)";
    	try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, ++cnt);
			pst.setString(2, txt_username_up.getText());
			pst.setString(3, txt_phone_up.getText());
			pst.setString(4, txt_address_up.getText());
			pst.setString(5, currentdate);
			pst.setString(6, "½Å±Ô°í°´");
			
			pst.execute();
			JOptionPane.showMessageDialog(null,"Saved");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
     
	@FXML
	private void initialize() {
		conn = mysqlconnect.ConnectDb();
		type_up.getItems().addAll("Client","Manage","Host","HeadQuarter");
	//	type.getItems().addAll("Client","Manage","Host","HeadQuarter");
	
		// db°¹¼ö °è»ê
    	try {
    		stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
    		srs = stmt.executeQuery("select * from pizza_history");
    		srs.last();  		
    		cnt = srs.getInt("id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
