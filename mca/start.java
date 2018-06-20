/**This serves as the main class of the program**/

package mca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.sun.media.jai.codec.TIFFEncodeParam;


public class start extends JFrame implements ActionListener{
	
	int mainwidth = 1300;
	int mainheight = 670; 
	
	int height1 = (int)(Math.round(mainheight * 0.05)); 
	int height2 = (int)(Math.round(mainheight * 0.91)); 
	int height3 = (int)(Math.round(mainheight * 0.04)); 
	int height4 = (int)(Math.round(height2 * 0.45));
    int height5 = (int)(Math.round(height2 * 0.5)); 
    int height6 = (int)(Math.round(height2 * 0.5));
    int height7 = (int)(Math.round(height5 * 0.7));
    int height8 = (int)(Math.round(height6 * 0.06));
    int height9 = (int)(Math.round(height6 * 0.94));
    int height10 = (int)(Math.round(height5 * 0.08)); 
    int height11 = (int)(Math.round(height5 * 0.92));     
	int width1 = (int)(Math.round(mainwidth * 0.01)); 
	int width2 = (int)(Math.round(mainwidth * 0.98)); 
	int width3 = (int)(Math.round(width2 * 0.25)); 
	int width4 = (int)(Math.round(width2 * 0.75)); 
	int width5 = (int)(Math.round(width4 * 0.4)); 
	int width6 = (int)(Math.round(width3 * 0.9)); 
	int width7 = (int)(Math.round(width3 * 0.04));
	int width8 = (int)(Math.round(width3 * 0.9)); 
	int width9 = (int)(Math.round(width3 * 0.04));
	int width10 = (int)(Math.round(width3 * 0.96));
	int width11 = (int)(Math.round(width10 * 0.04)); 
	int width12 = (int)(Math.round(width10 * 0.96)); 

	JFrame mainframe, mainpanel; 
	JPanel frame_top, frame_bottom, frame_right, frame_left, frame_middle;
	JPanel center_left, center_right;
	JPanel right_panel1, right_panel2, right_panel3, right_panel4, right_panel5, right_panel6;
	JPanel box1, box2, box3, box4, box5; 
	JPanel left_panel1,left_panel2; 
	JPanel space1, space2; 
	JPanel comboHold1, comboHold2;
	JPanel westA, westB;
	
	Border loweredbevel;
	TitledBorder titleborder1, titleborder2, titleborder3, titleborder4, titleborder5, titleborder6, titleborder7, titleborder8; //NIR2
	
	JMenuBar menuBar; 
	JMenu menu, menu2;
	JMenuItem menuItem1; 
	JMenuItem itemRed, itemBlue, itemGreen;
	JMenuItem menuItemA, menuItem3;
	JCheckBoxMenuItem r1, r2, r3, r4, r5, r6;
	JCheckBoxMenuItem g1, g2, g3, g4, g5, g6; 
	JCheckBoxMenuItem b1, b2, b3, b4, b5, b6;
	
	JFileChooser filechooser; 
	
	File selectedDir, selectedfile, file; 
	File image1, image2, image3, image4, image5, image6; 
	File redImage, greenImage, blueImage;
	
	ImagePreview previewpanel;
	
	String[] nameCheck = new String[200]; 
	String[] imageName = new String[6]; 
	String[] bandsRed = {"Select R","NIR(800nm)", "BLUE(490nm)", "GREEN(550nm)", "RED(680nm)", "RED EDGE(720nm)", "NIR2(900nm)" };
	String[] bandsBlue = {"Select B","NIR(800nm)", "BLUE(490nm)", "GREEN(550nm)", "RED(680nm)", "RED EDGE(720nm)", "NIR2(900nm)" };
	String[] bandsGreen = {"Select G","NIR(800nm)", "BLUE(490nm)", "GREEN(550nm)", "RED(680nm)", "RED EDGE(720nm)", "NIR2(900nm)" };
	String img1, img2, img3, img4, img5, img6;
	String namePattern;
	String selectedRed, selectedGreen, selectedBlue;
	String band1, band2, band3, band4, band5, band6, band7;
	String reds,greens,blues;
	
	static Image image; 
	Image imageLoad, imageblue, imageRedEdge, imageNir2, imageNir, imageRed, imageGreen;
	ImageIcon icon, iconf; 
	
	JLabel jlabel;
	JLabel red, blue, green;
	
	Boolean existence1 = false;
	Boolean existence2 = false;
	Boolean existence3 = false;
	Boolean existence4 = false;
	Boolean existence5 = false;
	Boolean existence6 = false;
	Boolean flagr, flagg, flagb = false;
	
	JButton okbutton;
	
	int indexflag = 0; 
	
	String desktoppath = System.getProperty("user.home") + "\\Desktop";
	String path = desktoppath + "\\Outputs";
	File directory = new File(path);
	
	String path2 = desktoppath + "\\Index";
	File directory2 = new File(path2);
	
	JTextField field_ndvi, field_sr, field_rendi, field_gndvi;
	
	
	/** This section decodes the bytes of the tiff images and load it into buffered images **/
	static Image load(byte[] data) throws Exception{ /*Loads tiff*/
	    image = null;
	    SeekableStream stream = new ByteArraySeekableStream(data);
	    String[] names = ImageCodec.getDecoderNames(stream);
	    ImageDecoder dec =  ImageCodec.createImageDecoder(names[0], stream, null);
	    RenderedImage im = dec.decodeAsRenderedImage();
	    image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
	    return image;
	  }
	
	private static JFrame ndviFrame = null;
	private static JFrame srFrame = null;
	private static JFrame reFrame = null;
	private static JFrame gndviFrame = null;
	private static JFrame aboutFrame = null;
	private static JFrame loadFrame = null;
	private static JFrame compareFrame = null;
	
	/**Return as buffered image**/
	public BufferedImage getBufferedImage(File file) throws IOException{
		FileSeekableStream stream1 = new FileSeekableStream(file);
		TIFFDecodeParam decodeParam1 = new TIFFDecodeParam();
		decodeParam1.setDecodePaletteAsShorts(true);
		ParameterBlock params1 = new ParameterBlock();
		params1.add(stream1);
		RenderedOp imager = JAI.create("tiff", params1);
		return imager.getAsBufferedImage();
	}
	
	/**Read the file in buffer**/
	public ByteBuffer getBuffer(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		FileChannel imgChannel = fis.getChannel();
		ByteBuffer bytebuffer = ByteBuffer.allocate((int)imgChannel.size());
		imgChannel.read(bytebuffer);
		return bytebuffer;
	}
	
	public start(){
		JFrame mainframe = new JFrame();	
		JPanel mainpanel = new JPanel(new BorderLayout()); 
		JPanel frame_bottom = new JPanel(); 
		JPanel frame_right = new JPanel();
		JPanel frame_left = new JPanel();
		JPanel frame_middle = new JPanel(new BorderLayout()); 
		frame_left.setPreferredSize(new Dimension(width1, height2)); 
		frame_right.setPreferredSize(new Dimension(width1, height2));
		frame_bottom.setPreferredSize(new Dimension(mainwidth, height3)); 
		frame_middle.setPreferredSize(new Dimension(width2,height2));
		JPanel center_left = new JPanel(new GridLayout(2,1)); 
		JPanel center_right = new JPanel(new GridLayout(2,3));
		center_left.setPreferredSize(new Dimension(width3,height2)); 
		center_right.setPreferredSize(new Dimension(width4, height2)); 
		final JPanel right_panel1 = new JPanel(new BorderLayout());	
		JPanel right_panel2 = new JPanel(new BorderLayout()); 
		JPanel right_panel3 = new JPanel(new BorderLayout()); 
		JPanel right_panel4 = new JPanel(new BorderLayout()); 
		JPanel right_panel5 = new JPanel(new BorderLayout());
		JPanel right_panel6 = new JPanel(new BorderLayout());
		final Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		
		/*NIR 2*/
		final JPanel box1 = new JPanel(); 
		box1.setPreferredSize(new Dimension(width5, height4)); 
		titleborder1 = BorderFactory.createTitledBorder(loweredbevel, "Near Infrared 2 Band (900 nm)");
		titleborder1.setTitlePosition(TitledBorder.ABOVE_TOP);
		titleborder1.setTitleColor(Color.black);
		box1.setBorder(titleborder1);
		right_panel1.add(box1, BorderLayout.CENTER); 
		
		/*NIR*/
		final JPanel box2 = new JPanel();
		box2.setPreferredSize(new Dimension(width5, height4)); 
		titleborder2 = BorderFactory.createTitledBorder(loweredbevel, "Near Infrared Band (800 nm)");
		titleborder2.setTitlePosition(TitledBorder.ABOVE_TOP);
		box2.setBorder(titleborder2);
		right_panel2.add(box2, BorderLayout.CENTER); 
		
		/*RED EDGE*/
		final JPanel box3 = new JPanel(); 
		box3.setPreferredSize(new Dimension(width5, height4)); 
		titleborder3 = BorderFactory.createTitledBorder(loweredbevel, "Red Edge Band (720 nm)");
		titleborder3.setTitlePosition(TitledBorder.ABOVE_TOP);
		box3.setBorder(titleborder3);
		right_panel3.add(box3, BorderLayout.CENTER);
		
		/*RED*/
		final JPanel box6 = new JPanel(); 
		box6.setPreferredSize(new Dimension(width5, height4)); 
		titleborder6 = BorderFactory.createTitledBorder(loweredbevel, "Red Band (680 nm)" );
		titleborder6.setTitlePosition(TitledBorder.ABOVE_TOP);
		box6.setBorder(titleborder6);
		right_panel4.add(box6, BorderLayout.CENTER);
		
		/*GREEN*/
		final JPanel box7 = new JPanel();
		box7.setPreferredSize(new Dimension(width5, height4)); 
		titleborder7 = BorderFactory.createTitledBorder(loweredbevel, "Green Band (550 nm)" );
		titleborder7.setTitlePosition(TitledBorder.ABOVE_TOP);
		box7.setBorder(titleborder7);
		right_panel5.add(box7, BorderLayout.CENTER);
		
		/*BLUE*/
		final JPanel box8 = new JPanel(); 
		box8.setPreferredSize(new Dimension(width5, height4)); 
		titleborder8 = BorderFactory.createTitledBorder(loweredbevel, "Blue Band (490 nm)");
		titleborder8.setTitlePosition(TitledBorder.ABOVE_TOP);
		box8.setBorder(titleborder8);
		right_panel6.add(box8, BorderLayout.CENTER);
		
		//panels for outputs
		JPanel left_panel1 = new JPanel(new BorderLayout());
		JPanel left_panel2 = new JPanel(new BorderLayout());
		left_panel1.setPreferredSize(new Dimension(width3, height5));
		left_panel2.setPreferredSize(new Dimension(width3, height6));
		
		/*COMPOSITE*/
		JPanel box5 = new JPanel(); 
		box5.setPreferredSize(new Dimension(width6, height7)); 
		
		JPanel comboHold1 = new JPanel(new BorderLayout()); 
		comboHold1.setPreferredSize(new Dimension(width10,height10));
		left_panel1.add(comboHold1, BorderLayout.PAGE_START);
		
		JPanel combobut = new JPanel(new BorderLayout());
		combobut.setPreferredSize(new Dimension(width12, height10));
		comboHold1.add(combobut, BorderLayout.CENTER);
		
		JPanel comboHold1a = new JPanel(new GridLayout(1,3));
		combobut.add(comboHold1a, BorderLayout.CENTER);
		final JButton okbutton = new JButton("OK");
		combobut.add(okbutton, BorderLayout.LINE_END);
		JPanel comboHold1b = new JPanel(); 
		comboHold1b.setPreferredSize(new Dimension(width11,height10));
		comboHold1.add(comboHold1b, BorderLayout.LINE_END);
		
		final JComboBox rlist = new JComboBox(bandsRed); 
		final JComboBox glist = new JComboBox(bandsGreen); 
		final JComboBox blist = new JComboBox(bandsBlue); 
		rlist.setPreferredSize(new Dimension(width8,height8));
		glist.setPreferredSize(new Dimension(width8,height8));
		blist.setPreferredSize(new Dimension(width8,height8)); 
		
		comboHold1a.add(rlist); 
		comboHold1a.add(glist); 
		comboHold1a.add(blist); 
		
		rlist.setEnabled(false);
		glist.setEnabled(false);
		blist.setEnabled(false);
		okbutton.setEnabled(false);
	
		final JPanel westA = new JPanel(); 
		westA.setPreferredSize(new Dimension(width10,height11)); 
		westA.setBorder(loweredbevel);
		left_panel1.add(westA, BorderLayout.CENTER); 
		
		/*FALSE COLORED*/
		final JPanel box4 = new JPanel(); 
		box4.setPreferredSize(new Dimension(width6, height7));
		
		JPanel comboHold2 = new JPanel(new BorderLayout()); 
		comboHold2.setPreferredSize(new Dimension(width10,height10));
		left_panel2.add(comboHold2, BorderLayout.PAGE_START);
		
		JPanel comboHold2a = new JPanel(); 
		comboHold2a.setPreferredSize(new Dimension(width12,height10)); 
		comboHold2.add(comboHold2a, BorderLayout.CENTER);
		
		JPanel comboHold2b = new JPanel();
		comboHold2b.setPreferredSize(new Dimension(width11,height10)); 
		comboHold2.add(comboHold2b, BorderLayout.LINE_END); 
		
		JLabel fc = new JLabel("False Colored");
		comboHold2a.add(fc);
		fc.setPreferredSize(new Dimension(width8, height8));
		left_panel2.add(comboHold2, BorderLayout.PAGE_START);  
		
		final JPanel westB = new JPanel();
		westB.setPreferredSize(new Dimension(width8,height9)); 
		westB.setBorder(loweredbevel); 	
		left_panel2.add(westB, BorderLayout.CENTER); 
		
		/*wall separator*/
		JPanel space1 = new JPanel(); 
		space1.setPreferredSize(new Dimension(width9,height7)); 
		left_panel1.add(space1, BorderLayout.LINE_END);
		
		JPanel space2 = new JPanel();
		space2.setPreferredSize(new Dimension(width7,height7)); 
		
		left_panel2.add(space2, BorderLayout.LINE_END);
		
		/*MENU*/
		JMenuBar menubar = new JMenuBar();
		
		/*TOOLS SECTION*/
		JMenu menu2 = new JMenu("Tools");	//Create menu 
		
		final JMenuItem toolload = new JMenuItem(new AbstractAction("RGB Image"){

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				
				FileNameExtensionFilter loadfilter = new FileNameExtensionFilter("Image Files", "jpg","jpeg", "png"); 
				fc.setFileFilter(loadfilter); 
				fc.setAcceptAllFileFilterUsed(false); //disables the user to select all the files
					
				ImagePreview2 previewpanel = new ImagePreview2(); //creates the panel for previewing thumbnails
				previewpanel.setPreferredSize(new Dimension(100,50)); //set size
				fc.setAccessory(previewpanel); //Add to the filechooser
				fc.addPropertyChangeListener(previewpanel); //add listener
				  
		        JFrame window=new JFrame();
		        window.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		        
		        int result = fc.showOpenDialog(window);
		        if (result == JFileChooser.APPROVE_OPTION) {
		        	File file = fc.getSelectedFile();
		        	if(fc.equals(null)){
		        		return;
					}
						
					if(!(loadFrame==null)){
						loadFrame.removeAll();
						loadFrame.dispose();
					}
						
		            try{	
						ByteBuffer bytebuffer = getBuffer(file);
		            	imageLoad = load(bytebuffer.array()); //load as buffered image
		            } catch (Exception e2) {
						e2.printStackTrace();
		            }	
		              
		            if(loadFrame == null){
						new Thread(new Runnable() {
							public void run() {
								try{
									loadFrame = new loadImage(imageLoad);
									loadFrame.addWindowListener(new WindowListener() {
											
										public void windowOpened(WindowEvent arg0) {}
										public void windowIconified(WindowEvent arg0) {}
										public void windowDeiconified(WindowEvent arg0) {}
										public void windowDeactivated(WindowEvent arg0) {}
										public void windowClosing(WindowEvent arg0) {}
										public void windowClosed(WindowEvent arg0) {
											loadFrame = null;
										}
										public void windowActivated(WindowEvent arg0) {}
									});
								}catch(IOException e){
										e.printStackTrace();
								}	
							}
						}).run();
					}
		     }else{
		        return;
		     }
			}
		});
		
		menu2.add(toolload);	//Add to the menu
		toolload.setMnemonic(KeyEvent.VK_O);
		menu2.addSeparator();
		toolload.setEnabled(false);
		
		final JMenuItem tool1 = new JMenuItem("NDVI Image");
		menu2.add(tool1);	//Add to the menu
		tool1.setMnemonic(KeyEvent.VK_N);
		tool1.setEnabled(false);
		
		final JMenuItem tool2 = new JMenuItem("SR Image");
		menu2.add(tool2);	//Add to the menu
		tool2.setMnemonic(KeyEvent.VK_S);
		tool2.setEnabled(false);
		
		final JMenuItem tool3 = new JMenuItem("RENDVI Image");
		menu2.add(tool3);	//Add to the menu
		tool3.setMnemonic(KeyEvent.VK_R);
		tool3.setEnabled(false);
		
		final JMenuItem tool4 = new JMenuItem("GNDVI Image");
		menu2.add(tool4);//Add to the menu
		tool4.setMnemonic(KeyEvent.VK_G);
		tool4.setEnabled(false);
		
		final JMenuItem toolcompare = new JMenuItem(new AbstractAction("Compare"){

			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				
				FileNameExtensionFilter loadfilter = new FileNameExtensionFilter("TIFF Files", "tiff", "tif", "TIFF", "TIF"); 
				fc.setFileFilter(loadfilter); 
				fc.setAcceptAllFileFilterUsed(false); //disables the user to select all the files
					
				ImagePreview2 previewpanel = new ImagePreview2(); //creates the panel for previewing thumbnails
				previewpanel.setPreferredSize(new Dimension(100,50)); //set size
				fc.setAccessory(previewpanel); //Add to the filechooser
				fc.addPropertyChangeListener(previewpanel); //add listener
				  
		        JFrame window=new JFrame();
		       
		        window.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
		        int result = fc.showOpenDialog(window);
		        if (result == JFileChooser.APPROVE_OPTION) {
		        	File file = fc.getSelectedFile();
		        	if(fc.equals(null)){
		        		return;
					}
						
					if(!(compareFrame==null)){
						compareFrame.removeAll();
						compareFrame.dispose();
					}
						
		            try{	
						ByteBuffer bytebuffer = getBuffer(file);
		            	imageLoad = load(bytebuffer.array()); //load as buffered image
		            } catch (Exception e2) {
						e2.printStackTrace();
		            }	
		              
		            if(compareFrame == null){
						new Thread(new Runnable() {
							public void run() {
								try {
									compareFrame = new showCompare(imageLoad);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								compareFrame.addWindowListener(new WindowListener() {
										
									public void windowOpened(WindowEvent arg0) {}
									public void windowIconified(WindowEvent arg0) {}
									public void windowDeiconified(WindowEvent arg0) {}
									public void windowDeactivated(WindowEvent arg0) {}
									public void windowClosing(WindowEvent arg0) {}
									public void windowClosed(WindowEvent arg0) {
										compareFrame = null;
									}
									public void windowActivated(WindowEvent arg0) {}
								});	
							}
						}).run();
					}
		     }else{
		        return;
		     }
			}
		});
		menu2.addSeparator(); //Add separator
		menu2.add(toolcompare);	//Add to the menu
		toolcompare.setMnemonic(KeyEvent.VK_O);
		toolcompare.setEnabled(false);
		
		
		JMenu menu = new JMenu("File");	//Create menu 
		menu.setMnemonic(KeyEvent.VK_F);
		menubar.add(menu);	//Add it to the menubar
		
		final JFileChooser filechooser = new JFileChooser(); //create the filechooser
		
		FileNameExtensionFilter imgfilter = new FileNameExtensionFilter("TIFF Files", "tiff","tif"); //it can only filter .tif or .tiff files
		filechooser.setFileFilter(imgfilter); //allows only tiff 
		filechooser.setAcceptAllFileFilterUsed(false); //disables the user to select all the files
		
		final ImagePreview previewpanel = new ImagePreview(); //creates the panel for previewing thumbnails
		previewpanel.setPreferredSize(new Dimension(100,50)); //set size
		filechooser.setAccessory(previewpanel); //Add to the filechooser
		filechooser.addPropertyChangeListener(previewpanel); //add listener

		/*vegetation index values */
		frame_bottom = new JPanel(new BorderLayout());
		frame_bottom.setPreferredSize(new Dimension(mainwidth,height3));
		
		int w1 = (int)(Math.round(mainwidth * 0.47)); 
		int w2 = (int)(Math.round(mainwidth * 0.2)); 
		int w3 = (int)(Math.round(mainwidth * 0.33));
		int w4 = (int)(Math.round(w3 * 0.3)); 
		int w5 = (int)(Math.round(w3 * 0.7)); 
		
		JPanel statusright = new JPanel(new GridLayout(1,4));
		JPanel statusmleft = new JPanel(new BorderLayout());
		JPanel statusleft1 = new JPanel(new GridLayout(1,2)); 
		JPanel statusleft = new JPanel(new GridLayout(1,6)); 
		JPanel statusmid = new JPanel(new GridLayout(1,8));
		
		statusright.setPreferredSize(new Dimension(w2,height3));
		statusmid.setPreferredSize(new Dimension(w1,height3));
		statusmleft.setPreferredSize(new Dimension(w3,height3));
		statusleft1.setPreferredSize(new Dimension(w4, height3));
		statusleft.setPreferredSize(new Dimension(w5, height3));
		
		statusright.setBorder(loweredbevel);
		statusmid.setBorder(loweredbevel);
		statusleft1.setBorder(loweredbevel);
		statusleft.setBorder(loweredbevel);
		
		statusmleft.add(statusleft1, BorderLayout.LINE_START);
		statusmleft.add(statusleft, BorderLayout.CENTER);
		frame_bottom.add(statusmleft, BorderLayout.LINE_START);
		frame_bottom.add(statusmid, BorderLayout.CENTER);
		
		//Intensity
		JLabel label_i = new JLabel("Intensity: ");
		final JLabel intensity = new JLabel();
		statusleft1.add(label_i);
		statusleft1.add(intensity);
		
		//R
		JLabel label_r = new JLabel("R: ");
		final JLabel field_r = new JLabel();
		statusleft.add(label_r);
		statusleft.add(field_r);
		
		//G
		JLabel label_g = new JLabel("G: ");
		final JLabel field_g = new JLabel();
		statusleft.add(label_g);
		statusleft.add(field_g);
		
		//B
		JLabel label_b = new JLabel("B: ");
		final JLabel field_b = new JLabel();
		statusleft.add(label_b);
		statusleft.add(field_b);
		
		//SR
		JLabel label_sr = new JLabel("SR: ");
		final JLabel field_sr = new JLabel();
		statusmid.add(label_sr);
		statusmid.add(field_sr);

		//NDVI
		JLabel label_ndvi = new JLabel("NDVI: ");
		final JLabel field_ndvi = new JLabel();
		statusmid.add(label_ndvi);
		statusmid.add(field_ndvi);
		
		//RENDVI
		JLabel label_rendi = new JLabel("RENDVI: ");
		final JLabel field_rendi = new JLabel();
		statusmid.add(label_rendi);
		statusmid.add(field_rendi);
		
		//GNDVI
		JLabel label_gndvi = new JLabel("GNDVI: ");
		final JLabel field_gndvi = new JLabel();
		statusmid.add(label_gndvi);
		statusmid.add(field_gndvi);
				
		//buttons for creating vegetation indices
		final JButton bi_compare = new JButton("Compare");
		final JButton bi_sr = new JButton("SR"); 
		final JButton bi_ndvi = new JButton("NDVI"); 
		final JButton bi_rendvi = new JButton("RENDVI");
		final JButton bi_gndvi = new JButton("GNDVI");
		bi_compare.setForeground(Color.white);
		bi_compare.setBackground(Color.lightGray);
		bi_sr.setForeground(Color.white);
		bi_sr.setBackground(Color.lightGray);
		bi_ndvi.setForeground(Color.white);
		bi_ndvi.setBackground(Color.lightGray);
		bi_rendvi.setForeground(Color.white);
		bi_rendvi.setBackground(Color.lightGray);
		bi_gndvi.setForeground(Color.white);
		bi_gndvi.setBackground(Color.lightGray);
		JPanel frame_top = new JPanel(new BorderLayout());
		frame_top.setPreferredSize(new Dimension(mainwidth,height1));
		JPanel topcenter = new JPanel();
		JPanel topright = new JPanel();
		int wc1 = (int)(Math.round(mainwidth * 0.74)); 
		int wc2 = (int)(Math.round(mainwidth * 0.26)); 
		topcenter.setPreferredSize(new Dimension(wc1,height1));
		topright.setPreferredSize(new Dimension(wc2,height1));
		frame_top.add(topcenter, BorderLayout.CENTER);
		frame_top.add(topright,BorderLayout.LINE_END);
		
		topright.add(bi_sr);
		topright.add(bi_ndvi);
		topright.add(bi_rendvi);
		topright.add(bi_gndvi);
		
		//disable index viewer buttons at first
		bi_compare.setEnabled(false);
		bi_sr.setEnabled(false);
		bi_ndvi.setEnabled(false);
		bi_rendvi.setEnabled(false);
		bi_gndvi.setEnabled(false);
		
		JMenuItem menuItem1 = new JMenuItem(new AbstractAction("Select Image"){
			
			public void actionPerformed(ActionEvent e) {
				JFrame window=new JFrame();
			    window.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
				int result = filechooser.showOpenDialog(window);
				if(result == JFileChooser.APPROVE_OPTION){ //Choose file
					
					if(result == JFileChooser.APPROVE_OPTION){ //Clears the fc panel when an image is selected
						westA.removeAll();
						westA.repaint();
					}
					
					selectedfile = filechooser.getSelectedFile(); //get file
					if(selectedfile.equals(null)){
						return;
					}
					
					if(!(compareFrame==null)){
						compareFrame.removeAll();
						compareFrame.dispose();
					}
					bi_compare.setEnabled(true);
					
					if(!(ndviFrame==null)){
						ndviFrame.removeAll();
						ndviFrame.dispose();
					}
					bi_ndvi.setEnabled(true);
					
					if(!(srFrame==null)){
						srFrame.removeAll();
						srFrame.dispose();
					}
					bi_sr.setEnabled(true);
					
					if(!(reFrame==null)){
						reFrame.removeAll();
						reFrame.dispose();
					}
					bi_rendvi.setEnabled(true);
					
					if(!(gndviFrame==null)){
						gndviFrame.removeAll();
						gndviFrame.dispose();
					}
					bi_gndvi.setEnabled(true);
					
					String selectedFileName = selectedfile.getName(); //get the name of the selected  file
					String nameStart = selectedFileName.substring(0, 3); //Gets string "TTC"
					
					if(nameStart.equals("TTC")){ //Check if it starts with TTC
						
						selectedDir = new File(filechooser.getSelectedFile().getParent()); //get directory of selected file
						
						/*Creating file names*/
						namePattern = selectedFileName.substring(4);    //Remove TTC* from the file name                                                                                                                                                                                                                                                                 
	
						for(int i=0; i<6; i++){
							imageName[i] = "TTC" + Integer.toString(i) + namePattern;//Form the 6 file names
																					//this will yield TTC0*, TTC1*,TTC2*,TTC3*,TTC4*,TTC5*
							img1 = imageName[0];
							img2 = imageName[1];
							img3 = imageName[2];
							img4 = imageName[3];
							img5 = imageName[4];
							img6 = imageName[5];
						}
						
						/* image files */
						image1 = new File(selectedDir + "\\" + img1);  //TTC0 -NIR 
						image2 = new File(selectedDir + "\\" + img2);  //TTC1 -BLUE
						image3 = new File(selectedDir + "\\" + img3);  //TTC2 -GREEN
						image4 = new File(selectedDir + "\\" + img4);  //TTC3 -RED
						image5 = new File(selectedDir + "\\" + img5);  //TTC4 -RED EDGE
						image6 = new File(selectedDir + "\\" + img6);  //TTC5 -NIR2
						
						/*Check if the files exist in the directory*/
						existence1 = image1.exists(); //if exists
						existence2 = image2.exists(); //if exists
						existence3 = image3.exists(); //if exists
						existence4 = image4.exists(); //if exists
						existence5 = image5.exists(); //if exists
						existence6 = image6.exists(); //if exists
						
						
						if((existence1 == true) && (existence2 == true)  && (existence3 == true) && (existence4 == true) && (existence5 == true) && (existence6 == true)){ //if images are complete
						
							//load image into buffered image then convert again to tiff
							try {	//NIR 2
								
								ByteBuffer bytebuffer = getBuffer(image6);
			            		 
								imageNir2 = load(bytebuffer.array()); //load as buffered image
								  
								Image imageScaled = imageNir2.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled);
								JLabel jlabel = new JLabel(icon);
									
								box1.removeAll(); //if an image already in panel, remove it
								box1.add(jlabel); //add the new image
								box1.revalidate(); //refresh 
										
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}
	
									public void mouseMoved(MouseEvent arg0) {
										int rgbs = ((BufferedImage) imageNir2).getRGB(arg0.getX(),arg0.getY());
							            Color color = new Color((int) rgbs, true);
							                   
							            int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
							            intensity.setText(Float.toString(val_ave));
									}
								});
								 
							} catch (Exception e1) {
								e1.printStackTrace();
							}			
						
							try { //NIR
								
								ByteBuffer bytebuffer = getBuffer(image1);
								imageNir = load(bytebuffer.array());  //load as buffered image
								  
								Image imageScaled = imageNir.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled); 
								JLabel jlabel = new JLabel(icon);
									
								box2.removeAll(); //if an image already in panel, remove it
								box2.add(jlabel); //add the new image
								box2.revalidate(); //refresh 
								
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}

									public void mouseMoved(MouseEvent arg0) {
										int rgbs = ((BufferedImage) imageNir).getRGB(arg0.getX(),arg0.getY());
						                   Color color = new Color((int) rgbs, true);
						                   
						                   int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
						                   intensity.setText(Float.toString(val_ave));
									}
								});
							} catch (Exception e2) {
								e2.printStackTrace();
							}		
							
							try { //RED EDGE
								
								ByteBuffer bytebuffer = getBuffer(image5);
								imageRedEdge = load(bytebuffer.array());  //load as buffered images
								  
								Image imageScaled = imageRedEdge.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled);
								JLabel jlabel = new JLabel(icon);
									
								box3.removeAll(); //if an image already in panel, remove it
								box3.add(jlabel); //add the new image
								box3.revalidate(); //refresh 
										
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}

									public void mouseMoved(MouseEvent arg0) {
										int rgbs = ((BufferedImage) imageRedEdge).getRGB(arg0.getX(),arg0.getY());
						                   Color color = new Color((int) rgbs, true);
						                   
						                   int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
						                   intensity.setText(Float.toString(val_ave));
									}
								});
							} catch (Exception e3) {
								e3.printStackTrace();
							}	

							try { //RED 
								
								ByteBuffer bytebuffer = getBuffer(image4);
								imageRed = load(bytebuffer.array());  //load as buffered image
								  
								Image imageScaled = imageRed.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled); 
								JLabel jlabel = new JLabel(icon);
									
								box6.removeAll(); //if an image already in panel, remove it
								box6.add(jlabel); //add the new image
								box6.revalidate(); //refresh 
								 
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}

									public void mouseMoved(MouseEvent arg0) {
										int rgbs = ((BufferedImage) imageRed).getRGB(arg0.getX(),arg0.getY());
										Color color = new Color((int) rgbs, true);
						                   
						                int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
						                intensity.setText(Float.toString(val_ave));
									}
								});
										
							} catch (Exception e4) {
								e4.printStackTrace();
							}	
							
							try { //GREEN
								
								ByteBuffer bytebuffer = getBuffer(image3);
								imageGreen = load(bytebuffer.array());  //load as buffered image
								  
								Image imageScaled = imageGreen.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled); 
								JLabel jlabel = new JLabel(icon);
									
								box7.removeAll(); //if an image already in panel, remove it
								box7.add(jlabel); //add the new image
								box7.revalidate(); //refresh 
								 
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}

									public void mouseMoved(MouseEvent arg0) {
										 int rgbs = ((BufferedImage) imageGreen).getRGB(arg0.getX(),arg0.getY());
						                 Color color = new Color((int) rgbs, true);
						                   
						                 int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
						                 intensity.setText(Float.toString(val_ave));
									}
								});
								
							} catch (Exception e4) {
								e4.printStackTrace();
							}	
							
							try { //BLUE
								
								ByteBuffer bytebuffer = getBuffer(image2);
								imageblue = load(bytebuffer.array());  //load as buffered images
								  
								Image imageScaled = imageblue.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								ImageIcon icon = new ImageIcon(imageScaled);
								JLabel jlabel = new JLabel(icon);
									
								box8.removeAll(); //if an image already in panel, remove it
								box8.add(jlabel); //add the new image
								box8.revalidate(); //refresh 
								 
								jlabel.addMouseMotionListener(new MouseMotionListener(){

									public void mouseDragged(MouseEvent arg0) {}

									public void mouseMoved(MouseEvent arg0) {
										 int rgbs = ((BufferedImage) imageblue).getRGB(arg0.getX(),arg0.getY());
						                 Color color = new Color((int) rgbs, true);
						                   
						                 int val_ave = (color.getRed() + color.getGreen() + color.getBlue())/3;
						                 intensity.setText(Float.toString(val_ave));
									}
								});
										
							} catch (Exception e4) {
								e4.printStackTrace();
							}	
							
							directory.mkdir(); //Create storage of images to be produced
							
							/*SECTION FOR CREATING COMPOSITE IMAGES*/
							
							rlist.setEnabled(true); //enable combo list for red	
							blist.setEnabled(true); //enable combo list for blue
							glist.setEnabled(true); //enable combo list for green
							okbutton.setEnabled(true);
							
							
							
							ActionListener selectedAction = new ActionListener(){

								public void actionPerformed(ActionEvent arg0) {
									if(arg0.getSource().equals(okbutton)){ //ok button is selected
										if(rlist.getSelectedIndex() == 0 || glist.getSelectedIndex() == 0 || blist.getSelectedIndex() == 0){ //notify if selection of bands is incomplete
											JOptionPane.showMessageDialog(new JFrame(), "Incomplete selected bands","", JOptionPane.INFORMATION_MESSAGE); 
										}
										else{//else complete
											//set the images for each index selected 
											
											if(rlist.getSelectedIndex() == 1){
												redImage = image1;
												reds = "800";
											} else if(rlist.getSelectedIndex() == 2){
												redImage = image2;
												reds = "490";
											} else if(rlist.getSelectedIndex() == 3){
												redImage = image3;
												reds = "550";
											} else if(rlist.getSelectedIndex() == 4){
												redImage = image4;
												reds = "680";
											} else if(rlist.getSelectedIndex() == 5){
												redImage = image5;
												reds = "720";
											} else if(rlist.getSelectedIndex() == 6){
												redImage = image6;
												reds = "900";
											}
											
											if(glist.getSelectedIndex() == 1){
												greenImage = image1;
												greens = "800";
											} else if(glist.getSelectedIndex() == 2){
												greenImage = image2;
												greens = "490";
											} else if(glist.getSelectedIndex() == 3){
												greenImage = image3;
												greens = "550";
											} else if(glist.getSelectedIndex() == 4){
												greenImage = image4;
												greens = "680";
											} else if(glist.getSelectedIndex() == 5){
												greenImage = image5;
												greens = "720";
											} else if(glist.getSelectedIndex() == 6){
												greenImage = image6;
												greens = "900";
											}
											
											if(blist.getSelectedIndex() == 1){
												blueImage = image1;
												blues = "800";
											} else if(blist.getSelectedIndex() == 2){
												blueImage = image2;
												blues = "490";
											} else if(blist.getSelectedIndex() == 3){
												blueImage = image3;
												blues = "550";
											} else if(blist.getSelectedIndex() == 4){
												blueImage = image4;
												blues = "680";
											} else if(blist.getSelectedIndex() == 5){
												blueImage = image5;
												blues = "720";
											} else if(blist.getSelectedIndex() == 6){
												blueImage = image6;
												blues = "900";
											}
											
											try {
							            		  //Read the red, green and blue band as buffered image
												BufferedImage bufferRed = getBufferedImage(redImage);
							            		BufferedImage bufferGreen = getBufferedImage(greenImage);
							            		BufferedImage bufferBlue = getBufferedImage(blueImage);
							            		 
							            		  //get dimensions
							            		int imgwidth = bufferGreen.getWidth();
							            		int imgheight = bufferGreen.getHeight();
							            		  
							            		  //iterate over the values
							            		for(int y=0; y<imgheight; y++){
							            			for(int x=0; x<imgwidth;x++){
					
							            				  //get RGB values
							            				Color c1 = new Color( bufferRed.getRGB(x, y)); 
							            				Color c2 = new Color( bufferGreen.getRGB(x, y)); 
							            				Color c3 = new Color( bufferBlue.getRGB(x, y)); 
							            				   
							            	            int red = (int)(c1.getRed()); //Get red values 
							            	            int green = (int)(c2.getGreen());//Get green values
							            	            int blue = (int)(c3.getBlue());//Get blue values
							            	            Color newColor = new Color(red,green,blue); //new set of r,g and b values
							            	               
							            	            bufferGreen.setRGB(x,y,newColor.getRGB()); //update image date
							            	          }
							            		  }
							            		 
							            		  //write the false colored image
							            		  TIFFEncodeParam    params=   new TIFFEncodeParam();
							            		  FileOutputStream   os    =   new FileOutputStream(directory + "\\TTC0" + namePattern + "(" + reds +"-" + greens + "-" + blues +")"+".tif"); 
							            		  javax.media.jai.JAI.create("encode", bufferGreen, os, "TIFF", params);
							            		  os.close();
										        	
											} catch (Exception e8) {
												// TODO Auto-generated catch block
												e8.printStackTrace();
											}
											
											try { //Displaying false colored to jpanel
												
												File filename = new File(directory + "\\TTC0" + namePattern + "(" + reds +"-" + greens + "-" + blues +")"+".tif"); 
												ByteBuffer bytebuffer = getBuffer(filename);
												final Image image = load(bytebuffer.array());  //load as buffered images
												
												Image imageScaled = image.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
												final ImageIcon icon = new ImageIcon(imageScaled);
												JLabel jlabel = new JLabel(icon);
												
												westA.removeAll(); //if an image already in panel, remove it
												westA.add(jlabel); //add the new image
												westA.revalidate(); //refresh 
												
											} catch (Exception e9) {
												// TODO Auto-generated catch block
												e9.printStackTrace();
											}	
										}
									}
								}
							};
							
							rlist.addActionListener(selectedAction);
							glist.addActionListener(selectedAction);
							blist.addActionListener(selectedAction);
							okbutton.addActionListener(selectedAction);
							
							//westA.removeAll();
							try{ //create false colored nir-red-green
								
								 //Read the red, green and blue band as buffered image
			            		 BufferedImage bufferRed = getBufferedImage(image1);
			            		 BufferedImage bufferGreen = getBufferedImage(image4);
			            		 BufferedImage bufferBlue = getBufferedImage(image3);
			            		 
			            		 //get dimensions
			            		 int imgwidth = bufferGreen.getWidth();
			            		 int imgheight = bufferGreen.getHeight();
			            		  
			            		 //iterate over the values
			            		 for(int y=0; y<imgheight; y++){
			            			 for(int x=0; x<imgwidth;x++){
			            				 //get RGB values
			            				 Color c1 = new Color( bufferRed.getRGB(x, y)); 
			            				 Color c2 = new Color( bufferGreen.getRGB(x, y)); 
			            				 Color c3 = new Color( bufferBlue.getRGB(x, y)); 
			            				 
			            				 int nirv = (c1.getRed() + c1.getGreen() + c1.getBlue())/3;
			            				 int redv = (c2.getRed() + c2.getGreen() + c2.getBlue())/3;
			            				 int greenv = (c3.getRed() + c3.getGreen() + c3.getBlue())/3;
			            				 Color newColor = new Color(nirv,redv,greenv);
			            				 bufferGreen.setRGB(x, y, newColor.getRGB());
			            				 
			            			  }
			            		 }
			            		 
			            		 //write the false colored image
			            		 TIFFEncodeParam    params =   new TIFFEncodeParam();
			            		 FileOutputStream   os    =   new FileOutputStream(directory + "\\TTC0" + namePattern + "(NIR-RED-GREEN)" +".tif"); 
			            		 javax.media.jai.JAI.create("encode", bufferGreen, os, "TIFF", params);
			            		 os.close();
								
							}catch (Exception e10) {
								e10.printStackTrace();
							}	
							try { //Displaying false colored to jpanel
	
								File filename = new File(directory + "\\TTC0" + namePattern + "(NIR-RED-GREEN)"+".tif"); 
								ByteBuffer bytebuffer = getBuffer(filename);
								final Image fcimage = load(bytebuffer.array());  //load as buffered images
								
								Image imageScaled = fcimage.getScaledInstance(300, -1,  Image.SCALE_SMOOTH);
								       
								iconf = new ImageIcon(imageScaled);
								JLabel jlabel = new JLabel(iconf);
									
								westB.removeAll(); //if an image already in panel, remove it
								westB.add(jlabel); //add the new image
								westB.revalidate(); //refresh 
								
								jlabel.addMouseMotionListener(new MouseMotionListener(){
									 public void mouseMoved(MouseEvent e) {
										 int rgbs = ((BufferedImage) fcimage).getRGB(e.getX(),e.getY());
										 Color color = new Color((int) rgbs, true);
						                   
						                 float val_red = color.getRed(); //NIR VALUE
						                 float val_green = color.getGreen(); //RED VALUE
						                 float val_blue = color.getBlue(); //GREEN VALUE
						                   
						                 /** Display rgb values of false colored **/
							             field_r.setText(Double.toString(val_red));
							             field_g.setText(Double.toString(val_green));
							             field_b.setText(Double.toString(val_blue));
						                   
							             //get nir average
							             int nirrgbs = ((BufferedImage) imageNir).getRGB(e.getX(), e.getY() );
							             Color colornir = new Color((int) nirrgbs, true);
							             float nir_ave = (colornir.getRed() + colornir.getGreen() + colornir.getBlue())/3;
							               	
							             //get green average
							             int greenrgbs = ((BufferedImage) imageGreen).getRGB(e.getX(), e.getY() );
							             Color colorgreen = new Color((int)greenrgbs, true);
							             float green_ave = (colorgreen.getRed() + colorgreen.getGreen() + colorgreen.getBlue())/3;
							               	
							             //get red average
							             int redrgbs = ((BufferedImage) imageRed).getRGB(e.getX(), e.getY() );
							             Color colorred = new Color((int)redrgbs, true);
							             float red_ave = (colorred.getRed()+colorred.getGreen() + colorred.getBlue())/3;
						                    
							             //get blue average
						                 int bluergbs = ((BufferedImage) imageblue).getRGB(e.getX(), e.getY());
				            			 Color colorb = new Color((int)bluergbs, true);
				            			 float b_ave = (colorb.getRed() + colorb.getGreen() + colorb.getBlue())/3;
				            			   	
				            			 //get red edge average
						                 int rergbs = ((BufferedImage) imageRedEdge).getRGB(e.getX(), e.getY());
						                 Color colorre = new Color((int)rergbs, true);
						                 float re_ave = (colorre.getRed() + colorre.getGreen() + colorre.getBlue())/3;
				            			   	
						                 //get nir 2 average
						                 int nir2rgbs = ((BufferedImage) imageNir2).getRGB(e.getX(), e.getY());
				            			 Color colornir2 = new Color((int)nir2rgbs, true);
						                 float nir2_ave = (colornir2.getRed() + colornir2.getGreen() + colornir2.getBlue())/3;
						              
						                 /** COMPUTATION OF INDICES **/
						                 float value_sr = nir_ave / red_ave;
						                 field_sr.setText(Float.toString(value_sr));
						                    
						                 float value_ndvi = (nir_ave - red_ave)/(nir_ave + red_ave);
						                 field_ndvi.setText(Float.toString(value_ndvi));
						                    
						                 float value_rendi = (re_ave - red_ave)/(re_ave + red_ave);
						                 field_rendi.setText(Float.toString(value_rendi));
						                    
						                 float value_gndvi= (nir_ave - green_ave)/(nir_ave + green_ave);
						                 field_gndvi.setText(Float.toString(value_gndvi));
									 }
					
									 public void mouseDragged(MouseEvent e) {}
								 });
								
								/** This section writes the vegetation index values into images **/
								
									bi_ndvi.addActionListener(new ActionListener(){
											
										public void actionPerformed(ActionEvent arg0) {
											if(ndviFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														ndviFrame = new showNDVI(fcimage,imageRed, imageNir,  namePattern);
														ndviFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																bi_ndvi.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																bi_ndvi.setEnabled(true);
																ndviFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
												}
											}).run();
										}
									}	
								 });
									
									
								bi_sr.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(srFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														srFrame = new showSR(fcimage,namePattern);
														srFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																bi_sr.setEnabled(false);											
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																bi_sr.setEnabled(true);
																srFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
													
												}
											}).run();
										}
										
									}
								});
								
								bi_rendvi.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(reFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														reFrame = new showRENDVI(fcimage,imageRedEdge,namePattern);
														reFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																bi_rendvi.setEnabled(false);																
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																bi_rendvi.setEnabled(true);
																reFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
												}
											}).run();
										}
									}
								});
								
								bi_gndvi.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(gndviFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														gndviFrame = new showGNDVI(fcimage,namePattern);
														gndviFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																bi_gndvi.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																bi_gndvi.setEnabled(true);
																gndviFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
												}
											}).run();
										}
									}
								});
									
								//create indices
								toolload.setEnabled(true);
								toolcompare.setEnabled(true);
								tool1.setEnabled(true);
								tool1.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(ndviFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														ndviFrame = new showNDVI(fcimage, imageRed, imageNir, namePattern);
														ndviFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																tool1.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																tool1.setEnabled(true);
																ndviFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
												}
											}).run();
										}
									}
								});
								
								tool2.setEnabled(true);
								tool2.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(srFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														srFrame = new showSR(fcimage, namePattern);
														srFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																tool2.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																tool2.setEnabled(true);
																srFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
												}
											}).run();
										}		
									}
								});
								
								tool3.setEnabled(true);
								tool3.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(reFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														reFrame = new showRENDVI(fcimage,imageRedEdge, namePattern);
														reFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																tool3.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																tool3.setEnabled(true);
																reFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}	
												}
											}).run();
											}
										}
									});
								
								tool4.setEnabled(true);
								tool4.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent arg0) {
										if(gndviFrame == null){
											new Thread(new Runnable() {
												public void run() {
													try{
														gndviFrame = new showGNDVI(fcimage, namePattern);
														gndviFrame.addWindowListener(new WindowListener() {
															
															public void windowOpened(WindowEvent arg0) {
																tool4.setEnabled(false);
															}
															
															public void windowIconified(WindowEvent arg0) {}
															
															public void windowDeiconified(WindowEvent arg0) {}
															
															public void windowDeactivated(WindowEvent arg0) {}
															
															public void windowClosing(WindowEvent arg0) {}
															
															public void windowClosed(WindowEvent arg0) {
																tool4.setEnabled(true);
																gndviFrame = null;
															}
															
															public void windowActivated(WindowEvent arg0) {}
														});
													}catch(IOException e){
														e.printStackTrace();
													}
													
												}
											}).run();
										}
									}
								});
								
							} catch (Exception e11) {
								e11.printStackTrace();
							}		
						}
						else{ //if not all the images exist
							JOptionPane.showMessageDialog(new JFrame(), "Incomplete set of bands","", JOptionPane.INFORMATION_MESSAGE); //notify if not existing
						}
						
				} //end of if that check if it start with TTC
				else{	//else, no file is chosen or chooser command closed
					return;
				}
			} //end of open dialog
		}			
	});	
		
	menu.add(menuItem1);	//Add to the menu
	menuItem1.setMnemonic(KeyEvent.VK_S);
	menu.addSeparator(); //Add separator
	menubar.add(menu2);	//Add it to the menubar	
		
	menuItem3 = new JMenuItem(new AbstractAction("Exit"){ //Create menu item 3
			
		public void actionPerformed(ActionEvent arg0) {
			String ObjButtons[] = {"Yes","No"};
			int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","VIA",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
			if(PromptResult==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}); 
	menuItem3.setMnemonic(KeyEvent.VK_X);
	menu.add(menuItem3); //Add to the menu
		
	JMenu menu3 = new JMenu("About");	//Create menu 
	menu.setMnemonic(KeyEvent.VK_H);
	menubar.add(menu3);	//Add it to the menubar	
		
	menuItemA = new JMenuItem(new AbstractAction("About"){ //Create menu item 3
			
		public void actionPerformed(ActionEvent arg0) {
			if(aboutFrame == null){
				new Thread(new Runnable() {
					public void run() {
						try {
								aboutFrame = new showAbout();
							} 
						catch (IOException e) {
							e.printStackTrace();
						}
						aboutFrame.addWindowListener(new WindowListener() {
							public void windowOpened(WindowEvent arg0) {
								menuItemA.setEnabled(false);
							}
								
							public void windowIconified(WindowEvent arg0) {}
							public void windowDeiconified(WindowEvent arg0) {}
							public void windowDeactivated(WindowEvent arg0) {}
							public void windowClosing(WindowEvent arg0) {}
							public void windowClosed(WindowEvent arg0) {
								menuItemA.setEnabled(true);
								aboutFrame = null;
							}
							
							public void windowActivated(WindowEvent arg0) {}
						});
							
					}
				}).run();
			}
		}
	}); 
	menuItemA.setMnemonic(KeyEvent.VK_A);
	menu3.add(menuItemA); //Add to the menu
		
	/*Add the 2 panels at center_left*/
	center_left.add(left_panel1);
	center_left.add(left_panel2);
		
	/*Add the 4 panels at center_right*/
	center_right.add(right_panel1);
	center_right.add(right_panel2);
	center_right.add(right_panel3);
	center_right.add(right_panel4);
	center_right.add(right_panel5);
	center_right.add(right_panel6);
		
	/*Add the right and left panels of center on frame_center*/
	frame_middle.add(center_left, BorderLayout.WEST);
	frame_middle.add(center_right, BorderLayout.EAST);
	
	/* Add the large frames in mainpanel */
	mainpanel.add(frame_top, BorderLayout.PAGE_START);
	mainpanel.add(frame_bottom, BorderLayout.PAGE_END);
	mainpanel.add(frame_left, BorderLayout.LINE_START);
	mainpanel.add(frame_middle, BorderLayout.CENTER);
	mainpanel.add(frame_right, BorderLayout.LINE_END);
		
	/*main panel */
	mainframe.add(mainpanel); 	
	
	/*menu bar set*/
	mainframe.setJMenuBar(menubar);
		
	mainframe.setIconImage(new ImageIcon(start.class.getResource("/resources/leafIcon.png")).getImage());
	mainframe.setSize(mainwidth, mainheight);
	mainframe.setVisible(true); //Show frame
	mainframe.setResizable(false);
	mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close frame
	mainframe.setTitle("VIA (Vegetation Index Analyzer)");	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		start s = new start();
	}

	public void actionPerformed(ActionEvent arg0) {
	 
	}

}
