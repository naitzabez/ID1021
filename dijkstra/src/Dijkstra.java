public class Dijkstra {
	private PathQueue pathQueue;
	private Path[] done;
	private City start,end = null;
	private final int mod = 541;
	private int processed = 0;
	private int dist = 0;
	private Path ending;
	//Constructor for Dijkstra
	public Dijkstra(City start, City end){
		this.start = start;
		this.end = end;
		this.done = new Path[mod];
		this.pathQueue = new PathQueue(mod);
		initStart();
	}
	//Initializes the path finder
	public void initStart() {
		addPath(start, null, 0); //add start -> cost to 0, since we're here
		addPath(end,null,null); //add end to last queue spot
	}
	//Finds shortest path
	public void pathFind() {
		Path current = pathQueue.getIndex(0);
		pathQueue.sink();
		while(current != null) {
			if(current.city == end) {
				break;
			}
			done[current.city.id] = current;
			addNeighbours(current);
			current = pathQueue.sink();
			processed +=1;
		}
		ending = current;
	}
	public void printPath(Path current) {

		if(current == null) {
			System.out.println("### No path found!");
		}
		System.out.println("### Path found!");
		System.out.println("### City: " + current.city.name);
		System.out.println("### Dist: " + current.dist);
		System.out.println("### Prev: " + current.prev.name);
		System.out.println("### Paths processed: " + processed);
		dist = current.dist;
		Path t = current;
		int a = 0;
		while(t.prev != null) {
			System.out.println(t.city.name + " index:"+ a + " dist: " + t.dist);
			a+=1;
			t = done[t.prev.id];
		}
		System.out.println(t.city.name + " index:"+ a);
	}
	//Simple path adding to pathqueue
	public void addPath(City c, City p, Integer d) {
		Path pt = new Path(c,p,d);
		pathQueue.bubble(pt);
	}
	public boolean existInQueue(int i) {
		return pathQueue.getIndex(i) == null ? false : true;
	}
	//Add neighbours for a city to queue (if they dont already exist / been processed)
	public void addNeighbours(Path p) {
		for(int i = 0; i < p.city.neighbours.length; i++) {
			Connection con = p.city.neighbours[i];
			if(con == null) {
				return;
			}else {
				if(existInDone(con.city.id,con.city)) {
					continue;
				}else{
					if(!pathQueue.exists(con.city)) {
						addPath(con.city, p.city ,(con.dst + p.dist));
						continue;
					}else {
						updateQueue(p, con);
						continue;
					}
				}
			}
		}
	}

	public void updateQueue(Path p, Connection c) {
		Integer ind = pathQueue.findPath(0, c.city.id);
		if(pathQueue.getIndex(ind).dist > (c.dst + p.dist)) {
				pathQueue.sinkIndex(ind); //remove old path
				addPath(c.city, p.city ,(c.dst + p.dist)); 
				return;
		}
		addPath(c.city, p.city ,(c.dst + p.dist)); //add new path
	}
	
	public boolean existInDone(int index, City c) {
		if(done[index] == null) {
			return false;
		}
		if(done[index].city == c) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		String path = "src/europe.csv";
		Map m = new Map(path);
		test(m);
		
	}
	public static void test(Map m) {
		long t1, t2, sum = 0;
		Dijkstra x = new Dijkstra(m.lookup("Madrid"), m.lookup("Kiruna"));
		t1 = System.nanoTime();
		x.pathFind();
		t2 = System.nanoTime();
		sum += (t2-t1);
		System.out.println("sum: " + sum /1_000_000 + "ms");
		x.printPath(x.ending);
	}
}
