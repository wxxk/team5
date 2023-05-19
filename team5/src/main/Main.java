package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Session s = new Session();
	
	static IUsersDAO uDAO = new UsersDAO();
	static UsersVO uVO = new UsersVO();
	
	static IOrderDAO oDAO = new OrderDAO();
	static OrderVO oVO = new OrderVO();

	public static UsersVO user = new UsersVO();
	public static UsersDAO userDAO = new UsersDAO();
	
	public static AdminVO adminVO = new AdminVO();
	public static IAdminDAO aDAO = new AdminDAO();
	
	public static ProductVO proVO = new ProductVO();
	public static IProductDAO proDAO = new ProductDAO();
	
	public static CartVO cVO = new CartVO();
	public static CartDAO cDAO = new CartDAO();

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
			System.out.println("(1)회원정보 | (2)상품보기 | (3)로그아웃 | (4)장바구니 | (5)주문내역");
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
						System.out.println("***회원 탈퇴***");
						System.out.print("아이디 : ");
						String id = sc.nextLine();
						System.out.print("비밀번호 : ");
						String pwd = sc.nextLine();
						if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserId())) {
							deleteUser();
							main(null);
						}
					
					// 뒤로가기
					} else if (userMenuSelect == 3) {
						mainPage();
					}
					break;
					
					
				case 2 :
					System.out.println("***상품목록***");
					product();
					break;

				case 3 :
					s.loginUserId = null;
					main(null);
					break;
				case 4:
					cart();
					break;
				case 5:
					order();
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
//			adminVO = aDAO.getAdmin(id);
//			System.out.println(uVO);
			if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserPassword())) {
				
				s.loginUserId = uVO.getUserId();
				mainPage();
//			}else if(id.equals("admin01")){
//				System.out.println("xka???????");
////				s.loginAdminId = adminVO.getAdminId();
//				System.out.println("탐?");
//				admin();
			}else{
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
		// 삭제안됨
		try {
			uDAO.deleteUser(uVO);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// END USER =========================================================================
	
	
	// START PRODUCT ====================================================================
	public static void product() {
		try {
			ArrayList<ProductVO> pVO = proDAO.getAllProducts();
			for (ProductVO pro : pVO) {
				System.out.println(pro);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("(1)카테고리 | (2)상품담기 | (3)바로구매 | (4)장바구니 | (5)뒤로가기");
		System.out.print("메뉴 번호 입력: ");
		int allProductSelect = sc.nextInt();
		switch (allProductSelect) {
		case 1 -> category();
		case 2 -> cartInsert();
//		case 3 ->		// order Insert
		case 4 -> cart();
		case 5 -> mainPage();
		default ->
			System.out.println("잘못된 입력");
		}
	}
	
	public static void category() {
		ICategoryDAO cDAO = new CategoryDAO();
		ArrayList<CategoryVO> cVO = new ArrayList<CategoryVO>();
		
		try {
			cVO = cDAO.getAllCategories();
			for (CategoryVO cate : cVO) {
				System.out.println(cate);
			}
			
			System.out.print("조회할 카테고리 ID:");
			sc.nextLine();
			int selectCategory = sc.nextInt();
			
			ArrayList<ProductVO> prooVO = new ArrayList<ProductVO>();
			prooVO = proDAO.getProductBy(selectCategory);

			for (ProductVO detailcategory : prooVO) {
				System.out.println(detailcategory);
			}
			
			System.out.println("(1)상품담기 | (2)바로구매 | (3)장바구니 | (4)뒤로가기");
			System.out.print("메뉴 번호 입력: ");
			
			int selectCategorymenu = sc.nextInt();
			switch (selectCategorymenu) {
			case 1 -> cartInsert();
//			case 2 ->
			case 3 -> cart();
			case 4 -> mainPage();
			default -> System.out.println("잘못된 입력");
			}
//			} catch (RuntimeException e){
//				System.out.println(e.getMessage());
//			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	
	}
	
	
	// END PRODUCT ======================================================================
	
	
	
	public static void cart() {
		ArrayList<CartVO> cartList = cDAO.getAllCart(uVO.getUserId());
		for (CartVO cart : cartList) {
			System.out.println(cart);
		}
//		System.out.println(cartList);
	}
	
	public static void cartInsert() {
		System.out.print("장바구니에 추가할 상품 이름: ");
		sc.nextLine();
		String addProductName = sc.nextLine();
		proVO = proDAO.getProduct(addProductName);
		System.out.print("추가할 개수: ");
		int addProdunctCnt = sc.nextInt();	
		cVO.setUserId(uVO.getUserId());
		cVO.setProductId(proVO.getProductId());
		cVO.setCartCnt(addProdunctCnt);
		cVO.setTotalPrice(addProdunctCnt * proVO.getProductPrice());
		System.out.println(cVO);
		cDAO.insertCart(cVO);
		System.out.println("추가되었습니다.");
	}
	
	public static void order() {
		ArrayList<OrderVO> orderlist = oDAO.getOrder(uVO.getUserId());

	}
	
	public static void admin() {
		System.out.println("---------------------------------------------");
		System.out.println("(1)상품등록   | (2)상품수정   | (3)상품삭제    |(4)상품조회   ");
		System.out.println("---------------------------------------------");
		System.out.println("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			
			switch(num) {
			case 1 :
				System.out.println("***상품등록***");
				System.out.println("카테고리 ID: ");
				proVO.setCategoryId(sc.nextInt());
				sc.nextLine();
				System.out.println("상품 이름: ");
				proVO.setProductName(sc.nextLine());
				System.out.println("상품 가격: ");
				proVO.setProductPrice(sc.nextInt());
				sc.nextLine();
				System.out.println("상품 이미지: ");
				System.out.println(sc.nextLine());
				System.out.println("수량: ");
				System.out.println(sc.nextInt());
				sc.nextLine();
				try {
					proDAO.insertProduct(proVO);					
				}catch(RuntimeException e) {
					System.out.println(e.getMessage());
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
	
	
	// exit
	public static void exit() {
		System.exit(0);
	}
	
}
