import java.util.Random;

public class Benchmarks {
	public static void main(String args[]) {
		int choise = 1;
		int runs = 100;
		int size = 10000000;
		int random = 10000;
		//benchHeapList2(10, 10);
		//benchHeapList1(10, 10);
		testArray(choise, runs, size, random);
		testTree(choise, runs, size, random);
	}
	public static void benchHeapList1(int size, long runs) {
		SimpleHeap sh = new SimpleHeap();
		sh.fillSelf(size);
		Random rand = new Random();
		long t1,t2,sumAdd = 0,sumRem = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			sh.remove();
			t2= System.nanoTime();
			sumRem += (t2-t1);
			Integer addy = rand.nextInt(size);
			t1 = System.nanoTime();
			sh.add(addy);
			t2= System.nanoTime();
			sumAdd +=(t2-t1);
		}
		System.out.println("Remove operation for heap1: " + sumRem/runs +" ns");
		System.out.println("Add operation for heap1: " + sumAdd/runs +" ns");
	}
	public static void benchHeapList2(int size, long runs) {
		SimpleHeap2 sh = new SimpleHeap2();
		sh.fillSelf(size);
		Random rand = new Random();
		long t1,t2,sumAdd = 0,sumRem = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			sh.remove();
			t2= System.nanoTime();
			sumRem += (t2-t1);
			Integer addy = rand.nextInt(size);
			t1 = System.nanoTime();
			sh.add(addy);
			t2= System.nanoTime();
			sumAdd +=(t2-t1);
		}
		System.out.println("Remove operation for heap2: " + sumRem/runs +" ns");
		System.out.println("Add operation for heap2: " + sumAdd/runs +" ns");
	}
	public static void testArray(int choise, int runs, int size, int random) {
		ArrayHeap ah = new ArrayHeap(size);
		ah.autoFill(size, random);
		System.out.println("fill done");
		Random rand = new Random();
		long t1,t2,sum=0;
		switch(choise) {
		case 0: 
			for(int i = 0; i < runs; i++) {
				Integer addy = rand.nextInt(10 + rand.nextInt(90));
				t1 = System.nanoTime();
				ah.sink();
				ah.bubble(addy);
				t2= System.nanoTime();
				sum +=(t2-t1);
			}
			System.out.println("Result for array bubble + sink: " + sum/runs + " ns");
			return;
		case 1:
			for(int i = 0; i < runs; i++) {
				Integer addy = rand.nextInt(10 + rand.nextInt(90));
				t1 = System.nanoTime();
				ah.push(addy);
				t2= System.nanoTime();
				sum +=(t2-t1);
			}
			System.out.println("Result for array push: " + sum/runs + " ns");
			return;
		default:
			
			return;
		}
	}
	public static void testTree(int choise, int runs, int size, int random) {
		LinkedHeap lh = new LinkedHeap();
		lh.autoFill(size, random);
		Random rand = new Random();
		long t1,t2,sum=0;
		switch(choise) {
		case 0: 
			for(int i = 0; i < runs; i++) {
				Integer addy = rand.nextInt(random);
				t1 = System.nanoTime();
				lh.remove();
				lh.add(addy);
				t2= System.nanoTime();
				sum +=(t2-t1);
			}
			System.out.println("Result for tree add + remove: " + sum/runs + " ns");
			return;
		case 1:
			Integer depth = 0;
			for(int i = 0; i < runs; i++) {
				Integer addy = rand.nextInt(10 + rand.nextInt(90));
				t1 = System.nanoTime();
				depth += lh.push(addy);
				t2= System.nanoTime();
				sum +=(t2-t1);
			}
			System.out.println("Result for tree push: " + sum/runs + " ns" + " to average depth: " + depth / runs);
			return;
		default:
			
			return;
		}
	}
}
