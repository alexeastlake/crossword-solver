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
	Map<Integer, List<char[]>> wordLists = new HashMap<Integer, List<char[]>>();
	
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
	
}