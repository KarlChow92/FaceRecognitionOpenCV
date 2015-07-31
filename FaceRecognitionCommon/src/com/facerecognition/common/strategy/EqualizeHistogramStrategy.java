package com.facerecognition.common.strategy;

import static org.bytedeco.javacpp.opencv_imgproc.equalizeHist;

import org.bytedeco.javacpp.opencv_core.Mat;

public class EqualizeHistogramStrategy implements PreProcessingStrategy {

	public EqualizeHistogramStrategy() {
	}
	
	@Override
	public Mat execute(Mat mat) {
		Mat equalizedMat = new Mat();
		equalizeHist(mat, equalizedMat);

		return equalizedMat;
				
	}

}
