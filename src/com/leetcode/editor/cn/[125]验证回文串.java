package com.leetcode.editor.cn;
/**
 * <p>给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。</p>
 *
 * <p><strong>说明：</strong>本题中，我们将空字符串定义为有效的回文串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "A man, a plan, a canal: Panama"
 * <strong>输出:</strong> true
 * <strong>解释：</strong>"amanaplanacanalpanama" 是回文串
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> "race a car"
 * <strong>输出:</strong> false
 * <strong>解释：</strong>"raceacar" 不是回文串
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
 * <li>字符串 <code>s</code> 由 ASCII 字符组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 555</li><li>👎 0</li></div>
 */

import java.util.Locale;
import java.util.Stack;

/**
 * @author haidi
 * 2022-08-03 16:13:25
 */
class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        public boolean isPalindrome(String s) {
////            s = s.replaceAll("[^A-Za-z0-9]+", "").toLowerCase(Locale.ROOT);
//
//            Stack stack = new Stack();
//            for (int i = 0; i < s.length(); i++) {
//
//                stack.push(Character.isLetterOrDigit(s.charAt(i)));
//            }
//            //出栈
//            String result = "";
//            while (!stack.empty()) {
//                String popStack = stack.pop().toString();
//                result += popStack;
//            }
//            if (s.equals(result)) {
//                return true;
//            }
//            return false;
//        }
            public boolean isPalindrome (String s){

                if (s.length() == 0) {
                    return true;
                }
                int left = 0, right = s.length()-1;

                while (left < right) {
                    while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                        left++;
                    }
                    while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                        right--;
                    }
                    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                        return false;
                    }
                    left++;
                    right--;
                }
                return true;
            }
        }

//leetcode submit region end(Prohibit modification and deletion)
}
	