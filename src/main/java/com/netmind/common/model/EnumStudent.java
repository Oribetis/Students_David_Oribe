package com.netmind.common.model;

public enum EnumStudent {
	ADD_STUDENT(1), SELECT_STUDENT(2), DELETE_STUDENT(3), UPDATE_STUDENT(4), EXIT(5);

	private int value;

	private EnumStudent(int value) {
		this.value = value;
	}

	public static EnumStudent fromValue(int value) {
		for (EnumStudent my : EnumStudent.values()) {
			if (my.value == value) {
				return my;
			}
		}

		return null;
	}

	public int value() {
		return value;
	}

}
