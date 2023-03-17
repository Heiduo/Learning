package com.heiduo;

public class LeetCode2209 {
    public static void main(String[] args) {

        /**
         * 788. 旋转数字
         */
        System.out.println("result:" + rotatedDigitsMine(100));
    }

    /**
     * 788. 旋转数字
     * 我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，
     * 我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。
     *
     * @param n
     * @return
     */
    public static int rotatedDigitsMine(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            boolean containsTrans = false;
            int number = i;
            while (number != 0) {
                if (number % 10 == 2 || number % 10 == 5
                        || number % 10 == 6 || number % 10 == 9) {
                    containsTrans = true;
                }else if (number % 10 == 0 || number % 10 == 1
                        || number % 10 == 8){

                }else {
                    containsTrans = false;
                    break;
                }
                number = number/10;
            }
            if (containsTrans){
                count++;
                System.out.println("number:" + i);
            }
        }

        return count;
    }
}
