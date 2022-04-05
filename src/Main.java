import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Contains the main method for the Word Finder tool
 * 
 * @author alexeastlake
 */
class Main {
	
	public static void main(String args[]) {
		WordFinder wordFinder = new WordFinder();
		
		try {
			wordFinder.readFile("words_alpha.txt");
			new GUI(wordFinder);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error reading word files:\n" + e.getMessage());
			System.exit(0);
		}
	}
}