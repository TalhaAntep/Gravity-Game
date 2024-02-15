import java.util.Random;

public class Boulders {
	public static void boulderFall() throws InterruptedException {
		for (int i = Initialization.HEIGHT - 1; i > 0; i--) {
			for (int j = 0; j < Initialization.WIDTH; j++) {

				if (Initialization.field[i][j] == Initialization.BOULDER) {

					int boulderY = 1;

					if (i + boulderY < Initialization.HEIGHT) {
						switch (Initialization.field[i + boulderY][j]) {

						case Initialization.SPACE: {
							Initialization.field[i][j] = Initialization.SPACE;
							Initialization.field[i + boulderY][j] = Initialization.BOULDER;
							break;
						}

						case Initialization.BOULDER: {
							switch (Initialization.DIRECTION) {
							case 1: { // left

								if (Initialization.field[i + boulderY][j - 1] == Initialization.PLAYER && Initialization.field[i][j - 1] == Initialization.SPACE) {
									Initialization.field[i][j] = Initialization.SPACE;
									Initialization.field[i + boulderY][j - 1] = Initialization.BOULDER;
									Game.gameOver = false;
									return;
								}

								else if (Initialization.field[i + boulderY][j - 1] == Initialization.SPACE && Initialization.field[i][j - 1] == Initialization.SPACE) {
									Initialization.field[i][j] = Initialization.SPACE;
									Initialization.field[i + boulderY][j - 1] = Initialization.BOULDER;
								}

								break;
							}
							case 2: { // right
								if (Initialization.field[i + boulderY][j + 1] == Initialization.PLAYER && Initialization.field[i][j + 1] == Initialization.SPACE) {
									Initialization.field[i][j] = Initialization.SPACE;
									Initialization.field[i + boulderY][j + 1] = Initialization.BOULDER;
									Game.gameOver = false;
									return;
								} else if (Initialization.field[i + boulderY][j + 1] == Initialization.SPACE && Initialization.field[i][j + 1] == Initialization.SPACE) {
									Initialization.field[i][j] = Initialization.SPACE;
									Initialization.field[i + boulderY][j + 1] = Initialization.BOULDER;
								}

								break;
							}
							case 3: { // random
								Random random = new Random();
								int token = random.nextInt(2);
								if (token == 0 && i + boulderY < Initialization.HEIGHT) {
									if (Initialization.field[i + boulderY][j - 1] == Initialization.PLAYER && Initialization.field[i][j - 1] == Initialization.SPACE) {
										Initialization.field[i][j] = Initialization.SPACE;
										Initialization.field[i + boulderY][j - 1] = Initialization.BOULDER;
										Game.gameOver = false;
										return;
									}

									else if (Initialization.field[i + boulderY][j - 1] == Initialization.SPACE && Initialization.field[i][j - 1] == Initialization.SPACE) {
										Initialization.field[i][j] = Initialization.SPACE;
										Initialization.field[i + boulderY][j - 1] = Initialization.BOULDER;
									}

								}
								if (token == 1 && i + boulderY < Initialization.HEIGHT) {

									if (Initialization.field[i + boulderY][j + 1] == Initialization.PLAYER && Initialization.field[i][j + 1] == Initialization.SPACE) {
										Initialization.field[i][j] = Initialization.SPACE;
										Initialization.field[i + boulderY][j + 1] = Initialization.BOULDER;
										Game.gameOver = false;
										return;
									}

									else if (Initialization.field[i + boulderY][j + 1] == Initialization.SPACE && Initialization.field[i][j + 1] == Initialization.SPACE) {
										Initialization.field[i][j] = Initialization.SPACE;
										Initialization.field[i + boulderY][j + 1] = Initialization.BOULDER;
									}

								}
								break;
							}

							default:
								break;
							}
							break;
						}

						case Initialization.ROBOT: {
							Initialization.field[i][j] = Initialization.SPACE;
							Initialization.field[i + boulderY][j] = Initialization.BOULDER;
							Robots.removeRobot(j, i + boulderY);
							Initialization.SCORE += 900;
							break;
						}
						}
					}

				}

			}
		}
	}
}
