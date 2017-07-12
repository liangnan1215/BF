package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import rmi.RemoteHelper;
import service.ExecuteService;
//import ui.MainFrame.AccoutMenuItemActionListener;
//import ui.MainFrame.HistoryMenuItemActionListen;
import ui.MainFrame.MenuItemActionListener;
//import ui.MainFrame.OpenActionListener;
//import ui.MainFrame.RedoMenuItemActionListener;
import ui.MainFrame.RunMenuItemActionListener;
import ui.MainFrame.SaveActionListener;
//import ui.MainFrame.SaveNewMenuItemActionListener;
//import ui.MainFrame.UndoMenuItemActionListener;


public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JLabel resultLabel;
	private JTextArea inputTextArea;
	private JTextArea resultTextArea;
	private JLabel accountLabel;
	private JMenu historyMenu;
	private JMenuItem undoMenuItem;
	private JMenuItem redoMenuItem;
	private Date d;
	public RemoteHelper remoteHelper=RemoteHelper.getInstance();
	
	private int Pointer=0;
	private ArrayList<String> pastCodeArrayList=new ArrayList<String >();
	private ArrayList<String> undoredoArrayList=new ArrayList<String> ();

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		frame.setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		//日期
	   
		//字体
		menuBar.setFont(new Font("Lato",1,20));
		accountLabel=new JLabel("                                                                                                                                                                          "+"unlogin");
		accountLabel.setBackground(Color.white);

		
		//第一栏file
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem saveAsMenuItem = new JMenuItem("SaveAs");
		fileMenu.add(saveAsMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		//第二栏runMenu
		JMenu RunMenu = new JMenu("Run");
		menuBar.add(RunMenu);
		JMenuItem runMenuItem = new JMenuItem("Execute");
		RunMenu.add(runMenuItem);
		//第三栏  重做撤销
		JMenu editMenu=new JMenu("Edit");
		menuBar.add(editMenu);
		 undoMenuItem=new JMenuItem("Undo");
		editMenu.add(undoMenuItem);
		 redoMenuItem=new JMenuItem("Redo");
		editMenu.add(redoMenuItem);
		//第四栏 历史版本
		historyMenu=new JMenu("Version");
		menuBar.add(historyMenu);
		JMenuItem checkCodeMenuItem=new JMenuItem("Check history code");
		historyMenu.add(checkCodeMenuItem);
		//第五栏 用户管理
		/*第5栏*/
		JMenu accountMenu=new JMenu("Login");
		menuBar.add(accountMenu);
		JMenuItem loginMenuItem=new JMenuItem("Login");
		accountMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem=new JMenuItem("Logout");
		accountMenu.add(logoutMenuItem);
		JMenuItem registerMenuItem=new JMenuItem("Register");
		accountMenu.add(registerMenuItem);
	
		menuBar.add(accountLabel);
		
		frame.setJMenuBar(menuBar);
		
		
		
		
		newMenuItem.addActionListener(new NewActionListener());
		openMenuItem.addActionListener(new OpenActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		saveAsMenuItem.addActionListener(new SaveAsActionListener());
		exitMenuItem.addActionListener(new MenuItemActionListener());
		loginMenuItem.addActionListener(new AccoutMenuItemActionListener());
		logoutMenuItem.addActionListener(new AccoutMenuItemActionListener());
		registerMenuItem.addActionListener(new AccoutMenuItemActionListener());
		runMenuItem.addActionListener(new RunMenuItemActionListener());
		undoMenuItem.addActionListener(new UndoMenuItemActionListener());
		redoMenuItem.addActionListener(new RedoMenuItemActionListener());


		textArea = new JTextArea("",20,10);
		textArea.setBackground(Color.white);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		JScrollPane scroller=new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		frame.add(scroller, BorderLayout.NORTH);



		//输入
		inputTextArea=new JTextArea("input",10,45);
		inputTextArea.setBorder(BorderFactory.createTitledBorder("input"));
		inputTextArea.setLineWrap(true);
		inputTextArea.setWrapStyleWord(true);
		inputTextArea.setBackground(Color.white);
		frame.add(inputTextArea,BorderLayout.LINE_START);


		// 显示结果
		resultTextArea=new JTextArea("",10,45);
		resultTextArea.setEditable(false);
		resultTextArea.setBorder(BorderFactory.createTitledBorder("output"));
		resultTextArea.setLineWrap(true);
		resultTextArea.setWrapStyleWord(true);
		resultTextArea.setBackground(Color.white);
		frame.add(resultTextArea,BorderLayout.LINE_END);
		
		
		textArea.addKeyListener(new unredoKeyListener());

		undoMenuItem.setEnabled(false);
		redoMenuItem.setEnabled(false);
		undoredoArrayList.add("");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 720);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
		frame.setLocation((dim.width - frame.getWidth()) / 2, (dim.height - frame.getHeight()) / 2);  
		frame.setVisible(true);
		frame.setResizable(false);

	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			 if(cmd.equals("Exit"))
				new ExitFrame(MainFrame.this);
		}
	}
	
	class NewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			inputTextArea.setText("input");
			resultTextArea.setText("");
			textArea.setText("");
		}

	}
	
	class OpenActionListener implements ActionListener {

		@Override
		
		public void actionPerformed(ActionEvent e) {
			if(remoteHelper.isLogin()==false)
				setMessage("please login first");
			else{
				new FileFrame(MainFrame.this);
			}
		}

	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String code = textArea.getText();
			d=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String dateNowStr = sdf.format(d);
			try {
				if(remoteHelper.isLogin()){
					if(remoteHelper.getCurrentFile().equals(""))
						setMessage("尚未创建文件");
					else{
						RemoteHelper.getInstance().getIOService().writeFile(code, remoteHelper.getUsername(), remoteHelper.getCurrentFile());
						addHistory(dateNowStr);
					}
				}
				else{
					setMessage("尚未登陆");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	class SaveAsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean judge=remoteHelper.isLogin();
			if(judge)
				new SaveFrame(MainFrame.this);
			else
				setMessage("you do not login");
		}

	}
	
	class RunMenuItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ExecuteService executeService=remoteHelper.getExecuteService();
			String code=textArea.getText();
			String param=inputTextArea.getText();
			if(remoteHelper.isLogin()==false)
				setMessage("请先登录");			
			else if(remoteHelper.getCurrentFile().equals(""))
				setMessage("请先创建文件");
			else{
				try {	
					String result=executeService.execute(code,param);
					resultTextArea.setText(result);
					resultTextArea.repaint();
					d=new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
					String dateNowStr = sdf.format(d);
					try {
						if(remoteHelper.isLogin()){
								RemoteHelper.getInstance().getIOService().writeFile(code, remoteHelper.getUsername(), remoteHelper.getCurrentFile());
								addHistory(dateNowStr);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			

		}
	}
	
	class AccoutMenuItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd=e.getActionCommand();
			switch (cmd){
				case "Login":new Login(MainFrame.this);break;
				case "Logout":
					try {
						remoteHelper.getUserService().logout(remoteHelper.getUsername());
						remoteHelper.setLogin(false);
						MainFrame.this.accoutUnLogin();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					break;
				case "Register":new Register();break;
			}
		}
	}
	

	class unredoKeyListener implements KeyListener{

	
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自动生成的方法存根
			
//			if ( e.getKeyCode()==KeyEvent.VK_F1)
//	            undoCode();
//	        else if (e.getKeyCode()==KeyEvent.VK_F2)
//	            redoCode();	
	        if(e.getKeyCode()==KeyEvent.VK_F3){
	        	if(remoteHelper.isLogin()==false)
					setMessage("please login first");
				else{
					new FileFrame(MainFrame.this);
				}
	        }
	        else if(e.getKeyCode()==KeyEvent.VK_F4){
	        	boolean judge=remoteHelper.isLogin();
				if(judge)
					new SaveFrame(MainFrame.this);
				else
					setMessage("you do not login");
	        }
	        else if(e.getKeyCode()==KeyEvent.VK_F5){
	        	ExecuteService executeService=remoteHelper.getExecuteService();
				String code=textArea.getText();
				String param=inputTextArea.getText();
				if(remoteHelper.isLogin()==false)
					setMessage("请先登录");			
				else if(remoteHelper.getCurrentFile().equals(""))
					setMessage("请先创建文件");
				else{
					try {	
						String result=executeService.execute(code,param);
						resultTextArea.setText(result);
						resultTextArea.repaint();
						d=new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						String dateNowStr = sdf.format(d);
						try {
							if(remoteHelper.isLogin()){
									RemoteHelper.getInstance().getIOService().writeFile(code, remoteHelper.getUsername(), remoteHelper.getCurrentFile());
									addHistory(dateNowStr);
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
	        }
	        	
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			
			
			if(Pointer!=undoredoArrayList.size()-1){
				int i=undoredoArrayList.size();
				System.out.println("123"+i);
				System.out.println(Pointer);
				for(int j=0;j<i-Pointer-1;j++ ){
					System.out.println(Pointer+j+1);
					undoredoArrayList.remove(i-1-j);
				}
			}
			undoredoArrayList.add(getCodeText());
			Pointer++;
			
			if(Pointer==undoredoArrayList.size()-1)
				redoMenuItem.setEnabled(false);
			else
				redoMenuItem.setEnabled(true);
			if(Pointer==0){
				undoMenuItem.setEnabled(false);
			}
			else
				undoMenuItem.setEnabled(true);	
			
			
						  
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO 自动生成的方法存根
			
		}
	}
	
	class RedoMenuItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			redoCode();
			System.out.println(undoredoArrayList.size());
			if(Pointer==undoredoArrayList.size()-1)
				redoMenuItem.setEnabled(false);
			else
				redoMenuItem.setEnabled(true);
			if(Pointer==0){
				undoMenuItem.setEnabled(false);
			}
			else
				undoMenuItem.setEnabled(true);	
		}
	}
	
	class UndoMenuItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			undoCode();
			if(Pointer==undoredoArrayList.size()-1)
				redoMenuItem.setEnabled(false);
			else
				redoMenuItem.setEnabled(true);
			if(Pointer==0){
				undoMenuItem.setEnabled(false);
			}
			else
				undoMenuItem.setEnabled(true);	
		}
	}
	
	public void accoutLogin(String username){
		accountLabel.setText("                            User:"+ username);

	}
	public void accoutUnLogin(){
		remoteHelper.setUsername("");
		remoteHelper.setLogin(false);
		remoteHelper.setPassword("");
		accountLabel.setText("                               unlogin");
	}
	
	public String  getCodeText(){
		return  textArea.getText();
	}
	public void setCodeText(String text){
		textArea.setText(text);
		textArea.repaint();
	}
	
	public void setMessage(String message){
    	JDialog infoDialog=new JDialog();
		infoDialog.setSize(200,100);
		infoDialog.setLocation(600, 300);
		infoDialog.setVisible(true);
		JLabel infoLabel=new JLabel(message);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVisible(true);
		infoDialog.add(infoLabel);
    }
	public void addHistory(String timename){
		pastCodeArrayList.add(timename+"_"+getCodeText());
		historyMenu.removeAll();
		if(pastCodeArrayList.size()<=3){
			for(int i =0;i<pastCodeArrayList.size();i++){
				JMenuItem historyCodeMenuItem=new JMenuItem(pastCodeArrayList.get(i).split("_")[0]);
				historyMenu.add(historyCodeMenuItem);
				historyCodeMenuItem.addActionListener(e->{
					for(int j=0;j<pastCodeArrayList.size();j++){
						if(pastCodeArrayList.get(j).split("_")[0].equals(historyCodeMenuItem.getText())){
							setCodeText(pastCodeArrayList.get(j).split("_")[1]);
							System.out.println(pastCodeArrayList.get(j));
						}
					}
					historyMenu.repaint();
					textArea.repaint();
				});
			}
		}
		else{
			int leng=pastCodeArrayList.size();
			for(int i=0;i<leng-3;i++)
				pastCodeArrayList.remove(0);
			for(int i =0;i<pastCodeArrayList.size();i++){
				JMenuItem historyCodeMenuItem=new JMenuItem(pastCodeArrayList.get(i).split("_")[0]);
				historyMenu.add(historyCodeMenuItem);
				historyCodeMenuItem.addActionListener(e->{
					for(int j=0;j<pastCodeArrayList.size();j++){
						if(pastCodeArrayList.get(j).split("_")[0].equals(historyCodeMenuItem.getText())){
							setCodeText(pastCodeArrayList.get(j).split("_")[1]);
							System.out.println(pastCodeArrayList.get(j));
						}
					}
					historyMenu.repaint();
					textArea.repaint();
				});
			}
		}
		
	}
	
	public void undoCode(){
		System.out.println(Pointer);
		if(Pointer>0) {
			Pointer--;
			setCodeText(undoredoArrayList.get(Pointer));			
		}
	}

	public void redoCode(){
		System.out.println(Pointer);
		if(Pointer<undoredoArrayList.size()-1) {
			Pointer++;	
			setCodeText(undoredoArrayList.get(Pointer));					
		}
	}
	
}
