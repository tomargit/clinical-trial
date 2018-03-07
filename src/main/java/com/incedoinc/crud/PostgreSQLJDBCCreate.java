package com.incedoinc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.incedoinc.dao.PostgreConnection;

public class PostgreSQLJDBCCreate {
	public static void main(String args[]) {
		
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = null;
			conn = PostgreConnection.getConnection();
			System.out.println("Opened database successfully");
			stmt = conn.createStatement();
			String sql = "CREATE TABLE Patient_Visit_Clinical_Data " + "( Visit_ID SERIAL PRIMARY KEY NOT NULL,"
					+ " Data JSON)";

			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
}