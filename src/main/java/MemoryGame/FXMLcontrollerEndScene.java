package MemoryGame;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLcontrollerEndScene  implements Initializable {
	    @FXML
	    private Label hnev1;
	    
	    @FXML
	    private Label hnev2;
	    
	    @FXML
	    private Label hnev3;
	    
	    @FXML
	    private Label hpont1;
	    
	    @FXML
	    private Label hpont2;
	    
	    @FXML
	    private Label hpont3;
	    
	    @FXML
	    private Label grat;
	    
	    @FXML
	    private Label Highscore;
	    
	    @FXML
	    private Button b_exit;
	    
	    @FXML
	    private Button b_newGame;
	    
	    @FXML
	    private void b_exitOnClick(ActionEvent event) {
	    	
	    	Platform.exit();
	    }
	    
	    @FXML
	    private void b_newGameOnClick(ActionEvent event) {
	    	   new Thread() {
	                @Override
	                public void run() {
	                	try{
	                
	                    new JFXPanel();
	                    Platform.runLater(new Runnable(){
	                    	@Override
	                    	public void run(){
	                    		try {
	                    			new GameLauncher().start(new Stage());
	                    			
	                    		}catch (Exception e){
	                    			e.printStackTrace();
	                    		}
	                    	}
	                    });
	                	
	                	}catch (Exception e){
	                		e.printStackTrace();
	                	}
	                }
	            }.start();    
	    }
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
