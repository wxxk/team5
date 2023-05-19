package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.AdminDAO;
import model.AdminVO;
import model.CartDAO;
import model.CartVO;
import model.CategoryDAO;
import model.CategoryVO;
import model.IAdminDAO;
import model.ICategoryDAO;
import model.IOrderDAO;
import model.IProductDAO;
import model.IUsersDAO;
import model.OrderDAO;
import model.OrderVO;
import model.ProductDAO;
import model.ProductVO;
import model.UsersDAO;
import model.UsersVO;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Session s = new Session();

	public static IUsersDAO uDAO = new UsersDAO();
	public static UsersVO uVO = new UsersVO();

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
		uVO = uDAO.getUser(id);
		adminVO = aDAO.getAdmin(id);

		try {

			if (uVO!=null && id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserPassword())) {
			uVO = uDAO.getUser(id);
			}else if (adminVO!=null&&id.equals(adminVO.getAdminId()) && pwd.equals(adminVO.getAdminPassword())) {
					s.loginAdminId = adminVO.getAdminId();
					admin();
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
		case 1 : 
			category();
			break;
		case 2 :
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
			break;
		case 3 : 
			// order Insert
			break;
		case 4 :
			// cart Get All
			break;
		case 5 :
			mainPage();
			break;
		default :
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
			String selectCategory = sc.nextLine();

			//			try {
			//				cVO = cDAO.getCategory(selectCategory);
			//				for (CategoryVO detailcategory : cVO) {
			//					System.out.println(detailcategory);
			//				}

			proVO = proDAO.getProduct(selectCategory);
//			for (ProductVO detailcategory : proVO ) {
//				System.out.println(detailcategory);
//			}
			

			System.out.println("(1)상품담기 | (2)바로구매 | (3)장바구니 | (4)뒤로가기");
			System.out.print("메뉴 번호 입력: ");
			
			int selectCategorymenu = sc.nextInt();
			switch (selectCategorymenu) {
			case 1:
				CartVO ccVO = new CartVO();
				CartDAO ccDAO = new CartDAO();
				System.out.print("장바구니에 추가할 상품 이름: ");
				sc.nextLine();
				String addProductName = sc.nextLine();
				proVO = proDAO.getProduct(addProductName);
				System.out.print("추가할 개수: ");
				int addProdunctCnt = sc.nextInt();	
				ccVO.setUserId(uVO.getUserId());
				ccVO.setProductId(proVO.getProductId());
				ccVO.setCartCnt(addProdunctCnt);
				ccVO.setTotalPrice(addProdunctCnt * proVO.getProductPrice());
				System.out.println(cVO);
				ccDAO.insertCart(ccVO);
				System.out.println("추가되었습니다.");
				break;
			case 2:
				// order insert
				break;
			case 3:
				// cart get all 
				break;
			case 4:
				mainPage();
				break;
			default:
				System.out.println("잘못된 입력");
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

	}

	public static void order() {

	}
	// START ADMIN =======================================================================
	public static void admin() {
		System.out.println("---------------------------------------------");
		System.out.println("  (1)상품관리    |  (2)회원관리    |  (3)주문관리    |  (4)종료     ");
		System.out.println("---------------------------------------------");
		System.out.println("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();

			switch(num) {
			case 1 ->
				adminProduct();
				
			case 2 ->
				adminUserManagement();
				
			case 3 ->
				adminOrderManagement();
				
			case 4->
				exit();
			default ->
				System.out.println("잘못된 선택");
			}
		} catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Admin 상품관리 시작 ===================================================================
	public static void adminProduct() {
		System.out.println("***Admin Product Management***");
		System.out.println("---------------------------------------------");
		System.out.println("(1)상품등록   | (2)상품수정   | (3)상품삭제    |(4)상품조회   |(5)뒤로가기 ");
		System.out.println("---------------------------------------------");
		System.out.println("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 -> 
				adminProductInsert();
			case 2 ->
				adminProductUpdate();
			case 3 ->
				adminProductDelete();
			case 4 ->
				adminProductSearch();
			case 5 ->
				admin();
			default ->
				System.out.println("잘못된 선택");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminProductInsert() {
        System.out.println("***상품 등록***");
        System.out.println("카테고리 ID: ");
        proVO.setCategoryId(sc.nextInt());
        sc.nextLine();
        System.out.println("상품 이름: ");
        proVO.setProductName(sc.nextLine());
        System.out.println("상품 가격: ");
        proVO.setProductPrice(sc.nextInt());
        sc.nextLine();
        System.out.println("상품 이미지: ");
        proVO.setProductImg(sc.nextLine());
        System.out.println("수량: ");
        proVO.setCnt(sc.nextInt());
        sc.nextLine();
        try {
           proDAO.insertProduct(proVO); 
           System.out.println(proVO);
        }catch(RuntimeException e) {
           System.out.println(e.getMessage());
        }
        adminProduct();
	}
	
	public static void adminProductUpdate() {
		System.out.println("***상품 수정***");
		System.out.println("상품 ID: ");
		int productId = sc.nextInt();
		sc.nextLine();
		proVO.setProductId(productId);
		System.out.println("카테코리아이디: ");
		proVO.setCategoryId(sc.nextInt());
		sc.nextLine();
		System.out.println("상품명: ");
		proVO.setProductName(sc.nextLine());
		System.out.println("상품가격: ");
		proVO.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.println("상품 이미지: ");
		proVO.setProductImg(sc.nextLine());
		System.out.println("수량: ");
		proVO.setCnt(sc.nextInt());
		sc.nextLine();
		try {
			proDAO.updateProduct(proVO);
			System.out.println(proVO);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminProduct();
		
	}
	
	public static void adminProductDelete() {
		System.out.println("***상품 삭제***");
		System.out.println("상품ID: ");
		int productId = sc.nextInt();
		sc.nextLine();
		try {
		proDAO.deleteProduct(proVO);
		System.out.println(productId + "삭제완료");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminProduct();
	}
	
	public static void adminProductSearch() {
		try {
			IProductDAO pDAO = new ProductDAO();
			ArrayList<ProductVO> pVO = pDAO.getAllProducts();
			for (ProductVO pro : pVO) {
				System.out.println(pro);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminProduct();
	}
	// Admin 상품관리 끝 =====================================================================
	
	// Admin 회원관리 시작 ====================================================================
	public static void adminUserManagement() {
		System.out.println("***Admin User Management***");
		System.out.println("---------------------------------------------");
		System.out.println("(1)회원정보 조회   | (2)뒤로가기  ");
		System.out.println("---------------------------------------------");
		System.out.print("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 -> 
				adminUserSearch();
			case 2 ->
				adminUserDelete();
			case 3 ->
				admin();
			default ->
				System.out.println("잘못된 선택");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void adminUserSearch() {
		System.out.println("***회원 조회***");
		try {
			ArrayList<UsersVO> uVO = uDAO.getAlluers();
			for(UsersVO users : uVO) {
				System.out.println(users);
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminUserManagement();
	}
	public static void adminUserDelete() {
		
		System.out.println("***회원 삭제***");
		System.out.print("회원ID: ");
		String userId = sc.nextLine();
		uVO = new model.UsersVO(); //uVO초기화
		uVO.setUserId(userId);
		try {
		uDAO.deleteUser(uVO);
		System.out.println(userId + "삭제완료");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminUserManagement();
	}
	// Admin 회원관리 끝 =======================================================================
	
	// Admin 주문관리 시작 ======================================================================
	public static void adminOrderManagement() {
		System.out.println("***Admin Order Management***");
		System.out.println("---------------------------------------------");
		System.out.println("(1)주문조회   | (2)주문수정  | (3)주문취소    |(4)뒤로가기  ");
		System.out.println("---------------------------------------------");
		System.out.println("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 -> 
				adminOrderInsert();
			case 2 ->
				adminOrderUpdate();
			case 3 ->
				adminOrderDelete();
			case 4 ->
				admin();
			default ->
				System.out.println("잘못된 선택");
			}
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void adminOrderInsert() {
		System.out.println("***주문 전체 조회***");
		try {
			ArrayList<OrderVO> oVO = oDAO.getAllOrderList();
			for(OrderVO orders : oVO) {
				System.out.println(orders);
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminOrderManagement();
	}
	
	public static void adminOrderUpdate() {
		System.out.println("***주문 수정***");
		System.out.println("주문 ID: ");
		int orderId = sc.nextInt();
		sc.nextLine();
		oVO.setOrderId(orderId);
		System.out.println("옵션: ");
		oVO.setOptions(sc.nextLine());
		System.out.println("주소: ");
		oVO.setUserAddress(sc.nextLine());
		try {
			oDAO.updateOrder(oVO);
			System.out.println(oVO);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminOrderManagement();
		
	}
	
	
	public static void adminOrderDelete() {
		System.out.println("***주문 취소***");
		System.out.print("주문 ID: ");
		int orderId = sc.nextInt();
		sc.nextLine();
		oVO = new model.OrderVO(); //uVO초기화
		oVO.setOrderId(orderId);
		try {
		oDAO.deleteOrder(uVO);
		System.out.println(orderId + "취소완료");
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminOrderManagement();
	}
	// Admin 주문관리 끝 =========================================================================
	//END ADMIN ======================================================================
	// exit
	public static void exit() {
		System.exit(0);
	}

}
