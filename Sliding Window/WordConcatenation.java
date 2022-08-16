import java.util.*;

// Words Concatenation (hard)
class WordConcatenation {

    // Time: O(N∗M∗Len) where ‘N’ is the number of characters in the given string, ‘M’ is the total number of words, and ‘Len’ is the length of a word.
    // Space: O(M) since at most, we will be storing all the words in the two HashMaps. In the worst case, we also need O(N) space for the resulting list. So, the overall space complexity of the algorithm will be O(M+N).
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        // keep the frequency of every word in a HashMap
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        // store every word from the array to the map
        for(String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        // create a new list that will hold the indices
        List<Integer> resultIndices = new ArrayList<>();
        // create a variable for the total count of words and the length of each word (the length is the same for all words)
        int wordsCount = words.length, wordLength = words[0].length();
        // limit sliding window range to str.length - wordCount * wordLength to avoid extraneous iteration
        for(int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
            // in each iteration, keep track of all the words that we have already seen in another HashMap
            Map<String, Integer> wordsSeen = new HashMap<>();
            // starting from every index in the string, try to match all the words
            for(int j = 0; j < wordsCount; j++) {
                // get the starting index of each word
                int nextWordIndex = i + j * wordLength;
                // get the next word (as a substring) from the input string
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // if a word is not found or has a higher frequency than required, we can move on to the next character in the string //
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(!wordFrequencyMap.containsKey(word)) {
                    break; // break if we don't need this word
                }
                // keep track of all the words that we have already seen in a second HashMap
                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1);
                // no need to process further if the word has higher frequency than required
                if(wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0)) {
                    break;
                }
                // store index if we have found all the words
                if(j + 1 == wordsCount) {
                    resultIndices.add(i);
                }
            }
        }

        return resultIndices;
    }


    public static void main(String[] args) {
        List<Integer> result = WordConcatenation.findWordConcatenation("catfoxcat", new String[] { "cat", "fox" }); // [0, 3]
        System.out.println(result);
        result = WordConcatenation.findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" }); // [3]
        System.out.println(result);
    }

}

/*
This problem follows the Sliding Window pattern and has a lot of similarities with Maximum Sum Subarray of Size K.
We will keep track of all the words in a HashMap and try to match them in the given string.

Here are the set of steps for our algorithm:

1. Keep the frequency of every word in a HashMap.
2. Starting from every index in the string, try to match all the words.
3. In each iteration, keep track of all the words that we have already seen in another HashMap.
4. If a word is not found or has a higher frequency than required, we can move on to the next character in the string.
5. Store the index if we have found all the words. */