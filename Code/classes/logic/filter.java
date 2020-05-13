package classes;
import eg.edu.alexu.csd.datastructure.mailServer.ILinkedList;

public class filter implements eg.edu.alexu.csd.datastructure.mailServer.IFilter {
  private ILinkedList index=new SLinkedList();
  
   public ILinkedList getSearch(ILinkedList type,String desired) {
        this.index=BinarySearch(type, desired);
        return index;
      }
   
   /**
    * Apply binary search algorithm
    * @param type
    * type to apply searching on.
    * @param desired
    * the string to search for.
    * @return 
    * the linkedList of the result.
    */
  private ILinkedList BinarySearch(ILinkedList type,String desired) {
         stack s=new stack();
         ILinkedList index=new SLinkedList();
         //search the list from left to right
    int left=0;
    int right=type.size()-1;
    int mid=right/2;
    s.push(mid);
    String midstring;
    while(!s.isEmpty()&&left<=right) {
      //get the middle of the mails
      if(isInteger(desired)) {
       midstring= (String) type.get((int) s.pop());
      }
      else {
       midstring =getSubstring(desired.length(), type,(int) s.pop());
      }
      if(midstring.compareTo(desired)==0) {
        //save the middle value 
       int temp=mid;
       //check if mails before the middle one is the same
       while(mid-1>=0) {
         String check=getSubstring(desired.length(), type,mid-1);
         if(isInteger(desired)) {
           check=(String)type.get(mid-1);
         }
         if(check.compareTo(desired)==0) {
         s.push(mid-1);
         mid--;}
         else {break;}
       }
       //evacuate the stack in the list
       while(!s.isEmpty()) {
         index.add(s.pop());
       }
       index.add(temp);
       //return mid to its value
       mid=temp;
      //check if mails after the middle one is the same
       while(mid+1<type.size()) {
         String check=getSubstring(desired.length(), type,mid+1);
         if(isInteger(desired)) {
           check=(String)type.get(mid+1);
         }
         if(check.compareTo(desired)==0) {
           index.add(mid+1);
           mid++;
         }
         else {break;}
         
       }
      }else {
        if(midstring.compareTo(desired)>0) {
          //search in the left of the middle only
        right=mid-1;
      }else {//search in the right of the middle only
        left=mid+1;
      }   
        mid=(left+right)/2;
        s.push(mid);
      }
    }
    
    return index;
    
  }
/**
 * Get the substring of an element in a ILinkedList. 
 * @param length
 * length of the desired String search of.
 * @param type
 * A ILinkedList
 * @param i
 * the index of the element
 * @return
 * the element itself it its length < length , otherwise returns a substring of the same length.
 */
  private String getSubstring(int length,ILinkedList type,int i) {
    String x=(String) type.get(i);
    if(x.length()<length) {
       return x;
    }else {
      return x.substring(0, length);
    }
    
    
  }
  /**
   * Check if the string is Integer or not.
   * @param exp
   * the expression to be checked.
   * @return
   * true if it is integer,otherwise false
   */
  private boolean isInteger(String  exp) {
	
	 try {
		 Integer.parseInt(exp);
	 }
	 catch( NumberFormatException e) {
		 return false;
	 }
	  
    return true;
    
  }

  
}