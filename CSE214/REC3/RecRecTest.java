import java.util.*;

public class RecRecTest {
	
	
	public static void main(String[] args) {
		RecRec r = new RecRec();
		ArrayList<Integer> a = new ArrayList<Integer>();
		Queue<Integer> l = new LinkedList<Integer>();
		
		for(int i = 0; i < 100; i++) {
			a.add(i % 5); // 0 1 2 3 4 0 1 2 3 4 0 1 2 3 4 0 1 2 3 4 ....
			l.add(i);     // 0 1 2 3 4 5 6 7 8 9 10 ....
		}
		l.add(5);
		l.add(5);
		int nCorr = 0;
		if(r.countIf(a, 5) != 0 || a.size() != 100) System.out.println("Wrong1");
		else {
			for(int i = 0; i < 100; i++) {
				if(a.get(i) != (i % 5)) {
					nCorr--;
					System.out.println("List modified");
					break;
				}
			}
			nCorr++;
		}

		if(r.countIf(l, 5) != 3 || l.size() != 102) System.out.println("Wrong2");
		else {
			Iterator<Integer> it = l.iterator();
			int ind = 0;
			while(it.hasNext()) {
				int x = it.next();
				if(x != ind) {
					nCorr--;
					System.out.println("Queue modified");
					System.out.println(x+" "+ind);
					break;
				}
				ind++;
			}
			nCorr++;
		}
		
		System.out.print("Final correctness score: ");
		if(nCorr == 2) System.out.println("8");
		else if(nCorr == 1) System.out.println("5");
		else System.out.println("0");
	}
}
