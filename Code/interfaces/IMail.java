package eg.edu.alexu.csd.datastructure.mailServer;

import classes.SLinkedList;

public interface IMail {
	
	/**
	 * @return
	 * the mail sender.
	 */
	public String getSender();
	/**
	 * Set the sender of the mail
	 * @param sender
	 * the sender of the mail
	 */
	public void setSender(String sender);
	/**
	 * @return
	 * A LinkedList of receivers.
	 */
	public SLinkedList getReceivers();
	/**
	 * Set the receivers of the mail
	 * @param receivers
	 * A LinkedList of receivers
	 */
	public void setReceivers(SLinkedList receivers);
	/**
	 * @return
	 * A LinkedList of attachments.
	 */
	public SLinkedList getAttachments() ;
	/**
	 * Set the attachments of the mail
	 * @param attachments
	 * A LinkedList of mail
	 */
	public void setAttachments(SLinkedList attachments);
	/**
	 * @return
	 * the mail subject.
	 */
	public String getSubject() ;
	/**
	 * Set the subject of the mail
	 * @param subject
	 * the subject of the mail
	 */
	public void setSubject(String subject) ;
	/**
	 * @return
	 * the mail date.
	 */
	public String getDate() ;
	/**
	 * Set the date of the mail
	 * @param date
	 * the date of the mail
	 */
	public void setDate(String date) ;
	/**
	 * @return
	 * the mail path.
	 */
	public String getPath();
	/**
	 * Set the mail Path
	 * @param path
	 * Path of the mail
	 */
	public void setPath(String path) ;
	/**
	 * @return
	 * the mail body.
	 */
	public String getBody() ;
	/**
	 * Set the mail body
	 * @param body
	 * body of the mail
	 */
	public void setBody(String body);
	/**
	 * @return
	 * the mail priority.
	 */
	public String getPriority() ;
	/**
	 * Set the mail priority
	 * @param priority
	 * priority of the mail.
	 */
	public void setPriority(String priority) ; 
}
