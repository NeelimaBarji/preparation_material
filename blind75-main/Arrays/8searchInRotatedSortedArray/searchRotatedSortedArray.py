class Solution:
    def search(self, nums: list[int], target: int) -> int:
        n = len(nums)
        left, right = 0, n - 1
        while left <= right:
            mid = left + (right - left) // 2
            
            # Case 1: find target
            if nums[mid] == target:
                return mid
            
            # Case 2: subarray on mid's left is sorted
            elif nums[mid] >= nums[left]:
                if target >= nums[left] and target < nums[mid]:
                    right = mid - 1
                else:
                    left = mid + 1
                    
            # Case 3: subarray on mid's right is sorted.
            else:
                if target <= nums[right] and target > nums[mid]:
                    left = mid + 1
                else:
                    right = mid - 1
        return -1
        

print(Solution().search([4,5,6,7,0,1,2], 0))
print(Solution().search([4,5,6,7,0,1,2], 3))
print(Solution().search([4,5,6,7,0,1,2], 5))
print(Solution().search([4,5,6,7,0,1,2], 4))
print(Solution().search([2], 3))