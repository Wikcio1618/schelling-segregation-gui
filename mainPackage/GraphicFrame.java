package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class GraphicFrame extends JFrame {
	private static final long serialVersionUID = -6342936101847016682L;
	private Society soc = new Society(this);
	private AgentsPane agentsPane = new AgentsPane(soc);
	private ControlPane controlPane = new ControlPane(soc, this);
	
	Timer timer = new Timer(true); // <-- Super important true
	private boolean running = false;
	private int gameSpeed = 2;
	static private int[] gameSpeedIntervals = {30, 10, 3, 1};
	
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
		
		timer.scheduleAtFixedRate(new TimerTask() {
			int lastRun = 0;
			@Override
			public void run() {
				if (running) {
					++lastRun;
					if (lastRun > gameSpeedIntervals[gameSpeed-1]) {
						lastRun = 0;
						soc.nextDay();
						repaintAgentsPane();
					}
				}
				
			}
		}, 1000, 25);
	}
	
	public void repaintAgentsPane() {
		agentsPane.repaint();
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setGameSpeed(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}


}
