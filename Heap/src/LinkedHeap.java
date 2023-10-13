import java.util.Random;

public class LinkedHeap {
	private class Node{
		private Node left,right = null;
		private Integer value = null;
		private Integer saturation = 0;
		public Node(Integer value) {
			this.value=value;
		}
		public void print() {
			if(left != null) {
				left.print();
			}
			System.out.println("\tvalue: " + value);
			if(right != null) {
				right.print();
			}
		}
	}
	private Node root = null;
	public LinkedHeap() {
		
	}
	public void add(Integer value) {
		Node temp = new Node(value);	
		if(root == null) {
			this.root = temp;
			temp.saturation +=1;
			return;
		} 
		Node current = root;
		while(current != null) {
			if(current.value > temp.value) {
				swap(current,temp);
			}
			if(current.left != null && current.right != null) {
				current.saturation +=1;
				current = current.left.saturation <= current.right.saturation ? current.left : current.right;
				continue;
			}
			if(current.left == null) {
				current.saturation +=1;
				current.left = temp;
				return;
			}else {
				current.saturation +=1;
				current.right = temp;
				return;
			}
		}
	}
	public void swap(Node a, Node b) {
		Integer temp = a.value;
		a.value = b.value;
		b.value = temp;
	}
	public Integer remove() {
		if(root == null ) {
			return null;
		}else {
			Integer ret = root.value;
			if(root.left == null && root.right == null) {
				root = null;
				return ret;
			}
			promoteChildren(root);
			return ret;
		}
	}
	public void promoteChildren(Node n) {
		while(true) {
			n.saturation -=1;
			if(n.left != null && n.right != null) {
				if(n.left.value <= n.right.value) {
					n.value = n.left.value;
					if(hasNoChild(n.left)) {
						n.left = null;
						return;
					}
					n = n.left;
					continue;
				}else {
					n.value = n.right.value;
					if(hasNoChild(n.right)) {
						n.right = null;
						return;
					}
					n = n.right;
					continue;
				}
			}
			if(n.left != null) {
				n.value = n.left.value;
				if(hasNoChild(n.left)) {
					n.left = null;
					return;
				}
				n = n.left;
				continue;
			}
			if(n.right != null) {
				n.value = n.right.value;
				if(hasNoChild(n.right)) {
					n.right = null;
					return;
				}
				n = n.right;
				continue;
			}
		}
	}
	public void autoFill(int size, int random) {
		Random rand = new Random();
		for(int i = 0; i < size; i++) {
			add(rand.nextInt(random));
		}
	}
	public boolean hasNoChild(Node n) {
		return n.left == null && n.right == null;
	}
	public Integer push(Integer incr) {
		Integer depth = 0;
		root.value +=incr;
		Node current = root;
		Node comp = root;
		while(true) {
			if(current.left != null && current.right != null) {
				comp = current.left.value <= current.right.value ? current.left : current.right;
				if(comp.value < current.value) {
					swap(comp,current);
					depth +=1;
					current = comp;
					continue;
				}
				break;
			}
			if(current.left != null) {
				comp = current.left;
				if(comp.value < current.value) {
					swap(comp,current);
					depth +=1;
					current = comp;
					continue;
				}
				break;
			}
			if(current.right != null) {
				comp = current.right;
				if(comp.value < current.value) {
					swap(comp,current);
					depth+=1;
					current = comp;
					continue;
				}
				break;
			}
			break;
		}
		return depth;
	}
	public void printTop() {
		System.out.println("###########################");
		if(root == null) {
			System.out.println(" Heap empty ");
			return;
		}else {
			System.out.println("root:  "+ root.value+ " | saturation: " + root.saturation);
		}
		if(root.left != null) {
			System.out.println("left:  "+root.left.value +" | saturation: " + root.left.saturation);
		}
		if(root.right != null) {
			System.out.println("right: "+ root.right.value+ " | saturation: " + root.right.saturation);
		}
		System.out.println("###########################");
	}
	public static void main(String args[]) {
		LinkedHeap lh = new LinkedHeap();
		lh.add(5);
		lh.add(3);
		lh.add(2);
		lh.add(8);
		lh.add(9);
		lh.add(1);
		lh.add(4);
		lh.add(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();
		lh.push(10);
		lh.root.print();
		lh.printTop();

	}
}
