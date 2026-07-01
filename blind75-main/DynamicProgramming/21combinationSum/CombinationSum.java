public class CombinationSum {
    private static void combine(int i, int total, int target, int numsLen, int[] candidates, List<List<Integer>> res,
            List<Integer> dp) {
        if (total == target) {
            res.add(List.copyOf(dp));
            return;
        }
        if (i == numsLen) {
            return;
        }
        if (total + candidates[i] <= target) {
            total += candidates[i];
            dp.add(candidates[i]);
            combine(i, total, target, numsLen, candidates, res, dp);
            int toMin = dp.get(dp.size() - 1);
            dp.remove(dp.size() - 1);
            total -= toMin;
        }
        combine(i + 1, total, target, numsLen, candidates, res, dp);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // System.out.println(res);
        List<Integer> dp = new ArrayList<Integer>();
        combine(0, 0, target, candidates.length, candidates, res, dp);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 5 };
        System.out.println(combinationSum(nums, 8));
    }
}