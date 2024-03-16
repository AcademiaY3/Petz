package vetproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import vetproducer.impl.VetServiceImpl;
import vetproducer.service.VetService;

public class Activator implements BundleActivator {
	ServiceRegistration registration=null;
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Vet Service Started");
		VetService vetService=new VetServiceImpl();
		registration= bundleContext.registerService(VetService.class.getName(),vetService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Vet Service Stopped");
		registration.unregister();
	}

}
