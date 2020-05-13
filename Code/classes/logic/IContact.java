package classes;
public class IContact implements eg.edu.alexu.csd.datastructure.mailServer.IContact {
private String Email;
private String password;
private String firstName;
private String secondName;
private String Date;

public void setEmail(String Email) {
  this.Email=Email;
}

public String GetEmail() {
  return Email;
  
}
public void setDate(String Date) {
  this.Date=Date;
}

public String GetDate() {
  return Date;
  
}

public void setPasssword(String password) {
  this.password=password;
  
}

public String getpassword() {
  return password;
  
}


public void setFirstName(String firstName) {
   this.firstName=firstName;
}

public String getFirstName() {
  return firstName ;
}

public void setSecondName(String secondName) {
  this.secondName=secondName;
  
}

public String getSecondName() {
  
  return secondName;
}
public boolean valid() {
  if(Email.indexOf('@')<0 || Email.substring(Email.indexOf("@")).compareTo("@mailserver.com")!=0 || Email.equals("username@mailserver.com") ) {
    return false;
  }
  String a = Email.substring(Email.indexOf("@"));
   if(!a.equals("@mailserver.com")) {
	  return false ;
  }
  return true;
}

}