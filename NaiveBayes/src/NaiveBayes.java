import java.util.*;
import java.util.Map.Entry;
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// TODO  Fixed Coverage -> Tokenization: stopWords & Classification: commonW
//  lingspam_public dataset -> better results when "not" & "any" added on stopWords
// Curves/Percentages/Accuracy/Training Data/ Test Data -> Report
// Clean-up unnecessary code, after using it for debugging.


public class NaiveBayes {
	private Category category;
	private static final String FILENAME = "C:/Users/ele_1/ML/NaiveBayes/0006.2003-12-18.GP.spam.txt";
	// Using a list of stopWords, as well as including deciding what to include and what not, really depends on the application.
	// In some cases it's necessary, in other it's detrimental.
	private String[] stopWords = {"about","a","an","like","of","to", "at","on","be","and","is","are","was","nor","for","from","how",
            "that","the","it","this","so", "was","what","when","where","who","will","with","und","but", "under", "towards",
            "before","in", "you", "not", "any", "doc"};
	 
	Map<ArrayList<String>, Category> trainingSet = new HashMap<>();
	static Map<String, Integer> wordFrequencySpam = new HashMap<>();
	static Map<String, Integer> wordFrequencyHam = new HashMap<>();
	static double spamCount=0;
	static double hamCount=0;
	public NaiveBayes() {
	
	}

	/*
	 * Unnecessary probably 
	 */
	public NaiveBayes(Category category) {  
		this.setCategory(Category.Spam);
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	
	public void train(ArrayList<String> list, Category category) {
		Category spam = Category.Spam;
		Category ham = Category.Ham;
		trainingSet.put(list, category);
	
		if (category == spam) {
			spamCount++;
			ArrayList <String> keyWords = new ArrayList<String>();
			keyWords.addAll(tokenize(list));
			for (String keyword : keyWords) {
				//System.out.println(keyword);
				Integer n = wordFrequencySpam.get(keyword);
				n = (n == null) ? 1 : ++n;
				wordFrequencySpam.put(keyword, n);
				//System.out.println("Keyword&noOcc: "+ keyword+n);	
			}
			//System.out.println("WordFrequency-MAP Contains the following:");
			//for(String key :wordFrequencySpam.keySet()) {
			//System.out.println("timesXkey:"+wordFrequencySpam.get(key)+ key);
		
			//}
		}
		if (category == ham) {
			hamCount++;
			ArrayList <String> keyWords = new ArrayList<String>();
			keyWords.addAll(tokenize(list));
			
			for (String keyword : keyWords) {
				//System.out.println(keyword);
				Integer n = wordFrequencyHam.get(keyword);
				n = (n == null) ? 1 : ++n;
				wordFrequencyHam.put(keyword, n);
			}
		}
	}
	
	
	
	public Category classify(ArrayList<String> list) {
		ArrayList <String> keyWords = new ArrayList<String>();
		keyWords = this.tokenize(list);
		category = this.decide(keyWords);
		System.out.println("************************   "+ category+ "    ***************************");
		return category;
	}

	





/* This function decides whether the email is spam or ham.
 * It takes an ArrayList of Strings/Words as input and returns
 * the category after applying Naive Bayes Classifier. Laplace
 * smoothing is added.
 */
	private Category decide(ArrayList<String> keyWords) {
		Category spam = Category.Spam;
		Category ham = Category.Ham;
		category = ham; //by def
		trainingSet.size();

		//System.out.println("Spam Count:" + spamCount);
		//System.out.println("Ham Count:"+ hamCount);
		double totalCount = spamCount + hamCount;
		//Calculating P(Spam) & P(Ham)
		double pSpam = spamCount / totalCount;
		double pHam = hamCount / totalCount;
		// Laplace Smoothing - Count of distinct words
		double commonW = 0;
		for( String word: wordFrequencyHam.keySet()) {
			//System.out.println(word);
			if(wordFrequencySpam.get(word)!= null) {
				commonW++;
			}
		}
		
		double distinctWords = wordFrequencySpam.size()+wordFrequencyHam.size() -(2*commonW);
		//System.out.println(wordFrequencySpam.size());
		//System.out.println(wordFrequencyHam.size());
		// Use Log probability because we're dealing with small numbers
		//Possible	precision problems ?
		double emailIsSpam = Math.log(pSpam);
		double emailIsHam = Math.log(pHam);
		//System.out.println(emailIsSpam);
		//System.out.println(emailIsHam);
		for(String keyword: keyWords) {
			//System.out.println(keyword);

			Integer spamOccur = wordFrequencySpam.get(keyword);
			if(spamOccur==null) spamOccur = 0;
			else spamOccur = wordFrequencySpam.get(keyword);

			//System.out.println(spamOccur);
			emailIsSpam += Math.log((spamOccur+1)/ (spamCount + distinctWords)); // or wordFrequency.size()
			
			
			Integer hamOccur = wordFrequencyHam.get(keyword);
			if(hamOccur==null) hamOccur = 0;
			else hamOccur = wordFrequencyHam.get(keyword);
			
			emailIsHam += Math.log((hamOccur+1)/ (hamCount + distinctWords));

		}
		//System.out.println("ham"+emailIsHam);
		//System.out.println("spam"+emailIsSpam);
		//System.out.println("common"+commonW);
		//System.out.println("spam"+wordFrequencySpam.size());
		//System.out.println("ham size" +wordFrequencyHam.size());
		//System.out.println("WordFrequency-MAP Contains the following:");
		//for(String key :wordFrequencySpam.keySet()) {
		//System.out.println("timesXkey:"+wordFrequencySpam.get(key)+ key);}
		if(emailIsHam>= emailIsSpam) {
			category = ham;
		}else {
			category = spam;
		}
		
		
		return category;
	}
	
	
	
	
	
	
	
	
	private ArrayList<String> tokenize(ArrayList<String> list) { //, ArrayList<String> keyWords)
		
		ArrayList <String> keyWords = new ArrayList<String>();
		for(int i=0; i<list.size(); i++) {
			boolean isStopWord = false;
			String word = list.get(i);
			//System.out.println(word);
			if(word!="/[^a-zA-Z 0-9]+/") {
				word.replaceAll("/[^a-zA-Z 0-9]+/", "");
			}
			//list.set(i,word.toLowerCase() ); // gia ta alla synola estw
		
			
			if(word.length()>=3) {
				//System.out.println("List of 3 letters+:" +word);
				for( String stopWord : stopWords) {
					if(word.equals(stopWord)== true) {
						//System.out.println("What is gonna be added in keyWords:" +word);
						isStopWord = true;
						//System.out.println(keyWords);
						break;
					}
				}
				if(!isStopWord) keyWords.add(word);

			}
			
		}
		//System.out.println(list.size());
		//System.out.println(keyWords);
		return keyWords;




	}
}