package BankMenuGUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class manager3 extends Frame implements ActionListener{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/app?characterEncoding=UTF-8&serverTimezone=UTC";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	DefaultTableModel dtm;
	
	Button b_delete, b_search;
	Label la1, la2;
	Label la_id, la_account, la_balance, la_name;
	Panel p1, p2, p3;
	TextField tf_account, tf_balance, tf_id, tf_search, tf_name;
	
	manager3(){
		b_delete = new Button("����");
		b_delete.setFont(new Font("�������", Font.BOLD, 13));
		b_delete.setBackground(new Color(0, 128, 255));
		b_delete.setForeground(Color.WHITE);
		b_search = new Button("�˻�");
		la1 = new Label(); //����
		la2 = new Label(); //����
		la_id = new Label("���̵�");
		la_account = new Label("���¹�ȣ");
		la_balance = new Label("�ܾ�");
		la_name = new Label("�̸�");
		tf_account = new TextField(15);
		tf_balance = new TextField(15);
		tf_name = new TextField(10);
		tf_id = new TextField(10);
		tf_search = new TextField(30);	
		
		p1 = new Panel();
		p1.add(tf_search);
		p1.add(b_search);
		p1.add(la1);
		p1.add(la2);
		p1.add(b_delete);
		
		p2 = new Panel();
		p2.add(la_name);
		p2.add(tf_name);
		p2.add(la_id);
		p2.add(tf_id);
		p2.add(la_account);
		p2.add(tf_account);
		p2.add(la_balance);
		p2.add(tf_balance);
				
		p3 = new Panel(new GridLayout(2,1));
		p3.add(p1);
		p3.add(p2);
		
		JFrame accountFrame = new JFrame("ȸ�� ���� ���");
		accountFrame.setBounds(600, 250,800, 400);
		accountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //����â�� ���� !
		
		JScrollPane sp = new JScrollPane();  //��ġ
		accountFrame.add(sp, "Center");
		accountFrame.add(p3, "South");
		
		JTable selectTable = new JTable();
		selectTable.getTableHeader().setReorderingAllowed(false);  //���̺� �� ��ġ ����Ұ�
		sp.setViewportView(selectTable);
		
		Vector<String> accountList = new Vector<String>(
				Arrays.asList("�̸�", "���̵�", "���¹�ȣ", "����� �ð�", "who", "balance"));
		dtm = new DefaultTableModel(accountList, 0);
		
		selectTable.setModel(dtm);
		accountFrame.setVisible(true);
		
		selectTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Object obj = e.getSource();
				int row = selectTable.getSelectedRow();
				String name = (String)selectTable.getValueAt(row,0);
				String id = (String)selectTable.getValueAt(row, 1);
				String account = (String)selectTable.getValueAt(row, 2);
				String balance = selectTable.getValueAt(row, 5).toString(); //�� ������ �´� ���ڸ� �־��ش�.
				
				tf_name.setText(name);
				tf_id.setText(id);
				tf_account.setText(account);
				tf_balance.setText(balance);				
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		// --------------------------------------------------------------- DB ������ ��������
		// --------------------------------------------------------------- 
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			String sql = "SELECT * FROM TRANSACTION";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			dtm = (DefaultTableModel) selectTable.getModel();

			while (rs.next()) {
				String name = rs.getString("NAME");
				String id = rs.getString("ID");
				String account = rs.getString("ACCOUNT");
				String time = rs.getString("time");
				String who = rs.getString("WHO");
				String balance = rs.getString("BALANCE");

				Vector<String> row = new Vector<>();
				row.add(name);
				row.add(id);
				row.add(account);
				row.add(time);
				row.add(who);
				row.add(balance);
				dtm.addRow(row); //***** ����� �����Ͱ� ���.
			}
			selectTable.setModel(dtm);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		b_delete.addActionListener(this);
		b_search.addActionListener(this);
	}
	// --------------------------------------------------------------- ȸ������
	// --------------------------------------------------------------- ����(Delete)
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b_delete) {
			int s = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
			if (s == JOptionPane.YES_OPTION) {
				try {
					Class.forName(driver);
					conn = DriverManager.getConnection(url, "root", "java");
					stmt = conn.createStatement();
					String id = tf_id.getText();
					String sql = "DELETE FROM TRANSACTION WHERE ID = '" + id + "'";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "�ý��� ����");
				} finally {
					try {
						if (stmt != null)
							stmt.close();
						if (conn != null)
							conn.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

		}
		if(obj == b_search) {
			userSearch(dtm, tf_search.getText());
		}
	}
	// --------------------------------------------------------------- ȸ������
	// --------------------------------------------------------------- �˻�(search/ like �̿�)
	public void userSearch(DefaultTableModel dtm, String word) {
		ResultSetMetaData rsmd = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "root", "java");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM TRANSACTION WHERE ID LIKE '%"+ word +"%'");
			rsmd = rs.getMetaData();
			
			for(int i =0; i< dtm.getRowCount();) { // ���� ���̺��� ������ �����
				dtm.removeRow(0);			
			}
			
			while(rs.next()) {
				String name = rs.getString("NAME");
				String id = rs.getString("ID");
				String account = rs.getString("ACCOUNT");
				String time = rs.getString("time");
				String who = rs.getString("WHO");
				String balance = rs.getString("BALANCE");

				Vector<String> row = new Vector<>();
				row.add(name);
				row.add(id);
				row.add(account);
				row.add(time);
				row.add(who);
				row.add(balance);
				
				dtm.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		new manager3();
	}
}
