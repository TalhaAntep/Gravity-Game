import java.awt.Color;
import java.io.*;
import java.util.Stack;
import enigma.console.TextAttributes;
import java.text.DecimalFormat;

public class displayMethods {
	public static void printBackpack() {
		Stack<Character> tempBackpack = new Stack<Character>();
		Game.cn.getTextWindow().setCursorPosition(65, 13);
		Game.cn.getTextWindow().output('+');
		for (int i = 0; i < 3; i++) {
			Game.cn.getTextWindow().output('-');
		}
		Game.cn.getTextWindow().output('+');
		Game.cn.getTextWindow().setCursorPosition(64, 14);
		Game.cn.getTextWindow().output("Backpack");
		for (int i = 1; i <= 8; i++) {
			Game.cn.getTextWindow().setCursorPosition(65, 13 - i);
			Game.cn.getTextWindow().output('|');
			Game.cn.getTextWindow().setCursorPosition(69, 13 - i);
			Game.cn.getTextWindow().output('|');
		}
		Game.cn.getTextWindow().setCursorPosition(59, 20);
		Game.cn.getTextWindow().output("Score:");
		Game.cn.getTextWindow().setCursorPosition(66, 20);
		Game.cn.getTextWindow().output(String.valueOf(Initialization.SCORE));
		if (!Backpack.backpack.isEmpty()) {

			tempBackpack.addAll(Backpack.backpack);

			while (!tempBackpack.empty()) {
				Game.cn.getTextWindow().setCursorPosition(67, 14 - tempBackpack.size() - 1);
				Game.cn.getTextWindow().output(tempBackpack.pop());

			}
		}
	}
	
	public static void consoleClear() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 100; j++) {
                Game.cn.getTextWindow().output(j,i,' ');
            }
            Game.cn.getTextWindow().output(0,i,'\n');
        }
	}
	
	public static void loginScreen() throws InterruptedException {
		boolean settingsChosen = false;

		Game.cn.getTextWindow().output("========================================\n" , new TextAttributes(Color.magenta));
		Game.cn.getTextWindow().output("                 GRAVITY                \n" , new TextAttributes(Color.orange));
		Game.cn.getTextWindow().output("========================================\n" , new TextAttributes(Color.magenta));
		Game.cn.getTextWindow().output("\n1-)New Game\n" , new TextAttributes(Color.orange));
		Game.cn.getTextWindow().output("2-)Settings\n" , new TextAttributes(Color.orange));
		Game.cn.getTextWindow().output("\nPlease enter one of the numbers above: " , new TextAttributes(Color.green));

		boolean validChoice1 = false;
		while (!validChoice1) {
			String input = Game.cn.readLine();
			if (input.equals("2")) {
				Initialization.SETTINGS = true;
				validChoice1 = true;
				settingsChosen = true;
				consoleClear();
			} else if (input.equals("1")) {
				validChoice1 = true;
				consoleClear();
				Game.cn.getTextWindow().setCursorPosition(0, 0);
				Game.cn.getTextWindow().output("*********************" , new TextAttributes(Color.red));
				Game.cn.getTextWindow().output("\n    DEFAULT RULES\n" , new TextAttributes(Color.yellow));
				Game.cn.getTextWindow().output("*********************" , new TextAttributes(Color.red));
				Game.cn.getTextWindow().output("\n1-) The boulders fall random sides.\n" , new TextAttributes(Color.cyan));
				Game.cn.getTextWindow().output("2-) The speed of the game is " + 1000/Initialization.SLEEP + " frames per second.\n" , new TextAttributes(Color.cyan));
				Game.cn.getTextWindow().output("3-) You have " + Initialization.TP + " teleport rights at the beginning of the game.\n" , new TextAttributes(Color.cyan));
				Game.cn.getTextWindow().output("\nAs soon as the game starts, your score may seem high due to boulders falling on some robots!" , new TextAttributes(Color.red));
				Thread.sleep(6000);
				consoleClear();
				Game.gameOver = true;

			} else {
				Game.cn.getTextWindow().output("Invalid choice. Please enter 1 or 2: ", new TextAttributes(Color.red));
			}
		}
		if (settingsChosen)

		{
			Game.cn.getTextWindow().setCursorPosition(0, 0);
			Game.cn.getTextWindow().output("========================================\n" , new TextAttributes(Color.green));
			Game.cn.getTextWindow().output("               SETTINGS                 \n" , new TextAttributes(Color.orange));
			Game.cn.getTextWindow().output("========================================\n" , new TextAttributes(Color.green));
			Game.cn.getTextWindow().output("Where do you want the boulders to drop?\n" , new TextAttributes(Color.orange));
			Game.cn.getTextWindow().output("\n1-)Left\n" , new TextAttributes(Color.red));
			Game.cn.getTextWindow().output("2-)Right\n" , new TextAttributes(Color.red));
			Game.cn.getTextWindow().output("3-)Random\n" , new TextAttributes(Color.red));
			Game.cn.getTextWindow().output("\nPlease enter one of the numbers above: " , new TextAttributes(Color.orange));
			boolean validChoice2 = false;
			while (!validChoice2) {
				String input = Game.cn.readLine();
				if (input.equals("1") || input.equals("2") || input.equals("3")) {
					Initialization.DIRECTION = Integer.parseInt(input);
					validChoice2 = true;
				} else {
					Game.cn.getTextWindow().output("Invalid choice. Please enter 1, 2, or 3: " , new TextAttributes(Color.red));
				}
			}

			Game.cn.getTextWindow().output("\nThe normal speed of the game is 10 frames per second.\n" , new TextAttributes(Color.magenta));
			Game.cn.getTextWindow().output("Do you want to change the game speed?(Y,y/N,n)\n" , new TextAttributes(Color.green));
			boolean validChoice3 = false;
			while (!validChoice3) {
				String input = Game.cn.readLine();
				if (input.equals("Y") || input.equals("y")) {
					Game.cn.getTextWindow().output("Please enter the new game speed as millisecond\n" , new TextAttributes(Color.magenta));
					String newSleep = Game.cn.readLine();
					try {
						Initialization.SLEEP = Integer.parseInt(newSleep);
						validChoice3 = true;
					} catch (NumberFormatException e) {
						Game.cn.getTextWindow().output("Invalid input. Please enter an integer: " , new TextAttributes(Color.red));
					}
				} else if (input.equals("N") || input.equals("n")) {
					validChoice3 = true;
				} else {
					Game.cn.getTextWindow().output("Invalid choice. Please enter Y or N(y,n): " , new TextAttributes(Color.red));
				}
			}
			Game.cn.getTextWindow().output("\nYou will have 3 teleport rights at the beginning of the game.\n" , new TextAttributes(Color.cyan));
			Game.cn.getTextWindow().output("Do you want to change the teleport rights?(Y/N)\n" , new TextAttributes(Color.green));
			boolean validChoice4 = false;
			while (!validChoice4) {
			    String input = Game.cn.readLine();
			    if (input.equals("Y") || input.equals("y")) {
			        Game.cn.getTextWindow().output("Please enter the new teleport rights (maximum 5):\n" , new TextAttributes(Color.cyan));
	                boolean next = true;
			        while(next) {
				        String newTP = Game.cn.readLine();
				        try {
				            int tp = Integer.parseInt(newTP);
				            if (tp >= 0 && tp <= 5) {
				                Initialization.TP = tp;
				                validChoice4 = true;
				                next = false;
				            } else {
				                Game.cn.getTextWindow().output("Invalid input. Please enter an integer between 0 and 5: " , new TextAttributes(Color.red));
				            }
				        } catch (NumberFormatException e) {
				            Game.cn.getTextWindow().output("Invalid input. Please enter an integer: " , new TextAttributes(Color.red));
				        }
			        }

			    } else if (input.equals("N") || input.equals("n")) {
			        validChoice4 = true;
			    } else {
			        Game.cn.getTextWindow().output("Invalid choice. Please enter Y or N (y,n): " , new TextAttributes(Color.red));
			    }
			}
			consoleClear();
			String directionString;
		    switch (Initialization.DIRECTION) {
		        case 1:
		            directionString = "Left";
		            break;
		        case 2:
		            directionString = "Right";
		            break;
		        case 3:
		            directionString = "Random";
		            break;
		        default:
		            directionString = "Random";
		            break;
		    }
		    int sleepTime = Initialization.SLEEP;
		    double speed = (double)1000/sleepTime;
		    DecimalFormat decimalFormat = new DecimalFormat("#.##");
			Game.cn.getTextWindow().setCursorPosition(0, 0);
			Game.cn.getTextWindow().output("********************" , new TextAttributes(Color.red));
			Game.cn.getTextWindow().output("\n    NEW RULES\n" , new TextAttributes(Color.yellow));
			Game.cn.getTextWindow().output("********************" , new TextAttributes(Color.red));
			Game.cn.getTextWindow().output("\n1-) The boulders fall " + directionString + " sides." , new TextAttributes(Color.cyan));
			Game.cn.getTextWindow().output("\n2-) The speed of the game is " + decimalFormat.format(speed) +" frames per second.\n" , new TextAttributes(Color.cyan));
			Game.cn.getTextWindow().output("3-) You have " + Initialization.TP + " teleport rights at the beginning of the game.\n" , new TextAttributes(Color.cyan));
			Game.cn.getTextWindow().output("\nAs soon as the game starts, your score may seem high due to boulders falling on some robots." , new TextAttributes(Color.red));
			Thread.sleep(6000);
			consoleClear();
			
		}
		Game.gameOver = true;
	}

	public static void gameArea() {
		for (int i = 0; i < Initialization.HEIGHT; i++) {
			for (int k = 0; k < Initialization.WIDTH; k++) {
				Game.cn.getTextWindow().setCursorPosition(k, i);
				Game.cn.getTextWindow().output(Initialization.field[i][k]);
			}
			Game.cn.getTextWindow().output("\n");
		}

		for (int x = 0; x < 55; x++) {
			for (int y = 0; y < 25; y++) {
				char ch = Initialization.field[y][x];
				if (ch == '#') {
					Game.cn.getTextWindow().output(x, y, '#', new TextAttributes(Color.white));
				} else if (ch == 'O') {
					Game.cn.getTextWindow().output(x, y, 'O', new TextAttributes(Color.magenta));
				} else if (ch == ':') {
					Game.cn.getTextWindow().output(x, y, ':', new TextAttributes(Color.cyan));
				} else if (ch == '1') {
					Game.cn.getTextWindow().output(x, y, '1', new TextAttributes(Color.yellow));
				} else if (ch == '2') {
					Game.cn.getTextWindow().output(x, y, '2', new TextAttributes(Color.yellow));
				} else if (ch == '3') {
					Game.cn.getTextWindow().output(x, y, '3', new TextAttributes(Color.yellow));
				} else if (ch == 'X') {
					Game.cn.getTextWindow().output(x, y, 'X', new TextAttributes(Color.red));
				} else if (ch == 'P') {
					Game.cn.getTextWindow().output(x, y, 'P', new TextAttributes(Color.green));
				} else if (ch == ' ') {
					Game.cn.getTextWindow().output(x, y, ' ', new TextAttributes(Color.black));
				}
			}
		}
	}

	public static void printQueue() {
		for (int i = 0; i < 15; i++) {
			Game.cn.getTextWindow().setCursorPosition(60 + i, 2);
			Game.cn.getTextWindow().output("<" , new TextAttributes(Color.yellow, Color.darkGray));
		}
		for (int i = 0; i < 15; i++) {
			Game.cn.getTextWindow().setCursorPosition(60 + i, 4);
			Game.cn.getTextWindow().output("<", new TextAttributes(Color.yellow, Color.darkGray));
		}

	}

	public static void endGame() throws InterruptedException {
		consoleClear();
		
		Game.cn.getTextWindow().setCursorPosition(40, 8);		
	    Game.cn.getTextWindow().output("\r\n"
	    		+ "   ____      _      __  __   _____        ___   __     __  _____   ____  \r\n"
	    		+ "  / ___|    / \\    |  \\/  | | ____|      / _ \\  \\ \\   / / | ____| |  _ \\ \r\n"
	    		+ " | |  _    / _ \\   | |\\/| | |  _|       | | | |  \\ \\ / /  |  _|   | |_) |\r\n"
	    		+ " | |_| |  / ___ \\  | |  | | | |___      | |_| |   \\ V /   | |___  |  _ < \r\n"
	    		+ "  \\____| /_/   \\_\\ |_|  |_| |_____|      \\___/     \\_/    |_____| |_| \\_\\\r\n"
	    		+ "                                                                         \r\n"
	    		+ "", new TextAttributes(Color.red)); 
	    Thread.sleep(3000);
	    consoleClear();
	    
	}	
	
	public static void readScores(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] splitted = line.split(" ");
				String name = splitted[0]; // even indices are names
				int score = Integer.parseInt(splitted[1]); // odd indices are scores
				addScore(name, score);
			}
		} catch (IOException e) {
			System.err.println();
		}
	}
	
	public static void addScore(String name, int score) {
		Node forScores = Initialization.scores.head;
		Node forNames = Initialization.names.head;
		Node prevforScores = null;
		Node prevforNames = null;

		if (Initialization.scores.size() < Initialization.MAX_HIGH_SCORE_PLAYER || score > (int) Initialization.scores.getTail().getData()) {
																						
			if (Initialization.scores.size() == Initialization.MAX_HIGH_SCORE_PLAYER) {

				Initialization.names.deleteTail(); // getTail is used for getting the last data.
				Initialization.scores.deleteTail(); // deleteTail is used for deleting the last data.
			}
			while (forNames != null) {
				if (forNames.getData().equals(name)) { /// if the name that is input is the same as in the high-score
														/// table
					if ((int) forScores.getData() < score) { /// set the data if the newest score is higher.
						forScores.setData(score);
					}
					return;
				}
				prevforScores = forScores;
				prevforNames = forNames;
				forScores = forScores.getLink(); // to next
				forNames = forNames.getLink(); // to next
			}
			Node newforScores = new Node(score);
			Node newforNames = new Node(name);
			if (prevforScores == null) {
				newforScores.setLink(Initialization.scores.head); // this algorithm creates two new nodes with the new score and name
													// respectively.
				Initialization.scores.head = newforScores;
				newforNames.setLink(Initialization.names.head);
				Initialization.names.head = newforNames;
			} else {
				newforScores.setLink(prevforScores.getLink());
				prevforScores.setLink(newforScores); // set the data if the sll is not null
				newforNames.setLink(prevforNames.getLink());
				prevforNames.setLink(newforNames);
			}
		}
		sortScores(); // sort them their scores.
	}
	// to sort the SLL respect to their scores.
		public static void sortScores() {

			boolean sorted = false;

			while (!sorted) {

				sorted = true;
				
				// to initialize nodes to iterate through the scores and names linked lists.
				Node currentforScores = Initialization.scores.head;
				Node currentforNames = Initialization.names.head;
				Node prevforScores = null;
				Node prevforNames = null;

				while (currentforScores != null && currentforScores.getLink() != null) {
					
					// if the score of the current node is lesser than the score of the next node,
					// swap the nodes in both the scores and names linked lists.
					if ((int) currentforScores.getData() < (int) currentforScores.getLink().getData()) {
						Node tempforScores = currentforScores.getLink();
						Node tempforNames = currentforNames.getLink();
						currentforScores.setLink(tempforScores.getLink());
						currentforNames.setLink(tempforNames.getLink());
						tempforScores.setLink(currentforScores);
						tempforNames.setLink(currentforNames);
						
						//  update the head nodes if the previous node is null.
						if (prevforScores == null) {
							Initialization.scores.head = tempforScores;
							Initialization.names.head = tempforNames;
						}
						
						// set the next link of the previous node to the current node.
						else {
							prevforScores.setLink(tempforScores);
							prevforNames.setLink(tempforNames);
						}
						
						// update the previous nodes and set sorted as false.
						prevforScores = tempforScores;
						prevforNames = tempforNames;
						sorted = false;

					}
					
					//  update the previous and current nodes
					// if the score of the current node is greater than or equal to the score of the next node.
					else {
						prevforScores = currentforScores;
						prevforNames = currentforNames;
						currentforScores = currentforScores.getLink();
						currentforNames = currentforNames.getLink();
					}
				}
			}
		}
		
		// it is used for writing the new highest scores in the "highscoretable.txt" text file.
		public static void writeScores(String filename) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
				Node forNames = Initialization.names.head;
				Node forScores = Initialization.scores.head;
				while (forNames != null && forScores != null) {
					writer.write(forNames.getData() + " " + forScores.getData() + "\n");
					forNames = forNames.getLink();
					forScores = forScores.getLink();
				}
			} catch (IOException e) {
				System.err.println();
			}
		}

		// to print the high-score table
		public static void printScores() {
			Game.cn.getTextWindow().setCursorPosition(35, 5);
			Game.cn.getTextWindow().output("********************", new TextAttributes(Color.red));
			Game.cn.getTextWindow().setCursorPosition(35, 6);
			Game.cn.getTextWindow().output("HIGH   SCORE   TABLE", new TextAttributes(Color.red));
			Game.cn.getTextWindow().setCursorPosition(35, 7);
			Game.cn.getTextWindow().output("********************", new TextAttributes(Color.red));
			Node forNames = Initialization.names.head;
			Node forScores = Initialization.scores.head;
			int i = 0;
			while (forNames != null && forScores != null) {
				Game.cn.getTextWindow().setCursorPosition(35, 8 + i);
				Game.cn.getTextWindow().output((String)forNames.getData(), new TextAttributes(Color.cyan));
				Game.cn.getTextWindow().setCursorPosition(46, 8 + i);
				Game.cn.getTextWindow().output( "" + forScores.getData(), new TextAttributes(Color.orange));
				forNames = forNames.getLink();
				forScores = forScores.getLink();
				i++;
			}
		}
	
}
