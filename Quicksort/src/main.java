import java.util.Random;

public class main {

	public static void main(String[] args) {
		long q =0,l = 0;
		long runs = 10000;
		for(int i = 0; i < runs; i++) {
			int array[] = arrayGen(1000);
			l += testLink(array);
			q += testArray(array);
		}
		System.out.println("Array in ns -> " + q/runs + " Linked list in ns -> " + l/runs);
	}
	public static void runQsa(int n) {
		QuickSortArray qsa = new QuickSortArray();
		int [] array = qsa.arrayGen(n);
		qsa.sort(array, 0, array.length-1);
		qsa.print(array);
	}
	public static long testArray(int [] array) {
		QuickSortArray qsa = new QuickSortArray();
		long t1,t2,sum = 0;
		t1 = System.nanoTime();
		qsa.sort(array, 0, array.length-1);
		t2 = System.nanoTime();
		sum += (t2-t1);
		return sum;
	}
	public static long testLink(int [] array) {
		LinkedList l = new LinkedList();
		l.fillFromArray(array);
		long t1,t2,sum = 0;
		t1 = System.nanoTime();
		l.sort(l);
		t2 = System.nanoTime();
		sum += (t2-t1);
		
		return sum;
	}
	public static int[] arrayGen(int in) {
		int[] ret = new int[in];
		Random rand = new Random();
		for(int i = 0; i < in; i++) {
			ret[i] = rand.nextInt(in);
		}
		return ret;
	}
}
