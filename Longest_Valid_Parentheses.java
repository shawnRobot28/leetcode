/*
   Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
   For "(()", the longest valid parentheses substring is "()", which has length = 2.
   Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
   Subscribe to see which companies asked this question
 */

import java.util.Stack;

public class Longest_Valid_Parentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        boolean[] valid = new boolean[s.length()];
        for (int i = 0; i < s.length() - 1; i++) {
            valid[i] = false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.empty()) {
                    valid[i] = true;
                    valid[stack.pop()] = true;
                }
            }
        }
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (valid[i]) {
                tmp++;
            } else {
                if (tmp > max) {
                    max = tmp;
                }
                tmp = 0;
            }
        }
        //for the last one
        if (tmp > max) {
            max = tmp;
        }
        return max;

    }

}


