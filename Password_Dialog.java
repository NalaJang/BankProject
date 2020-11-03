package BankMenuGUI;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//"지우기" 버튼 활성화 // '비밀번호가 맞지않습니다' 추가하기
public class Password_Dialog extends Frame implements ActionListener, KeyListener{
	Panel p1, p2;
	Dialog dialog1, dialog2;
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
	Button d1;  //------> 다이어로그 버튼
	Label la1;
	Label la[] = new Label[10];
	TextField tf1, tf2, tf3, tf4, tf5, tf6;
	int cntt;

	public Password_Dialog() {
		p1 = new Panel();
		p2 = new Panel();
		dialog1 = new Dialog(this);
		dialog2 = new Dialog(this);
		b1 = new Button("1");
		b2 = new Button("2");
		b3 = new Button("3");
		b4 = new Button("4");
		b5 = new Button("5");
		b6 = new Button("6");
		b7 = new Button("7");
		b8 = new Button("8");
		b9 = new Button("9");
		b10 = new Button("0");
		b11 = new Button("지우기");
		la1 = new Label("비밀번호 ", Label.CENTER);
		for (int i = 0; i < la.length; i++) {
			la[i] = new Label();
			la[6] = new Label("보안패드",Label.CENTER);
		}
		tf1 = new TextField();
		tf2 = new TextField();
		tf3 = new TextField();
		tf4 = new TextField();
		tf5 = new TextField();
		tf6 = new TextField();

		p1.setLayout(new GridLayout(2, 6));
		p1.add(la[0]);
		p1.add(la[1]);
		p1.add(la[2]);
		p1.add(la[3]);
		p1.add(la[4]);
		p1.add(la[5]);
		p1.add(tf1);
		p1.add(tf2);
		p1.add(tf3);
		p1.add(tf4);
		p1.add(tf5);
		p1.add(tf6);
		
		p2.setLayout(new GridLayout(4,3));
		p2.add(b1);   p2.add(b2);   p2.add(b3);
		p2.add(b4);   p2.add(b5);   p2.add(b6);
		p2.add(b7);   p2.add(b8);   p2.add(b9);
		p2.add(b11);  p2.add(b10);  p2.add(la[6]);

		setLayout(new GridLayout(3, 1));
		add(la1);
		add(p1);
		add(p2);

		setSize(400, 500);
		setVisible(true);
		setTitle("비밀번호");
		setLocation(750, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		dialog1.add(new Label("송금되었습니다.", Label.CENTER));
		dialog1.add(d1 = new Button("확인"));
		dialog1.setLayout(new GridLayout(2, 1));
		dialog1.setSize(200, 100);
		dialog1.setLocation(700, 200);

		dialog2.add(new Label("비밀번호가 맞지 않습니다.", Label.CENTER));  //추가하기
		dialog2.add(new Button("확인"));
		dialog2.setLayout(new GridLayout(2, 1));
		dialog2.setSize(200, 100);
		dialog2.setLocation(700, 200);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		d1.addActionListener(this);
		b11.addActionListener(new ActionListener() {  //지우기
			public void actionPerformed(ActionEvent e) {
				if(tf5.getText().length() !=0) {
					tf5.setText(tf5.getText().substring(0,tf5.getText().length()-1));
					tf5.requestFocus();
				}else if(tf4.getText().length() !=0) {					
					tf4.setText(tf4.getText().substring(0,tf4.getText().length()-1));
					tf4.requestFocus();
				}else if(tf3.getText().length() !=0) {
					tf3.setText(tf3.getText().substring(0,tf3.getText().length()-1));
					tf3.requestFocus();
				}else if(tf2.getText().length() !=0) {
					tf2.setText(tf2.getText().substring(0,tf2.getText().length()-1));
					tf2.requestFocus();
				}else if(tf1.getText().length() !=0) {
					tf1.setText(tf1.getText().substring(0,tf1.getText().length()-1));
					tf1.requestFocus();
				}
				/*if(cntt > 6) { cntt = 0;}
				switch(cntt++) {
				case 1 :
					tf5.setText(tf5.getText().substring(0,tf5.getText().length()-1));
					tf5.requestFocus();
					break;
				case 2 : 
					tf4.setText(tf4.getText().substring(0,tf4.getText().length()-1));
					tf4.requestFocus();
					break;
				case 3 :
					tf3.setText(tf3.getText().substring(0,tf3.getText().length()-1));
					tf3.requestFocus();
					break;
				case 4 :
					tf2.setText(tf2.getText().substring(0,tf2.getText().length()-1));
					tf2.requestFocus();
					break;
				case 5 :
					tf1.setText(tf1.getText().substring(0,tf1.getText().length()-1));
					tf1.requestFocus();
					break;
				}*/
			}
		});
	}

	int cnt;

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Button b = (Button) obj;
		if (cnt > 6) {
			cnt = 0;
		}
		switch (cnt++) {
		case 0:
			tf1.setText(((Button) e.getSource()).getLabel());
			tf1.setEchoChar('★');
			break;
		case 1:
			tf2.setText(((Button) e.getSource()).getLabel());
			tf2.setEchoChar('★');
			break;
		case 2:
			tf3.setText(((Button) e.getSource()).getLabel());
			tf3.setEchoChar('★');
			break;
		case 3:
			tf4.setText(((Button) e.getSource()).getLabel());
			tf4.setEchoChar('★');
			break;
		case 4:
			tf5.setText(((Button) e.getSource()).getLabel());
			tf5.setEchoChar('★');
			break;
		case 5:
			tf6.setText(((Button) e.getSource()).getLabel());
			tf6.setEchoChar('★');
		default:			
			if(Login.accountPw ==
					Integer.parseInt(""+tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText()+tf6.getText())) {
				System.out.println(""+tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText()+tf6.getText());
				System.out.println("맞음");
				dialog1.setVisible(true);
			}else{
				System.out.println("틀림");
				System.out.println(""+tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText()+tf6.getText());
			}			
			break;
		}
		if (obj == d1) {
			Menu menu = new Menu();
			dialog1.setVisible(false);
			setVisible(false);
		}                                         //switch 종료
		if(obj == b11) {	//지우기
			
		}
		/*
		for (int i = 0; i < b.getLabel().length(); i++) {
			if (i < 10) {
				tf1.setText(b.getLabel());
				tf2.setText(b.getLabel());
				tf3.setText(b.getLabel());
				tf4.setText(b.getLabel());
				tf5.setText(b.getLabel());
				tf6.setText(b.getLabel());
			}
			if (obj == b11) {
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			}
		}
		 */
	}

	public static void main(String[] args) {
		new Password_Dialog();
	}	
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}
