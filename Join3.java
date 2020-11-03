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

//id, pw 영문숫자 조합으로 4자 이상
// id 중복확인
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
		b2 = new Button("번호인증");
		b3 = new Button("가입");
		b4 = new Button("확인");
		b5 = new Button("중복확인");
		b6 = new Button("다시받기");
		cho_mail = new Choice(); // ------------->메일주소선택
		cho_mail.add("직접입력");
		cho_mail.add("naver.com");
		cho_mail.add("daum.net");
		dialog1 = new Dialog(this);
		dialog2 = new Dialog(this, "계좌비밀번호설정");
		font = new Font("맑은고딕", Font.PLAIN, 13);
		la1 = new Label("가입을 축하드립니다.", Label.CENTER);
		la2 = new Label("@");
		la3 = new Label();
		la4 = new Label();
		int su[] = new int[5];
		for(int i =0; i<su.length; i++) {
			su[i] = (int)(Math.random()*10)+1;
			su[i] = random.nextInt(10);
		}
		la4.setText(su[0]+""+su[1]+su[2]+su[3]+su[4]+"");
		la_name = new Label("이름");
		la_id = new Label("아이디");
		la_accountpw = new Label("계좌비밀번호");
		la_pw1 = new Label("비밀번호");
		la_pw2 = new Label("비밀번호 확인");
		la_phone = new Label("휴대폰번호( - 제외)");
		la_mail = new Label("이메일");
		la_birth = new Label("생년월일 6자리");
		tf_id = new TextField();
		tf_accountpw = new TextField();
		tf_accountpw.setEchoChar('●');
		tf_pw1 = new TextField();
		tf_pw1.setEchoChar('●');
		tf_pw2 = new TextField();
		tf_pw2.setEchoChar('●');
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

		// ---------------------------------------------------------------배치
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

		// ---------------------------------------------------------------프레임설정
		setSize(450, 550);
		setVisible(true);
		setLocation(750, 200);
		setTitle("회원가입");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// ---------------------------------------------------------------폰트설정
		b1.setBackground(new Color(0, 128, 255));
		b3.setBackground(new Color(0, 128, 255));
		b1.setForeground(Color.WHITE);
		b3.setForeground(Color.WHITE);
		b1.setFont(new Font("맑은고딕", Font.BOLD, 15));
		b3.setFont(new Font("맑은고딕", Font.BOLD, 15));
		tf_name.setFont(font);
		tf_id.setFont(font);
		tf_pw1.setFont(font);
		tf_pw2.setFont(font);
		tf_birth.setFont(font);
		tf_phone.setFont(font);
		tf_num.setFont(font);
		tf_mail.setFont(font);
		tf_mailaddress.setFont(font);
		// ---------------------------------------------------------------다이어로그
		dialog1.setTitle("확인");
		dialog1.setSize(300, 150);
		dialog1.setLocation(750, 300);
		dialog1.add(la1);
		dialog1.add(b4, "South");
		
		// ---------------------------------------------------------------이벤트
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
					JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_id.setText("");
					return;
				} else if (tf_accountpw.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "계좌비밀번호를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_accountpw.setText("");
					return;
				} else if (tf_pw1.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_pw1.setText("");
					return;
				} else if (!tf_pw1.getText().trim().equals(tf_pw2.getText().trim())) {
					JOptionPane.showMessageDialog(null, "비밀번호가 같지 않습니다.", "", JOptionPane.WARNING_MESSAGE);
					tf_pw2.setText("");
					return;
				} else if (tf_name.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_name.setText("");
					return;
				} else if (tf_phone.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "휴대폰번호를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_phone.setText("");
					return;
				} else if (tf_num.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "인증번호를 다시 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_num.setText("");
					return;
				} else if (tf_mail.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이메일주소를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_mail.setText("");
					return;
				} else if (tf_mailaddress.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "이메일주소를 입력해 주세요.", "", JOptionPane.WARNING_MESSAGE);
					tf_mailaddress.setText("");
					return;
				} else if(tf_num.getText().trim().length() == 0 || !tf_num.getText().equals(la4.getText().trim())) {
					JOptionPane.showMessageDialog(null, "번호를 인증해주세요.","인증 필요", JOptionPane.WARNING_MESSAGE);
				} else if (check()) { // --------------------------------------------------------------- 회원정보 DB에 저장
						
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
						String con = "가입";
						String sql = "insert into CUSTOMERS1 values('" + name + "', '" + id + "',"
								+ "" + account_pw + " , '" + pw + "'," + phone + ",'" + mail + "',"
										+ "'" + mailAddress + "', "+birth+", default, '"+con+"')";
						
						stmt.executeUpdate(sql);
						
						dialog1.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null, "아이디 중복을 확인해주세요", "시스템 오류", JOptionPane.WARNING_MESSAGE);
					} finally {
						try {
							if (stmt != null)
								stmt.close();
							if (conn != null)
								conn.close();
						} catch (Exception ex) {
						}
					}
					try {// --------------------------------------------------------------- 계좌번호, 잔액을 담을 테이블
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
		b4.addKeyListener(new KeyAdapter() {  //가입완료 확인 버튼에 엔터키 적용
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
				if (cho_mail.getSelectedItem().equals("직접입력")) {
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
		if (obj == b1) { // 뒤로가기
			Login login = new Login();
			setVisible(false);
			return;
		}
		if (obj == b2) {  // 번호인증
			if (!la4.getText().trim().equals(tf_num.getText().trim())) {
				JOptionPane.showMessageDialog(null, "인증번호를 다시 입력해주세요", "", JOptionPane.WARNING_MESSAGE);
				tf_num.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "인증되었습니다.", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (obj == b4) {  //가입버튼 후 -> 확인
			setVisible(false);
			dialog1.setVisible(false);
			Login login = new Login();
		}
		if (obj == b5) { // 중복확인
			unique();
		}
		if (obj == b6) { // 인증번호다시받기
			int su[] = new int[5];
			for(int i =0; i<su.length; i++) {
				su[i] = (int)(Math.random()*10)+1;
				su[i] = random.nextInt(10);
			}
			la4.setText(su[0]+""+su[1]+su[2]+su[3]+su[4]+"");
		}
	}
	// ---------------------------------------------------------------패턴
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
			JOptionPane.showMessageDialog(null, "아이디를 영문,숫자 조합으로 4자리 이상입력해주세요", "", JOptionPane.WARNING_MESSAGE);
			tf_id.setText("");
			return false;
		}if (!Pattern.matches(regExp[1], tf_accountpw.getText())) {
			JOptionPane.showMessageDialog(null, "계좌비밀번호는 숫자 6자리입니다", "", JOptionPane.WARNING_MESSAGE);
			tf_accountpw.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[2], tf_pw1.getText())) {
			JOptionPane.showMessageDialog(null, "비밀번호를 영문,숫자 조합으로 4자리 이상입력해주세요", "", JOptionPane.WARNING_MESSAGE);
			tf_pw1.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[3], tf_birth.getText())) {
			JOptionPane.showMessageDialog(null, "생년월일을 다시 입력해주세요.", "", JOptionPane.WARNING_MESSAGE);
			tf_birth.setText("");
			return false;
		}
		if (!Pattern.matches(regExp[4], tf_phone.getText())) {
			JOptionPane.showMessageDialog(null, "휴대폰번호가 올바르지 않습니다.", "", JOptionPane.WARNING_MESSAGE);
			tf_phone.setText("");
			return false;
		}
		return true;
	}
			
	// ---------------------------------------------------------------아이디 중복확인
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
				for (int i = 1; i <= cloCnt; i++) {	//수정하기
				}	
				if(id.equals(rs.getString("id")) || id.trim().length() == 0){
					throw new Exception();
				}
			}
			JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.","",JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "중복된 아이디입니다.","",JOptionPane.WARNING_MESSAGE);
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
	// ---------------------------------------------------------------계좌번호 생성
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
