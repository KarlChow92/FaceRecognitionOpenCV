package com.facerecognition.common.model.interfaces;

import java.io.File;

public interface ISubject {

	public abstract String getName();

	public abstract File[] getImages();

	public abstract void addImageFile(File file);

	public abstract int getAmountImages();

	public abstract ISubject getSubset(int start, int end);

}