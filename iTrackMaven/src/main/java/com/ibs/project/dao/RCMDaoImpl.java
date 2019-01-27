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
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RecruiterToRequest;
import com.ibs.project.model.RaiseRequestModel;


@Repository("rcmEntity")
public class RCMDaoImpl implements RCMDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public List<RaiseRequestEntity> getRequestsRCM() {						//Fetching the requests for RCM
		
		String sql = "with t as (select * from G2COSTCENTER natural join G2RAISEREQUEST natural join G2REQUESTSTATUS)select * from t natural join G2EMPLOYEEDESCRIPTOR where BUSINESSFINANCESTATUS is not null and REQUESTSTATUS is null and REQUESTID not in (select REQUESTID from g2recruitertorequest)";  
		
				List<RaiseRequestEntity> list=(List<RaiseRequestEntity>) jdbcTemplate.query(sql, new RowMapper<RaiseRequestEntity>(){

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
								
								EmployeeEntity ent=new EmployeeEntity();
								ent.setEmployeeName(rs.getString("EMPLOYEENAME"));
								requestEntity.setHiringManagerId(ent);
								
								CostCenterEntity cst=new CostCenterEntity();
								cst.setCostCenter(rs.getString("COSTCENTER"));
								requestEntity.setCostCenterId(cst);
								return requestEntity;
							} catch (SQLException e) {
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
	public List<EmployeeEntity> getRecruiters() {							//Fetch the list of recruiters in db
		
		String sql = "select EMPLOYEEID,EMPLOYEENAME from G2EMPLOYEEDESCRIPTOR where EMPLOYEEDESIGNATION = 'REC'";
		
		List<EmployeeEntity> recruiterList = (List<EmployeeEntity>) jdbcTemplate.query(sql,new RowMapper<EmployeeEntity>(){
			
			@Override
			public EmployeeEntity mapRow(ResultSet rs,int rowNum)
			{
			
			try {
				EmployeeEntity empEntity = new EmployeeEntity();
				
				empEntity.setEmployeeId(rs.getString("EMPLOYEEID"));
				empEntity.setEmployeeName(rs.getString("EMPLOYEENAME"));
				return empEntity;
			
			}
			catch (SQLException e) 
			{
				
				e.printStackTrace();
				return null;
			}
			}
			
		
	});
		System.out.println("List converted for recruiter");
		if(recruiterList.isEmpty())
		{
			System.out.println("Empty list");
		
		}
		return recruiterList;

}

	@Override
	public int saveRecruiter(RecruiterToRequest recruiterToRequestEntity) {			//Assigning recruiter for particular request
		
		Calendar now = Calendar.getInstance();									//Id generation using timestamp
		//	int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			int generatedId=Integer.parseInt(""+month+day+hour+minute+second);
			
			recruiterToRequestEntity.setRtrId(generatedId);
		
		return (Integer) sessionFactory.getCurrentSession().save(recruiterToRequestEntity);
		
	}
}
