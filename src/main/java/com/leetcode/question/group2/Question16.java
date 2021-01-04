package com.leetcode.question.group2;

import java.util.Arrays;

/**
 * @author yanbin
 * @version 1.0
 * @date 2020/12/8 14:48
 * @description
 */
//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。
//
//
//
// 示例：
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics 数组 双指针
// 👍 637 👎 0
public class Question16 {
    public static int threeSumClosest(int[] nums, int target) {
          int result=0;
        boolean flag=false;
        Arrays.sort(nums);
        for(int k=0;k<nums.length-2;k++){
            int i=k+1, j=nums.length-1;
          while (i<j) {
              int sum = nums[k] + nums[i] + nums[j];
              if(!flag){
                  result=sum;
                  flag=true;
              }
              result = Math.abs(sum - target) > Math.abs(result - target) ? result : sum;
              if (sum > target) {
                  while (i < j && nums[j] == nums[--j]) {
                      ;
                  }
                  ;
              } else if (sum < target) {
                  while (i < j && nums[i] == nums[++i]) {
                      ;
                  }
                  ;
              } else {
                  return sum;
              }
          }
        }
        return result;
    }

    /**
     * 优化
     * @param args
     */
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length-2;i++){
            int left = i+1;
            int right = nums.length - 1;
            while(left != right){
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
                if(sum > target){
                    right--;
                }
                else{
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,1,-3};
        int target = 1;
        System.out.println(threeSumClosest(nums,target));
    }
}
