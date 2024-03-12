package appointmentproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";

	@SuppressWarnings("rawtypes")
	ServiceRegistration registration;

	public void start(BundleContext context) throws Exception {
	    System.out.println(ANSI_YELLOW+"Appointment Details Producer Start."+ANSI_RESET);
	    ServicePublishImpl servicePublish = new ServicePublishImpl();
		registration= context.registerService(ServicePublisher.class.getName(),servicePublish, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println(ANSI_YELLOW+"Appointment Details Producer Stop."+ANSI_RESET);
		registration.unregister();
	}
}
