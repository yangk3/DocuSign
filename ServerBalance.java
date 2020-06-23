//Name: Kevin Yang
//Date: 6/23/2020

import java.util.HashMap;
import java.util.Random;

public class ServerBalance {

    public static void main(String[] args) {

        /**
         * IMPORTANT: Server names are case-sensitive!
         */

        HashMap<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
        HashMap<Character, Range> distribution = new HashMap<Character, Range>();

        int totalServers = 0;

        //This for loop is simply collecting the frequencies for each server from the command line
        for (int i = 0; i < args.length; i++) {
            char server = args[i].charAt(0);
            int frequency = Integer.parseInt(String.valueOf(args[i].charAt(2)));
            totalServers += frequency;

            if (!frequencyMap.containsKey(server)) {
                frequencyMap.put(server, frequency);
            } else {
                frequencyMap.put(server, frequencyMap.get(server) + frequency);
            }
        }

        //Here, we are updating the 'distribution' hashmap which maps each server to a specific index range
        //Similar to a number line
        int start = 0;
        int end = 0;
        for (char key : frequencyMap.keySet()) {
            int count = frequencyMap.get(key);
            end += count;
            Range r = new Range(start, end);
            distribution.put(key, r);
            start = end;
        }

        //Select a random index between 1 and totalServers inclusive
        //Iterate across 'distribution' hashmap and find corresponding server
        Random r = new Random();
        int serverIndex = r.nextInt(totalServers) + 1;
        for (char key : distribution.keySet()) {
            Range temp = distribution.get(key);
            if (temp.contains(serverIndex)) {
                System.out.println(key);
                break;
            }
        }

        calculatePercentages(1000.0, totalServers, distribution);

    }

    /**
     * This is a helper method that analyzes the percentages of each server by appearance given a sample size.
     * The larger the sample size, the more accurate the percentages will be towards their expected value.
     * @param sampleSize
     * @param totalServers
     * @param distribution
     */
    public static void calculatePercentages(double sampleSize, int totalServers, HashMap<Character,Range> distribution) {

        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();

        for(int i = 0; i < sampleSize; i++) {
            Random r = new Random();
            int serverIndex = r.nextInt(totalServers) + 1;

            for (char key : distribution.keySet()) {
                Range temp = distribution.get(key);
                if (temp.contains(serverIndex)) {
                    if (!countMap.containsKey(key)) {
                        countMap.put(key, 1);
                    } else {
                        countMap.put(key, countMap.get(key) + 1);
                    }
                    break;
                }
            }
        }
        for (char key : countMap.keySet()) {
            double percentage = countMap.get(key) / sampleSize;
            System.out.println("Server: " + key + " Percentage: " + percentage);
        }
    }
}
