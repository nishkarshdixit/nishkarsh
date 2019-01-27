package com.ibs.project.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibs.project.model.CandidateRegistrationModel;
import com.ibs.project.model.EmployeeModel;
import com.ibs.project.service.CandidateRegistrationService;



@Controller("myController")
public class MainController {
	
	/*@Autowired
	WebApplicationContext context;*/

	@Autowired
	CandidateRegistrationService candidateRegistrationService;
	
	@Autowired
	CandidateRegistrationModel registrationModel;
	
	@RequestMapping(value="/",method = RequestMethod.GET)  			 //Loading the main index.jsp
    public String show(){
         
         return "index"; 										// should redirect to the index.jsp page
    }    
	
	@RequestMapping(value="candidateRegistration",method = RequestMethod.POST)      					//Receiving new candidate details and saving in db
    public @ResponseBody String candidateRegistration(@RequestBody String candidateData) {
         System.out.println(candidateData);
         ObjectMapper mapper=new ObjectMapper();
         
			CandidateRegistrationModel newCandidate;
			
			
				try {
					newCandidate = mapper.readValue(candidateData, CandidateRegistrationModel.class);
					String email=newCandidate.getEmail().toLowerCase();
					newCandidate.setEmail(email);
				} catch (JsonParseException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					return "";
				} catch (JsonMappingException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					return "";
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					return "";
				}
		
			
			        
			        
			//System.out.println(Id);
			try {
				System.out.println("Saving candidate");								//Saving new candidate details
				int Id=candidateRegistrationService.saveCandidate(newCandidate);
				CandidateRegistrationModel model=new CandidateRegistrationModel();
				model.setCandidateId(Id);
				return mapper.writeValueAsString(model);
			} catch(ConstraintViolationException e) {
			    //Email Address already exists
				e.printStackTrace();
				CandidateRegistrationModel model=new CandidateRegistrationModel();
				model.setCandidateId(0);
				try {
					return mapper.writeValueAsString(model);
				} catch ( IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch(JDBCConnectionException e) {
			    //Lost the connection
				return "";
			}
			catch(DataIntegrityViolationException e)
			{
				
				return "";									//Catching the exceptions to check the unique mail id
			}
			catch(Exception e)
			{
				return "";
			}
			return "";	
       
    }
	
	@RequestMapping(value="getSession",method=RequestMethod.POST)
	public @ResponseBody String getUser(HttpSession session)					//Fetching the current session
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			if(session.getAttribute("employee")==null){
				return "";
			}else{
				return mapper.writeValueAsString((EmployeeModel) session.getAttribute("employee"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
		
	}
	
	

}
