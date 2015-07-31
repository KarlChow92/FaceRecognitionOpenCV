package com.facerecognition.common.visitor;

import com.facerecognition.common.factory.ISubjectDatabaseModelFactory;
import com.facerecognition.common.model.database.ATTDatabase;
import com.facerecognition.common.model.database.FERETDatabase;
import com.facerecognition.common.model.database.YaleADatabase;
import com.facerecognition.common.model.database.YaleBDatabase;
import com.facerecognition.common.model.interfaces.ISubjectDatabaseModel;

public class SubjectDatabaseModelCreater implements IDatabaseVisitor<ISubjectDatabaseModel> {

	private ISubjectDatabaseModelFactory factory;

	public SubjectDatabaseModelCreater(ISubjectDatabaseModelFactory factory) {
		this.factory = factory;
	}

	@Override
	public ISubjectDatabaseModel visit(ATTDatabase db) {
		return factory.makeATTDatabaseModel(db);
	}

	@Override
	public ISubjectDatabaseModel visit(FERETDatabase db) {
		return factory.makeFERETDatabaseModel(db);
	}

	@Override
	public ISubjectDatabaseModel visit(YaleADatabase db) {
		return factory.makeYaleADatabaseModel(db);
	}

	@Override
	public ISubjectDatabaseModel visit(YaleBDatabase db) {
		return factory.makeYaleBDatabaseModel(db);
	}

}
