package com.ibs.project.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.CostCenterModel;
import com.ibs.project.model.EmployeeModel;
import com.ibs.project.service.EmployeeServiceInterface;

@Controller
public class EmployeeController {
	
	
	@Autowired
	EmployeeServiceInterface employeeDescriptorService;
	
	@Autowired
	EmployeeModel employeeModel;   
	
	@Autowired
	CostCenterModel costCenterModel;
	
	@RequestMapping(value="employeeDescriptor",method = RequestMethod.POST)			  							//Creating new employee details
	public @ResponseBody String EmployeeModel(@RequestBody String employeeData)
	{
		System.out.println(employeeData);
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			EmployeeModel newEmployee = mapper.readValue(employeeData,EmployeeModel.class);
			
			return mapper.writeValueAsString(employeeDescriptorService.saveEmployee(newEmployee));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		catch(DataIntegrityViolationException e)
		{
			return "";
		}
		
		
		
	}
	
	
	@RequestMapping(value="employeeLogin",method=RequestMethod.POST)
	public @ResponseBody String EmployeeLogin(@RequestBody String loginData,HttpSession session)
	{
		ObjectMapper mapper=new ObjectMapper();
		
		EmployeeModel loginEmployee;
		try {																											//Authenticating employee
			
			loginEmployee = mapper.readValue(loginData, EmployeeModel.class);
			EmployeeModel retEmployee=employeeDescriptorService.authenticateEmployee(loginEmployee);
			session.setAttribute("employee", retEmployee);
			return mapper.writeValueAsString(retEmployee);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		catch(EmptyResultDataAccessException e)										//Catching the exception for wrong id and password
		{
			EmployeeModel model=new EmployeeModel();
			model.setEmployeeId("0");
			try {
				return mapper.writeValueAsString(model);
			} catch (JsonGenerationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch(Exception e)
		{
			return "";
		}
		return "";
		
	}
	
	@RequestMapping(value="projectDescriptor",method=RequestMethod.POST)			
	public @ResponseBody void AddProject(@RequestBody String addProject)			//New Cost Center object received as String to be saved
	{
		System.out.println(addProject);
		ObjectMapper mapper=new ObjectMapper();
		try {
			CostCenterModel addData=mapper.readValue(addProject, CostCenterModel.class);
			employeeDescriptorService.saveCostCenter(addData);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@RequestMapping(value="getCostCenter",method=RequestMethod.POST)			
	public @ResponseBody String listCostCenter()							//Fetching cost center list for hiring manager
	{
		System.out.println("Inside Controller");
		List<CostCenterModel> list=employeeDescriptorService.cclist();
		for(CostCenterModel ccent:list)
		{
			System.out.println(ccent.getCostCenter());
			System.out.println(ccent.getCcId());
		}
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			return mapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value="employeeLogout",method=RequestMethod.POST)
	public @ResponseBody void EmployeeLogout(HttpSession session)			//Invalidating session in spring
	{
		session.invalidate();
	}
	
	
	@RequestMapping(value="viewEmployees",method=RequestMethod.POST)
	public @ResponseBody String ViewEmployees()								//Fetching list of employees
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(employeeDescriptorService.viewEmployees());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
