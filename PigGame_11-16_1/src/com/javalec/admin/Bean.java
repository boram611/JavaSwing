package com.javalec.admin;

import java.sql.Date;

public class Bean {
	
	// Field 
	int seqno;
	String user_id;
	String title;
	String content;
	String user_date;
	String comment;
	String admin_date; 
	
	
	
	
	


// 전체 불러오기 
	public Bean(int seqno, String user_id, String title, String content, String user_date, String comment,
			String admin_date) {
		super();
		this.seqno = seqno;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.user_date = user_date;
		this.comment = comment;
		this.admin_date = admin_date;
	}

	public Bean(int seqno,  String user_id , String title, String content) {
		super();
		this.seqno = seqno;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		
	}

	
	


	public Bean(int seqno,String user_id,  String title, String content, String comment) {
		super();
		this.seqno = seqno;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.comment = comment;
	}



	// Constructor 
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	// Method 
	//setter ~ 데이터베이스 갖다 씀 /getter ~ 여기있는걸 가져감 (주는거)


	
	

	public String getUser_date() {
		return user_date;
	}


	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}


	public String getAdmin_date() {
		return admin_date;
	}


	public void setAdmin_date(String admin_date) {
		this.admin_date = admin_date;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getSeqno() {
		return seqno;
	}


	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}


	public Bean(int seqno, String title, String content) {
		super();
		this.seqno = seqno;
		this.title = title;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}




	
	

}
