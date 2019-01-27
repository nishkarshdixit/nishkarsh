package com.ibs.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ibs.project.entity.CostCenterEntity;
import com.ibs.project.entity.EmployeeEntity;


@Repository("employeeInterfaceDao")
public class EmployeeInterfaceImplDao implements EmployeeInterfaceDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String saveEmployee(EmployeeEntity employeeEntity) {			//Saving new Employee
		
		return (String) sessionFactory.getCurrentSession().save(employeeEntity);
	}

	
	
	@Override
	public EmployeeEntity authenticateEmployee(EmployeeEntity employee)
	{
		String sql = "select * from G2EMPLOYEEDESCRIPTOR where EMPLOYEEID = ? and EMPLOYEEPASSWORD = ?";		//Checking for id and password pair from db
		
		EmployeeEntity e=null;
		
		return e=jdbcTemplate.queryForObject(sql, new Object[]{employee.getEmployeeId(),employee.getPassword()}, new RowMapper<EmployeeEntity>(){
			
			@Override
			public EmployeeEntity mapRow(ResultSet rs,int rowNumber)
			{
				EmployeeEntity e=new EmployeeEntity();
				
				try {
					e.setEmail(rs.getString("EMPLOYEEEMAILID"));
					e.setAddress(rs.getString("EMPLOYEEADDRESS"));
					e.setDesignation(rs.getString("EMPLOYEEDESIGNATION"));
					e.setDob(rs.getDate("EMPLOYEEDATEOFBIRTH"));
					e.setDoj(rs.getDate("EMPLOYEEDATEOFJOINING"));
					e.setEmployeeName(rs.getString("EMPLOYEENAME"));
					e.setLocation(rs.getString("EMPLOYEELOCATION"));
					e.setPassword(rs.getString("EMPLOYEEPASSWORD"));
					e.setPhone(rs.getString("EMPLOYEEPHONENO"));
					e.setEmployeeId(rs.getString("EMPLOYEEID"));
					return e;
				} catch (SQLException e1) {
					
					e1.printStackTrace();
					
				}
				return e;
				
				
				
			}
			
		});
	}
	
	/*Cost Center Dao*/
	@Override
	public int saveCostCenter(CostCenterEntity costCenterEntity) {				//Saving the cost center in db
		
		Calendar now = Calendar.getInstance();								//Time stamp for generating unique ids
		//	int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			int generatedId=Integer.parseInt(""+month+day+hour+minute+second);
			
			costCenterEntity.setCostCenterId(generatedId);
		
		 int id=(Integer) sessionFactory.getCurrentSession().save(costCenterEntity);
		 return id;
	}
	
	public List<CostCenterEntity> costCenterDaolist(){
		
		System.out.println("Inside DAO");								//Fetching all the cost center list
		
		List<CostCenterEntity> cclist= jdbcTemplate.query("select * from G2costcenter", new RowMapper<CostCenterEntity>(){

			@Override
			public CostCenterEntity mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				
				CostCenterEntity cst=new CostCenterEntity();
				
				cst.setBfmId(rs.getString("BUSINESSFINANCEMANAGERID"));
				cst.setCostCenter(rs.getString("COSTCENTER"));
				cst.setCostCenterId(rs.getInt("costCenterId"));
				cst.setHmId(rs.getString("HIRINGMANAGERID"));
				cst.setProjectId(rs.getString("PROJECTID"));
				cst.setRmId(rs.getString("REPORTINGMANAGERID"));
				return cst;
			}
			
		});
		
		for(CostCenterEntity ccst:cclist)
		{
			System.out.println(ccst.getCostCenterId());
		}
		return cclist;
		}

	@Override
	public List<EmployeeEntity> viewEmployees() {						//Fetching all the employee from database
		
		String sql = "select EMPLOYEEID,EMPLOYEENAME,EMPLOYEEDATEOFBIRTH,EMPLOYEEADDRESS,EMPLOYEEPHONENO,EMPLOYEEEMAILID,EMPLOYEEPASSWORD,EMPLOYEELOCATION,EMPLOYEEDATEOFJOINING,EMPLOYEEDESIGNATION from G2EMPLOYEEDESCRIPTOR";
		
		List<EmployeeEntity> list = (List<EmployeeEntity>) jdbcTemplate.query(sql,new RowMapper<EmployeeEntity>(){
			
			@Override
			public EmployeeEntity mapRow(ResultSet rs, int rowNum)
			{
				
				try
				{
				EmployeeEntity emp = new EmployeeEntity();
				
				emp.setEmployeeId(rs.getString("EMPLOYEEID"));
				emp.setEmployeeName(rs.getString("EMPLOYEENAME"));
				emp.setDob(rs.getDate("EMPLOYEEDATEOFBIRTH"));
				emp.setAddress(rs.getString("EMPLOYEEADDRESS"));
				emp.setPhone(rs.getString("EMPLOYEEPHONENO"));
				emp.setEmail(rs.getString("EMPLOYEEEMAILID"));
				emp.setPassword(rs.getString("EMPLOYEEPASSWORD"));
				emp.setLocation(rs.getString("EMPLOYEELOCATION"));
				emp.setDoj(rs.getDate("EMPLOYEEDATEOFJOINING"));
				emp.setDesignation(rs.getString("EMPLOYEEDESIGNATION"));
				
				return emp;
				
			} 	catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
		}
	});
	if(list.isEmpty()){System.out.println("Nothing fetched");}
	return list;
	
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	

