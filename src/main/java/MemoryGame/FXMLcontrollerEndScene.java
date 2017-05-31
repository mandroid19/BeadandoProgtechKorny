package MemoryGame;
//CHECKSTYLE:OFF
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
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Az utolsó scene FXML controllere.
 * @author mandr
 *
 */
public class FXMLcontrollerEndScene  implements Initializable {
	    @FXML
	    private Label nevIde;
	    @FXML
	    private Label nevhiba;
	    
	    @FXML
	    private Label idoeredmeny;
	    
	    @FXML
	    private Label grat;
	    @FXML
	    private Button eredmeny;
	    
	    @FXML
	    private Button b_exit;
	    
	    @FXML
	    private Button b_newGame;
	    @FXML
	    private Label jatekosnev_kiir;
	    
	    @FXML
	    private Label jatekos;
	    @FXML
	    private TextField Pname;
	    
	    @FXML
	    private Label ido_label;
	    @FXML
	    private void b_exitOnClick(ActionEvent event) {
	    	
	    	Platform.exit();
	    }
	    @FXML
	    private void eredmenyOnClick(ActionEvent event){
	    	 if(Pname.getText().toString().length() > 10){
				   nevhiba.setText("Túl hosszú név! Maximum 10 karakter hosszút adj meg!");
				  
			   }else{
			   if (!(Pname.getText().isEmpty())){
				   nevhiba.setText(" Eredmény betöltve! ");
				   eredmeny.setDisable(true);
				   GameLauncher.PlayerName = (Pname.getText().toString());
				   jatekosnev_kiir.setText(GameLauncher.name());
				   ido_label.setText("Idő:");
				   jatekos.setText("Játékos:");
		idoeredmeny.setText(GameLauncher.timepassed + " second(s)");
		}else{nevhiba.setText("Kérlek add meg előtte a nevedet!");} 
	    }   
	  }
	    /**
	     * Új játék gombra kattintására betölti a játék felületet.
	     * @param event 
	     */
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

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	 
	}

}
