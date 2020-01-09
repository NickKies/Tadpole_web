package semi_project_1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DB_method {
	private Connection con;
	private String[] search_value;
	
	public DB_method(){
		con = new DB_Connect().getCon();	// 연결 받아옴
		search_value = new String[4];		// 검색 데이터
		search_value[0] = "-1";				// 초기화
		DB_Update();						// 최초 DB 초기화 및 업데이트
	}
	
	/* 받은 곡명을 가지고 상세정보 검색에 필요한 데이터를 읽어서 반환 */
	public String getID(String title){
		String ID = "";
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select info_link from songlist where title = '");
			sql.append(title);
			sql.append("'");
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			res.next();
			ID = res.getString("info_link");
			
		}catch(SQLException e){
			System.out.println("Query fail");
		}
		
		return ID;
	}
	
	/* 검색 결과를 배열로 저장해 반환 */
	public String[][] search(){
		String[][] list = null;
		if (search_value[0].equals("-1")) return list;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append("select rank, title, singer, album from songlist ");
			
			String rank = search_value[0];
			String title = search_value[1];
			String singer = search_value[2];
			String album = search_value[3];
			
			if (rank.length()+title.length()+singer.length()+album.length() > 0){	// 검색 데이터가 있을 시
				 sql.append("where ");
				 if (rank.length() > 0) sql.append("rank = " + rank);
				 if (title.length() > 0) {
					 if (sql.length() > 54) sql.append(" and ");
					 sql.append("title like \'%" + title + "%\'");		// 해당 문자열을 포함한 데이터를 검색
				 }
				 if (singer.length() > 0) {
					 if (sql.length() > 54) sql.append(" and ");
					 sql.append("singer like \'%" + singer + "%\'");	// 해당 문자열을 포함한 데이터를 검색
				 }
				 if (album.length() > 0) {
					 if (sql.length() > 54) sql.append(" and ");
					 sql.append("album like \'%" + album + "%\'");		// 해당 문자열을 포함한 데이터를 검색
				 }
			}
			
			sql.append(" order by rank");		// 랭킹 순으로 정렬
			
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet res = pstmt.executeQuery();
			
			sql.replace(7, 33, "count(*) as cnt");	
			
			pstmt = con.prepareStatement(sql.toString()); // 검색된 데이터의 수를 받아온다.
			ResultSet cnt = pstmt.executeQuery();
			cnt.next();
			int index = cnt.getInt("cnt");
			if (index < 24) index = 24;			// 검색 결과가 24개보다 적을 시 비어있는 테이블이 보이므로 빈 row를 채우게 설정
			list = new String[index][4];
			index = 0;
			
			while (res.next()){
				list[index][0] = Integer.toString(res.getInt("rank")); 
				list[index][1] = res.getString("title");
				list[index][2] = res.getString("singer");
				list[index][3] = res.getString("album");
				index++;
			}
		}catch(SQLException e){
			System.out.println("Query fail");
		}
		return list;
	}

	/* DB에서 모든 곡 데이터를 받아서 배열로 저장해 반환 */
	public String[][] showList(){
		String[][] list = new String[100][4];
		try{
			String sql = "select rank, title, singer, album from songlist order by rank";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet res = pstmt.executeQuery();
			while (res.next()){
				int index = res.getInt("rank")-1;
				list[index][0] = Integer.toString(index+1); 
				list[index][1] = res.getString("title");
				list[index][2] = res.getString("singer");
				list[index][3] = res.getString("album");
			}
			
		}catch(SQLException e){
			System.out.println("Query fail");
		}
		return list;
	}
	
	/* 검색용 데이터를 받아서 저장 */
	public void setSearchValue(String rank, String title, String singer, String album){
		search_value[0] = rank;
		search_value[1] = title;
		search_value[2] = singer;
		search_value[3] = album;
	}
	
	/* DB를 최신화 */
	public void DB_Update(){
		try {
			String url = "https://www.melon.com/chart/index.htm#params%5Bidx%5D";
			String clean_sql = "delete from songlist";
			String insert_sql = "insert into songlist(rank, title, singer, album, info_link) values(?,?,?,?,?)";
			Document doc = null;
			
			PreparedStatement pstmt = con.prepareStatement(clean_sql);
			pstmt.executeUpdate();
			
            doc = Jsoup.connect(url).get();
            
            // 멜론 사이트 크롤링
            Elements elements = doc.select("tr").select(".lst50");
    		Elements Ranks = elements.select("span").select(".rank");
    		Elements Titles = elements.select("div").select(".ellipsis").select(".rank01").select("a");
    		Elements Singers = elements.select("div").select(".ellipsis").select(".rank02").select("a");
    		Elements Albums = elements.select("div").select(".ellipsis").select(".rank03").select("a");
    		Elements Links = elements.select("div").select(".ellipsis").select(".rank01").select("span");
    		
    		for (int i=0; i < 50; i++){
    			Element Rank = Ranks.get(i);
    			Element Title = Titles.get(i);
    			Element Singer = Singers.get(i);
    			Element Album = Albums.get(i);
    			Element Link = Links.get(i);
    			StringTokenizer st = new StringTokenizer(Link.html(), "(,)");
    			st.nextToken(); st.nextToken();
    			pstmt = con.prepareStatement(insert_sql);
    			pstmt.setInt(1, Integer.parseInt(Rank.html()));
    			pstmt.setString(2, Title.html());
    			pstmt.setString(3, Singer.html());
    			pstmt.setString(4, Album.html());
    			pstmt.setString(5, st.nextToken());
    			if (pstmt.executeUpdate() != 1){
    				System.out.println("Top 100 리스트 업데이트 중 오류가 발생하였습니다.");
    				throw new SQLException();
    			}
    		}
    		
    		// 50곡씩 나눠서 table에 저장되어 있어서 반씩 읽어야함.
    		elements = doc.select("tr").select(".lst100");
    		Ranks = elements.select("span").select(".rank");
    		Titles = elements.select("div").select(".ellipsis").select(".rank01").select("a");
    		Singers = elements.select("div").select(".ellipsis").select(".rank02").select("a");
    		Albums = elements.select("div").select(".ellipsis").select(".rank03").select("a");
    		Links = elements.select("div").select(".ellipsis").select(".rank01").select("span");
    		
    		for (int i=0; i < 50; i++){
    			Element Rank = Ranks.get(i);
    			Element Title = Titles.get(i);
    			Element Singer = Singers.get(i);
    			Element Album = Albums.get(i);
    			Element Link = Links.get(i);
    			StringTokenizer st = new StringTokenizer(Link.html(), "(,)");
    			st.nextToken(); st.nextToken();
    			pstmt = con.prepareStatement(insert_sql);
    			pstmt.setInt(1, Integer.parseInt(Rank.html()));
    			pstmt.setString(2, Title.html());
    			pstmt.setString(3, Singer.html());
    			pstmt.setString(4, Album.html());
    			pstmt.setString(5, st.nextToken());
    			if (pstmt.executeUpdate() != 1){
    				System.out.println("Top 100 리스트 업데이트 중 오류가 발생하였습니다.");
    				throw new SQLException();
    			}
    		}
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
