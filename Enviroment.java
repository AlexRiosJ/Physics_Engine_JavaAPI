package com.rad.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Enviroment extends JFrame implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	private Body ball;
	
	private JButton startButton;
	private JButton resetButton;
	private JButton pauseButton;
	private JButton playButton;
	
	private JLabel sxLabel;
	private JLabel syLabel;
	private JLabel vxLabel;
	private JLabel vyLabel;
	private JLabel sliderLabel;
	
	private JSlider timeSlider;
	
	private JTextField sxTextField;
	private JTextField syTextField;
	private JTextField vxTextField;
	private JTextField vyTextField;
	
	private JPanel drawingPanel;
	private GridBagConstraints gbc;
	private Updater updater;
	private Timer gameTimer;
	
	public Enviroment() {
		
		//Quitar luego
		Vec2D ballV = new Vec2D(100, -71);
		Vec2D ballP = new Vec2D(100, 100);
		Vec2D gravity = new Vec2D(0, -3);
		double mass = 10;
		ball = new Body(ballP, ballV, gravity, ballV, 0, mass);
		ball.setRadius(15);
		ball.setCoefficientOfRestitution(0.9);
		ball.setMass(1);
		//
		
		// Create a Timer object that will be used
		// to slow the action down and an ActionListener
		// that the Timer will call. The timeDelay variable
		// is the time delay in milliseconds.
		updater = new Updater(ball);
		int timeDelay = 1;
		gameTimer = new Timer(timeDelay, updater);
		
		// Create JTextField objects to input the initial
		// velocity components of the ball.
		sxTextField = new JTextField("20", 6);
		syTextField = new JTextField("20", 6);
		vxTextField = new JTextField("0", 6);
		vyTextField = new JTextField("0", 6);
		
		// Create some JLabels
		sxLabel = new JLabel("x position");
		syLabel = new JLabel("y position");
		vxLabel = new JLabel("x velocity");
		vyLabel = new JLabel("y velocity");
		sliderLabel = new JLabel(String.format("Time increment"));
		
		// Create a JButton that will start the balls moving
		startButton = new JButton("Start");
		startButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		startButton.setPreferredSize(new Dimension(60, 35));
		startButton.addActionListener(this);

		// Create a JButton that will update the drawing area.
		resetButton = new JButton("Reset");
		resetButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		resetButton.setPreferredSize(new Dimension(60, 35));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// Stop the timer.
				gameTimer.stop();
				// Update the display.
				
				// Reset the ball location, velocity, and
				// paddle location.
				ball.getPosition().setX(20);
				ball.getPosition().setY(20);
				ball.getVelocity().setX(0);
				ball.getVelocity().setY(0);
				timeSlider.setValue((int) updater.getTimeIncrement()); 
				
				updateDisplay();
			}
		});
		
		// Create a JButton that will pause the balls moving
		playButton = new JButton("Play");
		playButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		playButton.setPreferredSize(new Dimension(60, 35));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				updater.setTimeIncrement((double) 0.005 * timeSlider.getValue());
			}
		});
		
		// Create a JButton that will pause the balls moving
		pauseButton = new JButton("Pause");
		pauseButton.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pauseButton.setPreferredSize(new Dimension(60, 35));
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				updater.setTimeIncrement(0);
			}
		});
		
		// Create a JTextArea that will display the results
		drawingPanel = new JPanel();
		drawingPanel.setPreferredSize(new Dimension(600, 400));
		
		// Place components on a panel using a GridBagLayout
		JPanel westPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		westPanel.setLayout(gridBagLayout);
		
		//Create a new slider that will change the time increment
		timeSlider = new JSlider(JSlider.VERTICAL, 1, 11, 6);
		timeSlider.addChangeListener(this);
		
		Insets insets = new Insets(5, 5, 5, 5);
		gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(startButton, gbc);
		gbc = new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(resetButton, gbc);
		gbc = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(playButton, gbc);
		gbc = new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(pauseButton, gbc);
		
		gbc = new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sxLabel, gbc);
		gbc = new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sxTextField, gbc);
		
		gbc = new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(syLabel, gbc);
		gbc = new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(syTextField, gbc);
		
		gbc = new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vxLabel, gbc);
		gbc = new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vxTextField, gbc);
		
		gbc = new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vyLabel, gbc);
		gbc = new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(vyTextField, gbc);
		
		gbc = new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(sliderLabel, gbc);
		gbc = new GridBagConstraints(0, 7, 1, 1, 100, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0);
		gridBagLayout.setConstraints(timeSlider, gbc);
		
		westPanel.add(startButton);
		westPanel.add(resetButton);
		westPanel.add(playButton);
		westPanel.add(pauseButton);
		westPanel.add(sxLabel);
		westPanel.add(sxTextField);
		westPanel.add(syLabel);
		westPanel.add(syTextField);
		westPanel.add(vxLabel);
		westPanel.add(vxTextField);
		westPanel.add(vyLabel);
		westPanel.add(vyTextField);
		westPanel.add(sliderLabel);
		westPanel.add(timeSlider);
		
		// The drawing panel.
		JPanel eastPanel = new JPanel();
		GridBagLayout gridBagLayout2 = new GridBagLayout();
		eastPanel.setLayout(gridBagLayout2);
		gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 20, 20, 20), 0, 0);
		gridBagLayout2.setConstraints(drawingPanel, gbc);
	
		eastPanel.add(drawingPanel);
		
		// Add the JPanel objects to the content pane
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(eastPanel, BorderLayout.EAST);
		getContentPane().add(westPanel, BorderLayout.WEST);
		
		// Add a title to the JFrame, size it, and make it visible.
		setTitle("Enviroment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 530);
		setVisible(true);

		// Update the GUI display
		updateDisplay();
	}
	
	@Override
	//stateChanged() is called when the slider is moved.
	public void stateChanged(ChangeEvent e) {
		updater.setTimeIncrement((double) 0.005 * timeSlider.getValue());
		updateDisplay();
	}
	
	// The actionPerformed() method is called when
	// the "Start" button is pressed.
	public void actionPerformed(ActionEvent event) {
		// Get the initial quantities from the textfields.
		ball.getPosition().setX(Double.parseDouble(sxTextField.getText()));
		ball.getPosition().setY(Double.parseDouble(syTextField.getText()));
		ball.getVelocity().setX(Double.parseDouble(vxTextField.getText()));
		ball.getVelocity().setY(Double.parseDouble(vyTextField.getText()));

		// Update the display
		updateDisplay();

		// Start the box sliding using a Timer object
		// to slow down the action.
		gameTimer.start();
	}
	
	private void updateDisplay() {
		Graphics g = drawingPanel.getGraphics();
		int width = drawingPanel.getWidth() - 1;
		int height = drawingPanel.getHeight() - 1;

		g.clearRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// Draw outline of game area.
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, width, 0);
		g.drawLine(width, 0, width, height);
		g.drawLine(width, height, 0, height);
		g.drawLine(0, 0, 0, height);
		
		// Update the position of the ball on the screen.
		g.setColor(Color.RED);
		int xPosition = (int) (ball.getPosition().getX() - ball.getRadius());
		int yPosition = (int) (height - ball.getRadius() - ball.getPosition().getY());
		g.fillOval(xPosition, yPosition, 2 * (int) (ball.getRadius()), 2 * (int) (ball.getRadius()));
		
	}
	
	class Updater implements ActionListener {

		private Body body;
		private double timeIncrement;
		
		Updater(Body body) {
			this.body = body;
		}
		
		public void setTimeIncrement(double value) {
			this.timeIncrement = value;
		}
		
		public double getTimeIncrement() {
			return timeIncrement;
		}

		public void actionPerformed(ActionEvent event) {
			
			// Get dimensions of drawing area.
			int width = drawingPanel.getWidth();
			int height = drawingPanel.getHeight();
			
			Boundary boundary = new Boundary(height, 1, width, 1);
			
			if(Contact.testBoundaryOverlap(body, boundary, Contact.BOTTOM)  || Contact.testBoundaryOverlap(body, boundary, Contact.TOP))
				Contact.elasticCollisionHandler(body, body.getCoefficientOfRestitution(), Contact.Y);
			if(Contact.testBoundaryOverlap(body, boundary, Contact.LEFT)	|| Contact.testBoundaryOverlap(body, boundary, Contact.RIGHT))	
				Contact.elasticCollisionHandler(body, body.getCoefficientOfRestitution(), Contact.X);
			
//			Ralentiza mucho el programa y parece que varía el timeIncrement cuando no debería pasar así
//			sxTextField.setText(String.format("%.2f",ball.getPosition().getX()));	
//			syTextField.setText(String.format("%.2f",ball.getPosition().getY()));
			body.integrate(timeIncrement);
			updateDisplay();
			
		}
		
	}// Fin de la clase Updater
	
}
