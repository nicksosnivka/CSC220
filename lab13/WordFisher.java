package lab13;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WordFisher {

	public HashMap<String, Integer> vocabulary;
	public List<String> stopwords;
	private String inputTextFiles;
	private String stopwordsFiles;
	
	public WordFisher(String inputTextFile, String stopwordsFile) {
		inputTextFiles = inputTextFile;
		stopwordsFiles = stopwordsFile;
		getStopwords();
		buildVocabulary();
	}
	
	private void getStopwords() {
		try {
			BufferedReader input = new BufferedReader(new FileReader(stopwordsFiles));
			String lineRead = input.readLine();
			stopwords = new ArrayList<String>();
            while(lineRead != null)
            {
            	stopwords.add(lineRead);
            	lineRead = input.readLine();
                
            }
            input.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	private void buildVocabulary() {
		
		
		try {
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFiles)));
			reader = reader.replaceAll("[^a-zA-Z0-9 ]", "");
			reader = reader.toLowerCase();
			String[] allWords = reader.split("\\s+");
			vocabulary = new HashMap<String,Integer>();
			
			for(int i = 0; i < allWords.length; i++) {
				if(vocabulary.containsKey(allWords[i])) {
					int frequency = vocabulary.get(allWords[i]) + 1;
					vocabulary.replace(allWords[i], frequency);
				} else {
					vocabulary.put(allWords[i], 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  

		
	}
	
	public String stopwordToString() {
		return stopwords.toString();
	}
	
	public String vocabularyToString() {
		return vocabulary.toString();
	}
	
	public int wordCount() {
		
		int wordCount = 0;
		
		List<Integer> values = new ArrayList<>(vocabulary.values());
		
		for(int i = 0; i < values.size(); i++) {
			wordCount += values.get(i);
		}
		
		return wordCount;
	}
	
	public int getNumUniqueWords() {
		
		return vocabulary.size();
		/*List<String> keys = new ArrayList<>(vocabulary.keySet());
		
		int wordCount = 0;
		
		for(int i = 0; i < keys.size(); i++) {
			if(!keys.get(i).equals(null)) {
				wordCount++;
			}
		}
		
		return wordCount;
		*/
	}
	
	public int getFrequency(String word) {
		if (vocabulary.containsKey(word)) {
			return vocabulary.get(word);
		} else {
			return -1;
		}
	
	}
	
	public void pruneVocabulary() {
		
		for(int i = 0; i < stopwords.size(); i++) {
			if(vocabulary.containsKey(stopwords.get(i))) {
				vocabulary.remove(stopwords.get(i));
			}
		}
	}
	
	
	public class WordNode {
		String word;
		int frequency;
		
		public WordNode(String inputWord,int inputFreq) {
			word = inputWord;
			frequency = inputFreq;
		}
	
	}
	
	protected class WordNodeComparator implements Comparator<WordNode> {
		public int compare(WordNode first, WordNode second) {
			if(first.frequency < second.frequency) {
				return -1;
			} else if(first.frequency > second.frequency) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public ArrayList<String> getTopWords(int n) {
		ArrayList<String> topWords = new ArrayList<String>();
		
		PriorityQueue<WordNode> queue = new PriorityQueue<WordNode>(new WordNodeComparator());
		
		List<String> keys = new ArrayList<>(vocabulary.keySet());
		List<Integer> values = new ArrayList<>(vocabulary.values());
		
		for(int i = 0; i < vocabulary.size(); i++) {
			WordNode temp = new WordNode(keys.get(i),values.get(i));
			queue.add(temp);
		}
		
		int queueSize = queue.size();
		
		for(int i = 0; i < queueSize; i++) {
			WordNode headNode = queue.poll();
			String head = headNode.word;
			topWords.add(head);
		}
		
		ArrayList<String> newTopWords = new ArrayList<String>();
		
		for(int i = topWords.size()-1; i >= topWords.size()-n; i--) {
			newTopWords.add(topWords.get(i));
		}
		
		return newTopWords;
		
	}
	
	public ArrayList<String> commonPopularWords(int n, WordFisher other) {
		
		ArrayList<String> text = new ArrayList<String>();
		ArrayList<String> text2 = new ArrayList<String>();
		ArrayList<String> commonPopularWords = new ArrayList<String>();
		
		text = getTopWords(n);
		text2 = other.getTopWords(n);
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(text.get(i).equals(text2.get(j))) {						
					commonPopularWords.add(text.get(i));
				}
			}
		}
		return commonPopularWords;
	}
	
	
}
