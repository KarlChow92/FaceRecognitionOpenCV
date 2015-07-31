package com.facerecognition.common.model.enums;

public enum Algorithm {
	FISHERFACES, EIGENFACES;

	public String getName() {
		switch (this) {
			case FISHERFACES :
				return "fisherfaces";
			case EIGENFACES :
				return "eigenfaces";
		}
		return "undefined";
	}

	public String getNameForWeb() {
		switch (this) {
			case FISHERFACES :
				return "fisher";
			case EIGENFACES :
				return "eigen";
		}
		return "undefined";
	}
}
