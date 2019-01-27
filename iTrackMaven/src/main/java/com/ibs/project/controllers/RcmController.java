package com.ibs.project.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.EmployeeModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RecruiterToRequestModel;
import com.ibs.project.service.RCMServiceInterface;

@Controller("rcmController")
public class RcmController {
	
	@Autowired
	RCMServiceInterface rcmService;
	
	@Autowired
	RecruiterToRequestModel model;

	@RequestMapping(value="getRcmRequest",method=RequestMethod.POST)		//Fetching requests for RCM accordingly
	public @ResponseBody String getRcmRequest()
	{
		
		
		List<RaiseRequestModel> list= rcmService.retRequestList();
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
	
	@RequestMapping(value="getRecruiter",method=RequestMethod.POST)			//Fetching list of recruiter whom RCM can assign the requests
	public @ResponseBody String getRecruiter()
	{
		ObjectMapper mapper=new ObjectMapper();
		List<EmployeeModel> list=rcmService.retRecruiterList();
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
	
	@RequestMapping(value="saveRecruiter",method=RequestMethod.POST)			//Assigning to the recruiter
	public @ResponseBody String saveRecruiter(@RequestBody String recDetails)
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			RecruiterToRequestModel model=mapper.readValue(recDetails, RecruiterToRequestModel.class);
			int id= rcmService.saveRecruiter(model);
			model.setIrequestId(id);
			
			return mapper.writeValueAsString(model);
			
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
		
	}
}
