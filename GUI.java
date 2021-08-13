import javax.swing.*;

/**
 * The GUI class creates and uses an interface to interact with the CrosswordSolver class
 * 
 * @author alexeastlake
 */
class GUI {
	
	// Primary GUI components
	private JFrame frame;
	private JPanel panelArea;
	private JTextArea outputArea;
	
	public GUI(CrosswordSolver crosswordSolver) {
		frame = new JFrame();
		panelArea = new JPanel();
		outputArea = new JTextArea();
		
		frame.add(panelArea);
		frame.add(outputArea);
		
		frame.setVisible(true);
	}
	
}