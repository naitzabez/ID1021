

public class ArrayQueueDynamic {
	private Integer n; //length
	private Integer aq []; //arrayqueue
	private int i = 0; //head
	private int k = 0; //tail
	private int eleAmount = 0;
	public ArrayQueueDynamic(int n) {
		this.aq = new Integer[n];
		this.n = n;
	}
	public void add(Integer item) {	
		if(k+1 == n) {
			resize();
			aq[k] = item;
			k+=1;
			eleAmount +=1;
			return; // if tail exceeds array -> resize
		}
			aq[k] = item;
			eleAmount +=1;
			k +=1; //else increment tail
		return;
	}
	public Integer remove() {
		if(aq[i] == null) { // queue empty
			System.out.println("null");
			return null;
		}
			Integer ret = aq[i];
			aq[i] = null;
			i +=1; //move to next head
			eleAmount -=1;
			desize();
			return ret;
	}
	public void resize() {
		Integer [] aqTemp = new Integer [2*aq.length];
		int newLocation =0;
		for(int i = this.i; i < n-1; i++) {
			aqTemp[newLocation] = aq[i];
			newLocation++;
		}
		this.i = 0;
		n=2*n;
		aq = aqTemp;
	}
	public void desize() {
		if(eleAmount == n/4) {
			Integer [] aqTemp = new Integer [aq.length / 2];
			int newLocation =0;
			for(int i = this.i; i < k; i++) {
				aqTemp[newLocation] = aq[i];
				newLocation++;
			}
			this.i = 0;
			k=eleAmount;
			n=n/2;
			aq = aqTemp;
			System.out.println("desizing");
		}
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
		System.out.println(this.eleAmount + " ele");
		System.out.println(this.n + " size");
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
		ArrayQueueDynamic aq = new ArrayQueueDynamic(5);
		aq.add(1);
		aq.remove();
		aq.remove();
		aq.print();
	}
}
