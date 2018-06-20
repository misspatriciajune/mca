package mca;
/*This section handles the Normal Difference Vegetation Index*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.media.jai.codec.TIFFEncodeParam;

public class showNDVI extends JFrame {
	JPanel mainpanel, up, down;
	JLabel indexLabel, label, vall, indexValue;
	JLabel xval,yval;
	String indexVal;
	float ndviVal;

	String desktoppath = System.getProperty("user.home") + "\\Desktop";
	String path = desktoppath + "\\Outputs";
	File directory = new File(path);

	public showNDVI(Image fcimage, Image imageRed, Image imageNir, String namePattern) throws IOException {
		
		float[][] pixels = new float[1280][1024];
		final BufferedImage b = new BufferedImage(1280,1024, 10);
		
		for(int i=0;i<1280;i++){
			for(int j=0;j<1024;j++){
				
				int rgbs = ((BufferedImage) fcimage).getRGB(i, j);
			    Color color = new Color((int) rgbs, true);
			          
			    int val_red = color.getRed();
	            int val_green = color.getGreen();
	            
	            //get nir average
                int nirrgbs = ((BufferedImage) imageNir).getRGB(i,j);
			   	Color colornir = new Color((int) nirrgbs, true);
                int nir_r = colornir.getRed();
                int nir_g = colornir.getGreen();
                int nir_b = colornir.getBlue();
                float nir_ave = (nir_r + nir_g + nir_b)/3; //RED EDGE VALUE
               
                //get nir average
                int redrgbs = ((BufferedImage) imageRed).getRGB(i,j);
			   	Color colorred = new Color((int) redrgbs, true);
                int red_r = colorred.getRed();
                int red_g = colorred.getGreen();
                int red_b = colorred.getBlue();
                float red_ave = (red_r + red_g + red_b)/3; //RED EDGE VALUE
                 
	            pixels[i][j] =  (((nir_ave - red_ave)/(nir_ave + red_ave)) * 128 ) + 127 ;
   	          
	            int rgb = (int)pixels[i][j]<<16| (int)pixels[i][j]<<8| (int)pixels[i][j] ;
	            b.setRGB(i, j, rgb); 
			}
		}	
		 
		Image imageScaled = b.getScaledInstance(600, -1,  Image.SCALE_SMOOTH);
		final ImageIcon indexNdvi = new ImageIcon(imageScaled);
		
		//write the false colored image
		TIFFEncodeParam    params=   new TIFFEncodeParam();
		FileOutputStream   os    =   new FileOutputStream(directory + "\\TTC0" + namePattern + "(NDVI)"+".tif"); 
		javax.media.jai.JAI.create("encode", b, os, "TIFF", params);
		os.close();

		mainpanel = new JPanel(new BorderLayout());
		up = new JPanel();
		down = new JPanel();
		mainpanel.add(up, BorderLayout.CENTER);
		mainpanel.add(down, BorderLayout.PAGE_END);
		label = new JLabel(indexNdvi);
		vall = new JLabel("");
		xval= new JLabel("");
		yval= new JLabel("");
		up.add(label);		
		Container container = getContentPane();		
		container.add(mainpanel);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setSize(620,525);		
		this.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		this.setVisible(true);
		this.setTitle("NORMALIZED DIFFERENCE VEGETATION INDEX");		
		this.setResizable(false);					
		this.setLocationRelativeTo(null);	
		label.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				int rgbs = ((BufferedImage) b).getRGB(arg0.getX(),arg0.getY());
                Color color = new Color((int) rgbs, true);
                   
                float val = (color.getRed()+color.getBlue()+color.getGreen())/3;
                   
                /** Display rgb values of false colored **/
	            vall.setText(Float.toString(val));        
			}
		});
	}
}
