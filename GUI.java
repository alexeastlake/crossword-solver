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
		addToGridBagLayout(inputLabel1, controlArea, controlConstr, 1, 0, 1, 1, GridBagConstraints.CENTER);
		
		JLabel inputLabel2 = new JLabel("Use \"?\" for Unknowns");
		addToGridBagLayout(inputLabel2, controlArea, controlConstr, 1, 1, 1, 1, GridBagConstraints.CENTER);
		
		JLabel inputLabel3 = new JLabel("e.g. cr?sswo?d");
		addToGridBagLayout(inputLabel3, controlArea, controlConstr, 1, 2, 1, 1, GridBagConstraints.CENTER);
		
		// Input field
		inputField = new JTextField();
		addToGridBagLayout(inputField, controlArea, controlConstr, 1, 3, 3, 1, GridBagConstraints.HORIZONTAL);
		
		// Button to search
		searchButton = new JButton("Search");
		addToGridBagLayout(searchButton, controlArea, controlConstr, 1, 4, 1, 1, GridBagConstraints.CENTER);
		
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
	
	/**
	 * Method to add components to the GridBagLayout
	 * 
	 * @param component to be added
	 * @param area to add them to
	 * @param constraints object for the GridBagLayout
	 * @param gridx
	 * @param gridy
	 * @param gridwidth
	 * @param gridheight
	 * @param fill
	 */
	public void addToGridBagLayout(JComponent component, JComponent area, GridBagConstraints constraints, int gridx, int gridy, int gridwidth, int gridheight, int fill) {
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.fill = fill;
		area.add(component, constraints);
	}
}