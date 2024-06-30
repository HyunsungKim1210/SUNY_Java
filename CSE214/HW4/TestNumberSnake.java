public class TestNumberSnake {
	public static void main(String[] s) {
		NumberSnake ns = new NumberSnake();
        ns.addFirst('1', '3');
        System.out.println(ns.isValidSnake());//false
        ns.addLast('3', '1');
        System.out.println(ns.isValidSnake());//false
        ns.removeLast();
        ns.addLast('0', 'H');
        ns.addFirst('H', '1');
        System.out.println(ns);//(H,1)-(1,3)-(0,H)
        System.out.println(ns.isValidSnake());//false
        ns.removeFirst();
        ns.addFirst('T', '1');
        ns.removeLast();
        ns.addLast('3', 'H');
        System.out.println(ns);//(T,1)-(1,3)-(3,H)
        System.out.println(ns.isValidSnake());//true;

        char[] chs = {'H','3','3','1','1','9','9','T'};
        ns.mergeSnakes(chs);
        System.out.println(ns);//(T,1)-(1,3)-(3,H)
        System.out.println(ns.isValidSnake());//true
        ns.removeLast();
        ns.addLast('3', 'T');
        System.out.println(ns);//(T,1)-(1,3)-(3,T)
        System.out.println(ns.isValidSnake());//false
        ns.removeChunk();
        System.out.println(ns);//(T,1)-(1,3)-(3,T)
        System.out.println(ns.isValidSnake());//false

        /*
         * false
         * false
         * (H,1)-(1,3)-(0,H)
         * false
         * (T,1)-(1,3)-(3,H)
         * true
         * (H,3)-(3,1)-(1,9)-(9,T)-(T,1)-(1,3)-(3,H)
         * false
         * (H,3)-(3,1)-(1,9)-(9,T)-(T,1)-(1,3)-(3,T)
         * true
         * (H,3)-(3,T)
         * true
         */
	}
}
