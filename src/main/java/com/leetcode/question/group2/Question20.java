package com.leetcode.question.group2;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author yanbin
 * @version 1.0
 * @date 2021/8/18 15:14
 * @description
 */

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//
//
//输入：s = "{[]}"
//输出：true
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串
// 👍 2579 👎 0


public class Question20 {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                switch (ch) {
                    case ')':
                        if (stack.peek() != '(') {
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.peek() != '{') {
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.peek() != '[') {
                            return false;
                        }
                        break;
                }
                stack.pop();
            } else {
                stack.add(ch);
            }
        }
        return stack.isEmpty();
    }



    public boolean isValid1(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }




    public boolean isValid2(String s){

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<Character, Character>() {
            {
                put('}', '{');
                put(']', '[');
                put(')', '(');
            }
        };
// 顺序读取字符
        for (Character c : s.toCharArray()) {
            // 是右括号 && 栈不为空
            if (!stack.isEmpty() && map.containsKey(c)) {
                // 取其对应的左括号直接和栈顶比
                if (map.get(c).equals(stack.peek())) {
                    // 相同则抵消，出栈
                    stack.pop();
                } else {
                    // 不同则直接返回
                    return false;
                }
            } else {
                // 左括号，直接入栈
                stack.push(c);
            }
        }
        // 看左右是否抵消完
        return stack.isEmpty();
    }







    public static void main(String[] args) {
        String str = "{}";

        System.out.println(isValid(str));
    }


}
