### 解法一：暴力搜索
设计一个函数计算任意两个时间点的时间差，时间差只能为正且不超过12小时（若大于12小时，将较小的时间点看做第二天的）。事先把时间点都转为int型的分钟数，然后就是双重循环暴力搜索，这个方法刚刚好可以AC，如果直接对string型的时间点进行暴搜会TLE。

```C++ []
class Solution
{
	static constexpr int Hour12 = 12 * 60;	// 12小时对应的分钟数
	static constexpr int Hour24 = 24 * 60;	// 一天对应的分钟数

public:
	int findMinDifference( const vector< string > &timePoints )
	{
		int n = timePoints.size(), mind = INT_MAX;
		vector< int > tp( n );

		for( int i{}; i < n; ++i )
		{
			int h, m;
			sscanf( timePoints[ i ].data(), "%2d:%2d", &h, &m );
			tp[ i ] = h * 60 + m;
		}

		for( int i{}; i < n; ++i )
			for( int j = i + 1; j < n; ++j )
				mind = min( mind, Duration( tp[ i ], tp[ j ] ) );
		return mind;
	}

	int Duration( int tp1, int tp2 )
	{
		auto dur = abs( tp1 - tp2 );
		return dur > Hour12 ? Hour24 - dur : dur;
	}
};
```

### 解法二：时间点排序 + 差分
想象把所有时间点都标记在一个表盘上，那么对于任一个时间点，只需与它左边和右边的时间点计算时间差就够了，其他时间点与它的差只会更大。
![2022-01-18_17-35-53.png](https://pic.leetcode-cn.com/1642564404-MIuuSE-2022-01-18_17-35-53.png)

落实到代码，其实就是把时间点序列排序，计算序列差分的最小值。这里有一个边界问题，就是最晚（大）的那个时间点（比如"23:53"），还要和它右边的时间点做一次差分，而它右边的时间点就是最早（小）的那个时间点加24小时。

```C++ []
class Solution
{
	static constexpr int Hour24 = 24 * 60;	// 一天对应的分钟数

public:
	int findMinDifference( const vector< string > &timePoints )
	{
		int n = timePoints.size();
		vector< int > tp( n );	// timePoints转换为分钟数

		for( int i{}; i < n; ++i )
		{
			auto s = timePoints[ i ].data();
			tp[ i ] = ( ( s[ 0 ] - '0' ) * 10 + s[ 1 ] - '0' ) * 60 + ( s[ 3 ] - '0' ) * 10 + s[ 4 ] - '0';
		}
		sort( tp.begin(), tp.end() );

		int mind = tp.front() + Hour24 - tp.back();
		for( int i = 1; i < n; ++i )
			mind = min( mind, tp[ i ] - tp[ i - 1 ] );

		return mind;
	}
};
```


### 解法三：桶排序 + 差分
这个方法参考了[@宫水三叶](/u/ac_oier/)的[题解](https://leetcode-cn.com/problems/minimum-time-difference/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-eygg/)。本质上和解法二相同，也是将时间点排序然后计算差分。

因为一天的分钟数是有限的，最多24 * 60 = 1440个，利用桶排序的思维，可以准备1440个桶，把每个时间点装到对应的桶里，这样所有时间点就天然排好序了，然后就是计算每两个有效时间点的差分了。

### 实现细节
1. 若时间点个数大于1440，则必然存在重复的时间点，直接返回0；
2. 在装桶的过程，若发现桶不为空，说明有重复时间点，没必要继续了，直接返回0；

```C++ []
class Solution
{
	static constexpr int Hour24 = 24 * 60;	// 一天对应的分钟数

public:
	int findMinDifference( const vector< string > &timePoints )
	{
		if( timePoints.size() > Hour24 )
			return 0;	// 至少有两个相同的时间点

		bool aday[ Hour24 ]{};
		for( auto &tp : timePoints )
		{
			auto s = tp.data();
			auto m = ( ( s[ 0 ] - '0' ) * 10 + s[ 1 ] - '0' ) * 60 + ( s[ 3 ] - '0' ) * 10 + s[ 4 ] - '0';
			
			if( aday[ m ] )
				return 0;	// 发现两个相同的时间点，直接返回
			aday[ m ] = true;
		}

		// 先找到第一个有效时间点
		int first = -1;
		while( !aday[ ++first ] );

		// 查找前后两个有效时间点差分的最小值
		int last = first, mind = INT_MAX;
		for( int i = first + 1; i < Hour24; ++i )
		{
			if( !aday[ i ] )
				continue;

			mind = min( mind, i - last );
			last = i;
		}

		return min( mind, first + Hour24 - last );
	}
};
```

### 执行结果
虽然桶排序法表现更好，但个人认为解法二仍是较为均衡和通用的，假如时间点精确到秒或毫秒桶排序是否仍然适合？
![2022-01-18_16-08-30.png](https://pic.leetcode-cn.com/1642564361-huTetz-2022-01-18_16-08-30.png)
