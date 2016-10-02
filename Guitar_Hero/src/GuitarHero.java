
public class GuitarHero {

	public static void main(String[] args) {

		// Create 37 guitar strings
		GuitarString[] Strings = new GuitarString[37];
		for (int i = 0; i < 37; i++) {
			Strings[i] = new GuitarString(440 * Math.pow(1.05956, i - 24));
		}
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

		// the main input loop
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
			}

			// compute the superposition of the samples
			double sample = 0;
			for (int i = 0; i < 37; i++) {
				sample += Strings[i].sample();
			}

			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for (int i = 0; i < 37; i++) {
				Strings[i].tic();
			}
		}
	}

}