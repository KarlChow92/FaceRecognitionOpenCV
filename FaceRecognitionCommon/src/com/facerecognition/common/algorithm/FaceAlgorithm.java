package com.facerecognition.common.algorithm;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_highgui.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_highgui.imread;

import java.io.File;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.opencv_contrib.FaceRecognizer;
import org.bytedeco.javacpp.opencv_contrib.IntStringMap;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;

import com.facerecognition.common.data.interfaces.ISubjectLabelManager;
import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.model.SubjectLabel;
import com.facerecognition.common.model.interfaces.ISubject;
import com.facerecognition.common.model.interfaces.ISubjectLabel;
import com.facerecognition.common.parser.ClassifierXmlParser;
import com.facerecognition.common.strategy.DctStrategy;
import com.facerecognition.common.strategy.EqualizeHistogramStrategy;
import com.facerecognition.common.strategy.PreProcessingStrategy;
import com.facerecognition.common.util.interfaces.IClassifierPathHandler;

/**
 * Created by karl on 15/04/15.
 */

public abstract class FaceAlgorithm {

	private ISubjectLabelManager manager;
	private FaceRecognizer faceRecognizer;
	protected File classifierPath;
	private boolean hasModel = false;
	private Mat labels;
	private MatVector images;
	private List<PreProcessingStrategy> preprocessingStrategy;

	public FaceAlgorithm(FaceRecognizer faceRecognizer, File classifierPath,
			ISubjectLabelManager manager) {
		this.manager = manager;
		this.faceRecognizer = faceRecognizer;
		this.classifierPath = classifierPath;
		this.preprocessingStrategy = new ArrayList<PreProcessingStrategy>();
		if (classifierPath.exists() && manager != null) {
			faceRecognizer.load(classifierPath.getAbsolutePath());
			hasModel = true;
			manager.loadNewSource(ClassifierXmlParser.getLabelsForClassifier(classifierPath));
		}
	}

	public ISubjectLabel predict(Mat matImage) {
		if (hasModel) {
			int label = faceRecognizer.predict(matImage);
			if (manager != null) {
				ISubjectLabel subjectLabel = manager.getSubject(label);
				return subjectLabel;
			}
			BytePointer stringPointer = faceRecognizer.getLabelInfo(label);
			return new SubjectLabel(label, stringPointer.getString());
		}
		return new SubjectLabel(0, "");
	}

	public ISubjectLabel predictPerson(Mat matImage, ISubject subject) {
		int[] labels = new int[1];
		double[] confidence = new double[]{0.0};
		if (hasModel) {
			faceRecognizer.predict(matImage, labels, confidence);
			return manager.getSubject(labels[0]);

		}
		return new SubjectLabel(0, "");
	}

	public void trainAlgorithm(ISubject[] subjectArray) {
		if (manager != null) {
			manager.emptySource();
		}
		int totalImages = 0;
		for (ISubject subject : subjectArray) {
			totalImages += subject.getAmountImages();
		}
		System.out.println(totalImages);
		this.images = new MatVector(totalImages);
		this.labels = new Mat(totalImages, 1, CV_32SC1);
		IntBuffer labelsBuf = this.labels.getIntBuffer();
		IntStringMap map = new IntStringMap();
		int counter = 0;

		for (ISubject subject : subjectArray) {
			for (File imageFile : subject.getImages()) {
				String name = subject.getName();
				Mat mat = imread(imageFile.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

				for (PreProcessingStrategy preProcessingStrategy : this.preprocessingStrategy) {
					mat= preProcessingStrategy.execute(mat);
				}
				
				int label = counter;
				images.put(counter, mat);
				map.put(label, new BytePointer(name));
				labelsBuf.put(counter, label);
				if (manager != null) {
					manager.saveLabel(new SubjectLabel(label, name));
				}
				counter++;	
			}
		}
		faceRecognizer.setLabelsInfo(map);
		faceRecognizer.train(images, this.labels);
		faceRecognizer.save(classifierPath.getAbsolutePath());
		hasModel = true;
	}

	public File getClassifierPath() {
		return classifierPath;
	}

	public boolean hasModel() {
		return hasModel;
	}

	public void addPreProcessing(PreProcessingStrategy strategy) {
		this.preprocessingStrategy.add(strategy);
	}

	public void addPreProcessing(List<PreProcessingStrategy> strategyList) {
		this.preprocessingStrategy.addAll(strategyList);
	}

	public abstract void setNewPath(AbstractDatabase database, IClassifierPathHandler handler);
}
