package com.filevisitor.demo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class UnwantedFilesVisitor extends SimpleFileVisitor<Path> {

	PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{log}");

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

		System.out.println("Before directory visit");

		System.out.println("No of unwanted (log) files in directory: " + dir.getFileName() + " - "
				+ Files.list(dir).filter(file -> matcher.matches(file.getFileName())).count());

		return FileVisitResult.CONTINUE;

	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

		if (matcher.matches(file.getFileName())) {

			Files.delete(file);

		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

		System.out.println("Post directory visit:");

		System.out.println("No of unwanted (log) files in directory: " + dir.getFileName() + " - "
				+ Files.list(dir).filter(file -> matcher.matches(file.getFileName())).count());

		return FileVisitResult.CONTINUE;
	}

}
