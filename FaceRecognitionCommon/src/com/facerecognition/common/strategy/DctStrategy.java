package com.facerecognition.common.strategy;

import static org.bytedeco.javacpp.opencv_core.CV_64F;
import static org.bytedeco.javacpp.opencv_core.CV_8UC1;
import static org.bytedeco.javacpp.opencv_core.dct;
import static org.bytedeco.javacpp.opencv_core.idct;

import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.opencv_core.Mat;

public class DctStrategy implements PreProcessingStrategy {

	private int grayValueIntensity = 0;

	public DctStrategy(int grayValueIntensity) {
		this.grayValueIntensity = grayValueIntensity;
	}

	@Override
	public Mat execute(Mat mat) {

		Mat convertedImg = new Mat();
		mat.convertTo(convertedImg, CV_64F);
		Mat dctFrequencies = new Mat();
		dct(convertedImg, dctFrequencies);
		Mat iDctMat = new Mat();
		DoubleIndexer indexer = dctFrequencies.createIndexer();
		for (int y = grayValueIntensity; y < dctFrequencies.rows(); y++) {
			for (int x = grayValueIntensity; x < dctFrequencies.cols(); x++) {
				indexer.put(y, x, 0.0);
			}
		}
		idct(dctFrequencies, iDctMat);
		iDctMat.convertTo(iDctMat, CV_8UC1);
		return iDctMat;
	}

}
