import java.util.Random;

public class Teleportation {
    private static int xCoord;
    private static int yCoord;
    
    public static void Teleport(int x, int y) {
    	xCoord = x;
    	yCoord = y;
    }
    
    public static int getXCoord() {
        return xCoord;
    }
    
    public static int getYCoord() {
        return yCoord;
    }
    
	public static void printTeleport() {
		Game.cn.getTextWindow().setCursorPosition(59, 18);
		Game.cn.getTextWindow().output("Teleport:");
		Game.cn.getTextWindow().setCursorPosition(69, 18);
		Game.cn.getTextWindow().output(String.valueOf(Initialization.TP));
		
	}
}