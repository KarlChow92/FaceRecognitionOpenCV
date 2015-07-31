package com.facerecognition.common.model.database;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.visitor.IDatabaseVisitor;

public class FERETDatabase extends AbstractDatabase {

	private static final long serialVersionUID = 1L;
	public static final String DB_NAME = "FERET_DATABASE";

	public FERETDatabase(int amountTrainingImages, int amountTrainingSubjects) {
		super(amountTrainingImages, amountTrainingSubjects);
	}

	public FERETDatabase() {
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
