import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//import java.util.*;
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		NaiveBayes NBSpam = new NaiveBayes();
        File file = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/spam");
        File[] files = file.listFiles();
        File f = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/spam/0697.2004-03-22.GP.spam.txt");
        System.out.println("Training Spam files");
        //for(File f: files){
        	Scanner s = new Scanner(f);
        	ArrayList<String> list = new ArrayList<String>();
    		while (s.hasNext()){
    		    list.add(s.next());
    		}
    		s.close();
    		System.out.println(list);
    		System.out.println(list.size());
           NBSpam.train( list, Category.Spam);
            NBSpam.classify( list);
        //}
	
	//b.train(args);

	}
}
