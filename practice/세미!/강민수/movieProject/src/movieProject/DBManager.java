package movieProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private String driver        = "org.mariadb.jdbc.Driver";
    private String url           = "jdbc:mariadb://localhost:3306/movie?useUnicode=true&characterEncoding=utf8";
    private String uId           = "root";
    private String uPwd          = "1234";
    
    private Connection               con;
    private PreparedStatement        pstmt;
    private ResultSet                rs;
    public DBManager() {
        try {
           Class.forName(driver);
           con = DriverManager.getConnection(url, uId, uPwd);
           
           if( con != null ){ System.out.println("데이터 베이스 접속 성공"); }
           
       } catch (ClassNotFoundException e) { System.out.println("드라이버 로드 실패");    } 
         catch (SQLException e) { System.out.println("데이터 베이스 접속 실패"); }
   }
    
    public List<MovieInfoData> searchSelect(String searchData,String searchMovie) {
    	List<MovieInfoData> searcharray = new ArrayList<>();
    	String sql = "SELECT title,filmrating,grade,releaseddata,rp,imgurl FROM "+ searchMovie +" where "+"title LIKE '"+searchData+"%'" ;
    	try {
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.clearBatch();
    		
    		
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			MovieInfoData data= new MovieInfoData();
            	data.setTitle(rs.getString("title"));
            	data.setFilmRating(rs.getString("filmrating"));
            	data.setGrade(rs.getString("grade"));
            	data.setReleasedData(rs.getString("releaseddata"));
            	data.setRp(rs.getString("rp"));
            	data.setImgUrl(rs.getString("imgurl"));
            	searcharray.add(data);
    		}
    		pstmt.clearParameters();
    	}catch(SQLException e) {
    		System.out.println("쿼리 수행 실패");
    	}
    	return searcharray;
    }
    
    public void scheduledInsert(List<MovieInfoData> datas) {
    	String sql = "INSERT INTO scheduled (title,filmrating,grade,releaseddata,rp,imgurl)" + 
    			" VALUES (?,?,?,?,?,?)";

    	try {
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.clearBatch();
    		for(int i =0; i<datas.size();i++) {
    			
    		
    		String t=datas.get(i).getTitle();
    		String fr=datas.get(i).getFilmRating();
    		String g=datas.get(i).getGrade();
    		String rd=datas.get(i).getReleasedData();
    		String rp=datas.get(i).getRp();
    		String iu=datas.get(i).getImgUrl();
    		
//    		System.out.println(datas.get(0).getFilmRating());
    		pstmt.setString(1, t); 
    		pstmt.setString(2, fr);
    		pstmt.setString(3, g); 
    		pstmt.setString(4, rd); 
    		pstmt.setString(5, rp);
    		pstmt.setString(6, iu);

    		System.out.println(t);
    		System.out.println(fr);
    		System.out.println(g);
    		System.out.println(rd);
    		System.out.println(rp);
    		System.out.println(iu);
    		int r = pstmt.executeUpdate();
    		pstmt.clearParameters();
    		System.out.println("변경된 row : " + r);
    		}

    	}catch(SQLException e) {
    		System.out.println("쿼리 수행 실패");
    	}
    	
    }

    public void insert(List<MovieInfoData> datas) {
    	String sql = "INSERT INTO showing (title,filmrating,grade,releaseddata,rp,imgurl)" + 
    			" VALUES (?,?,?,?,?,?)";

    	try {
    		
    		pstmt = con.prepareStatement(sql);
    		pstmt.clearBatch();
    		for(int i =0; i<datas.size();i++) {
    			
    		
    		String t=datas.get(i).getTitle();
    		String fr=datas.get(i).getFilmRating();
    		String g=datas.get(i).getGrade();
    		String rd=datas.get(i).getReleasedData();
    		String rp=datas.get(i).getRp();
    		String iu=datas.get(i).getImgUrl();
    		
//    		System.out.println(datas.get(0).getFilmRating());
    		pstmt.setString(1, t); 
    		pstmt.setString(2, fr);
    		pstmt.setString(3, g); 
    		pstmt.setString(4, rd); 
    		pstmt.setString(5, rp);
    		pstmt.setString(6, iu);

    		System.out.println(t);
    		System.out.println(fr);
    		System.out.println(g);
    		System.out.println(rd);
    		System.out.println(rp);
    		System.out.println(iu);
    		int r = pstmt.executeUpdate();
    		pstmt.clearParameters();
    		System.out.println("변경된 row : " + r);
    		}

    		

    		
    		
    		
    	}catch(SQLException e) {
    		System.out.println("쿼리 수행 실패");
    	}
    	
    }
    
    public List<MovieInfoData> selectShowing(List<MovieInfoData> movieData) {
    	movieData = new ArrayList<>();
    	String sql ="select title,filmrating,grade,releaseddata,rp,imgurl from showing";
    	try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	MovieInfoData data= new MovieInfoData();
            	data.setTitle(rs.getString("title"));
            	data.setFilmRating(rs.getString("filmrating"));
            	data.setGrade(rs.getString("grade"));
            	data.setReleasedData(rs.getString("releaseddata"));
            	data.setRp(rs.getString("rp"));
            	data.setImgUrl(rs.getString("imgurl"));
            	movieData.add(data);
            }
            
        } catch (SQLException e) { System.out.println(" 쿼리 수행 실패"); }
    	return movieData;
    }
    public List<MovieInfoData> selectScheduled(List<MovieInfoData> movieData) {
    	movieData = new ArrayList<>();
    	String sql ="select title,filmrating,grade,releaseddata,rp,imgurl from scheduled";
    	try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
            	MovieInfoData data= new MovieInfoData();
            	data.setTitle(rs.getString("title"));
            	data.setFilmRating(rs.getString("filmrating"));
            	data.setGrade(rs.getString("grade"));
            	data.setReleasedData(rs.getString("releaseddata"));
            	data.setRp(rs.getString("rp"));
            	data.setImgUrl(rs.getString("imgurl"));
            	movieData.add(data);
            }
           
        } catch (SQLException e) { System.out.println(" 쿼리 수행 실패"); }
    	return movieData;
    }

    public static void main(String[] args) {
		//웹 크롤링 해서 디비에 넣기
    	DBManager db = new DBManager(); //디비 연결하고
		MovieCrawling mcl = new MovieCrawling();
		List<MovieInfoData> datas;
		
//		datas = mcl.releasedMovieCrawling();//인기 영화 크롤링해서 데이터넣기
//		db.insert(datas); //디비에 데이터넣기
		
		datas = mcl.scheduledMovieCrawling();
		db.scheduledInsert(datas);
		
	}




}
