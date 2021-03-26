package com.javalec.qa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.qa.Bean;
import com.javalec.qa.DbAction;
import com.javalec.user.FrameSignUp;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class PanelQA extends JPanel {
	private JTextField tfCondition;
	private JComboBox cbCondition;
	private JButton btnCondition;
	private JScrollPane scrollPane;
	private JTable Inner_Table;
	private JRadioButton rbInsert;
	private JRadioButton rbUpdate;
	private JRadioButton rbDelete;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField tfTitle;
	private JLabel label_2_1;
	private JTextField tfSeqno;
	private JButton btnOk;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane_1;
	private JTextArea tfContent;
	private JTextArea tfComment;

	private final DefaultTableModel Outer_Table = new DefaultTableModel(); // ################
	
	/**
	 * Create the panel.
	 */
	public PanelQA() {
		
		
		
		
		setBackground(new Color(0, 102, 102));
		setBounds(0, 0, 634, 457);
		setLayout(null);
		add(getTfCondition());
		add(getCbCondition());
		add(getBtnCondition());
		add(getScrollPane());
		add(getRbInsert());
		add(getRbUpdate());
		add(getRbDelete());
		add(getLabel_1());
		add(getLabel_2());
		add(getTfTitle());
		add(getLabel_2_1());
		add(getTfSeqno());
		add(getBtnOk());
		add(getScrollPane_1());
		add(getTfComment());
		TableInit();
		SearchAction();
		ScreenPartition();

	}
	private JTextField getTfCondition() {
		if (tfCondition == null) {
			tfCondition = new JTextField();
			tfCondition.setBounds(92, 16, 130, 26);
			tfCondition.setColumns(10);
		}
		return tfCondition;
	}
	private JComboBox getCbCondition() {
		if (cbCondition == null) {
			cbCondition = new JComboBox();
			cbCondition.setModel(new DefaultComboBoxModel(new String[] {"제목", "내용", "답글"}));
			cbCondition.setBounds(19, 17, 74, 27);
		}
		return cbCondition;
	}
	private JButton getBtnCondition() {
		if (btnCondition == null) {
			btnCondition = new JButton("검색");
			btnCondition.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ConditionQuery();
				}
			});
			btnCondition.setBounds(222, 15, 117, 29);
		}
		return btnCondition;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(29, 51, 591, 159);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						TableClick();
					}
				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table); 
		}
		return Inner_Table;
	}
	private JRadioButton getRbInsert() {
		if (rbInsert == null) {
			rbInsert = new JRadioButton("입력");
			rbInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
					ClearColumn();
				}
			});
			rbInsert.setSelected(true);
			buttonGroup.add(rbInsert);
			rbInsert.setBounds(370, 16, 58, 23);
		}
		return rbInsert;
	}
	private JRadioButton getRbUpdate() {
		if (rbUpdate == null) {
			rbUpdate = new JRadioButton("수정");
			rbUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
				}
			});
			buttonGroup.add(rbUpdate);
			rbUpdate.setBounds(436, 16, 59, 23);
		}
		return rbUpdate;
	}
	private JRadioButton getRbDelete() {
		if (rbDelete == null) {
			rbDelete = new JRadioButton("삭제");
			rbDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ScreenPartition();
				}
			});
			buttonGroup.add(rbDelete);
			rbDelete.setBounds(498, 16, 58, 23);
		}
		return rbDelete;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("제목 : ");
			label_1.setForeground(Color.WHITE);
			label_1.setBounds(20, 244, 58, 16);
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("내용 : ");
			label_2.setForeground(Color.WHITE);
			label_2.setBounds(18, 279, 58, 16);
		}
		return label_2;
	}
	private JTextField getTfTitle() {
		if (tfTitle == null) {
			tfTitle = new JTextField();
			tfTitle.setBounds(54, 240, 271, 26);
			tfTitle.setColumns(10);
		}
		return tfTitle;
	}
	private JLabel getLabel_2_1() {
		if (label_2_1 == null) {
			label_2_1 = new JLabel("답글 : ");
			label_2_1.setForeground(Color.WHITE);
			label_2_1.setBounds(346, 243, 58, 16);
		}
		return label_2_1;
	}
	private JTextField getTfSeqno() {
		if (tfSeqno == null) {
			tfSeqno = new JTextField();
			tfSeqno.setEditable(false);
			tfSeqno.setVisible(false);
			tfSeqno.setBounds(568, 16, 52, 26);
			tfSeqno.setColumns(10);
		}
		return tfSeqno;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionPartition();
				}
			});
			btnOk.setBounds(508, 420, 117, 29);
		}
		return btnOk;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(54, 277, 270, 134);
			scrollPane_1.setViewportView(getTfContent());
		}
		return scrollPane_1;
	}
	private JTextArea getTfContent() {
		if (tfContent == null) {
			tfContent = new JTextArea();
			tfContent.setLocation(0, 280);
		}
		return tfContent;
	}
	private JTextArea getTfComment() {
		if (tfComment == null) {
			tfComment = new JTextArea();
			tfComment.setEditable(false);
			tfComment.setBounds(381, 243, 239, 168);
		}
		return tfComment;
	}
	
	// Click the Table

	private void TableClick() {
		
        int i = Inner_Table.getSelectedRow();
        String wkSequence = (String)Inner_Table.getValueAt(i, 0);
        
        int tsSequence = Integer.parseInt(wkSequence);
        
        DbAction dbAction = new DbAction(tsSequence);
        Bean bean = dbAction.tableCilck();
        
        tfSeqno.setText(Integer.toString(bean.getSeqno()));
        tfTitle.setText(bean.getTitle());
        tfContent.setText(bean.getContent());
        tfComment.setText(bean.getComment());

	}
	
	// Table 초기화
	public void TableInit(){
        int i = Outer_Table.getRowCount();
        Outer_Table.addColumn("Seq.");
        Outer_Table.addColumn("제목");
        Outer_Table.addColumn("내용");
        Outer_Table.addColumn("date");
        Outer_Table.addColumn("답글");
        Outer_Table.setColumnCount(5);

        for(int j = 0 ; j < i ; j++){
            Outer_Table.removeRow(0);
        }

        Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

        int vColIndex = 0;
        TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
        int width = 30;
        col.setPreferredWidth(width);

        vColIndex = 1;
        col = Inner_Table.getColumnModel().getColumn(vColIndex);
        width = 130;
        col.setPreferredWidth(width);

        vColIndex = 2;
        col = Inner_Table.getColumnModel().getColumn(vColIndex);
        width = 150;
        col.setPreferredWidth(width);

        vColIndex = 3;
        col = Inner_Table.getColumnModel().getColumn(vColIndex);
        width = 100;
        col.setPreferredWidth(width);

        vColIndex = 4;
        col = Inner_Table.getColumnModel().getColumn(vColIndex);
        width = 150;
        col.setPreferredWidth(width);
        
	}
	
	
	//DB to Table
	public void SearchAction(){
		
		DbAction dbAction = new DbAction();
		ArrayList<Bean> beanList = dbAction.selectList();
		
		int listCount = beanList.size();
		for(int i=0; i<listCount; i++) {
			String seqString = Integer.toString(beanList.get(i).getSeqno());
			String[] qTxt = {seqString, beanList.get(i).getTitle(), beanList.get(i).getContent(), beanList.get(i).getuser_date(), beanList.get(i).getComment()};
			Outer_Table.addRow(qTxt);
		}
		
	}
	
	// DataField 정리
	private void ClearColumn() {
		tfSeqno.setText("");
		tfTitle.setText("");
		tfContent.setText("");
		tfComment.setText("");
	}
	
	// 입력
	private void insertAction(){
		String title = tfTitle.getText().trim();
		String content = tfContent.getText().trim();
		
		DbAction dbAction = new DbAction(title, content);
		boolean msg = dbAction.insertAction();
		
		if(msg == true) {	Message();
		ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
		JOptionPane.showMessageDialog(null, "입력 완료", "성공!", JOptionPane.OK_OPTION, icon0);
	}else {
		Message();
		ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
		JOptionPane.showMessageDialog(null, "입력 실패", "경고", JOptionPane.OK_OPTION, icon);
		}
	}
	// 입력필드 체크
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
	
	// 조건검색
	private void ConditionQuery() {
		int i = cbCondition.getSelectedIndex();
		String ConditionQueryColumn = "";
		switch (i) {
		case 0:
			ConditionQueryColumn = "title";
			break;
		case 1:
			ConditionQueryColumn = "content";
			break;
		
		case 2:
			ConditionQueryColumn = "comment";
		default:
			break;
		}
		
		TableInit();
		ClearColumn();
		ConditionQueryAction(ConditionQueryColumn,tfCondition.getText());

	}
	
	
	private void ConditionQueryAction(String ConditionQueryColumn, String tfCondition) {
		
		DbAction dbAction = new DbAction();
		ArrayList<Bean> beanList = dbAction.QueryAction(ConditionQueryColumn, tfCondition);
		
		int listCount = beanList.size();
		for(int i =0; i< listCount; i++) {
			String seqString = Integer.toString(beanList.get(i).getSeqno());
			String[] qTxt = {seqString, beanList.get(i).getTitle(),  beanList.get(i).getContent(), beanList.get(i).getuser_date(), beanList.get(i).getComment()};
			Outer_Table.addRow(qTxt);
		}
	}
	
	// Data 수정
	private void UpdateAction() {
		String title = tfTitle.getText().trim();
		String content = tfContent.getText().trim();
		int seqno = Integer.parseInt(tfSeqno.getText());
		
		DbAction dbAction = new DbAction(seqno, title, content);
		
		boolean msg = dbAction.updateAction();
		
		if(msg == true) {
			Message();
			ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
			JOptionPane.showMessageDialog(null, "수정 완료", "성공!", JOptionPane.OK_OPTION, icon0);
		}else {
			Message();
			ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
			JOptionPane.showMessageDialog(null, "수정 실패", "경고", JOptionPane.OK_OPTION, icon);
		}
	}
	
	// Data 삭제
	private void DeleteAction() {
		String seqno = tfSeqno.getText().trim();
		
		DbAction dbAction = new DbAction(Integer.parseInt(seqno));
		
		// 
		boolean msg = dbAction.deleteAction();
		
		if(msg == true) {
			Message();
        	ImageIcon icon0 = new ImageIcon(FrameSignUp.class.getResource("/res/f.png"));
			JOptionPane.showMessageDialog(null, "삭제 완료", "성공!", JOptionPane.OK_OPTION, icon0);
		}else {
			Message();
			ImageIcon icon = new ImageIcon(FrameSignUp.class.getResource("/res/g.png"));
			JOptionPane.showMessageDialog(null, "삭제 실패", "경고", JOptionPane.OK_OPTION, icon);
		}
	
	}
	
	// 화면 정리 
	public void ScreenPartition() {
		// 입력일 경우
		if(rbInsert.isSelected() == true) {
			btnOk.setVisible(true);
			tfSeqno.setVisible(false);
			tfTitle.setEditable(true);
			tfContent.setEditable(true);
			tfComment.setEditable(false);
		}
		
		// 수정일 경우
		if(rbUpdate.isSelected() == true) {
			btnOk.setVisible(true);
			tfSeqno.setVisible(false);
			tfTitle.setEditable(true);
			tfContent.setEditable(true);	
			tfComment.setEditable(false);
		}
		
		// 삭제일 경우 
		if(rbDelete.isSelected() == true) {
			btnOk.setVisible(true);
			tfSeqno.setVisible(false);
			tfTitle.setEditable(false);
			tfContent.setEditable(false);
			tfComment.setEditable(false);
		}
	}
	
	// Action 구분 
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
				Message();
				JOptionPane.showMessageDialog(this, "내용 입력! " + "\n" + 
						"Data를 입력하세요!",
						"Q&A 정보", 
						JOptionPane.INFORMATION_MESSAGE);        			
				
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
				Message();
				JOptionPane.showMessageDialog(this, "내용 수정 ! " + "\n" + 
						"Data를 입력하세요!",
						"Q&A 정보", 
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
	
	private void Message() {
		UIManager.put("OptionPane.background", Color.decode("#004e4c"));
		UIManager.put("OptionPane.messageForeground", Color.white);
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
	}
	
}
