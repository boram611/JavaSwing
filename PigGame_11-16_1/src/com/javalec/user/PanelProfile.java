package com.javalec.user;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;

import com.javalec.base.FrameMain;
import com.javalec.share.ShareVar;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelProfile extends JPanel {
	private JPanel panel;
	//이미지 선언
	private Image img = new ImageIcon(FrameLogin.class.getResource("/res/pro.png")).getImage().getScaledInstance(300, 60, Image.SCALE_SMOOTH);
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblId_1_1;
	private JLabel lblId_1_1_1;
	private JLabel lblId_1_1_1_1;
	private JLabel lblId_1_1_1_1_1;
	private JTextField tfName;
	private JTextField tfId;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JButton btnOk;
	private JComboBox cbAddress;
	private JComboBox cbEmail;
	private JPasswordField pfPw;
	private JPasswordField pfPwCheck;
	
	private final String url_mysql = ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JLabel lblPicture;
	private JTextField getTfFilePath;
	private JButton getBtnFile;
	private JLabel lblPro;
	private JLabel non;
	private JLabel lblPwCheck;
	private JTextField tfEmailCheck;
	
	public int checkSignOut = 0;

	/**
	 * Create the panel.
	 */
	public PanelProfile() {
		setBackground(new Color(0, 102, 102));
		setBounds(0, 0, 634, 457);
		setLayout(null);
		add(getPanel());

	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(SystemColor.window);
			panel.setBounds(26, 26, 581, 403);
			panel.setLayout(null);
			panel.add(getLblNewLabel());
			panel.add(getLblId());
			panel.add(getLblName());
			panel.add(getLblId_1_1());
			panel.add(getLblId_1_1_1());
			panel.add(getLblId_1_1_1_1());
			panel.add(getLblId_1_1_1_1_1());
			panel.add(getTfName());
			panel.add(getTfId());
			panel.add(getTfAddress());
			panel.add(getTfEmail());
			panel.add(getBtnOk());
			panel.add(getCbAddress());
			panel.add(getCbEmail());
			panel.add(getPfPw());
			panel.add(getPfPwCheck());
			panel.add(getLblPicture());
			panel.add(getGetTfFilePath());
			panel.add(getGetBtnFile());
			panel.add(getLblPro());
			panel.add(getNon());
			panel.add(getLblPwCheck());
			panel.add(getTfEmailCheck());
			panel.add(getBtnSignOut());
			
			getId();
		}
		return panel;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("PROFILE");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
			lblNewLabel.setBounds(23, 24, 126, 35);
		}
		return lblNewLabel;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID ");
			lblId.setBounds(302, 87, 25, 16);
		}
		return lblId;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("NAME");
			lblName.setBounds(282, 129, 45, 16);
		}
		return lblName;
	}
	private JLabel getLblId_1_1() {
		if (lblId_1_1 == null) {
			lblId_1_1 = new JLabel("ADDRESS");
			lblId_1_1.setBounds(259, 171, 68, 16);
		}
		return lblId_1_1;
	}
	private JLabel getLblId_1_1_1() {
		if (lblId_1_1_1 == null) {
			lblId_1_1_1 = new JLabel("EMAIL");
			lblId_1_1_1.setBounds(282, 204, 45, 16);
		}
		return lblId_1_1_1;
	}
	private JLabel getLblId_1_1_1_1() {
		if (lblId_1_1_1_1 == null) {
			lblId_1_1_1_1 = new JLabel("변경할 비밀번호");
			lblId_1_1_1_1.setBounds(237, 275, 91, 16);
		}
		return lblId_1_1_1_1;
	}
	private JLabel getLblId_1_1_1_1_1() {
		if (lblId_1_1_1_1_1 == null) {
			lblId_1_1_1_1_1 = new JLabel("비밀번호 확인");
			lblId_1_1_1_1_1.setBounds(250, 307, 79, 16);
		}
		return lblId_1_1_1_1_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(344, 122, 176, 30);
			tfName.setColumns(10);
			
		}
		return tfName;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEnabled(false);
			tfId.setColumns(10);
			tfId.setBounds(344, 80, 176, 30);
			
			
//			getId();
		}
		return tfId;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(431, 163, 85, 30);
		}
		return tfAddress;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String stEmail = cbEmail.getSelectedItem().toString();
					tfEmailCheck.setText(tfEmail.getText().trim() + stEmail);
				}
			});
			tfEmail.setColumns(10);
			tfEmail.setBounds(344, 197, 85, 30);
		}
		return tfEmail;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("수정하기");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = insertFieldCheck();
					if(i_chk == 0){
					switch (checkName() + checkPw() + checkEmail() + checkAddress() + checkImage()) {
					case 0:
						Message();
						JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
						
						break;
					case 1:
						Message();
						JOptionPane.showMessageDialog(null, "변경전과 동일한 이름입니다.");
						tfName.requestFocus();
						break;
					case 2:
						Message();
						JOptionPane.showMessageDialog(null, "변경전과 동일한 비밀번호입니다.");
						pfPw.requestFocus();
						break;
					case 3:
						Message();
						JOptionPane.showMessageDialog(null, "변경전과 동일한 이메일 입니다.");
						tfEmail.requestFocus();
						break;
					case 4:
						Message();
						JOptionPane.showMessageDialog(null, "변경전과 동일한 주소 입니다.");
					case 5:
						Message();
						JOptionPane.showMessageDialog(null, "변경전과 동일한 이미지 입니다.");
						
					case 6: 
						Message();
						JOptionPane.showMessageDialog(null, "잘못된 이메일 형식입니다.");
						tfEmail.requestFocus();
						break;
						
					default:
						break;
						}
					}else {
					Message();
					JOptionPane.showMessageDialog(null, "Data를 입력하세요");
					}
				}
				
			});
			btnOk.setBounds(452, 352, 102, 37);
		}
		return btnOk;
	}
	private JComboBox getCbAddress() {
		if (cbAddress == null) {
			cbAddress = new JComboBox();
			cbAddress.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String stAddress = cbAddress.getSelectedItem().toString();
					tfAddress.setText(stAddress);
				}
			});
			cbAddress.setModel(new DefaultComboBoxModel(new String[] {"서울", "인천", "대전", "부산", "대구", "울산", "광주", "경기도", "강원도", "충청도", "경상도", "전라도"}));
			cbAddress.setBounds(344, 161, 79, 35);
		}
		return cbAddress;
	}
	private JComboBox getCbEmail() {
		if (cbEmail == null) {
			cbEmail = new JComboBox();
			cbEmail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String stEmail = cbEmail.getSelectedItem().toString();
					if (stEmail  == "직접입력") {
						tfEmailCheck.setEditable(true);
						tfEmailCheck.setText(tfEmail.getText());
					}
					else if (stEmail == "@naver.com" || stEmail == "@gmail.com"|| stEmail == "@icloud.com" ||stEmail ==  "@hanmail.net" || stEmail == "@kakao.com") {
						tfEmailCheck.setText(tfEmail.getText() + cbEmail.getSelectedItem().toString());
						
					}
					
				}
			});
			cbEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			cbEmail.setModel(new DefaultComboBoxModel(new String[] {"@naver.com", "@gmail.com", "@icloud.com", "@hanmail.net", "@kakao.com", "직접입력"}));
			cbEmail.setBounds(437, 195, 117, 35);
		}
		return cbEmail;
	}
	private JPasswordField getPfPw() {
		if (pfPw == null) {
			pfPw = new JPasswordField();
			pfPw.setBounds(345, 268, 176, 30);
		}
		return pfPw;
	}
	private JPasswordField getPfPwCheck() {
		if (pfPwCheck == null) {
			pfPwCheck = new JPasswordField();
			pfPwCheck.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					char[] secret_pw = pfPw.getPassword();
		        	char[] secret_pwCheck = pfPwCheck.getPassword();
		        	String secret_pw_String = new String(secret_pw);
		        	String secret_pwCheck_String = new String(secret_pwCheck);

					if (secret_pw_String.equals(secret_pwCheck_String)) {
						lblPwCheck.setText("같은 암호입니다.");
						lblPwCheck.setForeground(Color.BLUE);
					}
					else {
						lblPwCheck.setText("다른 암호입니다.");
						lblPwCheck.setForeground(Color.RED);
					}	
					
				}
			});
			pfPwCheck.setBounds(346, 299, 176, 30);
		}
		return pfPwCheck;
	}
	
	
	
	
	
	
	
	
	
	private void getId() {
		

	      String WhereDefault = "select id, name, address, email, img from user "; 
	      String WhereDefault2 = "where id = '" + ShareVar.user_id + "'"  ;
	      System.out.println(ShareVar.user_id);
	      
	      try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(WhereDefault + WhereDefault2 );
	
	          if(rs.next()){
	        	tfId.setText(rs.getString(1));
	        	tfName.setText(rs.getString(2));
	        	tfAddress.setText(rs.getString(3));
	        	
	        	
	        	
	        	
	        	tfEmail.setText(rs.getString(4).substring(0,(rs.getString(4).indexOf("@"))));
	        	tfEmailCheck.setText(rs.getString(4));
	        	
	        	
	        	
	        	 
	        	ShareVar.filename = ShareVar.filename + 1;
            	File file = new File(Integer.toString(ShareVar.filename));
            	
            
            	
            	
            	System.out.println(ShareVar.filename);
            	FileOutputStream output = new FileOutputStream(file);
            	InputStream input = rs.getBinaryStream(5);
                byte[] buffer = new byte[1024];
                while (input.read(buffer) > 0) {
                    output.write(buffer);
                }
	          }
	          conn_mysql.close();
	         
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
	      
	   // Image처리
	        // File name이 틀려야 즉각 보여주기가 가능하여   
	        // ShareVar에서 int값으로 정의하여 계속 증가하게 하여 file name으로 사용후 삭제
			String filePath = Integer.toString(ShareVar.filename);
			
			lblPicture.setIcon(new ImageIcon(filePath));
			lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
			
			File file = new File(filePath);
			file.delete();
	}//getId
	
	private int checkName() {
		PreparedStatement ps = null;
		
		int check = 0;
		String where1 = "select name from user ";
		String where2 = "where id = '" + ShareVar.user_id + "'" + " and name = '" + tfName.getText().trim()  + "'";
		String where3 = "update user set name = ?  where id = '" + ShareVar.user_id + "'";
		
		try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	          ps = conn_mysql.prepareStatement(where3);
	        		  
	          if(rs.next()==true) {
	        	  
	        	  
	          }else {
	        	  ps.setString(1, tfName.getText().trim());
	        	  ps.executeUpdate();
	        	  check = 0;
	          }
	          System.out.println("1 이면 이름 중복" + check);
	          conn_mysql.close();
	         
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
		return check;
	}//checkName
	
	private int checkPw() {
		PreparedStatement ps = null;
		int check = 0;
		
		
		String where1 = "select pw from user ";
		String where2 = "where id = '" + ShareVar.user_id + "'" +  " and pw = '" +String.valueOf(pfPwCheck.getPassword())  + "'";
		String where3 = "update user set pw = ?  where id = '" + ShareVar.user_id + "'";
		
		try{
			char[] secret_pw = pfPw.getPassword();
    		char[] secret_pwCheck = pfPwCheck.getPassword();
    		String secret_pw_String = new String(secret_pw);
    		String secret_pwCheck_String = new String(secret_pwCheck);
			if(secret_pw_String.equals(secret_pwCheck_String)) {
    		
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	          ps = conn_mysql.prepareStatement(where3);
	        		  
	          if(rs.next()==true) {
	        	  check = 2;
	          }else {
	        	  ps.setString(1, String.valueOf(pfPwCheck.getPassword()));
	        	  ps.executeUpdate();
	        	  check =  0;
	          }
	          System.out.println("2 이면 비번 중복!!" + check);
	          
	          conn_mysql.close();
	          
			}
			else {
				check =  4   ; 
				
			}
	         
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
		return check;
	}//checkPw
	
	EmailValidator emailValidator = new EmailValidator();
	private JButton btnSignOut;
	private int checkEmail() {
		PreparedStatement ps = null;
		int check = 0;
		
		
		String where1 = "select email from user ";
		String where2 = "where id = '" + ShareVar.user_id + "'" +  " and email = '" + tfEmailCheck.getText().trim()  + "'";
		String where3 = "update user set email = ?  where id = '" + ShareVar.user_id + "'";
		
		if (emailValidator.validate(tfEmailCheck.getText().trim())) {
		
		
		try{
			
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	          
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	          ps = conn_mysql.prepareStatement(where3);
	        		  
	          if(rs.next()==true) {
	        	
	          }else {
	        	  if (!emailValidator.validate(tfEmailCheck.getText().trim())) {
	  			 	check = 6;
//	  			Message();
//	  			ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
//	  			JOptionPane.showMessageDialog(null, "잘못된 Email 형식입니다.", "경고", JOptionPane.OK_OPTION, icon);
	  		}
	        	  ps.setString(1, String.valueOf(tfEmail.getText()));
	        	  ps.executeUpdate();
	        	  check =  0;
	          }
	          System.out.println("2 이면 비번 중복" + check);
	          
	          conn_mysql.close();
	          
			}
		
				
			catch (Exception e){
	          e.printStackTrace();
	      }
		return check;
		}
		else 
		return check;
	}//checkEmail
	
	
	
	
	
	private int checkAddress() {
		PreparedStatement ps = null;
		int check = 0;
		
		
		String where1 = "select address from user ";
		String where2 = "where id = '" + ShareVar.user_id + "'" +  " and address = '" + tfAddress.getText().trim()  + "'";
		String where3 = "update user set address = ?  where id = '" + ShareVar.user_id + "'";
		
		try{
			
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	          ps = conn_mysql.prepareStatement(where3);
	        		  
	          if(rs.next()==true) {
	        	
	          }else {
	        	  ps.setString(1, String.valueOf(tfAddress.getText()));
	        	  ps.executeUpdate();
	        	  check =  0;
	          }
	          System.out.println("2 이면 비번 중복" + check);
	          
	          conn_mysql.close();
	          
			}
		
				
			catch (Exception e){
	          e.printStackTrace();
	      }
		return check;
	
	}//checkEmail
	
	
	private int checkImage() {
		PreparedStatement ps = null;
		int check = 0;
		
		
		
		FileInputStream input = null;
		File file = new File(getTfFilePath.getText());
		
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          String where3 = "update user set img = ?  where id = '" + ShareVar.user_id + "'";

	          ps = conn_mysql.prepareStatement(where3);
	        		  
	        	  
	        	  ps.setBinaryStream(1, input);
	        	  ps.executeUpdate();
					
	          
	          conn_mysql.close();
	          
			}
		
				
			catch (Exception e){
	          e.printStackTrace();
	      }
		return check;
	
	}//checkEmail
	
	
	private JLabel getLblPicture() {
		if (lblPicture == null) {
			lblPicture = new JLabel("");
			lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
			lblPicture.setBounds(27, 84, 203, 209);
		}
		return lblPicture;
	}
	private JTextField getGetTfFilePath() {
		if (getTfFilePath == null) {
			getTfFilePath = new JTextField();
			getTfFilePath.setBounds(33, 306, 178, 26);
			getTfFilePath.setColumns(10);
		}
		return getTfFilePath;
	}
	private JButton getGetBtnFile() {
		if (getBtnFile == null) {
			getBtnFile = new JButton("사진선택");
			getBtnFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FilePath();
				}
			});
			getBtnFile.setBounds(101, 342, 117, 29);
		}
		return getBtnFile;
	}
	private void FilePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP", "jpg","png","bmp");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			Message();
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		getTfFilePath.setText(filePath);
		lblPicture.setIcon(new ImageIcon(filePath));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	//빈칸 있을때 
		private int insertFieldCheck(){
			int i = 0;
			
			if(tfName.getText().length() == 0){
				i++;
				tfName.requestFocus();
				
			}
			if(tfEmail.getText().length() == 0){
				i++;
				tfEmail.requestFocus();
			}
			if(tfAddress.getText().length() == 0){
				i++;
				tfAddress.requestFocus();
			}
//			if(getTfFilePath.getText().length() == 0) {
//				i++;
//			}
			if(pfPw.getPassword().length == 0) {
				i++;
				pfPw.requestFocus();
			}
		
					
			return i;
		}
		
		private void Message() {
			UIManager.put("OptionPane.background", Color.decode("#004e4c"));
			UIManager.put("OptionPane.messageForeground", Color.white);
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
			UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
		}
		
	private JLabel getLblPro() {
		if (lblPro == null) {
			lblPro = new JLabel("");
			lblPro.setIcon(new ImageIcon(img));
			lblPro.setBounds(-1, 0, 303, 67);
		}
		return lblPro;
	}
	private JLabel getNon() {
		if (non == null) {
			non = new JLabel("");
			non.setBounds(236, 239, 91, 16);
		}
		return non;
	}
	private JLabel getLblPwCheck() {
		if (lblPwCheck == null) {
			lblPwCheck = new JLabel("");
			lblPwCheck.setBounds(348, 333, 172, 16);
		}
		return lblPwCheck;
	}
	private JTextField getTfEmailCheck() {
		if (tfEmailCheck == null) {
			tfEmailCheck = new JTextField();
			tfEmailCheck.setEditable(false);
			tfEmailCheck.setBounds(344, 239, 176, 26);
			tfEmailCheck.setColumns(10);
		}
		return tfEmailCheck;
	}
	private JButton getBtnSignOut() {
		if (btnSignOut == null) {
			btnSignOut = new JButton("회원탈퇴");
			btnSignOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null, "회원탈퇴하시면 게임을 진행하지 못합니다.\n탈퇴 하시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, null) == 0) { 
					boolean msg  = deleteAction();
					if (msg = true) {
						JOptionPane.showMessageDialog(null, "탈퇴완료. 로그인 화면으로 돌아갑니다");
//						System.exit(0);
						checkSignOut = 1;
//						FrameMain frameMain = new FrameMain();
//						frameMain.dispose();
//						frameMain.toLogin();
//						FrameLogin frameLogin = new FrameLogin();
					}else {
						JOptionPane.showMessageDialog(null, "삭제실패");
					}
				}
				}
			});
			btnSignOut.setBounds(472, 10, 97, 23);
		}
		return btnSignOut;
	}
	
	public boolean deleteAction() {
		PreparedStatement ps = null;
		try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          @SuppressWarnings("unused")
				Statement stmt_mysql = conn_mysql.createStatement();
	          String A = "delete from score where user_id = '" + ShareVar.user_id + "'";
	          String B = "delete from user where id = '" + ShareVar.user_id + "'";
	          System.out.println(A);
	          ps = conn_mysql.prepareStatement(A);
	          ps.executeUpdate();
	          ps = conn_mysql.prepareStatement(B);
	          ps.executeUpdate();
	          
//	          ps.setInt(1, seqno);
	
	          conn_mysql.close();
	      } catch (Exception e){
	          return false;
	      }
		System.out.println("삭제진행중?");
	    return true;
		}
	
}
