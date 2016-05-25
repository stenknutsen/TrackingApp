package com.trackingapp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.trackingapp.eyetribe.TETEyeTrack;


public class AppDriver {
	
	private JFrame mainFrame;
	   private JLabel headerLabel;
	   private JLabel statusLabel;
	   private JPanel controlPanel;
	   
	public AppDriver(){
		prepareGUI();
	}//end ApppDriver constructor

	public static void main(String[] args) {
		System.out.println("Hi there from app driver!");
		AppDriver  appDriverStart = new AppDriver();      
	    appDriverStart.showTextField();
	}//end main
	
	private void prepareGUI(){
	      mainFrame = new JFrame("Eye Tracking App");
	      mainFrame.setSize(400,400);
	      mainFrame.setLayout(new GridLayout(3, 1));
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });    
	      headerLabel = new JLabel("", JLabel.CENTER);        
	      statusLabel = new JLabel("",JLabel.CENTER);    

	      statusLabel.setSize(350,100);

	      controlPanel = new JPanel();
	      controlPanel.setLayout(new FlowLayout());

	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }//end prepareGUI
	
	
	private void showTextField(){
	      headerLabel.setText("Press Start to Begin Recording EyeTribe Data");     

	      JButton loginButton = new JButton("Start");
	      JButton bananasButton = new JButton("Stop");
	      
	      loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 TETEyeTrack.gazeMangement();     
	         }
	      }); 
	      
	      bananasButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {     
		        	 TETEyeTrack.closeApplication();
		         }
		      });

	      controlPanel.add(loginButton);
	      controlPanel.add(bananasButton);
	      mainFrame.setVisible(true);  
	   }//end showTextField

}
