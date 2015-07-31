package com.facerecognition.common.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.facerecognition.common.model.interfaces.ISubject;

public class Subject implements ISubject {

	private final String subjectName;
	private final List<File> imageFiles;

	public Subject(String subjectName, File[] images) {
		this.subjectName = subjectName;
		this.imageFiles = new ArrayList<File>(Arrays.asList(images));
	}

	public Subject(String subjectName, List<File> images) {
		this.subjectName = subjectName;
		this.imageFiles = images;
	}

	public Subject(String subjectName) {
		this.subjectName = subjectName;
		this.imageFiles = new ArrayList<File>();
	}

	@Override
	public String getName() {
		return subjectName;
	}

	@Override
	public File[] getImages() {
		return imageFiles.toArray(new File[imageFiles.size()]);
	}

	public String[] getImagesAsString() {
		ArrayList<String> stringList = new ArrayList<String>();
		for (File file : imageFiles) {
			stringList.add(file.getAbsolutePath());
		}
		return stringList.toArray(new String[stringList.size()]);
	}

	@Override
	public void addImageFile(File file) {
		imageFiles.add(file);
	}

	@Override
	public int getAmountImages() {
		return imageFiles.size();
	}

	@Override
	public Subject getSubset(int start, int end) {
		List<File> subset = null;

		if (start >= end || start > imageFiles.size()) {
			subset = new ArrayList<File>();
		} else if (end > imageFiles.size()) {
			subset = imageFiles.subList(start, imageFiles.size());
		} else {
			subset = imageFiles.subList(start, end);
		}
		Subject subject = new Subject(getName(), subset);
		return subject;
	}
}