import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

/**
 * The GUI class creates and uses an interface to interact with the WordFinder class
 * 
 * @author alexeastlake
 */
class GUI {
	
	// WordFinder instance containing tool functionality
	private WordFinder wordFinder;
	
	// Primary GUI components
	private JFrame frame;
	private JPanel controlArea;
	private JTextPane outputArea;
	
	// Menu Bar components
	private JMenuBar menuBar;
	private JMenu helpMenu;
	private JMenuItem howToMenuItem;
	
	// Control Panel components
	private GridBagLayout controlLayout;
	private JTextField inputField;
	private JButton searchButton;
	
	public GUI(WordFinder wordFinder) {
		this.wordFinder = wordFinder;
		
		// Main frame setup
		frame = new JFrame();
		controlArea = new JPanel();
		outputArea = new JTextPane();
		JScrollPane outputAreaScroll = new JScrollPane(outputArea);
		frame.setTitle("Word Finder");
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(controlArea);
		frame.getContentPane().add(outputAreaScroll);
		
		controlArea.setPreferredSize(new Dimension(250, 300));
		outputArea.setPreferredSize(new Dimension(200, 300));
		outputArea.setEditable(false);
		
		// Menu bar setup
		menuBar = new JMenuBar();
		helpMenu = new JMenu("Help");
		howToMenuItem = new JMenuItem("How To Use");
		menuBar.add(helpMenu);
		helpMenu.add(howToMenuItem);
		frame.setJMenuBar(menuBar);
		
		howToMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Word Finder - How to Use\n\n"
						+ "Enter partial words using \"?\" for unknown letters and \"-\" between words "
						+ "to find matching full words.");
			}
		});
		
		// Setting up GridBagLayout and its constraints for the control area
		controlLayout = new GridBagLayout();
		controlArea.setLayout(controlLayout);
		GridBagConstraints controlConstr = new GridBagConstraints();
		
		// Instruction labels for controls
		JLabel inputLabelMain = new JLabel("Enter Word Letters to Search");
		inputLabelMain.setFont(new Font(inputLabelMain.getFont().getName(), Font.BOLD, inputLabelMain.getFont().getSize()));
		addToGridBagLayout(inputLabelMain, controlArea, controlConstr, 1, 0, 1, 1, GridBagConstraints.CENTER);
		
		String[] inputLabels = {" ", "Use \"?\" for Unknowns", "e.g. cr?sswo?d", " ", "Use \"-\" for Multiple Words", "e.g. wo?d-fi?d?r", " "};
		
		for (int i = 0; i < inputLabels.length; i++) {
			JLabel inputLabel = new JLabel(inputLabels[i]);
			addToGridBagLayout(inputLabel, controlArea, controlConstr, 1, i + 1, 1, 1, GridBagConstraints.CENTER);
		}
		
		// Input field
		inputField = new JTextField();
		addToGridBagLayout(inputField, controlArea, controlConstr, 1, inputLabels.length + 1, 3, 1, GridBagConstraints.HORIZONTAL);
		
		// Button to search
		searchButton = new JButton("Search");
		addToGridBagLayout(searchButton, controlArea, controlConstr, 1, inputLabels.length + 2, 1, 1, GridBagConstraints.CENTER);
		
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
		String[] words = {};
		
		if (inputField.getText().contains("-")) {
			words = inputField.getText().toLowerCase().split("-");
		} else {
			words = new String[]{inputField.getText().toLowerCase()};
		}
		
		outputArea.setText("");
		
		for (int i = 0; i < words.length; i++) {
			List<String> matches = wordFinder.matchWords(words[i].toCharArray());
			
			if (matches.size() != 0) {
				if (matches.size() == 1) {
					outputArea.setText(outputArea.getText() + matches.size() + " Match for \"" + words[i] + "\":" + "\n");
				} else {
					outputArea.setText(outputArea.getText() + matches.size() + " Matches for \"" + words[i] + "\":" + "\n");
				}
				
				if (matches.size() <= 100) {
					for (String s : matches) {
						outputArea.setText(outputArea.getText() + "\n" + s);
					}
				} else {		
					for (int j = 0; j < 100; j++) {
						outputArea.setText(outputArea.getText() + "\n" + matches.get(i));
					}
					
					JOptionPane.showMessageDialog(null, "Over 100 matches for \"" + words[i] + "\", displaying first 100 matches.\n" + "Consider filling in more letters.");
				}
			} else {
				outputArea.setText(outputArea.getText() + "No Matches for \"" + words[i] + "\"");
			}
			
			if (i != words.length) {
				outputArea.setText(outputArea.getText() + "\n\n");
			} else {
				outputArea.setText(outputArea.getText() + "\n");
			}
		}
		
		outputArea.setCaretPosition(0);
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