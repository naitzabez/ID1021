import java.io.BufferedReader;
import java.io.FileReader;

public class Map {	
	private City[] cities;
	private final int mod = 541;
	private int collisions = 0;
	public Map(String file) {
		cities = new City[mod];
		loadFile(file);
	}
	public void loadFile(String file) {
		int linecount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String [] lines = line.split(",");
				String from = lines[0];
				String to = lines[1];
				int mins = Integer.valueOf(lines[2]);
				City c1 = lookup(from);
				City c2 = lookup(to);
				c1.connect(c2, mins);
				c2.connect(c1, mins);
				linecount+=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" file " + file + " not found or corrupt");
		}
		System.out.println("Loaded "+ file + " and " + linecount + " lines of data, collisions when hashing: " + collisions);
	}
	private Integer hash(String name) {
		int hash = 0;
		for (int i = 0; i < name.length(); i++) {
			hash = (hash*31 % mod) + name.charAt(i);
		}
		return hash % mod;
	}
	public City lookup(String name) {
		int index = hash(name);
		if(cities[index] == null) {
			cities[index] = new City(name,index);
		}
		if(cities[index].name.equals(name)) {
			return cities[index];
		}
		//System.out.println("Collision on " + name + " with index " + index + " same as " + cities[index].name);
		collisions +=1;
		while(cities[index] != null) {
			if(cities[index].name.equals(name)) {
				return cities[index];
			}
			index+=1;
			if(index == cities.length) {
				index = 0;
			}
		}
		cities[index] = new City(name,index);
		return cities[index];
	}
	public void printConnections(String cityName) {
		System.out.println("Printing connections for: " + cityName);
		City c = lookup(cityName);
		for(int i = 0; i < c.neighbours.length; i++) {
			if(c.neighbours[i] == null) {
				continue;
			}
			System.out.println(c.neighbours[i].city.name +" dst " + c.neighbours[i].dst + " index " + c.neighbours[i].city.id);
			
		}
		System.out.println("");
	}
	public void printCities() {
		System.out.println("Printing all cities: ");
		for(int i = 0; i < cities.length; i ++) {
			if(cities[i] == null) {
				continue;
			}
			System.out.println(cities[i].name + " hash " + cities[i].id);
		}
		System.out.println("");
	}
	public static void main(String args []) {
		String path = "src/europe.csv";
		Map m = new Map(path);
		m.printCities();
	}
}