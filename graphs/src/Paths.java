
public class Paths {
	City[] path;
	int sp;
	
	public Paths() {
		path = new City[54];
		sp = 0;
	}
	private Integer shortestDynamic(City from, City to, Integer max) {
		if (from == to) {
			return 0;
		}
		for (int i = 0; i < sp; i++) {
			if (path[i] == from) {
				return null;
			}
		}
		path[sp++] = from;
		Integer shrt = null;
		for(int i = 0; i < from.neighbours.length; i++) {
			if(from.neighbours[i] != null) {
				Connection conn = from.neighbours[i];
				Integer dist = shortestDynamic(conn.neighbour, to, max);
				if(dist != null) {
					dist += conn.dst;
					if(max == null || max > dist) {
						max = dist;
						shrt = dist;
					}
				}
			}
		}
		if(max == null) {
			return null;
		}
		path[sp--] = null;
		return shrt;
	}
	public static void main(String[] args) {
		Map map = new Map("src/trains.csv");
		String from = "Södertälje";
		String to = "Kiruna";
		Paths loopfix = new Paths();
		long t0 = System.nanoTime();
		Integer dist = loopfix.shortestDynamic(map.lookup(from),map.lookup(to),null);
		long time = (System.nanoTime() - t0)/1000000;
		System.out.println("shortest dynamic: " + dist + " min (" + time + " ms)");
	}
	
}