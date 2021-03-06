import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Data {
	
	private int page = 1;
	private String url = null;
	private Document doc = null;
	private ArrayList<Integer> num = new ArrayList<Integer>();
	private ArrayList<String[]> data = new ArrayList<String[]>();

	Data(String category) {
		category = category.replaceAll(" ", "%20");
		int idx = 1;
		while (true) {
			url = String.format("https://search.shopping.naver.com/search/all.nhn?rigQuery=%s&pagingIndex=%d&pagingSize=40&productSet=model&viewType=list&sort=rel&frm=NVSHPAG&query=%s", category, page, category);
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Elements elements = doc.select("li._itemSection");
			for (int i = 0; true; i++) {
				try {
					String[] tmp = new String[5];
					Elements info = elements.select("div.info");
					tmp[0] = info.get(i).select("a.tit").text();
					Elements price = info.select("span.price").select("em").select("span.num");
					tmp[1] = price.get(i).text();
					String link = href(info.get(i).html());
					try {
						doc = Jsoup.connect(link).get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						tmp[2] = doc.select("div.gpa_view").select("div.gpa").text().substring(0, 3);
					} catch (StringIndexOutOfBoundsException e) {
						tmp[2] = null;
					}
					tmp[3] = href(doc.select("div.thmb").select("img#viewImage").outerHtml());
					tmp[4] = link;
					if (tmp[0] == null || tmp[1] == null || tmp[3] == null || tmp[4] == null) continue;
					num.add(idx);
					data.add(tmp);
					if (idx == 100) break;
					idx++;
				} catch (IndexOutOfBoundsException e) {
					break;
				}
			}
			if (idx == 100) break;
			page++;
		}
	}
	
	private String href(String html) {
		StringBuilder sb = new StringBuilder();
		boolean sw = true;
		for (int i = 0; sw; i++) {
			if (html.substring(i, i + 4).equalsIgnoreCase("http")) {
				for (int j = i; true; j++) {
					if (html.charAt(j) == '"') {
						sw = false;
						break;
					} else {
						sb.append(html.charAt(j));
					}
				}
			}
		}
		return sb.toString();
	}
	
	public ArrayList<Integer> getNum() {
		return num;
	}
	
	public ArrayList<String[]> getData() {
		return data;
	}
	
}
