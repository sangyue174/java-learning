package com.learning.java.filesystem;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;

/**
 * @Author sangyue1
 * @Date 2019/12/1 15:19
 */
public class FileSystemDemo {
	private static final String userDir = System.getProperty("user.dir");

	public static void main(String[] args) throws URISyntaxException {
		File file = new File(userDir);

		URI URIFromFile = file.toURI();
		System.out.println("URIFromFile is :" + URIFromFile);

		URI URIFromCustome = new URI("file:/" + userDir.replace(File.separatorChar, '/'));
		URI URIFromCustome1 = new URI("file:/" + userDir.replace(FileSystems.getDefault().getSeparator(), "/"));
		System.out.println("URIFromCustome is :" + URIFromCustome);
		System.out.println("URIFromCustome1 is :" + URIFromCustome1);
	}
}
