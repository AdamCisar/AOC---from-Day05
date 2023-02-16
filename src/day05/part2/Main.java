package day05.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	static LinkedList<LinkedList<Character>> arr = new LinkedList<>();
	
	public static void main(String[] args) {
	BufferedReader reader;
	createArr(arr);
		
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\adamc\\eclipse-workspace\\AdventOfCode_FromDay05\\src\\day05\\part2\\input.txt"));
			String line;
			
			while ((line = reader.readLine()) != null) {
				char[] temp = line.toCharArray();
				
				if(line.isEmpty() || temp[1] == '1') {
					continue;
				}
										
				else if(temp[1] != 'o') {
					processLine(temp);
				}else {
					moveCrate(line);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		printResult(arr);
	}
	
	private static void printResult(LinkedList<LinkedList<Character>> arr) {
		char result[] = new char[9]; 
		for(int i=0; i<arr.size(); i++) {
			result[i] = arr.get(i).getFirst();
		}
		System.out.println(result);
	}

	private static void moveCrate(String line) {
		int numOfCrate, moveFrom, moveTo;
		char crate = 0;
		
		Pattern pattern = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");
        Matcher matcher = pattern.matcher(line);
        
        if(matcher.find()) {
        	
			numOfCrate = Integer.valueOf(matcher.group(1));
			moveFrom = Integer.valueOf(matcher.group(2));
			moveTo = Integer.valueOf(matcher.group(3));
			
			for(int i=0; i<numOfCrate; i++) {
			crate = arr.get(moveFrom-1).remove();
			
			arr.get(moveTo-1).addFirst(crate);
			}
        }
			
	}

	private static void processLine(char[] temp) {
		int numOfArr = 0;
		for(int i=1; i<temp.length; i+=4) {
			
			if(temp[i]==' ') {
				numOfArr++;
				continue;
			}
			
			arr.get(numOfArr).add(temp[i]);
			numOfArr++;
			
		}
		numOfArr = 0;
			
	}
	
	private static void createArr(LinkedList<LinkedList<Character>> arr) {
		for(int i=0; i<9; i++) {
			arr.add(new LinkedList<>());
		}
	}
}
