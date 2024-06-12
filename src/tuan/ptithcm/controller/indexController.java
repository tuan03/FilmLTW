package ptithcm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.Mailer;
import ptithcm.dto.MovieWithViewsDTO;
import ptithcm.entity.Movie;
import ptithcm.entity.User;


@Controller
public class indexController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;
	
	@RequestMapping(value = "/testc", method = RequestMethod.GET)
	public String indexr(HttpServletRequest request) {
		return  "cinema";
	}
	@RequestMapping(value = "page", method = RequestMethod.GET)
	@Transactional
	public String homePage(@RequestParam("page") Long page,HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		
		String hql2 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
			       "FROM Movie m " +
			       "JOIN m.episodes e " +
			       "GROUP BY m.id, m.title " +
			       "ORDER BY m.createdAt DESC";
		List<MovieWithViewsDTO> newMovie = session.createQuery(hql2).list();
		
		int numPage = (newMovie.size()+7)/8;
		model.addAttribute("numPage",numPage);
		model.addAttribute("currentPage",page);
		
		List<MovieWithViewsDTO> result = newMovie.size() > page*8 ? newMovie.subList((int)(page-1)*8, (int)(page*8)) : newMovie.subList((int)(page-1)*8, newMovie.size()) ;
		model.addAttribute("newMovie",result);
		
		return "homePage";
	}
	
	
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@Transactional
	public String home(HttpServletRequest request, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql1 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
		           "FROM Movie m JOIN m.episodes e " +
		           "GROUP BY m.id, m.title " +
		           "ORDER BY SUM(e.views) DESC";
		List<MovieWithViewsDTO> top8_views_movies = session.createQuery(hql1).list();
		
		String hql2 = "SELECT new ptithcm.dto.MovieWithViewsDTO(m.id, m.title, m.posterUrl, SUM(e.views)) " +
			       "FROM Movie m " +
			       "JOIN m.episodes e " +
			       "GROUP BY m.id, m.title " +
			       "ORDER BY m.createdAt DESC";
		List<MovieWithViewsDTO> newMovie = session.createQuery(hql2).list();
		model.addAttribute("t8views",top8_views_movies.subList(0, 8));
		
		List<MovieWithViewsDTO> result = newMovie.size() > 8 ? newMovie.subList(0, 8) : newMovie;
		model.addAttribute("newMovie",result);
		int numPage = (newMovie.size()+7)/8;
		model.addAttribute("numPage",numPage);
		model.addAttribute("currentPage",1);
		return "home";
	}
	
	@RequestMapping(value = "mail", method = RequestMethod.GET)
	public String indexm(HttpServletRequest request) {
		String emailBody = "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <style>\n" +
				"        body {\n" +
				"            font-family: Arial, sans-serif;\n" +
				"            background-color: #f4f4f4;\n" +
				"            margin: 0;\n" +
				"            padding: 0;\n" +
				"        }\n" +
				"        .container {\n" +
				"            width: 100%;\n" +
				"            max-width: 600px;\n" +
				"            margin: 0 auto;\n" +
				"            background-color: #ffffff;\n" +
				"            border-radius: 8px;\n" +
				"            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
				"            overflow: hidden;\n" +
				"        }\n" +
				"        .header {\n" +
				"            background-color: #4CAF50;\n" +
				"            color: #ffffff;\n" +
				"            padding: 20px;\n" +
				"            text-align: center;\n" +
				"        }\n" +
				"        .content {\n" +
				"            padding: 20px;\n" +
				"        }\n" +
				"        .button {\n" +
				"            display: inline-block;\n" +
				"            background-color: #4CAF50;\n" +
				"            color: #ffffff;\n" +
				"            padding: 10px 20px;\n" +
				"            text-align: center;\n" +
				"            border-radius: 5px;\n" +
				"            text-decoration: none;\n" +
				"            font-size: 16px;\n" +
				"        }\n" +
				"        .footer {\n" +
				"            background-color: #f4f4f4;\n" +
				"            color: #777777;\n" +
				"            text-align: center;\n" +
				"            padding: 10px;\n" +
				"            font-size: 12px;\n" +
				"        }\n" +
				"    </style>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <div class=\"container\">\n" +
				"        <div class=\"header\">\n" +
				"            <h1>Khôi phục mật khẩu</h1>\n" +
				"        </div>\n" +
				"        <div class=\"content\">\n" +
				"            <p>Chào [Tên người dùng],</p>\n" +
				"            <p>Bạn đã yêu cầu khôi phục mật khẩu cho tài khoản của mình. Nhấn vào nút bên dưới để thiết lập lại mật khẩu:</p>\n" +
				"            <p style=\"text-align: center;\">\n" +
				"                <a href=\"[Link đặt lại mật khẩu]\" class=\"button\">Đặt lại mật khẩu</a>\n" +
				"            </p>\n" +
				"            <p>Nếu bạn không yêu cầu thay đổi mật khẩu, vui lòng bỏ qua email này.</p>\n" +
				"            <p>Trân trọng,</p>\n" +
				"            <p>Đội ngũ hỗ trợ</p>\n" +
				"        </div>\n" +
				"        <div class=\"footer\">\n" +
				"            <p>Bản quyền © 2024 Công ty của bạn. Tất cả các quyền được bảo lưu.</p>\n" +
				"        </div>\n" +
				"    </div>\n" +
				"</body>\n" +
				"</html>";

		mailer.send("lthloilth@gmail.com", "matrinh3@gmail.com", "Quên mật khẩu", emailBody);
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

	@RequestMapping("test")
	@Transactional
	public String cud() {
		Session session = factory.openSession();
		Transaction t = null;
		
		try {
			t= session.beginTransaction();
		Movie movie = new Movie();
		movie.setId(Long.valueOf(1));
		Movie find = (Movie)session.get(Movie.class, movie.getId());
			t.commit();
			System.out.print("Thành Công "+find.getTitle());
		} catch(StaleStateException e) {
			if (t != null) {
                t.rollback();
            }
			System.out.println("Xóa + cập nhật thật bại: " + e);
		}catch(ConstraintViolationException e) {
			if (t != null) {
                t.rollback();
            }
			System.out.print("Lỗi Xung Đột" + e );
		}
		catch (Exception e) {
			if (t != null) {
                t.rollback();
            }
			System.out.println("Lỗi: " + e);
		}  finally {
			session.close();
		}
		return "index";
	}
}
