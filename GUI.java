import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;

/**
 * The GUI class creates and uses an interface to interact with the CrosswordSolver class
 * 
 * @author alexeastlake
 */
class GUI {
	
	// Primary GUI components
	private JFrame frame;
	private JPanel controlArea;
	private JTextPane outputArea;

	public GUI(CrosswordSolver crosswordSolver) {
		frame = new JFrame();
		controlArea = new JPanel();
		outputArea = new JTextPane();
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		controlArea.setLayout(new BoxLayout(controlArea, BoxLayout.Y_AXIS));
		
		frame.getContentPane().add(controlArea);
		frame.getContentPane().add(outputArea);
		
		controlArea.setPreferredSize(new Dimension(300, 300));
		outputArea.setPreferredSize(new Dimension(200, 300));
		
		
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
}