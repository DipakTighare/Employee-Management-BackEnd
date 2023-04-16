package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Employee;

public interface EmployeeService {

	 Employee addEmployee (Employee employee) ;
	 List<Employee> getAllEmployees ();
	 Optional<Employee> getEmployeeById(Long id) ;
	void deleteEmployeeById(Long id);
	
	void deleteAll(Employee employee);
	 
}
