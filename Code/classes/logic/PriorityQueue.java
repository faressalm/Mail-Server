package classes;

import eg.edu.alexu.csd.datastructure.mailServer.IPriorityQueue;

public class PriorityQueue implements IPriorityQueue{
	
	private class Node {
	    private Object data ;
		private Node next ;
		private int key ;
		public Node (Object data,Node next,int key)
		{
			this.setData(data) ;
			this.setNext(next) ;
			this.setKey(key);
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
	}
	
	private Node min ;
	private int size =0 ;
	
	public void insert(Object item, int key) {
		// assume key is postive integer.
		if(key<1)
			throw new RuntimeException("Error ,please enter a postive integer.");
		Node n = new Node (item,null,key); 
		if(size()==0) //empty priority queue.
			min = n ; 
		else 
		{
			Node node = min ;
			Node prev = null ; // to keep track for the previous element to ease the inserting.
			while(node!=null && node.getKey()<=n.getKey())
			{
				prev = node ;
				node = node.getNext();
			}
			if(prev==null)
			{
				n.setNext(min);
				min = n ;
			}
			else
			{
				n.setNext(node);
				prev.setNext(n);
			}
		}
		size ++ ;
	}

	public Object removeMin() {
		if(isEmpty())
			throw new RuntimeException("Error,the priority queue is empty.");
		Object temp = min.getData();
		min = min.getNext();
		size-- ;
		return temp ;
	}
	
	public Object min() {
		if(isEmpty())
			throw new RuntimeException("Error,the priority queue is empty.");
		return min.getData();
	}
	
	public boolean isEmpty() {
		return (size==0) ;
	}

	public int size() {
		
		return size;
	}
	
	/**
	 * @return
	 * A key of the min.
	 */
	public int getKey() {
		return min.getKey();
	}
	
}
