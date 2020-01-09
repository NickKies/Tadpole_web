package movieProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class MovieCrawling {
	MovieCrawling(){
		
	}
	public List<MovieInfoData> scheduledMovieCrawling() {
		List<MovieInfoData> movieData = new ArrayList<>();
		String url="https://movie.daum.net/premovie/scheduled"; // 상영영화
		Document doc = null;
		String num = null;
		try {
			doc = Jsoup.connect(url).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Elements element = doc.select("div.cont_movie");
       for(Element el : element.select("div.paging_movie")) {   
    	   num = el.select("a.num_page").text();
    	   num = num.substring(num.length()-1,num.length());
    	   System.out.println(num);
    	   
       }
       int pageNum = Integer.parseInt(num);
       for(int i=0; i<pageNum;i++) {
    	   url = "https://movie.daum.net/premovie/scheduled?opt=reserve&page="+(i+1);
           System.out.println(url);
   			try {
   				doc = Jsoup.connect(url).get();
   			}catch (IOException e) {
   				e.printStackTrace();
   			}
   			element = doc.select("div.cont_movie");
//    		
           System.out.println("============================================================");
           System.out.println("최신 개봉 영화"+ i);
           System.out.println("============================================================");
            
           for(Element el : element.select("ul.list_movie").select("li")) {    // 하위 뉴스 기사들을 for문 돌면서 출력
        	   MovieInfoData data= new MovieInfoData();
        	   String name = el.select("a.name_movie").text();
        	   data.setTitle(name);
        	   String filmrating =el.select("div.info_tit").select(".ico_movie").text();
        	   data.setFilmRating(filmrating);
        	   String grade = el.select(".info_grade").text();
        	   data.setGrade(grade);
        	   grade = grade.substring(7,grade.length());
        	   String[] ary = el.select(".info_state").text().split("・");
        	   String showingData = ary[0];
        	   String moviedot;
        	   if(ary.length == 2) {moviedot=ary[1];}
        	   else {moviedot=" ";}
        	   
        	   data.setReleasedData(showingData);
        	   data.setRp(moviedot);
        	   String imgUrl = el.getElementsByTag("img").attr("src");
        	   if(imgUrl.equalsIgnoreCase("")) {
        		   imgUrl="https://media.istockphoto.com/vectors/no-photo-available-or-missing-image-vector-id499642119";
        	   }else {
        		   ary = imgUrl.split("=");
            	   
            	   imgUrl = ary[1];  
        	   }
        	   
        	   data.setImgUrl(imgUrl);
        	   
        	   movieData.add(data);
           }
 
       }
       return movieData;
	}
	public List<MovieInfoData> releasedMovieCrawling() {
		List<MovieInfoData> movieData = new ArrayList<>();
		String url="https://movie.daum.net/premovie/released"; // 상영영화
		Document doc = null;
		String num = null;
		try {
			doc = Jsoup.connect(url).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		Elements element = doc.select("div.cont_movie");
       for(Element el : element.select("div.paging_movie")) {   
    	   num = el.select("a.num_page").text();
    	   num = num.substring(1).replaceAll(" ", "");
    	   
       }
       int pageNum = Integer.parseInt(num);
       for(int i=0; i<pageNum;i++) {
    	   url = "https://movie.daum.net/premovie/released?opt=reserve&page="+(i+1);
           System.out.println(url);
   			try {
   				doc = Jsoup.connect(url).get();
   			}catch (IOException e) {
   				e.printStackTrace();
   			}
   			element = doc.select("div.cont_movie");
//    		
           System.out.println("============================================================");
           System.out.println("최신 개봉 영화"+ i);
           System.out.println("============================================================");
            
           for(Element el : element.select("ul.list_movie").select("li")) {    // 하위 뉴스 기사들을 for문 돌면서 출력
        	   MovieInfoData data= new MovieInfoData();
        	   String name = el.select("a.name_movie").text();
        	   data.setTitle(name);
        	   String filmrating =el.select(".ico_movie").text();
        	   data.setFilmRating(filmrating);
        	   String grade = el.select(".info_grade").text();
        	   data.setGrade(grade);
        	   grade = grade.substring(7,grade.length());
        	   String[] ary = el.select(".info_state").text().split("・");
        	   String showingData = ary[0];
        	   String moviedot;
        	   if(ary.length == 2) {moviedot=ary[1];}
        	   else {moviedot=" ";}

        	   data.setReleasedData(showingData);
        	   data.setRp(moviedot);
        	   String imgUrl = el.getElementsByTag("img").attr("src");
        	   ary = imgUrl.split("=");
        	   imgUrl = ary[1];
        	   data.setImgUrl(imgUrl);
        	   
        	   movieData.add(data);
           }
 
       }
       return movieData;
	}

	
}
