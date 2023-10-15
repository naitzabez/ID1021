import java.io.BufferedReader;
import java.io.FileReader;

public class ZipArray {
private Node[] data;
private int max;
private Integer[] keys;
private int load = 0;
private Node[] hashTable;
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
	public ZipArray(String file) {
		data = new Node[10000];
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String[] row = line.split(",");
				Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
				data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
				load +=1;
			}
			max = i-1;
		} catch (Exception e) {
			System.out.println(" file " + file + " not found");
		}
		System.out.println("File loaded");
	}
	public void initHash(int mod) {
		hashTable = new Node[mod];
	}
	public void addToHash(int mod, Node n) {
		if(n == null) {
			return;
		}
		Integer key = n.code % mod;
		for(int i = 0; i < mod; i++) {
			if(key+i == mod) {
				key = -i;
			}
			//System.out.println(key);
			if(hashTable[key+i] == null) {
				hashTable[key+i] = n;
				
				return;
			}
		}
		return;
	}
	public Node lookup(String zip, int  mod) {
		Integer key = Integer.valueOf(zip);
		Integer hk = key % mod;
		for(int i = 0; i < mod; i++) {
			if(hk+i == mod) {
				hk = -i;
			}
			if(hashTable[hk+i] == null ) {
				break;
			}
			if(hashTable[hk+i].code.equals(key)) {
				//System.out.println("found!");
				return hashTable[hk+i];
			}
			//System.out.println("continuing");
		}
		//
		//System.out.println("Not found");
		return null;
	}

	public void populateTable(int mod) {
		for(int i = 0; i < data.length; i++) {
			addToHash(mod, data[i]);
		}
	}
	public static void main (String args[]) {
		runTest(1000,"99999");
	}
	public static void runTest(int mod, String zip) {
		String path = "src/postnummer.csv";
		ZipArray za = new ZipArray(path);
		za.initHash(mod);
		za.populateTable(mod);
		long t1,t2,sum =0;
		int runs = 1000000;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			za.lookup(zip, mod);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		//System.out.println(za.lookup(zip,mod).code);
		System.out.println("avg lookup " + sum/runs + "ns");
	}
}