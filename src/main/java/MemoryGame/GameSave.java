package MemoryGame;
//CHECKSTYLE:OFF
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.sun.media.jfxmedia.logging.Logger;

/**
 * Mentéseket és betöltéseket tartalmaz.
 * @author mandr
 *
 */
public class GameSave {
public static int elapsedTime = GameLauncher.timepassed;
public static boolean bajnok;
public static String PlayerName;
public static String usr;
public static String time = null;
public static String vizsTime;
public static boolean loaded;
static Document doc = null;
static StreamResult file = null;
public static int inditott;
private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameSave.class);
/**
 * Ellenőrzi, hogy létezik-e már a bajnokot tartalmazó xml fájl,
 * ha igen, akkor eldönti, hogy hamarabb végzett-e a játékos, mint a bajnok,
 * ha igen elmenti őt új bajnokként, ha nem akkor nincs mentés.
 */
public static void Save(){
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;
    
    try {
		builder = factory.newDocumentBuilder();
	} catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   

       TransformerFactory tFactory = TransformerFactory.newInstance();
       Transformer transformer;
	try {
		transformer = tFactory.newTransformer();
		 transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	       transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	       Document doc = builder.newDocument();
	       DOMSource source = new DOMSource(doc);
	       File f = new File("./output.xml");
	       if(f.exists() && !f.isDirectory()) { 
	           logger.info("Lézetik ilyen file!");
	           StreamResult file = new StreamResult("./output.xml");
	           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	           Load();
	           int num = Integer.parseInt(time);
	           if(num > elapsedTime){
	           bajnok = true;
	           elapsedTime = GameLauncher.timepassed;

			try {
			       Element rootElement = doc.createElement("Jatekosok");
			       doc.appendChild(rootElement);

			       Element bajnok = doc.createElement("Bajnok");
			      bajnok.appendChild(doc.createTextNode(PlayerName));
			       rootElement.appendChild(bajnok);
			     
			       Integer et= new Integer(elapsedTime);
		           Element time = doc.createElement("Time");
				      time.appendChild(doc.createTextNode(et.toString()));
				       rootElement.appendChild(time);
				       logger.info("Játékos "+PlayerName+ " elmentve!");
				       elapsedTime = GameLauncher.timepassed;
			
				transformer.transform(source, file);
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }else{
	       	bajnok = false;
	    	 elapsedTime = GameLauncher.timepassed;
	    	logger.info("nincs uj bajnok");}
	    }else 
	       {  StreamResult file = new StreamResult(new File("./output.xml"));
	
	       bajnok = true;
	       Element rootElement = doc.createElement("Jatekosok");
	       doc.appendChild(rootElement);

	       Element bajnok = doc.createElement("Bajnok");
	      bajnok.appendChild(doc.createTextNode(PlayerName));
	       rootElement.appendChild(bajnok);
	       Integer et= new Integer(elapsedTime);
           Element time = doc.createElement("Time");
		      time.appendChild(doc.createTextNode(et.toString()));
		       rootElement.appendChild(time);
		       elapsedTime = GameLauncher.timepassed;
	   logger.info("Uj adatbazis elkeszult a bajnokkal!");
	   logger.info("Elso bajnok: "+PlayerName+ " elmentve!");
	       try {
			transformer.transform(source, file);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
	      
	} catch (TransformerConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	
    }


/**
 * Ellenőrzi, hogy létezik-e már a bajnokot tartalmazó xml, ha igen
 * betölti a bajnok nevét és idejét
 */
public static void Load(){
  
	File file = new File("./output.xml");
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	if(file.exists() && !file.isDirectory()) { 
        logger.info("Lézetik ilyen file!");
       loaded = true;
	try {
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		 usr = document.getElementsByTagName("Bajnok").item(0).getTextContent();
		time = document.getElementsByTagName("Time").item(0).getTextContent();
		
	} catch (ParserConfigurationException | SAXException | IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	
	logger.info("Bajnok betöltve!");
	
}else{
	loaded = false;
	logger.info("Még nincs bajnok file");}
}
	
}
