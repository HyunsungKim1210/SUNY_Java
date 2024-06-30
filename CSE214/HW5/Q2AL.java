/**
 * Implement your Queue-based array list. 
 * It doesn't matter how you use a queue, as long as the required functionalities are properly implemented.
 * You should use the 'q' field in the Q2AL class to implement the required methods.
 * Do not import anything else other than the Queue provided. 
 * @author <Your name here>
 */
import java.util.*;

public class Q2AL<E> {
	
	private Queue<E> q;
	private int size;
	
	//O(1). It is just an simple data movement , including the constructor part.
	//The constructor just set linked list's size to zero, first and last to null. 
	public Q2AL() {
		// TODO: Fill here
		q = new LinkedList<E>();
		size=0;
	}
	
	/*
	 * TODO: Implement the following methods using 'q' and provide the time complexity analysis.
	 * Use only enqueue, dequeue, and peek operations. 
	 * Calling any other methods of Queue will result in a 0 for whatever method that contains it.
	 */

	 //모르는데? equals때문에?
	 /*
	  * If equals() is a constant time operation, O(n). n is the size of Q2AL.
	  * Except for remove(), add(), and for loop, everything is just comparison or assignment operation. This includes the constructor.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * remove() is similar to add(). It is from linkedList, the list has 'first', and all the other parts are executed on constant time. Thus, it is O(1).
	  * Since this code uses if-else condition, the for loops are not nested. 
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time.

	  * However, if equals() is overridden and does not has a constant time operation, it is complicated.
	  * If obj is null, the indexOf() does not use equals(), so it is linear time.
	  * If obj is not null, then equals() will be executed for the size of Q2AL times because of the for loop.
	  * That is, if overridden equals()'s time complexity is O(x), indexOf()'s time complexity will be O(nx). n is the size of Q2AL. 
	  * We don't know what is x because it is totally depends on the generic type E, which overrode equals().
	  */
	public int indexOf(E obj) {
		Queue<E> newQ = new LinkedList<E>();
		int initialSize = size;
		int resultIndex = -1;
		if(obj == null){
			for(int i=0;i<initialSize;i++){
				E item = q.remove();
				if(obj == item && resultIndex == -1){
					resultIndex = i;
				}
				newQ.add(item);
			}
		}
		else{
			for(int i=0;i<initialSize;i++){
				E item = q.remove();
				if(obj.equals(item) && resultIndex == -1){
					resultIndex = i;
				}
				newQ.add(item);
			}
		}
		q=newQ;
		size = initialSize;
		return resultIndex;
	}
	
	//O(1). It is just an simple return and data movement operation.
	public int size() { return size; }
	
	 /* If the input index is smaller then 0 or greater than or equal to the size of Q2AL, the method throws exception immediately and ends.
	  * In this case, O(1).
	  * O(n). n is the size of Q2AL.
	  * Except for remove(), add(), and for loop, everything is just comparison or assignment operation. This includes the constructor.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * remove() is similar to add(). It is from linkedList, the list has 'first', and all the other parts are executed on constant time. Thus, it is O(1).
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time. 
	  */
	public E set(int index, E obj) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Queue<E> newQ = new LinkedList<E>();
		E item=null;
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			if(i==index){
				item = q.remove();
				newQ.add(obj);
				continue;
			}
			newQ.add(q.remove());
		}
		q=newQ;
		size = initialSize;
		return item;
	}
	

	 /* If the input index is smaller then 0 or greater than or equal to the size of Q2AL, the method throws exception immediately and ends.
	  * In this case, O(1).
	  * O(n). n is the size of Q2AL.
	  * Except for remove(), add(), and for loop, everything is just comparison or assignment operation. This includes the constructor.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * remove() is similar to add(). It is from linkedList, the list has 'first', and all the other parts are executed on constant time. Thus, it is O(1).
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time. 
	  */
	public E get(int index) {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Queue<E> newQ = new LinkedList<E>();
		E item=null;
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			if(i==index){
				item = q.remove();
				newQ.add(item);
				continue;
			}
			newQ.add(q.remove());
		}
		q=newQ;
		size = initialSize;
		return item;
	}


	 /* If the input index is smaller then 0 or greater than the size of Q2AL, the method returns false immediately.
	  * If the input index is equal to the size of Q2AL, the method calls immediately call add() and ends. 
	  * In this case, O(1). The time complexity of add() is explained below.
	  * O(n). n is the size of Q2AL.
	  * Except for remove(), add(), and for loop, everything is just comparison or assignment operation. This includes the constructor.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * remove() is similar to add(). It is from linkedList, the list has 'first', and all the other parts are executed on constant time. Thus, it is O(1).
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time. 
	  */
	public boolean add(int index, E obj) {
		if(index < 0 || index > size){
			return false;
		}
		if(index == size){
			return add(obj);
		}
		Queue<E> newQ = new LinkedList<E>();
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			if(i==index){
				newQ.add(obj);
			}
			newQ.add(q.remove());
		}
		q=newQ;
		size = initialSize+1;
		return true;
	}

	 /*
	  * O(1).
	  * Except for add(), everything is just comparison or assignment operation.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * Since all of this code is operated on constant time, this function is executed on constant time.
	  */
	public boolean add(E obj) {
		try{
			q.add(obj);
			size++;
		}catch(Exception e){
			return false;
		}
		return true;
	}
	

	 /* If the input index is smaller then 0 or greater than or equal to the size of Q2AL, the method returns false immediately.
	  * In this case, O(1).
	  * O(n). n is the size of Q2AL.
	  * Except for remove(), add(), and for loop, everything is just comparison or assignment operation. This includes the constructor.
	  * These are operated on constant time.
	  * add() is from linkedList. It calls linkLast() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of add() is just a comparison or assignment operation, so add() has the big-O of 1.
	  * remove() is similar to add(). It is from linkedList, the list has 'first', and all the other parts are executed on constant time. Thus, it is O(1).
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time. 
	  */
	public boolean remove(int index) {
		if(index < 0 || index >= size){
			return false;
		}
		Queue<E> newQ = new LinkedList<E>();
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			if(i==index){
				q.remove();
				continue;
			}
			newQ.add(q.remove());
		}
		q=newQ;
		size = initialSize-1;
		return true;
	}
	
	 /*
	  * O(n). n is the size of Q2AL.
	  * Except for remove() and for loop, everything is just comparison or assignment operation.
	  * These are operated on constant time.
	  * remove() is from linkedList. It calls removeFirst() function. Since the linkedList has 'first', the list can directly access to the first node.
	  * All the other operation of remove() is just a comparison or assignment operation, so remove() has the big-O of 1.
	  * The for loop iterates incrementally by 1 from the first element to the last element and the inner context is executed on constant time.
	  * That makes this method executed on linear time. 
	  */
	public void clear() {
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			q.remove();
		}
		size = 0;
	}
	
	public static void main(String[] args) {
		Q2AL<Integer> a = new Q2AL<Integer>();
		for(int i = 0; i < 10; i++)
			a.add(i);
		System.out.println(a.get(2)); 
	}
}
