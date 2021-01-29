import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Final extends JFrame implements ActionListener{
	
	   Connection con;
	   Statement stmt;
	   ResultSet rs;
	   
	   String Driver = "";
	   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	   String userid = "madang";
	   String pwd = "madang";
	   
		JTextArea txtResult = new JTextArea("");
		
		JButton btnReset = new JButton("초기화 버튼");	
	    JButton btnInput = new JButton("간호사 입력 버튼");
		JButton btnSearch1 = new JButton("검색1 버튼"); 
		JButton btnSearch2 = new JButton("검색2 버튼");
		JButton btnSearch3 = new JButton("검색3 버튼");
		
		JLabel nurses_idl = new JLabel("간호사 ID");
		JTextField nurses_idf = new JTextField(5);
		JLabel nurses_jobl = new JLabel("담당업무");
		JTextField nurses_jobf = new JTextField(10);
		JLabel nurses_namel = new JLabel("성명");
		JTextField nurses_namef = new JTextField(5);
		JLabel nurses_genl = new JLabel("성별");
		JTextField nurses_genf = new JTextField(3);
		JLabel nurses_phonel = new JLabel("전화번호");
		JTextField nurses_phonef = new JTextField(10);
		JLabel nurses_emaill = new JLabel("이메일");
		JTextField nurses_emailf = new JTextField(10);
		JLabel nurses_posl = new JLabel("직급");
		JTextField nurses_posf = new JTextField(10);
		
		//검색 1
		JLabel s1_in1l = new JLabel("간호사 테이블 검색");
		
		//검색2
		JLabel s2_in1l = new JLabel("담당 간호사의 성별이 ");
		JTextField s2_in1f = new JTextField(3);
		JLabel s2_in2l = new JLabel("인 환자들을 환자들의 성별로 나눈 자료의 건수 ");
		
		//검색3
		JLabel s3_in1l = new JLabel("담당 간호사의 성별이");
		JTextField s3_in1f = new JTextField(3);
		JLabel s3_in2l = new JLabel("면서, 담당 의사의 성별이");
		JTextField s3_in2f = new JTextField(3);
		JLabel s3_in3l = new JLabel("인 환자들을 ");
		JLabel s3_in4l = new JLabel("환자들의 성별로 나눈 자료의 건수");
		
		JPanel searchP1 = new JPanel();
		JPanel searchP2 = new JPanel();
		JPanel searchP3 = new JPanel();
		JPanel inputP = new JPanel();	
		JPanel resetP = new JPanel();
		
	   
	public Final() {
		setTitle("18011571/이은효"); // title 영역에 학번/본인이름
		setSize(700,600);
		conDB();
		
		this.setLayout(null);
		
		btnReset.addActionListener(this);
		btnInput.addActionListener(this);
		btnSearch1.addActionListener(this);
		btnSearch2.addActionListener(this);
		btnSearch3.addActionListener(this);

		resetP.add(btnReset);
		
		// 간호사 입력
		inputP.add(nurses_idl);
		inputP.add(nurses_idf);
		inputP.add(nurses_jobl);
		inputP.add(nurses_jobf);
		inputP.add(nurses_namel);
		inputP.add(nurses_namef);
		inputP.add(nurses_genl);
		inputP.add(nurses_genf);
		inputP.add(nurses_phonel);
		inputP.add(nurses_phonef);
		inputP.add(nurses_emaill);
		inputP.add(nurses_emailf);
		inputP.add(nurses_posl);
		inputP.add(nurses_posf);

		inputP.add(btnInput);
		
		
		//검색1
		searchP1.add(s1_in1l);
		searchP1.add(btnSearch1);
		
		//검색2
		searchP2.add(s2_in1l);
		searchP2.add(s2_in1f);
		searchP2.add(s2_in2l);	
		searchP2.add(btnSearch2);
		
		//검색3
		searchP3.add(s3_in1l);
		searchP3.add(s3_in1f);
		searchP3.add(s3_in2l);
		searchP3.add(s3_in2f);
		searchP3.add(s3_in3l);
		searchP3.add(s3_in4l);
		searchP3.add(btnSearch3);		
		
		txtResult.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtResult);
		
		
		resetP.setBounds(20,10,100,40);
		add(resetP);
		
		inputP.setBounds(20,50,600,70);
		add(inputP);
		
		scrollPane.setBounds(20,130,650,200);
		add(scrollPane);
	
		searchP1.setBounds(225,350,500,40);
		add(searchP1);
	
		searchP2.setBounds(20,400,600,50);
		add(searchP2);
		
		searchP3.setBounds(40,450,550,70);
		add(searchP3);
       
        this.setResizable(false);
       
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
    public Boolean isNum(String str) {
        char tmp;
        boolean output = true;
        for(int i=0; i<str.length(); i++) {
           tmp = str.charAt(i);
           if(Character.isDigit(tmp)==false)
              output=false;
        }
        return output;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			stmt = con.createStatement();
			
			String query;
			
			if(e.getSource()==btnSearch1) { //검색1버튼
				System.out.println("검색 1버튼 클릭!");
				
				query = "select * from nurses";
				rs = stmt.executeQuery(query);
				
				txtResult.setText("");
				txtResult.setText("간호사ID\t 담당업무\t 성명\t 성별\t 전화번호 \t 이메일 \t 직급\n");
				
				while(rs.next()) {
					String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) +
							"\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t"+ rs.getString(7)+"\n";
					txtResult.append(str);
					
				}
				
				System.out.println("검색 1 완료!");
             
			}
			
			if(e.getSource()==btnSearch2) {//검색2버튼
				System.out.println("검색 2버튼 클릭!");
				
				String s2_in1 = s2_in1f.getText();
				Boolean CheckError = false;
				
				if(s2_in1.length()==0) {
					System.out.println("오류 : 모든 칸에 데이터를 입력해야 합니다.");
					CheckError = true;
				}
				else {
					if(s2_in1.length()!=0) {
						if(!s2_in1.equals("M") && !s2_in1.equals("F")) {
							System.out.println("오류 : 간호사 성별은 M과 F로 입력해야 합니다.(남자는 M, 여자는 F)");
							CheckError = true;
						}
					}
					
				}
				
				if(!CheckError) {
					query = "select pat_gen, count(*) as pat_id\r\n" + 
							"from patients\r\n" + 
							"where Nurses_nur_id In(select nur_id\r\n" + 
							"						from nurses\r\n" + 
							"						where nur_gen = '"+s2_in1+"')\r\n" + 
							"group by pat_gen;";
					
					rs = stmt.executeQuery(query);
					
					
					txtResult.setText("");
					txtResult.setText("[검색2]\n");
					txtResult.append("환자의 성별\t 자료의 건수\n");
					
					while(rs.next()) {
						String str = rs.getString(1) + "\t" + rs.getInt(2)+"\n";
						txtResult.append(str);			
					}
					
					System.out.println("검색2 완료!");
				}
				
			}
			
			if(e.getSource() == btnSearch3) { //검색3버튼
				System.out.println("검색 3버튼 클릭!");
				
				String s3_in1 = s3_in1f.getText();
				String s3_in2 = s3_in2f.getText();
				
				Boolean CheckError = false;
				
				if(s3_in1.length()==0 || s3_in2.length()==0) {
					System.out.println("오류 : 모든 칸에 데이터를 입력해야 합니다.");
					CheckError = true;
				}
				else {
					if(s3_in1.length()!=0) {
						if(!s3_in1.equals("M") && !s3_in1.equals("F")) {
							System.out.println("오류 : 간호사 성별은 M과 F로 입력해야 합니다.(남자는 M, 여자는 F)");
							CheckError = true;
						}
					}
					
					if(s3_in2.length()!=0) {
						if(!s3_in2.equals("M") && !s3_in2.equals("F")) {
							System.out.println("오류 : 의사 성별은 M과 F로 입력해야 합니다.(남자는 M, 여자는 F)");
							CheckError = true;
						}
					}
				
				}
				
				if(!CheckError) {
					query = "select pat_gen, count(*) as pat_id\r\n" + 
							"from patients\r\n" + 
							"where Nurses_nur_id In(select nur_id\r\n" + 
							"						from nurses\r\n" + 
							"						where nur_gen = '"+ s3_in1+"') and\r\n" + 
							"	 Doctors_doc_id In(select doc_id\r\n" + 
							"						from doctors\r\n" + 
							"						where doc_gen = '"+ s3_in2+"')           \r\n" + 
							"group by pat_gen";
					
					rs = stmt.executeQuery(query);
					
					
					txtResult.setText("");
					txtResult.setText("[검색3]\n");
					txtResult.append("환자의 성별\t 자료의 건수\n");
					
					while(rs.next()) {
						String str = rs.getString(1) + "\t" + rs.getInt(2)+"\n";
						txtResult.append(str);			
					}
					
					System.out.println("검색3 완료!");
					
				}
				
			
			}
			
			if(e.getSource() == btnInput) { //입력버튼
				System.out.println("입력버튼 클릭!");
				
				Boolean CheckError = false;
				Boolean flag1 = false;
				Boolean flag2 = false;
				
				String nurses_id = nurses_idf.getText(); 
				String nurses_job = nurses_jobf.getText();
				String nurses_name = nurses_namef.getText();
				String nurses_gen = nurses_genf.getText();
				String nurses_phone = nurses_phonef.getText();
				String nurses_email = nurses_emailf.getText();
				String nurses_pos = nurses_posf.getText();

				if(nurses_id.length()==0 ||nurses_job.length()==0 || nurses_name.length()==0 || nurses_gen.length()==0 ||
						nurses_phone.length()==0 || nurses_email.length()==0 || nurses_pos.length()==0) {
					System.out.println("오류 : 모든 칸에 데이터를 입력해야 합니다.");
					CheckError = true;
				}
				else {
					if(!isNum(nurses_id)) {
						System.out.println("오류 : 간호사ID는 숫자로만 입력해야 합니다.");
						CheckError = true;
					}
					else {
						int tmp = Integer.parseInt(nurses_id);
						query = "select nur_id from nurses where nur_id = "+tmp;
						rs = stmt.executeQuery(query);
						if(rs.next()) {
							System.out.println("오류 : 간호사ID는 이미 간호사 테이블에 존재합니다.");
							CheckError = true;
						}
					}
					
					if(!nurses_gen.equals("F") && !(nurses_gen.equals("M"))) {
						System.out.println("오류: 성별은 M,F로 표시해야 합니다. (남자는 M, 여자는 F)");
						CheckError = true;
					}
	
					
					// 전화번호 오류
					if(nurses_phone.length()!=0) {
						StringTokenizer ST = new StringTokenizer(nurses_phone,"-");
						String str1 = ST.nextToken();
						String str2,str3;
						
						if(str1.length()!=3 || !isNum(str1)){
							flag1 = true;
						}
						else {
							if(ST.hasMoreTokens()) {
								str2 = ST.nextToken();
								
								if(str2.length()!=3 || !isNum(str2)) {
									flag1 = true;
								}
								else {
									if(ST.hasMoreTokens()) {
										str3 = ST.nextToken();
									
									if(str3.length()!=4 || !isNum(str3)) {
										flag1 = true;
										}
									}
									else {
										flag1 = true;
									}
								}
								
							
							}
							else
								flag1= true;
						}
						
						if(flag1 == true) {
							System.out.println("오류 : 전화번호는 000-000-0000의 형식으로 숫자로만 입력해야 합니다.");
							CheckError = true;
						}
					}
					
					
					//이메일 오류 
					if(nurses_email.length()!=0) {
						StringTokenizer ST1 = new StringTokenizer(nurses_email,"@");
						String str1 = ST1.nextToken();
						String str2,str3;
						
						if(ST1.hasMoreTokens()) {
							str2 = ST1.nextToken();
							
							ST1 = new StringTokenizer(str2, ".");
							if(!ST1.hasMoreTokens()) {
								flag2= true;
							}
							else {
								str3 = ST1.nextToken();
								
								if(!ST1.hasMoreTokens()) {
									flag2 = true;
								}
							}
							
						}
						else {
							flag2= true;
						}
						
						if(flag2 == true) {
							System.out.println("오류 : 이메일 형식은 xxx@xxx.xxx입니다. x의 수는 제한없습니다.");
							CheckError = true;
						}
					}
				
				
				}
				
				if(!CheckError) {
					int nurses_idI = Integer.parseInt(nurses_id);
		        	query = "insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values("+ nurses_idI + ",'"+nurses_job+"','"+nurses_name+"','"
		        			+nurses_gen+"','"+nurses_phone+"','"+nurses_email+"','"+nurses_pos+"');";
		        	
		        	stmt.executeUpdate(query);
		        	
		        	query = "select * from nurses where nur_id = "+nurses_idI;
		        	rs = stmt.executeQuery(query);
		        		        	
		        	if(rs.next()) {
		        	System.out.println("간호사ID\t 담당업무\t 성명\t 성별\t 전화번호 \t 이메일 \t 직급");
		        	
		        	String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) +
							"\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t"+ rs.getString(7)+"\n";
				
	        		System.out.println(str);
		        	}
		        	
					
					System.out.println("간호사 테이블 입력완료!");
					  
		        			
				}
				
			}
			
			
			if(e.getSource() == btnReset) { //초기화
				System.out.println("초기화 버튼 클릭!");
				
				stmt.execute("DROP DATABASE IF EXISTS  madang;");
				stmt.execute("create database madang");
				stmt.execute("grant all privileges on madang.* to madang@localhost with grant option");
				stmt.execute("commit");
				
				//-- MySQL Workbench Forward Engineering
				stmt.execute("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0");
				stmt.execute("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0");
				stmt.execute("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'");
				
				//-- -----------------------------------------------------
				//-- Schema madang
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8");
				stmt.execute("USE `madang`");
				
				//-- -----------------------------------------------------
				//-- Table `madang`.`Doctors`
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Doctors` (\r\n" + 
						"  `doc_id` INT(10) NOT NULL,\r\n" + 
						"  `major_treat` VARCHAR(25) NOT NULL,\r\n" + 
						"  `doc_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `doc_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `doc_phone` VARCHAR(15) NULL,\r\n" + 
						"  `doc_email` VARCHAR(50) NULL,\r\n" + 
						"  `doc_position` VARCHAR(20) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`doc_id`))");
				
				//stmt.execute("ENGINE = InnoDB");
				
				//-- -----------------------------------------------------
				//-- Table `madang`.`Nurses`
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Nurses` (\r\n" + 
						"  `nur_id` INT(10) NOT NULL,\r\n" + 
						"  `major_job` VARCHAR(25) NOT NULL,\r\n" + 
						"  `nur_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `nur_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `nur_phone` VARCHAR(15) NULL,\r\n" + 
						"  `nur_email` VARCHAR(50) NULL,\r\n" + 
						"  `nur_position` VARCHAR(20) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`nur_id`))");
				
				//stmt.execute("ENGINE = InnoDB");
				
				//-- -----------------------------------------------------
				//-- Table `madang`.`Patients`
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Patients` (\r\n" + 
						"  `pat_id` INT(10) NOT NULL,\r\n" + 
						"  `pat_name` VARCHAR(20) NOT NULL,\r\n" + 
						"  `pat_gen` CHAR(1) NOT NULL,\r\n" + 
						"  `pat_jumin` VARCHAR(14) NOT NULL,\r\n" + 
						"  `pat_addr` VARCHAR(100) NOT NULL,\r\n" + 
						"  `pat_phone` VARCHAR(15) NULL,\r\n" + 
						"  `pat_email` VARCHAR(50) NULL,\r\n" + 
						"  `pat_job` VARCHAR(20) NOT NULL,\r\n" + 
						"  `Nurses_nur_id` INT(10) NOT NULL,\r\n" + 
						"  `Doctors_doc_id` INT(10) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`pat_id`),\r\n" + 
						"  INDEX `fk_Patients_Nurses1_idx` (`Nurses_nur_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Patients_Doctors1_idx` (`Doctors_doc_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Patients_Nurses1`\r\n" + 
						"    FOREIGN KEY (`Nurses_nur_id`)\r\n" + 
						"    REFERENCES `madang`.`Nurses` (`nur_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Patients_Doctors1`\r\n" + 
						"    FOREIGN KEY (`Doctors_doc_id`)\r\n" + 
						"    REFERENCES `madang`.`Doctors` (`doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)");
				
				//stmt.execute("ENGINE = InnoDB");
						
				//-- -----------------------------------------------------
				//-- Table `madang`.`Treatments`
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Treatments` (\r\n" + 
						"  `treat_id` INT(15) NOT NULL,\r\n" + 
						"  `Doctors_doc_id` INT(10) NOT NULL,\r\n" + 
						"  `treat_contents` VARCHAR(1000) NULL,\r\n" + 
						"  `treat_date` DATE NULL,\r\n" + 
						"  `Patients_pat_id` INT(10) NOT NULL,\r\n" + 
						"  PRIMARY KEY (`treat_id`, `Doctors_doc_id`, `Patients_pat_id`),\r\n" + 
						"  INDEX `fk_Treatments_Doctors_idx` (`Doctors_doc_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Treatments_Patients1_idx` (`Patients_pat_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Treatments_Doctors`\r\n" + 
						"    FOREIGN KEY (`Doctors_doc_id`)\r\n" + 
						"    REFERENCES `madang`.`Doctors` (`doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Treatments_Patients1`\r\n" + 
						"    FOREIGN KEY (`Patients_pat_id`)\r\n" + 
						"    REFERENCES `madang`.`Patients` (`pat_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)");
				
				//stmt.execute("ENGINE = InnoDB");
				
				//-- -----------------------------------------------------
				//-- Table `madang`.`Charts`
				//-- -----------------------------------------------------
				
				stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Charts` (\r\n" + 
						"  `chart_id` VARCHAR(20) NOT NULL,\r\n" + 
						"  `Treatments_treat_id` INT(15) NOT NULL,\r\n" + 
						"  `Treatments_Doctors_doc_id` INT(10) NOT NULL,\r\n" + 
						"  `Treatments_Patients_pat_id` INT(10) NOT NULL,\r\n" + 
						"  `Nurses_nur_id` INT(10) NOT NULL,\r\n" + 
						"  `chart_contents` VARCHAR(1000) NULL,\r\n" + 
						"  PRIMARY KEY (`chart_id`, `Treatments_treat_id`, `Treatments_Doctors_doc_id`, `Treatments_Patients_pat_id`),\r\n" + 
						"  INDEX `fk_Charts_Treatments1_idx` (`Treatments_treat_id` ASC, `Treatments_Doctors_doc_id` ASC, `Treatments_Patients_pat_id` ASC) VISIBLE,\r\n" + 
						"  INDEX `fk_Charts_Nurses1_idx` (`Nurses_nur_id` ASC) VISIBLE,\r\n" + 
						"  CONSTRAINT `fk_Charts_Treatments1`\r\n" + 
						"    FOREIGN KEY (`Treatments_treat_id` , `Treatments_Doctors_doc_id`)\r\n" + 
						"    REFERENCES `madang`.`Treatments` (`treat_id` , `Doctors_doc_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION,\r\n" + 
						"  CONSTRAINT `fk_Charts_Nurses1`\r\n" + 
						"    FOREIGN KEY (`Nurses_nur_id`)\r\n" + 
						"    REFERENCES `madang`.`Nurses` (`nur_id`)\r\n" + 
						"    ON DELETE NO ACTION\r\n" + 
						"    ON UPDATE NO ACTION)");
				
				//stmt.execute("ENGINE = InnoDB");
				
				stmt.execute("SET SQL_MODE=@OLD_SQL_MODE");
				stmt.execute("SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS");
				stmt.execute("SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS");
				
				//** Doctors
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (980312,'소아과','이태정','M','010-333-1340','ltj@hanbh.com', '과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (000601,'내과','안성기','M','011-222-0987','ask@hanbh.com', '과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (001208,'외과','김민종','M','010-333-8743','kmj@hanbh.com', '과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (020403,'피부과','이태서','M','019-777-3764','lts@hanbh.com', '과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (050900,'소아과','김연아','F','010-555-3746','kya@hanbh.com', '전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (050101,'내과','차태현','M','011-222-7643','cth@hanbh.com', '전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (062019,'소아과','전지현','F','010-999-1265','jjh@hanbh.com', '전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (070576,'피부과','홍길동','M','016-333-7263','hgd@hanbh.com', '전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (080543,'방사선과','유재석','M','010-222-1263','yjs@hanbh.com', '과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (091001,'외과','김병만','M','010-555-3542','kbm@hanbh.com', '전문의');");
				//doctors 추가
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (051234,'소아과','이한빈','F','010-123-1522','lhb@hanbh.com','전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (010212,'외과','육성재', 'M','010-152-1021', 'yook@hanbh.com','과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (029123,'내과', '임현식', 'M', '010-023-0231','lim@hanbh.com', '전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (011293, '피부과', '이창섭','M', '010-034-1021', 'lcs@hanbh.com','전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (031123, '내과', '정일훈', 'M', '010-329-9431', 'jih@hanbh.com','전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (012942, '방사선과','최효정', 'F', '010-392-1293', 'candy@hanbh.com','과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (021232, '피부과', '김미현', 'F', '010-043-1203', 'mimi@hanbh.com','과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (012392, '소아과', '유시아', 'F', '010-923-9123', 'yua@hanbh.com','전문의');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (029212, '내과', '김지호', 'F', '010-301-0344', 'jiho@hanbh.com','과장');");
				stmt.execute("insert into doctors(doc_id, major_treat, doc_name, doc_gen, doc_phone, doc_email, doc_position) values (023012, '방사선과', '최예원', 'F', '010-204-0234','alin@hanbh.com','전문의');");

				//** Nurses
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(050302, '소아과', '김은영', 'F', '010-555-8751', 'key@hanbh.com','수간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(050021, '내과', '윤성애', 'F','016-333-8745','ysa@hanbh.com','수간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(040089, '피부과', '신지원', 'M', '010-666-7646', 'sjw@hanbh.com','주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(070605, '방사선과', '유정화', 'F', '010-333-4588', 'yjh@hanbh.com','주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(070804, '내과', '라하나', 'F', '010-222-1340','nhn@hanbh.com','주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(071018, '소아과', '김화경', 'F', '019-888-4116', 'khk@hanbh.com', '주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(100356, '소아과', '이선용', 'M', '010-777-1234', 'lsy@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(104145, '외과', '김현', 'M', '010-999-8520', 'kh@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(120309, '피부과', '박성완', 'M', '010-777-4996', 'psw@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(130211, '외과', '이서연', 'F', '010-222-3214', 'lsy2@hanbh.com', '간호사');");
				//추가
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(021929, '소아과', '이주리', 'F', '010-120-0521', 'ads@hanbh.com','수간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(102312, '방사선과', '송서영', 'F', '010-023-0421', 'wnv@hanbh.com','간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(129121, '외과', '김지훈', 'M', '010-212-0312', 'lzq@hanbh.com', '주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(002391, '피부과', '김가연', 'F', '010-090-0120', 'zcx@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(120821, '외과', '한기영', 'M', '010-023-0504', 'zxm@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(012911, '소아과', '심정희', 'F', '010-301-0421', 'cza@hanbh.com','주임');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(041821, '내과', '오연지', 'F', '010-201-0329', 'lqs@hanbh.com','간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(012312, '피부과', '오정봉', 'M', '010-230-0549', 'asf@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(002912, '외과', '유보영', 'F', '010-041-0219', 'las@hanbh.com', '간호사');");
				stmt.execute("insert into nurses(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email, nur_position) values(050192, '소아과', '이정록', 'M', '010-012-1041', 'alsx@hanbh.com', '주임');");
				
				
				//** Patients
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2345,'안상건','M','232345',"
						+ "'서울','010-555-7845','ask@ab.com','회사원',050302,980312);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(3545,'김성룡','M','543545',"
						+ "'서울','010-333-7812','ksr@bb.com','자영업',040089,020403);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(3424,'이종진','M','433424',"
						+ "'부산','019-888-4859','ljj@ab.com','회사원',070605,080543);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(7675,'최광석','M','677675',"
						+ "'당진','010-222-4847','cks@cc.com','회사원',100356,050900);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(4533,'정한경','M','744533',"
						+ "'강릉','010-777-9630','jhk@ab.com','교수',070804,000601);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(5546,'유원현','M','765546',"
						+ "'대구','016-777-0214','ywh@cc.com','자영업',120309,070576);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(4543,'최재정','M','454543',"
						+ "'부산','010-555-4187','cjj@ab.com','회사원',070804,050101);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(9768,'이진희','F','119768',"
						+ "'서울','010-888-3675','ljh@ab.com','교수',130211,091001);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(4234,'오나미','F','234234',"
						+ "'속초','010-999-6541','onm@cc.com','학생',130211,091001);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(7643,'송성묵','M','987643',"
						+ "'서울','010-222-5874','ssm@bb.com','학생',071018,062019);");
				//추가
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2346,'송영달','M','232153',"
						+ "'서울','010-521-7165','rqw@ab.com','자영업',050302,980312);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2347,'장옥분','F','912325',"
						+ "'서울','010-161-4762','wer@ab.com','회사원',040089,020403);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2348,'최윤정','F','812395',"
						+ "'속초','010-291-0092','pwer@ab.com','교수',070605,080543);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2349,'송나희','F','291283',"
						+ "'서울','010-412-2302','nahi@cb.com','학생',100356,050900);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2350,'윤규진','M','201238',"
						+ "'강릉','010-521-1293','haha@bb.com','학생',070804,000601);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2351,'송준선','M','290243',"
						+ "'당진','011-102-7943','wje@ab.com','회사원',120309,070576);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2352,'송가희','F','302932',"
						+ "'부산','010-943-0123','wps@ab.com','교수',070804,050101);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2353,'양치수','M','323732',"
						+ "'속초','010-219-1290','sdl@cc.com','자영업',130211,091001);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2354,'성현경','F','902382',"
						+ "'서울','010-012-0039','qla@bb.com','회사원',130211,091001);");
				stmt.execute("insert into patients(pat_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job, Nurses_nur_id, Doctors_doc_id) values(2355,'윤재석','M','923023',"
						+ "'부산','010-921-1295','dls@ab.com','학생',050302,980312);");
				
				//** Treatments
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(130516023, 980312, '감기, 몸살','2013-05-16',2345);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(130628100, 020403, '피부 트러블 치료', '2013-06-28',3545);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(131205056, 080543, '목 디스크로 MRI 촬영', '2013-12-05',3424 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(131218024, 050900, '중이염', '2013-12-18',7675 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(131224012, 000601, '장염', '2013-12-24',4533 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(140103001, 070576, '여드름 치료','2014-01-03',5546 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(140109026, 050101, '위염', '2014-01-09',4543 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(140226102, 091001, '화상치료','2014-02-26',9768 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(140303003, 091001, '교통사고 외상치료', '2014-03-03',4234 );");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(140308087, 062019, '장염', '2014-03-08',7643 );");
				//추가
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1502031020, 980312, '기침','2015-02-03',2346);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1205160023, 020403, '여드름 치료', '2012-05-16',2347);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1203120234, 080543, '허리 디스크로 MRI 촬영', '2012-03-12',2348);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1212120212, 050900, '외이도염', '2012-12-12',2349);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1405019123, 000601, '장염', '2014-05-01',2350);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1504281943, 070576, '피부 트러블 치료', '2015-04-28',2351);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1602120323, 050101, '위염', '2016-02-12',2352);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1301081243, 091001, '교통사고 외상치료', '2013-01-08',2353);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1508259234, 091001, '화상치료', '2015-08-25',2354);");
				stmt.execute("insert into treatments(treat_id, Doctors_doc_id, treat_contents, treat_date, Patients_pat_id) value(1402110912, 062019, '장염', '2014-02-11',2355 );");

				
				//**charts
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('1',130516023, 980312,2345,050302,'차트1');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('2',130628100, 020403,3545,040089,'차트2');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('3',131205056, 080543,3424,070605,'차트3');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('4',131218024, 050900,7675,100356,'차트4');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('5',131224012, 000601,4533,070804,'차트5');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('6',140103001, 070576,5546,120309,'차트6');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('7',140109026, 050101,4543,070804,'차트7');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('8',140226102, 091001,9768,130211,'차트8');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('9',140303003, 091001,4234,130211,'차트9');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('10',140308087, 062019,7643,071018,'차트10' );");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('11',1502031020, 980312,2346,050302,'차트11' );");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('12',1205160023, 020403,2347,040089,'차트12' );");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('13',1203120234, 080543,2348,070605, '차트13');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('14',1212120212, 050900,2349,100356, '차트14');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('15',1405019123, 000601,2350,070804, '차트15');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('16',1504281943, 070576,2351,120309, '차트16');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('17',1602120323, 050101,2352,070804, '차트17');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('18',1301081243, 091001,2353,130211, '차트18');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('19',1508259234, 091001,2354,130211, '차트19');");
				stmt.execute("insert into charts(chart_id, Treatments_treat_id, Treatments_Doctors_doc_id, Treatments_Patients_pat_id, Nurses_nur_id, chart_contents) value('20',1402110912 ,062019,2355,071018,'차트20');");
			
				System.out.println("초기화 완료!");
			}
			
		} catch (SQLException e1) {
			System.out.println("쿼리 읽기 실패: "+e1);
			e1.printStackTrace();
		}	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Final finalterm = new Final();
	}

	

}
