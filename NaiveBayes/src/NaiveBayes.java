import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// TODO Implement Naive Bayes. Clean-up after implementing Naive Bayes
// Will output.txt be used? -> Statistics etc?
// Clean-up after implementing Naive Bayes + decision on output.txt
// Add Ham training
// Iteration through the whole Folder



public class NaiveBayes {
	private Category category;
	private static final String FILENAME = "C:/Users/ele_1/ML/NaiveBayes/0006.2003-12-18.GP.spam.txt";
	private String[] stopWords = {"about","and","are","com","for","from","how",
            "that","the","this", "was","what","when","where","who","will","with","und","the","www"};
	ArrayList <String> keyWords = new ArrayList<String>();
	Map<ArrayList<String>, Category> trainingSet = new HashMap<>();
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
			Map<String, Integer> wordFrequency = new HashMap<>();
			for (String keyword : keyWords) {
				//System.out.println(keyword);
				Integer n = wordFrequency.get(keyword);
				n = (n == null) ? 1 : ++n;
				wordFrequency.put(keyword, n);
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
		int SpamCount = 0;
		int HamCount = 0;
		for( Map.Entry<ArrayList<String>, Category> entry : trainingSet.entrySet()) {
			if (entry.getValue() == spam) {
				SpamCount++;
			}else
				HamCount++;	
		}
		System.out.println("Spam Count:" + SpamCount);
		System.out.println("Ham Count:"+ HamCount);
		
		
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