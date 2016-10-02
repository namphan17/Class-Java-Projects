/*************************************************************************
 * Name : Nam Phan
 *
 * Dependencies : Description :
 * 
 * This is a template file for RingBuffer.java. It lists the constructors and
 * methods you need, along with descriptions of what they're supposed to do.
 * 
 * Note: it won't compile until you fill in the constructors and methods (or at
 * least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/

public class RingBuffer {
	private double[] rb; // items in the buffer
	private int first; // index for the next dequeue or peek
	private int last; // index for the next enqueue
	private int size; // number of items in the buffer

	// create an empty buffer, with given max capacity
	public RingBuffer(int capacity) {
		rb = new double[capacity];
		size = 0;
		first = (int) (Math.random() * capacity);
		last = first;

	}
	public double get(int i) {
		return rb[i];
	}
	// return number of items currently in the buffer
	public int size() {
		return size;
	}
	public int length() {
		return rb.length;
	}

	// is the buffer empty (size equals zero)?
	public boolean isEmpty() {
		if (size != 0) return false;
		return true;
	}

	// is the buffer full (size equals array capacity)?
	public boolean isFull() {
		if (size == rb.length)
			return true;
		return false;
	}

	// add item x to the end
	public void enqueue(double x) {
		if (isFull()) {
			throw new RuntimeException("Ring buffer overflow");
		}
		// YOUR CODE HERE

		rb[last] = x;
		if (last == rb.length - 1)
			last = 0;
		else
			last++;
		size++;
	}

	// delete and return item from the front
	public double dequeue() {
		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		double x = rb[first];
		rb[first] = 0;
		size--;
		if (first == rb.length - 1)
			first = 0;
		else
			first++;
		return x;
	}

	// return (but do not delete) item from the front
	public double peek() {
		if (isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		}
		// YOUR CODE HERE
		return rb[first];
	}

	// a simple test of the constructor and methods in RingBuffer
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		RingBuffer buffer = new RingBuffer(N);
		for (int i = 1; i <= N; i++) {
			buffer.enqueue(i);
		}
		System.out.println(buffer.peek());
		double t = buffer.dequeue();
		
		
		buffer.enqueue(t);
		System.out.println("Size after wrap-around is " + buffer.size());
		while (buffer.size() >= 2) {
			double x = buffer.dequeue();
			double y = buffer.dequeue();
			buffer.enqueue(x + y);
		}
		System.out.println(buffer.peek());
	}

}