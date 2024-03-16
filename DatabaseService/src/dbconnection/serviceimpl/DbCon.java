package dbconnection.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import dbconnection.service.IDbCon;

public class DbCon implements IDbCon {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/petz";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private Connection connection;


	public DbCon() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Database Connected Successfully");
		} catch (Exception e) {
			System.out.println("Could not connect to the database");
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void terminateConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
