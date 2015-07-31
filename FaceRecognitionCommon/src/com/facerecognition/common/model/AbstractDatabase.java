package com.facerecognition.common.model;

import java.io.Serializable;

import com.facerecognition.common.model.database.ATTDatabase;
import com.facerecognition.common.model.database.FERETDatabase;
import com.facerecognition.common.model.database.YaleADatabase;
import com.facerecognition.common.model.database.YaleBDatabase;
import com.facerecognition.common.visitor.IDatabaseVisitor;

public abstract class AbstractDatabase implements Serializable {

	private static final long serialVersionUID = 1L;
	private int amountTrainingImages = 0;
	private int amountTrainingSubjects = 0;

	public AbstractDatabase(int amountTrainingImages, int amountTrainingSubjects) {
		this.amountTrainingImages = amountTrainingImages;
		this.amountTrainingSubjects = amountTrainingSubjects;
	}

	public AbstractDatabase() {
	}

	public int getAmountTrainingImages() {
		return amountTrainingImages;
	}

	public void setAmountTrainingImages(int amountTrainingImages) {
		this.amountTrainingImages = amountTrainingImages;
	}

	public int getAmountTrainingSubjects() {
		return amountTrainingSubjects;
	}

	public void setAmountTrainingSubjects(int amountTrainingSubjects) {
		this.amountTrainingSubjects = amountTrainingSubjects;
	}

	public abstract String getName();

	public abstract <T> T accept(IDatabaseVisitor<T> visitor);

	public static AbstractDatabase getDatabaseByName(String name) {
		switch (name) {
			case ATTDatabase.DB_NAME :
				return new ATTDatabase();
			case YaleADatabase.DB_NAME :
				return new YaleADatabase();
			case YaleBDatabase.DB_NAME :
				return new YaleBDatabase();
			case FERETDatabase.DB_NAME :
				return new FERETDatabase();
			default :
				return null;
		}
	}
}
