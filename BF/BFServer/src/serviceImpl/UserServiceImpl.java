package serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.User;
import service.UserService;




public class UserServiceImpl implements UserService{


	
	@Override
	public boolean login(String username, String password) throws RemoteException {
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		String filePath = "C:\\Users\\liangnan\\Desktop\\学习作业\\大一下\\软工1\\大作业\\BFServer\\user_password.txt";
		File file=new File(filePath);
		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			String usernamepass;			
			while((usernamepass=br.readLine())!=null){
				String temp=username+"_"+password;
				if(usernamepass.equals(temp)){
					return true;
				}		
				else
					;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;	
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		
		return true;
	}

	@Override
	public boolean isLogin(String username) throws RemoteException {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean register(String username, String password) throws RemoteException {
		// TODO 自动生成的方法存根
		String filePath = "C:\\Users\\liangnan\\Desktop\\学习作业\\大一下\\软工1\\大作业\\BFServer\\user_password.txt";
		File file=new File(filePath);
		try {
			FileWriter fos = new FileWriter(file, true);
			BufferedReader br=new BufferedReader(new FileReader(file));
			String usernamepass;
			
			if((usernamepass=br.readLine())==null){
				String temp=username+"_"+password;
				fos.write(temp);
				fos.write("\r\n");
				fos.flush();
				fos.close();
				return true;
			}
			else{
				int judge=0;
				while((usernamepass=br.readLine())!=null){
					if(usernamepass.split("_")[0].equals(username)){
						judge++;
					}		
					else{
						;
					}							
				}
				if(judge==0){
					String temp=username+"_"+password;
					fos.write(temp);
					fos.write("\r\n");
					fos.flush();
					fos.close();
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return false;
	}
	

}
