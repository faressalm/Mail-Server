package Gui;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import classes.App;
import classes.SLinkedList;
import classes.folder;
import classes.mail;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class showmail extends JFrame {

 private JPanel contentPane;
 private App app=new App();
 private Point compCoords; 
 private JTable table;
 private DefaultTableModel dtm ;
 private Object[]  row ;
 private ILinkedList go;
 private String curButton="Inbox";
 private JComboBox searchtype;
 private  JComboBox sorttype;
 private JTextField searchtxt= new JPlaceholderTextField("For?",30);
 private String sortORsearch="sort";
 JLabel section ;
 /*
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     showmail frame = new showmail();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 public void setApp(App app) {
     this.app=app;
     go =app.setSort((String) sorttype.getSelectedItem(), curButton);
     changeTable();
     try {
		 UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		 
		}
     
   }
 
 
 /*
  * Create the frame.
  */
 public showmail() {
	
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 1430, 823);
  contentPane = new IPanel("images\\showmail.jpg");
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setUndecorated(true);
  setContentPane(contentPane);
  
  
  String[] columns = {"Date", "E-mail", "Sub", 
          "pri", "Attach","mail","Select"};
  
  dtm = new DefaultTableModel(); 
  dtm.setColumnIdentifiers(columns);
  table = new JTable(dtm){
    public boolean isCellEditable(int rowIndex, int colIndex) {
      if(colIndex==6)
        return true;
      return false; //Disallow the editing of any cell
      }
    private static final long serialVersionUID = 1L;
      @Override
      public Class getColumnClass(int column) {
          switch (column) {
              case 6:
                 return Boolean.class;
                 
              default:
                  return String.class;
          }
      }
      };
  JScrollPane scrollPane = new JScrollPane(table);
  table.setFillsViewportHeight(true);
  table.setForeground(Color.black);
  table.setRowHeight(65);
  table.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 18));
  table.setGridColor(Color.cyan);
  table.setBackground(Color.WHITE);
  contentPane.add(scrollPane);
  JTableHeader thread=table.getTableHeader();
  thread.setOpaque(false);
  thread.setForeground(Color.blue);
  thread.setFont(new Font("Courier New", Font.BOLD, 25));
  ((DefaultTableCellRenderer)thread.getDefaultRenderer())
  .setHorizontalAlignment(JLabel.CENTER);
  //change colomns width
  int[] columnsWidth = {
          215, 150, 100, 70, 100, 425,100
      };
  int j = 0;
  for (int width : columnsWidth) {
      TableColumn column = table.getColumnModel().getColumn(j++);
      column.setMinWidth(width);
      column.setMaxWidth(width);
      column.setPreferredWidth(width);
  }
 
  
  JButton compose = new JButton("");
  compose.setOpaque(false);
  compose.setContentAreaFilled(false);
 // compose.setBorderPainted(false);
  compose.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	dispose();
      compose compose = new compose();
      compose.setApp(app);
      compose.setVisible(true); 
    }
  });
  
  JButton Inbox = new JButton("");
  Inbox.setOpaque(false);
  Inbox.setContentAreaFilled(false);
 // Inbox.setBorderPainted(false);*/
  Inbox.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	sortORsearch="sort";
      curButton="Inbox";
      go =app.setSort((String) sorttype.getSelectedItem(), curButton);
      changeTable();
      section.setText("Inbox"); 

    }
  });
  
  JButton sent = new JButton("");
  sent.setOpaque(false);
  sent.setContentAreaFilled(false);
  //sent.setBorderPainted(false);
  sent.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	sortORsearch="sort";
      curButton="Sent";
      go =app.setSort((String) sorttype.getSelectedItem(), curButton);
       changeTable();
       section.setText("Sent"); 

    }
  });
  
  JButton drafts = new JButton("");
  drafts.setOpaque(false);
  drafts.setContentAreaFilled(false);
 // drafts.setBorderPainted(false);
  drafts.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	sortORsearch="sort";
      curButton="Draft";
      go =app.setSort((String) sorttype.getSelectedItem(), curButton);
      changeTable();
      section.setText("Drafts"); 

    }
  });
  
  JButton info = new JButton("");
  info.setOpaque(false);
  info.setContentAreaFilled(false);
 // info.setBorderPainted(false);*/
  info.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	dispose();
    	info inf = new info(app);
    	inf.setVisible(true);
    }
  });
  
  JButton contacts = new JButton("");
  contacts.setOpaque(false);
  contacts.setContentAreaFilled(false);
 // contacts.setBorderPainted(false);*/
  contacts.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	dispose();
    	Contacts con = new Contacts(app);
    	con.setVisible(true);
    }
  });
  
  JButton trash = new JButton("");
  trash.setOpaque(false);
  trash.setContentAreaFilled(false);
 // trash.setBorderPainted(false);*/
  trash.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	sortORsearch="sort";
      curButton="Trash";
      go =app.setSort((String) sorttype.getSelectedItem(), curButton);
      changeTable();
      section.setText("Trash"); 

    }
  });
  
  JButton logout = new JButton("");
  logout.setOpaque(false);
  logout.setContentAreaFilled(false);
  logout.setBorderPainted(false);
  logout.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      dispose();
        //Start Signin = new Start();
      Start Signin = new Start();
        
        Signin.frmMailServerApp.setVisible(true);
    }
  });
  
  JButton exit = new JButton("");
  exit.setOpaque(false);
  exit.setContentAreaFilled(false);
  exit.setBorderPainted(false);
  exit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  });
  
  JButton minimize = new JButton("");
  minimize.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      setState(JFrame.ICONIFIED);
    }
  });
  minimize.setOpaque(false);
  minimize.setContentAreaFilled(false);
  minimize.setBorderPainted(false);
  
  JButton sort = new JButton("");
  sort.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
       go =app.setSort((String) sorttype.getSelectedItem(), curButton);
     changeTable();
     sortORsearch="sort";
    }
  });
  sort.setOpaque(false);
  sort.setContentAreaFilled(false);
  sort.setBorderPainted(false);
  
   
  JButton search = new JButton("");
  search.setOpaque(false);
  search.setContentAreaFilled(false);
  search.setBorderPainted(false);
  search.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         go =app.setfilter(searchtxt.getText(),(String)searchtype.getSelectedItem(), curButton);
     changeTable();
     sortORsearch="search";
     searchtxt.setText("");
      }
    });
  
  JButton refresh = new JButton("");
  refresh.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	if(sortORsearch.equals("sort")) {
 		     go =app.setSort((String) sorttype.getSelectedItem(), curButton);
 		}else {
 			go =app.setfilter(searchtxt.getText(),(String)searchtype.getSelectedItem(), curButton);
 		}
 		changeTable();
    }
  });refresh.setOpaque(false);
  refresh.setContentAreaFilled(false);
  refresh.setBorderPainted(false);
  
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
  
  String[] arr =new String[4] ;
  arr[0]="Date";arr[1]="Name";arr[2]="Priority";arr[3]="subjects";
   searchtype = new JComboBox(arr);
  searchtype.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
  searchtype.setForeground(Color.DARK_GRAY);
  searchtype.setBackground(new Color(255, 255, 255));
  searchtype.setSelectedItem("Search"); 
  searchtype.setEditable(false);
  
  sorttype = new JComboBox(arr);
  sorttype.setForeground(Color.DARK_GRAY);
  sorttype.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
  sorttype.setEditable(false);
  sorttype.setBackground(new Color(240, 255, 255));
  sorttype.setSelectedItem("Sort");
  
 
  searchtxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
  searchtxt.setColumns(10);
  
  JButton Delete = new JButton("Delete");
  Delete.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent arg0) {
	  		ILinkedList index=new SLinkedList();
	  		if(curButton.equals("Trash")|| curButton.equals("Important")) {
	  				JOptionPane.showMessageDialog(null,"You can't delete mails from Trash or Important","Mails",JOptionPane.ERROR_MESSAGE);
	  				return;
	  		}
	  		for(int i=0;i<go.size();i++) {
	  			if((boolean)table.getValueAt(i,6)==true) {
	  				IMail mail=new mail();
	  				
	  				mail=app.readIMailtxt((String)go.get(i)+".txt");
	  				index.add(mail);
	  			}
	  		}
	  		app.deleteEmails(index);
	  		if(sortORsearch.equals("sort")) {
	  		     go =app.setSort((String) sorttype.getSelectedItem(), curButton);
	  		}else {
	  			go =app.setfilter(searchtxt.getText(),(String)searchtype.getSelectedItem(), curButton);
	  		}
	  		changeTable();
	  	}
	  });
  
  JButton ToDraft = new JButton("Important");
  
  ToDraft.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent e) {
	  		ILinkedList index=new SLinkedList();
	  		if(curButton.equals("Trash")||curButton.equals("Important")) {
	  			JOptionPane.showMessageDialog(null,"You can't move mails to Important from Trash or Important","Mails",JOptionPane.ERROR_MESSAGE);
	  			return;

	  		}
	  		for(int i=0;i<go.size();i++) {
	  			if((boolean)table.getValueAt(i,6)==true) {
	  				IMail mail=new mail();
	  				
	  				mail=app.readIMailtxt((String)go.get(i)+".txt");
	  				index.add(mail);
	  			}
	  		}
	  		IFolder s=new folder(app.curr.getImportant());
	  		app.moveEmails(index,s );
	  		changeTable();
	  	}
	  });
  
  JButton important = new JButton("");
  important.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		curButton="Important";
        go =app.setSort((String) sorttype.getSelectedItem(), curButton);
        changeTable();
        section.setText("Important"); 
  	}
  });
  important.setOpaque(false);
  important.setContentAreaFilled(false);
  
    section = new JLabel("Inbox");
    section.setFont(new Font("Bodoni MT Condensed", Font.PLAIN, 30));
    section.setOpaque(false);
    
  section.setForeground(Color.WHITE);
 // important.setBorderPainted(false);*/
  GroupLayout gl_contentPane = new GroupLayout(contentPane);
  gl_contentPane.setHorizontalGroup(
  	gl_contentPane.createParallelGroup(Alignment.TRAILING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(62)
  					.addComponent(logout, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
  					.addGap(59))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  						.addComponent(sent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(drafts, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(info, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(contacts, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(trash, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(important, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(Inbox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
  						.addComponent(compose, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
  					.addPreferredGap(ComponentPlacement.RELATED)))
  			.addGap(9)
  			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 1178, GroupLayout.PREFERRED_SIZE)
  			.addContainerGap())
  		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(346)
  					.addComponent(searchtype, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(387)
  					.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
  			.addGap(18)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addComponent(section, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED, 666, Short.MAX_VALUE)
  					.addComponent(minimize, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
  					.addGap(46)
  					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
  					.addGap(23))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
  					.addGap(30)
  					.addComponent(search, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
  					.addComponent(sorttype, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
  					.addPreferredGap(ComponentPlacement.RELATED)
  					.addComponent(sort, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
  					.addGap(68)
  					.addComponent(Delete, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
  					.addGap(5)
  					.addComponent(ToDraft)
  					.addGap(27))))
  );
  gl_contentPane.setVerticalGroup(
  	gl_contentPane.createParallelGroup(Alignment.LEADING)
  		.addGroup(gl_contentPane.createSequentialGroup()
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  				.addComponent(sorttype, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  						.addComponent(refresh, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
  						.addComponent(exit, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
  						.addComponent(minimize, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addContainerGap()
  							.addComponent(section)))
  					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addGap(25)
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
  								.addComponent(sort, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
  								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
  									.addComponent(searchtxt, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
  									.addComponent(searchtype, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))))
  						.addGroup(gl_contentPane.createSequentialGroup()
  							.addGap(37)
  							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
  								.addComponent(Delete)
  								.addComponent(ToDraft)))))
  				.addComponent(search, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
  			.addPreferredGap(ComponentPlacement.RELATED)
  			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(57)
  					.addComponent(compose, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
  					.addGap(53)
  					.addComponent(Inbox, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(sent, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(drafts, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(info, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(contacts, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(trash, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
  					.addGap(1)
  					.addComponent(important, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
  					.addGap(32)
  					.addComponent(logout, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
  					.addContainerGap(81, Short.MAX_VALUE))
  				.addGroup(gl_contentPane.createSequentialGroup()
  					.addGap(5)
  					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))))
  );
  
  table.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
          // This is for double click event on anywhere on JTable
        JTable target = (JTable) e.getSource();
          if (e.getClickCount() == 2 && target.getSelectedColumn()<6) {
             
              int row = target.getSelectedRow();
             //lll
              mail mail = new mail() ;
              dispose(); 
              // bring the required email according the row.
            mail = (classes.mail) app.readIMailtxt((String)go.get(row)+".txt");
            mailText view = new mailText(app,mail);
          
            view.setVisible(true);
             
          }
      }
   });
  contentPane.setLayout(gl_contentPane);
  //setUndecorated(true); 
 }
 public void changeTable() {
   
    int rows = dtm.getRowCount(); 
     for(int i = rows - 1; i >=0; i--)
     {
        dtm.removeRow(i); 
     }  
    for(int i=0;i<go.size();i++) {
    dtm.addRow(  app.changeMailToString(app.readIMailtxt((String)go.get(i)+".txt")));
    }
 } 
}