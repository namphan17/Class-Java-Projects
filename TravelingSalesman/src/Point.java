
public class Point {
	private final double x;
	private final double y;
	
	
	public Point(double x, double y)  {
		this.x = x;
		this.y = y;
	}
	public String toString() {
		String s = "(" + x + ", " + y + ")";
		return s;
	}
	public Point add(Point p) {
		double a = this.x+p.x;
		double b = this.y+p.y;
		Point sum = new Point(a, b);
		return sum;
	}
	public Point mul(double x) {
		double a = this.x*x;
		double b = this.y*y;
		Point p = new Point(a, b);
		return p;
	}
	public double distanceTo(Point b) {
		double m = Math.pow((b.x-x), 2);
		double n = Math.pow((b.y-y), 2);
		return Math.sqrt(m+n);
	}
	public void draw(){
		StdDraw.point(x, y);
	}
	public void drawTo(Point b) {
		StdDraw.line(x, y, b.x, b.y);
	}		
	
	public static void main(String[] args) {
		Point a = new Point(157, 443);
		Point b = new Point(161, 280);
		Point c = new Point (325, 544);
		StdOut.println("a to b = " + a.distanceTo(b));
		StdOut.println("a to c = " + a.distanceTo(c));
	}
}
