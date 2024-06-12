package ptithcm.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.Mailer;
import ptithcm.dto.ContentDTO;
import ptithcm.dto.MovieWithViewsDTO;
import ptithcm.dto.UserDTO;
import ptithcm.entity.Comment;
import ptithcm.entity.Episode;
import ptithcm.entity.Genre;
import ptithcm.entity.Movie;
import ptithcm.entity.Reply;
import ptithcm.entity.User;
import ptithcm.utils.ExceptionHandlerUtil;
import ptithcm.utils.RandomPassword;


@Controller
public class ForgotpassController {
	@Autowired 
	SessionFactory factory;
	
	@Autowired
	Mailer mailer;

	
	@RequestMapping(value = "forgot-password", method = RequestMethod.GET)
    public String fgp(ModelMap model) {
        return "user/forgot-password";
    }
	
	@RequestMapping(value = "forgot-password", method = RequestMethod.POST)
    public String fgpp(@RequestParam("email") String email,ModelMap model) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = (User) session.createQuery("SELECT u FROM User u WHERE u.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();
            
			if (user == null) {
				throw new RuntimeException("User not found with email: " + email);
			}
			System.out.print(user.getEmail());
			String randomPass = RandomPassword.generateRandomString(8);
			user.setPassword(randomPass);

			transaction.commit();
			
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
						"            <p>Chào "+ user.getFullname() +",</p>\n" +
						"            <p>Bạn đã quên mật khẩu cho tài khoản của mình. Đây là một mật khẩu thay thế được chúng tôi tạo ra:</p>\n" +
						"            <p style=\"text-align: center;\">\n" +
						"                <button class=\"button\">"+ user.getPassword() +"</button>\n" +
						"            </p>\n" +
						"            <p>Nếu bạn không yêu cầu thay đổi mật khẩu, vui lòng liên hệ chúng tôi.</p>\n" +
						"            <p>Trân trọng,</p>\n" +
						"            <p>Đội ngũ hỗ trợ</p>\n" +
						"        </div>\n" +
						"        <div class=\"footer\">\n" +
						"            <p>Bản quyền © 2024 Công ty của bạn. Tất cả các quyền được bảo lưu.</p>\n" +
						"        </div>\n" +
						"    </div>\n" +
						"</body>\n" +
						"</html>";
			mailer.send("lthloilth@gmail.com", email, "Quên mật khẩu", emailBody);
            return "redirect:/login.htm";
        } catch (Exception e) {
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "user/forgot-password";
    }
}
