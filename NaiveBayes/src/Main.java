import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//import java.util.*;
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
        File spamFolder = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/spamTrain");
		//File spamFolder = new File("C:/Users/ele_1/ML/NaiveBayes/part1/spam");
        File[] spamFiles = spamFolder.listFiles();
        for(File f: spamFiles) {
        	 NaiveBayes NBSpam = new NaiveBayes(Category.Spam);
        	System.out.println(f);
        	Scanner s = new Scanner(f);
        	ArrayList<String> list = new ArrayList<String>();
    		while (s.hasNext()){
    		    list.add(s.next());
    		}
    		s.close();
    		
    		NBSpam.train( list, Category.Spam);
    		
        }
        
       File folder = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/hamTrain");
     //   File folder = new File("C:/Users/ele_1/ML/NaiveBayes/part1/legit");
        File[] files = folder.listFiles();
        for(File f: files) {
        	 NaiveBayes NBHam = new NaiveBayes(Category.Ham);
        	System.out.println(f);
        	Scanner s = new Scanner(f);
        	ArrayList<String> list = new ArrayList<String>();
    		while (s.hasNext()){
    		    list.add(s.next());
    		}
    		s.close();
    		
    		NBHam.train( list, Category.Ham);
    		
        }
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
        
        
        
        System.out.println("*************CLASSIFICATION SECTION***************");
        System.out.println("*************CLASSIFICATION SECTION***************");
        System.out.println("*************CLASSIFICATION SECTION***************");
        System.out.println("********** s p a m *************");
        File spamFolder1 = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/spam1");
        File[] spamFiles1 = spamFolder1.listFiles();
        double NospamFiles=0;
   		double spamAccuracy = 0;
   		double hamAccuracy = 0;
        for(File f: spamFiles1) {
        	NospamFiles++;
       	 NaiveBayes NBSpam = new NaiveBayes();
       	System.out.println(f);
       	Scanner s = new Scanner(f);
       	ArrayList<String> list = new ArrayList<String>();
   		while (s.hasNext()){
   		    list.add(s.next());
   		}
   		s.close();
   		

   		if(NBSpam.classify(list)== Category.Spam) spamAccuracy++;
   		else hamAccuracy++;

       }
   		System.out.println("Number of Spam files examined"+ NospamFiles);
   		System.out.println("Number of Spam files classified in Spam"+ spamAccuracy);
        /*
        System.out.println("********** h a m *************");
        File spamFolder2 = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/ham1");
        File[] spamFiles2 = spamFolder2.listFiles();
        double NohamFiles=0;
   		double spamAccuracy1 = 0;
   		double hamAccuracy1 = 0;
        for(File f: spamFiles2) {
        	NohamFiles++;
       	 NaiveBayes NBSpam = new NaiveBayes();
       	System.out.println(f);
       	Scanner s = new Scanner(f);
       	ArrayList<String> list = new ArrayList<String>();
   		while (s.hasNext()){
   		    list.add(s.next());
   		}
   		s.close();
   		//System.out.println(NBSpam.classify(list));

   		if(NBSpam.classify(list)== Category.Spam) {
   			spamAccuracy1++;
   			System.out.println(f);
   		}
   		else hamAccuracy1++;
   		
   		System.out.println("Number of Ham files examined"+ NohamFiles);
   		System.out.println("Number of Ham files classified in Ham"+ hamAccuracy1);
       }

        */
        /*
        //File f = new File("C:/Users/ele_1/ML/NaiveBayes/enron1/spam/0697.2004-03-22.GP.spam.txt");
        System.out.println("Training Spam files");
        for(File f: files){
        	Scanner s = new Scanner(f);
        	ArrayList<String> list = new ArrayList<String>();
    		while (s.hasNext()){
    		    list.add(s.next());
    		}
    		s.close();
    		System.out.println(list);
    		System.out.println(list.size());
           NBSpam.train( list, Category.Spam);
            NBSpam.classify(list);
        }
	*/
	//b.train(args);

	}
}

/*
int i =0 ; 
for(File f: files){
	String str ="";
	Scanner s = new Scanner(f);
	ArrayList<String> list = new ArrayList<String>();
	while (s.hasNext()){
	    str+=s.next()+" ";
	    
	}
	list.add(i,str);
	i++;
	s.close();
	System.out.println(list);
	System.out.println(list.size());
	for (int y =0; y<list.size() ;y++){
		System.out.println("List-get-i"+list.get(y));
*/