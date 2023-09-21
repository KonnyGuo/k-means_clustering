
public class FeatureInfo {

	private double[] featureVector; // e.g. if length 3, each would R, G and B / 255.0
	private int classNumber; // this the class number associated with the featureVector
	
	public FeatureInfo(int numberOfDimensions) {
		featureVector = new double[numberOfDimensions];
		classNumber = 0;
	} 
	
	public void setValues(double []vals) {
		for (int i=0; i<vals.length; i++) {
			featureVector[i] = vals[i];
		}
	}

	public double[] getFeatureVectorArray(){
		return featureVector;
	}
	
	public void addValues(FeatureInfo f) {
		for (int i=0; i< featureVector.length; i++) {
			featureVector[i] += f.featureVector[i];
		}
	}
	public void setClass(int classNum) {
		classNumber = classNum;
	}
	
	public int getClassNum() {
		return classNumber;
	}

	public int getFeatureVLength(){
		return featureVector.length;
	}
	
	public double getFeature(int i) {
		return featureVector[i];
	}

	public void setFeature(int i, double fval) {
		featureVector[i] = fval;
	}
	
	public double distance(FeatureInfo f2) {
		// code up the distance between this and f2
		// L2 Euclidean
		// Square root of the sum of the differences squared
		double dist = 0;
		double answerDist = 0;
		double diff = 0;

		for(int i = 0; i < featureVector.length; i++){
			diff = featureVector[i] - f2.featureVector[i];
			dist += Math.pow(diff,2);
		}

		answerDist = Math.sqrt(dist);
		return answerDist;
	}
	
	public static double distance(FeatureInfo f1, FeatureInfo f2) {
		return f1.distance(f2);
	}
}