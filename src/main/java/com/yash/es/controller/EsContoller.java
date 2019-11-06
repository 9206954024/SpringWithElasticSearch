package com.yash.es.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.es.model.Employee;
import com.yash.es.service.EmpService;

@RestController
@RequestMapping("/employees")
public class EsContoller {

	@Autowired
	EmpService empService;

	@PostMapping("/create")
	public Map<String, Object> createEmployee(@RequestBody Employee employee) throws Exception {
			Map<String, Object> response = empService.insertEmployeeRecord(employee);
			// bookDao.insertBook(book);
		    return response;
			
	}

	@GetMapping("/retrive/{empId}")
	public Map<String, Object> retriveEmployee(@PathVariable String empId) {
		boolean flag=true;
		Map<String, Object> response =empService.fetchEmployeeRecord(empId,flag);
		// bookDao.insertBook(book);
		return response;
	}

	@GetMapping("/retriveQuery/{empId}")
	public Map<String, Object> retriveEmployeeQuery(@PathVariable String empId) {
        boolean flag=false;
		Map<String, Object> response =empService.fetchEmployeeRecord(empId,flag);
		return response;
	}
	@GetMapping("/requestSearch")
	public Map<String, Object> requestSearch(@RequestParam("EmpId") String empId){
		 boolean flag=false;
		Map<String, Object> response =empService.fetchEmployeeRecord(empId,flag);
		return response;
	}
	@PostMapping("/multiSearch")
	public List<Map<String, Object>> multiSearch(@RequestBody Employee employee){
		 Map<String,String> headerMap=new HashMap<>();
		 if(!Objects.isNull(employee.getEmpId()))
			 headerMap.put("EmpId", employee.getEmpId());
		/* if(!(employee.getEmpId()==null))
		 headerMap.put("EmpId", employee.getEmpId());*/
		 
		 if(!Objects.isNull(employee.getEmpName()))
			 headerMap.put("EmpName", employee.getEmpName());
		/* if(!(employee.getEmpName()==null))
		 headerMap.put("EmpName", employee.getEmpName());*/
		 
		 if(!Objects.isNull(employee.getCompanyName()))
		    headerMap.put("CompanyName", employee.getCompanyName());
		
		 /*if(!(employee.getCompanyName()==null))
	     headerMap.put("CompanyName", employee.getCompanyName());*/
		
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getCountry()))
		    headerMap.put("Address.Country", employee.getAddress().getCountry());
		/* if(!(employee.getAddress()==null) && !(employee.getAddress().getCountry()==null))
		 headerMap.put("Address.Country", employee.getAddress().getCountry());*/
		
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getState()))
			headerMap.put("Address.State", employee.getAddress().getState());
		/* if(!(employee.getAddress()==null) && !(employee.getAddress().getState()==null))
		 headerMap.put("Address.State", employee.getAddress().getState());*/
		
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getCityName()))
			 headerMap.put("Address.CityName", employee.getAddress().getCityName());
		/*if(!(employee.getAddress()==null) &&!(employee.getAddress().getCityName()==null))
			 headerMap.put("Address.CityName", employee.getAddress().getCityName());*/
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getPin())) 
			 headerMap.put("Address.Pin", employee.getAddress().getPin());
		 /*if(!(employee.getAddress()==null) &&!(employee.getAddress().getPin()==null))
		 headerMap.put("Address.Pin", employee.getAddress().getPin());*/
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getMobileNo()))
			 headerMap.put("Address.MobileNo", employee.getAddress().getMobileNo());
		/* if(!(employee.getAddress()==null) &&!(employee.getAddress().getMobileNo()==null))
		 headerMap.put("Address.MobileNo", employee.getAddress().getMobileNo());*/
		// System.out.println(headerMap);
		List<Map<String, Object>> response= empService.fetchQueryBuilder(headerMap);
	
		return response;
	}
	
	@PutMapping("/update/{empId}")
	public Map<String, Object> updateEmployee(@RequestBody Employee employee, @PathVariable String empId){

		Map<String, Object> response=empService.updateEmployeeRecord(employee, empId);
		
		return response;

	}
	
	@PostMapping("/partialUpdate")
	public Map<String, Object> partialupdateEmployee(@RequestBody Employee employee){
		 Map<String,String> headerMap=new HashMap<>();
		 String EmpID=employee.getEmpId();
		 if(!Objects.isNull(employee.getEmpId()))
			 headerMap.put("EmpId", employee.getEmpId());
		 if(!Objects.isNull(employee.getEmpName()))
			 headerMap.put("EmpName", employee.getEmpName());
		 if(!Objects.isNull(employee.getCompanyName()))
			    headerMap.put("CompanyName", employee.getCompanyName());
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getCountry()))
			    headerMap.put("Address.Country", employee.getAddress().getCountry());
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getState()))
				headerMap.put("Address.State", employee.getAddress().getState());
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getCityName()))
			 headerMap.put("Address.CityName", employee.getAddress().getCityName());
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getPin())) 
			 headerMap.put("Address.Pin", employee.getAddress().getPin());
		 if(!Objects.isNull(employee.getAddress()) && !Objects.isNull(employee.getAddress().getMobileNo()))
			 headerMap.put("Address.MobileNo", employee.getAddress().getMobileNo());

		 Map<String, Object> response=empService.partialUpdateEmployeeRecord(headerMap, EmpID);
		
		return response;

	}

	@DeleteMapping("/delete/{empId}")
	public String deleteEmployee(@PathVariable String empId) throws Exception {

		String response = empService.deleteEmployeeRecord(empId);
		// bookDao.insertBook(book);
		return response;
	}
}
