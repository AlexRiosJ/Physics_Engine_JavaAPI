package com.rad.tests;

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
import javax.swing.event.*;

import com.rad.tests.PaddleGame.GameUpdater;
import com.rad.vf.*;

public class RadPaddle extends JFrame implements ActionListener, ChangeListener{
	
	Body b,paddle;
	
	  private JTextField vxTextField;
	  private JTextField vzTextField;

	  private JLabel vxLabel;
	  private JLabel vzLabel;
	  private JLabel sliderLabel;

	  private JSlider paddleSlider;

	  private JButton startButton;
	  private JButton resetButton;
	  private JPanel drawingPanel;
	  private GridBagConstraints gbc;


	  private GameUpdater gameUpdater;
	  private Timer gameTimer;
	  

	  public RadPaddle() {
		  
		  Vec2D ballV=new Vec2D(100,-71),ballP=new Vec2D(100,100),paddleP=new Vec2D(0,100);
			double mass = 10,coefficient;
			b=new Body(ballP,ballV,ballV,ballV,0,mass);
			paddle=new Body(paddleP,null,null,null,0,0);
			b.radius=5.0;
			paddle.height=40;
		  
		  //  Create a Timer object that will be used
		    //  to slow the action down and an ActionListener
		    //  that the Timer will call. The timeDelay variable
		    //  is the time delay in milliseconds.
		    gameUpdater = new GameUpdater(b, paddle);
		    int timeDelay = 50;
		  	gameTimer = new Timer(timeDelay, gameUpdater);

			
		    //  Create JTextField objects to input the initial 
		    //  velocity components of the ball.
		    vxTextField = new JTextField("100.0",6);
		    vzTextField = new JTextField("-80.0",6);

		    //  Create some JLabels
		    vxLabel = new JLabel("x-velocity, m/s");
		    vzLabel = new JLabel("z-velocity, m/s");
		    sliderLabel = new JLabel("Ubicación del Paddle");

		    //  Create a JSlider that will move the paddle up and down.
		    paddleSlider = new JSlider(JSlider.VERTICAL,(int) paddle.height/2,
		                               (int)(200 - paddle.height/2), (int)paddle.getPosition().getY());
		    paddleSlider.addChangeListener(this);

		    //  Create a JButton that will start the balls moving
		    startButton = new JButton("Start");
		    startButton.setBorder(new BevelBorder(BevelBorder.RAISED));
		    startButton.setPreferredSize(new Dimension(60,35));
		    startButton.addActionListener(this);

		    //  Create a JButton that will update the drawing area.
		    resetButton = new JButton("Reset");
		    resetButton.setBorder(new BevelBorder(BevelBorder.RAISED));
		    resetButton.setPreferredSize(new Dimension(60,35));
		    resetButton.addActionListener( new ActionListener() {
		      public void actionPerformed(ActionEvent ae) {
		        //  stop the timer.
		        gameTimer.stop();

		        //  Reset the ball location, velocity, and
		        //  paddle location.
		        b.getVelocity().setX(100.0);
		        b.getVelocity().setY(-80.0);
		        b.getPosition().setX(100.0);
		        b.getPosition().setY(100.0);
		        paddle.getPosition().setY(100.0);
		        paddleSlider.setValue((int)paddle.getPosition().getY());
		  
		        //  Update the display.
		        updateDisplay();
		      }  
		    });
	  
	  
	//  Create a JTextArea that will display the results
	    drawingPanel = new JPanel();
	    drawingPanel.setPreferredSize(new Dimension(301, 201));

	    //  Place components on a panel using a GridBagLayout
	    JPanel northPanel = new JPanel();
	    GridBagLayout gridBagLayout1 = new GridBagLayout();
	    northPanel.setLayout(gridBagLayout1);

	    int col;
	    int row;
	    int numCol = 1;
	    int numRow = 1;
	    Insets insets = new Insets(5, 3, 5, 3);

	    row = 0;
	    col = 0;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.EAST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(vxLabel, gbc);

	    col = 1;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.WEST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(vxTextField, gbc);

	    row = 1;
	    col = 0;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.EAST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(vzLabel, gbc);

	    col = 1;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.WEST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(vzTextField, gbc);

	    row = 0;
	    col = 2;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.EAST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(startButton, gbc);

	    col = 3;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.EAST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout1.setConstraints(resetButton, gbc);

	    northPanel.add(vxLabel);
	    northPanel.add(vxTextField);
	    northPanel.add(vzLabel);
	    northPanel.add(vzTextField);
	    northPanel.add(startButton);
	    northPanel.add(resetButton);

	    //  Place components on a panel using a GridBagLayout
	    JPanel westPanel = new JPanel();
	    GridBagLayout gridBagLayout3 = new GridBagLayout();
	    westPanel.setLayout(gridBagLayout3);

	    row = 0;
	    col = 0;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.EAST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout3.setConstraints(sliderLabel, gbc);

	    col = 1;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.WEST,
	                 GridBagConstraints.NONE, insets, 0, 0);
	    gridBagLayout3.setConstraints(paddleSlider, gbc);

	    westPanel.add(sliderLabel);
	    westPanel.add(paddleSlider);

	    //  The drawing panel.
	    JPanel eastPanel = new JPanel();
	    GridBagLayout gridBagLayout2 = new GridBagLayout();
	    eastPanel.setLayout(gridBagLayout2);

	    row = 0;
	    col = 0;
	    gbc = new GridBagConstraints(col, row, numCol, numRow,
	                 0.0, 0.0, GridBagConstraints.CENTER,
	                 GridBagConstraints.NONE, 
	                 new Insets(10, 10, 10, 20), 0, 0);
	    gridBagLayout2.setConstraints(drawingPanel, gbc);

	    eastPanel.add(drawingPanel);

	    //  Add the JPanel objects to the content pane
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(northPanel, BorderLayout.NORTH);
	    getContentPane().add(eastPanel, BorderLayout.EAST);
	    getContentPane().add(westPanel, BorderLayout.WEST);

	    //  Add a title to the JFrame, size it, and make it visible.
	    setTitle("Prueba Paddle Game");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100,100,500,350);
	    setVisible(true);

	    //  Update the GUI display
	    updateDisplay(); 
	 }

	  //  The actionPerformed() method is called when 
	  //  the "Start" button is pressed. 
	  public void actionPerformed(ActionEvent event){
	    
	    //  Get the initial quantities from the textfields.
	    b.getVelocity().setX(Double.parseDouble(vxTextField.getText()));
	    b.getVelocity().setY(Double.parseDouble(vzTextField.getText()));

	    //  Update the display
	    updateDisplay();

	    //  Start the box sliding using a Timer object
	    //  to slow down the action.
	    gameTimer.start();
	  }

	  //  The stateChanged() method is called when 
	  //  the the JSlider position is changed. 
	  public void stateChanged(ChangeEvent event) {
	    
	    //  Set new paddle location based on position of JSlider.
	    paddle.getPosition().setY(paddleSlider.getValue());

	    //  Update the display
	    updateDisplay();
	  }
	  
	  
	  private void updateDisplay() {
	        Graphics g = drawingPanel.getGraphics();
	        int width = drawingPanel.getWidth() - 1;
	        int height = drawingPanel.getHeight() - 1;

	        g.clearRect(0, 0, width, height);
	        g.setColor(Color.WHITE);
	        g.fillRect(0, 0, width, height); 

	        //  Draw outline of game area.
	        g.setColor(Color.BLACK);
	        g.drawLine(0, 0, width, 0);
	        g.drawLine(width, 0, width, height);
	        g.drawLine(width, height, 0, height);

	        //  Update the position of the ball on the screen.
	        int xPosition = (int)(b.getPosition().getX() - b.radius);
	        int zPosition = (int)(height - b.radius - b.getPosition().getY());
	        g.fillOval(xPosition, zPosition, 2*(int)(b.radius), 
	                   2*(int)(b.radius));

	        //  Update the position of the paddle on the screen.
	        zPosition = (int)(height - paddle.getPosition().getY());
	        g.fillRect(10,(int) ((int) zPosition - paddle.height/2), 10,(int) paddle.height);
	    }
	  
	  
	  class GameUpdater implements ActionListener {
		  
		  	Body b,paddle;
		  	public double timeIncrement=0.07;
		  	GameUpdater(Body body,Body paddle){
		  		this.b=body;
		  		this.paddle=paddle;
		  	}
		  	
		    public void actionPerformed(ActionEvent event) {

		      //  Get dimensions of drawing area.
		      Graphics g = drawingPanel.getGraphics();
		      int width = drawingPanel.getWidth() - 1;
		      int height = drawingPanel.getHeight() - 1;

		      //  Determine if ball collides with right wall.
		      //  If it does, change the x-velocity of the ball.
		      if ( b.getVelocity().getX() > 0.0 && b.getPosition().getX() 
		    		  + b.radius >= width ) {
		        b.getVelocity().setX(-(b.getVelocity().getX()));
		      }

		      //  Determine if ball collides with the top wall.
		      //  If it does, change the z-velocity of the ball.
		      if ( b.getVelocity().getY() > 0.0 && b.getPosition().getY() 
		    		  + b.radius >= height ) {
			        b.getVelocity().setY(-(b.getVelocity().getY()));
		      }

		      //  Determine if ball collides with the bottom wall.
		      //  If it does, change the z-velocity of the ball.
		      if ( b.getVelocity().getY() < 0.0 && b.getPosition().getY() 
		    		  + b.radius <= 0.0 ) {
		    	  b.getVelocity().setY(-(b.getVelocity().getY()));
		      }

		      //  Determine if ball collides with paddle.
		      //  If it does, change the x-velocity of the ball.
		      if ( b.getVelocity().getX() < 0.0 && b.getPosition().getX() 
		    		  + b.radius <= 20.0 ) {
		        if (  b.getPosition().getY() - b.radius >= paddle.getPosition().getY() - paddle.height/2 &&
		        		 b.getPosition().getY() + b.radius <= paddle.getPosition().getY() + paddle.height/2 ) {
		        	b.getVelocity().setX(-(b.getVelocity().getX()));
		        }
		      }

		      //  If ball travels off the left edge of the game
		      //  area, stop the simulation.
		      if ( b.getPosition().getX() <= 0.0 ) {
		        gameTimer.stop();
		      }

		      //  Compute the new location of the ball.
		      
		      b.integrate(timeIncrement);

		      /*double timeIncrement = 0.07;
		      b.getPosition().setX(b.getPosition().getX() + timeIncrement*b.getVelocity().getX());
		      b.getPosition().setY(b.getPosition().getY() + timeIncrement*b.getVelocity().getY());
		      */
		      
		      
		      //  Update the display
		      updateDisplay();

		    }  
	}//Fin de la clase gameupdater
	 
	  public static void main(String args[]) {
		    RadPaddle gui = new RadPaddle();
		  }

}
