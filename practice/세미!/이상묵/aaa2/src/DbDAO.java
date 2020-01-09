import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbDAO {
	
	//디비에 저장하는 메서드
	static void UploadToDB(ArrayList<SongVO> SL) {
		
		String driver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://127.0.0.1:3306/MelonDB";
		String user ="root";
		String pw= "1234";
		Connection con;
		String sql;
		
		try {
		
			Class.forName(driver);
			con=DriverManager.getConnection(url, user ,pw);
			if(con!=null)
			{
				System.out.println("접속 성공");
			}
			for(int i=0;i<SL.size();i++)
			{
				/*System.out.println(SL.get(i).getArtist());*/
				sql="insert into MelonDB(rank, NAME, artist, album) values(?,?,?,?)";
				PreparedStatement pst =con.prepareStatement(sql);
				pst.setInt(1, SL.get(i).getRank());
				pst.setString(2, SL.get(i).getName());
				pst.setString(3, SL.get(i).getArtist());
				pst.setString(4, SL.get(i).getAlbum());
				pst.executeUpdate();
			}
			System.out.println("Upload_complete");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//UploadToDB 종료
	
	
	//DB에서 차트정보를 받아오는 메서드
	static void DownloadToDB(ArrayList<SongVO> SL)
	{
		String driver = "org.mariadb.jdbc.Driver";
		String url="jdbc:mariadb://127.0.0.1:3306/MelonDB";
		String user ="root";
		String pw= "1234";
		Connection con;
		String sql;
		
		int rank=0;
		String NT, ST, AT;
		
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user ,pw);
			if(con!=null)
			{
				System.out.println("접속 성공");
			}
			sql="select * from MelonDB";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet re=pst.executeQuery();
			while(re.next()) {
				rank = re.getInt("rank");
				NT = re.getString("NAME");
				ST = re.getString("artist");
				AT = re.getString("album");
				SL.add(new SongVO(rank, NT, ST, AT));
			}
			
			System.out.println("Download complete");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
