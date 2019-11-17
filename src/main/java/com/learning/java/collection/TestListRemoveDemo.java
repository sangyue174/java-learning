package com.learning.java.collection;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author sangyue1
 * @Date 2019/8/22 14:27
 */
public class TestListRemoveDemo {
	public static void main(String[] args) {
		List<Integer> list = Lists.newArrayList(1, 2, 3);
		Map<String, List<Integer>> map = Maps.newHashMap();

		List<Integer> listTemp = Lists.newArrayList(list);
		for (int m = 1; m <= 3; m++) {
			Iterator<Integer> iterator = listTemp.iterator();
			List<Integer> list1 = Lists.newArrayList();
			while (iterator.hasNext()) {
				Integer i = iterator.next();
				if (i == 1 || i == 3) {
					list1.add(i);
					iterator.remove();
				}
			}

			if (!CollectionUtils.isEmpty(list1)) {
				map.put("m" + m, list1);
			}
		}

		if (listTemp.size() != 0) {
			map.put("m", listTemp);
		}
		if (map.size() == 0) {
			map.put("m", Lists.newArrayList(99));
		}
		System.out.println(map);


	}
}
