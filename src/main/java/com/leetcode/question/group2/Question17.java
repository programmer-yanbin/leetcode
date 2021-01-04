package com.leetcode.question.group2;

import java.util.*;

/**
 * @author yanbin
 * @version 1.0
 * @date 2020/12/8 16:06
 * @description
 */
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
// 示例:
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//
// 说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
// Related Topics 深度优先搜索 递归 字符串 回溯算法
// 👍 1028 👎 0


public class Question17 {
    static  String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    static List<String> res = new ArrayList<String>();
    static List<String> res1 = new ArrayList<String>();
    public static List<String> letterCombinations(String digits) {
        if(digits==null||digits.trim().length()==0){
            return res1;
        }
       combinationStr(0,new StringBuilder(""),digits);

        return res1;
    }

    public static  void combinationStr(int index,StringBuilder stb,String digits){
        if(index==digits.length()){
            res1.add(stb.toString());
            return;
        }
        int newIndex=(int)(digits.charAt(index)-'0');
        String newDigits=letter_map[newIndex];
        for (int i = 0; i <newDigits.length() ; i++) {
            stb.append(newDigits.charAt(i));
            combinationStr(index+1,stb,digits);
            stb.deleteCharAt(stb.length()-1);
        }
    }
    /**
     * 解法1 递归
     */


    public static List<String> letterCombinations1(String digits) {
        //注意边界条件
        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        iterStr(digits, new StringBuilder(), 0);
        return res;
    }
    //最终输出结果的list


    //递归函数
    static void  iterStr(String str, StringBuilder letter, int index) {
        //递归的终止条件，注意这里的终止条件看上去跟动态演示图有些不同，主要是做了点优化
        //动态图中是每次截取字符串的一部分，"234"，变成"23"，再变成"3"，最后变成""，这样性能不佳
        //而用index记录每次遍历到字符串的位置，这样性能更好
        if(index == str.length()) {
            res.add(letter.toString());
            return;
        }
        //递归实现
        //获取index位置的字符，假设输入的字符是"234"
        //第一次递归时index为0所以c=2，第二次index为1所以c=3，第三次c=4
        //subString每次都会生成新的字符串，而index则是取当前的一个字符，所以效率更高一点
        char c = str.charAt(index);
        //map_string的下表是从0开始一直到9， c-'0'就可以取到相对的数组下标位置
        //比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
        int pos = c - '0';
        String map_string = letter_map[pos];
        //遍历字符串，比如第一次得到的是2，页就是遍历"abc"
        for(int i=0;i<map_string.length();i++) {
            //调用下一层递归，用文字很难描述，请配合动态图理解
            letter.append(map_string.charAt(i));
            //如果是String类型做拼接效率会比较低
            //iterStr(str, letter+map_string.charAt(i), index+1);
            iterStr(str, letter, index+1);
            letter.deleteCharAt(letter.length()-1);
        }
    }



    /**
     * 递归 区别于上一种是list集合存储映射关系 没用数组
     * @param digits
     * @return
     */
    public static List<String> letterCombinations2(String digits) {
        if (digits.length() == 0){
            return res;
        }
        dfs(0,digits,new StringBuilder());
        return res;
    }

    public static void dfs(int k, String digits, StringBuilder pre) {
        //出递归条件
        if (k == digits.length()) {
            res.add(pre.toString());
            return;
        }
        //获取对应的字符串集合
        int n = Integer.valueOf(String.valueOf(digits.charAt(k)));
        List<String> list = getCh(n);

        for (int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder(pre);
            sb.append(list.get(i));
            //进行递归
            dfs(k + 1, digits, sb);
        }
    }

    public static List<String> getCh(int n) {
        switch (n) {
            case 2:
                return Arrays.asList("a", "b", "c");
            case 3:
                return Arrays.asList("d", "e", "f");
            case 4:
                return Arrays.asList("g", "h", "i");
            case 5:
                return Arrays.asList("j", "k", "l");
            case 6:
                return Arrays.asList("m", "n", "o");
            case 7:
                return Arrays.asList("p", "q", "r", "s");
            case 8:
                return Arrays.asList("t", "u", "v");
            case 9:
                return Arrays.asList("w", "x", "y", "z");
            default:
                return new ArrayList<String>();
        }
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations1("23"));;
        System.out.println(letterCombinations("23"));;

    }
}
