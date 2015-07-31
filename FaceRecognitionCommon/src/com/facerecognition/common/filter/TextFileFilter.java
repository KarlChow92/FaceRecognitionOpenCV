package com.facerecognition.common.filter;

import java.io.File;
import java.io.FilenameFilter;

public class TextFileFilter implements FilenameFilter {

	private final static String[] acceptedFormats = new String[]{".txt"};

	@Override
	public boolean accept(File dir, String filename) {
		for (String format : acceptedFormats) {
			if (filename.toLowerCase().endsWith(format)) {
				return true;
			}
		}
		return false;
	}
}
