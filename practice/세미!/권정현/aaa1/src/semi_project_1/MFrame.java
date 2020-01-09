package semi_project_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class MFrame extends JFrame implements ActionListener, MouseListener{
	private DB_method dm;		// DB 처리용 객체
	private JButton update;		// 리스너에서 버튼을 식별할 수 있게 밖에 선언
	private JButton search;		// 리스너에서 버튼을 식별할 수 있게 밖에 선언
	private DefaultTableModel model; // 테이블 모델
	private JTable jt;			// 곡 리스트 테이블
	
	public static void main(String[] args) {
		MFrame mf = new MFrame();
	}
	
	public MFrame(){
		/* 전체 창 설정 */
		super("Melon Top 100");
		addWindowListener(new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {System.exit( 0 );}	// 닫기 버튼 클릭 시 프로그램 종료 설정
		});
		this.setSize(1000, 500);
		this.setResizable(false);			// 창 크기 조절 금지
		this.setLayout(new BorderLayout());
		
		/* DB 관련 메소드 */
		dm = new DB_method();
		
		/* 상단 버튼 설정 */
		JPanel bt_panel = new JPanel(new FlowLayout());
		bt_panel.setBackground(Color.white);
		
		update = new JButton("목록 최신화");
		search = new JButton("곡 검색");
		
		update.setSize(100, 50);
		search.setSize(100, 50);
		
		update.addActionListener(this);
		search.addActionListener(this);
		
		bt_panel.setBorder(new LineBorder(Color.BLACK));
		bt_panel.add(update);
		bt_panel.add(search);
		
		/* 하단 목록 설정 */
		JPanel list_panel = new JPanel();
		list_panel.setBackground(Color.white);
		
		String[] title = {"순위", "제목", "가수", "앨범제목"};
		String[][] list = dm.showList();
		
		model = new DefaultTableModel(list, title){
			@Override public boolean isCellEditable(int row, int column){return false;} // 셀 수정 금지
		};
		
		jt = new JTable(model);
		jt.setBackground(Color.WHITE);
		jt.setAutoscrolls(true);									// 마우스 휠과 연동
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  	// 다중 선택 금지
		jt.addMouseListener(this);
		jt.getTableHeader().setReorderingAllowed(false);			// 열 이동 금지
		jt.getTableHeader().setResizingAllowed(false);				// 열 크기조절 금지
		jt.getColumn(model.getColumnName(0)).setPreferredWidth(30);	// 셀 크기 조정
		jt.getColumn(model.getColumnName(1)).setPreferredWidth(450);
		jt.getColumn(model.getColumnName(2)).setPreferredWidth(120);
		jt.getColumn(model.getColumnName(3)).setPreferredWidth(300);
		
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setPreferredSize(new Dimension(900, 400));
		
		list_panel.add(jsp);
		
		/* 전체 창에 패널 삽입 및 보이도록 변경 */
		this.add("North", bt_panel);
		this.add("Center", list_panel);
		this.setVisible(true);
	}
	
	public void List_Update(){
		dm.DB_Update();			// 새로 크롤링하여 DB 최신화
		Update(dm.showList());	// 목록 업데이트
	}
	
	public void Search_Update(){
		String[][] sv = dm.search();	// 검색 결과 받기
		if (sv != null)
			Update(sv);					// 결과가 있을 시 업데이트
	}
	
	public void Update(String[][] list){
		model.setRowCount(0);			// 목록 일괄 삭제
		for (String[] row : list)		// 새로 작성
			model.addRow(row);
		jt.updateUI();					// 테이블을 다시 표기
	}
	
	/* 버튼 동작 메소드 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob1 = e.getSource();		// 이벤트를 발생 시킨 객체
		if ((JButton)ob1 == search){	// 어떤 객체인지 검사
			Search_Dialog sd = new Search_Dialog(dm, this);
			sd = null;					// 불필요한 객체 제거
			System.gc();
			Search_Update();
		}
		if ((JButton)ob1 == update){
			List_Update();
		}
	}
	
	/* 마우스 동작 메소드 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {		// 더블 클릭인식
			String title = "";
			int row = jt.getSelectedRow();
			title = (String)jt.getValueAt(row, 1);
			Info_Frame ifr = new Info_Frame(title, dm);	// 정보창 생성
			ifr = null;						// 불필요한 객체 제거
			System.gc();
		}
	}

	/* 사용 안하나 선언해두지 않으면 상속을 못받음 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
