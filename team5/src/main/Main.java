package main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.IAdminDAO;
import DAO.ICategoryDAO;
import DAO.IOrderDAO;
import DAO.IProductDAO;
import DAO.IProductDetailDAO;
import DAO.IUsersDAO;
import DAO.impl.AdminDAO;
import DAO.impl.CartDAO;
import DAO.impl.CategoryDAO;
import DAO.impl.OrderDAO;
import DAO.impl.OrderDetailDAO;
import DAO.impl.ProductDAO;
import DAO.impl.ProductDetailDAO;
import DAO.impl.UsersDAO;
import model.AdminVO;
import model.CartVO;
import model.CategoryVO;
import model.OrderDetailVO;
import model.OrderVO;
import model.ProductDetailVO;
import model.ProductVO;
import model.UsersVO;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Session s = new Session();

	public static IUsersDAO uDAO = new UsersDAO();
	public static UsersVO uVO = new UsersVO();

	static IOrderDAO oDAO = new OrderDAO();
	static OrderVO oVO = new OrderVO();

	public static OrderDetailDAO odDAO = new OrderDetailDAO();
	public static OrderDetailVO odVO = new OrderDetailVO(); 

	public static AdminVO adminVO = new AdminVO();
	public static IAdminDAO aDAO = new AdminDAO();

	public static ProductVO proVO = new ProductVO();
	public static IProductDAO proDAO = new ProductDAO();

	public static ProductDetailVO pdVO = new ProductDetailVO();
	public static IProductDetailDAO pdDAO = new ProductDetailDAO();

	public static CartVO cVO = new CartVO();
	public static CartDAO cDAO = new CartDAO();

	public static void main(String[] args) {
		while(true) {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("□□■■■■■■■■■■■■□□□■■■■■■■■■■■□□□□□□□■■■■■■□□□□□□■■■□□□□□□□□□□■■■□□■■■■■■■■■■■■■□□□□□");
			System.out.println("□□■■■■■■■■■■■■□□□■■■■■■■■■■■□□□□□□■■■□□■■■□□□□□■■■■■□□□□□□■■■■■□□■■■■■■■■■■■■■□□□□□");
			System.out.println("□□□□□■■■■■□□□□□□□■■■□□□□□□□□□□□□□■■■□□□□■■■□□□□■■■□■■□□□□■■□■■■□□■■■■□□□□□□□□□□□□□□");
			System.out.println("□□□□□■■■■■□□□□□□□■■■■■■■■■□□□□□□■■■■■■■■■■■■□□□■■■□□■■□□■■□□■■■□□■■■■■■■■■■■■■□□□□□");
			System.out.println("□□□□□■■■■■□□□□□□□■■■□□□□□□□□□□□■■■■■■■■■■■■■■□□■■■□□□■■■■□□□■■■□□□□□□□□□□□■■■■□□□□□");
			System.out.println("□□□□□■■■■■□□□□□□□■■■■■■■■■■■□□□■■■□□□□□□□□■■■□□■■■□□□□□□□□□□■■■□□■■■■■■■■■■■■■□□□□□");
			System.out.println("□□□□□■■■■■□□□□□□□■■■■■■■■■■■□□□■■■□□□□□□□□■■■□□■■■□□□□□□□□□□■■■□□■■■■■■■■■■■■■□□□□□");
			System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("\t \t \t (1)로그인 | (2)회원가입 | (9)종료 ");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.print("메뉴 번호 입력: ");
			String input = sc.nextLine();
			try {
				int num = Integer.parseInt(input);
				switch(num) {
				case 1 :
					login();
					break;
				case 2 :
					register();
					break;
				case 9 : 
					exit();
					break;
				default:
					System.out.println("잘못된 선택");
				}
			} catch(NumberFormatException e) {
				System.out.println("잘못된 입력");

			}
		}
	}

	public static void mainPage() {
		while(true) {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("\t \t \t \t" + uVO.getUserId() + "님 안녕하세요.");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("\t \t (1)회원정보 | (2)상품보기 | (3)로그아웃 | (4)장바구니 | (5)주문내역 | (9)종료");
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.print("메뉴 번호 입력: ");
			String input = sc.nextLine();
			try {   
				int num = Integer.parseInt(input);
				switch(num) {

				// 회원정보
				case 1 :
					// 회원 정보 출력
					System.out.println("-----------------------------------------------------------------------------------");
					System.out.println("\t \t \t \t ***회원정보***");
					System.out.println("-----------------------------------------------------------------------------------");
					uVO = uDAO.getUser(uVO.getUserId());
					System.out.println("userId   |   "+"userName   |   "+"userBirth   |   "+"userPhoneNumber   |   "+"userAddress   |   ");
					System.out.println("-----------------------------------------------------------------------------------");
					System.out.println(uVO);
					System.out.println("-----------------------------------------------------------------------------------");
					
					
					System.out.println("\t\t\t (1)회원정보수정 | (2)회원탈퇴 | (3)뒤로가기 | (9)종료");
					System.out.print("메뉴 번호 입력: ");
					String input1 = sc.nextLine();
					int userMenuSelect = Integer.parseInt(input1);
					
					switch(userMenuSelect) {
					case 1 : 
						updateUser();
						break;
					case 2 :
						System.out.println("\t\t\t\t ***회원 탈퇴***");
						System.out.println("-----------------------------------------------------------------------------------");
						System.out.print("아이디 : ");
						String id = sc.nextLine();
						System.out.print("비밀번호 : ");
						String pwd = sc.nextLine();
						if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserPassword())) {
							deleteUser();
							System.out.println("탈퇴되었습니다.");
							main(null);
						} else {
							System.out.println("비밀번호가 일치하지 않습니다");
							mainPage();
						}
						break;
					case 3: 
						mainPage();
						break;	
					case 9:
						exit();
					default :
						System.out.println("잘못된 입력");
					}

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
				case 9:
					exit();
				default:
					System.out.println("잘못된 선택");
				}
			} catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// START USER ===========================================================================
	public static void login() {   
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		uVO = uDAO.getUser(id);
		adminVO = aDAO.getAdmin(id);

		try {
			if (uVO!=null && id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserPassword()) && uVO.getStated() == 1) {
				s.loginUserId = uVO.getUserId();
				mainPage();
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
			System.out.println("회원가입이 완료되었습니다.");
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void updateUser() {
		// 이름, 핸드폰번호, 주소 수정
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t*** 수  정 ***");
		System.out.println("-----------------------------------------------------------------------------------");

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
		mainPage();
	}

	public static void deleteUser() {
		try {
			uVO.setStated(0);
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
			System.out.println("카테고리 ID  |" + "  상품 ID  |"  +
					"    상품 이름      |" + "    상품 가격      |" +
					"      상품 이미지         |");
			System.out.println("---------------------------------------------------------------------");
			for (ProductVO pro : pVO) {
				System.out.println(pro);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("(1)카테고리 | (2)상품담기 | (3)바로구매 | (4)장바구니 | (5)뒤로가기 | (9)종료");
		System.out.print("메뉴 번호 입력: ");
		String input = sc.nextLine();
		try {
			int allProductSelect = Integer.parseInt(input);
			switch (allProductSelect) {
			case 1 -> category();
			case 2 -> cartInsert();
			case 3 -> insertOrder();
			case 4 -> cart();
			case 5 -> mainPage();
			case 9 -> exit();
			default ->
			System.out.println("잘못된 입력");
			}
		} catch (NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}

	public static void category() {
		ICategoryDAO cDAO = new CategoryDAO();
		ArrayList<CategoryVO> cVO = new ArrayList<CategoryVO>();

		try {
			cVO = cDAO.getAllCategories(); //전체 카테고리 목록 보여주기
			System.out.println("--------------------------------------------------------");
			System.out.println("\t\t카테고리ID    |    "+"카테고리 이름");
			System.out.println("--------------------------------------------------------");
			for (CategoryVO cate : cVO) {
				System.out.println("\t\t"+cate);
			}

			System.out.println("--------------------------------------------------------");
			System.out.print("조회할 카테고리 ID : ");
			int selectCategory = sc.nextInt();
			sc.nextLine();
			System.out.println("--------------------------------------------------------");

			ArrayList<ProductVO> prooVO = new ArrayList<ProductVO>();
			prooVO = proDAO.getProductBy(selectCategory); //선택한 카테고리의 상품 보여주기

			System.out.println("카테고리 ID  |" + "  상품 ID  |"  +
					"    상품 이름      |" + "    상품 가격      |" +
					"      상품 이미지         |");
			System.out.println("---------------------------------------------------------------------");

			for (ProductVO detailcategory : prooVO) {
				System.out.println(detailcategory);
			}

			System.out.println("(1)상품담기 | (2)바로구매 | (3)장바구니 | (4)뒤로가기 | (9)종료");
			System.out.print("메뉴 번호 입력: ");
			String input = sc.nextLine();
			try {
				int selectCategorymenu = Integer.parseInt(input);
				switch (selectCategorymenu) {
				case 1 -> cartInsert();
				case 2 -> insertOrder();
				case 3 -> cart();
				case 4 -> mainPage();
				case 9 -> exit();
				default -> System.out.println("잘못된 입력");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

	}
	// END PRODUCT ======================================================================
 

	// START CART========================================================================
	public static void cart() {
		ArrayList<CartVO> cartList = cDAO.getAllCart(uVO.getUserId());

		if (cartList.size() == 0) {
			System.out.println("장바구니가 비어있습니다.");
			System.out.println("-----------------------------------------");
			System.out.println("(1)상품보기 | (2)메인으로");
			System.out.print("메뉴 입력 : ");
			String input = sc.nextLine();
			try {
				int num = Integer.parseInt(input);
				switch (num) {
				case 1 -> product();
				case 2 -> mainPage();
				default -> System.out.println("잘못된 입력!");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("카트ID\t|      "+"상품이름 \t  |    "+"카테고리이름      |\t    "+"옵션\t   |  "+"수량    |  "+"총가격");
			System.out.println("-----------------------------------------------------------------------------------------------");
			for (CartVO cart : cartList) {
				System.out.println(cart);            
			}
			System.out.println("(1)구매하기 | (2)뒤로가기 | (3)삭제하기 | (4)수량변경 | (9)종료");
			System.out.print("메뉴 번호 입력: ");
			String input = sc.nextLine();
			try {
				int cartmenu = Integer.parseInt(input);
				switch (cartmenu) {
				case 1 -> orderInsertCart(cartList);
				case 2 -> mainPage();
				case 3 -> deleteCart();
				case 4 -> updateCart();
				case 9 -> exit();
				default -> System.out.println("잘못된 입력");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	public static void cartInsert() {
		System.out.print("장바구니에 추가할 상품 ID: ");
		int addProductName = sc.nextInt();
		sc.nextLine();
		proVO = proDAO.getProduct(addProductName);

		// product detail
		ArrayList<ProductDetailVO> productDetailList = pdDAO.getProductDetail(proVO.getProductId());

		// 옵션 선택
		System.out.println("\t\t    ***제품 옵션(수량)***");
		for (ProductDetailVO productDetail : productDetailList) {
			System.out.print("\t\t" + productDetail.getOptions() + "(" + productDetail.getCnt() + ")");
		}
		System.out.println();
		System.out.println("--------------------------------------------------------------------------");
		System.out.print("옵션 선택:");
		String productOption = sc.nextLine();
		boolean isOption = false;
		
		for (ProductDetailVO productDetail : productDetailList) {
			if (productOption.equals(productDetail.getOptions())) {
				isOption = true;
			}
		}
		if (!isOption) {
			System.out.println("유효하지 않은 옵션입니다.");
			cartInsert();
		}

		System.out.print("추가할 개수: ");
		int addProductCnt = sc.nextInt();   
		sc.nextLine();

		ProductDetailVO pdVO = pdDAO.getProductD(addProductName, productOption);

		if (addProductCnt > pdVO.getCnt()) {
			System.out.println("재고 부족");
			cartInsert();
		} else {
			cVO.setUserId(uVO.getUserId());
			cVO.setProductId(proVO.getProductId());
			cVO.setProductName(proVO.getProductName());
			cVO.setCartCnt(addProductCnt);
			cVO.setTotalPrice(proVO.getProductPrice() * addProductCnt);
			cVO.setOptions(productOption);

			cDAO.insertCart(cVO);
			System.out.println(cVO.getProductName() + " " + cVO.getCartCnt() + "개가 추가되었습니다.");
		}

		System.out.println("(1)메인페이지 | (2)상품보기 | (3)장바구니 | (9)종료");
		System.out.print("메뉴 번호 입력: ");
		int cartInsertSelectMenu = sc.nextInt();
		sc.nextLine();
		try {
			switch (cartInsertSelectMenu) {
			case 1 -> mainPage();
			case 2 -> product();
			case 3 -> cart();
			case 9 -> exit();
			default -> System.out.println("잘못된 메뉴");
			}
		} catch (InputMismatchException e) {
			System.out.println("잘못된 메뉴");
		}
	}

	public static void deleteCart() {
		System.out.print("삭제할 카트 ID: ");
		int deleteCartNumber = sc.nextInt();
		sc.nextLine();
		cDAO.deleteCart(deleteCartNumber);
		cart();
	}

	public static void updateCart() {
		System.out.print("수량을 바꿀 카트 ID: ");
		int updateCartId = sc.nextInt();
		sc.nextLine();
		
		System.out.print("바꿀 수량: ");
		int updateCartCnt = sc.nextInt();
		sc.nextLine();
		
		cVO = cDAO.getOrderCart(updateCartId);
		proVO = proDAO.getProduct(cVO.getProductId()); 
		pdVO = pdDAO.getProductD(cVO.getProductId(), cVO.getOptions());
		
		if (updateCartCnt > pdVO.getCnt()) {
			System.out.println("--------------------------------------------------");
			System.out.println("재고가 부족합니다.");
			System.out.println("남은 재고 : " + pdVO.getCnt());
			System.out.println("--------------------------------------------------");
			updateCart();
		}
		
		if (updateCartCnt == 0) {
			System.out.println("0개 이하로 수정할 수 없습니다.");
			System.out.println("--------------------------------------------------");
			System.out.println("(1)수량변경 | (2)장바구니");
			System.out.print("메뉴 입력 : ");
			String input = sc.nextLine();
			int num = Integer.parseInt(input);
			switch (num) {
			case 1 -> updateCart();
			case 2 -> cart();
			default -> System.out.println("잘못된 메뉴");
			}	
		}
		int totalPrice = updateCartCnt * proVO.getProductPrice();
		cDAO.updateCart(updateCartCnt, totalPrice, updateCartId);
	}
	// END CART ========================================================================= 

	// START ORDER ======================================================================
	public static void order() {
		ArrayList<OrderVO> orderlist = oDAO.getOrderList(uVO.getUserId());
		System.out.println("주문ID\t|     "+"상품이름        |    "+"상품가격       |    "+"회원이름      |         "+
				"회원주소              |      "+"회원전화번호         |    "+"상품옵션      |  "+"주문총가격");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------");
		
		for (OrderVO order : orderlist) {
			System.out.println(order);
		}
		
		System.out.println("(1)메인 페이지 | (2)상품보기 | (3)장바구니 | (9)종료");
		System.out.print("메뉴 입력 : ");
		String input = sc.nextLine();
		int num = Integer.parseInt(input);
		try {
			switch(num) {
			case 1 -> mainPage();
			case 2 -> product();
			case 3 -> cart();
			case 9 -> exit();
			default -> System.out.println("잘못된 입력!");
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void orderInsertCart(List <CartVO> cl) {
		List <CartVO> cartlist = new ArrayList<CartVO>();

		System.out.println("***카트 구매***");
		System.out.println("(1)전체구매 | (2)선택구매");
		System.out.print("메뉴입력 : ");
		String input = sc.nextLine();
		int addnum = Integer.parseInt(input);

		switch(addnum) {
		case 1:
			oDAO.insertCartOrder(cl);
			break;
		case 2:
			while (true) {
				System.out.print("카트 ID: ");
				int cartId = sc.nextInt();
				sc.nextLine();
				try {
					cVO = cDAO.getOrderCart(cartId);
					cartlist.add(cVO);
					System.out.println("더 추가하시겠습니까?");
					System.out.println("(1)YES | (2)NO");
					System.out.print("메뉴 입력 : ");
					int num = sc.nextInt();
					sc.nextLine();
					if (num == 2) {
						break;
					}
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}

			}
			oDAO.insertCartOrder(cartlist);
		}
		order();
	}

	public static void insertOrder() {
		oVO.setUserId(uVO.getUserId());

		System.out.print("구매할 제품 ID : ");
		int insertOrderProductId = sc.nextInt();
		sc.nextLine();
		
		proVO = proDAO.getProduct(insertOrderProductId);
		ArrayList<ProductDetailVO> productDetailList = pdDAO.getProductDetail(proVO.getProductId());

		System.out.println("\t\t\t ***제품 옵션(수량)***");
		
		for (ProductDetailVO productDetail : productDetailList) {
			System.out.print("\t\t" + productDetail.getOptions() + "(" + productDetail.getCnt() + ")");
		}
		System.out.println();

		System.out.print("옵션 선택 : ");
		String orderDetailOptions = sc.nextLine();
		boolean isOption = false;
		
		for (ProductDetailVO productDetail : productDetailList) {
			if (orderDetailOptions.equals(productDetail.getOptions())) {
				isOption = true;
			}
		}
		
		if (!isOption) {
			System.out.println("유효하지 않은 옵션입니다!");
		}

		System.out.print("구매할 개수 : ");
		int cnt = sc.nextInt();
		sc.nextLine();

		pdVO = pdDAO.getProductD(insertOrderProductId, orderDetailOptions);

		if (cnt > pdVO.getCnt()) {
			System.out.println("재고가 부족합니다.");
			insertOrder();
		}
		if (cnt == 0) {
			System.out.println("1개 이상 입력해주세요.");
			insertOrder();
		}

		oVO.setUserId(uVO.getUserId());
		oVO.setProductId(proVO.getProductId());
		oVO.setTotalPrice(proVO.getProductPrice() * cnt);

		try {
			oDAO.insertProductOrder(oVO, cnt, orderDetailOptions);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	// END ORDER ========================================================================

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
		System.out.println("(1)상품등록   | (2)상품수정   | (3)상품삭제    | (4)상품조회   | (5)뒤로가기 ");
		System.out.println("---------------------------------------------");
		System.out.print("번호를 입력하세요: ");
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
		try {
			proDAO.insertProduct(proVO); 
			System.out.println(proVO);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminProductDetailInsert();
	}
	
	public static void adminProductDetailInsert() {
		System.out.println("***상품 디테일 등록***");
		System.out.println("상품ID: ");
		int productId = sc.nextInt();
		sc.nextLine();
		pdVO.setProductId(productId);
		System.out.println("옵션: ");
		pdVO.setOptions(sc.nextLine());
		System.out.println("재고: ");
		pdVO.setCnt(sc.nextInt());
		sc.nextLine();
		try {
			pdDAO.insertProductDetail(pdVO);
			System.out.println(pdVO);
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
		System.out.println("카테고리 아이디: ");
		proVO.setCategoryId(sc.nextInt());
		sc.nextLine();
		System.out.println("상품명: ");
		proVO.setProductName(sc.nextLine());
		System.out.println("상품가격: ");
		proVO.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.println("상품 이미지: ");
		proVO.setProductImg(sc.nextLine());
		try {
			proDAO.updateProduct(proVO);
			System.out.println(proVO);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		adminProductDetailUpdate();

	}
	
	public static void adminProductDetailUpdate() {
		System.out.println("***상품 디테일 수정***");
		System.out.println("상품ID: ");
		int productId = sc.nextInt();
		pdVO.setProductId(productId);
		System.out.println("재고: ");
		pdVO.setCnt(sc.nextInt());
		sc.nextLine();
		System.out.println("옵션: ");
		pdVO.setOptions(sc.nextLine());
		try {
			pdDAO.updateProductDetail(pdVO);
			System.out.println(pdDAO);
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
		System.out.println("상품 옵션: ");
		String options = sc.nextLine();
		pdVO = pdDAO.getProductD(productId, options);
		System.out.println("상품 재고: ");
		pdVO.setCnt(sc.nextInt());
		sc.nextLine();
		try {
			pdDAO.updateProductDetail(pdVO);
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
		System.out.println("(1)회원정보 조회   | (2)회원 삭제   | (3)뒤로가기  ");
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
//			"아이디 : " + userId + "\n" + "이름 : " + userName + "\n" +
//			"생년월일 : " + userBirth +  "\n" + "핸드폰 번호 : " + userPhoneNumber + "\n"+ 
//			"주소 : " + userAddress;
			System.out.println("   아이디\t|\t"+"이름\t  |     "+"생년월일       |      "+"핸드폰 번호          |\t\t"+"주소  ");
			System.out.println("----------------------------------------------------------------------------------------------");
			
			
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
		
		uVO = uDAO.getUser(userId);
		
		System.out.println("---------------------------------");
		
		System.out.print("*** 현재 상태 :");
		if (uVO.getStated() == 1) {
			System.out.println("활성화 ***");
		} else if (uVO.getStated() == 0) {
			System.out.println("비활성화 ***");
		}
		System.out.println("상태변경");
		System.out.println("(1)YES | (2)NO");
		System.out.print("메뉴 입력 : ");
		String input = sc.nextLine();
		int num = Integer.parseInt(input);
		switch (num) {
		case 1:
			try {
				if (uVO.getStated() == 1) {
					uVO.setStated(0);
					uDAO.deleteUser(uVO);
					System.out.println(userId + "님의 회원상태를 비활성화 하였습니다.");
				} else if (uVO.getStated() == 0) {
					uVO.setStated(1);
					uDAO.deleteUser(uVO);
					System.out.println(userId + "님의 회원상태를 활성화 하였습니다.");
				}
			}catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			adminUserManagement();
		}

		adminUserManagement();
	}
	// Admin 회원관리 끝 =======================================================================

	// Admin 주문관리 시작 ======================================================================
	public static void adminOrderManagement() {
		System.out.println("***Admin Order Management***");
		System.out.println("---------------------------------------------");
		System.out.println("(1)주문조회   | (2)뒤로가기   ");
		System.out.println("---------------------------------------------");
		System.out.println("번호를 입력하세요: ");
		try {
			int num = sc.nextInt();
			sc.nextLine();
			switch(num) {
			case 1 -> 
			adminOrderInsert();
			case 2 ->
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




	// Admin 주문관리 끝 =========================================================================
	//END ADMIN ======================================================================
	// exit
	public static void exit() {
		System.exit(0);
	}

}