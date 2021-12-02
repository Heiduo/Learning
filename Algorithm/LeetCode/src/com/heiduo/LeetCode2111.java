package com.heiduo;

public class LeetCode2111 {
    public static void main(String[] args) {
        /** 488. 祖玛游戏
         *
         */
//         System.out.println("data:" + findMinStepMine("Let's take LeetCode contest",""));

        /**
         * 319. 灯泡开关
         */
        System.out.println("data:" + bulbSwitchMine(10000000));

        /***
         * 1446. 连续字符
         */
        System.out.println("data:" + maxPowerMine("cc"));

    }

    /**
     * 1446. 连续字符
     * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
     * @param s
     * @return
     */
    public static int maxPowerMine(String s) {
        int max = 1;
        char[] data = s.toCharArray();
        int length = 1;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == data[i+1]){
                length++;
            }else {
                max = Math.max(max,length);
                length = 1;
            }
        }
        return Math.max(max,length);
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
                if (isHasSameNineMine(board,i,j) || isHasSamRowOrLineMine(board,i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHasSamRowOrLineMine(char[][] board, int a, int b){
        if (board[a][b] == '.'){
            return false;
        }
        for (int i = a+1; i < board.length; i++) {
            if (board[i][b] == board[a][b]){
                return true;
            }
        }

        for (int i = b+1; i < board[a].length; i++) {
            if (board[a][i] == board[a][b]){
                return true;
            }
        }

        return false;
    }

    public static boolean isHasSameNineMine(char[][] board, int a, int b) {
        if (board[a][b] == '.'){
            return false;
        }
        int n = (a / 3) * 3;
        int m = (b / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[n+i][m+j]=='.' || (n+i==a && m+j == b) || board[n+i][m+j]!=board[a][b]){
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
