package com.javalec.game1;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.javalec.game2.DbAction;
import com.javalec.share.ShareVar;

public class TimerThread extends Thread{
	
	
	JLabel timerlabel = null;
	int k = 60;
    public TimerThread(JLabel timerlabel) {
		super();
		this.timerlabel = timerlabel;
	}
    
    
   
    
    public void run() {
    	
	    while(true) {
	        timerlabel.setText(""+ k );
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        if(k != 0) {
	        	k--;
	        	System.out.println(k);
	        }
	        if (k == 0){
//	        	Thread.interrupted();
	        	k--;
	        	break;
	        }
	    }
//	    UIManager.put("OptionPane.background", Color.decode("#004e4c"));
//	    UIManager.put("OptionPane.messageForeground", Color.white);
//	    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));  
//	    UIManager.getLookAndFeelDefaults().put("Panel.background", Color.decode("#004e4c"));
//	    ImageIcon icon = new ImageIcon(GameMain1.class.getResource("/res/a.png"));
//	    if(JOptionPane.showConfirmDialog(null, "게임이 종료됩니다.", " ", 2, JOptionPane.YES_NO_OPTION, icon) == 0) {
//	    //	PigScoreAction();
//	    }
   }
    
    
}
