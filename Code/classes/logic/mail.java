package classes;

import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class mail implements IMail {
	private String sender ;
	private SLinkedList receivers ;
	private SLinkedList attachments ;
	private String subject ;
	private String date ;
	private String path ;
	private String body ;
	private String priority ;
	
	public String getSender() {
		return sender; 
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public SLinkedList getReceivers() {
		return receivers;
	}
	public void setReceivers(SLinkedList receivers) {
		this.receivers = receivers;
	}
	public SLinkedList getAttachments() {
		return attachments;
	}
	public void setAttachments(SLinkedList attachments) {
		this.attachments = attachments;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPriority() {
		return priority;
	} 
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
}
