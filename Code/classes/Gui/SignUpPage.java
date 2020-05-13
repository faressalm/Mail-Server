package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.App;
import classes.IContact;

import java.awt.Font;
import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class SignUpPage extends JFrame {

 /*
  * 
  */
 private static final long serialVersionUID = 1L; //handle warning.
 private JPanel contentPane;
 private JTextField firstName;
 private JTextField secondName;
 private JTextField email;
 private JPasswordField password;
 private JPasswordField passwordField;
 private App signup=new App();
 private IContact contact=new IContact();
 private JButton exit;
 private JButton minimize;
 private Point compCoords; 
 private JButton back;
 private JComboBox day;
 private JComboBox month;
 private JComboBox year;
 /*
  * Launch the application.
  */
 public static void main(String[] args) {
 
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     SignUpPage frame = new SignUpPage();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the frame.
  */
 public SignUpPage() {
	 
	 try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			
		}
  setFont(new Font("Dialog", Font.PLAIN, 30));
  setTitle("Sign up");
  setUndecorated(true); 
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(200,0,600, 750);
  contentPane = new IPanel("images\\signup.jpg") ;
  contentPane.setForeground(Color.LIGHT_GRAY);
  contentPane.setBackground(Color.LIGHT_GRAY);
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
 
  firstName = new JPlaceholderTextField("First Name",10);
  
  firstName.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
  
  firstName.setColumns(10);
  
  secondName = new JPlaceholderTextField("Second Name",10);
  secondName.setFont(new Font("Arial Unicode MS", Font.PLAIN, 22));
  secondName.setColumns(10);
  
  email = new JPlaceholderTextField("username@mailserver.com",40);
  email.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
  email.setColumns(10);
  
  JButton btnNewButton = new JButton("Sign Up");
  btnNewButton.addActionListener(new ActionListener() {
   @SuppressWarnings("deprecation")
public void actionPerformed(ActionEvent e) {
    contact.setFirstName(firstName.getText());
    contact.setSecondName(secondName.getText());
    contact.setEmail(email.getText());
    String Date= day.getSelectedItem()+" "+month.getSelectedItem()+" "+year.getSelectedItem();
    contact.setDate(Date);
    contact.setPasssword(password.getText());
    
     if(!signup.validInfo(firstName.getText(), secondName.getText(),day.getSelectedItem()+" "+month.getSelectedItem()+" "+year.getSelectedItem(), password.getText())) {
	     JOptionPane.showMessageDialog(null,"Please enter a valid info.","Sign up",JOptionPane.ERROR_MESSAGE);

		}
    else if( !( contact.valid()&&signup.signup(contact)&&contact.getpassword().equals(passwordField.getText())) ) {
      JOptionPane.showMessageDialog(null, "You may entered an existing email or wrong Re-password","Sign up",JOptionPane.ERROR_MESSAGE);
      
    }
    
    else {
    	dispose();
    	showmail home  = new showmail();
    	home.setApp(signup);
    	home.setVisible(true);
    }
    }
  });
 
  btnNewButton.setBackground(new Color(102, 153, 0));
  btnNewButton.setForeground(Color.WHITE);
  
  password = new IPassword("Enter your password",40);
  password.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
  
  passwordField = new IPassword("Re-enter your password",40);
  passwordField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 20));
  
  exit = new JButton("X");
  exit.setBackground(new Color(0, 0, 255));
  
  minimize = new JButton("-");
  minimize.setBackground(new Color(0, 0, 255));
  exit.setForeground(Color.white);
  minimize.setForeground(Color.white);
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
  back = new JButton(new ImageIcon("images\\back.png"));
  back.setBackground(SystemColor.activeCaption);
  back.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    dispose();
    Start Signin = new Start();
    Signin.frmMailServerApp.setVisible(true);
   }
  });
  String[]a = new String[31];
	 for(int i=1;i<=31;i++) {
		 a[i-1]= Integer.toString(i);
	 }
	 String[]b = new String[12];
	 for(int i=1;i<=12;i++) {
		 b[i-1]= Integer.toString(i);
	 }
	 String[] c = new String[71];
	 for(int i=0,j=1950;i<=70;i++,j++) {
		 c[i] = Integer.toString(j);
	 }
  
  day = new JComboBox(a);
  day.setEditable(true);
  day.setSelectedItem("Day"); 
  day.setEditable(false);
  month = new JComboBox(b);
  month.setEditable(true);
  month.setSelectedItem("Month"); 
  month.setEditable(false);
  year = new JComboBox(c);
  year.setEditable(true);
  year.setSelectedItem("Year"); 
  year.setEditable(false);
  
  GroupLayout gl_contentPane = new GroupLayout(contentPane);
  gl_contentPane.setHorizontalGroup(
  	gl_contentPane.createParallelGroup(Alignment.LEADING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addContainerGap()
  			.addComponent(back, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
  			.addPreferredGap(ComponentPlacement.RELATED, 459, Short.MAX_VALUE)
  			.addComponent(minimize)
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addComponent(exit)
  			.addGap(2))
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGap(206)
  			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
  			.addContainerGap(261, Short.MAX_VALUE))
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(160)
  					.addComponent(day, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
  					.addComponent(month, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
  					.addGap(26)
  					.addComponent(year, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(123)
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
  						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
  							.addComponent(password, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  							.addComponent(email, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  							.addComponent(secondName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  							.addComponent(firstName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))))
  			.addGap(143))
  );
  gl_contentPane.setVerticalGroup(
  	gl_contentPane.createParallelGroup(Alignment.LEADING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  						.addComponent(exit)
  						.addComponent(minimize))
  					.addGap(161)
  					.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
  					.addGap(50)
  					.addComponent(secondName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
  					.addGap(31)
  					.addComponent(email, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
  					.addGap(42)
  					.addComponent(password, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
  					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED))
  				.addComponent(back, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
  			.addGap(86)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(day, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
  				.addComponent(month, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
  				.addComponent(year, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
  			.addGap(43)
  			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
  			.addContainerGap(56, Short.MAX_VALUE))
  );
              contentPane.setLayout(gl_contentPane);
             }
            } 