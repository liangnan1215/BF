package ui;

import java.awt.Font;
import java.awt.GridLayout;

import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rmi.RemoteHelper;
import service.UserService;

public class Login  extends JFrame{
	
	private RemoteHelper remoteHelper=RemoteHelper.getInstance();
	private JButton loginButton;
	private JButton cancelButton;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private MainFrame mainFrame;
	
	public Login(MainFrame mainFrame){
		JFrame loginFrame=new JFrame();
		
		this.mainFrame=mainFrame;
		
		loginButton=new JButton("Login");
	    cancelButton=new JButton("Cancel");
	    usernameInput=new JTextField();
	    passwordInput=new JPasswordField();
	    
	    loginButton.addActionListener(e -> {
	    	UserService userService=remoteHelper.getUserService();
            String username=usernameInput.getText().trim();
            String password=new String(passwordInput.getPassword()).trim();

            try {
                if(userService.login(username,password)==true){
                    remoteHelper.setUsername(username);
                    remoteHelper.setPassword(password);
                    remoteHelper.setLogin(true);
                    mainFrame.accoutLogin(username);
                    setMessage("登陆成功");
                }
                else{
                	setMessage("密码错误");
                }
                loginFrame.dispose();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
	    cancelButton.addActionListener(e -> {
            loginFrame.dispose();
        });
	    
	    loginButton.setSize(5, 5);
	    cancelButton.setSize(5,5);

	    usernameLabel=new JLabel("username");
	    passwordLabel=new JLabel("password");
	    
	    loginFrame.add(usernameLabel);
        loginFrame.add(usernameInput);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordInput);
        loginFrame.add(loginButton);
        loginFrame.add(cancelButton);
        loginFrame.setLocation(800,400);
        loginFrame.setSize(300,200);
        loginFrame.setTitle("login");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setFont(new Font("TimesRoman",Font.BOLD,30));
        loginFrame.setVisible(true);
        GridLayout gridLayout=new GridLayout(3,2);
        gridLayout.setHgap(30);
        gridLayout.setVgap(30);
        loginFrame.setLayout(gridLayout);
		
        loginFrame.repaint();
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
