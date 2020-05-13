package Gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.App;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;



public class mailText extends JFrame {

 private JPanel contentPane;
 
  private Point compCoords; 

 /** 
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }
 

 /**
  * Create the frame.
  */
 public mailText(App app,classes.mail mail) {
	 try {
		 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		 
		}
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setUndecorated(true);
  setBounds(100, 100, 658, 600);
  contentPane = new IPanel("images\\newcontact.jpg");
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
   JTextArea message = new JTextArea();
   message.setFont(new Font("Arial", Font.PLAIN, 15));
   message.setForeground(new Color(0, 0, 0));
   message.setBackground(new Color(240, 255, 255));
   message.setLineWrap(true);
       
  JScrollPane scrollPane = new JScrollPane(message);
  message.setText(app.mailView(mail)); 
  message.setEditable(false);
  
  JButton exit = new JButton("X");
  exit.setBackground(new Color(0, 0, 205));
  exit.setForeground(new Color(255, 255, 255));
  
  JButton minimize = new JButton("-");
  minimize.setForeground(new Color(255, 255, 255));
  minimize.setBackground(new Color(0, 0, 205));
  
  exit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });
    minimize.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        setState(JFrame.ICONIFIED);
        }
    });
    
    compCoords = null;
    contentPane.addMouseListener(new MouseListener() {
        public void mouseReleased(MouseEvent e) {
            compCoords = null;
        }
        public void mousePressed(MouseEvent e) {
            compCoords = e.getPoint();
        }
   public void mouseClicked(MouseEvent e) {
   }
   public void mouseEntered(MouseEvent arg0) {
   }
   public void mouseExited(MouseEvent e) {
   }
    });
    contentPane.addMouseMotionListener(new MouseMotionListener() {
        public void mouseMoved(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) { 
            Point currCoords = e.getLocationOnScreen();
            setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
        }
    });
    String[] arr = new String[mail.getAttachments().size()];
  for(int i =0;i< mail.getAttachments().size();i++) { 
   arr[i]=Integer.toString(i+1);
  }
  JComboBox comboBox = new JComboBox(arr);
  comboBox.setBackground(new Color(0, 0, 0));
  comboBox.setForeground(new Color(64, 224, 208));
  comboBox.setFont(new Font("Arial Black", Font.BOLD, 16));
  comboBox.setEditable(true);
        comboBox.setSelectedItem("Attachments"); 
        comboBox.setEditable(false);
  JButton open = new JButton("Open");
  open.setFont(new Font("Arial Black", Font.BOLD, 16));
  open.setBackground(new Color(0, 0, 0));
  open.setForeground(new Color(64, 224, 208));
  open.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    if(comboBox.getSelectedItem().equals("Attachments")){ 
     JOptionPane.showMessageDialog(null,"Please choose your required attachment","Attacments",JOptionPane.ERROR_MESSAGE); 
    }
    else {
      Desktop d = Desktop.getDesktop(); 
      File req = new File((String) mail.getAttachments().get(Integer.parseInt((String) comboBox.getSelectedItem())-1));  
      try {
      d.open(req);
     } catch (IOException e) {
      // TODO Auto-generated catch block
     }
       }
   }
  });
  if(mail.getAttachments().size()==0) {
   open.setVisible(false);
   comboBox.setVisible(false); 
  }
  
  JButton back = new JButton("Back");
  back.setFont(new Font("Arial", Font.BOLD, 16));
  back.setForeground(new Color(64, 224, 208));
  back.setBackground(new Color(0, 0, 0));
  back.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0){
	    dispose();  
	    showmail home = new showmail();
	    home.setApp(app);
	    home.setVisible(true);
	   }
	  });
	  
	  GroupLayout gl_contentPane = new GroupLayout(contentPane);
	  gl_contentPane.setHorizontalGroup(
	  	gl_contentPane.createParallelGroup(Alignment.LEADING)
	  		.addGroup(gl_contentPane.createSequentialGroup()
	  			.addGap(26)
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	  				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
	  					.addGroup(gl_contentPane.createSequentialGroup()
	  						.addComponent(back)
	  						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	  						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
	  						.addPreferredGap(ComponentPlacement.RELATED)
	  						.addComponent(open, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
	  					.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE))
	  				.addGroup(gl_contentPane.createSequentialGroup()
	  					.addComponent(minimize)
	  					.addPreferredGap(ComponentPlacement.UNRELATED)
	  					.addComponent(exit)))
	  			.addGap(103))
	  );
	  gl_contentPane.setVerticalGroup(
	  	gl_contentPane.createParallelGroup(Alignment.LEADING)
	  		.addGroup(gl_contentPane.createSequentialGroup()
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	  				.addComponent(exit)
	  				.addComponent(minimize))
	  			.addGap(18)
	  			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
	  			.addGap(29)
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	  				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	  				.addComponent(open, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
	  				.addComponent(back))
	  			.addContainerGap(19, Short.MAX_VALUE))
	  );
	  contentPane.setLayout(gl_contentPane);
	  
	 }
	}