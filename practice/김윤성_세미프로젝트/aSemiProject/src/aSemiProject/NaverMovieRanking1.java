package aSemiProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NaverMovieRanking1 {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run = true;	
		while(run) {
			System.out.println("----------------------------------------------------------");
			System.out.println("1.날짜 선택 | 2.차트보기 | 3.검색 | 4.DB초기화 | 5.종료");
			System.out.println("----------------------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = scanner.nextInt();
			
			if(selectNo == 1) {				// 날짜 선택 -> 크롤링 -> DB저장
				createChart();
			} else if(selectNo == 2) {		// DB에 저장된 차트 열람
				list();
			} else if(selectNo == 3) {		// 원하는 키워드로 검색 
				search();
			} else if(selectNo == 4) {		// 다른 날짜를 보기위해서는 DB 초기화 필요
				clear();
			} else if(selectNo == 5) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}

	private static void createChart() {
		System.out.println("----------------------------------------------------------");
		System.out.println("저장할 영화평점순위의 날짜를 입력하세요.");
		System.out.println("예시) 0909");
		System.out.println("하루 전 까지만 집계되어 있습니다.");
		System.out.println("----------------------------------------------------------");
		System.out.print("선택> ");
		
		int date = scanner.nextInt();
		
		String curl = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=2019"+date;
		Document doc = null;
		String dbranking = null;
		String dbtitle = null;
		String dbpoint = null;
		String url="jdbc:mariadb://127.0.0.1:3306/movie";
		String user ="root";
		String pw= "1234";
		Connection con = null;		

		try {
			doc = Jsoup.connect(curl).get();
			Class.forName("org.mariadb.jdbc.Driver");
			con=DriverManager.getConnection(url, user ,pw);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tbody").select("tr");
		
		for(Element el : element) {
			dbranking = el.getElementsByTag("img").attr("alt");
			dbtitle = el.select(".title").text();
			dbpoint = el.select(".point").text();			
			
			if(dbtitle.equals("")) {
				continue;
			}
			
			String sql="insert into movie(ranking, title, point) values(?,?,?)";
			
			try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbranking);
			pstmt.setString(2, dbtitle);
			pstmt.setString(3, dbpoint);
			pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(date + "의 자료가 DB에 업로드 되었습니다!");
	}	

	private static void list() {
		System.out.println("----------------------------------------------------------");
		System.out.println("차트 보기");
		System.out.println("----------------------------------------------------------");
		
		String url="jdbc:mariadb://127.0.0.1:3306/movie";
		String user ="root";
		String pw= "1234";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user ,pw);
			String sql="select * from movie";
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				String ranking = rs.getString("ranking");
				System.out.print(ranking + "위| ");
				String title = rs.getString("title");
				System.out.print(title + "| ");
				String point = rs.getString("point");
				System.out.println(point + "점");
				System.out.println("----------------------------------------------------------");
			}			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void search() {
		boolean go = true; 
		while(go) {
			System.out.println("----------------------------------------------------------");
			System.out.println("1.(순위 || 제목  || 평점)  2.처음으로");
			System.out.println("----------------------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = scanner.nextInt();
			
			if(selectNo == 1) {
				System.out.println("----------------------------------------------------------");
				System.out.println("검색어를 입력하세요.");
				System.out.println("----------------------------------------------------------");
				System.out.print("입력> ");

				String sw = scanner.next();
				
				String url="jdbc:mariadb://127.0.0.1:3306/movie";
				String user ="root";
				String pw= "1234";
				
				try {
					Class.forName("org.mariadb.jdbc.Driver");
					Connection con=DriverManager.getConnection(url, user ,pw);
					String sql="SELECT * FROM `movie`.`movie` WHERE `ranking` LIKE '%"+sw+
							"%' OR `title` LIKE '%"+ sw + "%' OR `point` LIKE '%"+ sw +
							"%' ORDER BY `ranking` DESC LIMIT 1000;";
					
					PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) {
						String ranking = rs.getString("ranking");
						System.out.print(ranking + "위| ");
						String title = rs.getString("title");
						System.out.print(title + "| ");
						String point = rs.getString("point");
						System.out.println(point + "점");
						System.out.println("----------------------------------------------------------");
					}			
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				go = false;
			}
		}
	}
	
	private static void clear() {
		String url="jdbc:mariadb://127.0.0.1:3306/movie";
		String user ="root";
		String pw= "1234";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con=DriverManager.getConnection(url, user ,pw);
			String sql="delete from movie"; 
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeQuery();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DB를 초기화했습니다.");
	}
}

