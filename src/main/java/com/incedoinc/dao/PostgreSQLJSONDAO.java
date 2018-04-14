package com.incedoinc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public boolean update(String data, float index) {
		Connection conn = null;
		conn = PostgreConnection.getConnection();
		System.out.println(data);
		try {
			conn.setAutoCommit(false);
			//String sql = "INSERT INTO Patient_Visit_Clinical_Data (Data) VALUES (?::JSON)";
			String sql = "UPDATE Patient_Visit_Clinical_Data SET Data = (?::JSON) WHERE Visit_ID = "+ index;
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

	public int getVisitIndex(String visitId) {
		Connection conn = null;
		conn = PostgreConnection.getConnection();
		float Visit_ID=0;
		try {
			conn.setAutoCommit(false);
			String sql = "SELECT Visit_ID from Patient_Visit_Clinical_Data WHERE data->>'Visit_ID' LIKE '%"+visitId+"%';";
			System.out.println(sql);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				 Visit_ID = rs.getInt("Visit_ID");
			}
			
			
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
		return (int) Visit_ID;
	}
}