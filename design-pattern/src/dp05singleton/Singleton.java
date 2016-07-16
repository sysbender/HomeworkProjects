/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp05singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author jason
 */
public class Singleton {
    private static Singleton firstInstance = null;
    
    String[] scrabbleLetters = {"a", "a", "a", "a", "a", "a", "a", "a", "a",
      "b", "b", "c", "c", "d", "d", "d", "d", "e", "e", "e", "e", "e",
      "e", "e", "e", "e", "e", "e", "e", "f", "f", "g", "g", "g", "h",
      "h", "i", "i", "i", "i", "i", "i", "i", "i", "i", "j", "k", "l",
      "l", "l", "l", "m", "m", "n", "n", "n", "n", "n", "n", "o", "o",
      "o", "o", "o", "o", "o", "o", "p", "p", "q", "r", "r", "r", "r",
      "r", "r", "s", "s", "s", "s", "t", "t", "t", "t", "t", "t", "u",
      "u", "u", "u", "v", "v", "w", "w", "x", "y", "y", "z",}; 
    
    private LinkedList<String> letterList = new LinkedList(Arrays.asList(scrabbleLetters));
    
    
    // private constructor
    private Singleton(){}
    
    public Singleton getInstance(){
        if(firstInstance == null) {
            firstInstance = new Singleton();
        }
        return firstInstance;
    }
    
    
}
