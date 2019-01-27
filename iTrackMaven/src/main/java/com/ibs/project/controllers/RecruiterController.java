package com.ibs.project.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.CandidateRegistrationModel;
import com.ibs.project.model.RaiseRequestModel;
import com.ibs.project.model.RequestStatusModel;
import com.ibs.project.model.ShortlistCandidateModel;
import com.ibs.project.service.CandidateRegistrationService;
import com.ibs.project.service.RecruiterServiceInterface;

@Controller
public class RecruiterController {

	@Autowired
	CandidateRegistrationService candService;
	
	@Autowired
	RecruiterServiceInterface recService;
	
	@RequestMapping(value="getCandidates",method=RequestMethod.POST)						//Fetching all the available candidates registered
	public @ResponseBody String listCandidates()
	{
		List<CandidateRegistrationModel> list= candService.candidateList();
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
	
	
	@RequestMapping(value="getRecruiterRequests",method=RequestMethod.POST)
	public @ResponseBody String getRecruiterRequests(@RequestBody String idReceived)			//Fetching the list of requests appointed to particular recruiter
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			RequestStatusModel model=mapper.readValue(idReceived, RequestStatusModel.class);
			String id=model.getId();
			System.out.println("Recruiter id received in recruiter controller "+id);
			List<RaiseRequestModel> modelList=recService.getRequests(id);
			if(modelList.isEmpty()){System.out.println("Empty list converted in recruiter controller");}
			return mapper.writeValueAsString(modelList);
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
		
		return null;
		
	}
	
	@RequestMapping(value="shortlistCandidates",method=RequestMethod.POST)  		//Getting list of candidate id and request id shortlisted
	public @ResponseBody String shortListCandidates(@RequestBody String idReceived)
	{
		ObjectMapper mapper=new ObjectMapper();
		System.out.println(idReceived);
		try {
			List<ShortlistCandidateModel> modelList=mapper.readValue(idReceived, TypeFactory.collectionType(List.class, ShortlistCandidateModel.class));
			for(ShortlistCandidateModel s:modelList)
			{
				System.out.println("Shortlist candidate received from checkbox objects with id "+s.getCandidateId() +"Request id is "+s.getRequestId());
				Date date=new Date();
				s.setDate(date);
			}
			
			return mapper.writeValueAsString(recService.saveShortlistCandidates(modelList));
			
			
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
		return null;
		
	}
	
	
	@RequestMapping(value="shortlistedCandidates",method=RequestMethod.POST)		//Fetching the list of shortlisted candidates
	public @ResponseBody String getShortlisted(@RequestBody String id)
	{
		
		ObjectMapper mapper=new ObjectMapper();
		try {
			
			List<ShortlistCandidateModel> modelList=mapper.readValue(id, TypeFactory.collectionType(List.class, ShortlistCandidateModel.class));
			
			int requestId=modelList.get(0).getRequestId();
			System.out.println("Inside recruiter controller to get shortlisted candidate for id "+ requestId);
			return mapper.writeValueAsString(recService.getCandidateList(requestId));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value="updateCandidates",method=RequestMethod.POST)			//Updating status of selected candidates
	public @ResponseBody void updateStatus(@RequestBody String updData)
	{
		ObjectMapper mapper=new ObjectMapper();
		ShortlistCandidateModel model;
		try {
			model = mapper.readValue(updData, ShortlistCandidateModel.class);
			int candidateId=model.getCandidateId();
			int status=model.getStatus();
			String remark=model.getRemark();
			System.out.println("Updating the candidate with id "+ candidateId + "with status "+ status+"and remarks as "+remark);
			recService.saveUpdate(status, candidateId,remark);
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
		
		
	}
	
	@RequestMapping(value="selectedCandidates",method=RequestMethod.POST)			//Fetching list of selected candidates
	public @ResponseBody String updateStatus()
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			return mapper.writeValueAsString(recService.viewSelectedCandidates());
		} catch (JsonGenerationException e) {
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
	
	
	@RequestMapping(value="resolveRequest",method=RequestMethod.POST)				//Resolving selected request
	public @ResponseBody void resolveRequest(@RequestBody String resolveData)
	{
		ObjectMapper mapper=new ObjectMapper();
		RequestStatusModel model;
		try
		{
			model = mapper.readValue(resolveData,RequestStatusModel.class);
			int requestId = model.getRequestId();
			recService.resolveRequest(requestId);
		}catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
