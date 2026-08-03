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
