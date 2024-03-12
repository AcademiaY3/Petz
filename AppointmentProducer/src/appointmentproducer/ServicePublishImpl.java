package appointmentproducer;

import dbconnection.serviceimpl.DbCon;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServicePublishImpl implements ServicePublisher {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultSet;
	private  DbCon databaseConnection;
	
	Scanner scn = new Scanner(System.in);
	
	public ServicePublishImpl() {
		
		databaseConnection=(DbCon) new DbCon();
		connection= databaseConnection.getConnection();
		
	}

	@Override
	public void addAppointment() {
		
		
		System.out.println(ANSI_RED+"\n\n\n      -------------Add Appointment to Database-----------\n"+ANSI_RESET);
		
		Appointment appointment= new Appointment();
		
		System.out.print("enter appointment_no    - ");
		appointment.setAppointment_no(scn.nextInt());
		System.out.print("\n");
		
		System.out.print("enter appointment_name  - ");
		appointment.setAppointment_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter doctor_name - ");
		appointment.setDoctor_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter owner_name - ");
		appointment.setOwner_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter charge - ");
		appointment.setCharge(scn.nextInt());
		System.out.print("\n");
		
		
		System.out.print("enter description - ");
		appointment.setDescription(scn.next());
		System.out.print("\n");
		
		
		
		
		   String insertAppointmentSql = "INSERT INTO appointment (appointment_no, appointment_name, doctor_name, owner_name, charge, description ) VALUES (?, ?, ?, ?, ?, ?)";

           try (PreparedStatement preparedStatement = connection.prepareStatement(insertAppointmentSql)) {
               preparedStatement.setInt(1, appointment.getAppointment_no());
               preparedStatement.setString(2, appointment.getAppointment_name());
               preparedStatement.setString(3, appointment.getDoctor_name());
               preparedStatement.setString(4, appointment.getOwner_name());
               preparedStatement.setInt(5, appointment.getCharge());
               preparedStatement.setString(6, appointment.getDescription());
               
               
               int result = preparedStatement.executeUpdate();
               if (result > 0) {
            	   
            	   System.out.println(ANSI_GREEN +"New Prescription Added to the Database"+ANSI_RESET);
            	   System.out.println(ANSI_BLUE+"Appointment_no =  "+appointment.getAppointment_no()+"   Appointment_name =  "+appointment.getAppointment_name()+"   Doctor_name = "+appointment.getDoctor_name()+"   Owner_name = "+appointment.getOwner_name()+"   Charge = "+appointment.getCharge()+"   Description = "+appointment.getDescription()+ANSI_RESET);

                    
               }
           } catch (SQLException e) {
               System.out.println("Failed to add the Appointment_no.");
              
           } 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllAppointments() {
		
		System.out.println(ANSI_RED+"\n\n\n      -------------Get All Appointments-----------\n"+ANSI_RESET);

		ArrayList<Appointment> appointmentList=new ArrayList<Appointment>();
		try {

			statement=connection.createStatement();
			String query = "SELECT * FROM  appointment ";
			resultSet = statement.executeQuery(query);

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	
		
		try {
		      while (resultSet.next()) {
		    	  Appointment  appointment  = new  Appointment ();
		    	  
		    	  appointment.setAppointment_no(resultSet.getInt("appointment_no"));
		    	  appointment.setAppointment_name(resultSet.getString("appointment_name"));
		    	  appointment.setDoctor_name(resultSet.getString("doctor_name"));
		    	  appointment.setOwner_name(resultSet.getString("owner_name"));
		    	  appointment.setCharge(resultSet.getInt("charge"));
		    	  appointment.setDescription(resultSet.getString("description")); 
		    	  
		    	  appointmentList.add(appointment);
			    	
				} }catch (SQLException e) {
					
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-20s | %-16s | %-16s | %-16d | %-16s |%n";

		System.out.println("Appointment List\n");

		System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");
		System.out.format("|   Appointment no    |   Appointment name    |   Doctor name   |   Owner name    |   Charge         |   Description    |%n");
		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		for (Appointment appointmentModel : appointmentList) {
		    System.out.format(leftAlignFormat, appointmentModel.getAppointment_no(), appointmentModel.getAppointment_name(), appointmentModel.getDoctor_name(), appointmentModel.getOwner_name(), appointmentModel.getCharge(), appointmentModel.getDescription());
		}

		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		
		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAppointmentByID() {
		
System.out.println(ANSI_RED+"\n\n\n      -------------Get Appointment By ID-----------\n"+ANSI_RESET);
		
		int appointmentId;
		
		System.out.print("Enter Appointment ID : ");
		appointmentId = scn.nextInt();
 
		String query = "SELECT * FROM appointment WHERE id = '"+ appointmentId +"'";
		
		
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		      while (resultSet.next()) { 
		    	  System.out.println("\n");
		    	  System.out.println("Details of Appointment ID- " + appointmentId);
		    	  
		  		System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");
				System.out.format("|   Appointment no    |   Appointment name    |   Doctor name   |   Owner name    |   Charge         |   Description    |%n");
				System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		    	  System.out.printf("| %-20s | %-20s | %-16s | %-16s | %-16d | %-16s |%n", resultSet.getInt("appointment_no"), resultSet.getString("appointment_name"), resultSet.getString("doctor_name"), resultSet.getString("owner_name"), resultSet.getInt("charge"), resultSet.getString("description"));
   	
		    	  System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		  		
		    	  
		    	  }
		} catch (SQLException exc) {
			System.out.println("Error Occured");
			System.out.println(exc.getMessage());
		}
		
		
		
	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAppointmentByID() {
		
		System.out.println(ANSI_RED+"\n\n\n      -------------Delete Appointment By ID-----------\n"+ANSI_RESET);

		ArrayList<Appointment> appointmentList=new ArrayList<Appointment>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM appointment";
			resultSet = statement.executeQuery(query);
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
		      while (resultSet.next()) {
		    	  Appointment appointment = new Appointment();
		    	  
		    	  
		    	  appointment.setAppointment_no(resultSet.getInt("appointment_no"));
		    	  appointment.setAppointment_name(resultSet.getString("appointment_name"));
		    	  appointment.setDoctor_name(resultSet.getString("doctor_name"));
		    	  appointment.setOwner_name(resultSet.getString("owner_name"));
		    	  appointment.setCharge(resultSet.getInt("charge"));
		    	  appointment.setDescription(resultSet.getString("description")); 
		    	  
		    	  
		
		    	  appointmentList.add(appointment);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		String leftAlignFormat = "| %-20s | %-20s | %-16s | %-16s | %-16d | %-16s |%n";

		System.out.println("Appointment List\n");

		System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");
		System.out.format("|   Appointment no    |   Appointment name    |   Doctor name   |   Owner name    |   Charge         |   Description    |%n");
		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		for (Appointment appointmentModel : appointmentList) {
		    System.out.format(leftAlignFormat, appointmentModel.getAppointment_no(), appointmentModel.getAppointment_name(), appointmentModel.getDoctor_name(), appointmentModel.getOwner_name(), appointmentModel.getCharge(), appointmentModel.getDescription());
		}

		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		
		int appointmentId;
		
		System.out.print("Enter Appointment ID To Delete : ");
		appointmentId = Integer.parseInt(scn.next());
		String query ="delete from appointment where id='"+ appointmentId+"' ";
				
		try {
		
			
			statement = connection.createStatement();
			((java.sql.Statement) statement).executeUpdate(query);
			System.out.println("\nAppointment with appointment ID= "+appointmentId+ "  Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error occured while deleting");
			System.out.println(exc.getMessage());
		}
				
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAppointment() {
		
		System.out.println(ANSI_RED+"\n\n\n      -------------Update Appointment-----------\n"+ANSI_RESET);
		
		ArrayList<Appointment> appointmentList=new ArrayList<Appointment>();

		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM appointment";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
		      while (resultSet.next()) {
		    	  
		    	  Appointment appointment = new Appointment();
		    	  
		    	  
		    	  appointment.setAppointment_no(resultSet.getInt("appointment_no"));
		    	  appointment.setAppointment_name(resultSet.getString("appointment_name"));
		    	  appointment.setDoctor_name(resultSet.getString("doctor_name"));
		    	  appointment.setOwner_name(resultSet.getString("owner_name"));
		    	  appointment.setCharge(resultSet.getInt("charge"));
		    	  appointment.setDescription(resultSet.getString("description")); 
		    	  
		    	  
		
		    	  appointmentList.add(appointment);
		    	  
		    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-20s | %-16s | %-16s | %-16d | %-16s |%n";

		System.out.println("Appointment List\n");

		System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");
		System.out.format("|   Appointment no    |   Appointment name    |   Doctor name   |   Owner name    |   Charge         |   Description    |%n");
		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

		for (Appointment appointmentModel : appointmentList) {
		    System.out.format(leftAlignFormat, appointmentModel.getAppointment_no(), appointmentModel.getAppointment_name(), appointmentModel.getDoctor_name(), appointmentModel.getOwner_name(), appointmentModel.getCharge(), appointmentModel.getDescription());
		}

		System.out.format("+----------------------+----------------------+-----------------+------------------+------------------+------------------+%n");

	    int appointmentId;
	    System.out.print("Enter Appointment ID To Update : ");
	    appointmentId = scn.nextInt();

//		String query = "SELECT * FROM prescription WHERE id = '"+ prescriptionId +"'";

	    String query1 = "SELECT * FROM appointment WHERE id = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
	        preparedStatement.setInt(1, appointmentId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            System.out.print("Enter new no: ");
	            Integer appointment_no = scn.nextInt();
	            System.out.print("Enter new name: ");
	            String appointment_name = scn.next();
	            System.out.print("Enter new doctor name: ");
	            String doctor_name = scn.next();
	            System.out.print("Enter new owner name: ");
	            String owner_name = scn.next();
	            System.out.print("Enter new charge: ");
	            Integer charge = scn.nextInt();
	            System.out.print("Enter new description: ");
	            String description = scn.next();
	            
	            String updateQuery = "UPDATE appointment SET appointment_no = ?, appointment_name = ?, doctor_name = ?, owner_name = ?, charge = ?, description = ? WHERE id = ?";
	            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                updateStatement.setInt(1, appointment_no);
	                updateStatement.setString(2, appointment_name);
	                updateStatement.setString(3, doctor_name);
	                updateStatement.setString(4, owner_name);
	                updateStatement.setInt(5, charge);
	                updateStatement.setString(6, description);
	                updateStatement.setInt(7, appointmentId);
	                
	                int result = updateStatement.executeUpdate();
	                if (result > 0) {
	                	System.out.println("\n");
	                	System.out.println(ANSI_BLUE+"appointment_no= " + appointment_no + "  appointment_name= " + appointment_name + "  doctor_name= " + doctor_name+"  owner_name= " + owner_name+ "charge= " + charge + "description= " + description +ANSI_RESET );

	                   
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to update the prescription.");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Appointment with ID " + appointmentId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while updating Prescription.");
	        e.printStackTrace();
	    }
		
		
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

}
