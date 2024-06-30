

public class TestQ2AL {
    public static void main(String[] args) {
        Q2AL<String> q = new Q2AL<String>();
        q.add("First");
        q.add("Third");
        q.add("Last");
        printQ2AL(q);//[First][Third][Last]
        q.add(1, "Second");
        printQ2AL(q);//[First][Second][Third][Last]
        q.add(0,"Head");
        q.add(q.size(),"Tail");
        printQ2AL(q);//[Head][First][Second][Third][Last][Tail]
        q.set(1,"Target");
        q.set(q.size()-2,"Target");
        q.set(0, "Front");
        q.set(q.size()-1,"Front");
        printQ2AL(q);//[Front][Target][Second][Third][Target][Front]
        System.out.println(q.indexOf("Target"));//1
        System.out.println(q.indexOf("Front"));//0
        q.clear();
        for(int i=0;i<10;i++){
            q.add(""+i);
        }
        printQ2AL(q);//[0][1][2][3][4][5][6][7][8][9]
    }

    public static void printQ2AL(Q2AL<?> q){
        for(int i=0;i<q.size();i++){
            System.out.print("["+q.get(i)+"]");
        }
        System.out.println();
    }
}
