import java.util.Iterator;

public class BinaryTree implements Iterable<Integer>{
	Node root;
	Node aux;
	public BinaryTree() {
		root = null;
		TreeIterator ti = new TreeIterator();
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
	public void saturate(Integer [] array, int min, int max) {
		
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
		return new TreeIterator();
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
	
	/**************************************************************************
	 * STACK CLASS
	 * ***********************************************************************/
	private class Stack <T>{
		int length;
		int stackPointer = -1;
		Object[] items;
		public Stack(int length) {
			this.length = length;
			 this.items = new Object[length];
		}

		public void push(T item) {
			if(stackPointer+1 == length) {
				expand();
			}
			items[++stackPointer] = item;
		}

		@SuppressWarnings("unchecked")
		public T pop() {
			if(stackPointer <= -1) {
				return null;
			}
			if(stackPointer+1 == length/4) {
				shrink();
			}
			return (T) items[stackPointer--];
		}
		public void expand() {
			Object newStack[] = new Object[length*2];
			for(int i = 0; i < length; i++) {
				newStack[i] = items[i];
			}
			this.length = length*2;
			items = newStack;
		}
		public void shrink() {
			Object newStack[] = new Object [length/2];
			for(int i = 0; i < length/2; i++) {
				newStack[i] = items[i];
			}
			this.length = length/2;
			items = newStack;
		}
		public boolean isEmpty() {
			return stackPointer == -1;
		}
		@SuppressWarnings("unchecked")
		public T peek() {
			if(!isEmpty()) {
			return (T) items[stackPointer];
			}
			return null;
		}

	}
	
	/**************************************************************************
	 * TREE ITERATOR CLASS
	 * ***********************************************************************/
	private class TreeIterator implements Iterator<Integer> {
		
		private Node next;
		private Stack<Node> stack;
		boolean lock = false;
		public TreeIterator() {
			next = root; //init next to root
			stack = new Stack<Node>(1); // new dynamic stack
			stack.push(next);
		}
			
		@Override
		public boolean hasNext() {
			return (!stack.isEmpty()); //iterator has more.
		}
		@Override
		public Integer next() {
			next = stack.peek();					 //put next to stack top
			while(!lock && next.left != null) {      //if can go left
				stack.push(next.left);				 //push n.left to stack
				next = next.left;
			}
			next = stack.pop();						 //pop stack
			if(next.right != null) {			     //if can go right
				stack.push(next.right);				 //push n.right to stack	 
				lock = false;                		 //open left moves
				return next.value;	
			}
			if(next.left == null && next.right == null) { //if leaf
				lock = true;						  //lock left moves
				return next.value;
			}
			return next.value;           
			}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}