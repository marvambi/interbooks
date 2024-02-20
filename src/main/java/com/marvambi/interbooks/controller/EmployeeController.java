package com.marvambi.interbooks.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvambi.interbooks.model.Employee;
import com.marvambi.interbooks.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllemployees(@RequestParam(required = false) String title) {
		try {
			List<Employee> employees = new ArrayList<Employee>();

			if (title == null)
                employeeRepository.findAll().forEach(employees::add);
			else
                employeeRepository.findByTitleContaining(title).forEach(employees::add);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
    		headers.add("SERVER ERROR", "INTERNAL SERVER ERROR");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
		Optional<Employee> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {
			return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

    @PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee _employee = employeeRepository
					.save(new Employee(employee.getTitle(), employee.getDescription(), false));
			return new ResponseEntity<>(_employee, HttpStatus.CREATED);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
    		headers.add("SERVER ERROR", "INTERNAL SERVER ERROR");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		Optional<Employee> employeeData = employeeRepository.findById(id);

		if (employeeData.isPresent()) {
			Employee _employee = employeeData.get();
			_employee.setTitle(employee.getTitle());
			_employee.setDescription(employee.getDescription());
			_employee.setPublished(employee.isPublished());
			return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
		try {
			employeeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteAllemployees() {
		try {
			employeeRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/employees/published")
	public ResponseEntity<List<Employee>> findByPublished() {
		try {
			List<Employee> employees = employeeRepository.findByPublished(true);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
