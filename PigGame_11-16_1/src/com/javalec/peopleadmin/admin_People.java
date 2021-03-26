package com.javalec.peopleadmin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.admin.Main;
import com.javalec.admin.admin_Qa;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class admin_People extends JFrame {

	private JPanel contentPane;
	private JButton btnOK;
	private JLabel label;
	private JTextField tfAnswer;
	private JTextField tfCode;
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
	private Image back = new ImageIcon(admin_Qa.class.getResource("/res/back.png")).getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
	private JButton btnQuery;
	
	
	private JRadioButton rbInsert;
	private JTextField tfFilePath;
	private JLabel label_1;
	private JButton btnFilePath;
	private JLabel lblImage;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_People frame = new admin_People();
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
	public admin_People() {
		setTitle("관리자 메인 페이지");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Bean bean = new Bean();
				TableInit();
				SearchAction();
				ScreenPartition();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 615);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnOK());
		contentPane.add(getLabel());
		contentPane.add(getTfAnswer());
		contentPane.add(getTfCode());
		contentPane.add(getLblSequenceNo());
		contentPane.add(getScrollPane());
		contentPane.add(getRbUpdate());
		contentPane.add(getRbDelete());
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
		contentPane.add(getBtnQuery());
		contentPane.add(getRbInsert());
		contentPane.add(getTfFilePath());
		contentPane.add(getLabel_1_2());
		contentPane.add(getBtnFilePath());
		contentPane.add(getLblImage());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"code", "answer"}));
			cbSelection.setBounds(25, 101, 85, 27);
		}
		return cbSelection;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(117, 101, 128, 26);
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
			btnQuery.setBounds(254, 100, 101, 29);
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
			btnOK.setBounds(598, 537, 117, 29);
		}
		return btnOK;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("정답");
			label.setFont(new Font("굴림", Font.BOLD, 15));
			label.setForeground(Color.WHITE);
			label.setBounds(446, 181, 35, 16);
		}
		return label;
	}
	private JTextField getTfAnswer() {
		if (tfAnswer == null) {
			tfAnswer = new JTextField();
			tfAnswer.setColumns(10);
			tfAnswer.setBounds(487, 176, 153, 26);
		}
		return tfAnswer;
	}
	private JTextField getTfCode() {
		if (tfCode == null) {
			tfCode = new JTextField();
			tfCode.setEditable(false);
			tfCode.setColumns(10);
			tfCode.setBounds(487, 145, 61, 26);
		}
		return tfCode;
	}
	private JLabel getLblSequenceNo() {
		if (lblSequenceNo == null) {
			lblSequenceNo = new JLabel("CODE");
			lblSequenceNo.setFont(new Font("굴림", Font.BOLD, 15));
			lblSequenceNo.setForeground(Color.WHITE);
			lblSequenceNo.setBounds(436, 149, 49, 16);
		}
		return lblSequenceNo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(57, 142, 264, 426);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			buttonGroup.add(rbUpdate);
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
				}
			});
			rbUpdate.setBounds(479, 106, 61, 23);
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
			rbDelete.setBounds(547, 106, 61, 23);
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
	        
	        Outer_Table.addColumn("code");
	        Outer_Table.addColumn("정답");
	        Outer_Table.setColumnCount(2);

	        for(int j = 0 ; j < i ; j++){
	            Outer_Table.removeRow(0);
	        }

	        Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

	     // seqno ~ colum 크기 지정 
	        int vColIndex = 0;
	        TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        int width = 80;
	        col.setPreferredWidth(width);

	     // user_id ~ colum 크기 지정  
	        vColIndex = 1;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 150;
	        col.setPreferredWidth(width);
		}
		
		
	//DB to Table
		private void SearchAction(){
			PeopleDbAction dbAction = new PeopleDbAction();
			ArrayList<Bean> beanList = dbAction.selectList();
			
			int listCount = beanList.size();
			for(int i=0; i<listCount; i++) {
				String seqString = Integer.toString(beanList.get(i).getPig_code());
				String[] qTxt = {seqString, beanList.get(i).getPig_answer()};
				Outer_Table.addRow(qTxt);
			}
			
		}
		
		
		//입력 
		private void insertAction(){
			String answer = tfAnswer.getText();
			
			// Image File
			FileInputStream input = null;
			File file = new File(tfFilePath.getText());
			try {
				input = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PeopleDbAction dbaction = new PeopleDbAction(answer, input);
			boolean aaa = dbaction.InsertAction();
			if(aaa == true){
		          JOptionPane.showMessageDialog(null, tfAnswer.getText()+" 의 정보가 입력 되었습니다.!");                    
			}else{
		          JOptionPane.showMessageDialog(null, "DB에 자료 입력중 에러가 발생했습니다! \n 시스템관리자에 문의하세요!");                    
			}
		}
	
	
		// Table에서 Row를 Click후 검색 
		private void TableClick() {
	        int i = Inner_Table.getSelectedRow();
	        String tkSequence = (String)Inner_Table.getValueAt(i, 0);
	        int wkSequence = Integer.parseInt(tkSequence);
	        
	        PeopleDbAction dbAction = new PeopleDbAction(wkSequence);
	        Bean bean = dbAction.TableCilck();
	        
	        tfCode.setText(Integer.toString(bean.getPig_code()));
	        tfAnswer.setText(bean.getPig_answer());
	        
	        // Image처리
	        // File name이 틀려야 즉각 보여주기가 가능하여   
	        // ShareVar에서 int값으로 정의하여 계속 증가하게 하여 file name으로 사용후 삭제
	        
			String filePath = Integer.toString(com.javalec.share.ShareVar.filename);
			tfFilePath.setText(filePath);
			
			lblImage.setIcon(new ImageIcon(filePath));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			
			File file = new File(filePath);
			file.delete();
			
			tfFilePath.setText("");

	        
		}
		
	// 콤보박스 선택 관련 	
		private void ConditionQuery() {
			int i = cbSelection.getSelectedIndex();
			String ConditionQueryColumn = "";
			switch (i) {
			case 0:
				ConditionQueryColumn = "code";
				break;
			case 1:
				ConditionQueryColumn = "answer";
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
			tfCode.setText("");
			tfAnswer.setText("");
			tfFilePath.setText("");
			lblImage.setIcon(null);
		}
	
		
	// 조건 검색후 불러오는 테이블 
		private void ConditionQueryAction(String ConditionQueryColumn, String tfSelection) {
			
			PeopleDbAction dbAction = new PeopleDbAction();
			ArrayList<Bean> beanList = dbAction.QueryAction(ConditionQueryColumn, tfSelection);
			
			int listCount = beanList.size();
			for(int i =0; i< listCount; i++) {
				String seqString = Integer.toString(beanList.get(i).getPig_code());
				String[] qTxt = {seqString, beanList.get(i).getPig_answer()};
				Outer_Table.addRow(qTxt);
			}
		}
	
		
	// Action 구분 
		// 비어있는 값을 입력했을 때 오류메세지 출력 
		private void ActionPartition() {
			// 입력일 경우 
			if(rbInsert.isSelected() == true) {
				int i_chk = insertFieldCheck();
				if(i_chk == 0){
					insertAction();
					TableInit();
					SearchAction();
					ClearColumn();
				}else {
					JOptionPane.showMessageDialog(null, "돼지력 게임 정보 입력! ");        			
					
				}			
				ScreenPartition();
			}
			
			// 수정일 경우
			if(rbUpdate.isSelected() == true) {
				int i_chk = insertFieldCheck();
				if(i_chk == 0){
					UpdateAction();
					TableInit();
					SearchAction();
					ClearColumn();
				}else {
					JOptionPane.showMessageDialog(null, "돼지력 게임 정보 수정 ! ");             
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
			if(tfAnswer.getText().length() == 0){
				i++;
				tfAnswer.requestFocus();
			}
					
			return i;
		}
	//*************************************
		
		
		// 화면 정리 ( 버튼 띄우고 없애는 )
		private void ScreenPartition() {
			
			// 입력일 경우
			if(rbInsert.isSelected() == true) {
				btnOK.setVisible(true);
				btnFilePath.setVisible(true);
				tfAnswer.setEditable(true);	
			}
			
			// 수정일 경우
			if(rbUpdate.isSelected() == true) {
				btnOK.setVisible(true);
				tfAnswer.setEditable(true);
				tfFilePath.setEditable(true);	
			}
			
			// 삭제일 경우 
			if(rbDelete.isSelected() == true) {
				btnOK.setVisible(true);
				tfAnswer.setEditable(false);
				tfFilePath.setEditable(false);	
			}
		}
		
		
		// Data 삭제
		private void DeleteAction() {
			int code = Integer.parseInt(tfCode.getText());
			
			PeopleDbAction dbAction = new PeopleDbAction(code);
			boolean msg = dbAction.deleteAction();
			
			if(msg == true) {
				JOptionPane.showConfirmDialog(null, "삭제 완료");
			}else {
				JOptionPane.showConfirmDialog(null, "삭제 실패");
			}
		
		}
		
		
		
		// Data 수정
		private void UpdateAction() {
			int code = Integer.parseInt(tfCode.getText());
			String answer = tfAnswer.getText().trim();
			
			
			// Image File
			FileInputStream input = null;
			File file = new File(tfFilePath.getText());
			try {
				input = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PeopleDbAction dbAction = new PeopleDbAction(code, answer, input);	// 어떤 정보를 수정할지 모르므로 모두 선언 해주기 
			boolean msg = dbAction.updateAction();
			
			if(msg == true) {
				JOptionPane.showConfirmDialog(null, "수정 완료");
			}else {
				JOptionPane.showConfirmDialog(null, "수정 실패");
			}
		}
		
		
		
		
		private void FilePath() {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG, BMP", "jpg","png","bmp");
			chooser.setFileFilter(filter);
			
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다!", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			tfFilePath.setText(filePath);
			lblImage.setIcon(new ImageIcon(filePath));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ClearColumn();
					ScreenPartition();
				}
				
			});
			buttonGroup.add(rbInsert);
			rbInsert.setSelected(true);
			rbInsert.setBounds(410, 106, 61, 23);
		}
		return rbInsert;
	}
	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			tfFilePath.setEditable(false);
			tfFilePath.setColumns(10);
			tfFilePath.setBounds(487, 212, 182, 26);
		}
		return tfFilePath;
	}
	private JLabel getLabel_1_2() {
		if (label_1 == null) {
			label_1 = new JLabel("이미지 파일경로");
			label_1.setForeground(Color.WHITE);
			label_1.setFont(new Font("Dialog", Font.BOLD, 15));
			label_1.setBounds(384, 218, 97, 16);
		}
		return label_1;
	}
	private JButton getBtnFilePath() {
		if (btnFilePath == null) {
			btnFilePath = new JButton("파일 경로");
			btnFilePath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FilePath();
				}
			});
			btnFilePath.setBounds(672, 212, 101, 29);
		}
		return btnFilePath;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon("/Users/tj/Documents/Kenny/Temp/null.png"));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(383, 251, 375, 276);
		}
		return lblImage;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel(" 인물 사진/사물 퀴즈 관리");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(131, 4, 539, 90);
		}
		return lblNewLabel;
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
					admin_People.this.dispose();
				}
			});
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(35, 15, 70, 60);
		}
		return lblNewLabel_1;
	}
}