package classes;


import java.io.File;

import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.mailServer.IStack;

public class sort implements ISort {
	
	
	SLinkedList priority = new SLinkedList();
	SLinkedList path = new SLinkedList(); 
	
	public sort(File path,File priority) { //names and subject.
		SLinkedList obj = new SLinkedList();
		this.path = (SLinkedList) obj.readFromFile(path.getPath());
		this.priority = (SLinkedList) obj.readFromFile(priority.getPath());
		 QuickSort() ;
	}
	
	public sort(File[] path) { //send path and its priority.
		SLinkedList obj = new SLinkedList();
		this.path = (SLinkedList) obj.readFromFile(path[0].getPath());
		this.priority = (SLinkedList) obj.readFromFile(path[1].getPath());
		priority();
	}
	public sort(File path) { // for the date.
		
		SLinkedList obj = new SLinkedList();
		
		this.path = (SLinkedList) obj.readFromFile(path.getPath());
		
		if(path.getPath().indexOf("Trash")>0||path.getPath().indexOf("Important")>0) {
			sortDate();
		}
		else {
			date();	
		}
	}
	
	/**
	 * Apply sorting according to date.
	 */
	private void date() {
		stack s = new stack();
		for(int i=0;i<path.size();i++)
		{
			s.push(path.get(i));
		}
		path = new SLinkedList();
		while(!s.isEmpty()) {
			path.add(s.peek());
			File f = new File((String) s.pop());
			priority.add(f.getName());
		}
	}
	
	/**
	 * Sort according to date.(using only on sort trash or important).
	 */
	private void sortDate() {
		 int size = path.size();
			
		  for(int i=1;i<size;i++) {
			  String a = (String) path.get(i);
			  path.remove(i);
			  int j =0 ;
			  for( j=0;j<i&&compare(new File(a+".txt"),new File((String) path.get(j)+".txt"))>0 ;j++) ;
			  
			  path.add(j, a); 
		  	}
		  priority = path ;
	}
	
	/**
	 * Compare to files according its time modified.(using only on sort trash or important).
	 * @param f1
	 * the first file
	 * @param f2
	 * the second file
	 * @return
	 * the difference between f2 and f1 (in MilliSeconds).
	 */
	
	private   int compare(File f1, File f2)
	{
	     return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
	}
	
	/**
	 * Apply sort according to priority using a priority queue.
	 */
	
	private void priority(){
		PriorityQueue s = new PriorityQueue();
		for(int i=0;i<priority.size();i++)
		{
			int key = 1000;
			try {
				key = Integer.parseInt((String) priority.get(i));
			}
			catch(Exception e) {
				// if catch exception then key will be 1000 in the last of sorting.(this in draft case only).
			}
			s.insert(path.get(i), key);
		}
		priority = new SLinkedList();
		path = new SLinkedList();
		while(!s.isEmpty()) {
			priority.add( Integer.toString(s.getKey()) );
			path.add(s.removeMin());
		}
	
	}
	
	/**
	 * Apply quick sort
	 */
	private void  QuickSort() {
		IStack stack = new stack();
		 stack.push(0);
		 stack.push(priority.size()); 
		while (!stack.isEmpty()) 
		{ 
			int end = (int) stack.pop(); 
			int start = (int) stack.pop();
		 if (end - start < 2) { 
			 continue;
		 } 
		int p = start + ((end - start) / 2);
		 p = partition(p, start, end);
		 
		 stack.push(p + 1);
		 stack.push(end);
		 stack.push(start);
		 stack.push(p);
		 }
			
		}
	
	/**
	 * partition the ILinkedList into smaller ILinkedList, and comparing elements to rearrange them.
	 * @param position
	 * the middle index
	 * @param start
	 * the start index
	 * @param end
	 * the end index
	 * @return
	 * the P-index
	 */
	private int partition(int position,int start,int end) {
		int l = start;
        int r = end - 2;
        String pivot = (String) priority.get(position);
        swap( position, end - 1);

        while (l < r) {
            if ( pivot.compareTo((String) priority.get(l))>0 ) {
                l++;
            } 
            else if ( pivot.compareTo((String) priority.get(r))<=0) {
                r--;
            }
            else {
                swap( l, r);
            }
        }
        int index = r;
        if (pivot.compareTo((String) priority.get(r))>0) {
            index++;
        }
        swap( end - 1, index);
        return index;
    }
	
	/**
	 * Swap two elements in a singly LinkedList.
	 * @param x
	 * the first index.
	 * @param y
	 * the second index. 
	 */
	private void swap(int x,int y)
	{
		String X = (String) priority.get(y);
		String Y = (String) priority.get(x);
		String xPath = (String) path.get(y);
		String yPath = (String) path.get(x);
		priority.add(x, X);
		priority.remove(x+1);
		priority.add(y, Y);
		priority.remove(y+1);
		
		path.add(x, xPath);
		path.remove(x+1);
		path.add(y, yPath);
		path.remove(y+1);
	}
	
	public ILinkedList getType() {
		return priority ;
	}
	
	public ILinkedList getPath() {
		return path ;
	}
}



