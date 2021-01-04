package com.leetcode.question.group1;

/**
 * @author yanbin
 * @date 2020/10/21 18:27
 */
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
// '.' 匹配任意单个字符
//'*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
// 说明:
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
//
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3:
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
// 示例 4:
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
//
//
// 示例 5:
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false
// Related Topics 字符串 动态规划 回溯算法
// 👍 1625 👎 0
public class Question10_Hard {
    /**
     * 2）动态规划
     * *s*和*p*倒着看，*dp[i][j]*的取值分为以下几种情况：
     * * 1.*p[j - 1]*为普通字符,若*s[i - 1] == p[j - 1]*，则*dp[i][j] = dp[i - 1][j - 1]*，否则匹配失败
     * * 2.*p[j - 1]*为`'.'`，则*dp[i][j] = dp[i - 1][j - 1]*
     * * 3.*p[j - 1]*为`'*'`：
     *   * (1)不看，则*dp[i][j] = dp[i][j - 2]*
     *   * (2)看，则*dp[i][j] = dp[i - 1][j]*
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray(), ptr = p.toCharArray();
        boolean[][] dp = new boolean[str.length + 1][ptr.length + 1];
        dp[0][0] = true;
        for (int i = 0; i <= str.length; i++) {
            for (int j = 1; j <= ptr.length; j++) {
                if(ptr[j - 1] != '*') {
                    if(i > 0 && (str[i - 1] == ptr[j - 1] || ptr[j - 1] == '.')) dp[i][j] = dp[i - 1][j - 1];
                }else { //ptr[j - 1] == '*'
                    if(j > 1) dp[i][j] |= dp[i][j - 2];   //不看
                    if(i > 0 && j > 1 && (str[i - 1] == ptr[j - 2] || ptr[j - 2] == '.'))dp[i][j] |= dp[i - 1][j];    //看
                }
            }
        }
        return dp[str.length][ptr.length];
    }

    /**
     * 从头处理*s*和*p*两个字符串：
     *
     * 首先，如果*p*为空了，此时若*s*不为空，则说明匹配失败，直接返回*false*，如果都为空，说明匹配结束，返回*true*。
     * 其次，如果*p*不为空，存在两种情况，一种是单一匹配，一种是`'*'`的任意匹配。
     *   * 我们先假设单一匹配，创建一个`boolean`类型的`match`变量，计算一下单一匹配是否成功：
     *     * 在单一匹配的情况下，*s*不能为空。
     *     * *s*和*p*的当前第一个字符需要相等，或者p的第一个字符为`'.'`。
     *
     *   如果成功就可以将两个字符串的第一位去掉，继续匹配后续的字符。
     *   * 还有可能是要进行任意匹配，任意匹配的条件是当前*p*的大小需要大于2，并且*p[1]*要为`'*'`，这个时候，可以匹配*s*中任意数量的字符*p[0]*。那么又是两种情况：
     *     * 看：如果看（匹配）一次，就相当于去掉*s*的第一个字符，继续向后匹配。
     *     * 不看：如果不看（就是不进行匹配=匹配0次），就相当于*s*不变，*p*向后去掉两位（字符*p[0]*和*p[1]*的`'*'`）。
     * @param s
     * @param s1
     * @param p
     * @param p1
     * @return
     */
    public boolean isMatchChar(char[] s, int s1, char[] p, int p1) {
        if(p1 >= p.length) return s1 >= s.length;
        boolean match = s1 < s.length && ((s[s1] == p[p1]) || p[p1] == '.');
        if(p.length - p1 >= 2 && p[p1 + 1] == '*') return isMatchChar(s, s1, p, p1 + 2) || (match && isMatchChar(s, s1 + 1, p, p1));
        return match && isMatchChar(s, s1 + 1, p, p1 + 1);
    }
    public boolean isMatch1(String s, String p) {
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        return isMatchChar(ss, 0, pp, 0);
    }


    public boolean isMatch3(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {
            // p的第二个字符是 '*'
            //1,字符"*"把前面的字符消掉，也就是匹配0次
            //2,字符"*"匹配1次或多次
            return isMatch3(s, p.substring(2)) || (s.length() > 0 && comp(s, p)) && isMatch3(s.substring(1), p);
        } else {
            // p的第二个字符不是 '*'，判断首字符是否相同，如果相同再从第二位继续比较
            return s.length() > 0 && comp(s, p) && (isMatch3(s.substring(1), p.substring(1)));
        }
    }
    //比较s的首字符和p的首字符是否匹配
    private boolean comp(String s, String p) {
        return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
    }

}
