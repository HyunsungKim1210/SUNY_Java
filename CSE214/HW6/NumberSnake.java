
/**
 * Do not import or declare any packages.
 * Make sure to include your run-time analysis for each required method.
 * @author Hyunsung Kim 116030276
 *
 */
public class NumberSnake {
	
	Body first; // You must use this head pointer to manage your snake. Make sure to maintain a singly-linked snake.
	int size;
	Boolean isFirstTry;
	Boolean isTempSnake;
	Boolean isHeadNumH;
	

	//O(1). The code will be executed regardless of input data.
	public NumberSnake() {
		first = null;
		size = 0;
		isFirstTry = true;
		isTempSnake = false;
		isHeadNumH = true;
	}
	
	/*
	 * TODO: Should return true if this snake is a full, valid snake.
	 * A valid snake is one that starts with a head/tail and ends with a tail/head (respectively),
	 * and all adjacent numbers in the middle match.
	 * e.g., (H,1)-(1,2)-(2,T)   OR   (T,4)-(4,H)
	 */

	
	public boolean isValidSnake() {
		if(size <= 0){
			return false;
		}
		char headNum = first.headNum;
		char tailNum = getBody(size-1).tailNum;
		switch (headNum) {
			case 'H':
				if(!isFirstTry||tailNum != 'T'){
					return false;
				}
				break;
			case 'T':
				if(!isFirstTry||tailNum != 'H'){
					return false;
				}
				break;	
		
			default:
				if(isFirstTry){
					return false;
				}
				break;
		} 
		if(size == 1){
			return true;
		}
		else if(size == 2){
			if((first.tailNum != getBody(size-1).headNum) || !isNumber(first.tailNum)){
				return false;
			}
			return true;
		}

		if(first.tailNum != first.next.headNum || !isNumber(first.tailNum)){
			return false;
		}
		if(getBody(size-2).tailNum != getBody(size-1).headNum || !isNumber(getBody(size-2).tailNum)){
			return false;
		}
		Body firstBody = first;
		Body lastBody = getBody(size-1);
		boolean isValid;
		removeFirst();
		removeLast();
		isFirstTry = false;
		isValid = isValidSnake();
		isFirstTry = true;
		addFirst(firstBody);
		addLast(lastBody);
		return isValid;
	}

	public boolean isNumber(char c){
		return '0' <= c && c <= '9';
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
		removeFirst();
		returnString += currentBody.toString()+"-"+ toString();
		addFirst(currentBody);
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

	public void addLast(Body newBody){
		newBody.next = null;
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

	public void addFirst(Body firstBody){
		firstBody.next = first;
		first = firstBody;
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

	public Body getMaxValidSnake(Body[] b){
		if(!isTempSnake){
			Body[] ableArray = getFirstArray(b);
			if(ableArray == null) return null;

			int[] snakeLength = new int[ableArray.length];
			
			NumberSnake newSnake = new NumberSnake();

			for(int i=0;i<ableArray.length;i++){
				newSnake.isTempSnake = true;
				Body nextBody = ableArray[i];
				if(nextBody.headNum == 'H'){newSnake.isHeadNumH = true;}
				else{newSnake.isHeadNumH = false;}

				newSnake.addLast(nextBody);
				newSnake.getMaxValidSnake(b);

				snakeLength[i] = newSnake.size;

				newSnake.clearSnake();
			}

			int longestSnakeIndex = getLongestLengthIndex(snakeLength);

			newSnake.isTempSnake = true;
			Body nextBody = ableArray[longestSnakeIndex];
			if(nextBody.headNum == 'H'){newSnake.isHeadNumH = true;}
			else{newSnake.isHeadNumH = false;}

			newSnake.addLast(nextBody);
			newSnake.getMaxValidSnake(b);

			return newSnake.first;
		}

		int nextIndex = getNextIndex(this.getBody(size-1), b, isHeadNumH);
		if(nextIndex == -1){
			if(isValidSnake()){return null;}
			Body lastBody = this.getBody(size-1);
			removeLast();
			lastBody.clearRecord();
			if(first == null){
				return null;
			}else{
				this.getBody(size-1).addRecord(lastBody);
				b=arrayAddLast(b, lastBody);
			}
			return getMaxValidSnake(b);
		}
		Body nextBody = b[nextIndex];
		b = arrayRemove(nextIndex, b);
		addLast(nextBody);
		return getMaxValidSnake(b);
	}

	public int getLongestLengthIndex(int[] array){
		int result = 0;
		for(int i=1;i<array.length;i++){
			if(array[i] > array[result]) result = i;
		}
		return result;
	}

	public Body[] arrayRemove(int index, Body[] b){
		if(index < 0 || index >= b.length){throw new IndexOutOfBoundsException();}

		Body[] newB = new Body[b.length-1];
		for(int i=0;i<index;i++){
			newB[i] = b[i];
		}
		for(int i=index+1;i<b.length;i++){
			newB[i-1] = b[i];
		}
		return newB;
	}

	public Body[] arrayAddLast(Body[] b, Body newBody){
		Body[] newB = new Body[b.length+1];
		for(int i=0;i<b.length;i++){
			newB[i] = b[i];
		}
		newB[b.length] = newBody;
		return newB;
	}
	
	public Body[] getFirstArray(Body[] b){
		if(b.length<1) return null;
		int ableCount = 0;
		for(int i=0;i<b.length;i++){
			if(b[i].headNum == 'H' || b[i].headNum == 'T'){
				ableCount++;
			}
		}
		if(ableCount == 0) return null;
		Body[] ableArray = new Body[ableCount];
		int currentIndex = 0;
		for(int i=0;i<b.length;i++){
			if(b[i].headNum == 'H' || b[i].headNum == 'T'){
				ableArray[currentIndex] = b[i];
				currentIndex++;
			}
		}
		return ableArray;
	}

	public int getNextIndex(Body previousBody, Body[] b, boolean isHeadNumH){
		if(b.length < 1){return -1;};
		if(previousBody == null){
			for(int i=0;i<b.length;i++){
				if(b[i].headNum == 'H' || b[i].headNum == 'T'){
					return i;
				}
			}
		}
		else{
			for(int i=0;i<b.length;i++){
				if(isHeadNumH&&b[i].tailNum=='T'){continue;}
				else if(!isHeadNumH&&b[i].tailNum == 'H'){continue;}
				if((b[i].headNum == previousBody.tailNum)&& !previousBody.searchRecord(b[i]) && isNumber(b[i].headNum)){
					return i;
				}
			}
			for(int i=0;i<b.length;i++){
				if((b[i].headNum == previousBody.tailNum)&& !previousBody.searchRecord(b[i]) && isNumber(b[i].headNum)){
					return i;
				}
			}
		}
		return -1;
	}

	public void clearSnake(){
		if(size < 1) return;
		int initialSize = size;
		for(int i=0;i<initialSize;i++){
			removeLast();
		}
		isFirstTry = true;
		isTempSnake = false;
		isHeadNumH = true;
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
	Body[] bodyRecord;
	int size;
	
	//O(1). The code will be executed regardless of input data.
	public Body(char head, char tail, Body next) {
		headNum = head;
		tailNum = tail;
		this.next = next;
		// Feel free to add more code here...
		bodyRecord = new Body[10];
		size = 0;
	}

	public void grow(){
		Body[] newRecord = new Body[(int)(bodyRecord.length * 1.5)];
		for(int i=0;i<bodyRecord.length;i++){
			newRecord[i] = bodyRecord[i];
		}
		bodyRecord = newRecord;
	}

	public void addRecord(Body newBody){
		if(size >= bodyRecord.length){
			grow();
		}
		bodyRecord[size]=newBody;
		size++;
	}

	public void clearRecord(){
		for(int i=0;i<size;i++){
			bodyRecord[i] = null;
		}
		size = 0;
	}

	public boolean searchRecord(Body targetBody){
		for(int i=0;i<size;i++){
			if(bodyRecord[i].equals(targetBody)){
				return true;
			}
		}
		return false;
	}

	public boolean equals(Object obj){
		if(!(obj instanceof Body)){
			return false;
		}
		Body targetBody = (Body)obj;
		if(this.headNum == targetBody.headNum && this.tailNum == targetBody.tailNum){
			return true;
		}
		return false;
	}
	//O(1). The code will be executed regardless of input data.
	public String toString(){
		return "("+headNum+","+tailNum+")";
	}
	
}
