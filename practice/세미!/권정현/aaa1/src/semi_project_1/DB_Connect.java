package semi_project_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import semi_project_1.DB_Data;

public class DB_Connect {
	private Connection con;
	
	public DB_Connect(){
		try{
			Class.forName(DB_Data.getDriver());
			con = DriverManager.getConnection(DB_Data.getUrl(),DB_Data.getId(),DB_Data.getPass());
		}catch(ClassNotFoundException e){
			System.out.println("driver load fail");
		}catch(SQLException e){
			System.out.println("DB Connect fail");
		}
	}
	
	public Connection getCon(){
		return con;
	}
}
