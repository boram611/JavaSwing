package com.javalec.pigadmin;

import java.sql.Date;

public class Bean {
	
	// Field 
	int pig_code;
	String pig_answer;
	
	
	
	
	

	public Bean(int pig_code) {
		super();
		this.pig_code = pig_code;
	}

	public Bean(int pig_code, String pig_answer) {
		super();
		this.pig_code = pig_code;
		this.pig_answer = pig_answer;
	}

	public int getPig_code() {
		return pig_code;
	}


	public void setPig_code(int pig_code) {
		this.pig_code = pig_code;
	}


	public String getPig_answer() {
		return pig_answer;
	}


	public void setPig_answer(String pig_answer) {
		this.pig_answer = pig_answer;
	}






	// Constructor 
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	// Method 
	//setter ~ 데이터베이스 갖다 씀 /getter ~ 여기있는걸 가져감 (주는거)


	
	


}