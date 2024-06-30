import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// Do not import anything else

public class RecRec {

	/**
	 * @author Hyunsung Kim 116030276
	 * TODO: Implement *one* of the following two methods recursively. If you do both correctly, then you'll get 3 extra points.
	 * In addition to the code, you should also write comments clearly describing the base and recursive cases.
	 * Both methods should return the number of items equal to 'obj' contained in 'l'.
	 * For example, if l contains {"A", "a", "b", "b"} and 'obj' is "b", then 2 should be returned.
	 * 
	 * Do NOT alter 'l'! Doing so will result in 0 points. 
	 * The contents of 'l' should remain exactly the same upon completion of execution.
	 * Also, use of iterators is prohibited.
	 */


	 /*
	  * The base case is when the size of l is zero. Then it returns 0.
	  * If the size is not zero, the method removes an item in zero index and evaluates the removed item.
	  * Then, the count variable contains 0 or 1 depending on item's equality to obj.
	  * Next, the count variable evaluates the remain items in the list.
	  * That is, it recursively call the countIf method with the list l, which has 1 less items of original list, and adds the returned value.
	  * After coming back from nested recursive calls, the method put the removed item in zero index of the list.
	  * Finally, the method returns the count variable.
	  */
	public <E> int countIf(List<E> l, E obj) {
		if(l.size()==0){return 0;}
		int count = 0;
		E currentItem = l.remove(0);
		if(currentItem.equals(obj)){count = 1;}
		count += countIf(l, obj);
		l.add(0,currentItem);
		return count;
	}
	

	 /*
	  * The base case is when l has no element. Then it returns 0.
	  * If the queue is not empty, the method removes front and evaluates front.
	  * Then, the count variable contains 0 or 1 depending on item's equality to obj.
	  * Next, the count variable evaluates the remain items in queue.
	  * That is, it recursively call the countIf method with the queue l, which has 1 less items of original queue, and adds the returned value.
	  * After coming back from nested recursive calls, the method put the removed item in the front.
	  * To do this, I've made an helper method that reverses queue. 
	  * Finally, the method returns the count variable.
	  */

	public <E> int countIf(Queue<E> l, E obj) {
		try{
			l.element();
		}catch(Exception e){
			return 0;
		}
		int count = 0;
		E currentItem = l.remove();
		if(currentItem.equals(obj)){count = 1;}
		count += countIf(l,obj);
		makeQueueReverse(l);
		l.add(currentItem);
		makeQueueReverse(l);
		return count;
	}

	public <E> void makeQueueReverse(Queue<E> l){
		try{
			l.element();
		}catch(Exception e){
			return;
		}
		E currentItem = l.remove();
		makeQueueReverse(l);
		l.add(currentItem);
	}
	
}
