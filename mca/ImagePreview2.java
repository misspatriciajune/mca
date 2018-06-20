package mca;

/*This allows showing a thumbnail or a preview of the image file being selected in the jfilechooser*/

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

public class ImagePreview2 extends JComponent implements PropertyChangeListener {
	ImageIcon thumbnail = null; //Thumbnail of image selected
	Image image; 
	File f = null;

	public void loadImage() {
		if(f != null) {
			ImageIcon tmpIcon = new ImageIcon(f.getPath());
			if(tmpIcon.getIconWidth() > 90) {
			   thumbnail = new ImageIcon(
			    tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT));
			} 
			else {
				 thumbnail = tmpIcon;
			}
		}
	}

	public void propertyChange(PropertyChangeEvent e) {
		String prop = e.getPropertyName();
		if(prop == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
			f = (File) e.getNewValue();
			if(isShowing()) {
				    loadImage();
				    repaint();
			}
		}
	}

	public void paint(Graphics g) {
		if(thumbnail == null) {
			loadImage();
		}
		if(thumbnail != null) {
			int x = getWidth()/2 - thumbnail.getIconWidth()/2;
			int y = getHeight()/2 - thumbnail.getIconHeight()/2;
			if(y < 0) {
				 y = 0;
			}

			if(x < 5) {
				 x = 5;
			}
			thumbnail.paintIcon(this, g, x, y);
		}
	}

}
