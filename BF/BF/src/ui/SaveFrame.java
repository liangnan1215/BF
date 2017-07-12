package ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import service.IOService;

public class SaveFrame extends JFrame{
  
		private JTextField filenameField;
	    private JLabel promptLabel;
	    private JButton saveButton;
	    private JLabel fileLabel;
	    private JComboBox filebox;
	    public SaveFrame(MainFrame mainFrame){
	        filenameField=new JTextField();
	        promptLabel=new JLabel("please enter filename" +"\n"+
	                "(if the filename is existed,the file will be cover)");
	        saveButton=new JButton("save");
	        fileLabel=new JLabel("文件类型");
	        String []choseList=new String[]{"bf","ook"};
	        filebox=new JComboBox(choseList);        
	        filenameField.setFont(new Font("Lato",1,40));
	        promptLabel.setFont(new Font("Lato",1,20));
	        saveButton.setFont(new Font("Lato",1,40));
	        setVisible(true);
	        setBounds(700,400,650,300);
	        setLayout(new GridLayout(5,1));
	        saveButton.addActionListener(e -> {
	        	
	        	String s=(String)filebox.getSelectedItem();
	        	if(s.equals("bf"))
	        		  mainFrame.remoteHelper.setCurrentFile(filenameField.getText()+".bf");	
	        	else
	        		  mainFrame.remoteHelper.setCurrentFile(filenameField.getText()+".ook");	          
	            IOService ioService=mainFrame.remoteHelper.getIOService();
	            try {
	            	
	            		ioService.writeFile(mainFrame.getCodeText(),mainFrame.remoteHelper.getUsername(),mainFrame.remoteHelper.getCurrentFile());
	            		setMessage("Save Success");         	
	            } catch (RemoteException e1) {
	                e1.printStackTrace();
	            }
	            dispose();
	        });
	        add(fileLabel);
	        add(filebox);
	        add(promptLabel);
	        add(filenameField);
	        add(saveButton);
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
}
