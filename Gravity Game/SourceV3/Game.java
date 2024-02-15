import enigma.core.Enigma;
import enigma.console.Console;
import enigma.console.TextAttributes;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Game {
	public static Console cn = Enigma.getConsole("Gravity Game", 100, 30, 20);
	Console console = Enigma.getConsole();
	public KeyListener klis;
	Random rnd = new Random();
	// ------ Standard variables for keyboard ------
	public static int keypr; // key pressed?
	public static int rkey; // key (for press/release)
	// ----------------------------------------------------
	public static boolean gameOver = false;

	public static int px;
	public static int py;


	Game() throws Exception { // --- Constructor

		// ------ Standard code for keyboard

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
				// Exit the program if the Escape key is pressed
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
			
			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		
		// ----------------------------------------------------

		Robots robots = new Robots(7, Initialization.WIDTH, Initialization.HEIGHT);
		Backpack backpack = new Backpack(Initialization.BACKPACK_SIZE);


		do {

			px = rnd.nextInt(Initialization.WIDTH - 2) + 1;
			py = rnd.nextInt(Initialization.HEIGHT - 2) + 1;

		} while (Initialization.field[py][px] != Initialization.EARTH_SQUARE);

		// The number of robots are dynamic.
		// We created 7 robots at first for the game area.
		

		Queue inputQueue = new Queue(15);
		InputQueue.QueueInitialization(inputQueue);
		int size = inputQueue.size();
		for (int i = 0; i < size; i++) {
			Game.cn.getTextWindow().setCursorPosition(60 + i, 3);
			Game.cn.getTextWindow().output((char) inputQueue.peek());
			inputQueue.enqueue(inputQueue.dequeue());
		}
		
		while (gameOver) {

			int oldpx = px;
			int oldpy = py;
			if (keypr == 1) { // if keyboard button pressed

				if (rkey == KeyEvent.VK_LEFT)
					px--;
				if (rkey == KeyEvent.VK_RIGHT)
					px++;
				if (rkey == KeyEvent.VK_UP)
					py--;
				if (rkey == KeyEvent.VK_DOWN)
					py++;
				if (rkey == KeyEvent.VK_SPACE) {
					// teleport to a new location when space bar is pressed
					if (Initialization.TP != 0) {
						
						int x,y;
						do {
							x = rnd.nextInt(Initialization.WIDTH - 2) + 1;
							y = rnd.nextInt(Initialization.HEIGHT - 2) + 1;
						}while(Initialization.field[y][x] != Initialization.EARTH_SQUARE);
						
						Teleportation.Teleport(x, y);
						
						px = Teleportation.getXCoord();
						py = Teleportation.getYCoord();
						
						Initialization.TP--;

					}
				}
				if (Initialization.TP == 0) {
					Game.cn.getTextWindow().setCursorPosition(59, 16);
					Game.cn.getTextWindow().output("YOU DON'T HAVE TELEPORT");
				}

				switch (Initialization.field[py][px]) {
				case Initialization.WALL:
					px = oldpx;
					py = oldpy;
					Initialization.field[py][px] = Initialization.PLAYER;
					break;

				case Initialization.EARTH_SQUARE:
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.PLAYER;
					break;

				case Initialization.BOULDER:
					int boulderX = px;
					int boulderY = py;
					boolean movedBoulder = false;

					// Check the cells to the right, left, above, and below the boulder
					char nextCell;
					if (rkey == KeyEvent.VK_RIGHT) {
						switch (nextCell = Initialization.field[py][px + 1]) {
						case Initialization.SPACE:
							boulderX += 1;
							movedBoulder = true;
							break;
						}
					}
					if (rkey == KeyEvent.VK_LEFT) {
						switch (nextCell = Initialization.field[py][px - 1]) {
						case Initialization.SPACE:
							boulderX -= 1;
							movedBoulder = true;
							break;
						}
					}
					// If the boulder was moved, update the game area
					if (movedBoulder) {
						Initialization.field[boulderY][boulderX] = Initialization.BOULDER;
						Initialization.field[py][px] = Initialization.PLAYER;
						Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					} else {
						Initialization.field[py][px] = Initialization.BOULDER;
						Initialization.field[oldpy][oldpx] = Initialization.PLAYER;
						px = oldpx;
						py = oldpy;
					}

					break;

				case Initialization.ROBOT:
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.ROBOT;
					displayMethods.gameArea();
					gameOver = false;
					break;

				case '1':
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.PLAYER;
					if (backpack.isBackpackFull()) {
						backpack.pop();
						Game.cn.getTextWindow().setCursorPosition(67, 13 - backpack.size() - 1);
						Game.cn.getTextWindow().output(' ');
					}
					backpack.push('1');
					backpack.setBackpack();
					displayMethods.printBackpack();
					break;

				case '2':
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.PLAYER;
					if (backpack.isBackpackFull()) {
						backpack.pop();
						Game.cn.getTextWindow().setCursorPosition(67, 13 - backpack.size() - 1);
						Game.cn.getTextWindow().output(' ');
					}
					backpack.push('2');
					backpack.setBackpack();
					displayMethods.printBackpack();
					break;

				case '3':
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.PLAYER;
					if (backpack.isBackpackFull()) {
						backpack.pop();
						Game.cn.getTextWindow().setCursorPosition(67, 13 - backpack.size() - 1);
						Game.cn.getTextWindow().output(' ');
					}
					backpack.push('3');
					backpack.setBackpack();
					displayMethods.printBackpack();
					break;

				case Initialization.SPACE:
					Initialization.field[oldpy][oldpx] = Initialization.SPACE;
					Initialization.field[py][px] = Initialization.PLAYER;
					break;

				default:
					break;
				}
				if(oldpy + 1 == py) {
					if(Initialization.field[oldpy - 1][px] == Initialization.BOULDER) {
						Initialization.field[oldpy - 1][px] = Initialization.SPACE;
						Initialization.field[oldpy][px] = Initialization.SPACE;
						Initialization.field[py][px] = Initialization.BOULDER;
						gameOver = false;
						displayMethods.gameArea();
						Thread.sleep(100);
						break;
					}
				}
				char rckey = (char) rkey;
				// left right up down
				if (rckey == '%' || rckey == '\'' || rckey == '&' || rckey == '(') {
					cn.getTextWindow().output(px, py, Initialization.PLAYER);

				} else
					cn.getTextWindow().output(rckey);
			}
			Time.printTime();
			Boulders.boulderFall();
			displayMethods.gameArea();
			Teleportation.printTeleport();
			robots.moveRobots();

			Time.time_unit += 1;
			if (Time.seconds % 3 == 0 && Time.time_unit != 0) {
				InputQueue.ElementstoArea(inputQueue, robots);
				InputQueue.NewQueue(inputQueue);
			}
			cn.getTextWindow().setCursorPosition(66, 20);
			cn.getTextWindow().output(String.valueOf(Initialization.SCORE));

			keypr = 0; // last action
			Thread.sleep(Initialization.SLEEP);

		}
		displayMethods.endGame();
		displayMethods.readScores("highscoretable.txt");
		cn.getTextWindow().setCursorPosition(0, 0);
		cn.getTextWindow().output("Enter your name: ", new TextAttributes(Color.green));
		String name = cn.readLine();
		displayMethods.addScore(name, Initialization.SCORE);
		cn.getTextWindow().output("\n");
		displayMethods.writeScores("highscoretable.txt");
		displayMethods.sortScores();
		displayMethods.printScores();
		cn.getTextWindow().setCursorPosition(0, 2);
		cn.getTextWindow().output("Press ESC for exit...");
		
	}
	
}
