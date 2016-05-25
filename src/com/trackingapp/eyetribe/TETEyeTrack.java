package com.trackingapp.eyetribe;
import java.io.*;
import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;


public class TETEyeTrack {
	
	static String time_now;
	static int eyex;
	static int eyey;
	
	
	public static void gazeMangement() {
		final GazeManager gm = GazeManager.getInstance();
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
				eyex = (int) gazeData.smoothedCoordinates.x;
				eyey = (int) gazeData.smoothedCoordinates.y;
				System.out.println("time:"+time_now+", Gaze is here ("+eyex+", "+eyey+")");
				try {
					Logger.log("" + time_now + "," + eyex + "," + eyey + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	}//end GazeListener

	 
	public static class Logger {
	    public static void log(String message) throws IOException { 
	      PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
	      out.write(message);
	      out.close();
	    }
	}//end Logger
	
	 public static void closeApplication(){
		 System.out.println("Tracking complete.");
	        System.exit(0);	 
	 }//end closeApplication


}