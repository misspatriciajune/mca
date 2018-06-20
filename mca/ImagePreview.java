package mca;
/*This allows showing a thumbnail or a preview of the file being selected in the jfilechooser*/

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import com.sun.media.jai.codec.ByteArraySeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
 
public class ImagePreview extends JComponent implements PropertyChangeListener {
	ImageIcon thumbnail = null; //Thumbnail of image selected
	File file = null; //File selected
	Image image; //Image for tiff

	public void loadImage() throws IOException {
		if (file == null) { //If none
			thumbnail = null;
			return;
		}
		
		 FileInputStream fis = new FileInputStream(file.getPath()); 
		 FileChannel imgChannel = fis.getChannel();
		 ByteBuffer bytebuffer = ByteBuffer.allocate((int)imgChannel.size());
		 imgChannel.read(bytebuffer);
		 Image image = load(bytebuffer.array());
		  
		 Image imageScaled = image.getScaledInstance(100, -1,  Image.SCALE_SMOOTH);
		       
		
		ImageIcon newIcon = new ImageIcon(imageScaled);
		
		if (newIcon != null) {
			
			if (newIcon.getIconWidth() > 110) { //If too big, resize
				thumbnail = new ImageIcon(newIcon.getImage().getScaledInstance(110, -1, Image.SCALE_DEFAULT));
			} 
			else { //if not
				thumbnail = newIcon;
			}
		}
	}

	private Image load(byte[] data) throws IOException { //load tiff as buffered image
		
		    image = null;
			SeekableStream stream = new ByteArraySeekableStream(data);
		    String[] names = ImageCodec.getDecoderNames(stream);
		    ImageDecoder dec =  ImageCodec.createImageDecoder(names[0], stream, null);
		    RenderedImage im = dec.decodeAsRenderedImage();
		    image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
		    return image;

	}

	public void propertyChange(PropertyChangeEvent e) {
		boolean update = false;
		String prop = e.getPropertyName();

		if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) { //Dont show image if the directory has been changed
			file = null;
			update = true;
			
		} 
		else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) { //Find the file selected
			file = (File) e.getNewValue();
			update = true;
		}
		
		if (update) { //Updating the preview image
			thumbnail = null;
			if (isShowing()) {
				try {
					loadImage();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		if (thumbnail == null) {
			try {
				loadImage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (thumbnail != null) { //position
			int x = getWidth()/2 - thumbnail.getIconWidth()/2;
			int y = getHeight()/2 - thumbnail.getIconHeight()/2;
			
			if (y < 0) {
				y = 0;
			}

		if (x < 5) {
			x = 5;
		}
		thumbnail.paintIcon(this, g, x, y);
		}
	}

}
