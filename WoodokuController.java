import java.awt.event.*;

import javax.swing.JPanel;

public class WoodokuController implements MouseListener, MouseMotionListener{

	private WoodokuView mainPanel;
	private Block currentlyDraggedBlock;
	private int offsetX;
	private int offsetY;
	private int originalX;
	private int originalY;
	
	public WoodokuController(JPanel mainPanel, WoodokuView viewPanel) {
		this.mainPanel = viewPanel;
		mainPanel.addMouseListener(this); 
		mainPanel.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mainPanel.containsPoint(e.getX(),e.getY())==null) {
			return;
		}
		else {
			currentlyDraggedBlock = mainPanel.containsPoint(e.getX(),e.getY());
			originalX = currentlyDraggedBlock.getX();
			originalY = currentlyDraggedBlock.getY();
			offsetX = currentlyDraggedBlock.getX()-e.getX();
			offsetY = currentlyDraggedBlock.getY()-e.getY();
		}
		
		mainPanel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentlyDraggedBlock == null) {
			return;
		}
		if(currentlyDraggedBlock.outOfBounds()) {
			mainPanel.updateBlock(currentlyDraggedBlock, originalX, originalY);
		}
		else {
			currentlyDraggedBlock.snapToGrid();
			if(currentlyDraggedBlock.isSetX() == false || currentlyDraggedBlock.isSetY() == false) {
				mainPanel.updateBlock(currentlyDraggedBlock, originalX, originalY);
			}
			else {
				currentlyDraggedBlock.addCoordinates();
				if(!mainPanel.checkPlacement(currentlyDraggedBlock)) {
					mainPanel.updateBlock(currentlyDraggedBlock, originalX, originalY);
					currentlyDraggedBlock.removeCoordinates();
				}
				else {
					
					mainPanel.updateGrid(currentlyDraggedBlock);
				}
			}
		}
		currentlyDraggedBlock = null;
		mainPanel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentlyDraggedBlock == null) {
			return;
		}
		else {
			mainPanel.updateBlock(currentlyDraggedBlock, e.getX() + offsetX, e.getY() + offsetY);
		}
		mainPanel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void endGame() {
		mainPanel.removeMouseListener(this);
		mainPanel.removeMouseMotionListener(this);
	}

}
