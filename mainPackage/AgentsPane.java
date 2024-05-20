package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AgentsPane extends JPanel {
	
	private Society soc;

	public AgentsPane(Society soc) {
		super();
		this.soc = soc;
		
		this.setPreferredSize(new Dimension(900-290, 760));
//		this.setBackground(Color.BLACK);
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		drawAgents(g);
		g2d.dispose();
	}
	
	public void drawAgents(Graphics g) {
		int gridSize = 600;
		int[][] fields = soc.getFields();
		int rectSize = (int) (gridSize / soc.getSize());
		for (int i = 0; i < soc.getSize(); i++)
			for (int j = 0; j < soc.getSize(); j++) {
				if (fields[i][j] == 1)
					g.setColor(new Color(0,128,128));
				else if (fields[i][j] == -1)
					g.setColor(new Color(210,105,30));
				else
					g.setColor(new Color(245,245,245));
				g.fillRect(5 + j * rectSize, 5 + i * rectSize, rectSize, rectSize);
			}
		
		g.setColor(Color.black);
		for (int i = 0; i <= soc.getSize(); i++) {
			g.drawLine(5, 5+i*rectSize, 5+gridSize, 5+i*rectSize);
			g.drawLine(5+i*rectSize, 5, 5+i*rectSize, 5+gridSize);
		}
	}


}
