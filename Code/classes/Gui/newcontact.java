package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.App;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

public class newcontact extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private Point compCoords; 
	public static void main(String[] args) {
		  EventQueue.invokeLater(new Runnable() {
		   public void run() {
		    try {
		newcontact s=new     newcontact(null);
		s.setVisible(true);
		    } catch (Exception e) {
		     e.printStackTrace();
		    }
		   }
		  });
		 }

	/**
	 * Create the frame.
	 */
	public newcontact(App app) {
		try {
			 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			 
			}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		  contentPane = new IPanel("images\\newcontact.jpg");
		      setUndecorated(true);
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    setContentPane(contentPane);
		
		JButton back = new JButton("back");
		back.setFont(new Font("Tahoma", Font.BOLD, 17));
		back.setBackground(new Color(0, 0, 0));
		back.setForeground(new Color(64, 224, 208));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Contacts v=new Contacts(app);
				v.setVisible(true);
			}
		});
		
		name = new JTextField();
		name.setForeground(new Color(0, 0, 128));
		name.setBackground(new Color(255, 255, 255));
		name.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
		name.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea emails = new JTextArea();
		scrollPane.setViewportView(emails);
		emails.setFont(new Font("Arial", Font.BOLD, 18));
		emails.setForeground(new Color(0, 0, 128));
		emails.setBackground(new Color(255, 255, 255));
		emails.setLineWrap(true);
		emails.setToolTipText("Put , Between Emails");
		JButton save = new JButton("Save ");
		save.setFont(new Font("Tahoma", Font.BOLD, 17));
		save.setForeground(new Color(64, 224, 208));
		save.setBackground(new Color(0, 0, 0));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(app.addConacts(name.getText(),emails.getText(),"add")) {
					dispose();
					Contacts c=new Contacts(app);
					c.setVisible(true);   }
				    else {
				    	JOptionPane.showMessageDialog(null, "Wrong Email or Repeated Name","Error",JOptionPane.ERROR_MESSAGE);
				    	
				    }
			
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
		
		JButton exit = new JButton("X");
		exit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exit.setBackground(new Color(0, 0, 255));
		exit.setForeground(new Color(248, 248, 255));
		exit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });
		JButton minimize = new JButton("-");
		minimize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		minimize.setForeground(new Color(245, 255, 250));
		minimize.setBackground(new Color(0, 0, 255));
		minimize.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        setState(JFrame.ICONIFIED);
	        }
	    });
		
		JLabel lblNewLabel = new JLabel("Contact Name ");
		lblNewLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(248, 248, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Contact Emails");
		lblNewLabel_1.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(name, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
									.addComponent(minimize)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(exit))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(back)
							.addPreferredGap(ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
							.addComponent(save)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap(436, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(exit)
						.addComponent(minimize)
						.addComponent(lblNewLabel))
					.addGap(4)
					.addComponent(name, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(back)
						.addComponent(save))
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
