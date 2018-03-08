package com.incedoinc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PatientDao {

	public String getPatientData(String patientId) {
		Statement stmt = null;
		StringBuilder patientData = new StringBuilder();
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = null;
			conn = PostgreConnection.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient_Visit_Clinical_Data;");
			while (rs.next()) {
				// float Visit_ID = rs.getInt("Visit_ID");
				Object data = rs.getObject("Data");

				JSONParser parser = new JSONParser();
				Object obj = parser.parse(data.toString());

				JSONObject jsonObject = (JSONObject) obj;

				if (jsonObject.get("Patient_ID").equals(patientId)) {
					if (patientData.length() == 0)
						patientData.append("[" + data.toString());
					else
						patientData.append("," + data.toString());

				}

			}
			if (patientData.length() == 0)
				patientData.append("]");
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return patientData.toString();
	}

	public List<String> getPatientIds() {

		Statement stmt = null;
		List<String> patientIds = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = null;
			conn = PostgreConnection.getConnection();
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Patient_Visit_Clinical_Data;");
			while (rs.next()) {
				Object data = rs.getObject("Data");
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(data.toString());
				JSONObject jsonObject = (JSONObject) obj;
				if (!patientIds.contains(jsonObject.get("Patient_ID").toString()))
					patientIds.add(jsonObject.get("Patient_ID").toString());
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return patientIds;
	}
}
