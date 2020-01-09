package movieProject;

public class MovieInfoData {
	String title; //영화제목
	String filmRating; // 이용가
	String grade; // 평가점수
	String releasedData; // 개봉날짜
	String rp;//Reservation Percentage 예약율
	String imgUrl; //포스터이미지 URL
	public MovieInfoData() {
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilmRating() {
		return filmRating;
	}
	public void setFilmRating(String filmRating) {
		this.filmRating = filmRating;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getReleasedData() {
		return releasedData;
	}
	public void setReleasedData(String releasedData) {
		this.releasedData = releasedData;
	}
	public String getRp() {
		return rp;
	}
	public void setRp(String rp) {
		this.rp = rp;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
