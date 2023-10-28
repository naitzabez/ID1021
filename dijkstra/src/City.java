public class City {
	public String name;
	public Integer id;
	public Connection[] neighbours;
	public City(String name, Integer id) {
		this.name = name;
		this.neighbours = new Connection[6]; //Smallest length required, found by collect for europe.csv
		this.id = id;
	}
	public void connect(City nxt, int dst) {
		for(int i = 0; i < neighbours.length; i++) {
			if(neighbours[i] == null) {
				neighbours[i] = new Connection(nxt, dst);
				return;
			}
			if(neighbours[i].city.name.equals(nxt.name) && dst == neighbours[i].dst) {
				System.out.println(this.name + " is already connected to " + neighbours[i].city.name + " with this distance");
				return;
			}
		}
		System.out.println("Array lenngth too small"); //if neighbours is too small. 
	}
}