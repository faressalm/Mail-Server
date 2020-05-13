package eg.edu.alexu.csd.datastructure.mailServer;

public interface IContact {
	/**
	 * Set the email.
	 * @param Email
	 * the email of the contact.
	 */
public void setEmail(String Email);
/**
 * Get the email
 * @return
 * the email of the contact.
 */
public String GetEmail();
/**
 * Set the Password of the contact
 * @param password
 * the password of the contact.
 */
public void setPasssword(String password);
/**
 * Get the password.
 * @return
 * the password of the email
 */
public String getpassword();
/**
 * Checks if the email is valid or not.
 * @return
 * true if it is valid , otherwise return false.
 */
public boolean valid();
/**
 * Set the first name
 * @param firstName
 */
public void setFirstName(String firstName);
/**
 * Get the first name
 * @return
 * the first name
 */
public String getFirstName();
/**
 * Set the second name
 * @param secondName
 */
public void setSecondName(String secondName);
/**
 * Get the second name
 * @return
 * the second name
 */
public String getSecondName();
/**
 * Set the date of the user.
 * @param Date
 */
public void setDate(String Date);
/**
 * Get the date of the user
 * @return
 * the date of the user.
 */
public String GetDate();
}