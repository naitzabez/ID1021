abstract public class Stack{
	int length;
	int stackPointer = -1;
	int [] items;
	
	public Stack(int length) {
		this.length = length;
		this.items = new int[length];
	}
	abstract public void push(int value);
	abstract public int pop();
	abstract public boolean isEmpty();
}