# CS145_LevenDistance

This program focuses on programming with Java Collections classes that will implement a 
module that finds a simplified Levenshtein distance between two words represented by strings. 

Background: The Levenshtein distance, named for it's creator Vladimir Levenshtein, is a measure of the 
distance between two words. The edit distance between two strings is the minimum number of 
operations that are needed to transform one sting into the other. For a full implementation of the 
distance we would need to account for three different types of operations, removing a letter, 
adding a letter, or changing a letter. 
For THIS program however, we are going to focus solely on the ability to change one letter to 
another. So, we will be thinking of changing one letter at a time, while maintaining the fact that 
the word is a still a valid word at all times.
So, for an example, the edit distance from "dog" to "cat" is 3. One possible path for this is
"dog" to "dot" to "cot" to cat"
Note, that there might be other paths, but they all involve at least 3 changes. Another longer 
example is from "witch" to "coven".
witch->watch->match->march->marcs->mares->mores->moves->coves->coven
You will be implementing a secondary class called LevenshteinFinder that will be used by 
the main program to solve the problem

*** How to play the game: Please run the file "LevenDistanceFinderJava.java". Choose a starting word and an ending word then wait for the program to find the fastest route to change the starting word to the ending word
![image](https://user-images.githubusercontent.com/109646359/193101720-065f0cfe-8374-4444-8e2d-23084f228365.png)
