package com.javalec.game1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;

import com.javalec.base.FrameMain;
import com.javalec.base.PanelHome;
import com.javalec.game1.GameMain1;
import com.javalec.share.ShareVar;
import com.javalec.user.FrameLogin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PanelGame1 extends JPanel {
	private JPanel pnlBtnRul;
	private JLabel lblRul;
	private JPanel pnlBtnGame;
	private JLabel lblSignUp_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_2_1;
	
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	static JLabel lbl1st;
	static JLabel lbl2nd;
	static JLabel lbl3rd;
	static JLabel lbl4th;
	static JLabel lbl5th;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private Image img_1 = new ImageIcon(PanelGame1.class.getResource("/res/score1.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private Image img_2 = new ImageIcon(PanelGame1.class.getResource("/res/score2.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private Image img_3 = new ImageIcon(PanelGame1.class.getResource("/res/score3.png")).getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
	private Image img = new ImageIcon(PanelGame1.class.getResource("/res/b.png")).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
	private JLabel lblMain;
	private JLabel lblNewLabel_3;
	

	/**
	 * Create the panel.
	 */
	public PanelGame1() {
		setBackground(new Color(0, 102, 102));
		setBounds(0, 0, 634, 457);
		setLayout(null);
		add(getPnlBtnRul());
		add(getPnlBtnGame());
		add(getLblNewLabel());
		add(getLblNewLabel_1());
		add(getLblNewLabel_2());
		add(getLblNewLabel_1_1());
		add(getLblNewLabel_2_1());
		add(getLbl1st());
		add(getLbl2nd());
		add(getLbl3rd());
		add(getLbl4th());
		add(getLbl5th());
		add(getLbl1());
		add(getLbl2());
		add(getLbl3());
		add(getLblMain());
		add(getLblNewLabel_3());
		RankPig1st();
		RankPig2nd();
		RankPig3rd();
		RankPig4th();
		RankPig5th();

	}
	private JPanel getPnlBtnRul() {
		if (pnlBtnRul == null) {
			pnlBtnRul = new JPanel();
			pnlBtnRul.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					GameRul1 gameRul1 = new GameRul1();
					gameRul1.setVisible(true);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					pnlBtnRul.setBackground(new Color(30, 60, 60));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pnlBtnRul.setBackground(new Color(47, 79, 79));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pnlBtnRul.setBackground(new Color(60, 80, 80));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pnlBtnRul.setBackground(new Color(30, 60, 60));
				}
			});
			pnlBtnRul.setLayout(null);
			pnlBtnRul.setBackground(new Color(47, 79, 79));
			pnlBtnRul.setBounds(563, 17, 55, 55);
			pnlBtnRul.add(getLblRul());
		}
		return pnlBtnRul;
	}
	private JLabel getLblRul() {
		if (lblRul == null) {
			lblRul = new JLabel("?");
			lblRul.setBounds(0, 2, 55, 55);
			lblRul.setHorizontalTextPosition(SwingConstants.CENTER);
			lblRul.setHorizontalAlignment(SwingConstants.CENTER);
			lblRul.setForeground(Color.WHITE);
			lblRul.setFont(new Font("Arial", Font.BOLD, 25));
		}
		return lblRul;
	}
	private JPanel getPnlBtnGame() {
		if (pnlBtnGame == null) {
			pnlBtnGame = new JPanel();
			pnlBtnGame.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					GameMain1 gameMain1 = new GameMain1();
					gameMain1.setVisible(true);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					pnlBtnGame.setBackground(new Color(30, 60, 60));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					pnlBtnGame.setBackground(new Color(47, 79, 79));
				}
				@Override
				public void mousePressed(MouseEvent e) {
					pnlBtnGame.setBackground(new Color(60, 80, 80));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pnlBtnGame.setBackground(new Color(30, 60, 60));
					
				}
			});
			pnlBtnGame.setLayout(null);
			pnlBtnGame.setBackground(new Color(47, 79, 79));
			pnlBtnGame.setBounds(28, 386, 151, 50);
			pnlBtnGame.add(getLblSignUp_1());
		}
		return pnlBtnGame;
	}
	private JLabel getLblSignUp_1() {
		if (lblSignUp_1 == null) {
			lblSignUp_1 = new JLabel("게임 시작");
			lblSignUp_1.setBounds(37, 12, 82, 26);
			lblSignUp_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblSignUp_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblSignUp_1.setForeground(Color.WHITE);
			lblSignUp_1.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblSignUp_1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("1등");
			lblNewLabel.setBounds(197, 112, 70, 28);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("2등");
			lblNewLabel_1.setBounds(197, 177, 61, 16);
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("3등");
			lblNewLabel_2.setBounds(197, 237, 61, 16);
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("4등");
			lblNewLabel_1_1.setBounds(197, 296, 61, 16);
			lblNewLabel_1_1.setForeground(Color.WHITE);
			lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("5등");
			lblNewLabel_2_1.setBounds(197, 356, 61, 16);
			lblNewLabel_2_1.setForeground(Color.WHITE);
			lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lblNewLabel_2_1;
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
			lbl1st.setText(id + "님\t\t\t\t" + pig_score + " 점");
			
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
			lbl2nd.setText(id + "님\t\t\t\t" + pig_score + " 점");
			
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
			lbl3rd.setText(id + "님\t\t\t\t" + pig_score + " 점");
			
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
			lbl4th.setText(id + "님\t\t\t\t" + pig_score + " 점");
			
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
			lbl5th.setText(id + "님\t\t\t\t" + pig_score + " 점");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//Rankpig5th
	
	
	
	
	
	private JLabel getLbl1st() {
		if (lbl1st == null) {
			lbl1st = new JLabel("New label");
			lbl1st.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl1st.setBounds(258, 112, 204, 28);
			lbl1st.setForeground(Color.WHITE);
			lbl1st.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lbl1st;
	}
	private JLabel getLbl2nd() {
		if (lbl2nd == null) {
			lbl2nd = new JLabel("New label");
			lbl2nd.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl2nd.setBounds(258, 171, 204, 28);
			lbl2nd.setForeground(Color.WHITE);
			lbl2nd.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lbl2nd;
	}
	private JLabel getLbl3rd() {
		if (lbl3rd == null) {
			lbl3rd = new JLabel("New label");
			lbl3rd.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl3rd.setBounds(258, 231, 204, 28);
			lbl3rd.setForeground(Color.WHITE);
			lbl3rd.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lbl3rd;
	}
	private JLabel getLbl4th() {
		if (lbl4th == null) {
			lbl4th = new JLabel("New label");
			lbl4th.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl4th.setBounds(258, 290, 204, 28);
			lbl4th.setForeground(Color.WHITE);
			lbl4th.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lbl4th;
	}
	private JLabel getLbl5th() {
		if (lbl5th == null) {
			lbl5th = new JLabel("New label");
			lbl5th.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl5th.setBounds(258, 350, 204, 28);
			lbl5th.setForeground(Color.WHITE);
			lbl5th.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return lbl5th;
	}
	private JLabel getLbl1() {
		if (lbl1 == null) {
			lbl1 = new JLabel("");
			lbl1.setIcon(new ImageIcon(img_1));
			lbl1.setBounds(131, 104, 45, 45);
		}
		return lbl1;
	}
	private JLabel getLbl2() {
		if (lbl2 == null) {
			lbl2 = new JLabel("");
			lbl2.setIcon(new ImageIcon(img_2));
			lbl2.setBounds(131, 163, 45, 45);
		}
		return lbl2;
	}
	private JLabel getLbl3() {
		if (lbl3 == null) {
			lbl3 = new JLabel("");
			lbl3.setIcon(new ImageIcon(img_3));
			lbl3.setBounds(131, 223, 45, 45);
		}
		return lbl3;
	}
	private JLabel getLblMain() {
		if (lblMain == null) {
			lblMain = new JLabel("");
			lblMain.setIcon(new ImageIcon(img));
			lblMain.setBounds(405, 18, 65, 65);
		}
		return lblMain;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("돼지력 테스트 순위");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 22));
			lblNewLabel_3.setBounds(180, 30, 232, 45);
		}
		return lblNewLabel_3;
	}
}
