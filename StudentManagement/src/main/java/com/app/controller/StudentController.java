package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Student;
import com.app.repository.StudentRepository;


@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepository repo;
	//get all the students 
	//localhost:8080/student/students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		 List<Student> students = repo.findAll();
		  return students;
	}
	
	//localhost:8080/student/student/1
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		
		return student;
		
	}
	//localhost:8080/student/add
	@PostMapping("/add")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = repo.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
		
	}
	//localhost:8080/student/update/1
	@PutMapping("/update/{id}")
	public Student updateStudents(@PathVariable int id,@RequestBody Student studentDetails) {
	   Student student = repo.findById(id).get();
	   student.setName(studentDetails.getName());
	   student.setPercentage(studentDetails.getPercentage());
	   student.setBranch(studentDetails.getBranch());
	   Student updateStu = repo.save(student);
	   return updateStu;
		
	}
	//localhost:8080/student/delete/1
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeStudent(@PathVariable int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}

}
