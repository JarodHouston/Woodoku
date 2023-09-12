import java.awt.*;
import java.util.ArrayList;

public class Block {

	private int x;
	private int y;
	private int width;
	private boolean canMove = true;
	private int[][] blockComposition;
	private ArrayList<int[]> coordinates;
	private ArrayList<int[]> moveable;
	private boolean setX;
	private boolean setY;
	private int score;
	private Grid grid;
	
	public Block(int x, int y, int cellWidth, Grid grid) {
		this.x = x;
		this.y = y;
		this.grid = grid;
		this.width = cellWidth - 2;
		BlockComposition compositionReference = new BlockComposition();
		blockComposition = compositionReference.getComposition();
		coordinates = new ArrayList<int[]>();
		moveable = new ArrayList<int[]>();
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1) {
					score++;
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1 && canMove) {
					g.setColor(Color.BLACK);
					g2.drawRect(x + width*c + c, y + width*r + r, width, width); 
					Color color = new Color(204, 102, 0);
					g.setColor(color);
					g.fillRect(x + width*c + c, y + width*r + r, width, width);
				}
			}
		}
	}
	
	public boolean containsPoint(int xPres, int yPres) {
		if(!canMove) {
			return false;
		}
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1) {
					if(xPres >= x + width*c + c && xPres <= x + width*c + c + width &&
							yPres >= y + width*r + r && yPres <= y + width*r + r + width) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void updatePosition(int xPos, int yPos) {
		x = xPos;
		y = yPos;
	}
	
	public boolean outOfBounds() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1) {
					if(x + width*c + c < 91 || x + width*c + c + width > 517 ||
							y + width*r + r < 91 || y + width*r + r + width > 517) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void snapToGrid() {
		setX = false;
		setY = false;
		for(int i = 0; i < 11; i++) {
			if(Math.abs(x - (13 + 44*i + 2)) <= 20) {
				x = 13 + 44*i + 2;
				setX = true;
			}
		}
		for(int i = 0; i < 11; i++) {
			if(Math.abs(y - (13 + 44*i + 2)) <= 20) {
				y = 13 + 44*i + 2;
				setY = true;
			}
		}
	}
	
	public void addCoordinates() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1) {
					coordinates.add(new int[] {x + width*c + c, y + width*r + r});
				}
			}
		}
	}
	
	public void removeCoordinates() {
		coordinates = new ArrayList<int[]>();
	}
	
	public boolean isPlaceable() {
		moveable = new ArrayList<int[]>();
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(blockComposition[r][c] == 1) {
					moveable.add(new int[] {101 + 44*c, 101 + 44*r});
				}
			}
		}
		int leastX = 1000;
		int mostX = 0;
		int leastY = 1000;
		int mostY = 0;
		for(int i = 0; i < moveable.size(); i++) {
			if(moveable.get(i)[0] < leastX) {
				leastX = moveable.get(i)[0];
			}
			if(moveable.get(i)[0] > mostX) {
				mostX = moveable.get(i)[0];
			}
			if(moveable.get(i)[1] < leastY) {
				leastY = moveable.get(i)[1];
			}
			if(moveable.get(i)[1] > mostY) {
				mostY = moveable.get(i)[1];
			}
		}
		while(leastX > 101) {
			for(int[] i : moveable) {
				i[0] = i[0] - 44 - 0;
			}
			leastX = leastX - 44;
			mostX = mostX - 44;
		}
		while(leastY > 101) {
			for(int[] i : moveable) {
				i[1] = i[1] - 44 - 0;
			}
			leastY = leastY - 44;
			mostY = mostY - 44;
		}
		//mostX + 44*num = 497
		//44*num = 497 - mostX
		//num = (497 - mostX)/44
		for(int i = 0; i < (497 - mostX)/44; i++) {
			for(int j = 0; j < (497 - mostY)/44; j++) {
				if(grid.canPlace(moveable)) {
					return true;
				}
				for(int[] k : moveable) {
					k[1] = k[1] + 44 + 0;
				}
			}
			for(int[] k : moveable) {
				k[1] = k[1] - 44*(497 - mostY)/44;
			}
			for(int[] m : moveable) {
				m[0] = m[0] + 44 + 0;
			}
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isCanMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public ArrayList<int[]> getCoordinates() {
		return coordinates;
	}

	public boolean isSetX() {
		return setX;
	}

	public boolean isSetY() {
		return setY;
	}

	public int getScore() {
		return score;
	}
	
	
	
	
}
