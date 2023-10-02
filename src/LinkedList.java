import java.util.Random;
public class LinkedList {
	private Cell first;	
	private int length = 0;
	
	public void add(int item) {
		first = new Cell(item, first);
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
			current = current.tail;
		}
		return false;
	}
	public void remove(int item) {
		Cell current = first;
		if(current.head == item) {
			first = current.tail;
			length -=1;
			return;
		}
		while(current.tail != null) {
			if(current.tail.head == item) {
				if(current.tail.tail != null) {
					current.tail = current.tail.tail;
					length -=1;
					break;
				}else {
					current.tail = null;
					length -=1;
					break;
				}
			}
			current = current.tail;
		}
	}
	public void append(LinkedList b) {
		Cell nxt = this.first;
		while (nxt.tail != null) {
			nxt = nxt.tail;
			}
			nxt.tail = b.first;	
			this.length += b.length();
		}
	public void iterate() {
		System.out.println("Iterating --");
		Cell target = first;
		for(int i = 0; i < length; i++) {
			if(target != null) {
				System.out.println("Iterating @ index -> " + i + " value -> " + target.head);
				target = target.tail;
			}
		}
		System.out.println("Length " + length + " --");
		System.out.println("Iteration done --");
	}
	public void push(int n) {
		add(n);
	}
	public int pop() {
		int ret = first.head;
		this.remove(ret);
		return ret;
	}
	public LinkedList() {

	}
	public void fill(int n) {
		for(int i = 0; i < n; i++) {
			Cell temp = new Cell(i,first);
			insert(temp);
		}
	}
	public void insert(Cell c) {
		c.tail = first;
		first = c;
		length +=1;
	}
	public void unlink(Cell c) {
		Cell current = first;
		if(c == current) {
			first = current.tail; 
			c.tail = null;
			length -=1;
			return;
		}
		while(current.tail != null) {
			if(current.tail == c) {
				current.tail = c.tail;
				c.tail = null;
				length -=1;
				return;
			}
			current = current.tail;
		}
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
		System.out.println("Avg time LL ns: " + (sum / k));
	}
	private class Cell {
		int head;
		Cell tail;
		
		Cell(int val, Cell t1){
			head = val;
			tail = t1;
		}
	}
}
