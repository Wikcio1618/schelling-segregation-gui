package mainPackage;

import java.awt.BorderLayout;
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
		
		this.setPreferredSize(new Dimension(900-270, 600));
		this.setBounds(0, 0, 900-270, 600);
		
//		this.setBackground(Color.BLACK);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		drawAgents(g);
		g2d.dispose();
	}
	
	public void drawAgents(Graphics g) {
		int gridSize = 500;
		int rightMargin = 40;
		int upMargin = 30;
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
				g.fillRect(rightMargin + j * rectSize, upMargin + i * rectSize, rectSize, rectSize);
			}
		
		g.setColor(Color.black);
		for (int i = 0; i <= soc.getSize(); i++) {
			g.drawLine(rightMargin, upMargin+i*rectSize, rightMargin+gridSize, upMargin+i*rectSize);
			g.drawLine(rightMargin+i*rectSize, upMargin, rightMargin+i*rectSize, upMargin+gridSize);
		}
	}


}
