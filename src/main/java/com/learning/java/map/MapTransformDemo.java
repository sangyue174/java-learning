package com.learning.java.map;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Author sangyue1
 * @Date 2019/9/27 9:59
 */
public class MapTransformDemo {
	public static void main(String[] args) {
		Map<String, String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
		Map<String, Map.Entry<String, String>> mapUpper1 = uniqueKey(map.entrySet(), entry -> entry.getKey().toUpperCase());
		Map<String, Map.Entry<String, String>> mapUpper2 = uniqueKey(map.entrySet(), new Function<Map.Entry<String, String>, String>() {
			@Override
			public String apply(Map.Entry<String, String> entry) {
				return entry.getKey().toUpperCase();
			}
		});
		Map<String, Map.Entry<String, String>> mapUpper4 = uniqueKeyNormal(map.entrySet(), entry -> entry.getKey().toUpperCase());

		System.out.printf("mapUpper1 is [%s], mapUpper2 is [%s]\n", mapUpper1, mapUpper2);
		System.out.printf("mapUpper4 is [%s]\n", mapUpper4);
	}

	private static <K, V extends Map.Entry, V1> ImmutableMap<K, V1> uniqueKey(
			Iterable<V> values, Function<? super V, K> keyFunction) {
		return uniqueKey(values.iterator(), keyFunction);
	}

	private static <K, V extends Map.Entry, V1> ImmutableMap<K, V1> uniqueKey(
			Iterator<V> values, Function<? super V, K> keyFunction) {
		checkNotNull(keyFunction);
		ImmutableMap.Builder<K, V1> builder = ImmutableMap.builder();
		while (values.hasNext()) {
			V value = values.next();
			builder.put(keyFunction.apply(value), (V1) value.getValue());
		}
		try {
			return builder.build();
		} catch (IllegalArgumentException duplicateKeys) {
			throw new IllegalArgumentException(
					duplicateKeys.getMessage()
							+ ". To index multiple values under a key, use Multimaps.index.");
		}
	}

	private static <K, V extends Map.Entry, V1> Map<K, V1> uniqueKeyNormal(
			Iterable<V> values, Function<? super V, K> keyFunction) {
		return uniqueKeyNormal(values.iterator(), keyFunction);
	}

	private static <K, V extends Map.Entry, V1> Map<K, V1> uniqueKeyNormal(
			Iterator<V> values, Function<? super V, K> keyFunction) {
		checkNotNull(keyFunction);
		Map<K, V1> map = new LinkedHashMap<>();
		while (values.hasNext()) {
			V value = values.next();
			map.put(keyFunction.apply(value), (V1) value.getValue());
		}
		return map;
	}
}
