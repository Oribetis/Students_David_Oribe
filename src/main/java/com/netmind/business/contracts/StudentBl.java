package com.netmind.business.contracts;

import java.io.IOException;
import java.util.List;

import com.netmind.common.model.Student;

public interface StudentBl {
	public boolean addToJsonFile(Student student) throws IOException;

	public boolean add(Student student) throws IOException;

	public List<Student> getAllFromJson();
	
	public boolean removeFromJsonFile(Integer id) throws IOException;
	
	public boolean updateToJsonFile(Student student) throws IOException;
}
