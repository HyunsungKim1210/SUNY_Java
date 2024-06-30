public class TestParseTree {
    public static void main(String[] args) {
        ParseTree t = new ParseTree();

        t.buildTree("a+b");
        if(!t.toString().equals("a+b")) System.out.println("1");
        if(!t.toPostfixString().equals("ab+"))  System.out.println("2");
            

        t.buildTree("c*d");
        if(!t.toString().equals("c*d")) System.out.println("3");   
        if(!t.toPostfixString().equals("cd*")) System.out.println("4");
            
        t.buildTree("a+c*e/d");
        if(!t.toString().equals("a+c*e/d")) System.out.println("5");   
        if(!t.toPostfixString().equals("ace*d/+")) System.out.println("6");  
        
        t.buildTree("(a+b)/(c-d)");
        if(!t.toString().equals("(a+b)/(c-d)")) System.out.println("7");   
        if(!t.toPostfixString().equals("ab+cd-/")) System.out.println("8");
        
        t.buildTree("2^(3*2)");
        if(!t.toString().equals("2^(3*2)")) System.out.println("9");   
        if(!t.toPostfixString().equals("232*^")) System.out.println("10");
        if(t.eval() != 64) System.out.println("11");

        t.buildTree("2^3*2");
        if(!t.toString().equals("2^3*2")) System.out.println("12");   
        if(!t.toPostfixString().equals("23^2*")) System.out.println("13");
        if(t.eval() != 16) System.out.println("14");

        t.buildTree("2*2^3");
        if(!t.toString().equals("2*2^3")) System.out.println("15");   
        if(!t.toPostfixString().equals("223^*")) System.out.println("16");
        if(t.eval() != 16) System.out.println("17");

        t.buildTree("2^(3+2)");
        if(!t.toString().equals("2^(3+2)")) System.out.println("18");   
        if(!t.toPostfixString().equals("232+^")) System.out.println("19");
        if(t.eval() != 32) System.out.println("20");       

    }
}
