package newsletterconsumer;

import java.util.Scanner;



import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import newsletterproducer.service.NewLetterService;


public class Activator implements BundleActivator {

	ServiceReference reference;
	public void start(BundleContext bundleContext) throws Exception{
		System.out.println("NewsLetter Consumer Started");
		reference = bundleContext.getServiceReference(NewLetterService.class.getName());
		System.out.println(reference);
		NewLetterService newLetterService = (NewLetterService) bundleContext.getService(reference);
		newsLetterMethod(newLetterService);
	}

	public void stop(BundleContext bundleContext) throws Exception {
	    System.out.println("NewsLetter Consumer Stopped");
	}

	private void newsLetterMethod(NewLetterService newLetterService) {
		int option;
		String subOption = "y";

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("\n\n\n");
			System.out.println("                    ----------NewsLetter Management Section ----------\n");
			System.out.println("                         1  - Begin Sending NewsLetter To Number");
			System.out.println("                         2  - Email NewsLetter");
			System.out.println("\n                  --------------------------------------------------");
			System.out.println("\n                  --------------------------------------------------");

			System.out.print("\n\nChoose an option : ");

			option = Integer.parseInt(scan.nextLine().trim());

			switch (option) {
			case 1:
				newLetterService.createSmsNewsLetter();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to Send Another NewsLetter (y/n)");
					subOption = scan.nextLine().trim();
					if (subOption.equals("y") || subOption.equals("Y")) {
						newLetterService.createSmsNewsLetter();
					}
				}
				newsLetterMethod(newLetterService);
				break;
			case 2:
				newLetterService.createEmailNewsLetter();
				while (subOption.equals("y")) {
					System.out.println("\n\nDo you want to Send Another NewsLetter (y/n)");
					subOption = scan.nextLine().trim();

					if (subOption.equals("y") || subOption.equals("Y")) {
						newLetterService.createEmailNewsLetter();
					}
				}
				newsLetterMethod(newLetterService);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				newsLetterMethod(newLetterService);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
