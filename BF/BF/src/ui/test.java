package ui;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class test {
	
	public static void main(String[] args) throws IOException {
		String userid=
		String filePath = "C:\\Users\\liangnan\\Desktop\\学习作业\\大一下\\软工1\\大作业\\BFServer\\"+userId+"_"+"fileName";
		File file=new File(filePath);
		String result="";
		try {
			FileReader fr=new FileReader(file);
			BufferedReader br = new BufferedReader (fr);
	        String s;
	        while ((s = br.readLine() )!=null) {
	        	result=result+s;
	        	result+="\r\n";	   
	        	System.out.println(result);
	        }
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
}
