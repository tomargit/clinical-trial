package com.incedoinc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLJSONDAO {

	public boolean insert(String data) {
		Connection conn = null;
		conn = PostgreConnection.getConnection();
		System.out.println(data);
		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO Patient_Visit_Clinical_Data (Data) VALUES (?::JSON)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, data);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Records created successfully");
		return false;
	}
}