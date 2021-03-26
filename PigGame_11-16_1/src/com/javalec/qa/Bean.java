package com.javalec.qa;

public class Bean {

	// Field
	int seqno;
	String title;
	String content;
	String comment;
	String user_date;
	
	public Bean() {
		// TODO Auto-generated constructor stub
	}
	
	public Bean(int seqno, String title, String content, String user_date ,String comment) {
		super();
		this.seqno = seqno;
		this.title = title;
		this.content = content;
		this.user_date = user_date;
		this.comment = comment;
		
	}
	public Bean(int seqno, String title, String content, String comment) {
		super();
		this.seqno = seqno;
		this.title = title;
		this.content = content;
		this.comment = comment;
	}
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getuser_date() {
		return user_date;
	}
	public void setuser_date(String user_date) {
		this.user_date = user_date;
	}
	
	
	
}
