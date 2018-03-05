package com.incedoinc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.incedoinc.dao.PostgreConnection;

public class PostgreSQLJDBCSelect {
   public static void main( String args[] ) {
      Connection c = PostgreConnection.getConnection();
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "manish", "tomar");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM Patient_Visit_Clinical_Data;" );
         while ( rs.next() ) {
        	float Visit_ID = rs.getInt("Visit_ID");
        	Object data = rs.getObject("Data");
            System.out.println( "ID = " + Visit_ID );
            System.out.println( "Data = " + data );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
   }
}