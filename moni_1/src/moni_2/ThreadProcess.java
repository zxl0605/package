package moni_2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.security.auth.kerberos.KerberosKey;
import javax.swing.*;

import org.omg.PortableServer.THREAD_POLICY_ID;
public class ThreadProcess {
	
	
	public static void main(String[] args) {
		//ThreadFrame thFrame=new ThreadFrame();
		ThreadFrame Frame=new ThreadFrame();
		Frame.show();
		thread1=new Thread1(ThreadPanel.progressBar1);
		int i=0,j=0;
		int[] array=new int[1000];
		Random random=new Random(System.currentTimeMillis());
		for(i=0;i<1000;i++)
		{
			j= (int)(Math.random()*10%3);
			array[i]=j;
		}
		while(true)
		{
			for(i=0;i<1000;i++)
			{
				switch (array[i]) {
				case 0:
					if(thread1.flag==true) {
						thread1.run();
					}
					else {
						thread1.yield();
					}
					break;
				}
			}
		}
	}
	public static Thread1 thread1;

	
}
class ThreadFrame extends JFrame
{
	public int width=820;
	public int height=500;
	public ThreadPanel threadPanel=new ThreadPanel();
	public Container container;
	public ThreadFrame() {
		setTitle("模拟进程并发");
		setSize(width,height);
		setLocation(100,150);//选择组件位置
		container=getContentPane();
		container.add(threadPanel);
	}
}
class ThreadPanel extends JPanel
{
	public static  JProgressBar progressBar1;
	JButton buttonstart1;
	JButton buttonstop1;
	JLabel text1;
	//JTextArea out; //进度条文本
	public ThreadPanel() {
		setLayout(null);
		setSize(800,400);
		progressBar1=new JProgressBar();
		buttonstart1=new JButton("开始");
		buttonstop1=new JButton("结束");
		text1=new JLabel("进度条一：");
		text1.setBounds(20,70,80,40);
		progressBar1.setBounds(100,70,480,40);
		buttonstart1.setBounds(600,70,80,40);
		buttonstop1.setBounds(700,70,80,40);
		//out=new JTextArea();
		add(buttonstart1);
		add(buttonstop1);
		add(progressBar1);
		add(text1);
		buttonstart1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread1.flag=true;
			}
		});
		buttonstop1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Thread1.flag=false;
			}
		});
	}
}
class Thread1 extends Thread
{
	public static  boolean flag=true;
	JProgressBar progressBar;
	 int i=0;
	
	public Thread1(JProgressBar progressBar)
	{
		this.progressBar=progressBar;
	}
	public void run() {
		if(i<=100) {
			i+=1;
			progressBar.setValue(i);
			try {
				Thread.sleep(30);
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else {
			i=0;
		}
	}
}
