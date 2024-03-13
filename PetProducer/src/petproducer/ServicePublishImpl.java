package petproducer;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dbconnection.serviceimpl.DbCon;

public class ServicePublishImpl implements ServicePublish{
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
	public void addPet() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Add Pet to Database-----------\n"+ANSI_RESET);
			
		Pet pet= new Pet();
		
		System.out.print("enter owner_name    - ");
		pet.setOwner_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter owner_phone_no  - ");
		pet.setOwner_phone_no(scn.next());
		System.out.print("\n");
		
		System.out.print("enter pet_name  - ");
		pet.setPet_name(scn.next());
		System.out.print("\n");
		
		System.out.print("enter age - ");
		pet.setAge(scn.nextInt());
		System.out.print("\n");
		
		System.out.print("enter breed  - ");
		pet.setBreed(scn.next());
		System.out.print("\n");
		
		System.out.print("enter gender  - ");
		pet.setGender(scn.next());
		System.out.print("\n");
		
		   String insertPetSql = "INSERT INTO pet (owner_name, owner_phone_no, pet_name, age, breed, gender) VALUES (?, ?, ?, ?, ?, ?)";

           try (PreparedStatement preparedStatement = connection.prepareStatement(insertPetSql)) {
               preparedStatement.setString(1, pet.getOwner_name());
               preparedStatement.setString(2, pet.getOwner_phone_no());
               preparedStatement.setString(3, pet.getPet_name());
               preparedStatement.setInt(4, pet.getAge());
               preparedStatement.setString(5, pet.getBreed());
               preparedStatement.setString(6, pet.getGender());
              
               int result = preparedStatement.executeUpdate();
               if (result > 0) {
            	   
            	   System.out.println(ANSI_GREEN +"New Pet Added to the Database"+ANSI_RESET);
            	   System.out.println(ANSI_BLUE+"Owner_name =  "+pet.getOwner_name()+"   Owner_phone_no =  "+pet.getOwner_phone_no()+"   Pet_name = "+pet.getPet_name()+"   Age = "+pet.getAge()+"   Breed = "+pet.getBreed()+"   Gender = "+pet.getGender()+ANSI_RESET);

                    
               }
           } catch (SQLException e) {
               System.out.println("Failed to add the Pet Details.");
              
           } 
		
	}

	@Override
	public void getAllPet() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get All Pet-----------\n"+ANSI_RESET);

		ArrayList<Pet> petList=new ArrayList<Pet>();
		try {

			statement=connection.createStatement();
			String query = "SELECT * FROM  pet ";
			resultSet = statement.executeQuery(query);

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	
		
		try {
		      while (resultSet.next()) {
		    	  Pet  pet  = new  Pet ();
		    	  
		    	  pet.setOwner_name(resultSet.getString("owner_name"));
		    	  pet.setOwner_phone_no(resultSet.getString("owner_phone_no"));
		    	  pet.setPet_name(resultSet.getString("pet_name"));
		    	  pet.setAge(resultSet.getInt("age"));
		    	  pet.setBreed(resultSet.getString("breed"));
		    	  pet.setGender(resultSet.getString("gender"));
			    	 
		    	  petList.add(pet);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-20s | %-15s | %-5d | %-15s | %-10s |%n";

		System.out.println("Pet List");

		System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");
		System.out.format("|   Owner Name        |  Owner Phone No      |    Pet Name     |  Age  |     Breed       |   Gender   |%n");
		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

		for (Pet petModel : petList) {
		    System.out.format(leftAlignFormat, petModel.getOwner_name(), petModel.getOwner_phone_no(), petModel.getPet_name(), petModel.getAge(), petModel.getBreed(), petModel.getGender());
		}

		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

	}


	@Override
	public void getPetByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Get Pet By ID-----------\n"+ANSI_RESET);
		
		int PetId;
		
		System.out.print("Enter Pet ID : ");
		PetId = scn.nextInt();
 
		String query = "SELECT * FROM pet WHERE id = '"+ PetId +"'";
		
		
		
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

		      while (resultSet.next()) { 
		    	  System.out.println("\n");
		    	  System.out.println("Details of Pet ID- " + PetId);
		    	  

		    	  System.out.format(ANSI_BLUE + "+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");
		    	  System.out.format("|   Owner Name        |  Owner Phone No      |    Pet Name     |  Age  |     Breed       |   Gender   |%n");
		    	  System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

		    	  System.out.printf("| %-20s | %-20d | %-15s | %-5d | %-15s | %-10s |%n", resultSet.getString("owner_name"), resultSet.getInt("owner_phone_no"), resultSet.getString("pet_name"), resultSet.getInt("age"), resultSet.getString("breed"), resultSet.getString("gender"));
   	
		    	  System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");	
		    	  }
		} catch (SQLException exc) {
			System.out.println("Error Occured");
			System.out.println(exc.getMessage());
		}
		
	}

	@Override
	public void deletePetByID() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Delete Pet By ID-----------\n"+ANSI_RESET);

		ArrayList<Pet> petList=new ArrayList<Pet>();
		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM pet";
			resultSet = statement.executeQuery(query);
						
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		
		try {
		      while (resultSet.next()) {
		    	  Pet pet = new Pet();
		    	  
		    	  pet.setOwner_name(resultSet.getString("owner_name"));
		    	  pet.setOwner_phone_no(resultSet.getString("owner_phone_no"));
		    	  pet.setPet_name(resultSet.getString("pet_name"));
		    	  pet.setAge(resultSet.getInt("age"));
		    	  pet.setBreed(resultSet.getString("breed"));
		    	  pet.setGender(resultSet.getString("gender"));
		    	  
		    	  petList.add(pet);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-20s | %-15s | %-5d | %-15s | %-10s |%n";

		System.out.println("Pet List");

		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");
		System.out.format("|   Owner Name        |  Owner Phone No      |    Pet Name     |  Age  |     Breed       |   Gender   |%n");
		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

		for (Pet petModel : petList) {
		    System.out.format(leftAlignFormat, petModel.getOwner_name(), petModel.getOwner_phone_no(), petModel.getPet_name(), petModel.getAge(), petModel.getBreed(), petModel.getGender());
		}

		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

		int PetID;
		
		System.out.print("Enter Pet ID To Delete : ");
		PetID = Integer.parseInt(scn.next());
		String query ="delete from pet where id='"+ PetID+"' ";
				
		try {
			
			statement = (Statement) connection.createStatement();
			((java.sql.Statement) statement).executeUpdate(query);
			System.out.println("\nPet with Pet ID= "+PetID+ "  Successfully deleted\n");

		} catch (SQLException exc) {
			System.out.println("Error occured while deleting");
			System.out.println(exc.getMessage());
		}
}

	@Override
	public void updatePet() {
		System.out.println(ANSI_RED+"\n\n\n      -------------Update Pet-----------\n"+ANSI_RESET);
		ArrayList<Pet> petList=new ArrayList<Pet>();

		try {
			statement=connection.createStatement();
			String query = "SELECT * FROM pet";
			resultSet = statement.executeQuery(query);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
		      while (resultSet.next()) {
		    	  Pet pet = new Pet();
		    	  
		    	  pet.setOwner_name(resultSet.getString("owner_name"));
		    	  pet.setOwner_phone_no(resultSet.getString("owner_phone_no"));
		    	  pet.setPet_name(resultSet.getString("pet_name"));
		    	  pet.setAge(resultSet.getInt("age"));
		    	  pet.setBreed(resultSet.getString("breed"));
		    	  pet.setGender(resultSet.getString("gender"));
			    	 
		    	  petList.add(pet);
			    	
				} }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		String leftAlignFormat = "| %-20s | %-20s | %-15s | %-5d | %-15s | %-10s |%n";

		System.out.println("Pet List");

		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");
		System.out.format("|   Owner Name        |  Owner Phone No      |    Pet Name     |  Age  |     Breed       |   Gender   |%n");
		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

		for (Pet petModel : petList) {
		    System.out.format(leftAlignFormat, petModel.getOwner_name(), petModel.getOwner_phone_no(), petModel.getPet_name(), petModel.getAge(), petModel.getBreed(), petModel.getGender());
		}

		System.out.format("+----------------------+----------------------+-----------------+-------+-----------------+------------+%n");

	    int PetId;
	    System.out.print("Enter Pet ID To Update : ");
	    PetId = scn.nextInt();

//		String query1 = "SELECT * FROM pet WHERE id = '"+ PetId +"'";

	    String query1 = "SELECT * FROM pet WHERE id = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
	        preparedStatement.setInt(1, PetId);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	    		System.out.print("enter owner_name    - ");
	    		String owner_name = scn.next();
	    		
	    		System.out.print("enter owner_phone_no  - ");
	    		String owner_phone_no = scn.next();
	    		
	    		System.out.print("enter pet_name  - ");
	    		String pet_name = scn.next();
	    		
	    		System.out.print("enter age - ");
	    		Integer age = scn.nextInt();
	    		
	    		System.out.print("enter breed  - ");
	    		String breed = scn.next();
	    		
	    		System.out.print("enter gender  - ");
	    		String gender = scn.next();
	    		
	            String updateQuery = "UPDATE pet SET owner_name = ?, owner_phone_no = ?, pet_name = ?, age = ?, breed = ?, gender = ? WHERE id = ?";
	            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	         
	                updateStatement.setString(1, owner_name);
	                updateStatement.setString(2, owner_phone_no);
	                updateStatement.setString(3, pet_name);
	                updateStatement.setInt(4, age);
	                updateStatement.setString(5, breed);
	                updateStatement.setString(6, gender);
	                updateStatement.setInt(7, PetId);  
	                
	                int result = updateStatement.executeUpdate();
	                if (result > 0) {
	                	System.out.println("\n");
	             	   System.out.println(ANSI_BLUE+"Owner_name =  "+ owner_name +"   Owner_phone_no =  "+ owner_phone_no +"   Pet_name = "+ pet_name +"   Age = "+ age +"   Breed = "+ breed +"   Gender = "+ breed +ANSI_RESET);
	             	  System.out.println("Pet Updated succssfully.");
	                }
	            } catch (SQLException e) {
	                System.out.println("Failed to update the Pet.");
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Pet with ID " + PetId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error occurred while updating Pet.");
	        e.printStackTrace();
	    }
	    	    
	}
		
	}
