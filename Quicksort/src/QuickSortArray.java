import java.util.Random;

public class QuickSortArray {
	public QuickSortArray() {
	}
	public void sort(int[] array, int min, int  max) {
		if(max > min) {
			int pivot = partition(array,min,max);
			sort(array,min,pivot-1);
			sort(array,pivot+1,max);
		}
	}
	public int partition(int[]array, int min, int max) {
		int pivot = array[min];
		int j = max;
		int i = min;
		while(i <= j) {
			if(array[j] > pivot) {
				--j;
			}
			if(array[i] <= pivot) {
				++i;
			}
			if(i < j) {
				swap(array,i,j);
			}
		}
		swap(array,j,min);
		return j;
	}
	public void swap(int [] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
		
	}
	public void print(int array[]) {
		for(int i = 0; i < array.length; i++) {
			System.out.println("Array @ index: "+ i + " Value: " + array[i]);
		}
	}
	public int [] arrayGen(int n) {
		Random rand = new Random();
		int [] ret = new int[n];
		for(int i = 0; i < n; i++) {
			ret[i] = rand.nextInt(n);
		}
		return ret;
	}
}
