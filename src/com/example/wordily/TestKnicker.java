package com.example.wordily;



import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.jeremybrooks.knicker.AccountApi;
import net.jeremybrooks.knicker.Knicker;
import net.jeremybrooks.knicker.Knicker.SourceDictionary;
import net.jeremybrooks.knicker.KnickerException;
import net.jeremybrooks.knicker.WordApi;
import net.jeremybrooks.knicker.WordsApi;
import net.jeremybrooks.knicker.dto.Definition;
import net.jeremybrooks.knicker.dto.Example;
import net.jeremybrooks.knicker.dto.SearchResult;
import net.jeremybrooks.knicker.dto.SearchResults;
import net.jeremybrooks.knicker.dto.TokenStatus;
import net.jeremybrooks.knicker.dto.Word;
import net.jeremybrooks.knicker.dto.WordOfTheDay;

public class TestKnicker {

	public static String myList;
	
	

    public static void main(String[] args) throws Exception {
        // use your API key here
        

	// check the status of the API key

	

    }
    
    public void init(){
    	System.setProperty("WORDNIK_API_KEY", "0ef07b7186e9a61d9eb680c8d70002ff46aa73a0f1e731fe4");

    }
    
    public void stuff(String str) throws KnickerException{
    	
    	TokenStatus status = AccountApi.apiTokenStatus();
    	if (status.isValid()) {
    	    System.out.println("API key is valid.");
    	    WordDisplay.thestatus = "Valid";
    	} else{
    	    System.out.println("API key is invalid!");
    	    System.exit(1);
    	    WordDisplay.thestatus = "InValid";
    	}
    	
    	
    	Set<Knicker.PartOfSpeech> exclude = new TreeSet<Knicker.PartOfSpeech>();
    	exclude.add(Knicker.PartOfSpeech.noun_plural);
    	exclude.add(Knicker.PartOfSpeech.proper_noun_plural);
    	
    	Set<Knicker.PartOfSpeech> include = new TreeSet<Knicker.PartOfSpeech>();
    	include.add(Knicker.PartOfSpeech.noun);
    	
    	
    	
    	//net.jeremybrooks.knicker.dto.Word random = WordsApi.randomWord(true, null , null, 0, 0, 0, 0, 10, 0);
    	
    	//net.jeremybrooks.knicker.dto.Word random = WordsApi.randomWord(true, include , exclude, 0, 0, 0, 0, 0, 0);
    	//CollectionDemoActivity.myWord = random.getWord();
		//List<Definition> def = WordApi.definitions(random.getWord());
		
		//int i = 1;
		//for (Definition d : def) {
		//	CollectionDemoActivity.definition = d.getPartOfSpeech().toUpperCase() + " - " + d.getText();
		//}
    	
		List<Word> myWordList = WordsApi.randomWords();

		//Iterator<Word> it = myWordList.iterator();

		int k = 0;
		
		/*while (it.hasNext()) {
			//String value = (String) it.next().toString();
			net.jeremybrooks.knicker.dto.Word temp = (Word) it.next();
			CollectionDemoActivity.word_arr[k] = temp.getWord();
			//CollectionDemoActivity.word_arr[k] = value;
			//CollectionDemoActivity.defintiions[k] =
			k++;
		}*/
		
		
		for (int i = 0; i < myWordList.size(); i++) {
			net.jeremybrooks.knicker.dto.Word temp = myWordList.get(i);
			WordDisplay.word_arr[i] = temp.getWord();
		  	//WordDisplay.something[i] = WordApi.examples(WordDisplay.word_arr[i],false, null, false, 0, 0);
			SearchResults myResults =  WordApi.examples(WordDisplay.word_arr[i]);
			List<Example> searchResults = myResults.getExamples();
			//WordDisplay.something[i][0] = searchResults.get(1).toString();
			
			//while (k < 5) {
				for (Example e : searchResults) {
					WordDisplay.something[i][0] = e.getText();
				}
				//k++;
			//}
			
			
			
			
		}
		
		k = 0;
		
		//Set<Knicker.SourceDictionary> mySD = null;
		//mySD.add(Knicker.SourceDictionary.webster);
		
		//List<Definition> def = WordApi.definitions(WordDisplay.word_arr[k]);
		
		
		
		while (k< myWordList.size()) {
			
			List<Definition> def = WordApi.definitions(WordDisplay.word_arr[k]);
			for (Definition d : def) {
				WordDisplay.partOfSpeech[k] = d.getPartOfSpeech().toUpperCase();
				WordDisplay.defintiions[k] = d.getText();
				
				
			}
			k++;
		}
	
    	
    	
    }
}

