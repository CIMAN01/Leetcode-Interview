import java.util.*;

// String Anagrams (hard)
class StringAnagrams {

    // Time: O(N+M) where ‘N’ and ‘M’ are the number of characters in the input string and the pattern respectively.
    // Space: O(M) since in the worst case, the whole pattern can have distinct characters going into the HashMap or O(N) space for the result list, this will happen when the pattern has only one character and the string contains only that character.
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        // create and initialize needed variables
        int windowStart = 0, matched = 0;
        // create a HashMap to calculate the frequencies of all characters in the pattern
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // convert pattern string to an array of characters so that each character can be added to the frequency map
        for(char ch : pattern.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }
        // create a list to store the starting indices of the anagrams of the pattern in the string
        List<Integer> resultIndices = new ArrayList<>();
        // we need to find every occurrence of any permutation of the pattern in the string
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // grab the current character
            char rightChar = str.charAt(windowEnd);
            // decrement the frequency of the matched character
            if(charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                // if the character frequency becomes zero, we got a complete match
                if(charFrequencyMap.get(rightChar) == 0) {
                    matched++; // character is completely matched
                }
            }
            // have we found an anagram?
            if(matched == charFrequencyMap.size()) {
                resultIndices.add(windowStart); // add char index to the list
            }
            // if the window size is greater than the length of the pattern, shrink the window to make it equal to the size of the pattern
            if(windowEnd >= pattern.length() - 1) { // shrink the window
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

        return resultIndices;
    }

    public static void main(String[] args) {
        System.out.println(StringAnagrams.findStringAnagrams("ppqp", "pq")); // [1, 2]
        System.out.println(StringAnagrams.findStringAnagrams("abbcabc", "abc")); // [2, 3, 4]
    }

}
