
public class KMeans {

	private int K; // number of classes
	private FeatureInfo[][] pixelFeatures; // 2d array of same size as the image
	private FeatureInfo[] means; // array of size K holding the means 
	private int numDimensions; // number of dimensions of the feature vector
	
	public KMeans(int numClasses, int numRows, int numCols, int n) {
		K = numClasses;
		numDimensions = n;
		pixelFeatures = new FeatureInfo[numRows][numCols];
		means = new FeatureInfo[K];
	} 

	public int getNumDimension(){
		return numDimensions;
	}

	public RGBImage runKmeans() {
	
		randomizeMeans();

		// create a labelled (by classes) image 
		RGBImage lbldImg = new RGBImage(pixelFeatures.length, 
                                                pixelFeatures[0].length);

		boolean changesHappened = true;
		int iter = 0;
		// loop until no changes to classes
		while (changesHappened) {
			changesHappened = false;
			// needs to determine the class for a pixel 
			// can have a method that determines this class and returns it
			// e.g. call:  int classNum = determineClass(pixelFeatures[r][c]);

			for(int r = 0; r < pixelFeatures.length; r++){
				for(int c = 0; c < pixelFeatures[0].length; c++){
					int classNum = determineClass(pixelFeatures[r][c]);
					// if ((r==0) && (c==2)) {
					// 	System.out.println(pixelFeatures[r][c].getFeature(0)*255);
					// 	System.out.println(pixelFeatures[r][c].getFeature(1)*255);
					// 	System.out.println(pixelFeatures[r][c].getFeature(2)*255);
					// System.out.println("r = " + r + " c = " + c  + " classNum = " + classNum);
						
					//}

					if (classNum != pixelFeatures[r][c].getClassNum()) {
						changesHappened = true;
						pixelFeatures[r][c].setClass(classNum);
					}
				}
			}
			// iter += 1;
			// System.out.println("iteration # " + iter);

			if (changesHappened) {
				//call: recomputeMeans();
				recomputeMeans();
			}
		}
		// for (int i=0; i<means.length; i++) {
		// 	System.out.println("r " + (int)(means[i].getFeature(0)*255) + " g " + (int) (means[i].getFeature(1)*255) + " b " + (int) (means[i].getFeature(2)*255));
		// }
		// set labelled image now from the classes
		for(int r = 0; r < pixelFeatures.length; r++){
			for(int c = 0; c < pixelFeatures[0].length; c++){
				//int classNumber1 = determineClass(pixelFeatures[r][c]);
				int classNumber = pixelFeatures[r][c].getClassNum();

				//means[classNumber] contains the centroid mean for the clusters of that class
				//we want to set that to each of the RGB

				lbldImg.setPixel(r, c, (int) (means[classNumber].getFeature(0)*255), (int) (means[classNumber].getFeature(1)*255),
									   (int) (means[classNumber].getFeature(2)*255));


				// lbldImg.setPixel(r, c, (int) means[classNumber].getFeature(classNumber), (int) means[classNumber].getFeature(classNumber),
				// (int) means[classNumber].getFeature(classNumber));
				// System.out.println(lbldImg.getPixel(r, c).getRed());

			}
		}

		
		return lbldImg;
	}


	// Standard deviation is square root of [sum of (value - mean) to second power) divided by the total number of values]
	public double calculateDeviation(int neighborSize){
		return 0;
	}

	//method for running texture and Intensity
	//incomplete
	public RGBImage runTextureAndIntensity(int neighborSize){
		randomizeMeans();
		RGBImage lbldImg = new RGBImage(pixelFeatures.length, pixelFeatures[0].length);
	
		boolean changesHappened = true;
	
		while (changesHappened) {
			changesHappened = false;
	
			for (int r = 0; r < pixelFeatures.length; r++) {
				for (int c = 0; c < pixelFeatures[0].length; c++) {
	
					// Compute intensity and standard deviation features for the current pixel
					double intensity = lbldImg.getPixel(r, c).getIntensity();
					// double stdDev = someValues.calculate Deviation
					// put info in class
						
					int classNum = determineClass(pixelFeatures[r][c]);
	
					if (classNum != pixelFeatures[r][c].getClassNum()) {
						changesHappened = true;
						pixelFeatures[r][c].setClass(classNum);
					}
				}
			}
	
			if (changesHappened) {
				recomputeMeans();
			}
		}
	
		for (int r = 0; r < pixelFeatures.length; r++) {
			for (int c = 0; c < pixelFeatures[0].length; c++) {
				int classNumber = pixelFeatures[r][c].getClassNum();
	
				lbldImg.setPixel(r, c, (int) (means[classNumber].getFeature(0) * 255),
						(int) (means[classNumber].getFeature(1) * 255),
						(int) (means[classNumber].getFeature(2) * 255));
			}
		}
	
		return lbldImg;
	}


// 0.9196448511591157
// 0.40150798563541157
// 0.9418937659094855
// =-------
// 0.8655356787176697
// 0.7739606750594253
// 0.4796701067041663
// =-------
// 0.9809310965706453
// 0.6628758875841083
// 0.3077209163732021

	private void randomizeMeans() {
		// give random means for each dimension of each K in means array
		for (int i = 0; i < K; i++) {
			means[i] = new FeatureInfo(numDimensions);
			for (int j = 0; j < numDimensions; j++) {
				// generate a random value from 0-1
				double randomNum = Math.random();
				means[i].setFeature(j, randomNum);
			}
		}
		// means[0] = new FeatureInfo(numDimensions);
		// means[1] = new FeatureInfo(numDimensions);
		// means[2] = new FeatureInfo(numDimensions);
		// means[0].setFeature(0, 0.9196448511591157);
		// means[0].setFeature(1, 0.40150798563541157);
		// means[0].setFeature(2, 0.9418937659094855);
		// means[1].setFeature(0, 0.8655356787176697);
		// means[1].setFeature(1, 0.7739606750594253);
		// means[1].setFeature(2, 0.4796701067041663);
		// means[2].setFeature(0, 0.9809310965706453);
		// means[2].setFeature(1, 0.6628758875841083);
		// means[2].setFeature(2, 0.3077209163732021);

	}

	private void recomputeMeans() {
		//System.out.println("executing recompute");
		// will need a sum per K of all the items belonging to each class
		
		//FeatureInfo FeatureArr [] = new FeatureInfo[K];
		for(int i = 0; i < means.length; i++){
			means[i] = new FeatureInfo(numDimensions);
		}
		//System.out.println(FeatureArr.length + " 5 is expected");

		int counts[] = new int[K];

		// for all K's k 
		//for(int k = 0; k < K; k++){
			//int count = 0;
			// FeatureInfo sum = new FeatureInfo(numDimensions);
			for(int r = 0; r < pixelFeatures.length; r++){
				for(int c = 0; c < pixelFeatures[0].length; c++){
					//if (pixelFeatures[r][c].getClassNum() == k)
					//{
					means[pixelFeatures[r][c].getClassNum()].addValues(pixelFeatures[r][c]);;
					counts[pixelFeatures[r][c].getClassNum()]++;
					//}
					
				}
			}
			//need to divide by count after adding to compute new mean

			for(int k = 0; k < K; k++){
				for(int i = 0; i < numDimensions; i++){
					if(counts[k] > 0){
						//want .getFeatures to be 0-2
						double sum = means[k].getFeature(i);
						means[k].setFeature(i, sum/counts[k]); 
					}
				}

			}

	}

	public void assignPixelFeatures(int r, int c, double[] feats) {
		pixelFeatures[r][c] = new FeatureInfo(numDimensions);
		pixelFeatures[r][c].setValues(feats);
	}

	private int determineClass(FeatureInfo f) {
		// call distance in FeatureInfo class 
		// to determine smallest distance between f and each mean
		// return class associated with the smallest distance
		double dist = 0;
		int minClass = 0;
		double minDist = Double.MAX_VALUE;
		for(int i = 0; i < K; i++){
			dist = FeatureInfo.distance(f, means[i]);
			if(dist < minDist){
				minClass = i;
				minDist = dist;
			}
		}
		
		return minClass;

	}
}