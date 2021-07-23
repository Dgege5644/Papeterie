package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {
	private static String urldb;
	private static String userdb;
	private static String passworddb;
	
	
	static {
		//url = Settings.getPropriete("url");
		urldb= Settings.getProperty("urldb");
		userdb = Settings.getProperty("userdb");
		passworddb = Settings.getProperty("passworddb");
	}
	
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				urldb, userdb, passworddb
	);
		return connection;
	}
}
