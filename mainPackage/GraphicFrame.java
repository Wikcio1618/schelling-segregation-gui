package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicFrame extends JFrame {
	private static final long serialVersionUID = -6342936101847016682L;
	private Society soc = new Society(this);
	private AgentsPane agentsPane = new AgentsPane(soc);
	private ControlPane controlPane = new ControlPane(soc, this);
	private ChartPane chartPane = new ChartPane(soc);
	private JPanel simulationPane = new JPanel();
	
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
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setRunning(false);
				System.exit(0);
			}
		});
		
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		
		simulationPane.setLayout(new BorderLayout());
		simulationPane.setPreferredSize(new Dimension(900-270, 760));
		simulationPane.setBounds(0, 0, 900-270, 760);
		simulationPane.add(chartPane, BorderLayout.SOUTH);
		simulationPane.add(agentsPane, BorderLayout.CENTER);
		
		controlPane.setBounds(900-270, 0, 270, 760);
		
		this.add(controlPane, BorderLayout.EAST);
		this.add(simulationPane, BorderLayout.WEST);
		
		timer.scheduleAtFixedRate(new TimerTask() {
			int lastRun = 0;
			@Override
			public void run() {
				if (running) {
					++lastRun;
					if (lastRun > gameSpeedIntervals[gameSpeed-1]) {
						lastRun = 0;
						soc.nextDay();
						if (soc.getDay() % 5 == 0)
							chartPane.addXYValue(soc.getDay(), soc.getNeighbourhoodLikeness());
						repaintAgentsPane();
					}
				}
				
			}
		}, 1000, 5);
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

	public ChartPane getChartPane() {
		return chartPane;
	}


}
