二分法，找到并且满足k*k <x条件的最大的k。
```java
class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x, res = 0;
        while (l <= r){
            int mid = (l + r) / 2;
            if ((long)mid * mid <= x){
                res = mid;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return res;
    }
}
```