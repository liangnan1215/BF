package serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.omg.CORBA.ExceptionList;

public class Test {
	
	Test(){
		File directory=new File("");
		String filePath = null;
		try {
			filePath = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.out.println(filePath);
		File file=new File(filePath);
		File[] list=file.listFiles();        /* 此处获取文件夹下的所有文件 */
		String[] fileList=new String[list.length];
		for(int i=0;i<list.length;i++){
			fileList[i]=list[i].getName();
			System.out.println(fileList[i]);
		}
	}
	public static void main(String[] args) {
		new Test();
	}

}
