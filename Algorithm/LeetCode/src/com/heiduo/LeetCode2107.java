package com.heiduo;

import java.util.Arrays;

import javax.lang.model.element.Element;

public class LeetCode2107 {
    public static void main(String[] args){
        /**
         * 704. 二分查找
         */
        // int [] data = {-1,0,3,5,9,12};
        // System.out.println("data:" + searchMine(data, 9));

        /**
         * 278. 第一个错误的版本
         */
        // System.out.println("data:" + firstBadVersionMine(1420736637));

        /**
         * 35. 搜索插入位置
         */
        // System.out.println("data:" + searchInsertMine(new int[]{1,3,5,6},2));

        /**
         * 977. 有序数组的平方
         */
        // System.out.println("data:" + Arrays.toString(sortedSquaresMine(new int[]{-7,-3,2,3,11})));

        /**
         * 189. 旋转数组
         */
        int[] data = new int[]{1,2,3,4};
        rotateMine2(data,2);
        System.out.println("data:" + Arrays.toString(data));
    }


    /**
     * 189. 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * @param nums
     * @param k
     */
    public static void rotateMine2(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        if(k==0){
            return;
        }
        int pos = 0;
        int last = nums[pos],next ;
        while(pos!=k*length){
            next = nums[(pos+k)%length];
            nums[(pos+k)%length] = last;
            last = next;
            pos = pos + k;
        }
    }

    public static void rotateMine(int[] nums, int k) {
        int length = nums.length;
        k = k%length;
        int[] nums2= new int[length];
        for (int i = 0; i < length; i++) {
            nums2[i] = nums[(i-k+length)%length];
        }
        for (int i = 0; i < nums2.length; i++) {
            nums[i] = nums2[i];
        }
    }

    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * @param nums
     * @return
     */
    public static int[] sortedSquaresMine(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0,right = nums.length - 1;
        int pos = right;
        while(left<=right){
            if(Math.abs(nums[left])<Math.abs(nums[right])){
                result[pos] = nums[right]*nums[right];
                right--;
            }else{
                result[pos] = nums[left]*nums[left];
                left++;
            }
            pos--;
        }
        return result;
    }

    /**
     * 1877. 数组中最大数对和的最小值
     * @param nums
     * @return
     */
    public static int minPairSumMin(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length/2; i++) {
            int value = nums[i] + nums[nums.length-i -1];
            if(max<value){
                max = value;
            }
        }
        return max;
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsertMine(int[] nums, int target) {
        int left = 0,right = nums.length-1;
        int mid = 0;
        while(left<=right){
            mid = left + (right-left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid]>target){
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }
        return nums[mid]<target?mid+1:mid;
    }

    private static int bad = 1150769282;
    /**
     * 278. 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
。
     * @param n
     * @return
     */
    public static int firstBadVersion(int n){
        int left = 1,right = n;
        while(left<=right){
            int mid = (right - left)/2 + left;
            if(isBadVersion(mid)){
                
                right = mid;
            }else{
            
                left = mid +1;
            }
        }
        return left;
    }

    public static int firstBadVersionMine(int n) {
        int pos = -1;
        int left = 1,right = n;
        while(left<=right){
            int mid = (right - left)/2 + left;
            if(isBadVersion(mid)){
                if(mid==left){
                    return mid;
                }
                right = mid;
            }else{
                if(mid == right - 1){
                    return right;
                }
                left = mid +1;
            }
        }
        return left;
    }

    public static boolean isBadVersion(int i){
        if(i >= bad) return true;
        return false;
    }

    /**
     * 704. 二分查找
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int pos = -1;
        int left = 0,right = nums.length-1;
        while(left<=right){
            //避免溢出 (right - left) >> 1
            int mid = (right-left)/2 + left;
            if(nums[mid] == target){
                pos = mid;
                break;
            }else if(nums[mid]>target){
                right = mid -1;
            }else{
                left = mid +1;
            }
        }
        return pos;
    }

    public static int searchMine(int[] nums, int target) {
        int pos = -1;
        int left = 0,right = nums.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(nums[mid] == target){
                pos = mid;
                break;
            }else if(nums[mid]>target){
                right = mid -1;
            }else{
                left = mid +1;
            }
        }
        return pos;
    }
}