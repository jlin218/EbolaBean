package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/pages/product.controller")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ghhaahhaah");
		String temp1 = request.getParameter("id");
		String name = request.getParameter("name");
		String temp2 = request.getParameter("price");
		String temp3 = request.getParameter("make");
		String temp4 = request.getParameter("expire");
		System.out.println("dfds");
		System.out.println(name);
		Map<String, String> errorMap = new HashMap<String, String>();
		int id=0;
		double price=0;
		Date make=null;
		int expire=0;

		if(temp1!=null&& temp1.trim().length()!=0){
			try {  
				id = Integer.parseInt(temp1);
			}  
			catch(NumberFormatException nfe)   {  
				errorMap.put("idError","id 不是一個合法的數字");
			}  
		}
		
//			try {  
//				id = Integer.parseInt(temp1);
//			}  
//			catch(NumberFormatException nfe)   {  
//				errorMap.put("idError","id 不是一個合法的數字");
//			}  
		
		if (temp2!=null&& temp2.trim().length()!=0) {
			try {
				price = Double.parseDouble(temp2);
			} catch (NumberFormatException nfe) {
				errorMap.put("priceError", "price 不是一個合法的數字");
			}
		}
		if (temp3!=null&& temp3.trim().length()!=0) {
			try {
				make = sFormat.parse(temp3.trim());
			} catch (Exception e) {
				errorMap.put("dateError", "Date 不是一個合法的日期");
			}
		}
		if (temp4!=null&& temp4.trim().length()!=0) {
			try {
				expire = Integer.parseInt(temp4);
			} catch (NumberFormatException nfe) {
				errorMap.put("expireError", "Expire 不是一個合法的日期");
			}
		}
		
		String button = request.getParameter("production");
		if (button!= null){
			if (button.equals("Insert")||button.equals("Update")||button.equals("Delete")){
				if(temp1==null||temp1.trim().length()==0||Integer.parseInt(temp1)<=0){
					errorMap.put("idError","id 不是一個合法的數字");
				}
				if(temp2==null|| temp2.trim().length()==0||Double.parseDouble(temp2)<=0){
					errorMap.put("priceError", "price 不是一個合法的數字");
				}
				if(temp3==null|| temp3.trim().length()==0){
					errorMap.put("dateError", "Date 不是一個合法的日期");
				}
				if(temp4==null||temp4.trim().length()==0||Integer.parseInt(temp4)<0){
					errorMap.put("expireError", "Expire 不是一個合法的日期");
				}
			}
		}
		if(errorMap!=null && !errorMap.isEmpty()){
			
			request.setAttribute("errorMap",errorMap);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
			rd.forward(request, response);
			return;
		}
		
		ProductBean pb = new ProductBean();
		pb.setId(id);
		pb.setName(name);
		pb.setExpire(expire);
		pb.setPrice(price);
		pb.setMake(make);
		ProductService service = new ProductService();
		if (button!= null){
			if (button.equalsIgnoreCase("insert")){
				ProductBean successful = service.insert(pb);
				if(successful!=null){
					request.setAttribute("msg", "insert successfully");
				}else{
					request.setAttribute("msg", "insert unsuccessfully");
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
				rd.forward(request, response);
				return;
				
			}else if(button.equalsIgnoreCase("Update")){
				ProductBean successful = service.update(pb); 
				if(successful!=null){
					request.setAttribute("msg", "update successfully");
				}else{
					request.setAttribute("msg", "update unsuccessfully");
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
				rd.forward(request, response);
				return;
				
			}else if(button.equalsIgnoreCase("Delete")){
				boolean successful = service.delete(pb);
				if(successful){
					request.setAttribute("msg", "Delete successfully");
				}else{
					request.setAttribute("msg", "Delete unsuccessfully");
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
				rd.forward(request, response);
				return;
				
			}else if(button.equalsIgnoreCase("Select")){
				
				List<ProductBean> list = service.select(pb);
				request.setAttribute("select", list);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/display.jsp");
				rd.forward(request, response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("im post");
		this.doGet(request, response);
		
	}

}
