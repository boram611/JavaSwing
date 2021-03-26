package com.javalec.game2;

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

import com.javalec.game2.Bean;
import com.javalec.share.ShareVar;

public class DbAction {
	
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	int seqno;
	String name;
	String user_Id;
	int people_score;
	
	FileInputStream file;
	
	public DbAction() {
		// TODO Auto-generated constructor stub
	}
	
	

	
	public DbAction(int people_score, String user_Id) {
		super();
		this.people_score = people_score;
		this.user_Id = user_Id;
	}




	public Bean loadBlob() {
		Bean bean = null;
		
		String WhereDefault = "select answer, image from people ";
		String WhereDefault2 = " order by rand() limit 1";
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
            

            if(rs.next()){
            	String wkName = rs.getString(1);
            	// File
            	ShareVar.filename = ShareVar.filename + 1;
            	File file = new File(Integer.toString(ShareVar.filename));
            	
            
            	
            	
            	System.out.println(ShareVar.filename);
            	FileOutputStream output = new FileOutputStream(file);
            	InputStream input = rs.getBinaryStream(2);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
            bean = new Bean(wkName);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		
		return bean;
	}
	
	public ArrayList<Bean> loadBlob1() {
		ArrayList<Bean> beanList = new ArrayList<Bean>();
		
		String WhereDefault = "select answer, image from people ";
		String WhereDefault2 = " order by rand()";
		
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
            Statement stmt_mysql = conn_mysql.createStatement();

            ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2);
            

            while(rs.next()){
            	String wkName = rs.getString(1);
            	// File
            	ShareVar.filename = ShareVar.filename + 1;
            	File file = new File(Integer.toString(ShareVar.filename));
            	
            	
            	
            	
            	System.out.println(ShareVar.filename);
            	FileOutputStream output = new FileOutputStream(file);
            	InputStream input = rs.getBinaryStream(2);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
                Bean bean = new Bean(wkName, ShareVar.filename);
                beanList.add(bean);
            }
            
            conn_mysql.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
		
		return beanList;
	}
	
	
	
	public void peopleScoreAction() {
	      PreparedStatement ps = null;
	      try{
	          

	          String A = "update score set people_score = ? ";
	          String B = " where user_id = ? ";
	          
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();

	          ps = conn_mysql.prepareStatement(A+B);
	          ps.setInt(1, people_score);
	          ps.setString(2, ShareVar.user_id);
	          
	          ps.executeUpdate();

	          conn_mysql.close();
	      } catch (Exception e){
	    	  e.printStackTrace();
	      }
			
		}
	
	
}
