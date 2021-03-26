package com.javalec.game2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import org.omg.PortableInterceptor.TRANSPORT_RETRY;

import com.javalec.base.FrameMain;
import com.javalec.game2.DbAction;
import com.javalec.share.ShareVar;
import com.javalec.user.FrameSignUp;
import com.mysql.cj.util.TestUtils;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameMain2 extends JFrame {

	private JPanel contentPane;
	private JLabel lblExit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblImage;
	private JTextField tfAnswerSheet;
	private static JLabel timerLabel;
	private JPanel panel_1;
	private JLabel lblScore;
	int score = 0;
	
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3;
	
	
	DbAction dbAction = new DbAction();
	ArrayList<Bean> beanList = dbAction.loadBlob1();
	int i = 0;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMain2 frame = new GameMain2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
//		Timer();
	}

	/**
	 * Create the frame.
	 */
	public GameMain2() {
		
		Timer();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {// 초기 프레임 시작할때 사진 불러오기
				loadBlob();
//				Timer();
				
			}
		});
		setForeground(Color.WHITE);
		//***********************
		setUndecorated(true);
		setLocationRelativeTo(null);
		//************************
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 687);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getLblExit());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblImage());
		contentPane.add(getTfAnswerSheet());
		contentPane.add(getPanel_1());
		contentPane.add(getLblScore());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_3());
	}

	private JLabel getLblExit() {
		if (lblExit == null) {
			lblExit = new JLabel("X");
			lblExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Message();
					ImageIcon icon = new ImageIcon(GameMain2.class.getResource("/res/a.png"));
					if(
					JOptionPane.showConfirmDialog(null, "인물 사진 게임을 종료 하시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
						GameMain2.this.dispose();
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
			lblExit.setHorizontalAlignment(SwingConstants.CENTER);
			lblExit.setForeground(Color.WHITE);
			lblExit.setFont(new Font("Dialog", Font.BOLD, 17));
			lblExit.setBounds(742, 6, 35, 35);
		}
		return lblExit;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("인물 & 사물 퀴즈");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(214, 18, 373, 76);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("사진을 보고 어떤 인물/사물 인지 맞춰보세요!");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
			lblNewLabel_1.setBounds(183, 84, 421, 76);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(153, 160, 492, 390);
		}
		return lblImage;
	}
	private JTextField getTfAnswerSheet() {
		if (tfAnswerSheet == null) {
			tfAnswerSheet = new JTextField();
			tfAnswerSheet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkAnswer();
				}
			});
			tfAnswerSheet.setHorizontalAlignment(SwingConstants.CENTER);
			tfAnswerSheet.setBounds(273, 556, 252, 63);
			tfAnswerSheet.setColumns(10);
		}
		return tfAnswerSheet;
	}
	
	// Db에서 사진 랜덤으로 가져오기
	private void loadBlob() {
//		Random random = new Random();
//		int i = random.nextInt(4);
		
		ShareVar.answer = beanList.get(i).getName(); // ShareVar에 정답 저장
		
		
		// Image처리
        // File name이 틀려야 즉각 보여주기가 가능하여   
        // ShareVar에서 int값으로 정의하여 계속 증가하게 하여 file name으로 사용후 삭제
		String filePath = Integer.toString(beanList.get(i).getFileName());
		
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
	
	
	// 입력 필드에서 Enter 칠 때 정답 확인해주기
		private int checkAnswer() {
			
			
		
			if (tfAnswerSheet.getText().equals(ShareVar.answer)) { // 정답일 때
				score++;
				lblScore.setText(Integer.toString(score));
		         Message();
	          	ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
				JOptionPane.showMessageDialog(null, "정답", "입력 완료!", JOptionPane.OK_OPTION, icon0);  

		    // 정답인 경우 텍스트필드 초기화, 사진 새로 불러오기
		    tfAnswerSheet.setText("");
		    i++;
			loadBlob(); 
				
			}else {	// 오답일 때
				
		          Message();
		          ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "땡!  " + ShareVar.answer + " 입니다." , "경고", JOptionPane.OK_OPTION, icon);
		    
		    tfAnswerSheet.setText("");
		    i++;
		    loadBlob(); 
		    
			}
			return 0;
		}
	
	
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	
	
	private JLabel getTimerLabel() {
		if (timerLabel == null) {
			timerLabel = new JLabel(" ");
			timerLabel.setBackground(new Color(102, 0, 0));
			timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			timerLabel.setForeground(new Color(153, 0, 0));
			timerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		}
		return timerLabel;
	}
	
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("0");
			lblScore.setBackground(new Color(0, 0, 102));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setForeground(Color.YELLOW);
			lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 35));
			lblScore.setBounds(634, 28, 106, 42);
		}
		return lblScore;
	}
	private  void Timer() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
        timerLabel = new JLabel("0");
        timerLabel.setFont(new Font("Arial",Font.BOLD,35));
        timerLabel.setForeground(Color.RED);
        
        panel.add(timerLabel);
     
        panel.setSize(300,150);
        panel.setVisible(true);
        
        TimerThread timerThread = new TimerThread(timerLabel);
        timerThread.start();
        
        new Thread() {  
            public void run() {  
                for (int i = 0; i < 1; i++) {  
                    try {  
                        Thread.sleep(16000);  
                    } catch (InterruptedException e) {  
                    }  
                    GameMain2.this.dispose(); 
                    PeopleScoreAction();
                    RankPeople1st();
                    RankPeople2nd();
                    RankPeople3rd();
                    RankPeople4th();
                    RankPeople5th();
                    UIManager.put("OptionPane.background", Color.decode("#004e4c"));
            	    UIManager.put("OptionPane.messageForeground", Color.white);
            	    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
            	    UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
            	    ImageIcon icon = new ImageIcon(GameMain2.class.getResource("/res/a.png"));
            	    
            	    JOptionPane.showMessageDialog(null, "게임이 종료됩니다.", " ",JOptionPane.OK_OPTION, icon);
                }  
            }  
        }.start();  
        
        
        
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setForeground(Color.WHITE);
			panel_1.setBackground(new Color(0, 102, 102));
			panel_1.setBounds(13, 27, 133, 90);
			panel_1.add(getTimerLabel());
		}
		return panel_1;
	}

    private void PeopleScoreAction() {
		String user_Id = ShareVar.user_id;
		int score = Integer.parseInt(lblScore.getText());
		
		DbAction dbAction = new DbAction(score , user_Id);
		dbAction.peopleScoreAction();
		
//		if(msg == true) {
//			JOptionPane.showMessageDialog(null, "수정 완료", "성공!", JOptionPane.OK_OPTION);
//		}else {
//			JOptionPane.showMessageDialog(null, "수정 실패", "경고", JOptionPane.OK_OPTION);
//		}
	}

    public void RankPeople1st () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, people_score from score  order by  people_score desc  limit 1" );
			String id = "";
			String people_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				people_score = rs.getString(2);
			}
			PanelGame2.lbl1st.setText(id + "님\t\t\t\t" + people_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig1st
	
	public void RankPeople2nd () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, people_score from score  order by  people_score desc  limit 1,1 " );
			String id = "";
			String people_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				people_score = rs.getString(2);
			}
			PanelGame2.lbl2nd.setText(id + "님\t\t\t\t" + people_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig2nd
	
	public void RankPeople3rd () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, people_score from score  order by  people_score desc  limit 2 ,1 " );
			String id = "";
			String people_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				people_score = rs.getString(2);
			}
			PanelGame2.lbl3rd.setText(id + "님\t\t\t\t" + people_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig3rd
	
	public void RankPeople4th () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, people_score from score  order by  people_score desc  limit 3,1 " );
			String id = "";
			String people_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				people_score = rs.getString(2);
			}
			PanelGame2.lbl4th.setText(id + "님\t\t\t\t" + people_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig4th
	
	public void RankPeople5th () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, people_score from score  order by  people_score desc  limit 4,1 " );
			String id = "";
			String people_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				people_score = rs.getString(2);
			}
			PanelGame2.lbl5th.setText(id + "님\t\t\t\t" + people_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig5th
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("점수");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel_2.setBounds(599, 25, 61, 35);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("타이머");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel_2_1.setBounds(105, 25, 61, 35);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("copyright © 2조 : 고종찬, 김보람, 박지은, 차종한");
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			lblNewLabel_3.setBounds(498, 648, 273, 28);
		}
		return lblNewLabel_3;
	}
}
