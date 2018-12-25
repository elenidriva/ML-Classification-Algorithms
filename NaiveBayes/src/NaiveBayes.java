import java.util.*;
import java.util.Map.Entry;
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// TODO  No debugging done
// Will output.txt be used? -> Statistics etc?
// Clean-up after implementing Naive Bayes + decision on output.txt
// Iteration through the whole Folder
/* *******TEXT PROCESSING*********
* Prepare the text so that it's suitable for tokenization and stopWord-filtering
* xDeterminers: the, a, an, another
* xCoordinating Conjunctions: for, an, nor, but, or, yet, so
* xPrepositions: in, under, towards, before
* Add more Words. Ref: https://towardsdatascience.com/machine-learning-text-processing-1d5a2d638958
* */

public class NaiveBayes {
	private Category category;
	private static final String FILENAME = "C:/Users/ele_1/ML/NaiveBayes/0006.2003-12-18.GP.spam.txt";
	
	private String[] stopWords = {"about","a","an","like","of","to", "at","on","be","and","is","are","was","com","nor","for","from","how",
            "that","the","it","this","so", "was","what","when","where","who","will","with","und","but","www", "under", "towards",
            "before","in"};
	 
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
			//Map<String, Integer> wordFrequency = new HashMap<>();
			for (String keyword : keyWords) {
				//System.out.println(keyword);
				Integer n = wordFrequencySpam.get(keyword);
				n = (n == null) ? 1 : ++n;
				wordFrequencySpam.put(keyword, n);
				//System.out.println("Keyword&noOcc: "+ keyword+n);
				
			}
			//System.out.println("WordFrequency-MAP Contains the following:");
			//for(String key :wordFrequencySpam.keySet()) {
			//System.out.println("timeskey:"+wordFrequencySpam.get(key)+ key);
		
			//}
		}
		
		if (category == ham) {
			hamCount++;
			ArrayList <String> keyWords = new ArrayList<String>();
			keyWords.addAll(tokenize(list)); //, keyWords)
			//Map<String, Integer> wordFrequency = new HashMap<>();
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
		System.out.println("++++++++++++++++++   "+ category+ "   ++++++++++++++++++");
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
		
		double common = 0;
		for( Entry<String, Integer> words: wordFrequencyHam.entrySet()) {
			if(wordFrequencySpam.get(words)!=null) {
				common++;
			}
		}
		double distinctWords = wordFrequencySpam.size()+wordFrequencyHam.size() -(2*common );
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
		if(emailIsHam>= emailIsSpam) {
			category = ham;
		}else {
			category = spam;
		}
		
		
		return category;
	}
	
	
	
	
	
	
	
	
	private ArrayList<String> tokenize(ArrayList<String> list) { //, ArrayList<String> keyWords)
		//System.out.println("Inside tokenizer");
		ArrayList <String> keyWords = new ArrayList<String>();
		for(int i=0; i<list.size(); i++) {
			String word = list.get(i);
			//System.out.println(word);
			if(word=="/[^a-zA-Z 0-9]+/") {
				word.replaceAll("/[^a-zA-Z 0-9]+/", "");
			}
			list.set(i,word.toLowerCase() ); // gia ta alla synola estw
			
			if(word.length()>=3) {
				//System.out.println("List of 3 letters+:" +word);
				for( String stopWord : stopWords) {
					if(word.equals(stopWord)== false) {
						//System.out.println("What is gonna be added in keyWords:" +word);
						keyWords.add(word);
						//System.out.println(keyWords);
						break;
					}
				}

			}
		}
		//System.out.println(list.size());
		//System.out.println(keyWords);
		return keyWords;




	}
}