package BankMenuGUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Membership extends Frame {
	Font f_brand, f_ment;
	Image img, img2, img3, img4;
	JLabel jla1, jla2, jla3, jla4;
	Label la_brand, la_brand2, la_brand3, la_brand4, la_vvip, la_vip, la_vip2, la_vip3, la_vip4;
	Label la1, la2, la3, blank;
	
	Membership(){
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("�����.png"));
		img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bb.png"));
		img3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("starbucks.png"));
		img4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("CGV.png"));
		
		f_brand = new Font("�������", Font.BOLD, 15);
		f_ment = new Font("�������", Font.PLAIN, 11);
		
		jla1 = new JLabel();		//�귣�� 1
		jla1.setIcon(new ImageIcon(img));
		la_brand= new Label("��������", Label.CENTER);
		la_vip = new Label("VIP  | ���� 60% ���� + ���� 3�� 30%");
		la1 = new Label("�Ϲ� | ���� 40% ���� + ���� 2�� 30%");
		
		jla2 = new JLabel();		//�귣�� 2
		jla2.setIcon(new ImageIcon(img2));
		la_brand2 = new Label("��纸Ʋ", Label.CENTER);
		la_vip2 = new Label("VIP  | ��纸Ʋ ���� ���� ����");
		
		jla3 = new JLabel();		//�귣�� 3
		jla3.setIcon(new ImageIcon(img3));
		la_brand3 = new Label("��Ÿ����", Label.CENTER);
		la_vip3 = new Label("VIP  | �Ƹ޸�ī�� short������ ����");
		la2 = new Label("�Ϲ� | �������");
		
		jla4 = new JLabel();		//�귣�� 4
		jla4.setIcon(new ImageIcon(img4));
		la_brand4 = new Label("CGV", Label.CENTER);
		la_vvip = new Label("VVIP  | 1DAY ��ȭ����");
		la_vip4 = new Label("VIP  | ��ȭ���� ����");
		la3 = new Label("�Ϲ� | �ִ� 8õ�� ���� + ����1��");
		
		blank = new Label();
		
		add(jla1);
		add(jla2);
		add(jla3);
		add(jla4);
		add(la_brand);
		add(la_brand2);
		add(la_brand3);
		add(la_brand4);
		add(la_vvip);
		add(la_vip);
		add(la_vip2);
		add(la_vip3);
		add(la_vip4);
		add(la1);
		add(la2);
		add(la3);
		add(blank);
		
		jla1.setBounds(45, 80, 120, 100);
		la_brand.setBounds(60, 190, 110, 30);
		la_vip.setBounds(55, 220, 135, 30);
		la1.setBounds(55, 245, 140, 30);

		jla2.setBounds(230, 80, 120, 100);
		la_brand2.setBounds(245, 190, 110, 30);
		la_vip2.setBounds(240, 220, 150, 30);

		jla3.setBounds(55, 295, 120, 100);
		la_brand3.setBounds(60, 400, 110, 30);
		la_vip3.setBounds(50, 430, 180, 30);
		la2.setBounds(50, 460, 140, 30);
		
		jla4.setBounds(230, 310, 180, 90);
		la_brand4.setBounds(245, 400, 110, 30);
		la_vvip.setBounds(240, 430, 180, 30);
		la_vip4.setBounds(240, 460, 150, 30);
		la3.setBounds(240, 490, 145, 30);
		
		setSize(450, 550);
		setVisible(true);
		setTitle("����� ����");
		setLocation(1200, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		la_brand.setFont(f_brand);
		la_brand2.setFont(f_brand);
		la_brand3.setFont(f_brand);
		la_brand4.setFont(f_brand);
		la_vvip.setFont(f_ment);
		la_vip.setFont(f_ment);
		la_vip2.setFont(f_ment);
		la_vip3.setFont(f_ment);
		la_vip4.setFont(f_ment);
		la1.setFont(f_ment);
		la2.setFont(f_ment);
		la3.setFont(f_ment);
	}
	public static void main(String[] args) {
		new Membership();
	}
}
