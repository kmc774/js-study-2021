package calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	public static String readFile() {
		
		StringBuffer sb = new StringBuffer();
		
		try{
	        //파일 객체 생성
	        File file = new File("C:\\Users\\tlduf\\eclipse-workspace\\windfall\\calculator\\example_cal.txt");
	        //입력 스트림 생성
	        FileReader filereader = new FileReader(file);
	      //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                sb.append( line.replaceAll("\\p{Z}+|\\p{Z}+$", "") );
                System.out.println(sb.toString());
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.            
            bufReader.close();
	    }catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }catch(IOException e){
	        e.printStackTrace();
	    }
		
		return sb.toString();
	}
	
	
}

