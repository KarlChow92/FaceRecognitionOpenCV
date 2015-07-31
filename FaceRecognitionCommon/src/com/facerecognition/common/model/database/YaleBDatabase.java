package com.facerecognition.common.model.database;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.visitor.IDatabaseVisitor;

public class YaleBDatabase extends AbstractDatabase {

	private static final long serialVersionUID = 1L;
	public static final String DB_NAME = "YALE_B_DATABASE";
	
	public YaleBDatabase(int amountTrainingImages, int amountTrainingSubjects) {
		super(amountTrainingImages, amountTrainingSubjects);
	}

	public YaleBDatabase() {
		super();
	}

	@Override
	public String getName() {
		return DB_NAME;
	}

	@Override
	public <T> T accept(IDatabaseVisitor<T> visitor) {
		return visitor.visit(this);
	}
}
