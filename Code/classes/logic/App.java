package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import java.io.FileWriter;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

/**
 * 
 * @author Ali Ahmed,Fares Waheed.
 *
 */

public class App implements IApp {
   
 private File mainpath;  // main Folder that hold all folders of application.
 private File email ;
 public folder curr ;
 private sort sort ;
 private filter filter ;
 

 public App()  {
   File main = new File("Server");
    main.mkdir();
     this.mainpath = main ;
 }
 
 
 public boolean signin(String email, String password) {
  
	if(!checkemails(email)) {
		return false ;
	}
		 File user = new File(mainpath+"\\"+email);
       try {  FileReader fileReader = new FileReader(user.getPath()+"\\Info.txt");//;;
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      if(password.compareTo(bufferedReader.readLine())!=0)
       {   return false; }
      
      this.email = user;
     curr = new folder(this.email);
     removeTrash();
      return true;
           } catch(IOException e) {    
      
           }
      
    
  return false;
 }

 
 public boolean signup(IContact contact) {
	 
	 if(checkemails(contact.GetEmail())) {
		 return false ;
	 }
	File trash,inbox,important,sent,draft,Contacts;
	  // add a new folder email .
	  (this.email =  new File(mainpath.getPath()+"\\"+contact.GetEmail()) ).mkdir();
	  ( trash = new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Trash") ).mkdir();
	  ( inbox = new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Inbox") ).mkdir();
	  ( sent  = new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Sent") ).mkdir();
	  ( important = new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Important") ).mkdir();
	  ( draft = new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Draft") ).mkdir();
	  ( Contacts= new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Contacts") ).mkdir();
	  
	 
	    
	  createTxts(inbox);
	  createTxts(important);
	  createTxts(sent);
	  createTxts(draft);
	  createTxts(trash);
	 
	  
	  
	  File user= new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Info.txt"); 
	 File names=new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Contacts\\names.txt");
	 File emails=new File(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Contacts\\emails.txt");
	 try {
	   names.createNewFile();
	  emails.createNewFile();
	} catch (IOException e1) {
	}
	  try {
	      FileWriter myWriter = new FileWriter(mainpath.getPath()+"\\"+contact.GetEmail()+"\\Info.txt",true);
	      myWriter.write(contact.getpassword()+"\n");
	      myWriter.write(contact.GetEmail()+"\n");
	      myWriter.write(contact.getFirstName()+"\n");
	      myWriter.write(contact.getSecondName()+"\n");
	      myWriter.write(contact.GetDate()+"\n");
	      myWriter.close();
	    } catch (IOException e) {
	    
	    }
	   
	   curr = new folder(email);
	  
	  return true  ;
	 }

 
 @Override 
 // we make our functions instead of using it.
 public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
  
 }
 
  /**
   * Apply searching and return a LinkedList of paths of the required mails.
   * @param desired
   * the String user search for.
   * @param type
   * the type we apply searching on as Name,Date...etc.
   * @param fileName
   * String of the file user search in as inbox,sent...etc
   * @return
   * return a LinkedList of paths of the required mails.
   */
 public ILinkedList setfilter(String desired,String type,String fileName) {
  
  File des = getPlace(fileName);
  File paths = new File(des.getPath()+"\\paths.txt");
  SLinkedList res = new SLinkedList();
  SLinkedList mails = new SLinkedList();
  SLinkedList index = new SLinkedList();

  if(type.equals("Name")) {
   File names = new File(des.getPath()+"\\names.txt");
   sort obj = new sort(paths,names); 
   SLinkedList kind = new SLinkedList();
   kind = (SLinkedList) obj.getType();
   filter = new filter();
  mails = (SLinkedList) obj.getPath();
  index = (SLinkedList) filter.getSearch(kind,desired);
  }
  else if(type.equals("subjects")) {
   File subject = new File(des.getPath()+"\\subjects.txt");
   sort obj = new sort(paths,subject); 
   SLinkedList kind = new SLinkedList();
   kind = (SLinkedList) obj.getType();
   filter = new filter();
   mails = (SLinkedList) obj.getPath();
   index = (SLinkedList) filter.getSearch(kind,desired);
  }
  else if(type.equals("Date")) {
	 sort obj = new sort(paths);
	 mails = (SLinkedList) obj.getPath();
	 filter  =new filter();
	 index = (SLinkedList) filter.getSearch(obj.getType(), desired);
  }
  else { // search for priority .
   File[] in = new File[2];
   in[0]  = new File(des.getPath()+"\\paths.txt");
   in[1]  = new File(des.getPath()+"\\priority.txt");
   sort obj = new sort(in); 
   SLinkedList kind = new SLinkedList();
   kind = (SLinkedList) obj.getType();
   filter = new filter();
   mails = (SLinkedList) obj.getPath();
   index = (SLinkedList) filter.getSearch(kind,desired);
  }
  
 for(int i=0;i<index.size();i++) {
  int pos = (int) index.get(i);
  res.add(mails.get(pos)); 
  }
 
 return res ; 
  }
 
 /**
  * Apply sorting and return the paths of required mails.
  * @param type
  *the type we apply sorting on as Name,Date...etc.
  * @param fileName   
  * String of the file user view mails in as inbox,sent...etc
  * @return
  * return a LinkedList of paths of the required mails.
  */
 public ILinkedList setSort(String type,String fileName) {
  
  File des = getPlace(fileName);
  File paths = new File(des.getPath()+"\\paths.txt");
  SLinkedList res = new SLinkedList();
  if(type.equals("Sort")||type.equals("Date")){
   sort = new sort(paths); 
   res = (SLinkedList) sort.getPath();
  }
  else if(type.equals("Priority")) { 
   File[] p = new File[2];
   p[0]= paths ;
   p[1] = new File(des.getPath()+"\\priority.txt");
   sort = new sort(p); 
   res = (SLinkedList) sort.getPath();
  }
  else if(type.equals("Name")) {
   File f = new File(des.getPath()+"\\names.txt");
   sort= new sort(paths,f);
   res = (SLinkedList) sort.getPath();
  }
  else { // subjects 
   File f = new File(des.getPath()+"\\subjects.txt");
   sort= new sort(paths,f);
   res = (SLinkedList) sort.getPath();
  }
  return res ;
 }
 
 // we use scrolling instead of paging.
 @Override
 public IMail[] listEmails(int page) {
	 
  return null;
 }

 
 public void deleteEmails(ILinkedList mails) {
   
	 for(int i=0;i<mails.size();i++) {
		 mail mail = (mail) mails.get(i);
		 if(repeated(curr.getTrash().getPath()+"\\paths.txt", mail.getPath())) {
			 JOptionPane.showMessageDialog(null, "The email with date " + mail.getDate().replaceAll(",", ":") +" is already existing in Trash mails.","Mails",JOptionPane.ERROR_MESSAGE);
			 continue;
		 }
		 removeAttributes(mail);
		 CreateCopyAndDelete(new File(mail.getPath()),new File(curr.getTrash().getPath()+"\\"+mail.getDate()+".txt"));      
			
		 modify_mail(mail,curr.getTrash());
	 }
  
 }
 
 	/**
 	 * Remove any thing related to the mail was deleted as we stores names and subject in a single text file.
 	 * @param mail
 	 * mail to remove any thing related to it from its place.
 	 */
 	public void removeAttributes(IMail mail) {
 		File place = getPlace(mail.getPath());
 		SLinkedList paths = new SLinkedList(); 
 		paths = (SLinkedList) paths.readFromFile(place.getPath()+"\\paths.txt");
 		int index = -1 ;
 		for(int i=0;i<paths.size();i++) {
 			if( mail.getPath().indexOf((String)paths.get(i))>=0) { 
 				index = i ;
 				break ;
 			}
 		}
 		    doDeletion(place.getPath()+"\\paths.txt", index);
			doDeletion(place.getPath()+"\\names.txt", index);
			doDeletion(place.getPath()+"\\priority.txt", index);
			doDeletion(place.getPath()+"\\subjects.txt", index);
 	}
 	
 	/**
 	 * Help the above function to do its work.
 	 * @param place
 	 * the file the user want to delete email from as : inbox,sent,.....etc.
 	 * @param index
 	 * index of the line required to be deleted from the text file.
 	 */
 	public void doDeletion(String place,int index) {
 		
 		if(index<0) // a check to avoid errors.
 			return ;
 		
 		File des = new File(place);
 		SLinkedList list = new SLinkedList();
 		list = (SLinkedList) list.readFromFile(place);
 		list.remove(index);
 		des.delete();
 		writeList(list, place);
 	}
 	/**
 	 * Get the place of mail may be inbox,sent,important,....etc.
 	 * @param mailPath
 	 * the path of the email.
 	 * @return
 	 * returns a file the user in .
 	 */
 	public File getPlace(String mailPath) {
 		if(mailPath.indexOf("Inbox")>=0) {
 			return curr.getInbox(); 
 		}
 		else if(mailPath.indexOf("Sent")>=0) {
 			return curr.getSent();
 		}
 		else if(mailPath.indexOf("Important")>=0) {
 			return curr.getImportant(); 
 		}
 		else if(mailPath.indexOf("Draft")>=0) {
 			return curr.getDraft();
 		}
 		else  { // Trash
 			return curr.getTrash();
 		}
 	}
 	/**
 	 * Write a linkedList into a text file.
 	 * @param list
 	 * a linkedList to be written.
 	 * @param place
 	 * the path of the text file we want write the linkedList in.
 	 */
 	public void writeList(ILinkedList list,String place) {

		 try {
			new File(place).createNewFile() ;
			 FileWriter m = new FileWriter(place);
			 for(int i =0;i<list.size();i++) {
				 m.write( (String)list.get(i)+"\n");
			 }
			 m.close();
		 } catch (IOException e) {
			
		} 
		
 	}
 	
 	/**
 	 * Create a copy of file and delete it.
 	 * @param f
 	 * the file we make a copy of and be deleted.
 	 * @param newF
 	 * the file we copy to.
 	 */
 	public void CreateCopyAndDelete(File f,File newF) {
 		try {
			Files.copy(f.toPath(), newF.toPath(), StandardCopyOption.REPLACE_EXISTING);
			f.delete() ;
 		} catch (IOException e) {
			
		}
 	}
 	
 public void moveEmails(ILinkedList mails, IFolder des) {
	 
	 
	 for(int i=0;i<mails.size();i++) {
		 mail mail = (mail) mails.get(i); 	 
		 if(repeated(curr.getImportant().getPath()+"\\paths.txt",mail.getPath())) {
			 JOptionPane.showMessageDialog(null, "The email with date " + mail.getDate().replaceAll(",", ":") +" is already existing in Important mails.","Mails",JOptionPane.ERROR_MESSAGE);
			 continue;
		 } 
		 try {
			//assume we setEmail here as the File_Des to move email to. 
			Files.copy(new File(mail.getPath()).toPath(),new File(des.getMail().getPath()+"\\"+mail.getDate()+".txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			
		}      
		
			modify_mail(mail,curr.getImportant());
		
	 }
 }
 public boolean repeated(String place,String desired) {
	 SLinkedList paths = new SLinkedList();
	 paths = (SLinkedList) paths.readFromFile(place);
			 
	 for(int i=0;i<paths.size();i++) {
		 int closeIndex = ((String)paths.get(i)).lastIndexOf("\\");
		 String a = ((String)paths.get(i)).substring(closeIndex+1) ;
		 closeIndex = desired.lastIndexOf("\\");
		 String b = desired.substring(closeIndex+1);
		 if( b.indexOf(a)>=0) {
			 return true ;
		 }
	 }
	 return false ;
 }
 
 public boolean compose(IMail email) {
	if(email.getReceivers().size()==0 || email.getPriority().equals("Priority")|| email.getSubject().equals("Subject")||email.getSubject().equals("")) 
		return false ; 
   File[] paths = readPaths(mainpath) ;
   folder[] arrf=new folder[email.getReceivers().size()]; 
   for(int i=0;i<email.getReceivers().size();i++) {
     boolean found=false;
     for(int j=0;j<paths.length;j++) {
      if(((String)email.getReceivers().get(i)).equals(paths[j].getName())) {
        found=true;
        arrf[i]=new folder(paths[j]);
        break;
      }
     }
     if(!found)
       return false;
   }
   String place = this.curr.getSent().getPath()+"\\"+email.getDate()+".txt"; // create text file .
   
   WriteMailInTxt((mail) email,place);  
   
  
    modify_mail((mail) email, this.curr.getSent());  

   for(int i=0;i<arrf.length;i++) {
     String p = arrf[i].getInbox().getPath()+"\\"+email.getDate()+".txt" ; // create text file . 
     
     WriteMailInTxt((mail) email, p); 
     
     
        modify_mail((mail) email,arrf[i].getInbox());
    
   }
   
  return true;
 }
 
 /**
  * Read all paths in any directory.
  * @param folder
  * the directory we want to read all files in .
  * @return
  * array of the required files.
  */
 public File[] readPaths(File folder )
 { 
  File[] paths = folder.listFiles();
  return paths ;
 }
 
 
/**
 * Create 4 file texts which help in apply sorting and searching. 
 * @param des
 */
 public void  createTxts(File des){
     try {
   new File(des.getPath()+"\\paths.txt").createNewFile() ;
     new File(des.getPath()+"\\subjects.txt").createNewFile();
      new File(des.getPath()+"\\names.txt").createNewFile()  ;
       new File(des.getPath()+"\\priority.txt").createNewFile();
    } catch (IOException e) {
   
  } 
 }
 
 /**
  * Read a mail from the text file it is stored in.
  * @param path
  * the path of the mail text we want to read.
  * @return
  * the required mail.
  */
 public IMail readIMailtxt(String path) {
  
  mail mail = new mail();
  mail.setPath(path);   
  SLinkedList data = new SLinkedList();
  // read all data in file text .
  data = (SLinkedList) data.readFromFile(path);
  mail.setPriority((String) data.get(0)); 
  mail.setDate((String) data.get(1));
  mail.setSubject((String) data.get(2)); 
  mail.setSender((String) data.get(3));
  boolean reciver = true , body = true ;
  
  SLinkedList receivers = new SLinkedList();
  SLinkedList attachments = new SLinkedList();
  String Body = "" ;
  for(int i =4 ;i<data.size();i++) {
   if(reciver) {
    receivers.add(data.get(i));
    if( ((String)data.get(i+1)).indexOf("@")<0) {
     reciver = false ;
     mail.setReceivers(receivers);
    }
   }
   else if(body) {
    Body = Body + (String) data.get(i) +"\n" ;
    
    if( ((String)data.get(i+1)).equals("attachments:")){
     body = false ;
     Body = Body.substring(0,Body.length()-1);
     mail.setBody(Body);
     i++;
    }
   }
   else {
    attachments.add(data.get(i));
   }
  }
  mail.setAttachments(attachments);
  return mail ; 
 }
 
 
 /**
  * Take  inputs that user entered in compose page.
  * @param reciever
  * @param body
  * the String written in to JTextField.
  * @param attachments
  * A linkedlist of attachments.
  * @param subject
  * the String written in subject JTextField.
  * @param priority
  * the priority the user chooses.
  * @return
  * an IMail contain all user inputs.
  */
 
 public IMail readMailInput(String reciever,String body,SLinkedList attachments,String subject,String priority) { 
    mail mail = new mail();  
    mail.setSender(email.getName()) ; // the name of the sender .
    GregorianCalendar date = new GregorianCalendar();
    mail.setDate( date.getTime().toString().replaceAll(":", ",")); 
    mail.setSubject(subject); 
    SLinkedList receivers = new SLinkedList(); 
    mail.setBody(body);    
    mail.setAttachments(attachments);
    mail.setPriority(priority); // temporary. 
    for(int i=0;i<reciever.length();i++) {
     int closeIndex = reciever.indexOf(",",i) ;
     if(closeIndex>0 ) {
      receivers.add(reciever.substring(i, closeIndex));
     } 
     else { 
      receivers.add(reciever.substring(i));
      break ; 
     }
    
      i = closeIndex ; 
     
    }
    mail.setReceivers(receivers);  
    return mail ;
   }
 
   /**
    *  Write an email in a text file.
    * @param mail
    * the mail to be stored.
    * @param path
    * the path to store the mail in.
    */
   public void WriteMailInTxt(mail mail,String path) { 
	   try {
    	
    		 new File(path).createNewFile() ; 
     FileWriter m = new FileWriter(path);
     m.write(mail.getPriority()+"\n");
     m.write(mail.getDate()+"\n");
     m.write(mail.getSubject()+"\n");
     m.write(mail.getSender()+"\n");  
     for(int i=0;i<mail.getReceivers().size();i++)
     {
      m.write((String) mail.getReceivers().get(i)+"\n");     
     }
     // check for draft as the place of receivers should be an empty line.
     if(mail.getReceivers().size()==0) {
    	 m.write(" \n");
     }
     m.write(mail.getBody()+"\n");     
     m.write("attachments:"+"\n");
     for(int i=0;i<mail.getAttachments().size();i++)
     {
       m.write((String) mail.getAttachments().get(i)+"\n");
     }
     m.close();
    }
    catch(Exception e) {
    
    }
    
   }
  /**
   * Write in text files that help in apply sorting and searching.(names-subjects-paths of mails-priority)
   * @param mail
   * mail to write from.
   * @param des
   * destination to write in.
   */
   public void modify_mail(mail mail ,File des) {              
	   
       FileWriter names;
	try {
		
		names = new FileWriter(des.getPath()+"\\names.txt",true);
		 if(des.getPath().indexOf("Sent")>=0 || des.getPath().indexOf("Draft")>=0 ||des.getPath().indexOf(mail.getSender())>0 ) {
	    	   if(mail.getReceivers().size()>0) {
	    		   int closeIndex = ((String)mail.getReceivers().get(0)).indexOf("@");
	               names.write( ((String)mail.getReceivers().get(0)).substring(0,closeIndex)+"\n");
	    	   }
	    	   else {
	    		   names.write("   "+"\n");
	    	   }
	       
	       }
	       else {               
	        int closeIndex = mail.getSender().indexOf("@");
	         names.write(mail.getSender().substring(0, closeIndex)+"\n"); 
	       } 
	       names.close();
	       FileWriter path = new FileWriter(des.getPath()+"\\paths.txt",true);
	       path.write(des.getPath()+"\\"+mail.getDate()+"\n");
	       path.close();
	       FileWriter priority = new FileWriter(des.getPath()+"\\priority.txt",true);
	       priority.write(mail.getPriority()+"\n");
	       priority.close();
	       FileWriter subject = new FileWriter(des.getPath()+"\\subjects.txt",true);
	       subject.write(mail.getSubject()+"\n");     
	       subject.close();
	} catch (IOException e) {
		
	}
     
      
   }
   
   /**
    * Remove mails from trash after 30 days.
    */
   public void removeTrash() {
	  String place = curr.getTrash().getPath()+"\\paths.txt" ;
	  SLinkedList paths = new SLinkedList();
	  paths = (SLinkedList) paths.readFromFile(place);
	 for(int i=0;i<paths.size();i++) {
		String p = ((String) paths.get(i));
		Path file = Paths.get(p);
		   BasicFileAttributes attr;
		try {
			attr = Files.readAttributes(file, BasicFileAttributes.class);
			long diff  = new Date().getTime() - attr.creationTime().toMillis() ; 	
			if(diff>30 *24 *60 * 60 * 1000) {
				mail m = (mail) readIMailtxt(p); 
				removeAttributes(m); 
				new File(p).delete();
			}
		} catch (IOException e) {
			
		}
	 }
   }
   
   /**
    * Read a user info.
    * @return
    * linkedList that stores the users'info.
    */
   public SLinkedList readInfo() {
	   SLinkedList info = new SLinkedList();
	   info = (SLinkedList) info.readFromFile(curr.getInfo().getPath());
		return info ;	
   }
   
   /**
    * Create an Object[] of mail to display in JTable.
    * @param mail
    * mail to be converted
    * @return
    * an Object[]
    */
   public Object[] changeMailToString(IMail mail) {
	   Object[] arr=new Object[7];
	   arr[0]=mail.getDate().replace(',', ':');
	   
	   arr[2]=mail.getSubject();
	   arr[3]=mail.getPriority();
	   arr[5]=mail.getBody();
	   if(curr.getMail().getName().compareTo(mail.getSender())==0) {
	     arr[1]=(String) mail.getReceivers().get(0);
	   }
	   else {
	     arr[1]=mail.getSender();
	   }
	     arr[4]=Integer.toString(mail.getAttachments().size());
	     arr[6]=false;
	   return arr;  
	   }
   
   /**
    * Show the mail  as an String.
    * @param mail
    * mail to be shown.
    * @return
    * an string of the mail to be shown in JTextArea.
    */
  public String mailView(mail mail) {
	  String m = "Date : "+mail.getDate().replaceAll("," , ":") +"\n" ;
	  	m = m+"Priority : "+mail.getPriority()+"\n";
	  	m = m+"Subject : "+mail.getSubject()+"\n";
	  if(mail.getSender().equals(curr.getMail().getName())){
		  m = m + "From : me\n" ;
		  m = m+ "To : \n"; 
		  for(int i =0 ;i<mail.getReceivers().size();i++) {
			 m = m+(String)mail.getReceivers().get(i)+"\n";
			 
			 m = m+"\n" ;	 
		  }
	  }
	  else {
		  m = m + "From :  " + mail.getSender()+"\n" ;
		  m = m+ "To :  me \n"; 
	  }
	  
	  
	  m = m+"\t"+mail.getBody().replaceAll("\n", "\n\t")+"\n\n" ; 
	  if(mail.getAttachments().size()>0) {
		  m = m+"Attachments : \n" ;
	  }
	  for(int i =0 ;i<mail.getAttachments().size();i++) {
			 m = m+(String)mail.getAttachments().get(i)+"\n";
		  }
	  return  m ;
  }
  
  /**
   *Apply editing info if the user want.
   * @param info
   */
 public void relplceInfo(SLinkedList info) {
	 
	 File in = curr.getInfo();
	 String p = in.getPath();
	 in.delete();
	writeList(info, p);
	 

 }
 /**
  * Add the new contact if the check equals "add" 
  * else it checks if the email is correct or not 
  * and checks if name is repeated or not 
  * @param name
  * name of the contact
  * @param emails
  * email of the contact
  * @param check
  * check is the string to know to add this contact or edit this contact
  * @return
  * false if name is repeated or wrong email else true
  */
 public boolean addConacts(String name,String emails,String check) {
     SLinkedList receivers=new SLinkedList();
     for(int i=0;i<emails.length();i++) {
         int closeIndex = emails.indexOf(",",i) ;
         if(closeIndex>0 && emails.indexOf("@mailserver.com", i)>0) {
          receivers.add(emails.substring(i, closeIndex));
         } 
         else if(emails.indexOf("@mailserver.com", i)>0) { 
          receivers.add(emails.substring(i));
          break ; 
         }
         if(closeIndex>0) {
             i = closeIndex ; 
            }else {
           	 return false ;
            }
            
        }
     if(receivers.size()==0)
    	 return false ;
  for(int i=0;i<receivers.size();i++) {
    if(!checkemails((String)receivers.get(i))) {
      return false;  }
  }
  
 SLinkedList obj= new SLinkedList();
 SLinkedList snames=(SLinkedList) obj.readFromFile(curr.getMail()+"\\Contacts\\names.txt");
 for(int i=0;i<snames.size();i++) {
	 if(name.equals(snames.get(i))) {
		 return false;
	 }
		 
 }
  
  
  if(check.equals("add")) {
  FileWriter names;
try {
  names = new FileWriter(curr.getMail()+"\\Contacts\\names.txt",true);
    names.write(name+"\n");
    names.close();
    FileWriter emmails = new FileWriter(curr.getMail()+"\\Contacts\\emails.txt",true);
    emmails.write(emails+"\n");
    emmails.close();
} catch (IOException e) {
}
}

          
  return true;
     
  }
 
 
 
 
 
  /**
   * Check if the email existed or not.
   * @param email
   * email to be checked.
   * @return
   * true if the email existed otherwise return false.
   */
   public boolean checkemails(String email) {
     File[] paths = readPaths(mainpath) ;
     
      for(int i=0;i<paths.length;i++) 
      { 
    	  if(paths[i].getName().equals(email)) 
        			return true;
    	     
       }   
      return false;
    }
   
   /**
    * Sort contacts or just return it according its time of adding.
    * @param oper
    * If it is sort, the contacts will be sorted.else not.
    * @return
    *  Two linkedlist one for contacts and other for corresponding emails.
    */
   public SLinkedList[] contactSort(String oper) {
	   SLinkedList[] res = new SLinkedList[2]; 
	   if(oper.equals("sort")) {
		   sort s = new sort(new File(curr.getContacts()+"\\emails.txt"),new File(curr.getContacts()+"\\names.txt"));
		   res[0]=new SLinkedList();
		   res[0]=(SLinkedList) s.getType();
		   res[1]=new SLinkedList();
		   res[1]=(SLinkedList) s.getPath();
	   }
	   else {
		   res[0]=new SLinkedList();
		   res[0]= (SLinkedList) res[0].readFromFile(curr.getContacts()+"\\names.txt");
		   res[1]=new SLinkedList();
		   res[1]= (SLinkedList) res[1].readFromFile(curr.getContacts()+"\\emails.txt"); 
	   }
	   return res ;
   }
   /**
    * Apply search for contacts.
    * @param desired
    * Contact to search for.
    * @return
    * Two linkedlist one for contacts and other for corresponding emails.
    */
   public SLinkedList[]contactSearch(String desired){
	   
	   SLinkedList[] res = new SLinkedList[2];
	   SLinkedList[] temp = new SLinkedList[2];
	   temp = contactSort("sort");
	   filter f =new filter();
	   SLinkedList index = (SLinkedList) f.getSearch(temp[0], desired);
	   res[0]=new SLinkedList();
	   res[1]=new SLinkedList();
	   for(int i =0;i<index.size();i++) {
		   res[0].add(temp[0].get((int) index.get(i)));
		   res[1].add(temp[1].get((int) index.get(i)));
	   }
	   return res ;
   }
   
   /**
    * Delete a contact.
    * @param name
    * name of the contact.
    * @param email
    * Email(s) of the contacts. 
    */
   public void deleteContact(String name,String email) {
	   int index = -1 ;
	  
		  index = getIndex(name,  email);
			  doDeletion(curr.getContacts().getPath()+"\\names.txt", index);
			  doDeletion(curr.getContacts().getPath()+"\\emails.txt", index);  
		   
	   }
   /**
    * Get index of line which contact stored in.
    * @param name
    * name of the contact.
    * @param email
    * Email(s) of the contacts. 
    * @return
    * the index.
    */
   private int getIndex(String name,String email) {
	   int index = -1 ;
	   SLinkedList[] temp = new SLinkedList[2];
	   temp = contactSort("s");
	   for(int j=0;j<temp[0].size();j++) {
		   if(name.equals(temp[0].get(j)) && email.equals(temp[1].get(j))) {
			   index  =  j ;
			   break ;
		   }
    }
	   return index ;
   }
   /**
    * Edit a contact.
    * @param name
    * Old name of the contact.
    * @param email
    * Old email(s) of the contacts. 
    * @param name
    * New name of the contact.
    * @param email
    * New email(s) of the contacts. 
    */
   public void editContact(String name,String email,String name1,String email1) {
	   int index = getIndex(name, email);
	   SLinkedList[] temp = new SLinkedList[2];
	   temp = contactSort("s");
	   temp[0].remove(index);
	   temp[1].remove(index);
	   temp[0].add(index, name1); 
	   temp[1].add(index, email1);
	   new File(curr.getContacts().getPath()+"\\names.txt").delete();
	   writeList(temp[0], curr.getContacts().getPath()+"\\names.txt");
	   new File(curr.getContacts().getPath()+"\\emails.txt").delete();
	   writeList(temp[1], curr.getContacts().getPath()+"\\emails.txt");
   }
   /**
    * Checks if the info user that user entered is valid or not.
    * @param first
    * First Name
    * @param second
    * Second Name
    * @param date
    * Date of birth
    * @param pass
    * Password
    * @return
    * true if this info is valid otherwise false
    */
   public boolean validInfo(String first,String second,String date,String pass) {
	 if(first.equals("First Name") || first.equals("") || second.equals("") || second.equals("Second Name")) {
			  return false;
		  }
	 else if(pass.equals("")||pass.equals("")) {
		 return false ;
	 }
	 SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
		sdf.setLenient(false);
	
		try {
			//if not valid, it will throw ParseException
			 sdf.parse(date);

		} catch (ParseException e) {

			return false ;
		}
		return true ;
   }
   
}