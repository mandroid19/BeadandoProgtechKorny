package MemoryGame;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.LoggerFactory;
public class TimerScheduler {
	private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameSave.class);
	    
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	       
	        @Override       
	        public void run() {
	            GameLauncher.timepassed++;
	            logger.info("Másodperc eltelt: "+GameLauncher.timepassed);
	        }
	    }; 
	    /**
	     * Elindítj a timert
	     */
	    public void start(){
	        timer.scheduleAtFixedRate(task, 1000, 1000);
	    }   
	    /**
	     * Megállítja a timert
	     */
	    public void end(){
	        timer.cancel();
	    }
	    /**
	     * Újraindítja a timert
	     */
	    public void restart(){
	    	GameLauncher.timepassed = 0;
	    }
	}

