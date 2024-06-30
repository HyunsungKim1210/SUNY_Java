/**
 * Do not import or declare any packages.
 * Make sure to include your run-time analysis for each required method.
 * @author Hyunsung Kim 116030276
 *
 */
public class NumberSnake {
	
	Body first; // You must use this head pointer to manage your snake. Make sure to maintain a singly-linked snake.
	int size;
	

	//O(1). The code will be executed regardless of input data.
	public NumberSnake() {
		first = null;
		size = 0;
	}
	
	/*
	 * TODO: Should return true if this snake is a full, valid snake.
	 * A valid snake is one that starts with a head/tail and ends with a tail/head (respectively),
	 * and all adjacent numbers in the middle match.
	 * e.g., (H,1)-(1,2)-(2,T)   OR   (T,4)-(4,H)
	 */

	 //O(n). n is the size of the snake.
	 //Both getBody() and for loop iterates incrementally using the size value.
	public boolean isValidSnake() {
		if(size <= 0){
			return false;
		}
		char headNum = first.headNum;
		char tailNum = getBody(size-1).tailNum;
		switch (headNum) {
			case 'H':
				if(tailNum != 'T'){
					return false;
				}
				break;
			case 'T':
				if(tailNum != 'H'){
					return false;
				}
				break;	
		
			default:
				return false;
		} 
		if(size == 1){
			return true;
		}
		Body currentBody = first.next;
		char previousTailNum = first.tailNum;
		for(int i=1;i<size;i++){
			if(currentBody.headNum != previousTailNum){
				return false;
			}
			previousTailNum = currentBody.tailNum;
			currentBody = currentBody.next;
		}
		return true;
	}
	
	/*
	 * TODO: Given a body sequence 's', try to insert it into the current snake. 
	 * The resulting snake should also be a valid snake.
	 * e.g., s = (3,5)-(5,2)-(2,3), main = (H,4)-(4,3)-(3,T), result = (H,4)-(4,3)-(3,5)-(5,2)-(2,3)-(3,T)
	 * 's' will be formatted as follows: {c1, c2, c3, c4, c5, c6, ..., c(N-1), cN}. That is,
	 * it is a sequence of head-tail pairs. See the example in main() for a clearer idea.
	 * Return false if it's not possible to make the insertion and true otherwise.
	 * 'main' is guaranteed to be a valid snake, and 's' has length at least 2.
	 */

	 //O(n+m). n is the size of the snake and m is the length of the body sequence 's'.
	 //Every looping operation in this method iterates incrementally.
	 //Some loop use the size of the snake and others use the length of s.
	 //Since non of these loops are nested, the mergeSnakes() has linear time complexity.
	public boolean mergeSnakes(char[] s) {
		if(s.length%2 != 0){
			return false;
		}
		NumberSnake newSnake = new NumberSnake();
		for(int i=0;i<s.length/2;i++){
			if((int)s[i]<48 || (int)s[i]>57){
				return false;
			}
			newSnake.addLast(s[2*i],s[2*i+1]);
		}

		newSnake.addFirst('H', s[0]);
		newSnake.addLast(s[s.length-1],'T');
		if(!newSnake.isValidSnake()){
			return false;
		}
		newSnake.removeFirst();
		newSnake.removeLast();

		int insertStartIndex = -1;
		Body currentBody = first;
		for(int i=0;i<size;i++){
			if(currentBody.tailNum == s[0]){
				currentBody = currentBody.next;
				if(currentBody.headNum == s[s.length-1]){
					insertStartIndex = i+1;
					break;
				}
				continue;
			}
			currentBody = currentBody.next;
		}
		if(insertStartIndex == -1){
			return false;
		}

		insertSnake(newSnake, insertStartIndex);
		return true;
	}
	
	/*
	 * TODO: Remove a body sequence of the current snake that will result in a 
	 * shorter valid snake.
	 * e.g., main = (H,4)-(4,3)-(3,5)-(5,2)-(2,3)-(3,T), result = (H,4)-(4,3)-(3,T)
	 * Remove the first chunk if there are multiple removable chunks.  
	 */

	 //O(n^2). n is the size of the snake.
	 //The outer loop iterates incrementally by 1 and the inner loop also iterates incrementally by 1.
	public void removeChunk() {
		if(size < 3){
			return;
		}
		Body startBody = first;
		int startIndex = -1;
		int finishIndex = -1;
		for(int i=0; i<size-2;i++){
			Body finishBody = startBody.next.next;
			for(int j=i+2;j<size;j++){
				if(startBody.tailNum == finishBody.headNum){
					startIndex = i;
					finishIndex = j;
					break;
				}
				finishBody = finishBody.next;
			}
			if(startIndex != -1 && finishIndex != -1){
				break;
			}
			startBody = startBody.next;
		}
		if(startIndex == -1 || finishIndex == -1){
			return;
		}

		for(int i=startIndex+1;i<finishIndex;i++){
			Body targetBody = startBody.next;
			Body nextBody = targetBody.next;

			targetBody.next=null;
			startBody.next=nextBody;
			size--;
		}
	}
	
	/*
	 * TODO: Return a string representation following *exactly* this format:
	 * (c1,c2)-(c3,c4)-... and so on. The ellipsis is replaced by the rest of the sequence.
	 * There should be no white spaces (' ', '\n', '\t', etc.) anywhere in the string.
	 */

	 //O(n). n is the size of the snake. The method iterates incrementally by 1 from the first body to the last body to print.
	public String toString() {
		if(size<=0){
			return "";
		}
		else if(size == 1){
			return first.toString();
		}
		String returnString = "";
		Body currentBody = first;
		for(int i=0;i<size-1;i++){
			returnString += currentBody.toString()+"-";
			currentBody=currentBody.next;
		}
		returnString += currentBody.toString();
		return returnString;
	}
	
	/*
	 * TODO: The usual add/remove methods. 
	 * Addition/removal should happen regardless of the numbers matching. 
	 */

	// O(n), due to the getBody().
	//n is the size of the snake.
	public void addLast(char headNum, char tailNum) {
		Body newBody = new Body(headNum, tailNum, null);
		if(size == 0){
			first = newBody;
		}
		else{
			Body lastBody = getBody(size-1);
			lastBody.next = newBody;
		}
		size++;
	}
	
	//O(n), because of getBody().
	//n is the size of the snake.
	public void removeLast() {
		if(size <= 0){
			return;
		}
		if(size == 1){
			first = null;
		}
		else{
			Body previousBody = getBody(size-2);
			previousBody.next=null;
		}
		size--;
	}

	//Since Body() is O(1), the big-O of addFirst() is constant time. The code will be executed regardless of input data.
	public void addFirst(char headNum, char tailNum) {
		Body newBody = new Body(headNum, tailNum, first);
		first = newBody;
		size++;
	}
	
	//O(1). The code will be executed regardless of input data.
	public void removeFirst() {
		Body nextBody = first.next;
		first.next=null;
		first=nextBody;
		size--;
	}


	//Mostly, O(n). n is the size of the snake.
	//It has to iterate from the first body to the target body.
	//However, if the index is not valid, O(1). It will return null right away.
	public Body getBody(int index){
		if( size <= 0 || index<0 || index >= size){
			return null;
		}
		Body currentBody = first;
		for(int i=0;i<index;i++){
			currentBody = currentBody.next;
		}
		return currentBody;
	}

	

	//If base snake's size is greater than or equal to newSnake's size, then O(n). n is the base snake's size.
	//If the newSake's size is greater than the base snake's size, then O(m). m is the newSnakes's size.
	//It is not O(mn) because newSnake.getBody() and this.getBody() are not nested. They are separated.
	public boolean insertSnake(NumberSnake newSnake, int insertStartIndex){
		if(insertStartIndex == 0){
			Body newSnakeLastBody = newSnake.getBody(newSnake.size-1);
			newSnakeLastBody.next = first;
			first = newSnake.first;
			size += newSnake.size;
			return true;
		}
		if(insertStartIndex == size){
			Body lastBody = getBody(size-1);
			lastBody.next = newSnake.first;
			size += newSnake.size;
			return true;
		}
		Body previousBody = getBody(insertStartIndex-1);
		Body nextBody = previousBody.next;

		previousBody.next = newSnake.first;

		Body newSnakeLastBody = newSnake.getBody(newSnake.size-1);
		newSnakeLastBody.next = nextBody;
		size += newSnake.size;
		return true;
	}

	/* Do not modify or use this method. This will only be used for grading your code. */
	public Body getMain() { return first; }
	
	// Below is a test code given for your reference.
	public static void main(String[] s) {
		NumberSnake ns = new NumberSnake();
		ns.addFirst('H', '1');
		ns.addLast('1', 'T');
		System.out.println(ns.isValidSnake()); // true
		// The following represents a body sequence (0,1)-(1,2)-(2,3)-(3,4)-(4,1)
		char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
		ns.mergeSnakes(chs);
		String gt = "(H,1)-(1,2)-(2,3)-(3,4)-(4,1)-(1,T)";
		System.out.println(ns.toString().equals(gt)); // true
		ns.removeChunk();
		gt = "(H,1)-(1,T)";
		System.out.println(ns.toString().equals(gt)); // true
	}
}

/*
 * You may add more methods as necessary but don't change the existing code.
 * In particular, you must maintain a singly-linked representation of the snake.
 */
class Body {
	char headNum, tailNum;
	Body next;
	
	//O(1). The code will be executed regardless of input data.
	public Body(char head, char tail, Body next) {
		headNum = head;
		tailNum = tail;
		this.next = next;
		// Feel free to add more code here...
	}
	//O(1). The code will be executed regardless of input data.
	public String toString(){
		return "("+headNum+","+tailNum+")";
	}
	
}
