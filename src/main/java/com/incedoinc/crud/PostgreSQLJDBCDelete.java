package com.incedoinc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.incedoinc.dao.PostgreConnection;


public class PostgreSQLJDBCDelete {
   public static void main( String args[] ) {
      Connection c = PostgreConnection.getConnection();
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/postgres",
            "manish", "tomar");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "DELETE FROM Patient_Visit_Clinical_Data ";
         
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table data delete successfully");
   }
}