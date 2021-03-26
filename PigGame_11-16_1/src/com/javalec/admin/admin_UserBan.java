package com.javalec.admin;

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

public class admin_UserBan extends JFrame {

	private JPanel contentPane;
	private JButton btnOK;
	private JScrollPane scrollPane;
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
	private JLabel lblImage;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblId;
	private JTextField tfId;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblId_1_1;
	private JTextField tfAddress;
	private JTextField tfEmail;
	private JLabel lblId_1_1_1;
	private JTextField tfFilePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_UserBan frame = new admin_UserBan();
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
	public admin_UserBan() {
		setTitle("관리자 메인 페이지");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				BeanUser beanUser = new BeanUser();
				TableInit();
				SearchAction();
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
		contentPane.add(getScrollPane());
		contentPane.add(getCbSelection());
		contentPane.add(getTfSelection());
		contentPane.add(getBtnQuery());
		contentPane.add(getLblImage());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblId());
		contentPane.add(getTfId());
		contentPane.add(getLblName());
		contentPane.add(getTfName());
		contentPane.add(getLblId_1_1());
		contentPane.add(getTfAddress());
		contentPane.add(getTfEmail());
		contentPane.add(getLblId_1_1_1());
		contentPane.add(getTfFilePath());
	}

	private JComboBox getCbSelection() {
		if (cbSelection == null) {
			cbSelection = new JComboBox();
			cbSelection.setModel(new DefaultComboBoxModel(new String[] {"id", "name", "address", "email"}));
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
			btnOK = new JButton("BAN");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ActionPartition();
				}
			});
			btnOK.setBounds(553, 378, 117, 29);
		}
		return btnOK;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 142, 395, 426);
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
	        
	        Outer_Table.addColumn("id");
	        Outer_Table.addColumn("name");
	        Outer_Table.addColumn("address");
	        Outer_Table.addColumn("email");
	        Outer_Table.setColumnCount(4);

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
	        width = 100;
	        col.setPreferredWidth(width);
	     // user_id ~ colum 크기 지정  
	        vColIndex = 2;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 100;
	        col.setPreferredWidth(width);
	     // user_id ~ colum 크기 지정  
	        vColIndex = 3;
	        col = Inner_Table.getColumnModel().getColumn(vColIndex);
	        width = 150;
	        col.setPreferredWidth(width);
		}
		
		
	//DB to Table
		private void SearchAction(){
			DbActionUser dbAction = new DbActionUser();
			ArrayList<BeanUser> beanList = dbAction.selectList();
			
			int listCount = beanList.size();
			for(int i=0; i<listCount; i++) {
				String[] qTxt = {beanList.get(i).getId(), beanList.get(i).getName(), beanList.get(i).getAddress(), beanList.get(i).getEmail()};
				Outer_Table.addRow(qTxt);
			}
			
		}
		
		
		// Table에서 Row를 Click후 검색 
		private void TableClick() {
	        int i = Inner_Table.getSelectedRow();
	        String tkSequence = (String)Inner_Table.getValueAt(i, 0);
	        
	        DbActionUser dbAction = new DbActionUser(tkSequence);
	        BeanUser beanUser = dbAction.TableCilck();
	        
	        tfId.setText(beanUser.getId());
	        tfName.setText(beanUser.getName());
	        tfAddress.setText(beanUser.getAddress());
	        tfEmail.setText(beanUser.getEmail());
	        
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
				ConditionQueryColumn = "id";
				break;
			case 1:
				ConditionQueryColumn = "name";
				break;
			case 2:
				ConditionQueryColumn = "address";
				break;
			case 3:
				ConditionQueryColumn = "email";
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
			tfId.setText("");
			tfName.setText("");
			tfAddress.setText("");
			tfEmail.setText("");
			lblImage.setIcon(null);
		}
	
		
	// 조건 검색후 불러오는 테이블 
		private void ConditionQueryAction(String ConditionQueryColumn, String tfSelection) {
			
			DbActionUser dbActionUser = new DbActionUser();
			ArrayList<BeanUser> beanList = dbActionUser.QueryAction(ConditionQueryColumn, tfSelection);
			
			int listCount = beanList.size();
			for(int i =0; i< listCount; i++) {
				String[] qTxt = {beanList.get(i).getId(), beanList.get(i).getName(), beanList.get(i).getAddress(), beanList.get(i).getEmail()};
				Outer_Table.addRow(qTxt);
			}
		}
	
		
	// Action 구분 
		// 비어있는 값을 입력했을 때 오류메세지 출력 
		private void ActionPartition() {
			// 삭제일 경우
				DeleteAction();
				TableInit();
				SearchAction();
				ClearColumn();			
		}
		
		
		
		
		// Data 삭제
		private void DeleteAction() {
			String id = tfId.getText();
			
			DbActionUser dbActionUser = new DbActionUser(id);
			boolean msg = dbActionUser.deleteAction();
			
			if(msg == true) {
				JOptionPane.showConfirmDialog(null, "삭제 완료");
			}else {
				JOptionPane.showConfirmDialog(null, "삭제 실패");
			}
		
		}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon("/Users/tj/Documents/Kenny/Temp/null.png"));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(612, 114, 148, 180);
		}
		return lblImage;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("User Ban");
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
					admin_UserBan.this.dispose();
				}
			});
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(35, 15, 70, 60);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID ");
			lblId.setFont(new Font("굴림", Font.BOLD, 16));
			lblId.setForeground(Color.WHITE);
			lblId.setBounds(425, 147, 25, 16);
		}
		return lblId;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEditable(false);
			tfId.setEnabled(false);
			tfId.setColumns(10);
			tfId.setBounds(508, 142, 84, 30);
		}
		return tfId;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("NAME");
			lblName.setFont(new Font("굴림", Font.BOLD, 16));
			lblName.setForeground(Color.WHITE);
			lblName.setBounds(425, 198, 45, 16);
		}
		return lblName;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(508, 193, 84, 30);
		}
		return tfName;
	}
	private JLabel getLblId_1_1() {
		if (lblId_1_1 == null) {
			lblId_1_1 = new JLabel("ADDRESS");
			lblId_1_1.setFont(new Font("굴림", Font.BOLD, 16));
			lblId_1_1.setForeground(Color.WHITE);
			lblId_1_1.setBounds(425, 256, 101, 16);
		}
		return lblId_1_1;
	}
	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setEditable(false);
			tfAddress.setColumns(10);
			tfAddress.setBounds(507, 251, 85, 30);
		}
		return tfAddress;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setEditable(false);
			tfEmail.setColumns(10);
			tfEmail.setBounds(508, 306, 187, 30);
		}
		return tfEmail;
	}
	private JLabel getLblId_1_1_1() {
		if (lblId_1_1_1 == null) {
			lblId_1_1_1 = new JLabel("EMAIL");
			lblId_1_1_1.setFont(new Font("굴림", Font.BOLD, 16));
			lblId_1_1_1.setForeground(Color.WHITE);
			lblId_1_1_1.setBounds(425, 311, 85, 16);
		}
		return lblId_1_1_1;
	}
	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			tfFilePath.setBounds(728, 310, 45, 21);
			tfFilePath.setColumns(10);
		}
		return tfFilePath;
	}
}