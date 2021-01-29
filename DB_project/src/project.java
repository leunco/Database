
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class project extends JFrame{
   
   Connection con;
   Statement stmt;
   ResultSet rs;
   
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";
   
   Mpanel mp = new Mpanel();
   Upanel up = new Upanel();
   
   Boolean[] carBoolean = new Boolean[30]; 		// �ʱⰪ�� false(�뿩�Ұ���)
   Boolean[] repairBoolean = new Boolean[30];	// �ʱⰪ false(�������ʿ�)
   
   
   public boolean CheckNumber(String str){
		char check;
		
		if(str.equals(""))
		{	
			return false;
		}
		
		for(int i = 0; i<str.length(); i++){
			check = str.charAt(i);
			if( check < 48 || check > 58)
			{
				
				return false;
			}
			
		}		
		return true;
	}
   
   class Mpanel extends JPanel implements ActionListener{
	      JLabel manager = new JLabel("������");
	      JButton initB = new JButton("�ʱ�ȭ");
	      
	      JPanel northMP = new JPanel();
	      JPanel northMP0 = new JPanel();
	      JPanel northMP1 = new JPanel();
	      JPanel northMP2 = new JPanel();
	      JPanel northMP3 = new JPanel();
	      JPanel carReturnP = new JPanel();
	      JPanel carRepairP = new JPanel();
	      
	      JPanel sP1 = new JPanel();
	      JPanel sP2 = new JPanel();
	      JPanel sP3 = new JPanel();
	      JPanel sP4 = new JPanel();
	      
	      JPanel sP = new JPanel();
	      JPanel resultP = new JPanel();
  
	      JLabel company = new JLabel("ķ��ī�뿩ȸ��");
	      
	      // ȸ�� �Է�
	      JLabel comp_namel = new JLabel("ȸ�� �̸�");
	      JTextField comp_namef = new JTextField(8);
	      JLabel comp_addressl = new JLabel("�ּ�");
	      JTextField comp_addressf = new JTextField(7);
	      JLabel comp_phonel = new JLabel("��ȭ��ȣ");
	      JTextField comp_phonef = new JTextField(10);
	      JLabel comp_personl = new JLabel("����� �̸�");
	      JTextField comp_personf = new JTextField(7);
	      JLabel comp_emaill = new JLabel("�̸���");
	      JTextField comp_emailf = new JTextField(13);
	      JButton comp_insertB = new JButton("ȸ�� �Է�");
	      JLabel comp_idl = new JLabel("ȸ�� ID");
	      JTextField comp_idf = new JTextField(3);
	      JButton comp_convertB = new JButton("ȸ�� ����");
	      JButton comp_deleteB = new JButton("ȸ�� ����");
	      
	      
	      // ķ��ī �Է�
	      JLabel car_comp_idl = new JLabel("ȸ�� ID");
	      JTextField car_comp_idf = new JTextField(3);
	      JLabel car_namel = new JLabel("ķ��ī �̸�");
	      JTextField car_namef = new JTextField(10);
	      JLabel car_numl = new JLabel("ķ��ī ��ȣ");
	      JTextField car_numf = new JTextField(10);
	      JLabel car_boardl = new JLabel("�����ο���");
	      JTextField car_boardf = new JTextField(3);
	      JLabel car_manucompl = new JLabel("����ȸ��");
	      JTextField car_manucompf = new JTextField(5);
	      JLabel car_manudatel = new JLabel("��������");
	      JTextField car_manudatef = new JTextField(7);
	      JLabel car_distancel = new JLabel("��������Ÿ�");
	      JTextField car_distancef = new JTextField(4);
	      JLabel car_pricel = new JLabel("�뿩���");
	      JTextField car_pricef = new JTextField(5);
	      JLabel car_datel = new JLabel("�������");
	      JTextField car_datef = new JTextField(7);
	      JButton car_insertB = new JButton("ķ��ī �Է�");
	      JLabel car_idl = new JLabel("ķ��ī ID");
	      JTextField car_idf = new JTextField(3);
	      JButton car_deleteB = new JButton("ķ��ī ����");
	      JButton car_convertB = new JButton("ķ��ī ����");

	      
	      // �� ����
	      JLabel cust_idl = new JLabel("������������ȣ");
	      JTextField cust_idf = new JTextField(10);
	      JLabel cust_namel = new JLabel("����");
	      JTextField cust_namef = new JTextField(7);
	      JLabel cust_addressl = new JLabel("�� �ּ�");
	      JTextField cust_addressf = new JTextField(7);
	      JLabel cust_phonel = new JLabel("�� ��ȭ��ȣ");
	      JTextField cust_phonef = new JTextField(10);
	      JLabel cust_emaill = new JLabel("�� �̸���");
	      JTextField cust_emailf = new JTextField(10);
	      JButton insert_cust = new JButton("�� �Է�");
	      JButton delete_cust = new JButton("�� ����"); //���� ������������ȣ�� ���� cust_idl, cust_idf ����
	      JButton convert_cust = new JButton("�� ����");
	      
	      // ����� garage
	      JLabel gar_namel = new JLabel("����Ҹ�");
	      JTextField gar_namef = new JTextField(8);
	      JLabel gar_addressl = new JLabel("����� �ּ�");
	      JTextField gar_addressf = new JTextField(7);
	      JLabel gar_phonel = new JLabel("����� ��ȭ��ȣ");
	      JTextField gar_phonef = new JTextField(10);
	      JLabel gar_personl = new JLabel("����� �̸�");
	      JTextField gar_personf = new JTextField(7);
	      JLabel gar_emaill = new JLabel("�̸���");
	      JTextField gar_emailf = new JTextField(10);
	      JButton insert_gar = new JButton("����� �Է�");
	      JLabel gar_idl = new JLabel("����� ID");
	      JTextField gar_idf = new JTextField(3);
	      JButton delete_gar = new JButton("����� ����");
	      JButton convert_gar = new JButton("����� ����");
	      
	      
	      // ķ��ī ��ȯ
	      JLabel inspec_rentl = new JLabel("�뿩��ȣ");
	      JTextField inspec_rentf = new JTextField(3);
	      JLabel inspec_carl = new JLabel("ķ��ī ID");
	      JTextField inspec_carf = new JTextField(3);
	      JLabel inspec_frontl = new JLabel("�պκ�");
	      JTextField inspec_frontf = new JTextField(8);
	      JLabel inspec_leftl = new JLabel("���ʺκ�");
	      JTextField inspec_leftf = new JTextField(8);
	      JLabel inspec_rightl = new JLabel("�����ʺκ�");
	      JTextField inspec_rightf = new JTextField(8);
	      JLabel inspec_behindl = new JLabel("�޺κ�");
	      JTextField inspec_behindf = new JTextField(8);
	      JLabel inspec_repairl = new JLabel("�����ʿ俩��");
	      JTextField inspec_repairf = new JTextField(3);
	      JButton return_car = new JButton("ķ��ī ��ȯ/���˳��� ����");
	      
	      
	      // ķ��ī �����Ƿ�
	      JLabel repair_idl= new JLabel("���񳻿� ID");
	      JTextField repair_idf = new JTextField(3);
	      JLabel repair_carl = new JLabel("ķ��ī ID");
	      JTextField repair_carf = new JTextField(3);
	      JLabel repair_garl = new JLabel("����� ID");
	      JTextField repair_garf = new JTextField(3);
	      JLabel repair_corpl = new JLabel("ȸ�� ID");
	      JTextField repair_corpf = new JTextField(3);
	      JLabel repair_custl = new JLabel("�� ������������ȣ");
	      JTextField repair_custf = new JTextField(10);
	      JLabel repair_infol = new JLabel("���񳻿�");
	      JTextField repair_infof = new JTextField(12);
	      JLabel repair_datel = new JLabel("������¥");
	      JTextField repair_datef = new JTextField(7);
	      JLabel repair_pricel = new JLabel("�������");
	      JTextField repair_pricef = new JTextField(5);
	      JLabel repair_paydatel = new JLabel("���Ա���");
	      JTextField repair_paydatef = new JTextField(7);
	      JLabel repair_extral = new JLabel("��Ÿ���񳻿�");
	      JTextField repair_extraf = new JTextField(10);
	      JButton request_car = new JButton("���� ���� �Է�");
	      JButton repair_delete = new JButton("���� ���� ����");
	      JButton repair_convert = new JButton("���� ���� ����");      
	      
	      // �˻����
	      JTextArea result = new JTextArea("");
	      
	      // �˻�1
	      JLabel s1_con1l = new JLabel("�뿩�Ⱓ��");
	      JTextField s1_con1f = new JTextField(3);
	      JLabel s1_con1l2 = new JLabel("�� �̻��̸鼭");
	      JLabel s1_con2l = new JLabel("���� ���� �ο�����");
	      JTextField s1_con2f = new JTextField(3);
	      JLabel s1_con2l2 = new JLabel("�̻��� �� ����");
	      JButton s1b = new JButton("�˻�");	      
	      
	      // �˻�2
	      JLabel s2_con1l = new JLabel("��ȯ�� �������ΰ�");
	      JTextField s2_con1f = new JTextField(3);
	      JLabel s2_con1l2 = new JLabel("�̸鼭(�ʿ�=1,���ʿ�=0)");
	      JLabel s2_con2l = new JLabel("�뿩�Ⱓ��");
	      JTextField s2_con2f = new JTextField(3);
	      JLabel s2_con2l2 = new JLabel("�̻��� ķ��ī ����");
	      JButton s2b = new JButton("�˻�");
	      
	      // �˻�3
	      JLabel s3_con1l = new JLabel("��������Ÿ���");
	      JTextField s3_con1f = new JTextField(5);
	      JLabel s3_con1l2 = new JLabel("km�̻��̸鼭");
	      JLabel s3_con2l = new JLabel("�뿩�Ⱓ��");
	      JTextField s3_con2f = new JTextField(3);
	      JLabel s3_con2l2 = new JLabel("�̻��� ķ��ī ����");
	      JButton s3b = new JButton("�˻�");

	      // �˻�4
	      JLabel s4_con1l = new JLabel("���� ����");
	      JTextField s4_con1f = new JTextField(3);
	      JLabel s4_con1l2 = new JLabel("�̸鼭");
	      JLabel s4_con2l = new JLabel("���������");
	      JTextField s4_con2f = new JTextField(5);
	      JLabel s4_con2l2 = new JLabel("�̻��� �� ����");
	      JButton s4b = new JButton("�˻�");
	      
	      public Mpanel(){
	         this.setBackground(new Color(194,247,255));
	         
	         // ��ü ���
	         northMP.setBackground(new Color(194,247,255));
	         
	         // ȸ��, ķ��ī, ��, �����
	         northMP0.setBackground(new Color(174,222,229));
	         northMP1.setBackground(new Color(174,222,229));
	         northMP2.setBackground(new Color(174,222,229));
	         northMP3.setBackground(new Color(174,222,229));
	         
	         // ķ��ī ��ȯ ����/���˳��� ����
	         carReturnP.setBackground(new Color(202,230,242));
	         
	         // ���񳻿�
	         carRepairP.setBackground(new Color(202,230,242));
	         
	         // �˻�
	         sP.setBackground(new Color(194,247,255));
	         sP1.setBackground(new Color(174,195,229));
	         sP2.setBackground(new Color(174,195,229));
	         sP3.setBackground(new Color(174,195,229));
	         sP4.setBackground(new Color(174,195,229));	         
	         
	         //�ʱ�ȭ��ư
	         initB.addActionListener(this);
	         
	         //ȸ��
	         comp_insertB.addActionListener(this);
	         comp_deleteB.addActionListener(this);
	         comp_convertB.addActionListener(this);
	         
	         //ķ��ī ���
	         car_insertB.addActionListener(this);
	         car_deleteB.addActionListener(this);
	         car_convertB.addActionListener(this);
	         
	         //��
	         insert_cust.addActionListener(this);
	         delete_cust.addActionListener(this);
	         convert_cust.addActionListener(this);
	         
	         //�����
	         insert_gar.addActionListener(this);
	         delete_gar.addActionListener(this);
	         convert_gar.addActionListener(this);
	         
	         //ķ��ī��ȯ/���˳��� ����
	         return_car.addActionListener(this);
	         
	         //���񳻿�
	         request_car.addActionListener(this);
	         repair_delete.addActionListener(this);
	         repair_convert.addActionListener(this);
	         
	         //�˻�
	         s1b.addActionListener(this);
	         s2b.addActionListener(this);
	         s3b.addActionListener(this);
	         s4b.addActionListener(this);	         
	         
	         manager.setPreferredSize(new Dimension(500,20));

	         northMP.add(manager);
	         northMP.add(initB); //�ʱ�ȭ
	         
	         // ȸ��
	         northMP0.add(comp_namel);
	         northMP0.add(comp_namef);
	         northMP0.add(comp_addressl);
	         northMP0.add(comp_addressf);
	         northMP0.add(comp_phonel);
	         northMP0.add(comp_phonef);
	         northMP0.add(comp_personl);
	         northMP0.add(comp_personf);
	         northMP0.add(comp_emaill);
	         northMP0.add(comp_emailf); 
	                  
	         northMP0.add(comp_insertB); //ȸ�� �Է¹�ư
	         northMP0.add(comp_idl);
	         northMP0.add(comp_idf);
	         northMP0.add(comp_deleteB); //ȸ�� ������ư
	         northMP0.add(comp_convertB); // ȸ�� �����ư
	         
	         // ķ��ī 
	         northMP1.add(car_comp_idl);
	         northMP1.add(car_comp_idf);
	         northMP1.add(car_namel);
	         northMP1.add(car_namef);
	         northMP1.add(car_numl);
	         northMP1.add(car_numf);
	         northMP1.add(car_boardl);
	         northMP1.add(car_boardf);
	         northMP1.add(car_manucompl);
	         northMP1.add(car_manucompf);
	         northMP1.add(car_manudatel);
	         northMP1.add(car_manudatef);
	         northMP1.add(car_distancel);
	         northMP1.add(car_distancef);
	         northMP1.add(car_pricel);
	         northMP1.add(car_pricef);
	         northMP1.add(car_datel);
	         northMP1.add(car_datef);
	         northMP1.add(car_insertB); //ķ��ī �Է¹�ư
	         northMP1.add(car_idl);
	         northMP1.add(car_idf);
	         northMP1.add(car_deleteB); //ķ��ī ������ư
	         northMP1.add(car_convertB); //ķ��ī �����ư
	         
	         // ��
	         northMP2.add(cust_idl);
	         northMP2.add(cust_idf);
	         
	         northMP2.add(cust_namel);
	         northMP2.add(cust_namef);
	         
	         northMP2.add(cust_addressl);
	         northMP2.add(cust_addressf);
	         
	         northMP2.add(cust_phonel);
	         northMP2.add(cust_phonef);
	         
	         northMP2.add(cust_emaill);
	         northMP2.add(cust_emailf);
	         
	         northMP2.add(insert_cust); //�� �Է� ��ư
	         northMP2.add(delete_cust); //�� ������ư
	         northMP2.add(convert_cust); //�� �����ư
	         
	         // �����
	         northMP3.add(gar_namel);
	         northMP3.add(gar_namef);
	         
	         northMP3.add(gar_addressl);
	         northMP3.add(gar_addressf);
	         
	         northMP3.add(gar_phonel);
	         northMP3.add(gar_phonef);
	         
	         northMP3.add(gar_personl);
	         northMP3.add(gar_personf);
	         
	         northMP3.add(gar_emaill);
	         northMP3.add(gar_emailf);
	         
	         northMP3.add(insert_gar); //����� �Է¹�ư
	         northMP3.add(gar_idl);
	         northMP3.add(gar_idf);
	         northMP3.add(delete_gar); //����� ������ư
	         northMP3.add(convert_gar); //����� �����ư
	         
	         // �� ��ȯ �г�
	         carReturnP.add(inspec_rentl);
	         carReturnP.add(inspec_rentf);
	         carReturnP.add(inspec_carl);
	         carReturnP.add(inspec_carf);
	         carReturnP.add(inspec_frontl);
	         carReturnP.add(inspec_frontf);
	         carReturnP.add(inspec_leftl);
	         carReturnP.add(inspec_leftf);
	         carReturnP.add(inspec_rightl);
	         carReturnP.add(inspec_rightf);
	         carReturnP.add(inspec_behindl);
	         carReturnP.add(inspec_behindf);
	         carReturnP.add(inspec_repairl);
	         carReturnP.add(inspec_repairf);
	         carReturnP.add(return_car); //����ȯ
	         
	         
	         // �� ���� ��û �г�   
	         carRepairP.add(repair_carl);
	         carRepairP.add(repair_carf);
	         carRepairP.add(repair_garl);
	         carRepairP.add(repair_garf);
	         carRepairP.add(repair_corpl);
	         carRepairP.add(repair_corpf);
	         carRepairP.add(repair_custl);
	         carRepairP.add(repair_custf);
	         carRepairP.add(repair_infol);
	         carRepairP.add(repair_infof);
	         carRepairP.add(repair_datel);
	         carRepairP.add(repair_datef);
	         carRepairP.add(repair_pricel);
	         carRepairP.add(repair_pricef);
	         carRepairP.add(repair_paydatel);
	         carRepairP.add(repair_paydatef);
	         carRepairP.add(repair_extral);
	         carRepairP.add(repair_extraf);
	         carRepairP.add(request_car); //���񳻿� �Է¹�ư
	         carRepairP.add(repair_idl);
	         carRepairP.add(repair_idf);
	         carRepairP.add(repair_delete); //���񳻿� ������ư
	         carRepairP.add(repair_convert); //���񳻿� �����ư
	         
	         
	         // �˻�1 �г�
	         sP1.add(s1_con1l);
	         sP1.add(s1_con1f);
	         sP1.add(s1_con1l2);
	         sP1.add(s1_con2l);
	         sP1.add(s1_con2f);
	         sP1.add(s1_con2l2);
	         sP1.add(s1b);
	         
	         // �˻�2 �г�
	         sP2.add(s2_con1l);
	         sP2.add(s2_con1f);
	         sP2.add(s2_con1l2);
	         sP2.add(s2_con2l);
	         sP2.add(s2_con2f);
	         sP2.add(s2_con2l2);
	         sP2.add(s2b);
	         
	         // �˻�3 �г�
	         sP3.add(s3_con1l);
	         sP3.add(s3_con1f);
	         sP3.add(s3_con1l2);
	         sP3.add(s3_con2l);
	         sP3.add(s3_con2f);
	         sP3.add(s3_con2l2);
	         sP3.add(s3b);
	         
	         // �˻�3 �г�
	         sP4.add(s4_con1l);
	         sP4.add(s4_con1f);
	         sP4.add(s4_con1l2);
	         sP4.add(s4_con2l);
	         sP4.add(s4_con2f);
	         sP4.add(s4_con2l2);
	         sP4.add(s4b);
	         
	         // �˻����
	         result.setEditable(false);
	         JScrollPane scroll = new JScrollPane(result);
	         resultP.add(scroll);         
	         
	         add(northMP);
	         northMP.setPreferredSize(new Dimension(580,40));
	         add(northMP0);
	         northMP0.setPreferredSize(new Dimension(580,70));
	         add(northMP1);
	         northMP1.setPreferredSize(new Dimension(580,95));
	         add(northMP2);
	         northMP2.setPreferredSize(new Dimension(580,70));
	         add(northMP3);
	         northMP3.setPreferredSize(new Dimension(580,100));
	         add(carReturnP);
	         carReturnP.setPreferredSize(new Dimension(580,70));
	         add(carRepairP);
	         carRepairP.setPreferredSize(new Dimension(580,125));
	         
	         add(sP1);
	         sP1.setPreferredSize(new Dimension(580,40));
	         add(sP2);
	         sP2.setPreferredSize(new Dimension(580,40));
	         add(sP3);
	         sP3.setPreferredSize(new Dimension(580,40));
	         add(sP4);
	         sP4.setPreferredSize(new Dimension(580,40));
	        
	         add(scroll);
	         scroll.setPreferredSize(new Dimension(580,200));
	         
	      }

	      
	      @Override
	      public void actionPerformed(ActionEvent e) {
	         int rowcount;
	         String query;
	          try {               
	              stmt = con.createStatement();
	               
	              	//�ʱ�ȭ ��ư
	                if(e.getSource()==initB) {
	                   System.out.println("�ʱ�ȭ ��ư Ŭ��!");
	                   
	                 stmt.execute("DROP DATABASE IF EXISTS  madang");
	                 stmt.execute("create database madang");
	                 stmt.execute("grant all privileges on madang.* to madang@localhost with grant option");
	                 stmt.execute("commit");
	                 
	                 // -- MySQL Workbench Forward Engineering
	                 
	                 stmt.execute("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0");
	                 stmt.execute("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0");
	                 stmt.execute("SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'");
	                 
	                 /* -- -----------------------------------------------------
						-- Schema madang
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ");
	                 stmt.execute("USE `madang` ");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Company`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Company` (\r\n" + 
	                 		"  `comp_id` INT NOT NULL,\r\n" + 
	                 		"  `comp_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_person` VARCHAR(45) NULL,\r\n" + 
	                 		"  `comp_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`comp_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Car`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Car` (\r\n" + 
	                 		"  `car_id` INT NOT NULL,\r\n" + 
	                 		"  `car_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_num` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_board` INT NULL,\r\n" + 
	                 		"  `car_manucomp` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_manudate` VARCHAR(45) NULL,\r\n" + 
	                 		"  `car_distance` INT NULL,\r\n" + 
	                 		"  `car_price` INT NULL,\r\n" + 
	                 		"  `car_date` DATE NULL,\r\n" + 
	                 		"  `Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`car_id`, `Company_comp_id`),\r\n" + 
	                 		"  INDEX `fk_Car_Company_idx` (`Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Car_Company`\r\n" + 
	                 		"    FOREIGN KEY (`Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Company` (`comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Customer`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Customer` (\r\n" + 
	                 		"  `cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  `cust_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `cust_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`cust_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Rent`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Rent` (\r\n" + 
	                 		"  `rent_id` INT NOT NULL,\r\n" + 
	                 		"  `rent_start` DATE NULL,\r\n" + 
	                 		"  `rent_period` INT NULL,\r\n" + 
	                 		"  `rent_price` INT NULL,\r\n" + 
	                 		"  `rent_paydate` DATE NULL,\r\n" + 
	                 		"  `rent_extra` VARCHAR(45) NULL,\r\n" + 
	                 		"  `rent_extrafee` INT NULL,\r\n" + 
	                 		"  `Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  `Customer_cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`rent_id`, `Car_car_id`),\r\n" + 
	                 		"  INDEX `fk_Rent_Car1_idx` (`Car_Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Rent_Customer1_idx` (`Customer_cust_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Rent_Car1`\r\n" + 
	                 		"    FOREIGN KEY (`Car_Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Car` (`Company_comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Rent_Customer1`\r\n" + 
	                 		"    FOREIGN KEY (`Customer_cust_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Customer` (`cust_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Inspection`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Inspection` (\r\n" + 
	                 		"  `frontinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `leftinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `rightinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `behindinfo` VARCHAR(45) NULL,\r\n" + 
	                 		"  `repairinfo` TINYINT NULL,\r\n" + 
	                 		"  `Rent_rent_id` INT NOT NULL,\r\n" + 
	                 		"  `Rent_Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  INDEX `fk_Inspection_Rent1_idx` (`Rent_rent_id` ASC, `Rent_Car_car_id` ASC) VISIBLE,\r\n" + 
	                 		"  PRIMARY KEY (`Rent_rent_id`),\r\n" + 
	                 		"  CONSTRAINT `fk_Inspection_Rent1`\r\n" + 
	                 		"    FOREIGN KEY (`Rent_rent_id` , `Rent_Car_car_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Rent` (`rent_id` , `Car_car_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Garage`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Garage` (\r\n" + 
	                 		"  `gar_id` INT NOT NULL,\r\n" + 
	                 		"  `gar_name` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_address` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_phone` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_person` VARCHAR(45) NULL,\r\n" + 
	                 		"  `gar_email` VARCHAR(45) NULL,\r\n" + 
	                 		"  PRIMARY KEY (`gar_id`))");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 /* -- -----------------------------------------------------
						-- Table `madang`.`Repair`
						-- -----------------------------------------------------*/
	                 stmt.execute("CREATE TABLE IF NOT EXISTS `madang`.`Repair` (\r\n" + 
	                 		"  `repair_id` INT NOT NULL,\r\n" + 
	                 		"  `repair_info` VARCHAR(45) NULL,\r\n" + 
	                 		"  `repair_date` DATE NULL,\r\n" + 
	                 		"  `repair_price` INT NULL,\r\n" + 
	                 		"  `repair_paydate` DATE NULL,\r\n" + 
	                 		"  `repair_extra` VARCHAR(45) NULL,\r\n" + 
	                 		"  `Garage_gar_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_car_id` INT NOT NULL,\r\n" + 
	                 		"  `Car_Company_comp_id` INT NOT NULL,\r\n" + 
	                 		"  `Customer_cust_id` VARCHAR(45) NOT NULL,\r\n" + 
	                 		"  PRIMARY KEY (`repair_id`),\r\n" + 
	                 		"  INDEX `fk_Repair_Garage1_idx` (`Garage_gar_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Repair_Car1_idx` (`Car_car_id` ASC, `Car_Company_comp_id` ASC) VISIBLE,\r\n" + 
	                 		"  INDEX `fk_Repair_Customer1_idx` (`Customer_cust_id` ASC) VISIBLE,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Garage1`\r\n" + 
	                 		"    FOREIGN KEY (`Garage_gar_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Garage` (`gar_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Car1`\r\n" + 
	                 		"    FOREIGN KEY (`Car_car_id` , `Car_Company_comp_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Car` (`car_id` , `Company_comp_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION,\r\n" + 
	                 		"  CONSTRAINT `fk_Repair_Customer1`\r\n" + 
	                 		"    FOREIGN KEY (`Customer_cust_id`)\r\n" + 
	                 		"    REFERENCES `madang`.`Customer` (`cust_id`)\r\n" + 
	                 		"    ON DELETE NO ACTION\r\n" + 
	                 		"    ON UPDATE NO ACTION)");
	                 //stmt.execute("ENGINE = InnoDB");
	                 
	                 
	                 stmt.execute("SET SQL_MODE=@OLD_SQL_MODE");
	                 stmt.execute("SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS");
	                 stmt.execute("SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS");
	                 
	                 // company Ʃ�� �߰�
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(1,'����ķ��','���� ������','010-1253-5995','���ڿ�','gocamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(2,'ķ���ϸ�ķ��ī','���� ��õ��','010-2954-1935','��浿','gildong@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(3,'�Բ�ķ��','��õ ����','010-8424-5357','���ϴ�','together@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(4,'��Ÿķ��','���� ������','010-7979-2462','���켺','starcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(5,'ģ����ķ��','��⵵ ������','010-6423-1112','�屸��','friendcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(6,'�������ڴ¿���','��⵵ ���ý�','010-9669-7985','�ڹμ�','sleepcamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(7,'ķ��ī�¿����','�λ� �ؿ��','010-2674-8831','�����','herecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(8,'�ٰ���ķ��ī','������ ����','010-1335-7524','��θ�','moducamping@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(9,'��ķ��','����ϵ� ����','010-3345-7511','�̼���','somecamp@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(10,'�۽�Ʈķ��ī','���� ��������','010-0023-0543','�ڼ���','firstcamp@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(11,'��ķ��ī��Ʈ','��⵵ ȭ����','010-1168-9975','�ѳ���','newrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(12,'������ķ��ī','�λ� �߱�','010-5555-6654','������','goodcamping@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(13,'������ķ��','��û�ϵ� û�ֽ�','010-1128-8975','���̽�','llullu@naver.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(14,'��ġķ��ī��Ʈ','��⵵ ����','010-3353-5542','��ä��','matchrent@gmail.com');");
	                 stmt.executeUpdate("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email) values(15,'�ö��ķ��ī','���� ���α�','010-3377-5456','�̿켺','flowercamping@naver.com');");
	                 
	                 // customer Ʃ�� �߰�
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values ('12-24-123456-02', '�Ѱ���', '�λ�', '010-1234-2523', 'hanriver@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-20-578234-23' , '������', '����', '010-2032-1242', 'strong14@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-15-234923-25', '������', '���', '010-4823-2932', 'jindong@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('12-29-363106-74', '�۹̶�', '�λ�', '010-2258-1125', 'ssong@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-49-295264-19', '������', '���', '010-2592-2106', 'zizi@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('17-21-754167-55', '���¿�', '����', '010-9823-8216', 'bagegar@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('16-82-024902-21', '���ٰ�', '�泲', '010-2482-9325', 'jennie@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('15-52-430802-11', '����', '���', '010-3552-1051', 'jeolmi@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('18-76-080706-66', '������', '����', '010-3203-0203', 'hand31@bs.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('19-41-104824-25', '�����', '���', '010-2062-0432', 'jaesueobs@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('20-15-429232-62', '������', '�泲', '010-7869-2914', 'gyugyu@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('21-52-203192-25', '��ȿ��', '����', '010-2033-2932', 'eomwo@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-23-527823-23', '���ؿ�', '����', '010-2402-1024', 'noans@ls.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('11-39-293826-72', '������', '����', '010-3406-2460', 'min51@as.com');");
	                 stmt.executeUpdate("insert into customer (cust_id, cust_name, cust_address, cust_phone, cust_email) values('13-21-391283-52', '���α�', '���', '010-7632-1851', 'sseule@bs.com');");
	                 
	                 // garage Ʃ�� �߰�
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (1,'���ڻ�ī�����', '����', '010-1025-2012', '������', 'ask@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (2,'�츮ī����', '���', '010-1144-1234', '������', 'ksr@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (3,'�ѵ��ڵ��������', '�λ�', '010-2524-1414', '���ؿ�', 'lij@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (4,'�Ǳ��ڵ��������', '����', '010-2020-1919', '�缮��', 'sd@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (5,'����ī��Ÿ', '���', '010-1717-7171', 'ä��ȭ', 'wes@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (6,'ī�ý��ڵ��������', '�泲', '010-1515-5151', '���λ�', 'kjw@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (7,'�޸�ī', '���', '010-1616-6161', '������', 'a12@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (8,'ȭ��˻���������', '����', '010-1414-4141', '������', 'hgj@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (9,'�Ѿ��ڵ������������', '����', '010-1313-3131', '�뼮��', 'sk1s@bb.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (10,'�ϴ�ī����', '���', '010-2323-3232', '��ܿ�', 'bgh@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (11,'�¾�ī����', '����', '010-5252-2324', '��ġȫ', 'q21rd3@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (12,'����ī����', '���', '010-6363-3636', '������', 'djth@ab.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (13,'����ī�����', '����', '010-3737-7373', '�߹���', 'jiaqdos@cc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (14,'�ڵ�������', '���', '010-2929-9292', '�㼱��', 'sfdklj@abc.com');");
	                 stmt.executeUpdate("insert into garage (gar_id, gar_name, gar_address, gar_phone, gar_person, gar_email) values (15,'����ī����', '�λ�', '010-1529-3921', '������', 'uqpr@bb.com');");
	                  
	                 // car Ʃ�� �߰�
	                 stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(1,'car1','23�� 1234',5,'����','2016',1000,11000,'2020-06-06',1);");
	                  carBoolean[1]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(2,'car2','19�� 2634',6,'���','2014',7000,10000,'2020-06-13',2);");            
	                  carBoolean[2]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(3,'car3','15�� 9231',3,'����','2015',5000,16000,'2020-06-20',3);");
	                  carBoolean[3]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(4,'car4','12�� 4712',8,'����','2016',10000,8500,'2020-06-27',4);");             
	                  carBoolean[4]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(5,'car5','25�� 0293',7,'Jeep','2016',8000,14000,'2020-06-12',5);");             
	                  carBoolean[5]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(6,'car6','21�� 1012',8,'���','2013',40000,9000,'2020-06-01',6);");
	                  carBoolean[6]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(7,'car7','17�� 1539',9,'����','2018',1000,10000,'2020-06-05',7);");
	                  carBoolean[7]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                   		+ "values(8,'car8','24�� 1278',5,'����','2017',7000,7600,'2020-06-12',8);");
	                  carBoolean[8]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                    		+ "values(9,'car9','10�� 7203',4,'����','2015',17000,9500,'2020-06-24',9);");
	                  carBoolean[9]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(10,'car10','13�� 8129',3,'����','2016',19000,10000,'2020-06-30',10);");
	                  carBoolean[10]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(11,'car11','27�� 4822',8,'����','2013',47000,18000,'2020-06-08',11);");
	                  carBoolean[11]=true;
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                     		+ "values(12,'car12','22ȣ 4831',6,'Jeep','2015',27000,19000,'2020-06-07',12);");
	                  carBoolean[12]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(13,'car13','17�� 6362',3,'Jeep','2015',27500,17500,'2020-06-19',13);");
	                  carBoolean[13]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(14,'car14','26�� 1243',8,'����','2017',22000,10000,'2020-06-27',14);");
	                  carBoolean[14]=true;             
	                  stmt.executeUpdate("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id) "
	                  		+ "values(15,'car15','22�� 1920',4,'��Ŀ����','2018',18500,13000,'2020-06-25',15);");
	                  carBoolean[15]=true;
	                  
	                  // ķ��ī �뿩
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                       +"values(1,'2020-05-05',2,30000,'2020-05-03','���',2000,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(2,'2020-05-07',2,30000,'2020-05-05','���',1000,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(3,'2020-05-10',3,40000,'2020-05-08','���',2000,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(4,'2020-05-11',2,35000,'2020-05-09','����Ŀ',12000,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        +"values(5,'2020-05-06',4,50000,'2020-05-04','�ϸ��ī',5000,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(6,'2020-05-21',3,40000,'2020-05-19','��',3000,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(7,'2020-05-15',2,30000,'2020-05-13','����',7000,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(8,'2020-05-17',4,50000,'2020-05-15','���',2000,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(9,'2020-05-18',3,40000,'2020-05-16','�ϸ��ī',5000,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                         +"values(10,'2020-05-13',2,20000,'2020-05-11','���',3000,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(11,'2020-05-26',3,30000,'2020-05-24','����',2500,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(12,'2020-05-23',4,50000,'2020-05-21','��',5000,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(13,'2020-05-30',2,20000,'2020-05-28','����',6000,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(14,'2020-05-03',2,20000,'2020-05-01','���',2000,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into rent (rent_id, rent_start, rent_period, rent_price, rent_paydate, rent_extra, rent_extrafee, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                          +"values(15,'2020-05-21',3,35000,'2020-05-19','�ϸ��ī',3500,15,15,'21-52-203192-25');");               
	                  
	                  // inspection Ʃ�� �߰�                  
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                  			+ "values('��ũ��ġ','�̻� ��','�̻� ��','�̻� ��',true,1,1);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','��ũ��ġ','�̻� ��','�̻� ��',true,2,2);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('�̻� ��','�̻� ��','��ũ��ġ','�Ĺ����� ����',true,3,3);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','�̻� ��','�̻� ��','�̻� ��',true,4,4);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','��ũ��ġ','�̻� ��','�̻� ��',true,5,5);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('�̻� ��','�̻� ��','�̻� ��','���� ������',true,6,6);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('������ ����','�̻� ��','�̻� ��','�̻� ��',true,7,7);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('�̻� ��','�̻� ��','�̻� ��','Ʈ��ũ ����',true,8,8);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','�̻� ��','�̻� ��','�̻� ��',true,9,9);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','�̻� ��','�̻� ��','�̻� ��',true,10,10);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('�̻� ��','�� ����','�̻� ��','�̻� ��',true,11,11);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','�̻� ��','�� ����','�̻� ��',true,12,12);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('����Ʈ ����','�̻� ��','�̻� ��','�̻� ��',true,13,13);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('�̻� ��','�̻� ��','�̻� ��','���� ����',true,14,14);");
	                  stmt.executeUpdate("insert into inspection (frontinfo, leftinfo, rightinfo, behindinfo, repairinfo, Rent_rent_id, Rent_Car_car_id) "
	                    		+ "values('��ũ��ġ','��ũ��ġ','��ũ��ġ','�̻� ��',true,15,15);");
	                  
	                  // ���񳻿�
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(1,'��̷� ��ü,��ũ��ġ����','2020-05-10',20000,'2020-05-12','�������˼���',1,1,1,'11-20-578234-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(2,'�Ĺ����� ��ü','2020-05-12',60000,'2020-05-14','���Ż�뼭��',2,2,2,'11-23-527823-23');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(3,'�������� Ż����,��ũ��ġ����','2020-05-16',70000,'2020-05-18','�������˼���',3,3,3,'11-39-293826-72');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(4,'������ �������� Ż����,��ũ��ġ����','2020-05-16',30000,'2020-05-18','�������˼���',4,4,4,'12-24-123456-02');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(5,'�������� ��ȯ,��ũ��ġ����','2020-05-13',50000,'2020-05-15','���Ż�뼭��',5,5,5,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(6,'�ڹ��� ��ü','2020-05-27',100000,'2020-05-29','�������˼���',6,6,6,'13-15-234923-25');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(7,'������ ��ü','2020-05-20',20000,'2020-05-22','�������˼���',7,7,7,'13-21-391283-52');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(8,'Ʈ��ũ ����','2020-05-24',20000,'2020-05-22','�������˼���',8,8,8,'15-49-295264-19');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(9,'�������� ��ü,��ũ��ġ����','2020-05-24',50000,'2020-05-26','���Ż�뼭��',9,9,9,'15-52-430802-11');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(10,'�չ��� ��ü,��ũ��ġ����','2020-05-18',70000,'2020-05-20','�������˼���',10,10,10,'12-29-363106-74');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(11,'�� ��ü','2020-05-28',30000,'2020-05-30','���Ż�뼭��',11,11,11,'16-82-024902-21');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(12,'�� ��ü, ��ũ��ġ����','2020-05-30',50000,'2020-06-01','���Ż�뼭��',12,12,12,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(13,'����Ʈ ��ü','2020-06-04',20000,'2020-06-06','�������˼���',13,13,13,'17-21-754167-55');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(14,'�ڹ��� ��ü','2020-05-08',50000,'2020-05-10','�������˼���',14,14,14,'20-15-429232-62');");
	                  stmt.executeUpdate("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id) "
	                        + "values(15,'������ �������� Ż����,��ũ��ġ����','2020-05-27',50000,'2020-05-29','���Ż�뼭��',15,15,15,'21-52-203192-25');");
	                  
	                  System.out.println("�ʱ�ȭ �Ϸ�!");
	                }
	                
	                //ȸ�� �Է¹�ư
	                else if(e.getSource()==comp_insertB) {
	                	System.out.println("ȸ�� �Է¹�ư Ŭ��!");
	                	
	                	Boolean isError = false;
	                	Boolean phoneError = false;
	                	Boolean emailError = false;
	                	int comp_idI;
	                	String comp_nameS = comp_namef.getText();
	                	String comp_addressS = comp_addressf.getText();
	                	String comp_phoneS = comp_phonef.getText();
	                	String comp_personS = comp_personf.getText();
	                	String comp_emailS = comp_emailf.getText();
	                   
	                   
	                	// ��ĭ �ִ��� Ȯ��
	                	if(comp_nameS.length()==0 || comp_addressS.length()==0 || comp_phoneS.length()==0 || comp_personS.length()==0 || comp_emailS.length()==0) {
	                		System.out.println("[�����߻� : ������ �Է� ����] ȸ�� ID�� ������ ��� ĭ�� �����͸� �Է����ּ���.");
	               			isError=true;
	                	}
	                	else {
	                		
	                		// phone ���� Ȯ��
	                		String p1, p2, p3;
				            StringTokenizer st = new StringTokenizer(comp_phoneS,"-");
				            p1=st.nextToken();
				            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
				            if(!st.hasMoreTokens())  phoneError=true;
				            else{
				            	p2=st.nextToken();
				            	if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
				            	if(!st.hasMoreTokens()) phoneError=true;
				            	else {
				            		p3=st.nextToken();
				            		if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
				            	}
				            }
				       
				            if(phoneError) {
				            	System.out.println("[�����߻� : ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
				        		isError=true;
				             }
				             
				            // email ���� Ȯ��
				            String e1,e2=null,e3=null;
				            st = new StringTokenizer(comp_emailS,"@");
				            e1=st.nextToken();
				            if(!st.hasMoreTokens())  emailError=true;
				            else {
				            	e2=st.nextToken();
				            	 
				            	st = new StringTokenizer(e2,".");
				            	if(!st.hasMoreTokens()) emailError=true;
				            	else{
				            		e2=st.nextToken();
				            		if(!st.hasMoreTokens()) emailError=true;
				            		else {
				            			e3=st.nextToken();
					            	}
					            }
				            	
				           }
				            
				           if(emailError) {
				        	   System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
				        	   isError=true;
				           }
		                   
				             
				           // ������ ������ ���� 
		                   if(!isError) {
		                	   query="SELECT * FROM company";
		                       rs=stmt.executeQuery(query);
		                       rs.last();
		                       comp_idI=rs.getInt(1)+1;
		                       
		                       PreparedStatement statement = null;
		                       statement = con.prepareStatement("insert into company (comp_id, comp_name, comp_address, comp_phone, comp_person, comp_email)"+"value(?,?,?,?,?,?)");
		              
		                       statement.setInt(1,comp_idI);
		                       statement.setString(2, comp_nameS);
		                       statement.setString(3, comp_addressS);
		                       statement.setString(4, comp_phoneS);
		                       statement.setString(5, comp_personS);
		                       statement.setString(6, comp_emailS);
		                       
		                	   System.out.println("��������");
		                	   statement.executeUpdate();
				             
		                	   System.out.println("ķ��ī �뿩ȸ�� �Է� �Ϸ�Ǿ����ϴ�.");
		                	   System.out.println("ȸ�� ID \t ȸ�� �̸� \t�ּ� \t��ȭ��ȣ \t\t������� \t�̸���");
		                   
		                	   rs=stmt.executeQuery(query);
		                	   rs.last();
		                   
		                	   String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)+ "\t" + rs.getString(5) + "\t" + rs.getString(6);
		                	   System.out.println(str);
		                    }
	                   }
	                }
	                
	                //ȸ�� ������ư
	                else if(e.getSource()==comp_deleteB) {
	                	System.out.println("ȸ�� ���� ��ư Ŭ��!");
	                	
	                	String comp_idS = comp_idf.getText();
	                	
	                	if(comp_idS.length()==0) {
	                   		System.out.println("[�����߻� : ������ �Է� ����] ȸ�� ID�� �����͸� �Է����ּ���.");
	                	}
	                	
	                	else {
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �������� �մϴ�.");
		                	}
		                	else{
		                		int comp_idI = Integer.parseInt(comp_idS);
			                	query = "SELECT * FROM company WHERE comp_id =" + comp_idS;
					        	rs = stmt.executeQuery(query);
					        	
					        	if(!rs.next()) { 
					        		System.out.println("[�����߻� : ȸ�� ID] �ش� ID�� ȸ�簡 �����ϴ�.");
					        	}
					        	else {
					        		int rentI;
					        		
					        		// �뿩 ���̺�
					        		String query1 = "SELECT * FROM rent WHERE Car_Company_comp_id =" + comp_idI;
						        	rs = stmt.executeQuery(query1);
						        	
						        	while(rs.next()) { 
						        		// ���� �������� ����
						        		Statement stmt2 = con.createStatement();
						        		rentI = rs.getInt(1);
						        		query = "DELETE FROM inspection WHERE Rent_rent_id = " + rentI;
							        	stmt2.executeUpdate(query);
						        	}
					        		
						        	// �뿩 ���̺��� ����
					        		query = "DELETE FROM rent WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// ���� �������� ����
					        		query = "DELETE FROM repair WHERE Car_Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);
					        		
					        		// ķ��ī ��Ͽ��� ����
					        		query = "DELETE FROM car WHERE Company_comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);	
					        		
					        		// ȸ�翡�� ����
					        		query = "DELETE FROM company WHERE comp_id = " + comp_idI;
					        		stmt.executeUpdate(query);		        		
					        		
					        		System.out.println("�ش� ID�� ȸ�� ������ �Ϸ�Ǿ����ϴ�.");
					        		
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
				                    
				                    System.out.println("ȸ�� ID \t ȸ���̸� ----����----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t �������� ���� -----");
				                       
				                    }
					        	}
		                	}
	                	}
	                } 
	                
	                //ȸ�� �����ư
	                else if(e.getSource()==comp_convertB) {
	                	System.out.println("ȸ�� ���� ��ư Ŭ��!");
	                	
	                	Boolean isError=false;
	                	Boolean phoneError=false;
	                	Boolean emailError=false;
	                	
	                	Boolean name=false;
	                	Boolean address=false;
	                	Boolean phone=false;
	                	Boolean person=false;
	                	Boolean email=false;
	                	
	                	StringTokenizer st;
	               	
	                	String comp_idS = comp_idf.getText();	
	                    String comp_nameS = comp_namef.getText();
	                    String comp_addressS = comp_addressf.getText();
	                    String comp_phoneS = comp_phonef.getText();
	                    String comp_personS = comp_personf.getText();
	                    String comp_emailS = comp_emailf.getText();
	                	
	                    //��ĭ�� �ִ��� Ȯ��
	                	if(comp_idS.length()==0 || (comp_nameS.length()==0 && comp_addressS.length()==0 && comp_phoneS.length()==0 && comp_personS.length()==0 && comp_emailS.length()==0)) {
	                   		System.out.println("[�����߻� : ������ �Է� ����] ȸ��IDĭ�� �ٲٰ���� �Ӽ�ĭ�� �����͸� �Է����ּ���.");
	                   		isError=true;
	                    }
	                	else {
	                		int comp_idI = Integer.parseInt(comp_idS);
	                		
		                	if(!CheckNumber(comp_idS)) {
		                		System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �������� �մϴ�.");
		                	}
		                	else{
			                	
			                    if(comp_nameS.length()!=0) {
			                    	name=true;
			                    }
			                    if(comp_addressS.length()!=0) {
			                    	address=true;
			                    }
			                    if(comp_personS.length()!=0) {
			                    	person=true;
			                    }
			                    
			                    // phone ���� Ȯ��
			                    if(comp_phoneS.length()!=0) {
						            String p1, p2, p3;
						            st = new StringTokenizer(comp_phoneS,"-");
						            p1=st.nextToken();
						            
						            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
						            
						            if(!st.hasMoreTokens())  phoneError=true;
						            else{
						            	p2=st.nextToken();
						            	if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
						            	
						            	if(!st.hasMoreTokens()) phoneError=true;
						            	else {
						            		p3=st.nextToken();
						            		if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
						            	 }
						             }
						       
						             if(phoneError) {
						            	 System.out.println("[�����߻� : ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email ���� Ȯ��
			                    if(comp_emailS.length()!=0) {
						             String e1,e2=null,e3=null;
						             st = new StringTokenizer(comp_emailS,"@");
						             e1=st.nextToken();
						             
						             if(!st.hasMoreTokens())  emailError=true;
						             else {
						            	 e2=st.nextToken();
						            	 
						            	 st = new StringTokenizer(e2,".");
						            	 if(!st.hasMoreTokens()) emailError=true;
						            	 else{
						            		 e2=st.nextToken();
						            		 
						            		 if(!st.hasMoreTokens()) emailError=true;
						            		 else {
								            	 e3=st.nextToken();
							            	 }
							            }
						            	
						             }
						             
						             if(emailError) {
						            	 System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                    
					            // ������ ������ ����
					            if(!isError) {
					            	if(name) {
						            	query = "UPDATE company SET comp_name='"+comp_nameS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					            	}
					            	if(address) {
						        		query = "UPDATE company SET comp_address='"+comp_addressS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					            	}
					        		if(phone) {
						        		query = "UPDATE company SET comp_phone='"+comp_phoneS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		if(person) {
						        		query = "UPDATE company SET comp_person='"+comp_personS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		if(email) {
						        		query = "UPDATE company SET comp_email='"+comp_emailS+"' WHERE comp_id=" + comp_idI;
						        		stmt.executeUpdate(query);
					        		}
					        		
					        		System.out.println("�ش� ID�� ȸ�� ���� ������ �Ϸ�Ǿ����ϴ�.");
					        		query="SELECT * FROM company WHERE comp_id >=" + 0;
				                    rs = stmt.executeQuery(query);
			
				                    
				                    System.out.println("ȸ�� ID \t ȸ���̸� ----����----");
			
				                    while(rs.next()) {
				                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t �������� ���� -----");
				                       
				                    }
			                    }
			                    
		                	}
	                	}
	                }
	                
	                //ķ��ī �Է¹�ư
	                else if(e.getSource()==car_insertB) {
	                	System.out.println("ķ��ī �Է¹�ư Ŭ��!");
	                	
	                	Boolean isError = false;
	                	Boolean numError = false;
	                	Boolean dateError =false;
	                	
	                	// �뿩ȸ�� ID
	                	String car_comp_idS = car_comp_idf.getText();
	                	int car_comp_idI=0;
	                	
	                	// ķ��ī �̸�
	                	String car_nameS = car_namef.getText();
	                	// ķ��ī ��� ID
	                	String car_numS = car_numf.getText();
	                	
	                	//ķ��ī ���� �ο���
	                	String car_boardS = car_boardf.getText();
	                	int car_boardI=0;
	                	
	                	// ����ȸ��
	                	String car_manucompS = car_manucompf.getText();
	                	// ��������
	                	String car_manudateS = car_manudatef.getText();
	                	
	                	//��������Ÿ�
	                	String car_distanceS = car_distancef.getText();
	                	int car_distanceI=0; 
	                	
	                	// �뿩���
	                	String car_priceS = car_pricef.getText();
	                	int car_priceI=0;
	                	
	                	// ķ��ī �������
	                	String car_dateS = car_datef.getText();
	                	
	                	// �� ���� ������ Ȯ��
	                	if(car_comp_idS.length()==0 || car_nameS.length()==0 || car_numS.length()==0 || car_boardS.length()==0 || car_manucompS.length()==0 
	                			|| car_manudateS.length()==0 || car_distanceS.length()==0 ||car_priceS.length()==0 || car_dateS.length()==0) {
	                		System.out.println("[�����߻� : ������ �Է� ����] ��� ĭ�� �����͸� �Է����ּ���.");
	                		isError=true;
	                	}
	                	else {

		                	// ȸ�� ID ���� Ȯ��
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �����Դϴ�.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[�����߻� : ȸ�� ID] �ش� ID�� ȸ�簡 �����ϴ�.");
					        		 isError=true;
					        	 }
				        	 }
				        	 
		                	// ������ȣ ���� Ȯ��
		                	StringTokenizer st;
		                	st = new StringTokenizer(car_numS," ");
			                String n1,n2;
			                	
			                n1=st.nextToken();
			                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
		                		|| !(n1.charAt(2)>='��' && n1.charAt(2)<='�R')) { 
			                	numError=true;
			       		
			                }
			                
			                else {
			                	if(!st.hasMoreTokens()) { numError=true; }
				                else{
				                	n2=st.nextToken();
				                	if(n2.length()!=4 || !CheckNumber(n2)) { numError=true; }
				                	else {
				                		query = "SELECT * FROM car WHERE car_num ='" + car_numS+"'";
				                			
								        rs = stmt.executeQuery(query);
								        if(rs.next()) {
								        	System.out.println("[�����߻� : ������ȣ] �ش� ������ȣ�� �̹� �����մϴ�.");
								        	isError=true;
								        }
				                	}
				                }
				            }
		                	
		                	if(numError) {
		                		System.out.println("[�����߻� : ������ȣ] ������ȣ ������ �߸��Ǿ����ϴ�. ����)OOX OOOO  �̶� O�� ����, X�� �ѱ�");
		                		isError=true;
		                	}
		                	
		          
		                	// ž���ο� ���� Ȯ��
		                	if(!CheckNumber(car_boardS)) { 
		                		isError=true;
		                		System.out.println("[�����߻� : �����ο�] �����ο���  ���ڷ� �Է��ؾ� �մϴ�.");
		                	}
		                	else {
		                		car_boardI=Integer.parseInt(car_boardS);
			                	if(car_boardI < 1) {
			                		System.out.println("[�����߻� : �����ο�] �����ο��� 1���̻� �̾�� �մϴ�.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// �������� ���� Ȯ��
		                	if(!CheckNumber(car_manudateS)) {
		                		System.out.println("[�����߻� : ��������] ���������� 4�ڸ� ���� YYYY �Դϴ�.");
				        		 isError=true;
				        		 dateError=true;
		                	}
		                	else {
		                		if(car_manudateS.length()!=4) {
		                			System.out.println("[�����߻� : ��������] ���������� 4�ڸ� ���� YYYY �Դϴ�.");
					        		 isError=true;
					        		 dateError=true;
		                		}
		                	}
		                	
		                	// ����Ÿ� ���� Ȯ��
				            if(!CheckNumber(car_distanceS)) { 
			                		isError=true;
			                		System.out.println("[�����߻� : ����Ÿ�] ����Ÿ��� ���� �����Դϴ�.");
			                }
				            else {
				            	 car_distanceI = Integer.parseInt(car_distanceS);
				            	 if(car_distanceI < 0) {
				            		 System.out.println("[�����߻� : ����Ÿ�] ����Ÿ��� 0�̻� �̾�� �մϴ�.");
				                	isError=true;
				            	}
				            }
				             
		                	// �뿩��� ���� Ȯ��
		                	if(!CheckNumber(car_priceS)) { 
		                		isError=true;
		                		System.out.println("[�����߻� : �뿩���] �뿩����� ���� �����Դϴ�.");
		                	}
		                	else {
		                		car_priceI = Integer.parseInt(car_priceS);
			                	if(car_priceI < 1) {
			                		System.out.println("[�����߻� : �뿩���] �뿩����� 1���̻� �̾�� �մϴ�.");
			                		isError=true;
			                	}
		                	}
		                	
		                	// ��ϳ�¥ ���� Ȯ��
		                	String Y, M, D;
				            int y,m,d;
				            st = new StringTokenizer(car_dateS,"-");
				            Y=st.nextToken();
				            
				            if(st.hasMoreTokens()) {
				            	M=st.nextToken();
				            	if(st.hasMoreTokens()) {
				            		D=st.nextToken();
						            y=Integer.parseInt(Y);
						            m=Integer.parseInt(M);
						            d=Integer.parseInt(D);
						            
						            if(m>12 || m <1 || d >31 || d<1) {
						            	System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
						        		isError=true;
						        		dateError=true;
						            }
					             }
				            	
					             else {
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
					             }
				             }
				            
				             else {
				            	 System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
				            	 isError=true;
				            	 dateError=true;
				             }
	                	
				             
				             // ��������<��ϳ�¥ ���� Ȯ��
				             if(!dateError) {
				            	 String Y1;
					             int y1;
					             
					             st = new StringTokenizer(car_dateS,"-");
					             Y1=st.nextToken();
					           
							     y1=Integer.parseInt(Y1);
							   
							     if(Integer.parseInt(car_manudateS)>y1) {
							    	 isError=true;
							    	 dateError=true;
							     }
							     
							     if(dateError) {
							    	 System.out.println("[�����߻� : ���������� ��ϳ�¥] ���������� ��ϳ�¥���� ���ſ����մϴ�.");
							     }
				             }
				             
				             // ������ ������ ���� 
				             if(!isError) {
				            	 query="SELECT * FROM car";
			                     rs=stmt.executeQuery(query);
			                     rs.last();
			                     
			                     int car_idI=rs.getInt(1)+1;
			                    
			                     PreparedStatement statement = null;
		                         statement = con.prepareStatement("insert into car (car_id,car_name,car_num,car_board,car_manucomp,car_manudate,car_distance,car_price,car_date, Company_comp_id)"+"value(?,?,?,?,?,?,?,?,?,?)");
		                    
		                         statement.setInt(1,car_idI);
		                         statement.setString(2, car_nameS);
		                         statement.setString(3, car_numS);
		                         statement.setInt(4, car_boardI);
		                         statement.setString(5,car_manucompS);
		                         statement.setString(6,car_manudateS);
		                         statement.setInt(7,car_distanceI);
		                         statement.setInt(8, car_priceI);
		                         statement.setDate(9, java.sql.Date.valueOf(car_dateS));
		                         statement.setInt(10,car_comp_idI);
		                             
		                         statement.executeUpdate();

			                     carBoolean[car_idI]=true;
			                       
			                     rs=stmt.executeQuery(query);
			                     rs.last();
			                     
			                     System.out.println("ķ��ī �Է� �Ϸ�Ǿ����ϴ�.");
			                     System.out.println("ȸ��ID \t �뿩���� ���� \tķ��īID \t���̸� \t������ȣ \t�����ο��� \t����ȸ�� \t������¥ \t����Ÿ� \t�뿩��� \t������� ");
			                     String str = rs.getInt(10) +"\t"+ carBoolean[car_idI] + "\t\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
			                                rs.getInt(4) + "\t" + rs.getString(5)+ "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t" +
			                                rs.getInt(8) + "\t" + rs.getDate(9);
			                      
			                     System.out.println(str);
			                      
			                  }
	                	}
			             
	                }
	                
	                //ķ��ī ������ư
	                else if(e.getSource()==car_deleteB) {
	                	System.out.println("ķ��ī ������ư Ŭ��!");
	                	
	                    String car_idS = car_idf.getText();
	                    
	                    // ��ĭ
	                    if(car_idS.length()==0) {
	                   		System.out.println("[�����߻� : ������ �Է� ����] ķ��ī ID�� �����͸� �Է����ּ���.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(car_idS)) {
		                		System.out.println("[�����߻� : ķ��ī ID] ķ��ī ID�� ���� �������� �մϴ�.");
		                	}
	                    	else {
			                   int car_idI = Integer.parseInt(car_idS);
			                   query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                   rs = stmt.executeQuery(query);
			                
			                   if(!rs.next()) {
			                	   System.out.println("[�����߻� : ķ��ī��� ID] �ش� ID�� ķ��ī�� �����ϴ�.");
			                   }
			                   else {
			                	   int rentI;
			                	   String query1 = "SELECT * FROM rent WHERE Car_car_id =" + car_idI;
			                	   rs = stmt.executeQuery(query1);
			                	
			                	   while(rs.next()) {
			                		   Statement stmt2 = con.createStatement();
			                		   rentI=rs.getInt(1);
			                		   query = "DELETE FROM inspection WHERE Rent_rent_id = " + rentI;
			                		   stmt2.executeUpdate(query);
			                		  
			                	   }
			                	  
			                	   
			                	   query = "DELETE FROM rent WHERE Car_car_id = " + car_idI;
			                	   stmt.executeUpdate(query);
			                	
			                
			                	   query = "DELETE FROM repair WHERE Car_car_id = " + car_idI;
			                	   stmt.executeUpdate(query);
			                	   
			                	
			                	   stmt.executeUpdate("SET foreign_key_checks = 0");
			                	
			                	   query = "DELETE FROM car WHERE car_id = " + car_idI;
			                	   stmt.executeUpdate(query);   
			                	
			                	   System.out.println("�ش� ID�� ķ��ī ������ �Ϸ�Ǿ����ϴ�.");
			                	   
			                	   stmt.executeUpdate("SET foreign_key_checks = 1");
			                    
			                    
			                	   query="SELECT * FROM car WHERE car_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("ķ��ī ID \t ķ��ī�̸� ----����----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" �������� ���� -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                // ķ��ī �����ư
	                else if(e.getSource()== car_convertB) {
	                	System.out.println("ķ��ī �����ư Ŭ��!");
	                	
	                	Boolean isError = false;
	                	Boolean numError = false;
	                	Boolean dateError =false;
	                	Boolean carError = false;
	                	
	                	Boolean name=false;
	                	Boolean num=false;
	                	Boolean board=false;
	                	Boolean manucomp=false;
	                	Boolean manudate=false;
	                	Boolean distance=false;
	                	Boolean price=false;
	                	Boolean date=false;
	                	
	                	String Y, M, D;
			            int y,m,d;
			            
	                	StringTokenizer st;
	                	
	                	String car_idS=car_idf.getText();
	                	int car_idI=0;
	                	
	                	String car_comp_idS = car_comp_idf.getText();
	                	int car_comp_idI=0;
	                	
	                	String car_nameS = car_namef.getText();
	                	String car_numS = car_numf.getText();
	                	
	                	String car_boardS = car_boardf.getText();
	                	int car_boardI=0;
	                	
	                	String car_manucompS = car_manucompf.getText();
	                	String car_manudateS = car_manudatef.getText();
	                	
	                	String car_distanceS = car_distancef.getText();
	                	int car_distanceI=0;
	                	
	                	String car_priceS = car_pricef.getText();
	                	int car_priceI=0;
	                	
	                	String car_dateS = car_datef.getText();
	                	
	                	// �� ���� ������ Ȯ��
	                	if(car_idS.length()==0 || car_comp_idS.length()==0 || (car_nameS.length()==0 && car_numS.length()==0 && car_boardS.length()==0 && car_manucompS.length()==0 
	                			&& car_manudateS.length()==0 && car_distanceS.length()==0 && car_priceS.length()==0 && car_dateS.length()==0)) {
	                		System.out.println("[�����߻� : ������ �Է� ����] ķ��īIDĭ�� ȸ��IDĭ�� �ٲٰ���� �Ӽ�ĭ�� �����͸� �Է����ּ���.");
	                   		isError=true;
	                	}
	                	else {
	                		// ķ��ī ID ����Ȯ��
	                		if(!CheckNumber(car_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[�����߻� : ķ��ī ID] ķ��ī ID�� ���� �����Դϴ�.");
		                	}
		                	else{
		                		car_idI=Integer.parseInt(car_idS);
		                		query = "SELECT * FROM car WHERE car_id =" + car_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[�����߻� : ķ��ī ID] �ش� ID�� ķ��ī�� �����ϴ�.");
					        		 isError=true;
					        		 carError = true;
					        	 }
					            
				        	 }

		                	// ȸ�� ID ���� Ȯ��
		                	if(!CheckNumber(car_comp_idS)) { 
		                		isError=true;
		                		carError = true;
		                		System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �����Դϴ�.");
		                	}
		                	else{
		                		car_comp_idI=Integer.parseInt(car_comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + car_comp_idI;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[�����߻� : ȸ�� ID] �ش� ID�� ȸ�簡 �����ϴ�.");
					        		 isError=true;
					        		 carError = true;
					        	 }
				        	 }
		                	
		                	 if(!carError) {
		                    	 car_idI = Integer.parseInt(car_idS);
		                    	 if(!carError) {
				                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
				                     rs = stmt.executeQuery(query);
				                     if(rs.next()) {
				                    	 int i = rs.getInt(10); 
				                    	 if(Integer.parseInt(car_comp_idS)!=i) {
				                    		 isError=true;
				                   		 System.out.println("[�����߻� : ȸ�� ID] ķ��ī ID�� ȸ�� ID�� ���� �����ʽ��ϴ�.");
				                    	 }
				                     } 	 
				                 } 
		                     }
		                	
		                	// ���̸� ����Ȯ��
		                	if(car_nameS.length()!=0) {
		                		name=true;
		                	}
				        	 
		                	// ������ȣ ���� Ȯ��
		                	if(car_numS.length()!=0) {
		                		st = new StringTokenizer(car_numS," ");
				                String n1,n2;
				                	
				                n1=st.nextToken();
				                if( n1.length()!=3 || !(n1.charAt(0)>='0' && n1.charAt(0)<='9') || !(n1.charAt(1)>='0' && n1.charAt(1)<='9')
			                		|| !(n1.charAt(2)>='��' && n1.charAt(2)<='�R')) { 
				                	numError=true;	
				                }
				                else {
				                	if(!st.hasMoreTokens()) { numError=true; }
				                	else{
				                		n2=st.nextToken();
				                		if(n2.length()!=4 || !CheckNumber(n2)) { numError=true; }
				                		else {
				                			query = "SELECT * FROM car WHERE car_num ='" + car_numS+"'";
					                		
				                			rs = stmt.executeQuery(query);
				                			if(rs.next()) {
				                				System.out.println("[�����߻� : ������ȣ] �ش� ������ȣ�� �̹� �����մϴ�.");
				                				isError=true;
									        }
					                	}
					                }
					            }
			                	
			                	if(numError) {
			                		System.out.println("[�����߻� : ������ȣ] ������ȣ ������ �߸��Ǿ����ϴ�. ����)OOX OOOO  �̶� O�� ����, X�� �ѱ�");
			                		isError=true;
			                	}
			                	else {
			                		num=true;
			                	}
		                	
		                	}
		   		                	
		                	// ž���ο� ���� Ȯ��
		                	if(car_boardS.length()!=0) {
			                	if(!CheckNumber(car_boardS)) { 
			                		isError=true;
			                		System.out.println("[�����߻� : �����ο�] �����ο��� ���� �����Դϴ�.");
			                	}
			                	else {
			                		car_boardI=Integer.parseInt(car_boardS);
				                	if(car_boardI < 1) {
				                		System.out.println("[�����߻� : �����ο�] �����ο��� 1���̻� �̾�� �մϴ�.");
				                		isError=true;
				                	}
				                	else {
				                		board=true;
				                	}
			                	}
		                	}
		                	
		                	// ����ȸ�� ���� Ȯ��
		                	if(car_manucompS.length()!=0) {
		                		manucomp=true;
		                	}
		                	
		                	// �������� ���� Ȯ��
		                	if(car_manudateS.length()!=0) {
		                		if(!CheckNumber(car_manudateS)) {
			                		System.out.println("[�����߻� : ��������] ���������� 4�ڸ� ���� YYYY �Դϴ�.");
					        		 isError=true;
					        		 dateError=true;
			                	}
			                	else {
			                		if(car_manudateS.length()!=4) {
			                			System.out.println("[�����߻� : ��������] ���������� 4�ڸ� ���� YYYY �Դϴ�.");
						        		 isError=true;
						        		 dateError=true;
			                		}
			                		else {
			                			manudate=true;
			                		}
			                	}
		                	}
		                	
		                	// ��������Ÿ� ���� Ȯ��
		                	if(car_distanceS.length()!=0) {
					             if(!CheckNumber(car_distanceS)) { 
				                		isError=true;
				                		System.out.println("[�����߻� : ��������Ÿ�] ��������Ÿ��� ���� �����Դϴ�.");
				                	}
				                	else {
				                		car_distanceI = Integer.parseInt(car_distanceS);
					                	if(car_distanceI < 0) {
					                		System.out.println("[�����߻� : ��������Ÿ�] ��������Ÿ��� 0�̻� �̾�� �մϴ�.");
					                		isError=true;
					                	}
					                	else {
					                		distance=true;
					                	}
				                	}
		                	}
		                	
		                	// �뿩��� ���� Ȯ��
		                	if(car_priceS.length()!=0) {
			                	if(!CheckNumber(car_priceS)) { 
			                		isError=true;
			                		System.out.println("[�����߻� : �뿩���] �뿩����� ���� �����Դϴ�.");
			                	}
			                	else {
			                		car_priceI = Integer.parseInt(car_priceS);
				                	if(car_priceI < 1) {
				                		System.out.println("[�����߻� : �뿩���] �뿩����� 1���̻� �̾�� �մϴ�.");
				                		isError=true;
				                	}
				                	else {
				                		price=true;
				                		
				                	}
			                	}
		                	}
		                	
		                	// ��ϳ�¥ ���� Ȯ��
		                	if(car_dateS.length()!=0) {
					             st = new StringTokenizer(car_dateS,"-");
					             Y=st.nextToken();
					             if(st.hasMoreTokens()) {
						             M=st.nextToken();
						             
						             if(st.hasMoreTokens()) {
							             D=st.nextToken();
							             y=Integer.parseInt(Y);
							             m=Integer.parseInt(M);
							             d=Integer.parseInt(D);
							             
							             if(m>12 || m <1 || d >31 || d<1) {
							            	 System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
							        		 isError=true;
							        		 dateError=true;
							             }
						             }
						             else { 
						            	 isError=true;
						            	 dateError=true;
						            	 System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
						             }
					             }
					             
					             else {
					            	 System.out.println("[�����߻� : ��ϳ�¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
					            	 isError=true;
					            	 dateError=true;
					             }
					             
					             if(!dateError) {
					            	 date=true;
					            }
		                	}
		                	
		                	// ��������<��ϳ�¥ ���� Ȯ��
	                         if(!date && manudate) {
	                           query = "SELECT * FROM car WHERE car_id ='" + car_idS+"'";
	                           rs=stmt.executeQuery(query);
	                           if(!rs.next()) {}
	                           else {
	                        	   String da=rs.getString(9);
	                               st = new StringTokenizer(da,"-");
	                               String Y2=st.nextToken();
	                               int y2=Integer.parseInt(Y2);
	                           
	                              
	                               int y1=Integer.parseInt(car_manudateS);
	                          
	                             
	                               if(y2<y1) {
	                            	   isError=true;
	                            	   dateError=true;
	                               }
	                            
	                           }
	                           
	                           if(dateError) {
	                        	   System.out.println("[�����߻� : ���������� ��ϳ�¥] ���������� ��ϳ�¥���� �����̾���մϴ�.");
	                            }
	                         }
	                          
	                	
	                         if(date && !manudate) {
	                        	 query = "SELECT * FROM car WHERE car_id ='" + car_idS+"'";
	                        	 rs=stmt.executeQuery(query);
	                           
	                        	 if(!rs.next()) {}
	                        	 else {
	                              String da=rs.getString(6);
	                              
	                              st = new StringTokenizer(car_dateS,"-");
	                              String Y2 =st.nextToken();
	                              int y2=Integer.parseInt(Y2);
	                            
	                              int y1 =Integer.parseInt(da);
	                              if(y2<y1) {
	                                isError=true;
	                                dateError=true;
	                             }
	                          
	                             }
	                             if(dateError) {
	                                System.out.println("[�����߻� : ���������� ��ϳ�¥] ��ϳ�¥�� ������������ �ֱ��̾���մϴ�.");
	                             }
	                         }
	                      
	                         if(date && manudate) {
	                        	st = new StringTokenizer(car_dateS,"-");
		                        String Y2=st.nextToken();
		                        int y2 = Integer.parseInt(Y2);
		                        int y1 = Integer.parseInt(car_manudateS);
		                         
		                        if(y2<y1) {
		                        	isError=true;
		                            dateError=true;
		                        }
		                        
		                        if(dateError) {
		                           System.out.println("[�����߻� : ���������� ��ϳ�¥] ��ϳ�¥�� ������������ �ֱ��̾���մϴ�.");
		                        }
	                         }
	                          
	                	}
				             
			            if(!isError) {
			            	if(name) {
			            		query = "UPDATE car SET car_name='"+car_nameS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(num) {
					        	query = "UPDATE car SET car_num='"+car_numS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(board) {
					        	query = "UPDATE car SET car_board='"+car_boardS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(manudate) {
					        	query = "UPDATE car SET car_manudate='"+car_manudateS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(manucomp) {
					        	query = "UPDATE car SET car_manucomp='"+car_manucompS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(distance) {
					        	query = "UPDATE car SET car_distance='"+car_distanceS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(price) {
					        	query = "UPDATE car SET car_price='"+car_priceS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        if(date) {
					        	query = "UPDATE car SET car_date='"+car_dateS+"' WHERE car_id=" + car_idI;
						        stmt.executeUpdate(query);
					        }
					        		
					        System.out.println("�ش� ID�� ķ��ī ���� ������ �Ϸ�Ǿ����ϴ�.");
					        query="SELECT * FROM car WHERE car_id >=" + 0;
				            rs = stmt.executeQuery(query);
			
				                    
				            System.out.println("ķ��ī ID \t ķ��ī�̸� ----����----");
			
				            while(rs.next()) {
				            	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t �������� ���� -----");
				            }
 
			             }
	          		}
	                
	                //�� �Է¹�ư
	                else if(e.getSource() == insert_cust) {
	                    Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                    
	                   StringTokenizer st;
	                    
	                   System.out.println("�� �Է¹�ư Ŭ��!");
	                   
	                   String cust_id = cust_idf.getText();;
	                   String cust_name = cust_namef.getText();
	                   String cust_address = cust_addressf.getText();
	                   String cust_phone = cust_phonef.getText();
	                   String cust_email = cust_emailf.getText();
	                   
	                   if(cust_id.length()==0 || cust_name.length()==0 || cust_address.length()==0 || cust_phone.length()==0 || cust_email.length()==0) {
	                		System.out.println("[�����߻� : ������ �Է� ����] ��� ĭ�� �����͸� �Է����ּ���.");
	                   		isError=true;
	                	}
	                   else {
	                   
		                   // ������������ȣ
		                   if(cust_id.length()!=15 || cust_id.charAt(2)!='-' || cust_id.charAt(5)!='-' || cust_id.charAt(12)!='-' ||
		                         !Character.isDigit(cust_id.charAt(0)) || !Character.isDigit(cust_id.charAt(1)) || !Character.isDigit(cust_id.charAt(3)) ||
		                         !Character.isDigit(cust_id.charAt(4)) || !Character.isDigit(cust_id.charAt(6)) || !Character.isDigit(cust_id.charAt(7)) ||
		                         !Character.isDigit(cust_id.charAt(8)) || !Character.isDigit(cust_id.charAt(9)) || !Character.isDigit(cust_id.charAt(10)) ||
		                         !Character.isDigit(cust_id.charAt(11)) || !Character.isDigit(cust_id.charAt(13)) || !Character.isDigit(cust_id.charAt(14))) {
		                      System.out.println("[���� �߻� : ���������� ��ȣ] ������������ȣ�� nn-nn-nnnnnn-nn �������� �Է��ؾ� �մϴ�. n�� ����");
		                      isError = true;
		                   }
		                   else {
		                      query = "select * from customer where cust_id = '"+cust_id+"'";
		                      rs = stmt.executeQuery(query);
		                      
		                      while(rs.next()) {
		                       if(rs.getString(1).length()!=0) {
		                          System.out.println("[�����߻� : ������������ȣ] �ش� ������������ȣ�� ��ϵǾ� �ֽ��ϴ�.");
		                             isError = true;
		                       }
		                      }
		                   
		                   }
		                   
		                  //����
		                    if(cust_name.length()==0) {
		                      System.out.println("[�����߻� : ����] ������ �Է��ؾ� �մϴ�.");
		                      isError = true;
		                    }
		                    
		                    // phone ���� Ȯ��
		                    if(cust_phone.length()!=0) {
					            String p1, p2, p3;
					            st = new StringTokenizer(cust_phone,"-");
					             p1=st.nextToken();
					             if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
					             if(!st.hasMoreTokens())  phoneError=true;
					             else{
					            	 p2=st.nextToken();
					            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
					            	 if(!st.hasMoreTokens()) phoneError=true;
					            	 else {
					            	 p3=st.nextToken();
					            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
					            	 }
					             }
					       
					             if(phoneError) {
					            	 System.out.println("[�����߻� : ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
					        		 isError=true;
					             }
					             
		                    }
				            // email ���� Ȯ��
		                    if(cust_email.length()!=0) {
					             String e1,e2=null,e3=null;
					             st = new StringTokenizer(cust_email,"@");
					             e1=st.nextToken();
					             if(!st.hasMoreTokens())  emailError=true;
					             else {
					            	 e2=st.nextToken();
					            	 
					            	 st = new StringTokenizer(e2,".");
					            	 if(!st.hasMoreTokens()) emailError=true;
					            	 else{
					            		 e2=st.nextToken();
					            		 if(!st.hasMoreTokens()) emailError=true;
					            		 else {
							            	 e3=st.nextToken();
						            	 }
						            }
					            	
					             }
					             if(emailError) {
					            	 System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
					        		 isError=true;
					             }
					             
		                    }
		                   
		                   
		                    if(!isError) {
	                            query = "insert into customer(cust_id,cust_name,cust_address,cust_phone,cust_email) values('"+ cust_id +"','"+cust_name+"','"+
	                               cust_address+"','"+cust_phone+"','"+cust_email+"');";
	                                 
	                            stmt.executeUpdate(query);
	                            System.out.println("�� �Է� �Ϸ�");
	                            
	                            query="SELECT * FROM customer WHERE cust_id = '" +cust_id +"'";
	                            rs=stmt.executeQuery(query);
	                            
	                            if(rs.next()) {
	                              System.out.println("������������ȣ \t ���� \t ���ּ� \t�� ��ȭ��ȣ \t�� �̸��� ");
	                              String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
	                                            rs.getString(5) ;
	                                  
	                              System.out.println(str);
	                           }
	                       }
		                   
	                   }
	                }
	      			
	                // �� ������ư
	                else if(e.getSource() == delete_cust) {
	                	String cust_idS = cust_idf.getText();
	                    
	                    if(cust_idS.length()==0) {
	                   		System.out.println("[�����߻� : ������ �Է� ����] ������������ȣ�� �����͸� �Է����ּ���.");		
	                    }
	                    else {
	                    	if(cust_idS.length()!=15 || cust_idS.charAt(2)!='-' || cust_idS.charAt(5)!='-' || cust_idS.charAt(12)!='-' ||
	   	                         !Character.isDigit(cust_idS.charAt(0)) || !Character.isDigit(cust_idS.charAt(1)) || !Character.isDigit(cust_idS.charAt(3)) ||
	   	                         !Character.isDigit(cust_idS.charAt(4)) || !Character.isDigit(cust_idS.charAt(6)) || !Character.isDigit(cust_idS.charAt(7)) ||
	   	                         !Character.isDigit(cust_idS.charAt(8)) || !Character.isDigit(cust_idS.charAt(9)) || !Character.isDigit(cust_idS.charAt(10)) ||
	   	                         !Character.isDigit(cust_idS.charAt(11)) || !Character.isDigit(cust_idS.charAt(13)) || !Character.isDigit(cust_idS.charAt(14))) {
	   	                      System.out.println("[���� �߻� : ���������� ��ȣ ���� ����] ������������ȣ�� nn-nn-nnnnnn-nn �������� �Է��ؾ� �մϴ�. n�� ����");
	   	                   	}
	                    	
	                    	else {
			                   query = "SELECT * FROM customer WHERE cust_id ='" + cust_idS+"'";
			                   rs = stmt.executeQuery(query);
			                   
			                   System.out.println("�� ������ư Ŭ��!");
			                 
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[�����߻� : ������������ȣ] �ش� ������������ ���� ���� �����ϴ�.");
			                 }
			                 else {
			                	 query = "SELECT * FROM rent WHERE Customer_cust_id ='" + cust_idS+"'";
			                	 rs = stmt.executeQuery(query);
			                	 while(rs.next()) {
			                		 Statement stmt2 = con.createStatement();
			                		 int car_pk = rs.getInt(1);
			                		 query = "DELETE FROM inspection WHERE Rent_Car_car_id = " + car_pk;
					                 stmt2.executeUpdate(query);
			                	 }
			                	 
			                	 
			                    query = "DELETE FROM rent WHERE Customer_cust_id = '" + cust_idS+"'";
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM repair WHERE Customer_cust_id = '" + cust_idS+"'";
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM customer WHERE cust_id = '" + cust_idS+ "'";
			                    stmt.executeUpdate(query);   
			                                                       
			                    
			                    System.out.println("�ش� ������������ ���� ���� ������ �Ϸ�Ǿ����ϴ�.");
			                    
			                    
			                    query="SELECT * FROM customer WHERE cust_id >= '00-00-000000-00'";
			                    rs = stmt.executeQuery(query);
			
			                       
			                    System.out.println("������������ȣ \t ���� ----����----");
			
			                    while(rs.next()) {
			                        System.out.println(rs.getString(1)+"\t"+rs.getString(2)+" �������� ���� -----");
			                          
			                      }
			                 }
	                    }
	                 }
	                }
	                

	                else if(e.getSource()==convert_cust) {
	                	 Boolean isError = false;
	                	 Boolean phoneError=false;
	                	 Boolean emailError=false;
	                	 Boolean name=false;
		                 Boolean address=false;
		                 Boolean phone=false;
		                 Boolean email=false;
		                    
		                 StringTokenizer st;
		                   
		                 String cust_id = cust_idf.getText();
		                 String cust_name = cust_namef.getText();
		                 String cust_address = cust_addressf.getText();
		                 String cust_phone = cust_phonef.getText();
		                 String cust_email = cust_emailf.getText();
		                   
		                 if(cust_id.length()==0 ||  (cust_name.length()==0 && cust_address.length()==0 && cust_phone.length()==0 && cust_email.length()==0)) {
		                		System.out.println("[�����߻� : ������ �Է� ����] ���������ȣĭ�� �ٲٰ���� �Ӽ�ĭ�� �����͸� �Է����ּ���.");
		                   		isError=true;
		                 }
		                 else {
			                   // ������������ȣ
		                	   query = "select * from customer where cust_id = '"+cust_id+"'";
			                      rs = stmt.executeQuery(query);
						        if(!rs.next()) { 
						        	 System.out.println("[�����߻� : ���������ȣ] �ش� ���������ȣ�� ���� �����ϴ�.");
						        	 isError=true;
						        }
						        else {
				                   
				                    //����
				                    if(cust_name.length()!=0) {
				                      name=true;
				                    }
				                    
				                    //�� �ּ�
				                    if(cust_address.length()!=0) {
				                       address=true;
				                    }
				                    
				                    // phone ���� Ȯ��
				                    if(cust_phone.length()!=0) {
							            String p1, p2, p3;
							            st = new StringTokenizer(cust_phone,"-");
							             p1=st.nextToken();
							             if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
							             if(!st.hasMoreTokens())  phoneError=true;
							             else{
							            	 p2=st.nextToken();
							            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
							            	 if(!st.hasMoreTokens()) phoneError=true;
							            	 else {
							            	 p3=st.nextToken();
							            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
							            	 }
							             }
							       
							             if(phoneError) {
							            	 System.out.println("[�����߻� : ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
							        		 isError=true;
							             }
							             else {
							            	 phone=true;
							             }
				                    }
						            // email ���� Ȯ��
				                    if(cust_email.length()!=0) {
							             String e1,e2=null,e3=null;
							             st = new StringTokenizer(cust_email,"@");
							             e1=st.nextToken();
							             if(!st.hasMoreTokens())  emailError=true;
							             else {
							            	 e2=st.nextToken();
							            	 
							            	 st = new StringTokenizer(e2,".");
							            	 if(!st.hasMoreTokens()) emailError=true;
							            	 else{
							            		 e2=st.nextToken();
							            		 if(!st.hasMoreTokens()) emailError=true;
							            		 else {
									            	 e3=st.nextToken();
								            	 }
								            }
							            	
							             }
							             if(emailError) {
							            	 System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
							        		 isError=true;
							             }
							             else {
							            	 email=true;
							             }
					                   
				                    }
				                   
				                   
				                   if(!isError) {
				                	   if(name) {
							            	query = "UPDATE customer SET cust_name='"+cust_name+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						            	}
						            	if(address) {
							        		query = "UPDATE customer SET cust_address='"+cust_address+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						            	}
						        		if(phone) {
							        		query = "UPDATE customer SET cust_phone='"+cust_phone+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						        		}
						        		
						        		if(email) {
							        		query = "UPDATE customer SET cust_email='"+cust_email+"' WHERE cust_id='" + cust_id+"'";
							        		stmt.executeUpdate(query);
						        		}
						        		
						        		System.out.println("�ش� ���������ȣ�� �������� ������ �Ϸ�Ǿ����ϴ�.");
						        		query="SELECT * FROM customer WHERE cust_id='"+cust_id+"'";
					                    rs = stmt.executeQuery(query);
				
					                    
					                    System.out.println("���������ȣ \t �̸� ----����----");
				
					                    while(rs.next()) {
					                       System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t �������� ���� -----");
					                    }
				                   }
						        }
		                   }
	                }
	                
	                //����� �Է¹�ư
	                else if(e.getSource() == insert_gar) {
	                	Boolean isError = false;
	                    Boolean phoneError=false;
	                    Boolean emailError=false;
	                     
	                    System.out.println("����� �Է¹�ư Ŭ��!");
	                     
	                    String gar_name = gar_namef.getText();
	                    String gar_address = gar_addressf.getText();
	                    String gar_phone = gar_phonef.getText();
	                    String gar_person = gar_personf.getText();
	                    String gar_email = gar_emailf.getText();
	                    int gar_id;
	                     
	                     //����Ҹ�
	                    if(gar_name.length()==0) {
	                    	System.out.println("[�����߻� : ����Ҹ�] ����Ҹ��� �Է��ؾ� �մϴ�.");
	                        isError = true;
	                    }
	                     
	                     //����� �ּ�
	                    if(gar_address.length()==0) {
	                        System.out.println("[�����߻� : ����� �ּ�] ����� �ּҸ� �Է��ؾ� �մϴ�.");
	                        isError = true;
	                    }
	                     
	                    //����� ��ȭ��ȣ
	                    if(gar_phone.length()==0) {
	                        System.out.println("[�����߻� : ����� ��ȭ��ȣ] ����� ��ȭ��ȣ�� �Է��ؾ� �մϴ�.");
	                        isError=true;
	                    }
	                    else {
	                        // phone ���� Ȯ��
	                         String p1, p2, p3;
	                         StringTokenizer st = new StringTokenizer(gar_phone,"-");
	                          p1=st.nextToken();
	                          
	                          if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
	                          
	                          if(!st.hasMoreTokens())  phoneError=true;
	                          else{
	                             p2=st.nextToken();
	                             if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
	                             if(!st.hasMoreTokens()) phoneError=true;
	                             else {
	                            	 p3=st.nextToken();
	                             	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
	                             }
	                          }
	                    
	                          if(phoneError) {
	                             System.out.println("[�����߻� : ����� ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
	                            isError=true;
	                          }
	                     }
	                     
	                     //����� �̸�
	                     if(gar_person.length()==0) {
	                        System.out.println("[�����߻� : ����� �̸�] ����� �̸��� �Է��ؾ� �մϴ�.");
	                        isError=true;
	                     }
	                     
	                     //����� �̸���
	                     if(gar_email.length()==0) {
	                        System.out.println("[�����߻� : �̸���] �̸����� �Է��ؾ� �մϴ�.");
	                        isError = true;
	                     }
	                     else {
	                         // email ���� Ȯ��
	                         String e1,e2=null,e3=null;
	                         StringTokenizer st = new StringTokenizer(gar_email,"@");
	                         e1=st.nextToken();
	                         
	                         if(!st.hasMoreTokens())  emailError=true;
	                         else {
	                            e2=st.nextToken();
	                            
	                            st = new StringTokenizer(e2,".");
	                            if(!st.hasMoreTokens()) emailError=true;
	                            else{
	                               e2=st.nextToken();
	                               if(!st.hasMoreTokens()) emailError=true;
	                               else {
	                                  e3=st.nextToken();
	                               }
	                           }
	                           
	                         }
	                         if(emailError) {
	                            System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
	                           isError=true;
	                         }
	                     }          
	                                    

	                    if(!isError) {
	                       query="SELECT * FROM garage";
	                        rs=stmt.executeQuery(query);
	                        rs.last();
	                        gar_id=rs.getInt(1)+1;
	                         
	                       
	                       query = "insert into garage(gar_id,gar_name,gar_address,gar_phone,gar_person,gar_email) values('"+ gar_id +"','"+gar_name+"','"+
	                          gar_address+"','"+gar_phone+"','"+gar_person+"','"+gar_email+"');";
	                            
	                       stmt.executeUpdate(query);
	                       System.out.println("����� �Է� �Ϸ�Ǿ����ϴ�.");
	                       
	                       query="SELECT * FROM garage";
		                   rs=stmt.executeQuery(query);
			               rs.last();
			                 
			               System.out.println("ķ��ī ����� ID \t ����Ҹ� \t ����� �ּ� \t����� ��ȭ��ȣ \t����� �̸� \t�̸��� ");
			               String str = rs.getInt(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getString(5)+"\t"+rs.getString(6) ;
			                      
			               System.out.println(str);
	                    
	                    }
	                     
	                }
	                
	                //����� ����
	                else if(e.getSource()==delete_gar) {
	                	System.out.println("����� ������ư Ŭ��!");
	                	
	                	String gar_idS = gar_idf.getText();
	                    
	                    if(gar_idS.length()==0) {
	                   		System.out.println("[�����߻� : ������ �Է� ����] ����� ID�� �����͸� �Է����ּ���.");
	                   		
	                    }
	                    else {
	                    	if(!CheckNumber(gar_idS)) {
		                		System.out.println("[�����߻� : ����� ID] ����� ID�� ���� �������� �մϴ�.");
		                	}
	                    	else {
			                   int gar_idI = Integer.parseInt(gar_idS);
			                   query = "SELECT * FROM garage WHERE gar_id =" + gar_idI;
			                   rs = stmt.executeQuery(query);
			              
			                 
			                 if(!rs.next()) { 
			                    System.out.println("[�����߻� : ķ��ī ����� ID] �ش� ID�� ����Ұ� �����ϴ�.");
			                 }
			                 else {
		                
			                    query = "DELETE FROM repair WHERE Garage_gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);
			                    
			                    query = "DELETE FROM garage WHERE gar_id = " + gar_idI;
			                    stmt.executeUpdate(query);   
			                                                       
			                    
			                    System.out.println("�ش� ID�� ����� ������ �Ϸ�Ǿ����ϴ�.");
			                    
			     
			                    
			                       query="SELECT * FROM garage WHERE gar_id >=" + 0;
			                       rs = stmt.executeQuery(query);
			
			                       
			                       System.out.println("����� ID \t ������̸� ----����----");
			
			                       while(rs.next()) {
			                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" �������� ���� -----");
			                          
			                       }
			                 }
	                    }
	                 }
	                }
	                
	                //����� �����ư
	                else if(e.getSource()==convert_gar) {
	                	System.out.println("����� �����ư Ŭ��!");
	                	
	                	 Boolean isError = false;
	                     Boolean phoneError=false;
	                     Boolean emailError=false;
	                     
	                     StringTokenizer st;
	                     
	                     Boolean name=false;
	                     Boolean address=false;
	                     Boolean phone=false;
	                     Boolean person=false;
	                     Boolean email=false;
	                    
	                     
	                     String gar_name = gar_namef.getText();
	                     String gar_address = gar_addressf.getText();
	                     String gar_phone = gar_phonef.getText();
	                     String gar_person = gar_personf.getText();
	                     String gar_email = gar_emailf.getText();
	                     String gar_idS = gar_idf.getText();
	                     int gar_id;
	                     
	                     if(gar_idS.length()==0 ||  (gar_name.length()==0 && gar_address.length()==0 && gar_phone.length()==0 && gar_email.length()==0)) {
		                		System.out.println("[�����߻� : ������ �Է� ����] ����� IDĭ�� �ٲٰ���� �Ӽ�ĭ�� �����͸� �Է����ּ���.");
		                   		isError=true;
		                }
	                     else { 
	                     
		                     //����Ҹ�
		                     if(gar_name.length()!=0) {
		                       name=true;
		                     }
		                     
		                     //����� �ּ�
		                     if(gar_address.length()!=0) {
		                        address=true;
		                     }
		                     
		                     // phone ���� Ȯ��
			                    if(gar_phone.length()!=0) {
						            String p1, p2, p3;
						            st = new StringTokenizer(gar_phone,"-");
						            p1=st.nextToken();
						            if(p1.length()!=3 || !CheckNumber(p1)) phoneError=true;
						            if(!st.hasMoreTokens())  phoneError=true;
						            else{
						            	 p2=st.nextToken();
						            	 if(p2.length()!=4 || !CheckNumber(p2)) phoneError=true;
						            	 if(!st.hasMoreTokens()) phoneError=true;
						            	 else {
						            	 p3=st.nextToken();
						            	 if(p3.length()!=4 || !CheckNumber(p3)) phoneError=true;
						            	 }
						             }
						       
						             if(phoneError) {
						            	 System.out.println("[�����߻� : ��ȭ��ȣ] ��ȭ��ȣ�� XXX-XXXX-XXXX ���� �մϴ�.(X�� ���� 3,4,4�� ����, X�� ���ڿ�����)");
						        		 isError=true;
						             }
						             else {
						            	 phone=true;
						             }
			                    }
			                    
					            // email ���� Ȯ��
			                    if(gar_email.length()!=0) {
						             String e1,e2=null,e3=null;
						             st = new StringTokenizer(gar_email,"@");
						             e1=st.nextToken();
						             if(!st.hasMoreTokens())  emailError=true;
						             else {
						            	 e2=st.nextToken();
						            	 
						            	 st = new StringTokenizer(e2,".");
						            	 if(!st.hasMoreTokens()) emailError=true;
						            	 else{
						            		 e2=st.nextToken();
						            		 if(!st.hasMoreTokens()) emailError=true;
						            		 else {
								            	 e3=st.nextToken();
							            	 }
							            }
						            	
						             }
						             if(emailError) {
						            	 System.out.println("[�����߻� : �̸���] �̸����� XXX@XXX.XXX ���� �մϴ�.(X�� ���� ���� ����)");
						        		 isError=true;
						             }
						             else {
						            	 email=true;
						             }
				                   
			                    }
			                   
		                     
		                     //����� �̸�
		                     if(gar_person.length()!=0) {
		                        person=true;
		                     }
		                     
		                    
		                    if(!isError) {
		                    	if(name) {
					            	query = "UPDATE garage SET gar_name='"+gar_name+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				            	}
				            	if(address) {
					        		query = "UPDATE garage SET gar_address='"+gar_address+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				            	}
				        		if(phone) {
					        		query = "UPDATE garage SET gar_phone='"+gar_phone+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		if(person) {
				        			query = "UPDATE garage SET gar_person='"+gar_person+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		if(email) {
					        		query = "UPDATE garage SET gar_email='"+gar_email+"' WHERE gar_id='" + gar_idS+"'";
					        		stmt.executeUpdate(query);
				        		}
				        		
				        		
				        		System.out.println("�ش� ID�� ����� ������ ������ �Ϸ�Ǿ����ϴ�.");
				        		query="SELECT * FROM garage WHERE gar_id > '0'";
			                    rs = stmt.executeQuery(query);
		
			                    
			                    System.out.println("�����ID \t �̸� ----����----");
		
			                    while(rs.next()) {
			                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t �������� ���� -----");
			                    }
		                    }
	                     }
	                }
	                
	                //��ȯ
	                else if(e.getSource() == return_car) {
	                      String inspec_rents = inspec_rentf.getText(); //�뿩��ȣ                      
	                      String inspec_cars = inspec_carf.getText(); // ķ��ī ��� ID
	                      String inspec_fronts = inspec_frontf.getText();
	                      String inspec_lefts = inspec_leftf.getText();
	                      String inspec_rights = inspec_rightf.getText();
	                      String inspec_behinds = inspec_behindf.getText();
	                      String inspec_repairs = inspec_repairf.getText(); // �����ʿ俩�� �ʿ� 1,���ʿ� 0
	                      int inspec_carI;
	                      int inspec_rentI;
	                      int inspec_repairI;
	                      
	                      Boolean isError = false;
	                      
	                      System.out.println("��ȯ/���˳��� �����ư Ŭ��!");
	                      
	                   
	                      if(inspec_rents.length()==0||inspec_cars.length()==0||inspec_fronts.length()==0||inspec_lefts.length()==0
	                            ||inspec_rights.length()==0||inspec_behinds.length()==0||inspec_repairs.length()==0) {
	                         System.out.println("[���� �߻�] ��� ĭ�� �����͸� �Է��ؾ� �մϴ�.");
	                         isError= true;
	                      }
	                      else { 
	                         if(!CheckNumber(inspec_repairs) || !CheckNumber(inspec_cars) || !CheckNumber(inspec_rents)
	                               || !inspec_repairs.contentEquals("0") && !inspec_repairs.contentEquals("1")) {
	                            System.out.println("[���� �߻�] �뿩��ȣ, ķ��ī ���ID, �����ʿ俩�δ� 0���� ū �����θ� �Է��ؾ� �մϴ�.");
	                            System.out.println("��, �����ʿ俩�δ� �ʿ��ϴٸ� 1, ���ʿ��ϴٸ� 0�� �Է��ؾ� �մϴ�.");
	                            isError = true;
	                         }
	                         else {
	                            inspec_carI= Integer.parseInt(inspec_cars); //ķ��ī ���ID
	                            inspec_rentI = Integer.parseInt(inspec_rents); //�뿩��ȣ
	                                 
	                           query = "SELECT * FROM rent WHERE Car_car_id =" + inspec_carI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                              System.out.println("[�����߻� : ķ��ī ID] �ش� ID�� ķ��ī�� �����ϴ�.");
	                              isError = true;
	                            }
	                                
	                           query = "SELECT * FROM rent WHERE rent_id =" + inspec_rentI;
	                           rs = stmt.executeQuery(query);
	                                  
	                           if(!rs.next()) { 
	                                System.out.println("[�����߻� : �뿩 ��ȣID] �ش� �뿩 ��ȣ�� �����ϴ�.");
	                                isError = true;
	                           }
	                                
	                          
	                           query = "select * from rent where rent_id = '"+inspec_rents+"'";
	                           rs = stmt.executeQuery(query);
	                           if(!rs.next()) { 
	                              System.out.println("[�����߻�] �ش� �뿩 ��ȣ�� ķ��īID�� �´� �뿩 ����� �����ϴ�.");
	                              isError = true;
	                          }
	                           else {
	                        	   if(rs.getInt(8)!=Integer.parseInt(inspec_cars)) {
	                        		   System.out.println("[�����߻�] �ش� �뿩 ��ȣ�� ķ��īID�� �´� �뿩 ����� �����ϴ�.");
	                        		   isError = true;
	                        	   }
	                           }
	                           
	                           //�ߺ�����
	                           query = "select * from inspection where Rent_rent_id = "+inspec_rentI;
	                           rs= stmt.executeQuery(query);
	                           
	                           while(rs.next()) {
			                       if(rs.getString(1).length()!=0) {
			                          System.out.println("[�����߻� : �뿩��ȣ] �̹� ���˳����� ��ϵǾ� �ֽ��ϴ�.");
			                          isError = true;
			                       }
			                   }
	                               
	                          }
	                         
	                       }

	                      
	                      if(!isError) {
	                         
	                         inspec_repairI = Integer.parseInt(inspec_repairs);
	                         inspec_carI= Integer.parseInt(inspec_cars); //ķ��ī ���ID
	                         inspec_rentI = Integer.parseInt(inspec_rents); //�뿩��ȣ
	                     
	                         query = "insert into inspection(frontinfo,leftinfo,rightinfo,behindinfo,repairinfo,Rent_rent_id,Rent_Car_car_id) values('"+ inspec_fronts +"','"+inspec_lefts+"','"+
	                                inspec_rights+"','"+inspec_behinds+"',"+inspec_repairI+","+inspec_rentI+","+inspec_carI+")";
	                                stmt.executeUpdate(query); 
	                         
	                         if(inspec_repairI==0) //�����ʿ������ʴٸ� �ٽ� �� �뿩 ����
	                            carBoolean[inspec_carI]=true;
	                         
	                         System.out.println("��ȯ/���˳��� ���� �Ϸ�");
	                         if(inspec_repairI==1) { // �����ʿ��ϸ� ���񳻿� �ۼ� �ʿ�
	                        	 repairBoolean[inspec_carI]=true;
	                         }
	                         
	                         query="SELECT * FROM inspection";
		                     rs=stmt.executeQuery(query);
			                 rs.last();
			                 
			                 System.out.println("�պκ� ���� \t ���� ���� \t ������ ���� \t ���� ���� \t�����ʿ� ���� \t���� �뿩 ��ȣ \tķ��ī ��� ID ");
			                 String str = rs.getString(1) +"\t"+ rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + 
			                                rs.getInt(5)+"\t" + rs.getInt(6) +"\t" + rs.getInt(7);
			                      
			                 System.out.println(str);
	                     }
	               }
	                
	                //���񳻿� �Է�
	                else if(e.getSource()==request_car) {
	                	System.out.println("���񳻿� �Է� ��ư Ŭ��!");
	                	Boolean isError = false;
	                	Boolean carError = false;
	                	Boolean compError =false;
	                	Boolean dateError =false;
	                	
	                	String car_idS=repair_carf.getText();
	                	int car_id=0;
	                	String gar_idS=repair_garf.getText();
	                	int gar_id=0;
	                	String comp_idS=repair_corpf.getText();
	                	int comp_id=0;
	                	String cust_idS=repair_custf.getText();
	                	String infoS=repair_infof.getText();
	                	String dateS=repair_datef.getText();
	                	String priceS=repair_pricef.getText();
	                	int price=0;
	                	String paydateS=repair_paydatef.getText();
	                	String extraS=repair_extraf.getText();
	                	
	                	// �� ���� ������ Ȯ��
	                	if(car_idS.length()==0 || gar_idS.length()==0 || comp_idS.length()==0 || cust_idS.length()==0 || infoS.length()==0 
	                			|| dateS.length()==0 || priceS.length()==0 ||paydateS.length()==0 || extraS.length()==0) {
	                		System.out.println("[�����߻� : ������ �Է� ����] ��� ĭ�� �����͸� �Է����ּ���.");
	                		isError=true;
	                	}
	                	else {
	                		// �� ID ����Ȯ��
	                		if(CheckNumber(car_idS)) {
		                    	 car_id = Integer.parseInt(car_idS);
			                     query = "SELECT * FROM car WHERE car_id =" + car_id;
			                     rs = stmt.executeQuery(query);
			                     if(!rs.next()) { 
			                        System.out.println("[�����߻� : ķ��ī ID] �ش� ID�� ķ��ī�� �����ϴ�.");
			                        isError=true;
			                        carError=true;
			                     }
			                     else {
			                    	 int i = rs.getInt(1);
			                    	 if(repairBoolean[i]==false) {
			                    		 System.out.println("[�����߻� : �������ʿ� ķ��ī] �ش� ID�� ķ��ī�� ���� ���ʿ��մϴ�.");
			 	                        isError=true;
			 	                        carError=true;
			                    	 }
			                     }
		                     }
		                     else {
		                    	 isError=true;
		                    	 carError=true;
		                    	 System.out.println("[�����߻� : ķ��ī ID] ķ��ī ID�� ���� �������� �մϴ�.");
		                     }
	                		
	                		// ����� ID ����Ȯ��
	                		if(!CheckNumber(gar_idS)) { 
		                		isError=true;
		                		System.out.println("[�����߻� : ����� ID] ����� ID�� ���� �����Դϴ�.");
		                	}
		                	else{
		                		gar_id=Integer.parseInt(gar_idS);
		                		query = "SELECT * FROM garage WHERE gar_id =" + gar_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[�����߻� : ����� ID] ����� ID�� ȸ�簡 �����ϴ�.");
					        		 isError=true;
					        	 }
				        	 }
	                		
	                		
		                	// ȸ�� ID ���� Ȯ��
		                	if(!CheckNumber(comp_idS)) { 
		                		isError=true;
		                		compError=true;
		                		System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �����Դϴ�.");
		                	}
		                	else{
		                		comp_id=Integer.parseInt(comp_idS);
		                		query = "SELECT * FROM company WHERE comp_id =" + comp_id;
					        	 rs = stmt.executeQuery(query);
					        	 if(!rs.next()) { 
					        		 System.out.println("[�����߻� : ȸ�� ID] �ش� ID�� ȸ�簡 �����ϴ�.");
					        		 isError=true;
					        		 compError=true;
					        	 }
					        	 else {
					        		 if(!carError) {
					        			 query = "SELECT * FROM car WHERE car_id =" + car_id;
					        			 rs = stmt.executeQuery(query);
					        			 if(rs.next()) {
					                    	 int i = rs.getInt(10); 
					                    	 if(Integer.parseInt(comp_idS)!=i) {
					                    		 isError=true;
					                    		 compError=true;
					                   		 System.out.println("[�����߻� : ȸ�� ID] ķ��ī ID�� ȸ�� ID�� ���� �����ʽ��ϴ�.");
					                    	 }
					                     }
					        		 }
					        	 }
				        	 }
		                	
		                	// �� ���������� ��ȣ ���� Ȯ��		
		                	if(!(compError||carError)) {
			                	query = "SELECT * FROM rent WHERE Car_car_id = '" + car_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			
			        			if(rs.next()) {
			        				rs.last();
			        				if(!rs.getString(10).contentEquals(cust_idS)) {
				        				System.out.println("[�����߻� : ���������ȣ] ���������ȣ�� ��ġ���� �����ϴ�.");
				                        isError=true;
			                        }
			        			}
		                	}
		                     
		                	// ������¥ ���� Ȯ��
		                	String Y, M, D;
				             int y,m,d;
				             StringTokenizer st;
				             st = new StringTokenizer(dateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
						        		 isError=true;
						        		 dateError=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 dateError=true;
					            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
					             }
				             }
				             else {
				            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
				            	 isError=true;
				            	 dateError=true;
				             }
				             if(!dateError) {
				            	 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				            	 String date1="";
				            	
				            	 query = "SELECT * FROM rent WHERE Car_car_id = '" + car_idS +"'";
				        			rs = stmt.executeQuery(query); 
				        			if(rs.next()) {
				        				rs.last();
				        				date1 = fm.format(rs.getDate(5)); //�뿩���̺��� �뿩 ���Ա���
				        			}
				        			
				            	 
				            	 if(dateS.compareTo(date1)<=0) {
		                              System.out.println("[�����߻� : ������¥] ������¥�� �뿩��볳�Ա��� ���� �����̾���մϴ�.");
		                              isError = true;
		                              dateError=true;
		                       }
				            	 
				            	 
				             }
				             
				             
		                	// ������� ���� Ȯ��
				             if(!CheckNumber(priceS)) { 
			                		isError=true;
			                		System.out.println("[�����߻� : �������] ��������� ���� �����Դϴ�.");
			                }
			                else {
			                	price = Integer.parseInt(priceS);
				                if(price < 0) {
				                	System.out.println("[�����߻� : �������] ��������� 0�̻� �̾�� �մϴ�.");
				                	isError=true;
				               	}
			                }
				    	
		                	
		                	// ���Ա��� ���� Ȯ��
			                Boolean date1Error=false;  
				             st = new StringTokenizer(paydateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
						        		 isError=true;
						        		 date1Error=true;
						             }
					             }
					             else { 
					            	 isError=true;
					            	 date1Error=true;
					            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
					             }
				             }
				             else {
				            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
				            	 isError=true;
				            	 date1Error=true;
				             }
				             if(!date1Error && !dateError) {
				            	 if(paydateS.compareTo(dateS)<=0) {
		                         System.out.println("[�����߻� : ���Ա���] ���Ա����� ������¥ ���� �����̾���մϴ�.");
		                         isError = true;
		                          date1Error=true;
		                       }
				             }
				             
				             // ������ ������ ���� 
			                   if(!isError) {
			                	   query="SELECT * FROM repair";
			                       rs=stmt.executeQuery(query);
			                       rs.last();
			                       int repair_idI=rs.getInt(1)+1;
			                    
			                       PreparedStatement statement = null;
		                           statement = con.prepareStatement("insert into repair(repair_id, repair_info, repair_date, repair_price, repair_paydate, repair_extra, Garage_gar_id, Car_car_id, Car_Company_comp_id, Customer_cust_id)"
		                            		 +"value(?,?,?,?,?,?,?,?,?,?)");
		                    
		                           statement.setInt(1,repair_idI);
		                           statement.setString(2, infoS);
		                           statement.setDate(3, java.sql.Date.valueOf(dateS));
		                           statement.setInt(4, Integer.parseInt(priceS));
		                           statement.setDate(5,java.sql.Date.valueOf(paydateS));
		                           statement.setString(6,extraS);
		                           statement.setInt(7,gar_id);
		                           statement.setInt(8, car_id);
		                           statement.setInt(9, comp_id);
		                           statement.setString(10,cust_idS);
		                             
		                           statement.executeUpdate();

			                       carBoolean[car_id]=true;
			                       repairBoolean[car_id]=false;
			                       
			                       rs=stmt.executeQuery(query);
			                     
			                       rs.last();
			                       System.out.println("���񳻿� �Է� �Ϸ�Ǿ����ϴ�.");
			                       System.out.println("����ID \t ���� ���� \t ---- ������ ���� ----");
			                       String str = rs.getInt(1) +"\t" + rs.getString(2) ;
			                      
			                       System.out.println(str);
			                      
			                    }
	                	}
	                	
	                }
	                else if(e.getSource()==repair_delete) {
	                	System.out.println("���񳻿� ���� ��ư Ŭ��!");
	                	Boolean isError=false;
	                	String repair_idS = repair_idf.getText();
	                	if(repair_idS.length()==0) {
	                		System.out.println("[�����߻� : �����ȣ �Է� ����] ���� ��ȣ�� �Է��ؾ��մϴ�.");
	                		isError=true;
	                	}
	                	else {
	                		if(CheckNumber(repair_idS)) {
	                			query = "SELECT * FROM repair WHERE repair_id = '" + repair_idS +"'";
			        			rs = stmt.executeQuery(query);
			        			if(!rs.next()) {
			        				System.out.println("[�����߻� : �����ȣ] �ش� ���� ��ȣ�� ���񳻿��� �����ϴ�.");
		                			isError=true;
			        			}
	                		}
	                		else {
	                			System.out.println("[�����߻� : �����ȣ] ���� ��ȣ�� ���������̾�� �մϴ�.");
	                			isError=true;
	                		}
	                	}
	                	if(!isError) {
	                		query = "DELETE FROM repair WHERE repair_id = '" + repair_idS + "'";
		                    stmt.executeUpdate(query);   
		                                                       
		                    
		                    System.out.println("�ش� ID�� ���񳻿� ������ �Ϸ�Ǿ����ϴ�.");
		                    
		                    
		                    query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                       rs = stmt.executeQuery(query);
		
		                       
		                       System.out.println("���񳻿� ID \t ���񳻿� ----����----");
		
		                       while(rs.next()) {
		                          System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" �������� ���� -----");
		                          
		                       }
	                	}
	                }
	                
	                else if(e.getSource()==repair_convert) {
	                	System.out.println("���񳻿� ���� ��ư Ŭ��!");

	                	String repair_ids = repair_idf.getText();//���񳻿� ID(PK)
	                	String repair_cars = repair_carf.getText(); // ķ��ī ���ID (FK)
	                	String repair_gars = repair_garf.getText(); //�����ID (FK)
	                	String repair_corps = repair_corpf.getText(); //ȸ��ID (FK)
	                	String repair_custs = repair_custf.getText(); //��  ������������ȣ(FK)
	                	String repair_infos = repair_infof.getText(); // ���񳻿�
	                	String repair_dates = repair_datef.getText(); //������¥
	                	String repair_prices = repair_pricef.getText(); // �������
	                	String repair_paydates = repair_paydatef.getText(); //���� ����
	                	String repair_extras = repair_extraf.getText(); //��Ÿ ���� ���� ����
	                	
	                	Boolean isError = false;
	                	Boolean dateError = false;
	                	
	                	Boolean date1 = false; //������¥
	                	Boolean date2 = false; //���Ա���
	                	Boolean price = false; //���� ���
	                	Boolean info = false; //���񳻿�
	                	Boolean extra = false; //��Ÿ ���� ����
	                	
	                	int car_idI=0;
	                	int repair_idI = 0;
	                	int repair_priceI = 0;
	                	
	                	String Y, M, D;
			            int y,m,d;
	                	StringTokenizer st;
	                	
	                	
	                	//�� �� �ִ��� Ȯ��, ����ִ��� Ȯ��
	                	if(repair_ids.length()==0 || repair_cars.length()!=0|| repair_gars.length()!=0|| repair_corps.length()!=0||
	                			repair_custs.length()!=0 ||(repair_infos.length()==0 && repair_dates.length()==0 
	                			&& repair_prices.length()==0 && repair_paydates.length()==0 && repair_extras.length()==0)) {
	                		System.out.println("[�����߻�] ���񳻿� IDĭ�� �ٲٰ���� �Ӽ�ĭ�� �����͸� �Է��ؾ� �մϴ�.");
	                		System.out.println("����, �����ID, �뿩ȸ��ID, �� ������������ȣ, ķ��ī ���ID�� ����� �մϴ�.");
	                		isError = true;
	                	}
	                	else {
	                		//���񳻿� ID ����
	                		if(repair_ids.length()!=0) {
	                			repair_idI = Integer.parseInt(repair_ids);
	                			query = "select * from repair where repair_id ="+repair_idI;
	                			rs = stmt.executeQuery(query);
	                			
	                			if(!rs.next()){
	                				System.out.println("[�����߻�: ���񳻿�ID] �ش� ���񳻿�ID�� ���� ���񳻿��� �����ϴ�.");
	                				isError = true;
	                			}
	                			
	                			else {
	                				car_idI=rs.getInt(7);
		                			if(repair_infos.length()!=0) {
			                			info = true;
			                		}
			                		
			                		if(repair_extras.length()!=0) {
			                			extra = true;
			                		}
			               		
				                     // ���� ��¥ ����
				                  	if(repair_dates.length()!=0) {		                  		
							             st = new StringTokenizer(repair_dates,"-");
							             Y=st.nextToken();
							             if(st.hasMoreTokens()) {
								             M=st.nextToken();
								             if(st.hasMoreTokens()) {
									             D=st.nextToken();
									             y=Integer.parseInt(Y);
									             m=Integer.parseInt(M);
									             d=Integer.parseInt(D);
									             if(m>12 || m <1 || d >31 || d<1) {
									            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
									        		 isError=true;
									        		 dateError=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 dateError=true;
								            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
								             }
							             }
							             else {
							            	 System.out.println("[�����߻� : ������¥] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
							            	 isError=true;
							            	 dateError=true;
							             }
							             if(!dateError) {
							            	 date1=true;
							            	 SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
							            	 String datel="";
							            	
							            	 query = "SELECT * FROM rent WHERE Car_car_id = " + car_idI;
							        			rs = stmt.executeQuery(query);
							        			if(rs.next()) {
							        				rs.last();
							        				datel = fm.format(rs.getDate(5));
							        				
							        			}
							        			
							            	 
							            	 if(repair_dates.compareTo(datel)<0) { //�뿩���̺� �뿩��볳�Ա���
					                              System.out.println("[�����߻� : ������¥] ������¥�� �뿩��볳�Ա��� ���� �����̾���մϴ�.");
					                              isError = true;
					                              dateError=true;
					                       }
							             } 
							            
				                  	}	 
				                  	
				                  	//���� ���� ����
				                  	Boolean date1Error=false;
				                	if(repair_paydates.length()!=0) {				             
							             st = new StringTokenizer(repair_paydates,"-");
							             Y=st.nextToken();
							             if(st.hasMoreTokens()) {
								             M=st.nextToken();
								             if(st.hasMoreTokens()) {
									             D=st.nextToken();
									             y=Integer.parseInt(Y);
									             m=Integer.parseInt(M);
									             d=Integer.parseInt(D);
									             if(m>12 || m <1 || d >31 || d<1) {
									            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
									        		 isError=true;
									        		 date1Error=true;
									             }
								             }
								             else { 
								            	 isError=true;
								            	 date1Error=true;
								            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
								             }
							             }
							             else {
							            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
							            	 isError=true;
							            	 date1Error=true;
							             }
							             if(!dateError) {
							            	 date2=true;  	 
							             }
				                	}
				                	
				                	// ���� ��¥, ���Ա��� 
				                	if(!date1 && date2 && !isError) { //date1: ������¥, date2: ���Ա���(���񳻿�) ������¥�� ����, ���Ա��Ѹ� �Է¹���
				                		
				                		query = "select repair_date from repair where repair_id ="+repair_idI; //�̹� �Էµ� ������¥
							        	 rs=stmt.executeQuery(query);
							        	 
							        	 if(!rs.next()) {
							        		 System.out.println("[�����߻�: ���񳻿�ID] �ش� ���񳻿�ID�� ���� ���񳻿��� �����ϴ�.");
							        	 }
							        	 
							        	 else {
							        		 String da=rs.getString(1); 
								        	 
								        	 String Y2, M2, D2;
								             int y2,m2,d2;
								             
								             st = new StringTokenizer(da,"-");
								             Y2=st.nextToken();
								             M2=st.nextToken();
									         D2=st.nextToken();
										     y2=Integer.parseInt(Y2);
										     m2=Integer.parseInt(M2);
										     d2=Integer.parseInt(D2);
							            	 
							            	 
							            	 String Y1, M1, D1;
								             int y1,m1,d1;
								             
								             st = new StringTokenizer(repair_paydates,"-"); //���Ա���
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y2 : ������¥, y1 : ���Ա���
										    	 isError=true;
										    	 dateError=true;
										     }
										     else if(y1==y2){
										    	 if(m1<m2) { 
										    		 dateError=true;
										    		 isError=true;
										    	 }
										    	 else if(m2==m1){
										    		 if(d1<d2) { 
										    			 dateError=true;
										    			 isError=true;
										    		 }
										    	 }
										     }
										     if(dateError) {
										    	 System.out.println("[�����߻� : ���� ���Ѱ� ������¥] ���� ������ �̹� ��ϵ� ������¥ ���� �����̾���մϴ�.");
										     }
							        	 }
							        	
				                	}
							         
				                	//������¥, ���Ա���
				                	if(date1 && !date2 && !isError) { //date1: ������¥, date2: ���Ա��� / ������¥�� �Է¹���
				                		query = "select repair_paydate from repair where repair_id ="+repair_idI;
							        	 rs=stmt.executeQuery(query);
							        	
							        	 if(!rs.next()) {}
							        	else {
							        		 String da =rs.getString(1); // �̹� ������ �ִ� ���Ա���
								        	 
								        	 String Y2, M2, D2;
								             int y2,m2,d2;
								             
								             st = new StringTokenizer(repair_dates,"-"); //������¥
								             Y2=st.nextToken();
								             M2=st.nextToken();
									         D2=st.nextToken();
										     y2=Integer.parseInt(Y2);
										     m2=Integer.parseInt(M2);
										     d2=Integer.parseInt(D2);
							            	 
							            	 
							            	 String Y1, M1, D1;
								             int y1,m1,d1;
								             
								             st = new StringTokenizer(da,"-"); //���Ա���
								             Y1=st.nextToken();
								             M1=st.nextToken();
									         D1=st.nextToken();
										     y1=Integer.parseInt(Y1);
										     m1=Integer.parseInt(M1);
										     d1=Integer.parseInt(D1);
										     
										     if(y1<y2) { //y1: ���Ա���, y2: ������¥
										    	 isError=true;
										    	 dateError=true;
										     }
										     else if(y1==y2){
										    	 if(m1<m2) { 
										    		 dateError=true;
										    		 isError=true;
										    	 }
										    	 else if(m2==m1){
										    		 if(d1<d2) { 
										    			 dateError=true;
										    			 isError=true;
										    		 }
										    	 }
										     }
										     if(dateError) {
										    	 System.out.println("[�����߻� : ���� ��¥�� ���� ����] �̹� ��ϵ� ���Ա����� ������¥ ���� �����̾���մϴ�.");
										     }
							        	 }
									 }
				               
				                	
				                	if(date1 && date2 && !isError) { //date1: ������¥, date2: ���Ա���
							        	 String Y2, M2, D2;
							             int y2,m2,d2;
							             
							             st = new StringTokenizer(repair_dates,"-"); 
							             Y2=st.nextToken();
							             M2=st.nextToken();
								         D2=st.nextToken();
									     y2=Integer.parseInt(Y2);
									     m2=Integer.parseInt(M2);
									     d2=Integer.parseInt(D2);
						            	 
						            	 
						            	 String Y1, M1, D1;
							             int y1,m1,d1;
							             
							             st = new StringTokenizer(repair_paydates,"-");
							             Y1=st.nextToken();
							             M1=st.nextToken();
								         D1=st.nextToken();
									     y1=Integer.parseInt(Y1);
									     m1=Integer.parseInt(M1);
									     d1=Integer.parseInt(D1);
									     
									     if(y1<y2) { //y1 : ���Ա���, y2: ������¥
									    	 isError=true;
									    	 dateError=true;
									     }
									     else if(y1==y2){
									    	 if(m1<m2) { 
									    		 dateError=true;
									    		 isError=true;
									    	 }
									    	 else if(m1==m2){
									    		 if(d1<d2) { 
									    			 dateError=true;
									    			 isError=true;
									    		 }
									    	 }
									     }
									     if(dateError) {
									    	 System.out.println("[�����߻� : ���Ա��Ѱ� ������¥] ���Ա����� ������¥ ���� �����̾���մϴ�.");
									     }
				                	 }
				                	
				                	
				                	// ���� ���
				                	if(repair_prices.length()!=0) {
				                		if(!CheckNumber(repair_prices)) {
				                			System.out.println("[���� �߻� : ���� ���] ��������� ���� ������ �Է��ؾ� �մϴ�.");
				                			isError = true;
				                		}
				                	
				                	}
	                			}
	                		
	                		}
	                
	                		
	                	}
	                	
	                	if(!isError) {
	               
	                		if(date1) { //������¥
	                			query = "UPDATE repair SET repair_date='"+repair_dates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
	                		}
		                	if(date2) { //���Ա���
		                		query = "UPDATE repair SET repair_paydate='"+repair_paydates+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(price) { //�������
		                		query = "UPDATE repair SET repair_price="+repair_priceI+" WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(info) { //���񳻿�
		                		query = "UPDATE repair SET repair_info='"+repair_infos+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	if(extra) { //��Ÿ���񳻿�
		                		query = "UPDATE repair SET repair_extra='"+repair_extras+"' WHERE repair_id=" + repair_idI;
				        		stmt.executeUpdate(query);
		                	}
		                	
		                	System.out.println("�ش� ID�� ���񳻿� ������ �Ϸ�Ǿ����ϴ�.");
			        		query="SELECT * FROM repair WHERE repair_id >=" + 0;
		                    rs = stmt.executeQuery(query);
	
		                    
		                    System.out.println("���񳻿� ID \t ���� ���� ----����----");
	
		                    while(rs.next()) {
		                       System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t �������� ���� -----");
		                       
		                    }
	                	}
	                }
	                   
	                 
	             // �˻�1 ������
	                else if(e.getSource() == s1b) {
	                	String s1_con1s = s1_con1f.getText(); //�뿩�Ⱓ
	                	String s1_con2s = s1_con2f.getText(); //���� ���� �ο���
	                	boolean isError=false;
	                	
	                	System.out.println("�˻�1��ư Ŭ��");
	                	
	                	if(s1_con1s.length()==0 || s1_con2s.length()==0) {
	                		System.out.println("[�����߻�] �˻�1 ������ ��� ����� �մϴ�.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s1_con1s) || !CheckNumber(s1_con2s)) {
	                			System.out.println("[�����߻�] �뿩�Ⱓ�� ���� ���� �ο����� ���ڷ� ����� �մϴ�.");
	                			isError = true;	
	                		}
	                		else {
	                			int s1_con1I =  Integer.parseInt(s1_con1s);
	                			int s1_con2I =  Integer.parseInt(s1_con2s);
	                			
	                			if(s1_con1I ==0) {
	                				System.out.println("[�����߻�] �뿩�Ⱓ�� 0���� Ŀ�� �մϴ�.");
	                				isError = true;
	                			}
	                			
	                			if(s1_con2I==0) {
	                				System.out.println("[�����߻�] �����ο����� 0���� Ŀ�� �մϴ�.");
	                				isError = true;
	                			}
	                			
	                			query = "select Customer_cust_id from rent where rent_period >= "+s1_con1I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[�뿩�Ⱓ] �ش� ���ǿ� �´� ���� �����ϴ�.");
	                				isError = true;
	                			}
	                			
	                			query = "select car_id from car where car_board >= "+s1_con2I;
	                			rs = stmt.executeQuery(query);
	                			if(!rs.next()) {
	                				System.out.println("[���� �����ο���] �ش� ���ǿ� �´� ���� �����ϴ�.");
	                				isError = true;
	                			}
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("�˻�1�� �Ϸ�Ǿ����ϴ�.");
	                		
	                		int s1_con1I =  Integer.parseInt(s1_con1s);
                			int s1_con2I =  Integer.parseInt(s1_con2s);
	                		
                			query = "select *\r\n" + 
                					"from customer\r\n" + 
                					"where cust_id IN(select Customer_cust_id\r\n" + 
                					"				from rent\r\n" + 
                					"				where rent_period >= "+s1_con1I+" And\r\n" + 
                					"                     			         Car_car_id IN(select car_id\r\n" + 
                					"						from car\r\n" + 
                					"						where car_board >="+s1_con2I+"))";
                			
                			
                			 rs = stmt.executeQuery(query);
                			 
    	                     result.setText("");
    	                     result.setText("������������ȣ\t ���� \t�� �ּ� \t�� ��ȭ��ȣ \t�� �̸���  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                
	                //�˻�2 ķ��ī����
	                else if(e.getSource() == s2b) {
	                	String s2_con1s = s2_con1f.getText(); //���������Ǵ�
	                	String s2_con2s = s2_con2f.getText(); //�뿩�Ⱓ
	                	boolean isError=false;
	                	
	                	System.out.println("�˻�2��ư Ŭ��");
	                	
	                	if(s2_con1s.length()==0 || s2_con2s.length()==0) {
	                		System.out.println("[�����߻�] �˻�2 ������ ��� ����� �մϴ�.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s2_con1s) || !CheckNumber(s2_con2s)) {
	                			System.out.println("[�����߻�] ���������Ǵܰ� �뿩�Ⱓ�� ���ڷ� ����� �մϴ�.");
	                			isError = true;
	                		}
	                		else {
	                			
	                			int s2_con1I =  Integer.parseInt(s2_con1s); //���������Ǵ�
	                			
	                			Boolean b;
	                			if(s2_con1I==1) b= true;
	                			else b=false;
	                			
	                			int s2_con2I =  Integer.parseInt(s2_con2s); //�뿩�Ⱓ
	                			if(s2_con2I>0) {
	                				query = "select Rent_rent_id from inspection where repairinfo = "+b;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[��������] �ش� ���ǿ� �´� ķ��ī�� �����ϴ�.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s2_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[�뿩�Ⱓ] �ش� ���ǿ� �´� ķ��ī�� �����ϴ�.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[�����߻�] �뿩�Ⱓ�� 0���� ū ���ڿ��� �մϴ�.");
	                				isError = true;
	                			}
	                	
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("�˻�2�� �Ϸ�Ǿ����ϴ�.");
	                		
	                		int s2_con1I =  Integer.parseInt(s2_con1s);
                			int s2_con2I =  Integer.parseInt(s2_con2s);
                			
                			Boolean b;
                			if(s2_con1I==1) b= true;
                			else b=false;
	                		
                			query = "select *\r\n" + 
                					"from car\r\n" + 
                					"where car_id IN(select Car_car_id\r\n" + 
                					"			 from rent\r\n" + 
                					"			 where rent_period >= "+s2_con2I+" And\r\n" + 
                					"		                      rent_id In(select Rent_rent_id\r\n" + 
                					"                                                             from   inspection\r\n" + 
                					"		                                    where  repairinfo = "+b+"))";
                			
                			 rs = stmt.executeQuery(query);
                			 
                	         result.setText("");
    	                     result.setText("�뿩���ɿ��� \t ķ��īID \t���̸� \t������ȣ \t�����ο���  \t����ȸ�� \t�������� \t��������Ÿ� \t�뿩��� \t������� \t�뿩ȸ��ID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                //�˻�3 ķ��ī����
	                else if(e.getSource() == s3b) {
	                	String s3_con1s = s3_con1f.getText(); //��������Ÿ�
	                	String s3_con2s = s3_con2f.getText(); //�뿩�Ⱓ
	                	boolean isError=false;
	                	
	                	System.out.println("�˻�3��ư Ŭ��");
	                	
	                	if(s3_con1s.length()==0 || s3_con2s.length()==0) {
	                		System.out.println("[�����߻�] �˻�3 ������ ��� ����� �մϴ�.");
	                		isError = true;
	                	}
	                	else {
	                		if(!CheckNumber(s3_con1s) || !CheckNumber(s3_con2s)) {
	                			System.out.println("[�����߻�] ��������Ÿ��� �뿩�Ⱓ�� ���ڷ� ����� �մϴ�.");
	                			isError = true;
	                		}
	                		else {
	                			int s3_con1I =  Integer.parseInt(s3_con1s); //��������Ÿ�
	                			int s3_con2I =  Integer.parseInt(s3_con2s); //�뿩�Ⱓ
	                			
	                			if(s3_con2I>0) {
	                				query = "select * from car where car_distance >="+s3_con1I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[�����߻� : ��������Ÿ�] �ش� ���ǿ� �´� ���� �����ϴ�.");
		                				isError = true;
		                			}
		                			
		                			query = "select Car_car_id from rent where rent_period >= "+s3_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[�����߻� : �뿩�Ⱓ] �ش� ���ǿ� �´� ���� �����ϴ�.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[�����߻� : �뿩�Ⱓ] �뿩�Ⱓ�� 0���� ū ���ڷ� �ؾ��մϴ�.");
	                				isError = true;
	                			}
	                		
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("�˻�3�� �Ϸ�Ǿ����ϴ�.");
	                		
	                		int s3_con1I =  Integer.parseInt(s3_con1s);
                			int s3_con2I =  Integer.parseInt(s3_con2s);
	                		
                			query = "select *\r\n" + 
                					"from car\r\n" + 
                					"where car_distance >="+s3_con1I+" And\r\n" + 
                					"      car_id IN(select Car_car_id\r\n" + 
                					"                 from rent\r\n" + 
                					"	    where rent_period >= "+s3_con2I+")";
                			
                			 rs = stmt.executeQuery(query);
                			 
                	         result.setText("");
    	                     result.setText("�뿩���ɿ��� \t ķ��īID \t���̸� \t������ȣ \t�����ο���  \t����ȸ�� \t�������� \t��������Ÿ� \t�뿩��� \t������� \t�뿩ȸ��ID \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
    	                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
    	                        result.append(str);
    	                       
    	                     }
	                	}
	                }
	                
	                // �˻�4 ������
	                else if(e.getSource() == s4b) {
	                	String s4_con1s = s4_con1f.getText(); //���� �� 
	                	String s4_con2s = s4_con2f.getText(); //�������
	                	boolean isError=false;
	                	
	                	System.out.println("�˻�4��ư Ŭ��");
	                	
	                	if(s4_con1s.length()==0 || s4_con2s.length()==0) {
	                		System.out.println("[�����߻�] �˻�4 ������ ��� ����� �մϴ�.");
	                		isError = true;
	                	}
	                	else {
	                		if( CheckNumber(s4_con1s) || !CheckNumber(s4_con2s)) {
	                			System.out.println("[�����߻�] ���� ���� ���ڷ� ��������� ���ڷ� ����� �մϴ�.");
	                			isError = true;
	                		}
	                		else {
	                			int s4_con2I =  Integer.parseInt(s4_con2s); //�������
	                			
	                			if(s4_con2I>0) {
	                				query = "select * from customer where cust_name Like'"+s4_con1s+"%'";
	    	                		
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[���� ��] �ش� ���ǿ� �´� ���� �����ϴ�.");
		                				isError = true;
		                			}
		                			
		                			query = "select * from repair where repair_price >= "+s4_con2I;
		                			rs = stmt.executeQuery(query);
		                			if(!rs.next()) {
		                				System.out.println("[�������] �ش� ���ǿ� �´� ���� �����ϴ�.");
		                				isError = true;
		                			}
	                			}
	                			else {
	                				System.out.println("[�����߻�] ��������� 0���� ū ���ڷ� �Է��ؾ� �մϴ�.");
	                				isError = true;
	                			}
	                			
	                			
	                		}
	                	}
	                	
	                	
	                	if(!isError) {
	                		System.out.println("�˻�4�� �Ϸ�Ǿ����ϴ�."); 	
                			int s4_con2I =  Integer.parseInt(s4_con2s);
	                		
                			query ="select * from customer where cust_name like '"+s4_con1s+"%' And "
                                    + "cust_id IN(select Customer_cust_id from repair where repair_price >="+s4_con2I+")";

                			
                			 rs = stmt.executeQuery(query);
                			 
    	                     result.setText("");
    	                     result.setText("������������ȣ\t ���� \t�� �ּ� \t�� ��ȭ��ȣ \t�� �̸���  \n");
    	                     
    	                     while(rs.next()) {                    
    	                        String str = rs.getString(1) + "\t" + rs.getString(2)  + "\t" + rs.getString(3) + "\t" + 
    	                              rs.getString(4) +  "\t" + rs.getString(5) +"\n";
    	                        result.append(str);
    	                     }
	                	}
	                }
	                  
	              
	            } catch (Exception e2) {
	                System.out.println("���� �б� ���� :" + e2);
	            }  
	         
	      }
	   }

   class Upanel extends JPanel implements ActionListener{
	      
	      JLabel user = new JLabel("�Ϲݻ����");
	      JTextArea resultArea = new JTextArea("");
	      JLabel b_num = new JLabel("���� �ο���");
	      JTextField b_n = new JTextField(3);
	      JLabel b_label = new JLabel("�� �̻�");
	      JButton searchB = new JButton("�˻�");
	      
	      JButton ableCarB = new JButton("�뿩������ ķ��ī ����");
	      
	      JLabel car_idl = new JLabel("ķ��ī ID");
	      JTextField car_idf = new JTextField(3);
	      JLabel comp_idl = new JLabel("ķ��ī�뿩ȸ�� ID");
	      JTextField comp_idf = new JTextField(3);
	      JLabel cust_idl = new JLabel("���������ȣ");
	      JTextField cust_idf = new JTextField(12);
	      JLabel start_datel = new JLabel("�뿩������");
	      JTextField start_datef = new JTextField(9);
	      JLabel periodl = new JLabel("�뿩�Ⱓ");
	      JTextField periodf = new JTextField(3);
	      
	      JLabel pricel = new JLabel("û�����");
	      JTextField pricef = new JTextField(6);
	      JLabel paydatel = new JLabel("���Ա���");
	      JTextField paydatef = new JTextField(7);
	      JLabel extral = new JLabel("��Ÿû������");
	      JTextField extraf = new JTextField(7);
	      JLabel extrafeel = new JLabel("��Ÿû���������");
	      JTextField extrafeef = new JTextField(5);
	      
	      JButton rentB = new JButton("�뿩");
	      
	      JPanel northUP = new JPanel();
	      JPanel centerUP = new JPanel();
	      //JPanel center2UP = new JPanel();
	      JPanel southUP = new JPanel();
	    
	     
	      public Upanel() {
	         northUP.setBackground(new Color(252,231,231));
	         centerUP.setBackground(new Color(252,231,231));
	         southUP.setBackground(new Color(252,231,231));
	         
	         
	         resultArea.setEditable(false);
	         JScrollPane scrollPane = new JScrollPane(resultArea);
	        
	         
	         user.setPreferredSize(new Dimension(350,20));
	         
	         searchB.addActionListener(this);
	         rentB.addActionListener(this);
	         ableCarB.addActionListener(this);
	         
	         northUP.add(user);
	         northUP.add(b_num);
	         northUP.add(b_n);
	         northUP.add(b_label);
	         northUP.add(searchB);
	         northUP.add(ableCarB);
	         
	         
	         southUP.add(car_idl);
	         southUP.add(car_idf);
	         southUP.add(comp_idl);
	         southUP.add(comp_idf);
	        
	         southUP.add(cust_idl);
	         southUP.add(cust_idf);
	         southUP.add(start_datel);
	         southUP.add(start_datef);
	         southUP.add(periodl);
	         southUP.add(periodf);
	         
	         southUP.add(pricel);
	         southUP.add(pricef);
	         southUP.add(paydatel);
	         southUP.add(paydatef);
	         southUP.add(extral);
	         southUP.add(extraf);
	         southUP.add(extrafeel);
	         southUP.add(extrafeef);
	         
	         
	         southUP.add(rentB);
	         
	         setLayout(new BorderLayout(0,0));
	         add(northUP,BorderLayout.NORTH);
	         northUP.setPreferredSize(new Dimension(375,100));
	         add(scrollPane,BorderLayout.CENTER);
	         
	         add(southUP,BorderLayout.SOUTH);
	         southUP.setPreferredSize(new Dimension(375,300));

	    
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
	         String query;
	         try {
	               
	             stmt = con.createStatement();
	              
	               if(e.getSource()==searchB) {
	                  System.out.println("�˻�!");
	                  String b_numS = b_n.getText();
	                  
	                  if(b_numS.length()==0) {
	                	  System.out.println("[�����߻�: �����ο���] �����ο����� ���ڷ� �Է��ؾ� �մϴ�.");
	                  }
	                  else {
	                	   // ���ڷθ� �Է��ؾ���
		                  if(!isNum(b_numS)) {
		                    System.out.println("[�����߻�: �����ο���] �����ο����� ���ڷθ� �Է��ؾ� �մϴ�");
		                  }
		                  
		                  else {
		                     int b_numI = Integer.parseInt(b_numS);
		                     
		                     if(b_numI>0) {
		                    	   query="SELECT * FROM Car WHERE car_board >=" + b_numI;
				                     rs = stmt.executeQuery(query);

				                     resultArea.setText("");
				                     resultArea.setText("�뿩���ɿ��� \t ķ��īID \t���̸� \t������ȣ \t�����ο���  \t����ȸ�� \t�������� \t��������Ÿ� \t�뿩��� \t������� \t�뿩ȸ��ID \n");
				                     
				                     int i=0;
				                     while(rs.next()) {                    
				                        String str = carBoolean[rs.getInt(1)] + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
				                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
				                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
				                        resultArea.append(str);
				                        
				                        i++;
				                     }
		                     }
		                     else {
		                    	 System.out.println("[�����߻� : �����ο���] �����ο����� 0���� ū ���ڷ� �Է��ؾ� �մϴ�.");
		                     }
		               
		                    
		                  }
	                  }
	               
	               }
	               else if(e.getSource()==ableCarB) {
	            	   System.out.println("�뿩������ ķ��ī����!");
	                    
		                  query="SELECT * FROM Car";
		                  rs = stmt.executeQuery(query);
		                  resultArea.setText("");
		                  resultArea.setText("ķ��īID \t���̸� \t������ȣ \t�����ο���  \t����ȸ�� \t�������� \t��������Ÿ� \t�뿩��� \t������� \t�뿩ȸ��ID \n");
		                  int i=0;
		                  while(rs.next()) {                    
		                        String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + 
		                              rs.getInt(4) +  "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" +
		                              rs.getInt(7) + "\t" + rs.getInt(8)+"\t" + rs.getDate(9)+"\t" + rs.getInt(10)+"\n";
		                        if(carBoolean[rs.getInt(1)]==true) resultArea.append(str);
		                        
		                        i++;
		                  }
		                    
		                 
	               }
	               else if(e.getSource()==rentB) {
	                  Boolean isError = false;
	                  Boolean carError=false;
	                  
	                  
	                  System.out.println("�뿩��ư Ŭ��!");
	                  
	                  String car_idS = car_idf.getText();
	                  String comp_idS = comp_idf.getText();
	                  String cust_idS = cust_idf.getText();
	                  String car_start_dateS = start_datef.getText();
	                  String car_periodS = periodf.getText();
	                  String priceS=pricef.getText();
	                  String pay_dateS = paydatef.getText();
	                  String extraS = extraf.getText();
	                  String extrafeeS = extrafeef.getText();
	                  
	                  
	                  if(car_idS.length()==0 || comp_idS.length()==0 || cust_idS.length()==0 || car_start_dateS.length()==0 ||
	                		  car_periodS.length()==0 || pay_dateS.length()==0 || extraS.length()==0 || extrafeeS.length()==0) {
	                	  isError = true;
	                	  System.out.println("[�����߻� : ������ �Է� ����] ��� ĭ�� �����͸� �Է����ּ���.");
	                  }
	                  else {
	                	  
	                	//�뿩�Ⱓ ���� Ȯ��
	                	 if(CheckNumber(car_periodS)) {
	                		 int car_periodI = Integer.parseInt(car_periodS);
	                		 if(car_periodI>0) {}
		                     else {
		                    	 System.out.println("[�����߻� : �뿩�Ⱓ] �뿩�Ⱓ�� 0���� ū ���ڷ� �Է��ؾ� �մϴ�.");
		                    	 isError = true;
		                     }
	                	 }
	                	 else {
	                		 System.out.println("[�����߻� : �뿩�Ⱓ] �뿩�Ⱓ�� 0���� ū ���ڷ� �Է��ؾ� �մϴ�.");
	                		 isError = true;
	                	 }
	                     
	                     	                     
	                     // ķ��ī ID ���� Ȯ��
	                     if(CheckNumber(car_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
		                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
		                     rs = stmt.executeQuery(query);
		                     if(!rs.next()) { 
		                        System.out.println("[�����߻� : ķ��ī ID] �ش� ID�� ķ��ī�� �����ϴ�.");
		                        isError=true;
		                        carError=true;
		                     }
		                     else {
		                    	 
		                    	 int i = rs.getInt(1);
		                    	 if(carBoolean[i]==false) {
		                    		 System.out.println("[�����߻� : �뿩�Ұ��� ķ��ī] �ش� ID�� ķ��ī�� ���� �뿩 �Ұ����Դϴ�.");
		 	                        isError=true;
		 	                        carError=true;
		                    	 }
		                     }
	                     }
	                     else {
	                    	 isError=true;
	                    	 carError=true;
	                    	 System.out.println("[�����߻� : ķ��ī ID] ķ��ī ID�� ���� �������� �մϴ�.");
	                     }
	                     
	                     // ķ��ī�뿩ȸ�� ID ���� Ȯ��
	                     if(CheckNumber(comp_idS)) {
	                    	 int car_idI = Integer.parseInt(car_idS);
	                    	 if(!carError) {
			                     query = "SELECT * FROM car WHERE car_id =" + car_idI;
			                     rs = stmt.executeQuery(query);
			                     if(rs.next()) {
			                    	 int i = rs.getInt(10); 
			                    	 if(Integer.parseInt(comp_idS)!=i) {
			                    		 isError=true;
			                   		 System.out.println("[�����߻� : ȸ�� ID] ķ��ī ID�� ȸ�� ID�� ���� �����ʽ��ϴ�.");
			                    	 }
			                     }
			                   	
			                    	 
			                 }
	                    	 
	                     }
	                     else {
	                    	 System.out.println("[�����߻� : ȸ�� ID] ȸ�� ID�� ���� �������� �մϴ�.");
	                     }

	                     // ���������ȣ ���� Ȯ��
	                     if(cust_idS.length()!=0) {
	                     query = "SELECT * FROM customer WHERE cust_id = '" + cust_idS+"'";
	                     rs = stmt.executeQuery(query);
	                     if(!rs.next()) { 
	                        System.out.println("[�����߻� : ���������ȣ] �ش� ���������ȣ�� ���� �����ϴ�.");
	                        isError=true;
	                     }
	                     


	                     // ķ��ī �뿩������ ��¥ ����
	                     String Y, M, D;
	                     int y,m,d;

	                     StringTokenizer st = new StringTokenizer(car_start_dateS,"-");
	                     Y=st.nextToken();
	                     M=st.nextToken();
	                     D=st.nextToken();

	                     y=Integer.parseInt(Y);
	                     m=Integer.parseInt(M);
	                     d=Integer.parseInt(D);

	                     if(m>12 || m <1 || d >31 || d<1) {
	                        System.out.println("[�����߻� : �뿩������ ����] �뿩������  YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
	                        isError=true;
	                     }   
	                     else {
	                       
	                        // ķ��ī �������, ķ��ī �뿩������ �� 
	                        query = "SELECT car_date FROM car where car_id = '" + car_idS +"'"; //�������
	                        rs = stmt.executeQuery(query);
	                        if(rs.next()) {
	                           String str = rs.getString(1);
	                           if(str.compareTo(car_start_dateS)>0) {
	                              System.out.println("[�����߻� : ķ��ī ������ں��� ����] �ش� ķ��ī�� ���� ��ϵǾ� ���� �ʽ��ϴ�.");
	                              isError = true;
	                           }
	                        }
	                     }
	                 
	                  }
	                     
	                   // ���� ���� Ȯ��
	                   if(CheckNumber(priceS)) {
	                	   int price = Integer.parseInt(priceS);
	                	   if(price<=0) {
	                		   System.out.println("[�����߻� : û�����] û������� ���� �����̾���մϴ�.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[�����߻� : û�����] û������� ���� �����̾���մϴ�.");
                           isError = true;
	                   }
	                   
	                   // ��Ÿû����� ���� Ȯ��
	                   if(CheckNumber(extrafeeS)) {
	                	   int price = Integer.parseInt(extrafeeS);
	                	   if(price<=0) {
	                		   System.out.println("[�����߻� : ��Ÿû�����] ��Ÿû������� ���� �����̾���մϴ�.");
	                           isError = true;
	                	   }
	                   }
	                   else {
	                	   System.out.println("[�����߻� : ��Ÿû�����] ��Ÿû������� ���� �����̾���մϴ�.");
                           isError = true;
	                   }
	                   
	                   // ���Ա��ѳ�¥ ����Ȯ��
	                   
	                   Boolean date=false;
	                   StringTokenizer st;
	                   String Y,M,D;
	                   int y,m,d;
	                   
	                   if(pay_dateS.length()!=0) {
				             st = new StringTokenizer(pay_dateS,"-");
				             Y=st.nextToken();
				             if(st.hasMoreTokens()) {
					             M=st.nextToken();
					             if(st.hasMoreTokens()) {
						             D=st.nextToken();
						             y=Integer.parseInt(Y);
						             m=Integer.parseInt(M);
						             d=Integer.parseInt(D);
						             if(m>12 || m <1 || d >31 || d<1) {
						            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
						        		 isError=true;
						        		 date=true;
						             }
					             }
					             else { 
					            	 isError=true;	
					            	 date=true;
					            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
					             }
				             }
				             else {
				            	 System.out.println("[�����߻� : ���Ա���] YYYY-MM-DD���� MM�� 1~12����, DD�� 1~31������ ������ �մϴ�.");
				            	 isError=true;		
				            	 date=true;
				             }
				            
	                	}
	                   if(!date) {
	                         
	                         if(car_start_dateS.compareTo(pay_dateS)>=0) {
	                                 System.out.println("[�����߻� : ���Ա���] ���Ա����� �뿩��¥���� �����̾���մϴ�.");
	                                 isError = true;
	                                 date=true;
	                          }
	                    }
		                 
	                   	int car_idI = Integer.parseInt(car_idS);
		                 // ������ ������ �뿩
		                 if(!isError) {
		                    carBoolean[car_idI]=false;
		                   
		                    System.out.println("ķ��ī �뿩�� �Ϸ�Ǿ����ϴ�.");
		                    
		                    query="SELECT * FROM rent";
		                    rs=stmt.executeQuery(query);
		                    rs.last();
		                     
		                    int rent_idI=rs.getInt(1)+1;
		                       
		                    PreparedStatement statement = null;
	                       	statement = con.prepareStatement("insert into rent (rent_id,rent_start,rent_period,rent_price,rent_paydate,rent_extra,rent_extrafee,Car_car_id,Car_Company_comp_id,Customer_cust_id)"+"value(?,?,?,?,?,?,?,?,?,?)");
	                    
	                        statement.setInt(1,rent_idI);
	                        statement.setDate(2, java.sql.Date.valueOf(car_start_dateS));
	                        statement.setInt(3, Integer.parseInt(car_periodS));
	                        statement.setInt(4, Integer.parseInt(priceS));
	                        statement.setDate(5,java.sql.Date.valueOf(pay_dateS));
	                        statement.setString(6,extraS);
	                        statement.setInt(7,Integer.parseInt(extrafeeS));
	                        statement.setInt(8,Integer.parseInt(car_idS));
	                        statement.setInt(9, Integer.parseInt(comp_idS));
	                        statement.setString(10,cust_idS);
	                             
	                             
					        statement.executeUpdate();
					             

	                        rs=stmt.executeQuery(query);
		                    rs.last();
		                    System.out.println("ķ��ī �뿩�� �Ϸ�Ǿ����ϴ�.");
		                    System.out.println("�뿩ID \t �뿩������ \t ---- ���� ----");
		                    String str = rs.getInt(1) +"\t"+ rs.getDate(2) + "\t---- ���� ----";
		               
		                    System.out.println(str);
		                       
		                 }
	                  }
	               }
	             
	           } catch (Exception e2) {
	               System.out.println("���� �б� ���� :" + e2);
	           } 
	         
	      }
	   }

	   
	   public project() {
	        contactDB();
	        setTitle("18011571_18011584/����ȿ_�̼���");
	        setSize(1015,650);
	        this.setResizable(false);
	        setVisible(true);
	       
	        Arrays.fill(carBoolean, Boolean.FALSE);
	        Arrays.fill(repairBoolean, Boolean.FALSE);
	        
	        Container c=getContentPane();
	        c.setLayout(new BorderLayout());
	        
	        add(new JScrollPane(mp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.WEST);
	        mp.setPreferredSize(new Dimension(590,1000));
	        add(up,BorderLayout.EAST);
	        up.setPreferredSize(new Dimension(390,650));
	        
	        
	        
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	       
	   }
	   
   
   public void contactDB() {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("����̹� �ε� ����");
        } catch(ClassNotFoundException e) {
           e.printStackTrace();
        }
        try {
            System.out.println("�����ͺ��̽� ���� �غ� ...");
            con=DriverManager.getConnection(url,userid,pwd);
            System.out.println("�����ͺ��̽� ���� ����");
          } catch(SQLException e1) {
             e1.printStackTrace();
       }
       
   }
   
   public static void main(String[] args) {
      project pro=new project();
      
   }
   
   
      
}