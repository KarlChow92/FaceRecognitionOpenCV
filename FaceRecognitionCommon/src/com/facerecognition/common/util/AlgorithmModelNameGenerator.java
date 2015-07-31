package com.facerecognition.common.util;

import java.io.File;

import com.facerecognition.common.model.AbstractDatabase;

public class AlgorithmModelNameGenerator {

	private final static String XML_EXTENTION = ".xml";
	private final static String EIGENFACES = "eigenfaces";
	private final static String FISHERFACES = "fisherfaces";
	private final static String EIGENFACES_UNDERSCORE = EIGENFACES + "_";
	private final static String FISHERFACES_UNDERSCORE = EIGENFACES + "_";
	private final static String EIGENFACES_XML = EIGENFACES + XML_EXTENTION;
	private final static String FISHERFACES_XML = FISHERFACES + XML_EXTENTION;

	public static File getEigen(AbstractDatabase config) {
		if (config == null) {
			getEigen();
		}
		return getNameWithExtension(EIGENFACES_UNDERSCORE, config);
	}

	public static File getFisher(AbstractDatabase config) {
		if (config == null) {
			getFisher();
		}
		return getNameWithExtension(FISHERFACES_UNDERSCORE, config);
	}

	public static File getEigen() {
		return new File(EIGENFACES_XML);
	}

	public static File getFisher() {
		return new File(FISHERFACES_XML);
	}

	public static File getEigenWithoutExtension(AbstractDatabase config) {
		if (config == null) {
			getEigen();
		}
		return baseName(EIGENFACES_UNDERSCORE, config);
	}

	public static File getFisherWithoutExtension(AbstractDatabase config) {
		if (config == null) {
			getFisher();
		}
		return baseName(FISHERFACES_UNDERSCORE, config);
	}

	public static File getEigenWithoutExtension() {
		return new File(EIGENFACES);
	}

	public static File getFisherWithoutExtension() {
		return new File(FISHERFACES);
	}

	private static File getNameWithExtension(String algorithmPrefix, AbstractDatabase config) {
		StringBuilder builder = new StringBuilder();
		builder.append(baseName(algorithmPrefix, config).getAbsolutePath());
		builder.append(XML_EXTENTION);
		return new File(builder.toString());
	}

	private static File baseName(String algorithmPrefix, AbstractDatabase config) {
		StringBuilder builder = new StringBuilder();
		builder.append(algorithmPrefix);
		builder.append(config.getName());
		builder.append("_");
		builder.append(config.getAmountTrainingSubjects());
		builder.append("_");
		builder.append(config.getAmountTrainingImages());
		return new File(builder.toString());
	}
}
