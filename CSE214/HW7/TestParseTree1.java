public class TestParseTree1 {
    public static void main(String[] args) {
           // Case of Instruction
       String exp1 = "1*(2-4)/7";
       ParseTree pt1 = new ParseTree();
       String exp2 = "(2-4)*(6+9)";
       ParseTree pt2 = new ParseTree();
       pt1.buildTree(exp1);
       System.out.println(pt1.toString()); // Should print the same string.
       System.out.println(pt1.toPostfixString()); // 124-*7/
       System.out.println(pt1.eval()); // -0.2857142857142857
       pt2.buildTree(exp2);
       System.out.println(pt2.toString()); // Should print the same string.
       System.out.println(pt2.toPostfixString()); // 24-69+*
       System.out.println(pt2.eval()); // -30.0
       System.out.println();

      // My case 1
       String exp3 = "1+2*(3-4)/5";
       ParseTree pt3 = new ParseTree();
       pt3.buildTree(exp3);
       System.out.println(pt3.toString()); // "1+2*(3-4)/5"
       System.out.println(pt3.toPostfixString()); // 1234-*5/+
       System.out.println(pt3.eval()); // 0.6
       System.out.println();

      // My case 2
       String exp4 = "((1+2)*3)^4";
       ParseTree pt4 = new ParseTree();
       pt4.buildTree(exp4);
       System.out.println(pt4.toString()); // "((1+2)*3)^4"
       System.out.println(pt4.toPostfixString()); // 12+3*4^
       System.out.println(pt4.eval()); // 6561.0
       System.out.println();
    }
}
