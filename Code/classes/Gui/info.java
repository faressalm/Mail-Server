package Gui;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.App;
import classes.SLinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;

public class info extends JFrame {

 private JPanel contentPane;
 
    private Point compCoords; 
    private JTextField firstName;
    private JTextField secondName;
    private JTextField Email ;
    private SLinkedList info = new SLinkedList(); // for set texts in textfields.
    private JPasswordField Pass;
    private JLabel email,date ;
    private JComboBox day,year,month ;
    private JButton save,edit;
    private JButton cancel;
    private JButton back;
    
    // this functions for handling actions in gui.
    
    public  void setTexts() { // before edit and after edit
     Pass.setText((String) info.get(0));
     Pass.setEditable(false);
     firstName.setText((String) info.get(2));
     firstName.setEditable(false);
     secondName.setText((String) info.get(3));
     secondName.setEditable(false);
     year.setEnabled(false);
     month.setEnabled(false);
     day.setEnabled(false);
     save.setVisible(false);
     cancel.setVisible(false);
     edit.setVisible(true);
     back.setVisible(true);
     email.setVisible(true);
     Email.setVisible(true);
    }
    // info that can be edit
    public void invisibleEdit() {
     email.setVisible(false);
     Email.setVisible(false);
    }
    public void setEdit() {
     Pass.setEditable(true);
     firstName.setEditable(true);
     secondName.setEditable(true);
     day.setEnabled(true);
     year.setEnabled(true);
     month.setEnabled(true);
     back.setVisible(false);
     save.setVisible(true);
     cancel.setVisible(true);
    }
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
 public info(App app) {
	 try {
		 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		 
		}
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 620, 484);
  contentPane = new IPanel("images\\info.jpg");
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  setUndecorated(true);
  
  info = app.readInfo(); 
  
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
  
  firstName = new JPlaceholderTextField("",10);
  
  firstName.setColumns(10);
  
  JLabel first = new JLabel("First Name");
  first.setForeground(new Color(255, 255, 255));
  first.setFont(new Font("Tahoma", Font.BOLD, 18));
  
  JLabel second = new JLabel("Second Name");
  second.setForeground(new Color(255, 255, 255));
  second.setFont(new Font("Tahoma", Font.BOLD, 18));
  
   secondName = new JPlaceholderTextField("", 10);
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
   String d = (String) info.get(4);
   year = new JComboBox(c);
   year.setForeground(new Color(0, 0, 0));
   year.setSelectedItem(d.substring(d.lastIndexOf(" ")+1));
   
   month = new JComboBox(b);
   month.setForeground(new Color(0, 0, 0));
   month.setSelectedItem(d.substring(d.indexOf(" ")+1, d.lastIndexOf(" ")));
  
   day = new JComboBox(a);
   day.setForeground(new Color(0, 0, 0));
   day.setSelectedItem(d.substring(0,d.indexOf(" ")));
  
   email = new JLabel("Email");
   email.setForeground(new Color(255, 255, 255));
   
   email.setFont(new Font("Tahoma", Font.BOLD, 18));
  
   Email = new JPlaceholderTextField("", 35);
   Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
   Email.setText((String) info.get(1));
   Email.setEditable(false); 
   JLabel pass = new JLabel("Password");
   pass.setForeground(new Color(255, 255, 255));
   pass.setFont(new Font("Tahoma", Font.BOLD, 18));
  
   date = new JLabel("Date of Birth");
   date.setForeground(new Color(255, 255, 255));
   date.setFont(new Font("Tahoma", Font.BOLD, 18)); 
  
  
  
   edit = new JButton("Edit");
   edit.setForeground(new Color(64, 224, 208));
   edit.setBackground(new Color(0, 0, 0));
   save = new JButton("Save");
   save.setForeground(new Color(64, 224, 208));
   save.setBackground(new Color(0, 0, 0));
  
  
  edit.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    edit.setVisible(false);
    setEdit();
    invisibleEdit();
   }
  });
  save.addActionListener(new ActionListener() {
	     public void actionPerformed(ActionEvent e) {
	      if(!app.validInfo(firstName.getText(), secondName.getText(),day.getSelectedItem()+" "+month.getSelectedItem()+" "+year.getSelectedItem(), Pass.getText())) {
	          JOptionPane.showMessageDialog(null,"Please enter a valid info.","Edit",JOptionPane.ERROR_MESSAGE);

	      }
	     else {
	      info.remove(0);
	      info.add(0, Pass.getText());
	      info.remove(2);
	      info.add(2, firstName.getText());
	      info.remove(3);
	      info.add(3, secondName.getText());
	      info.remove(4);
	      info.add(4, day.getSelectedItem()+" "+month.getSelectedItem()+" "+year.getSelectedItem());  
	      app.relplceInfo(info);
	      setTexts();
	      }
	    } 
	  });
  
  Pass = new IPassword("",35);
  
  
  
  cancel = new JButton("Cancel");
  cancel.setBackground(new Color(0, 0, 0));
  cancel.setForeground(new Color(64, 224, 208));
  cancel.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    setTexts();
   }
  });
  
  back = new JButton("Back");
  back.setBackground(new Color(0, 0, 0));
  back.setForeground(new Color(64, 224, 208));
  back.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    dispose();  
    showmail home = new showmail();
    home.setApp(app);
    home.setVisible(true);
   }
  });

  setTexts();
  
  JCheckBox view = new JCheckBox("View");
  view.setForeground(new Color(64, 224, 208));
  view.setBackground(new Color(0, 0, 0));
  view.setOpaque(false);
  view.setContentAreaFilled(false);
  view.setBorderPainted(false);
  view.addActionListener(ae -> {
          JCheckBox r = (JCheckBox) ae.getSource();
          Pass.setEchoChar(r.isSelected() ? '\u0000' : (Character)UIManager.get("PasswordField.echoChar"));
       });
  GroupLayout gl_contentPane = new GroupLayout(contentPane);
  gl_contentPane.setHorizontalGroup(
  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(20)
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
  								.addComponent(second, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
  								.addComponent(first, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
  								.addComponent(email, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
  								.addComponent(pass, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
  							.addPreferredGap(ComponentPlacement.RELATED)
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  								.addComponent(Pass, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
  								.addGroup(gl_contentPane.createSequentialGroup()
  									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
  										.addComponent(secondName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  										.addComponent(firstName, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
  									.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE))
  								.addComponent(Email, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)))
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  								.addGroup(gl_contentPane.createSequentialGroup()
  									.addGap(20)
  									.addComponent(back, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
  									.addPreferredGap(ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
  									.addComponent(edit, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
  								.addGroup(gl_contentPane.createSequentialGroup()
  									.addComponent(date, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
  									.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
  									.addComponent(day, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
  									.addGap(41)
  									.addComponent(month, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
  							.addGap(44)
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  								.addGroup(gl_contentPane.createSequentialGroup()
  									.addComponent(year, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
  									.addGap(74))
  								.addGroup(gl_contentPane.createSequentialGroup()
  									.addComponent(cancel)
  									.addPreferredGap(ComponentPlacement.UNRELATED)
  									.addComponent(save, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))))))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addContainerGap()
  					.addComponent(minimize)))
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addComponent(view)
  				.addComponent(exit))
  			.addContainerGap())
  );
  gl_contentPane.setVerticalGroup(
  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(minimize, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
  				.addComponent(exit))
  			.addGap(55)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(first, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  				.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
  			.addGap(29)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(second, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
  				.addComponent(secondName, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
  			.addGap(40)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(email, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
  				.addComponent(Email, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
  			.addGap(28)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(pass, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
  				.addComponent(Pass, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
  				.addComponent(view))
  			.addGap(41)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  				.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  				.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  				.addComponent(date, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
  			.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  						.addComponent(save)
  						.addComponent(cancel))
  					.addGap(32))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  						.addComponent(edit)
  						.addComponent(back))
  					.addGap(33))))
  );
    	  contentPane.setLayout(gl_contentPane);
    	  
    	 
    	 }
    	}