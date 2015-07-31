package com.facerecognition.common.requestbody.interfaces;

import com.facerecognition.common.model.AbstractDatabase;

public interface IPredictionBody {

	public byte[] getImageInBytes();

	public AbstractDatabase getDatabase();
}
