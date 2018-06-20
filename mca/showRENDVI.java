package mca;
/*This section handles the Red Edge Vegetation Index*/

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

public class showRENDVI extends JFrame {
	JPanel mainpanel, up, down;
	JLabel indexLabel;
	String indexVal;
	JLabel label, indexValue;
	Float sumRENDVI=(float) 0, averageRENDVI, value;
	
	String desktoppath = System.getProperty("user.home") + "\\Desktop";
	String path = desktoppath + "\\Outputs";
	File directory = new File(path);
	
	public showRENDVI(Image fcimage, Image imageRedEdge, String namePattern) throws IOException{
		
		float[][] pixels = new float[1280][1024];
		final BufferedImage b = new BufferedImage(1280,1024, 10);

		for(int i=0;i<1280;i++){
			for(int j=0;j<1024;j++){
				
				float rgbs = ((BufferedImage) fcimage).getRGB(i, j);
			    Color color = new Color((int) rgbs, true);
			    
	            float val_green = color.getGreen();
	            
	         	//get red edge average
                float rergbs = ((BufferedImage) imageRedEdge).getRGB(i,j);
			   	Color colorre = new Color((int) rergbs, true);
                float re_r = colorre.getRed();
                float re_g = colorre.getGreen();
                float re_b = colorre.getBlue();
                float re_ave = (re_r + re_g + re_b)/3; //RED EDGE VALUE
                
	            pixels[i][j] =  (float)((re_ave - val_green)/(re_ave + val_green) * 128 ) + 127;
	                  
	            int rgb = (int)pixels[i][j]<<16| (int)pixels[i][j]<<8| (int)pixels[i][j] ;
	            b.setRGB(i, j, rgb); 
			}
		}
		
		Image imageScaled = b.getScaledInstance(600, -1,  Image.SCALE_SMOOTH);
		final ImageIcon indexRENDVI = new ImageIcon(imageScaled);
		
		//write the false colored image
		TIFFEncodeParam    params=   new TIFFEncodeParam();
		FileOutputStream   os    =   new FileOutputStream(directory + "\\TTC0" + namePattern + "(RENDVI)"+".tif"); 
		javax.media.jai.JAI.create("encode", b, os, "TIFF", params);
		os.close();	
		
		mainpanel = new JPanel(new BorderLayout());
		up = new JPanel();
		down = new JPanel();
		mainpanel.add(up, BorderLayout.CENTER);
		mainpanel.add(down, BorderLayout.PAGE_END);
		label = new JLabel(indexRENDVI);
		up.add(label);		
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(620,525);						
		this.setVisible(true);
		this.setTitle("RED EDGE NORMALIZED VEGETATION INDEX");		
		this.setResizable(false);	
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setLocationRelativeTo(null);	
	}
}
