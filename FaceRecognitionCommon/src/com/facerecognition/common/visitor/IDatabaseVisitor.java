package com.facerecognition.common.visitor;

import com.facerecognition.common.model.database.ATTDatabase;
import com.facerecognition.common.model.database.FERETDatabase;
import com.facerecognition.common.model.database.YaleADatabase;
import com.facerecognition.common.model.database.YaleBDatabase;

public interface IDatabaseVisitor<T> {

	public T visit(ATTDatabase db);
	public T visit(FERETDatabase db);
	public T visit(YaleADatabase db);
	public T visit(YaleBDatabase db);
	
}
