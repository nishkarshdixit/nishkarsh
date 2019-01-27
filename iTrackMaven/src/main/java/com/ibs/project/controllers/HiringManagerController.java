package com.ibs.project.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;
import com.ibs.project.service.HiringManagerServiceInterface;

@Controller("requestRaiseController")
public class HiringManagerController {

	@Autowired
	HiringManagerServiceInterface hiringManagerServiceInterface;
	
	@Autowired
	RaiseRequestModel raiseRequestModel;
	
	@RequestMapping(value = "raiseRequest", method=RequestMethod.POST)
    public @ResponseBody String RaiseRequestModel(@RequestBody String requestData)									//Raising new Request

	{
		System.out.println(requestData);
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			
		RaiseRequestModel newRequest = mapper.readValue(requestData,RaiseRequestModel.class);
		Date date = new Date();
		newRequest.setIssueDate(date);
		System.out.println(newRequest.getIssueDate());
		int id= hiringManagerServiceInterface.saveRequest(newRequest);
		
		RaiseRequestModel model=new RaiseRequestModel();
		model.setCostCenter(id);
		return mapper.writeValueAsString(model);
		
		
		
	} catch (IOException e) {					//Catching the exception to return the success of request
		//e.printStackTrace();
		return "";
	}
	catch(ConstraintViolationException e)
		{
			return "";
		}
		catch(EmptyResultDataAccessException e)
		{
			return "";
		}
		
	
	}
	
	@RequestMapping(value="viewRequests",method=RequestMethod.POST)   					//Fetching requests for HM as per his employee id
	public @ResponseBody String viewRequests(@RequestBody String id)
	{
		System.out.println("inside the hiring manager controller");
		System.out.println(id);
		ObjectMapper mapper=new ObjectMapper();
		try {
			RaiseRequestModel model = mapper.readValue(id,RaiseRequestModel.class);
			
			 return mapper.writeValueAsString(hiringManagerServiceInterface.viewRequests(model.getHmId()));
			
			
			
	}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	
	
	
	}	
	
	@RequestMapping(value="viewStatus",method=RequestMethod.POST)						//View Status of HM as per all his requests
	public @ResponseBody String viewStatus(@RequestBody String id)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			raiseRequestModel = mapper.readValue(id,RaiseRequestModel.class);
			
			return mapper.writeValueAsString(hiringManagerServiceInterface.viewStatus(raiseRequestModel.getHmId()));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
}
