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

import com.ibs.project.entity.CandidateRegistrationEntity;

@Repository("candidateRegistrationEntity")
public class CandidateRegistrationDaoImpl implements CandidateRegistrationDao {		
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(CandidateRegistrationEntity candidateRegistrationEntity) {
		
		Calendar now = Calendar.getInstance();						//Generating id using timestamp
		//	int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			int second = now.get(Calendar.SECOND);
			int generatedId=Integer.parseInt(""+month+day+hour+minute+second);
			
			candidateRegistrationEntity.setCandidateId(generatedId);
		
		  int id=(Integer) sessionFactory.getCurrentSession().save(candidateRegistrationEntity);  //Sending to database
		return id;
		
	}

	@Override
	public List<CandidateRegistrationEntity> candDaolist() {
		
		
		
		List<CandidateRegistrationEntity> list= jdbcTemplate.query("select * from G2CandidateRegistration where CANDIDATESTATUS=0", new RowMapper<CandidateRegistrationEntity>(){		
			@Override
			public CandidateRegistrationEntity mapRow(ResultSet rs,int rowNumber)				//Making list of objects returned
			{
				
				try {
					CandidateRegistrationEntity cand=new CandidateRegistrationEntity();
					cand.setCandidateId(rs.getInt("CANDIDATEID"));
					cand.setCurrentLocation(rs.getString("CANDIDATECURRENTLOCATION"));
					//cand.setCv(rs.getString("CANDIDATECV"));
					cand.setDob(rs.getDate("CANDIDATEDOB"));
					cand.setEmail(rs.getString("CANDIDATEEMAIL"));
					cand.setFirstName(rs.getString("CANDIDATEFIRSTNAME"));
					cand.setLastName(rs.getString("CANDIDATELASTNAME"));
					cand.setMiddleName(rs.getString("CANDIDATEMIDDLENAME"));
					cand.setPhoneNumber(rs.getString("CANDIDATEPHONENUMBER"));
					cand.setPreferredLocation(rs.getString("CANDIDATEPREFERREDLOCATION"));
					cand.setRelevantExperience(rs.getInt("CANDIDATERELEVANTEXPERIENCE"));
					cand.setTitle(rs.getString("CANDIDATETITLE"));
					cand.setTotalExperience(rs.getInt("CANDIDATETOTALEXPERIENCE"));
					cand.setStatus(rs.getInt("CANDIDATESTATUS"));
					cand.setCv(rs.getString("CANDIDATECV"));
					return cand;																//Fetching and returning object
				} catch (SQLException e) {
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
