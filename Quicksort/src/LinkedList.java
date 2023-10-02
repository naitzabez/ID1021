import java.util.Random;

public class LinkedList {
	public class Node{
		private Node next;
		private int value;
		private Node(Node next, int value) {
			this.next=next;
			this.value=value;
		}
	}
	private int length = 0;
	private Node first,last;
	public LinkedList() {
		this.first = null;
		this.last = null;
	}
	public void fillFromArray(int a[]) {
		for(int i = 0; i < a.length; i++) {
			add(a[i]);
		}
	}
	public void print() {
		System.out.println("Printing...");
		Node current = first;
		int i = 0;
		while(current != null) {
			System.out.println("LL @ index: "+ i + " Value: " + current.value);
			current = current.next;
			i+=1;
		}
		System.out.println("... done!");
		System.out.println("");
	}
	public int length() {
		return length;
	}
	public void insert(Node n) {
		if(n != null) {
			if(first == null) {
				first = n;
				last = n;
				n.next = null;
			}else {
				n.next = first;
				first = n;
			}
			length+=1;
		}
	}
	public void add(int value) {
			first = new Node(first,value);
			if(length == 0) {
				last = first;
				last.next = null;
			}
			length+=1;
	}
	public void sort(LinkedList L) {
		if(L.first == null || L.first.next == null) {
			return;
		}
		LinkedList small = new LinkedList();
		LinkedList large = new LinkedList();
		Node current = L.first;
		Node pivot = L.first;
		while(current != null) {
			Node temp = current;
			current = current.next;
			if(pivot.value >= temp.value) {
				if(temp == pivot) {
					continue;
				}
				small.insert(temp);
			}else {
				large.insert(temp);
			}
		}
		sort(small);
		sort(large);
		concat(small,L,large);
	}
	public void concat(LinkedList small, LinkedList l, LinkedList large) {
		if(small.last != null) {
			large.insert(l.first);
			large.insert(small.last);
			l.first = small.first;
			l.last = large.last;
			return;
		}
		large.insert(l.first);
		l.last = large.last;
	}
	public int[] deathTest(int n) {
		int [] ret = new int[n];
		Random rand = new Random();
		for(int i = 0; i < n; i++) {
			ret[i] = rand.nextInt(n);
		}
		return ret;
	}
	public static void main(String args[]) {
		//int test[] = {3,6,7,1,10,5,-2,9,8,-6,0,4};
		LinkedList LL = new LinkedList();
		//LL.fillFromArray(test);
		LL.fillFromArray(LL.deathTest(10));
		System.out.println("Unsorted below");
		LL.print();
		LL.sort(LL);
		System.out.println("Result below");
		LL.print();
	}
}

