
public class Static extends Stack{

	public Static(int length) {
		super(length);
	}

	@Override
	public void push(int value) {
		items[++stackPointer] = value;
	}

	@Override
	public int pop() {
		return items[stackPointer--];
	}

	@Override
	public boolean isEmpty() {
		return stackPointer == -1;
	}

	public boolean isFull() {
		return stackPointer == length-1;
	}
}
