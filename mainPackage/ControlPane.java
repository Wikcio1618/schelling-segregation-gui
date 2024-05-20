package mainPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPane extends JPanel {
	
	private Society soc;
	private GraphicFrame owner;
	private final JButton stepButton, startButton, stopButton, resetButton, manualButton;
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
		stepButton = new JButton("Step");
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		resetButton = new JButton("Reset");
		
		speedLabel = new JLabel("Simualtion Speed", SwingConstants.CENTER);
		toleranceLabel = new JLabel("Satisfaction Demand", SwingConstants.CENTER);
		vacantLabel = new JLabel("Fraction of Vacant", SwingConstants.CENTER);
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
		
		sizeButton1 = new JRadioButton("30");
		sizeButton2 = new JRadioButton("60");
		sizeButton2.setSelected(true);
		sizeButton3 = new JRadioButton("100");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(sizeButton1);
		radioGroup.add(sizeButton2);
		radioGroup.add(sizeButton3);
		
		GridLayout gLay = new GridLayout(2, 2, 10, 5);
		mainButtonsPanel.setLayout(gLay);
		mainButtonsPanel.add(stepButton);
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
		
		stepButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				soc.nextDay();
				owner.repaintAgentsPane();
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				owner.setRunning(true);
				owner.repaintAgentsPane();
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
				int value = 30;
				soc.setSize(value);
			}
		});
		
		sizeButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = 60;
				soc.setSize(value);
			}
		});
		
		sizeButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = 100;
				soc.setSize(value);
			}
		});
		
		manualButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Manual\n"
						+ "The program impements the famous Schelling's segregation model in its basic form.\n"
						+ "There are 2 types of agents, who assume different positions on a square grid. Some places on the grid are vacant\n"
						+ "The number of agents of each type is the same \n\n"
						+ "The program allows to control some parameters of the model:\n"
						+ "- Satisfaction demand is a number between 0 and 1. If the fraction of similar, neighbouring (8 nearest) agents\n"
						+ "is less that this parameter, the agent will relocate randomly to a vacant place\n"
						+ "- The fraction of vacant places\n"
						+ "- Size of the grid as number of rows and columns\n\n"
						+ "The user can run the asynchronous simulation, stop it, reset the agents positions or move the evolution by a single step\n\n"
						+ "The author of this program is Wiktor C., who created it as part of Politechnika Warszawska - Sociophysics classess\n"
						+ "The source code can be found on github.com/Wikcio1618 as of may 2024",
						"Manual", JOptionPane.DEFAULT_OPTION);
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
		stepButton.setEnabled(b);
		resetButton.setEnabled(b);
	}


}
