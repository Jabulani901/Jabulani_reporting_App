package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommentAnalyzer {
	
	private File _file;
	private Integer _wordCount = 0;
	Scanner scanner = new Scanner(System.in);
		
	public CommentAnalyzer(File file) {
		this._file = file;
	}
	
	 public Map<String, Integer> analyze() throws IOException {
		
		Map<String, Integer> resultsMap = new HashMap<>();
		
	      String[] words=null; 
	      FileReader fileReaderObject = new FileReader(_file);
	      BufferedReader bufferReaderObject = new BufferedReader(fileReaderObject);
	      String content;     
	      System.out.print("Enter  product (Mover or Shaker) you want to pull from the comment section: ");
	      String input = scanner.nextLine();
	      while((content=bufferReaderObject.readLine())!=null)   
	      {
	         words=content.split(" ");  
	          for (String word : words) 
	          {
	                 if (word.toLowerCase().contains(input.toLowerCase()))   
	                 {
	                	 _wordCount++;   
	                 }
	                 if (word.length() > 0) {
	                	  for(int i = 0; i < word.length(); i++) {    
	                          if(word.charAt(i) != ' ')    
	                        	  _wordCount++;    
	                      }    
	                	  incOccurrence(resultsMap, "Length Less Than  15" );
	                 }
	                
	          }
	          if(input.contains("Mover".toLowerCase())){
		          incOccurrence(resultsMap, "MOVER_MENTIONS");
		          }
		          else {
		        	  incOccurrence(resultsMap, "SHAKER_MENTIONS");
		          }   
	          
	      }
	    
		return resultsMap;
		
				
	}
		private void incOccurrence(Map<String, Integer> countMap, String key) {
			
			countMap.putIfAbsent(key, 0);
			countMap.put(key, countMap.get(key) + 1);
		}

}
