/**
 * Name: Hyunsung Kim
 * 
 * Do not import anything else.
 * You may add other methods and classes as needed, but do not alter what's given.
 */

public class Huffman {

	private Queueable<Node> pq; // Implement a heap-based priority queue
	private Node root; // TODO: Create your own Node class
	
	class Node implements Comparable<Node>{
		char data;
		Node parent, left, right;
		int priority;
		boolean isValid, hasVisited;

		public Node(char data, Node parent, Node left, Node right, int priority, boolean isValid){
			this.data = data;
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.priority = priority;
			this.isValid = isValid;
			hasVisited = false;
		}

		public int compareTo(Node n){
			return this.priority - n.priority;
		}

		public boolean equals(Node n){
			return this.data == n.data;
		}
	}
	
	public Huffman() {
		pq = null;
		root = null;
	}
	
	/*
	 * Perform a Huffman encoding of 'msg', and return a String containing 0s and 1s that
	 * encodes 'msg'. You MUST use a priority queue-based algorithm for this assignment.
	 * 'msg' is guaranteed to consist only of ASCII values (0 - 255). See https://www.asciitable.com/
	 */

	/*
	 * Time Complexity:
	 * 
	 * The arrays in this method are created with constant value. Therefore, arrays' time complexity is O(1).
	 * 
	 * The first for loop iterates the first index of the input string to the last index.
	 * The context of the first loop is just an assignment or a comparison, so it is constant time. This includes charAt().
	 * So the overall time complexity of the first loop is O(n), n is the length of the input string.
	 * 
	 * The second for loop iterates 256 times constantly.
	 * Node()'s time complexity is O(1) and enqueue()'s time complexity is O(logm), m is the size of pq.
	 * However, no matter how bad the input is, enqueue() gets repeated maximum 256 times.
	 * Therefore, the overall time complexity of the second loop is O(1).
	 * 
	 * The first while loop breaks when there is only one or no element in pq.
	 * If there are more than one elements in pq, it merges the first two elements and enqueue them in pq.
	 * That is, after one iteration executed, pq's size decreases by one.
	 * Therefore, the while loop repeats as many times as the size of pq.
	 * enqueue() and dequeue()'s time complexity are O(logk), k is the size of pq.
	 * Without those methods, the context of the while loop is just an assignment or a comparison. This includes Node().
	 * However, as you could see in the second for loop, the maximum size of pq is 256.
	 * Since 256*log2(256) is equal or less than 2048, the overall time complexity is upper bounded by constant value.
	 * Therefore, the time complexity is O(1).
	 * 
	 * The second while loop iterates every leaves by using back tracking.
	 * That is, the while loop repeats 4n-3 times, where n is the number of leaves.
	 * substring() method's time complexity is known to be as O(n), where n is the length of the input string.
	 * In this while loop, input string's length is the same as current node's level.
	 * However, the maximum number of leaves is 256.
	 * That is, no matter what the input string is, the maximum node's level is 255. This number is obtained by extremely skewed binary tree.
	 * Except the substring method, everything else in the context are just an assignment or a comparison.
	 * Since while loop's repetition is upper bounded by a constant and the context's time complexity is upper bounded by a constant,
	 * the overall time complexity must be upper bounded by a constant.
	 * Therefore, the second while loop's time complexity is O(1).
	 * 
	 * The last for loop iterates from the first index of input string to the last index.
	 * The context is just an assignment or a comparison.
	 * Thus, the overall time complexity of this for loop is O(n), where n is the length of the input string. 
	 * 
	 * Since the first for loop and the last for loop are not nested, the overall time complexity of this method is O(n).
	 * n is the length of the input string.
	 * 
	 * Space Complexity:
	 * 
	 * The only factor that matters to the space complexity is the dynamic allocation.
	 * 
	 * The dynamic allocation of minHeap<Node> is executed only once. So its space complexity is O(1).
	 * 
	 * The arrays in this method are created with constant value. Therefore, arrays' space complexity is O(1).
	 * 
	 * The dynamic allocation of Node associated with the enqueue method is wrapped by the for loop.
	 * However, enqueue will be executed maximum 256 times. That is, the dynamic allocation of node in this part will be executed maximum 256 times.
	 * Since its space complexity is upper bounded by a constant, its space complexity is constant.
	 * 
	 * The dynamic allocation of parentNode will be executed (n-1) times, where n is the size of pq.
	 * Since n's maximum number is 256, the space complexity is constant.
	 * 
	 * Therefore, the overall space complexity is O(1).
	 */
	public String encode(String msg) {
		pq = new minHeap<Node>(msg.length());
		int[] frequency = new int[256];

		for(int i=0;i<msg.length();i++){
			int characterIndex = (int)msg.charAt(i);
			frequency[characterIndex]++; 
		}

		for(int i=0;i<frequency.length;i++){
			if(frequency[i] != 0){
				pq.enqueue(new Node((char)i,null,null,null,frequency[i],true));
			}
		}

		while(true){
			Node firstNode = pq.dequeue();
			if(firstNode == null){
				return "";
			}
			Node secondNode = pq.dequeue();
			if(secondNode == null){
				root = firstNode;
				break;
			}

			Node parentNode = new Node('\0',null,firstNode,secondNode,firstNode.priority+secondNode.priority,false);
			firstNode.parent = parentNode;
			secondNode.parent = parentNode;
			pq.enqueue(parentNode);
		}

		String[] codeTable = new String[256];
		String code = "";
		Node currentNode = root;
		while(currentNode != null){

			if(currentNode.isValid){
				if(currentNode.parent == null){
					codeTable[(int)currentNode.data] = "0";
					break;	
				}
				Node parentNode = currentNode.parent;
				codeTable[(int)currentNode.data] = code;
				code = code.substring(0, code.length()-1);
				currentNode.hasVisited = true;
				currentNode = parentNode;
				continue;
			}

			if(currentNode.left.hasVisited == true && currentNode.right.hasVisited == true){
				if(currentNode.parent == null){
					currentNode.left.hasVisited=false;
					currentNode.right.hasVisited=false;
					break;
				}
				Node parentNode = currentNode.parent;
				code = code.substring(0, code.length()-1);
				currentNode.hasVisited = true;
				currentNode.left.hasVisited=false;
				currentNode.right.hasVisited=false;
				currentNode = parentNode;
				continue;
			}

			if(currentNode.left.hasVisited == false){
				code += "0";
				currentNode = currentNode.left;
				continue;
			}

			if(currentNode.right.hasVisited == false){
				code+= "1";
				currentNode = currentNode.right;
				continue;
			}
		}

		String returnString = "";
		for(int i=0;i<msg.length();i++){
			returnString += codeTable[(int)msg.charAt(i)];
		}

		return returnString;
	}
	
	/*
	 * Perform decoding of the binary string 'code' using the Huffman tree represented by 'this.root'.
	 * This method should return a null in case the given code cannot be decoded.
	 * (e.g., error in code, or Huffman tree doesn't exist)
	 */

	/*
	 * Time Complexity: O(n), n is the length of the input string.
	 * 
	 * Except the for loop, everything is just an assignment or a comparison. This includes charAt() and toString().
	 * The for loop iterates from the first index of the input string to the last index.
	 * Again, the content of the for loop is just an assignment or a comparison, so the content's time complexity is constant.
	 * Thus, the overall time complexity is linear.
	 * 
	 * Space Complexity: O(1).
	 * 
	 * To calculate space complexity, you have to look for dynamic allocation.
	 * However, there is no dynamic allocation in this code.
	 * The node tree is related with the dynamic allocation, but for this method, you are just using the tree.
	 * Since you are not making the tree in this method, so you don't have to worry about it.
	 * Thus, the overall space complexity is constant.
	 */
	public String decode(String code) {
		if(root == null) return null;
		Node currentNode = root;
		String returnString = "";
		for(int i=0;i<code.length();i++){
			if(root.isValid){
				if(code.charAt(i) == '1') return null;
				returnString += Character.toString(currentNode.data);
				continue;
			}
			if(code.charAt(i) == '0'){
				currentNode = currentNode.left;
				if(currentNode.isValid){
					returnString += Character.toString(currentNode.data);
					currentNode = root;
					continue;
				}
			}
			else{
				currentNode = currentNode.right;
				if(currentNode.isValid){
					returnString += Character.toString(currentNode.data);
					currentNode = root;
					continue;
				}
			}
		}
		if(currentNode != root) return null;
		return returnString;
	}
	
	/*
	 * The following two methods are just for testing purposes, and you do not have to use them in your implementation.
	 * You can use these to see what the binary representation of the original string looks like.
	 * It's probably useless in this assignment, but just in case you're curious....
	 */
	public String toBinary(String s) {
		String ret = "";
		for(int i = 0; i < s.length(); i++) 
			ret = toBinary(s.charAt(i)) + ret;
		return ret;
	}
	
	private String toBinary(int ch) {
		ch = 0xFFFF & ch; // Just want to deal with char's
		String ret = "";
		for(int i = 0; i < 16; i++) {
			ret = (ch & 1) + ret;
			ch = ch >> 1;
		}
		return ret;
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		Huffman h = new Huffman();
		String msg = "Hello world!";
		//String msg = "There is a pleasure in philosophy, and a lure even in the mirages of metaphysics, which every student feels until the coarse necessities of physical existence drag him from the heights of thought into the mart of economic strife and gain.";
		//String msg = "She sells sea shells by the sea shore.\nThe shells she sells are seashells, I\'m sure.\nAnd if she sells seashells on the seashore\nThen I\'m sure she sells seashells.";
		//String msg = "And I shall have some peace there, for peace comes dropping slow, Dropping from the veils of the morning to where the cricket sings; There midnight\'s all a glimmer, and noon a purple glow, And evening full of the linnet\'s wings.";
		//String msg = "Paying anything to roll the dice, just one more time. Some will win, some will lose, some are born to sing the blues. Oh the movie never ends it goes on and on and on and on.";
		//String msg = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaazzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		//String msg = "azazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazazaz";
		System.out.println("Original binary:\n" + h.toBinary(msg));
		String code = h.encode(msg);
		System.out.println("Compressed binary:\n" + code);
		System.out.println(h.decode(code).equals(msg)); // Original message should be reconstructed.
		System.out.println(code.length() < msg.length() * 16); // Code should be compressed.
	}
}

/*
 * Provide your priority queue implementation here.
 */
interface Queueable<E extends Comparable<E> > {
	public void enqueue(E obj);
	public E dequeue();
}

class minHeap<E extends Comparable<E>> implements Queueable<E>{

	Object[] array;
	int size;

	public minHeap(int capacity){
		array = new Object[capacity];
		size = 0;
	}

	public void enqueue(E obj){
		if(size == 0){
			array[size] = obj;
			size++;
			return;
		}

		array[size] = obj;
		percolateUp();
		size++;
	}

	public void percolateUp(){

		int targetIndex = size;
		int parentIndex = (int)((targetIndex-1)/2);

		if(array[parentIndex] == null) return;

		while(((E)array[targetIndex]).compareTo((E)array[parentIndex])<0){
			Object temp = array[targetIndex];
			array[targetIndex] = array[parentIndex];
			array[parentIndex] = temp;

			targetIndex = parentIndex;
			parentIndex = (int)((targetIndex-1)/2);

			if(array[parentIndex] == null) return;
		}

	}

	public E dequeue(){
		if(size == 0){
			return null;
		}

		Object targetObject = array[0];
		array[0] = array[size-1];
		array[size-1] = null;
		size--;

		percolateDown();

		return (E)targetObject;
	}

	public void percolateDown(){

		int targetIndex = 0;
		int leftChildIndex = 2*targetIndex +1;
		int rightChildIndex = 2*targetIndex +2;

		while(true){

			if(array[leftChildIndex] == null && array[rightChildIndex] == null){
				return;
			}

			if(array[rightChildIndex] == null){
				if(((E)array[targetIndex]).compareTo((E)array[leftChildIndex])>0){
					Object temp = array[targetIndex];
					array[targetIndex] = array[leftChildIndex];
					array[leftChildIndex] = temp;
				}
				
				return;
			}

		
			int smallerChildIndex = getSmallerChildIndex(targetIndex);

			if(((E)array[targetIndex]).compareTo((E)array[smallerChildIndex])<= 0) return;

			Object temp = array[targetIndex];
			array[targetIndex] = array[smallerChildIndex];
			array[smallerChildIndex] = temp;

			targetIndex = smallerChildIndex;
			leftChildIndex = 2*targetIndex +1;
			rightChildIndex = 2*targetIndex +2;
		}

	}

	public int getSmallerChildIndex(int targetIndex){
		int leftChildIndex = 2*targetIndex +1;
		int rightChildIndex = 2*targetIndex +2;

		if(((E)array[leftChildIndex]).compareTo((E)array[rightChildIndex])<=0)
			return leftChildIndex;
		else
			return rightChildIndex;
	}

	public boolean contains(E e){
		int currentIndex = 0;

		while(currentIndex<size && array[currentIndex] != null){
			if(((E)array[currentIndex]).equals(e))
				return true;
			currentIndex++;
		}
		return false;
	}
}

