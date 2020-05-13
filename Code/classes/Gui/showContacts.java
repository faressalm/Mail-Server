package Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.SystemColor;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class showContacts extends JFrame {

 private JPanel contentPane;
 
    private Point compCoords; 
    private JTextField firstName;
    private SLinkedList info = new SLinkedList(); // for set texts in textfields.
    private JButton save,edit;
    private JButton cancel;
    private JButton back;
    private JScrollPane scrollPane;
    private JTextArea emails;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;


    public static void main(String[] args) {
    	   
        EventQueue.invokeLater(new Runnable() {
          public void run() {
            try {
            	
              showContacts frame = new showContacts(null,"ss","sd");
              frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
      }
    public  void setTexts() {
     
     firstName.setEditable(false);
     emails.setEditable(false);
     save.setVisible(false);
     cancel.setVisible(false);
     edit.setVisible(true);
     back.setVisible(true);
    }
    // info that can be edit
   
    public void setEdit() {
    
     firstName.setEditable(true);
     emails.setEditable(true);
     back.setVisible(false);
     save.setVisible(true);
     cancel.setVisible(true);
    }
 /**
  * Launch the application.
  */
 

 /**
  * Create the frame.
  */
 public showContacts(App app,String oldname,String oldemails) {
	 try {
		 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		 
		}
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 800, 400);
  contentPane = new IPanel("images\\showcontact.jpg");      
  contentPane.setForeground(SystemColor.activeCaption);
  contentPane.setBackground(Color.WHITE);
  setUndecorated(true);
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
  

  
  JButton exit = new JButton("X");
  exit.setBackground(new Color(0, 0, 205));
  exit.setForeground(new Color(255, 255, 255));
  
  JButton minimize = new JButton("-");
  minimize.setBackground(new Color(0, 0, 205));
  minimize.setForeground(new Color(255, 255, 255));
  
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
    scrollPane = new JScrollPane();
    emails = new JTextArea();
    scrollPane.setViewportView(emails);
    emails.setFont(new Font("Arial", Font.BOLD, 20));
  	emails.setForeground(new Color(0, 0, 0));
  	emails.setBackground(new Color(255, 255, 255));
  	emails.setLineWrap(true);
    emails.setText(oldemails.replaceAll(",", "\n"));
    firstName = new JPlaceholderTextField("",15);
    firstName.setFont(new Font("Tahoma", Font.BOLD, 20));
  
  firstName.setColumns(10);
  firstName.setText(oldname);

  
  
  
   edit = new JButton("Edit");
   edit.setFont(new Font("Tahoma", Font.BOLD, 17));
   edit.setBackground(new Color(0, 0, 0));
   edit.setForeground(new Color(102, 205, 170));
   save = new JButton("Save");
   save.setForeground(new Color(64, 224, 208));
   save.setBackground(new Color(0, 0, 0));
   save.setFont(new Font("Tahoma", Font.BOLD, 17));
   save.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		String s;
	   		if(oldname.equals(firstName.getText())) {
	   			s="";
	   		}else {
	   			s=firstName.getText();
	   		}
	   		if(app.addConacts(s,emails.getText(),"")) {
	   		    app.editContact(oldname, oldemails, firstName.getText(), emails.getText());  
	   		 emails.setText(emails.getText().replace(",","\n"));
	    		setTexts();
	   		}
	   		    else {
	   		    	JOptionPane.showMessageDialog(null, "Wrong Email or Repeated Name","Error",JOptionPane.ERROR_MESSAGE);
	   		    	
	   		    }
	   	}
	   });
  
  edit.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
	   emails.setText(emails.getText().replace("\n",","));
    edit.setVisible(false);
    setEdit();
    
   }
  });
   
  
  
  cancel = new JButton("Cancel");
  cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
  cancel.setForeground(new Color(64, 224, 208));
  cancel.setBackground(new Color(0, 0, 0));
  cancel.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    setTexts();
   }
  });
  
  back = new JButton("Back");
  back.setFont(new Font("Tahoma", Font.BOLD, 17));
  back.setBackground(new Color(0, 0, 0));
  back.setForeground(new Color(64, 224, 208));
  back.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    dispose();  
    Contacts home = new Contacts(app);
    home.setVisible(true);
   }
  });

  setTexts();
  
  lblNewLabel = new JLabel("Contact Name ");
  lblNewLabel.setForeground(new Color(64, 224, 208));
  lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
  
  lblNewLabel_1 = new JLabel("Contact Emails");
  lblNewLabel_1.setForeground(new Color(64, 224, 208));
  lblNewLabel_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 22));
  

  GroupLayout gl_contentPane = new GroupLayout(contentPane);
  gl_contentPane.setHorizontalGroup(
  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addContainerGap()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addComponent(back, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
  							.addPreferredGap(ComponentPlacement.RELATED, 334, Short.MAX_VALUE)
  							.addComponent(edit, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
  							.addGap(30)
  							.addComponent(cancel)
  							.addPreferredGap(ComponentPlacement.UNRELATED)
  							.addComponent(save)
  							.addGap(38))
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
  							.addPreferredGap(ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
  							.addComponent(minimize)
  							.addPreferredGap(ComponentPlacement.RELATED)))
  					.addComponent(exit))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
  					.addContainerGap(360, Short.MAX_VALUE))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
  					.addContainerGap(566, Short.MAX_VALUE))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
  					.addContainerGap())))
  );
  gl_contentPane.setVerticalGroup(
  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  				.addComponent(exit)
  				.addComponent(minimize)
  				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
  			.addGap(11)
  			.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
  			.addGap(48)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  						.addComponent(save, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
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