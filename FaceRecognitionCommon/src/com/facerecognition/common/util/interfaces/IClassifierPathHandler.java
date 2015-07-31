package com.facerecognition.common.util.interfaces;

import java.io.File;

import com.facerecognition.common.model.AbstractDatabase;

public interface IClassifierPathHandler {
	public File getEigenfaceClassifierFile(AbstractDatabase database);

	public File getFisherfaceClassifierFile(AbstractDatabase database);

	public File getEigenfaceClassifierFile();

	public File getFisherfaceClassifierFile();
	
	public File getBasePath();
}