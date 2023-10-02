import java.util.Random;

public class Main {

	public static void test(int n, int r) {
		BinaryTree bt = new BinaryTree();
		bt.saturateSelf(0, n);
		System.out.println("saturated!");
		long sum = 0;
		long t1 = 0;
		long t2 = 0;
		Random rand = new Random();
		for(int i = 0; i < r; i++) {
			int s = rand.nextInt(n);
			t1 = System.nanoTime();
			bt.lookup(s);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Time ns => " + (sum / r));
	}
	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();

		test(1000000,1000000);
//		System.out.println("");
//		BinaryTree tree2 = new BinaryTree();
//		tree2.add(10,10);
//		tree2.add(9,9);
//		tree2.add(8,8);
//		tree2.add(7,7);
//		tree2.add(4,4);
//		tree2.add(3,3);
//		tree2.add(2,2);
//		tree2.add(1,1);
//		tree2.add(5,5);
//		tree2.add(13,13);
//		tree2.add(12,12);
//		tree2.add(11,11);
//		tree2.add(14,14);
//		tree2.add(15,15);
//		tree2.add(6,6);
//		for (int i : tree2) {
//			System.out.println("next value " + i);
//		}
	}
}
