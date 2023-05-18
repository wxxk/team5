package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.UsersDAO;
import model.UsersVO;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static UsersVO user = new UsersVO();
	public static UsersDAO userDAO = new UsersDAO();
	
	public static void main(String[] args) {

		while(true) {
			System.out.println("---------------------------------------------");
			System.out.println("안녕하세요. 환영합니다.");
			System.out.println("---------------------------------------------");
			System.out.println("(1)로그인 | (2)회원가입 | (3)종료 ");
			System.out.println("---------------------------------------------");
			System.out.print("메뉴 번호 입력: ");
			try {
				int num = sc.nextInt();
				sc.nextLine();
				switch(num) {
				case 1 :
					login();
					break;
				case 2 :
					register();
					break;
				case 3 : 
					exit();
					break;
				case 9 :
					admin();
					break;
				default:
					System.out.println("잘못된 선택");
				}
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public static void mainPage() {
		while(true) {
			System.out.println("---------------------------------------------");
			System.out.println(user.getUserId() + "님 안녕하세요");
			System.out.println("---------------------------------------------");
			System.out.println("(1)회원정보 | (2)상품보기 | (3)로그아웃 | (4)종료 ");
			System.out.println("---------------------------------------------");
			System.out.println("메뉴 번호 입력: ");
			try {
				int num = sc.nextInt();
				sc.nextLine();
				switch(num) {
				case 1 :
					System.out.println("***회원정보***");
					System.out.println(user.getUserId());
					System.out.println(user.getUserName());
					System.out.println(user.getUserBirth());
					System.out.println(user.getUserPhoneNumber());
					System.out.println(user.getUserAddress());
					System.out.println("(1)회원정보수정 | (2)회원탈퇴 | (3)뒤로가기");
					int userMenuSelect = sc.nextInt();
					sc.nextLine();
					if (userMenuSelect == 1) {
						updateUser();						
					} else if (userMenuSelect == 2) {
						deleteUser();
					} else if (userMenuSelect == 3) {
						mainPage();
					}
					break;
				case 2 :
					break;
				case 3 :
					main(null);
					break;
				case 4:
					exit();
					break;
				default:
					System.out.println("잘못된 선택");
				}
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// START USER ===========================================================================
	public static void login() {
		Connection con = null;
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		String sql = "select * from users where user_id=? and user_password=?";
		try {
			con = DataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getString(1));
				user.setUserPassword(rs.getString(2));
				user.setUserName(rs.getString(3));
				user.setUserBirth(rs.getString(4));
				user.setUserPhoneNumber(rs.getString(5));
				user.setUserAddress(rs.getString(6));
				mainPage();
			}
		} catch (SQLException e) {
			System.out.println("비밀번호가 틀렸습니다.");
		} finally {
			if (con!=null) {
				try {con.close();} catch(Exception e) {}
			}
		}
	
	}

	public static void register() {
			System.out.print("아이디: ");
			user.setUserId(sc.nextLine());
			System.out.print("비밀번호: ");
			user.setUserPassword(sc.nextLine());
			System.out.print("이름: ");
			user.setUserName(sc.nextLine());
			System.out.print("생년월일: ");
			user.setUserBirth(sc.nextLine());
			System.out.print("핸드폰번호: ");
			user.setUserPhoneNumber(sc.nextLine());
			System.out.print("주소: ");
			user.setUserAddress(sc.nextLine());
		try {	
			userDAO.insertUser(user);
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateUser() {
		// 이름, 핸드폰번호, 주소 수정
		UsersVO uVO = new UsersVO();
		System.out.println("***수정***");
		System.out.print("이름 : ");
		uVO.setUserName(sc.nextLine());
		System.out.println("핸드폰 : ");
		uVO.setUserPhoneNumber(sc.nextLine());
		System.out.println("주소 : ");
		uVO.setUserAddress(sc.nextLine());
		try {
			userDAO.updateUser(uVO);
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void deleteUser() {}
	// END USER =========================================================================
	
	
	// START PRODUCT ====================================================================
	public static void product() {
		
	}
	
	// END PRODUCT ======================================================================
	
	
	
	public static void cart() {
		
	}
	
	public static void order() {
		
	}
	
	public static void admin() {
		
	}
	
	public static void exit() {
		
	}
	
}
