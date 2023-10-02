public class Queue {
Node head;
Node last;
	private class Node {
	Integer item;
	Node next;
		private Node(Integer item, Node list) {
			this.item = item;
			this.next = list;
		}
	}
	public Queue() {
		head = null;
	}
	//Cost = O(n) -> O(1)
	public void add(Integer item) {
		Node toAdd = new Node(item,null);
		if(head == null) {
			head = toAdd;
			last = head;
			return;
		}
		last.next = toAdd;
		last = toAdd;
	}
	
	//Cost = O(1)
	public Integer remove() {
		Integer headValue = head.item;
		if(head.next != null) {
			head = head.next;
		}else {
			head = null;
		}
		
	return headValue;
	}
	public void print() {
		Node pos = head;
		while(pos != null) {
			System.out.println("Item " + pos.item);
			pos=pos.next;
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		Queue q = new Queue();
		q.add(5);
		q.add(6);
		q.add(8);
		q.print();
		q.remove();
		q.print();
	}
}