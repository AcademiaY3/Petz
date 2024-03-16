package prescriptionproducer;


public class Prescription {
	
	private String medicine_name;
	private  int dose;
	private  int quentity;
	private String issue_by;
	
	public String getMedicine_name() {
		return medicine_name;
	}
	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	public int getQuentity() {
		return quentity;
	}
	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	public String getIssue_by() {
		return issue_by;
	}
	public void setIssue_by(String issue_by) {
		this.issue_by = issue_by;
	}

}

