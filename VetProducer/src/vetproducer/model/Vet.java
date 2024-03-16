package vetproducer.model;

public class Vet {
	private String serial;
	private String fname;
	private String lname;
	private String gender;
	private String telephone;
	private String address;
	private String created;
	
	
	public Vet() {
	}
	public Vet(String serial,String fname, String lname, String gender, String telephone, String address,String created) {
		super();
		this.fname = fname;
		this.serial = serial;
		this.lname = lname;
		this.gender = gender;
		this.telephone = telephone;
		this.address = address;
		this.created = created;
	}
	
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
