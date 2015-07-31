package com.facerecognition.common.model.interfaces;

import java.io.File;

import com.facerecognition.common.model.AbstractDatabase;
import com.facerecognition.common.model.Subject;

public interface ISubjectDatabaseModel {

	public String[] getSubjectNames();

	public ISubject[] getTrainingSubjects();

	public Subject[] getTestSubjects();

	public void setTrainingImagesPerSubject(int amount);

	public int getTrainingImagesPerSubject();

	public void setSubjectsPerTraining(int amount);

	public int getSubjectsPerTraining();

	public int getTotalTestImages();

	public int getTotalTrainingImages();

	public int getTotalImages();

	public File getRandomImage();
	
	public int getMaxImagesPerSubject();

	public AbstractDatabase getDb();
}