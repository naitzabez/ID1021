import java.io.BufferedReader;
import java.io.FileReader;


public class Zip {
private Node[] data;
private int max;

	public class Node {
	private String code;
	private String name;
	private Integer pop;
	public Node(String code, String name, Integer pop) {
		this.name = name;
		this.code = code;
		this.pop = pop;
	}
}
	public Zip(String file) {
		data = new Node[10000];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
			}
			max = i-1;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
		}
	}
	public String linear(String zip) {
		for(int i = 0; i <= max; i++) {
			if(data[i].code.equals(zip)) {
				return data[i].code;
			}
		}
		return null;
	}
	public String binary(String zip) {
		int first = 0;
		int last = max;
		while (true) {
			int index = (last+first) / 2; // middle of array
			int result = zip.compareTo(data[index].code);
			if (result == 0) {
				return data[index].code;
			}
			if (result > 0) { // check right hand of array
				
			first = index+1; // increase the lower limit for next iteration with index excluded
			continue;
			}
			if (result < 0) { //check left hand of array

			last = index-1; // lower the upper limit for next iteration with index excluded
			continue;
			}
			return null;
		}
	}
	public Node[] getZip() {
		return data;
	}
	public int getMax() {
		return max;
	}
	public static void main (String args[]) {
		String path = "src/postnummer.csv";
		Zip zip = new Zip(path);
		Node [] data = zip.getZip();
		String target1 = "984 99";
		String target2 = "111 15";
		int runs = 100000;
		runLinearTest(zip, 100000, target2);
		runBinaryTest(zip, 100000, target2);
	}
	public static void runLinearTest(Zip z, int runs, String zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			z.linear(zip);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time linear search: " + sum/runs + "ns" );
	} 
	public static void runBinaryTest(Zip z, int runs, String zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			z.binary(zip);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time binary search: " + sum/runs + "ns" );
	}
}