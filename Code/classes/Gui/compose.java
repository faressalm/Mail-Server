package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import classes.App;
import classes.SLinkedList;
import classes.mail;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTabbedPane;



public class compose extends JFrame {

  
  private JPanel contentPane;
  private Point compCoords; 
  private JTextField to;
  private JTextField subject;                  
  private JButton send;
  private JButton exit;
  private JButton minimize;  
  private JButton Attach;
  private SLinkedList attachlist = new SLinkedList() ;
  private App app = new App();
  private JComboBox comboBox;
  private JButton savedraft;

  /*
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
 public void setApp(App app) {
   this.app=app;
   
 }
  /*
   * Create the frame.
   */
  public compose() {
	  try {
  	       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  	      } catch (Exception e1) {
  	       
  	      }
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(500, 250, 750, 600);
    contentPane = new IPanel("images\\compose.jpg") ;  
      contentPane.setForeground(SystemColor.activeCaption);
      contentPane.setBackground(Color.WHITE);
      setUndecorated(true);

      
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    to =new JPlaceholderTextField("T0",1000);
    to.setFont(new Font("Tahoma", Font.ITALIC, 20));
    to.setForeground(new Color(0, 0, 0));
    to.setBackground(new Color(255, 255, 255));
    to.setColumns(10);
    to.setToolTipText("Put (,) to add more recievers.");  
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
          }public void mouseDragged(MouseEvent e) {
              Point currCoords = e.getLocationOnScreen();
              setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);
          }
      });
      JTextArea body = new JTextArea();
      body.setFont(new Font("Courier New", Font.BOLD, 20));
      body.setForeground(new Color(0, 0, 0));
      body.setBackground(new Color(255, 255, 255));
      body.setLineWrap(true);
      
      JScrollPane scroll = new JScrollPane(body);
      
    subject = new JPlaceholderTextField("Subject",30); 
    subject.setFont(new Font("Tahoma", Font.ITALIC, 20));
    subject.setForeground(new Color(0, 0, 0));
    subject.setBackground(new Color(255, 255, 255));
    subject.setColumns(10);
    
    send = new JButton();
    send.setFont(new Font("Tahoma", Font.BOLD, 16));
    send.setForeground(new Color(0, 0, 0));
    send.setText("Send");
    send.setBackground(new Color(0, 0, 0));
  
    send.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          String priority = (String) comboBox.getSelectedItem();
          IMail email=app.readMailInput(to.getText(), body.getText(),attachlist,subject.getText(),priority);
          
          if(app.compose(email)) {
        	  try {
 				 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
 				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException t) {
 				 
 				}
        
        	 dispose();
        	 showmail c = new showmail();
        	 c.setApp(app);
        	 c.setVisible(true);
          }
          else {
        	    JOptionPane.showMessageDialog(null, "Invalid Input","Error",JOptionPane.ERROR_MESSAGE);          
        	  }
         }
       });
   exit = new JButton("");
   exit.setOpaque(false);
   exit.setContentAreaFilled(false);
   exit.setBorderPainted(false);
       exit.setForeground(new Color(255, 255, 255));
       exit.setBackground(new Color(0, 51, 255));
       exit.setBorderPainted(false);
       minimize = new JButton(""); 
       minimize.setOpaque(false);
       minimize.setContentAreaFilled(false);
       minimize.setBorderPainted(false);
       minimize.setForeground(new Color(255, 255, 255));
       minimize.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
       });
       exit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                dispose();
             }
         });
         minimize.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
             }
         });
         
       minimize.setBackground(new Color(0, 51, 255));
       
       Attach = new JButton("Attach");
       Attach.setFont(new Font("Tahoma", Font.BOLD, 16));
       Attach.setForeground(new Color(0, 0, 0));
      
       Attach.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
               j.setCurrentDirectory(new java.io.File("."));
               j.setFileSelectionMode(JFileChooser.FILES_ONLY);
               j.setMultiSelectionEnabled(true);
             // Open the save dialog 
               int r = j.showSaveDialog(null) ; 
              if ( r == JFileChooser.APPROVE_OPTION) {
           	   File[] a = j.getSelectedFiles();
           	   for(int i=0;i<a.length;i++)
                     attachlist.add(a[i].getPath()); 
                  }
                 
              }
            });
       Attach.setBackground(new Color(0, 0, 0));
       String[] arr =new String[1000] ;
       for(int i=0;i<1000;i++)
        arr[i] = Integer.toString(i+1);
       comboBox = new JComboBox(arr); 
       comboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
       comboBox.setForeground(new Color(0, 0, 0));
       comboBox.setBackground(new Color(240, 255, 255));
       comboBox.setEditable(true);
       comboBox.setSelectedItem("Priority"); 
       comboBox.setEditable(false);
       
       JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
       
       savedraft = new JButton("Save Draft");
       savedraft.setFont(new Font("Tahoma", Font.BOLD, 16));
       savedraft.setForeground(new Color(0, 0, 0));
       savedraft.setBackground(new Color(0, 0, 0));
       
       savedraft.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		String priority = (String) comboBox.getSelectedItem();
       		if(priority.equals("Priority")) {
       			priority="";
       		}
       		if(subject.getText().equals("Subject")) {
       			subject = new JPlaceholderTextField("",30);
       		}
       		mail email=new mail();
       			to = new JPlaceholderTextField("",1000);
         	 email=(mail) app.readMailInput(to.getText(), body.getText(),attachlist,subject.getText(),priority);
         	
       	 app.WriteMailInTxt(email, app.curr.getDraft().getPath()+"\\"+email.getDate()+".txt");
     
       
			app.modify_mail(email, app.curr.getDraft());
			try {
				 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException t) {
				 
				}
       
       	 dispose();
       	 showmail c = new showmail();
       	 c.setApp(app);
       	 c.setVisible(true);
       	
       		
       	}
       	
       	
       });
       
       JButton back = new JButton("Cancel");
       back.setFont(new Font("Tahoma", Font.BOLD, 16));
       back.setForeground(new Color(0, 0, 0));
 	  back.setBackground(new Color(0, 0, 0));
 	  back.addActionListener(new ActionListener() {
 		   public void actionPerformed(ActionEvent e) {
 			  try {
 				 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
 				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException t) {
 				 
 				}
 		    dispose();  
 		    showmail home = new showmail();
 		    home.setApp(app);
 		    home.setVisible(true);
 		   }
 		  });
    
       
      
       GroupLayout gl_contentPane_1 = new GroupLayout(contentPane);
       gl_contentPane_1.setHorizontalGroup(
       	gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
       		.addGroup(gl_contentPane_1.createSequentialGroup()
       			.addGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
       				.addGroup(gl_contentPane_1.createSequentialGroup()
       					.addContainerGap()
       					.addComponent(savedraft))
       				.addGroup(Alignment.LEADING, gl_contentPane_1.createSequentialGroup()
       					.addContainerGap()
       					.addComponent(minimize))
       				.addGroup(Alignment.LEADING, gl_contentPane_1.createSequentialGroup()
       					.addGap(45)
       					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING)
       						.addComponent(subject, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
       						.addComponent(to, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
       						.addGroup(Alignment.LEADING, gl_contentPane_1.createSequentialGroup()
       							.addGroup(gl_contentPane_1.createParallelGroup(Alignment.TRAILING, false)
       								.addComponent(back, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       								.addComponent(comboBox, Alignment.LEADING, 0, 108, Short.MAX_VALUE))
       							.addPreferredGap(ComponentPlacement.RELATED)
       							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       							.addPreferredGap(ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
       							.addComponent(Attach, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
       							.addPreferredGap(ComponentPlacement.RELATED)
       							.addComponent(send, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
       						.addComponent(scroll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))))
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(exit))
       );
       gl_contentPane_1.setVerticalGroup(
       	gl_contentPane_1.createParallelGroup(Alignment.LEADING)
       		.addGroup(gl_contentPane_1.createSequentialGroup()
       			.addComponent(minimize, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
       			.addGap(27)
       			.addComponent(savedraft)
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(to, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(subject, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
       			.addGap(9)
       			.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
       				.addGroup(gl_contentPane_1.createSequentialGroup()
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addGroup(gl_contentPane_1.createParallelGroup(Alignment.LEADING)
       						.addGroup(gl_contentPane_1.createParallelGroup(Alignment.BASELINE)
       							.addComponent(send, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
       							.addComponent(Attach, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
       						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       					.addGap(18)
       					.addComponent(back, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED))
       				.addGroup(gl_contentPane_1.createSequentialGroup()
       					.addGap(20)
       					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
       			.addGap(6))
       		.addGroup(gl_contentPane_1.createSequentialGroup()
       			.addComponent(exit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
       			.addContainerGap(556, Short.MAX_VALUE))
       );
        	    
        	    
        	          contentPane.setLayout(gl_contentPane_1);
        	        }
        	      }