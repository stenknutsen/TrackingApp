package com.trackingapp;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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
	      mainFrame.setSize(600,300);
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
	      headerLabel.setText("Enter file name and press start to begin recording.");     
	      
	      JLabel  namelabel= new JLabel("File name (minus extension): ", JLabel.RIGHT);
	      final JTextField userText = new JTextField(15);
	      
	      JButton loginButton = new JButton("Start");
	      JButton bananasButton = new JButton("Stop");
	      
	      loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	        	 String data = userText.getText();
	        	 data = data.trim();
	        	 statusLabel.setText("Recording started. Writing to file: " + data+".txt");
	        	 TETEyeTrack.gazeMangement(data); 
	        	 
	         }
	      }); 
	      
	      bananasButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {     
		        	 try {
						TETEyeTrack.closeApplication();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		         }
		      });
	      controlPanel.add(namelabel);
	      controlPanel.add(userText);
	      controlPanel.add(loginButton);
	      controlPanel.add(bananasButton);
	      mainFrame.setVisible(true);  
	   }//end showTextField

}
