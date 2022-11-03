package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentService {

	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addStudent(Student student){
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()){
			throw new IllegalStateException("Student already exists");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId){
		boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("Student with ID : " + studentId + " does not exists");
		}
		studentRepository.deleteById(studentId);
	}
	@Transactional
	public void updateStudent(Long studentId, String name, String email){
		Student student = studentRepository.findById(studentId)
		.orElseThrow(() -> new IllegalStateException("Student with ID : " + studentId + " does not exists"));

		if(!(name==null) &&
		name.length() > 0 &&
		!Objects.equals(name, student.getName())){
		student.setName(name);
		}
		if(!(email==null) &&
		email.length() > 0 &&
		!Objects.equals(email, student.getEmail())){
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
		if(studentOptional.isPresent()){
			throw new IllegalStateException("Email is already taken");
		}
		student.setEmail(email);
		}

	}
}
