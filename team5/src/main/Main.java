package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.IUsersDAO;
import model.UsersDAO;
import model.UsersVO;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Session s = new Session();
	
	static IUsersDAO uDAO = new UsersDAO();
	static UsersVO uVO = new UsersVO();
	
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
			System.out.println(uVO.getUserId() + "님 안녕하세요");
			System.out.println("---------------------------------------------");
			System.out.println("(1)회원정보 | (2)상품보기 | (3)로그아웃 | (4)종료 ");
			System.out.println("---------------------------------------------");
			System.out.print("메뉴 번호 입력: ");
			try {
				int num = sc.nextInt();
				sc.nextLine();
				switch(num) {
				
				// 회원정보
				case 1 :
					System.out.println("***회원정보***");
					uVO = uDAO.getUser(uVO.getUserId());
					System.out.println(uVO);
					System.out.println("(1)회원정보수정 | (2)회원탈퇴 | (3)뒤로가기");
					System.out.print("메뉴 번호 입력: ");
					int userMenuSelect = sc.nextInt();
					sc.nextLine();
					
					// 회원정보 수정
					if (userMenuSelect == 1) {
						updateUser();						
					
					// 회원정보 삭제
					} else if (userMenuSelect == 2) {
						System.out.print("아이디 : ");
						String id = sc.nextLine();
						System.out.print("비밀번호 : ");
						String pwd = sc.nextLine();
						if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserId())) {
							deleteUser();
						}
					
					// 뒤로가기
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
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		try {
			uVO = uDAO.getUser(id);
			
			System.out.println(uVO);
			if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserPassword())) {
				s.loginUserId = uVO.getUserId();
				mainPage();
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void register() {		
		System.out.print("아이디: ");
		uVO.setUserId(sc.nextLine());
		System.out.print("비밀번호: ");
		uVO.setUserPassword(sc.nextLine());
		System.out.print("이름: ");
		uVO.setUserName(sc.nextLine());
		System.out.print("생년월일: ");
		uVO.setUserBirth(sc.nextLine());
		System.out.print("핸드폰번호: ");
		uVO.setUserPhoneNumber(sc.nextLine());
		System.out.print("주소: ");
		uVO.setUserAddress(sc.nextLine());
		try {	
			uDAO.insertUser(uVO);
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
		System.out.print("핸드폰 : ");
		uVO.setUserPhoneNumber(sc.nextLine());
		System.out.print("주소 : ");
		uVO.setUserAddress(sc.nextLine());
		try {
			uDAO.updateUser(uVO);
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void deleteUser() {
//		try {
			uDAO.deleteUser(uVO);
//		};
	}
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
