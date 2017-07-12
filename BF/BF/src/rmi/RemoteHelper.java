package rmi;

import java.rmi.Remote;

import service.ExecuteService;
import service.IOService;
import service.UserService;

public class RemoteHelper {
	private Remote remote;
	private String username="asd";
	private String password="12345";
	private String currentFile="";
	private boolean isLogin=false;
	
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public IOService getIOService(){
		return (IOService)remote;
	}
	
	public UserService getUserService(){
		return (UserService)remote;
	}
	
	public ExecuteService getExecuteService(){
		return  (ExecuteService)remote;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
        this.setLogin(true);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public void setCurrentFile(String currentFile) {
		this.currentFile = currentFile;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean login) {
		isLogin = login;
	}
}
