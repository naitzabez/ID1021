
public class Naive {
	//:
	public static void main(String[] args) {
		Map map = new Map("src/trains.csv");
		String from = "Sundsvall";
		String to = "Göteborg";
		Integer max = 600;
		Paths loopfix = new Paths();
		long t0 = System.nanoTime();
		Integer dist = shortest(map.lookup(from), map.lookup(to), max);
		long time = (System.nanoTime() - t0)/1_000_000;
		
		System.out.println("shortest: " + dist + " min (" + time + " ms)");
	}
	private static Integer shortest(City from, City to, Integer max) {
		if (max < 0) {
			return null;
		}
		if (from == to) {
			return 0;
		}
		Integer shrt = null;
		for (int i = 0; i < from.neighbours.length; i++) {
			if (from.neighbours[i] != null) {
				Connection conn = from.neighbours[i];
				Integer timeLeft = max-conn.dst;
				Integer dist = shortest(conn.neighbour, to, timeLeft);
				if(dist != null) {
					dist +=conn.dst;
					if(shrt == null || dist < shrt) {
						shrt = dist;
					}
				}
			}
		}
		return shrt;
	}
	
}