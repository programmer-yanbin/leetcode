package com.leetcode.question.group1;
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5344 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author yanbin
 * @date 2021/4/20
 */
public class Question3 {
    /**
     * 7ms  个人能力范围内最优解
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n=s.length(),ans=0;
        Map<Character , Integer> map=new HashMap<>();
        for(int end=0,start=0;end<n;end++){
            if(map.containsKey(s.charAt(end))){
                start=Math.max(map.get(s.charAt(end))+1,start);
            }
            ans=Math.max(ans,end-start+1);
            map.put(s.charAt(end),end);
        }
        return ans;
    }


    /**
     *  119ms  速度太慢不建议  参考思路
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        int size=0;
        HashSet<Character> temp=new HashSet<>();

        for (int i = 0; i < chars.length; i++) {
            for (int j=i;j<chars.length;j++){
                if(temp.contains(chars[j])){
                    if(size<temp.size()) {
                        size=temp.size();
                    }
                    temp=new HashSet<>();
                    break;
                }else{
                    temp.add(chars[j]);
                    if(size<temp.size()) {
                        size=temp.size();
                    }
                }
            }
            temp=new HashSet<>();
        }

        return size;
    }
}
