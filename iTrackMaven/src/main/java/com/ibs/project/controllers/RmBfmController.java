package com.ibs.project.controllers;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;
import com.ibs.project.service.RmBfmServiceInterface;

@Controller("rmBfmController")
public class RmBfmController {
	
	@Autowired
	RmBfmServiceInterface rmBfmService;
	
	@RequestMapping(value="getAllRequest",method=RequestMethod.POST)			
	public @ResponseBody String getAllRequest(@RequestBody String employeeData)
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			RequestStatusModel received=mapper.readValue(employeeData, RequestStatusModel.class);
			if(received.getDesignation().equals("RM"))											//Resolving designation and fetching the requests
			{
				System.out.println("RmId received in controller is "+received.getId());
				List<RaiseRequestModel> list=rmBfmService.retRequestListRm(received.getId());
				/*Gson gson=new Gson();
				String jsonString=gson.toJson(list);
				return jsonString;*/
				return mapper.writeValueAsString(list);
			}
			else
			{
				List<RaiseRequestModel> list=rmBfmService.retRequestListBfm(received.getId());
			/*	Gson gson=new Gson();
				String jsonString=gson.toJson(list);
				return jsonString;*/
				return mapper.writeValueAsString(list);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping(value="saveRequest",method=RequestMethod.POST)				//Saving particular request
	public @ResponseBody void setStatus(@RequestBody String statusData)
	{
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			RequestStatusModel saved=mapper.readValue(statusData, RequestStatusModel.class);
			if(saved.getDesignation().equals("RM"))
			{
				System.out.println("Inside java controller for RM");
				System.out.println("Id got is " + saved.getId());
				System.out.println(("Request Id is " + saved.getRequestId()));
				System.out.println(("Status is "+ saved.getStatus()));
				rmBfmService.rmStatus(saved);
			}
			else
			{
				System.out.println("Inside java controller for BFM");
				System.out.println("Id got is " + saved.getId());
				System.out.println(("Request Id is " + saved.getRequestId()));
				System.out.println(("Status is "+ saved.getStatus()));
				rmBfmService.bfmStatus(saved);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
