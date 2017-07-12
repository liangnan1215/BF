//请不要修改本文件名
package serviceImpl;

import java.io.IOException;
import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class OokexecuteServiceImpl implements ExecuteService {

	public OokexecuteServiceImpl() throws RemoteException { 
    }
	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		
		int loopcount=0;
		int looppointer=0;
		int pointer=0;
		int []store=new int [1000];
		String codegroup[]=new String[1000];
		String result="";
		//初始化数组为零
		
		for(int i=0;i<store.length;i++){
			store[i]=0;
			codegroup[i]="";
		}
		
		String temp[]=code.split(" ");
		for(int i=0;i<temp.length/2;i++){
			codegroup[i]=temp[2*i]+" "+temp[2*i+1];
		}
		
		for (int i = 0; i < codegroup.length; i++) {
			if(codegroup.equals("Ook! Ook?")){
				loopcount++;
		    }
		    if(codegroup.equals("Ook? Ook!")){
		        loopcount--;
		    }
		    if(loopcount<0){
		         return "ERROR";
		    }

		}
		if(loopcount!=0){
		     return "ERROR";

		}
		loopcount=0;
		
		//读取code
		for(int i=0;i<codegroup.length;i++){
			System.out.println(codegroup[i]);
			switch(codegroup[i]){
			case"Ook? Ook.":
				pointer--;//溢出？
				break;
			case"Ook. Ook?":
				pointer++;
				break;
			case"Ook. Ook.":
				store[pointer]++;
				break;
			case"Ook! Ook!":
				store[pointer]--;
				break;
			case"Ook! Ook.":
				result=result+(char)store[pointer];
				break;
			case"Ook. Ook!":
				if(param.equals("")==false)
					store[pointer]=param.toCharArray()[0];//ASCII
				if(param.length()!=0){
					param=param.substring(1);//截去已经输入
				}else
					param="";
				System.out.println(store[pointer]);
				System.out.println(param);
				break;
			case"Ook! Ook?":
				if(store[pointer]==0) {
		            loopcount++;
		            while (loopcount!=0) {
		                i++;
		                if(codegroup[i].equals("Ook! Ook?")){
		                    loopcount++;
		                }
		                if(codegroup[i].equals("Ook? Ook!")){
		                    loopcount--;
		                }
		            }
		            loopcount=0;
		        }
		        break;
			case"Ook? Ook!":
				if(i>=0&&i<code.length()){
					if(store[pointer]!=0){
						loopcount++;
						while (loopcount!=0) {
							i--;
							if(codegroup[i].equals("Ook? Ook!"))
								loopcount++;							
							if(codegroup[i].equals("Ook! Ook?"))
								loopcount--;
						}
						loopcount=0;
					}
				}		
		        break;
				
			}
		}
		System.out.println(result);
		
		return result;

		
	}
}


