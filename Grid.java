import java.awt.*;
import java.util.ArrayList;

public class Grid {
	
	private int startX;
	private int startY;
	private int rows;
	private int cols;
	private int cellWidth;
	private int[][] layout;
	private int[][] tempLayout;
	private int[][] tempLayout2;
	private int score;

	public Grid(int startX, int startY, int rows, int cols, int cellWidth) {
		this.startX = startX;
		this.startY = startY;
		this.rows = rows;
		this.cols = cols;
		this.cellWidth = cellWidth;
		layout = new int[rows][cols];
		tempLayout = new int[rows][cols];
		tempLayout2 = new int[rows][cols];
		score = 0;
	}
	public void update(ArrayList<int[]> array) {
		for(int[] i: array) {
			int r = (i[1] - 101)/44;
			int c = (i[0] - 101)/44;
			layout[r][c] = 1;
		}
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				tempLayout[r][c] = layout[r][c];
				tempLayout2[r][c] = layout[r][c];
			}
		}
		checkBoxes();
		checkRows(tempLayout);
		checkCols(tempLayout2);
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(tempLayout[r][c] == 0 || tempLayout2[r][c] == 0 || layout[r][c] == 0) {
					layout[r][c] = 0;
				}
				else {
					layout[r][c] = 1;
				}
			}
		}
	}
	
	public boolean canPlace(ArrayList<int[]> array) {
		for(int[] i: array) {
			int r = (i[1] - 101)/44;
			int c = (i[0] - 101)/44;
			if(layout[r][c] == 1) {
				return false;
			}
		}
		return true;
	}
	
	public void draw(Graphics g) {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				if(layout[r][c] == 1) {
					Color color = new Color(204, 102, 0);
					g.setColor(color);
					g.drawRect(startX + cellWidth*c, startY + cellWidth*r, cellWidth, cellWidth);
					g.fillRect(startX + cellWidth*c, startY + cellWidth*r, cellWidth, cellWidth);
				}
			}
		}
	}
	
	private void checkBoxes() {
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				if(layout[r*3][c*3] == 1 && layout[r*3][c*3 + 1] == 1 && layout[r*3][c*3 + 2] == 1 &&
				   layout[r*3 + 1][c*3] == 1 && layout[r*3 + 1][c*3 + 1] == 1 && layout[r*3 + 1][c*3 + 2] == 1 &&
				   layout[r*3 + 2][c*3] == 1 && layout[r*3 + 2][c*3 + 1] == 1 && layout[r*3 + 2][c*3 + 2] == 1) {
						for(int i = r*3; i < r*3 + 3; i++) {
							for(int j = c*3; j < c*3 + 3; j++) {
								layout[i][j] = 0;
								score++;
							}
						}
				} 
			}
		}
	}
	
	private void checkCols(int[][] temp) { //mixed up the rows and columns
		for(int r = 0; r < rows; r++) {
			if(temp[r][0] == 1 && temp[r][1] == 1 && temp[r][2] == 1 && temp[r][3] == 1 && 
					temp[r][4] == 1 && temp[r][5] == 1 && temp[r][6] == 1 && temp[r][7] == 1 &&
					temp[r][8] == 1) {
				for(int c = 0; c < cols; c++) {
					temp[r][c] = 0;
					score++;
				}
			}
		}
	}
	
	private void checkRows(int[][] temp) { //mixed up the rows and columns
		for(int c = 0; c < cols; c++) {
			if(temp[1][c] == 1 && temp[2][c] == 1 && temp[3][c] == 1 && temp[4][c] == 1 &&
					temp[5][c] == 1 && temp[6][c] == 1 && temp[7][c] == 1 && temp[8][c] == 1 && temp[0][c] == 1) {
				for(int r = 0; r < rows; r++) {
					temp[r][c] = 0;
					score++;
				}
			}
		}
	}
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
