import java.io.BufferedReader;
import java.io.FileReader;

public class ZipBucket {
private Node[] data;
private int max;
private Node[] hashTable;
private Integer[] keys;
	public class Node {
	private Integer code;
	private String name;
	private Integer pop;
	private Node next = null;
	public Node(Integer code, String name, Integer pop) {
		this.name = name;
		this.code = code;
		this.pop = pop;
	}
	}
	public ZipBucket(String file) {
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
	public void initHash(int mod) {
		hashTable = new Node[mod];
	}
	public void addToHash(int mod, Node n) {
		if(n == null) {
			return;
		}
		Integer key = n.code % mod;
		if(hashTable[key] == null) {
			hashTable[key] = n;
			return;
		}else {
			Node current = hashTable[key];
			while(current.next != null) {
				current = current.next;
			}
			//System.out.println("Collision! "+ "Current: " + current.code + " next = " + n.code);
			current.next = n;
			return;
		}
	}
	public Node lookup(String zip, int mod) {
		Integer key = Integer.valueOf(zip);
		Integer hk = key % mod;
		Node current = hashTable[hk];
		//System.out.println("Looking for: " + hk);
		if(current == null) {
		}else {
			//System.out.println("Head node " + current.code);
			while(current != null) {
				if(key.equals(current.code)) {
					//System.out.println("Found! " + current.code);
					return current;
				}
				//System.out.println("Going for next: " + current.code);
				current = current.next;
			}
		}
		//System.out.println("node not found");
		return null;
	}
	public void populateTable(int mod) {
		for(int i = 0; i < data.length; i++) {
			addToHash(mod, data[i]);
		}
	}
	public Node[] getData() {
		return data;
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

	public static void main (String args[]) {
		//String path = "src/postnummer.csv";
		//ZipBucket zh = new ZipBucket(path);
		//Node []data = zh.getData();
		//int mod = 10007;
		//zh.initHash(mod);
		//zh.populateTable(mod);
		//zh.lookup("90436", mod);
		//zh.lookup("99999", mod);
		runTest(1000,"99999");
	}
	public static void runTest(int mod, String zip) {
		String path = "src/postnummer.csv";
		ZipBucket zh = new ZipBucket(path);
		Node []data = zh.getData();
		System.out.println("data : " + data.length);
		zh.initHash(mod);
		zh.populateTable(mod);
		long t1,t2,sum =0;
		int runs = 1000000;
		for(int i = 0; i < runs; i++) {
			t1 = System.nanoTime();
			zh.lookup(zip, mod);
			t2 = System.nanoTime();
			sum += (t2-t1);
		}
		//System.out.println(zh.lookup(zip,mod).code);
		System.out.println("avg lookup " + sum/runs + "ns");
	}
}