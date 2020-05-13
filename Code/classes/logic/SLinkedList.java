package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;

public class SLinkedList implements ILinkedList{
	
	private  class SNode {
		Object data ;
		SNode next; 

	}

	 SNode head ;
	public void add(int index, Object element) { // I assume index starts  from 0 as java.api . 
		
		if(index==0)
		{
			 SNode node = new  SNode() ;
			node.data = element ;
			node.next = head ;
			head = node ;
		}
		else 
		{
			 SNode  node = new   SNode() ;
			node.data = element ;
			 SNode n = head ;
			for(int i =0 ; i<index-1;i++)
					n=n.next ;
			node.next = n.next ;
			n.next = node ;
		}
	}

	
	public void add(Object element) {
		
		SNode node = new SNode();
		node.data = element ;
		node.next = null ; 
		if(head==null)
		{
			head = node ; 
		}
		else 
		{
			SNode n = head  ;
			while(n.next!=null)
			{
				n=n.next ;
			}	
			n.next = node ;
		}
	}
	
	public Object get(int index) {
	
		
			SNode node = head ;
			for(int i =0 ;i < index ; i++)
				node=node.next ;
		
			return node.data ;		
	}

	
	public void set(int index, Object element) {
		SNode node = head ;
			for(int i =0 ;i<index;i++)
				node=node.next ;
			
			node.data = element ;
	}

	
	public void clear() {
		head = null ;
		// Garbage collector will  free the memory from nodes because there is  no pointers points to it .
		
	}

	public boolean isEmpty() {
		if(size()==0)
			return true ;
		else
			return false;
	}

	
	public void remove(int index) {
		
		if(index==0)
			head=head.next ;
		else 
		{
			SNode node = head ;
			for(int i =0;i<index-1;i++)
				node=node.next ;
			node.next = node.next.next ;
		}
	}

	
	public int size() {
		if(head == null)
			return 0;
		else 
			{
			 	SNode n = head ;
				int count =1 ;
				while(n.next!=null)
				{
					count++;
					n=n.next ;
				}
				return count ;
			}
	}

	public ILinkedList sublist(int fromIndex, int toIndex) {
		ILinkedList sub = new  SLinkedList()  ;
		
			SNode node = head ; 
			for(int i=0;i<fromIndex;i++)
				node=node.next ;
			while(fromIndex<=toIndex)
				{
					sub.add(node.data);
					node = node.next ;
					fromIndex++;
				}
		
			return sub;
	}
	public boolean contains(Object o) {
		SNode node = head ;
		
		while(node.next!=null)
		{
			if(node.data==o)
				return true ;
			node=node.next ;
		}
		if(node.data==o) // check the last node or if the size=1 ;
			return true;
		
		return false;
	}
	/**
	 * Read a text file
	 * @param path
	 * path of the text file
	 * @return
	 * A LinkedList of the text content
	 */
	public ILinkedList readFromFile(String path)
	{
		
		ILinkedList list = new SLinkedList();
		
		 String word = "";
		 BufferedReader objReader = null;
		  try { 
		   String strCurrentLine;
		   objReader = new BufferedReader(new FileReader(path));   // path of the file that will be read with its extension.
		   while ((strCurrentLine = objReader.readLine()) != null) {
			 word=strCurrentLine ;
			
			 list.add(word);
		   }

		  } catch (IOException e) {
		   e.printStackTrace();

		  } finally {
		   try {
		    if (objReader != null)
		     objReader.close();
		   } catch (IOException ex) {
		    ex.printStackTrace();
		   }
		  }
		  return list ;
	}
	
}
