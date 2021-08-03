package com.leetcode.question.group1;



import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yanbin
 * @date 2020/10/14 15:26
 */
//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
//
// L   C   I   R
//E T O E S I I G
//E   D   H   N
//
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
// string convert(string s, int numRows);
//
// 示例 1:
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
//
//
// 示例 2:
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G
// Related Topics 字符串
// 👍 868 👎 0
public class Question6 {

    private static final int NUMROWS_MIN = 2;


    public static String convert3(String s, int numRows) {
        int len = Math.min(s.length(), numRows);
        String []rows = new String[len];
        for(int i = 0; i< len; i++) {
            rows[i] = "";
        }
        int loc = 0;
        boolean down = false;
       for(int i=0,j=s.length();i<j;i++){
           rows[loc]=s.substring(i,i+1);
           if(loc == 0 || loc == numRows - 1) {
               down = !down;
           }
           loc+=down?1:-1;
       }
        String ans = "";
        for(String row : rows) {
            ans += row;
        }
        return ans;
    }
    public static String convert2(String s, int numRows) {
            if(numRows == 1) {
                return s;
            }

            int len = Math.min(s.length(), numRows);
            String []rows = new String[len];
            for(int i = 0; i< len; i++) {
                rows[i] = "";
            }
            int loc = 0;
            boolean down = false;

            for(int i=0;i<s.length();i++) {
                rows[loc] += s.substring(i,i+1);
                if(loc == 0 || loc == numRows - 1) {
                    down = !down;
                }
                loc += down ? 1 : -1;
            }

            String ans = "";
            for(String row : rows) {
                ans += row;
            }
            return ans;
    }


    public static String convert(String s, int numRows){
        String result = new String();

        //1、构造numRows个ArrayList
        ArrayList[] list = new ArrayList[numRows];

        for (int i = 0; i < numRows; i++) {
            list[i] = new ArrayList();
        }

        //2、构造一个循环队列  对于numRow = 4 ，遍历顺序为012321,012321,012321
        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i <numRows ; i++) {
            queue.offer(i);
        }
        for (int i = numRows - 2; i > 0 ; i--) {
            queue.offer(i);
        }

        //该循环队列实现Z形的遍历s,精彩的地方就在这
        for (int i = 0; i < s.length(); i++) {
            int list_no = queue.poll();
            list[list_no].add(s.charAt(i));
            queue.offer(list_no);
        }

        //3、生成result字符串返回
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < list[i].size(); j++) {
                result += list[i].get(j);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(convert2("ABC",4));
        System.out.println(convert("ABC",4));
        System.out.println("ABC".substring(2,3));
    }
}
