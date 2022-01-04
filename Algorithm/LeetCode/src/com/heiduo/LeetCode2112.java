package com.heiduo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode2112 {
    public static void main(String[] args) {

        /***
         * 567. 字符串的排列
         */
        System.out.println("data:" + checkInclusionMine("ab", "eidboaoo"));
    }

    /**
     * 567. 字符串的排列
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion3(String s1, String s2){
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < m; ++right) {
            int x = s2.charAt(right) - 'a';
            ++cnt[x];
            while (cnt[x] > 0) {
                --cnt[s2.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == n) {
                return true;
            }
        }
        return false;
    }
    public static boolean checkInclusion2(String s1, String s2){
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - n) - 'a';
            if (x == y) {
                continue;
            }
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            if (cnt[x] == 0) {
                --diff;
            }
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInclusion(String s1, String s2){
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInclusionMine(String s1, String s2) {
        int lengthS1 = s1.length(), lengthS2 = s2.length();

        if (lengthS1 > lengthS2) return false;
        int[] cnt = LeetCode2111.getCnt(s1);
        int position = 0;
        while(position+lengthS1<=lengthS2){
            String str = s2.substring(position,position+lengthS1);
            int[] cur = LeetCode2111.getCnt(str);
            boolean inclusion = true;
            for (int i = 0; i < cnt.length; i++) {
                if (cur[i]<cnt[i]){
                    position+= 1;
                    inclusion = false;
                    break;
                }else if (cur[i]>cnt[i]){
                    int pos = str.indexOf('a' + i) + 1;
                    position+=pos;
                    inclusion = false;
                    break;
                }
            }
            if (inclusion) return true;
        }

        return false;
    }

}
