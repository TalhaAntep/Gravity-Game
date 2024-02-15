import java.util.Random;

public class Robots {
	private static Random rand = new Random();
	private static int[][] robotsPositions; // 2D array to store robots' positions
	private static int numRobots; // number of robots in the game area

	public Robots(int numRobots, int gridSizeX, int gridSizeY) {
		this.numRobots = numRobots;
		this.robotsPositions = new int[numRobots][2];
		placeRobots(gridSizeX, gridSizeY);
	}
	
	private static void placeRobots(int gridSizeX, int gridSizeY) {
		for (int i = 0; i < numRobots; i++) {
			// initialize robots randomly within the game area
			int x;
			int y;

			do {
				x = rand.nextInt(gridSizeX - 2) + 1;
				y = rand.nextInt(gridSizeY - 2) + 1;
			} while (Initialization.field[y][x] != Initialization.EARTH_SQUARE);

			Initialization.field[y][x] = Initialization.ROBOT;
			robotsPositions[i][0] = x;
			robotsPositions[i][1] = y;
		}
	}

	public void moveRobots() {
		Random rand = new Random();
		// choose a random direction to move
		for (int index = 0; index < numRobots; index++) {
			int direction = rand.nextInt(4);
			int X = robotsPositions[index][0];
			int Y = robotsPositions[index][1];

			int addX = 0;
			int addY = 0;

			// int oldX = X;
			/// int oldY = Y;
			// update the robot's position based on the chosen direction
			switch (direction) {
			case 0: // move left
					addX = -1;
				break;
			case 1: // move right
					addX = 1;
				break;
			case 2: // move up
					addY = -1;
				break;
			case 3: // move down
					addY = 1;
				break;
			}

			int newY = Y + addY;
			int newX = X + addX;
			char newCell = Initialization.field[newY][newX];
			switch (newCell) {
			case '1':
				break;
			case '2':
				break;
			case '3':
				break;
			case Initialization.EARTH_SQUARE:
				break;
			case Initialization.WALL:
				break;
			case Initialization.BOULDER:
				break;
			case Initialization.ROBOT:
				// robot encounters a wall, boulder or another robot, do nothing
				break;
			case Initialization.PLAYER:
				// robot encounters the player, game over
				Game.gameOver = true;
				return;
				
			case Initialization.SPACE:
			default:
				// update the robot's position
				Initialization.field[Y][X] = Initialization.SPACE;
				Initialization.field[newY][newX] = Initialization.ROBOT;
				robotsPositions[index][0] += addX;
				robotsPositions[index][1] += addY;
				break;
			}
		}
	}
	public static void addRobot(int gridSizeX, int gridSizeY) {
	    int x;
	    int y;

	    do {
	        x = rand.nextInt(gridSizeX - 2) + 1;
	        y = rand.nextInt(gridSizeY - 2) + 1;
	    } while (Initialization.field[y][x] != Initialization.EARTH_SQUARE && Initialization.field[y][x] != Initialization.SPACE);

	    Initialization.field[y][x] = Initialization.ROBOT;
	    int[][] newRobotsPositions = new int[numRobots + 1][2];
	    for (int i = 0; i < numRobots; i++) {
	        newRobotsPositions[i][0] = robotsPositions[i][0];
	        newRobotsPositions[i][1] = robotsPositions[i][1];
	    }
	    newRobotsPositions[numRobots][0] = x;
	    newRobotsPositions[numRobots][1] = y;
	    robotsPositions = newRobotsPositions;
	    numRobots++;
	}
	
	public static void removeRobot(int x, int y) {
	    for (int i = 0; i < numRobots; i++) {
	        if (robotsPositions[i][0] == x && robotsPositions[i][1] == y) {
	            Initialization.field[y][x] = Initialization.BOULDER;
	            int[][] newRobotsPositions = new int[numRobots - 1][2];
	            for (int j = 0; j < i; j++) {
	                newRobotsPositions[j][0] = robotsPositions[j][0];
	                newRobotsPositions[j][1] = robotsPositions[j][1];
	            }
	            for (int j = i+1; j < numRobots; j++) {
	                newRobotsPositions[j-1][0] = robotsPositions[j][0];
	                newRobotsPositions[j-1][1] = robotsPositions[j][1];
	            }
	            robotsPositions = newRobotsPositions;
	            numRobots--;
	            return;
	        }
	    }
	}
}
