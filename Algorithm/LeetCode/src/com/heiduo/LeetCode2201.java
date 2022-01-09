package com.heiduo;

import java.util.*;

public class LeetCode2201 {
    public static void main(String[] args) {

        /***
         * 217. 存在重复元素
         */
//        System.out.println("data:" + containsDuplicate(new int[]{1, 2, 3, 4}));

        /**
         * 53. 最大子数组和
         */
//        System.out.println("data:" + maxSubArrayMine(new int[]{5,4,-1,7,8}));

        /**
         * 350. 两个数组的交集 II
         */
//        System.out.println("data:" + Arrays.toString(intersectMine(new int[]{1,2,2,1},new int[]{2,2})));

        /**
         * 121. 买卖股票的最佳时机
         */
//        System.out.println("data:" + maxProfitMine2(new int[]{7,1,5,3,6,4}));

        /**
         * 566. 重塑矩阵
         */
//        System.out.println("data:" + matrixReshapeMine(new int[][2]{[1,2],[3,4]}));


        /**
         * 118. 杨辉三角
         */
//        System.out.println("data:" + generateMine(2).toString());

        /**
         * 1614. 括号的最大嵌套深度
         */
//        System.out.println("data:" + maxDepthMine("1+(2*3)/(2-1)"));

        /**
         * 89. 格雷编码
         */
//        System.out.println("data:" + grayCodeMine(2).toString());

        /**
         * 1629. 按键持续时间最长的键
         */
//        System.out.println("data:" + slowestKeyMine(new int[]{28,65,97},"gaf"));

        /**
         * 387. 字符串中的第一个唯一字符
         */
//        System.out.println("data:" + firstUniqCharMine("loveleetcode"));

        /**
         * 383. 赎金信
         */
//        System.out.println("data:" + canConstructMine("aa","ab"));

        /**
         * 242. 有效的字母异位词
         */
          System.out.println("data:" + isAnagramMine("rat","nagaram"));

    }

    /**
     * 242. 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagramMine(String s, String t) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i)-'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            result[t.charAt(i)-'a']--;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i]!=0){
                return false;
            }
        }

        return true;
    }

    /**
     * 383. 赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstructMine(String ransomNote, String magazine) {
        int[] result = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            result[magazine.charAt(i)-'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            result[ransomNote.charAt(i)-'a']--;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i]<0){
                return false;
            }
        }

        return true;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public static int firstUniqCharMine(String s) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (result[s.charAt(i)-'a'] == 1){
                return i;
            }
        }
        return -1;

    }

    /**
     * 1629. 按键持续时间最长的键
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public static char slowestKeyMine(int[] releaseTimes, String keysPressed) {
        char result = keysPressed.charAt(0);
        int max = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] - releaseTimes[i-1] > max){
                result = keysPressed.charAt(i);
                max = releaseTimes[i] - releaseTimes[i-1];
            }else if (releaseTimes[i] - releaseTimes[i-1] == max){
                result = (char) Math.max(result,keysPressed.charAt(i));
            }
        }

        return result;
    }

    /**
     * 89. 格雷编码
     *
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            list.add((i >> 1) ^ i);
        }
        return list;
    }


    public static List<Integer> grayCodeMine(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int i = 1; i < n; i++) {
            int length = list.size();
            int first = 1 << i;
            System.out.println("first:" + first);
            System.out.println("length:" + length);
            for (int j = 0; j < length; j++) {
                list.add(list.get(length-j-1) | first);
            }
        }

        return list;
    }

    /**
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public static void setZeroesMine(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean firstColumn = false;
        boolean firstRow = false;
        int rows = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColumn = true;
                break;
            }
        }
        for (int i = 0; i < column; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < column; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < column; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (firstColumn) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRow) {
            for (int i = 0; i < column; i++) {
                matrix[0][i] = 0;
            }
        }
    }


    /**
     * 1614. 括号的最大嵌套深度
     *
     * @param s
     * @return
     */
    public static int maxDepth(String s) {
        int max = 0;
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                size++;
                max = Math.max(max, size);
            } else if (s.charAt(i) == ')') {
                size--;
            }
        }
        return max;

    }

    public static int maxDepthMine(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                max = Math.max(max, stack.size());
            } else if (s.charAt(i) == ')') {
                stack.pop();
            }
        }
        return max;

    }

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generateMine(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> listSub = new ArrayList<>();
        listSub.add(1);
        list.add(listSub);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> listP = list.get(i - 2);
            List<Integer> listNext = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    listNext.add(1);
                } else if (j == i - 1) {
                    listNext.add(1);
                } else {
                    listNext.add(listP.get(j - 1) + listP.get(j));
                }
            }

            list.add(listNext);
        }
        return list;
    }

    /**
     * 566. 重塑矩阵
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public static int[][] matrixReshapeMine(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length != r * c) {
            return mat;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < mat.length * mat[0].length; i++) {
            result[i / c][i % c] = mat[i / mat[0].length][i % mat[0].length];
        }
        return result;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public static int maxProfitMine2(int[] prices) {
        int[] result = new int[prices.length];
        int pos = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[pos] > 0) {
                result[i] = prices[i] - prices[pos];
            } else {
                pos = i;
            }
        }
        pos = 0;
        for (int i = 0; i < result.length; i++) {
            pos = Math.max(pos, result[i]);
        }
        return pos;
    }

    //超时
    public static int maxProfitMine(int[] prices) {
//        int[][] result = new int[prices.length][prices.length];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(Math.max(0, prices[j] - prices[i]), max);
            }
        }
        return max;
    }

    /**
     * 350. 两个数组的交集 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectMine(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
//        int length = Math.min(nums1.length,nums2.length);
        List<Integer> list = new ArrayList<>();
        int a = 0, b = 0;
        while (a < nums1.length && b < nums2.length) {
            if (nums1[a] == nums2[b]) {
                list.add(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] > nums2[b]) {
                b++;
            } else {
                a++;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
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
        int result = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (result + nums[i] < 0) {
                result = 0;
                max = Math.max(max, nums[i]);
            } else {
                result += nums[i];
                max = Math.max(max, result);
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
