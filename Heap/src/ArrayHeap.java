import java.util.Random;

public class ArrayHeap {
	private class Node{
		private Integer value = null;
		private int saturation = 0;
		public Node(Integer value) {
			this.value = value;
		}
	}
	private Node[] array;
	private int k = 0;
	public ArrayHeap(int size) {
		array = new Node[size];
	}
	public void autoFill(int size, int random) {
		Random rand = new Random();
		for(int i = 0; i < size; i++) {
			bubble(rand.nextInt(random));
		}
	}
	public int getK() {
		return k;
	}
	public void bubble2(Integer x) {
		Node temp = new Node(x);
		if(array[0] == null) {
			array[0] = temp;
			return;
		}
		int index = 0;
		Integer aux = null;
		while(array[index] != null) {
			if(array[index].value > temp.value) {
				aux = array[index].value;
				array[index].value = temp.value;
				temp.value = aux;
			}
			array[index].saturation +=1;
			if(array[leftInc(index)] == null) {// left empty
				System.out.println("added to left");
				array[leftInc(index)] = temp;
				return;
			}
			if(array[rightInc(index)] == null){// right empty
				System.out.println("added to right");
				array[rightInc(index)] = temp;
				return;
			}
			index = array[leftInc(index)].saturation < array[rightInc(index)].saturation ? leftInc(index) : rightInc(index);
		}
	}
	public void bubble(Integer x) {
		Node temp = new Node(x);
		if(array[0] == null) {
			array[0] = temp;
		}else {
			int index = k;
			array[index] = temp;
			while(array[parentIndex(index)].value > array[index].value) {
				swap(array, index, parentIndex(index));
				index = parentIndex(index);
				if(index == 0) {
					break;
				}
			}
		}
		k +=1;
	}
	public Integer getIndex(int index) {
		return array[index].value;
	}
	public Integer sink() {
		if(k == 0 ) {
			return null;
		}
		Integer ret = array[0].value;
		k -=1;
		array[0].value = array[k].value;
		array[k] = null;
		int index = 0;
		int index2 = 0;
		while(k > leftInc(index) && k > rightInc(index)) {
			
			if(array[leftInc(index)] != null && array[rightInc(index)] != null) {
				if(array[index].value > array[leftInc(index)].value && array[index].value > array[rightInc(index)].value) {
					index2 = index;
					index = array[leftInc(index)].value < array[rightInc(index)].value ? leftInc(index) : rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[leftInc(index)] != null ) {
				if(array[index].value > array[leftInc(index)].value) {
					index2 = index;
					index = leftInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[rightInc(index)] != null) {
				if(array[index].value > array[rightInc(index)].value) {
					index2 = index;
					index = rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			
			break;
		}
		return ret;
	}
	public void push(Integer inc) {
		if(k == 0 ) {
			return;
		}
		array[0].value += inc;
		int index = 0;
		int index2 = 0;
		while(k > leftInc(index) && k > rightInc(index)) {
			
			if(array[leftInc(index)] != null && array[rightInc(index)] != null) {
				if(array[index].value > array[leftInc(index)].value && array[index].value > array[rightInc(index)].value) {
					index2 = index;
					index = array[leftInc(index)].value < array[rightInc(index)].value ? leftInc(index) : rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[leftInc(index)] != null ) {
				if(array[index].value > array[leftInc(index)].value) {
					index2 = index;
					index = leftInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			if(array[rightInc(index)] != null) {
				if(array[index].value > array[rightInc(index)].value) {
					index2 = index;
					index = rightInc(index);
					swap(array,index2,index);
					continue;
				}
			}
			
			break;
		}
	}
	public int leftInc(int index) {
		return (index * 2) + 1;
	}
	public int rightInc(int index) {
		return (index * 2) + 2;
	}
	public int parentIndex(int position) {
		if(position % 2 == 0) {
			return  (position - 2) / 2;
		}else {
			return  (position - 1) / 2;
		}
	}
	public void swap(Node[] array, int index1, int index2) {
		Integer temp = array[index1].value;
		array[index1].value = array[index2].value;
		array[index2].value = temp;
	}
	
	public void print(int index) {
		if(array[0] == null || index > k-1) {
			System.out.println("array empty");
			return;
		}
		if(array[leftInc(index)] != null) {
			print(leftInc(index));
		}
		System.out.println("# Array @ index: "+ index + " with value: "+ array[index].value + " #");
		//System.out.println("# Array @ index: "+ index + " with value: "+ array[index].value + " with saturation: " + array[index].saturation + " #"); //bubble2
		if(array[rightInc(index)] != null) {
			print(rightInc(index));
		}
	}
	public void printTriangle(int index) {
		if(array[index] == null) {
			System.out.println("array empty (trig)");
			return;
		}
		System.out.println("root node: " + array[index].value);
		if(array[leftInc(index)] != null) {
			System.out.println("left of root node: " + array[leftInc(index)].value);
		}else {
			System.out.println("left of root node: null");
		}
		if(array[rightInc(index)] != null) {
			System.out.println("right of root node: " + array[rightInc(index)].value);
		}else {
			System.out.println("right of root node: null");
		}
	}
	public static void main(String args[]) {
		ArrayHeap ah = new ArrayHeap(100);
		ah.bubble(10);
		ah.bubble(9);
		ah.bubble(8);
		ah.bubble(4);
		ah.bubble(5);
		ah.bubble(1);
		ah.bubble(2);
		ah.bubble(-5);
		ah.print(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
		System.out.println("");
		ah.push(3);
		ah.print(0);
		ah.printTriangle(0);
	}
}
