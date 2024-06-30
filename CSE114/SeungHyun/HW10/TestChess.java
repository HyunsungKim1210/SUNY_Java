public class TestChess {

    public static void main(String[] args) {

	King k1 = new King('w', "a1");
	King k2 = new King('b', "e4");

	System.out.println("Is valid k1 => a2? " + k1.isValidMove("a2"));
	System.out.println("Is valid k1 => b2? " + k1.isValidMove("b2"));
	System.out.println("Is valid k1 => b1? " + k1.isValidMove("b1"));
	System.out.println("Is valid k1 => b0? " + k1.isValidMove("b0"));

	System.out.println("Is valid k2 => e6? " + k2.isValidMove("e6"));
	System.out.println("Is valid k2 => e4? " + k2.isValidMove("e4"));
	System.out.println("Is valid k2 => e5? " + k2.isValidMove("e5"));
	System.out.println("Is valid k2 => f5? " + k2.isValidMove("f5"));
	System.out.println("Is valid k2 => f4? " + k2.isValidMove("f4"));
	System.out.println("Is valid k2 => f3? " + k2.isValidMove("f3"));
	System.out.println("Is valid k2 => e3? " + k2.isValidMove("e3"));
	System.out.println("Is valid k2 => d3? " + k2.isValidMove("d3"));
	System.out.println("Is valid k2 => d4? " + k2.isValidMove("d4"));
	System.out.println("Is valid k2 => d5? " + k2.isValidMove("d5"));


	Rook r1 = new Rook('w', "e5");
	Rook r2 = new Rook('b', "a2");

	System.out.println("Is valid r1 => e8? " + r1.isValidMove("e8"));
	System.out.println("Is valid r1 => e7? " + r1.isValidMove("e7"));
	System.out.println("Is valid r1 => e6? " + r1.isValidMove("e6"));
	System.out.println("Is valid r1 => e5? " + r1.isValidMove("e5"));
	System.out.println("Is valid r1 => e4? " + r1.isValidMove("e4"));
	System.out.println("Is valid r1 => e3? " + r1.isValidMove("e3"));
	System.out.println("Is valid r1 => e2? " + r1.isValidMove("e2"));
	System.out.println("Is valid r1 => e1? " + r1.isValidMove("e1"));
	System.out.println("Is valid r1 => d8? " + r1.isValidMove("d8"));


	System.out.println("Is valid r1 => a5? " + r1.isValidMove("a5"));
	System.out.println("Is valid r1 => b5? " + r1.isValidMove("b5"));
	System.out.println("Is valid r1 => c5? " + r1.isValidMove("c5"));
	System.out.println("Is valid r1 => d5? " + r1.isValidMove("d5"));
	System.out.println("Is valid r1 => e5? " + r1.isValidMove("e5"));
	System.out.println("Is valid r1 => f5? " + r1.isValidMove("f5"));
	System.out.println("Is valid r1 => g5? " + r1.isValidMove("g5"));
	System.out.println("Is valid r1 => h5? " + r1.isValidMove("h5"));
	System.out.println("Is valid r1 => e9? " + r1.isValidMove("e9"));
	System.out.println("Is valid r1 => i6? " + r1.isValidMove("i6"));
	
	Bishop b1 = new Bishop('w',"e5");

	System.out.println("Is valid b1 => f6? "+ b1.isValidMove("f6"));
	System.out.println("Is valid b1 => e6? "+ b1.isValidMove("e6"));
    }
    


}
