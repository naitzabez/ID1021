import java.io.BufferedReader;
import java.io.FileReader;

public class ZipHash {
private Node[] data;
private int max;
private Integer[] keys;
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
	public ZipHash(String file) {
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
	public void lookup(String zip) {
		
	}
	public void keyGen() {
		keys = new Integer[max];
		int i = 0;
		while(i != max) {
			keys[i] = data[i].code;
			i++;
		}
		System.out.println("keygen done");
	}
	public void collisions(int mod) {
		int[] data = new int[mod];
		int[] cols = new int[10];
		System.out.println(max);
		for (int i = 0; i < max; i++) {
			Integer index = keys[i] % mod;
			cols[data[index]]++;
			data[index]++;
		}
			System.out.println("Modulus " + mod);
		for (int i = 0; i < 10; i++) {
			System.out.println((i) +" cols:" + cols[i]);
		}
		System.out.println();
	}
	public static void main (String args[]) {
		String path = "src/postnummer.csv";
		ZipHash zh = new ZipHash(path);
		zh.keyGen();
		zh.collisions(98500);
	}
	public static void runHashTest(ZipHash zh, int runs, String zip) {
		long t1,t2,sum = 0;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			zh.lookup(zip);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		System.out.println("Avg time binary search: " + sum/runs + "ns" );
	}
}