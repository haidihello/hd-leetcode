### 解题思路
动态规划代码很简单如下
```java
public int climbStairs(int n) {
        int pps = 0, ps = 0, s=1;
        for(int i = 1; i <= n; i++){
            pps = ps;
            ps = s;
            s = ps + pps;
        }
        return s;
    }
```
代码很简短，而且最终结果也是正确的，但是有一点点难懂，那这个代码是如何演变成这样子的呢？本博客将把这题梳理一下。
首先假设我们已经爬完了整个楼梯方法为*f(n)*。
现在我们来假设最后一步爬了几个楼梯，根据题目意思有两种可能

 - 上了一个楼梯：*f(n-1)*
 - 上了两个楼梯：*f(n-2)*
只有这种可能，就将这两种可能相加，可以得到递推公式
*f(n)=f(n-1)+f(n-2)*
这样我们就可以使用递归来实现，这也是这个动态规划的第一个雏形。
在写递归代码的时候我们把边界条件算一下
 
 - 只有一阶台阶的时候有一种方法*f(1) = 1*
 - 只有两阶台阶的时候有两种方法*f(2)=2*
 
下面就是递归的算法
```java
public int climbStairs(int n) {
       if(n == 1) return 1;
       if(n == 2) return 2;
       return climbStairs(n - 1) + climbStairs(n - 2);
    }
```
仔细观察这个递归，我们可以发现最大的嵌套次数是每次都*n-1*，直到`n = 2`,那最大嵌套次数就是`n - 2`次，现在能不能考虑用循环来代替嵌套呢？
现在假设使用一个数组`dp[]`来记录上到一个台阶有多少种方法，那
*f(n) = f(n - 1) + f(n -2)*
就变成了
*dp[n] = dp[n-1] + dp[n-2]*
同时    *dp[1] = 1, dp [2] =2*
接下来就可以使用数组加循环来完成这段代码了
```java
public int climbStairs(int n) {
       //这个if是防止数组越位的 
       if(n == 1) return 1;
       
       int[] dp = new int[n + 1];
       dp[1] = 1;
       dp[2] = 2;
       //循环次数是n-3+1(+1 是加上3这个状态)
       for(int i = 3; i <= n; i++){
           dp[i] = dp[i - 1] + dp[i - 2];
       }
       return dp[n];
    }
```
再仔细观察这段for循环，和递归很像，但是有一些区别
 - 递归：是从最后一个楼梯开始，往回找，我们并不知道前面的状态，所以只能一个一个去找
 - 循环：我们是从第一个楼梯开始，往上爬，前面的状态，我们都知道了，只要把前面的状态拿来用
而在循环中只有三个状态：当前状态 = 前一个状态 + 前前一个状态，拿我们只要把这两个状态保留就可以。
 - ppStatus前前状态
 - pStatus前状态
 - status此状态
*status = pStatus + ppStatus*
如果是三阶楼梯的话，那ppStatus = 1， pStatus = 2，这也是这两个状态的初始值
那就得到以下代码
```java
 public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;

        //前前状态
        int ppStatus = 1;
        //前状态
        int pStatus = 2;

        int status = 0;
        for(int i = 3; i <= n; i++){
            //当前状态
            status = ppStatus + pStatus;
            //状态移动，
            ppStatus = pStatus;
            pStatus = status;
        }
        return status;
    }
```

这个状态基本上再做开始初始化三个状态分别是-1 0 1 那就得到了我们最开始的代码

有很多表述不到位的地方还见谅

### 代码

```java
class Solution {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;

        //前前状态
        int ppStatus = 1;
        //前状态
        int pStatus = 2;

        int status = 0;
        for(int i = 3; i <= n; i++){
            //当前状态
            status = ppStatus + pStatus;
            //状态移动，
            ppStatus = pStatus;
            pStatus = status;
        }
        return status;
    }
}
```