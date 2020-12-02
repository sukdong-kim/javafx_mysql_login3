package application;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.Initializable;

public class MenuController {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    static int cnt=0;
    
    String p_type,p_size;
    int p1,p2,p3;
    String currentdate;
	
    @FXML    private ComboBox<String> comboBox, comboBox2;
    @FXML    private CheckBox ch1;
    @FXML    private CheckBox ch2;
    @FXML    private CheckBox ch3;
    @FXML    private Button b1;
    @FXML    private Button b2;
    @FXML    private Button b3;
    @FXML    private TextArea ta; 
    @FXML    private Label lab;
    
    @FXML    private Label lbl_name;
    @FXML    private Label lbl_phone;
    @FXML    private Label lbl_address;
    @FXML    private Label lbl_date;
    
	ObservableList<String> comboBoxList = 
			FXCollections.observableArrayList("페페로니 피자: 12,000원","포테이토 피자: 15,000원","불고기 피자: 18,000원","콤보 피자: 20,000원");
	ObservableList<String> comboBoxList2 = 
			FXCollections.observableArrayList("small size: 0원","medium size: 1,500원","large size: 3,000원");
	ObservableList<Pizza> list;
	
	@FXML
	private TableView<Pizza> tableContent;
	
    @FXML
    private TableColumn<Pizza, String> col_date;

    @FXML
    private TableColumn<Pizza, String> col_pizza;

    @FXML
    private TableColumn<Pizza, String> col_size;

    @FXML
    private TableColumn<Pizza, Integer> col_1;

    @FXML
    private TableColumn<Pizza, Integer> col_2;

    @FXML
    private TableColumn<Pizza, Integer> col_3;
	
    String sel1 = "";
    String sel2 = "";
    String sel3 = "";
    
    int num1=0;
    int num2=0;
    int num3=0;
    
	public void initialize() {	
		comboBox.setValue("피자선택");
		comboBox.setItems(comboBoxList);	
		comboBox2.setItems(comboBoxList2);
		
    	conn = mysqlconnect.ConnectDb();

    	DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    	currentdate = format.format(Calendar.getInstance().getTime());
		list = FXCollections.observableArrayList();
    	petch();
    	try {
			test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    void test() throws SQLException {
    	pst = conn.prepareStatement("select * from pizza_history");
		rs = pst.executeQuery();
		while(rs.next()) {
			cnt++;
		}
		System.out.println("db count = "+ cnt);
    }
	public void petch() {
    	try {
    		String sql = "select * from pizza_history where name =? and phone=?";
     //   	String sql = "select * from pizza_history where name =?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, "kim");
			pst.setString(2, "123");

			rs = pst.executeQuery();
			while(rs.next()) {		
				int r1 = rs.getInt("id");
				String r2 = rs.getString("name");
				String r3 = rs.getString("phone");
				String r4 = rs.getString("address");
				String r5 = rs.getString("pizza_type");
				String r6 = rs.getString("pizza_size");
				int r7 = rs.getInt("add_1");
				int r8 = rs.getInt("add_2");
				int r9 = rs.getInt("add_3");
				String r10 = rs.getString("date");
				
				lbl_name.setText(r2);
				lbl_phone.setText(r3);
				lbl_address.setText(r4);
				lbl_date.setText(currentdate);
				
				Pizza pizza = new Pizza(r1,r2,r3,r4,r5,r6,r7,r8,r9,r10);	

				list.add(pizza);

				col_date.setCellValueFactory(new PropertyValueFactory<Pizza,String>("date"));
				col_pizza.setCellValueFactory(new PropertyValueFactory<Pizza,String>("pizza_type"));
				col_size.setCellValueFactory(new PropertyValueFactory<Pizza,String>("pizza_size"));
				col_1.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_1"));										
				col_2.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_2"));
				col_3.setCellValueFactory(new PropertyValueFactory<Pizza,Integer>("add_3"));
				
				tableContent.setItems(list);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    @FXML
    void onClickbombo(ActionEvent event) {
    	String sel = comboBox.getValue() ;
    	p_type = sel;
    	int a = comboBox.getSelectionModel().getSelectedIndex(); 
       	ta.appendText(sel+"를 선택 \n");
   	
       	if(a==0)
    		num1 += 12000;
    	else 	if(a==1)
    		num1 += 15000;
    	else 	if(a==2)
    		num1 += 18000;
    	else 	if(a==3)
    		num1 += 20000;
       	
    	sel1+=sel +"\n";
    }
    
    @FXML
    void onClickCombo2(ActionEvent event) {
    	String sel = comboBox2.getValue() ;
    	p_size = sel;
       	ta.appendText(sel+"를 선택 \n");
       	
       	if(sel.equals("small size: 0원"))
    		num3 += 0;
    	else if(sel.equals("medium size: 1,500원"))
    		num3 += 1500;
    	else if(sel.equals("large size: 3,000원"))
    		num3 += 3000;
       	
    	sel3+=sel +"\n";
    }
    
    @FXML
    void onClickb1(ActionEvent event) {
    	Integer number = num1+num2+num3;
        NumberFormat numberFormatter;
        numberFormatter = NumberFormat.getNumberInstance();
        String quantityOut = numberFormatter.format(number);
    	 	
    	ta.setText("주문 내역");
    	ta.appendText("\n\n"+ sel1 + sel3 +sel2+"\n");
    	ta.appendText("\n total = "+ quantityOut +"원 입니다.\n");
    	lab.setText(quantityOut);
    	p1=0;
    	p2=0;
    	p3=0;
    	
    	if(ch1.isSelected())
    		p1=1;
    	if(ch2.isSelected())
    		p2=1;
    	if(ch3.isSelected())
    		p3=1;

    	String sql = "insert into pizza_history (id,name,phone,address,pizza_type,pizza_size,add_1,add_2,add_3,date)"
    			+ " values (?,?,?,?,?,?,?,?,?,?)";
    	try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, ++cnt);
			pst.setString(2, "kim");
			pst.setString(3, "123");
			pst.setString(4, "1234567");
			pst.setString(5, p_type);
			pst.setString(6, p_size);
			pst.setInt(7, p1);
			pst.setInt(8, p2);
			pst.setInt(9, p3);
			pst.setString(10, currentdate);
			
			pst.execute();
			
			JOptionPane.showMessageDialog(null,"Saved");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void onClickb2(ActionEvent event) {

    	if(ch1.isSelected())
    		ch1.fire();
    	if(ch2.isSelected())
    		ch2.fire();
    	if(ch3.isSelected())
    		ch3.fire();
    	
    	sel1="";
    	sel2="";
    	sel3="";
    	
    	num1=0;
    	num2=0;
    	num3=0;
    	
    	ta.setText(""); 
    	comboBox2.setValue("");
    	comboBox.setValue("");
    	
    }
    @FXML
    
    void onClickb3(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void onClickch1(ActionEvent event) {
    	if(ch1.isSelected()) {
    		ta.appendText(ch1.getText()+" 선택 \n");
    		num2+=2000;
        	sel2+=ch1.getText()+"\n";
    	}
    	if(ch1.isSelected()== false) {
    		ta.appendText(ch1.getText()+" 취소 \n");
    		num2-=2000;
        	sel2+=ch1.getText()+" 취소\n";
    	}
    }

    @FXML
    void onClickch2(ActionEvent event) {
    	if(ch2.isSelected()) {
    		ta.appendText(ch2.getText()+" 선택 \n");
    		num2+=1000;
        	sel2+=ch2.getText()+"\n";
    	}
    	if(ch2.isSelected()== false) {
    		ta.appendText(ch2.getText()+" 취소 \n");
    		num2-=1000;
        	sel2+=ch2.getText()+" 취소\n";
    	}
    }

    @FXML
    void onClickch3(ActionEvent event) {
    	if(ch3.isSelected()) {
    		ta.appendText(ch3.getText()+" 선택 \n");
    		num2+=500;
        	sel2+=ch3.getText()+"\n";
    	}
    	if(ch3.isSelected()== false) {
    		ta.appendText(ch3.getText()+" 취소 \n");
    		num2-=500;
        	sel2+=ch3.getText()+" 취소\n";
    	}

    }
	
}
