package vetproducer.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dbconnection.serviceimpl.DbCon;
import vetproducer.dao.VetDao;
import vetproducer.model.Vet;
import vetproducer.service.VetService;

public class VetServiceImpl implements VetService {
	Scanner scn = new Scanner(System.in);
	private DbCon databaseConnection = new DbCon();;
	private Connection conn = databaseConnection.getConnection();
	private VetDao vetDao = new VetDao(conn);

	public VetServiceImpl() {
	}

	@Override
	public void createVet() {
		try {
			System.out.println("\n\n\n      -------------Add Vet to Database-----------\n");
			Vet vet = new Vet();

			System.out.print("enter vet First Name    - ");
			vet.setFname(scn.nextLine());

			System.out.print("enter vet Last Name  - ");
			vet.setLname(scn.nextLine());

			System.out.print("enter vet Telephone - ");
			vet.setTelephone(scn.nextLine());

			System.out.print("enter vet Gender - ");
			vet.setGender(scn.nextLine());

			System.out.print("enter vet Address - ");
			vet.setAddress(scn.nextLine());

			int result = 0;
			result = vetDao.createVet(vet);
			if (result > 0) {
				System.out.println("New Vet Added to the Database");
				System.out.println("Vet Name =  Mr." + vet.getFname() + " " + vet.getLname() + "   Telephone =  "
						+ vet.getTelephone() + "   Address = " + vet.getAddress() + "   Gender = " + vet.getGender());
			} else if (result == 0) {
				System.out.println("Failed to add the Record.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured");
		}
	}

	@Override
	public void getAllVet() {
		System.out.println("\n\n\n      -------------All Vet Present-----------\n");
		String leftAlignFormat = "| %-10s | %-20s | %-20s | %-15s | %-40s | %-10s |%n";
		System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");
		System.out.format("| Serial Num |       First Name      |       Last Name       |    Telephone   |                 Address                |   Gender   |%n");
		System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");

		List<Vet> vetList = new ArrayList<Vet>();
		vetList = vetDao.getAllVet();
		for (Vet vet : vetList) {
			System.out.format(leftAlignFormat, vet.getSerial(), vet.getFname(), vet.getLname(), vet.getTelephone(),
					vet.getAddress(), vet.getGender());
		}
		System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");

	}

	@Override
	public void getVetByID() {
		System.out.println("\n\n\n      -------------Get Vet By ID-----------\n");

		String vetId;
		System.out.print("Enter Vet ID : ");
		vetId = scn.next();
		Vet vet = new Vet();
		vet = vetDao.getVetByID(vetId);
		if (vet != null) {
			System.out.println("\n");
			System.out.println("Details of Vet ID- " + vetId);

			String leftAlignFormat = "| %-10s | %-20s | %-20s | %-15s | %-40s | %-10s |%n";
			System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");
			System.out.format("| Serial Num |       First Name      |       Last Name       |    Telephone   |                 Address                |   Gender   |%n");
			System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");
			System.out.format(leftAlignFormat, vet.getSerial(), vet.getFname(), vet.getLname(), vet.getTelephone(),
					vet.getAddress(), vet.getGender());
		} else {
			System.out.format("|    Not Found   |%n");
		}
		System.out.format("+------------+----------------------+----------------------+-----------------+----------------------------------------+------------+%n");
	}

	@Override
	public void deleteVet() {
		System.out.println("\n\n\n      -------------Delete Vet By ID-----------\n");
		getAllVet();
		String vetId;

		System.out.print("Enter Vet Serial To Delete : ");
		vetId = scn.next();

		int result = 0;
		result = vetDao.deleteVet(vetId);
		if (result > 0) {
			System.out.println("\nVet with Vet Serial= " + vetId + "  Successfully deleted\n");
		} else {
			System.out.println("Deletion Error");
		}

	}

	@Override
	public void updateVet() {
		Vet vet = new Vet();
		String vetId;
		
		System.out.println("\n\n\n      -------------Update Vet-----------\n");
		getAllVet();
		
	    System.out.print("Enter Vet Serial To Update : ");
	    vetId = scn.next();
	    
	    System.out.print("enter vet First Name    - ");
		vet.setFname(scn.nextLine());

		System.out.print("enter vet Last Name  - ");
		vet.setLname(scn.nextLine());

		System.out.print("enter vet Telephone - ");
		vet.setTelephone(scn.nextLine());

		System.out.print("enter vet Gender - ");
		vet.setGender(scn.nextLine());

		System.out.print("enter vet Address - ");
		vet.setAddress(scn.nextLine());
		
		int result = 0;
		result = vetDao.updateVet(vet,vetId);
		if (result > 0) {
			System.out.println("\nVet with Vet Serial= " + vetId + "  Successfully Updated\n");
			System.out.println("Vet Name =  Mr." + vet.getFname() + " " + vet.getLname() + "   Telephone =  "
					+ vet.getTelephone() + "   Address = " + vet.getAddress() + "   Gender = " + vet.getGender());
		
		} else {
			System.out.println("Update Error");
		}
	}

}
