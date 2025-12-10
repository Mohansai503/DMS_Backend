package com.dms.dmsproject.helper;

	import java.io.File;
	import java.io.IOException;

	public class TestFile {
	    public static void main(String[] args) throws IOException {
	        String filePath = "C:/uploads/testfile.txt"; // or any path you like
	        String filePath1 = "C:/uploads/testfile1.txt";
	        String filePath2 = "C:/uploads/testfile2.txt";
	        String filePath3 = "C:/uploads/testfile3.txt";
	        File file = new File(filePath);
	        File file1 = new File(filePath1);
	        File file2 = new File(filePath2);
	        File file3 = new File(filePath3);
	        

	        // Make sure the folder exists
	        file.getParentFile().mkdirs();
	        file1.getParentFile().mkdirs();
	        file2.getParentFile().mkdirs();
	        file3.getParentFile().mkdirs();

	        // Create a dummy file
	        if (file3.createNewFile()) {
	            System.out.println("Dummy file created at: " + filePath3);
	        } else {
	            System.out.println("File already exists: " + filePath);
	        }
	    }
	}
