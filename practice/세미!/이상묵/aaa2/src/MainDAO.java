


import java.util.ArrayList;
/*멜론차트 크롤링&DB 이용 프로젝트
작성자:이상묵
작성일 : 2019_09_11
메서드는 이용하고 싶은 경우 주석을 풀면 된다.(꼭 순서에 맞게 사용하자)
순서 
0. 동봉 파일의 query문을 이용하여 테이블을 생성하기
1. crawling -> DB저장 (다운로드 메서드는 주석처리)
2. DB 업로드-> 콘솔로 기능 출력 (크롤링 & 업로드 메서드는 주석처리)
*/
public class MainDAO {
	public static void main(String[] args) {
		ArrayList<SongVO> SL = new ArrayList<SongVO>();
		
		
		//크롤링 하는 메서드
		//CrawlingDAO.Crawling(SL);
		
		
		//크롤링 된 정보를 DB에 업로드 하는 메서드
		//DbDAO.UploadToDB(SL);
		
		
		//DB에서 정보를 다운로드 하는 메서드
		DbDAO.DownloadToDB(SL);
		
		//console창에서 결과확인 & 기능 이용하기
		ViewVO.view_in_console(SL);
		
	}
}
