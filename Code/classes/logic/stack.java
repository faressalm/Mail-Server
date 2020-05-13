package classes;

import eg.edu.alexu.csd.datastructure.mailServer.IStack;

public class stack implements IStack {

	private class Node {
			Object data ;
			Node next ;
		 public Node (Object data , Node next)
		 {
			 this.data = data ;
			 this.next = next ;
		 }
	}
	private int size = 0 ;
	private	Node top ;
	
	public Object pop() {
		if(top == null)
			throw new RuntimeException("Error,the stack is empty.");
		Object data = top.data ;
		top = top.next ;
		size-- ;
		return data;
	}

	public Object peek() {
		if(top == null)
			throw new RuntimeException("Error,the stack is empty.");
		
		Object data = top.data ;
		return data;
		}

	
	public void push(Object element) {
		Node n = new Node(element,top);
		top = n ;
		size++;
	}

	public boolean isEmpty() {
		if(top==null)
			return true;
		else
			return false ;
	}

	public int size() {
		return size;
	}


}
