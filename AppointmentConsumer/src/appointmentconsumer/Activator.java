package appointmentconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import appointmentproducer.ServicePublisher;


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
		System.out.println(ANSI_YELLOW+"Appointment Consumer Start"+ANSI_RESET);
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
		System.out.println(ANSI_RED+"                    ----------Appointment Management Section ----------\n"+ANSI_RESET);
		System.out.println("                         1  - Add Appointment to the Database");
		System.out.println("                         2  - Get all Appointments in the Database");
		System.out.println("                         3  - Get Appointment By the Id ");
		System.out.println("                         4  - Update Appointment by the Id");
		System.out.println("                         5  - Delete Appointment by the Id");
		System.out.println("\n                  --------------------------------------------------");
		System.out.println("\n                  --------------------------------------------------");
		
		
		System.out.print("\n\nChoose an option : ");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
		case 1:
			courseServices.addAppointment();
			
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to Add Another Appointment (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.addAppointment();
				}
			}
			courseMethod(courseServices);
			break;
		case 2:
			courseServices.getAllAppointments();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to See All Appointments again (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getAllAppointments();
				}
			}
			courseMethod(courseServices);
			break;
		case 3:
			courseServices.getAppointmentByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want get another Appointment (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.getAppointmentByID();
				}
			}
			courseMethod(courseServices);
			break;
		case 4:
			courseServices.updateAppointment();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to update again(y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.updateAppointment();
				}
			}
			courseMethod(courseServices);
			break;
		case 5:
			courseServices.deleteAppointmentByID();
			while(subOption.equals("y")) {
				System.out.println("\n\nDo you want to delete another Appointment (y/n)");
				subOption = scan.nextLine().trim();
	
				if(subOption.equals("y")||subOption.equals("Y")) {
					courseServices.deleteAppointmentByID();
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
		System.out.println(ANSI_YELLOW+"Appointment Consumer Stop"+ANSI_RESET);
		context.ungetService(reference);
	}

}
