//请不要修改本文件名
package serviceImpl;

import java.io.IOException;
import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	public ExecuteServiceImpl() throws RemoteException { 
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
		int []store=new int [100];
		String result="";
		//初始化数组为零
		
		for(int i=0;i<store.length;i++){
			store[i]=0;
		}
		
		for (int i = 0; i < code.length(); i++) {
			if(code.charAt(i)=='['){
				loopcount++;
		    }
		    if(code.charAt(i)==']'){
		        loopcount--;
		    }
		    if(loopcount<0){
		         return "ERROR:Invalid code,bracket not match";
		    }

		}
		if(loopcount!=0){
		     return "ERROR:Invalid code,bracket not match";

		}
		loopcount=0;
		
		//读取code
		char[]temp=code.toCharArray();
		for(int i=0;i<code.length();i++){
			switch(temp[i]){
			case'<':
				pointer--;//溢出？
				break;
			case'>':
				pointer++;
				break;
			case'+':
				store[pointer]++;
				break;
			case'-':
				store[pointer]--;
				break;
			case'.':
				result=result+(char)store[pointer];
				break;
			case',':
				if(param.equals("")==false)
					store[pointer]=param.toCharArray()[0];//ASCII
				if(param.length()!=0){
					param=param.substring(1);//截去已经输入
				}else
					param="";
				System.out.println(param);
				break;
			case'[':
				if(store[pointer]==0) {
		            loopcount++;
		            while (loopcount!=0) {
		                i++;
		                if(temp[i]=='['){
		                    loopcount++;
		                }
		                if(temp[i]==']'){
		                    loopcount--;
		                }
		            }
		            loopcount=0;
		        }
		        break;
			case']':
				if(i>=0&&i<code.length()){
					if(store[pointer]!=0){
						loopcount++;
						while (loopcount!=0) {
							i--;
							if(temp[i]==']')
								loopcount++;							
							if(temp[i]=='[')
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


