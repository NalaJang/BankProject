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
		img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("멤버십.png"));
		img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bb.png"));
		img3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("starbucks.png"));
		img4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("CGV.png"));
		
		f_brand = new Font("맑은고딕", Font.BOLD, 15);
		f_ment = new Font("맑은고딕", Font.PLAIN, 11);
		
		jla1 = new JLabel();		//브랜드 1
		jla1.setIcon(new ImageIcon(img));
		la_brand= new Label("에버랜드", Label.CENTER);
		la_vip = new Label("VIP  | 본인 60% 할인 + 동반 3인 30%");
		la1 = new Label("일반 | 본인 40% 할인 + 동반 2인 30%");
		
		jla2 = new JLabel();		//브랜드 2
		jla2.setIcon(new ImageIcon(img2));
		la_brand2 = new Label("블루보틀", Label.CENTER);
		la_vip2 = new Label("VIP  | 블루보틀 굿즈 한정 제공");
		
		jla3 = new JLabel();		//브랜드 3
		jla3.setIcon(new ImageIcon(img3));
		la_brand3 = new Label("스타벅스", Label.CENTER);
		la_vip3 = new Label("VIP  | 아메리카노 short사이즈 무료");
		la2 = new Label("일반 | 사이즈업");
		
		jla4 = new JLabel();		//브랜드 4
		jla4.setIcon(new ImageIcon(img4));
		la_brand4 = new Label("CGV", Label.CENTER);
		la_vvip = new Label("VVIP  | 1DAY 영화무료");
		la_vip4 = new Label("VIP  | 영화예매 무료");
		la3 = new Label("일반 | 최대 8천원 할인 + 동반1인");
		
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
		setTitle("멤버십 혜택");
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
