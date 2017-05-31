package MemoryGame;

import static org.junit.Assert.*;
import org.junit.Test;
import javafx.application.Application;
import javafx.scene.text.Text;
import org.junit.BeforeClass;
/**
 * Unit test for simple App.
 */
public class TileTest{          
     
    static Thread t = new Thread("JavaFX Init Thread") {
        @Override
        public void run() {
            Application.launch(MemoryGame.class, new String[0]);
        }
    };   
    
    @BeforeClass
    public static void javafx (){
         
        t.setDaemon(true);
        t.start();
     }
    
    @Test
    public void test_equal() {
        Tile tite = new Tile("k");
        assertEquals(tite.hasSameValue(new Tile("k")), true);
        assertEquals(tite.hasSameValue(new Tile("b")), false);
        assertEquals(tite.hasSameValue(new Tile("K")), false);
        assertEquals(tite.hasSameValue(new Tile("]")), false);
        assertEquals(tite.hasSameValue(new Tile("[")), false);
        assertEquals(tite.isOpen(), true);
        
    }
    
    @Test
    public void test_equal_2() {
        Tile tite = new Tile("k");  
        Text text = new Text("j");
        assertNotEquals(tite.getChildren().contains(text),true);
    }
    
    
   /* 
    @AfterClass
    public static void javafx_2(){
        t.stop();
    }
    */                   
}
