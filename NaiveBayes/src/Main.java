import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException {
		
		/* ************************** *
		 * T R A I N I N G   S P A M  *
		 * ************************** */
        File spamFolder = new File("C:\\Users\\ele_1\\ML\\enron1\\10% Train\\spamTest");
		//File spamFolder = new File("C:/Users/ele_1/ML/NaiveBayes/part1/spam");// LingSpam DataSet
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
        
		/* ************************** *
		 * T R A I N I N G   H A M    *
		 * ************************** */
        
       File folder = new File("C:\\Users\\ele_1\\ML\\enron1\\10% Train\\hamTest");
     //File folder = new File("C:/Users/ele_1/ML/NaiveBayes/part1/legit"); //LingSpam DataSet
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
      //  System.out.println("Press Any Key To Continue...");
       // new java.util.Scanner(System.in).nextLine();
        
		/* *************************** *
		 * C L A S S I F I C A T I O N *
		 * *************************** */
        
        System.out.println("*************CLASSIFICATION SECTION***************");
        System.out.println("*************CLASSIFICATION SECTION***************");
        System.out.println("*************CLASSIFICATION SECTION***************");
        
        
        System.out.println("********** s p a m *************");
       File spamFolder1 = new File("C:\\Users\\ele_1\\ML\\enron1\\10% Train\\spamTrain");
      // File spamFolder1 = new File("C:/Users/ele_1/ML/NaiveBayes/part1/test");
        File[] spamFiles1 = spamFolder1.listFiles();
        double NospamFiles=0;
   		double TP = 0;
   		double FN = 0;
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
   
   		if(NBSpam.classify(list)== Category.Spam) TP++;
   		else FN++;

       }
   		System.out.println("Number of Spam files examined"+ NospamFiles);
   		System.out.println("Number of Spam files classified in Spam"+ TP);
        
   		
        System.out.println("********** h a m *************");
        File spamFolder2 = new File("C:\\Users\\ele_1\\ML\\enron1\\10% Train\\hamTrain");
        File[] spamFiles2 = spamFolder2.listFiles();
        double NohamFiles=0;
   		double TN = 0;
   		double FP = 0;
        for(File f: spamFiles2) {
        	NohamFiles++;
       	 NaiveBayes NBHam = new NaiveBayes();
       	System.out.println(f);
       	Scanner s = new Scanner(f);
       	ArrayList<String> list = new ArrayList<String>();
   		while (s.hasNext()){
   		    list.add(s.next());
   		}
   		s.close();
   		//System.out.println(NBSpam.classify(list));

   		if(NBHam.classify(list)== Category.Spam) {
   			FP++;
   			
   			System.out.println(f);
   		}
   		else TN++;
   		
   		//System.out.println("Number of Ham files examined"+ NohamFiles);
   		//System.out.println("Number of Ham files classified in Ham"+ TN);
       }
        System.out.println("Spam files total: " + NospamFiles);
        System.out.println("Ham files total: " + NohamFiles);
        System.out.println("Spam Accuracy: " + TP /NospamFiles);
        System.out.println("Ham Accuracy: " + TN /NohamFiles);
        System.out.println("TP: " + (int)TP + " FP: " + (int)FP + " FN: " + (int)FN + " TN: " + (int)TN );
        double precision = TP / (TP + FP);
        System.out.println("PRECISION: "+ precision);
        double recall = TP / (TP + FN);
        System.out.println("RECALL: "+ recall);
        System.out.println("F1: "+ 2*((precision * recall)/(precision + recall)));
	}
	
}