
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
	public void insert(Cell c) {
		first = c;
		length+=1;
	}
	public void unlink(Cell c) {
		Cell current = first;
		if(c == first) {
			first = first.tail;
			length-=1;
			return;
		}
		while(current.tail != null) {
			if(current.tail == c) {
				current.tail = current.tail.tail;
				length -=1;
				return;
			}
			current = current.tail;
		}
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
	public void traverse() {
		Cell current = first;
		int index = 0;
		while(current != null) {
			
			System.out.println("Value @ index: " + index + " = " + current.head);
			if(current.tail == null) {
				break;
			}
			current = current.tail;
			index +=1;
		}
	}
	public void push(int n) {
		add(n);
	}
	public int pop() {
		int ret = first.head;
		this.remove(ret);
		return ret;
	}
	public LinkedList(int n) {
		Cell last = null;
		for (int i = 0; i < n; i++) {
		last = new Cell(i, last);
		}
		first = last;
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