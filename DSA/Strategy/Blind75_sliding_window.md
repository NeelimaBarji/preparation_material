# Sliding Window Blind75 problems and solutions:

## 1004. Max Consecutive Ones III
### Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2: 
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length


```java 

class Solution {
    public int longestOnes(int[] nums, int k) {
       int left=0;
       int zero_count=0;
       int count=0;
       int maxConsecutive=0;

       for(int right=0; right< nums.length; right++){
        System.out.println("Before right "+right+" left "+left);
        if(nums[right] == 0){
            zero_count++;
        }
        
        while(zero_count>k){
            if(nums[left] == 0){
                zero_count--;
                count--;
            }
            left++;    
        }

         // Calculate and track the maximum valid window size
        System.out.println("After right "+right+" left "+left);
        System.out.println("maxConsecutive "+(right - left + 1));
        maxConsecutive = Math.max(maxConsecutive, right - left + 1);
       }
    
       return maxConsecutive;
    }
}

```


## 643. Maximum Average Subarray I
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
 

Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104

```java 

class Solution {
    // public double findMaxAverage(int[] nums, int k) {
    //     double windowAvg=0.0;
    //     List<Double> avgList = new ArrayList();
    //     int windowsum=0;
    //     for(int i=0; i< nums.length; i++){
    //         windowsum = windowsum + nums[i];              
    //         if(i >= k-1){             
    //             windowAvg=(double)windowsum/k;
    //             avgList.add(windowAvg);
    //             windowsum-=nums[i-k+1];             
    //         } 
    //     }
    //     double max = Collections.max(avgList);
    //     return max;  
    // }

    public double findMaxAverage(int[] nums, int k) {
        int sum=0;

        for(int i=0; i< k; i++){
            System.out.println("sum "+sum+" nums "+nums[i]);
            sum+=nums[i];
        }
        int windowsum=sum;
        System.out.println("sum "+sum+" windowsum "+windowsum);
        for( int i=k; i< nums.length; i++){
          
        }
        
        return windowsum/k;
       
    }
}
```


## 1456. Maximum Number of Vowels in a Substring of Given Length
Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.


Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length

```java

class Solution {
    public int maxVowels(String s, int k) {
        List<Character> vowel = List.of('a','e','i','o','u');
        int count=0;
        int maxcount=0;
        for(int i=0; i< s.length(); i++){
           // System.out.println("s[i] "+s.charAt(i)+" i "+i+" length"+s.length());
            if(vowel.contains(s.charAt(i))){
                count++;
            }
            if(i>=k-1){
                maxcount = Math.max(count, maxcount);
                if(vowel.contains(s.charAt(i-k+1)))
                    count--;
                
            }
        }
        return maxcount;
    }
}

```

## 1493. Longest Subarray of 1's After Deleting One Element
Given a binary array nums, you should delete one element from it.

Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 

Example 1:

Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
Example 2:

Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
Example 3:

Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.

``` java
class Solution {
    public int longestSubarray(int[] nums) {
        int allowed_delete=0;
        int longest_subarray=0;
        int left=0;

        for(int right=0; right < nums.length; right++){
            if(nums[right]== 0){
                allowed_delete++;
            }

            while(allowed_delete > 1){
                if(nums[left] == 0)
                    allowed_delete--;
                left++;
            }

            longest_subarray = Math.max(longest_subarray, right-left);
            
        }
        
        return longest_subarray;
    }
}

```
