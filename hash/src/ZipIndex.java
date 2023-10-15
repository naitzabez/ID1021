import java.io.BufferedReader;
import java.io.FileReader;

public class ZipIndex {
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
	public ZipIndex(String file) {
		data = new Node[100000];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
				data[code] = new Node(code, row[1], Integer.valueOf(row[2]));
			}
			max = i-1;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
		}
		System.out.println("File loaded");
	}
	public Node lookup(String zip) {
		String conv = zip.replace(" ", "");
		Integer key = Integer.valueOf(conv);
		return data[key] != null ? data[key] : null;
	}
	public static void main (String args[]) {
		String path = "src/postnummer.csv";
		ZipIndex zh = new ZipIndex(path);
		int runs = 100000;
		String target = "11115";
		runIndexTest(zh, runs, target);
	}

	public static void runIndexTest(ZipIndex zi, int runs, String zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			if(zi.lookup(zip)==null) {
				System.out.println("null");
			}
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time binary search: " + sum/runs + "ns" );
	}
}