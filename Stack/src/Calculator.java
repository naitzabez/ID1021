
public class Calculator {
	private Item [] expression;
	private int instructionPointer;
	private Stack stack;
	
	/**
	 * Creates an HP-35 calculator with automatic stack scaling starting at size 4
	 * 
	 * @param expression an Item[] array of expressions to be calculated
	 */
	public Calculator (Item [] expression) {
		this.expression = expression;
		this.instructionPointer = 0;
		this.stack = new Dynamic(4);
	}
	/**
	 * Creates an HP-35 calculator with a static stack size
	 * 
	 * @param expression an Item[] array of expressions to be calculated
	 * @param stackSize size of the stack
	 */
	public Calculator (Item [] expression, int stackSize) {
		this.expression = expression;
		this.instructionPointer = 0;
			this.stack = new Static(stackSize);
	}
	public int run() {
		while ( instructionPointer < expression.length ) {
		step();
		}
		return stack.pop();
		}
	public void step() {
		try {
		Item next = expression[instructionPointer++];
		
		switch(next.type()) {
			case ADD : {
				int y = stack.pop();
				int x = stack.pop();
				stack.push(x + y);
				break;
			}
			case SUB : {
				int y = stack.pop();
				int x = stack.pop();
				stack.push(x - y);
				return;
			}
			case MUL : {
				int y = stack.pop();
				int x = stack.pop();
				stack.push(x * y);
				break;
			}
			case DIV : {
				int y = stack.pop();
				int x = stack.pop();
				stack.push(x / y);
				return;
			}
			case VALUE : {
				stack.push(next.getValue());
				break;
			}
			default: {
				System.out.println("Invalid command, shutting down");
				System.exit(0);
				return;
			}
			
		}}catch(Exception e) {
			System.out.println("Invalid expression, closing program.");
			System.exit(0);
		}
	}
}
