package petconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import petproducer.ServicePublish;
import petconsumer.Activator;
import petproducer.ServicePublish;

@SuppressWarnings("unused")
public class Activator implements BundleActivator {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ServicePublish = null;
	
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
		 reference = context.getServiceReference(ServicePublish.class.getName());
		 ServicePublish courseServices= (ServicePublish)context.getService(reference);
		
		//courseServices.updateCourse();
		courseMethod(courseServices);
	}
	
	private void courseMethod(ServicePublish courseServices) {
		int option;
		String subOption="y";
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println(ANSI_RED+"                    ----------Pet Management Section ----------\n"+ANSI_RESET);
		System.out.println("                         1  - Add Pet to the Database");
		System.out.println("                         2  - Get all Pet in the Database");
		System.out.println("                         3  - Get Pet By the Id ");
		System.out.println("                         4  - Update Pet by the Id");
		System.out.println("                         5  - Delete Pet by the Id");
		System.out.println("\n                  --------------------------------------------------");
		System.out.println("\n                  --------------------------------------------------");
		
		
		System.out.print("\n\nChoose an option : ");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
		case 1:
			courseServices.addPet();
			
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Pet (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.addPet();
				}
			}
			courseMethod(courseServices);
			break;
		case 2:
			courseServices.getAllPet();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to See All Pet again (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getAllPet();
				}
			}
			courseMethod(courseServices);
			break;
		case 3:
			courseServices.getPetByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want get another Pet (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getPetByID();
				}
			}
			courseMethod(courseServices);
			break;
		case 4:
			courseServices.updatePet();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to update again(y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.updatePet();
				}
			}
			courseMethod(courseServices);
			break;
		case 5:
			courseServices.deletePetByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to delete another Pet (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.deletePetByID();
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
	}

}
