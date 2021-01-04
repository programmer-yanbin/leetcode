package com.leetcode.question.group2;

import java.util.Arrays;
import java.util.Date;

/**
 * @author yanbin
 * @version 1.0
 * @date 2020/10/24 15:58
 * @description
 */
//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
// 示例 1:
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
//
//
// 示例 2:
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
//
//
// 说明:
//
// 所有输入只包含小写字母 a-z 。
// Related Topics 字符串
// 👍 1323 👎 0
public class Question14 {

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        for(int i = 0 ; i < strs.length - 1 ; i++){
            strs[i+1] = commonPrefix(strs[i],strs[i+1]);
        }
        return strs[strs.length - 1];
    }
    private String commonPrefix(String s1 , String s2){
        int len = (s1.length() > s2.length())? s2.length() : s1.length();
        int i = 0 ;
        while(i < len){
            if(s1.charAt(i) != s2.charAt(i)){
                break;
            }
            i++;
        }
        return s1.substring(0,i);
    }

    /**
     * 其实，如果判断几个字符串的公共最长子串，可以先排序，然后直接比较第一个和最后一个字符串中最长的公共子串个数即可。
     * @param strs
     * @return
     */
    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int len = strs.length;
        Arrays.sort(strs);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            if (strs[len - 1].charAt(i) == strs[0].charAt(i)) {
                sb.append(strs[0].charAt(i));
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public String longestCommonPrefix2(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int num = strs.length;
        String no = "";
        String min;

        if(num == 0) {
            return no;
        }else if(num == 1){
            return strs[0];
        }else{
            min = strs[0];
        }

        for(int i = 1; i < num; i++){
            if(strs[i].length() < min.length()){
                min = strs[i];
            }
        }
        if(min.equals("")){
            return no;
        }

        if(strs[0].charAt(0) != strs[1].charAt(0)){
            return no;
        }

        int len = min.length();

        loop: for(int i = 0; i < len; i++){
            for(int m = 0; m < num; m++){
                if(min.charAt(i) != strs[m].charAt(i)) {
                    break loop;
                }
            }
            sb.append(min.charAt(i));
        }

        return sb.equals("") ? no : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix1(new String[]{"sdf","df","werdsfssdf"}));
    }
}
