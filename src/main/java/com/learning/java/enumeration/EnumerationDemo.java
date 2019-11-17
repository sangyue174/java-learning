package com.learning.java.enumeration;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @Author sangyue1
 * @Date 2019/5/28 18:07
 */
public class EnumerationDemo {
	public static void main(String[] args) {
		MyEnumeration<Integer> source = new MyEnumeration(3, 1);
		List<Integer> transformList = source.asList();

		System.out.printf("before update, list is %s \n", transformList);

		source.setSize(4);
		source.setElement(2);

		System.out.printf("after update, list is %s \n", transformList);

	}

	private static class MyEnumeration<E> implements Enumeration<E> {
		private int size;
		private E element;
		int cursor = 0;

		public MyEnumeration(int size, E element) {
			this.size = size;
			this.element = element;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public void setElement(E element) {
			this.element = element;
		}

		List<E> asList() {
			return new ArrayList<E>() {
				@Override
				public Iterator<E> iterator() {
					return new Iterator<E>() {
						int count = 0;

						@Override
						public boolean hasNext() {
							return count < size;
						}

						@Override
						public E next() {
							if (count < size) {
								count++;
								return element;
							}
							return null;
						}
					};
				}
			};
		}

		@Override
		public boolean hasMoreElements() {
			return cursor < size;
		}

		@Override
		public E nextElement() {
			if (cursor < size) {
				cursor++;
				return element;
			}
			return null;
		}

	}
}
