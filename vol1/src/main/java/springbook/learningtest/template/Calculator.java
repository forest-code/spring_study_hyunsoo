package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	/*
	public Integer calcSum(String filepath) throws Exception {
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			
			@Override
			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				Integer sum = 0;
				String line = null;
				
				while ((line = br.readLine()) != null) {
					sum += Integer.valueOf(line);
				}
				
				return sum;
			}
		};
		return fileReadTemplate(filepath, sumCallback);
	}
	
	public Integer calcMultiply(String filepath) throws Exception {
		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
			
			@Override
			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
				Integer multiply = 1;
				String line = null;
				
				while((line = br.readLine()) != null) {
					multiply *= Integer.valueOf(line);
				}
				
				return multiply;
			}
		};
		return fileReadTemplate(filepath, multiplyCallback);
	}
	
	public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			int ret = callback.doSomethingWithReader(br);
			return ret;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	*/
	
	public Integer calcSum(String filepath) throws Exception {
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, sumCallback, 0);
	}
	
	public Integer calcMultiply(String filepath) throws Exception {
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
			
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, multiplyCallback, 1);
	}
	
	public String concatenate(String filepath) throws Exception {
		LineCallback<String> concatenateCallback = new LineCallback<String>() {
			
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		return lineReadTemplate(filepath, concatenateCallback, "");
	}
	
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		} catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
