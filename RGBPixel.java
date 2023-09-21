import javax.lang.model.util.ElementScanner6;

/*

	A class that represents 1 pixel of a color image

	The ranges of the red, green and blue values are 0 .. 255 inclusive

	Author: Michael Eckmann
	Skidmore College
	for Spring 2023
	Digital Image Processing Course

*/
public class RGBPixel {
	private int r;
	private int g;
	private int b;

	public RGBPixel(int r, int g, int b) {
		if (r >= 0 && r <= 255)
			this.r = r;
		else
			if (r < 0) 
				this.r = 0;
			else   
				this.r = 255;

		if (g >= 0 && g <= 255)
			this.g = g;
		else
			if (g < 0) 
				this.g = 0;
			else   
				this.g = 255;
		if (b >= 0 && b <= 255)
			this.b = b;
		else
			if (b < 0) 
				this.b = 0;
			else   
				this.b = 255;

	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}

	public void setRed(int r) {
		if (r >= 0 && r <= 255)
			this.r = r;
	}

	public void setGreen(int g) {
		if (g >= 0 && g <= 255)
			this.g = g;
	}

	public void setBlue(int b) {
		if (b >= 0 && b <= 255)
			this.b = b;
	}

	public int getIntensity() {
		return (int) (r * 0.299 + g * 0.587 + b * .114);
	}

	// public YCbCrPixel convertToYCbCr() {
	// 	int y = (int) (this.r * 0.299 + this.g * 0.587 + this.b * .114);
	// 	double Cb = -0.17 * this.r - 0.33 * this.g + 0.5 * this.b + 128;
	// 	double Cr = 0.5 * this.r - 0.42 * this.g - 0.08 * this.b + 128;
	// 	return new YCbCrPixel(y, Cb, Cr);

	// }
}