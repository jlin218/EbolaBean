package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerBean;
import model.CustomerService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/secure/login.controller")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("username");
		String password= request.getParameter("password");
		System.out.println(userName+password);
		Map<String, String> error = new HashMap<String,String>();
		if (userName == null || userName.length()==0){
			error.put("username", "請輸入帳號");
		}
		if (password == null || password.length()==0){
			error.put("password", "請輸入密碼");
		}
		if(!error.isEmpty()){
			request.setAttribute("error", error);
			RequestDispatcher rd = request.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
			return;
		}
		CustomerService service = new CustomerService();
		CustomerBean bean = service.login(userName, password);
		
		if (bean!= null){
			request.getSession().setAttribute("user", bean);
			String path = request.getContextPath()+"/index.jsp";
			response.sendRedirect(path);
		}else{
			request.setAttribute("loginError", "帳號密碼配對錯誤");
			RequestDispatcher rd = request.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
		}
		
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
