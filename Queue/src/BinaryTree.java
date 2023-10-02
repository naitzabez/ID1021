import java.util.Iterator;
public class BinaryTree implements Iterable<Integer>{
	Node root;
	public BinaryTree() {
		root = null;
		QueueIterator ti = new QueueIterator();
	}
	public void add(Integer key, Integer value) {
		Node parent = root;
		if(root == null) {
			root = new Node(key,value);
			return;
		}
		if(root.key == key) {
			root.value = value;
			return;
		}
		if(root.key > key) {
			if(root.left == null) {
				root.left = new Node(key,value);
				return;
			}else {
				root = root.left;
				add(key,value);
			}
		}else{
			if(root.right == null) {
				root.right = new Node(key,value);
				return;
			}else {
				root = root.right;
				add(key,value);
			}
		}
		root = parent; //to reset root
		return;
	}
	public void saturateSelf(int k, int n) {
		int insert = (n + k)/2;
		if(insert == k || insert == n) {
			return;
		}
		add(insert,insert);
		saturateSelf(k,insert); //lower
		saturateSelf(insert,n); //upper
	}
	public Integer lookup(Integer key) {
		Node parent = root;
		if(root == null) {
			return null;
		}
		if(root.key == key) {
			return root.value;
		}
		if(root.key > key) {
			if(root.left == null) {
				return null;
			}else {
				root = root.left;
				lookup(key);
			}
		}else{
			if(root.right == null) {
				return null;
			}else {
				root = root.right;
				lookup(key);
			}
		}
		root = parent; //to reset root
		return root.value;
	}
	@Override
	public Iterator<Integer> iterator() {
		return new QueueIterator();
	}
	
	/**************************************************************************
	 * NODE CLASS
	 * ***********************************************************************/
	public class Node {
		public Integer key;
		public Integer value;
		public Node left, right;
		public Node(Integer key, Integer value) {
			this.key = key;
			this.value = value;
			this.left = this.right = null;
		}
		public void print() {
			if(left != null) {
				left.print();
			}
			System.out.println(" key: " + key + "\tvalue: " + value);
			if(right != null) {
				right.print();
			}
		}
	}
	public class QueueIterator implements Iterator<Integer>{
		private Queue queue;
		public QueueIterator() {
			queue = new <Node>Queue();
			queue.add(root);
		}
		@Override
		public boolean hasNext() {
			return (queue.head != null);
		}

		@Override
		public Integer next() {
			Node rem = (Node) queue.remove();
			if(rem != null) {
				if(rem.left != null) {
					queue.add(rem.left);
				}
				if(rem.right != null) {
					queue.add(rem.right);
				}
				return rem.key;
			}
		return null;
		}
	}
	public class Queue<T>{
		private qNode head;
		private qNode last;
		private int length = 0;
		private class qNode<T> {
			private T item;
			private qNode next;
			private qNode(T item, qNode list) {
				this.item = item;
				this.next = list;
			}
			}
			public Queue() {
				head = null;
			}
			//Cost = O(n) -> O(1)
			public void add(T n) {
				length +=1;
				qNode toAdd = new qNode(n,null);
				if(head == null) {
					head = toAdd;
					last = head;
					return;
				}
				last.next = toAdd;
				last = toAdd;
			}
			
			//Cost = O(1)
			@SuppressWarnings("unchecked")
			public T remove() {
				length-=1;
				T headValue = (T) head.item;
				if(head.next != null) {
					head = head.next;
				}else {
					head = null;
				}
				
			return headValue;
			}
			public void print() {
				qNode pos = head;
				while(pos != null) {
					System.out.println("Item " + pos.item);
					pos=pos.next;
				}
				System.out.println("");
			}
	}
	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		bt.saturateSelf(0, 25);
		for(int i : bt) {
			System.out.println("tree " + i);
		}
	}
}