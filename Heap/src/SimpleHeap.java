import java.util.Random;

public class SimpleHeap {
	private class Node{
		private Node next= null;
		private Integer value;
		public Node(int value) {
			this.value=value;
		}
	}
	private Node head = null;
	public SimpleHeap() {
		
	}
	
	public void add(Integer value) {
		if(head == null) {
			this.head = new Node(value);
			return;
		}
		Node temp = new Node(value);
		if(head.value  <= value) {
			Node curr = head;
			while(curr.next != null && curr.next.value <= value) {
				curr = curr.next;
			}
			if(curr.next == null) {
				curr.next = temp;
				return;
			}else {
				temp.next = curr.next;
				curr.next = temp;
				return;
			}
		}else {
			temp.next = head;
			head = temp;
		}
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
	// Remove is O(1) as first element has highest priority
	public Integer remove() {
		Node current = head;
		head = head.next;
		return current.value;
	}
	public void fillSelf(int n) {
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			add(rand.nextInt(n));
		}
	}
	public static void main(String args[]) {
		SimpleHeap sh = new SimpleHeap();
		sh.add(5);
		sh.add(2);
		sh.add(1);
		sh.print();
		sh.add(3);
		sh.add(4);
		sh.add(0);
		sh.add(8);
		sh.print();
	}
}
