package MemoryGame;
//CHECKSTYLE:OFF

import javax.xml.transform.TransformerException;


/**
 * @author mandr
 *
 */
public interface GameDAO {
    
    /**
     * Bajnok mentése.
     * @throws TransformerException ha van exception.
     */
    public void Saving() throws TransformerException;
    
    /**
     * Bajnok betöltése.
     */
    public void Loading(); 
}
