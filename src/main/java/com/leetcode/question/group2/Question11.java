package com.leetcode.question.group2;

/**
 * @author yanbin
 * @date 2020/10/23 14:39
 */
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。
//
//
//
//
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
//
//
// 示例：
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49
// Related Topics 数组 双指针
// 👍 1937 👎 0

public class Question11 {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j){
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    public int maxArea2(int[] height) {
        // 双指针
        int maxAreaValue = 0;
        int left = 0;
        int right = height.length - 1;
        for (int i = 0; i < height.length; i++) {
            if (right != left) {
                // 选择短板
                int minHeight = height[left] > height[right] ? height[right] : height[left];
                // 记录最大的面积值
                maxAreaValue = minHeight * (right - left) > maxAreaValue ? minHeight * (right - left) : maxAreaValue;
                // 找出最小的高度，并收紧
                if (height[left] > height[right]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return maxAreaValue;
    }
}
