package prescriptionproducer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dbconnection.serviceimpl.DbCon;

public class ServicePublishImpl implements ServicePublisher{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet;
	private  DbCon databaseConnection;
	
	Scanner scn = new Scanner(System.in);
	
	public ServicePublishImpl() {
		
		databaseConnection=(DbCon) new DbCon();
		connection= databaseConnection.getConnection();
	}
	

	@Override
	public void addPrescription() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Add Prescription to Database-----------\n"+ANSI_RESET);
			
		Prescription prescription= new Prescription();
		
		System.out.print("enter medicine_name    - ");
		prescription.setMedicine_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter dose  - ");
		prescription.setDose(scn.nextInt());
		System.out.print("\n");
		
		System.out.print("enter quentity - ");
		prescription.setQuentity(scn.nextInt());
		System.out.print("\n");
		
		System.out.print("enter vet name - ");
		prescription.setIssue_by(scn.next());
		System.out.print("\n");
		
		
		   String insertPrescriptionSql = "INSERT INTO prescription (medicine_name, dose, quentity, issue_by) VALUES (?, ?, ?, ?)";

           try (PreparedStatement preparedStatement = connection.prepareStatement(insertPrescriptionSql)) {
               preparedStatement.setString(1, prescription.getMedicine_name());
               preparedStatement.setInt(2, prescription.getDose());
               preparedStatement.setInt(3, prescription.getQuentity());
               preparedStatement.setString(4, prescription.getIssue_by());
              
               int result = preparedStatement.executeUpdate();
               if (result > 0) {
            	   
            	   System.out.println(ANSI_GREEN +"New Prescription Added to the Database"+ANSI_RESET);
            	   System.out.println(ANSI_BLUE+"Medicine_name =  "+prescription.getMedicine_name()+"   Dose =  "+prescription.getDose()+"   Quentity = "+prescription.getQuentity()+"   Vet name = "+prescription.getIssue_by()+ANSI_RESET);

                    
               }
           } catch (SQLException e) {
               System.out.println("Failed to add the Medicine_name.");
              
           } 
		
	}

	@Override
	public void getAllPrescription() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get All Prescription-----------\n"+ANSI_RESET);

		ArrayList<Prescription> prescriptionList=new ArrayList<Prescription>();
		try {

			statement=connection.createStatement();
			String query = "SELECT * FROM  prescription ";
			resultSet = statement.executeQuery(query);

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	
		
		try {
		      while (resultSet.next()) {
		    	  Prescription  prescription  = new  Prescription ();
		    	  
		    	  prescription.setMedicine_name(resultSet.getString("medicine_name"));
		    	  prescription.setDose(resultSet.getInt("dose"));
		    	  prescription.setQuentity(resultSet.getInt("quentity"));
		    	  prescription.setIssue_by(resultSet.getString("issue_by"));
			    	 
		    	  prescriptionList.add(prescription);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-16d | %-15d | %-16s |%n";
		
		System.out.printf("Prescription List\n");

		
		
		System.out.format(ANSI_BLUE + "+----------------------+------------------+-----------------+------------------+%n");
  	    System.out.format("|   Medicine Name      |       Dose       |    Quantity     |    Issue_by      |%n");
  	    System.out.format("+----------------------+------------------+-----------------+------------------+%n");

		for(Prescription prescriptionModel: prescriptionList){
			 System.out.format(leftAlignFormat, prescriptionModel.getMedicine_name(), prescriptionModel.getDose(), prescriptionModel.getQuentity(), prescriptionModel.getIssue_by());
	      } 


  	  System.out.format("+----------------------+------------------+-----------------+------------------+%n");
		
	}


	@Override
	public void getPrescriptionByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get Prescription By ID-----------\n"+ANSI_RESET);
		
		int prescriptionId;
		
		System.out.print("Enter Prescription ID : ");
		prescriptionId = scn.nextInt();
 
		String query = "SELECT * FROM prescription WHERE id = '"+ prescriptionId +"'";
		
		
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		      while (resultSet.next()) { 
		    	  System.out.println("\n");
		    	  System.out.println("Details of Prescription ID- " + prescriptionId);
		    	  
		    	  System.out.format(ANSI_BLUE + "+----------------------+------------------+-----------------+------------------+%n");
		    	  System.out.format("|   Medicine Name      |       Dose       |    Quantity     |    Issue_by      |%n");
		    	  System.out.format("+----------------------+------------------+-----------------+------------------+%n");
		    	  
		    	  System.out.printf("| %-20s | %-16d | %-15d | %-16s |%n", resultSet.getString("medicine_name"), resultSet.getInt("dose"), resultSet.getInt("quentity"), resultSet.getString("issue_by"));
   	
		    	  System.out.format("+----------------------+------------------+-----------------+------------------+%n");
		    	  }
		} catch (SQLException exc) {
			System.out.println("Error Occured");
			System.out.println(exc.getMessage());
		}
		
	}

	@Override
	public void deletePrescriptionByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Delete Prescription By ID-----------\n"+ANSI_RESET);

		ArrayList<Prescription> prescriptionList=new ArrayList<Prescription>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM prescription";
			resultSet = statement.executeQuery(query);
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
		      while (resultSet.next()) {
		    	  Prescription prescription = new Prescription();
		    	  
		    	  prescription.setMedicine_name(resultSet.getString("medicine_name"));
		    	  prescription.setDose(resultSet.getInt("dose"));
		    	  prescription.setQuentity(resultSet.getInt("quentity"));
		    	  prescription.setIssue_by(resultSet.getString("issue_by"));
			    	 
		    	  prescriptionList.add(prescription);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		String leftAlignFormat = "| %-20s | %-16d | %-15d | %-16s |%n";
		System.out.printf("Prescription List\n");
		
		System.out.format(ANSI_BLUE + "+----------------------+------------------+-----------------+------------------+%n");
		System.out.format("|   Medicine Name      |       Dose       |    Quantity     |    Issue_by      |%n");
		System.out.format("+----------------------+------------------+-----------------+------------------+%n");

		for(Prescription prescriptionModel: prescriptionList){
			 System.out.format(leftAlignFormat, prescriptionModel.getMedicine_name(), prescriptionModel.getDose(), prescriptionModel.getQuentity(), prescriptionModel.getIssue_by());
	      } 

		System.out.format("+---------------------+------------------+-----------------+------------------+%n");		
		
		int prescriptionID;
		
		System.out.print("Enter Prescription ID To Delete : ");
		prescriptionID = Integer.parseInt(scn.next());
		String query ="delete from prescription where id='"+ prescriptionID+"' ";
				
		try {
			
			statement = (Statement) connection.createStatement();
			((java.sql.Statement) statement).executeUpdate(query);
			System.out.println("\nPrescription with prescription ID= "+prescriptionID+ "  Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error occured while deleting");
			System.out.println(exc.getMessage());
		}
}

	@Override
	public void updatePrescription() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Update Prescription-----------\n"+ANSI_RESET);
		ArrayList<Prescription> prescriptionList=new ArrayList<Prescription>();

		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM prescription";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
		      while (resultSet.next()) {
		    	  Prescription prescription = new Prescription();
		    	  
		    	  prescription.setMedicine_name(resultSet.getString("medicine_name"));
		    	  prescription.setDose(resultSet.getInt("dose"));
		    	  prescription.setQuentity(resultSet.getInt("quentity"));
		    	  prescription.setIssue_by(resultSet.getString("issue_by"));
			    	 
		    	  prescriptionList.add(prescription);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-16d | %-15d | %-16s |%n";
		
		System.out.printf("Prescription List\n");

		
		
		System.out.format(ANSI_BLUE + "+----------------------+------------------+-----------------+------------------+%n");
		System.out.format("|   Medicine Name      |       Dose       |    Quantity     |    Issue_by      |%n");
		System.out.format("+----------------------+------------------+-----------------+------------------+%n");

		for(Prescription prescriptionModel: prescriptionList){
			 System.out.format(leftAlignFormat, prescriptionModel.getMedicine_name(), prescriptionModel.getDose(), prescriptionModel.getQuentity(), prescriptionModel.getIssue_by());
	      } 

		System.out.format("+---------------------+------------------+-----------------+------------------+%n");		
		
	    int prescriptionId;
	    System.out.print("Enter Prescription ID To Update : ");
	    prescriptionId = scn.nextInt();

//		String query = "SELECT * FROM prescription WHERE id = '"+ prescriptionId +"'";

	    String query1 = "SELECT * FROM prescription WHERE id = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
	        preparedStatement.setInt(1, prescriptionId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            System.out.print("Enter new medicine: ");
	            String medicine_name = scn.next();
	            System.out.print("Enter new dose: ");
	            Integer dose = scn.nextInt();
	            System.out.print("Enter quentity: ");
	            Integer quentity = scn.nextInt();
	            System.out.print("Enter issue_by ");
	            String issue_by = scn.next();
	            
	            String updateQuery = "UPDATE prescription SET medicine_name = ?, dose = ?, quentity = ?, issue_by = ? WHERE id = ?";
	            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                updateStatement.setString(1, medicine_name);
	                updateStatement.setInt(2, dose);
	                updateStatement.setInt(3, quentity);
	                updateStatement.setString(4, issue_by);
	                updateStatement.setInt(5, prescriptionId);
	                int result = updateStatement.executeUpdate();
	                if (result > 0) {
	                	System.out.println("\n");
	                	System.out.println(ANSI_BLUE+"medicine_name= " + medicine_name + "  dose= " + dose + "  quentity= " + quentity+"  issue_by= " + issue_by+ANSI_RESET );

	                   
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to update the prescription.");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Prescription with ID " + prescriptionId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while updating Prescription.");
	        e.printStackTrace();
	    }
	    	    
	}
		
	}
