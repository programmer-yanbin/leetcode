package com.leetcode.question.group1;

/**
 * @author yanbin
 * @date 2021/4/20
 */
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学
// 👍 6064 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.math.BigDecimal;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * @author yanbin
 */
public class Question2 {

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        l2.val = l1.val + l2.val;
        if (l2.val >= 10) {
            l2.val = l2.val % 10;
            if (l2.next != null) {
                l2.next.val = l2.next.val + 1;
                if (l2.next.val == 10) {
                    l2.next = addTwoNumbers(new ListNode(0, null), l2.next);
                }
            } else {
                l2.next = new ListNode(1, null);
            }
        }
        l2.next = addTwoNumbers(l1.next, l2.next);
        return l2;

    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode=null;
        StringBuilder str1=new StringBuilder().append(l1.val);
        StringBuilder str2=new StringBuilder().append(l2.val);
        while(l1.next!=null){
            str1.append(l1.next.val);
            l1=l1.next;
        }
        while(l2.next!=null){
            str2.append(l2.next.val);
            l2=l2.next;
        }

        String result=(new BigDecimal(str1.reverse().toString())).add(new BigDecimal(str2.reverse().toString())).toString();
        char[] chars = result.toCharArray();
        ListNode temp=null;
        for (int i = chars.length-1; i >=0; i--) {
            final  int x=Integer.parseInt(String.valueOf(chars[i]));
            if(listNode==null){
                listNode=new ListNode(x);
                temp=listNode;
            }else{
                temp.next=new ListNode(x);
                temp=temp.next;
            }
        }

        return listNode;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
