package smsservice;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import smsservice.implemention.SmsServiceImpl;
import smsservice.service.SmsService;

public class Activator implements BundleActivator {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	private ServiceRegistration<?> SmsServiceRegister;


	public void start(BundleContext bundleContext) throws Exception {
		System.out.println(ANSI_YELLOW + "Starting SMS Service" + ANSI_RESET);
		SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
//		smsServiceImpl.SendSms();
		SmsServiceRegister = bundleContext.registerService(SmsService.class.getName(), smsServiceImpl, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println(ANSI_YELLOW + "Stoping DB Connection" + ANSI_RESET);
	}

}
