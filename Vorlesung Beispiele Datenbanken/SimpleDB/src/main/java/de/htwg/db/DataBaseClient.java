package de.htwg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseClient {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/retroweb_app?useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "user", "user");
		
		//createTableAndData(connection);
		selectStatement(connection);
		
		//createTables(connection);
		//transactionExample(connection);
		
		//preparedStatementExample(connection);
		
		connection.close();
		System.out.println("Fertig!");
		
	}
	
	public static void createTableAndData(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		// Vorhandene Tabelle löschen
		statement.execute("DROP TABLE IF EXISTS Auto");
		// Tabelle anlegen
		statement.execute("CREATE TABLE Auto (Seriennr INTEGER, Typ VARCHAR(32), Kaufdatum DATE, Kosten NUMERIC);");
		// Primärschlüssel einfügen
		statement.execute("ALTER TABLE Auto ADD PRIMARY KEY (Seriennr);");
		// Werte hinzufügen
		statement.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (121239658, 'Audi', '2017-10-09', 31332.20);");
		statement.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (135679934, 'VW', '2015-11-12', 15232.99);");
		statement.execute("INSERT INTO Auto (Seriennr, Typ, Kaufdatum, Kosten) VALUES (148887675, 'VW', '2013-1-1', 5232.99);");
		statement.close();
	}
	
	public static void selectStatement(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM Auto");
		//("SELECT p.* FROM projects p Inner Join projects_users pu on p.id = pu.projects_id WHERE pu.users_id = 3");
		System.out.println("SELECT mit Statement");
		printResults(rs);
	}
	
	public static void preparedStatementExample(Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM AUTO WHERE Typ = ?");
		preparedStatement.setString(1, "Audi");
		
		ResultSet rs = preparedStatement.executeQuery();
		System.out.println("SELECT mit Prepared Statement");
		printResults(rs);
		preparedStatement.close();
	}
	
	private static void createTables(Connection connection) throws SQLException {
		
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE IF EXISTS Mitarbeiter");
		statement.execute("DROP TABLE IF EXISTS Abteilung");
		statement.execute("CREATE TABLE Abteilung (ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Abteilungsname VARCHAR(100));");
		statement.execute("CREATE TABLE Mitarbeiter (ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, Mitarbeitername VARCHAR(100), Abteilungs_ID BIGINT);");
		statement.execute("ALTER TABLE Mitarbeiter ADD FOREIGN KEY  (Abteilungs_ID) REFERENCES Abteilung(ID)");
		statement.close();
	}
	
	public static void transactionExample(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		Statement statement = connection.createStatement();
		try {
			statement.execute("INSERT INTO Abteilung (Abteilungsname) VALUES ('Abteilung 1')");
			statement.execute("INSERT INTO Mitarbeiter (Mitarbeitername, Abteilungs_ID) VALUES ('Hugo Meier', (SELECT ID FROM Abteilung WHERE Abteilungsname = 'Abteilung 1'))");
			int i = 1/0;
			connection.commit();
		} catch(Exception e) {
			connection.rollback();
			throw e;
		} finally {
			connection.setAutoCommit(true);
			statement.close();
		}
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
}
