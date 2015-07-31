package com.facerecognition.common.data.interfaces;

import java.util.List;

import com.facerecognition.common.model.SubjectLabel;
import com.facerecognition.common.model.interfaces.ISubjectLabel;

public interface ISubjectLabelManager {

	public void loadNewSource(List<SubjectLabel> subjectLabelList);
	
	public void saveLabel(ISubjectLabel label);

	public ISubjectLabel getSubject(int label);

	public void emptySource();

	public List<SubjectLabel> getLabels();

	public boolean isSourceEmpty();
	
	public int[] getLabelsForSubject(String name);
}
