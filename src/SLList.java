
/**
 * Write a description of class SLList here.
 * 
 * @author Dan Kolan
 * @version 1.0
 */
import bridges.base.SLelement;

public class SLList<E> implements List<E>{
	private SLelement<E> head;					//head of list
	private SLelement<E> tail;					//tail of list
	protected SLelement<E> curr;				//Access to current element
	private int cnt;							//size of list
	
	// Constructors
	public SLList() {
		head = new SLelement<E>();
		tail = head;
		curr = head;
		cnt = 0;
	}
	
	@Override
	// Remove all elements
	public void clear() {
		head.setNext(null);						//drop all links
		head = new SLelement<E>();			//now empty head node
		tail = head;
		curr = head;
	}

	@Override
	// Insert "it" at current position
	public void insert(E it) {
		curr.setNext(new SLelement<E>(it, curr.getNext()));	//set it's next element
		if(tail == curr) tail = curr.getNext(); //new tail
		cnt++;
	}

	@Override
	// Append "it" to end of list
	public void append(E it) {
		tail.setNext(new SLelement<E>(it, null));	//add it to tail
		tail = tail.getNext();					//make it the tail
		cnt++;
	}

	@Override
	// Remove and return current element
	//MUST setNext() to null before returning or re-inserting the item causes problems
	public E remove() {
		if(curr.getNext() == null) return null; //nothing to remove
		E it = curr.getNext().getValue();		//remember value
		if(tail == curr.getNext()) tail = curr;	//Removed last
		curr.setNext(curr.getNext().getNext()); //Remove from list
		cnt--;					
		
		return it;								//return value
	}

	@Override
	// Set curr at list start
	public void moveToStart() { 
		curr = head; 
	}

	@Override
	// Set curr at list end
	public void moveToEnd() { 
		curr = tail;	
	}

	@Override
	// Move curr one step left; no change if now at front
	public void prev() {
		SLelement<E> temp = head;		//starting point to go through list
		
		if(curr == head) {  
			//no previous element 
		} 
		else {
			while(temp.getNext() != curr)  {
				temp = temp.getNext();
			}
		}
		curr = temp;
	}

	@Override
	// Move curr one step right; no change if now at end
	public void next() {
		if (curr != tail) 
			curr = curr.getNext();
	}

	@Override
	// @return List length
	public int length() { 
		return cnt;
	}

	@Override
	// @return The position of the current element
	public int currPos() {
		SLelement<E> temp = head;
				//loop through list until index of current is found
		int i;
		for(i = 0; curr != temp; i++) {
			temp = temp.getNext();
		}
		return i;
	}

	@Override
	// Move down list to "pos" position
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <=cnt) : "Position out of range";
		curr = head;
		for(int i = 0; i < pos; i++) 
			curr = curr.getNext();
	}

	@Override
	//@return Current element value
	public E getValue() {
		if(curr.getNext() == null) 
			return null;

		return curr.getNext().getValue();
	}
	
	public SLelement<E> getFront() {
		return head;
	}
	public SLelement<E> getEnd() {
		return tail;
	}
	public SLelement<E> getCurrent() {
		return curr;
	}
	public void setCurrent(SLelement<E>  c) {
		curr = c;
	}
}
