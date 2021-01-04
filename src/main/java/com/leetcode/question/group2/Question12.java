package com.leetcode.question.group2;

/**
 * @author yanbin
 * @date 2020/10/24 10:39
 */
//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
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
// 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
//
// 示例 1:
//
// 输入: 3
//输出: "III"
//
// 示例 2:
//
// 输入: 4
//输出: "IV"
//
// 示例 3:
//
// 输入: 9
//输出: "IX"
//
// 示例 4:
//
// 输入: 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
//
//
// 示例 5:
//
// 输入: 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
// Related Topics 数学 字符串
// 👍 432 👎 0
public class Question12 {
    static String[] thousands = {"M"};
    static String[] hundreds = {"C", "D", "M"};
    static String[] tens = {"X", "L", "C"};
    static String[] ones = {"I", "V", "X"};

    /**
     * 独立解决
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            return null;
        }
        StringBuilder result = new StringBuilder(16);
        //判断数值区间
        if (num >= 1000) {
            for (int i = 0, j = num / 1000; i < j; i++) {
                result.append(thousands[0]);
            }
            num = num % 1000;
        }
        if (num >= 100) {
            numberAppend(result, hundreds, num / 100);
            num = num % 100;
        }
        if (num >= 10) {
            numberAppend(result, tens, num / 10);
            num = num % 10;
        }
        if (num >= 0) {
            numberAppend(result, ones, num);
        }


        return result.toString();
    }

    public static StringBuilder numberAppend(StringBuilder result, String[] str, int number) {
        switch (number) {
            case 0:
                break;
            case 1:
                result.append(str[0]);
                break;
            case 2:
                result.append(str[0]).append(str[0]);
                break;
            case 3:
                result.append(str[0]).append(str[0]).append(str[0]);
                break;
            case 4:
                result.append(str[0]).append(str[1]);
                break;
            case 5:
                result.append(str[1]);
                break;
            case 6:
                result.append(str[1]).append(str[0]);
                break;
            case 7:
                result.append(str[1]).append(str[0]).append(str[0]);
                break;
            case 8:
                result.append(str[1]).append(str[0]).append(str[0]).append(str[0]);
                break;
            case 9:
                result.append(str[0]).append(str[2]);
                break;
        }
        return result;
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman1(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num >= 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 时间复杂度：*O(1)*。无论输入的大小，都会执行相同数量的操作。因此，时间复杂度是常数的。
     * 空间复杂度：*O(1)*，虽然我们使用数组，但不管输入的大小，它们都是相同的大小。因此，它们是常数级空间。
     * <p>
     * 这种方法的缺点是，如果要扩展罗马数字，它是不灵活的（这是一个有趣的后续问题）。例如，如果我们说符号 `H` 现在表示 `5000`，而 `P` 现在表示 `10000`，允许我们表示多达 `39999` 的数字，会怎么样？
     * 方法 1 修改起来要快得多，因为您只需要将这两个值添加到代码中，而不需要进行任何计算。但是对于
     * 方法 2，您需要计算并硬编码 `10` 个新的表示。如果我们再加上一些符号就能达到 `39999999` 呢？
     * 方法2变得越来越难管理，我们添加的符号越多。
     *
     * @param num
     * @return
     */
    public String intToRoman2(int num) {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }

    public String intToRoman3(int num) {
        StringBuilder ans = new StringBuilder();
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};  // 罗马数字
        int[] arab = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};  // 阿拉伯数字
        int index = 0;
        while (num > 0) {
            int count = num / arab[index];
            while (count-- > 0) {
                ans.append(roman[index]);
            }
            num %= arab[index];
            index++;
        }
        return ans.toString();
    }


    public String intToRoman4(int num) {
        if (num < 1 || num > 3999) {
            return null;
        }
        StringBuilder result = new StringBuilder(16);
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0, j = values.length; i < j && num >= 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }

        return result.toString();
    }


    public static void main(String[] args) {
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(5));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(10));
        System.out.println(intToRoman(44));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }
}
