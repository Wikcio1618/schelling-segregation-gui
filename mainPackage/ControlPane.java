package mainPackage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private final JSlider speedSlider, toleranceSlider, vacantSlider;
	private final JRadioButton sizeButton1, sizeButton2, sizeButton3;
	private final ButtonGroup radioGroup;
	
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
		
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintLabels(true);
		speedSlider.setSnapToTicks(true);
		
//		toleranceSlider.setMajorTickSpacing(25);
//		toleranceSlider.setPaintLabels(true);
//		
//		vacantSlider.setMajorTickSpacing(5);
//		vacantSlider.setPaintLabels(true);
		
		sizeButton1 = new JRadioButton("100");
		sizeButton2 = new JRadioButton("200");
		sizeButton2.setSelected(true);
		sizeButton3 = new JRadioButton("300");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(sizeButton1);
		radioGroup.add(sizeButton2);
		radioGroup.add(sizeButton3);
		
		GridLayout gLay = new GridLayout(1, 3, 5, 5);
		mainButtonsPanel.setLayout(gLay);
		mainButtonsPanel.add(startButton);
		mainButtonsPanel.add(stopButton);
		mainButtonsPanel.add(resetButton);
		
		speedPanel.setLayout(new GridLayout(3, 1));
		speedPanel.add(speedLabel);
		speedPanel.add(speedSlider);
	
		sizePanel.setLayout(new GridLayout(4, 1));
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeButton1);
		sizePanel.add(sizeButton2);
		sizePanel.add(sizeButton3);
		
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
				owner.setRunning(true);
				stopButton.setEnabled(true);
				startButton.setEnabled(false);
				changesAvailable(false);
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setRunning(false);
				stopButton.setEnabled(false);
				startButton.setEnabled(true);
				changesAvailable(true);
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
				owner.setGameSpeed(speedSlider.getValue());
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
		
		sizeButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = 10;
				soc.setSize(value);
			}
		});
		
		sizeButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = 200;
				soc.setSize(value);
			}
		});
		
		sizeButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = 300;
				soc.setSize(value);
			}
		});
		
	}
	
	public void changesAvailable(boolean b) {
		toleranceSlider.setEnabled(b);
		vacantSlider.setEnabled(b);
		sizeButton1.setEnabled(b);
		sizeButton2.setEnabled(b);
		sizeButton3.setEnabled(b);
		speedSlider.setEnabled(b);
		resetButton.setEnabled(b);
	}


}
