package dbconnection;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import dbconnection.service.IDbCon;
import dbconnection.serviceimpl.DbCon;

public class Activator implements BundleActivator {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	private ServiceRegistration<?> databaseConnectionService;

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting DB Connection");

		DbCon databaseConnection = new DbCon();
		databaseConnectionService = context.registerService(IDbCon.class.getName(), databaseConnection, null);

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stoping DB Connection");
		if (databaseConnectionService != null) {
			databaseConnectionService.unregister();
			databaseConnectionService = null;
		}
	}

}
