package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import classes.App;

import javax.swing.JPasswordField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Point;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;


public class Start {
 
 private  JPanel contentPane ;
 public JFrame frmMailServerApp;
 private JPasswordField passwordField;
 private JTextField textField;
 private App signIn = new App();
 private String email="e" ;
 private String pass="p" ;
 private Point compCoords; 

 
 /*
   Launch the application.
  */
 public static void main(String[] args) {
	 
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     Start window = new Start();
     window.frmMailServerApp.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
    
   }
  });
 }

 /*
   Create the application.
  */
 public Start() {
  initialize();

 }

 /**
  * Initialize the contents of the frame.
  */
 private void initialize() {
  frmMailServerApp = new JFrame();
  frmMailServerApp.setResizable(false);
  frmMailServerApp.setUndecorated(true);
  frmMailServerApp.setForeground(Color.BLUE);
  frmMailServerApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  try {
	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
	
}
  frmMailServerApp.getContentPane().setBackground(Color.LIGHT_GRAY);
  frmMailServerApp.setBounds(100, 100,1250,600);
  
   contentPane =  new IPanel("images\\start.jpg");
  frmMailServerApp.setContentPane(contentPane);
       
        
  frmMailServerApp.getContentPane().addPropertyChangeListener(new PropertyChangeListener() {
   public void propertyChange(PropertyChangeEvent arg0) {
   }
  });
  frmMailServerApp.setTitle("Mail Server App");
  frmMailServerApp.setBackground(Color.BLUE);
  
  passwordField = new IPassword("Enter your passowrd",30);
  passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
  textField = new JPlaceholderTextField("username@mailserver.com",30);
  textField.setBackground(new Color(245, 255, 250));
  textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
  
  
  
  
  
  JButton btnNewButton_2 = new JButton("Log in");
  btnNewButton_2.setForeground(new Color(245, 255, 250));
  btnNewButton_2.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 26));
  btnNewButton_2.setBackground(new Color(0, 191, 255));
  
  
  btnNewButton_2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    
    email = textField.getText();
    pass = passwordField.getText(); 
    if(signIn.signin(email,pass)) {
    	frmMailServerApp.dispose();            
    	showmail home = new showmail();
    	home.setApp(signIn);
    	home.setVisible(true);
    } 
    else
    {
     JOptionPane.showMessageDialog(null,"You may entered a wrong eamil or a wrong password","Log in",JOptionPane.ERROR_MESSAGE);
     
    }
   }
  });
  
  
  
  
  textField.setColumns(10);
  
  JButton btnNewButton = new JButton("Sign up");
  btnNewButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    
    frmMailServerApp.dispose();
    SignUpPage page = new SignUpPage();
    page.setVisible(true);
   }
  });
  btnNewButton.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 26));
  btnNewButton.setBackground(new Color(60, 179, 113));
  btnNewButton.setForeground(new Color(255, 255, 255));
  
  JButton exit = new JButton("X");
  exit.setForeground(new Color(255, 255, 255));
  exit.setBackground(new Color(0, 0, 255));
  
  JButton minimize = new JButton("-");
  minimize.setForeground(new Color(255, 255, 255));
  minimize.setBackground(new Color(0, 0, 255));
  
  exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          System.exit(0);
      }
  });
  minimize.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  frmMailServerApp.setState(JFrame.ICONIFIED);
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
          frmMailServerApp.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
      }
  });
  
  GroupLayout groupLayout = new GroupLayout(frmMailServerApp.getContentPane());
  groupLayout.setHorizontalGroup(
  	groupLayout.createParallelGroup(Alignment.TRAILING)
  		.addGroup(groupLayout.createSequentialGroup()
  			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(131)
  					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
  						.addComponent(passwordField, Alignment.LEADING)
  						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)))
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(218)
  					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(842)
  					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
  			.addContainerGap(179, Short.MAX_VALUE))
  		.addGroup(groupLayout.createSequentialGroup()
  			.addContainerGap(1168, Short.MAX_VALUE)
  			.addComponent(minimize)
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addComponent(exit))
  );
  groupLayout.setVerticalGroup(
  	groupLayout.createParallelGroup(Alignment.LEADING)
  		.addGroup(groupLayout.createSequentialGroup()
  			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
  				.addComponent(exit)
  				.addComponent(minimize, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
  			.addGap(160)
  			.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  			.addGap(70)
  			.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  			.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
  			.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
  			.addGap(85)
  			.addComponent(btnNewButton)
  			.addContainerGap())
  );
  frmMailServerApp.getContentPane().setLayout(groupLayout);
 }
}