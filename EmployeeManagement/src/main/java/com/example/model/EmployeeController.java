package com.example.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.EmployeeNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api")
public class EmployeeController {
	 
	@Autowired
	 private EmployeeService service;
	@Autowired
	 private EmployeeRepository repository;
	
	
	@GetMapping("/users")
	 public List<Employee> getAllEmployee(){
		return service.getAllEmployees(); 
	 }
	
	@PostMapping("/user")
	public Employee addEmployee (@Valid @RequestBody Employee employee) {
		
		return service.addEmployee(employee);
	}
	
	
	@PutMapping("/{id}")
	public Employee updatEmployee (@RequestBody Employee NewEmployee,
			@PathVariable Long id ) {
		 return service.getEmployeeById(id)
		.map(employee ->{
			
		employee.setEmployeeName(NewEmployee.getEmployeeName()  );
		employee.setMobileNo(NewEmployee.getMobileNo());
		employee.setEmployeeSalary(NewEmployee.getEmployeeSalary());
		
		return service.addEmployee(employee);
		}).orElseThrow(()-> new EmployeeNotFoundException(id));

		
	}
	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById (@PathVariable Long id)
	{
		return Optional.ofNullable(service.getEmployeeById(id)
				.orElseThrow(()-> new EmployeeNotFoundException(id)));
				
	}
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable Long id) {	
		if (!repository.existsById(id)) {
			throw new EmployeeNotFoundException(id);	
		}else {
		service.deleteEmployeeById(id);}
	}
	
	@DeleteMapping("/delete")
	ResponseEntity<String> deleteAll(Employee employee) {
		
		service.deleteAll(employee);
		return ResponseEntity.ok("All Employees Deleted");
	}
		
	
}
