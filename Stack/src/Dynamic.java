
public class Dynamic extends Stack{


	public Dynamic(int length) {
		super(length);
		
	}

	@Override
	public void push(int value) {
		if(stackPointer+1 == length) {
			expand();
		}
		items[++stackPointer] = value;
		//System.out.println("Added item "+ value +" to stack" + ", stack pointer is at: " + stackPointer);
	}

	@Override
	public int pop() {
		if(stackPointer+1 == length/4) {
			shrink();
		}
		//System.out.println("Removed item "+ items[stackPointer] + " stackPointer is at: " + (stackPointer-1));
		return items[stackPointer--];
	}
	public void expand() {
		int newStack[] = new int [length*2];
		for(int i = 0; i < length; i++) {
			newStack[i] = items[i];
		}
		this.length = length*2;
		items = newStack;
		//System.out.println("Stack expanded to new size: " + length);
	}
	public void shrink() {
		int newStack[] = new int [length/2];
		for(int i = 0; i < length/2; i++) {
			newStack[i] = items[i];
		}
		this.length = length/2;
		items = newStack;
		//System.out.println("Stack shrunk to new size: " + length);
	}

	@Override
	public boolean isEmpty() {
		return stackPointer == -1;
	}

}
