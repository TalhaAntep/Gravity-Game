import java.util.*;

public class Backpack {
	private int backpack_size;

	public static Stack<Character> backpack = new Stack<Character>();

    public Backpack(int size) {
        this.backpack_size = size;
        this.backpack = new Stack<Character>();
    }
    
    public Character pop() {
        if (this.backpack.empty()) {
            throw new EmptyStackException();
        }
        return this.backpack.pop();
    }
    
    public void push(Character item) {
        if (this.isBackpackFull()) {
            throw new IllegalStateException("Backpack is full!");
        }
        this.backpack.push(item);
    }
    
	public boolean isBackpackFull() {
		return backpack.size() == this.backpack_size;
	}
	
	public int size() {
	    return this.backpack.size();
	}
	
	public void setBackpack() {

		if (backpack.size() >= 2 && backpack.peek().equals(backpack.elementAt(backpack.size() - 2))) {
			switch (backpack.peek()) {
			case '1':
				Initialization.SCORE += 10;
				break;
			case '2':
				Initialization.SCORE += 40;
				break;
			case '3':
				Initialization.SCORE += 90;
				Initialization.TP++;
				break;
			default:
				break;
			}
			backpack.pop();
			backpack.pop();
			if(Initialization.TP != 0) {
				for(int i = 1; i <= 23; i++) {
		        	Game.cn.getTextWindow().setCursorPosition(58+i, 16);
		        	Game.cn.getTextWindow().output(' ');
				}
			}

			// If at the top of two numbers of backpack is identical to delete top of the element
        	Game.cn.getTextWindow().setCursorPosition(67, 13 - backpack.size() -1);
        	Game.cn.getTextWindow().output(' ');
		}
		
	}


}