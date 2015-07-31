package com.facerecognition.common.strategy;

import org.bytedeco.javacpp.opencv_core.Mat;

public interface PreProcessingStrategy {
	public Mat execute(Mat mat);
}
