package dbconnection.serviceimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import dbconnection.service.IDbCon;

public class DbCon implements IDbCon {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/petz";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private Connection connection;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";

	public DbCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println(ANSI_YELLOW + "Database Connected Successfully" + ANSI_RESET);
		} catch (Exception e) {
			System.out.println(ANSI_YELLOW + "Could not connect to the database" + ANSI_RESET);
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
