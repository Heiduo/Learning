package com.heiduo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode2111 {
    public static void main(String[] args) {
        /** 488. 祖玛游戏
         *
         */
//         System.out.println("data:" + findMinStepMine("Let's take LeetCode contest",""));

        /**
         * 319. 灯泡开关
         */
//        System.out.println("data:" + bulbSwitchMine(10000000));

        /***
         * 1446. 连续字符
         */
//        System.out.println("data:" + maxPowerMine("cc"));

        /***
         * 506. 相对名次
         */
//        System.out.println("data:" + Arrays.toString(findRelativeRanksMine(new int[]{5, 4, 3, 2, 1})));

        /***
         * 1005. K 次取反后最大化的数组和
         */
//        System.out.println("data:" + largestSumAfterKNegationsMine(new int[]{3,-1,0,2},3));

        /***
         * 748. 最短补全词
         */
        System.out.println("data:" + shortestCompletingWordOther("s"
                , new String[]{"looks","pest","stew","show"}));
    }

    /**
     * 748. 最短补全词
     *
     * @param licensePlate
     * @param words
     * @return
     */

    public static String shortestCompletingWordOther(String licensePlate, String[] words){
        int[] cnt = getCnt(licensePlate);
        int position = -1;
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (position != -1 && words[i].length() >= length) {
                continue;
            }
            int[] cur = getCnt(words[i]);
            boolean ok = true;
            for (int j = 0; j < 26 && ok; j++) {
                if (cnt[j] > cur[j]) ok = false;
            }
            if (ok ){
                position = i;
                length = words[i].length();
            }
        }

        return words[position];
    }
    
    public static int[] getCnt(String s){
        int[] cnt = new int[26];
        for (char c :
                s.toCharArray()) {
            if (Character.isLetter(c)) {
                cnt[Character.toLowerCase(c) - 'a']++;
            }
        }
        return cnt;
    }
    
    public static String shortestCompletingWordMine(String licensePlate, String[] words) {
        char[] data = licensePlate.toCharArray();
        int[] plate = new int[26];
        for (int i = 0; i < data.length; i++) {
            int pos = 0;
            if (data[i] >= 'a') {
                pos = data[i] - 'a';
            } else {
                pos = data[i] - 'A';
            }
            if (pos >= 0 && pos < plate.length) {
                plate[pos]++;
            }
        }
        int position = -1;
        int[] plateTemp = new int[26];
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (position != -1 && words[i].length() >= length) {
                continue;
            }
            System.arraycopy(plate, 0, plateTemp, 0, plateTemp.length);
            for (int j = 0; j < words[i].length(); j++) {
                plateTemp[words[i].charAt(j)-'a']--;
            }
            boolean isContain = true;
            for (int i1 : plateTemp) {
                if (i1 > 0) {
                    isContain = false;
                    break;
                }
            }
            if (isContain) {
                position = i;
                length = words[i].length();
            }
        }
        if (position >= 0) {
            return words[position];
        }
        return "";

    }

    /**
     * 1005. K 次取反后最大化的数组和
     *
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegationsMine(int[] nums, int k) {
        int result = 0;
        Arrays.sort(nums);
        int i = 0;
        for (i = 0; i < k; i++) {
            if (i < nums.length) {
                if (nums[i] < 0) {
                    nums[i] = -nums[i];
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        Arrays.sort(nums);
        i = Math.abs(k - i) % 2;
        if (i == 1) {
            nums[0] = -nums[0];
        }
        for (int j = 0; j < nums.length; j++) {
            result += nums[j];
        }
        return result;
    }

    /**
     * 506. 相对名次
     *
     * @param score
     * @return
     */
    public static String[] findRelativeRanksMine(int[] score) {
        String[] result = new String[score.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }
        Arrays.sort(score);
        for (int i = 0; i < score.length; i++) {
            if (i == score.length - 1) {
                result[map.get(score[i])] = "Gold Medal";
            } else if (i == score.length - 2) {
                result[map.get(score[i])] = "Silver Medal";
            } else if (i == score.length - 3) {
                result[map.get(score[i])] = "Bronze Medal";
            } else {
                result[map.get(score[i])] = "" + (score.length - i);
            }
        }
        return result;
    }

    /**
     * 1446. 连续字符
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     *
     * @param s
     * @return
     */
    public static int maxPowerMine(String s) {
        int max = 1;
        char[] data = s.toCharArray();
        int length = 1;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == data[i + 1]) {
                length++;
            } else {
                max = Math.max(max, length);
                length = 1;
            }
        }
        return Math.max(max, length);
    }

    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public static boolean isValidSudokuMine(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isHasSameNineMine(board, i, j) || isHasSamRowOrLineMine(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHasSamRowOrLineMine(char[][] board, int a, int b) {
        if (board[a][b] == '.') {
            return false;
        }
        for (int i = a + 1; i < board.length; i++) {
            if (board[i][b] == board[a][b]) {
                return true;
            }
        }

        for (int i = b + 1; i < board[a].length; i++) {
            if (board[a][i] == board[a][b]) {
                return true;
            }
        }

        return false;
    }

    public static boolean isHasSameNineMine(char[][] board, int a, int b) {
        if (board[a][b] == '.') {
            return false;
        }
        int n = (a / 3) * 3;
        int m = (b / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[n + i][m + j] == '.' || (n + i == a && m + j == b) || board[n + i][m + j] != board[a][b]) {
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 319. 灯泡开关
     *
     * @param n
     * @return
     */
    public static int bulbSwitchMine(int n) {
        return (int) Math.sqrt(n);
    }

    /**
     * 488. 祖玛游戏
     * 你正在参与祖玛游戏的一个变种。
     * <p>
     * 在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
     * <p>
     * 你的目标是 清空 桌面上所有的球
     *
     * @param board
     * @param hand
     * @return
     */
    public static int findMinStepMine(String board, String hand) {

        return -1;
    }
}
