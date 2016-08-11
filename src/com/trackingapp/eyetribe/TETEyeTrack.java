package com.trackingapp.eyetribe;
import java.io.*;
import java.util.ArrayList;

import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;


public class TETEyeTrack {
	
	static String fileName = "default";
	static String time_now;
	static int eyex;
	static int eyey;
	static int gx;
	static int gy;
	static int hx;
	static int hy;
	static final GazeManager gm = GazeManager.getInstance();
	static ArrayList<String> buf = new ArrayList<String>();
	
	
	public static void gazeMangement(String str) {
		fileName = str;
        gm.activate();
        final GazeListener gazeListener = new GazeListener();
        gm.addGazeListener(gazeListener);
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });
	}//end gazeManagement
        
	private static class GazeListener implements IGazeListener {
			
	        @Override
	        public void onGazeUpdate(GazeData gazeData)
	        {
	        	
	        	time_now = gazeData.timeStampString;
				eyex = (int) gazeData.rawCoordinates.x;
				eyey = (int) gazeData.rawCoordinates.y;
				gx = (int) gazeData.leftEye.rawCoordinates.x;
				gy = (int) gazeData.leftEye.rawCoordinates.y;
				hx = (int) gazeData.rightEye.rawCoordinates.x;
				hy = (int) gazeData.rightEye.rawCoordinates.y;
				System.out.println("time: "+time_now+", Gaze is here ("+eyex+", "+eyey+")");
				buf.add("" + time_now + "," + eyex + "," + eyey + "," + gx + "," +gy +"," + hx + "," + hy + "\n");
				
	        }
	}//end GazeListener

	 
	public static class Logger {
	    public static void log(String message) throws IOException { 
	      PrintWriter out = new PrintWriter(new FileWriter("log.txt", true), true);
	      out.write(message);
	      out.close();
	    }
	}//end Logger
	
	
	
	public static void closeApplication() throws IOException{
		 
		 System.out.println("Tracking complete. Writing file.");
		 gm.deactivate();
		 ArrayList<String> dupCheck = new ArrayList<String>();
		
		 PrintWriter out = new PrintWriter(new FileWriter(fileName +".txt", true), true);
		 
		 //check for duplicates and write to file
		 //
		 for(int i = 0;i<buf.size();i++){
			 String tmp = timeSinceMidnight(buf.get(i));
			 if(dupCheck.contains(tmp)){
				 System.out.println("DUP: "+tmp);
			 }else{
				 out.write(tmp+"\n"); 
				 dupCheck.add(tmp);
			 }
			 
			 	 
			 
		 }
	     	
		 out.close();
			 
		 System.exit(0);
	}//end closeApplication

	
	
	//converts timestamp to milliseconds since midnight and 
	//adds as CSV
	//
	public static String timeSinceMidnight(String str){
			str = str.trim();
			String time = str.split(",")[0].split(" ")[1];
			int hours = Integer.parseInt(time.split(":")[0])*60*60*1000;
			int minutes = Integer.parseInt(time.split(":")[1])*60*1000;
			int seconds = (int)((Float.parseFloat(time.split(":")[2]))*1000);
			String total = ""+(hours+minutes+seconds)+","+str;
			
			return total;
	}
	 

}