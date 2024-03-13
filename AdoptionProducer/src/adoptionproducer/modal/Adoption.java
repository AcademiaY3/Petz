package adoptionproducer.modal;

public class Adoption {
	private String serial;
	private String name;
	private String animal;
	private String age;
	private String disease;
	private String adopter;
	private String created;
	
	
	
	public Adoption() {
		super();
	}
	public Adoption(String serial, String name, String animal, String age, String disease, String adopter,
			String created) {
		super();
		this.serial = serial;
		this.name = name;
		this.animal = animal;
		this.age = age;
		this.disease = disease;
		this.adopter = adopter;
		this.created = created;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getAdopter() {
		return adopter;
	}
	public void setAdopter(String adopter) {
		this.adopter = adopter;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	
	

}
