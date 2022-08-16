import java.util.*;

// Smallest Window containing Substring (hard)
public class MinimumWindowSubstring {

    // Time: O(N + M) where ‘N’ and ‘M’ are the number of characters in the input string and the pattern respectively.
    // Space: O(M) since in the worst case, the whole pattern can have distinct characters which will go into the HashMap, or O(N) space for the resulting substring, which will happen when the input string is a permutation of the pattern.
    public static String findSubstring(String str, String pattern) {
        // create and initialize needed variables
        int windowStart = 0, matched = 0, minLength = str.length() + 1, subStrStart = 0;
        // create a HashMap to calculate the frequencies of all characters in the pattern
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // convert pattern string to an array of characters so that each character can be added to the frequency map
        for(char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }
        // try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // grab the current character
            char rightChar = str.charAt(windowEnd);
            // decrement the frequency of the matched character
            if(charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                // count every matching of a character
                if(charFrequencyMap.get(rightChar) >= 0) {
                    matched++;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////
            // whenever we have matched all the characters, try to shrink the window from the beginning, //
            // keeping track of the smallest substring that has all the matching characters             //
            /////////////////////////////////////////////////////////////////////////////////////////////
            // shrink the window if we can, finish as soon as we remove a matched character
            while(matched == pattern.length()) {
                // make sure minLength is not greater than the current sliding window
                if(minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1; // keeping track of the smallest substring that has all the matching characters
                    subStrStart = windowStart;
                }
                // grab the character going out and increment the pointer
                char leftChar = str.charAt(windowStart++);
                // at the same time, if the character going out was part of the pattern, put it back in the frequency HashMap
                if(charFrequencyMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the matched count only when a useful occurrence of a matched character is going out of the window
                    if(charFrequencyMap.get(leftChar) == 0) {
                        matched--;
                    }
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1); // put the character back for matching
                }
            }
        }

        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }


    public static void main(String[] args) {
        System.out.println(MinimumWindowSubstring.findSubstring("aabdec", "abc")); // abdec
        System.out.println(MinimumWindowSubstring.findSubstring("abdabca", "abc")); // abc
        System.out.println(MinimumWindowSubstring.findSubstring("adcad", "abc")); // ""
    }

}

/*

1. We will keep a running count of every matching instance of a character.

2. Whenever we have matched all the characters, we will try to shrink the window from the beginning,
   keeping track of the smallest substring that has all the matching characters.

3. We will stop the shrinking process as soon as we remove a matched character from the sliding window.
   One thing to note here is that we could have redundant matching characters, e.g., we might have two ‘a’ in the sliding window when we only need one ‘a’.
   In that case, when we encounter the first ‘a’, we will simply shrink the window without decrementing the matched count.
   We will decrement the matched count when the second ‘a’ goes out of the window. */