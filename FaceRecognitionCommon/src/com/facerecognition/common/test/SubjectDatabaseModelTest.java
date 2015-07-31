package com.facerecognition.common.test;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.facerecognition.common.model.SubjectDatabaseModel;
import com.facerecognition.common.model.database.ATTDatabase;
import com.facerecognition.common.model.interfaces.ISubject;
import com.facerecognition.common.model.interfaces.ISubjectDatabaseModel;

public class SubjectDatabaseModelTest {

	private ISubjectDatabaseModel model;
	private final String attTestDb = "/Users/karl/Documents/bunq/Databases/att_normal";

	@Before
	public void setUp() throws Exception {
		File testDb = new File(attTestDb);
		Assert.assertEquals(true, testDb.exists());
		model = new SubjectDatabaseModel(testDb, new ATTDatabase());
	}

	@Test
	public void subjectsAmountTest() {
		Assert.assertEquals(40, model.getSubjectNames().length);
	}

	@Test
	public void subjectsNameTest() {
		for (String name : model.getSubjectNames()) {
			Assert.assertNotEquals(0, name.length());
		}
	}

	@Test
	public void subjectsUniqueNameTest() {
		Set<String> names = new HashSet<String>(Arrays.asList(model.getSubjectNames()));
		Assert.assertEquals(true, names.size() == model.getSubjectNames().length);
	}

	@Test
	public void testAmountTrainingSubject() {
		Assert.assertEquals(40, model.getTrainingSubjects().length);
	}

	@Test
	public void testAmountTestSubjects() {
		Assert.assertEquals(40, model.getTestSubjects().length);
	}

	@Test
	public void testInitialAmountTrainingImages() {
		ISubject[] subjects = model.getTrainingSubjects();
		for (ISubject subject : subjects) {
			Assert.assertEquals(model.getTrainingImagesPerSubject(), subject.getAmountImages());

		}
	}

	@Test
	public void testInitialAmountTestImages() {
		ISubject[] subjects = model.getTestSubjects();
		for (ISubject subject : subjects) {
			Assert.assertEquals(model.getTrainingImagesPerSubject(), subject.getAmountImages());
		}
	}

	@Test
	public void testAmountAllImages() {
		Assert.assertEquals(400, model.getTotalImages());
	}

	@Test
	public void testSetTrainingImagesPerSubject() {
		model.setTrainingImagesPerSubject(2);
		Assert.assertEquals(2, model.getTrainingImagesPerSubject());
	}

	@Test
	public void testAdjustedAmountTrainingImages() {
		model.setTrainingImagesPerSubject(3);
		ISubject[] subjects = model.getTrainingSubjects();

		for (ISubject subject : subjects) {
			Assert.assertEquals(model.getTrainingImagesPerSubject(), subject.getAmountImages());
			Assert.assertEquals(3, subject.getAmountImages());
		}
		Assert.assertEquals(120, model.getTotalTrainingImages());
		Assert.assertEquals(280, model.getTotalTestImages());
	}

	@Test
	public void testAdjustedAmountTrainingImages2() {
		model.setTrainingImagesPerSubject(8);
		ISubject[] subjects = model.getTrainingSubjects();

		for (ISubject subject : subjects) {
			Assert.assertEquals(model.getTrainingImagesPerSubject(), subject.getAmountImages());
			Assert.assertEquals(8, subject.getAmountImages());
		}
		Assert.assertEquals(320, model.getTotalTrainingImages());
		Assert.assertEquals(80, model.getTotalTestImages());
	}

	@Test
	public void testAdjustedAmountTestImages() {
		model.setTrainingImagesPerSubject(2);
		ISubject[] subjects = model.getTestSubjects();
		for (ISubject subject : subjects) {
			Assert.assertEquals(8, subject.getAmountImages());
		}
		Assert.assertEquals(80, model.getTotalTrainingImages());
		Assert.assertEquals(320, model.getTotalTestImages());
	}

	@Test
	public void testAdjustedAmountTestImages2() {
		model.setTrainingImagesPerSubject(5);
		ISubject[] subjects = model.getTestSubjects();
		for (ISubject subject : subjects) {
			Assert.assertEquals(5, subject.getAmountImages());
		}
		Assert.assertEquals(200, model.getTotalTrainingImages());
		Assert.assertEquals(200, model.getTotalTestImages());
	}

	@Test
	public void testMultipleRandomImagesOnExcistence() {
		for (int i = 0; i < 100; i++) {
			Assert.assertEquals(true, model.getRandomImage().exists());
		}
	}

	@Test
	public void testMaxImagePerSubject() {
		Assert.assertEquals(10, model.getMaxImagesPerSubject());
	}
}
