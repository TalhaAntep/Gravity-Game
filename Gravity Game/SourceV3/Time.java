
public class Time {
	public static int time_unit = 0;
	public static double seconds = 0;
	public static void printTime() {
		seconds = ((double)time_unit * ((double)Initialization.SLEEP / 1000.0));
		Game.cn.getTextWindow().setCursorPosition(59, 22);
		Game.cn.getTextWindow().output("Time:");
		Game.cn.getTextWindow().setCursorPosition(66, 22);
		Game.cn.getTextWindow().output(String.valueOf((int)seconds));
		
	}
	
}
