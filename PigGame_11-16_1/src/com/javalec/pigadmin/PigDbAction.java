package com.javalec.pigadmin;

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

import javax.swing.JOptionPane;

import com.javalec.share.ShareVar;

public class PigDbAction {
	// 기능이 안될때 이쪽만 확인해줄것 
	
	
	// ShareVar 클래스에서 가져옴 
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	
	//********pig_game************
	int pig_code;
	String pig_answer;
	FileInputStream pig_img;
	//**************************
	
	
	
	public PigDbAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	public PigDbAction(int pig_code, String pig_answer, FileInputStream pig_img) {
		super();
		this.pig_code = pig_code;
		this.pig_answer = pig_answer;
		this.pig_img = pig_img;
	}


	//돼지력 테스트 게임 입력 *********************
	public PigDbAction(String pig_answer, FileInputStream pig_img) {
		super();
		this.pig_answer = pig_answer;
		this.pig_img = pig_img;
	}
	
	
	

	// 전체 검색
	public ArrayList<Bean> selectList() {
		ArrayList<Bean> beanList = new ArrayList<Bean>();
		 String WhereDefault = "select code, answer from pig " ;
		 System.out.println(WhereDefault);
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault);

	            while(rs.next()){
	            	int wkPig_code = rs.getInt(1);
	              	String wkPig_answer = rs.getString(2);
	              	
	              	Bean bean = new Bean(wkPig_code, wkPig_answer);
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
		
	       	String WhereDefault = "select * from pig where " + ConditionQueryColumn;
	        String WhereDefault2 = " like '%" + tfSelection + "%'";
	        System.out.println(WhereDefault + WhereDefault2);
	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	
	            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
	
	            while(rs.next()){
	            	int wkPig_code = rs.getInt(1);
	              	String wkPig_answer = rs.getString(2);
	              	
	              	Bean bean = new Bean(wkPig_code, wkPig_answer);
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
	public Bean TableCilck() {
		Bean bean = null;	//선언을 해준 이유 
		
		String WhereDefault = "select code, answer, image from pig "; 
		String WhereDefault2 = "where code = " + pig_code ;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);

          if(rs.next()){
          	int wkPig_code = rs.getInt(1);
          	String wkPig_answer = rs.getString(2);
          	// pig_img
        	ShareVar.filename = ShareVar.filename + 1;
        	File pig_img = new File(Integer.toString(ShareVar.filename));
        	FileOutputStream output = new FileOutputStream(pig_img);
        	InputStream input = rs.getBinaryStream(3);
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);
            }
          	bean = new Bean(wkPig_code, wkPig_answer);
          }
          conn_mysql.close();
      }
      catch (Exception e){
          e.printStackTrace();
      }
		return bean;
	}
	
//
	
	
	
	
	
	
// 		Data 입력 
	public boolean InsertAction() {
	      PreparedStatement ps = null;
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();
	
	          String A = "insert into pig (answer, image";
	          String B = ") values (?,?)";
	
	          ps = conn_mysql.prepareStatement(A+B);
	          ps.setString(1, pig_answer.trim());
	          ps.setBinaryStream(2, pig_img);
	          ps.executeUpdate();
	
	          conn_mysql.close();
	      } catch (Exception e){
	          e.printStackTrace();
	          return false;
	      }
	      return true;
	}
	
	
	
	
	
//	 Data 삭제 
	public boolean deleteAction() {
      PreparedStatement ps = null;
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

          String A = "delete from pig where code = ? ";

          ps = conn_mysql.prepareStatement(A);
          
          ps.setInt(1, pig_code);
          ps.executeUpdate();

          conn_mysql.close();
      } catch (Exception e){
    	  return false;
      }
      return true;   
	}
	
	
	public PigDbAction(int pig_code) {
		super();
		this.pig_code = pig_code;
	}


	//Data 수정 
	public boolean updateAction() {
		PreparedStatement ps = null;
      try{
    	  String A = "update pig set answer = ?, image = ?";
        String B = " where code = ? ";
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
          @SuppressWarnings("unused")
			Statement stmt_mysql = conn_mysql.createStatement();

          
          ps = conn_mysql.prepareStatement(A+B);
          ps.setString(1, pig_answer);
          ps.setBinaryStream(2, pig_img);
          ps.setInt(3, pig_code);
            	
           ps.executeUpdate();
          conn_mysql.close();
      } catch (Exception e){
          return false;
      }
      return true;
	}
	
	



	

}
