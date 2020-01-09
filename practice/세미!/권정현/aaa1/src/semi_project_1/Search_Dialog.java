package semi_project_1;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Search_Dialog extends JDialog implements ActionListener{
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private DB_method dm;
	public Search_Dialog(DB_method dm, JFrame main) {
		this.dm = dm;
		setTitle("검색창");
		setModal(true);
		setBounds(main.getX() + main.getWidth()/2 - 200, main.getY() + main.getHeight()/2 - 120, 380, 240); // 메인 창 가운데 나타나게 설정
		setResizable(false);								// 크기 조절 금지
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		JLabel label1 = new JLabel("순   위 : ");
		panel.add(label1);
		
		textField1 = new JTextField();
		textField1.setColumns(25);
		panel.add(textField1);
		
		
		JLabel label2 = new JLabel("곡   명 : ");
		panel.add(label2);
		
		textField2 = new JTextField();
		textField2.setColumns(25);
		panel.add(textField2);
		
		JLabel label3 = new JLabel("가   수 : ");
		panel.add(label3);
		
		textField3 = new JTextField();
		textField3.setColumns(25);
		panel.add(textField3);
		
		JLabel label_1 = new JLabel("앨범명 : ");
		panel.add(label_1);
		
		textField4 = new JTextField();
		textField4.setColumns(25);
		panel.add(textField4);
		
		JLabel warning = new JLabel("<일부만 작성해도 검색됩니다.>                 ");
		warning.setForeground(Color.LIGHT_GRAY);
		panel.add(warning);
		
		JButton search = new JButton("Search");
		search.addActionListener(this);
		panel.add(search);
		
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() { // 닫기 버튼을 누르면 창이 닫히도록 설정
			@Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dm.setSearchValue(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText());
		dispose();
	}
}
