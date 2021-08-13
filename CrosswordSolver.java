import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The CrosswordSolver class contains the main functionality of the tool
 * 
 * @author alexeastlake
 */
class CrosswordSolver {
	
	// Map with length of word as the key to list of words of that length
	private Map<Integer, List<char[]>> wordLists = new HashMap<Integer, List<char[]>>();
	
	public CrosswordSolver() {
		this.readFile("words_alpha.txt");
	}
	
	/**
	 * Read a .txt file of words into the wordLists map
	 * 
	 * @param path of .txt file
	 */
	public void readFile(String path) {
		// TODO: Add error handling to try catch block
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String currentWord;
			char[] currentWordChars;
			
			while ((currentWord = reader.readLine()) != null) {
				currentWordChars = currentWord.toCharArray();
				
				if (wordLists.containsKey(currentWordChars.length)) {
					wordLists.get(currentWordChars.length).add(currentWordChars);
				} else {
					List<char[]> newLengthList = new ArrayList<char[]>();
					newLengthList.add(currentWordChars);
					wordLists.put(currentWordChars.length, newLengthList);
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {} catch (IOException e) {}
	}
	
	/**
	 * Gets all known matches of the given char[] to words of the same length as the char[] where spaces (' ') are unknowns
	 * 
	 * @param knownChars
	 * @return list of matching words
	 */
	public List<String> matchWords(char[] knownChars) {
		List<String> matches = new ArrayList<String>();
		
		for (char[] word : this.wordLists.get(knownChars.length)) {
			boolean match = true;
			
			for (int i = 0; i < word.length; i++) {
				if (word[i] != knownChars[i] && knownChars[i] != ' ') {
					match = false;
					break;
				}
			}
			
			if (match) {
				matches.add(String.valueOf(word));
			}
		}
		
		return matches;
	}
}