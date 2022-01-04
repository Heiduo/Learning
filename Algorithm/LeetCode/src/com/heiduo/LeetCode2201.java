package com.heiduo;

import java.util.*;

public class LeetCode2201 {
    public static void main(String[] args) {

        /***
         * 217. 存在重复元素
         */
//        System.out.println("data:" + containsDuplicate(new int[]{1, 2, 3, 4}));

        /***
         * 53. 最大子数组和
         */
        System.out.println("data:" + maxSubArrayMine(new int[]{5,4,-1,7,8}));
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public static int maxSubArrayMine(int[] nums) {
        int result = 0,max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (result+nums[i]<0){
                result = 0;
                max = Math.max(max,nums[i]);
            }else {
                result+=nums[i];
                max = Math.max(max,result);
            }
        }
        return max;
    }

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> res = new HashSet<Integer>();
        for (int i : nums)
            res.add(i);
        return res.size() < nums.length;
    }

    public static boolean containsDuplicateMine(int[] nums) {
        Arrays.parallelSort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
