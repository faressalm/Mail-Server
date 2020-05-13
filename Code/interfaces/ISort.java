package eg.edu.alexu.csd.datastructure.mailServer;

public interface ISort {
	/**
	 * @return
	 * A LinkedList of the type required in sorted form.
	 */
	public ILinkedList getType();
	/**
	 * @return
	 *  A LinkedList of the paths according to the type sorted.
	 */
	
	public ILinkedList getPath();
}
