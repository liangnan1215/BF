package ui;

import rmi.RemoteHelper;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;


public class Register extends JFrame {
    private RemoteHelper remoteHelper=RemoteHelper.getInstance();
    private JButton loginButton;
    private JButton cancelButton;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JPasswordField passwordInput1;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel passwordLabel1;
    
    public Register(){

        loginButton=new JButton("Submit");
        cancelButton=new JButton("Cancel");
        usernameInput=new JTextField();
        passwordInput=new JPasswordField();
        passwordInput1=new JPasswordField();

        usernameLabel=new JLabel("username");
        passwordLabel=new JLabel("password");
        passwordLabel1=new JLabel("again");

        loginButton.setSize(100,50);
        cancelButton.setSize(100,50);

        cancelButton.addActionListener(e -> {
            dispose();
        });

       
        loginButton.addActionListener(e -> {
            UserService userService=remoteHelper.getUserService();
            String username=usernameInput.getText().trim();
            String password=new String(passwordInput.getPassword()).trim();
            String check=new String(passwordInput1.getPassword()).trim();
            
            if(password.equals(check)){
            	passwordLabel1.setText("true");
            	try {
    				boolean result=userService.register(username, password);
    				if(result){
    					setMessage("注册成功");
    					dispose();
    				}
    				else{
    					setMessage("已注册过");
    				}
    			}  	 
            	catch (RemoteException e1) {
    				// TODO 自动生成的 catch 块
    				e1.printStackTrace();
    			}
            }
            else{
            	passwordLabel1.setText("different");
            }
            
        });

        cancelButton.setFont(new Font("Dialog",1,20));
        loginButton.setFont(new Font("Dialog",1,20));
        usernameLabel.setFont(new Font("Dialog",1,20));
        passwordLabel.setFont(new Font("Dialog",1,20));
        passwordLabel1.setFont(new Font("Dialog",1,20));
        usernameInput.setFont(new Font("Dialog",1,20));
        passwordInput.setFont(new Font("Dialog",1,20));
        passwordInput1.setFont(new Font("Dialog",1,20));
        
        add(usernameLabel);
        add(usernameInput);
        add(passwordLabel);
        add(passwordInput);
        add(passwordLabel1);
        add(passwordInput1);
        add(loginButton);
        add(cancelButton);
        setLocation(800,400);
        setSize(400,300);
        setTitle("login");

        setFont(new Font("Dialog",Font.BOLD,30));
        setVisible(true);
        GridLayout gridLayout=new GridLayout(4,2);
        gridLayout.setHgap(40);
        gridLayout.setVgap(40);
        setLayout(gridLayout);
    
    }
    void setMessage(String message){
    	JDialog infoDialog=new JDialog();
		infoDialog.setSize(200,100);
		infoDialog.setLocation(600, 300);
		infoDialog.setVisible(true);
		JLabel infoLabel=new JLabel(message);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVisible(true);
		infoDialog.add(infoLabel);
    }
}
