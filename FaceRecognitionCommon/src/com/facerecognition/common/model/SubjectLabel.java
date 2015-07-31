package com.facerecognition.common.model;

import com.facerecognition.common.model.interfaces.ISubjectLabel;

public class SubjectLabel implements ISubjectLabel {
	private int id;
	private String name;

	public SubjectLabel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public SubjectLabel() {
	}

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubjectLabel) {
			if (((ISubjectLabel) obj).getId() == getId()
					&& ((ISubjectLabel) obj).getName().equals(getName())) {
				return true;
			}
			return false;
		}
		return super.equals(obj);
	}
}
