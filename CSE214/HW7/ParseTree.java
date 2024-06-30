/**
 * Name: Hyunsung Kim
 * SBUID: 116030276
 * Do not use any unauthorized packages. 
 * The only place where you're allowed to use data structures is in the iterativeEval() method.
 * 
 * For all recursive methods, feel free to use helper methods.
 * However, your recursive implementations must not use any global variables other than 'root'.
 */
import java.util.*;
public class ParseTree {
	
	Node root; // You MUST use this tree to manage your expression.
	
	public ParseTree() {
		root = null;
	}
	
	private class Node {
		Node parent, left, right;
		// Add more necessary fields and methods
		String data;
		
		public Node(String exp) { // Modify the constructor as you see fit.
			data = exp;
			parent = null;
			left = null;
			right = null;
		}

		public int comparePrecedence(Node n){
			return getPrecedence(this) - getPrecedence(n);
		}

		public int getPrecedence(Node n){
			char operator = n.data.charAt(0);
			if(operator == '^')
				return 2;
			else if(operator == '*' || operator == '/')
				return 1;
			else if(operator == '+' || operator == '-')
				return 0;
			else
				return -1;
		}
	}
	
	/*
	 * Build a parse tree, to be pointed by root, that represents 'expression'.
	 * Implement recursively.
	 */

	 /*
	  * This just simply set the root to a node that getParseTree() returns.
	  */
	public void buildTree(String expression) {
		root = getParseTree(expression);
	}


	/*
	 * If the input expression is covered by one parenthesis-for example, (2+3)-then the method removes the redundant parenthesis and
	 * call the same method again.
	 * 
	 * The base case: If the input expression is consist of a single operand, then the method makes a node that contains the expression as data
	 * and returns the node.
	 * 
	 * Recursive case:
	 * Through getTermStartIndex(), getParseTree() can distinguish operators and terms.
	 * The getTermStartIndex() iterates from the last index of the expression string.
	 * If the last index of the string is ')', the getTermStartIndex() returns the index of '(' that matches with the last index's ')'.
	 * For example, if the expression is 1+(3*(3+1)), then the getTermStartIndex() returns 2.
	 * If the last index of the input string is not ')', then it searches for an operator and returns the operator's index + 1.
	 * If there is no operator, the getTermStartIndex() returns -1. That means that the input string is a pure operand.
	 * 
	 * Through getTermStartIndex(), getParseTree() iterates the last operator to the first iterator.
	 * It first search for + and -, then * and /, and finally ^.
	 * For each case, getParseTree() makes a subtree.
	 * The searched operator get stored in the root node.
	 * The right side of the expression string's operator is passed to getParseTree() as an recursive parameter
	 * and the recursive getParseTree() will express the recursive parameter to another subtree.
	 * The root of the returned subtree of recursive getParseTree() will be stored at the rightChild node.
	 * The same thing symmetrically happens to the left child.
	 * After connecting children nodes and the root node, the getParseTree() returns root.
	 */
	public Node getParseTree(String expression){
		if(expression.charAt(expression.length()-1) == ')' && getTermStartIndex(expression) == 0)
			return getParseTree(expression.substring(1, expression.length()-1));
		if(getTermStartIndex(expression) == -1)
			return new Node(expression);

		Node root, rightChild, leftChild;

		for(int i=getTermStartIndex(expression)-1;i>-1;i=getTermStartIndex(expression.substring(0,i))-1){
			if(expression.charAt(i) == '+' || expression.charAt(i) == '-'){
				root = new Node(expression.substring(i, i+1));
				rightChild = getParseTree(expression.substring(i+1));
				leftChild = getParseTree(expression.substring(0, i));
				root.right = rightChild;
				root.left = leftChild;
				rightChild.parent = root;
				leftChild.parent = root;
				return root;
			}
		}
		for(int i=getTermStartIndex(expression)-1;i>-1;i=getTermStartIndex(expression.substring(0,i))-1){
			if(expression.charAt(i) == '*' || expression.charAt(i) == '/'){
				root = new Node(expression.substring(i, i+1));
				rightChild = getParseTree(expression.substring(i+1));
				leftChild = getParseTree(expression.substring(0, i));
				root.right = rightChild;
				root.left = leftChild;
				rightChild.parent = root;
				leftChild.parent = root;
				return root;
			}
		}
		int operatorIndex = getTermStartIndex(expression)-1;
		root = new Node(expression.substring(operatorIndex,operatorIndex+1));
		rightChild = getParseTree(expression.substring(operatorIndex+1));
		leftChild = getParseTree(expression.substring(0, operatorIndex));
		root.right = rightChild;
		root.left = leftChild;
		rightChild.parent = root;
		leftChild.parent = root;
		return root;
	}

	public int getTermStartIndex(String expression){
		if(expression.charAt(expression.length()-1) == ')'){
			int parenthesesCount = 0;
			for(int i=expression.length()-2;i>-1;i--){
				if(expression.charAt(i) == ')') parenthesesCount++;
				if(expression.charAt(i) == '('){
					if(parenthesesCount == 0) return i;
					parenthesesCount--;
				}
			}
		}
		else{
			for(int i=expression.length()-1;i>-1;i--){
				if(isOperator(expression.charAt(i))){
					return i+1;
				}
			}
		}
		return -1;
	}

	public boolean isOperator(char c){
		if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^') return true;
		return false;
	}

	public boolean isOperator(Node n){
		if(n.data.length() != 1) return false;
		if(n.data.equals("+") || n.data.equals("-") || n.data.equals("*") ||
		 n.data.equals("/") || n.data.equals("^"))
		 	return true;
		return false;
	}
	
	/*
	 * Evaluate the expression represented by 'root'.
	 */

	/*
	 * This just simply returns calculateTree()'s returned value.
	 */
	public double eval() {
		return calculateTree(root);
	}


	/*
	 * The base case:
	 * If the input node's data is not an operator, the method changes data to double value and returns it.
	 * 
	 * The recursive case:
	 * The calculateTree() first look at the input node's data and determine which formula to use.
	 * Then, the method recursively call calculateTree() with the left child and right child of input node
	 * and execute the formula with the returned values.
	 * This is because the children of the input node are not guaranteed to be a single operand. One or all of them could be
	 * another subtree of operators and operands.
	 * 
	 * This method returns 0010010 as a default value but calculateTree() will never reach to that code.
	 * The input root is not an operator, then the method returns the root's data as double value.
	 * If it is an operator, then it must be + or - or * or / or ^.
	 */
	public double calculateTree(Node root){
		if(!isOperator(root)) return Double.parseDouble(root.data);
		char operator = root.data.charAt(0);
		switch (operator) {
			case '+':
				return calculateTree(root.left) + calculateTree(root.right);
			case '-':
				return calculateTree(root.left) - calculateTree(root.right);
			case '*':
				return calculateTree(root.left) * calculateTree(root.right);
			case '/':
				return calculateTree(root.left) / calculateTree(root.right);
			case '^':
				return Math.pow(calculateTree(root.left), calculateTree(root.right));
		}
		return 0010010;//This will never be executed. Err.
	}
	
	/*
	 * Return the original infix notation. You shouldn't just return the stored input string.
	 * Furthermore, the returned string shouldn't contain any superfluous parentheses.
	 * E.g., "((2+2))" is not allowed, although it's technically the same as "2+2".
	 */

	 /*
	  * This code just simply returns getInfixNotation()'s returned string.
	  */
	public String toString() {
		return getInfixNotation(root);
	}


	/*
	 * The base case:
	 * If the input node is not an operator, the method returns the input node's data.
	 * 
	 * The recursive case:
	 * First, string variable leftChild contains infix notation of the input node's left subtree. This is done by recursive operation.
	 * If the left subtree's root node is another operator, the input node's operator and the left child's operator get compared their precedence.
	 * If input node's precedence is higher than the child's precedence, parentheses are added to the child's string.
	 * The same thing symmetrically happens to the right child.
	 * 
	 * Finally the getInfixNotation() returns the combined string of the input node's data and children's strings.
	 */
	public String getInfixNotation(Node root){
		if(!isOperator(root)) return root.data;

		String leftChild = getInfixNotation(root.left);
		if(isOperator(root.left) && root.comparePrecedence(root.left) > 0)
			leftChild = "(" + leftChild + ")";
		String rightChild = getInfixNotation(root.right);
		if(isOperator(root.right) && root.comparePrecedence(root.right) > 0)
			rightChild = "(" + rightChild + ")";
		return leftChild + root.data + rightChild;
	}
	
	/*
	 * Return the postfix version of the expression.
	 */

	//This method just returns getPostfixNotation()'s returned string.
	public String toPostfixString() {
		return getPostfixNotation(root);
	}

	/*
	 * The base case:
	 * If the input node is not an operator, the method returns the input node's data.
	 * 
	 * The recursive case:
	 * getPostfixNotation() just simply returns input node's left child's postfix notation, right child's notation, and input node's data.
	 * It is similar to post-order traversal.
	 * Getting children's postfix notation is done by recursive call of getPostfixNotation().
	 */
	public String getPostfixNotation(Node root){
		if(!isOperator(root)) return root.data;
		return getPostfixNotation(root.left)+getPostfixNotation(root.right)+root.data;
	}

	public void printTree(){
		Queue<Node> temp = new LinkedList<Node>();
		temp.add(root);
		temp.add(new Node("\n"));
		int endCount = 0;
		while(temp.peek() != null){
			Node currentNode = temp.remove();
			if(currentNode.data == "\n"){
				System.out.println();
				temp.add(currentNode);
				endCount++;
				if(endCount == 2) return;
				continue;
			}
			if(currentNode.data == " "){
				System.out.printf("[]");
				continue;
			}
			endCount = 0;
			System.out.printf("[%s]",currentNode.data);
			if(currentNode.left != null) temp.add(currentNode.left);
			else temp.add(new Node(" "));
			if(currentNode.right != null) temp.add(currentNode.right);
			else temp.add(new Node(" "));
		}
	}
		
	/*
	 * The main() method is provided only as a reference. 
	 * No need to fill this in.
	 */
	public static void main(String[] args) {
		String exp = "(2-5)/(6+4)+1"; // Try many more expressions of your own
		ParseTree pt = new ParseTree();
		pt.buildTree(exp);
		System.out.println(pt.toString()); // Should print the same string.
		System.out.println(pt.toPostfixString()); // "25-64+/1+"
		System.out.println(pt.eval()); // 0.7, or 0.6999999999999, or something like that
	}

}
