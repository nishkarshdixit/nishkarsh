package com.ibs.project.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;



@Repository("raiseRequestEntity")
public class HiringManagerDaoImpl implements HiringManagerInterfaceDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(RaiseRequestEntity raiseRequestEntity,int costCenterId) {
		
		Calendar now = Calendar.getInstance();					//Generating id for rquest raised using timestamp
		//	int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			int generatedId=Integer.parseInt(""+month+day+hour+minute+second);
			
			raiseRequestEntity.setRequestId(generatedId);
		
		int IdGenerated= (Integer) sessionFactory.getCurrentSession().save(raiseRequestEntity);		//Saving request
		if(IdGenerated!=0)
		{
		RequestStatusEntity newRequest=new RequestStatusEntity();
		raiseRequestEntity.setRequestId(IdGenerated);
		newRequest.setRequestId(raiseRequestEntity);
		
		newRequest.setReqCostCenterId(raiseRequestEntity.getCostCenterId());
		
			int generatedIdd=Integer.parseInt(""+month+day+hour+minute+second);
			
			newRequest.setRequestStatusId(generatedIdd);
		
		if(checkRm(costCenterId))								//Checking for corresponding RM
		{															
		sessionFactory.getCurrentSession().save(newRequest);			//Inserting tupple in the request status table
		return IdGenerated;
		}
		else
		{
			newRequest.setRmStatus("A");
			sessionFactory.getCurrentSession().save(newRequest);
			return IdGenerated;
		}
		}
		else {return IdGenerated;}
	}
	
	@Override
	public boolean checkRm(int costCenterId)						//Checking for rm for particular request
	{
		String sql = "select count(*) from G2COSTCENTER where COSTCENTERID= ? and REPORTINGMANAGERID is not null";

        Integer checkRm = jdbcTemplate.queryForObject(sql, Integer.class, costCenterId);

        if (checkRm > 0) {

               return true;
        }

        else {
               return false;
        }

		
		
	}

	@Override
	public List<RaiseRequestEntity> viewRequests(String employeeId) {				//Fetching data from db according to the employee id and conditions
		System.out.println("Hiring manager id received is "+employeeId);
		String sql = "with t as (select * from G2RAISEREQUEST natural join G2REQUESTSTATUS where EMPLOYEEID=? and REQUESTSTATUS is null) select * from t natural join G2EMPLOYEEDESCRIPTOR natural join G2COSTCENTER";
		
		List<RaiseRequestEntity> list = (List<RaiseRequestEntity>) jdbcTemplate.query(sql,new Object[]{employeeId},new RowMapper<RaiseRequestEntity>(){
			
			@Override
			public RaiseRequestEntity mapRow(ResultSet rs, int rowNum) {
				try
				{
					RaiseRequestEntity entity = new RaiseRequestEntity();
					
					entity.setRequestId(rs.getInt("REQUESTID"));
					
					CostCenterEntity cst = new CostCenterEntity();
					cst.setCostCenter(rs.getString("COSTCENTER"));
					cst.setCostCenterId(rs.getInt("COSTCENTERID"));
					entity.setCostCenterId(cst);
					
					entity.setProjectName(rs.getString("PROJECTNAME"));
					entity.setIssueDate(rs.getDate("DATEOFISSUE"));
					entity.setPrimarySkill(rs.getString("PRIMARYSKILL"));
					entity.setSecondarySkill(rs.getString("SECONDARYSKILL"));
					entity.setResourceCount(rs.getInt("RESOURCECOUNT"));
					entity.setLocation(rs.getString("LOCATION"));
					entity.setExperience(rs.getInt("EXPERIENCE"));
					entity.setDeadline(rs.getDate("DEADLINE"));
					
					
					return entity;
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
					
			
		}
		
	});
	
	System.out.println("List converted for hiring manager for viewing requests");
	if(list.isEmpty()){System.out.println("Empty list");}
	return list;
	
	}

	@Override
	public List<RequestStatusEntity> viewStatus(String employeeId) {				//Viewing status of requests raised by particular employee
		
		String sql = "select * from G2REQUESTSTATUS natural join G2RAISEREQUEST where requeststatus <> 'R' or requeststatus is null and employeeid=?";
		
		List<RequestStatusEntity> list = (List<RequestStatusEntity>) jdbcTemplate.query(sql, new Object[]{employeeId},new RowMapper<RequestStatusEntity>(){
			
			@Override
			public RequestStatusEntity mapRow(ResultSet rs,int rowNum)
			{
				try
				{
					RequestStatusEntity requestStatusEntity = new RequestStatusEntity();
					
					RaiseRequestEntity raise = new RaiseRequestEntity();
					raise.setRequestId(rs.getInt("REQUESTID"));
					requestStatusEntity.setRequestId(raise);
					System.out.println(rs.getInt("REQUESTID"));
					System.out.println(requestStatusEntity.getRequestId().getRequestId());
					
					requestStatusEntity.setRmStatus(rs.getString("REPORTINGMANAGERMSTATUS"));
					requestStatusEntity.setBfmStatus(rs.getString("BUSINESSFINANCESTATUS"));
					requestStatusEntity.setRequestStatus(rs.getString("REQUESTSTATUS"));
					
					return requestStatusEntity;
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
					
			
			}
		
	});
	
	System.out.println("List converted for hm for request status view");
	if(list.isEmpty()){System.out.println("Empty list");}
	return list;
	

	}
}
