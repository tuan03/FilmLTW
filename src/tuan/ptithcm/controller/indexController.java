package ptithcm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.User;

@Transactional
@Controller
@RequestMapping("/i")
public class indexController {
	@Autowired 
	SessionFactory factory;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String Glogin() {
		return "login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String Plogin(HttpServletRequest request) {
		System.out.print(request.getParameter("email"));
		return "login";
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexx(HttpServletRequest request) {
		return "home";
	}
	
	@RequestMapping("query")
	public String index() {
		Session session = factory.getCurrentSession();
		String HQL = "FROM User";
		Query query = session.createQuery(HQL);
//		List<User> list = query.list();
//		if (list.isEmpty()) {
//		    System.out.println("Không tìm thấy đối tượng nào phù hợp.");
//		} else {
//		    System.out.println(list); // In ra danh sách kết quả
//		}
		return "index";
	}
		
	@RequestMapping("set")
	public String setSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
	    session.setAttribute("tuann", "Tuấn nè");
	    String htmlContent = "<html><head><title>Error</title></head><body><h1>Authentication Failed</h1><p>Please log in to access this page.</p></body></html>";
        return "index";
	}
	
	@RequestMapping("cud")
	public String cud() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = new User();
		user.setEmail("mt3@gmail.com");
		try {
			session.save(user);
			t.commit();
			System.out.print("Thêm Thành Công");
		} catch (Exception e) {
			t.rollback();
			System.out.print("Thêm Thất Bại");
		} finally {
			session.close();
		}
		return "index";
	}
}
