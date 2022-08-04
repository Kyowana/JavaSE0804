package com.edu.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edu.seiryo.*;

public class LoginPage {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String pattern = "[A-Z].*[!@#&()]";
//		TeacherInfo teacher = new TeacherInfo();
		List<TeacherInfo> listT = new ArrayList<>();
		
		List<StudentInfo> listS = new ArrayList<>();
		listS.add(new StudentInfo("1", "張三", "男", "20", "11301"));
		listS.add(new StudentInfo("2", "李四", "男", "21", "11302"));
		listS.add(new StudentInfo("3", "王五", "男", "20", "11301"));
		listS.add(new StudentInfo("4", "麻六", "男", "19", "11301"));
		listS.add(new StudentInfo("5", "趙七", "男", "18", "11301"));
		
		register(scan, pattern, listT);
		
		while(true) {
			System.out.println("請輸入 1:註冊 2:登入");
			String op1 = scan.next();
			if (op1.equals("1")) {
				register(scan, pattern, listT);
			} else if (op1.equals("2")){
				TeacherInfo nowUser = new TeacherInfo();
				boolean isCorrect = false;
				for (int i = 0; i < 3; i++) {
					System.out.println("請登入系統");
					System.out.print("請輸入帳號: ");
					String account = scan.next();
					nowUser.setAccount(account);
					System.out.print("請輸入密碼: ");
					String pwd = scan.next();
					nowUser.setPwd(pwd);
					for (TeacherInfo teacherInfo : listT) {
						if (teacherInfo.getAccount().equals(account) && teacherInfo.getPwd().equals(pwd)) {
							isCorrect = true;
							System.out.println("登入成功");
				            break;
				        }
					}
					if (isCorrect)
						break;
					System.out.println("帳號或密碼錯誤，剩餘" + (2-i) + "次機會");
					if (i == 2 && !isCorrect) {
						System.out.println("連續輸入錯誤達3次，系統退出");
						return;
					}
				}
				if (isCorrect) {
					while(true) {
						reviewInfo(scan, listS);						
					}
				}
			} else {
				System.out.println("輸入錯誤，請重新選擇功能");
			}
		}
		
		
		

	}

	private static void reviewInfo(Scanner scan, List<StudentInfo> listS) {
		System.out.println("歡迎使用青凌學生管理系統");
		System.out.println("======================");
		System.out.println("1. 顯示所有學生資料");
		System.out.println("2. 根據編號查詢學生資料");
		System.out.println("3. 根據編號修改學生年齡");
		System.out.println("4. 根據姓名修改學生班級");
		System.out.println("5. 根據班級查詢全部學生資料");
		System.out.println("======================");
		System.out.print("請選擇[1/2/3/4/5]: ");
		String op2 = scan.next();
		switch(op2) {
		case "1":
			System.out.println("======================");
			System.out.println("編號\t姓名\t性別\t年齡\t班級");
			for (StudentInfo item : listS) {
				item.printInfo(item);
			}
			System.out.println("======================");
			askContinue(scan);
			break;
		case "2":
			System.out.print("請輸入需要查詢的學生編號: ");
			String num = scan.next();
			if (!listS.stream().filter(o -> o.getNum().equals(num)).findFirst().isPresent()) {
				System.out.println("======================");
				System.out.println("查無此人");
				System.out.println("======================");
			} else {
				for (StudentInfo item : listS) {				
					if (item.getNum().equals(num)) {
						System.out.println("======================");
						item.printInfo(item);
						System.out.println("======================");
						break;
					}
				}							
			}
			askContinue(scan);
			break;
			
		case "3":
			System.out.print("請輸入學生編號: ");
			String num1 = scan.next();
			if (!listS.stream().filter(o -> o.getNum().equals(num1)).findFirst().isPresent()) {
				System.out.println("======================");
				System.out.println("查無此人");
				System.out.println("======================");
			} else {
				System.out.print("請輸入年齡: ");
				String age = scan.next();
				try{
					Integer.parseInt(age);
					for (StudentInfo item : listS) {				
						if (item.getNum().equals(num1)) {
							item.setAge(age);
							System.out.println("======================");
							System.out.println("修正後資料為");
							item.printInfo(item);
							System.out.println("======================");
							break;
						}
					}
		        } catch (NumberFormatException ex) {
		            System.out.println("年齡請輸入整數");
		        }											
			}
			askContinue(scan);
			break;
			
		case "4":
			System.out.print("請輸入學生姓名: ");
			String name = scan.next();
			if (!listS.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent()) {
				System.out.println("======================");
				System.out.println("查無此人");
				System.out.println("======================");
			} else {
				System.out.print("請輸入學生班級: ");
				String classes = scan.next();
				for (StudentInfo item : listS) {				
					if (item.getName().equals(name)) {
						item.setClasses(classes);
						System.out.println("======================");
						System.out.println("修正後資料為");
						item.printInfo(item);
						System.out.println("======================");
						break;
					}
				}							
			}
			askContinue(scan);
			break;
			
		case "5":
			System.out.print("請輸入欲查詢班級: ");
			String classes = scan.next();
			if (!listS.stream().filter(o -> o.getClasses().equals(classes)).findFirst().isPresent()) {
				System.out.println("======================");
				System.out.println("無此班級");
				System.out.println("======================");
			} else {
				System.out.println("======================");
				for (StudentInfo item : listS) {				
					if (item.getClasses().equals(classes)) {
						item.printInfo(item);
					}
				}							
				System.out.println("======================");
			}
			askContinue(scan);
			break;
			
		default:
			System.out.println("輸入錯誤，請重新選擇功能");
			break;
		}
	}

	private static void askContinue(Scanner scan) {
		while(true) {
			System.out.print("是否繼續[y/n]: ");
			String yn = scan.next();
			if (yn.equalsIgnoreCase("Y")) {
				break;
			} else if (yn.equalsIgnoreCase("N")) {
				System.out.println("登出系統");
				System.exit(0);
			} else {
				System.out.println("輸入錯誤，請重新輸入");
			}							
		}
	}

	private static void register(Scanner scan, String pattern, List<TeacherInfo> listT) {
		TeacherInfo teacher = new TeacherInfo();
		while(true) {
			System.out.print("請註冊帳號: ");
			String account = scan.next();
			if(!account.matches(pattern)) {
				System.out.println("帳號必須符合首字母大寫；最後一個字為特殊符號，例!@#&()");
			} else {
				teacher.setAccount(account);
				break;
			}
		}
		while(true) {
			System.out.print("請設定密碼: ");
			String pwd = scan.next();
			if(!pwd.matches(pattern)) {
				System.out.println("密碼必須符合首字母大寫；最後一個字為特殊符號，例!@#&()");
			} else {
				teacher.setPwd(pwd);
				System.out.println("註冊成功");
				break;						
			}
		}
		listT.add(teacher);
	}

}
