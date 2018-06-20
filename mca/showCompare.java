package mca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class showCompare extends JFrame {
	JPanel mainpanel, up, down;
	JLabel label;
	float ndviVal;
	JLabel xval,yval;
	JLabel labell,vall,indexLabel;
	
	public showCompare(Image imageLoad) throws IOException{
	
		Image imageScaled = imageLoad.getScaledInstance(600, -1,  Image.SCALE_SMOOTH);
		final ImageIcon img = new ImageIcon(imageScaled);
		JLabel label = new JLabel(img);
		 
		mainpanel = new JPanel(new BorderLayout());
		up = new JPanel();
		down = new JPanel();
		mainpanel.add(up, BorderLayout.CENTER);
		mainpanel.add(down, BorderLayout.PAGE_END);
		up.add(label);		
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(620,530);						
		this.setVisible(true);
		this.setTitle("");		
		this.setResizable(false);		
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setLocationRelativeTo(null);	
		
		label.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				   int px = arg0.getX();
				   int py = arg0.getY();
				 	
				   /** Display x and y positions **/
				   xval.setText(Integer.toString(px));
				   yval.setText(Integer.toString(py));	

				   int rgbs = ((BufferedImage) imageLoad).getRGB(arg0.getX(),arg0.getY());
                   Color color = new Color((int) rgbs, true);
                   
                   float val = (color.getRed()+color.getBlue()+color.getGreen())/3;
                   
                   /** Display rgb values of false colored **/
	               vall.setText(Float.toString(val));
                   
			}});
	}
	
}
