package com.ibs.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;









import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;
import com.ibs.project.entity.RaiseRequestEntity;
import com.ibs.project.entity.RequestStatusEntity;
import com.ibs.project.model.EmployeeModel;
import com.ibs.project.model.RaiseRequestModel;



@Repository("requestStatusEntity")
public class RmBfmDaoImpl implements RmBfmInterfaceDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	RaiseRequestModel requestEntity;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RaiseRequestEntity> getPendingRequestRM(String Id)			//Pending requests of rm according to employee id
	{
		System.out.println("Pending request for Rm with Id "+ Id);
		String sql = "with t as (select * from G2COSTCENTER natural join G2RAISEREQUEST natural join G2REQUESTSTATUS)select REQUESTID,HIRINGMANAGERID,PROJECTNAME,DEADLINE,DATEOFISSUE,RESOURCECOUNT,JOBDESCRIPTION,G2EMPLOYEEDESCRIPTOR.EMPLOYEENAME,COSTCENTER from t inner join G2EMPLOYEEDESCRIPTOR on t.REPORTINGMANAGERID=G2EMPLOYEEDESCRIPTOR.EMPLOYEEID where REPORTINGMANAGERMSTATUS is null and REQUESTSTATUS is null and REPORTINGMANAGERID=?";
		
		
		
		List<RaiseRequestEntity> list=(List<RaiseRequestEntity>) jdbcTemplate.query(sql, new Object[]{Id},new RowMapper<RaiseRequestEntity>(){

			@Override
			public RaiseRequestEntity mapRow(ResultSet rs, int rowNum) {
					
					
					try {
						RaiseRequestEntity requestEntity=new RaiseRequestEntity();
						
						requestEntity.setDeadline(rs.getDate("DEADLINE"));
						requestEntity.setIssueDate(rs.getDate("DATEOFISSUE"));
						EmployeeEntity emp=new EmployeeEntity();
						emp.setEmployeeId(rs.getString("HIRINGMANAGERID"));
						emp.setEmployeeName(rs.getString("EMPLOYEENAME"));
						requestEntity.setHiringManagerId(emp);
						
						CostCenterEntity cst=new CostCenterEntity();
						cst.setCostCenter(rs.getString("COSTCENTER"));
						requestEntity.setCostCenterId(cst);
						
						requestEntity.setProjectName(rs.getNString("PROJECTNAME"));
						requestEntity.setResourceCount(rs.getInt("RESOURCECOUNT"));
						requestEntity.setRequestId(rs.getInt("REQUESTID"));
						requestEntity.setJobDescription(rs.getString("JOBDESCRIPTION"));
						
						return requestEntity;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
						
				
			}
			
		});
		
		System.out.println("List converted");
		if(list.isEmpty()){System.out.println("Empty list");}
		return list;
		
	}


	//@Override
	public List<RaiseRequestEntity> getPendingRequestBFM(String Id) {				//Get pending requests of BFM according to his employee id
		
		String sql = "with t as (select * from G2COSTCENTER natural join G2RAISEREQUEST natural join G2REQUESTSTATUS)select REQUESTID,HIRINGMANAGERID,PROJECTNAME,DEADLINE,DATEOFISSUE,RESOURCECOUNT,JOBDESCRIPTION,G2EMPLOYEEDESCRIPTOR.EMPLOYEENAME,COSTCENTER from t inner join G2EMPLOYEEDESCRIPTOR on t.BUSINESSFINANCEMANAGERID=G2EMPLOYEEDESCRIPTOR.EMPLOYEEID where BUSINESSFINANCESTATUS is null and REQUESTSTATUS is null and REPORTINGMANAGERMSTATUS='A' and BUSINESSFINANCEMANAGERID=?";
		
		
		
		List<RaiseRequestEntity> list=jdbcTemplate.query(sql, new Object[]{Id},new RowMapper<RaiseRequestEntity>(){

			@Override
			public RaiseRequestEntity mapRow(ResultSet rs, int rowNum) {
					
					
					try {
						RaiseRequestEntity requestEntity=new RaiseRequestEntity();
						requestEntity.setDeadline(rs.getDate("DEADLINE"));
						requestEntity.setIssueDate(rs.getDate("DATEOFISSUE"));
						
						
						EmployeeEntity emp=new EmployeeEntity();
						emp.setEmployeeId(rs.getString("HIRINGMANAGERID"));
						emp.setEmployeeName(rs.getString("EMPLOYEENAME"));
						requestEntity.setHiringManagerId(emp);
						
						
						CostCenterEntity cst=new CostCenterEntity();
						cst.setCostCenter(rs.getString("COSTCENTER"));
						requestEntity.setCostCenterId(cst);
						
						
						requestEntity.setProjectName(rs.getString("PROJECTNAME"));
						requestEntity.setResourceCount(rs.getInt("RESOURCECOUNT"));
						requestEntity.setRequestId(rs.getInt("REQUESTID"));
						requestEntity.setJobDescription(rs.getString("JOBDESCRIPTION"));
						return requestEntity;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
						
				
			}
			
		});
		
		System.out.println("List converted");
		if(list.isEmpty()){System.out.println("Empty list");}
		return list;

	}


	@Override
	public void saveRmStatus(RequestStatusEntity requestStatus,int requestId) {			//updating rm status
		
		int id=requestId;
		
		String status=requestStatus.getRmStatus();
		String remark=requestStatus.getRemark();
		System.out.println("Inside RM dao");
		
		if(remark!=null)						//Check consition for accept/reject
		{
		Query q=sessionFactory.getCurrentSession().createQuery("update RequestStatusEntity set REMARK=:remark ,REPORTINGMANAGERMSTATUS=:status ,REQUESTSTATUS='R' where REQUESTID=:id ");
		q.setParameter("remark", remark);
		q.setParameter("status", status);
		q.setParameter("id", id);
		q.executeUpdate();
		}
		else
		{
		Query q=sessionFactory.getCurrentSession().createQuery("update RequestStatusEntity set REMARK=:remark ,REPORTINGMANAGERMSTATUS=:status where REQUESTID=:id ");
		
		q.setParameter("remark", remark);
		q.setParameter("status", status);
		q.setParameter("id", id);
		q.executeUpdate();
		}
		
		
		
	}


	@Override
	public void saveBfmStatus(RequestStatusEntity requestStatus,int requestId) {		//Updating the status of bfm
		
		int id=requestId;
		String status=requestStatus.getBfmStatus();
		String remark=requestStatus.getRemark();
		System.out.println("Inside BFM dao");
		System.out.println(status);
		System.out.println(id);
		System.out.println(remark);
		if(remark!=null)									//Checking for accept/reject
		{
		Query q=sessionFactory.getCurrentSession().createQuery("update RequestStatusEntity set REMARK=:remark ,BUSINESSFINANCESTATUS=:status ,REQUESTSTATUS='R' where REQUESTID=:id ");
		q.setParameter("remark", remark);
		q.setParameter("status", status);
		q.setParameter("id", id);
		q.executeUpdate();
		}
		else
		{
			Query q=sessionFactory.getCurrentSession().createQuery("update RequestStatusEntity set REMARK=:remark ,BUSINESSFINANCESTATUS=:status where REQUESTID=:id ");
			q.setParameter("remark", remark);
			q.setParameter("status", status);
			q.setParameter("id", id);
			q.executeUpdate();
		}
	}
}
		



