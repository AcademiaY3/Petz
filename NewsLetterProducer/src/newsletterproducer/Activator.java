package newsletterproducer;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import newsletterproducer.impl.NewsLetterImpl;
import newsletterproducer.service.NewLetterService;


public class Activator implements BundleActivator {

	ServiceRegistration registration=null;
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("NewsLetter Service Started");
		NewLetterService newsLetterService=new NewsLetterImpl();
		registration= bundleContext.registerService(NewLetterService.class.getName(),newsLetterService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("NewsLetter Service Stopped");
		registration.unregister();
	}

}
