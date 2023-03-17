package com.heiduo;

import java.util.*;

public class LeetCode2201 {

    public static void main(String[] args) {

        /***
         * 217. 存在重复元素
         */
//        System.out.println("data:" + containsDuplicate(new int[]{1, 2, 3, 4}));

        /**
         * 53. 最大子数组和
         */
//        System.out.println("data:" + maxSubArrayMine(new int[]{5,4,-1,7,8}));

        /**
         * 350. 两个数组的交集 II
         */
//        System.out.println("data:" + Arrays.toString(intersectMine(new int[]{1,2,2,1},new int[]{2,2})));

        /**
         * 121. 买卖股票的最佳时机
         */
//        System.out.println("data:" + maxProfitMine2(new int[]{7,1,5,3,6,4}));

        /**
         * 566. 重塑矩阵
         */
//        System.out.println("data:" + matrixReshapeMine(new int[][2]{[1,2],[3,4]}));


        /**
         * 118. 杨辉三角
         */
//        System.out.println("data:" + generateMine(2).toString());

        /**
         * 1614. 括号的最大嵌套深度
         */
//        System.out.println("data:" + maxDepthMine("1+(2*3)/(2-1)"));

        /**
         * 89. 格雷编码
         */
//        System.out.println("data:" + grayCodeMine(2).toString());

        /**
         * 1629. 按键持续时间最长的键
         */
//        System.out.println("data:" + slowestKeyMine(new int[]{28,65,97},"gaf"));

        /**
         * 387. 字符串中的第一个唯一字符
         */
//        System.out.println("data:" + firstUniqCharMine("loveleetcode"));

        /**
         * 383. 赎金信
         */
//        System.out.println("data:" + canConstructMine("aa","ab"));

        /**
         * 242. 有效的字母异位词
         */
//        System.out.println("data:" + isAnagramMine("rat", "nagaram"));


        /**
         * 306. 累加数
         */
//        System.out.println("data:" + isAdditiveNumber("121474836472147483648"));

        /**
         * 20. 有效的括号
         */
//        System.out.println("data:" + isValid20Mine("{[]}"));

        /**
         * 334. 递增的三元子序列
         */
//        System.out.println("data:" + increasingTripletMine(new int[]{1, 5, 0, 4, 1, 3}));

        /**
         * 747. 至少是其他数字两倍的最大数
         */
//        System.out.println("data:" + dominantIndexMine(new int[]{1, 2}));

        /**
         * 373. 查找和最小的K对数字
         */
//        System.out.println("data:" + kSmallestPairsMine(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 3));

        /**
         * 1716. 计算力扣银行的钱
         */
//        System.out.println("data:" + totalMoney1716Mine(8));

        /**
         * 539. 最小时间差
         */
//        System.out.println("data:" + findMinDifferenceMine(Arrays.asList("23:59","00:00")));

        /**
         * 219. 存在重复元素 II
         */
//        System.out.println("data:" + containsNearbyDuplicateMine(new int[]{
//                1,2,3,1,2,3
//        },2));

        /**
         * 2029. 石子游戏 IX
         */
        System.out.println("data:" + stoneGameIXMine(new int[]{1,1,7,10,8,17,10,20,2,10}));
    }



    /**
     * 2029. 石子游戏 IX
     * error
     * @param stones
     * @return
     */
    public static boolean stoneGameIX(int[] stones){
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }

    public static boolean stoneGameIXMine(int[] stones) {
        int total = 0;
        for (int i = 0; i < stones.length; i++) {
            total += stones[i];
        }
        int remain = 0;
        boolean[] select = new boolean[stones.length];
        int nums = 0;
        while (remain != total) {
            boolean hasSelect = false;
            for (int i = 0; i < stones.length; i++) {
                if (!select[i] && (remain + stones[i]) % 3 != 0) {
                    select[i] = true;
                    remain += stones[i];
                    hasSelect = true;
                    nums++;
                }
            }
            if (!hasSelect) {
                return nums % 2 == 1;
            }
        }

        if (nums == stones.length){
            return nums%2==1;
        }
        return false;
    }

    /**
     * 219. 存在重复元素 II
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsNearbyDuplicateMine(int[] nums, int k) {
        if (k == 0 || nums.length == 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null
                    && Math.abs(map.get(nums[i]) - i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * 539. 最小时间差
     *
     * @param timePoints
     * @return
     */
    public static int findMinDifferenceMine(List<String> timePoints) {
        Collections.sort(timePoints);
        int min = Integer.MAX_VALUE;
        int length = timePoints.size();
        for (int i = 0; i < length - 1; i++) {
            int first = Integer.parseInt(timePoints.get(i).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(i).substring(3));
            int second = Integer.parseInt(timePoints.get(i + 1).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(i + 1).substring(3));
            min = Math.min(min, second - first);
        }
        int first = Integer.parseInt(timePoints.get(0).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(0).substring(3));
        int second = Integer.parseInt(timePoints.get(length - 1).substring(0, 2)) * 60 + Integer.parseInt(timePoints.get(length - 1).substring(3));
        min = Math.min(min, Math.min(second - first, 24 * 60 - second + first));
        return min;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorMine(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode head = root;
        while (head != null) {
            if (head.val < p.val && head.val < q.val) {
                head = head.right;
            } else if (head.val > p.val && head.val > q.val) {
                head = head.left;
            } else {
                break;
            }
        }
        return head;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTargetMine(TreeNode root, int k) {
        if (root == null) return false;
        Map<Integer, Integer> map = new HashMap<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            if (map.get(node.val) != null) return true;
            map.put(k - node.val, node.val);
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
        return false;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBSTMine(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }


    /**
     * 701. 二叉搜索树中的插入操作
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode head = root;
        while (head != null) {
            if (head.val < val) {
                if (head.right == null) {
                    head.right = new TreeNode(val);
                    break;
                } else {
                    head = head.right;
                }
            } else {
                if (head.left == null) {
                    head.left = new TreeNode(val);
                    break;
                } else {
                    head = head.left;
                }
            }
        }

        return root;
    }

    public TreeNode insertIntoBSTMine(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        TreeNode head = root;
        TreeNode pre = head;
        boolean isLeft = false;
        while (head != null) {
            pre = head;
            if (head.val < val) {
                isLeft = false;
                head = head.right;
            } else {
                isLeft = true;
                head = head.left;
            }
        }
        if (isLeft) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }

        return root;
    }

    /**
     * 700. 二叉搜索树中的搜索
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBSTMine(TreeNode root, int val) {
        TreeNode head = root;
        while (head != null) {
            if (head.val == val) return head;
            else if (head.val < val) {
                head = head.right;
            } else {
                head = head.left;
            }
        }
        return null;
    }

    /**
     * 112. 路径总和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSumMine(TreeNode root, int targetSum) {
        if (root == null) return false;

        return hasPathSumMineOther(root, 0, targetSum);

    }

    public static boolean hasPathSumMineOther(TreeNode node, int sum, int targetSum) {
        if (node.left == null && node.right == null) {
            return sum + node.val == targetSum;
        } else {
            if (node.left != null && node.right != null) {
                return hasPathSumMineOther(node.left, sum + node.val, targetSum)
                        || hasPathSumMineOther(node.right, sum + node.val, targetSum);
            } else if (node.right == null) {
                return hasPathSumMineOther(node.left, sum + node.val, targetSum);
            } else {
                return hasPathSumMineOther(node.right, sum + node.val, targetSum);
            }
        }
    }

    /**
     * 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode invertTreeMine(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
        return root;
    }

    /**
     * 1716. 计算力扣银行的钱
     *
     * @param n
     * @return
     */
    public static int totalMoney1716Mine(int n) {
        int total = 0;

        for (int i = 1; i <= n; i++) {
            total += ((i - 1) / 7 + (i - 1) % 7 + 1);
        }

        return total;
    }

    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetricMine(TreeNode root) {
        if (root == null) {
            return false;
        }

        return isSymmetricOther(root.left, root.right);
    }

    public boolean isSymmetricOther(TreeNode first, TreeNode second) {
        if (first != null && second != null) {
            if (first.val == second.val) {
                return isSymmetricOther(first.left, second.right) && isSymmetricOther(first.right, second.left);
            } else {
                return false;
            }
        } else if (first == null && second == null) {
            return true;
        }
        return false;
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static int maxDepthMine(TreeNode root) {
        int max = 0;
        if (root != null) {
            Deque<TreeNode> deque = new LinkedList<>();
            deque.add(root);
            int size = deque.size();
            while (!deque.isEmpty()) {
                TreeNode node = deque.poll();
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
                size--;
                if (size == 0) {
                    max++;
                    size = deque.size();
                }

            }
        }

        return max;
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderMine(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        deque.add(root);
        int length = deque.size();
        List<Integer> list = new ArrayList<>();
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            list.add(node.val);
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
            length--;
            if (length == 0) {
                result.add(list);
                list = new ArrayList<>();
                length = deque.size();
            }

        }

        return result;
    }

    /**
     * 373. 查找和最小的K对数字
     * error
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static List<List<Integer>> kSmallestPairsMine(int[] nums1, int[] nums2, int k) {
        int pos1 = 0, pos2 = 0;
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length * nums2.length <= k) {
            for (int i1 : nums1) {
                for (int i : nums2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i1);
                    list.add(i);
                    result.add(list);
                }
            }
            return result;
        }
        while (result.size() < k) {
            List<Integer> list = new ArrayList<>();
            list.add(nums1[pos1]);
            list.add(nums2[pos2]);
            if (pos1 + 1 < nums1.length && pos2 + 1 < nums2.length) {
                int sum1 = nums1[pos1 + 1] + nums2[pos2];
                int sum2 = nums1[pos1] + nums2[pos2 + 1];
                if (sum1 <= sum2) {
                    pos1++;
                    pos2 = 0;
                } else {
                    pos2++;
                    pos1 = 0;
                }
            } else if (pos1 + 1 == nums1.length) {
                pos2++;
                pos1 = 0;
            } else {
                pos1++;
                pos2 = 0;
            }

            result.add(list);
        }

        return result;
    }

    /**
     * 747. 至少是其他数字两倍的最大数
     *
     * @param nums
     * @return
     */
    public static int dominantIndexMine(int[] nums) {
        if (nums.length == 1) return 0;
        int max = Integer.MIN_VALUE;
        int pos = -1;
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                pos = i;
                max = nums[i];
            }

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max && nums[i] > max2) {
                max2 = nums[i];
            }

        }
        if (max2 == 0 || max / max2 >= 2) {
            return pos;
        }
        return -1;
    }

    /**
     * 145. 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversalMine2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == prev) {
                    list.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
        }

        return list;
    }


    public static List<Integer> postorderTraversalMine(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversalOteher(root, list);
        return list;
    }

    public static void postorderTraversalOteher(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorderTraversalOteher(root.left, list);
        postorderTraversalOteher(root.right, list);
        list.add(root.val);
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalMine2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            TreeNode node = root;
            stack.add(node);
            while (node.left != null) {
                stack.add(node.left);
                node = node.left;
            }
            while (!stack.isEmpty()) {
                node = stack.pop();
                list.add(node.val);
                if (node.right != null) {
                    stack.add(node.right);
                    node = node.right;
                    while (node.left != null) {
                        stack.add(node.left);
                        node = node.left;
                    }
                }

            }
        }

        return list;
    }


    public static List<Integer> inorderTraversalMine(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversalOteher(root, list);
        return list;
    }

    public static void inorderTraversalOteher(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversalOteher(root.left, list);
        list.add(root.val);
        inorderTraversalOteher(root.right, list);
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversalMine2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.add(root);
            while (!deque.isEmpty()) {
                TreeNode node = deque.pollLast();
                list.add(node.val);
                if (node.right != null) {
                    deque.add(node.right);
                }
                if (node.left != null) {
                    deque.add(node.left);
                }
            }
        }

        return list;
    }

    public static List<Integer> preorderTraversalMine(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            list.add(root.val);
            preorderTraversalOteher(root.left, list);
            preorderTraversalOteher(root.right, list);
        }

        return list;
    }

    public static void preorderTraversalOteher(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderTraversalOteher(root.left, list);
        preorderTraversalOteher(root.right, list);
    }

    /**
     * 334. 递增的三元子序列
     *
     * @param nums
     * @return
     */

    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num > second) {
                return true;
            } else if (num > first) {
                second = num;
            } else {
                first = num;
            }
        }
        return false;
    }


    public static boolean increasingTripletMine(int[] nums) {
        if (nums.length < 3) return false;
        Deque<Integer> deque = new LinkedList<>();
        int first = 0;
        int second = 0;
        boolean hasSecond = false;
        for (int i = 0; i < nums.length; i++) {
            if (deque.isEmpty()) {
                deque.push(nums[i]);
                first = nums[i];
            } else if (deque.size() == 1) {
                if (deque.peek() > nums[i]) {
                    deque.pop();
                    first = nums[i];
                    deque.push(nums[i]);
                } else if (deque.peek() < nums[i]) {
                    if (hasSecond && second < nums[i]) return true;
                    deque.push(nums[i]);
                }
            } else if (deque.size() == 2) {
                if (deque.peek() > nums[i]) {
                    if (first > nums[i]) {
                        hasSecond = true;
                        second = deque.pop();
                        first = nums[i];
                        deque.push(nums[i]);
                    } else if (first < nums[i]) {
                        deque.pop();
                        deque.push(nums[i]);
                    }
                } else if (deque.peek() < nums[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 20. 有效的括号
     *
     * @param s
     * @return
     */
    public static boolean isValid20Mine(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stringStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stringStack.push(c);
            } else if (c == '}') {
                if (stringStack.empty() || stringStack.peek() != '{') {
                    return false;
                } else {
                    stringStack.pop();
                }
            } else if (c == ']') {
                if (stringStack.empty() || stringStack.peek() != '[') {
                    return false;
                } else {
                    stringStack.pop();
                }
            } else if (c == ')') {
                if (stringStack.empty() || stringStack.peek() != '(') {
                    return false;
                } else {
                    stringStack.pop();
                }
            }
        }

        return stringStack.empty();

    }

    /**
     * 206. 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseListMine(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    public static ListNode reverseMine(ListNode head, ListNode pre) {
        if (head.next == null) {
            return head;
        }
        reverseMine(head.next, pre.next).next = pre;
        return pre;
    }


    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode list = new ListNode(0);
        list.next = head;
        ListNode pre = list;
        ListNode next = list.next;

        while (next != null) {
            if (next.val == val) {
                pre.next = next.next;
            } else {
                pre = pre.next;
            }
            next = next.next;
        }
        return list.next;
    }

    /**
     * 21. 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode list = new ListNode(0);
        ListNode pre = list;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                pre.next = list2;
                list2 = list2.next;
            } else {
                pre.next = list1;
                list1 = list1.next;
            }
            pre = pre.next;
        }
        if (list1 != null) {
            pre.next = list1;
        }

        if (list2 != null) {
            pre.next = list2;
        }

        return list.next;
    }

    /**
     * 141. 环形链表
     *
     * @param head
     * @return
     */
    public static boolean hasCycleMine2(ListNode head) {
        if (head == null) return false;
        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode bef = pre;
        while (bef.next != null && bef != null) {
            bef = bef.next.next;
            pre = pre.next;
            if (bef == pre) return true;
        }
        return false;
    }

    public static boolean hasCycleMine(ListNode head) {
        if (head == null) return false;
        Set<ListNode> set = new HashSet<>();
        ListNode pre = head;
        while (pre.next != null) {
            if (set.contains(pre)) return true;
            set.add(pre);
            pre = pre.next;
        }
        return false;
    }

    /**
     * 306. 累加数
     *
     * @param num
     * @return
     */

    public static boolean isAdditiveNumber(String num) {
        return dfs(num, 0, 0, 0, 0);
    }

    private static boolean dfs(String num, int index, int count, long prevprev, long prev) {
        if (index >= num.length()) {
            return count > 2;
        }

        long current = 0;
        for (int i = index; i < num.length(); i++) {
            char c = num.charAt(i);

            if (num.charAt(index) == '0' && i > index) {
                // 剪枝1：不能做为前导0，但是它自己是可以单独做为0来使用的
                return false;
            }

            current = current * 10 + c - '0';

            if (count >= 2) {
                long sum = prevprev + prev;
                if (current > sum) {
                    // 剪枝2：如果当前数比之前两数的和大了，说明不合适
                    return false;
                }
                if (current < sum) {
                    // 剪枝3：如果当前数比之前两数的和小了，说明还不够，可以继续添加新的字符进来
                    continue;
                }
            }

            // 当前满足条件了，或者还不到两个数，向下一层探索
            if (dfs(num, i + 1, count + 1, prev, current)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAdditiveNumberMine(String num) {
        int length = num.length();
        if (length < 3) return false;
        for (int i = 0; i <= length - i; i++) {
            String first = num.substring(0, i + 1);
            if (first.length() > 1 && first.charAt(0) == '0') {
                continue;
            }
            for (int j = i + 1; j <= length - j + i; j++) {
                String second = num.substring(i + 1, j + 1);
                if (second.length() > 1 && second.charAt(0) == '0') {
                    continue;
                }
                String next = addNum(first, second);

                if (next.length() > (length - j - 1)) continue;
                int pos = j + 1;
                boolean is = false;
                String first2 = first;
                String second2 = second;
                while (pos < length) {
                    if (!next.equals(num.substring(pos, Math.min(pos += next.length(), length)))) {
                        is = false;
                        break;
                    }
                    first2 = second2;
                    second2 = next;
                    next = addNum(first2, second2);
                    is = true;
                }
                if (is && pos == length) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String addNum(String first, String second) {
        String next = "";
        if (first.length() < 10 && second.length() < 10) {
            next = String.valueOf(Integer.parseInt(first) + Integer.parseInt(second));
        } else {
            int[] data = new int[Math.max(first.length(), second.length()) + 1];
            boolean carry = false;
            int i = 0;
            for (i = 0; i < Math.min(first.length(), second.length()); i++) {
                int result = first.charAt(i) + second.charAt(i) - 2 * '0';
                if (carry) {
                    result += 1;
                }
                if (result < 10) {
                    data[i] = result;
                    carry = false;
                } else {
                    data[i] = result - 10;
                    carry = true;
                }
            }

            for (; i < first.length(); i++) {
                int result = first.charAt(i) - '0';
                if (carry) {
                    result += 1;
                }
                if (result < 10) {
                    data[i] = result;
                    carry = false;
                } else {
                    data[i] = result - 10;
                    carry = true;
                }
            }

            for (; i < second.length(); i++) {
                int result = second.charAt(i) - '0';
                if (carry) {
                    result += 1;
                }
                if (result < 10) {
                    data[i] = result;
                    carry = false;
                } else {
                    data[i] = result - 10;
                    carry = true;
                }
            }
            if (carry) {
                data[i] = 1;
            }

            for (int j = 0; j < data.length; j++) {
                next += data[i];
            }
        }

        return next;
    }


    /**
     * 242. 有效的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagramMine(String s, String t) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            result[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 383. 赎金信
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public static boolean canConstructMine(String ransomNote, String magazine) {
        int[] result = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            result[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            result[ransomNote.charAt(i) - 'a']--;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 387. 字符串中的第一个唯一字符
     *
     * @param s
     * @return
     */
    public static int firstUniqCharMine(String s) {
        int[] result = new int[26];
        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (result[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;

    }

    /**
     * 1629. 按键持续时间最长的键
     *
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public static char slowestKeyMine(int[] releaseTimes, String keysPressed) {
        char result = keysPressed.charAt(0);
        int max = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] - releaseTimes[i - 1] > max) {
                result = keysPressed.charAt(i);
                max = releaseTimes[i] - releaseTimes[i - 1];
            } else if (releaseTimes[i] - releaseTimes[i - 1] == max) {
                result = (char) Math.max(result, keysPressed.charAt(i));
            }
        }

        return result;
    }

    /**
     * 89. 格雷编码
     *
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            list.add((i >> 1) ^ i);
        }
        return list;
    }


    public static List<Integer> grayCodeMine(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        for (int i = 1; i < n; i++) {
            int length = list.size();
            int first = 1 << i;
            System.out.println("first:" + first);
            System.out.println("length:" + length);
            for (int j = 0; j < length; j++) {
                list.add(list.get(length - j - 1) | first);
            }
        }

        return list;
    }

    /**
     * 73. 矩阵置零
     *
     * @param matrix
     */
    public static void setZeroesMine(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        boolean firstColumn = false;
        boolean firstRow = false;
        int rows = matrix.length;
        int column = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColumn = true;
                break;
            }
        }
        for (int i = 0; i < column; i++) {
            if (matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < column; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 1; i < column; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < rows; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        if (firstColumn) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRow) {
            for (int i = 0; i < column; i++) {
                matrix[0][i] = 0;
            }
        }
    }


    /**
     * 1614. 括号的最大嵌套深度
     *
     * @param s
     * @return
     */
    public static int maxDepth(String s) {
        int max = 0;
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                size++;
                max = Math.max(max, size);
            } else if (s.charAt(i) == ')') {
                size--;
            }
        }
        return max;

    }

    public static int maxDepthMine(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
                max = Math.max(max, stack.size());
            } else if (s.charAt(i) == ')') {
                stack.pop();
            }
        }
        return max;

    }

    /**
     * 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generateMine(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> listSub = new ArrayList<>();
        listSub.add(1);
        list.add(listSub);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> listP = list.get(i - 2);
            List<Integer> listNext = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0) {
                    listNext.add(1);
                } else if (j == i - 1) {
                    listNext.add(1);
                } else {
                    listNext.add(listP.get(j - 1) + listP.get(j));
                }
            }

            list.add(listNext);
        }
        return list;
    }

    /**
     * 566. 重塑矩阵
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public static int[][] matrixReshapeMine(int[][] mat, int r, int c) {
        if (mat.length * mat[0].length != r * c) {
            return mat;
        }
        int[][] result = new int[r][c];
        for (int i = 0; i < mat.length * mat[0].length; i++) {
            result[i / c][i % c] = mat[i / mat[0].length][i % mat[0].length];
        }
        return result;
    }

    /**
     * 121. 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public static int maxProfitMine2(int[] prices) {
        int[] result = new int[prices.length];
        int pos = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[pos] > 0) {
                result[i] = prices[i] - prices[pos];
            } else {
                pos = i;
            }
        }
        pos = 0;
        for (int i = 0; i < result.length; i++) {
            pos = Math.max(pos, result[i]);
        }
        return pos;
    }

    //超时
    public static int maxProfitMine(int[] prices) {
//        int[][] result = new int[prices.length][prices.length];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(Math.max(0, prices[j] - prices[i]), max);
            }
        }
        return max;
    }

    /**
     * 350. 两个数组的交集 II
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectMine(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
//        int length = Math.min(nums1.length,nums2.length);
        List<Integer> list = new ArrayList<>();
        int a = 0, b = 0;
        while (a < nums1.length && b < nums2.length) {
            if (nums1[a] == nums2[b]) {
                list.add(nums1[a]);
                a++;
                b++;
            } else if (nums1[a] > nums2[b]) {
                b++;
            } else {
                a++;
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     *
     * @param nums
     * @return
     */
    public static int maxSubArrayMine(int[] nums) {
        int result = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (result + nums[i] < 0) {
                result = 0;
                max = Math.max(max, nums[i]);
            } else {
                result += nums[i];
                max = Math.max(max, result);
            }
        }
        return max;
    }

    /**
     * 217. 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate2(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> res = new HashSet<Integer>();
        for (int i : nums)
            res.add(i);
        return res.size() < nums.length;
    }

    public static boolean containsDuplicateMine(int[] nums) {
        Arrays.parallelSort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
