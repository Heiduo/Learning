package com.heiduo;

import java.util.HashSet;
import java.util.Set;

public class LeetCode2303 {
    public static void main(String[] args) {

        /**
         * 2395. 和相等的子数组
         */
        System.out.println("result:" + findSubarrays(new int[]{1, 2, 3, 4, 5}));

        /**
         * 2373. 矩阵中的局部最大值
         */
//        System.out.println("result:" + largestLocalMine());

    }

    /**
     * 2373. 矩阵中的局部最大值
     * 给你一个大小为 n x n 的整数矩阵 grid 。
     *
     * 生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
     *
     * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
     * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
     * @param grid
     * @return
     */
    public static int[][] largestLocalMine(int[][] grid) {
        int[][] maxGrid = new int[grid.length-2][grid.length-2];
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid.length - 2; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (grid[i+k][j+l]>max){
                            max = grid[i+k][j+l];
                        }
                    }
                }

                maxGrid[i][j] = max;
            }
        }
        return maxGrid;
    }

    public static int[][] largestLocal(int[][] grid) {
        int[][] maxGrid = new int[grid.length-2][grid.length-2];
        for (int i = 0; i < grid.length - 2; i++) {
            for (int j = 0; j < grid.length - 2; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (grid[i+k][j+l]>maxGrid[i][j]){
                            maxGrid[i][j] = grid[i+k][j+l];
                        }
                    }
                }
            }
        }
        return maxGrid;
    }

    public static int[][] largestLocalMine2(int[][] grid) {
        int[][] maxGrid = new int[grid.length-2][grid.length-2];

        return maxGrid;
    }

    /**
     * 2395. 和相等的子数组
     * 给你一个下标从 0 开始的整数数组 nums ，判断是否存在 两个 长度为 2 的子数组且它们的 和 相等。注意，这两个子数组起始位置的下标必须 不相同 。
     * <p>
     * 如果这样的子数组存在，请返回 true，否则返回 false 。
     * <p>
     * 子数组 是一个数组中一段连续非空的元素组成的序列。
     */
    public static boolean findSubarraysMine(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        for (int i = 0; i < nums.length - 2; i++) {
//            if (nums[i] == nums[i+2]) return true;
            for (int j = i+1; j < nums.length - 1; j++) {
                if ((nums[i] + nums[i+1]) == (nums[j]+nums[j+1])) return true;
            }
        }
        return false;
    }

    public static boolean findSubarrays(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            set.add(nums[i]+nums[i+1]);
        }
        return set.size() != (nums.length-1);
    }
}
