package MemoryGame;


import org.slf4j.LoggerFactory;


import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
 * A lapokat létrehozó és eseményvezérlő osztály.
 *
 */
public class Tile extends StackPane {
	private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(Tile.class);        
	private Text text = new Text();

        /**
         * A Tile-ök jellemzőit állítja be.
         * @param value a lapkához rendelendő betű
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
         * @param event klikkelés
         */
        private void handleMouseClick(MouseEvent event) {
        	   	
        	if (isOpen() || GameLauncher.clickCount == 0)
        		
        		return;

        	GameLauncher.clickCount--;
            

            if (GameLauncher.selected == null) {
            	GameLauncher.selected = this;
                open(() -> {});
               try {
					//TimeUnit.SECONDS.sleep((long) 0.7);
                	Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            else {
                open(() -> {
                    if (!hasSameValue(GameLauncher.selected)) {
                        logger.info("Nincs találat");
                    	GameLauncher.selected.close();
                        this.close();
                        
                    }
                    else{
                    	GameLauncher.i++;
						logger.info("Találat! Eddigi párok száma: "+GameLauncher.i);
                    	if(GameLauncher.i==GameLauncher.NUM_OF_PAIRS){logger.info("Mindent megtaláltál! Gratulálok!");
                    	try {
                    		
                    		start2(GameLauncher.primaryStage);
                    		GameLauncher.scTimer.end();
					
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 	 				  }

	                        }	 
	                   
                    	}
                    GameLauncher.selected = null;
                    GameLauncher.clickCount = 2; 
                });
            }
        }
        /**
         * Eldönti, hogy a Tile tartalma látható e.
         * @return true vagy false 
         * 	{@code true} a lapka tartalma látható
         * 	{@code false} a lapka tartalma nem látható
         */
        public boolean isOpen() {
            return text.getOpacity() == 1;
        }

        /**
         * Megjeleníti a Tile-ökben lévő betűket,írásjeleket.
         * @param action lapkára való kattintás 
         *
         */
        private void open(Runnable action) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.3), text);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        /**
         * Elfedi a Tile-ökben lévő betűket,írásjeleket. 
         * Eltünteti őket.
         */
        private void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.3), text);
            ft.setToValue(0);
            ft.play();
        }

        /**
         * Vizsgálja, hogy 2 lapka tartalma azonos-e.
         * 
         * @param other a másik lapka tartalma
         * @return boolean kifejezést hogy a két kiválaszott lapka megegyezik-e.
         */
        public boolean hasSameValue(Tile other) {
        	
            return text.getText().equals(other.text.getText());
        }
       
        /**
         * Betölti az utolsó scene-t.
         * @param primaryStage maga a stage neve
         * @throws Exception ha van exception
         */
        public void start2(Stage primaryStage) throws Exception {
        	Pane endScene = (Pane) FXMLLoader.load(getClass().getResource("/sceneEnd.fxml"));
                        	       GameLauncher.Mainstage.setScene(new Scene(endScene));
                        	       GameLauncher.Mainstage.setTitle("Memory Game End");
                        	       GameLauncher.Mainstage.show();
                        	       GameLauncher.Mainstage.setOnCloseRequest(e -> Platform.exit());
                        	        
                        	        
                        	  
                        	    }
 }

