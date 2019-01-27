package com.ibs.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ibs.project.entity.CandidateRegistrationEntity;
import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.ShortlistCandidateEntity;

@Repository("recruiterEntity")
public class RecruiterDaoImpl implements RecruiterDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public List<RaiseRequestEntity> getRequests(String id) {				//Fetching the particular requests for Recruiter
		
		String sql = "with t as(select * from G2COSTCENTER natural join G2RAISEREQUEST natural join G2REQUESTSTATUS)select * from t natural join g2employeedescriptor where requestid in (select REQUESTID from G2RECRUITERTOREQUEST where EMPLOYEEID=?) and REQUESTSTATUS is null";
		
		List<RaiseRequestEntity> list = (List<RaiseRequestEntity>) jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<RaiseRequestEntity>(){
			
			@Override
			public RaiseRequestEntity mapRow(ResultSet rs, int rowNum) {
					
					
					try {
						RaiseRequestEntity requestEntity=new RaiseRequestEntity();
						requestEntity.setDeadline(rs.getDate("DEADLINE"));
						requestEntity.setIssueDate(rs.getDate("DATEOFISSUE"));
						requestEntity.setPrimarySkill(rs.getString("PRIMARYSKILL"));
						requestEntity.setSecondarySkill(rs.getString("SECONDARYSKILL"));
						requestEntity.setProjectName(rs.getNString("PROJECTNAME"));
						requestEntity.setResourceCount(rs.getInt("RESOURCECOUNT"));
						requestEntity.setRequestId(rs.getInt("REQUESTID"));
						requestEntity.setJobDescription(rs.getString("JOBDESCRIPTION"));
						
						EmployeeEntity ent = new EmployeeEntity();
						ent.setEmployeeName(rs.getString("EMPLOYEENAME"));
						requestEntity.setHiringManagerId(ent);
						
						CostCenterEntity cst = new CostCenterEntity();
						cst.setCostCenter(rs.getString("COSTCENTER"));
						requestEntity.setCostCenterId(cst);
						
						return requestEntity;
						
			
			
			
			
		}
		
		
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
						
				
			}
			
		});
		
		System.out.println("List converted for rcm");
		if(list.isEmpty()){System.out.println("Empty list");}
		return list;
		
	

	}



	@Override
	public List<String> saveShortlistCandidates(List<ShortlistCandidateEntity> entList) {		//Saving multiple candidates for 1 request id
		
		List<String> intList=new ArrayList<String>();
		
		for(ShortlistCandidateEntity ent:entList)
		{
			Calendar now = Calendar.getInstance();						//Generating id as string with millis to stop conflict
			//	int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
				int day = now.get(Calendar.DAY_OF_MONTH);
				int hour = now.get(Calendar.HOUR_OF_DAY);
				int minute = now.get(Calendar.MINUTE);
				int second = now.get(Calendar.SECOND);
				int milli=now.get(Calendar.MILLISECOND);
				String generatedId=(""+month+day+hour+minute+second+milli);
				ent.setShortlistId(generatedId);
			intList.add((String) sessionFactory.getCurrentSession().save(ent));
			CandidateRegistrationEntity cnd=ent.getCandidateId();
			int cid=cnd.getCandidateId();
			System.out.println("In Recruiter Dao to update status of candidate " + cid);
			
			if(cid!=0)
			{
				Query q=sessionFactory.getCurrentSession().createQuery("update CandidateRegistrationEntity set CANDIDATESTATUS=:status where CANDIDATEID =:cid ");
				q.setParameter("cid",cid);	
		        q.setParameter("status",1);
				q.executeUpdate();
			}
			
		}
		
		return intList;
	}



	@Override
	public List<CandidateRegistrationEntity> getShortlistCandidates(int requestId) {				//Fetching available candidates from table
		
		System.out.println("Id received for shortlisted candidates in dao is "+ requestId);
		String sql = "with t as(select * from G2CANDIDATEREGISTRATION natural join G2SHORTLISTCANDIDATE)select CANDIDATEID,CANDIDATEFIRSTNAME,STATUS from t where REMARK is null and STATUS <> 3 and REQUESTID =?";
		
		List<CandidateRegistrationEntity> list = (List<CandidateRegistrationEntity>) jdbcTemplate.query(sql, new Object[]{requestId}, new RowMapper<CandidateRegistrationEntity>(){
			
			@Override
			public CandidateRegistrationEntity mapRow(ResultSet rs, int rowNum) {
					
				try
				{
					CandidateRegistrationEntity entity=new CandidateRegistrationEntity();
					entity.setCandidateId(rs.getInt("CANDIDATEID"));
					entity.setFirstName(rs.getString("CANDIDATEFIRSTNAME"));
					entity.setStatus(rs.getInt("STATUS"));
					return entity;
					
					
				}

				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
					
			
		}
		
	});
	
	System.out.println("List converted for Recruiter shortlisted candidates for particular Request");
	if(list.isEmpty()){System.out.println("Empty list");}
	return list;
	
			
			
		}



	@Override
	public void saveUpdate(int status, int candidateId,String remark) {
		
		if(remark==null)
		{
			System.out.println("Status inside dao for update is "+status+"cid is "+ candidateId+"remark is "+remark);
		Query q = sessionFactory.getCurrentSession().createQuery("update ShortlistCandidateEntity set STATUS=:sta where CANDIDATEID=:id");
		q.setParameter("sta", status);
		q.setParameter("id",candidateId);
		q.executeUpdate();
		if(status==3)
		{
			Query qq1=sessionFactory.getCurrentSession().createQuery("update CandidateRegistrationEntity set candidatestatus=:st where CANDIDATEID=:id");
			qq1.setParameter("st",2);
			qq1.setParameter("id", candidateId);
			qq1.executeUpdate();
			Query qq2=sessionFactory.getCurrentSession().createQuery("update ShortlistCandidateEntity set OFFER=:off where CANDIDATEID=:id");
			qq2.setParameter("off", "G");
			qq2.setParameter("id", candidateId);
			qq2.executeUpdate();
		}
		}
		else
		{

			System.out.println("Status inside dao for rejection is "+status+"cid is "+ candidateId+"remark is "+remark);
			Query q = sessionFactory.getCurrentSession().createQuery("update ShortlistCandidateEntity set STATUS =:stat,REMARK=:rem,shortlistdate=:date where CANDIDATEID=:id and remark is null");
			Date date=new Date();
			q.setParameter("date", date);
			q.setParameter("rem",remark);
			q.setParameter("id",candidateId);
			q.setParameter("stat", -1);
			q.executeUpdate();
		}
	}	
	



	@Override
	public List<CandidateRegistrationEntity> viewSelectedCandidates() {						//fetching selected candidates from db 
		
		String sql = "select * from G2CANDIDATEREGISTRATION where candidatestatus =2";
		List<CandidateRegistrationEntity> list = (List<CandidateRegistrationEntity>) jdbcTemplate.query(sql,new RowMapper<CandidateRegistrationEntity>(){
			
			@Override
			public CandidateRegistrationEntity mapRow(ResultSet rs, int rowNum) {
				try
				{
					CandidateRegistrationEntity entity = new CandidateRegistrationEntity();
					
					entity.setCandidateId(rs.getInt("CANDIDATEID"));
					entity.setTitle(rs.getString("CANDIDATETITLE"));
					entity.setFirstName(rs.getString("CANDIDATEFIRSTNAME"));
					entity.setMiddleName(rs.getString("CANDIDATEMIDDLENAME"));
					entity.setLastName(rs.getString("CANDIDATELASTNAME"));
					entity.setDob(rs.getDate("CANDIDATEDOB"));
					entity.setPhoneNumber(rs.getString("CANDIDATEPHONENUMBER"));
					entity.setCurrentLocation(rs.getString("CANDIDATECURRENTLOCATION"));
					entity.setPreferredLocation(rs.getString("CANDIDATEPREFERREDLOCATION"));
					entity.setEmail(rs.getString("CANDIDATEEMAIL"));
					entity.setTotalExperience(rs.getInt("CANDIDATETOTALEXPERIENCE"));
					entity.setRelevantExperience(rs.getInt("CANDIDATERELEVANTEXPERIENCE")); 
					entity.setCv(rs.getString("CANDIDATECV"));
					
					return entity;
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
					
			
		}
		
	});
	
	System.out.println("List converted for Recruiter shortlisted candidates for particular Request");
	if(list.isEmpty()){System.out.println("Empty list");}
	return list;
	
			
			
	}



	@Override
	public void resolveRequest(int requestId) {				//Resolving the request finally
		
		Query q = sessionFactory.getCurrentSession().createQuery("update RequestStatusEntity set REQUESTSTATUS =:requestStatus where requestid=:requestId");
		q.setParameter("requestStatus","P");
		q.setParameter("requestId",requestId);
		q.executeUpdate();
		
		
		
		
	}
		

}
