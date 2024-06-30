import java.util.Iterator;

public class TestHW10 {
    public static void main(String[] args) {
        Connectable c = new HW10();
        for(int i=0;i<7;i++){
            c.addNode('A'+i);
        }
        c.addEdge('A', 'A'+6, 1);
        c.addEdge('A', 'A'+1, 2);
        c.addEdge('A', 'A'+3, 3);
        c.addEdge('A'+1, 'A'+2, 4);
        c.addEdge('A'+2, 'A'+3, 10);
        c.addEdge('A'+1, 'A'+4, 5);
        c.addEdge('A'+4, 'A'+5, 6);
        /*
         * 
         * /---G
         * |      /---E---F
         * |      |
         * A---B--|
         * |      C
         * |      |
         * \---D--/
         * 
         */

        Iterator<Integer> it1 = c.breadthFirstIterator('A');
        while(it1.hasNext()){
            System.out.print((char)((int)it1.next())+" ");
        }
        System.out.println();
        
        Iterator<Integer> it2 = c.depthFirstIterator('A');
        while(it2.hasNext()){
            System.out.print((char)((int)it2.next())+" ");
        }
        System.out.println(); 
        
        Connectable mst = c.getMST();
        System.out.println(mst.numNodes()+" "+mst.numEdges());//7 6
        System.out.println(mst.isEdge('A'+2, 'A'+3));//false
    }
}
