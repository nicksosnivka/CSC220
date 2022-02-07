package lab13;

public class WordFisherTester {

	public static void main(String[] args) {
		
		WordFisher carroll = new WordFisher("texts/carroll-alice.txt","texts/stopwords.txt");
		
		//System.out.println(test1.stopwordToString());
		
		//System.out.println(test1.vocabularyToString());
		
		WordFisher moby = new WordFisher("texts/moby-dick.txt","texts/stopwords.txt");
		
		//System.out.println(carroll.wordCount());
		
		//System.out.println(moby.wordCount());
		
		//System.out.println(carroll.getNumUniqueWords());
		
		//System.out.println(moby.getNumUniqueWords());
		
		//System.out.println(moby.getFrequency("whale"));
		
		//System.out.println(moby.getFrequency("handkerchief"));
		
		moby.pruneVocabulary();
		
		carroll.pruneVocabulary();
		
		System.out.println(moby.wordCount());
		
		System.out.println(moby.getTopWords(10));
		
		//carroll.pruneVocabulary();
		
		//System.out.println(carroll.wordCount());
		
		System.out.println(moby.commonPopularWords(20, carroll));
	}

}
