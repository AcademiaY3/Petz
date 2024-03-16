package newsletterproducer.Modal;

public class NewsLetter {
	private String email;
	private String number;
	private String content;
	private String sentDate;
	
	public NewsLetter() {
		super();
	}
	public NewsLetter(String email, String number, String content, String sentDate) {
		super();
		this.email = email;
		this.number = number;
		this.content = content;
		this.sentDate = sentDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSentDate() {
		return sentDate;
	}
	public void setSentDate(String sentDate) {
		this.sentDate = sentDate;
	}
	
	
}
