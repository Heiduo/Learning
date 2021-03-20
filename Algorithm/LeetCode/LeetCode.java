/*
 * @Description: 
 * @Version: 2.0
 * @Autor: Heiduo
 * @Date: 2021-03-01 15:28:37
 * @LastEditTime: 2021-03-20 09:51:48
 * @Contact: heiduox@163.com
 */
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Arrays;
public class LeetCode{
    private static Stack<Integer> s1,s2;

    public LeetCode(){
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    public static void main(String[] args){
        /**
         * 10.正则匹配
         */
        // System.out.println(isMatch2("aa","a*")); 
        
        /**
         * 338. 比特位计数
         */
        // System.out.println(Arrays.toString((countBits2(32))));
        
        /**
         * 354. 俄罗斯套娃信封问题 
         */
        // int [][] envelopes = {
        //     {5,4},{6,4},{6,7},{2,3}
        // };
        // System.out.println(maxEnvelopes(envelopes));
        
        /**
        * 232. 用栈实现队列
        */
        // LeetCode le = new LeetCode();
        // le.push(1);
        // le.push(2);
        // System.out.println(le.peek() + ", " + le.pop() + ", " + le.empty());

        /**
        * 503. 下一个更大元素 II                 
        */
        // int[] data = {
        //     1,2,1
        // };
        // System.out.println(Arrays.toString(nextGreaterElements(data)));

        /**
        * 132. 分割回文串 II
        */
        // System.out.println("cut:" + minCutMine("didhihd"));
        // System.out.println("cut:" + minCut("aab"));


        /**
         * 1047. 删除字符串中的所有相邻重复项
         */
        // System.out.println("result: " + removeDuplicates("abbaca"));

        /**
         * 485. 最大连续 1 的个数
         */
        // System.out.println("result: " + findMaxConsecutiveOnesMine(new int[]{
        //     1,1,0,1,1,1,1,1,1,1,1
        // }));

        /**
         * 1725. 可以形成最大正方形的矩形数目
         */
        // System.out.println("result: " + countGoodRectanglesMine(new int[][]{
        //     {2,3},{3,7},{4,3},{3,7}
        // }));

        /**
        * 224. 基本计算器
        */
        // System.out.println("result: " + calculateMine2("2147483647"));

        /**
         * 227. 基本计算器 II
         */
        // System.out.println("result: " + calculateMine22("1+2*5/3+6/4*2"));

        /**
         * 331. 验证二叉树的前序序列化
         */
        // System.out.println("result: " + isValidSerializationMine("9,#,#,1,#,#"));
        // List<Integer> list = new ArrayList<>();
        
         /**
         * 54. 螺旋矩阵
         */
        // int[][] data = new int[][]{
        //     {1,2,3,4},
        //     {5,6,7,8},
        //     {9,10,11,12}

        //     // {1,2}

        //     // {1},
        //     // {2},
        //     // {3}
        // };
        // System.out.println("result: " + spiralOrder(data).toString() );

        /**
         * 59. 螺旋矩阵 II
         */
        // System.out.println("result: " + Arrays.deepToString(generateMatrix(3)));

        /**
         * 13. 罗马数字转整数
         */
        // System.out.println("result: " + romanToIntMine("IX"));

        /**
         * 115. 不同的子序列
         */
        // System.out.println("result: " + numDistinctMine("babgbag","bag"));

        /**
         * 92. 反转链表 II
         */
        // int length = 2;
        // List<Integer> data = new ArrayList<>();
        // ListNode lst = new ListNode(length+1);
        // for (int i = length; i > 0; i--) {
        //     ListNode lst2 = new ListNode(i);
        //     lst2.next = lst;
        //     lst = lst2;
        // }
        // ListNode result = reverseBetweenMine(lst,1,2);
        // while(result!=null){
        //     data.add(result.val);
        //     result = result.next;
        // }
        // System.out.println("result: " + data.toString());

        /**
         * 74. 搜索二维矩阵
         */
        // int[][] test = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        // System.out.println("result: " + searchMatrix(test,3));

        /**
         * 16. 最接近的三数之和
         */
        // int[] test = {-100,-98,-2,-1};
        // System.out.println("result: " + threeSumClosestMine(test,-101));

        /**
         * 150. 逆波兰表达式求值
         */
        String[] test = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println("result: " + evalRPNMine(test));
    }

    /**
     * 150. 逆波兰表达式求值
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = -1;
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")){
                top--;
                stack[top] = stack[top] + stack[top+1];
            }else if(tokens[i].equals("-")){
                top--;
                stack[top] = stack[top] - stack[top+1];
            }else if(tokens[i].equals("*")){
                top--;
                stack[top] = stack[top] * stack[top+1];
            }else if(tokens[i].equals("/")){
                top--;
                stack[top] = stack[top] / stack[top+1];
            }else{
                top ++;
                stack[top] = Integer.valueOf(tokens[i]);
            }
        }
        return stack[top];
    }

    public static int evalRPNMine(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("+")){
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n+m);
            }else if(tokens[i].equals("-")){
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n-m);
            }else if(tokens[i].equals("*")){
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n*m);
            }else if(tokens[i].equals("/")){
                int m = stack.pop();
                int n = stack.pop();
                stack.push(n/m);
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 16. 最接近的三数之和
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosestMine(int[] nums, int target) {
        int resultMin= -1000000,resultMax = 1000000;
        int length = nums.length;
        if(length < 3){
            return 0;
        }
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;
            
            int target2 = target-nums[i];
            int left = i + 1;
            int right = length -1;
            while(left<right){
                if(nums[left] + nums[right] == target2){
                    return target;
                }else if(nums[left] + nums[right] < target2){
                    resultMin = Math.max(nums[i] + nums[left] + nums[right],resultMin);
                    left++;
                }else{
                    resultMax = Math.min(nums[i] + nums[left] + nums[right],resultMax);
                    right--;
                }
            } 
            
        }

        return (target - resultMin)<(resultMax-target)?resultMin:resultMax;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSumMine(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if(length<3){
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            if(nums[i]>0) break;
            if(i>0 && nums[i] == nums[i-1]) continue;

            int target = -nums[i];
            int left = i + 1;
            int right = length -1;
            while(left<right){
                if(nums[left] + nums[right] == target){
                    result.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    left ++;
                    right --;
                    while(left<right && nums[left] == nums[left-1]) left++;
                    while(left<right && nums[right] == nums[right+1]) right--;
                }else if(nums[left] + nums[right]<target){
                    left++;
                }else{
                    right --;
                }
            }   
        }
        return result;
    }

    /**
     * 74. 搜索二维矩阵
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int colmuns = matrix[0].length;
        int left = 0,right = rows*colmuns-1;
        while(left < right){
            int mid = (right+left)/2;
            int row = mid/colmuns;
            int column = mid%colmuns;
            if(matrix[row][column] == target){
                return true;
            }else if(matrix[row][column] < target){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        
        return false;
    }

    public boolean search74(int[][]matrix,int left,int right,int target){
        
        return false;
    }

    /**
     * 92. 反转链表 II
     * 头插法 或 用栈
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode result = new ListNode(-1,head);
        ListNode pre = result;
        for (int i = 0; i < left-1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return result.next;
    }

    public static ListNode reverseBetweenMine(ListNode head, int left, int right) {
        if(left == right){
            return head;
        }

        int position = 1;
        ListNode result2 = new ListNode(-1,head);
        ListNode result = result2;
        ListNode result_rever = null;
        
        while(head!=null){
            if(position<left-1){
                result2 = result2.next;
            }else if(position >right){
                result2.next = result_rever;
                while(result_rever.next!=null){
                    result_rever = result_rever.next;
                }
                result_rever.next = head;
                return result.next;
            }else if(position>=left && position <=right){
                ListNode result_temp = new ListNode(head.val);
                result_temp.next = result_rever;
                result_rever = result_temp;
            }
            position++;
            head = head.next;
            
        }
        result2.next = result_rever;
        return result.next;
    }

    /**
     * 115. 不同的子序列
     */
    public static int numDistinctMine(String s, String t) {
        char[] tArray = t.toCharArray();
        char[] sArray = s.toCharArray();
        int n = tArray.length;
        int m = sArray.length;
        if(n==0 || m==0){
            return 0;
        }
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < m; j++) {
                if(tArray[i] == sArray[j]){
                    if(j == 0){
                        dp[i][j] = 1;
                    }else if(i == 0){
                        dp[i][j] = dp[i][j-1] + 1;
                    }else{
                        dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                    }
                }else{
                    if(j!=0){
                        dp[i][j] = dp[i][j-1];                        
                    }
                }
            }
        }

        return dp[n-1][m-1];
    }

    /**
     * 14. 最长公共前缀
     * @param strs
     * @return
     */
    public static String longestCommonPrefixMine(String[] strs) {
        String commonHead = "";
        if(strs.length>0){
            char[] data = strs[0].toCharArray();
            for (int i = 0; i < data.length; i++) {
                for (String string : strs) {
                    if(string.length() <= i || string.charAt(i)!=data[i]){
                        return commonHead;
                    }
                }
                commonHead+=data[i];
            }
        }
        return commonHead;
    }

    /**
     * 13. 罗马数字转整数
     */
    public static int romanToIntMine(String s) {
        int[] intArray = new int[]{1000,500,100,50,10,5,1};
        char[] romanArray = new char[]{'M','D','C','L','X','V','I'};
        char[] data = s.toCharArray();
        int result = 0;
        int last = -1;
        for (int i = 0; i < data.length; i++) {
            int position = -1;
            for (int j = 0; j < romanArray.length; j++) {
                if(data[i] == romanArray[j]){
                    position = j;
                    break;
                }
            }
            if(last!=-1 && position<last){
                result -= intArray[last]*2;
            }
            result += intArray[position];
            last = position;
        }
        return result;
    }

    /**
     * 59. 螺旋矩阵 II
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int count = 1;
        if(n>0){
            int rows = n, columns = n;
            int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
            while(left <= right && top <= bottom){
                for (int i = left; i <= right; i++) {
                    result[top][i] = count++;
                }
                for (int i = top + 1; i <= bottom; i++) {
                    result[i][right] = count++;
                }
                if(left<right && top<bottom){
                    for (int i = right - 1; i > left; i--) {
                        result[bottom][i] = count++;
                    }
                    for (int i = bottom ; i > top; i--) {
                        result[i][left] = count++;
                    }
                }
                top++;
                left++;
                bottom--;
                right--;
            }
        }
        
        return result;
    }

    public static int[][] generateMatrixMine(int n) {
        int[][] result = new int[n][n];
        int count = 1;
        if(n>0){
            int num = n;
            int position = num>2?num-2:0;
            for (int i = 0; i <= position; i++) {
                count = setList2(i, result,count);
            }
        }
        
        return result;
    }

    public static int setList2(int position,int[][] matrix,int count){
        int row = matrix[0].length;
        int line = matrix.length;
        for (int i = position; i < row - position; i++) {
            matrix[position][i] = count++;
        }

        for (int i = position + 1; i < line - position; i++) {
            matrix[i][row - position - 1] = count++;
        }

        if((line - position - 1)>position){
            for (int i = row - position - 2; i >= position; i--) {
                matrix[line-position-1][i] = count++;
            }
        }
        
        if(position<row-position-1){
            for (int i = line - position - 2; i >= position + 1; i--) {
                matrix[i][position] = count++;
            }
        }

        return count;
        
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix!=null && matrix.length>0){
            int rows = matrix.length, columns = matrix[0].length;
            int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
            while(left <= right && top <= bottom){
                for (int i = left; i <= right; i++) {
                    list.add(matrix[top][i]);
                }
                for (int i = top + 1; i <= bottom; i++) {
                    list.add(matrix[i][right]);
                }
                if(left<right && top<bottom){
                    for (int i = right - 1; i > left; i--) {
                        list.add(matrix[bottom][i]);
                    }
                    for (int i = bottom ; i > top; i--) {
                        list.add(matrix[i][left]);
                    }
                }
                top++;
                left++;
                bottom--;
                right--;
            }
        }
        
        return list;
    }

    public static List<Integer> spiralOrderMine(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix!=null && matrix.length>0){
            int num = Math.min(matrix[0].length, matrix.length);
            int position = num>2?num-2:0;
            for (int i = 0; i <= position; i++) {
                setList(i, matrix, list);
            }
        }
        
        return list;
    }

    public static void setList(int position,int[][] matrix, List<Integer> list){
        int row = matrix[0].length;
        int line = matrix.length;
        for (int i = position; i < row - position; i++) {
            list.add(matrix[position][i]);
        }

        for (int i = position + 1; i < line - position; i++) {
            list.add(matrix[i][row - position - 1]);
        }

        if((line - position - 1)>position){
            for (int i = row - position - 2; i >= position; i--) {
                list.add(matrix[line-position-1][i]);
            }
        }
        
        if(position<row-position-1){
            for (int i = line - position - 2; i >= position + 1; i--) {
                list.add(matrix[i][position]);
            }
        }
        
    }

    /**
     * 331. 验证二叉树的前序序列化
     * @param preorder
     * @return
     */
    public static boolean isValidSerializationMine(String preorder) {
        String[] divide = preorder.split(",");
        int length = divide.length;
        if(length%2!=1){
            return false;
        }
        int[] treeCount = new int[length];
        int[] topPath = new int[length];
        Arrays.fill(topPath,-1);
        int top = -1;
        for (int i = 0; i < length; i++) {
            // if(i == 0){
            //     top = 0;
            //     // continue;
            // }

            if(i != 0 && top == -1){
                return false;
            }

            if(divide[i].equals("#")){
                treeCount[i] = 2;
                topPath[i] = top;
                if(i != 0){
                    treeCount[top] ++;
                    if(treeCount[top] == 2){
                        top = topPath[top];
                    }
                }
                
            }else{
                if(i!=0){
                    if((++treeCount[i])==2){
                        top = topPath[top];
                    }
                }
                
                topPath[i] = top;
                top = i;
            }
            
        }
        if(top!=-1){
            return false;
        }
        for (int i = 0; i < length; i++) {
            if(treeCount[i]!=2){
                return false;
            }
        }
        
        return true;
    }

    /**
     * 227. 基本计算器 II
     * @param s
     * @return
     */
    public static int calculateMine22(String s){
        char[] data = s.toCharArray();
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        int temp = 0;
        int isLastNum = 0;
        for (int i = data.length-1;i >= 0 ; i--) {
            if(data[i] == '('){
                char d =' ';
                while(!charStack.isEmpty() && (d = charStack.pop())!=')'){
                    if(d == '+'){
                        temp = temp + intStack.pop();
                    }else if(d == '-'){
                        temp = temp - intStack.pop();
                    }else if(d == '*'){
                        temp = temp*intStack.pop();
                    }else if(d=='/'){
                        temp = temp/intStack.pop();
                    }
                }
                isLastNum = 0;
            }else if(data[i] == ')'){
                charStack.add(data[i]);
                temp = 0;
                isLastNum = 0;
            }else if(data[i] == ' '){
                continue;
            }else if(data[i] == '+' || data[i] == '-' || 
                        data[i] == '*' || data[i] == '/'){
                while(!charStack.isEmpty() && (data[i] == '+' || data[i] == '-') 
                && (charStack.peek() == '*' || charStack.peek()=='/')){
                    if(charStack.peek() == '*'){
                        temp = temp*intStack.pop();
                        charStack.pop();
                    }else if(charStack.peek()=='/'){
                        temp = temp/intStack.pop();
                        charStack.pop();
                    }
                }
                
                intStack.add(temp);
                charStack.add(data[i]);
                temp = 0;
                isLastNum = 0;
            }else{
                if(isLastNum>0){
                    temp = ((int)Math.pow(10,isLastNum))*(data[i] - 48) + temp;
                }else{
                    temp = data[i] - 48;
                }
                isLastNum ++;
            }
        }
        while(!charStack.isEmpty()){
            char d = charStack.pop();
            if(d == '+'){
                temp = temp + intStack.pop();
            }else if(d == '-'){
                temp = temp - intStack.pop();
            }else if(d == '*'){
                temp = temp*intStack.pop();
            }else if(d=='/'){
                temp = temp/intStack.pop();
            }
        }
        return temp;
    }


    /**
     * 224. 基本计算器
     */
    public static int calculateMine2(String s) {
        char[] data = s.toCharArray();
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        int temp = 0;
        int isLastNum = 0;
        for (int i = data.length-1;i >= 0 ; i--) {
            if(data[i] == '('){
                char d =' ';
                while(!charStack.isEmpty() && (d = charStack.pop())!=')'){
                    if(d == '+'){
                        temp = temp + intStack.pop();
                    }else if(d == '-'){
                        temp = temp - intStack.pop();
                    }
                }
                isLastNum = 0;
            }else if(data[i] == ')'){
                charStack.add(data[i]);
                temp = 0;
                isLastNum = 0;
            }else if(data[i] == ' '){
                continue;
            }else if(data[i] == '+' || data[i] == '-'){
                intStack.add(temp);
                charStack.add(data[i]);
                temp = 0;
                isLastNum = 0;
            }else{
                if(isLastNum>0){
                    temp = ((int)Math.pow(10,isLastNum))*(data[i] - 48) + temp;
                }else{
                    temp = data[i] - 48;
                }
                isLastNum ++;
            }
        }
        while(!charStack.isEmpty()){
            char d = charStack.pop();
            if(d == '+'){
                temp = temp + intStack.pop();
            }else if(d == '-'){
                temp = temp - intStack.pop();
            }
        }
        return temp;
    }

    public static int calculateMine1(String s) {
        char[] data = s.toCharArray();
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        int temp = 0;
        for (int i = 0; i < data.length; i++) {
            if(data[i] == '('){
                charStack.add(data[i]);
                temp = 0;
            }else if(data[i] == ')'){
                char d =' ';
                while(!charStack.isEmpty() && (d = charStack.pop())!='('){
                    if(d == '+'){
                        temp = temp + intStack.pop();
                    }else if(d == '-'){
                        temp = -1*temp + intStack.pop();
                    }
                }
            }else if(data[i] == ' '){
                continue;
            }else if(data[i] == '+' || data[i] == '-'){
                intStack.add(temp);
                charStack.add(data[i]);
                temp = 0;
            }else{
                temp = temp * 10 + data[i] - 48;
            }
        }
        while(!charStack.isEmpty() ){
            char d = charStack.pop();
            if(d == '+'){
                temp = temp + intStack.pop();
            }else if(d == '-'){
                temp = -1*temp + intStack.pop();
            }
        }

        return temp;
    }

    /**
     * 1725. 可以形成最大正方形的矩形数目
     * @param rectangles
     * @return
     */
    public static int countGoodRectanglesMine(int[][] rectangles) {
        int num = 0,maxLength = 0;
        for (int i = 0; i < rectangles.length; i++) {
            int maxLen = Math.min(rectangles[i][0],rectangles[i][1]);
            if(maxLength<maxLen){
                num = 1;
                maxLength = maxLen;
            }else if(maxLength == maxLen){
                num++;
            }
        }
        return num;
    }

    /**
     * 485. 最大连续 1 的个数
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnesMine(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
                count ++;
                if(max<count){
                    max = count;
                }
            }else{
                count = 0;
            }
        }
        return max;
    }


    /**
     * 1047. 删除字符串中的所有相邻重复项
     * @param S
     * @return
     */
    public static String removeDuplicates(String S) {
        char[] data = S.toCharArray();
        int top = -1;
        for (int i = 0; i < data.length; i++) {
            if(top == -1 || data[top]!=data[i]){
                data[++top] = data[i];
            }else{
                top--;
            }
        }
        return String.valueOf(data,0,top+1);
    }

    public static String removeDuplicatesMine(String S) {
        Stack<String> stack = new Stack<>();
        char[] data = S.toCharArray();
        for(int i = 0, length = data.length;i<length;i++){
            if(stack.isEmpty() || !stack.peek().equals(String.valueOf(data[i]))){
                stack.push(String.valueOf(data[i]));
            }else{
                stack.pop();
            }
        }
        String[] list =new String[stack.size()];
        list = stack.toArray(list);
        String result="";
        for (int i = 0,length = list.length; i < length; i++) {
            result += list[i];
        }
        return result;
    }

    /**
     * 132. 分割回文串 II
     * @param s
     * @return
     */
    public static int minCut(String s){
        char[] data = s.toCharArray();
        int length = data.length;
        int[] result = new int[length];
        boolean[][] f = new boolean[length][length];

        for(int i = 0;i < length;i++){
            for(int j = i; j>=0; j--){
                if(i == j){
                    f[i][j] = true;
                }else{
                    if(i - j + 1 == 2){
                        f[j][i] = f[i][j] = data[i] == data[j];
                    }else{
                        f[j][i] = f[i][j] = data[i] == data[j] && f[i-1][j+1];
                    }
                }
            }
        }

        for(int i =1 ;i<length; i++){
            if(f[0][i]){
                result[i] = 0;
            }else{
                result[i] = result[i-1] + 1;
                for(int j = 1;j<i;j++){
                    if(f[i][j]){
                        result[i] = Math.min(result[i],result[j-1] + 1);
                    }
                }
            }
        }

        return result[length -1];
    }


    public static int minCutMine(String s) {
        char[] data = s.toCharArray();
        int length = data.length;
        int[] result = new int[length];
        for(int i = 1;i<length;i++){
            boolean containsHuiwen = false;
            int position = length;
            int min = Integer.MAX_VALUE;;
            for(int j = 0;j < i;j++){
                if(isHuiwen(data,j,i)){
                    containsHuiwen = true;
                    if(j != 0){
                        // for(int k = j-1 ; k < i ;k++){
                            if(result[j -1 ] < min){
                                min = result[j-1];
                            }
                            
                        // }
                    }else{
                        result[i] = 0;
                        min = -1;
                    }
                    position = j;
                    // break;
                }
            }
            if(!containsHuiwen){
                result[i] = result[i-1] + 1;
            }else{
                if(position == 0){
                    result[i] = 0;
                }else{
                    result[i] = Math.min(min + 1,result[i-1] + 1);
                }
            }

        }
        
        return result[length-1];
    }

    public static boolean isHuiwen(char[] data,int start,int end){
        while(start<end){
            if(data[start]!=data[end]){
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }

    /**
     * 503. 下一个更大元素 II
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);
        for(int i = 0 ;i < 2* length -1;i++){
            int temp = i % length;
            while(!stack.isEmpty() && (nums[stack.peek()] < nums[temp])){
                result[stack.pop()] = nums[temp];
            }
            stack.push(temp);
        }
        return result;
    }

    public static int[] nextGreaterElementsMine(int[] nums) {
        int length = nums.length;
        int[] result = new int[length];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < length;i++){
            if(nums[i]>max){
                max = nums[i];
            }
        }

        for(int i = 0; i < length;i++){
            if(nums[i] == max){
                result[i] = -1;
                continue;
            }
            for(int j = i + 1;j<i + length;j++){
                int temp = j % length;
                if(nums[i]<nums[temp]){
                    result[i] = nums[temp];
                    break;
                }
            }
        }



        return result;
    }

    /**
     * 232. 用栈实现队列
     * @param x
     */
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s2.size() == 0){
            for(int i = 0,length = s1.size();i < length;i++){
                s2.push(s1.pop());
            }
        }

        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(s2.size() == 0){
            for(int i = 0,length = s1.size();i < length;i++){
                s2.push(s1.pop());
            }
        }
        
        return s2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.size() + s2.size() ==0;
    }

    /**
     * 354. 俄罗斯套娃信封问题
     */
    public static int maxEnvelopes(int[][] envelopes) {
        int length = envelopes.length;
        if(envelopes == null || length == 0){
            return 0;
        }
        int num = envelopes[0].length;
        int[] fm = new int[length];
        fm[0] = 1;
        for(int i = 0;i<length;i++){
            for(int j = i+1;j<length;j++){
                if(envelopes[i][0]>envelopes[j][0]){
                    int [] temp = envelopes[j];
                    envelopes[j] = envelopes[i];
                    envelopes[i] = temp;
                }
            }
        }

        int maxA = 0;
        for(int i = 1;i<length; i++){
            int max = 1;
            for(int j = 0;j<i;j++){
                if(envelopes[i][0]>envelopes[j][0] && envelopes[i][1]>envelopes[j][1]){
                    max = Math.max(fm[j] + 1,max);
                    maxA = Math.max(max,maxA);
                }
            }
            fm[i] = max;
        }

        return maxA;
    }

    /**
     * 338. 比特位计数
     * @param num
     * @return
     */
    public static int[] countBits2(int num) {
        int[] result = new int[num+1];
        for(int i = 1;i<=num;i++){
            result[i] = result[i/2];
            if((i & 1) == 1){
                result[i]++;
            }
        }
        return result;
    }
    public static int[] countBits(int num) {
        int[] result = new int[num+1];
        int pow = 0;
        for(int i = 0;i<=num;i++){
            int test = (int)Math.pow(2, pow+1);
            if(i == test){
                pow++;
            }else{
                test = test/2;
            }
            int powData = test/2;
            
            if(i<2){
                int a = i % 2;
                if(a == 0){
                    result[i] = 0;
                }else if(a == 1){
                    result[i] =1;
                }
            }else{
                if(i<test + powData){
                    result[i] = result[i-powData];
                }else{
                    result[i] = result[i-powData] + 1;
                }
            }
            
        }
        return result;
    }

    /**
     * 10.正则匹配
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch2(String s, String p) {
        int sLength = s.length(),pLength = p.length();
        boolean fm[][] = new boolean[sLength + 1][pLength + 1];
        char[] sData = s.toCharArray();
        char[] pData = p.toCharArray();
        fm[0][0] = true;
        for(int i = 0; i<=sLength;i++){
            for(int j = 1;j<=pLength;j++){
                if(pData[j-1] == '*'){
                    fm[i][j] = fm[i][j-2];
                    if(matches(sData,pData,i,j-1)){
                        fm[i][j] = fm[i][j] || fm[i-1][j];
                    }
                }else{
                    if(matches(sData,pData,i,j)){
                        fm[i][j] = fm[i-1][j-1];
                    }
                }
            }
        }
        
        return fm[sLength][pLength];
    }

    public static boolean matches(char[] s, char[] p,int i,int j){
        if(i == 0){
            return false;
        }
        if(p[j-1] == '.'){
            return true;
        }
        return p[j-1] == s[i-1];
    }


    public static boolean isMatch(String s, String p) {
        System.out.println("s: " + s + ", p: " + p);
        if(s.equals(p)){
            return true;
        }
        if("".equals(p) || null == p){
            return false;
        }
        int sLength = s.length(),pLength = p.length();
        char[] pData = p.toCharArray();
        List<String> pList = new ArrayList<>();
        List<Boolean> pListB = new ArrayList<>();
        int position = 0;
        boolean lastDom = false;
        for(int i = 0; i< pLength; i++){
            if(pData[i] == '*'){
                if(position != i-1){
                    pList.add(p.substring(position,i-1));
                    pListB.add(false);
                }
                pList.add(p.substring(i-1,i));
                pListB.add(true);
                position = i + 1;
                lastDom = false;
            }else if(pData[i] == '.'){
                if(i != 0 && position !=i){
                    pList.add(p.substring(position,i));
                    pListB.add(false);
                    position = i;
                }
                if(i == pLength - 1){
                    pList.add(p.substring(i,i+1));
                    pListB.add(false);
                }
                lastDom = true;
            }else{
                if(lastDom){
                    pList.add(p.substring(position,i));
                    pListB.add(false);
                    lastDom = false;
                    position = i;
                }
                if(i == pLength - 1){
                    pList.add(p.substring(position,i+1));
                    pListB.add(false);
                }
            }
        }
        // System.out.println(pList.toString());
        // System.out.println(pListB.toString());

        position = 0;
        int change = 0;
        boolean isLastB =false;
        boolean add = false;
        int addPosition = 0;
        for (int i = 0; i < pList.size(); i++) {
            String matchStr = pList.get(i);
            if(pListB.get(i)){

                if(matchStr.equals(".")){
                    isLastB = true;
                    continue;
                }
                if(isLastB){
                    // isLastB = false;
                    continue;
                }
                if(!add){
                    change = position;
                    addPosition = i;
                }
                while(position<sLength && s.substring(position, position + 1).equals(pList.get(i))){
                    position++;
                }
                if(change != position){
                    add = true;
                }
            }else{
                int strLength = matchStr.length();
                // if(position + strLength > sLength){
                //     if(i>0 && pListB.get(i-1) && add ){
                //         if(matchStr.equals(".")){
                //             if(i == pListB.size()-1){
                //                 return true;
                //             }
                //             continue;
                //         }
                //         int index = s.substring(change, sLength).indexOf(matchStr);
                //         if(index>=0){
                //             position  = position + index + strLength;
                //             continue;
                //         }else{
                //             return false;
                //         }
                //     }
                //     return false;
                // }
                if(i>0 && pListB.get(i-1) && add ){
                    if(matchStr.equals(".")){
                        if(i == pListB.size()-1 && position + strLength >= sLength){
                            return true;
                        }
                        continue;
                    }
                    if(matchStr.substring(0, 1).equals(pList.get(addPosition))){
                        int index = s.substring(change, sLength).indexOf(matchStr);
                        if(index>=0){
                            position  = position + index + strLength;
                            continue;
                        }else{
                            return false;
                        }
                    }
                    
                }
                // return false;
                add = false;
                

                if(isLastB){
                    int index = 0;
                    if(!matchStr.equals(".")){
                        if(i == pList.size()-1){
                            index = s.substring(position,s.length()).lastIndexOf(matchStr);                        
                        }else{
                            index = s.substring(position,s.length()).indexOf(matchStr);                        
                        }
                        if(index < 0 ){
                            return false;
                        }
                        // if(position == index || pList.get(i-1).equals(".")){
                        //     continue;
                        // }
                        // for(int j = position;j<index;j++){
                        //     if(!s.substring(j, j+1).equals(pList.get(i-1))){
                        //         return false;
                        //     }
                        // }
                        position = position + index + strLength;
                        isLastB = false;
                    }else{
                        position = position + strLength;
                        if(position>sLength){
                            return false;
                        }
                    }
                    
                }else{
                    // if(matchStr.equals(".")){
                    //     position += strLength;
                    //     isLastB = false;
                    //     if(position>sLength){
                    //         return false;
                    //     }
                    //     continue;
                    // }

                    if(position+strLength>sLength){
                        return false;
                    }
                    if(!matchStr.equals(".")){
                        if(!matchStr.equals(s.substring(position, position+strLength))){
                            return false;
                        }
                    }
                    
                    position += strLength;
                }
                
            }
        }
        if(isLastB){
            return true;
        }
        if(position<s.length()){
            return false;
        }

        return true;
    }
}
