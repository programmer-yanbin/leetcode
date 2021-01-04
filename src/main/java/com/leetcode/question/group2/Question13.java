package com.leetcode.question.group2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  yanbin
 * @Description :
 * @date : 2020/10/24 14:06
 * @since :
 */
//罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
//
// 字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + I
//I 。
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//
//
// 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
//
//
//
// 示例 1:
//
// 输入: "III"
//输出: 3
//
// 示例 2:
//
// 输入: "IV"
//输出: 4
//
// 示例 3:
//
// 输入: "IX"
//输出: 9
//
// 示例 4:
//
// 输入: "LVIII"
//输出: 58
//解释: L = 50, V= 5, III = 3.
//
//
// 示例 5:
//
// 输入: "MCMXCIV"
//输出: 1994
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
//
//
//
// 提示：
//
//
// 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
// IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
// 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
//
// Related Topics 数学 字符串
// 👍 1092 👎 0
public class Question13 {
    /**
     * 独立解决
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int result=0;
        int i=0;
        while (s!=null){
            String symbol=symbols[i];
            if(s.startsWith(symbol)){
                if(!s.equals(symbol)){
                    s=s.substring(symbol.length());
                }else{
                    s=null;
                }
                result+=values[i];
            }else{
                i++;
            }

        }
        return result;
    }
    public int romanToInt2(String s) {
        Map<String, Integer> map = new HashMap<String,Integer>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int ans = 0;
        for(int i = 0;i < s.length();) {
            if(i + 1 < s.length() && map.containsKey(s.substring(i, i+2))) {
                ans += map.get(s.substring(i, i+2));
                i += 2;
            } else {
                ans += map.get(s.substring(i, i+1));
                i ++;
            }
        }
        return ans;
    }

    /**
     * 相对来说最优解
     *
     * 在代码实现上，可以往后看多一位，对比当前位与后一位的大小关系，从而确定当前位是加还是减法。当没有下一位时，做加法即可。
     * 也可保留当前位的值，当遍历到下一位的时，对比保留值与遍历位的大小关系，再确定保留值为加还是减。最后一位做加法即可。
     * @param s
     * @return
     */
    public int romanToInt3(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MMM"));

    }
}
