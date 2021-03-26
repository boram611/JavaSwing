package com.javalec.game1;

import java.io.InputStream;

public class Bean {

	int seqno;
	String name;
	
	int fileName;
	
	
	
	
	
	public int getFileName() {
		return fileName;
	}

	public void setFileName(int fileName) {
		this.fileName = fileName;
	}


	public Bean(String name, int fileName) {
		super();
		this.name = name;
		this.fileName = fileName;
	}


	public Bean(String name) {
		super();
		this.name = name;
	}
	
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
