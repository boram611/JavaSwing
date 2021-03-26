package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

import com.javalec.game1.GameMain1;
import com.javalec.game1.PanelGame1;
import com.javalec.game2.PanelGame2;
import com.javalec.qa.PanelQA;
import com.javalec.share.ShareVar;
import com.javalec.user.FrameLogin;
import com.javalec.user.PanelProfile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameMain extends JFrame {

	private JPanel contentPane;
	
	private Image img_logo = new ImageIcon(FrameLogin.class.getResource("/res/Game.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_home = new ImageIcon(FrameLogin.class.getResource("/res/Home.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_gamerul = new ImageIcon(FrameLogin.class.getResource("/res/qa.png")).getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_game1 = new ImageIcon(FrameLogin.class.getResource("/res/Game1.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_game2 = new ImageIcon(FrameLogin.class.getResource("/res/Game2.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_profile = new ImageIcon(FrameLogin.class.getResource("/res/ID.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img_logout = new ImageIcon(FrameLogin.class.getResource("/res/Logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	
	private PanelHome panelHome;
	private PanelQA panelQA;
	private PanelGame1 panelGame1;
	private PanelGame2 panelGame2;
	private PanelProfile panelProfile;
	
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	/**
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMain frame = new FrameMain();
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
	public FrameMain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				checkSignOut();
			}
		});
		setBackground(new Color(47, 79, 79));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//------------ 창줄일때 사용 
		setUndecorated(true);
		setLocationRelativeTo(null);
		//------------------
		
		//----------------창바뀌기 선언-------
		panelHome = new PanelHome();
		panelHome.setBounds(0, 0, 634, 457);
		panelQA = new PanelQA();
		panelGame1 = new PanelGame1();
		panelGame2 = new PanelGame2();
		panelProfile = new PanelProfile();
		//---------------
		
		// 창위치 바꾸기
		setBounds(500, 300, 916, 508);
		//-------------------
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBackground(new Color(0, 51, 51));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(0, 128, 128));
		panelMenu.setBounds(0, 0, 249, 506);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogo.setBounds(10, 11, 229, 142);
		lblIconLogo.setIcon(new ImageIcon(img_logo));
		panelMenu.add(lblIconLogo);
		
		JPanel paneHome = new JPanel();
		paneHome.addMouseListener(new PanelButtonMouseAdapter(paneHome) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelHome);
				RankCheckGame1();
				RankCheckGame2();
				RankCheckGameTotal();
			}
		});
		paneHome.setBackground(new Color(47, 79, 79));
		paneHome.setForeground(new Color(230, 230, 250));
		paneHome.setBounds(-1, 165, 249, 40);
		panelMenu.add(paneHome);
		paneHome.setLayout(null);
		
		JLabel lblHome = new JLabel("HOME");
		lblHome.setForeground(Color.WHITE);
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblHome.setFont(new Font("Arial", Font.BOLD, 15));
		lblHome.setBounds(87, 12, 151, 18);
		paneHome.add(lblHome);
		
		JLabel lblIconHome = new JLabel("");
		lblIconHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHome.setBounds(20, 0, 40, 40);
		lblIconHome.setIcon(new ImageIcon(img_home));
		paneHome.add(lblIconHome);
		
		JPanel paneGameRul = new JPanel();
		paneGameRul.addMouseListener(new PanelButtonMouseAdapter(paneGameRul) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelQA);
			}
		});
		paneGameRul.setBackground(new Color(47, 79, 79));
		paneGameRul.setForeground(new Color(230, 230, 250));
		paneGameRul.setBounds(-1, 205, 249, 40);
		panelMenu.add(paneGameRul);
		paneGameRul.setLayout(null);
		
		JLabel lblGameMenual = new JLabel("Q & A");
		lblGameMenual.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameMenual.setForeground(Color.WHITE);
		lblGameMenual.setFont(new Font("Arial", Font.BOLD, 15));
		lblGameMenual.setBounds(87, 12, 151, 18);
		paneGameRul.add(lblGameMenual);
		
		JLabel lblIconGameRul = new JLabel("");
		lblIconGameRul.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconGameRul.setBounds(20, 0, 40, 40);
		lblIconGameRul.setIcon(new ImageIcon(img_gamerul));
		paneGameRul.add(lblIconGameRul);
		
		
		JPanel paneGame1 = new JPanel();
		paneGame1.addMouseListener(new PanelButtonMouseAdapter(paneGame1) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelGame1);
			}
		});
		paneGame1.setBackground(new Color(47, 79, 79));
		paneGame1.setForeground(new Color(230, 230, 250));
		paneGame1.setBounds(-1, 245, 249, 40);
		panelMenu.add(paneGame1);
		paneGame1.setLayout(null);
		
		JLabel lblGame_1 = new JLabel("돼지력 테스트");
		lblGame_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGame_1.setForeground(Color.WHITE);
		lblGame_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblGame_1.setBounds(87, 12, 151, 18);
		paneGame1.add(lblGame_1);
		
		JLabel lblIconGame1 = new JLabel("");
		lblIconGame1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconGame1.setBounds(20, 0, 40, 40);
		lblIconGame1.setIcon(new ImageIcon(img_game1));
		paneGame1.add(lblIconGame1);
		
		JPanel paneGame2 = new JPanel();
		paneGame2.addMouseListener(new PanelButtonMouseAdapter(paneGame2) {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				menuClicked(panelGame2);
			}
		});
		paneGame2.setBackground(new Color(47, 79, 79));
		paneGame2.setForeground(new Color(230, 230, 250));
		paneGame2.setBounds(-1, 285, 249, 40);
		panelMenu.add(paneGame2);
		paneGame2.setLayout(null);
		
		JLabel lblGame = new JLabel("인물/사물 사진 게임");
		lblGame.setHorizontalAlignment(SwingConstants.LEFT);
		lblGame.setForeground(Color.WHITE);
		lblGame.setFont(new Font("Arial", Font.BOLD, 15));
		lblGame.setBounds(87, 12, 151, 18);
		paneGame2.add(lblGame);
		
		JLabel lblIconGame2 = new JLabel("");
		lblIconGame2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconGame2.setBounds(20, 0, 40, 40);
		lblIconGame2.setIcon(new ImageIcon(img_game2));
		paneGame2.add(lblIconGame2);
		
		JPanel paneProfile = new JPanel();
		paneProfile.addMouseListener(new PanelButtonMouseAdapter(paneProfile) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(panelProfile);
			}
		});
		paneProfile.setBackground(new Color(47, 79, 79));
		paneProfile.setForeground(new Color(230, 230, 250));
		paneProfile.setBounds(-1, 325, 249, 40);
		panelMenu.add(paneProfile);
		paneProfile.setLayout(null);
		
		JLabel lblProfile = new JLabel("PROFILE");
		lblProfile.setHorizontalAlignment(SwingConstants.LEFT);
		lblProfile.setForeground(Color.WHITE);
		lblProfile.setFont(new Font("Arial", Font.BOLD, 15));
		lblProfile.setBounds(87, 12, 151, 18);
		paneProfile.add(lblProfile);
		
		JLabel lblIconProfile = new JLabel("");
		lblIconProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconProfile.setBounds(20, 0, 40, 40);
		lblIconProfile.setIcon(new ImageIcon(img_profile));
		paneProfile.add(lblIconProfile);
		
		JPanel paneLogout = new JPanel();
		paneLogout.addMouseListener(new PanelButtonMouseAdapter(paneLogout) {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message();
				ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/e.png"));
				if(	
				JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
					FrameLogin frameLogin = new FrameLogin();
					frameLogin.setVisible(true);
					FrameMain.this.dispose();
				}
			}
		});
		paneLogout.setBackground(new Color(47, 79, 79));
		paneLogout.setForeground(new Color(230, 230, 250));
		paneLogout.setBounds(1, 467, 249, 40);
		panelMenu.add(paneLogout);
		paneLogout.setLayout(null);
		
		JLabel lblLogout = new JLabel("LOGOUT");
		lblLogout.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Arial", Font.BOLD, 15));
		lblLogout.setBounds(87, 12, 151, 18);
		paneLogout.add(lblLogout);
		
		JLabel lblIconLogout = new JLabel("");
		lblIconLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconLogout.setBounds(20, 0, 40, 40);
		lblIconLogout.setIcon(new ImageIcon(img_logout));
		paneLogout.add(lblIconLogout);
		
		JLabel lblExit = new JLabel("X");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setForeground(Color.WHITE);
		lblExit.setFont(new Font("Gill Sans MT", Font.BOLD, 17));
		lblExit.setBounds(881, 0, 35, 35);
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Message();
				ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/c.png"));
				if(
				JOptionPane.showConfirmDialog(null, "프로그램을 종료 하시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
					FrameMain.this.dispose();
				}	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.WHITE);
			}
		});
		contentPane.add(lblExit);
		
		JPanel paneMainContent = new JPanel();
		paneMainContent.setBounds(266, 31, 634, 457);
		contentPane.add(paneMainContent);
		paneMainContent.setLayout(null);
		
		paneMainContent.add(panelHome);
		paneMainContent.add(panelQA);
		paneMainContent.add(panelGame1);
		paneMainContent.add(panelGame2);
		paneMainContent.add(panelProfile);
		
		menuClicked(panelHome);
		
		JLabel lblNewLabel = new JLabel("copyright © 2조 : 고종찬, 김보람, 박지은, 차종한");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(261, 0, 273, 28);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}
	
	public void toLogin() {
		FrameLogin frameLogin = new FrameLogin();
		frameLogin.setVisible(true);
		FrameMain.this.dispose();
	}
	//-----------메뉴 선택시 창 변환----------------------
	public void menuClicked(JPanel panel) {
		panelHome.setVisible(false);
		panelQA.setVisible(false);
		panelGame1.setVisible(false);
		panelGame2.setVisible(false);
		panelProfile.setVisible(false);
		
		panel.setVisible(true);
	}
	
	
	
	//------------메뉴 선택시 색 변환-------------------
	private class PanelButtonMouseAdapter extends MouseAdapter{
		
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
			this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
			
		}
		@Override
		public void mouseExited(MouseEvent e){
			panel.setBackground(new Color(47, 79, 79));
			
		}
		@Override
		public void mousePressed(MouseEvent e){
			panel.setBackground(new Color(60, 179, 113));
			
		}
		@Override
		public void mouseReleased(MouseEvent e){
			panel.setBackground(new Color(112, 128, 144));
			
		}
	}
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	
	
//	--------------------------------------------------------------------------------------------
	
	public void RankCheckGameTotal () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, user_address from score order by (pig_score+ people_score) desc limit 1" );
			String id = "";
			String address ="";
			while(rs.next()) {
				id = rs.getString(1);
				address = rs.getString(2);
			}
			PanelHome.lblTotalRankNo1.setText(address + " 지역의  " + id + "님");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//RankCheckGame 전체 합산
	
	public void RankCheckGame1 () {
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		Statement stmt_mysql = conn_mysql.createStatement();
		ResultSet rs = stmt_mysql.executeQuery("select user_id , user_address from score  order by  pig_score desc  limit 1" );
		String id = "";
		String address ="";
		while(rs.next()) {
			id = rs.getString(1);
			address = rs.getString(2);
		}
		PanelHome.lblGame1RankNo1.setText(address +" 지역의  " + id + "님");
		
		conn_mysql.close();
	}
	catch (Exception e){
		System.out.println(e);
	}	
}//RankCheckGame 돼지게임
	
	public void RankCheckGame2 () {
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	            Statement stmt_mysql = conn_mysql.createStatement();
	            ResultSet rs = stmt_mysql.executeQuery("select user_id ,user_address from score  order by  people_score desc  limit 1" );
	            	String id = "";
	            	String address ="";
	            while(rs.next()) {
	            	id = rs.getString(1);
	            	address = rs.getString(2);
	            }
	           PanelHome.lblGame2RankNo1.setText(address +" 지역의  " + id + "님");
	            
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }	
	}//RankCheckGame2 인물게임
	
	
	private void checkSignOut() {
		switch (panelProfile.checkSignOut) {
		case 0:
			
			break;

		default:
			toLogin();
			break;
		}
	}
	
	
}
