package com.javalec.admin;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.share.ShareVar;

public class DbAction {
	// 기능이 안될때 이쪽만 확인해줄것 
	
	
	// ShareVar 클래스에서 가져옴 
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	//********* 게시판 **********
	int seqno;
	String title;
	String content;
	String user_id;
	String comment;
	//**********************
	
	public DbAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

	// 삭제할때 필요 
	public DbAction(int seqno) {
		super();
		this.seqno = seqno;
	}

	// 
	public DbAction(int seqno, String title, String content, String comment) {
		super();
		this.seqno = seqno;
		this.title = title;
		this.content = content;
		this.comment = comment;
	}

	// 입력할때 필요 
	public DbAction(String title, String content, String user_id, String comment) {
		super();
		this.title = title;
		this.content = content;
		this.user_id = user_id;
		this.comment = comment;
	}

	// 
	public DbAction(String title, String content, String comment) {
		super();
		this.title = title;
		this.content = content;
		this.comment = comment;
	}



	// 전체 검색
	//Bean 타입 ~ Bean 가져와서 사용 같이 볼것 
	public ArrayList<Bean> selectList() {
		ArrayList<Bean> beanList = new ArrayList<Bean>();
		//한번에 저장 (4개를 : seqno, name, telno, address, 
		// beanList 4개를 하나로 
		
		// SearchAction() 불러옴 
		 String WhereDefault = "select * from qa " ;
		 System.out.println(WhereDefault);
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

	            while(rs.next()){
	               int wkSeq = rs.getInt(1);
	               String wkUser_id = rs.getString(2);
	               String wkTitle = rs.getString(3);
	               String wkContent = rs.getString(4);
	               String wkUser_date = rs.getString(5);
	               String wkComment = rs.getString(6);
	               String wkAdmin_date = rs.getString(7);
	               
	               Bean bean = new Bean(wkSeq, wkUser_id, wkTitle, wkContent, wkUser_date, wkComment, wkAdmin_date);
	               beanList.add(bean);
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
	        return beanList;
	}
	
	// 부분
	public ArrayList<Bean> QueryAction(String ConditionQueryColumn, String tfSelection){
		ArrayList<Bean> beanList = new ArrayList<Bean>();
		
	       	String WhereDefault = "select * from qa where " + ConditionQueryColumn;
	        String WhereDefault2 = " like '%" + tfSelection + "%'";
	        System.out.println(WhereDefault + WhereDefault2);
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
	
	            while(rs.next()){
	            	int wkSeq = rs.getInt(1);
		               String wkUser_id = rs.getString(2);
		               String wkTitle = rs.getString(3);
		               String wkContent = rs.getString(4);
		               String wkUser_date = rs.getString(5);
		               String wkComment = rs.getString(6);
		               String wkAdmin_date = rs.getString(7);
		               
		               Bean bean = new Bean(wkSeq, wkUser_id, wkTitle, wkContent, wkUser_date, wkComment, wkAdmin_date);
	                  beanList.add(bean);
	            }
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            e.printStackTrace();
	        }
		
	        return beanList;
	}
	
	
	
	
	
	
	// Table을 Click하였을때 검색
	public Bean tableCilck() {
		Bean bean = null;	//선언을 해준 이유 
		
		String WhereDefault = "select seqno, user_id, title, content, comment from qa "; 
		String WhereDefault2 = "where seqno = " + seqno ;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

          if(rs.next()){
          	int wkSeq = rs.getInt(1);
          	String wkUser_id = rs.getString(2);
          	String wkTitle = rs.getString(3);
          	String wkContent = rs.getString(4);
          	String wkComment = rs.getString(5);
          	bean = new Bean(wkSeq, wkUser_id, wkTitle, wkContent, wkComment);
          }
          conn_mysql.close();
      }
      catch (Exception e){
          e.printStackTrace();
      }
		return bean;
	}
	
//
//	
//	// Data 삭제 
	public boolean deleteAction() {
      PreparedStatement ps = null;
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

          String A = "delete from qa where seqno = ? ";

          ps = conn_mysql.prepareStatement(A);
          
          ps.setInt(1, seqno);
          ps.executeUpdate();

          conn_mysql.close();
      } catch (Exception e){
    	  return false;
      }
      return true;   
	}
	
	
	//Data 수정 
	public boolean updateAction() {
		PreparedStatement ps = null;
      try{
    	  String A = "update qa set title = ?, content = ?, comment = ? , admin_date = now() ";
        String B = " where seqno = ? ";
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

          
          ps = conn_mysql.prepareStatement(A+B);
          		ps.setString(1, title);
            	ps.setString(2, content);
            	ps.setString(3, comment);
            	ps.setInt(4, seqno);
            	
            	
           ps.executeUpdate();
          conn_mysql.close();
      } catch (Exception e){
          return false;
      }
      return true;
	}
	
	



	

}
