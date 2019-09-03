package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//https://www.icourse163.org/learn/PKU-1001941004?tid=1003090008#/learn/content?type=detail&id=1004330175&cid=1005374366&replay=true
public class SwingDemo {
	public static void main(String[] args) {
//		Function1_FlowLayout();
//		Function1_BorderLayout();
//		Function1_gridLayout();
//		Function1_GridBagLayout();
//		ActionDeom();
		Calculator();

	}

	// 显示窗体
	public static void Function1_FlowLayout() {
		JFrame frame = new JFrame("hello");

		// 点关闭按钮时退出程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setBackground(Color.black);
		frame.setLocation(140, 120);
		frame.setVisible(true);

		// 流式布局
//		public FlowLayout(int align,int hgap,int vgap)
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));// 左对齐
//		frame.setLayout(new GridLayout());

		// 添加按钮
		final int num = 40;
		JButton buttons[] = new JButton[num];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(Integer.toString(i));
		}
		for (int i = 0; i < buttons.length; i++) {
//			buttons[i].setSize(100,80);
			buttons[i].setToolTipText("第" + i + "个按钮");// 提示信息
			frame.getContentPane().add(buttons[i]);
		}
		frame.setVisible(true);

		// 添加lable
		JLabel jb = new JLabel("hello world!");
		frame.getContentPane().add(jb);
		frame.setVisible(true);
	}

	public static void Function1_BorderLayout() {
		JFrame frame = new JFrame("BorderLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocation(300, 130);

		// 布局
		frame.setLayout(new BorderLayout());

		// 添加组件
		frame.getContentPane().add(new JButton("north"), BorderLayout.NORTH);
		frame.getContentPane().add(new JButton("sourth"), BorderLayout.SOUTH);
		frame.getContentPane().add(new JButton("centure"), BorderLayout.CENTER);
		frame.getContentPane().add(new JLabel("east"), BorderLayout.EAST);
		frame.getContentPane().add(new JLabel("west"), BorderLayout.WEST);

		frame.setVisible(true);
	}

	public static void Function1_gridLayout() {
		JFrame frame = new JFrame("Grid layout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocation(300, 130);

		// 布局
		frame.setLayout(new GridLayout(5, 4));

		final int NUM = 20;
		JButton buttons[] = new JButton[NUM];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(Integer.toString(i + 1));
			frame.getContentPane().add(buttons[i]);
		}

		frame.setVisible(true);
	}

	// GridBagLayout******
	public static void Function1_GridBagLayout() {
		JFrame frame = new JFrame("Function1_GridBagLayout ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocation(300, 130);

		frame.setLayout(new GridBagLayout());

		final int NUM = 20;
		JButton buttons[] = new JButton[NUM];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(Integer.toString(i + 1));
			frame.getContentPane().add(buttons[i]);
		}

		frame.setVisible(true);
	}

	public static void ActionDeom() {
		JFrame frame = new JFrame();
		frame.setSize(800, 500);
		frame.setLocation(300, 130);
		frame.setLayout(new FlowLayout());

		JButton but = new JButton();
		but.setSize(50, 30);
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
				System.out.println(e.getSource());
				System.out.println(e);
			}

		};
		but.addActionListener(action);
		frame.getContentPane().add(but);
		frame.setVisible(true);
	}

	public static void Calculator() {
		String strNames[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", ".", "0", "=", "+" };
		Font font = new Font("courier new", Font.ITALIC, 18);
		
		JFrame frame = new JFrame();
		frame.setSize(800/3, 500/2);
		frame.setLocation(300, 130);

		JPanel panHead = new JPanel();
		JTextField display = new JTextField();
		
		MyActionListener.display =display;
		
		JButton reset = new JButton("CE");
		reset.addActionListener(new MyActionListener());
		panHead.setLayout(new BorderLayout());
		panHead.add(display, BorderLayout.CENTER);
		panHead.add(reset, BorderLayout.EAST);
		display.setFont(font);
		reset.setFont(font);

		JPanel panBody = new JPanel();
		panBody.setLayout(new GridLayout(4, 4));
		JButton[] buttons = new JButton[16];
		for (int i = 0; i < strNames.length; i++) {
			buttons[i] = new JButton(strNames[i]);
//			buttons[i].setText(strNames[i]);
			buttons[i].setFont(font);
			panBody.add(buttons[i]);
		}
		
		frame.setLayout(new BorderLayout());
		frame.add(panHead,BorderLayout.NORTH);
		frame.add(panBody,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//事件监听
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addActionListener(new MyActionListener());
		}

		frame.setVisible(true);
	}
	
	public static class MyActionListener implements ActionListener{
		public static JTextField display=null;
		private static boolean isFirstDigit =true;
		private static double result =0.0;
		private static String operator ="=";

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd =e.getActionCommand();
			if (cmd.equals("CE")) {
				//清零
				Clear();
			}
			else if("0123456789.".indexOf(cmd)>0){
				//输入数字
				if (operator.equals("=")&& isFirstDigit) {
					Clear();
				}
				handleNumber(cmd);	
				
			}
			else if (cmd.equals("=")) {
				HandleOp(operator);
				display.setText(Double.toString(result));
				operator="=";
			}
			else {
				HandleOp(operator);
				operator=cmd;
			}
		}
		
		public static void Clear() {
			display.setText("0");
			operator = "=";
			isFirstDigit=true;
		}
		
	    public static void handleNumber(String key) {
	        if (isFirstDigit)
	            display.setText(key);
	        else if (!key.equals("."))
	            display.setText(display.getText() + key);
	        else if (display.getText().indexOf(".") < 0)
	            display.setText(display.getText() + ".");
	        
	        isFirstDigit = false;
	    }
	    
	    public static void HandleOp(String OP) {
	    	double temp= Double.valueOf(display.getText());

	    	switch (OP) {
			case "+":result+=temp;  break;
			case "-":result-=temp;  break;
			case "*":result*=temp;  break;
			case "/":result/=temp;  break;
			case "=":result =temp;
			}
	    	display.setText(Double.toString(result));
	    	isFirstDigit =true;
	    }
	}

}



