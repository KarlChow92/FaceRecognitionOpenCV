package com.facerecognition.common.requestbody;

import org.apache.tomcat.util.codec.binary.Base64;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.requestbody.interfaces.IPredictionBody;

public class PredictioniOSBody implements IPredictionBody {
	private AbstractDatabase database;
	private String image;

	public PredictioniOSBody() {
	}

	public PredictioniOSBody(AbstractDatabase database, String image) {
		this.image = image;
		this.database = database;
	}

	public byte[] getImageInBytes() {
		return Base64.decodeBase64(image);
	}

	public void setImage(String image) {
		this.image = image;
	}

	public AbstractDatabase getDatabase() {
		return database;
	}

	public void setConfig(AbstractDatabase config) {
		this.database = config;
	}
}
