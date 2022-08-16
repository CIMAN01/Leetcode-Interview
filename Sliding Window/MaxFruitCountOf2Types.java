import java.util.*;

// Fruits into Baskets (medium)
class MaxFruitCountOf2Types {

    // time: O(N) / O(N + N) | space: O(1)
    public static int findLength(char[] arr) {
        // declare and initialize variables
        int windowStart = 0, maxLength = 0;
        // use a Hashmap to store distinct fruits (or fruit types) from an array of fruits
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // increment the frequency of the fruit going into the sliding window
            fruitFrequencyMap.put(arr[windowEnd], fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1);
            // shrink the sliding window, until we are left with '2' fruits in the frequency map
            while(fruitFrequencyMap.size() > 2) { // inner while loop processes each character only once
                // decrement the frequency of the fruit going out of the sliding window
                fruitFrequencyMap.put(arr[windowStart], fruitFrequencyMap.get(arr[windowStart]) - 1);
                // and remove it from the HashMap if its frequency becomes zero
                if(fruitFrequencyMap.get(arr[windowStart]) == 0) {
                    fruitFrequencyMap.remove(arr[windowStart]);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' })); // 3

        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' })); // 5
    }

}