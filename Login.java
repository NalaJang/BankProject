package BankMenuGUI;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

public class Login extends Frame implements ActionListener, MouseListener {
	Image img;
	Button b1;
	Checkbox ch;
	Font font;
	Label forgot, join, blank;
	Label la[] = new Label[30];
	Panel p1, p2;	
	static TextField id, password;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSet rs2;
	
	static String aa;
	static String userAccount;
	static int accountPw;
	static int userBalance;
	static int userCost;
	static String idid;
	static String pwpw;
	static int userPhone;
	static String userMail;
	static String userMailAddress;	
	static String userMemo;
	
	public Login() {
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("gooogle1.png")); 
		b1 = new Button("Log In");
		blank = new Label("---------------------------------",Label.CENTER);
		ch = new Checkbox("���̵� ����");
		forgot = new Label("Forgot your PASSWORD?",Label.CENTER);
		font = new Font("�������", Font.BOLD, 13);
		join = new Label("Join",Label.CENTER);
		id = new TextField("ID", 20);
		p1 = new Panel();
		p2 = new Panel();		
		password = new TextField("PASSWORD", 20);
		for (int i = 0; i < la.length; i++) {
			la[i] = new Label("");
		}
		// ---------------------------------------------------------------��ġ
		p1.setLayout(new GridLayout(5,3));   //���̵� , ��й�ȣ, ���̵����� 
		p1.add(la[0]);  p1.add(la[1]);    p1.add(la[2]);
		p1.add(la[3]);  p1.add(id);       p1.add(la[4]);
		p1.add(la[5]);  p1.add(password); p1.add(ch);
		
		p1.add(la[6]);  p1.add(la[7]);    p1.add(la[8]);  // �α��ι�ư
		p1.add(la[9]);  p1.add(b1);       p1.add(la[10]);
				 
		p2.setLayout(new GridLayout(4,1));   //��й�ȣ ã��, ȸ������
		p2.add(la[20]);
		p2.add(forgot);
		p2.add(blank);
		p2.add(join);
		
		setLayout(new GridLayout(3, 1));
		add(new ImageCanvas(),"North");
		add(p1);
		add(p2);
		pack();
		// --------------------------------------------------------------- �����Ӽ���
		setLocation(750, 200);
		setSize(450,550);
		setVisible(true);
		setTitle("�α���");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// ---------------------------------------------------------------��Ʈ����
		b1.setBackground(new Color(0, 128, 255));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("�������", Font.BOLD, 15));
		
		// �̺�Ʈ ����� �ؾ� ��� *****		
		// ---------------------------------------------------------------�̺�Ʈ
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id.getText().length() == 0 || id.getText().equals("ID")) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
				} else if (password.getText().length() == 0 || password.getText().equals("PASSWORD")) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
				} else if (id.getText().equals("manager1111") && password.getText().equals("manager00")) {
					JOptionPane.showMessageDialog(null, "������ ��� �Դϴ�.","", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					manager2 manager = new manager2();
					manager3 manager2 = new manager3();
					Menu menu = new Menu();
					
				}
				else {
					select();
				}
			}
		});
		id.addMouseListener(this);
		password.addMouseListener(this);
		join.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj == id) {				//���̵�, ��й�ȣ �Է½�
			id.setText("");
			id.setFont(font);
		}else if(obj == password) { //���̵�, ��й�ȣ �Է½�
			password.setText("");
			password.setEchoChar('��');
		}else if(obj == forgot) {
			
		}else if(obj == join) {	   //ȸ������
			setVisible(false);
			Join3 join = new Join3();
		}		
	}
	// ---------------------------------------------------------------ȸ������ Ȯ��
	public void select() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/app?characterEncoding=UTF-8&serverTimezone=UTC";
		String userId = id.getText();
		String userPw = password.getText();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			System.out.println("�����ͺ��̽� ���� ���� !");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM CUSTOMERS1 WHERE ID= '" + userId + "' && PW= '" + userPw + "'";  //�ش� ȸ�� ���� ��������
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				aa = rs.getString("name");
				idid = rs.getString("id");
				accountPw = rs.getInt("account_pw");				
				pwpw = rs.getString("pw");
				userPhone = rs.getInt("phone");
				userMail = rs.getString("mail");
				userMailAddress = rs.getString("mailAddress");
				if(id.getText().equals(idid) && password.getText().equals(pwpw)) {
					setVisible(false);
					Menu menu = new Menu();
				}				
			}
			else {
				JOptionPane.showMessageDialog(null, "ȸ�������� �ùٸ��� �ʽ��ϴ�.", "�ٽ� �����غ�����", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			System.out.println("�����ͺ��̽� ���� ����");
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"�ٽ� �õ��� �ּ���", "�ý��� ����", JOptionPane.WARNING_MESSAGE);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
		try {			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			stmt = conn.createStatement();
			String sql = "SELECT * FROM TRANSACTION WHERE ID= '"+userId+"'"; //�ش� ȸ�� ���� ���� ��������
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				aa = rs.getString("name");
				idid = rs.getString("id");
				userAccount = rs.getString("account");
				userCost = rs.getInt("cost");
				userBalance = rs.getInt("balance");
				userMemo = rs.getString("memo");
			
			}
		}catch(Exception e) {			
			e.printStackTrace();
		}finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
	}
	
	// ---------------------------------------------------------------main method
	public static void main(String[] args) {
		new Login();

	}
	// ---------------------------------------------------------------Image class
	class ImageCanvas extends Canvas{		
		public void paint(Graphics g) {
			g.drawImage(img, 75,50, this);
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	
	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}