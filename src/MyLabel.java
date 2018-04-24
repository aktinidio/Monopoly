import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MyLabel extends JLabel{

	public MyLabel () {
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setOpaque(true);
		this.setIcon(new ImageIcon("Images/null.png"));
		this.setLayout(new GridBagLayout());
	}
	
	public MyLabel (int k) {
				
		this.setVerticalTextPosition(TOP);
		this.setHorizontalTextPosition(CENTER);
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setOpaque(true);
		
		ImageIcon icon = new ImageIcon("Images/"+k+".png");
		Image img = icon.getImage();		
		this.setIcon(new ImageIcon(img.getScaledInstance(50,50, Image.SCALE_SMOOTH)));
		
		this.setLayout(new GridLayout());
	}
}