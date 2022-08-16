import java.util.*;

// Longest Sub-string with Same Letters after Replacement (hard)
class CharacterReplacement {

    // Time: O(N) where ‘N’ is the number of letters in the input string | Space: O(26) or O(1) where each letter’s frequency is stored in the HashMap
    public static int findLength(String str, int k) {
        // keep track of the count of the maximum repeating letter in any window in a variable called maxRepeatLetterCount
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0; // most frequent letter count
        // use a HashMap to count the frequency of each letter
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // iterate through the string to add one letter at a time in the window
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) { // try to extend the range [windowStart, windowEnd]
            // grab current character from the string
            char rightChar = str.charAt(windowEnd);
            // store the current character's frequency in the map
            letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
            // determine the most frequent letter count so far
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));
            // current window size is from windowStart to windowEnd, overall we have a letter which is repeating 'maxRepeatLetterCount' times
            // this means we can have a window which has one letter repeating 'maxRepeatLetterCount' times, and we should try to replace the remaining letters
            // if the remaining letters are more than 'k', it is the time to shrink the window as we are not allowed to replace more than 'k' letters
            if(windowEnd - windowStart + 1 - maxRepeatLetterCount > k) { // sliding window - frequency count > k ----> i.e. baabb - bb > 2 ----> aa > 2
                char leftChar = str.charAt(windowStart);
                letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1); // decrease frequency of leftChar
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(CharacterReplacement.findLength("aabccbb", 2)); // 5
        System.out.println(CharacterReplacement.findLength("abbcb", 1)); // 4
        System.out.println(CharacterReplacement.findLength("abccde", 1));  // 3
    }

}