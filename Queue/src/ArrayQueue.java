

public class ArrayQueue {
	private Integer n; //length
	private Integer aq []; //arrayqueue
	private int i = 0; //head
	private int k = 0; //tail
	public ArrayQueue(int n) {
		this.aq = new Integer[n];
		this.n = n;
	}
	public void add(Integer item) {	
		if(k == n) {
			k = 0;
			aq[k] = item;
			return; // if tail exceeds array -> wrap
		}
		if(aq[i] != null && k == i) { // if list is full
			return;
		}
		aq[k] = item;
		if(k+1 == n) {
			k=0; // if tail exceeds array -> wrap
		}else {
			k +=1; //else increment tail
		}
		return;
	}
	public Integer remove() {
		if(aq[i] == null) { // queue empty
			return null;
		}
		if(i==n-1) { //if head wraps
			Integer ret = aq[i];
			aq[i] = null;
			i=0;
			return ret;
		}
			Integer ret = aq[i];
			aq[i] = null;
			i +=1; //move to next head
			return ret;
	}
	public void print() {
		s();
		for(int i = 0; i < n; i++) {
			if(i == this.i && i == this.k) {
				System.out.println("Q @ " + i + " with value " + aq[i] + " <-- Head & tail");
				continue;
			}
			if(i == this.i) {
				System.out.println("Q @ " + i + " with value " + aq[i] + " <-- Head");
				continue;
			}if(i == this.k) {
				System.out.println("Q @ " + i + " with value " + aq[i] + " <-- Tail");
				continue;
			}else {
			System.out.println("Q @ " + i + " with value " + aq[i] );
			}
		}
		System.out.println(this.k + " tail");
		System.out.println(this.i + " head");
		s();
		System.out.println("");
	}
	public void s() {
		System.out.println("##############################");
	}
	public void p() {
		System.out.println("was here ");
	}
	public static void main(String args[]) {
		ArrayQueue aq = new ArrayQueue(5);
		aq.add(1);
		aq.add(1);
		aq.add(1);
		aq.add(1);
		aq.print();
		aq.remove();
		aq.remove();
		aq.remove();
		aq.remove();
		aq.remove();
		aq.add(1);
		aq.print();
		aq.add(1);
		aq.add(1);
		aq.add(1);
		aq.add(1);
		//Integer a = aq.remove();
		aq.print();
		aq.remove();
		aq.print();
		aq.add(1);
		aq.print();
		aq.remove();
		aq.remove();
		aq.remove();
		aq.remove();
		aq.print();
		Integer a = aq.remove();
		aq.print();
		System.out.println(a);
		Integer b = aq.remove();
		System.out.println(b);
	}
}
