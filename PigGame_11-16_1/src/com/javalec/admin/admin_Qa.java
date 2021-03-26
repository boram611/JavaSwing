package com.javalec.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.peopleadmin.admin_People;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class admin_Qa extends JFrame {

	private JPanel contentPane;
	private JButton btnOK;
	private JTextField tfContent;
	private JLabel label_1;
	private JLabel label;
	private JTextField tfTitle;
	private JTextField tfSeqno;
	private JLabel lblSequenceNo;
	private JScrollPane scrollPane;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JTable Inner_Table;
	private JButton btnUser;
	
	//*******테이블 선언해줘야 테이블에 뜬다.****************************************
	private final DefaultTableModel Outer_Table = new DefaultTableModel(); 
	//**********************************************************************
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cbSelection;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JTextField tfUser;
	private JLabel lblUserid;
	private JLabel label_1_1;
	private JScrollPane scrollPane_1;
	private JTextArea tfComment;
	private JLabel lblIconMain;
	
	private Image amain = new ImageIcon(admin_Qa.class.getResource("/res/amain.png")).getImage().getScaledInstance(190, 120, Image.SCALE_SMOOTH);
	private Image back = new ImageIcon(admin_Qa.class.getResource("/res/back.png")).getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
	private JLabel lblNewLabel_1;
	private JLabel lblQA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_Qa frame = new admin_Qa();
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
	public admin_Qa() {
		setTitle("관리자 메인 페이지");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Bean bean = new Bean();
				tfContent.setText(bean.getUser_id());
				TableInit();
				SearchAction();
				ScreenPartition();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1167, 615);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnOK());
		contentPane.add(getTfContent());
		contentPane.add(getLabel_1());
		contentPane.add(getLabel());
		contentPane.add(getTfTitle());
		contentPane.add(getTfSeqno());
		contentPane.add(getLblSequenceNo());
		contentPane.add(getScrollPane());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
		contentPane.add(getBtnQuery());
		contentPane.add(getTfUser());
		contentPane.add(getLblUserid());
		contentPane.add(getLabel_1_1());
		contentPane.add(getScrollPane_1());
		contentPane.add(getLblIconMain());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblQA());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"제목", "내용", "고객 아이디"}));
			cbSelection.setBounds(25, 88, 115, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(153, 88, 415, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("검색");
			btnQuery.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConditionQuery();
				}
			});
			btnQuery.setBounds(573, 87, 101, 29);
		}
		return btnQuery;
	}

	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionPartition();
				}
			});
			btnOK.setBounds(998, 537, 117, 29);
		}
		return btnOK;
	}
	private JTextField getTfContent() {
		if (tfContent == null) {
			tfContent = new JTextField();
			tfContent.setColumns(10);
			tfContent.setBounds(758, 189, 345, 138);
		}
		return tfContent;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("내용 : ");
			label_1.setFont(new Font("굴림", Font.BOLD, 15));
			label_1.setForeground(Color.WHITE);
			label_1.setBounds(701, 194, 284, 16);
		}
		return label_1;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("제목 :");
			label.setFont(new Font("굴림", Font.BOLD, 15));
			label.setForeground(Color.WHITE);
			label.setBounds(701, 154, 281, 16);
		}
		return label;
	}
	private JTextField getTfUser() {
		if (tfUser == null) {
			tfUser = new JTextField();
			tfUser.setEditable(false);
			tfUser.setColumns(10);
			tfUser.setBounds(988, 119, 114, 26);
		}
		return tfUser;
	}
	private JLabel getLblUserid() {
		if (lblUserid == null) {
			lblUserid = new JLabel("user_id");
			lblUserid.setFont(new Font("굴림", Font.BOLD, 15));
			lblUserid.setForeground(Color.WHITE);
			lblUserid.setBounds(923, 123, 61, 16);
		}
		return lblUserid;
	}
	private JLabel getLabel_1_1() {
		if (label_1_1 == null) {
			label_1_1 = new JLabel("답글 : ");
			label_1_1.setFont(new Font("굴림", Font.BOLD, 15));
			label_1_1.setForeground(Color.WHITE);
			label_1_1.setBounds(701, 355, 284, 16);
		}
		return label_1_1;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(758, 349, 345, 172);
			scrollPane_1.setViewportView(getTfComment());
		}
		return scrollPane_1;
	}
	private JTextArea getTfComment() {
		if (tfComment == null) {
			tfComment = new JTextArea();
		}
		return tfComment;
	}
	private JTextField getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new JTextField();
			tfTitle.setColumns(10);
			tfTitle.setBounds(755, 149, 343, 26);
		}
		return tfTitle;
	}
	private JTextField getTfSeqno() {
		if (tfSeqno == null) {
			tfSeqno = new JTextField();
			tfSeqno.setEditable(false);
			tfSeqno.setColumns(10);
			tfSeqno.setBounds(779, 119, 61, 26);
		}
		return tfSeqno;
	}
	private JLabel getLblSequenceNo() {
		if (lblSequenceNo == null) {
			lblSequenceNo = new JLabel("Q&A 번호");
			lblSequenceNo.setFont(new Font("굴림", Font.BOLD, 15));
			lblSequenceNo.setForeground(Color.WHITE);
			lblSequenceNo.setBounds(700, 123, 72, 16);
		}
		return lblSequenceNo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(26, 127, 647, 435);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("입력 / 수정");
			rbUpdate.setSelected(true);
			buttonGroup.add(rbUpdate);
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
				}
			});
			rbUpdate.setBounds(700, 90, 93, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			buttonGroup.add(rbDelete);
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
				}
			});
			rbDelete.setBounds(796, 90, 61, 23);
		}
		return rbDelete;
	}
	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1){
						TableClick();
					}
				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table); 
		}
		return Inner_Table;
	}
	
	
	
	//---------**************************---------
	// Table 초기화 ( 컬럼 크기 지정 )
		@SuppressWarnings("static-access")
		private void TableInit(){
	        int i = Outer_Table.getRowCount();
	        
	        Outer_Table.addColumn("Seq.");
	        Outer_Table.addColumn("User_id");
	        Outer_Table.addColumn("제목");
	        Outer_Table.addColumn("내용");
	        Outer_Table.addColumn("질문 올린 시간");
	        Outer_Table.addColumn("답글");
	        Outer_Table.addColumn("답글 단 시간");
	        Outer_Table.setColumnCount(7);

	        for(int j = 0 ; j < i ; j++){
	            Outer_Table.removeRow(0);
	        }

	        Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

	     // seqno ~ colum 크기 지정 
	        int vColIndex = 0;
	        TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        int width = 30;
	        col.setPreferredWidth(width);

	     // user_id ~ colum 크기 지정  
	        vColIndex = 1;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 70;
	        col.setPreferredWidth(width);
	        
	     // 제목 ~ colum 크기 지정 
	        vColIndex = 2;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 80;
	        col.setPreferredWidth(width);

	     // 내용 ~ colum 크기 지정 
	        vColIndex = 3;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 140;
	        col.setPreferredWidth(width);
	     
	     // 사용자가 질문을 단 시간 ~ colum 크기 지정 
	        vColIndex = 4;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 90;
	        col.setPreferredWidth(width);
	        
	     // 관리가자 다는 답글 ~ colum 크기 지정 
	        vColIndex = 5;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 140;
	        col.setPreferredWidth(width);
	        
	     // 사용자가 질문을 단 시간 ~ colum 크기 지정 
	        vColIndex = 6;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 90;
	        col.setPreferredWidth(width);

		}
		
		
	//DB to Table
		private void SearchAction(){
			
			DbAction dbAction = new DbAction();
			ArrayList<Bean> beanList = dbAction.selectList();
			
			int listCount = beanList.size();
			for(int i=0; i<listCount; i++) {
				String seqString = Integer.toString(beanList.get(i).getSeqno());
				String[] qTxt = {seqString, beanList.get(i).getUser_id(), beanList.get(i).getTitle(), 
					beanList.get(i).getContent(), beanList.get(i).getUser_date(), beanList.get(i).getComment(), beanList.get(i).getAdmin_date()};
				Outer_Table.addRow(qTxt);
			}
			
		}
	
	
	// Click the Table
		private void TableClick() {
			
	        int i = Inner_Table.getSelectedRow();
	        String wkSequence = (String)Inner_Table.getValueAt(i, 0);
	        
	        int tsSequence = Integer.parseInt(wkSequence);
	        
	        DbAction dbAction = new DbAction(tsSequence);
	        Bean bean = dbAction.tableCilck();
	        
	        tfSeqno.setText(Integer.toString(bean.getSeqno()));
	        tfUser.setText(bean.getUser_id());
	        tfTitle.setText(bean.getTitle());
	        tfContent.setText(bean.getContent());
	        tfComment.setText(bean.getComment());

		}
		
	// 콤보박스 선택 관련 	
		private void ConditionQuery() {
			int i = cbSelection.getSelectedIndex();
			String ConditionQueryColumn = "";
			switch (i) {
			case 0:
				ConditionQueryColumn = "title";
				break;
			case 1:
				ConditionQueryColumn = "content";
				break;
			case 2:
				ConditionQueryColumn = "user_id";
				break;
			default:
				break;
			}
			
			TableInit();
			ClearColumn();
			ConditionQueryAction(ConditionQueryColumn,tfSelection.getText());

		}
		
	// DataField 정리
		private void ClearColumn() {
			tfSeqno.setText("");
			tfUser.setText("");
			tfTitle.setText("");
			tfContent.setText("");
			tfComment.setText("");
		}
	
		
	// 조건 검색후 불러오는 테이블 
		private void ConditionQueryAction(String ConditionQueryColumn, String tfSelection) {
			
			DbAction dbAction = new DbAction();
			ArrayList<Bean> beanList = dbAction.QueryAction(ConditionQueryColumn, tfSelection);
			
			int listCount = beanList.size();
			for(int i =0; i< listCount; i++) {
				String seqString = Integer.toString(beanList.get(i).getSeqno());
				String[] qTxt = {seqString, beanList.get(i).getUser_id(), beanList.get(i).getTitle(), 
					beanList.get(i).getContent(), beanList.get(i).getUser_date(), beanList.get(i).getComment()};
				Outer_Table.addRow(qTxt);
			}
		}
	
		
	// Action 구분 
		// 비어있는 값을 입력했을 때 오류메세지 출력 
		private void ActionPartition() {
			// 수정일 경우
			if(rbUpdate.isSelected() == true) {
				int i_chk = insertFieldCheck();
				if(i_chk == 0){
					UpdateAction();
					TableInit();
					SearchAction();
					ClearColumn();
				}else {
					JOptionPane.showMessageDialog(this, "답글 수정 ! " + "\n" + 
							"답글을 입력하세요!",
							"답글 정보", 
							JOptionPane.INFORMATION_MESSAGE);        
				}			
				ScreenPartition();
			}
			
			// 삭제일 경우
			if(rbDelete.isSelected() == true) {
				DeleteAction();
				TableInit();
				SearchAction();
				ClearColumn();			
				ScreenPartition();
			}
		}
		
		
	//*******************************************
	// ??????????????????????????????	
		private int insertFieldCheck(){
			int i = 0;
			if(tfTitle.getText().length() == 0){
				i++;
				tfTitle.requestFocus();
			}
			if(tfContent.getText().length() == 0){
				i++;
				tfContent.requestFocus();
			}
					
			return i;
		}
	//*************************************
		
		
		// 화면 정리 ( 버튼 띄우고 없애는 )
		private void ScreenPartition() {
			
			// 수정일 경우
			if(rbUpdate.isSelected() == true) {
				btnOK.setVisible(true);
				tfTitle.setEditable(false);
				tfContent.setEditable(false);	
				tfComment.setEditable(true);
			}
			
			// 삭제일 경우 
			if(rbDelete.isSelected() == true) {
				btnOK.setVisible(true);
				tfTitle.setEditable(false);
				tfContent.setEditable(false);
				tfComment.setEditable(false);
			}
		}
		
		
		// Data 삭제
		private void DeleteAction() {
			String seqno = tfSeqno.getText().trim();
			
			DbAction dbAction = new DbAction(Integer.parseInt(seqno));	//
			boolean msg = dbAction.deleteAction();
			
			if(msg == true) {
				JOptionPane.showConfirmDialog(null, "삭제 완료");
			}else {
				JOptionPane.showConfirmDialog(null, "삭제 실패");
			}
		
		}
		
		
		
		// Data 수정
		private void UpdateAction() {
			String title = tfTitle.getText().trim();
			String content = tfContent.getText().trim();
			String comment = tfComment.getText().trim();
			int seqno = Integer.parseInt(tfSeqno.getText());
			
			DbAction dbAction = new DbAction(seqno, title, content, comment);	// 어떤 정보를 수정할지 모르므로 모두 선언 해주기 
			boolean msg = dbAction.updateAction();
			
			if(msg == true) {
				JOptionPane.showConfirmDialog(null, "수정 완료");
			}else {
				JOptionPane.showConfirmDialog(null, "수정 실패");
			}
		}
		

	private JLabel getLblIconMain() {
		if (lblIconMain == null) {
			lblIconMain = new JLabel("");
			lblIconMain.setHorizontalAlignment(SwingConstants.CENTER);
			lblIconMain.setIcon(new ImageIcon(amain));
			lblIconMain.setBounds(895, 3, 190, 120);
		}
		return lblIconMain;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(back));
			lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Main main = new Main();
					main.setVisible(true);
					admin_Qa.this.dispose();
				}
			});
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(35, 15, 70, 60);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblQA() {
		if (lblQA == null) {
			lblQA = new JLabel("Q & A 관리");
			lblQA.setHorizontalAlignment(SwingConstants.CENTER);
			lblQA.setForeground(Color.WHITE);
			lblQA.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblQA.setBounds(339, 5, 509, 74);
		}
		return lblQA;
	}
}