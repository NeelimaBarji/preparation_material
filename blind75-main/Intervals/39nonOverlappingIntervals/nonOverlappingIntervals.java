
public class nonOverlappingIntervals {
    public static int nonOverLap(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        int result = 0, prevEnd = intervals[0][1], length = intervals.length;
        for (int i = 1; i < length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            if (start >= prevEnd) {
                prevEnd = end;
            } else {
                result += 1;
                prevEnd = prevEnd < end ? prevEnd : end;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[][] = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        System.out.println(nonOverLap(arr));
    }
}
