import java.util.Stack;


public class FarthestInsertion {
	  public static void main(String[] args) {
	    	In in = new In("usa13509.txt");
	    	
	        // get dimensions
	        int w = in.readInt();
	        int h = in.readInt();
	        StdDraw.setCanvasSize(w, h);
	        StdDraw.setXscale(0, w);
	        StdDraw.setYscale(0, h);

	        // turn on animation mode
	        StdDraw.show(0);

	        // run smallest insertion heuristic
	        Tour tour = new Tour();
	        Queue<Point> Points = new Queue<Point>();
	        while (!in.isEmpty()) {
	            double x = in.readDouble();
	            double y = in.readDouble();
	            Point p = new Point(x, y);
	            Points.enqueue(p);
	          
	            // uncomment the 4 lines below to animate
	            // StdDraw.clear();
	            // tour.draw();
	            // StdDraw.text(100, 0, "" + tour.distance());
	            // StdDraw.show(50);
	        }
	        Point v = Points.dequeue();
	        Point[] store = new Point[Points.size()];
	        for (int i = 0; i<store.length; i++) {
	        	store[i] = Points.dequeue();
	        }
	        tour.insertSmallest(v);
	        
	        for (int i = 0; i<store.length; i++) {
	        	double min = store[i].distanceTo(tour.center());
	        	Point temp = store[i];
	        	for (Point p : store) {
	        		if (p.distanceTo(tour.center())<min && p!= null);
	        		{
	        			temp = p;
	        			min = p.distanceTo(tour.center());
	        		}
	        	}
	        	tour.insertSmallest(temp);
	        }
	        // draw to standard draw 
	        tour.draw();
	        StdDraw.show();
	        
	        // print tour to standard output
	        StdOut.printf("Tour distance =  %.4f\n", tour.distance());
	        StdOut.printf("Number of points = %d\n", tour.size());
	        tour.show();
	    }

}
