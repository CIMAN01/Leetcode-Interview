import java.util.*;

// Permutation in a String (hard)
class StringPermutation {

    // Time: O(N+M) where ‘N’ and ‘M’ are the number of chars in the input string & pattern, respectively | Space: O(M) since in the worst case, the whole pattern can have distinct chars going into the HashMap
    public static boolean findPermutation(String str, String pattern) {
        // create and initialize needed variables
        int windowStart = 0, matched = 0;
        // create a HashMap to calculate the frequencies of all characters in the pattern
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // convert pattern string to an array of characters so that each character can be added to the frequency map
        for(char ch : pattern.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////
        // our goal is to match all the characters from the 'charFrequencyMap' with the current window //
        ////////////////////////////////////////////////////////////////////////////////////////////////
        // iterate through the string, adding one character at a time in the sliding window
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) { // try to extend the range [windowStart, windowEnd]
            // grab the current character
            char rightChar = str.charAt(windowEnd);
            // if the character being added matches a character in the HashMap, decrement its frequency in the map
            if(charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                // if the character frequency becomes zero, we got a complete match
                if(charFrequencyMap.get(rightChar) == 0) {
                    matched++; // character is completely matched
                }
            }
            // if at any time, the number of characters matched is equal to the number of distinct characters in the pattern, we have gotten our required permutation
            if(matched == charFrequencyMap.size()) {
                return true;
            }
            // if the window size is greater than the length of the pattern, shrink the window to make it equal to the size of the pattern
            if(windowEnd >= pattern.length() - 1) { // shrink the window by one character
                // grab the character going out and increment the pointer
                char leftChar = str.charAt(windowStart++);
                // at the same time, if the character going out was part of the pattern, put it back in the frequency HashMap
                if(charFrequencyMap.containsKey(leftChar)) {
                    // before putting the character back, decrement the matched count
                    if(charFrequencyMap.get(leftChar) == 0) {
                        matched--;
                    }
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1); // put the character back for matching
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("oidbcaf", "abc")); // true
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("odicf", "dc")); // true
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx")); // false
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("aaacb", "abc")); // true
    }

}