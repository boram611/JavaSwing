package com.javalec.peopleadmin;

import java.sql.Date;

public class Bean {
	
	// Field 
	int people_code;
	String people_answer;
	
	
	
	
	

	public Bean(int people_code) {
		super();
		this.people_code = people_code;
	}

	public Bean(int people_code, String people_answer) {
		super();
		this.people_code = people_code;
		this.people_answer = people_answer;
	}

	public int getPig_code() {
		return people_code;
	}


	public void setPig_code(int people_code) {
		this.people_code = people_code;
	}


	public String getPig_answer() {
		return people_answer;
	}


	public void setPig_answer(String people_answer) {
		this.people_answer = people_answer;
	}






	// Constructor 
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	// Method 
	//setter ~ 데이터베이스 갖다 씀 /getter ~ 여기있는걸 가져감 (주는거)


	
	


}