package appointmentproducer;

public class Appointment {
	
	private int appointment_no;
	private  String appointment_name;
	private  String doctor_name;
	private String owner_name;
	private  int charge;
	private String description;
	
	
	public int getAppointment_no() {
		return appointment_no;
	}
	public void setAppointment_no(int appointment_no) {
		this.appointment_no = appointment_no;
	}
	public String getAppointment_name() {
		return appointment_name;
	}
	public void setAppointment_name(String appointment_name) {
		this.appointment_name = appointment_name;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	


}
