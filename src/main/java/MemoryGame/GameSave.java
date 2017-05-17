package MemoryGame;

import org.slf4j.LoggerFactory;

public class GameSave {
public int elapsedTime = GameLauncher.timepassed;
public static String PlayerName;
private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameSave.class);
/**
 * Elmenti a játékos nevét és idejét.
 */
public static void Save(){
	
	 logger.info("Játékos "+PlayerName+ " elmentve!");
	 
}

/**
 * Betölti a már meglévő játékosok nevét és idejét
 */
public static void Load(){
	logger.info("Eredmények betöltve!");
	
}
}
