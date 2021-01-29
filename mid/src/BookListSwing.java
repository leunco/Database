import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class BookListSwing extends JFrame implements ActionListener{
	JButton btnReset,btnInput,btnSearch1, btnSearch2, btnSearch3;
	JTextArea txtResult;
	JPanel pn1;
	JPanel pn2;
	
	JLabel id1 = new JLabel("orderid: "); //orderid
	JLabel id2 = new JLabel("custid: "); //custid
	JLabel id3 = new JLabel("bookid: "); //bookid
	JLabel p = new JLabel("saleprice: "); //saleprice
	JLabel d = new JLabel("orderdate: "); //orderdate
	
	JTextField o_id = new JTextField(3);
	JTextField c_id = new JTextField(3);
	JTextField b_id = new JTextField(3);
	JTextField price = new JTextField(10);
	JTextField date = new JTextField(10);
	
		
	static Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang"; //사용자 ID
	String pwd = "madang";  // 사용자 Password
	
	// 임의로 추가한 튜플의 개수 셀 때
	int cnt=0;
	
	// 오류 판단할때 
	int f1=0;
	int f3=0;
	int f4=0;
	int f6=0;
	int f7=0;
	int f8=0;
	
	// 오류가 있는지 없는지 판단
	int checkerror=0;
	
	int array[] = new int[10000]; //orderid

	public BookListSwing(){
		setTitle("18011571/이은효"); // title 영역에 학번/본인이름
		layInit();
		conDB();
		
		setVisible(true);
		setBounds(200,200,800,400); //가로위치, 세로위치, 가로길이, 세로길이
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void layInit() {
		btnReset = new JButton("초기화 버튼");
		btnInput = new JButton("입력1 버튼");
		btnSearch1 = new JButton("검색1 버튼"); // select * from book
		btnSearch2 = new JButton("검색2 버튼"); // select * from orders
		btnSearch3 = new JButton("검색3 버튼"); // select * from customer
		
		txtResult = new JTextArea();
		pn1 = new JPanel();
		pn2 = new JPanel();
		
		pn1.add(btnReset);
		pn1.add(btnSearch1);
		pn1.add(btnSearch2);
		pn1.add(btnSearch3);
			
		pn2.add(id1);
		pn2.add(o_id);
		pn2.add(id2);
		pn2.add(c_id);
		pn2.add(id3);
		pn2.add(b_id);
		pn2.add(p);
		pn2.add(price);
		pn2.add(d);
		pn2.add(date);
		pn2.add(btnInput);
		
		txtResult.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(txtResult);
		add("North",pn1);
		add("Center",scrollPane);
		add("South",pn2);
		
		
		btnReset.addActionListener(this);
		btnSearch1.addActionListener(this);
		btnSearch2.addActionListener(this);
		btnSearch3.addActionListener(this);
		btnInput.addActionListener(this);
		
	}
	
	
	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("데이터베이스 연결 준비...");
			con = DriverManager.getConnection(url,userid,pwd);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			stmt = con.createStatement();
			
			String query1 = "SELECT * FROM Book";
			String query2 = "SELECT * FROM Orders";
			String query3 = "SELECT * FROM Customer";
			
			if(e.getSource()==btnSearch1) { //검색1버튼
				txtResult.setText("");				
				txtResult.setText("bookid	bookname	publisher	price\n");
				rs = stmt.executeQuery(query1);
				while(rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) +"\n";
					txtResult.append(str);
				}
			}
			
			if(e.getSource()==btnSearch2) {//검색2버튼
				txtResult.setText("");
				txtResult.setText("orderid	custid	bookid	saleprice 	orderdate\n");
				rs = stmt.executeQuery(query2);
				while(rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) +"\t" + rs.getInt(4) + "\t" + rs.getString(5) +"\n";
					txtResult.append(str);
				}
			}
			
			if(e.getSource() == btnSearch3) { //검색3버튼
				txtResult.setText("");
				txtResult.setText("custid	name	address	phone\n");
				rs = stmt.executeQuery(query3);
				while(rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) +"\t" + rs.getString(4) +"\n";
					txtResult.append(str);
				}
			}
			
			if(e.getSource() == btnInput) { //입력버튼
				insert();
			}
			
			
			if(e.getSource() == btnReset) { //초기화
				
				// 다 지움
				stmt.executeUpdate("delete from orders");
	    	    stmt.executeUpdate("delete from book");
	    	    stmt.executeUpdate("delete from customer");
	    	        
	    	    // 원래 있던 튜플
	    	    stmt.executeUpdate("INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000);");
	        	stmt.executeUpdate("INSERT INTO Book VALUES(2, '축구아는 여자', '나무수', 13000);");
	        	stmt.executeUpdate("INSERT INTO Book VALUES(3, '축구의 이해', '대한미디어', 22000);");
	        	stmt.executeUpdate("INSERT INTO Book VALUES(4, '골프 바이블', '대한미디어', 35000);");
	        	stmt.executeUpdate("INSERT INTO Book VALUES(5, '피겨 교본', '굿스포츠', 8000);");
	       		stmt.executeUpdate("INSERT INTO Book VALUES(6, '역도 단계별기술', '굿스포츠', 6000);");
	       		stmt.executeUpdate("INSERT INTO Book VALUES(7, '야구의 추억', '이상미디어', 20000);");
	       		stmt.executeUpdate("INSERT INTO Book VALUES(8, '야구를 부탁해', '이상미디어', 13000);");
	       		stmt.executeUpdate("INSERT INTO Book VALUES(9, '올림픽 이야기', '삼성당', 7500);");
	       		stmt.executeUpdate("INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000);");
	       		 
	       		stmt.executeUpdate("INSERT INTO Customer VALUES (1, '박지성', '영국 맨체스타', '000-5000-0001');");
		       	stmt.executeUpdate("INSERT INTO Customer VALUES (2, '김연아', '대한민국 서울', '000-6000-0001');");
		       	stmt.executeUpdate("INSERT INTO Customer VALUES (3, '장미란', '대한민국 강원도', '000-7000-0001');");
		       	stmt.executeUpdate("INSERT INTO Customer VALUES (4, '추신수', '미국 클리블랜드', '000-8000-0001');");
		       	stmt.executeUpdate("INSERT INTO Customer VALUES (5, '박세리', '대한민국 대전',  NULL);");
	        	
		       	stmt.executeUpdate("INSERT INTO Orders VALUES (1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d'));");
		       	stmt.executeUpdate("INSERT INTO Orders VALUES (2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d'));");
		       	stmt.executeUpdate("INSERT INTO Orders VALUES (3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d'));");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d'));");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d'));");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d'));");
		       	stmt.executeUpdate("INSERT INTO Orders VALUES (7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d'));");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d')); ");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d')); ");
		        stmt.executeUpdate("INSERT INTO Orders VALUES (10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d'));");
		        	
					
		        // 임의로 추가한 튜플
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (11,'축구란 무엇인가','굿스포츠',10000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (12,'스포츠 세계','대한미디어',5000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (13,'볼링의 매력','삼성당',25000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (14,'역도의 정석','나무수',23000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (15,'피겨 여왕','나무수',12000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (16,'스포츠 역사','대한미디어',7000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (17,'운동 바이블','삼성당',4000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (18,'야구의 이해','굿스포츠',6000);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (19,'피겨의 이해','굿스포츠',6500);");
				stmt.executeUpdate("insert into book (bookid, bookname, publisher, price) values (20,'스포츠와 경쟁','이상미디어',9000);");
				
				stmt.executeUpdate("insert into customer (custid, name, address, phone) values(6,'박찬호', '대한민국 서울', '000-2233-1234');");
				stmt.executeUpdate("insert into customer (custid, name, address, phone) values(7,'이한빈', '미국 하와이주', '000-1532-2464');");
				stmt.executeUpdate("insert into customer (custid, name, address, phone) values(8,'김희진', '캐나다 토론토', '000-8736-9015');");
				stmt.executeUpdate("insert into customer (custid, name, address, phone) values(9,'손흥민', '호주 캔버라' , '000-1052-2124');");
				stmt.executeUpdate("insert into customer (custid, name, address, phone) values(10,'김연경', '스위스 베른', '000-8232-4269');");
					
				stmt.executeUpdate("INSERT INTO Orders VALUES (11, 7, 12, 8000, STR_TO_DATE('2014-07-11','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (13, 3, 15, 18000, STR_TO_DATE('2014-07-13','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (14, 9, 4, 27000, STR_TO_DATE('2014-07-14','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (15, 2, 17, 31000, STR_TO_DATE('2014-07-15','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (16, 3, 8, 5000, STR_TO_DATE('2014-07-16','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (17, 5, 6, 16000, STR_TO_DATE('2014-07-17','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (18, 10, 3, 24000, STR_TO_DATE('2014-07-18','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (19, 1, 19, 11000, STR_TO_DATE('2014-07-19','%Y-%m-%d'));");
				stmt.executeUpdate("INSERT INTO Orders VALUES (20, 4, 11, 8000, STR_TO_DATE('2014-07-20','%Y-%m-%d'));");
				
				
				txtResult.setText("");
				
				o_id.setText("");
				c_id.setText("");
				b_id.setText("");
				price.setText("");
				date.setText("");
				
				System.out.println("초기화되었습니다.");
				System.out.println("----------------------------------");
			}
			
		} catch (SQLException e1) {
			System.out.println("쿼리 읽기 실패: "+e1);
			e1.printStackTrace();
		}	
	}
	
	public boolean isNum(String str,int k) { //숫자가 아닌 문자가 있는지 판단
		int length = str.length();
		int flag=0;
		
		if(str.charAt(0)=='-' && k == 5) { //saleprice가 음수일 때만  
			for(int i=1;i<length;i++) {
				char data=str.charAt(i);
				
				if('0'<= data && data<='9')
					flag=0;
				else {
					flag = 1;
					break;
				}
			}
			
			if(str.length()==1) //saleprice에 '-'만 들어오면
				flag=1;
		}
		
		else {
			for(int i=0;i<length;i++) {
				char data = str.charAt(i);

				if('0'<= data && data<='9')
					flag=0;
				else {
					flag = 1;
					break;
				}

			}
		}
			
		if(flag==1)
			return false;
		else
			return true;
	}
	
	
	public void insert() { //입력 버튼
		String order_id = o_id.getText();
		String cust_id = c_id.getText();
		String book_id = b_id.getText();
		String sale_price = price.getText();
		String order_date = date.getText();  
		
		int n1 = 0;
		int n2 = 0;
		int n3 = 0;
		int n4 = 0;
		int n5 = 0;
		
		if(order_id.length()==0 || cust_id.length()==0 || book_id.length()==0 || sale_price.length()==0 || order_date.length()==0)
			System.out.println("오류: 값이 없음! 값을 입력하세요");
		
		else if(isNum(order_date,1) == false || isNum(order_id,2) == false || isNum(cust_id,3) == false || isNum(book_id,4) == false || isNum(sale_price,5) == false) {
			System.out.println("오류: 문자가 아닌 양의 숫자로만 쓰세요!(단, saleprice는 0도 입력 가능)");
			}
		else {
			n1 = Integer.parseInt(order_id);
			n2 = Integer.parseInt(cust_id);
			n3 = Integer.parseInt(book_id);
			n4 = Integer.parseInt(sale_price);		
			n5 = Integer.parseInt(order_date);

			int year = n5 / 10000;
			int month = (n5 % 10000) / 100;
			int day = (n5 % 10000) % 100;


			// f1 -> 날짜 오류
			if (year >= 1000 && year <= 9999) { // 연도는 4자리
				if (1 <= month && month <= 12) {
					if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) {
						if (day < 1 || day > 31) {
							f1 = 1;
						}
					}

					else if (month == 4 || month == 6 || month == 9 || month == 11) {
						if (day < 1 || day > 30) {
							f1 = 1;
						}
					} else if (month == 2) {
						if (day < 1 || day > 28) {
							f1 = 1;
						}
					}
				}	

				else { // month가 다른 숫자면 아님
						f1 = 1;
				}
			} 
			else {
				f4 = 1;
			}
		
			if(n4<0) { //saleprice가 음수
				f6 = 1;
			}


			array[cnt] = n1; //orderid
			
			String query4 = "insert into orders (orderid, custid, bookid, saleprice, orderdate) values("
					+ n1 + "," +  n2+ "," + n3 + "," + n4 + "," + "'" + order_date +"');";

			if(1<=n2 && n2<=10) //custid 범위
				f7=0;
			else
				f7=1; //범위 벗어남

			if(1<=n3 && n3<=20) //bookid 범위
				f8=0;
			else 
				f8=1; //범위 벗어남

			try {
				for(int i=0;i<cnt;i++) {
					if(array[i]==n1) { //중복
						f3=1;
						break;
					}
				}

				if(f1==1) {
					System.out.println("오류 : orderdate를 알맞은 날짜로 입력하세요!");
					checkerror=1;
				}

				if(f6==1) {
					System.out.println("오류: saleprice를 음수가 아닌 수로 입력하세요!");
					checkerror=1;
				}

				if(1<= n1 && n1 <= 20 || f3==1) {
					System.out.println("오류: orderid 값 중복!");
					checkerror=1;
				}

				if(f4==1) {
					System.out.println("오류: orderdate를 숫자 8개로 쓰세요!");
					checkerror=1;
				}

				if(f7==1) {
					System.out.println("오류: custid 범위를 벗어남! custid 범위는 1~10");
					checkerror=1;
				}

				if(f8==1) {
					System.out.println("오류: bookid 범위를 벗어남! bookid 범위는 1~20");
					checkerror=1;
				}
				

				// 위의 에러들이 전부 다 발생안한다면
				if(checkerror==0) {
					stmt.executeUpdate(query4);
					cnt++;
					System.out.println("입력 성공! 검색2버튼을 다시 눌러 확인하세요");
					
					o_id.setText("");
					c_id.setText("");
					b_id.setText("");
					price.setText("");
					date.setText("");
				}

				f1=0;
				f3=0;
				f4=0;
				f6=0;
				f7=0;
				f8=0;

				checkerror=0;

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("----------------------------------");
	}
	


	public static void main(String[] args) {
		BookListSwing BLS = new BookListSwing();
		
		BLS.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try{
					con.close();
				} catch(Exception e4) { }
				
				System.out.println("프로그램 완전 종료!");
				System.exit(0);
			}
		});
	}

	
}
