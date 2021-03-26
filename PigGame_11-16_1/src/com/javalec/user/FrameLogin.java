package com.javalec.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

import com.javalec.admin.Main;
import com.javalec.admin.admin_Qa;
import com.javalec.base.FrameMain;
import com.javalec.game1.GameMain1;
import com.javalec.share.ShareVar;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrameLogin extends JFrame {
	
	private Image img_logo = new ImageIcon(FrameLogin.class.getResource("/res/Game.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon(FrameLogin.class.getResource("/res/ID.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon(FrameLogin.class.getResource("/res/Pass.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_log_in = new ImageIcon(FrameLogin.class.getResource("/res/Login.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	

	private JPanel contentPane;
	private JTextField tfId;
	private JPasswordField pfPw;
	private JLabel lblLoginMessage = new JLabel("");
	private JPanel pnlBtnSignUp;
	private JLabel lblSignUp;
	
	private int adminCount = 0;
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
					FrameLogin frame = new FrameLogin();
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
	public FrameLogin() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(170, 133, 250, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfId = new JTextField();
		tfId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(tfId.getText().equals("UserName")) {
					tfId.setText("");
				}
				else {
					tfId.selectAll();
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(tfId.getText().equals(""))
				tfId.setText("UserName");
			}
		});
		tfId.setText("UserName");
		tfId.setBackground(Color.WHITE);
		tfId.setToolTipText("");
		
		tfId.setBorder(null);
		tfId.setFont(new Font("Arial", Font.PLAIN, 15));
		tfId.setBounds(10, 11, 170, 20);
		panel.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblIconUsername = new JLabel("");
		lblIconUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUsername.setBounds(210, 0, 40, 40);
		lblIconUsername.setIcon(new ImageIcon(img_username));
		panel.add(lblIconUsername);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(170, 184, 250, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		pfPw = new JPasswordField();
		pfPw.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(pfPw.getText().equals("Password")) {
					pfPw.setEchoChar('●');
					pfPw.setText("");
				}
				else {
					pfPw.selectAll();
				}
			}
			
			@Override
			public void focusLost(FocusEvent arg0) {
				if(pfPw.getText().equals("")) {
					pfPw.setText("Password");
					pfPw.setEchoChar((char)0);
				}
			}
		});
		pfPw.setText("Password");
		pfPw.setEchoChar((char)0);
		pfPw.setBorder(null);
		pfPw.setBounds(10, 11, 170, 20);
		panel_1.add(pfPw);
		JLabel lblIconPassword = new JLabel("");
		lblIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPassword.setBounds(210, 0, 40, 40);
		lblIconPassword.setIcon(new ImageIcon(img_password));
		panel_1.add(lblIconPassword);
		
		JPanel pnlBtnLogin = new JPanel();
		pnlBtnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int checkLogin = 1;
				int checkAdmin = 1;
				
				switch (adminCount) {
				case 5:
					checkLogin = DBLogin(tfId.getText(), String.copyValueOf(pfPw.getPassword()), "admin");
					checkAdmin = 0;
					adminCount = 0;
					break;

				default:
					checkLogin = DBLogin(tfId.getText(), String.copyValueOf(pfPw.getPassword()), "user");
					break;
				}
				
				switch (checkLogin) {
				case 0: // 로그인 성공한 경우
					
					ShareVar.user_id = tfId.getText();
					Message();
					ImageIcon icon0 = new ImageIcon(GameMain1.class.getResource("/res/f.png"));
					JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다.", " ", JOptionPane.OK_OPTION, icon0);
					
					switch (checkAdmin) { // 유저 or 관리자 로그인 체크
					case 0: // 관리자 로그인
						//----새창 띄우기----------
						Main admin_Main = new Main();
						admin_Main.setVisible(true);
						//----갖고있던 창 죽이기----
						FrameLogin.this.dispose();
						
						break;
						
					case 1: // 유저 로그인
						//----새창 띄우기----------
						FrameMain frameMain = new FrameMain();
						frameMain.setVisible(true);
						//----갖고있던 창 죽이기----
						FrameLogin.this.dispose();
						break;
					}
					break; 
				case 1: // 로그인 실패한 경우
					
					tfId.setText("");
					pfPw.setText("");
					Message();
					ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/c.png"));
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해 주세요!", " ", JOptionPane.OK_OPTION, icon);
					tfId.requestFocus();
					break;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(30, 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(47, 79, 79));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(60, 80, 80));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				pnlBtnLogin.setBackground(new Color(30, 60, 60));
			}
		});
		pnlBtnLogin.setBackground(new Color(47, 79, 79));
		pnlBtnLogin.setBounds(172, 246, 250, 50);
		contentPane.add(pnlBtnLogin);
		pnlBtnLogin.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial", Font.BOLD, 15));
		lblLogin.setBounds(93, 13, 82, 26);
		pnlBtnLogin.add(lblLogin);
		
		JLabel lblIconLogin = new JLabel("");
		lblIconLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogin.setBounds(49, 1, 50, 50);
		lblIconLogin.setIcon(new ImageIcon(img_log_in));
		pnlBtnLogin.add(lblIconLogin);
		
		// x버튼 클릭시 나오는 메세지 출력 
		JLabel lblx = new JLabel("X");
		lblx.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Message();
				ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/c.png"));
				if(
				JOptionPane.showConfirmDialog(null, "프로그램을 종료 하시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
					FrameLogin.this.dispose();
				}	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblx.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblx.setForeground(Color.WHITE);
			}
		});
		lblx.setForeground(Color.WHITE);
		lblx.setFont(new Font("Gill Sans MT", Font.BOLD, 17));
		lblx.setHorizontalAlignment(SwingConstants.CENTER);
		lblx.setBounds(565, 0, 35, 35);
		contentPane.add(lblx);
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					adminCount++;
					if (adminCount == 5) {
						JOptionPane.showMessageDialog(null, "관리자 로그인 모드가 활성화되었습니다! 관리자 ID와 PW를 입력해주세요");
					}

			}
		});
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIconLogo.setBounds(164, 15, 256, 112);
		contentPane.add(lblIconLogo);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		
		lblLoginMessage.setForeground(new Color(139, 0, 0));
		lblLoginMessage.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMessage.setBounds(169, 282, 250, 18);
		contentPane.add(lblLoginMessage);
		contentPane.add(getPnlBtnSignUp());
		
		JLabel lblFindId = new JLabel("아이디 찾기");
		lblFindId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFindId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1){
					//----새창 띄우기----------
					FrameFindId frameMain = new FrameFindId();
					frameMain.setVisible(true);
					//----갖고있던 창 죽이기----
					FrameLogin.this.dispose();
					//----------------------------
				}
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFindId.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFindId.setForeground(Color.WHITE);
			}
		});
		lblFindId.setForeground(Color.WHITE);
		lblFindId.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblFindId.setBounds(193, 364, 85, 15);
		contentPane.add(lblFindId);
		
		JLabel lblFindPw = new JLabel("비밀번호 찾기");
		lblFindPw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1){
					//----새창 띄우기----------
					FrameFindPw frameMain = new FrameFindPw();
					frameMain.setVisible(true);
					//----갖고있던 창 죽이기----
					FrameLogin.this.dispose();
				}		//----------------------------
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFindPw.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblFindPw.setForeground(Color.WHITE);
			}
		});
		lblFindPw.setForeground(Color.WHITE);
		lblFindPw.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblFindPw.setBounds(306, 364, 96, 15);
		contentPane.add(lblFindPw);
		
		JLabel lblNewLabel = new JLabel("copyright © 2조 : 고종찬, 김보람, 박지은, 차종한");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(8, 0, 273, 28);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}
	private JPanel getPnlBtnSignUp() {
		if (pnlBtnSignUp == null) {
			pnlBtnSignUp = new JPanel();
			pnlBtnSignUp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				
					//----새창 띄우기----------
					FrameSignUp frame = new FrameSignUp();
					frame.setVisible(true);
					//----갖고있던 창 죽이기----
					FrameLogin.this.dispose();
					//----------------------------
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					pnlBtnSignUp.setBackground(new Color(30, 60, 60));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pnlBtnSignUp.setBackground(new Color(47, 79, 79));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pnlBtnSignUp.setBackground(new Color(60, 80, 80));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pnlBtnSignUp.setBackground(new Color(30, 60, 60));
				}
			});
			pnlBtnSignUp.setLayout(null);
			pnlBtnSignUp.setBackground(new Color(47, 79, 79));
			pnlBtnSignUp.setBounds(171, 304, 250, 50);
			pnlBtnSignUp.add(getLblSignUp());
		}
		return pnlBtnSignUp;
	}
	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new JLabel("SIGN UP");
			lblSignUp.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
			lblSignUp.setForeground(Color.WHITE);
			lblSignUp.setFont(new Font("Arial", Font.BOLD, 15));
			lblSignUp.setBounds(84, 13, 82, 26);
		}
		return lblSignUp;
	}
	
	private int DBLogin(String id, String pw, String tableName) {
		
		String where1 = "select * from " + tableName;
		String where2 = " where id = '" + id + "'";
		int result = 1;
		try{
	          Class.forName("com.mysql.cj.jdbc.Driver");
	          Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	          Statement stmt_mysql = conn_mysql.createStatement();
	
	          ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
	          if(rs.next() == false || id.isEmpty() == true) {
	        	  result = 1;
	          }else {
	        	  where1 = "select * from (select * from " + tableName + " where id = '" + id + "') as pw";
	        	  rs = stmt_mysql.executeQuery(where1);
	        	  while (rs.next() == true) {
	        		  if (rs.getString(2).equals(pw)) {
						result = 0;
					} else {
						result = 1;
					}
				}
	          }
	          conn_mysql.close();
	         
	      }
	      catch (Exception e){
	          e.printStackTrace();
	      }
		
		return result;
	}
	
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	
	
	
}
