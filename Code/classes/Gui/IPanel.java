package Gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class IPanel extends JPanel {
	
	private String imgPath ; 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IPanel(String imgPath)
	{
		this.imgPath = imgPath ;
		
	}
	public void paintComponent(Graphics g) {  
        Image img = Toolkit.getDefaultToolkit().getImage(imgPath);  
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
   }  
	

}
