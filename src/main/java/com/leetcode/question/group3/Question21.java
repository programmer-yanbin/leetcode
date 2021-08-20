package com.leetcode.question.group3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yanbin
 * @version 1.0
 * @date 2021/8/19 10:12
 * @description
 */

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例 1：
//
//
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
//
// 示例 2：
//
//
//输入：l1 = [], l2 = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：l1 = [], l2 = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 两个链表的节点数目范围是 [0, 50]
// -100 <= Node.val <= 100
// l1 和 l2 均按 非递减顺序 排列
//
// Related Topics 递归 链表
// 👍 1854 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class Question21 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummyhead = new ListNode();
        ListNode head = dummyhead;
        while(l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                dummyhead.next = l2;
                l2 = l2.next;
            } else {
                dummyhead.next = l1;
                l1 = l1.next;
            }
            dummyhead = dummyhead.next;
        }
        dummyhead.next = l1 == null ? l2:l1;
        return head.next;
    }

    /**
     * 递归是最多提交的解法  真的好？？？？？？？？？？
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }
    }
    public static void main(String[] args) {

    }
}
