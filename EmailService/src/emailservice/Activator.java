package emailservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import emailservice.implementation.EmailServiceImpl;

public class Activator implements BundleActivator {


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Email Service Up");
		EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
		emailServiceImpl.sendEmail();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Email Service Stopped");
	}

}
