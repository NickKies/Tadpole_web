import java.util.ArrayList;

public class SongVO {
	int rank;
	String name;
	String Artist;
	String Album;
	
	public SongVO(int rank, String name, String artist, String album) {
		this.rank = rank;
		this.name = name;
		Artist = artist;
		Album = album;
	}
	
	/*public SongVO(int rank, String name, String artist, String album, int rate, ArrayList<SongVO>SL) {
		SongVO temp = new SongVO(rank, name, artist, album, rate);
		SL.add(temp);
	}*/

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}

	public String getAlbum() {
		return Album;
	}

	public void setAlbum(String album) {
		Album = album;
	}



	
}
