### 思路

- 标签：数学
- 如果是负数则一定不是回文数，直接返回 false
- 如果是正数，则将其倒序数值计算出来，然后比较和原数值是否相等
- 如果是回文数则相等返回 true，如果不是则不相等 false
- 比如 123 的倒序 321，不相等；121 的倒序 121，相等

### 代码

```Java []
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int cur = 0;
        int num = x;
        while(num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }
}
```

### 画解

 ![frame_00001.png](https://pic.leetcode-cn.com/823e08d4f367e26478d1caff98dcfa3db7797b79a8bbd77ed031c4888d28d7c5-frame_00001.png) ![frame_00002.png](https://pic.leetcode-cn.com/b3dc555a232ce4055a2e4455a2e9016a5ac542dc3327f80f9d32ed4472a4ef97-frame_00002.png) ![frame_00003.png](https://pic.leetcode-cn.com/5dc12719629ece8122ebb1b0f045f47131607001182fa4a3e209320d036bb953-frame_00003.png) 

想看大鹏画解更多高频面试题，欢迎阅读大鹏的 LeetBook：[《画解剑指 Offer 》](https://leetcode-cn.com/leetbook/detail/illustrate-lcof/)，O(∩_∩)O