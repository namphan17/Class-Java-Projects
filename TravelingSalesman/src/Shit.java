
public class Shit {
	private static class Node {
		private Point point;
		private Node next;
	}
	public static void main(String[] args) {
		/*
		Node start = new Node();
		start.point = new Point(0.1, 0.1);
		Node second = new Node();
		second.point = new Point(0.1, 0.9);
		start.next = second;
		second.next = start;
		
		StdOut.println(second.point);
		StdOut.println(start.next.point);
		start.next = start;
		StdOut.println(second.point);
		StdOut.println(start.next.point);
		StdOut.println(Math.pow(2, 3));
		*/
		Point a = new Point(1, 1);
		Point b = a;
		StdOut.println(b);
		b = null;
		StdOut.println(b);
	}
}
