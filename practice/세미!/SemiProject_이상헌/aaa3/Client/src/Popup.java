
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Popup {
	
	private JFrame jframe;
	private JPanel jpannel;
	private JButton jbutton;
	private JLabel jlabel;
	
	public Popup(String content, boolean makeButton) {
		jframe = new JFrame();
		jpannel = new JPanel();
		jbutton = new JButton("확인");
		jlabel = new JLabel(content);
		
		jframe.setSize(200, 60);
		jframe.setResizable(false);
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				notVisible();
			}
		});
		jbutton.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					notVisible();
				}
			}
		});
		
		jpannel.setBackground(Color.WHITE);
		jpannel.add(BorderLayout.NORTH, jlabel);
		if (makeButton) {
			jframe.setSize(200, 90);
			jpannel.add(BorderLayout.SOUTH, jbutton);
			
			jframe.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					notVisible();
				}
			});
		}
		jframe.add(BorderLayout.CENTER, jpannel);
		jframe.setLocationRelativeTo(null);
	}
	
	public Popup(String content, boolean makeButton, boolean onTop) {
		jframe = new JFrame();
		jpannel = new JPanel();
		jbutton = new JButton("확인");
		jlabel = new JLabel(content);
		
		jframe.setSize(200, 60);
		jframe.setResizable(false);
		jbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				notVisible();
			}
		});
		jbutton.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					notVisible();
				}
			}
		});
		
		jpannel.setBackground(Color.WHITE);
		jpannel.add(BorderLayout.NORTH, jlabel);
		if (makeButton) {
			jframe.setSize(200, 90);
			jpannel.add(BorderLayout.SOUTH, jbutton);
			
			jframe.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					notVisible();
				}
			});
		}
		if (onTop) {
			jframe.setSize(250, 90);
			jframe.setAlwaysOnTop(true);
			jbutton.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					char keyCode = e.getKeyChar();
					if (keyCode == KeyEvent.VK_ENTER) {
						System.exit(0);
					}
				}
			});
			jframe.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			jframe.add(BorderLayout.CENTER, jpannel);
			jframe.setLocationRelativeTo(null);
		} else {
			jframe.add(BorderLayout.CENTER, jpannel);
			jframe.setLocationRelativeTo(null);
		}
	}
	
	public void visible() {
		this.jframe.setVisible(true);
	}
	
	public void notVisible() {
		this.jframe.setVisible(false);
	}
	
	public void setButtonText(String s) {
		this.jbutton.setText(s);
	}
	
}
