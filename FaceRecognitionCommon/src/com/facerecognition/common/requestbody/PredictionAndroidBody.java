package com.facerecognition.common.requestbody;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.requestbody.interfaces.IPredictionBody;

public class PredictionAndroidBody implements IPredictionBody {
	private AbstractDatabase database;
	private byte[] image;

	public PredictionAndroidBody() {
	}

	public PredictionAndroidBody(AbstractDatabase database, byte[] image) {
		this.image = image;
		this.database = database;
	}

	public byte[] getImageInBytes() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public AbstractDatabase getDatabase() {
		return database;
	}

	public void setConfig(AbstractDatabase database) {
		this.database = database;
	}
}
