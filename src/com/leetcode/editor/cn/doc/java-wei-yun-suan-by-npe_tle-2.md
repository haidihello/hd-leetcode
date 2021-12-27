**思路**
由于ASCII码字符个数为128个，而且题目说了`如果你不使用额外的数据结构，会很加分`。因此可以使用两个64位的long变量来存储是否出现某个字符，二进制位1表示出现过， 0表示未出现过。具体代码如下：

```java
  public boolean isUnique(String astr) {
        long low64 = 0;
        long high64 = 0;

        for (char c : astr.toCharArray()) {
            if (c >= 64) {
                long bitIndex = 1L << (c - 64);
                if ((high64 & bitIndex) != 0) {
                    return false;
                }

                high64 |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((low64 & bitIndex) != 0) {
                    return false;
                }

                low64 |= bitIndex;
            }

        }

        return true;
    }

```