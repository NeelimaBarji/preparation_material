class Solution {
    public static void moveZeroes(int[] nums) {
        int left=0;
        for(int right=1; right< nums.length; right++){    
        if(nums[right]!=0 && nums[left]==0){
                int temp = nums[left];
                nums[left]=nums[right];
                nums[right]=temp;
                left++;             
        }else if(nums[left]!=0){
            left++; 
        }        
        }
    }

  public static void moveZeroes1(int[] nums) {
       
        int left=0;
        for(int right=0; right< nums.length; right++){
            if(nums[right]!=0){
                  int temp = nums[left];
                nums[left]=nums[right];
                nums[right]=temp;
                left++;  
            }
                                  
        }
    }


    public static void main(String[] args){
   //    int[] nums = [0,1,0,3,12];
    int[] nums;
    nums = new int[] {0,1,0,3,12}; 
        moveZeroes(nums);
    }
}
