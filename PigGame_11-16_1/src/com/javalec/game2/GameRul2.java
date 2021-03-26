package com.javalec.game2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.javalec.base.FrameMain;
import com.javalec.game1.GameRul1;
import com.javalec.user.FrameLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameRul2 extends JFrame {

	private JPanel contentPane;
	private JLabel lblExit;
	
	private Image img = new ImageIcon(GameRul2.class.getResource("/res/Game2_Rul.png"))
			.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img0 = new ImageIcon(GameRul1.class.getResource("/res/Rul.jpg"))
			.getImage().getScaledInstance(440, 350, Image.SCALE_SMOOTH);
	private JLabel lblIcon;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameRul2 frame = new GameRul2();
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
	public GameRul2() {
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 627);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIcon());
		contentPane.add(getLblExit());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getLblNewLabel_3());
	}
	

	private JLabel getLblExit() {
		if (lblExit == null) {
			lblExit = new JLabel("X");
			lblExit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Message();
					ImageIcon icon = new ImageIcon(GameMain2.class.getResource("/res/d.png"));
					if(
					JOptionPane.showConfirmDialog(null, "인물/사물 게임 방법 창을 닫으시겠습니까?", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
						GameRul2.this.dispose();
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
			lblExit.setBounds(453, 0, 35, 35);
		}
		return lblExit;
	}
	
	private JLabel getLblIcon() {
		if (lblIcon == null) {
			lblIcon = new JLabel("");
			lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
			lblIcon.setBounds(67, 19, 85, 98);
			lblIcon.setIcon(new ImageIcon(img));
		}
		return lblIcon;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("인물 / 사진 게임 방법");
			lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(164, 42, 206, 53);
		}
		return lblNewLabel_1;
	}
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("게임 시작 버튼을 클릭하시면 게임화면이 뜹니다.");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(32, 489, 419, 33);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("60초 내에 랜덤으로 나오는 인물 혹은 물건 사진을 보시고");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(32, 523, 419, 33);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("정답을 맞추시면 됩니다.");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setForeground(Color.WHITE);
			lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
			lblNewLabel_2_1.setBounds(32, 556, 419, 33);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(img0));
			lblNewLabel_3.setBounds(20, 113, 440, 350);
		}
		return lblNewLabel_3;
	}
}
