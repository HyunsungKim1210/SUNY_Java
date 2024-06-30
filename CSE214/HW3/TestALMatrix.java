public class TestALMatrix {
    public static void main(String[] args) {
        ALMatrix mtx = new ALMatrix(3, 3, 0);
        System.out.println(mtx);
        /*
         * 0 0 0
         * 0 0 0
         * 0 0 0
         */
        System.out.println(mtx.getNumRows() +" " + mtx.getNumCols());//3,3
        try{
            System.out.println(mtx.get(-1, 4));
        } catch (Exception e){
            System.err.println("get() err");
        }
        mtx.set(1,1,-5);
        System.out.println(mtx.get(1,1));//-5
        try{
            mtx.addRows(-1, 7, false);
        } catch(Exception e){
            System.out.println("addRows() err");
        }
        mtx.addRows(2, 7, false);
        mtx.addRows(3, 8, true);
        System.out.println(mtx);
        /*
         * 7 7 7
         * 7 7 7
         * 0 0 0
         * 0 -5 0
         * 0 0 0
         * 8 8 8
         * 8 8 8 
         * 8 8 8 
         */
        try{
            mtx.addColumns(-3, 3, false);
        } catch (Exception e){
            System.err.println("addColumns err");
        }
        mtx.addColumns(2, 2, false);
        mtx.addColumns(3, 3, true);
        System.out.println(mtx);
        /*
         * 2 2 7 7 7 3 3 3 
         * 2 2 7 7 7 3 3 3 
         * 2 2 0 0 0 3 3 3
         * 2 2 0 -5 0 3 3 3 
         * 2 2 0 0 0 3 3 3 
         * 2 2 8 8 8 3 3 3 
         * 2 2 8 8 8 3 3 3 
         * 2 2 8 8 8 3 3 3         
         */
        System.out.println(mtx.get(7, 7));//3
    }
    
}
