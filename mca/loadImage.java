package mca;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class loadImage extends JFrame {
	JPanel mainpanel;
	JLabel label;
	
	public loadImage(Image imageLoad) throws IOException{
		
		Image imageScaled = imageLoad.getScaledInstance(600, -1,  Image.SCALE_SMOOTH);
		final ImageIcon img = new ImageIcon(imageScaled);
		JLabel label = new JLabel(img);

		mainpanel = new JPanel(new BorderLayout());
		mainpanel.add(label, BorderLayout.CENTER);
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(620,530);						
		this.setVisible(true);
		this.setTitle("RGB IMAGE");		
		this.setResizable(false);		
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setLocationRelativeTo(null);	
	}
}
