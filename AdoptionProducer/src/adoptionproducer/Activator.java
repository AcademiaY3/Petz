package adoptionproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import adoptionproducer.impl.AdoptionServiceImpl;
import adoptionproducer.service.AdoptionService;

public class Activator implements BundleActivator {

	ServiceRegistration registration=null;
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Adoption Service Started");
		AdoptionService adoptionService=new AdoptionServiceImpl();
		registration= bundleContext.registerService(AdoptionService.class.getName(),adoptionService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Adoption Service Stopped");
		registration.unregister();
	}
}
