package com.javalec.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.admin.BeanUser;
import com.javalec.share.ShareVar;

public class DbActionUser {
	// ShareVar 클래스에서 가져옴 
		private final String url_mysql = ShareVar.url_mysql;
		private final String id_mysql = ShareVar.id_mysql;
		private final String pw_mysql = ShareVar.pw_mysql;
		
		String id;
		String name;
		String address;
		String email;
		
		FileInputStream user_img;
		
		public DbActionUser() {
			// TODO Auto-generated constructor stub
		}
		


		public DbActionUser(String id) {
			super();
			this.id = id;
		}



		// 전체 검색
		public ArrayList<BeanUser> selectList() {
			ArrayList<BeanUser> beanUserList = new ArrayList<BeanUser>();
			 String WhereDefault = "select id, name, address, email from user " ;
			 System.out.println(WhereDefault);
		        try{
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		            Statement stmt_mysql = conn_mysql.createStatement();
		            
		            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

		            while(rs.next()){
		            	String wkId = rs.getString(1);
		            	String wkName = rs.getString(2);
		            	String wkAddress = rs.getString(3);
		            	String wkEmail = rs.getString(4);
		              	
		              	BeanUser beanUser = new BeanUser(wkId, wkName, wkAddress, wkEmail);
		              	beanUserList.add(beanUser);
		            }
		            conn_mysql.close();
		        }
		        catch (Exception e){
		            e.printStackTrace();
		        }
		        return beanUserList;
		}
		
		// 부분
		public ArrayList<BeanUser> QueryAction(String ConditionQueryColumn, String tfSelection){
			ArrayList<BeanUser> BeanUserList = new ArrayList<BeanUser>();
			
		       	String WhereDefault = "select * from user where " + ConditionQueryColumn;
		        String WhereDefault2 = " like '%" + tfSelection + "%'";
		        System.out.println(WhereDefault + WhereDefault2);
		        try{
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		            Statement stmt_mysql = conn_mysql.createStatement();
		
		            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
		
		            while(rs.next()){
		            	String wkId = rs.getString(1);
		            	String wkName = rs.getString(2);
		            	String wkAddress = rs.getString(3);
		            	String wkEmail = rs.getString(4);
		              	
		            	BeanUser beanUser = new BeanUser(wkId, wkName, wkAddress, wkEmail);
		                BeanUserList.add(beanUser);
		            }
		            conn_mysql.close();
		        }
		        catch (Exception e){
		            e.printStackTrace();
		        }
			
		        return BeanUserList;
		}
		
		
		
		// Table을 Click하였을때 검색
		public BeanUser TableCilck() {
			BeanUser BeanUser = null;	//선언을 해준 이유 
			
			String WhereDefault = "select id, name, address, email, img from user "; 
			String WhereDefault2 = "where id = '" + id + "'" ;
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

	          if(rs.next()){
	        	String wkId = rs.getString(1);
            	String wkName = rs.getString(2);
            	String wkAddress = rs.getString(3);
            	String wkEmail = rs.getString(4);
	          	// pig_img
	        	ShareVar.filename = ShareVar.filename + 1;
	        	File user_img = new File(Integer.toString(ShareVar.filename));
	        	FileOutputStream output = new FileOutputStream(user_img);
	        	InputStream input = rs.getBinaryStream(5);
	            byte[] buffer = new byte[1024];
	            while (input.read(buffer) > 0) {
	                output.write(buffer);
	            }
	          	BeanUser = new BeanUser(wkId, wkName, wkAddress, wkEmail);
	          }
	          conn_mysql.close();
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
			return BeanUser;
		}
		
	//
		
//		 Data 삭제 
		public boolean deleteAction() {
	      PreparedStatement ps = null;
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();

	          String A = "delete from score where user_id = ? ";

	          ps = conn_mysql.prepareStatement(A);
	          
	          ps.setString(1, id);
	          
	          ps.executeUpdate();
	          
	          String B = "delete from user where id = ? ";
	          
	          ps = conn_mysql.prepareStatement(B);
	          
	          ps.setString(1, id);
	          
	          ps.executeUpdate();

	          conn_mysql.close();
	      } catch (Exception e){
	    	  return false;
	      }
	      return true;   
		}
		

		
		



		

}
