/**
 * Name: Hyunsung Kim
 * 
 * Implement an array-based priority queue. In fact, you can use an ArrayList to do this.
 * This does NOT mean you should use an array to implement a heap.
 * You're free to add fields and methods, but do not import anything other than the ArrayList. 
 */
import java.util.ArrayList;

public class REC6 {

	private ArrayList<Entry> arr;
	
	private class Entry {
		String data;
		int priority;
		Entry(String d, int p) {
			data = d;
			priority = p;
		}
	}
	
	public REC6() {
		arr = new ArrayList<Entry>();
	}
	
	public void enqueue(String dat, int priority) {
		ArrayList<Entry> temp = new ArrayList<Entry>();
		if(isEmpty()){
			arr.add(new Entry(dat,priority));
			return;
		}
		while(arr.get(0).priority >= priority){
			Entry e = arr.remove(0);
			temp.add(e);
			if(isEmpty()) break;
		}
		temp.add(new Entry(dat,priority));
		while(!isEmpty()){
			temp.add(arr.remove(0));
		}
		arr = temp;
	}
	
	public String dequeue() {
		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException();
		Entry e = arr.remove(0);
		return e.data;
	}
	
	public String peek() {
		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException();
		return arr.get(0).data;
	}
	
	public boolean isEmpty() {
		if(arr.size() == 0) return true;
		return false;
	}
	
	public static void main(String[] args) {
		REC6 pq = new REC6();
		pq.enqueue("1", 0);
		pq.enqueue("1", 5);
		pq.enqueue("4", 1);
		pq.enqueue("3", 4);
		pq.enqueue("2", -1);
		pq.enqueue("5", 2);
		pq.enqueue("6", 7);
		pq.enqueue("8", 1);
		
		// The following loop should print the numbers in this order:
		// 6, 1, 3, 5, 4, 8, 1, 2
		// i.e., Entries with higher priority should get printed first.
		while(!pq.isEmpty()) {
			System.out.println(pq.dequeue());
		}
		
	}
}
