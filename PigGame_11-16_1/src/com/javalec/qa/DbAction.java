package com.javalec.qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.qa.Bean;
import com.javalec.share.ShareVar;

public class DbAction {
	
	// ShareVar 클래스에서 가져옴 
		private final String url_mysql = ShareVar.url_mysql;
		private final String id_mysql = ShareVar.id_mysql;
		private final String pw_mysql = ShareVar.pw_mysql;
		
		int seqno;
		String title;
		String content;
//		String user_id;
		
		// Constructor
		
		public DbAction() {
			// TODO Auto-generated constructor stub
		}
		
		public DbAction(int seqno) {
			super();
			this.seqno = seqno;
		}
		

		public DbAction(String title, String content) {
			super();
			this.title = title;
			this.content = content;
		}

		
		public DbAction(int seqno, String title, String content) {
			super();
			this.seqno = seqno;
			this.title = title;
			this.content = content;
		}

		// Table을 Click하였을때 검색
		public Bean tableCilck() {
			
			Bean bean = null;	//선언을 해준 이유 
			
			String WhereDefault = "select seqno, title, content, comment from qa "; 
			String WhereDefault2 = "where seqno = " + seqno ;
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	          if(rs.next()){
	          	int wkSeq = rs.getInt(1);
	          	String wkTitle = rs.getString(2);
	          	String wkContent = rs.getString(3);
	          	String wkComment = rs.getString(4);
	          	bean = new Bean(wkSeq, wkTitle, wkContent, wkComment);
	          }
	          conn_mysql.close();
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
			return bean;
		}
		
		
		
		
		
		
		// 전체 검색
		//Bean 타입 ~ Bean 가져와서 사용 같이 볼것 
		public ArrayList<Bean> selectList() {
			ArrayList<Bean> beanList = new ArrayList<Bean>();
			//한번에 저장 (4개를 : seqno, name, telno, address, 
			// beanList 4개를 하나로 
			
			// SearchAction() 불러옴 
			 System.out.println(ShareVar.user_id);
			 String WhereDefault = "select seqno, title, content, user_date, comment from qa " + "where user_id = '" + ShareVar.user_id + "'";
			 System.out.println(WhereDefault);
		        try{
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		            Statement stmt_mysql = conn_mysql.createStatement();
		            
		            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

		            while(rs.next()){
		               int wkSeq = rs.getInt(1);
		               String wkTitle = rs.getString(2);
		               String wkContent = rs.getString(3);
		               String wkuser_date = rs.getString(4);
		               String wkComment = rs.getString(5);
		               Bean bean = new Bean(wkSeq, wkTitle, wkContent, wkuser_date, wkComment);
		               beanList.add(bean);
		            }
		            conn_mysql.close();
		        }
		        catch (Exception e){
		            e.printStackTrace();
		        }
		        return beanList;
		}
		
		




		public ArrayList<Bean> QueryAction(String ConditionQueryColumn, String tfCondition){
			ArrayList<Bean> beanList = new ArrayList<Bean>();
			
		       	String WhereDefault = "select seqno, title, content, user_date, comment from qa where " + ConditionQueryColumn;
		        String WhereDefault2 = " like '%" + tfCondition + "%' and user_id = '" + ShareVar.user_id + "'";
		        System.out.println(WhereDefault + WhereDefault2);
		        try{
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		            Statement stmt_mysql = conn_mysql.createStatement();
		
		            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
		
		            while(rs.next()){
		            	int wkSeq = rs.getInt(1);
			               String wkTitle = rs.getString(2);
			               String wkContent = rs.getString(3);
			               String wkuser_date = rs.getString(4);
			               String wkComment = rs.getString(5);
		                  
		                  Bean bean = new Bean(wkSeq, wkTitle, wkContent, wkuser_date, wkComment);
		                  beanList.add(bean);
		            }
		            conn_mysql.close();
		        }
		        catch (Exception e){
		            e.printStackTrace();
		        }
			
		        return beanList;
		}
		
		
		
		
		// 입력 
		public boolean insertAction() {
	      PreparedStatement ps = null;
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();

	          String A = "insert into qa (title, content, user_id, user_date";
	          String B = ") values (?,?,?, now())";

	          ps = conn_mysql.prepareStatement(A+B);
	          ps.setString(1, title);
	          ps.setString(2, content);
	          ps.setString(3, ShareVar.user_id);
	          
	          ps.executeUpdate();

	          conn_mysql.close();
	      } catch (Exception e){
	    	  e.printStackTrace();
	         return false;
	      }
	        return true;      
			
		}
		
		
		
	//
	//	
//		// Data 삭제 
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
	    	  String A = "update qa set title = ?, content = ?";
	        String B = " where seqno = ? ";
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();

	          
	          ps = conn_mysql.prepareStatement(A+B);
	          		ps.setString(1, title);
	            	ps.setString(2, content);
	            	ps.setInt(3, seqno);
	            	
	           ps.executeUpdate();
	          conn_mysql.close();
	      } catch (Exception e){
	          return false;
	      }
	      return true;
		}
		
		



		

		
		
}
