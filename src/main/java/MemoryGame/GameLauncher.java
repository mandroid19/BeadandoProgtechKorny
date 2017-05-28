package MemoryGame;
import java.awt.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/** 
 * A játékot vezérlő és háttérszámításokat végző osztály.
 * @author mandr
 *
 */
public class GameLauncher extends Application {

    public static final int NUM_OF_PAIRS = 36;
    public static final int NUM_PER_ROW = 12;
    public  static Stage Mainstage = new Stage();
    public static Tile selected = null;
    public static int clickCount = 2;
    static Stage primaryStage;
    public static int i=0; 
    public  static  int timepassed = 0;
    private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameLauncher.class);

   static TimerScheduler scTimer = new TimerScheduler();
	  // TimerScheduler scTimer2 = new TimerScheduler();
    /**
     * Létrehoz egy új timert.
     * @return új timert
     */
    TimerScheduler ti(){
	   return scTimer = new TimerScheduler();
   }
	
    /**
     * Elkészíti magát a játék felületet.
     * @return  a felületet 
     */
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(721, 361);
      
     
     char c = 'A';
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < NUM_OF_PAIRS; i++) {
            tiles.add(new Tile(String.valueOf(c)));
            tiles.add(new Tile(String.valueOf(c)));
            c++;
        }

        Collections.shuffle(tiles);

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(60 * (i % NUM_PER_ROW));
            tile.setTranslateY(60 * (i / NUM_PER_ROW));
            root.getChildren().add(tile);
           
        }

        return root;
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	ti();
        scTimer.restart();
    	scTimer.start();
    	 primaryStage.setOnCloseRequest(event -> {
    		  logger.info("Stage bezárul");
    		  scTimer.end();
    		  Platform.exit();
    		});
    	        Scene scene  = new Scene(createContent());
    	        Mainstage.setScene(scene);
    	        Mainstage.setTitle("MemoryGame");
    	        Mainstage.show();
    	        Mainstage.setOnCloseRequest(e -> scTimer.end());
    	        logger.info("Elkeszult a játék!");
    	        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
    	            if (key.getCode() == KeyCode.UP) {
    	                try {
    	                	scTimer.end();
    						start2(primaryStage);
    						logger.info("Fejlesztői mód aktiválva!");
    					} catch (Exception e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    	            }
    	        });}

	/**
	 * Betölti az utolsó scene-t.
	 * @param primaryStage fő stage
	 * @throws Exception 
	 * 
	 */
	public void start2(Stage primaryStage) throws Exception {
		Pane endScene = (Pane) FXMLLoader.load(getClass().getResource("/sceneEnd.fxml"));
                    	        Mainstage.setScene(new Scene(endScene));
                    	        Mainstage.setTitle("Memory Game End");
                    	        Mainstage.show();
                    	        Mainstage.setOnCloseRequest(e -> Platform.exit());
                    	        
                    	        
                    	  
                    	    }
}