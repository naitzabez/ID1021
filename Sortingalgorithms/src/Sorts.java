import java.util.Random;

public class Sorts {
	
	
	public static int[] selectionSort(int array[]) {
		for(int i = 0; i < array.length; i++) { //O(n)
			int candidate = i;
			for(int j = i; j < array.length; j++) { //O(n)
				if(array[j] < array[candidate]) {
					candidate = j;
				}
			}
			swap(array, i, candidate);
		}
		return array;
	}
	
	public static int[] insertionSort(int array[]) {
		for(int i = 0; i < array.length; i++) {
			for(int j = i; j > 0 && (array[j-1] > array[j]); j--) {
				swap(array,j,j-1); 
			}
		}
		return array;
	}
	
	
	public static void swap(int [] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	public static int [] arrayGen(int length) {
		int [] ret = new int[length];
		Random rand = new Random();
		for(int i = 0; i < length; i++) {
			ret[i] = rand.nextInt(100) + 1;
		}
		return ret;
	}
	
	public static void sort(int[] org) {
		if (org.length == 0)
		return;
		int[] aux = new int[org.length];
		sort(org, aux, 0, org.length -1);
	}
	
	private static void sort(int[] org, int[] aux, int lo, int hi) {
		if (lo != hi) {
			int mid = (lo + hi)/2;
			sort(org,aux,lo,mid);
			sort(org,aux,mid+1,hi);
			merge(org, aux, lo, mid, hi);
		}
	}
	private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
		for (int a = lo; a <= hi; a++) { // cpy org -> aux
			aux[a] = org[a];
		}
		int i = lo;
		int j = mid+1;
		for ( int k = lo; k <= hi; k++) { 
			if( i > mid) {
				org[k] = aux[j++];
			}else if( j > hi) {
				org[k] = aux[i++];
			}else if(aux[i] < aux[j]) { 
				org[k] = aux[i++];
			}else {
				org[k] = aux[j++]; 
			}
		}
	}
	public static void sort2(int[] org) {
		if (org.length == 0)
		return;
		int[] aux = new int[org.length];
		for(int i=0; i < org.length; i++) {
			aux[i] = org[i];
		}
		sort2(org, aux, 0, org.length -1);
	}
	private static void sort2(int[] org, int[] aux, int lo, int hi) {
		if (lo != hi) {
			int mid = (lo + hi)/2;
			sort2(aux,org,lo,mid); // low
			sort2(aux,org,mid+1,hi); //high
			merge2(org,aux, lo, mid, hi);
		}
	}
	
	private static void merge2(int[] org, int[] aux, int lo, int mid, int hi) {
			int i = lo;
			int j = mid+1;
			for ( int k = lo; k <= hi; k++) { 
				if( i > mid) {
					org[k] = aux[j++];
				}else if( j > hi) {
					org[k] = aux[i++];
				}else if(aux[i] < aux[j]) { 
					org[k] = aux[i++];
				}else {
					org[k] = aux[j++]; 
				}
			}
		}
	private static long timer_sort(int [] array1) {
		long sum = 0;
		long t0;
		long t1;
				t0 = System.nanoTime();
				sort(array1); // search for key
				t1 = System.nanoTime();
				sum = (t1-t0); // add time for run to sum
		return sum;
		
	}
	private static long timer_sort2(int [] array1) {
		long sum = 0;
		long t0;
		long t1;
				t0 = System.nanoTime();
				sort2(array1); // search for key
				t1 = System.nanoTime();
				sum = (t1-t0); // add time for run to sum
		return sum;  // return average time for all runs
		
	}
	private static long timer_sel(int [] array1) {
		long sum = 0;
		long t0;
		long t1;
				t0 = System.nanoTime();
				selectionSort(array1); // search for key
				t1 = System.nanoTime();
				sum = (t1-t0); // add time for run to sum
		return sum;  // return average time for all runs
		
	}
	private static long timer_ins(int [] array1) {
		long sum = 0;
		long t0;
		long t1;
				t0 = System.nanoTime();
				insertionSort(array1); // search for key
				t1 = System.nanoTime();
				sum = (t1-t0); // add time for run to sum
		return sum;  // return average time for all runs
		
	}
	
	public static void main(String[] args) {
		boolean selectionSort = true;
		boolean insertionSort = true;
		boolean mergeSort = false;
		boolean mergeTest = false;
		
		if(selectionSort) {
			long runs = 10000;
			int size = 10000;
			int [] array = arrayGen(size);
			long t1 = 0;
			for(int i = 0; i < runs; i++) {
				array = arrayGen(size);

				t1 += timer_sel(array);
			}
			System.out.println("Selection sort avg. time: " + t1 / runs);
		}
		if(insertionSort) {
//			int[] test = arrayGen(10);
//			for(int i = 0; i < test.length; i++) {
//				System.out.println(test[i]);
//			}
//			System.out.println("InsertionSort below");
//			test = insertionSort(test);
//			for(int i = 0; i < test.length; i++) {
//				System.out.println(test[i]);
//			}
			long runs = 10000;
			int size = 10000;
			int [] array = arrayGen(size);
			long t1 = 0;
			for(int i = 0; i < runs; i++) {
				array = arrayGen(size);
				t1 += timer_ins(array);
			}
			System.out.println("Insertion sort avg. time: " + t1 / runs);
		}
		if(mergeTest) {
			int [] array3 = arrayGen(12);
			for(int i = 0; i < array3.length; i++) {
				System.out.println(array3[i]);
			}
			System.out.println("Sorted below");
			sort2(array3);
			for(int i = 0; i < array3.length; i++) {
				System.out.println(array3[i]);
			}
			System.out.println("Speed tests below");
		}
		if(mergeSort) {
			long runs = 100000;
			int size = 1000;
			long t1 = 0;
			long t2 = 0;
			int [] array = new int [size];
			int [] array2 = new int [size];
			for(int i = 0; i < runs; i++) {
				array = arrayGen(size);
				for(int j = 0; j < array.length; j++) {
					array2[j]=array[j];
				}
				t1 += timer_sort(array);
				t2 += timer_sort2(array2);
			}
			System.out.println("Time 1 - " + t1/runs );
			System.out.println("Time 2 - " + t2/runs );
		}
	}
}
