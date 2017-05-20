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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLcontrollerEndScene  implements Initializable {
	    @FXML
	    private Label nevIde;
	    @FXML
	    private Label nevhiba;
	    
	    @FXML
	    private Label hnev2;
	    
	    @FXML
	    private Label hpont1;
	    @FXML
	    private Label bajnokLabel;
	    
	    @FXML
	    private Label hpont2;
	    
	    @FXML
	    private Label grat;
	    @FXML
	    private Label nevhibakieg;
	    @FXML
	    public Button SaveButton;
	    @FXML
	    private Button eredmeny;
	    
	    @FXML
	    private Button b_exit;
	    
	    @FXML
	    private Button b_newGame;
	    
	    @FXML
	    public TextField Pname;
	    
	    @FXML
	    private Label ido_label;
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
	   @FXML
	   private void eredmenyOnClick(ActionEvent event){
		 
		   GameSave.Load();
		   if (GameSave.loaded){
		   hnev2.setText(GameSave.usr);
		   hpont2.setText(GameSave.time+" second(s)");
		   nevhiba.setText("Bajnok betöltve!");
		   bajnokLabel.setText("Bajnok: ");
		   nevhibakieg.setText("");
		   }else{
			   nevhiba.setText("Még nincs bajnok!");
		   }
		  
	   }
	   
	   @FXML
	   private void SaveButtonClick(ActionEvent event){
		  
		   if(Pname.getText().toString().length() > 10){
			   nevhiba.setText("Túl hosszú név! Maximum 10 karakter hosszút adj meg!");
		   }else{
		   if (!(Pname.getText().isEmpty())){
			   GameSave.PlayerName = Pname.getText().toString();
			
			   SaveButton.setDisable(true);
		   GameSave.Save();
		   if(GameSave.bajnok){
		   nevhiba.setText("Játékos "+GameSave.PlayerName + " elmentve!");
		   }else{ nevhiba.setText("Sajnos nem győzted le a bajnokot :( " );
		   nevhibakieg.setText("A bajnok megtekintéséhez kattints a Mutasd a bajnokot gombra!");}
		  }else{
			  GameSave.PlayerName = null;
			nevhiba.setText("Írj be egy nevet!");
	   }
		   
	   }}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		hpont1.setText(GameLauncher.timepassed + " second(s)"); 
		bajnokLabel.setText(" ");
	}

}
