package com.incedoinc.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class PostgreConnection {

	public static Connection getConnection() {
		
		Properties prop = new Properties();
		Connection c = null;
		try {
			//String dir = System.getProperty("user.dir");
			Path currentRelativePath = Paths.get("");
	        String s = currentRelativePath.toAbsolutePath().toString();
	        System.out.println(s);
			InputStream input = new FileInputStream("config.properties");
			prop.load(input);
			final String dbUser = "manish";//prop.getProperty("DB_USER");
			final String dbPassword = "tomar";//prop.getProperty("DB_PASSWORD");
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;
	}
	

}
