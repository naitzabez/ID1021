public class Path {
	public City city;
	public City prev;
	public Integer dist = null;
	public Integer index = null;
	public Path(City c, City p, Integer d) {
		this.city = c;
		this.prev = p;
		if(d == null) {
			this.dist = Integer.MAX_VALUE;
		}else {
			this.dist = d;
		}
	}

}