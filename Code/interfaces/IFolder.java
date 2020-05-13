package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

public interface IFolder {
	/**
	 * @return
	 * the user folder
	 */
	public File getMail();
	/**
	 * @return
	 * the inbox folder of this user
	 */
	public File getInbox();
	/**
	 * @return 
	 * info folder of this user.
	 */
	public File getInfo();
	/**
	 * @return
	 * the important folder of this user.
	 */
	public File getImportant();
	/**
	 * @return
	 * the sent folder of this user.
	 */
	public File getSent();
	/**
	 * @return
	 * the draft folder of this user.
	 */
	public File getDraft();
	/**
	 * @return
	 * the contacts folder of this user.
	 */
	public File getContacts();
	/**
	 * @return
	 * the trash folder of this user.
	 */
	public File getTrash() ;
	
}
