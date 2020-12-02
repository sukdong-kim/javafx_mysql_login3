package application;

public class Pizza {
	int id;
	String name,phone,address,pizza_type,pizza_size;
	int add_1,add_2,add_3;
	String date;
	public Pizza(int id, String name, String phone, String address, String pizza_type, String pizza_size, int add_1,
			int add_2, int add_3, String date) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.pizza_type = pizza_type;
		this.pizza_size = pizza_size;
		this.add_1 = add_1;
		this.add_2 = add_2;
		this.add_3 = add_3;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPizza_type() {
		return pizza_type;
	}
	public void setPizza_type(String pizza_type) {
		this.pizza_type = pizza_type;
	}
	public String getPizza_size() {
		return pizza_size;
	}
	public void setPizza_size(String pizza_size) {
		this.pizza_size = pizza_size;
	}
	public int getAdd_1() {
		return add_1;
	}
	public void setAdd_1(int add_1) {
		this.add_1 = add_1;
	}
	public int getAdd_2() {
		return add_2;
	}
	public void setAdd_2(int add_2) {
		this.add_2 = add_2;
	}
	public int getAdd_3() {
		return add_3;
	}
	public void setAdd_3(int add_3) {
		this.add_3 = add_3;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
