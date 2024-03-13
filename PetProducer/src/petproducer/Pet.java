package petproducer;

public class Pet {

	private String owner_name;
	private  String owner_phone_no;
	private  String pet_name;
	private  int age;
	private String breed;
	private  String gender;

	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	
	public String getOwner_phone_no() {
		return owner_phone_no;
	}
	public void setOwner_phone_no(String owner_phone_no) {
		this.owner_phone_no = owner_phone_no;
	}
	
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
