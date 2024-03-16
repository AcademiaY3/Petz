package smsservice;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import smsservice.implemention.SmsServiceImpl;
import smsservice.service.SmsService;

public class Activator implements BundleActivator {
	private ServiceRegistration<?> SmsServiceRegister;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting SMS Service");
		SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
		SmsServiceRegister = bundleContext.registerService(SmsService.class.getName(), smsServiceImpl, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stoping SMS Service");
	}

}
