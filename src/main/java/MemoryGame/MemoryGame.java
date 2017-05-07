package MemoryGame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemoryGame extends Application {
  
  
    public  static Stage Mainstage = new Stage();


    @Override
    public void start(Stage primaryStage) throws Exception {
  /////// FXML betöltő  	
    Pane startPane = (Pane) FXMLLoader.load(MemoryGame.class.getResource("/scene1.fxml"));
    Mainstage.setScene(new Scene(startPane));
    Mainstage.setTitle("MemoryGame Start");
    Mainstage.show();
    Mainstage.setOnCloseRequest(e -> Platform.exit());
    }

   
    public static void main(String[] args) {
       Application.launch(args);
    }
}
