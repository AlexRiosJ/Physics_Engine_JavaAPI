import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.*;
import com.rad.classes.*;

public class RadPaddle extends JFrame implements ActionListener, ChangeListener{
	

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
	  
	  Vec2D ballV=new Vec2D(100,-71),ballP=new Vec2D(100,100),paddleP=new Vec2D(0,100);
	  double paddleHeight = 40;
	  double ballRadius = 5.0;
	  
	  double mass,radius,coefficient;
	  public Body bod=new Body(ballP,ballV,ballP,ballP,0,mass);
	/*
	  class GameUpdater implements ActionListener {
		    public void actionPerformed(ActionEvent event) {

		      //  Get dimensions of drawing area.
		      Graphics g = drawingPanel.getGraphics();
		      int width = drawingPanel.getWidth() - 1;
		      int height = drawingPanel.getHeight() - 1;

		      //  Determine if ball collides with right wall.
		      //  If it does, change the x-velocity of the ball.
		      if ( ballVx > 0.0 && ballX + ballRadius >= width ) {
		        ballVx = -ballVx;
		      }

		      //  Determine if ball collides with the top wall.
		      //  If it does, change the z-velocity of the ball.
		      if ( ballVz > 0.0 && ballZ + ballRadius >= height ) {
		        ballVz = -ballVz;
		      }

		      //  Determine if ball collides with the bottom wall.
		      //  If it does, change the z-velocity of the ball.
		      if ( ballVz < 0.0 && ballZ - ballRadius <= 0.0 ) {
		        ballVz = -ballVz;
		      }

		      //  Determine if ball collides with paddle.
		      //  If it does, change the x-velocity of the ball.
		      if ( ballVx < 0.0 && ballX - ballRadius <= 20.0 ) {
		        if ( ballZ - ballRadius >= paddleZ - paddleHeight/2 &&
		             ballZ + ballRadius <= paddleZ + paddleHeight/2 ) {
		          ballVx = -ballVx;
		        }
		      }

		      //  If ball travels off the left edge of the game
		      //  area, stop the simulation.
		      if ( ballX <= 0.0 ) {
		        gameTimer.stop();
		      }

		      //  Compute the new location of the ball. 
		      double timeIncrement = 0.07;
		      ballX = ballX + timeIncrement*ballVx;
		      ballZ = ballZ + timeIncrement*ballVz;

		      //  Update the display
		      updateDisplay();

		    }
		  }
		}

	
	*/
	public void stateChanged(ChangeEvent arg0) {
	}

	public void actionPerformed(ActionEvent arg0) {
	}
}
