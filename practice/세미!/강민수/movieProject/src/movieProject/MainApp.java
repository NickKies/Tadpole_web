package movieProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MainApp {
	static List<MovieInfoData> movieData=null;
	static List<MovieInfoData> scheduledMovieData=null;

	 public static void main(String[] args) {
		 //-데이터베이스에 있는데이터 가져와서 보여주기
		 DBManager db = new DBManager();
		 movieData = db.selectShowing(movieData);
		 scheduledMovieData = db.selectScheduled(scheduledMovieData);
		 //-UI 설정 원래 ui만 설정하고 따로 기능해야하는데 귀찮아서 한곳에 했습니다.
		 InitUi obj = new InitUi(movieData,scheduledMovieData,db);


	 }
	


}



class InitUi extends JFrame{
	List<MovieInfoData> movieData=null;
	List<MovieLabelData> labelData=null;
	List<MovieLabelData> sMovieLabelData=null;
	List<MovieLabelData> searchLabelData =null;
	List<MovieInfoData> scheduledMovieData=null;
	List<MovieInfoData> searchMovieData = new ArrayList<>();
	DBManager db;	 //showPage 기본 0은 상영 영화이다.
	int showPage = 0; // 지금 보여주고있는 페이지(검색할때 나, 인기영화인지 상영영화인지 개봉전영화인지 알려주는) 
	int page =0;//showPage에서 보여주는 영화를 15개씩 나눌때 사용하는 변수 
	int maxpage;// 15개씩 나누어 맥스페이지가 5라면 75개의 영화가 있는것이다.
	JPanel btnPanel= new JPanel(); //인기 영화 상영영화 개봉전영화 버튼
	JPanel mainPanel= new JPanel(); //  영화 컨텐츠 레이아웃
	
	JPanel mainContentPn = new JPanel(new GridLayout(5, 3,10,10));	 //영화 리스트 레이아웃
	JPanel mainBottomPn = new JPanel(new GridLayout(1,2,10,10));	 //영화 페이지 이전&다음 레이아웃 
 
	JButton btn1=new JButton("상영 영화");
	
	JButton btn3=new JButton("개봉전 영화");
	JButton btn4=new JButton("검 색");
	JTextField tf = new JTextField();
	JButton nextBtn = new JButton("다음");
	JButton preBtn = new JButton("이전");
	 
     public InitUi(List<MovieInfoData> movieData, List<MovieInfoData> scheduledMovieData,DBManager db)
     {
    	 this.movieData=movieData;
    	 this.scheduledMovieData = scheduledMovieData;
    	 this.db = db;
    	 labelData=new ArrayList<>();
    	 sMovieLabelData=new ArrayList<>();
    	 searchLabelData = new ArrayList<>();
    	 setInfoTread();
    	 setupBtn();
    	 setupContent();
    	 
    	 
    	 
         setTitle("영화");
         setSize(1000, 800);
         setLayout(new BorderLayout());
         
//         mainPanel.setMaximumSize(new Dimension(1000, 800));
         add(mainPanel,BorderLayout.CENTER);
         add(btnPanel,BorderLayout.SOUTH);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setVisible(true);
         onclick();
     }
     public void setupMovieData(List<MovieInfoData> movieData, List<MovieLabelData> labelData) {
			
			
			for(int i=0;i<movieData.size();i++) {
				MovieLabelData item = new MovieLabelData();
				String strurl = movieData.get(i).getImgUrl();
				URL url;
				try {
					url = new URL(strurl);
				
//					BufferedImage image = null;
				
					BufferedImage image = ImageIO.read(url);
//				
//				    //싸이즈 조정
					Image tmp = image.getScaledInstance(125, 100, Image.SCALE_DEFAULT);
			        
//
					item.imgLb = new JLabel(new ImageIcon(tmp));
					
//					item.imgLb = new JLabel("이미지");
//					
					item.imgLb.setMinimumSize(new Dimension(125, 100));
					item.imgLb.setPreferredSize(new Dimension(125, 100));
					item.imgLb.setMaximumSize(new Dimension(125, 100));
					
			
					item.titleLb = new JLabel(movieData.get(i).getTitle());
					item.filmratingLb = new JLabel(movieData.get(i).getFilmRating());
					item.gradeLb = new JLabel(movieData.get(i).getGrade());
					item.releaseddataLb = new JLabel(movieData.get(i).getReleasedData());
					item.rpLb = new JLabel(movieData.get(i).getRp());
					item.itemPn = new JPanel();
					System.out.println("쓰레드 1 아이템 하나 넣기 완료");
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				item.setupPn();
				
				labelData.add(item);
				if(i==14) {
					for(int j =0;j<15;j++) {
						mainContentPn.add(this.labelData.get(j).itemPn);
						mainContentPn.revalidate();
						mainContentPn.repaint();
					}		
				}
			}
			
		}
	private void setInfoTread() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				setupMovieData(movieData,labelData);
				
				
	
				System.out.println("다시 보이기");
				mainContentPn.revalidate();
				mainContentPn.repaint();
				
			}

			
			
		}); 
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				setupMovieData(scheduledMovieData,sMovieLabelData);			
			}
		});
		t1.start();
		t2.start();
		
	}
	public void presPage(List<MovieInfoData> movieData, List<MovieLabelData> labelData) {
		if(page<0) {
			page=0;
		}
		else if(page>0) {
			page--;
		}
		System.out.println(page);
		//0페이지는 0-15 1페이지는 16 -30 2페이지는 31-45
		int itemsize = movieData.size(); //총 아이템
		mainContentPn.removeAll();


		for(int i=page*15;i<(page+1)*15;i++) {
			if(i>itemsize-1)break;
			mainContentPn.add(labelData.get(i).itemPn);
		}
		
	}
	public void nextPage(List<MovieInfoData> movieData, List<MovieLabelData> labelData) {
		int itemsize = movieData.size(); //총 아이템
		if(page<maxpage-1) {
			page++;
		}else {
			System.out.println(page);
			return;
		}

		System.out.println(page);
		//0페이지는 0-15 1페이지는 16 -30 2페이지는 31-45

		mainContentPn.removeAll();
		
		for(int i=page*15;i<(page+1)*15;i++) {	
			if(i>itemsize-1)break;
			
			mainContentPn.add(labelData.get(i).itemPn);
		}
	}
	public void showMoviePage(List<MovieInfoData> MovieData, List<MovieLabelData> LabelData) {
		int itemSize = MovieData.size();
		mainContentPn.removeAll();
		System.out.println(itemSize);
		
		for(int i=0;i<15;i++) {
			if(i>itemSize-1)break;
			mainContentPn.add(LabelData.get(i).itemPn);
		}
		
		
		mainContentPn.revalidate();
		mainContentPn.repaint();
		
	}
	private void onclick() {
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//상영영화 버튼 눌럿을떄
				showPage=0;
				page=0;
				setupMaxPage();
				showMoviePage(movieData,labelData);
				
			}
		});
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//개봉전 영화 버튼 눌렀을때
				page=0;
				showPage =2;
				setupMaxPage();
				showMoviePage(scheduledMovieData,sMovieLabelData);
				
			}

			
		});
		preBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("이전 버튼 클릭");
				
				if(showPage==0) {
					presPage(movieData,labelData);
				}else if(showPage==1) {
					System.out.println("다으버튼에서 페이지 인기영화");
				}else {
					presPage(scheduledMovieData,sMovieLabelData);
				}

				mainContentPn.revalidate();
				mainContentPn.repaint();
				
			}

		
			
		});
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("다음 버튼 클릭");
				
				if(showPage==0) {
					nextPage(movieData,labelData);
				}else if(showPage==1) {
					System.out.println("다으버튼에서 페이지 인기영화");
				}else {
					nextPage(scheduledMovieData,sMovieLabelData);
				}

				mainContentPn.revalidate();
				mainContentPn.repaint();
				
			}
			
		});
		btn4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//검색 버튼 눌럿을때
				searchMovieData.clear();
				searchLabelData.clear();
				String searchStr = tf.getText();
				String sMovieSql = "";
				if(searchStr.equalsIgnoreCase("")) {
					return;//검색창에 아무것도 안썻을떄
				}
				switch (showPage) {
				case 0:
					sMovieSql="showing";//상영 영화
					break;
				case 2:
					sMovieSql="scheduled";//개봉전 영화
					break;

				default:
					break;
				}
				
				searchMovieData =db.searchSelect(searchStr,sMovieSql);
				try {
					
					mainContentPn.removeAll();
					System.out.println(searchMovieData.size()+""+searchMovieData.get(0).title);
					
			
					for(int i=0;i<searchMovieData.size();i++) {
						MovieLabelData item = new MovieLabelData();
						String strurl = searchMovieData.get(i).getImgUrl();
						URL url = new URL(strurl);
					    BufferedImage image = ImageIO.read(url);
					    //싸이즈 조정
					    Image tmp = image.getScaledInstance(125, 100, Image.SCALE_SMOOTH);
				        
			
						item.imgLb = new JLabel(new ImageIcon(tmp));
						item.imgLb.setMinimumSize(new Dimension(125, 100));
						item.imgLb.setPreferredSize(new Dimension(125, 100));
						item.imgLb.setMaximumSize(new Dimension(125, 100));
						
				
						item.titleLb = new JLabel(searchMovieData.get(i).getTitle());
						item.filmratingLb = new JLabel(searchMovieData.get(i).getFilmRating());
						item.gradeLb = new JLabel(searchMovieData.get(i).getGrade());
						item.releaseddataLb = new JLabel(searchMovieData.get(i).getReleasedData());
						item.rpLb = new JLabel(searchMovieData.get(i).getRp());
						item.itemPn = new JPanel();
				
						item.setupPn();
						
						searchLabelData.add(item);
					}
					
				}catch(Exception e1) {
					
				}
				for(int i=0;i<searchLabelData.size();i++) {
					
					
					mainContentPn.add(searchLabelData.get(i).itemPn);
				}
				mainContentPn.revalidate();
				mainContentPn.repaint();
				
			}
		});
	}

	private void setupContent() {
		try {
			setupMaxPage();
			
			System.out.println("maxpage : " +maxpage+" "+movieData.size());
			//MainBottomPn
			
			mainBottomPn.add(preBtn);
			mainBottomPn.add(nextBtn);
			mainPanel.add(mainContentPn,BorderLayout.CENTER);
			mainPanel.add(mainBottomPn,BorderLayout.SOUTH);
		}catch(Exception e) {
			
		}
	}

	private void setupMaxPage() {
		if(showPage == 0) {
			maxpage = movieData.size()/15;
			if((movieData.size()%15)>0)maxpage++;
		}else if(showPage ==1) {
			//인기영화
		}else {
			maxpage = scheduledMovieData.size()/15;
			if((movieData.size()%15)>0)maxpage++;
		}
		
	}

	private void setupBtn() {
	 btn1.setPreferredSize(new Dimension(150,50));
   	 
   	 btn3.setPreferredSize(new Dimension(150,50));
   	 btn4.setPreferredSize(new Dimension(150,50));
   	 btnPanel.add(btn1);
   	 
   	 btnPanel.add(btn3);
   	 btnPanel.add(btn4);
   	 tf.setPreferredSize(new Dimension(250, 50));
   	 btnPanel.add(tf);
		
	}
}

