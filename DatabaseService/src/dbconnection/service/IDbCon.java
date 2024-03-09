package dbconnection.service;

import java.sql.Connection;

public interface IDbCon {
	Connection getConnection();
	void terminateConnection();
}
