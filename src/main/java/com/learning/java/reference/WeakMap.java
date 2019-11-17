package com.learning.java.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * WeakMap
 *
 * @Author sangyue1
 * @Date 2019/6/17 22:53
 */
public class WeakMap<K, V> extends HashMap<K, V> {
	private static ReferenceQueue<Object> rq = new ReferenceQueue<>();

	private Map<K, Entry<K, V>> map = new HashMap<>();

	@Override
	public V put(K key, V value) {
		Entry<K, V> entry = new Entry<>(key, value, rq);
		handleSize();
		map.put(entry.getKey(), entry);
		return entry.get();
	}

	@Override
	public int size() {
		handleSize();
		return map.size();
	}

	@Override
	public V get(Object key) {
		handleSize();
		return map.get(key) == null ? null : map.get(key).get();
	}

	private void handleSize() {
		Entry<K, V> entry;
		for (; (entry = (Entry<K, V>) rq.poll()) != null; ) {
			System.out.println("回收了:" + entry);
			map.remove(entry.getKey());
			entry.key = null;
		}
	}

	private static class Entry<K, V> extends WeakReference<V> {
		private K key;

		public Entry(K key, V value, ReferenceQueue<? super V> rq) {
			super(value, rq);
			this.key = key;
		}

		public K getKey() {
			return key;
		}
	}

	public static void main(String[] args) {
		int _1M = 1024 * 1024;
		Map<Integer, byte[]> wm = new WeakMap<>();
		for (int i = 0; i < 1000; i++) {
			byte[] bytes = new byte[_1M];
			wm.put(i, bytes);
		}
		System.out.println("Map size is: " + wm.size());
		//System.out.println("Map get 999: " + new String(wm.get(999)));

	}

	//WeakMap<Integer, byte[]> wm = new WeakMap<>();
}
