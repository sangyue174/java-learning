package com.learning.java.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 最长上升子序列
 *
 * @author sangyue <sangyue@kuaishou.com>
 * Created on 2021-09-14
 */
public class LengthOfLIS {
    public int getLengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    /**
     * 获取最长上升子序列索引(有问题)
     */
    public int[] getLengthOfLISArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] dp = new int[nums.length];
        int[] prevIndex = new int[nums.length];
        Arrays.fill(prevIndex, -10); // 用-10(随表找一个值)填充
        List<Integer> index = new LinkedList<>();
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prevIndex[i] = j;
                    }
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        System.out.println("dp is " + Arrays.toString(dp));
        System.out.println("prevIndex is " + Arrays.toString(prevIndex));
        for (int i = 0; i < dp.length; i++) {
            // 找到最大序列所对应的索引, 可以获取到最大序列对应的最后一个值, 然后通过倒序循环prevIndex, 来找到其对应的前缀索引
            if (dp[i] == maxans) {
                int targetIndex = i;
                while (prevIndex[targetIndex] != -10) {
                    index.add(nums[targetIndex]);
                    targetIndex--;
                }
            }
        }
        Collections.reverse(index);
        return index.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101};
        System.out.println(new LengthOfLIS().getLengthOfLIS(nums));
        System.out.println(Arrays.toString(new LengthOfLIS().getLengthOfLISArray(nums)));
    }
}
