package mca;
/*This section handles the Green Normalized Difference Vegetation Index*/

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

public class showGNDVI extends JFrame {
	JPanel mainpanel, up, down;
	JLabel indexLabel, label, indexValue;
	String indexVal;
	Float sumGNDVI, averageGNDVI;
	Float value;
	
	String desktoppath = System.getProperty("user.home") + "\\Desktop";
	String path = desktoppath + "\\Outputs";
	File directory = new File(path);
	
	public showGNDVI(Image fcimage, String namePattern) throws IOException{
		
		float[][] pixels = new float[1280][1024];
		final BufferedImage b = new BufferedImage(1280,1024, 10);

		for(int i=0;i<1280;i++){
			for(int j=0;j<1024;j++){
				
				float rgbs = ((BufferedImage) fcimage).getRGB(i, j);
			    Color color = new Color((int) rgbs, true);
			    
	            float val_red = color.getRed();
	            float val_blue = color.getBlue();
	                 
	            pixels[i][j] =  (float)((val_red - val_blue)/(val_red + val_blue) * 128 ) + 127;	                  
	                 
	            int rgb = (int)pixels[i][j]<<16| (int)pixels[i][j]<<8| (int)pixels[i][j] ;
	            b.setRGB(i, j, rgb);
	            
	            //get gndvi sum in all pixels
	            sumGNDVI =+ ((pixels[i][j] - 127) * 128);
			}
		}
		
		Image imageScaled = b.getScaledInstance(600, -1,  Image.SCALE_SMOOTH);
		final ImageIcon indexGndvi = new ImageIcon(imageScaled);
		
		//write the false colored image
		TIFFEncodeParam    params=   new TIFFEncodeParam();
		FileOutputStream   os    =   new FileOutputStream(directory + "\\TTC0" + namePattern + "(GNDVI)"+".tif"); 
		javax.media.jai.JAI.create("encode", b, os, "TIFF", params);
		os.close();	
		
		mainpanel = new JPanel(new BorderLayout());
		up = new JPanel();
		down = new JPanel();
		mainpanel.add(up, BorderLayout.CENTER);
		mainpanel.add(down, BorderLayout.PAGE_END);
		label = new JLabel(indexGndvi);
		up.add(label);			
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(620,525);						
		this.setVisible(true);
		this.setTitle("GREEN NORMALIZED DIFFERENCE VEGETATION INDEX");		
		this.setResizable(false);		
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setLocationRelativeTo(null);	
	}
}
