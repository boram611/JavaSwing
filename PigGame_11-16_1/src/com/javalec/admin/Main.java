package com.javalec.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.base.FrameMain;
import com.javalec.peopleadmin.admin_People;
import com.javalec.pigadmin.admin_Pig;
import com.javalec.user.FrameLogin;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JButton btnNewButton_1_1;
	private JButton btnNewButton_1_1_1;
	private JButton btnNewButton_1_1_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 572);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtnNewButton_1_1());
		contentPane.add(getBtnNewButton_1_1_1());
		contentPane.add(getBtnNewButton_1_1_2());
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Q & A 관리");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin_Qa adQa = new admin_Qa();
					adQa.setVisible(true);
					Main.this.dispose();
				}
			});
			btnNewButton.setForeground(Color.BLACK);
			btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnNewButton.setBounds(80, 89, 291, 49);
		}
		return btnNewButton;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("관리자 메인 페이지");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel.setBounds(0, 6, 450, 68);
		}
		return lblNewLabel;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("돼지력 테스트 관리");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin_Pig adPig = new admin_Pig();
					adPig.setVisible(true);
					Main.this.dispose();
				}
			});
			btnNewButton_1.setForeground(Color.BLACK);
			btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnNewButton_1.setBounds(80, 175, 291, 49);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_1_1() {
		if (btnNewButton_1_1 == null) {
			btnNewButton_1_1 = new JButton("인물/사물 퀴즈 관리");
			btnNewButton_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin_People adPeople = new admin_People();
					adPeople.setVisible(true);
					Main.this.dispose();
				}
			});
			btnNewButton_1_1.setForeground(Color.BLACK);
			btnNewButton_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnNewButton_1_1.setBounds(80, 261, 291, 49);
		}
		return btnNewButton_1_1;
	}
	private JButton getBtnNewButton_1_1_1() {
		if (btnNewButton_1_1_1 == null) {
			btnNewButton_1_1_1 = new JButton("로그아웃");
			btnNewButton_1_1_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FrameLogin frameLogin = new FrameLogin();
					frameLogin.setVisible(true);
					Main.this.dispose();
				}
			});
			btnNewButton_1_1_1.setForeground(Color.BLACK);
			btnNewButton_1_1_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnNewButton_1_1_1.setBounds(80, 433, 291, 49);
		}
		return btnNewButton_1_1_1;
	}
	private JButton getBtnNewButton_1_1_2() {
		if (btnNewButton_1_1_2 == null) {
			btnNewButton_1_1_2 = new JButton("User BAN");
			btnNewButton_1_1_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin_UserBan admin_UserBan = new admin_UserBan();
					admin_UserBan.setVisible(true);
					Main.this.dispose();
				}
			});
			btnNewButton_1_1_2.setForeground(Color.BLACK);
			btnNewButton_1_1_2.setFont(new Font("Dialog", Font.BOLD, 16));
			btnNewButton_1_1_2.setBounds(80, 347, 291, 49);
		}
		return btnNewButton_1_1_2;
	}
}
