package emailservice;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import emailservice.implementation.EmailServiceImpl;
import emailservice.service.EmailService;

public class Activator implements BundleActivator {

	ServiceRegistration registration=null;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Email Service Up");
		EmailService emailService = new EmailServiceImpl();
		registration = bundleContext.registerService(EmailServiceImpl.class.getName(), emailService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Email Service Stopped");
		registration.unregister();
	}

}
