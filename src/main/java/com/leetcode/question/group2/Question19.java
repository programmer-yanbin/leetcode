package com.leetcode.question.group2;

/**
 * @author yanbin
 * @version 1.0
 * @date 2021/8/16 17:12
 * @description
 */

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 进阶：你能尝试使用一趟扫描实现吗？
//
//
//
// 示例 1：
//
//
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
//
// 示例 2：
//
//
//输入：head = [1], n = 1
//输出：[]
//
//
// 示例 3：
//
//
//输入：head = [1,2], n = 1
//输出：[1]
//
//
//
//
// 提示：
//
//
// 链表中结点的数目为 sz
// 1 <= sz <= 30
// 0 <= Node.val <= 100
// 1 <= n <= sz
//
// Related Topics 链表 双指针
// 👍 1507 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


public class Question19 {

    public class ListNode {
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


    /**
     * 双指针解题思路   答案中有动态图解
     * https://pic.leetcode-cn.com/cc43daa8cbb755373ce4c5cd10c44066dc770a34a6d2913a52f8047cbf5e6e56-file_1559548337458
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        if (head.next == null) {
            return null;
        }

        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }


    /**
     * 定位到具体删除的直接遍历 更快一点 和双指针一个思路
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode L = head;
        int count = 1;
        while (L.next != null) {
            count++;
            L = L.next;
        }
        if (n == count) {
            head = head.next;
        } else {
            L = head;
            for (int i = 1; i < count - n; i++) {
                L = L.next;
            }
            L.next = L.next.next;
        }
        return head;
    }

    /**
     * 遍历全部节点集合 定位到具体需要删除的节点 修改上一个节点的指针，操作复杂，不好理解 不推荐
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode p = head;
        List<ListNode> nodelist = new ArrayList<>();
        while (p != null) {
            nodelist.add(p);
            p = p.next;
        }
        int len = nodelist.size();
        if (len == 1 && len == n) {
            return null;
        }
        if (len == n) {
            return nodelist.get(1);
        }
        nodelist.get(len - n - 1).next = nodelist.get(len - n).next;
        return head;
    }


}





