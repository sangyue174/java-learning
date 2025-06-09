package com.learning.java.algorithm;

/**
 * 最长回文子序列
 *
 * @author sangyue <sangyue@kuaishou.com>
 * Created on 2021-09-14
 */
public class LongestPalindromeSubseq {
    /**
     * 基于动态规划法寻找最长回文子序列
     */
    public int longestPalindromeSubseq(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        int len = str.length();
        // 使用dpResult[i][j] 来表示从i到j的最大回文子序列长度
        int[][] dpResult = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dpResult[i][i] = 1;
            for (int j = i + 1; j <= len - 1; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dpResult[i][j] = dpResult[i + 1][j - 1] + 2;
                } else {
                    dpResult[i][j] = Math.max(dpResult[i + 1][j], dpResult[i][j - 1]);
                }
            }
        }
        return dpResult[0][len - 1];
    }

    public static void main(String[] args) {
        //        System.out.println("abcb中的回文串为" + new LongestPalindrome().getPalindrome("abcb", 2, 2));
        System.out.println("abcb中的回文串为" + new LongestPalindromeSubseq().longestPalindromeSubseq("abcbcbccbdb"));
    }
}
