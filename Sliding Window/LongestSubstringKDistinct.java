import java.util.*;

// Longest Substring with K Distinct Characters (medium)
class LongestSubstringKDistinct {

    // time: O(N) / O(N + N) | space: O(K)
    public static int findLength(String str, int k) {
        // handle invalid inputs
        if (str == null || str.length() == 0 || str.length() < k) {
            throw new IllegalArgumentException();
        }
        int windowStart = 0, maxLength = 0;
        // use a Hashmap to store distinct characters from a string
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            // insert characters from the beginning of the string until we have ‘K’ distinct characters in the HashMap
            char rightChar = str.charAt(windowEnd);
            // increment the frequency of the character going into the sliding window
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // try to shrink the window from the beginning if the count of distinct characters in the HashMap is larger than ‘K’ //
            // then shrink the window until we have no more than ‘K’ distinct characters in the HashMap                         //
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
            while(charFrequencyMap.size() > k) { // inner while loop processes each element only once
                char leftChar = str.charAt(windowStart);
                // decrement the frequency of the character going out of the sliding window
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                // and remove it from the HashMap if its frequency becomes zero
                if(charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2)); // 4
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1)); // 2
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3)); // 5
    }

}
 