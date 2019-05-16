package vn.edu.vnuk.sep.sql;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.sep.jdbc.ConnectionFactory;

public class Sql0000RunAllQueries {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connectionDb = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/");
		
		// Create database
		
		new Sql1000DropDatabase(connectionDb).run();
		new Sql1010CreateDatabase(connectionDb).run();

		connectionDb.close();
		
		Connection connectionTable = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/sep");
		
		//Create
		new Sql2010CreatePersons(connectionTable).run();
		new Sql2020CreateStaffs(connectionTable).run();
		new Sql2030CreateLecturers(connectionTable).run();
		new Sql2040CreateCasualWorkers(connectionTable).run();
		
		connectionTable.close();
	}
}
