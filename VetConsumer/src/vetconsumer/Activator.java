package vetconsumer;

import java.util.Scanner;

import vetproducer.service.VetService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
	ServiceReference reference;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Vet Consumer Started");
		reference = bundleContext.getServiceReference(VetService.class.getName());
		VetService vetService = (VetService) bundleContext.getService(reference);
		vetMethod(vetService);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Vet Consumer Stopped");
		bundleContext.ungetService(reference);
	}

	private void vetMethod(VetService vetServices) {
		int option;
		String subOption = "y";

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("\n\n\n");
			System.out.println("                    ----------Vet Management Section ----------\n");
			System.out.println("                         1  - Add vet to the Database");
			System.out.println("                         2  - Get all Vet in the Database");
			System.out.println("                         3  - Get Vet By the Id ");
			System.out.println("                         4  - Update Vet by the Id");
			System.out.println("                         5  - Delete Vet by the Id");
			System.out.println("\n                  --------------------------------------------------");
			System.out.println("\n                  --------------------------------------------------");

			System.out.print("\n\nChoose an option : ");

			option = Integer.parseInt(scan.nextLine().trim());

			switch (option) {
			case 1:
				vetServices.createVet();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Vet (y/n)");
					subOption = scan.nextLine().trim();
					if (subOption.equals("y") || subOption.equals("Y")) {
						vetServices.createVet();
					}
				}
				vetMethod(vetServices);
				break;
			case 2:
				vetServices.getAllVet();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to See All Vet again (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						vetServices.getAllVet();
					}
				}
				vetMethod(vetServices);
				break;
			case 3:
				vetServices.getVetByID();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want get another Vet (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						vetServices.getVetByID();
					}
				}
				vetMethod(vetServices);
				break;
			case 4:
				vetServices.updateVet();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to update again(y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						vetServices.updateVet();
					}
				}
				vetMethod(vetServices);
				break;
			case 5:
				vetServices.deleteVet();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to delete another Vet (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						vetServices.deleteVet();
					}
				}
				vetMethod(vetServices);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				vetMethod(vetServices);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
