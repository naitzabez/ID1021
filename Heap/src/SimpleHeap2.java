import java.util.Random;

public class SimpleHeap2 {
	private class Node{
		private Node next= null;
		private Integer value;
		public Node(int value) {
			this.value=value;
		}
	}
	private Node head = null;
	public SimpleHeap2() {
		
	}
	// O(1) add, priority lies in remove O(n)
	public void add(Integer value) {
		Node temp = new Node(value);
		temp.next = head;
		head = temp;
	}
	public void print() {
		System.out.println("Printing");
		Node current = this.head;
		int k = 0;
		while(current != null) {
			System.out.println("index "+ k  + " value " + current.value);
			k++;
			current = current.next;
		}
		System.out.println("");
	}
	// Search to remove O(n)
	public Integer remove() {
		if(head == null) {
			return null;
		}
		Node smallest = head;
		Node previous = head;
		Node current = head;
		Integer ret = head.value;
		while(current.next != null) {
			if(current.next.value < smallest.value) {
				smallest = current.next;
				previous = current;
				ret = smallest.value;
			}
			current = current.next;
			if(current == null) {
				break;
			}
		}
		if(smallest == head) {
			head = smallest.next;
			smallest = null;
			return ret;
		}
		if(smallest.next != null) {
			ret = smallest.value;
			previous.next = smallest.next;
			smallest = null;
		}else {
			ret = smallest.value;
			smallest = null;
		}
		return ret;
	}
	public void fillSelf(int n) {
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			add(rand.nextInt(n));
		}
	}
	public static void main(String args[]) {
		SimpleHeap2 sh = new SimpleHeap2();
		sh.add(3);
		sh.add(2);
		sh.add(1);
		sh.print();
		sh.remove();
		sh.print();
		sh.remove();
		sh.print();
		sh.remove();
		sh.print();
		sh.add(3);
		sh.add(2);
		sh.add(1);
		sh.add(3);
		sh.add(2);
		sh.add(-1);
		sh.add(5);
		sh.add(8);
		sh.print();
		sh.remove();
		sh.print();
		sh.remove();
		sh.remove();
		sh.remove();
		sh.remove();
		sh.print();
	}
}