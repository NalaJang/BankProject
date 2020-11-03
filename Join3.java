package BankMenuGUI;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import DataBase.DBAction;

//id, pw �������� �������� 4�� �̻�
// id �ߺ�Ȯ��
public class Join3 extends Frame implements ActionListener, MouseListener, KeyListener {
	Button b1, b2, b3, b4, b5, b6;
	Choice cho_mail;
	Dialog dialog1, dialog2;
	Font font;
	Label la1, la2, la3, la4;
	Label la_name, la_id, la_accountpw, la_pw1, la_pw2, la_phone, la_mail, la_birth;
	String regExp[] = new String[7];
	TextField tf_id, tf_pw1, tf_pw2, tf_name, tf_phone, tf_num, tf_mail, tf_mailaddress, tf_birth;
	TextField tf_accountpw;
	Random random = new Random();
	
	public Join3() {
		b1 = new Button("<");
		b2 = new Button("��ȣ����");
		b3 = new Button("����");
		b4 = new Button("Ȯ��");
		b5 = new Button("�ߺ�Ȯ��");
		b6 = new Button("�ٽùޱ�");
		cho_mail = new Choice(); // ------------->�����ּҼ���
		cho_mail.add("�����Է�");
		cho_mail.add("naver.com");
		cho_mail.add("daum.net");
		dialog1 = new Dialog(this);
		dialog2 = new Dialog(this, "���º�й�ȣ����");
		font = new Font("�������", Font.PLAIN, 13);
		la1 = new Label("������ ���ϵ帳�ϴ�.", Label.CENTER);
		la2 = new Label("@");
		la3 = new Label();
		la4 = new Label();
		int su[] = new int[5];
		for(int i =0; i<su.length; i++) {
			su[i] = (int)(Math.random()*10)+1;
			su[i] = random.nextInt(10);
		}
		la4.setText(su[0]+""+su[1]+su[2]+su[3]+su[4]+"");
		la_name = new Label("�̸�");
		la_id = new Label("���̵�");
		la_accountpw = new Label("���º�й�ȣ");
		la_pw1 = new Label("��й�ȣ");
		la_pw2 = new Label("��й�ȣ Ȯ��");
		la_phone = new Label("�޴�����ȣ( - ����)");
		la_mail = new Label("�̸���");
		la_birth = new Label("������� 6�ڸ�");
		tf_id = new TextField();
		tf_accountpw = new TextField();
		tf_accountpw.setEchoChar('��');
		tf_pw1 = new TextField();
		tf_pw1.setEchoChar('��');
		tf_pw2 = new TextField();
		tf_pw2.setEchoChar('��');
		tf_name = new TextField();
		tf_phone = new TextField();
		tf_birth = new TextField();
		tf_num = new TextField();
		tf_mail = new TextField();
		tf_mailaddress = new TextField();

		add(b1);
		add(b2);
		add(b3);
		add(b5);
		add(b6);
		add(cho_mail);
		add(la2);
		add(la4);
		add(la_name);
		add(la_id);
		add(la_accountpw);
		add(la_pw1);
		add(la_pw2);
		add(la_phone);
		add(la_mail);
		add(la_birth);
		add(tf_id);
		add(tf_accountpw);
		add(tf_pw1);
		add(tf_pw2);
		add(tf_name);
		add(tf_phone);
		add(tf_birth);
		add(tf_num);
		add(tf_mail);
		add(tf_mailaddress);
		add(la3);

		// ---------------------------------------------------------------��ġ
		b1.setBounds(40, 40, 40, 30);

		la_name.setBounds(40, 100, 110, 30);
		tf_name.setBounds(210, 100, 110, 30);
		la_id.setBounds(40, 140, 110, 30);
		tf_id.setBounds(210, 140, 110, 30);
		b5.setBounds(320, 140, 110, 30);
		la_accountpw.setBounds(40, 180, 110, 30);
		tf_accountpw.setBounds(210, 180, 110, 30);		
		la_pw1.setBounds(40, 220, 110, 30);
		tf_pw1.setBounds(210, 220, 110, 30);
		
		la_pw2.setBounds(40, 260, 110, 30);
		tf_pw2.setBounds(210, 260, 110, 30);
		la_birth.setBounds(40, 300, 110, 30);
		tf_birth.setBounds(210, 300, 110, 30);
		la_phone.setBounds(40, 340, 110, 30);
		tf_phone.setBounds(210, 340, 110, 30);
		tf_num.setBounds(40, 380, 110, 30);
		la4.setBounds(160, 380, 110, 30);
		b2.setBounds(200, 380, 105, 30);
		b6.setBounds(310, 380, 110, 30);
		tf_mail.setBounds(40, 420, 110, 30);
		la2.setBounds(160, 420, 20, 30);
		tf_mailaddress.setBounds(190, 420, 110, 30);
		cho_mail.setBounds(310, 420, 110, 30);

		b3.setBounds(150, 460, 150, 40);

		// ---------------------------------------------------------------�����Ӽ���
		setSize(450, 550);
		setVisible(true);
		setLocation(750, 200);
		setTitle("ȸ������");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// ---------------------------------------------------------------��Ʈ����
		b1.setBackground(new Color(0, 128, 255));
		b3.setBackground(new Color(0, 128, 255));
		b1.setForeground(Color.WHITE);
		b3.setForeground(Color.WHITE);
		b1.setFont(new Font("�������", Font.BOLD, 15));
		b3.setFont(new Font("�������", Font.BOLD, 15));
		tf_name.setFont(font);
		tf_id.setFont(font);
		tf_pw1.setFont(font);
		tf_pw2.setFont(font);
		tf_birth.setFont(font);
		tf_phone.setFont(font);
		tf_num.setFont(font);
		tf_mail.setFont(font);
		tf_mailaddress.setFont(font);
		// ---------------------------------------------------------------���̾�α�
		dialog1.setTitle("Ȯ��");
		dialog1.setSize(300, 150);
		dialog1.setLocation(750, 300);
		dialog1.add(la1);
		dialog1.add(b4, "South");
		
		// ---------------------------------------------------------------�̺�Ʈ
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		tf_id.addMouseListener(this);
		tf_pw1.addMouseListener(this);
		tf_pw2.addMouseListener(this);
		tf_name.addMouseListener(this);
		tf_phone.addMouseListener(this);
		tf_num.addMouseListener(this);
		tf_mail.addMouseListener(this);		
		
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tf_id.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_id.setText("");
					return;
				} else if (tf_accountpw.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "���º�й�ȣ�� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_accountpw.setText("");
					return;
				} else if (tf_pw1.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_pw1.setText("");
					return;
				} else if (!tf_pw1.getText().trim().equals(tf_pw2.getText().trim())) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "", JOptionPane.WARNING_MESSAGE);
					tf_pw2.setText("");
					return;
				} else if (tf_name.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_name.setText("");
					return;
				} else if (tf_phone.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�޴�����ȣ�� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_phone.setText("");
					return;
				} else if (tf_num.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "������ȣ�� �ٽ� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_num.setText("");
					return;
				} else if (tf_mail.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸����ּҸ� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_mail.setText("");
					return;
				} else if (tf_mailaddress.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "�̸����ּҸ� �Է��� �ּ���.", "", JOptionPane.WARNING_MESSAGE);
					tf_mailaddress.setText("");
					return;
				} else if(tf_num.getText().trim().length() == 0 || !tf_num.getText().equals(la4.getText().trim())) {
					JOptionPane.showMessageDialog(null, "��ȣ�� �������ּ���.","���� �ʿ�", JOptionPane.WARNING_MESSAGE);
				} else if (check()) { // --------------------------------------------------------------- ȸ������ DB�� ����
						
					String driver = "com.mysql.cj.jdbc.Driver";
						String url = "jdbc:mysql://localhost:3306/app?characterEncoding=UTF-8&serverTimezone=UTC";
						Connection conn = null;
						Statement stmt = null;
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url, "root", "java");
						stmt = conn.createStatement();
						
						String name = tf_name.getText();
						String id = tf_id.getText();
						int account_pw = Integer.valueOf(tf_accountpw.getText());
						String pw = tf_pw1.getText();						
						int phone = Integer.valueOf(tf_phone.getText());
						String mail = tf_mail.getText();
						String mailAddress ="@" + tf_mailaddress.getText();
						int birth = Integer.valueOf(tf_birth.getText());
						String con = "����";
						String sql = "insert into CUSTOMERS1 values('" + name + "', '" + id + "',"
								+ "" + account_pw + " , '" + pw + "'," + phone + ",'" + mail + "',"
										+ "'" + mailAddress + "', "+birth+", default, '"+con+"')";
						
						stmt.executeUpdate(sql);
						
						dialog1.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "���̵� �ߺ��� Ȯ�����ּ���", "�ý��� ����", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
							if (stmt != null)
								stmt.close();
							if (conn != null)
								conn.close();
						} catch (Exception ex) {
						}
					}
					try {// --------------------------------------------------------------- ���¹�ȣ, �ܾ��� ���� ���̺�
						Class.forName(driver);
						conn = DriverManager.getConnection(url, "root", "java");
						stmt = conn.createStatement();
						String name = tf_name.getText();
						String id = tf_id.getText();
						String account = getAccount();
						int deposit =0;
						int withdraw = 0;
						String who = null;
						int cost =0;
						int balance = 0;
						String memo = null;
						String sql2 = "INSERT INTO transaction VALUES ('"+name +"', '"+id+"', '"+account+"',default, '"+ deposit+"', '"+ withdraw +"', '"+who+"', '"+cost+"', '"+balance+"', '"+memo+"')";
						stmt.executeUpdate(sql2);
					}catch(Exception ex) {
						ex.printStackTrace();
					}finally {
						try {
							if (stmt != null)
								stmt.close();
							if (conn != null)
								conn.close();
						} catch (Exception ex) {
						}
					}
				}
			}
		});
		b4.addKeyListener(new KeyAdapter() {  //���ԿϷ� Ȯ�� ��ư�� ����Ű ����
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					Login log = new Login();
					dispose();
				}
			}
		});

		cho_mail.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (cho_mail.getSelectedItem().equals("�����Է�")) {
					tf_mailaddress.setEditable(true);
					tf_mailaddress.setText("");
				} else {
					tf_mailaddress.setText(cho_mail.getSelectedItem().toString());
					tf_mailaddress.setEditable(false);
				}
			}
		});		
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1) { // �ڷΰ���
			Login login = new Login();
			setVisible(false);
			return;
		}
		if (obj == b2) {  // ��ȣ����
			if (!la4.getText().trim().equals(tf_num.getText().trim())) {
				JOptionPane.showMessageDialog(null, "������ȣ�� �ٽ� �Է����ּ���", "", JOptionPane.WARNING_MESSAGE);
				tf_num.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (obj == b4) {  //���Թ�ư �� -> Ȯ��
			setVisible(false);
			dialog1.setVisible(false);
			Login login = new Login();
		}
		if (obj == b5) { // �ߺ�Ȯ��
			unique();
		}
		if (obj == b6) { // ������ȣ�ٽùޱ�
			int su[] = new int[5];
			for(int i =0; i<su.length; i++) {
				su[i] = (int)(Math.random()*10)+1;
				su[i] = random.nextInt(10);
			}
			la4.setText(su[0]+""+su[1]+su[2]+su[3]+su[4]+"");
		}
	}
	// ---------------------------------------------------------------����
	public boolean check() {
		for (int i = 0; i < regExp.length; i++) {
			regExp[i] = new String();
			regExp[0] = new String("\\w+\\d"); // -- id
			regExp[1] = new String("\\d{6}");  // -- account password
			regExp[2] = new String("\\w+\\d"); // -- password
			regExp[3] = new String("\\d{6}");  // -- year
			regExp[4] = new String("(010)\\d{7,8}"); // -- phone
		}
		if (!Pattern.matches(regExp[0], tf_id.getText())) {
			JOptionPane.showMessageDialog(null, "���̵� ����,���� �������� 4�ڸ� �̻��Է����ּ���", "", JOptionPane.WARNING_MESSAGE);
			tf_id.setText("");
			return false;
		}if (!Pattern.matches(regExp[1], tf_accountpw.getText())) {
			JOptionPane.showMessageDialog(null, "���º�й�ȣ�� ���� 6�ڸ��Դϴ�", "", JOptionPane.WARNING_MESSAGE);
			tf_accountpw.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[2], tf_pw1.getText())) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ����,���� �������� 4�ڸ� �̻��Է����ּ���", "", JOptionPane.WARNING_MESSAGE);
			tf_pw1.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[3], tf_birth.getText())) {
			JOptionPane.showMessageDialog(null, "��������� �ٽ� �Է����ּ���.", "", JOptionPane.WARNING_MESSAGE);
			tf_birth.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[4], tf_phone.getText())) {
			JOptionPane.showMessageDialog(null, "�޴�����ȣ�� �ùٸ��� �ʽ��ϴ�.", "", JOptionPane.WARNING_MESSAGE);
			tf_phone.setText("");
			return false;
		}
		return true;
	}
			
	// ---------------------------------------------------------------���̵� �ߺ�Ȯ��
	public void unique() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/app?characterEncoding=UTF-8&serverTimezone=UTC";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String id = tf_id.getText();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM CUSTOMERS1";
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cloCnt = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= cloCnt; i++) {	//�����ϱ�
				}	
				if(id.equals(rs.getString("id")) || id.trim().length() == 0){
					throw new Exception();
				}
			}
			JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.","",JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.","",JOptionPane.WARNING_MESSAGE);
		}finally {			
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {				
			}
		}
		
	}
	// ---------------------------------------------------------------���¹�ȣ ����
	public static String getAccount() {
		int su[] = new int[8];
		for(int i =0; i<su.length; i++) {
			su[i] = (int)(Math.random()*9)+1;
		}
		String account = "110-" + su[0] + su[1]+  + su[2] +"-"+ su[3] + su[4] + su[5];
		
		return account;
	}
	
	// ---------------------------------------------------------------main method
	public static void main(String[] args) {
		new Join3();
	}

	// ---------------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
