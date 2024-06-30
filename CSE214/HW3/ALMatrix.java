/**
 * Do *NOT* import anything except the ArrayList class. 
 * In addition to the code, you should also write header comments that describe the 
 * time complexities of the following methods:
 * Constructor, get(), set(), addRows(), addColumns(), toString().
 * You *must* justify your answers. Don't just say "It's linear because there's a loop".
 * Remember to identify the variable of the time complexity.
 * @author: Hyunsung Kim 116030276
 *
 */

import java.util.ArrayList;

public class ALMatrix {
	private ArrayList<Integer> mtx; // You must use this variable to internally represent a matrix.
	private int numRows, numCols;
	
	/*
	 * You should create a matrix having 'numRows' rows and 'numCols' columns.
	 * The matrix should be initially contain 'initVal' for every location.
	 */
	/*
	 * O(nm), n is numRows and m is numCols.
	 * The for loop increments by 1.
	 */
	public ALMatrix(int numRows, int numCols, int initVal) {
		// TODO: Fill the rest here
		mtx = new ArrayList<Integer>();
		this.numRows = numRows;
		this.numCols = numCols;
		for(int i=0;i<numRows*numCols;i++){
			mtx.add(initVal);
		}
	}
	
	/*
	 * TODO: Implement get() and set() which retrieves and sets, respectively,
	 * the value at the given location. The indexes are all zero-based.
	 * Consider what the code should do if the given location is invalid.
	 */
	/*
	 * O(1). Random access.
	 */
	public int get(int row, int col) {
		if(!(0<=row && row < numRows && 0<= col && col< numCols)){
			throw new IndexOutOfBoundsException();
		}
		return mtx.get(row*numRows+col);
	}
	
	/*
	 * O(1). Random access.
	 */
	public int set(int row, int col, int data) {
		if(!(0<=row && row < numRows && 0<= col && col< numCols)){
			throw new IndexOutOfBoundsException();
		}
		return mtx.set(row*numRows+col,data);
	}
	
	/*
	 * TODO: Implement addRows() which adds the given number of rows,
	 * whose values are initialized to initVal.
	 * The parameter 'addToBottom' is a flag that indicates whether the
	 * new rows should be appended to the bottom of the matrix. If this
	 * value is false, then the rows are added to the top of the matrix.
	 * See the test cases in main() to gain a better understanding
	 * of the behavior of this variable.
	 */

	 /*
	  * if addToBottom is true, O(n). n is numRows times this.numCols.
	  * if addToBottom is false, O(nm). m is the size of mtx.
	  */
	public void addRows(int numRows, int initVal, boolean addToBottom) {
		if(numRows<1){
			return;
		}
		for(int i=0;i<numRows*this.numCols;i++){
			if(addToBottom){
				mtx.addLast(initVal);
			}
			else{
				mtx.addFirst(initVal);
			}
		}
		this.numRows += numRows;
	}
	
	/*
	 * TODO: Implement addColumns() which adds the given number of columns,
	 * whose values are initialized to initVal.
	 * The parameter 'addToRight' indicates whether the new columns should
	 * be added to the right side of this matrix. The new columns are added
	 * to the left of this matrix if this value is false.
	 */

	 /*
	  * O(nmk). n is this.numRows, m is numCols, and k is the size of mtx.
	  * The other for loops are O(numCols) or O(numCols * sizeOfMtx), so the first for loop decides the final big-O. 
	  */
	public void addColumns(int numCols, int initVal, boolean addToRight) {
		if(numCols<1){
			return;
		}
		for(int i=1;i<this.numRows;i++){//1,2,3
				for(int j=0;j<numCols;j++){
					mtx.add(this.numCols*(this.numRows-i), initVal);
				}
		}
		if(addToRight){
			for(int j=0;j<numCols;j++){
				mtx.addLast(initVal);
			}
		}
		else{
			for(int j=0;j<numCols;j++){
				mtx.addFirst(initVal);
			}
		}
		this.numCols += numCols;
	}

	/*
	 * TODO: Implement toString() that builds a String representation of this
	 * matrix. The matrix is represented as a sequence of numbers laid out in
	 * a numRows-by-numCols grid. See the examples given in main().
	 */

	 /*
	  * O(nm). n is this.numRows and m is this.numCols.
	  */
	public String toString() {
		String result = "";
		for(int i=0;i<this.numRows*this.numCols;i++){
			result += mtx.get(i) + " ";
			if(i % this.numCols == this.numCols-1){
				result += "\n";
			}
		}
		return result;
	}
	
	/*
	 * Don't modify or remove the following three methods.
	 * These are for testing purposes, and hence will be used by my grading script.
	 * You're free to use these in your code, however.
	 */
	public int getNumRows() { return numRows; }
	public int getNumCols() { return numCols; }
	public ArrayList<Integer> getAL() { return mtx; }
	
	public static void main(String[] s) {
		ALMatrix m = new ALMatrix(3, 4, 0);
		System.out.println(m); // Should print out zeros of 3 rows and 4 columns.
		System.out.println(m.set(0, 0, 1)); // 0
		System.out.println(m.get(0, 0));    // 1
		m.addRows(2, 4, false); // Add two rows of 4s to the top of the matrix.
		System.out.println(m);		// Should print the following
		/*
		 * 4 4 4 4
		 * 4 4 4 4
		 * 1 0 0 0
		 * 0 0 0 0
		 * 0 0 0 0
		 */
		m.addColumns(1, 5, true);  // Add one column of 5s to the right of the matrix.
		System.out.println(m);		// Should print the following
		/*
		 * 4 4 4 4 5
		 * 4 4 4 4 5
		 * 1 0 0 0 5
		 * 0 0 0 0 5
		 * 0 0 0 0 5
		 */
		System.out.println(m.get(1, 4)); // 5

	}
}