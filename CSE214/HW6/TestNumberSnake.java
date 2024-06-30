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

        Body[] b = {
            new Body('T', '7', null),
            new Body('H','1',null),
            new Body('1','T',null),
            new Body('1','1',null),
            new Body('H','H',null),
            new Body('1','7',null),
            new Body('7','3',null),
            new Body('3','1',null),
            new Body('1','H',null)
        };
        Body result = ns.getMaxValidSnake(b);
        if(result == null){System.out.println("getMaxValidSnake: null");}
        while(result != null){
            System.out.printf("%s-", result.toString());
            result = result.next;
        }
        //(H,1)-(1,7)-(7,3)-(3,1)-(1,1)-(1,T)-

	}
}
