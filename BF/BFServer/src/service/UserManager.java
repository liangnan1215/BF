package service;

import java.util.ArrayList;



public class UserManager {
	
	public UserManager(){
		
	}
	 private ArrayList<String> currentUsers=new ArrayList<String >();

	    public void userLogin(User user){
	        currentUsers.add(user.getUsername());
	        for (int i = 0; i < currentUsers.size(); i++) {
	            System.out.println(currentUsers.get(i));
	        }
	    }

	    public void userLogout(String  username){
	        for (int i = 0; i < currentUsers.size(); i++) {
	            if(currentUsers.get(i).equals(username)){
	                currentUsers.remove(i);
	            }
	        }
	    }
}
