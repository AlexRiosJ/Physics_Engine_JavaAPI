package com.rad.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Enviroment extends JFrame implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;

	private static Body ball1, ball2;
	private static Vec2D gravity;
	private static double COEFFICIENT_E;
	
	private JButton startButton;
	private JButton stopButton;
	private JButton playPauseButton;
	private JButton showValuesButton;
	private JButton showVectorsButton;

	private JLabel ball1Label;
	private JLabel sx1Label;
	private JLabel sy1Label;
	private JLabel vx1Label;
	private JLabel vy1Label;
	private JLabel mass1Label;
	private JLabel radius1Label;
	
	private JLabel ball2Label;
	private JLabel sx2Label;
	private JLabel sy2Label;
	private JLabel vx2Label;
	private JLabel vy2Label;
	private JLabel mass2Label;
	private JLabel radius2Label;
	
	private JLabel xGravityLabel;
	private JLabel yGravityLabel;
	private JLabel eLabel;
	private JLabel sliderLabel;

	private JSlider timeSlider;

	private JTextField sx1TextField;
	private JTextField sy1TextField;
	private JTextField vx1TextField;
	private JTextField vy1TextField;
	private JTextField mass1TextField;
	private JTextField radius1TextField;
	
	private JTextField sx2TextField;
	private JTextField sy2TextField;
	private JTextField vx2TextField;
	private JTextField vy2TextField;
	private JTextField mass2TextField;
	private JTextField radius2TextField;
	
	private JTextField xGravityTextField;
	private JTextField yGravityTextField;
	private JTextField eTextField;

	private JPanel drawingPanel;
	private GridBagConstraints gbc;
	private Updater updater;
	private Timer timer;
	private boolean isPlaying   = true;
	private boolean isStoped    = false;
	private boolean showValues  = false;
	private boolean showVectors = false;
	
	static {
		gravity = new Vec2D(0, 0);
		ball1   = new Body(new Vec2D(0,0), new Vec2D(0,0), gravity, new Vec2D(0,0), 0, 1);
		ball2   = new Body(new Vec2D(0,0), new Vec2D(0,0), gravity, new Vec2D(0,0), 0, 1);
	}

	public Enviroment() {

		// Create a Timer object that will be used
		// to slow the action down and an ActionListener
		// that the Timer will call. The timeDelay variable
		// is the time delay in milliseconds.
		updater = new Updater();
		int timeDelay = 5;
		timer = new Timer(timeDelay, updater);

		// Create JTextField objects to input the initial
		// velocity components of the ball.
		sx1TextField = new JTextField("100", 4);
		sy1TextField = new JTextField("100", 4);
		vx1TextField = new JTextField("0", 4);
		vy1TextField = new JTextField("0", 4);
		mass1TextField = new JTextField("10", 4);
		radius1TextField = new JTextField("10", 4);
		sx2TextField = new JTextField("20", 4);
		sy2TextField = new JTextField("20", 4);
		vx2TextField = new JTextField("0", 4);
		vy2TextField = new JTextField("0", 4);
		mass2TextField = new JTextField("20", 4);
		radius2TextField = new JTextField("20", 4);
		xGravityTextField = new JTextField("0", 4);
		yGravityTextField = new JTextField("-9.8", 4);
		eTextField  = new JTextField("1", 4);

		// Create some JLabels
		ball1Label = new JLabel("Red Ball");
		ball2Label = new JLabel("Blue Ball");
		sx1Label = new JLabel("x Position");
		sy1Label = new JLabel("y Position");
		vx1Label = new JLabel("x Velocity");
		vy1Label = new JLabel("y Velocity");
		mass1Label = new JLabel("Mass");
		radius1Label = new JLabel("Radius");
		sx2Label = new JLabel("x Position");
		sy2Label = new JLabel("y Position");
		vx2Label = new JLabel("x Velocity");
		vy2Label = new JLabel("y Velocity");
		mass2Label = new JLabel("Mass");
		radius2Label = new JLabel("Radius");
		xGravityLabel = new JLabel("x Gravity");
		yGravityLabel = new JLabel("y Gravity");
		eLabel  = new JLabel("Coefficient e");
		sliderLabel = new JLabel(String.format("Time"));

		// Create a JButton that will start the simulation
		startButton = new JButton("Start");
		startButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		startButton.setPreferredSize(new Dimension(60, 35));
		startButton.addActionListener(this);

		// Create a JButton that will stop the simulation
		stopButton = new JButton("■");
		stopButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		stopButton.setPreferredSize(new Dimension(60, 35));
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				timer.stop();
				isStoped = true;
				updateDisplay();
			}
		});

		// Create a JButton that will play/pause the balls moving
		playPauseButton = new JButton(String.format("►ll"));
		playPauseButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playPauseButton.setPreferredSize(new Dimension(60, 35));
		playPauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(isPlaying) {
					updater.setTimeIncrement(0);
					isPlaying = false;
				} else {
					updater.setTimeIncrement((double) 0.01 * timeSlider.getValue());
					isPlaying = true;
				}
			}
		});
		
		showValuesButton = new JButton(String.format("Values"));
		showValuesButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		showValuesButton.setPreferredSize(new Dimension(60, 35));
		showValuesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				showValues = !showValues;
			}
		});
		
		showVectorsButton = new JButton(String.format("Vectors"));
		showVectorsButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		showVectorsButton.setPreferredSize(new Dimension(60, 35));
		showVectorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				showVectors = !showVectors;
			}
		});

		// Create a JTextArea that will display the results
		drawingPanel = new JPanel();
		drawingPanel.setPreferredSize(new Dimension(600, 400));

		// Place components on a panel using a GridBagLayout
		JPanel westPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		westPanel.setLayout(gridBagLayout);

		// Create a new slider that will change the time increment
		timeSlider = new JSlider(JSlider.HORIZONTAL, 1, 11, 6);
		timeSlider.addChangeListener(this);

		Insets insets = new Insets(5, 5, 5, 5);
		
		// Start, Reset, Pause/Play, Values Buttons
		gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(startButton, gbc);
		gbc = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(playPauseButton, gbc);
		gbc = new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(stopButton, gbc);
		gbc = new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(showValuesButton, gbc);
		gbc = new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(showVectorsButton, gbc);
		
		// Red Ball Labels
		gbc = new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(ball1Label, gbc);
		
		gbc = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sx1Label, gbc);
		gbc = new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sx1TextField, gbc);
		
		gbc = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sy1Label, gbc);
		gbc = new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sy1TextField, gbc);
		
		gbc = new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vx1Label, gbc);
		gbc = new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vx1TextField, gbc);
	
		gbc = new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vy1Label, gbc);
		gbc = new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vy1TextField, gbc);
		
		gbc = new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(mass1Label, gbc);
		gbc = new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(mass1TextField, gbc);
		
		gbc = new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(radius1Label, gbc);
		gbc = new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(radius1TextField, gbc);
		
		// Blue Ball Labels
		gbc = new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(ball2Label, gbc);
		
		gbc = new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sx2Label, gbc);
		gbc = new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sx2TextField, gbc);
		
		gbc = new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sy2Label, gbc);
		gbc = new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sy2TextField, gbc);
		
		gbc = new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vx2Label, gbc);
		gbc = new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vx2TextField, gbc);
		
		gbc = new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vy2Label, gbc);
		gbc = new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vy2TextField, gbc);
		
		gbc = new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(mass2Label, gbc);
		gbc = new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(mass2TextField, gbc);
		
		gbc = new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(radius2Label, gbc);
		gbc = new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(radius2TextField, gbc);
		
		gbc = new GridBagConstraints(0, 8, 2, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(eLabel, gbc);
		gbc = new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(eTextField, gbc);
		
		gbc = new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(xGravityLabel, gbc);
		gbc = new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(xGravityTextField, gbc);
		
		gbc = new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(yGravityLabel, gbc);
		gbc = new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(yGravityTextField, gbc);
		
		gbc = new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sliderLabel, gbc);
		gbc = new GridBagConstraints(1, 11, 3, 1, 100, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(timeSlider, gbc);

		westPanel.add(startButton);
		westPanel.add(playPauseButton);
		westPanel.add(stopButton);
		westPanel.add(showValuesButton);
		westPanel.add(showVectorsButton);
		westPanel.add(ball1Label);
		westPanel.add(sx1Label);
		westPanel.add(sx1TextField);
		westPanel.add(sy1Label);
		westPanel.add(sy1TextField);
		westPanel.add(vx1Label);
		westPanel.add(vx1TextField);
		westPanel.add(vy1Label);
		westPanel.add(vy1TextField);
		westPanel.add(mass1Label);
		westPanel.add(mass1TextField);
		westPanel.add(radius1Label);
		westPanel.add(radius1TextField);
		westPanel.add(ball2Label);
		westPanel.add(sx2Label);
		westPanel.add(sx2TextField);
		westPanel.add(sy2Label);
		westPanel.add(sy2TextField);
		westPanel.add(vx2Label);
		westPanel.add(vx2TextField);
		westPanel.add(vy2Label);
		westPanel.add(vy2TextField);
		westPanel.add(mass2Label);
		westPanel.add(mass2TextField);
		westPanel.add(radius2Label);
		westPanel.add(radius2TextField);
		westPanel.add(eLabel);
		westPanel.add(eTextField);
		westPanel.add(xGravityLabel);
		westPanel.add(xGravityTextField);
		westPanel.add(yGravityLabel);
		westPanel.add(yGravityTextField);
		westPanel.add(sliderLabel);
		westPanel.add(timeSlider);

		// The drawing panel.
		JPanel eastPanel = new JPanel();
		GridBagLayout gridBagLayout2 = new GridBagLayout();
		eastPanel.setLayout(gridBagLayout2);
		gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(20, 20, 20, 20), 0, 0);
		gridBagLayout2.setConstraints(drawingPanel, gbc);

		eastPanel.add(drawingPanel);

		// Add the JPanel objects to the content pane
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(eastPanel, BorderLayout.EAST);
		getContentPane().add(westPanel, BorderLayout.WEST);

		image = new BufferedImage(drawingPanel.getPreferredSize().width, drawingPanel.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);

		// Add a title to the JFrame, size it, and make it visible.
		setTitle("Enviroment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 500);
		setVisible(true);

		// Update the GUI display
		// drawDisplay();
		updateDisplay();
	}

	// stateChanged() is called when the slider is moved.
	@Override
	public void stateChanged(ChangeEvent e) {
		if(isPlaying) {
			updater.setTimeIncrement((double) 0.01 * timeSlider.getValue());
		}
		updateDisplay();
	}

	// The actionPerformed() method is called when
	// the "Start" button is pressed.
	public void actionPerformed(ActionEvent event) {
		// Get the initial quantities from the textfields.
		try {
			ball1.getPosition().setX(Double.parseDouble(sx1TextField.getText()));
			ball1.getPosition().setY(Double.parseDouble(sy1TextField.getText()));
			ball1.getVelocity().setX(Double.parseDouble(vx1TextField.getText()));
			ball1.getVelocity().setY(Double.parseDouble(vy1TextField.getText()));
			ball1.setMass(Double.parseDouble(mass1TextField.getText()));
			ball1.setRadius(Double.parseDouble(radius1TextField.getText()));
			ball2.getPosition().setX(Double.parseDouble(sx2TextField.getText()));
			ball2.getPosition().setY(Double.parseDouble(sy2TextField.getText()));
			ball2.getVelocity().setX(Double.parseDouble(vx2TextField.getText()));
			ball2.getVelocity().setY(Double.parseDouble(vy2TextField.getText()));
			ball2.setMass(Double.parseDouble(mass2TextField.getText()));
			ball2.setRadius(Double.parseDouble(radius2TextField.getText()));
			gravity.setX(Double.parseDouble(xGravityTextField.getText()));
			gravity.setY(Double.parseDouble(yGravityTextField.getText()));
			COEFFICIENT_E = Double.parseDouble(eTextField.getText());
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Wrong input values!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch(NegativeMassException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);
		}
		isStoped = false;
		updater.setTimeIncrement((double) 0.01 * timeSlider.getValue());
		isPlaying = true;
		timer.start();
	} // End of the constructor

	BufferedImage image = null;
	
	private void updateDisplay() {

		Graphics2D g = (Graphics2D) image.getGraphics();
		int width = drawingPanel.getWidth();
		int height = drawingPanel.getHeight();
		g.clearRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// Draw outline of game area.
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, width - 1, 0);
		g.drawLine(width - 1, 0, width - 1, height - 1);
		g.drawLine(width - 1, height - 1, 0, height - 1);
		g.drawLine(0, 0, 0, height);

		// Update the position of the ball on the screen.
		int xPosition = (int) (ball1.getPosition().getX() - ball1.getRadius());
		int yPosition = (int) (height - ball1.getRadius() - ball1.getPosition().getY());
		g.setColor(Color.RED);
		g.fillOval(xPosition, yPosition, 2 * (int) (ball1.getRadius()), 2 * (int) (ball1.getRadius()));
		
		// Update the position of the second ball on the screen.
		g.setColor(Color.BLUE);
		int xPosition2 = (int) (ball2.getPosition().getX() - ball2.getRadius());
		int yPosition2 = (int) (height - ball2.getRadius() - ball2.getPosition().getY());
		g.fillOval(xPosition2, yPosition2, 2 * (int) (ball2.getRadius()), 2 * (int) (ball2.getRadius()));
		
		if(!isPlaying) {
			g.setColor(Color.BLACK);
			g.fillRect(10, 10, 3, 10);
			g.fillRect(15, 10, 3, 10);
		}
		
		if(isStoped) {
			g.setColor(Color.RED);
			g.fillRect(10, 10, 10, 10);
		}
		
		if(showValues) {
			g.setColor(Color.BLACK);
			
			g.drawString("Speed",      10, 350);
			g.drawString("X Position", 10, 360);
			g.drawString("Y Position", 10, 370);
			g.drawString("Weight",     10, 380);
			g.drawString("Density",    10, 390);
			
			g.drawString(String.format("%.2f",    ball1.getVelocity().getMagnitude()), xPosition, yPosition - 40);
			g.drawString(String.format("x: %.2f", ball1.getPosition().getX()), xPosition, yPosition - 30);
			g.drawString(String.format("y: %.2f", ball1.getPosition().getY()), xPosition, yPosition - 20);
			g.drawString(String.format("%.2f",    ball1.getMass() * gravity.getMagnitude()), xPosition, yPosition - 10);
			g.drawString(String.format("%.2f",    ball1.getDensity()), xPosition, yPosition);
			
			g.drawString(String.format("%.2f",    ball2.getVelocity().getMagnitude()), xPosition2, yPosition2 - 40);
			g.drawString(String.format("x: %.2f", ball2.getPosition().getX()), xPosition2, yPosition2 - 30);
			g.drawString(String.format("y: %.2f", ball2.getPosition().getY()), xPosition2, yPosition2 - 20);
			g.drawString(String.format("%.2f",    ball2.getMass() * gravity.getMagnitude()), xPosition2, yPosition2 - 10);
			g.drawString(String.format("%.2f",    ball2.getDensity()), xPosition2, yPosition2);
		}
		
		if(showVectors) {
			g.setColor(Color.MAGENTA);
			g.drawLine(xPosition  + (int) ball1.getRadius(), yPosition  + (int) ball1.getRadius(), xPosition  + (int) ball1.getRadius() + (int) ball1.getVelocity().getX(), yPosition  + (int) ball1.getRadius() - (int) ball1.getVelocity().getY());
			g.drawLine(xPosition2 + (int) ball2.getRadius(), yPosition2 + (int) ball2.getRadius(), xPosition2 + (int) ball2.getRadius() + (int) ball2.getVelocity().getX(), yPosition2 + (int) ball2.getRadius() - (int) ball2.getVelocity().getY());
			g.drawString("Velocity",      10, 310);
			g.setColor(Color.GREEN);
			g.drawLine(xPosition  + (int) ball1.getRadius(), yPosition  + (int) ball1.getRadius(), xPosition  + (int) ball1.getRadius() + (int) ball1.getVelocity().getX(), yPosition  + (int) ball1.getRadius());
			g.drawLine(xPosition  + (int) ball1.getRadius(), yPosition  + (int) ball1.getRadius(), xPosition  + (int) ball1.getRadius(), yPosition  + (int) ball1.getRadius() - (int) ball1.getVelocity().getY());
			g.drawLine(xPosition2 + (int) ball2.getRadius(), yPosition2 + (int) ball2.getRadius(), xPosition2 + (int) ball2.getRadius() + (int) ball2.getVelocity().getX(), yPosition2 + (int) ball2.getRadius());
			g.drawLine(xPosition2 + (int) ball2.getRadius(), yPosition2 + (int) ball2.getRadius(), xPosition2 + (int) ball2.getRadius(), yPosition2 + (int) ball2.getRadius() - (int) ball2.getVelocity().getY());
			g.drawString("x Velocity",    10, 320);
			g.drawString("y Velocity",    10, 330);
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, height, xPosition  + (int) ball1.getRadius(), yPosition  + (int) ball1.getRadius());
			g.drawLine(0, height, xPosition2 + (int) ball2.getRadius(), yPosition2 + (int) ball2.getRadius());
			g.drawString("Position",      10, 340);
		}
		Graphics g1 = drawingPanel.getGraphics();
		g1.drawImage(image, 0, 0, this);
	}

	class Updater implements ActionListener {

		private double timeIncrement;

		public void setTimeIncrement(double value) {
			this.timeIncrement = value;
		}

		public double getTimeIncrement() {
			return timeIncrement;
		}

		public void actionPerformed(ActionEvent event) {

			// Get dimensions of drawing area
			int width  = drawingPanel.getWidth();
			int height = drawingPanel.getHeight();

			Boundary boundary = Boundary.getInstance(height, 0, width, 0);

			try {
				// ball1 Boundary test
				if (Contact.testBoundaryOverlap(ball1, boundary, Contact.BOTTOM)
						|| Contact.testBoundaryOverlap(ball1, boundary, Contact.TOP))
					Contact.collisionHandler(ball1, COEFFICIENT_E, Contact.Y);
				if (Contact.testBoundaryOverlap(ball1, boundary, Contact.LEFT)
						|| Contact.testBoundaryOverlap(ball1, boundary, Contact.RIGHT))
					Contact.collisionHandler(ball1, COEFFICIENT_E, Contact.X);
	
				// ball2 Boundary test
				if (Contact.testBoundaryOverlap(ball2, boundary, Contact.BOTTOM)
						|| Contact.testBoundaryOverlap(ball2, boundary, Contact.TOP))
					Contact.collisionHandler(ball2, COEFFICIENT_E, Contact.Y);
				if (Contact.testBoundaryOverlap(ball2, boundary, Contact.LEFT)
						|| Contact.testBoundaryOverlap(ball2, boundary, Contact.RIGHT))
					Contact.collisionHandler(ball2, COEFFICIENT_E, Contact.X);
	
				if (Contact.testBodiesOverlap(ball1, ball2)) {
					Contact.collisionHandler(ball1, ball2, COEFFICIENT_E);
				}
			} catch(InvalidCoefficientOfRestitutionException e) {
				timer.stop();
				isStoped = true;
				JOptionPane.showMessageDialog(null, e.getMessage(), e.toString(), JOptionPane.ERROR_MESSAGE);
			}

			ball1.updateConstAcc(timeIncrement);
			ball2.updateConstAcc(timeIncrement);
			updateDisplay();
		}

	} // End of class Updater

}
