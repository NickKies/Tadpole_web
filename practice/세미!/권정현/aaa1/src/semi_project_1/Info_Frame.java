package semi_project_1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Info_Frame extends JFrame {
	private JPanel contentPane;
	private DB_method dm;
	public Info_Frame(String title, DB_method DM) {
		super("곡 정보");
		dm = DM;											// DB 처리용 객체 받아옴
		this.setSize(1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.white);				// 배경 흰색
		contentPane.setLayout(null);
		
		Font label_font = new Font("Serif", Font.BOLD, 20);
		
		// 곡명
		JLabel tl = new JLabel("<html>" + title + "</html>");
		tl.setBounds(550, 30, 400, 60);
		tl.setFont(label_font);
		contentPane.add(tl);
		
		// 가수
		JLabel sl = new JLabel();
		sl.setBounds(550, 90, 400, 30);
		sl.setFont(label_font);
		sl.setForeground(Color.GREEN);
		contentPane.add(sl);
		
		label_font = new Font("Serif", Font.PLAIN, 15);
		
		// 앨범
		JLabel al = new JLabel();
		al.setBounds(550, 120, 400, 30);
		al.setFont(label_font);
		contentPane.add(al);
		
		// 발매일
		JLabel dl = new JLabel();
		dl.setBounds(550, 150, 400, 30);
		dl.setFont(label_font);
		contentPane.add(dl);
		
		// 장르
		JLabel jl = new JLabel();
		jl.setBounds(550, 180, 400, 30);
		jl.setFont(label_font);
		contentPane.add(jl);
		
		// 가사
		JLabel ly = new JLabel("가    사");
		ly.setBounds(550, 210, 400, 30);
		ly.setFont(label_font);
		contentPane.add(ly);
		
		JTextArea tf = new JTextArea();
		tf.setEditable(false);			// 수정 불가
		tf.setLineWrap(true);			// 새롭게 문자열 추가 시 길이가 창의 길이를 넘어가면 다음 줄로 넘김 허용
		
		JScrollPane ls = new JScrollPane(tf);
		ls.setBounds(550, 240, 400, 280);
		contentPane.add(ls);
		
		String url = "https://www.melon.com/song/detail.htm?songId=" + dm.getID(title);	// 상세정보 페이지 주소
		String image_url = "";	// 앨범 자켓 주소
		
		try {
			// 상세정보 페이지 크롤링
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("div").select("#conts");
			Element image = elements.select("div").select(".thumb").first();
			Element singer = elements.select("div").select(".artist").select("span").first();
			Elements list = elements.select("dl").select(".list");
			Element album = list.select("dd").select("a").first();
			Element date = list.select("dd").get(1);
			Element janre = list.select("dd").get(2);
			Element lyric = elements.select("div").select(".lyric").first();
			
			// 이미지 주소 받기
			image_url = image.html();
			int begin = image_url.indexOf("https://");
			int end = image_url.indexOf(".jpg", begin);
			image_url = image_url.substring(begin, end+4);
			
			// JLabel들에 내용 채우기
			sl.setText(singer.html());
			al.setText("앨   범 : " + album.html());
			dl.setText("발매일 : " + date.html());
			jl.setText("장   르 : " + janre.html());
			
			// 한줄로 된 문자열을 잘라서 저장
			StringTokenizer st = new StringTokenizer(lyric.html().replaceAll("<br>", "\n"), "\n");
			boolean cut = true;
			while (st.hasMoreTokens()){
				String line = st.nextToken();
				if (cut){
					line = line.substring(32);
					cut = false;
				}
				tf.append(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("곡 정보 크롤링에 실패하였습니다.");
		}
		
		tf.setCaretPosition(0);	// 가사창 스크롤이 맨 위에 오도록 설정
		
		// 앨범 자켓
		JPanel Jacket = new Jacket_Panel(image_url);
		Jacket.setBorder(new LineBorder(Color.GREEN, 5));
		Jacket.setBounds(20, 20, 510, 510);
		contentPane.add(Jacket);
		
		this.setVisible(true);
	}
	
	class Jacket_Panel extends JPanel{
		private String link;	// 이미지 주소
		public Jacket_Panel(String s){
			super();
			link = s;
		}
		/* 패널에 이미지를 그리는 메소드 */
		@Override
		public void paint(Graphics g){
			super.paint(g);
			ImageIcon icon;	// Image 객체에는 URL을 사용할 수 없어서 ImageIcon을 사용
			try {
				icon = new ImageIcon(new URL(link));
				Image img = icon.getImage();
				g.drawImage(img, 5, 5, this);	// 이미지 그리기
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}
