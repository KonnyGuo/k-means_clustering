import java.io.IOException;

public class ClusterProg
{
	// args[0] is image name
	// args[1] is the K
	// - have a string as the third command line parameter with the following
	// possible values:
	//  "color"
	//  "intensity-and-texture"
	//  "texture"
	public static void main(String args[])
	{
		RGBImage inputImg = new RGBImage(args[0]);
		// K, rows, cols, numDimensions
		// KMeans km = new KMeans(Integer.parseInt(args[1]), 
		// 		     inputImg.getNumRows(), inputImg.getNumCols(), 3);
		// RGBImage ocean = new RGBImage("ocean.jpeg");
		// RGBImage inputImg = ocean;
		// K, rows, cols, numDimensions
		KMeans km = new KMeans(Integer.parseInt(args[1]), 
				     inputImg.getNumRows(), inputImg.getNumCols(), 3);

		// write code to compute the features of each pixel and then 
                //   assign those features for that pixel
		// need to call: km.assignPixelFeatures(r,c,features);

//row 2 column 2 line 126
//row 3 column 2 


		if((args[2]).equals("color")){
			for(int r =0; r<inputImg.getNumRows(); r++){
				for(int c = 0; c<inputImg.getNumCols(); c++){
					FeatureInfo features = new FeatureInfo(3);
					features.setFeature(0,inputImg.getPixel(r, c).getRed()/255.0);
					features.setFeature(1, inputImg.getPixel(r, c).getGreen()/255.0);
					features.setFeature(2, inputImg.getPixel(r, c).getBlue()/255.0);
	
					km.assignPixelFeatures(r,c, features.getFeatureVectorArray());
				}
			}
			
		}

		if(args[2].equals("intensity-and-texture")){
			for(int r =0; r<inputImg.getNumRows(); r++){
				for(int c = 0; c<inputImg.getNumCols(); c++){
					FeatureInfo features = new FeatureInfo(3);
					features.setFeature(0,inputImg.getPixel(r, c).getRed()/255.0);
					features.setFeature(1, inputImg.getPixel(r, c).getGreen()/255.0);
					features.setFeature(2, inputImg.getPixel(r, c).getBlue()/255.0);
	
					km.assignPixelFeatures(r,c, features.getFeatureVectorArray());
				}
			}
		}

		if(args[2].equals("texture")){
			for(int r =0; r<inputImg.getNumRows(); r++){
				for(int c = 0; c<inputImg.getNumCols(); c++){
					FeatureInfo features = new FeatureInfo(3);
					features.setFeature(0,inputImg.getPixel(r, c).getRed()/255.0);
					features.setFeature(1, inputImg.getPixel(r, c).getGreen()/255.0);
					features.setFeature(2, inputImg.getPixel(r, c).getBlue()/255.0);
	
					km.assignPixelFeatures(r,c, features.getFeatureVectorArray());
				}
			}
		}

		
		RGBImage labelledImage = km.runKmeans();
		try {
			labelledImage.writeImage("color-" + args[1] + "means-" + args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// try {
		// 	labelledImage.writeImage("Intensity-and-texture" + args[1] + "means-" + args[0]);
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// }

		
		// labelledImage.writeImage("output.jpg");
	}
}

