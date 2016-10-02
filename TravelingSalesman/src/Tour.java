import java.awt.Color;

public class Tour {
	private int N;
	private Node start; // Starting point of the tour.

	private class Node {
		private Point point;
		private Node next;
	}

	public Tour() {
		N = 0;
		start = null;
	}

	public Tour(Point a, Point b, Point c, Point d) {
		this.insertSmallest(a);
		this.insertSmallest(b);
		this.insertSmallest(c);
		this.insertSmallest(d);
	}

	public int size() {
		return N;
	}
	
	public Point center() {
		Node temp = start;
		Point sum  = new Point(0, 0);
		for (int i = 0; i<N; i++) {
			sum = sum.add(temp.point);
			temp = temp.next;
		}
		return sum.mul(1.0/N);
	}

	public double distance() {
		double d = 0;
		Node temp = start;
		if (N == 0) {
			return d;
		} else {

			for (int i = 0; i < N; i++) {
				d += temp.point.distanceTo(temp.next.point);
				temp = temp.next;
			}
			d += temp.point.distanceTo(start.point);
			return d;
		}
	}

	public void show() {
		Node temp = start;
		if (N != 0) {
			for (int i = 0; i < N; i++) {
				StdOut.println(temp.point);
				temp = temp.next;
			}
		}
	}

	public void draw() {
		Node temp = start;
		if (N != 0) {
			for (int i = 0; i < N; i++) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.setPenRadius(0.009);
				temp.point.draw();
				StdDraw.setPenColor(Color.BLUE);
				StdDraw.setPenRadius(0.003);
				temp.point.drawTo(temp.next.point);
				temp = temp.next;
			}
		}
		StdDraw.setPenColor();
		StdDraw.setPenRadius();
	}

	public void insertSmallest(Point p) {
		Node key = new Node();
		key.point = p;

		if (N > 1) {
			key.next = start.next;

			Node temp = new Node();
			temp = start;
			double d1 = temp.point.distanceTo(temp.next.point);
			double d2 = p.distanceTo(temp.point);
			double d3 = p.distanceTo(temp.next.point);
			double min = d3 + d2 - d1;

			for (int i = 0; i < N; i++) {
				double d11 = temp.point.distanceTo(temp.next.point);
				double d22 = p.distanceTo(temp.point);
				double d33 = p.distanceTo(temp.next.point);
				double minTemp = d33+d22-d11;
				if (minTemp < min) {
					key.next = temp.next;
					min = minTemp;
				}
				temp = temp.next;
			}

			while (temp.next != key.next) {
				temp = temp.next;
			}
			temp.next = key;

		} else {
			if (N == 0) {
				start = key;
				start.next = start;
			} else {
				start.next = key;
				key.next = start;
			}
		}
		N++;
	}

	public void insertNearest(Point p) {
		Node key = new Node();
		key.point = p;

		if (N > 1) {
			Node temp = start;
			double min = p.distanceTo(start.point);

			key.next = start.next;
			for (int i = 0; i < N; i++) {
				if (p.distanceTo(temp.point) < min) {
					min = p.distanceTo(temp.point);
					key.next = temp.next;
				}
				temp = temp.next;
			}

			while (temp.next != key.next) {
				temp = temp.next;
			}
			temp.next = key;
		} else {
			if (N == 0) {
				start = key;
				start.next = start;
			} else {
				start.next = key;
				key.next = start;
			}
		}

		N++;
	}

	public static void main(String[] args) {
		Point a = new Point(0.1, 0.1);
		Point b = new Point(0.1, 0.9);
		Point c = new Point(0.9, 0.9);
		Point d = new Point(0.9, 0.1);
		Tour tsm = new Tour(a, b, c, d);
		tsm.show();
		tsm.draw();
	}

}
