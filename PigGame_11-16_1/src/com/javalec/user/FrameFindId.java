package com.javalec.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.javalec.share.ShareVar;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FrameFindId extends JFrame {
	
	
	
	private JPanel contentPane;
	private JTextField tfEmail;
	
	//DB 선언
			private final String url_mysql =ShareVar.url_mysql;
			private final String id_mysql = ShareVar.id_mysql;
			private final String pw_mysql = ShareVar.pw_mysql;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameFindId frame = new FrameFindId();
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
	public FrameFindId() {
		setTitle("Id찾기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 324);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(19, 26, 515, 249);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("ID 찾기");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(208, 32, 95, 38);
		panel.add(lblTitle);
		lblTitle.setToolTipText("");
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("회원가입 시 입력한 이메일을 입력해 주세요.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel.setBounds(90, 92, 331, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(78, 132, 50, 15);
		panel.add(lblNewLabel_1);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(132, 123, 300, 32);
		panel.add(tfEmail);
		tfEmail.setColumns(10);
		
		JComboBox cbEmail = new JComboBox();
		cbEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stEmail = cbEmail.getSelectedItem().toString();
				tfEmail.setText(tfEmail.getText().trim() + stEmail);
			}
		});
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"@naver.com", "@gamil.com", "@icloud.com", "@hanmail.net", "@kakao.com"}));
		cbEmail.setBounds(309, 154, 141, 32);
		panel.add(cbEmail);
		
		JButton btnFindId = new JButton("찾기");
		btnFindId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findId();
			}
		});
		btnFindId.setBounds(191, 186, 95, 32);
		panel.add(btnFindId);
		
		JButton btnGoLogin = new JButton("로그인하러 가기");
		btnGoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//----새창 띄우기----------
				FrameLogin frameMain = new FrameLogin();
				frameMain.setVisible(true);
				//----갖고있던 창 죽이기----
				FrameFindId.this.dispose();
			} //----------------------------
			
		});
		btnGoLogin.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btnGoLogin.setBounds(351, 207, 152, 32);
		panel.add(btnGoLogin);
		
		JLabel lblNewLabel_2 = new JLabel("copyright © 2조 : 고종찬, 김보람, 박지은, 차종한");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_2.setBounds(3, -3, 273, 28);
		contentPane.add(lblNewLabel_2);
	}
	  
	//id찾기 ->  	btnFindId 에서 동작
	
	private void findId() {
		String id ;
		
		String where1 = "select id from user ";
		String where2 = "where email = '" + tfEmail.getText() + "'";
		
		try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	        		  
	          if(rs.next()==true) {
	        	  Message();
	        	  id = rs.getString(1);
	        	  ImageIcon icon0 = new ImageIcon(FrameFindPw.class.getResource("/res/f.png"));
					JOptionPane.showMessageDialog(null,"가입한 아이디는 " + id + " 입니다.", "입력 완료!", JOptionPane.OK_OPTION, icon0); 
	        	 
	          }
	          conn_mysql.close();
	         
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
		
	}
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
}
