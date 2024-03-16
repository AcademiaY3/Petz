package adoptionproducer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adoptionproducer.modal.Adoption;

public class AdoptionDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private Date sysdate = new Date();
	private String serial = "";
	
	public AdoptionDao(Connection con) {
		this.con = con;
	}
	
	public int createAdoption(Adoption adoption) {
		int result = 0;
		try {
			int random = (int) (Math.random()*(30000-10000+1)+10000);
			serial = "adt"+random;
			
			query = "Insert into adoption (serial,name,animal,adopter,age,disease) values(?,?,?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			pst.setString(2, adoption.getName());
			pst.setString(3, adoption.getAnimal());
			pst.setString(4, adoption.getAdopter());
			pst.setString(5, adoption.getAge());
			pst.setString(6, adoption.getDisease());
			result=pst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Adoption> getAllAdoption(){
		List<Adoption> adoptions = new ArrayList<Adoption>();
		try {
			query = "select * from adoption";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Adoption adoption = new Adoption();
				adoption.setSerial(rs.getString("serial"));
				adoption.setName(rs.getString("name"));
				adoption.setAnimal(rs.getString("animal"));
				adoption.setAdopter(rs.getString("adopter"));
				adoption.setAge(rs.getString("age"));
				adoption.setDisease(rs.getString("disease"));
				adoption.setCreated(rs.getString("created"));

				adoptions.add(adoption);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adoptions;
	}
	public int deleteAdoption(String serial) {
		int result =0;
		try {
			query = "delete from adoption where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			result =pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateAdoption(Adoption adoption,String serial) {
		int result = 0;
		try {
			Timestamp currentTime = new Timestamp(sysdate.getTime());
			query = "update adoption set name=?,animal=?,adopter=?,age=?,disease=?,updated=? where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, adoption.getName());
			pst.setString(2, adoption.getAnimal());
			pst.setString(3, adoption.getAdopter());
			pst.setString(4, adoption.getAge());
			pst.setString(5, adoption.getDisease());
			pst.setTimestamp(6, currentTime);
			pst.setString(7, serial);

			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public Adoption getAdoptionByID(String serial) {
		Adoption adoption = null;
		try {
			query = "select * from adoption where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			rs = pst.executeQuery();
			if (rs.next()) {
				adoption = new Adoption();
				adoption.setSerial(rs.getString("serial"));
				adoption.setName(rs.getString("name"));
				adoption.setAnimal(rs.getString("animal"));
				adoption.setAdopter(rs.getString("adopter"));
				adoption.setAge(rs.getString("age"));
				adoption.setDisease(rs.getString("disease"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adoption;
	}
}
