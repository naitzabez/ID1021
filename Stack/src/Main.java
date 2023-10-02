
public class Main {
	
	public static long runStaticTest() {
		long startTime;
		long endTime;
		long duration = 999999999;
		for(int i = 0; i < 1000; i++) {
			startTime = System.nanoTime();
			Stack staticStack = new Static(1024);
			for(int j = 0; j < 1000; j++) {
				staticStack.push(1);
			}
			for(int j = 0; j < 1000; j++) {
				staticStack.pop();
			}
			endTime = System.nanoTime();
			if(duration > (endTime - startTime)) {
				duration = endTime - startTime;
			}
		}
		return duration ;
	}
	public static long runDynamicTest() {
		long startTime;
		long endTime;
		long duration = 999999999;
		for(int i = 0; i < 1000; i++) {
			startTime = System.nanoTime();
			Stack dynamicStack = new Dynamic(4);
			for(int j = 0; j < 1000; j++) {
				dynamicStack.push(1);
			}
			for(int j = 0; j < 1000; j++) {
				dynamicStack.pop();
			}
			endTime = System.nanoTime();
			if(duration > (endTime - startTime)) {
				duration = endTime - startTime;
			}
		}
		return duration ;
	}
	public static void finalTest() {
		//STATIC TEST
		long b = 999999999; // Big declared initial value such comparisons can be carried out at start
		long a;
		for(int i = 0; i < 1000; i++) {
			a = runStaticTest();
			if(b > a) {
				b = a;
			}
		}
		System.out.println("Static stack duration in ns: " + b);
		
		//DYNAMIC TEST
		long d = 999999999; // Big declared initial value such comparisons can be carried out at start
		long c;
		for(int i = 0; i < 1000; i++) {
			c = runDynamicTest();
			if(d > c) {
				d = c;
			}
		}
		System.out.println("Dynamic stack duration in ns: " + d);
	}
	//test 3 4 + 2 4 + * = 42
	public static void main(String[] args) {
		
		Item[] expr = {
				Item.Value(1),
				Item.Value(2),
				Item.Value(3),
				Item.Value(4),
				Item.Value(5),
				Item.Value(6),
				Item.Value(7),
				Item.Value(8),
				Item.Value(9),
				Item.Value(10),
				Item.Value(11),
				Item.Value(12),
				Item.Value(13),
				Item.Value(14),
				Item.Value(15),
				Item.Value(16),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				Item.Add(),
				Item.Mul(),
				};
		Item[] expr2 = {
				Item.Value(10),
				Item.Value(2),
				Item.Value(5),
				Item.Mul(),
				Item.Add()
				};
		//Calculator calc = new Calculator(expr2);
		//int res = calc.run();
		//System.out.println("Res is: " + res);
		//System.out.println("Static runtime 1000 tests, reported in ns " + runStaticTest());
		//System.out.println("Dynamic runtime 1000 tests, reported in ns " + runDynamicTest());
		finalTest();
	}

}
