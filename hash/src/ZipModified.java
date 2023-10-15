import java.io.BufferedReader;
import java.io.FileReader;

public class ZipModified {
private Node[] data;
private int max;

	public class Node {
	private Integer code;
	private String name;
	private Integer pop;
	public Node(Integer code, String name, Integer pop) {
		this.name = name;
		this.code = code;
		this.pop = pop;
	}
}
	public ZipModified(String file) {
		data = new Node[10000];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
				data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
			}
			max = i-1;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
		}
		System.out.println("File loaded");
	}
	public Integer linear(Integer zip) {
		for(int i = 0; i <= max; i++) {
			if(data[i].code.equals(zip)) {
				return data[i].code;
			}
		}
		return null;
	}
	public Integer binary(Integer zip) {
		int first = 0;
		int last = max;
		while (true) {
			int index = (last+first) / 2; // middle of array
			if (zip.equals(data[index].code)) {
				return data[index].code;
			}
			if (zip > data[index].code) { // check right hand of array
				
			first = index+1; // increase the lower limit for next iteration with index excluded
			continue;
			}
			if (zip < data[index].code) { //check left hand of array

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
		ZipModified zm = new ZipModified(path);
		Node [] data = zm.getZip();
		int runs = 100000;
		Integer target = 98499;
		runLinearTest(zm, runs, target);
		runBinaryTest(zm, runs, target);
	}
	public static void runLinearTest(ZipModified zm, int runs, Integer zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			zm.linear(zip);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time linear search: " + sum/runs + "ns" );
	}
	public static void runBinaryTest(ZipModified zm, int runs, Integer zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			zm.binary(zip);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time binary search: " + sum/runs + "ns" );
	}
}