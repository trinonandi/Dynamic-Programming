# Dynamic-Programming

### 0/1 Knap Sack
----
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item or don’t pick it (0-1 property).
**Input:** val[] = {60, 100, 120}, wt[] = {10, 20, 30}, W = 50  
**Output:** 220

### Subset Sum Problem
---
Given a set of non-negative integers, and a value _sum_, determine if there is a subset of the given set with sum equal to given _sum_
**Input:** set[] = {3, 34, 4, 12, 5, 2}, sum = 9
**Output:** True  
There is a subset (4, 5) with sum 9.

**Input:** set[] = {3, 34, 4, 12, 5, 2}, sum = 30
**Output:** False
There is no subset that add up to 30.

### Equal Sum Partition LeetCode 416
---
Given a **non-empty** array `nums` containing **only positive integers**, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
**Input:** nums = [1,5,11,5]
**Output:** true

**Input:** nums = [1,2,3,5]
**Output:** false 

### Count Subset Sum
---
Given an array **arr[]** of length **N** and an integer **X**, the task is to find the number of subsets with sum equal to **X**.
**Input:** arr[] = {1, 2, 3, 3}, X = 6  
**Output:** 3

**Input:** arr[] = {1, 1, 1, 1}, X = 1  
**Output:** 4
