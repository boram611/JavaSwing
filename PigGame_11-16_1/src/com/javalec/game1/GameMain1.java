package com.javalec.game1;

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
import com.javalec.game1.DbAction;
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

public class GameMain1 extends JFrame {

	private JPanel contentPane;
	private JLabel lblExit;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblImage;
	private JTextField tfAnswerSheet;
	private static JLabel timerLabel;
	private JPanel panel_1;
	int score = 0;
	
	
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JLabel lblScore;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3;
	
	com.javalec.game1.DbAction dbAction = new com.javalec.game1.DbAction();
	ArrayList<Bean> beanlist = dbAction.loadBlob1();
	int i = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameMain1 frame = new GameMain1();
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
	public GameMain1() {
		
		Timer();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {// ?????? ????????? ???????????? ?????? ????????????
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
		setBounds(100, 100, 649, 492);
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
		contentPane.add(getLblScore_1());
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
					ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/a.png"));
					if(
					JOptionPane.showConfirmDialog(null, "????????? ????????? ????????? ?????? ???????????????????", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
						GameMain1.this.dispose();
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
			lblExit.setBounds(612, 0, 35, 35);
		}
		return lblExit;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("????????? ????????? ??????");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(169, 20, 373, 76);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("????????? ?????? ?????? ???????????? ???????????????!");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
			lblNewLabel_1.setBounds(158, 77, 373, 76);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(272, 192, 125, 132);
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
			tfAnswerSheet.setBounds(207, 382, 252, 63);
			tfAnswerSheet.setColumns(10);
		}
		return tfAnswerSheet;
	}
	
	// Db?????? ?????? ???????????? ????????????
	private void loadBlob() {
//		Random random = new Random();
//		int i = random.nextInt(4);
//		com.javalec.game1.DbAction dbAction = new com.javalec.game1.DbAction();
//		beanlist = dbAction.loadBlob1();
		
		ShareVar.answer = beanlist.get(i).getName(); // ShareVar??? ?????? ??????
		
		
		// Image??????
        // File name??? ????????? ?????? ??????????????? ????????????
        // ShareVar?????? int????????? ???????????? ?????? ???????????? ?????? file name?????? ????????? ??????
		String filePath = Integer.toString(beanlist.get(i).getFileName());
		
		lblImage.setIcon(new ImageIcon(filePath));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		File file = new File(filePath);
		file.delete();
		
	}
	
	
	// ?????? ???????????? Enter ??? ??? ?????? ???????????????
		private void checkAnswer() {
			
			System.out.println("???????????? : " + i);
			
			if (tfAnswerSheet.getText().equals(ShareVar.answer)) { // ????????? ???
				score++;
				lblScore.setText(Integer.toString(score));
				
		         Message();
	          	ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
				JOptionPane.showMessageDialog(null, "??????", "?????? ??????!", JOptionPane.OK_OPTION, icon0);  

		    // ????????? ?????? ??????????????? ?????????, ?????? ?????? ????????????
		    tfAnswerSheet.setText("");
		    i++;
			loadBlob(); 
				
			}else {	// ????????? ???
				// dialog n??? ??? ???????????? ?????? ???
				
		          Message();
		          ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
					JOptionPane.showMessageDialog(null, "???!  " + ShareVar.answer + " ?????????.", "??????", JOptionPane.OK_OPTION, icon);
		    
		    tfAnswerSheet.setText("");
		    i++;
		    loadBlob(); 
		    
			}
			 
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
			timerLabel.setBackground(new Color(255, 255, 255));
			timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			timerLabel.setForeground(Color.WHITE);
			timerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		}
		return timerLabel;
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
                        Thread.sleep(61000);  
                    } catch (InterruptedException e) {  
                    }  
                    GameMain1.this.dispose();
                    PigScoreAction();
                    RankPig1st();
                    RankPig2nd();
                    RankPig3rd();
                    RankPig4th();
                    RankPig5th();
                   
                    UIManager.put("OptionPane.background", Color.decode("#004e4c"));
            	    UIManager.put("OptionPane.messageForeground", Color.white);
            	    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
            	    UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
            	    ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/a.png"));
            	    JOptionPane.showMessageDialog(null, "????????? ???????????????.", " ",JOptionPane.OK_OPTION, icon);
                }  
            }  
        }.start();  
        
        
        
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setForeground(Color.RED);
			panel_1.setBackground(new Color(0, 102, 102));
			panel_1.setBounds(13, 27, 133, 90);
			panel_1.add(getTimerLabel());
		}
		return panel_1;
	}

    private void PigScoreAction() {
		String user_Id = ShareVar.user_id;
		int score = Integer.parseInt(lblScore.getText());
		
		DbAction dbAction = new DbAction(score , user_Id);
		dbAction.peopleScoreAction();
		
//		if(msg == true) {
//			JOptionPane.showMessageDialog(null, "?????? ??????", "??????!", JOptionPane.OK_OPTION);
//		}else {
//			JOptionPane.showMessageDialog(null, "?????? ??????", "??????", JOptionPane.OK_OPTION);
//		}
	}

    
    public void RankPig1st () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, pig_score from score  order by  pig_score desc  limit 1" );
			String id = "";
			String pig_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				pig_score = rs.getString(2);
			}
			PanelGame1.lbl1st.setText(id + "???\t\t\t\t" + pig_score + " ???");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig1st
	
	public void RankPig2nd () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, pig_score from score  order by  pig_score desc  limit 1,1 " );
			String id = "";
			String pig_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				pig_score = rs.getString(2);
			}
			PanelGame1.lbl2nd.setText(id + "???\t\t\t\t" + pig_score + " ???");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig2nd
	
	public void RankPig3rd () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, pig_score from score  order by  pig_score desc  limit 2 ,1 " );
			String id = "";
			String pig_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				pig_score = rs.getString(2);
			}
			PanelGame1.lbl3rd.setText(id + "???\t\t\t\t" + pig_score + " ???");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig3rd
	
	public void RankPig4th () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, pig_score from score  order by  pig_score desc  limit 3,1 " );
			String id = "";
			String pig_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				pig_score = rs.getString(2);
			}
			PanelGame1.lbl4th.setText(id + "???\t\t\t\t" + pig_score + " ???");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig4th
	
	public void RankPig5th () {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery("select user_id, pig_score from score  order by  pig_score desc  limit 4,1 " );
			String id = "";
			String pig_score ="";
			while(rs.next()) {
				id = rs.getString(1);
				pig_score = rs.getString(2);
			}
			PanelGame1.lbl5th.setText(id + "???\t\t\t\t" + pig_score + " ???");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig5th   
	private JLabel getLblScore_1() {
		if (lblScore == null) {
			lblScore = new JLabel("0");
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setForeground(Color.YELLOW);
			lblScore.setFont(new Font("Lucida Grande", Font.BOLD, 35));
			lblScore.setBackground(new Color(0, 0, 102));
			lblScore.setBounds(511, 28, 106, 42);
		}
		return lblScore;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("??????");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel_2.setBounds(476, 25, 61, 35);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("?????????");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel_2_1.setBounds(116, 25, 61, 35);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("copyright ?? 2??? : ?????????, ?????????, ?????????, ?????????");
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			lblNewLabel_3.setBounds(366, 456, 273, 28);
		}
		return lblNewLabel_3;
	}
}
