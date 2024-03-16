package prescriptionconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import prescriptionproducer.ServicePublisher;

public class Activator implements BundleActivator {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_YELLOW = "\u001B[33m";

	@SuppressWarnings("rawtypes")
	ServiceReference reference;
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	@SuppressWarnings("unchecked")
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println(ANSI_YELLOW+"Prescription Consumer Start"+ANSI_RESET);
		 reference = context.getServiceReference(ServicePublisher.class.getName());
		 ServicePublisher courseServices= (ServicePublisher)context.getService(reference);
		
		//courseServices.updateCourse();
		courseMethod(courseServices);
	}

	private void courseMethod(ServicePublisher courseServices) {
		int option;
		String subOption="y";
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println(ANSI_RED+"                    ----------Prescription Management Section ----------\n"+ANSI_RESET);
		System.out.println("                         1  - Add Prescription to the Database");
		System.out.println("                         2  - Get all Prescription in the Database");
		System.out.println("                         3  - Get Prescription By the Id ");
		System.out.println("                         4  - Update Prescription by the Id");
		System.out.println("                         5  - Delete Prescription by the Id");
		System.out.println("\n                  --------------------------------------------------");
		System.out.println("\n                  --------------------------------------------------");
		
		
		System.out.print("\n\nChoose an option : ");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
		case 1:
			courseServices.addPrescription();
			
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Prescription (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.addPrescription();
				}
			}
			courseMethod(courseServices);
			break;
		case 2:
			courseServices.getAllPrescription();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to See All Prescription again (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getAllPrescription();
				}
			}
			courseMethod(courseServices);
			break;
		case 3:
			courseServices.getPrescriptionByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want get another Prescription (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getPrescriptionByID();
				}
			}
			courseMethod(courseServices);
			break;
		case 4:
			courseServices.updatePrescription();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to update again(y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.updatePrescription();
				}
			}
			courseMethod(courseServices);
			break;
		case 5:
			courseServices.deletePrescriptionByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to delete another Prescription (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.deletePrescriptionByID();
				}
			}
			courseMethod(courseServices);
			break;
		default:
			System.out.println("Incorrect Input. Try Again...");
			courseMethod(courseServices);
	}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println(ANSI_YELLOW+"Prescription Consumer Stop"+ANSI_RESET);
		context.ungetService(reference);
	}

}