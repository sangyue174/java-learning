package com.learning.java.algorithm;

/**
 * 剑指offer
 * 将一个字符串中的空格替换成 "%20"
 * <p>
 * Input:
 * "A B"
 * <p>
 * Output:
 * "A%20B"
 *
 * @Author sangyue1
 * @Date 2019/11/9 23:38
 */
public class ReplaceBlankStr {
	public static String replaceSpace(StringBuffer str) {
		int P1 = str.length() - 1;
		for (int i = 0; i <= P1; i++) {
			if (str.charAt(i) == ' ') {
				str.append("  ");
			}
		}

		int P2 = str.length() - 1;
		while (P1 >= 0 && P2 > P1) {
			char c = str.charAt(P1--);
			if (c == ' ') {
				str.setCharAt(P2--, '0');
				str.setCharAt(P2--, '2');
				str.setCharAt(P2--, '%');
			} else {
				str.setCharAt(P2--, c);
			}
		}
		return str.toString();
	}

	public static void main(String[] args) {
		System.out.println(replaceSpace(new StringBuffer("a  b c d")));
	}
}
