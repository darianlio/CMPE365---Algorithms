//Bounding Box Class
public class BoundingBox {
	
	//Initialize Pairs for all four points in rectangle
	private Pair bl, br, tl, tr;
		
	//Create Constructor
	public BoundingBox(Pair a, Pair b, Pair c, Pair d) {
		this.bl = a;
		this.br = b;
		this.tl = c;
		this.tr = d;
	}
	
	//Getters
	public Pair getBL() {
		return bl;
	}
	
	public Pair getBR() {
		return br;
	}
	
	public Pair getTL() {
		return tl;
	}
	
	public Pair getTR() {
		return tr;
	}	
	
	//Count the amount of pairs there are in the bounding box
	public int countPair(double[] x, double[] y) {
		
		int pair = 0;
		
		for(int i = 0; i < x.length; i++) {
			//Count each pair that is within the boundary
			if(x[i] >= bl.getX() && x[i] <= br.getX() && y[i] >= bl.getY() && y[i] <= tl.getY()) {
				pair++;
			}
		}
		
		return pair;
	}
	
	public double generateRatio(double[] x, double[] y) {
		//Ratio is equal to number of points per area
		return countPair(x, y)/getArea();
	}

	public double getArea() {
		//Area = (x2-x1)*(y2-y1)
		return ((br.getX() - bl.getX())*(tl.getY()-bl.getY()));
	}
}
