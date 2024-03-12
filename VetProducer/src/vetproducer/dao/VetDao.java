package vetproducer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vetproducer.model.Vet;

public class VetDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private Date sysdate = new Date();
	private String serial = "";
	
	public VetDao(Connection con) {
		this.con = con;
	}
	
	public int createVet(Vet vet) {
		int result = 0;
		try {
			int random = (int) (Math.random()*(30000-10000+1)+10000);
			serial = "vt"+random;
			
			query = "Insert into vet (serial,fname,lname,telephone,gender,address) values(?,?,?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			pst.setString(2, vet.getFname());
			pst.setString(3, vet.getLname());
			pst.setString(4, vet.getTelephone());
			pst.setString(5, vet.getGender());
			pst.setString(6, vet.getAddress());
			result=pst.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Vet> getAllVet(){
		List<Vet> vets = new ArrayList<Vet>();
		try {
			query = "select * from vet";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vet vet = new Vet();
				vet.setSerial(rs.getString("serial"));
				vet.setTelephone(rs.getString("telephone"));
				vet.setAddress(rs.getString("address"));
				vet.setFname(rs.getString("fname"));
				vet.setLname(rs.getString("lname"));
				vet.setGender(rs.getString("gender"));

				vets.add(vet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vets;
	}
	public int deleteVet(String serial) {
		int result =0;
		try {
			query = "delete from vet where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			result =pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateVet(Vet vet,String serial) {
		int result = 0;
		try {
			Timestamp currentTime = new Timestamp(sysdate.getTime());
			query = "update vet set telephone=?,fname=?,lname=?,address=?,gender=?,updated=? where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, vet.getTelephone());
			pst.setString(2, vet.getFname());
			pst.setString(3, vet.getLname());
			pst.setString(4, vet.getAddress());
			pst.setString(5, vet.getAddress());
			pst.setTimestamp(6, currentTime);
			pst.setString(7, serial);

			result=pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public Vet getVetByID(String serial) {
		Vet vet = null;
		try {
			query = "select * from vet where serial=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, serial);
			rs = pst.executeQuery();
			if (rs.next()) {
				vet = new Vet();
				vet.setSerial(rs.getString("serial"));
				vet.setTelephone(rs.getString("telephone"));
				vet.setAddress(rs.getString("address"));
				vet.setFname(rs.getString("fname"));
				vet.setLname(rs.getString("lname"));
				vet.setGender(rs.getString("gender"));
				vet.setCreated(rs.getString("created"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vet;
	}
}
