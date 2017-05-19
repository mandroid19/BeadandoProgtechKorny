package MemoryGame;
import java.awt.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author mandr
 *
 */


public class GameLauncher extends Application {

    private static final int NUM_OF_PAIRS = 36;
    private static final int NUM_PER_ROW = 12;
    public  static Stage Mainstage = new Stage();
    private Tile selected = null;
    private int clickCount = 2;
    Stage primaryStage;
    int i=0; 
    public  static  int timepassed = 0;
    private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameLauncher.class);
   
    TimerScheduler scTimer = new TimerScheduler();

    
    /**
     * Elkészíti magát a játék felületet.
     * @return
     */
    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(721, 361);
      logger.info("Elkeszult a játék!");
     
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

    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	scTimer.restart();
  scTimer.start();
 primaryStage.setOnCloseRequest(event -> {
	  logger.info("Stage bezárul");
	  scTimer.end();
	  Platform.exit();
	});
        Mainstage.setScene(new Scene(createContent()));
        Mainstage.setTitle("MemoryGame");
        Mainstage.show();
        Mainstage.setOnCloseRequest(e -> scTimer.end());
        
  
    }

    
    private class Tile extends StackPane {
        private Text text = new Text();

        /**
         * A Tile-ök jellemzőit állítja be.
         */
        public Tile(String value) {
            Rectangle border = new Rectangle(60, 60);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setText(value);
            text.setFont(Font.font(30));
           
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(this::handleMouseClick);
            close();
        }

        /**
         * A játékban lévő klikkeléseket kezeli.
         * Továbbá vizsgája hány párat talált meg a játékos, megadja a viselkedését
         * a tile-öknek.
         */
        public void handleMouseClick(MouseEvent event) {
        	   	
        	if (isOpen() || clickCount == 0)
        		
        		return;

            clickCount--;
            

            if (selected == null) {
                selected = this;
                open(() -> {});
            }
            else {
                open(() -> {
                    if (!hasSameValue(selected)) {
                        logger.info("Nincs találat");
                    	selected.close();
                        this.close();
                        
                    }
                    else{
                    	i++;
                    	logger.info("Találat! Eddigi párok száma: "+i);
                    	if(i==NUM_OF_PAIRS){logger.info("Mindent megtaláltál! Gratulálok!");
                    	try {
                    		
							start2(primaryStage);
							scTimer.end();
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 	 				  }

                      
	                        }	 
	                    
                    	
                    	
                    	}
                    selected = null;
                    clickCount = 2;
                });
            }
        }

        /**
         * Eldönti, hogy a Tile tartalma látható e.
         */
        public boolean isOpen() {
            return text.getOpacity() == 1;
        }

        /**
         * Animálja a Tile-ökben lévő betűket,írásjeleket.
         * Megjeleníti őket.
         */
        public void open(Runnable action) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.3), text);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        /**
         * Animálja a Tile-ökben lévő betűket,írásjeleket. 
         * Eltünteti őket.
         */
        public void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.3), text);
            ft.setToValue(0);
            ft.play();
        }

        /**
         * Vizsgálja, hogy 2 Tile tartalma azonos-e.
         */
        public boolean hasSameValue(Tile other) {
        	
            return text.getText().equals(other.text.getText());
        }
       

 }

	/**
	 * Betölti az utolsó scene-t.
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