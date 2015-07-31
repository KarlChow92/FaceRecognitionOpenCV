package com.facerecognition.common.factory;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.model.interfaces.ISubjectDatabaseModel;

public interface ISubjectDatabaseModelFactory {

	public ISubjectDatabaseModel makeATTDatabaseModel(AbstractDatabase database);

	public ISubjectDatabaseModel makeFERETDatabaseModel(AbstractDatabase database);

	public ISubjectDatabaseModel makeYaleADatabaseModel(AbstractDatabase database);

	public ISubjectDatabaseModel makeYaleBDatabaseModel(AbstractDatabase database);

}
