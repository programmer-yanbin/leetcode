package com.leetcode.question.group2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanbin
 * @version 1.0
 * @date 2021/8/16 14:57
 * @description
 */

//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b
//], nums[c], nums[d]] ：
//
//
// 0 <= a, b, c, d < n
// a、b、c 和 d 互不相同
// nums[a] + nums[b] + nums[c] + nums[d] == target
//
//
// 你可以按 任意顺序 返回答案 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics 数组 双指针 排序
// 👍 919 👎 0


public class Question18 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return resultList;
        }
        //数组排序
        Arrays.sort(nums);

        int k = nums.length;
        for (int i = 0; i < k - 3; i++) {
            //如果最小值大于目标值 则无解
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            //如果最大值小于目标值 则当前数加1
            if (nums[i] + nums[k - 3] + nums[k - 2] + nums[k - 1] < target) {
                continue;
            }
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < k - 2; j++) {
                //去重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = k - 1;
                while (left < right) {
                    int resultTarget = nums[i] + nums[j] + nums[left] + nums[right];
                    if (resultTarget == target) {
                        resultList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (resultTarget < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 0, -1, 0, -2, 2, 0, 0};
        int[] nums = {2, 2, 2, 2, 2, 2};
        System.out.println(fourSum(nums, 8));
    }
}
