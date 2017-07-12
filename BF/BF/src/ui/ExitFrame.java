package ui;

import rmi.RemoteHelper;
import service.IOService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;


public class ExitFrame extends JFrame {
    private JButton exit;
    private JButton cancel;
    private JButton saveAndExit;
    private JLabel label;
    public ExitFrame(MainFrame mainFrame){
        exit=new JButton("退出");
        cancel=new JButton("取消");
        saveAndExit=new JButton("保存并退出");
        label=new JLabel();
        exit.addActionListener(e-> {           
                System.exit(0);
        });

        cancel.addActionListener(e-> {
                dispose();           
        });
        saveAndExit.addActionListener(e-> {
           IOService ioService= RemoteHelper.getInstance().getIOService();
           try {
				ioService.writeFile(mainFrame.getCodeText(),RemoteHelper.getInstance().getCurrentFile().split("_")[0], RemoteHelper.getInstance().getCurrentFile().split("_")[1]);
			} catch (RemoteException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
               System.exit(0);
            
        });
        label.setText("确定要退出吗,是否保存?");
        add(label);
        add(exit);
        add(cancel);
        add(saveAndExit);
        setLayout(new FlowLayout());
        setTitle("退出");
        setVisible(true);
        setBounds(800,600,400,300);

    }
}
