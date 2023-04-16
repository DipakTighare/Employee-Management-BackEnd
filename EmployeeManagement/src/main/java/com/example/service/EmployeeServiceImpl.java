package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.EmployeeNotFoundException;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee addEmployee(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		Optional<Employee> employee = repository.findById(id);
			
			return employee;
	}

	@Override
	public void deleteEmployeeById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll(Employee employee) {
		repository.deleteAll();
	}

}
