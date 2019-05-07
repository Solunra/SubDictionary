import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//-----------------------------------------------------
//Assignment 4
//Question: 1
//Written by: Sunny Deng (40095407)
//-----------------------------------------------------
/**
* Sunny Deng (40095407)<br/>
* COMP249<br/>
* Assignment #4<br/>
* Due April 8, 2019<br/>
* <br/>
* Creates a dictionary of all valid words within a text document.
*/
public class SubDictionaryCreator {
	public static void main(String[] args) {
		//Part 1
		char searchUpper='A';//In order to increment from A to B (char)(char+1) (cast it) A + 25 = Z OR get char at 0
		char searchLower='a';
		ArrayList<String> wordsFromText=new ArrayList<String>();
		try {
			Scanner inputText=new Scanner(new FileInputStream("Required\\PersonOfTheCentury.txt"));
			while(inputText.hasNext()) {
				wordsFromText.add(inputText.next());// use this for MC² <----unique exception + has correct number of words (checked)
			}
			inputText.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> alphabet=new ArrayList<String>();
		PrintWriter output=null;
		try {
			output=new PrintWriter(new FileOutputStream("Required\\SubDictionary.txt"));
		} 
		catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		//Adding words
		for(int e=0;e<26;++e) {
			alphabet.clear();
			for(int i=0;i<wordsFromText.size();++i) {
				if(wordsFromText.get(i).charAt(0)==searchUpper||wordsFromText.get(i).charAt(0)==searchLower) {
					String word=wordsFromText.get(i);
					String temp=word;
					if(!word.equals("MC²"))
						word=searchEnds(temp);
					if(!alphabet.contains(word.toUpperCase())&&checkDigit(word)&&(word.length()!=1||searchUpper=='A'||searchUpper=='I'||searchLower=='a'||searchLower=='i'))
						alphabet.add(word.toUpperCase());
				}
			}
			//Sorting
			for(int h=0;h<alphabet.size();++h) {
				for(int j=h+1;j<alphabet.size();++j) {
					if(alphabet.get(h).compareTo(alphabet.get(j))>0) {
						String temp=alphabet.get(h);
						alphabet.set(h, alphabet.get(j));
						alphabet.set(j, temp);
					}
				}
			}
			//Output
			if(alphabet.size()!=0) {
				output.println(searchUpper);
				output.println("==");
			}
			for(int i=0;i<alphabet.size();++i)
				output.println(alphabet.get(i));
			if(alphabet.size()!=0) 
				output.println();
			++searchUpper;
			++searchLower;
		}
		output.close();
	}
	public static String searchEnds(String input) {
		if(input.indexOf("?")!=-1)
			return input.substring(0, input.indexOf("?"));
		if(input.indexOf(":")!=-1) 
			return input.substring(0, input.indexOf(":"));
		if(input.indexOf("’")!=-1) 
			return input.substring(0, input.indexOf("’"));
		if(input.indexOf(";")!=-1) 
			return input.substring(0, input.indexOf(";"));
		if(input.indexOf("!")!=-1) 
			return input.substring(0, input.indexOf("!"));
		if(input.indexOf(".")!=-1) 
			return input.substring(0, input.indexOf("."));
		if(input.indexOf(",")!=-1) 
			return input.substring(0, input.indexOf(","));
		return input;
	}
	public static boolean checkDigit(String input) {
		if(input.indexOf("0")==-1)
			return true;
		if(input.indexOf("1")==-1)
			return true;
		if(input.indexOf("2")==-1)
			return true;
		if(input.indexOf("3")==-1)
			return true;
		if(input.indexOf("4")==-1)
			return true;
		if(input.indexOf("5")==-1)
			return true;
		if(input.indexOf("6")==-1)
			return true;
		if(input.indexOf("7")==-1)
			return true;
		if(input.indexOf("8")==-1)
			return true;
		if(input.indexOf("9")==-1)
			return true;
		return false;
	}
}
