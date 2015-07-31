package com.facerecognition.common.filter;

import java.io.File;
import java.io.FilenameFilter;

public class ImageFilter implements FilenameFilter {

	private final static String[] acceptedFormats = new String[]{".png", ".gif", ".jpg", ".jpeg",
			".jpg", ".bmp"};

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
