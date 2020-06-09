package com.filevisitor.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {

	
	
	public static void main(String a[]) {
		
		
		try {
			Files.walkFileTree(Path.of("G://testfolder"), new UnwantedFilesVisitor());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
