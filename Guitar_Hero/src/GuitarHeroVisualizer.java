public class GuitarHeroVisualizer {

	public static void main(String[] args) {

		// Create 37 guitar strings
		GuitarString[] Strings = new GuitarString[37];
		for (int i = 0; i < 37; i++) {
			Strings[i] = new GuitarString(440 * Math.pow(1.05956, i - 24));
		}
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		// the main input loop

		// this ringbuffer stores the values of the samples. Give it the
		// argument for the number of sample we want to plot with StdDraw.
		RingBuffer wave = new RingBuffer(Integer.parseInt(args[0]));

		StdDraw.setXscale(0, wave.length());
		StdDraw.setYscale(-.5, .5);
		StdDraw.line(0, 0, wave.length(), 0);

		while (true) {

			// check if the user has typed a key, and, if so, process it
			if (StdDraw.hasNextKeyTyped()) {

				// the user types this character
				char key = StdDraw.nextKeyTyped();

				// pluck the corresponding string
				for (int i = 0; i < 37; i++) {
					if (key == keyboard.charAt(i))
						Strings[i].pluck();
				}
				for (int i = 0; i < wave.length(); i++) {
					StdOut.println(wave.get(i));
				}
				StdDraw.clear();
				for (int i = 1; i < wave.length(); i++) {
					// draw a line which connects the values of the samples. In
					// doing so, we kinda create a graph for every sample, which
					// looks like the sound wave.
					StdDraw.line(i - 1, wave.get(i - 1), i, wave.get(i));
				}
				StdDraw.line(wave.length() - 1, wave.get(wave.length() - 1),
						wave.length(), 0);
			}

			// compute the superposition of the samples
			double sample = 0;
			for (int i = 0; i < 37; i++) {
				sample += Strings[i].sample();
			}
			if (wave.isFull()) {
				wave.dequeue(); // dequeue value when wave is full.
				wave.enqueue(sample); // enqueue value to wave.
			} else {
				wave.enqueue(sample); // enqueue value to wave.
			}

			// send the result to standard audio
			StdAudio.play(sample);

			// if (count==109000) StdOut.println(s.elapsedTime());

			/*
			 * if (count % 66600 == 0) {
			 * 
			 * for (int i = 1; i < wave.length(); i++) { StdDraw.clear();
			 * StdDraw.line(i-1, 0, i, wave.get(i)); StdDraw.line(i,
			 * wave.get(i), i+1, 0); } }
			 */
			// advance the simulation of each guitar string by one step
			for (int i = 0; i < 37; i++) {
				Strings[i].tic();
			}
		}
	}

}