package mainPackage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPane extends JPanel {
	
	private Society soc;
	private GraphicFrame owner;
	private final JButton startButton, stopButton, resetButton, manualButton;
	private final JPanel speedPanel, tolerancePanel, vacantPanel, mainButtonsPanel, sizePanel, manualPanel;
	private final JLabel speedLabel, toleranceLabel, vacantLabel, sizeLabel, toleranceValueLabel, vacantValueLabel;
	private final JSlider speedSlider, toleranceSlider, vacantSlider, sizeSlider;
	
	public ControlPane(Society soc, GraphicFrame owner) {
		super();
		this.soc = soc;
		this.owner = owner;
		
		this.setPreferredSize(new Dimension(270, 760));
		this.setLayout(new GridLayout(10,1));
//		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		speedPanel = new JPanel();
		tolerancePanel = new JPanel();
		vacantPanel = new JPanel();
		mainButtonsPanel = new JPanel();
		sizePanel = new JPanel();
		manualPanel = new JPanel();
		
		mainButtonsPanel.setPreferredSize(new Dimension(100, 10));
		sizePanel.setPreferredSize(new Dimension(400, 20));
		tolerancePanel.setPreferredSize(new Dimension(400, 20));
		vacantPanel.setPreferredSize(new Dimension(400, 20));
		manualPanel.setPreferredSize(new Dimension(400, 20));
		
//		mainButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		sizePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		tolerancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		vacantPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		manualPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		
		manualButton = new JButton("Manual");
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		resetButton = new JButton("Reset");
		
		speedLabel = new JLabel("Simualtion speed", SwingConstants.CENTER);
		toleranceLabel = new JLabel("Tolerance", SwingConstants.CENTER);
		vacantLabel = new JLabel("Fraction of vacant", SwingConstants.CENTER);
		sizeLabel = new JLabel("Society Size", SwingConstants.CENTER);
		toleranceValueLabel = new JLabel(String.format("%.2f", soc.getTolerance()), SwingConstants.CENTER);
		vacantValueLabel = new JLabel(String.format("%.2f", soc.getFracVacant()), SwingConstants.CENTER);
		
		speedSlider = new JSlider(1, 4, 1);
		toleranceSlider = new JSlider(0, 100, (int)(soc.getTolerance() * 100));
		vacantSlider = new JSlider(5, 90, (int)(soc.getFracVacant() * 100));
		sizeSlider = new JSlider(100, 300, soc.getSize());
		
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		
//		toleranceSlider.setMajorTickSpacing(25);
//		toleranceSlider.setPaintLabels(true);
//		
//		vacantSlider.setMajorTickSpacing(5);
//		vacantSlider.setPaintLabels(true);
		
		sizeSlider.setMajorTickSpacing(100);
		sizeSlider.setPaintLabels(true);
		sizeSlider.setSnapToTicks(true);
		
		GridLayout gLay = new GridLayout(1, 3, 5, 5);
		mainButtonsPanel.setLayout(gLay);
		mainButtonsPanel.add(startButton);
		mainButtonsPanel.add(stopButton);
		mainButtonsPanel.add(resetButton);
		
		speedPanel.setLayout(new GridLayout(3, 1));
		speedPanel.add(speedLabel);
		speedPanel.add(speedSlider);
	
		sizePanel.setLayout(new GridLayout(3, 1));
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeSlider);
		
		tolerancePanel.setLayout(new GridLayout(3, 1));
		tolerancePanel.add(toleranceLabel);
		tolerancePanel.add(toleranceSlider);
		tolerancePanel.add(toleranceValueLabel);
		
		vacantPanel.setLayout(new GridLayout(3, 1));
		vacantPanel.add(vacantLabel);
		vacantPanel.add(vacantSlider);
		vacantPanel.add(vacantValueLabel);
		
		manualPanel.add(manualButton);
		
		this.add(mainButtonsPanel);
		this.add(speedPanel);
		this.add(new JSeparator());
		this.add(tolerancePanel);
		this.add(new JSeparator());
		this.add(vacantPanel);
		this.add(new JSeparator());
		this.add(sizePanel);
		this.add(new JSeparator());
		this.add(manualPanel);
		
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
				
		});

		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				soc.initSociety();
				owner.repaintAgentsPane();
			}
		});
		
		speedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
//				gameSpeed = gameSpeedSlider.getValue();
			}
		});
		
		toleranceSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double value = (double)toleranceSlider.getValue() / 100;
				soc.setTolerance(value);
				toleranceValueLabel.setText(String.format("%.2f", value));
			}
		});
		
		vacantSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double value = (double)vacantSlider.getValue() / 100;
				soc.setFracVacant(value);
				vacantValueLabel.setText(String.format("%.2f", value));
			}
		});
		
		sizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = sizeSlider.getValue();
				soc.setSize(value);
			}
		});
	}


}
