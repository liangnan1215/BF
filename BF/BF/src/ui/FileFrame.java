package ui;

import service.IOService;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;


public class FileFrame extends JFrame{
    private JComboBox<String> fileComboBox;
    private JButton confirmButton;
    private JButton cancelButton;
    public FileFrame(MainFrame mainFrame){
        IOService ioService=mainFrame.remoteHelper.getIOService();

        try {
        	System.out.println(mainFrame.remoteHelper.getUsername());
            String[] fileList=ioService.readFileList(mainFrame.remoteHelper.getUsername());
            fileComboBox=new JComboBox<String >();
            for (int i = 0; i < fileList.length; i++) {
            	if(fileList[i].contains(mainFrame.remoteHelper.getUsername()))
            		fileComboBox.addItem(fileList[i]);
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }

        confirmButton=new JButton("Confirm");
        confirmButton.addActionListener(e -> {
            try {
                String filename=(String)fileComboBox.getSelectedItem();
                mainFrame.remoteHelper.setCurrentFile(filename.split("_")[1]);
                String codeText=ioService.readFile(mainFrame.remoteHelper.getUsername(),filename);
                mainFrame.setCodeText(codeText);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            dispose();

        });

        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        fileComboBox.setFont(new Font("TimesRoman",1,25));
        confirmButton.setFont(new Font("TimesRoman",1,20));
        cancelButton.setFont(new Font("TimesRoman",1,20));
//        fileComboBox.setBounds(0,0,100,200);
//        confirmButton.setBounds(210,200,30,30);
//        cancelButton.setBounds(210,200,30,30);
        confirmButton.setSize(5, 5);
	    cancelButton.setSize(5,5);
        add(fileComboBox);
        add(confirmButton);
        add(cancelButton); 
        setLocation(800,400);
        setSize(600,200);
        
        GridLayout gridLayout=new GridLayout(3,1);
        gridLayout.setHgap(30);
        gridLayout.setVgap(30);
        setLayout(gridLayout);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        


    }
}
