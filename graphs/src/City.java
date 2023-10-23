
public class City {
	public Connection [] neighbours;
	public String name;
	
	public City(String name) {
		this.name = name;
		this.neighbours = new Connection [52];
	}
	
	public void connect(City nxt, int dst) {
		for(int i = 0; i < neighbours.length; i++) {
			if(neighbours[i] == null) {
				neighbours[i] = new Connection(nxt, dst);
				break;
			}
			if(neighbours[i].neighbour.name.equals(nxt.name)) {
				System.out.println("already exists");
				break;
			}
		}
	}
}
