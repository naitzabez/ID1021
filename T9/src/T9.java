import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class T9{
	private class Node {
		public Node[] next;
		public boolean valid;
		public Node() {
			next = new Node[27];
			valid = false;
		}
	}
	private class Trie{
		private Node root;
		private int denied = 0;
		public Trie() {
			root = new Node();
		}
	}
	private final String path = "src/kelly.txt";
	private String[] lexicon;
	private int max;
	private Trie trie;
	public T9() {
		init();
		loadTrie(lexicon);
	}
	private int code(char w) {
		switch (w) {
		case 'a' :
			return 0;
		case 'b' :
			return 1;
		case 'c' :
			return 2;
		case 'd' :
			return 3;
		case 'e' :
			return 4;
		case 'f' :
			return 5;
		case 'g' :
			return 6;
		case 'h' :
			return 7;
		case 'i' :
			return 8;
		case 'j' :
			return 9;
		case 'k' :
			return 10;
		case 'l' :
			return 11;
		case 'm' :
			return 12;
		case 'n' :
			return 13;
		case 'o' :
			return 14;
		case 'p' :
			return 15;
		case 'r' :
			return 16;
		case 's' :
			return 17;
		case 't' :
			return 18;
		case 'u' :
			return 19;
		case 'v' :
			return 20;
		case 'x' :
			return 21;
		case 'y' :
			return 22;
		case 'z' :
			return 23;
		case 'å' :
			return 24;
		case 'ä' :
			return 25;
		case 'ö' :
			return 26;
		}
		return -1;
		}
	private char num(int w) {
		switch (w) {
		case 0 :
			return 'a';
		case 1 :
			return 'b';
		case 2 :
			return 'c';
		case 3 :
			return 'd';
		case 4 :
			return 'e';
		case 5 :
			return 'f';
		case 6 :
			return 'g';
		case 7 :
			return 'h';
		case 8 :
			return 'i';
		case 9 :
			return 'j';
		case 10 :
			return 'k';
		case 11 :
			return 'l';
		case 12 :
			return 'm';
		case 13 :
			return 'n';
		case 14 :
			return 'o';
		case 15 :
			return 'p';
		case 16 :
			return 'r';
		case 17 :
			return 's';
		case 18 :
			return 't';
		case 19 :
			return 'u';
		case 20 :
			return 'v';
		case 21 :
			return 'x';
		case 22 :
			return 'y';
		case 23 :
			return 'z';
		case 24 :
			return 'å';
		case 25 :
			return 'ä';
		case 26 :
			return 'ö';
		}
		return '.';
	}
	
	public void loadTrie(String [] lex) {
		int count = 0;
		for(String s : lexicon) {
			loadWord(s);
			count++;
		}
		System.out.println("Trie loaded from lexicon with: " + (count - trie.denied) +" entries.");
	}
	
	public void loadWord(String word) {
		if(word == null) {
			trie.denied +=1;
			return;
		}
		char[] inject = word.toCharArray();
		Node current = trie.root;
		for(int i = 0; i < inject.length; i++) {
			int index = code(inject[i]);
			if(current.next[index] == null) {
				current.next[index] = new Node();
				current = current.next[index];
			}else {
				current = current.next[index];
			}
		}
		current.valid = true;
	}
	
	public ArrayList<String> decode(String sequence) {
		ArrayList <String> al = new ArrayList <String> ();
		String start = "";
		char[] charSeq = sequence.toCharArray();
		int charOffset = 48;
		int[] numbs = new int [charSeq.length];
		for(int i = 0; i < charSeq.length; i++) {
			numbs[i] = Integer.valueOf(charSeq[i]-charOffset);
		}
		collect(al,0, numbs, start, trie.root);
		printList(al);
		return al;	
	}
	public void collect(ArrayList <String> al,int index, int []numbs, String found, Node current) {
		if(index == numbs.length) {
			return;
		}
		int[] validBranches = toBranches(numbs[index]);
		Node a = current.next[validBranches[0]];
		Node b = current.next[validBranches[1]];
		Node c = current.next[validBranches[2]];
		String toAdd = found;
		if(a!= null){
			if(a.valid && index == numbs.length-1) {
				toAdd+=num(validBranches[0]);
				al.add(toAdd);
			}
				toAdd+=num(validBranches[0]);
				collect(al,index+1,numbs,toAdd,a);
		}
		toAdd = found;
		if(b != null){
			if(b.valid && index == numbs.length-1) {
				toAdd+=num(validBranches[1]);
				al.add(toAdd);
			}
				toAdd+=num(validBranches[1]);
				collect(al,index+1,numbs,toAdd,b);
		}
		toAdd = found;
		if(c != null){
			if(c.valid && index == numbs.length-1) {
				toAdd+=num(validBranches[2]);
				al.add(toAdd);
			}
				toAdd+=num(validBranches[2]);
				collect(al,index+1,numbs,toAdd,c);
		}
	}
	public void printList(ArrayList<String> decoded) {
		int count = 0;
		for(String word : decoded) {
			count +=1;
			System.out.println("Word # "+ count+ ": " + word);
		}
		System.out.println("");
	}
	public void addWordToTrie(String word) {
		if(word == null) {
			System.out.println("Denied word ");
			return;
		}else {
			char[] inject = word.toCharArray();
			Node current = trie.root;
			for(int i = 0; i < inject.length; i++) {
				int index = code(inject[i]);
				if(current.next[index] == null) {
					current.next[index] = new Node();
					current = current.next[index];
				}else {
					current = current.next[index];
				}
			}
			current.valid = true;
		}
	}
	public int[] toBranches(int num) {
		int n = (num-1)*3;
		int [] ret = {n, (n + 1), (n + 2)};
		return ret;
	}
	
	public void lookup(String word) {
		char[] charSeq = word.toCharArray();
		String res = "";
		for(int i = 0; i < charSeq.length; i++) {
			res += (code(charSeq[i])/3 + 1);
		}
		System.out.println(word + " translates into " + res);
	}
	
	public void init() {
		trie = new Trie();
		lexicon = new String[10000];
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				lexicon[i++] = line;
			}
			max = i-1;
		} catch (Exception e) {
			System.out.println(" file " + path + " not found");
		}
		System.out.println("Lexicon loaded with: " + (max+1) + " entries.");
	}
	
	public static void main(String[] args) {
		T9 t9 = new T9();
		System.out.println("");
		t9.addWordToTrie("aaa");
		t9.addWordToTrie("aba");
		t9.addWordToTrie("aab");
		t9.addWordToTrie("aca");
		t9.addWordToTrie("acc");
		t9.addWordToTrie("baa");
		t9.addWordToTrie("cca");
		t9.addWordToTrie("acb");
		t9.addWordToTrie("ccc");
		t9.addWordToTrie("bbb");
		t9.addWordToTrie("bbc");
		t9.addWordToTrie("ccb");
		t9.addWordToTrie("cbc");
		t9.addWordToTrie("bcb");
		t9.addWordToTrie("bcc");
		t9.addWordToTrie("bca");
		t9.addWordToTrie("cab");
		t9.addWordToTrie("caa");
		t9.addWordToTrie("cba");
		t9.addWordToTrie("abc");
		t9.addWordToTrie("bca");
		t9.addWordToTrie("cbb");
		t9.addWordToTrie("aac");
		t9.addWordToTrie("aba");
		t9.addWordToTrie("cac");
		t9.addWordToTrie("cbc");
		t9.addWordToTrie("bab");
		t9.addWordToTrie("bac");
		t9.addWordToTrie("abb");
		t9.addWordToTrie("bba");
		System.out.println("Testing 27 self added words (permutations of {a,b,c} or {1,1,1}), to see if decode finds them all.");
		t9.decode("111"); //all above
		
		t9.decode("39721563"); //göteborg
		t9.decode("752224"); //toffel (from example)
		t9.decode("537735"); //nittio
		t9.decode("53"); //ni
		t9.decode("54"); //oj
		t9.decode("9"); //å, ö
		t9.decode("22"); //ed, de
		t9.decode("7713425353653536726"); //utbildningsminister
		t9.lookup("utbildningsminister"); //reverse translation to keypress
	}
}
