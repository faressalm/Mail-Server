package classes;

import java.io.File;

import eg.edu.alexu.csd.datastructure.mailServer.IFolder;

public class folder implements IFolder {
	
	private File mail ; // the path of the mail folder ;
	private File inbox ;
	private File info ;
	private File contacts ;
	private File sent ;
	private File important ;
	private File draft ;
	private File trash ;  
	
	public folder(File path) {
		setMail(path) ;
		setFolders();
	}
	/**
	 * Set all folders
	 */
	private void setFolders() {
		File [] folders = getMail().listFiles();
		for(int i =0 ;i<folders.length;i++)
		{
			if(folders[i].getName().equals("Info.txt")) {
				setInfo(folders[i]);
			}
			else if(folders[i].getName().equals("Contacts")) {
				setContacts(folders[i]);
			}
			else if(folders[i].getName().equals("Draft")) {
				setDraft(folders[i]);
			}
			else if(folders[i].getName().equals("Important")) {
				setImportant(folders[i]);
			}
			else if(folders[i].getName().equals("Sent")) {
				setSent(folders[i]);
			}
			else if(folders[i].getName().equals("Trash")) {
				setTrash(folders[i]);
			}
			else  {  
				setInbox(folders[i]);
			}
		}
	}

	public File getMail() {
		return mail;
	}
	/**
	 * Set the user folder
	 * @param mail
	 * the user folder.
	 */
	private void setMail(File mail) {
		this.mail = mail;
	}

	public File getInbox() {
		return inbox;
	}
	/**
	 * Set the inbox folder
	 * @param inbox
	 * the inbox folder.
	 */
	private void setInbox(File inbox) {
		this.inbox = inbox;
	}
	
	public File getInfo() {
		return info;
	}
	
	/**
	 * Set the info folder
	 * @param info
	 * the info folder.
	 */
	private void setInfo(File info) {
		this.info = info;
	}

	public File getImportant() {
		return important;
	}
	/**
	 * Set the important folder
	 * @param important
	 * the important folder.
	 */
	private void setImportant(File important) {
		this.important = important;
	}

	public File getSent() {
		return sent;
	}
	/**
	 * Set the sent folder
	 * @param sent
	 * the sent folder.
	 */
	private void setSent(File sent) {
		this.sent = sent;
	}
	
	public File getDraft() {
		return draft;
	}
	/**
	 * Set the draft folder
	 * @param draft
	 * the draft folder.
	 */
	private void setDraft(File draft) {
		this.draft = draft;
	}

	public File getContacts() {
		return contacts;
	}
	/**
	 * Set the contacts folder
	 * @param contacts
	 * the contacts folder.
	 */
	private void setContacts(File contacts) {
		this.contacts = contacts;
	}

	public File getTrash() {
		return trash;
	}
	/**
	 * Set the trash folder.
	 * @param trash
	 * the trash folder
	 */
	private void setTrash(File trash) {
		this.trash = trash;
	}
	
	
}
