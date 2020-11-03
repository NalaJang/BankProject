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
		b1 = new Button("��");
		b2 = new Button("�ƴϿ�");
		b3 = new Button("ù �������� ���ư���");
		la1 = new Label(""+Login.idid, Label.CENTER);
		la2 = new Label("�α׾ƿ� �Ͻðڽ��ϱ�?", Label.CENTER);
		la3 = new Label("�α׾ƿ� ���� !", Label.CENTER);
		for (int i = 0; i < la.length; i++) {
			la[i] = new Label();
		}
		dialog = new Dialog(this);

		p1.setLayout(new GridLayout(3, 1)); // ��Ʈ
		p1.add(la[0]);
		p1.add(la1);
		p1.add(la2);

		p2.setLayout(new GridLayout(2,2));  // ��, �ƴϿ� ��ư
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

		setTitle("�α׾ƿ�");
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
		dialog.setTitle("�α׾ƿ� �Ϸ�");
		dialog.setLocation(750, 200);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1) { // b2"��" Ŭ����
			update();
			setVisible(false);						
			dialog.setVisible(true);
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		} else if (obj == b2) { // b1"�ƴϿ�" Ŭ����
			setVisible(false);
			
			Menu menu = new Menu();
		} else if (obj == b3) { // "ù �������� ���ư���" Ŭ����
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
			String sql  = "update customers1 set TIME=now() where ID='" + id + "'";  //�α׾ƿ� �ð� �Է� (TIME �� �빮�ڷ� �Է�)
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
