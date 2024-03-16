package newsletterproducer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import newsletterproducer.Modal.NewsLetter;

public class NewLetterDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	private String lettercode = "";
	
	public NewLetterDao(Connection con) {
		this.con = con;
	}
	public int createEmailNewsLetter(NewsLetter newsLetter) {
		int result = 0;
		try {
			int random = (int) (Math.random()*(30000-10000+1)+10000);
			lettercode = "adt"+random;
			
			query = "Insert into newsletter (lettercode,email,number,content) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, lettercode);
			pst.setString(2, newsLetter.getEmail());
			pst.setString(3, newsLetter.getNumber());
			pst.setString(4, newsLetter.getContent());
			result=pst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int createSmsNewsLetter(NewsLetter newsLetter) {
		int result = 0;
		try {
			int random = (int) (Math.random()*(30000-10000+1)+10000);
			lettercode = "adt"+random;
			
			query = "Insert into newsletter (lettercode,email,number,content) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, lettercode);
			pst.setString(2, newsLetter.getEmail());
			pst.setString(3, newsLetter.getNumber());
			pst.setString(4, newsLetter.getContent());
			result=pst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
