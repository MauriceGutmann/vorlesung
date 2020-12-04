package de.htwg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseClient {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		long timeInMillis;
		long timeDifInMillis;
		
		for(int i = 1; i <= 5; i++) {
			//get time stamp at the beginning
			timeInMillis = System.currentTimeMillis();
			
			//open database connection
			Connection connection = DataSource.getConnection();
			//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/retroweb_app?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "user", "user");
			Statement statement = connection.createStatement();
			
			//calculate and print time interval in milliseconds
			timeDifInMillis = System.currentTimeMillis() - timeInMillis;
			System.out.println("\nOpen connection and statement " + i + ": " + timeDifInMillis + "ms\n");
			
			//retrieve data, print data, close database connection
			ResultSet rs = statement.executeQuery("SELECT * FROM Auto");
			printResults(rs);
			statement.close();
			connection.close();
			
			//calculate and print time interval in milliseconds
			timeDifInMillis = System.currentTimeMillis() - timeInMillis;
			System.out.println("\nconnection " + i + ": " + timeDifInMillis + "ms\n");
		}
		System.out.println("Fertig!");
	}
	
	public static void printResults(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int numberColumns = metaData.getColumnCount();
		String separator = "============";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numberColumns; i++) {
			System.out.print(metaData.getColumnName(i + 1) + "\t");
			sb.append(separator);
		}
		System.out.println("\n" + sb.toString());
		while (rs.next()) {
			for (int i = 0; i < numberColumns; i++) {
				System.out.print(rs.getString(i + 1) + "\t");
			}
			System.out.println("\n");
		}
	}
	
	public static void dropAndCreateTable(Statement statement) throws SQLException {
		// Vorhandene Tabelle löschen
		statement.execute("DROP TABLE Auto");
		// Tabelle anlegen
		statement
		.execute("CREATE TABLE Auto (Seriennr INTEGER, Typ VARCHAR(32), Kaufdatum DATE, Kosten NUMERIC);");
		// Primärschlüssel einfügen
		statement.execute("ALTER TABLE Auto ADD PRIMARY KEY (Seriennr);");
		// Werte hinzufügen
		statement
		.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (121239658, 'Audi', '2017-10-09', 31332.20);");
		statement
		.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (135679934, 'VW', '2015-11-12', 15232.99);");
		statement
		.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (148887675, 'VW', '2013-1-1', 5232.99);");
	}
}
