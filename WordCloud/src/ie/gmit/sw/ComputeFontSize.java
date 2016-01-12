package ie.gmit.sw;

public class ComputeFontSize {

	public static int getFontSize(int freq) {

		//
		int fontSize = 25; // min
		if (freq >= 1 && freq <= 3) {
			fontSize = fontSize;
		} else if (freq > 3 && freq <= 5) {
			fontSize = fontSize * 2;

		} else if (freq > 5) {
			fontSize = fontSize * 3;
		}

		return fontSize;
	}
}
