
/*************************************************************************
 * Name         : Nam Phan
 *
 * Dependencies :
 * Description  : 
 *  
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/

public class GuitarString {

    private RingBuffer buffer; // ring buffer
    // YOUR OTHER INSTANCE VARIABLES HERE
    private int capacity = 0;
    private int time = 0;
    
    
    // create a guitar string of the given frequency
    public GuitarString(double frequency) {
        // YOUR CODE HERE
    	buffer = new RingBuffer((int)(44100/frequency));
    	capacity = (int)frequency;
    	
    	
    }

    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        // YOUR CODE HERE
    	buffer = new RingBuffer(init.length);
    	for(int i = 0; i<init.length; i++) {
    		buffer.enqueue(init[i]);
    	}
    }

    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        // YOUR CODE HERE
    	for(int i = 0; i<capacity; i++){
    	
    		double x = StdRandom.uniform(-0.5, 0.5);
			if (buffer.isFull()) {
				buffer.dequeue();
			}
			buffer.enqueue(x);
    	}
    }

    // advance the simulation one time step
    public void tic() {
        // YOUR CODE HERE
    	if (!buffer.isEmpty()){
			double x = buffer.dequeue();
			double y = buffer.peek();
			buffer.enqueue(0.994*(x + y)/2.0);
			time++;
    	}
    }

    // return the current sample
    public double sample() {
        // YOUR CODE HERE
    	if (buffer.isEmpty()) return 0;
    	
    	return buffer.peek();
    }

    // return number of times tic was called
    public int time() {
        // YOUR CODE HERE
    	return time;
    	
    }

    public static void main(String[] args) {
        /*
    	int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        /*
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
        */
    	GuitarString testString = new GuitarString(10);
    	
    	System.out.println(testString.sample());
    }

}