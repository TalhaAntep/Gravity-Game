import java.util.Random;

public class Initialization {
	public static final SingleLinkedList names = new SingleLinkedList();
	public static final SingleLinkedList scores = new SingleLinkedList();
	public static final int MAX_HIGH_SCORE_PLAYER = 50;
	public static final int WIDTH = 55;
	public static final int HEIGHT = 25;
	public static final char WALL = '#';
	public static final char BOULDER = 'O';
	public static final char EARTH_SQUARE = ':';
	public static final char PLAYER = 'P';
	public static final char ROBOT = 'X';
	public static final char SPACE = ' ';
	public static int DIRECTION = 3;
	public static int TP = 3;
	public static final int BACKPACK_SIZE = 8;
	public static int SCORE = 0;
	public static int SLEEP = 100;
	public static char[][] field = new char[HEIGHT][WIDTH];
	public static boolean SETTINGS = false;


	static Random random = new Random();
	public static void Initialize() throws InterruptedException {
		
		// 
		for (int i = 0; i < HEIGHT; i++) {
			for (int t = 0; t < WIDTH; t++) {
				field[i][t] = EARTH_SQUARE;
			}
		}

		for (int i = 0; i < WIDTH; i++) {
			field[0][i] = WALL;
			field[HEIGHT - 1][i] = WALL;
		}

		for (int i = 0; i < HEIGHT; i++) {
			field[i][0] = WALL;
			field[i][WIDTH - 1] = WALL;
		}

		// 8##############
		for (int i = 0; i < WIDTH - 5; i++) {
			field[8][i] = WALL;
		}
		// 16 ################
		for (int i = 5; i < WIDTH; i++) {
			field[16][i] = WALL;
		}

		for (int i = 1; i <= 30; i++) {
			PlaceNumbers();
		}
		
		for (int i = 1; i <= 180; i++) {
			PlaceBoulders();
		}

		for (int i = 1; i <= 200; i++) {
			PlaceEmptySquares();
		}
		
		displayMethods.loginScreen();
		
		displayMethods.gameArea();

		displayMethods.printBackpack();
		
		displayMethods.printQueue();
		try {
			Game myGame = new Game();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static void PlaceBoulders() {
		int x;
		int y;

		do {

			x = random.nextInt(WIDTH - 2) + 1;
			y = random.nextInt(HEIGHT - 2) + 1;

		} while (field[y][x] != EARTH_SQUARE);
		field[y][x] = BOULDER;
	}

	static void PlaceNumbers() {
		double rand = random.nextDouble();

		char number;
		
		// 1/3 ile 2/3 arası
		if (rand > 2.0 / 3.0) {
			number = '1';
		} 
		// 2/3 ile 3/3 arası
		else if (rand > 1.0 / 3.0) {
			number = '2';
		} 
		// 0 ile 1/3 arası
		else 
		{
			number = '3';
		}
		int x;
		int y;

		do {

			x = random.nextInt(WIDTH - 2) + 1;
			y = random.nextInt(HEIGHT - 2) + 1;

		} while (field[y][x] != EARTH_SQUARE);
		field[y][x] = number;

	}

	static void PlaceEmptySquares() {
		int x;
		int y;

		do {

			x = random.nextInt(WIDTH - 2) + 1;
			y = random.nextInt(HEIGHT - 2) + 1;

		} while (field[y][x] != EARTH_SQUARE);
		field[y][x] = SPACE;
	}

}
