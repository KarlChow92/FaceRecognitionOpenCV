package com.facerecognition.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.facerecognition.common.model.enums.Algorithm;
import com.facerecognition.common.util.interfaces.IClassifierPathHandler;

public class FalseMatchRateManager {

	private int sizeTestSet;
	private Map<String, Integer> falseMatchMap;

	public FalseMatchRateManager(int sizeTestSet) {
		this.sizeTestSet = sizeTestSet;
		setFalseMatchMap(new HashMap<String, Integer>());
	}

	public void addFalseMatchRate(String subjectName) {
		if (getFalseMatchMap().containsKey(subjectName)) {
			int falseCount = getFalseMatchMap().get(subjectName);
			falseCount++;
			getFalseMatchMap().put(subjectName, falseCount);
		} else {
			getFalseMatchMap().put(subjectName, 1);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		double percFMR = getFalseMatchRate();
		builder.append("\nPercentage false match rate = " + round(percFMR, 2) + "%");
		builder.append("\nRaw stats ");
		builder.append("\nTotal size test set  = " + sizeTestSet);
		return builder.toString();
	}

	public void writeToFile(Algorithm algorithm, String db, int trainingSubjects,
			IClassifierPathHandler classifierPathHandler) {
		File experimentOutputFile = new File(classifierPathHandler.getBasePath().getAbsolutePath()
				+ "/" + algorithm.getName() + "_" + db + "_" + trainingSubjects + ".txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(experimentOutputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		out.print(toString());
		out.close();
	}

	private double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void reset(int newTestSize) {
		setFalseMatchMap(new HashMap<String, Integer>());
		if (newTestSize != 0) {
			sizeTestSet = 0;
		}
	}

	public Map<String, Integer> getFalseMatchMap() {
		return falseMatchMap;
	}

	private void setFalseMatchMap(Map<String, Integer> falseMatchMap) {
		this.falseMatchMap = falseMatchMap;
	}

	public double getFalseMatchRate() {
		int totalFalseMatchCount = 0;
		for (String subject : falseMatchMap.keySet()) {
			double falseMatchCountSubject = falseMatchMap.get(subject);
			totalFalseMatchCount += falseMatchCountSubject;
		}
		return ((double) totalFalseMatchCount / (double) sizeTestSet) * 100;
	}
}
