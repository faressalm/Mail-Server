package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import classes.App;
import classes.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Contacts extends JFrame {

 private JPanel contentPane;
  private Point compCoords; 
  private JTable table;
  private DefaultTableModel dtm ;
  private JTextField searchtxt;
  private SLinkedList[] go;

 /** 
  * Launch the application.
  */
  public static void main(String[] args) {
	   
	    EventQueue.invokeLater(new Runnable() {
	      public void run() {
	        try {
	         Contacts c=new Contacts(null);
	         c.setVisible(true);
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    });
	  }

 /**
  * Create the frame.
  */
 public Contacts(App app) {
	 try {
		 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		 
		}
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 1300, 550);
  contentPane = new IPanel("images\\Contacts.jpg") ; 
  contentPane.setForeground(new Color(0, 0, 0));
  contentPane.setBackground(new Color(64, 224, 208));
  setUndecorated(true);
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  
  JButton exit = new JButton("X");
  exit.setBackground(new Color(0, 0, 205));
  exit.setForeground(Color.WHITE);
  
  JButton minimize = new JButton("-");
  minimize.setBackground(new Color(0, 0, 205));
  minimize.setForeground(Color.WHITE);
  
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
	  
   String[] columns = {"Contacts Names","Select"};
   dtm = new DefaultTableModel(); 
	  dtm.setColumnIdentifiers(columns);
	  table = new JTable(dtm){
	    public boolean isCellEditable(int rowIndex, int colIndex) {
	      if(colIndex==1)
	        return true;
	      return false; //Disallow the editing of any cell
	      }
	    private static final long serialVersionUID = 1L;
	      @Override
	      public Class getColumnClass(int column) {
	          switch (column) {
	              case 1:
	                 return Boolean.class;
	                 
	              default:
	                  return String.class;
	          }
	      }
	      };
	      JScrollPane scrollPane = new JScrollPane(table);
	      table.setFillsViewportHeight(true);
	      table.setForeground(Color.black);
	      table.setRowHeight(45);
	      table.setFont(new Font("Courier New", Font.PLAIN, 25));
	      table.setGridColor(Color.cyan);
	      table.setBackground(Color.white);
	      contentPane.add(scrollPane);
	      JTableHeader thread=table.getTableHeader();
	      thread.setOpaque(false);
	      thread.setForeground(Color.blue);
	      thread.setFont(new Font("Courier New", Font.BOLD, 25));
	      ((DefaultTableCellRenderer)thread.getDefaultRenderer())
	      .setHorizontalAlignment(JLabel.CENTER);
	      //width
	      int[] columnsWidth = {
	              550,150
	          };
	      int j = 0;
	      for (int width : columnsWidth) {
	          TableColumn column = table.getColumnModel().getColumn(j++);
	          column.setMinWidth(width);
	          column.setMaxWidth(width);
	          column.setPreferredWidth(width);
	      }
	   go=app.contactSort("");
	    changeTable();
	  JButton add = new JButton("New Contact");
	  add.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		dispose();
	  		newcontact c=new newcontact(app);
	  		c.setVisible(true);
	  	}
	  });
	  add.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 18));
	  add.setForeground(new Color(64, 224, 208));
	  add.setBackground(new Color(0, 0, 0));
	  
	  JButton sort = new JButton("Sort");
	  sort.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  sort.setForeground(new Color(64, 224, 208));
	  sort.setBackground(new Color(0, 0, 0));
	  sort.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            go=app.contactSort("sort");
	            changeTable();
	        }
	    });
	  
	  searchtxt = new JTextField();
	  searchtxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  searchtxt.setColumns(10);
	  
	  JButton searh = new JButton("search");
	  searh.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  searh.setForeground(new Color(64, 224, 208));
	  searh.setBackground(new Color(0, 0, 0));
	  searh.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            go=app.contactSearch(searchtxt.getText());
	            changeTable();
	            searchtxt.setText("");
	        }
	    });
	  
	  JButton delete =  new JButton("Delete");
	  delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  delete.setForeground(new Color(64, 224, 208));

	
			
		
	    
	  delete.setBackground(new Color(0, 0, 0));
	  delete.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
ILinkedList index=new SLinkedList();
	  		int count=0;
	  		for(int i=0;i<go[0].size();i++) {
	  			if((boolean)table.getValueAt(i-count,1)==true) {
	  				app.deleteContact((String)go[0].get(i),(String)go[1].get(i));	
	  				dtm.removeRow(i-count);
	  				count++;
	  			}
	  		}
	  		
	  	}
	  });
      
	  table.addMouseListener(new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	          // This is for double click event on anywhere on JTable
	        JTable target = (JTable) e.getSource();
	          if (e.getClickCount() == 2 && target.getSelectedColumn()<6) {
	             
	              int row = target.getSelectedRow();
	              showContacts view=new showContacts(app,(String)go[0].get(row),(String)go[1].get(row));  
                   dispose();  	          
	               view.setVisible(true);
	             
	          }
	      }
	   });
	  
	  JButton back = new JButton(new ImageIcon("images\\back.png"));
	  back.setBackground(SystemColor.activeCaption);
	  back.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
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
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
	  				.addGroup(gl_contentPane.createSequentialGroup()
	  					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
	  						.addGroup(gl_contentPane.createSequentialGroup()
	  							.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
	  							.addGap(28)
	  							.addComponent(searh)
	  							.addGap(18)
	  							.addComponent(sort))
	  						.addComponent(back, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
	  					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	  					.addComponent(delete, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
	  				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE))
	  			.addPreferredGap(ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	  				.addGroup(gl_contentPane.createSequentialGroup()
	  					.addComponent(minimize)
	  					.addGap(18)
	  					.addComponent(exit)
	  					.addContainerGap())
	  				.addGroup(gl_contentPane.createSequentialGroup()
	  					.addComponent(add, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
	  					.addGap(176))))
	  );
	  gl_contentPane.setVerticalGroup(
	  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
	  		.addGroup(gl_contentPane.createSequentialGroup()
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	  				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	  					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
	  					.addComponent(minimize))
	  				.addComponent(back, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
	  			.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
	  			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	  				.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	  				.addComponent(searh)
	  				.addComponent(sort)
	  				.addComponent(delete, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
	  			.addPreferredGap(ComponentPlacement.RELATED)
	  			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE))
	  		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
	  			.addGap(511)
	  			.addComponent(add))
	  );
	
	  scrollPane.setViewportView(table);
	  contentPane.setLayout(gl_contentPane);
	  
	 }
 
 private void changeTable() {
	 int rows = dtm.getRowCount(); 
     for(int i = rows - 1; i >=0; i--)
     {
        dtm.removeRow(i); 
     }  
	 for(int i=0;i<go[0].size();i++) {
		 Object[] s= {go[0].get(i),false};
		 dtm.addRow(s);
	 }
 }
 
 
 
	}