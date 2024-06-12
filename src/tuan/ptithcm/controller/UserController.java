package ptithcm.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.dto.UserDTO;
import ptithcm.entity.User;
import ptithcm.utils.ExceptionHandlerUtil;

@Controller
public class UserController {
	@Autowired 
	SessionFactory factory;
	@RequestMapping(value = "logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
        session.removeAttribute("User");
        return "redirect:/login.htm";
    }
	@RequestMapping(value = "login",method = RequestMethod.GET)
    public String showLoginForm() {
        return "user/login";
    }
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	@Transactional
    public String login(HttpSession session, ModelMap model,HttpServletRequest request,@RequestParam("password") String password,@RequestParam("email") String email) {
		String hql = "SELECT new ptithcm.dto.UserDTO(u.id,u.email,u.fullname, u.role) FROM ptithcm.entity.User u WHERE u.email = :email AND u.password = :password";
		Session s = factory.getCurrentSession();
		UserDTO user = (UserDTO) s.createQuery(hql)
		                   .setParameter("email", email)
		                   .setParameter("password", password)
		                   .uniqueResult();


        if (user != null) {
            session.setAttribute("User", user);
            return "redirect:/index.htm";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "user/login";
        }
	}
	@RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public String register(HttpServletRequest request, 
    		@ModelAttribute("User") User user,
                           ModelMap model) {
		user.setFullname(user.getFullname().trim());
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return "redirect:login.htm";
        } catch (Exception e) {
        	model.addAttribute("User",user);
            ExceptionHandlerUtil.handleException(transaction, e, model);
        } finally {
            session.close();
        }
        return "user/register";
    }
	
	@RequestMapping(value = "sign-up",method = RequestMethod.GET)
    public String Gregister(HttpServletRequest request) {
		return "user/register";
	}
}
