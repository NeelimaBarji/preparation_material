
public class insertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (newInterval == null || interval[1] < newInterval[0]) {
                result.add(interval);
            } else if (interval[0] > newInterval[1]) {
                result.add(newInterval);
                result.add(interval);
                newInterval = null;
            } else {
                newInterval[0] = interval[0] < newInterval[0] ? interval[0] : newInterval[0];
                newInterval[1] = interval[1] > newInterval[1] ? interval[1] : newInterval[1];
            }
        }
        if (newInterval != null) {
            result.add(newInterval);
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] interval = { { 1, 3 }, { 6, 9 } };
        int[][] result = insert(interval, new int[] { 2, 5 });
        for (int i = 0; i < result.length; i++) {
            System.out.print(Arrays.toString(result[i]));
        }
    }
}