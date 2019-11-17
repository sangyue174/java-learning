package com.learning.java.sort;

import java.util.Arrays;

/**
 * QuickSort
 * 平均复杂度 nlogn
 *
 * @Author sangyue1
 * @Date 2019/6/3 16:09
 */
public class QuickSort<T extends Comparable<T>> implements Sort<T> {
	@Override
	public void sort(T[] values) {
		if (values == null || values.length <= 0) {
			return;
		}
		int high = values.length - 1;
		int low = 0;
		//sort(values, low, high);
		quickSort(values, low, high);
	}

	private void sort(T[] values, int low, int high) {
		// 终止标志
		if (low < high) {
			T pivot = values[high];
			// 获取分区index
			int pIndex = getPartition(values, low, high, pivot);
			// 对上部分分区进行递归排序
			sort(values, low, pIndex - 1);
			// 对下部分分区进行递归排序
			sort(values, pIndex + 1, high);
		}
	}

	/**
	 * 获取分区索引
	 *
	 * @param values
	 * @param low
	 * @param high
	 * @param pivot  当前分区最后一个元素(high位置对应的元素)
	 * @return
	 */
	private int getPartition(T[] values, int low, int high, T pivot) {
		// 代表当前分区中小于pivot的元素游标
		int cursor = low;
		for (int i = low; i <= high; i++) {
			// 如果值小于pivot, 则将cursor位置和该元素进行交换, 如果不小于, 则直接跳过
			// 比如[4, 1, 2, 5, 3] pivot=5, 这一步操作会将小于5的值放在数组前边, 结果变成[1, 2, 4, 5, 3]
			// 计算步骤
			// 1. 4>3, 位置不变
			// 2. 1<3, 将4和1交换, [1, 4, 2, 5, 3]
			// 3. 2<3, 将4和2交换, [1, 2, 4, 5, 3]
			// 4. 4>3, 位置不变 [1, 2, 4, 5, 3]
			// 5. 5>3, 位置不变 [1, 2, 4, 5, 3]
			if (values[i].compareTo(pivot) < 0) {
				T temp = values[i];
				values[i] = values[cursor];
				values[cursor] = temp;
				cursor++;
			}
		}

		// cursor为2, values[2] 为4
		// 此步目的将cursor和pivot元素交换, 这样所有小于pivot的元素都在前边, 大于pivot的元素都在后边.
		// 结果为 [1, 2, 3, 5, 4]
		if (cursor < high) {
			T temp = values[cursor];
			values[cursor] = pivot;
			values[high] = temp;
		}
		return cursor;
	}


	public static void main(String[] args) {
		// 快速排序
		QuickSort sort = new QuickSort();
		Integer[] values;
		//values = Sort.of(3, 5, 2, 1, 4);
		values = Sort.of(5, 4, 3, 2, 1);
		sort.sort(values);
		System.out.println(Arrays.toString(values));
	}

	/*//////////////快排, 减少交换次数，提高效率/////////////////////*/
	// 6 2 7 3 8 9, key = 6, i = 0, j = 5, 从后往前找
	// -> 3 2 7 6 8 9
	// -> 3 2 6 7 8 9, 6前边都是小于他的, 后边都会大于他的
	private void quickSort(T[] values, int low, int high) {
		int l = low, h = high;
		if(l >= h){ return;}
		T key = values[l];
		while (l < h) {
			// 按high--方向遍历目标数组，直到比key小的值为止
			while (h > l && values[h].compareTo(key) >= 0) {
				h--;
			}
			if (l < h) {
				// values[i]已经保存在key中，可将后面的数填入
				values[l] = values[h];
				l++;
			}
			// 按low++方向遍历目标数组，直到比key大的值为止
			while (l < h && values[l].compareTo(key) <= 0){
				// 此处一定要小于等于零，假设数组之内有一亿个1，0交替出现的话，而key的值又恰巧是1的话，那么这个小于等于的作用就会使下面的if语句少执行一亿次
				l++;
			}
			if (l < h) {
				// values[j]已保存在values[i]中，可将前面的值填入
				values[h] = values[l];
				h--;
			}
		}
		values[l] = key;//把key赋给i, 此时i==j
		// 递归调用，分组把key前后的元素进行排序
		quickSort(values, low, l - 1);
		quickSort(values, h + 1, high);
	}

}
