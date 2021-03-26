package com.javalec.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;

import com.javalec.game1.GameMain1;
import com.javalec.game2.GameMain2;
import com.javalec.game2.GameRul2;
//import com.javalec.function;
//import com.javalec.function;
import com.javalec.share.ShareVar;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.CyclicBarrier;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JTextArea;

public class FrameSignUp extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JPasswordField pfPw;
	private JPasswordField pfPwCheck;
	FileInputStream file;
	//DB 선언
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	
	//이미지 선언
	private Image img_logo = new ImageIcon(FrameLogin.class.getResource("/res/signup.png")).getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
	
	private JTextField tfFilePath;
	private JLabel lblImage;
	private JLabel lblPicture;
	private JLabel lblNewLabel;
	private JLabel lblPwCheck;
	private JTextField tfEmailCheck;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSignUp frame = new FrameSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameSignUp() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 761);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(38, 29, 623, 669);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfId = new JTextField();
		tfId.setBounds(206, 127, 229, 31);
		panel.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Arial", Font.BOLD, 18));
		lblID.setBounds(173, 131, 26, 23);
		panel.add(lblID);
		
		JLabel lblSignUp = new JLabel("SIGN UP");
		lblSignUp.setBounds(266, 68, 132, 44);
		panel.add(lblSignUp);
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(Color.DARK_GRAY);
		lblSignUp.setFont(new Font("Arial", Font.BOLD, 28));
		
		JButton btnIdCheck = new JButton("중복확인");
		btnIdCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IdCheck();
			}
		});
		btnIdCheck.setForeground(Color.DARK_GRAY);
		btnIdCheck.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnIdCheck.setBounds(447, 127, 81, 31);
		panel.add(btnIdCheck);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Arial", Font.BOLD, 18));
		lblName.setBounds(135, 182, 64, 23);
		panel.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(206, 178, 229, 31);
		panel.add(tfName);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("Arial", Font.BOLD, 18));
		lblPw.setBounds(161, 297, 38, 23);
		panel.add(lblPw);
		
		JLabel lblPwcheck = new JLabel("CHECK PW");
		lblPwcheck.setFont(new Font("Arial", Font.BOLD, 18));
		lblPwcheck.setBounds(99, 348, 100, 23);
		panel.add(lblPwcheck);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 18));
		lblAddress.setBounds(99, 407, 100, 23);
		panel.add(lblAddress);
		
		JComboBox cbAddress = new JComboBox();
		cbAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stAddress = cbAddress.getSelectedItem().toString();
				tfAddress.setText(stAddress);
			}
		});
		cbAddress.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		cbAddress.setModel(new DefaultComboBoxModel(new String[] {"서울", "인천", "대전", "부산", "대구", "울산", "광주", "경기도", "강원도", "충청도", "경상도", "전라도"}));
		cbAddress.setBounds(206, 403, 64, 31);
		panel.add(cbAddress);
		
		tfAddress = new JTextField();
		tfAddress.setEnabled(false);
		tfAddress.setBounds(279, 403, 156, 31);
		panel.add(tfAddress);
		tfAddress.setColumns(10);
		
		JButton btnOk = new JButton("JOIN ");
	
		btnOk.setFont(new Font("Arial", Font.BOLD, 15));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int i_chk = insertFieldCheck();
				if(i_chk == 0){
				insertAction();
//				InsertScore();
//				
//				//----새창 띄우기----------
//				FrameLogin frame = new FrameLogin();
//				frame.setVisible(true);
//				//----갖고있던 창 죽이기----
//				FrameSignUp.this.dispose();
//				//----------------------------
				}else {
					//*********************************
					Message();
					//********************************
					
					ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "Data를 입력하세요", "", JOptionPane.OK_OPTION, icon);
				}
			}
			
		});
		btnOk.setBounds(483, 598, 108, 55);
		panel.add(btnOk);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
		lblEmail.setBounds(135, 234, 64, 23);
		panel.add(lblEmail);
		
		JComboBox cbEmail = new JComboBox();
		cbEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stEmail = cbEmail.getSelectedItem().toString();
				if (stEmail  == "직접입력") {
					tfEmailCheck.setEditable(true);
					tfEmailCheck.setText(tfEmail.getText());
				}
				else if (stEmail == "@naver.com" || stEmail == "@gmail.com"|| stEmail == "@icloud.com" ||stEmail ==  "@hanmail.net" || stEmail == "@kakao.com") {
					tfEmailCheck.setText(tfEmail.getText() + cbEmail.getSelectedItem().toString());
					
				}
				}
					
//			콤보박스가 직접입력이면 이멜 아이디 입력부분이 초기화하고 tfEmailCheck 부분이 활성화됨		
		});
		cbEmail.setFont(new Font("맑은 고딕", Font.PLAIN, 12)); 
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"@naver.com", "@gmail.com", "@icloud.com", "@hanmail.net", "@kakao.com", "직접입력"}));
		cbEmail.setBounds(335, 230, 100, 31);
		panel.add(cbEmail);
		
		tfEmail = new JTextField();
		tfEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String stEmail = cbEmail.getSelectedItem().toString();
					tfEmailCheck.setText(tfEmail.getText().trim() + stEmail);
					
				
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(206, 230, 115, 31);
		panel.add(tfEmail);
		
		JButton btnEmailCheck = new JButton("중복확인");
		btnEmailCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emailCheck();
			}
		});
		btnEmailCheck.setForeground(Color.DARK_GRAY);
		btnEmailCheck.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnEmailCheck.setBounds(447, 230, 81, 31);
		panel.add(btnEmailCheck);
		
		pfPw = new JPasswordField();
		pfPw.setBounds(206, 293, 229, 31);
		panel.add(pfPw);
		
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
		pfPwCheck.setBounds(206, 344, 229, 31);
		panel.add(pfPwCheck);
		
		tfFilePath = new JTextField();
		tfFilePath.setBounds(64, 519, 130, 26);
		panel.add(tfFilePath);
		tfFilePath.setColumns(10);
		
		JButton btnFilePath = new JButton("사진 업로드");
		btnFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilePath();
			}
		});
		btnFilePath.setBounds(74, 554, 117, 29);
		panel.add(btnFilePath);
		panel.add(getLblImage());
		
		JLabel lblProfilePictureProfile = new JLabel("PROFILE IMG");
		lblProfilePictureProfile.setFont(new Font("Arial", Font.BOLD, 18));
		lblProfilePictureProfile.setBounds(76, 468, 123, 23);
		panel.add(lblProfilePictureProfile);
		panel.add(getLblNewLabel());
		panel.add(getLblPwCheck());
		panel.add(getTfEmailCheck());
		
		JLabel lblNewLabel_1 = new JLabel("copyright © 2조 : 고종찬, 김보람, 박지은, 차종한");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(7, 0, 273, 28);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
	
	//join 버튼 눌렀을때  
	public void insertAction() {
        PreparedStatement ps = null;
        
        try{
        	//암호체크	
        		char[] secret_pw = pfPw.getPassword();
        		char[] secret_pwCheck = pfPwCheck.getPassword();
        		String secret_pw_String = new String(secret_pw);
        		String secret_pwCheck_String = new String(secret_pwCheck);
				
				if (secret_pw_String.equals(secret_pwCheck_String)) {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
					@SuppressWarnings("unused")
					Statement stmt_mysql = conn_mysql.createStatement();
					
					String A = "insert into user (id, pw, name , address, email, img ";
					String B = ") values (?,?,?,?,?,?)";
					
					
					ps = conn_mysql.prepareStatement(A+B);
					ps.setString(1, tfId.getText().trim());
					ps.setString(2, String.valueOf(pfPwCheck.getPassword()));
					ps.setString(3, tfName.getText().trim());
					ps.setString(4, tfAddress.getText().trim());
					ps.setString(5, tfEmailCheck.getText().trim());
					
					//사진 넣기
					FileInputStream input = null;
					File file = new File(tfFilePath.getText());
					try {
						input = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ps.setBinaryStream(6, input);
					ps.executeUpdate();
					
					conn_mysql.close();
					Message();
					ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
					JOptionPane.showMessageDialog(null, tfName.getText()+" 님의 정보가 입력 되었습니다.!", "입력 완료!", JOptionPane.OK_OPTION, icon0);                
					
					InsertScore();
					
					//----새창 띄우기----------
					FrameLogin frame = new FrameLogin();
					frame.setVisible(true);
					//----갖고있던 창 죽이기----
					FrameSignUp.this.dispose();
					//----------------------------
					
					
				}
				else {
					Message();
					ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "암호가 같지 않습니다. 같은 암호를 입력하세요", "경고", JOptionPane.OK_OPTION, icon);
				}//else
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!",
                                         "Critical Error!", 
                                         JOptionPane.ERROR_MESSAGE);                    
            e.printStackTrace();
        }//catch
}//insertAction
	
	//************
	public void InsertScore() {
		 PreparedStatement ps = null;
	        try{
	        	//암호체크	
	        		char[] secret_pw = pfPw.getPassword();
	        		char[] secret_pwCheck = pfPwCheck.getPassword();
	        		String secret_pw_String = new String(secret_pw);
	        		String secret_pwCheck_String = new String(secret_pwCheck);
					
					if (secret_pw_String.equals(secret_pwCheck_String)) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
						@SuppressWarnings("unused")
						Statement stmt_mysql = conn_mysql.createStatement();
						
						String A = "insert into score (user_id, user_address ";
						String B = ") values (?,?)";
						
						ps = conn_mysql.prepareStatement(A+B);
						ps.setString(1, tfId.getText().trim());
						ps.setString(2, tfAddress.getText().trim());
						
						ps.executeUpdate();
						
						conn_mysql.close();     
					}
					else {
					}//else
	        } catch (Exception e){                 
	            e.printStackTrace();
	        }//catch
	}
	//**********
	
	

	
	
	
	//id 중복체크 -> btnIdcheck
	public void IdCheck() {
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            
	            ResultSet rs = stmt_mysql.executeQuery("SELECT count(id) from user where id = '"+ tfId.getText()+"'" );

	            	int id = 0;
	            while(rs.next()) {
	            	id = rs.getInt(1);
	            }
	                
	            if (id == 0 ) {
	            	Message();
	            	ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
					JOptionPane.showMessageDialog(null, "사용가능한 ID 입니다.", "입력 완료!", JOptionPane.OK_OPTION, icon0);  
					
				}
	            else {
	            	Message();
	            	ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "다른 ID를 입력하세요.", "경고", JOptionPane.OK_OPTION, icon);
	            	
	            }
	            
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }	
	}//idCheck
	
	//Email 중복체크 -> btnEmailcheck
	EmailValidator emailValidator = new EmailValidator();
	public void emailCheck() {
		if (emailValidator.validate(tfEmailCheck.getText().trim())) {
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery("SELECT count(email)  from user where email = '"+ tfEmailCheck.getText()+"'" );
				int id = 0;
				while(rs.next()) {
					id = rs.getInt(1);
				}
				
				if (id == 0 ) {
					Message();
					ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
					JOptionPane.showMessageDialog(null, "사용가능한 Email 입니다.", "입력 완료!", JOptionPane.OK_OPTION, icon0);  
					
				}
				else {
					Message();
					ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "다른 Email를 입력하세요.", "경고", JOptionPane.OK_OPTION, icon);
				}
				
				conn_mysql.close();
			}
			catch (Exception e){
				System.out.println(e);
			}	
		}
		else if (!emailValidator.validate(tfEmailCheck.getText().trim())) {
			Message();
			ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
			JOptionPane.showMessageDialog(null, "잘못된 Email 형식입니다.", "경고", JOptionPane.OK_OPTION, icon);
		}
		
		
		
		
	}//emailCheck
	
	
	//빈칸 있을때 
	private int insertFieldCheck(){
		int i = 0;
		if(tfId.getText().length() == 0){
			i++;
			tfId.requestFocus();
		}
		if(tfName.getText().length() == 0){
			i++;
			tfName.requestFocus();
			
		}
		if(tfEmailCheck.getText().length() == 0){
			i++;
			tfEmailCheck.requestFocus();
		}
		if(tfAddress.getText().length() == 0){
			i++;
			tfAddress.requestFocus();
		}
		if(tfFilePath.getText().length() == 0) {
			i++;
		}
	
				
		return i;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(206, 468, 235, 174);
		}
		return lblImage;
	}
	//파일 경로 가져오기
	private void FilePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP", "jpg","png","bmp");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			Message();
			ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.OK_OPTION, icon);
			return;
		}
		String filePath = chooser.getSelectedFile().getPath();
		tfFilePath.setText(filePath);
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.resize(200, 200);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
			lblNewLabel.setBounds(301, 6, 64, 63);
			lblNewLabel.setIcon(new ImageIcon(img_logo));
		}
		return lblNewLabel;
	}
	
	
	//******************************
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	private JLabel getLblPwCheck() {
		if (lblPwCheck == null) {
			lblPwCheck = new JLabel("");
			lblPwCheck.setBounds(205, 379, 227, 16);
		}
		return lblPwCheck;
	}
	private JTextField getTfEmailCheck() {
		if (tfEmailCheck == null) {
			tfEmailCheck = new JTextField();
			tfEmailCheck.setEditable(false);
			tfEmailCheck.setBounds(206, 262, 322, 31);
			tfEmailCheck.setColumns(10);
		}
		return tfEmailCheck;
	}
}