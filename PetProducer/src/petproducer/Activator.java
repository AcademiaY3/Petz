package petproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	private static BundleContext context;

	@SuppressWarnings("rawtypes")
	ServiceRegistration registration;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext context) throws Exception {
	    System.out.println(ANSI_YELLOW+"Pet Details Producer Start."+ANSI_RESET);
	    ServicePublishImpl servicePublish = new ServicePublishImpl();
		registration= context.registerService(ServicePublish.class.getName(),servicePublish, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println(ANSI_YELLOW+"Pet Details Producer Stop."+ANSI_RESET);
		registration.unregister();
	}


}
