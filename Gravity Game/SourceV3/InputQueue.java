import java.util.Random;

public class InputQueue {
	static Random random = new Random();
	public static Queue QueueInitialization(Queue queue){		
		for (int i = 0; i < 15; i++) {
			int token = random.nextInt(40) + 1;
			if (token >= 36)
				queue.enqueue('e');
			else if (token >= 27)
				queue.enqueue(Initialization.EARTH_SQUARE);
			else if (token >= 17)
				queue.enqueue(Initialization.BOULDER);
			else if (token == 16)
				queue.enqueue(Initialization.ROBOT);
			else if (token >= 12)
				queue.enqueue('3');
			else if (token >= 7)
				queue.enqueue('2');
			else
				queue.enqueue('1');
		}
	    return queue;	    
	}	
	public static void NewQueue(Queue queue) {
		queue.dequeue();
		int token = random.nextInt(40) + 1;
		if (token >= 36)
			queue.enqueue('e');
		else if (token >= 27)
			queue.enqueue(Initialization.EARTH_SQUARE);
		else if (token >= 17)
			queue.enqueue(Initialization.BOULDER);
		else if (token == 16)
			queue.enqueue(Initialization.ROBOT);
		else if (token >= 12)
			queue.enqueue('3');
		else if (token >= 7)
			queue.enqueue('2');
		else
			queue.enqueue('1');    
    	int size=queue.size();
		for(int i=0;i<size;i++) {
			Game.cn.getTextWindow().setCursorPosition(60+i,3);
			Game.cn.getTextWindow().output((char)queue.peek());
			queue.enqueue(queue.dequeue());}	
	}


	public static void ElementstoArea(Queue queue, Robots robots) {
		int x;
		int y;
		char key = (char) queue.peek();
		switch (key) {
		case '1':
			do {

				x = random.nextInt(Initialization.WIDTH - 2) + 1;
				y = random.nextInt(Initialization.HEIGHT - 2) + 1;

			} while (Initialization.field[y][x] != Initialization.EARTH_SQUARE
					&& Initialization.field[y][x] != Initialization.SPACE);
			Initialization.field[y][x] = key;
			break;
		case '2':
			do {

				x = random.nextInt(Initialization.WIDTH - 2) + 1;
				y = random.nextInt(Initialization.HEIGHT - 2) + 1;

			} while (Initialization.field[y][x] != Initialization.EARTH_SQUARE
					&& Initialization.field[y][x] != Initialization.SPACE);
			Initialization.field[y][x] = key;
			break;
		case '3':
			do {

				x = random.nextInt(Initialization.WIDTH - 2) + 1;
				y = random.nextInt(Initialization.HEIGHT - 2) + 1;

			} while (Initialization.field[y][x] != Initialization.EARTH_SQUARE
					&& Initialization.field[y][x] != Initialization.SPACE);
			Initialization.field[y][x] = key;
			break;	
		case Initialization.BOULDER:
			int token = random.nextInt(2);
			if (token == 0) {
			    do {
			        x = random.nextInt(Initialization.WIDTH - 2) + 1;
			        y = random.nextInt(Initialization.HEIGHT - 2) + 1;
			    } while (Initialization.field[y][x] != Initialization.EARTH_SQUARE && Initialization.field[y][x] != Initialization.SPACE);

			    Initialization.field[y][x] = Initialization.BOULDER;
			}
			if (token == 1) {
				do {

					x = random.nextInt(Initialization.WIDTH - 2) + 1;
					y = random.nextInt(Initialization.HEIGHT - 2) + 1;

				} while (Initialization.field[y][x] != Initialization.BOULDER);
				Initialization.field[y][x] = Initialization.EARTH_SQUARE;
			}
			break;
		case Initialization.EARTH_SQUARE:
			do {

				x = random.nextInt(Initialization.WIDTH - 2) + 1;
				y = random.nextInt(Initialization.HEIGHT - 2) + 1;

			} while (Initialization.field[y][x] != Initialization.SPACE);
			Initialization.field[y][x] = Initialization.EARTH_SQUARE;
			break;
		case 'e':
			do {

				x = random.nextInt(Initialization.WIDTH - 2) + 1;
				y = random.nextInt(Initialization.HEIGHT - 2) + 1;

			} while (Initialization.field[y][x] != Initialization.EARTH_SQUARE);
			Initialization.field[y][x] = Initialization.SPACE;
			break;
		case Initialization.ROBOT:
			Robots.addRobot(Initialization.WIDTH,Initialization.HEIGHT);  
			break;
		}
		displayMethods.gameArea();
	}
}
