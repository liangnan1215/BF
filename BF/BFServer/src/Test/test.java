package Test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

import serviceImpl.ExecuteServiceImpl;
import serviceImpl.OokexecuteServiceImpl;


public class test {
	
	@Test
	public void test1() throws RemoteException {
		ExecuteServiceImpl executor = new ExecuteServiceImpl();
	
		assertEquals("A", executor.execute(",.", "A"));
		assertEquals("F", executor.execute(",+++++.", "A"));
		assertEquals("7" + "\n", executor.execute(",>++++++[<-------->-],,[<+>-],<.>.", "4+3\n"));
		assertEquals("Hi NJU", executor.execute("++++++++[>+++++++++[>+>+>+>+>+>+<<<<<<-]<-]++++++[>++++++[>>+>-<<<-]<-]>>>--->---->++++++>++>+++++++++++++<<<<<.>.>.>.>.>.", "")); 
		
		assertEquals("8", executor.execute(",>,,>++++++++[<------<------>>-]<<[>[>+>+<<-]>>[<<+>>-]<<<-]>>>++++++[<++++++++>-]<.", "2*4"));
		assertEquals("AB", executor.execute(",>,<.>.", "AB"));
		assertEquals("G", executor.execute(",+++.", "D"));
		assertEquals("6", executor.execute(",>++++++[<-------->-],,[<+>-]<.", "2+4"));
		assertEquals("Hello World!", executor.execute("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.", ""));
		assertEquals("4", executor.execute(",>,,>++++++++[<------<------>>-]<<[>[>+>+<<-]>>[<<+>>-]<<<-]>>>++++++[<++++++++>-]<.","2*2"));
		
	}
	
	@Test
	public void test2() throws RemoteException {
		OokexecuteServiceImpl executor = new OokexecuteServiceImpl();
	
		assertEquals("A", executor.execute("Ook. Ook! Ook! Ook. ", "A"));
		assertEquals("F", executor.execute("Ook. Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. ", "A"));
		assertEquals("7", executor.execute("Ook. Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook! Ook! Ook? Ook! Ook. Ook! Ook. Ook! Ook! Ook? Ook? Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook. ", "4+3"));
		assertEquals("Hi NJU", executor.execute("Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook! Ook? Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook. Ook? Ook. Ook? Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook. Ook? Ook. Ook? Ook. Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook! Ook? Ook! Ook. Ook? Ook. Ook? Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook! Ook. Ook. Ook? Ook! Ook. Ook. Ook? Ook! Ook. Ook. Ook? Ook! Ook. Ook. Ook? Ook! Ook. Ook. Ook? Ook! Ook. ", "")); 
		
		assertEquals("8", executor.execute("Ook. Ook! Ook. Ook? Ook. Ook! Ook. Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook? Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. Ook? Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook? Ook. Ook! Ook? Ook. Ook? Ook! Ook? Ook. Ook? Ook. Ook. Ook. Ook? Ook. Ook. Ook? Ook. Ook? Ook. Ook! Ook! Ook? Ook! Ook. Ook? Ook. Ook? Ook! Ook? Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook? Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook? Ook. Ook? Ook. Ook! Ook! Ook? Ook! Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook. ", "2*4"));	
	}


	


}
