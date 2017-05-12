package MemoryGame;

import org.slf4j.LoggerFactory;

public class GameSave {
public int elapsedTime = GameLauncher.timepassed;
public static String PlayerName;
private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(GameSave.class);
public static void Save(){
	
	 logger.info("Játékos "+PlayerName+ " elmentve!");
	 
}

public static void Load(){
	logger.info("Eredmények betöltve!");
	
}
}
