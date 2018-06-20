package mca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.media.jai.codec.TIFFEncodeParam;

public class showAbout extends JFrame {

	JPanel mainpanel;
	JLabel label;
	
	public showAbout() throws IOException{
		ImageIcon ii = new ImageIcon(start.class.getResource("/resources/copyright.jpg"));
		mainpanel = new JPanel(new BorderLayout());
		label = new JLabel(ii);
		mainpanel.add(label);				
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(475,302);						
		this.setVisible(true);
		this.setTitle("ABOUT");		
		this.setResizable(false);		
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setLocationRelativeTo(null);	
	}
}