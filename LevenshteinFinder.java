import java.util.*;
/**
 * Class that used to find the Levenshtein distance between two words
 * @author Zohar Le
 * @version 10/13/2021
 */
public class LevenshteinFinder {
  
   private String start, end;
   private Map<String, Set<String>> map = new TreeMap<>();
   private int distance = -1;
   private List<String> path= new ArrayList<String>();

   /** Constructor that check legality of the entered words
    * and set up the neighbor map for words in the dictionary
    * @param startWord starting word in the Levenshtein distance
    * @param endWord end word in the Levenshtein distance
    * @param dictionary String set that contain the dictionary words
    */
   public LevenshteinFinder(String startWord, String endWord, Set<String> dictionary) {
       start = startWord;
       end = endWord;
       if(start.length()!= end.length())                    // throw exception if words are different length
           throw new IllegalArgumentException();
       for(String word: dictionary) {
           if(word.length()==start.length()){               // if the words are the same size, our word now are in smaller set of same size words    
               Set<String> set = new TreeSet<>() ;          // create a temp set
               for(String x: dictionary) {                  // go through the set again and compare that with our word
                   if(differentLetters(word, x)==1) {       // if the number of different letter is 1 then this becomes a neighbor                              
                   set.add(x);                              // add to the temp set if they are neighbor          
                   }
               }
               map.put(word, set);                          // our map will have the word with its corresponding set of neighbor words            
           }
       }                          
       distance = findDistance(start, end);                 // get the distance between 2 words
       findPath(start, end);
   }
   // private method
   private int differentLetters(String startWord, String endWord) {       
       int count=0;
       if (startWord.length() != endWord.length())                         // make sure they are same size words
           return -1;                                                      // else return -1
       for(int i=0; i < startWord.length() ; i++) {                        // now check their corresponding characters and return count
           if(startWord.charAt(i) != endWord.charAt(i))
               count++;
       }
       return count;
   }
   /**
    * method that get Levenshtein distance between given words
    * @return Levenshtein distance between given words as an integer
    */
   public int getDistance() {
       return distance;
   }
   /**
    * method that get the path and return the string representation of the path
    * @return path as a String
    */
   public String getPath()
   {
      String holder = "";
      if (distance == -1)
      {
         holder = "There is no path";
      }
      else                                                  // if there is distance >= 0 then convert to string
      {
         for (int i = 0; i < path.size() - 1 ; i++)
         {
            holder = holder + path.get(i) + "->";
         }
         holder = holder + path.get(path.size() - 1);
      }
      return holder;
   }
   /**
    * method that find distance between 2 words and return the number of steps needed to get from start till end
    * @param startWord starting word in the Levenshtein distance
    * @param endWord ending word in the Levenshtein distance
    * @return number of steps needed to get from start till end word
    */
   public int findDistance(String startWord, String endWord) {  
       if(startWord.equals(endWord))                                 // follow instructions
           return 0;
      Set<String> set1 = new TreeSet<>();       
      Set<String> set2 = new TreeSet<>();
      set2.add(startWord);
      int count = 0;
      while (set1.size() != set2.size() && !set2.contains(endWord))
      {
         set1.addAll(set2);
         set2.clear();
         for ( String x : set1)                                      // taking every word in set1
         {
            for (String y : map.get(x))                              // and neighbor words of set 1 
            {
               set2.add(y);                                          // add them into set 2
            }
            set2.add(x);
            
         }
         count++;
      }
      if (set2.contains(endWord))                                    // if reach the end word then return distance
      {
         return count;
      } else { return -1;}
   }
   // private method
   private void findPath(String startWord, String endWord) 
   {
      if (distance == -1)
      {
         path.add("There is no path");
      }
      else                                                              // if distance is is zero or positive
      {
         path.add(startWord);                                           // add first word into our list
         for (int i = distance - 1; i >= 1; i--)                        // loop from distance - 1 down to 1
         {
            int tempCount = 0;
            for ( String x : map.get(path.get(path.size()-1)))                         // adding neighbor of the lastWord of the path into path
            {
               if (findDistance (endWord, x) == i && tempCount ==0 )                    // if there distance is the same as counter and restrict to only add 1 neighbor to path
               {
                  path.add(x);
                  tempCount++;
               }

            }
         }
         path.add(endWord);
      }      
   } 
}
