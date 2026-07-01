
class containerWithMostWater {
    public static int maxArea(int[] height) {
        int l = 0,
                r = height.length - 1,
                res = 0,
                area = 0;
        while (l < r) {
            area = (r - l) * Math.min(height[l], height[r]);
            res = Math.max(res, area);
            if (height[l] < height[r]) {
                l += 1;
            } else {
                r -= 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] h = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(maxArea(h));
    }
}