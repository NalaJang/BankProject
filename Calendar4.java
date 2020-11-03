package BankMenuGUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class Calendar4 extends Frame implements ActionListener {
	private Button b_previous, b_next, b_search, b_checkbox, b_save;
	private Button[] b_day = null;
	private Checkbox check;
	private Choice cho_month;
	private Choice cho_year;
	private Dialog dialog;
	private Font font;
	private Label la1, la2, la_month;
	private Label la_dayOfWeek[] = new Label[7];
	private int year, month, date, lastDay, space;
	private String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
	private TextArea ta_memo;
	private TextField tf_search;
	private Panel p1, p2, p3, p4, p5;
	
	Calendar now;
	
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

	
	Calendar4() {
		now = Calendar.getInstance();
		year = now.get(Calendar.YEAR);
		month = now.get(Calendar.MONTH) +1;
		
		b_search = new Button("검색");
		b_next = new Button(">");
		b_previous = new Button("<");
		cho_month = new Choice();
//		cho_month.add("10");
//		cho_month.add("9");
//		cho_month.add("8");
//		cho_year = new Choice();
//		cho_year.add("2020");
//		cho_year.add("2019");
		
		la1 = new Label();
		la2 = new Label();
		font = new Font("맑은고딕", font.PLAIN, 13);
		tf_search = new TextField(50);
		
		for(int i =year-3; i<= year+3; i++) {
			yearModel.addElement(i);
		}
		for(int i =1; i<= 12; i++) {
			monthModel.addElement(i);
		}
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);
		
		p1 = new Panel();			//달력 상단
		p1.setLayout(new FlowLayout());
		p1.add(b_previous);
		p1.add(yearCombo);
		p1.add(monthCombo);
		p1.add(b_search);
		p1.add(b_next);

		p3 = new Panel();			//달력 하단
		p3.setLayout(new FlowLayout());
		p3.add(tf_search);
		p3.add(b_search);

		getCalendar(year, month);  //현재날짜
		
		add(p1, "North");
		add(getP2(), "Center");
		add(p3, "South");
		
		
		//---------------------------------------------------------------프레임
		setSize(500, 500);
		setTitle("달력");
		setVisible(true);
		setLocation(750, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		//---------------------------------------------------------------메모 다이어로그
		p4 = new Panel();
		p4.setLayout(new GridLayout(1, 2));
		b_checkbox = new Button("Check");
		b_save = new Button("저장");
		p4.add(b_checkbox);
		p4.add(b_save);
		
		p5 = new Panel(new BorderLayout());
		ta_memo = new TextArea("", 50, 50, 1);
		p5.add(ta_memo);
		p5.add(p4,"South");
	
		dialog= new Dialog(this, "메모");
		dialog.add(p5);
		
		dialog.setSize(300, 300);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
			}
		});
		
		//---------------------------------------------------------------폰트설정
		b_next.setFont(font);
		b_previous.setFont(font);
		yearCombo.setFont(font);
		monthCombo.setFont(font);
		tf_search.setFont(font);	
		
		// ---------------------------------------------------------------이벤트
		b_search.addActionListener(this);
		b_previous.addActionListener(this);
		b_checkbox.addActionListener(this);
		b_save.addActionListener(this);
		b_next.addActionListener(this);
			
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		Button b = (Button) obj;
		int yy = (Integer)yearCombo.getSelectedItem();
		int mm = (Integer)monthCombo.getSelectedItem();
		if(b.equals(b_previous)) {
			if(mm == 1) {
				yy--; mm=12;
			}else {
				mm--;
			}			
		}else if(b.equals(b_next)) {
			if(mm == 12) {
				yy++; mm=1;
			}else {
				mm++;
			}			
		}
		yearCombo.setSelectedItem(yy);
		monthCombo.setSelectedItem(mm);
		
		if( obj instanceof JComboBox) {
			createDay();
		}
		if(obj == b_checkbox) {
			check = new Checkbox();
		}
		if(obj == b_save) {			//일정 저장
			BufferedWriter bw= null;
			try {
				bw = new BufferedWriter(new FileWriter(new File("C:/IO/Calendar.txt"), true));
				bw.write("+" + ta_memo.getText());
				bw.flush();
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					bw.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
		//---------------------------------------------------------------캘린더설정
	public void calendarData(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		for (int i = 1; i < lastDay + startDay; i++) {
			if (i < startDay) {
				la_dayOfWeek[i] = new Label(dayOfWeek[i]);
				la_dayOfWeek[i].setFont(font);				
				continue;
			}
		}
	}
	public void getCalendar(Integer year, Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 1);
		date = cal.get(Calendar.DAY_OF_WEEK);
		space = date - 1;
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
		//---------------------------------------------------------------버튼설정
	public void setCalendarButton() {
		for (Integer i = 0; i < 42; i++) {
			b_day[i].setLabel("");
		}
		for (Integer i = 1; i < lastDay + 1; i++) {
			b_day[i + space - 1].setLabel(i.toString());
		}
	}

	// ---------------------------------------------------------------요일 및 날짜-> 버튼 위에 얹기
	public Panel getP2() {
		if (p2 == null) {
			p2 = new Panel();
			p2.setLayout(new GridLayout(7, 7));

			la_dayOfWeek = new Label[7];
			for (int i = 0; i < 7; i++) {
				la_dayOfWeek[i] = new Label(dayOfWeek[i], Label.CENTER);
				la_dayOfWeek[i].setFont(new Font("맑은고딕", Font.BOLD, 13));
				p2.add(la_dayOfWeek[i]);
			}

			b_day = new Button[42];
			for (Integer i = 0; i < 42; i++) {
				b_day[i] = new Button();
				b_day[i].setFont(new Font("맑은고딕", Font.PLAIN, 13));
				b_day[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand() != "") {
							dialog.setVisible(true);
						}
					}
				});
				p2.add(b_day[i]);
			}
			setCalendarButton();
		}
		return p2;
	}
	//---------------------------------------------------------------월에 맞는 날짜 재 출력
	public void createDay() {
		p2.setVisible(false);
		p2.removeAll();
		getCalendar((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
		p2.setVisible(true);		
	}
	//---------------------------------------------------------------main method
	public static void main(String[] args){
		new Calendar4();
	}
}