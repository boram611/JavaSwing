package com.javalec.base;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.javalec.share.ShareVar;
import com.javalec.user.FrameLogin;
import com.javalec.user.FrameSignUp;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelHome extends JPanel {
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_1_1;
	static JLabel lblTotalRankNo1;
	static JLabel lblGame1RankNo1;
	static JLabel lblGame2RankNo1;


	
	private final String url_mysql =ShareVar.url_mysql;
	private final String id_mysql = ShareVar.id_mysql;
	private final String pw_mysql = ShareVar.pw_mysql;
	private JButton btnResult;
	private JLabel lbltot;
	private JLabel lblpig;
	private JLabel lblpeople;
	
	private Image img_tot = new ImageIcon(PanelHome.class.getResource("/res/score_m.png")).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	private Image img_pig = new ImageIcon(PanelHome.class.getResource("/res/b.png")).getImage().getScaledInstance(55, 50, Image.SCALE_SMOOTH);
	private Image img_people = new ImageIcon(PanelHome.class.getResource("/res/a.png")).getImage().getScaledInstance(70, 65, Image.SCALE_SMOOTH);
	
	/**
	 * Create the panel.
	 */
	public PanelHome() {
		setBackground(new Color(0, 102, 102));
		setBounds(0, 0, 634, 457);
		setLayout(null);
		add(getLblNewLabel());
		add(getLabel());
		add(getLabel_1());
		add(getLabel_1_1());
		add(getLblTotalRankNo1());
		add(getLblGame1RankNo1());
		add(getLblGame2RankNo1());
		add(getLbltot());
		add(getLblpig());
		add(getLblpeople());
		setVisible(true);
		RankCheckGameTotal();
		RankCheckGame1();
		RankCheckGame2();

	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("전체 순위");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(240, 5, 155, 64);
		}
		return lblNewLabel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("전체 1위");
			label.setForeground(Color.WHITE);
			label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setBounds(181, 120, 75, 22);
		}
		return label;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("돼지력 1위");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			label_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1.setBounds(160, 238, 96, 28);
		}
		return label_1;
	}
	private JLabel getLabel_1_1() {
		if (label_1_1 == null) {
			label_1_1 = new JLabel("인물 1위");
			label_1_1.setForeground(Color.WHITE);
			label_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			label_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
			label_1_1.setBounds(151, 362, 105, 23);
		}
		return label_1_1;
	}
	private JLabel getLblTotalRankNo1() {
		if (lblTotalRankNo1 == null) {
			lblTotalRankNo1 = new JLabel("New label");
			lblTotalRankNo1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTotalRankNo1.setForeground(Color.WHITE);
			lblTotalRankNo1.setFont(new Font("Arial", Font.BOLD, 20));
			lblTotalRankNo1.setBounds(290, 120, 213, 23);
		}
		return lblTotalRankNo1;
	}
	private JLabel getLblGame1RankNo1() {
		if (lblGame1RankNo1 == null) {
			lblGame1RankNo1 = new JLabel("New label");
			lblGame1RankNo1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGame1RankNo1.setForeground(Color.WHITE);
			lblGame1RankNo1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblGame1RankNo1.setBounds(290, 240, 213, 25);
		}
		return lblGame1RankNo1;
	}
	private JLabel getLblGame2RankNo1() {
		if (lblGame2RankNo1 == null) {
			lblGame2RankNo1 = new JLabel("New label");
			lblGame2RankNo1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGame2RankNo1.setForeground(Color.WHITE);
			lblGame2RankNo1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			lblGame2RankNo1.setBounds(290, 362, 213, 23);
		}
		return lblGame2RankNo1;
	}
	
	
//	*******************************************************
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
			lblGame1RankNo1.setText(address +" 지역의  " + id + "님");
			
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
	                lblGame2RankNo1.setText(address +" 지역의  " + id + "님");
	            
	            conn_mysql.close();
	        }
	        catch (Exception e){
	            System.out.println(e);
	        }	
	}//RankCheckGame2 인물게임
	
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
			lblTotalRankNo1.setText(address + " 지역의  " + id + "님");
			
			conn_mysql.close();
		}
		catch (Exception e){
			System.out.println(e);
		}	
	}//RankCheckGame 전체 합산

	
	
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	
	private JLabel getLbltot() {
		if (lbltot == null) {
			lbltot = new JLabel("");
			lbltot.setHorizontalAlignment(SwingConstants.CENTER);
			lbltot.setIcon(new ImageIcon(img_tot));
			lbltot.setBounds(97, 98, 60, 60);
		}
		return lbltot;
	}
	private JLabel getLblpig() {
		if (lblpig == null) {
			lblpig = new JLabel("");
			lblpig.setHorizontalAlignment(SwingConstants.CENTER);
			lblpig.setIcon(new ImageIcon(img_pig));
			lblpig.setBounds(98, 218, 60, 60);
		}
		return lblpig;
	}
	private JLabel getLblpeople() {
		if (lblpeople == null) {
			lblpeople = new JLabel("");
			lblpeople.setHorizontalAlignment(SwingConstants.CENTER);
			lblpeople.setIcon(new ImageIcon(img_people));
			lblpeople.setBounds(97, 343, 60, 60);
		}
		return lblpeople;
	}
}
