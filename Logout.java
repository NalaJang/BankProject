package BankMenuGUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logout extends Frame implements ActionListener {
	Panel p1, p2, p3;
	Button b1, b2, b3;
	Label la1, la2, la3;
	Label la[] = new Label[10];
	Dialog dialog;

	Logout() {
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		b1 = new Button("네");
		b2 = new Button("아니오");
		b3 = new Button("첫 페이지로 돌아가기");
		la1 = new Label(""+Login.idid, Label.CENTER);
		la2 = new Label("로그아웃 하시겠습니까?", Label.CENTER);
		la3 = new Label("로그아웃 성공 !", Label.CENTER);
		for (int i = 0; i < la.length; i++) {
			la[i] = new Label();
		}
		dialog = new Dialog(this);

		p1.setLayout(new GridLayout(3, 1)); // 멘트
		p1.add(la[0]);
		p1.add(la1);
		p1.add(la2);

		p2.setLayout(new GridLayout(2,2));  // 예, 아니오 버튼
		p2.add(la[1]); p2.add(la[2]);
		p2.add(b1); p2.add(b2);

		p3.setLayout(new GridLayout(2, 1)); // p1 + p2
		p3.add(p1);
		p3.add(p2);

		add(p3, "Center");
		add(la[3], "North");
		add(la[4], "South");
		add(la[5], "West");
		add(la[6], "East");

		setTitle("로그아웃");
		setSize(400, 500);
		setVisible(true);
		setLocation(750, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		dialog.setLayout(new BorderLayout());
		dialog.add(la3);
		dialog.add(b3, "South");

		dialog.setSize(300, 150);
		dialog.setTitle("로그아웃 완료");
		dialog.setLocation(750, 200);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1) { // b2"예" 클릭시
			update();
			setVisible(false);						
			dialog.setVisible(true);
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		} else if (obj == b2) { // b1"아니오" 클릭시
			setVisible(false);
			
			Menu menu = new Menu();
		} else if (obj == b3) { // "첫 페이지로 돌아가기" 클릭시
			Login login = new Login();
			dialog.setVisible(false);
		}
	}

	public void update() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/app?characterEncoding=UTF-8&serverTimezone=UTC";
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			stmt = conn.createStatement();
			String id = Login.idid;
			String sql  = "update customers1 set TIME=now() where ID='" + id + "'";  //로그아웃 시간 입력 (TIME 은 대문자로 입력)
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Logout();
	}
}
