package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		String filePath = "C:\\Users\\liangnan\\Desktop\\学习作业\\大一下\\软工1\\大作业\\BFServer\\"+fileName;
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
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public String[] readFileList(String userId) {
		
		String filePath = "C:\\Users\\liangnan\\Desktop\\学习作业\\大一下\\软工1\\大作业\\BFServer";
		File file=new File(filePath);
		File[] list=file.listFiles();        /* 此处获取文件夹下的所有文件 */
		String[] fileList=new String[list.length];
		for(int i=0;i<list.length;i++)
			fileList[i]=list[i].getName();
		
		
		return fileList;
		// TODO Auto-generated method stub
		
	}
	
}
