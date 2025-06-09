package com.learning.java.algorithm;

/**
 * 找到当前字符串中的最大回文子串
 *
 * @author sangyue <sangyue@kuaishou.com>
 * Created on 2021-09-12
 */
public class LongestPalindrome {

    /**
     * 基于中心枚举法寻找最大回文子串
     */
    public String getLongestPalindromeByMiddleEnum(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String longest = "";
        for (int i = 0; i < str.length(); i++) {
            // 回文数是奇数个
            String oddPalindrome = getPalindrome(str, i, i);
            if (longest.length() < oddPalindrome.length()) {
                longest = oddPalindrome;
            }
            // 回文数是偶数个
            String evenPalindrome = getPalindrome(str, i, i + 1);
            if (longest.length() < evenPalindrome.length()) {
                longest = evenPalindrome;
            }
        }
        return longest;
    }

    /**
     * 获取包含left和right的回文串
     */
    private String getPalindrome(String str, int left, int right) {
        while (left >= 0 && right <= str.length() - 1) {
            if (str.charAt(left) != str.charAt(right)) {
                break;
            }
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }

    /**
     * 基于动态规划法寻找最大回文子串
     */
    public String getLongestPalindromeByDP(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        int length = str.length();
        // 使用数组dpResult[i][j]来表示从[i]到[j]是否为回文数, 以区间作为状态
        boolean[][] dpResult = new boolean[length][length];
        int longest = 1; // 最大长度
        int start = 0; // 起始位置
        // 初始化i到i肯定是回文数, 切长度为1
        for (int i = 0; i <= length - 1; i++) {
            dpResult[i][i] = true;
        }


        // 初始化i到i+1, 通过比较两个位置值是否一致, 因为后边的操作在这俩值之上, 比如[1][6]基于[2][5], [2][5]基于[3][4]
        // [1][5]基于[2][4], [2][4]基于[3][3], 这些都需要提前完成初始化
        for (int i = 0; i < length - 1; i++) {
            dpResult[i][i + 1] = str.charAt(i) == str.charAt(i + 1);
            if (dpResult[i][i + 1]) {
                start = i;
                longest = 2;
            }
        }
        // dp[i][j] = dp[i+1][j-1] && charAt(i) == charAt(j), i <= j, i依赖i+1的结果, j依赖j-1的结果
        // 所以整体循环按照i从length到小循环, j按照从i到length循环
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 2; j <= length - 1; j++) {
                dpResult[i][j] = dpResult[i + 1][j - 1] && str.charAt(i) == str.charAt(j);
                if (dpResult[i][j] && j - i + 1 > longest) {
                    start = i;
                    longest = j - i + 1;
                }
            }
        }

        return str.substring(start, start + longest);
    }

    /**
     * 基于动态规划法寻找最大回文子串2
     */
    public String getLongestPalindromeByDP2(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        int length = str.length();
        // 使用数组dpResult[i][j]来表示从[i]到[j]的回文数最大长度, 最后返回dpResult[0][length-1]即为中间最大长度
        int[][] dpResult = new int[length][length];
        int start = 0; // 起始位置
        int longest = 1;
        // 初始化i到i肯定是回文数, 切长度为1
        for (int i = 0; i <= length - 1; i++) {
            dpResult[i][i] = 1;
        }

        // dp[i][j] = dp[i+1][j-1] && charAt(i) == charAt(j), i <= j, i依赖i+1的结果, j依赖j-1的结果
        // 所以整体循环按照i从length到小循环, j按照从i到length循环
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= length - 1; j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    dpResult[i][j] = dpResult[i + 1][j - 1] + 2;

                } else {
                    dpResult[i][j] = dpResult[i + 1][j - 1];
                }

                if (dpResult[i][j] > longest) {
                    start = i;
                    longest = dpResult[i][j];
                }
            }
        }

        System.out.println("max length is " + longest);
        return str.substring(start, start + longest);
    }

    public static void main(String[] args) {
        //        System.out.println("abcb中的回文串为" + new LongestPalindrome().getPalindrome("abcb", 2, 2));
        System.out.println("abcb中的回文串为" + new LongestPalindrome().getLongestPalindromeByMiddleEnum("abcb"));
        System.out.println("abcb中的回文串为" + new LongestPalindrome().getLongestPalindromeByDP("abcb"));
        System.out.println("abcb中的回文串为" + new LongestPalindrome().getLongestPalindromeByDP2("abcb"));

        boolean[][] dpResult = new boolean[2][2];
        System.out.println("dpResult[0][1] is " + dpResult[0][1]);
    }
}
