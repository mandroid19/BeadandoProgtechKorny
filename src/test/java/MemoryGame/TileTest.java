package MemoryGame;

import javafx.scene.text.Text;
import static org.junit.Assert.*;
import org.junit.Test;
import javafx.animation.FadeTransition;
import javafx.application.Application;
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
import org.junit.AfterClass;
import org.junit.BeforeClass;
/**
 * Unit test for simple App.
 */
public class TileTest{          
     
       
     @BeforeClass
     public static void javafx (){
         Thread t = new Thread("JavaFX Init Thread") {
        public void run() {
            Application.launch(MemoryGame.class, new String[0]);
        }
    };
    t.setDaemon(true);
    t.start();
     }
     
     
    @Test
    public void test_equal() {
        Tile tite = new Tile("k");
        assertEquals(tite.hasSameValue(new Tile("k")), true);
        assertEquals(tite.hasSameValue(new Tile("b")), false);
        assertEquals(tite.isOpen(), true);
        
    }
    
    @Test
    public void test_equal_2() {
        Tile tite = new Tile("k");        
        assertEquals(tite.hasSameValue(new Tile("K")), false);
        assertEquals(tite.hasSameValue(new Tile("]")), false);
        assertEquals(tite.hasSameValue(new Tile("[")), false);
        
    }
     
    
    
              
}
