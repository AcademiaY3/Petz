package adoptionproducer.impl;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import adoptionproducer.dao.AdoptionDao;
import adoptionproducer.modal.Adoption;
import adoptionproducer.service.AdoptionService;
import dbconnection.serviceimpl.DbCon;

public class AdoptionServiceImpl implements AdoptionService {
	Scanner scn = new Scanner(System.in);
	private DbCon databaseConnection = new DbCon();;
	private Connection conn = databaseConnection.getConnection();
	private AdoptionDao adoptionDao = new AdoptionDao(conn);

	public AdoptionServiceImpl() {
	}

	@Override
	public void createAdoption() {
		try {
			System.out.println("\n\n\n      -------------Add Adoption to Database-----------\n");
			Adoption adoption = new Adoption();

			System.out.print("Enter Animal Name    - ");
			adoption.setName(scn.nextLine());

			System.out.print("Enter Animal Category  - ");
			adoption.setAnimal(scn.nextLine());

			System.out.print("Enter Adopter name - ");
			adoption.setAdopter(scn.nextLine());

			System.out.print("enter disease of animal - ");
			adoption.setDisease(scn.nextLine());

			System.out.print("enter animal age - ");
			adoption.setAge(scn.nextLine());

			int result = 0;
			result = adoptionDao.createAdoption(adoption);
			if (result > 0) {
				System.out.println("New Adoption Added to the Database");
				System.out.println("Adopter Name =  Mr." + adoption.getAdopter() + "   Animal =  "
						+ adoption.getAnimal() + "   Animal Name = " + adoption.getName() + "   Disease = "
						+ adoption.getDisease() + "Animal Age = " + adoption.getAge());
			} else if (result == 0) {
				System.out.println("Failed to add the Record.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Occured");
		}

	}

	@Override
	public void getAllAdoption() {
		System.out.println("\n\n\n      -------------All Adoption Present-----------\n");
		String leftAlignFormat = "| %-10s | %-20s | %-20s | %-15s | %-40s | %-15s | %-10s |%n";
		System.out.format(
				"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");
		System.out.format(
				"| Serial Num |      Animal Name      |     Adopter Name     |   Animal Age   |               Disease                  |   Animal Type   |  Created   |%n");
		System.out.format(
				"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");

		List<Adoption> adoptionList = new ArrayList<>();
		adoptionList = adoptionDao.getAllAdoption();
		for (Adoption adoption : adoptionList) {
			System.out.format(leftAlignFormat, adoption.getSerial(), adoption.getName(), adoption.getAdopter(),
					adoption.getAge(), adoption.getDisease(), adoption.getAnimal(), adoption.getCreated());
		}
		System.out.format(
				"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");

	}

	@Override
	public void getAdoptionByID() {
		System.out.println("\n\n\n      -------------Get Adoption By ID-----------\n");

		String adoptionId;
		System.out.print("Enter Adoption ID : ");
		adoptionId = scn.next();
		Adoption adoption = new Adoption();
		adoption = adoptionDao.getAdoptionByID(adoptionId);
		if (adoption != null) {
			System.out.println("\n");
			System.out.println("Details of Adoption ID- " + adoptionId);

			String leftAlignFormat = "| %-10s | %-20s | %-20s | %-15s | %-40s | %-15s | %-10s |%n";
			System.out.format(
					"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");
			System.out.format(
					"| Serial Num |      Animal Name      |     Adopter Name     |   Animal Age   |               Disease                  |   Animal Type   |  Created   |%n");
			System.out.format(
					"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");

			System.out.format(leftAlignFormat, adoption.getSerial(), adoption.getName(), adoption.getAdopter(),
					adoption.getAge(), adoption.getDisease(), adoption.getAnimal(), adoption.getCreated());
		} else {
			System.out.format("|    Not Found   |%n");
		}
		System.out.format(
				"+------------+----------------------+----------------------+-----------------+----------------------------------------+-----------------+------------+%n");
	}

	@Override
	public void deleteAdoption() {
		System.out.println("\n\n\n      -------------Delete Adoption By ID-----------\n");
		getAllAdoption();
		String adoptionId;

		System.out.print("Enter Adoption Serial To Delete : ");
		adoptionId = scn.next();

		int result = 0;
		result = adoptionDao.deleteAdoption(adoptionId);
		if (result > 0) {
			System.out.println("\nAdoption with Adoption Serial= " + adoptionId + "  Successfully deleted\n");
		} else {
			System.out.println("Deletion Error");
		}
	}

	@Override
	public void updateAdoption() {
		Adoption adoption = new Adoption();
		String adoptionId;

		System.out.println("\n\n\n      -------------Update Adoption-----------\n");
		getAllAdoption();

		System.out.print("Enter Adoption Serial To Update : ");
		adoptionId = scn.nextLine();

		System.out.print("Enter Animal Name    - ");
		adoption.setName(scn.nextLine());

		System.out.print("Enter Animal Category  - ");
		adoption.setAnimal(scn.nextLine());

		System.out.print("Enter Adopter name - ");
		adoption.setName(scn.nextLine());

		System.out.print("enter disease of animal - ");
		adoption.setDisease(scn.nextLine());

		System.out.print("enter animal age - ");
		adoption.setAge(scn.nextLine());

		int result = 0;
		result = adoptionDao.updateAdoption(adoption, adoptionId);
		if (result > 0) {
			System.out.println("\nAdoption with Adoption Serial= " + adoptionId + "  Successfully Updated\n");
			System.out.println("Adopter Name =  Mr." + adoption.getAdopter() + "   Animal =  " + adoption.getAnimal()
					+ "   Animal Name = " + adoption.getName() + "   Disease = " + adoption.getDisease()
					+ "Animal Age = " + adoption.getAge());
		} else {
			System.out.println("Update Error");
		}
	}

}
