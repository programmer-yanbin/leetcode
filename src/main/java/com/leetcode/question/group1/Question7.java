package com.leetcode.question.group1;

/**
 * @author yanbin
 * @date 2020/10/15 15:11
 */
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
//输出: 321
//
//
// 示例 2:
//
// 输入: -123
//输出: -321
//
//
// 示例 3:
//
// 输入: 120
//输出: 21
//
//
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学
// 👍 2262 👎 0
public class Question7 {
    public static int reverse(int x) {
        boolean flag=false;
        if(x<0){
            flag=true;
        }
        String result = String.valueOf(x);
        if(flag){
            result=result.substring(1);
        }
        StringBuilder str=new StringBuilder(result);
        StringBuilder reverse = str.reverse();
        Integer integer = 0;
        try {
            integer=Integer.valueOf(reverse.toString());
        } catch (Exception e) {
            return 0;
        }
        return flag?-integer.intValue():integer.intValue();
    }
    public int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -214748364 || (res == -214748364 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(reverse(-512355));
        System.out.println(reverse(Integer.MAX_VALUE));
        System.out.println(Integer.MAX_VALUE/10);
        System.out.println(Integer.MAX_VALUE%10);
        System.out.println(reverse(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE%10);
    }
}
