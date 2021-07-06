package com.heiduo;

import javax.lang.model.element.Element;

public class LeetCode2107 {
    public static void main(String[] args){
        /**
         * 704. 二分查找
         */
        int [] data = {-1,0,3,5,9,12};
        System.out.println("data:" + searchMine(data, 9));
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