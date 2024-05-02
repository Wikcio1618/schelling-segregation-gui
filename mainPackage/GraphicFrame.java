package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GraphicFrame extends JFrame {
	private static final long serialVersionUID = -6342936101847016682L;
	private Society soc = new Society();
	private AgentsPane agentsPane = new AgentsPane(soc);
	private ControlPane controlPane = new ControlPane(soc, this);
	
	public GraphicFrame() throws HeadlessException {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Segregation Model");
		this.setSize(900, 760);
//		this.setMinimumSize(new Dimension(900, 760));
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
	
		this.add(agentsPane, BorderLayout.WEST);
		this.add(controlPane, BorderLayout.EAST);
	}
	
	public void repaintAgentsPane() {
		agentsPane.repaint();
	}


}
