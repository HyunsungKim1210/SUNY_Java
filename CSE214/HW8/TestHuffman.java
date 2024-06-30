public class TestHuffman {
    public static void main(String[] args) {
        Huffman h = new Huffman();
        String msg1 = "err1";
        String code1 = h.encode(msg1);

        code1 += 1;
        if(h.decode(code1) != null){
            System.out.println(h.decode(code1));
            System.out.println("It should be a null.");
        }
        
        code1 = h.encode(msg1);

        Huffman h1 = new Huffman();
        if(h1.decode(code1) != null){
            System.out.println(h1.decode(code1));
            System.out.println("It should be a null.");
        }
            
    }
}
