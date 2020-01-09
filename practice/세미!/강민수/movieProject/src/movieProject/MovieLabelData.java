package movieProject;



import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieLabelData {
	
	JPanel rightPn = new JPanel(new GridLayout(5,1));
	JPanel itemPn;
	JLabel imgLb;
	JLabel titleLb;
	JLabel filmratingLb;
	JLabel gradeLb;
	JLabel releaseddataLb;
	JLabel rpLb;
	MovieLabelData(){
		
	}
	public void setupPn() {

		itemPn.add(imgLb);
		
		rightPn.add(titleLb);
		rightPn.add(filmratingLb);
		rightPn.add(gradeLb);
		rightPn.add(releaseddataLb);
		rightPn.add(rpLb);
		itemPn.add(rightPn);
	}

}
