# DocuSign

The goal for the project is that given a number of different servers and their respective quantities, output a random server so that as the program is run an infinite number of times, the distribution for each server becomes more normalized to its true percentage with respect to the total number of servers. 

For example, given following program arguments: 

X:3 Y:1 Z:5 X:3 Y:2 X:7

There are 13 X-servers, 3 Y-servers and 5 Z-servers for a total of 21 servers. Assuming the program runs an “infinite” number of times, roughly 62% (13/21) of the outputted servers should be X, 14% (3/21) should be Y, and 24% (5/21) should be Z.

## How to Run

1. Clone Project
2. Open local terminal and navigate to directory containing ServerBalance.java and Range.java
3. To compile program, run command 
```bash
javac ServerBalance.java
```
4. To run program with arguments, run command 
```bash
java ServerBalance X:3 Y:1 Z:2 ...
```

IMPORTANT: program arguments MUST be formatted as such
```bash
[server1]:[quantity1][whitespace][server2]:[quantity2][whitespace]...[serverN]:[quanityN]
```

## Approach

1. Count and keep track of all servers and their respective frequencies via a hashmap
2. Set a “range” for each server given their respective frequencies and store map each “range” to its respective server via a hashmap
3. Select a random index (Java.util.Random) between the ranges 0 and total number of servers.
4. Iterate through each key entry in hashmap from (2) and check if random index is in given range; once appropriate range is found, output that server.
        
The runtime for this approach is ~O(2n) since we are initially iterating to store server frequencies and then iterate once more to check if our random index is any of the given frequency ranges. The space complexity is ~O(2n) since we are using two hashmaps; one for mapping servers and frequencies and another for servers and ranges. In both cases, n is the number of servers.

For debugging purposes, I have also included a helper method in ServerBalance.java called calculatePercentages() which essentially picks a random server a number of times given a sample size parameter. It should be noted that the larger the sample size, the closer the percentages will align with their expected values.
