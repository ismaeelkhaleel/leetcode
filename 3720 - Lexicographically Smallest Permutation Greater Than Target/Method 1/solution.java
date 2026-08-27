class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < n; i++) {

            int idx = target.charAt(i) - 'a';

            if (freq[idx] == 0) {

                // Try making target[i] bigger first
                for (int k = idx + 1; k < 26; k++) {

                    if (freq[k] > 0) {

                        StringBuilder ans =
                                new StringBuilder(target.substring(0, i));

                        ans.append((char) ('a' + k));
                        freq[k]--;

                        for (int x = 0; x < 26; x++) {
                            while (freq[x] > 0) {
                                ans.append((char) ('a' + x));
                                freq[x]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                // Can't increase at i, so backtrack
                for (int j = i - 1; j >= 0; j--) {

                    int prev = target.charAt(j) - 'a';
                    freq[prev]++;

                    for (int k = prev + 1; k < 26; k++) {

                        if (freq[k] > 0) {

                            StringBuilder ans =
                                    new StringBuilder(target.substring(0, j));

                            ans.append((char) ('a' + k));
                            freq[k]--;

                            for (int x = 0; x < 26; x++) {
                                while (freq[x] > 0) {
                                    ans.append((char) ('a' + x));
                                    freq[x]--;
                                }
                            }

                            return ans.toString();
                        }
                    }
                }

                return "";
            }

            freq[idx]--;
        }

        // target itself is possible -> find next permutation
        for (int j = n - 1; j >= 0; j--) {

            int prev = target.charAt(j) - 'a';
            freq[prev]++;

            for (int k = prev + 1; k < 26; k++) {

                if (freq[k] > 0) {

                    StringBuilder ans =
                            new StringBuilder(target.substring(0, j));

                    ans.append((char) ('a' + k));
                    freq[k]--;

                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans.append((char) ('a' + x));
                            freq[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}