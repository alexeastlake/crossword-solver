import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	
	// Control Panel components
	private GridBagLayout controlLayout;
	private JTextField inputField;
	private JButton searchButton;
	
	public GUI(CrosswordSolver crosswordSolver) {
		// Main frame setup
		frame = new JFrame();
		controlArea = new JPanel();
		outputArea = new JTextPane();
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(controlArea);
		frame.getContentPane().add(outputArea);
		
		controlArea.setPreferredSize(new Dimension(250, 300));
		outputArea.setPreferredSize(new Dimension(150, 300));
		
		// Setting up GridBagLayout and its constraints for the control area
		controlLayout = new GridBagLayout();
		controlArea.setLayout(controlLayout);
		GridBagConstraints controlConstr = new GridBagConstraints();
		
		// Instruction labels for controls
		JLabel inputLabel1 = new JLabel("Enter Word Letters to Search");
		inputLabel1.setFont(new Font(inputLabel1.getFont().getName(), Font.BOLD, inputLabel1.getFont().getSize()));
		controlConstr.gridx = 1;
		controlConstr.gridy = 0;
		controlArea.add(inputLabel1, controlConstr);
		
		JLabel inputLabel2 = new JLabel("Use \"?\" for Unknowns");
		controlConstr.gridx = 1;
		controlConstr.gridy = 1;
		controlArea.add(inputLabel2, controlConstr);
		
		JLabel inputLabel3 = new JLabel("e.g. cr?sswo?d");
		controlConstr.gridx = 1;
		controlConstr.gridy = 2;
		controlArea.add(inputLabel3, controlConstr);
		
		// Input field
		inputField = new JTextField();
		controlConstr.gridx = 1;
		controlConstr.gridy = 3;
		controlConstr.gridwidth = 3;
		controlConstr.fill = GridBagConstraints.HORIZONTAL;
		controlArea.add(inputField, controlConstr);
		
		// Button to search
		searchButton = new JButton("Search");
		controlConstr.gridx = 1;
		controlConstr.gridy = 4;
		controlConstr.gridwidth = 1;
		controlConstr.fill = GridBagConstraints.CENTER;
		controlArea.add(searchButton, controlConstr);
		
		frame.pack();
		frame.setVisible(true);
	}
}