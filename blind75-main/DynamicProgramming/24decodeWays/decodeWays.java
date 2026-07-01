class decodeWays {
    public static int numDecodings(String s) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(s.length(), 1);
        int strLen = s.length();
        String r = "0123456";
        for (int i = strLen - 1; i > -1; i--) {
            if (s.charAt(i) == '0') {
                dp.put(i, 0);
            } else {
                dp.put(i, dp.get(i + 1));
            }
            if (i + 1 < strLen && (s.charAt(i) == '1'
                    || s.charAt(i) == '2' && r.indexOf(s.charAt(i + 1)) >= 0 && r.indexOf(s.charAt(i + 1)) <= 6)) {
                dp.put(i, dp.get(i) + dp.get(i + 2));
            }

        }
        return dp.get(0);
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
    }
}