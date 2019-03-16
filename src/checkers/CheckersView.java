package checkers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JComponent;

public class CheckersView extends JComponent{
	private CheckersGame gameState;
	private static final int SQUAREDIM = 100;
	private static final int BOARDDIM = SQUAREDIM * 8;
	private Dimension dimSize;
	private boolean dragging = false;
	private int deltaX, deltaY;
	
	public CheckersView(CheckersGame gameState) {
		this.gameState = gameState;
		dimSize = new Dimension(BOARDDIM, BOARDDIM);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
			}
			
			public void mouseReleased(MouseEvent me) {
				if(dragging)
					dragging = false;
				else
					return;
				int x = me.getX();
				int y = me.getY();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				if(dragging) {
					
				}
			}
		});
	}
	
	@Override
	public Dimension getPreferredSize() {
		return dimSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		paintCheckersBoard(g);
	}
	
	private void paintCheckersBoard(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int row=0;row<8;row++) {
			g.setColor(((row % 2) == 0) ? Color.WHITE : Color.BLACK);
			for(int col=0;col<8;col++) {
				g.fillRect(col * SQUAREDIM, row * SQUAREDIM, SQUAREDIM, SQUAREDIM);
				g.setColor((g.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE);
			}
		}
	}
}