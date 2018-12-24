import java.util.*;
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
	ArrayList <String> keyWords = new ArrayList<String>();
	Map<ArrayList<String>, Category> trainingSet = new HashMap<>();
	Map<String, Integer> wordFrequencySpam = new HashMap<>();
	Map<String, Integer> wordFrequencyHam = new HashMap<>();
	public NaiveBayes() {
	
	}
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
			keyWords.addAll(tokenize(list, keyWords));
			//Map<String, Integer> wordFrequency = new HashMap<>();
			for (String keyword : keyWords) {
				//System.out.println(keyword);
				Integer n = wordFrequencySpam.get(keyword);
				n = (n == null) ? 1 : ++n;
				wordFrequencySpam.put(keyword, n);
			}
		}
		/*
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String data = " This is new content";

			File Tfile = new File(FILENAME);

			// if file doesn't exist, then create it
			if (!Tfile.exists()) {
				Tfile.createNewFile();
			}

			// true = append file
			fw = new FileWriter(Tfile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(data);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

		
		*/
		if (category == ham) {
			keyWords.addAll(tokenize(list, keyWords));
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
		keyWords = this.tokenize(list, keyWords);
		category = this.decide(list);
		return category;
	}

	





/* This function decides whether the email is spam or ham.
 * It takes an ArrayList of Strings/Words as input and returns
 * the category after applying Naive Bayes Classifier. Laplace
 * smoothing is added.
 */
	private Category decide(ArrayList<String> list) {
		Category spam = Category.Spam;
		Category ham = Category.Ham;
		category = ham; //by def
		trainingSet.size();
		double spamCount = 0;
		double hamCount = 0;
		for( Map.Entry<ArrayList<String>, Category> entry : trainingSet.entrySet()) {
			if (entry.getValue() == spam) {
				spamCount++;
			}else
				hamCount++;	
		}
		System.out.println("Spam Count:" + spamCount);
		System.out.println("Ham Count:"+ hamCount);
		double totalCount = spamCount + hamCount;
		//Calculating P(Spam) & P(Ham)
		double pSpam = spamCount / totalCount;
		double pHam = spamCount / totalCount;
		// Laplace Smoothing - Count of distinct words
		double distinctWords = wordFrequencySpam.size();
		// Use Log probability because we're dealing with small numbers
		//Possible	precision problems ?
		double emailIsSpam = Math.log(pSpam);
		double emailIsHam = Math.log(pHam);
		for(String keyword: keyWords) {
			if (category==spam) {
			Integer spamOccur = wordFrequencySpam.get(keyword);
			emailIsSpam += Math.log((spamOccur+1)/ (spamCount + distinctWords)); // or wordFrequency.size()
			}
			
			if (category==ham) {
			Integer hamOccur = wordFrequencyHam.get(keyword);
			emailIsHam += Math.log((hamOccur+1)/ (hamCount + distinctWords));
			}
			
		}
		
		if(emailIsHam>= emailIsSpam) {
			category = ham;
		}else {
			category = spam;
		}
		
		
		return category;
	}
	
	
	
	
	
	
	
	
	private ArrayList<String> tokenize(ArrayList<String> list, ArrayList<String> keyWords) {
		System.out.println("Inside tokenizer");
		for(int i=0; i<list.size(); i++) {
			if(list.get(i)=="/[^(a-zA-ZA-Яa-я0-9_)+\\s]" ||list.get(i)=="[/(){}\\[\\]\\|@,;]" ) {
				list.get(i).replaceAll("[/(){}\\\\[\\\\]\\\\|@,;]", "");
			}
			list.set(i,list.get(i).toLowerCase() ); // gia ta alla synola estw
			if(list.get(i).length()>=3) {
				for( String word : stopWords) {
					if(list.get(i).equals(word)== false) {
						keyWords.add(list.get(i));
						//System.out.println(keyWords);
						break;
					}
				}

			}
		}
		//System.out.println(list.size());
		System.out.println(keyWords);
		return keyWords;




	}
}