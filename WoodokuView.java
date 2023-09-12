import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class WoodokuView extends JPanel{
	
	private static final int startX = 101;
	private static final int startY = 101;
	private static final int rows = 9;
	private static final int cols = 9;
	private static final int cellWidth = 44;
	private static final int thickLineThickness = 6;
	
	private Grid grid;
	private Block block1;
	private Block block2;
	private Block block3;
	
	private WoodokuController controller;
	private int numOfBlocksToPlace;
	private JLabel score;
	private JLabel gameOver;
	private JButton restart;
	private JButton menu;
	private boolean isGameOver;
	private boolean playable1;
	private boolean playable2;
	private boolean playable3;
	private int[] scores;

	public WoodokuView() {
		grid = new Grid(startX, startY, rows, cols, cellWidth);
		controller = new WoodokuController(this, this);
		spawnNew();
		numOfBlocksToPlace = 3;
		playable1 = true;
		playable2 = true;
		playable3 = true;
		isGameOver = false;
		
		
		score = new JLabel("Score: " + grid.getScore());
		score.setFont(new Font("SansSerif", Font.BOLD, 28));
		this.add(score);
		
		scores = new int[5];
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		grid.draw(g);
		g.setColor(Color.BLACK);
		score.setText("Score: " + grid.getScore());
		for(int i = 0; i < rows + 1; i++) {
		    g.drawLine(startX, startY + i*cellWidth,
		    		startX + cols*cellWidth, startY + i*cellWidth);
		    g.drawLine(startX + i*cellWidth, startY, 
		    		startX + i*cellWidth, startY + rows*cellWidth);
		}
		for(int i = 0; i < rows/3 + 1; i++) {
		    g2.setStroke(new BasicStroke(thickLineThickness));
		    g2.drawLine(startX,  startY + 3*i*cellWidth, 
		    		startX + cols*cellWidth, startY + 3*i*cellWidth);
		    g2.drawLine(startX + 3*i*cellWidth,  startY, 
		    		startX + 3*i*cellWidth, startY + rows*cellWidth);
		}
		
		g2.setStroke(new BasicStroke(1));
		spawn(g);
	}
	
	public void spawn() {
		spawn(this.getGraphics());
	}
	
	private void spawnNew() {
		block1 = new Block(51, 550, cellWidth, grid);
		block2 = new Block(233, 550, cellWidth, grid);
		block3 = new Block(415, 550, cellWidth, grid);
		playable1 = block1.isPlaceable();
		playable2 = block2.isPlaceable();
		playable3 = block3.isPlaceable();
		
		if(!playable1 && !playable2 && !playable3) {
			isGameOver = true;
			gameOver();
		}
	}
	
	private void spawn(Graphics g) {
		block1.draw(g);
		block2.draw(g);
		block3.draw(g);
	}
	
	
	public Block containsPoint(int x, int y) {
		if(block1.containsPoint(x, y)) {
			return block1;
		}
		if(block2.containsPoint(x, y)) {
			return block2;
		}
		if(block3.containsPoint(x, y)) {
			return block3;
		}
		return null;
	}
	
	public void updateBlock(Block block, int x, int y) {
		block.updatePosition(x, y);
		block.draw(this.getGraphics());
	}
	
	public void updateGrid(Block block) {
		grid.setScore(grid.getScore() + block.getScore());
		grid.update(block.getCoordinates());
		grid.draw(this.getGraphics());
		block.setCanMove(false);
		numOfBlocksToPlace--;
		if(block1.isCanMove()) {
			playable1 = block1.isPlaceable();
		}
		else {
			playable1 = false;
		}
		if(block2.isCanMove()) {
			playable2 = block2.isPlaceable();
		}
		else {
			playable2 = false;
		}
		if(block3.isCanMove()) {
			playable3 = block3.isPlaceable();
		}
		else {
			playable3 = false;
		}
		if(!playable1 && !playable2 && !playable3 && numOfBlocksToPlace > 0) {
			isGameOver = true;
			gameOver();
			return;
		}
		if(numOfBlocksToPlace == 0) {
			spawnNew();
			spawn(this.getGraphics());
			this.repaint();
			numOfBlocksToPlace = 3;
		}
	}
	
	public boolean checkPlacement(Block block) {
		return grid.canPlace(block.getCoordinates());
	}

	public boolean isGameOver() {
		return isGameOver;
	}
	
	private void gameOver() {
		this.setLayout(null);
		controller.endGame();
		
		restart = new JButton("Restart");
		restart.setBounds(100, 700, 150, 50); //or 500 for y
		restart.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	            WoodokuMain.changeContent();
	         }
	    });
		
		menu = new JButton("Menu");
		menu.setBounds(350, 700, 150, 50); //or 500 for y
		menu.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) { 
	            WoodokuMain.menu();
	         }
	    });
		
		gameOver = new JLabel("GAME OVER");
		gameOver.setBounds(187, 0, 225, 50);
		gameOver.setFont(new Font("SansSerif", Font.BOLD, 36));
		
		this.add(restart);
		this.add(gameOver);
		this.add(menu);
		score.setLocation(220, 50);
		
		updateLeaderboard();
		
		
	}
	
	public void updateLeaderboard() {
		int score = grid.getScore();
		boolean needsUpdate = false;
		
		TextIO.readFile("result.txt");
		String highscore = TextIO.getlnString();
		TextIO.readStandardInput();
		
		String[] leaderString = new String[5];
		leaderString = highscore.split(", ");
		
		for(int i = 0; i < 5; i++) {
			scores[i] = Integer.parseInt(leaderString[i]);
			if(!needsUpdate && score > scores[i]) {
				needsUpdate = true;
			}
		}
		Arrays.sort(scores);
		if(needsUpdate) {
			scores[0] = score;
		}
		
		Arrays.sort(scores);
		TextIO.writeFile("result.txt");
		
		String str = "";
		for(int i = 4; i > 0; i--) {
			str = str + scores[i] + ", ";
		}
		str = str + scores[0];
		
		TextIO.putln(str);
	}
	
	
}
