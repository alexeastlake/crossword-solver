import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * The GUI class creates and uses an interface to interact with the CrosswordSolver class
 * 
 * @author alexeastlake
 */
class GUI {
	
	// CrosswordSolver instance containing tool functionality
	CrosswordSolver crosswordSolver;
	
	// Primary GUI components
	private JFrame frame;
	private JPanel controlArea;
	private JTextPane outputArea;
	
	// Control Panel components
	private GridBagLayout controlLayout;
	private JTextField inputField;
	private JButton searchButton;
	
	public GUI(CrosswordSolver crosswordSolver) {
		this.crosswordSolver = crosswordSolver;
		
		// Main frame setup
		frame = new JFrame();
		controlArea = new JPanel();
		outputArea = new JTextPane();
		JScrollPane outputAreaScroll = new JScrollPane(outputArea);
		frame.setTitle("Crossword Solver");
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(controlArea);
		frame.getContentPane().add(outputAreaScroll);
		
		controlArea.setPreferredSize(new Dimension(250, 300));
		outputArea.setPreferredSize(new Dimension(200, 300));
		outputArea.setEditable(false);
		
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
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!inputField.getText().equals("")) {
					displayMatches();
				}
			}
		});
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Gets words matching the current contents of the inputField and adds them to the outputArea
	 */
	public void displayMatches() {
		String currentWord = inputField.getText().toLowerCase();
		List<String> matches = crosswordSolver.matchWords(currentWord.toCharArray());
		
		if (matches.size() != 0 && matches.size() <= 100) {
			outputArea.setText(matches.size() + " Matches for \"" + currentWord + "\":" + "\n");
			
			for (String s : matches) {
				outputArea.setText(outputArea.getText() + "\n" + s);
			}
			
			outputArea.setCaretPosition(0);
		} else if (matches.size() != 0 && matches.size() > 100) {
			outputArea.setText("*Over 100 Matches, Displaying First 100 Matches. Consider Filling in More Letters*\n\n"	+ matches.size() + " Matches for \"" + currentWord + "\":" + "\n");
			
			for (int i = 0; i < 100; i++) {
				outputArea.setText(outputArea.getText() + "\n" + matches.get(i));
			}
			
			outputArea.setCaretPosition(0);
		} else {
			outputArea.setText("No Matches for \"" + currentWord + "\"");
		}
	}
}