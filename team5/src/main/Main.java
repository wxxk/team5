package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.AdminDAO;
import DAO.CategoryDAO;
import DAO.IAdminDAO;
import DAO.ICategoryDAO;
import DAO.IOrderDAO;
import DAO.IProductDAO;
import DAO.IProductDetailDAO;
import DAO.IUsersDAO;
import DAO.impl.CartDAO;
import DAO.impl.OrderDAO;
import DAO.impl.ProductDAO;
import DAO.impl.ProductDetailDAO;
import DAO.impl.UsersDAO;
import model.AdminVO;
import model.CartVO;
import model.CategoryVO;
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

   public static UsersVO user = new UsersVO();
   public static UsersDAO userDAO = new UsersDAO();

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
         System.out.println("\t \t \t (1)로그인 | (2)회원가입 | (3)종료 ");
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
            case 3 : 
               exit();
               break;
            case 9 :
               admin();
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
         System.out.println("\t \t (1)회원정보 | (2)상품보기 | (3)로그아웃 | (4)장바구니 | (5)주문내역");
         System.out.println("-----------------------------------------------------------------------------------");
         System.out.print("메뉴 번호 입력: ");
         String input = sc.nextLine();
         try {   
            int num = Integer.parseInt(input);
            switch(num) {

            // 회원정보
            case 1 :
               System.out.println("-----------------------------------------------------------------------------------");
               System.out.println("\t \t \t \t ***회원정보***");
               System.out.println("-----------------------------------------------------------------------------------");
               uVO = uDAO.getUser(uVO.getUserId());
               System.out.println(uVO);
               System.out.println("-----------------------------------------------------------------------------------");
               System.out.println("\t\t\t (1)회원정보수정 | (2)회원탈퇴 | (3)뒤로가기");
               System.out.print("메뉴 번호 입력: ");
               String input1 = sc.nextLine();
               int userMenuSelect = Integer.parseInt(input1);
               switch(userMenuSelect) {
               case 1 : 
                  updateUser();
                  break;
               case 2 :
                  System.out.println("***회원 탈퇴***");
                  System.out.print("아이디 : ");
                  String id = sc.nextLine();
                  System.out.print("비밀번호 : ");
                  String pwd = sc.nextLine();
                  if (id.equals(uVO.getUserId()) && pwd.equals(uVO.getUserId())) {
                     deleteUser();
                     main(null);
                  } else {
                     System.out.println("비밀번호가 일치하지 않습니다");
                  }
                  break;
               case 3: 
                  mainPage();
                  break;
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
      String input = sc.nextLine();
      try {
         int allProductSelect = Integer.parseInt(input);
         switch (allProductSelect) {
         case 1 -> category();
         case 2 -> cartInsert();
         case 3 -> orderInsert();
         case 4 -> cart();
         case 5 -> mainPage();
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
         String input = sc.nextLine();
         try {
            int selectCategorymenu = Integer.parseInt(input);
            switch (selectCategorymenu) {
            case 1 -> cartInsert();
            case 2 -> orderInsertCart(cl);
            case 3 -> cart();
            case 4 -> mainPage();
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
      for (CartVO cart : cartList) {
         System.out.println(cart);            
      }
      System.out.println("(1)구매하기 | (2)뒤로가기 | (3)삭제하기 | (4)수량변경");
      System.out.print("메뉴 번호 입력: ");
      String input = sc.nextLine();
      try {
         int cartmenu = Integer.parseInt(input);
         switch (cartmenu) {
         case 1 -> orderInsertCart(cartList);
         case 2 -> mainPage();
         case 3 -> deleteCart();
         case 4 -> updateCart();
         default -> System.out.println("잘못된 입력");
         }
      } catch (NumberFormatException e) {
         System.out.println(e.getMessage());
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
      for (ProductDetailVO productDetail : productDetailList) {
         System.out.println("제품 옵션" + productDetail.getOptions());
         System.out.println("남은 수량" + productDetail.getCnt());
      }
      System.out.print("옵션 선택:");
      String productOption = sc.nextLine();

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
         cVO.setOptions(productOption);
         cDAO.insertCart(cVO);
         System.out.println(cVO.getProductName() + " " + cVO.getCartCnt() + "개가 추가되었습니다.");
      }

      System.out.println("(1)메인페이지 | (2)장바구니");
      System.out.print("메뉴 번호 입력: ");
      int cartInsertSelectMenu = sc.nextInt();
      sc.nextLine();
      try {
         switch (cartInsertSelectMenu) {
         case 1 -> mainPage();
         case 2 -> cart();
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
   }

   public static void updateCart() {
      System.out.print("수량을 바꿀 카트 ID: ");
      int updateCartId = sc.nextInt();
      sc.nextLine();
      System.out.print("바꿀 수량: ");
      int updateCartCnt = sc.nextInt();
      sc.nextLine();
      cDAO.updateCart(updateCartCnt, updateCartId);
   }
   // END CART =========================================================================

   // START ORDER ======================================================================
   public static void order() {
      ArrayList<OrderVO> orderlist = oDAO.getOrderList(uVO.getUserId());
      for (OrderVO order : orderlist) {
         System.out.println(order);
      }
   }

   public static void orderInsertCart(List <CartVO> cl) {
	   	System.out.println("***카트 구매***");
	   	System.out.println("카트 ID: ");
	   	int cartId = sc.nextInt();
	   	sc.nextLine();
	   	System.out.println(cartId);
	   	cVO = cDAO.getOrderCart(cartId);
	   	try {
	   		oDAO.insertCartOrder(s.loginUserId, cVO.getProductId(), cVO.getCartId(), cl);
	        System.out.println(oDAO);
	      }catch(RuntimeException e) {
	         System.out.println(e.getMessage());
	      }
	     // product();
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
      //        proVO.setCnt(sc.nextInt());
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
      //      proVO.setCnt(sc.nextInt());
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
      System.out.println("(1)주문조회   | (2)주문수정  | (3)뒤로가기   ");
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


   // Admin 주문관리 끝 =========================================================================
   //END ADMIN ======================================================================
   // exit
   public static void exit() {
      System.exit(0);
   }

}