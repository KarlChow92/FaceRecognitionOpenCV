package com.facerecognition.common.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.facerecognition.common.filter.DirectoryFilter;
import com.facerecognition.common.filter.ImageFilter;
import com.facerecognition.common.model.interfaces.ISubject;
import com.facerecognition.common.model.interfaces.ISubjectDatabaseModel;

public class SubjectDatabaseModel implements ISubjectDatabaseModel {

	private List<ISubject> subjects;
	private int trainingImagesPerSubject = 0;
	private int amountSubjectsPerTraining = 0;
	private int totalTrainingImages = 0;
	private int totalTestImages = 0;
	private int totalSubjects = 0;
	private int max = 0;
	private AbstractDatabase database;

	public SubjectDatabaseModel(File dataSetPath, AbstractDatabase database) {
		subjects = new ArrayList<ISubject>();
		for (File folder : dataSetPath.listFiles(new DirectoryFilter())) {
			List<File> subjectImages = new ArrayList<File>();
			for (File imageFile : folder.listFiles(new ImageFilter())) {
				subjectImages.add(imageFile);
			}
			subjects.add(new Subject(folder.getName(), subjectImages));
		}
		if (database != null) {
			setSubjectsPerTraining(database.getAmountTrainingSubjects());
			setTrainingImagesPerSubject(database.getAmountTrainingImages());
			this.database = database;
		}
	}

	@Override
	public Subject[] getTestSubjects() {
		totalTestImages = 0;
		totalTrainingImages = 0;
		List<ISubject> testSubjectList = new ArrayList<ISubject>();
		for (ISubject subject : subjects) {
			ISubject testSubject = subject.getSubset(getTrainingImagesPerSubject(),
					getMaxImagesPerSubject());
			testSubjectList.add(testSubject);
			totalTestImages += testSubject.getAmountImages();
			totalTrainingImages += getTrainingImagesPerSubject();
		}
		return testSubjectList.toArray(new Subject[testSubjectList.size()]);
	}
	@Override
	public ISubject[] getTrainingSubjects() {
		totalTrainingImages = 0;
		List<ISubject> trainingSubjectList = new ArrayList<ISubject>();
		List<ISubject> subSet = subjects.subList(0, getSubjectsPerTraining());
		for (ISubject subject : subSet) {
			if (getTrainingImagesPerSubject() > subject.getAmountImages()) {
				trainingSubjectList.add(subject.getSubset(0, subject.getAmountImages()));
			} else {
				trainingSubjectList.add(subject.getSubset(0, getTrainingImagesPerSubject()));
			}
			totalTestImages += subject.getAmountImages() - getTrainingImagesPerSubject();
			totalTrainingImages += getTrainingImagesPerSubject();
		}
		return trainingSubjectList.toArray(new Subject[trainingSubjectList.size()]);
	}

	@Override
	public void setTrainingImagesPerSubject(int amountTestImages) {
		if (getTrainingImagesPerSubject() == 0) {
			this.trainingImagesPerSubject = 5;
		}
		this.trainingImagesPerSubject = amountTestImages;
	}

	@Override
	public int getTrainingImagesPerSubject() {
		if (this.trainingImagesPerSubject == 0) {
			return 5;
		}
		return this.trainingImagesPerSubject;
	}

	@Override
	public String[] getSubjectNames() {
		List<String> namesList = new ArrayList<String>();
		for (ISubject subject : subjects) {
			namesList.add(subject.getName());
		}
		return namesList.toArray(new String[namesList.size()]);
	}

	public int getMaxImagesPerSubject() {
		if (max == 0) {
			for (ISubject subject : subjects) {
				if (subject.getImages().length > max) {
					max = subject.getImages().length;
				}
			}
		}
		return max;
	}

	public int getTotalTestImages() {
		return totalTestImages;
	}

	public int getTotalTrainingImages() {
		return totalTrainingImages;
	}

	public int getTotalImages() {
		if (totalSubjects == 0) {
			for (ISubject subject : subjects) {
				totalSubjects += subject.getAmountImages();
			}
		}
		return totalSubjects;
	}

	@Override
	public void setSubjectsPerTraining(int amount) {
		this.amountSubjectsPerTraining = amount;
	}

	@Override
	public int getSubjectsPerTraining() {
		if (this.amountSubjectsPerTraining == 0
				|| this.amountSubjectsPerTraining >= subjects.size()) {
			return subjects.size();
		}
		return this.amountSubjectsPerTraining;
	}

	public File getRandomImage() {
		Random random = new Random();
		ISubject subject = subjects.get(random.nextInt(subjects.size() - 1));
		return subject.getImages()[random.nextInt(subject.getImages().length)];
	}

	public AbstractDatabase getDb() {
		return database;
	}
}
