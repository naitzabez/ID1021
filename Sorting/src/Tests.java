import java.util.Random;


public class Tests {

	public static void main(String[] args) {
//		int array_size = 1000000;
//		int runs = 1000;
//		int array[] = unSorted(array_size);
//		int array2[] = sorted(array_size);
//		int keys[] = keygen(array_size,runs);
//		int keys2[] = keygen2(array_size,runs,array2[array2.length-1]);
//		System.out.println("Avg time unsort: " + timer_unsorted(array,keys,runs));
//		System.out.println("Avg time sort: " + timer_sorted(array2,keys2,runs));
		
//		int array_size = 1000000;
//		int runs = 1000000;
//		int array[] = sorted(array_size);
//		int key [] = keygen2(array_size, runs, array[array.length-1]);
//		System.out.println("avg time binarysearch: " + timer_binary(array,key,runs));
		
		int array_size = 10000;
		int runs = 1000;
		int array1[] = sorted(array_size);
		int array2[] = sorted(array_size);
		System.out.println("avg time duplicates: " + timer_racer(array1,array2,runs));
	}
	
	public static boolean search_unsorted(int[] array, int key) {
			for (int index = 0; index < array.length ; index++) {
				if (array[index] == key) {
				return true;
				}
			}
		return false;
	}
	public static boolean search_sorted(int[] array, int key) {
		for (int index = 0; index < array.length ; index++) {
			if(array[index] < key) {
				continue;
			}else if(array[index] == key){
				return true;
			}
			else {
				break;
			}
		}
		return false;
	}
	private static int[] sorted(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		int nxt = 0;
		for (int i = 0; i < n ; i++) {
				nxt += rnd.nextInt(10) + 1;
				array[i] = nxt;
			}
		
				return array;
		}

	private static int [] keygen(int n, int runs) {
		Random rnd = new Random();
		int keys[] = new int [runs];
		for (int i = 0; i < runs ; i++) {
			keys[i] = rnd.nextInt(n*5)+1;
		}
		return keys;
	}
	private static int [] keygen2(int n, int runs, int max) {
		Random rnd = new Random();
		int keys[] = new int [runs];
		for (int i = 0; i < runs ; i++) {
			keys[i] = rnd.nextInt(max)+1;
		}
		return keys;
	}
	private static int[] unSorted(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		for (int i = 0; i < n ; i++) {
			array[i] = rnd.nextInt(n*5)+1;
			}
				return array;
		}
	public static boolean binary_search(int[] array, int key) {
		int first = 0;
		int last = array.length-1;
		while (true) {
			int index = (last+first) / 2; // middle of array
			if (array[index] == key) {
				return true;
			}
			if (array[index] < key && index < last) { // check right hand of array
				
			first = index+1; // increase the lower limit for next iteration with index excluded
			continue;
			}
			if (array[index] > key && index > first) { //check left hand of array

			last = index-1; // lower the upper limit for next iteration with index excluded
			continue;
			}
			return false;
		}
	}
	public static int[] duplicates_search(int [] array1, int [] array2, int [] result) {
		int dupPointer = 0;
		for(int i = 0; i < array1.length; i ++) {
			if(binary_search(array2, array1[i])) {
				result[dupPointer++] = array1[i];
			}
		}
		return result;
	}
	public static int[] array_racing(int [] array1, int [] array2, int result []) {
		int index1 = 0;
		int index2 = 0;
		int dupPointer = 0; 
		while(index1 < array1.length && index2 < array2.length) {
			if(array1[index1] == array2[index2]) {
				result[dupPointer++] = array1[index1];
				index1+=1;
				index2+=1;
			}else if(array1[index1] < array2[index2]) {
				index1+=1;
			}else {
				index2+=1;
			}
		}
		return result;
	}

		private static long timer_unsorted(int [] array, int [] keys, long runs) {
			long sum = 0;
			long t0;
			long t1;
			int key = 0;
			for(int j=0; j<runs; j++) {
					key = keys[j]; // fetch random key
					t0 = System.nanoTime();
					search_unsorted(array, key); // search for key
					t1 = System.nanoTime();
					sum += (t1-t0); // add time for run to sum
			}
			System.out.println(sum);
			return sum/runs;  // return average time for all runs
			
		}
		private static long timer_sorted(int [] array, int [] keys, long runs) {
			long sum = 0;
			long t0;
			long t1;
			int key = 0;
			for(int j=0; j<runs; j++) {
					key = keys[j]; // fetch random key
					t0 = System.nanoTime();
					search_sorted(array, key); // search for key
					t1 = System.nanoTime();
					sum += (t1-t0); // add time for run to sum
			}
			System.out.println(sum);
			return sum/runs;  // return average time for all runs
			
		}
		private static long timer_binary(int [] array, int [] keys, long runs) {
			long sum = 0;
			long t0;
			long t1;
			int key = 0;
			for(int j=0; j<runs; j++) {
					key = keys[j]; // fetch random key
					t0 = System.nanoTime();
					binary_search(array, key); // search for key
					t1 = System.nanoTime();
					sum += (t1-t0); // add time for run to sum
			}
			System.out.println(sum);
			return sum/runs;  // return average time for all runs
			
		}
		private static long timer_duplicates(int [] array1, int []array2, long runs) {
			long sum = 0;
			long t0;
			long t1;
			int [] result = new int [array1.length];
			for(int j=0; j<runs; j++) {
					t0 = System.nanoTime();
					duplicates_search(array1, array2,result); // search for key
					t1 = System.nanoTime();
					sum += (t1-t0); // add time for run to sum
			}
			System.out.println(sum);
			return sum/runs;  // return average time for all runs
			
		}
		private static long timer_racer(int [] array1, int []array2, long runs) {
			long sum = 0;
			long t0;
			long t1;
			int [] result = new int [array1.length];
			for(int j=0; j<runs; j++) {
					t0 = System.nanoTime();
					array_racing(array1, array2,result); // search for key
					t1 = System.nanoTime();
					sum += (t1-t0); // add time for run to sum
			}
			System.out.println(sum);
			return sum/runs;  // return average time for all runs
			
		}


}
