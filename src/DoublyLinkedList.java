import java.util.Random;

public class DoublyLinkedList {
	
	private class Cell{
		private Cell next = null;
		private Cell prev = null;
		private int head;
		
		public Cell(int val, Cell next) {
			head = val;
			this.next = next;
		}
	}
	
	private Cell first = null;
	private int length = 0;
	public DoublyLinkedList() {
		
	}
	public void add(int item) {
		first = new Cell(item, first);
		if(first.next != null) {
			first.next.prev = first;
		}
		length+=1;
	}
	public int length() {
		return length;
	}
	public boolean find(int item) {
		Cell current = first;
		while(current != null) {
			if(current.head == item) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public void remove(int item) {
		Cell c = first;
		while(c != null) {
			if(c.head == item) {
				if(c.prev != null && c.next == null) {
					c.prev.next = null;
					length -=1;
					return;
				}
				if(c.prev != null && c.next != null) {
					c.prev.next = c.next;
					c.next.prev = c.prev;
					length -=1;
					return;
				}
				if(c.prev == null && c.next != null) {
					first = c.next;
					first.prev = null;
					length -=1;
					return;
				}
			}
			c = c.next;
		}
	}
	public void unlink(Cell c) {
		if(c.prev != null && c.next != null) { // c = mid
			c.prev.next = c.next;
			c.next.prev = c.prev;
			c.next = null;
			c.prev = null;
			length -=1;
			return;
		}
		if(c.prev != null && c.next == null) { // c = last
			c.prev.next = null;
			c.prev = null;
			length -=1;
			return;
		}
		if(c.prev == null && c.next != null) { // c = first
			first = c.next;
			first.prev = null;
			c.next = null;
			length -=1;
			return;
		}
	}
	public void insert(Cell c) {
		if(first == null) {
			first = c;
		}else {
			first.prev = c;
			c.next = first;
			first = c;
		}
		length += 1;
	}
	
	public void iterate() {
		System.out.println("Iterating --");
		Cell target = first;
		for(int i = 0; i < length; i++) {
			if(target != null) {
				System.out.println("Iterating @ index -> " + i + " value -> " + target.head);
				target = target.next;
			}
		}
		System.out.println("Length " + length + " --");
		System.out.println("Iteration done --");
	}
	public void findLast() {
		Cell target = first;
		while(target.next != null) {
			target = target.next;
		}
		System.out.println("Last element value: " + target.head);
	}
	public void selfTest(int n, int k) {
		Cell[] cellArray = new Cell[n];
		int [] cellIndex = new int[k];
		Random rand = new Random();
		long t1,t2, sum = 0;
		for(int i = 0; i < k; i++) {
			cellIndex[i] = rand.nextInt(n);
		}
		for(int i = 0; i < n; i++) { 
			Cell temp = new Cell(i,first);
			cellArray[i] = temp;
			insert(temp);
		}
		for(int i = 0; i < k; i++) {
			t1 = System.nanoTime();
			unlink(cellArray[cellIndex[i]]);
			insert(cellArray[cellIndex[i]]);
			t2 = System.nanoTime();
			sum += (t2 - t1);
		}
		System.out.println("Avg time DLL ns:  " + (sum / k));
	}
	public void fill(int n) {
		for(int i = 0; i < n; i++) {
			Cell temp = new Cell(i,first);
			insert(temp);
		}
	}
	public static void main(String args[]) {
		DoublyLinkedList DLL = new DoublyLinkedList();
		LinkedList LL = new LinkedList();
		LL.selfTest (1000, 1000000);
		DLL.selfTest(1000, 1000000);
	}

}
