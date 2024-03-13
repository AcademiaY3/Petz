package adoptionconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import adoptionproducer.service.AdoptionService;

public class Activator implements BundleActivator {

	ServiceReference reference;
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Adoption Consumer Started");
		reference = bundleContext.getServiceReference(AdoptionService.class.getName());
		AdoptionService adoptionService = (AdoptionService) bundleContext.getService(reference);
		adoptionMethod(adoptionService);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Adoption Consumer Stopped");
		bundleContext.ungetService(reference);
	}
	private void adoptionMethod(AdoptionService adoptionServices) {
		int option;
		String subOption = "y";

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("\n\n\n");
			System.out.println("                    ----------Adoption Management Section ----------\n");
			System.out.println("                         1  - Add adoption to the Database");
			System.out.println("                         2  - Get all Adoption in the Database");
			System.out.println("                         3  - Get Adoption By the Id ");
			System.out.println("                         4  - Update Adoption by the Id");
			System.out.println("                         5  - Delete Adoption by the Id");
			System.out.println("\n                  --------------------------------------------------");
			System.out.println("\n                  --------------------------------------------------");

			System.out.print("\n\nChoose an option : ");

			option = Integer.parseInt(scan.nextLine().trim());

			switch (option) {
			case 1:
				adoptionServices.createAdoption();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Adoption (y/n)");
					subOption = scan.nextLine().trim();
					if (subOption.equals("y") || subOption.equals("Y")) {
						adoptionServices.createAdoption();
					}
				}
				adoptionMethod(adoptionServices);
				break;
			case 2:
				adoptionServices.getAllAdoption();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to See All Adoption again (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						adoptionServices.getAllAdoption();
					}
				}
				adoptionMethod(adoptionServices);
				break;
			case 3:
				adoptionServices.getAdoptionByID();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want get another Adoption (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						adoptionServices.getAdoptionByID();
					}
				}
				adoptionMethod(adoptionServices);
				break;
			case 4:
				adoptionServices.updateAdoption();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to update again(y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						adoptionServices.updateAdoption();
					}
				}
				adoptionMethod(adoptionServices);
				break;
			case 5:
				adoptionServices.deleteAdoption();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to delete another Adoption (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						adoptionServices.deleteAdoption();
					}
				}
				adoptionMethod(adoptionServices);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				adoptionMethod(adoptionServices);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
