package main;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {
	private static BasicDataSource dataSource;
	static {
		try {
		//내부적으로 DB연결할 때 발생하는 예외는 RuntimeException으로 넘어가줌 
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@192.168.0.209:1521:xe");
		dataSource.setUsername("team5");
		dataSource.setPassword("55555");
		dataSource.setInitialSize(10);
		dataSource.setMaxTotal(20); //최대 커넥션 할 수 있는~
		System.out.println("DataSource create");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {con.close();}catch(Exception e) {}
		}
	}
}
